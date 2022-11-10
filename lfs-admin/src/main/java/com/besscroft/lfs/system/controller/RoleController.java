package com.besscroft.lfs.system.controller;

import com.besscroft.lfs.entity.AuthRole;
import com.besscroft.lfs.result.AjaxResult;
import com.besscroft.lfs.result.CommonResult;
import com.besscroft.lfs.system.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Time 2021/12/10 16:01
 */
@Slf4j
@Tag(name = "管理系统角色接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @Operation(summary = "查询后台管理角色列表")
    @Parameters({
            @Parameter(name = "pageNum", description = "第几页", required = true),
            @Parameter(name = "pageSize", description = "多少条", required = true)
    })
    @GetMapping("/list")
    public CommonResult<Page<AuthRole>> list(@RequestParam("pageNum") Integer pageNum,
                                             @RequestParam("pageSize") Integer pageSize) {
        Page<AuthRole> pageList = roleService.getRolePageList(pageNum, pageSize, null);
        return CommonResult.success(pageList);
    }

    @Operation(summary = "查询角色详情")
    @Parameter(name = "id", description = "角色id", required = true)
    @GetMapping("/getRole/{id}")
    public CommonResult<AuthRole> getRole(@PathVariable("id") Long id) {
        AuthRole role = roleService.getRoleById(id);
        return CommonResult.success(role);
    }

    @Operation(summary = "新增角色")
    @PostMapping("/addRole")
    public AjaxResult addRole(@RequestBody AuthRole authRole) {
        roleService.addRole(authRole);
        return AjaxResult.success("新增成功！");
    }

    @Operation(summary = "修改角色")
    @PutMapping("/updateRole")
    public AjaxResult updateRole(@RequestBody AuthRole authRole) {
        roleService.updateRole(authRole);
        return AjaxResult.success("更新成功！");
    }

    @Operation(summary = "删除角色")
    @Parameter(name = "id", description = "角色id", required = true)
    @DeleteMapping("/delRole/{id}")
    public AjaxResult delRole(@PathVariable("id") List<Long> ids) {
        roleService.delRoleById(ids);
        return AjaxResult.success("删除成功！");
    }

    @Operation(summary = "角色是否可用状态更新")
    @Parameters({
            @Parameter(name = "status", description = "可用状态", required = true),
            @Parameter(name = "id", description = "角色id", required = true)
    })
    @PutMapping("/changeSwitch")
    public AjaxResult changeSwitch(@RequestParam("status") boolean status,
                                   @RequestParam("id") Long id) {
        roleService.changeSwitch(status, id);
        return AjaxResult.success("状态更新成功！");
    }

    @Operation(summary = "查询所有可用角色")
    @GetMapping("/getRoleAll")
    public CommonResult<List<AuthRole>> getRoleAll() {
        List<AuthRole> roles = roleService.listAll();
        return CommonResult.success(roles);
    }

    @Operation(summary = "更新用户的角色")
    @Parameters({
            @Parameter(name = "userId", description = "用户id", required = true),
            @Parameter(name = "roleId", description = "角色id", required = true)
    })
    @PutMapping("/updateRoleById")
    public AjaxResult updateRoleById(@RequestParam("userId") Long userId,
                                     @RequestParam("roleId") Long roleId) {
        roleService.updateRoleById(userId, roleId);
        return AjaxResult.success("更新角色绑定成功！");
    }

    @Operation(summary = "根据用户id查询角色")
    @Parameter(name = "id", description = "用户id", required = true)
    @GetMapping("/getRoleById/{id}")
    public CommonResult<AuthRole> getRoleById(@PathVariable("id") Long id) {
        AuthRole role = roleService.getRoleById(id);
        return CommonResult.success(role);
    }

}
