package com.besscroft.lfs.system.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.besscroft.lfs.annotation.WebLog;
import com.besscroft.lfs.constant.HttpStatus;
import com.besscroft.lfs.dto.LoginParam;
import com.besscroft.lfs.entity.AuthUser;
import com.besscroft.lfs.result.AjaxResult;
import com.besscroft.lfs.result.CommonResult;
import com.besscroft.lfs.system.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Bess Croft
 * @Time 2021/7/2 15:12
 */
@Slf4j
@Tag(name = "管理系统用户接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    private final UserService userService;

    @WebLog(description = "登录以后返回token")
    @Operation(summary = "登录以后返回token")
    @PostMapping("/login")
    public CommonResult<Map<String, String>> login(@Validated @RequestBody LoginParam loginParam) {
        String token = userService.login(loginParam.getUsername(), loginParam.getPassword());
        Assert.notNull(token, "用户名或密码错误");
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @WebLog(description = "获取当前后台系统登录用户的一些信息")
    @Operation(summary = "获取当前后台系统登录用户的一些信息")
    @GetMapping("/info")
    public CommonResult<Map<String, Object>> getInfo() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(StrUtil.hasEmpty(username)){
            return CommonResult.failed(HttpStatus.UNAUTHORIZED, "暂未登录或token已经过期");
        }
        Map<String, Object> userInfo = userService.getUserInfo();
        return CommonResult.success(userInfo);
    }

    @WebLog(description = "后台管理系统登出功能")
    @Operation(summary = "后台管理系统登出功能")
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
    @Operation(summary = "查询权限管理模块用户列表")
    @Parameters({
            @Parameter(name = "pageNum", description = "第几页", required = true),
            @Parameter(name = "pageSize", description = "多少条", required = true)
    })
    @GetMapping("/list")
    public CommonResult<Page<AuthUser>> list(@RequestParam("pageNum") Integer pageNum,
                                             @RequestParam("pageSize") Integer pageSize) {
        Page<AuthUser> pageList = userService.getUserPageList(pageNum, pageSize, null);
        return CommonResult.success(pageList);
    }

    @WebLog(description = "查询权限管理模块用户详情")
    @Operation(summary = "查询权限管理模块用户详情")
    @Parameter(name = "id", description = "用户id", required = true)
    @GetMapping("/getUser/{id}")
    public CommonResult<AuthUser> getUser(@PathVariable("id") Long id) {
        AuthUser user = userService.getUserById(id);
        user.setPassword("");
        return CommonResult.success(user);
    }

    @WebLog(description = "修改权限管理模块用户")
    @Operation(summary = "修改权限管理模块用户")
    @PutMapping("/updateUser")
    public AjaxResult updateUser(@Validated @RequestBody AuthUser authUser) {
        userService.updateUser(authUser);
        return AjaxResult.success("更新成功！");
    }

    @WebLog(description = "用户账户启用状态更新")
    @Operation(summary = "用户账户启用状态更新")
    @Parameters({
            @Parameter(name = "status", description = "启用状态", required = true),
            @Parameter(name = "id", description = "用户id", required = true)
    })
    @PutMapping("/changeSwitch")
    public AjaxResult changeSwitch(@RequestParam("status") boolean status,
                                   @RequestParam("id") Long id) {
        userService.changeSwitch(status, id);
        return AjaxResult.success("更新成功！");
    }

    @WebLog(description = "删除权限管理模块用户")
    @Operation(summary = "删除权限管理模块用户")
    @Parameter(name = "id", description = "用户id", required = true)
    @DeleteMapping("/delUser/{id}")
    public AjaxResult delUser(@PathVariable("id") Long id) {
        userService.delUser(id);
        return AjaxResult.success("删除成功！");
    }

    @WebLog(description = "新增权限管理模块用户")
    @Operation(summary = "新增权限管理模块用户")
    @PostMapping("/addUser")
    public AjaxResult addUser(@RequestBody AuthUser authUser) {
        userService.addUser(authUser);
        return AjaxResult.success("添加成功！");
    }

    @Operation(summary = "导出权限管理模块用户")
    @PostMapping("/exportUser")
    public void export(@RequestBody List<Long> data, HttpServletResponse response) {
        userService.export(data, response);
    }

}
