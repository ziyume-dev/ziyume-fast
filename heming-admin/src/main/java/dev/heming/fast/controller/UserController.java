package dev.heming.fast.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import dev.heming.fast.entity.User;
import dev.heming.fast.param.LoginParam;
import dev.heming.fast.param.user.PageListParam;
import dev.heming.fast.param.user.UserAddParam;
import dev.heming.fast.param.user.UserUpdateParam;
import dev.heming.fast.result.CommonResult;
import dev.heming.fast.service.UserService;
import dev.heming.fast.util.CommonPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description 用户接口
 * @Author Bess Croft
 * @Date 2023/5/25 20:23
 */
@Tag(name = "用户接口")
@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @SaIgnore
    @PostMapping("/login")
    @Operation(summary = "登录")
    public CommonResult<SaTokenInfo> login(@RequestBody @Valid LoginParam param) {
        SaTokenInfo tokenInfo = userService.login(param.getUsername(), param.getPassword());
        return CommonResult.success(tokenInfo);
    }

    @GetMapping("/info")
    @Operation(summary = "获取用户信息")
    public CommonResult<Map<String, Object>> info() {
        Map<String, Object> userInfo = userService.getUserInfo();
        return CommonResult.success(userInfo);
    }

    @PostMapping("/pageList")
    @Operation(summary = "分页查询")
    public CommonResult<CommonPage<User>> pageList(@RequestBody @Valid PageListParam param) {
        return CommonResult.success(CommonPage.restPage(userService.pageList(param)));
    }

    @PostMapping("/addUser")
    @Operation(summary = "新增用户")
    public CommonResult<Void> addUser(@RequestBody @Valid UserAddParam param) {
        userService.addUser(param);
        return CommonResult.success();
    }

    @PutMapping("/updateUser")
    @Operation(summary = "修改用户")
    public CommonResult<Void> updateUser(@RequestBody @Valid UserUpdateParam param) {
        userService.updateUser(param);
        return CommonResult.success();
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/deleteUser/{userId:[\\d]+}")
    public CommonResult<Void> deleteUser(@PathVariable(name = "userId") Long userId) {
        userService.deleteUser(userId);
        return CommonResult.success();
    }

}
