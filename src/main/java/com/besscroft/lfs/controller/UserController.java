package com.besscroft.lfs.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.besscroft.lfs.constant.HttpStatus;
import com.besscroft.lfs.dto.LoginParam;
import com.besscroft.lfs.entity.AuthRole;
import com.besscroft.lfs.entity.AuthUser;
import com.besscroft.lfs.result.AjaxResult;
import com.besscroft.lfs.result.CommonResult;
import com.besscroft.lfs.service.MenuService;
import com.besscroft.lfs.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author Bess Croft
 * @Time 2021/7/2 15:12
 */
@Slf4j
@Api(tags = "管理系统用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "登录以后返回token")
    @PostMapping("/login")
    public CommonResult login(@Validated @RequestBody LoginParam loginParam) {
        String token = userService.login(loginParam.getUsername(), loginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "获取当前后台系统登录用户的一些信息")
    @GetMapping("/info")
    public AjaxResult getInfo() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(StrUtil.hasEmpty(username)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, "暂未登录或token已经过期");
        }
        AuthUser currentAdmin = userService.getCurrentAdminByUserName(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", currentAdmin.getUsername());
        data.put("menus", menuService.getMenuList(currentAdmin.getId()));
        data.put("icon", currentAdmin.getIcon());
        List<AuthRole> roleList = userService.getRoleList(currentAdmin.getId());
        if(CollUtil.isNotEmpty(roleList)){
            List<String> roles = roleList.stream().map(AuthRole::getName).collect(Collectors.toList());
            data.put("roles",roles);
        }
        // 设置登录时间
        userService.setLoginTime(new Date(), currentAdmin.getId());
        return AjaxResult.success(data);
    }

    @ApiOperation("后台管理系统登出功能")
    @PostMapping(value = "/logout")
    public AjaxResult logout() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(StrUtil.hasEmpty(username)){
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, "暂未登录或token已经过期");
        }
        AuthUser currentAdmin = userService.getCurrentAdminByUserName(username);
        if (ObjectUtil.isNotNull(currentAdmin)) {
            userService.logout(currentAdmin.getId());
        }
        return AjaxResult.success("成功退出登录啦！");
    }

}
