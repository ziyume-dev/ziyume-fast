package com.besscroft.lfs.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.besscroft.lfs.annotation.WebLog;
import com.besscroft.lfs.constant.HttpStatus;
import com.besscroft.lfs.dto.LoginParam;
import com.besscroft.lfs.entity.AuthRole;
import com.besscroft.lfs.entity.AuthUser;
import com.besscroft.lfs.result.AjaxResult;
import com.besscroft.lfs.result.CommonResult;
import com.besscroft.lfs.service.MenuService;
import com.besscroft.lfs.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

    @WebLog(description = "登录以后返回token")
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

    @WebLog(description = "获取当前后台系统登录用户的一些信息")
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

    @WebLog(description = "后台管理系统登出功能")
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

    @WebLog(description = "查询权限管理模块用户列表")
    @ApiOperation("查询权限管理模块用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页",required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "多少条",required = true, dataType = "Integer")
    })
    @GetMapping("/list")
    public AjaxResult list(@RequestParam("pageNum") Integer pageNum,
                           @RequestParam("pageSize") Integer pageSize) {
        Page<AuthUser> pageList = userService.getUserPageList(pageNum, pageSize, null);
        return AjaxResult.success(pageList);
    }

    @WebLog(description = "查询权限管理模块用户详情")
    @ApiOperation("查询权限管理模块用户详情")
    @ApiImplicitParam(name = "id", value = "用户id",required = true, dataType = "Long")
    @GetMapping("/getUser/{id}")
    public AjaxResult getUser(@PathVariable("id") Long id) {
        AuthUser user = userService.getUserById(id);
        user.setPassword("");
        return AjaxResult.success(user);
    }

    @WebLog(description = "修改权限管理模块用户")
    @ApiOperation("修改权限管理模块用户")
    @PutMapping("/updateUser")
    public AjaxResult updateUser(@Validated @RequestBody AuthUser authUser) {
        AuthUser currentAdmin = userService.getCurrentAdmin();
        if (!"1".equals(authUser.getId()) || ("1".equals(authUser.getId()) && "1".equals(currentAdmin.getId()))) {
            boolean b = userService.updateUser(authUser);
            if (b) {
                return AjaxResult.success("更新成功！");
            } else {
                return AjaxResult.error("更新失败！");
            }
        }
        return AjaxResult.error("Unauthorized");
    }

    @WebLog(description = "用户账户启用状态更新")
    @ApiOperation("用户账户启用状态更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "启用状态",required = true, dataType = "Boolean"),
            @ApiImplicitParam(name = "id", value = "用户id",required = true, dataType = "Long")
    })
    @PutMapping("/changeSwitch")
    public AjaxResult changeSwitch(@RequestParam("status") boolean status,
                                   @RequestParam("id") Long id) {
        AuthUser currentAdmin = userService.getCurrentAdmin();
        if (currentAdmin.getId() != id && !"1".equals(id)) {
            boolean b = userService.changeSwitch(status, id);
            if (b && status == true) {
                return AjaxResult.success("启用成功");
            } else if (b && status == false) {
                return AjaxResult.success("禁用成功");
            }
        }
        return AjaxResult.error("哎呀，更新失败了！");
    }

    @WebLog(description = "删除权限管理模块用户")
    @ApiOperation("删除权限管理模块用户")
    @ApiImplicitParam(name = "id", value = "用户id",required = true, dataType = "Long")
    @DeleteMapping("/delUser/{id}")
    public AjaxResult delUser(@PathVariable("id") Long id) {
        AuthUser currentAdmin = userService.getCurrentAdmin();
        if (!"1".equals(id) || currentAdmin.getId() != id) {
            boolean b = userService.delUser(id);
            if (b) {
                return AjaxResult.success("删除成功！");
            }
        }
        return AjaxResult.error("哎呀，删除失败了！");
    }

    @WebLog(description = "新增权限管理模块用户")
    @ApiOperation("新增权限管理模块用户")
    @PostMapping("/addUser")
    public AjaxResult addUser(@RequestBody AuthUser authUser) {
        boolean b = userService.addUser(authUser);
        if (b) {
            return AjaxResult.success("添加成功！");
        }
        return AjaxResult.error("添加失败！");
    }

    @ApiOperation("导出权限管理模块用户")
    @PostMapping("/exportUser")
    public void export(@RequestBody List<Long> data, HttpServletResponse response) {
        userService.export(data, response);
    }

}
