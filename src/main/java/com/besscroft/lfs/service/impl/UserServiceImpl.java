package com.besscroft.lfs.service.impl;

import cn.hutool.json.JSONUtil;
import com.besscroft.lfs.entity.AuthResource;
import com.besscroft.lfs.entity.AuthRole;
import com.besscroft.lfs.entity.AuthUser;
import com.besscroft.lfs.model.LFSUser;
import com.besscroft.lfs.repository.RoleRepository;
import com.besscroft.lfs.repository.UserRepository;
import com.besscroft.lfs.service.ResourceService;
import com.besscroft.lfs.service.UserService;
import com.besscroft.lfs.utils.JWTUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Author Bess Croft
 * @Time 2021/7/7 15:55
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RoleRepository repository;

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
        // 走到这代表查询到了实体对象，返回我们自定义的UserDetail对象（这里权限暂时放个空集合，后面我会讲解）
        return new LFSUser(user, resourceList);
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
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
    public AuthUser getCurrentAdminByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<AuthRole> getRoleList(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public boolean setLoginTime(Date loginTime, Long id) {
        return userRepository.updateLoginTime(loginTime, id) > 0;
    }

    @Override
    public boolean logout(Long adminId) {
        return true;
    }

}
