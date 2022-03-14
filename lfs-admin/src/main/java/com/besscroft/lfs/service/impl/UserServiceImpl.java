package com.besscroft.lfs.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.besscroft.lfs.converter.UserConverterMapper;
import com.besscroft.lfs.dto.AuthUserExcelDto;
import com.besscroft.lfs.entity.AuthResource;
import com.besscroft.lfs.entity.AuthRole;
import com.besscroft.lfs.entity.AuthUser;
import com.besscroft.lfs.model.LFSUser;
import com.besscroft.lfs.repository.UserRepository;
import com.besscroft.lfs.service.MenuService;
import com.besscroft.lfs.service.ResourceService;
import com.besscroft.lfs.service.UserService;
import com.besscroft.lfs.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Bess Croft
 * @Time 2021/7/7 15:55
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final ResourceService resourceService;
    private final JWTUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    private MenuService menuService;

    @Autowired
    public void setUserService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 先调用DAO层查询用户实体对象
        AuthUser user = userRepository.findByUsername(username);
        // 若没查询到一定要抛出该异常，这样才能被Spring Security的错误处理器处理
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        // 查询权限集合
        List<AuthResource> resourceList = resourceService.getResourceList(user.getId());
        // 返回自定义的 UserDetail 对象
        return new LFSUser(user, resourceList);
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        // 密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            LOGGER.info("UserDetails:{}", JSONUtil.toJsonStr(userDetails));
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new RuntimeException("密码不正确");
            }
            if(!userDetails.isEnabled()){
                throw new RuntimeException("帐号已被禁用");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtUtils.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public AuthUser getCurrentAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
            log.info("currentUserName:{}", JSONUtil.toJsonStr(currentUserName));
        } else {
            throw new RuntimeException("暂未登录或token已经过期");
        }
        return userRepository.findByUsername(currentUserName);
    }

    @Override
    public AuthUser getCurrentAdminByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> getUserInfo() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AuthUser currentAdmin = getCurrentAdminByUserName(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", currentAdmin.getUsername());
        data.put("menus", menuService.getMenuList(currentAdmin.getId()));
        data.put("icon", currentAdmin.getIcon());
        List<AuthRole> roleList = getRoleList(currentAdmin.getId());
        if(CollUtil.isNotEmpty(roleList)){
            List<String> roles = roleList.stream().map(AuthRole::getName).collect(Collectors.toList());
            data.put("roles",roles);
        }
        // 设置登录时间
        setLoginTime(new Date(), currentAdmin.getId());
        return data;
    }

    @Override
    public List<AuthRole> getRoleList(Long userId) {
        return userRepository.findById(userId).get().getRoles();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean setLoginTime(Date loginTime, Long id) {
        return userRepository.updateLoginTime(loginTime, id) > 0;
    }

    @Override
    public boolean logout(Long adminId) {
        return true;
    }

    @Override
    public Page<AuthUser> getUserPageList(Integer pageNum, Integer pageSize, String keyword) {
        return userRepository.findAll(PageRequest.of(Objects.equals(pageNum, 0) ? 0 : pageNum - 1, pageSize));
    }

    @Override
    public AuthUser getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUser(AuthUser authUser) {
        return userRepository.save(authUser) != null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean changeSwitch(boolean flag, Long id) {
        int status;
        if (flag == true) {
            status = 1;
        } else {
            status = 0;
        }
        return userRepository.changeSwitch(status, id) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delUser(Long id) {
        userRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addUser(AuthUser authUser) {
        // 设置用户注册的时间
        authUser.setCreateTime(LocalDateTime.now());
        // 设置用户登录时间与注册时间一致
        authUser.setLoginTime(LocalDateTime.now());
        // 加密密码
        authUser.setPassword(new BCryptPasswordEncoder().encode(authUser.getPassword()));
        // 设置删除状态
        authUser.setDel(1);
        return userRepository.save(authUser) != null;
    }

    @Override
    public void export(List<Long> ids, HttpServletResponse response) {
        List<AuthUser> userList = userRepository.findAllById(ids);
        if (CollUtil.isNotEmpty(userList)) {
            List<AuthUserExcelDto> excelDtos = UserConverterMapper.INSTANCE.authUserToAuthUserExcelListDto(userList);
            excelDtos.forEach(excelDto -> {
                String status = excelDto.getStatus();
                switch (status) {
                    case "0":
                        excelDto.setStatus("禁用");
                        break;
                    case "1":
                        excelDto.setStatus("启用");
                        break;
                }
                String del = excelDto.getDel();
                switch (del) {
                    case "0":
                        excelDto.setDel("已删除");
                        break;
                    case "1":
                        excelDto.setDel("可用状态");
                        break;
                }
            });
            try {
                // 这里注意 有同学反应使用 swagger 会导致各种问题，请直接用浏览器或者用 postman
                response.setContentType("application/vnd.ms-excel");
                // 设置返回的数据编码
                response.setCharacterEncoding("utf-8");
                // 这里 URLEncoder.encode 可以防止中文乱码 当然和 easyexcel 没有关系
                String fileName = URLEncoder.encode("用户信息", "UTF-8").replaceAll("\\+", "%20");
                response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
                EasyExcel.write(response.getOutputStream(), AuthUserExcelDto.class).autoCloseStream(true).sheet("用户信息").doWrite(excelDtos);
            } catch (IOException e) {
                log.error("excel 导出失败.", e);
            }
        }
    }

}
