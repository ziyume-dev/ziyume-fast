package com.besscroft.lfs.controller;

import com.besscroft.lfs.annotation.WebLog;
import com.besscroft.lfs.entity.AuthRole;
import com.besscroft.lfs.result.AjaxResult;
import com.besscroft.lfs.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Time 2021/12/10 16:01
 */
@Slf4j
@Api(tags = "管理系统角色接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @WebLog(description = "查询后台管理角色列表")
    @ApiOperation("查询后台管理角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页",required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "多少条",required = true, dataType = "Integer")
    })
    @GetMapping("/list")
    public AjaxResult list(@RequestParam("pageNum") Integer pageNum,
                           @RequestParam("pageSize") Integer pageSize) {
        Page<AuthRole> pageList = roleService.getRolePageList(pageNum, pageSize, null);
        return AjaxResult.success(pageList);
    }

    @WebLog(description = "查询角色详情")
    @ApiOperation("查询角色详情")
    @ApiImplicitParam(name = "id", value = "角色id",required = true, dataType = "Long")
    @GetMapping("/getRole/{id}")
    public AjaxResult getRole(@PathVariable("id") Long id) {
        AuthRole role = roleService.getRoleById(id);
        return AjaxResult.success(role);
    }

    @WebLog(description = "新增角色")
    @ApiOperation("新增角色")
    @PostMapping("/addRole")
    public AjaxResult addRole(@RequestBody AuthRole authRole) {
        boolean b = roleService.addRole(authRole);
        Assert.isTrue(b, "哎呀，新增失败了！");
        return AjaxResult.success("新增成功！");
    }

    @WebLog(description = "修改角色")
    @ApiOperation("修改角色")
    @PutMapping("/updateRole")
    public AjaxResult updateRole(@RequestBody AuthRole authRole) {
        boolean b = roleService.updateRole(authRole);
        Assert.isTrue(b, "哎呀，更新失败了！");
        return AjaxResult.success("更新成功！");
    }

    @WebLog(description = "删除角色")
    @ApiOperation("删除角色")
    @ApiImplicitParam(name = "id", value = "角色id",required = true, dataType = "Long")
    @DeleteMapping("/delRole/{id}")
    public AjaxResult delRole(@PathVariable("id") List<Long> ids) {
        boolean b = roleService.delRoleById(ids);
        Assert.isTrue(b, "哎呀，删除失败了！");
        return AjaxResult.success("删除成功！");
    }

    @WebLog(description = "角色是否可用状态更新")
    @ApiOperation("角色是否可用状态更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "可用状态",required = true, dataType = "Boolean"),
            @ApiImplicitParam(name = "id", value = "角色id",required = true, dataType = "Long")
    })
    @PutMapping("/changeSwitch")
    public AjaxResult changeSwitch(@RequestParam("status") boolean status,
                                   @RequestParam("id") Long id) {
        boolean b = roleService.changeSwitch(status, id);
        Assert.isTrue(b, "哎呀，状态更新失败了！");
        return AjaxResult.success("状态更新成功！");
    }

    @WebLog(description = "查询所有可用角色")
    @ApiOperation("查询所有可用角色")
    @GetMapping("/getRoleAll")
    public AjaxResult getRoleAll() {
        List<AuthRole> roles = roleService.listAll();
        return AjaxResult.success(roles);
    }

    @WebLog(description = "更新用户的角色")
    @ApiOperation("更新用户的角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "Long")
    })
    @PutMapping("/updateRoleById")
    public AjaxResult updateRoleById(@RequestParam("userId") Long userId,
                                     @RequestParam("roleId") Long roleId) {
        boolean b = roleService.updateRoleById(userId, roleId);
        Assert.isTrue(b, "哎呀，更新失败了呢！");
        return AjaxResult.success("更新角色绑定成功！");
    }

    @WebLog(description = "根据用户id查询角色")
    @ApiOperation("根据用户id查询角色")
    @ApiImplicitParam(name = "id", value = "用户id", readOnly = true, dataType = "Long")
    @GetMapping("/getRoleById/{id}")
    public AjaxResult getRoleById(@PathVariable("id") Long id) {
        AuthRole role = roleService.getRoleById(id);
        return AjaxResult.success(role);
    }

}
