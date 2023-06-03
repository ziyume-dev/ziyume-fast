package dev.heming.fast.controller;

import dev.heming.fast.entity.Role;
import dev.heming.fast.param.role.RoleAddParam;
import dev.heming.fast.param.role.RoleUpdateParam;
import dev.heming.fast.param.role.PageListParam;
import dev.heming.fast.result.CommonResult;
import dev.heming.fast.service.RoleService;
import dev.heming.fast.util.CommonPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 角色接口
 * @Author Bess Croft
 * @Date 2023/5/29 17:40
 */
@Tag(name = "角色接口")
@RequestMapping("/role")
@RestController
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/pageList")
    @Operation(summary = "分页查询")
    public CommonResult<CommonPage<Role>> pageList(@RequestBody @Valid PageListParam param) {
        return CommonResult.success(CommonPage.restPage(roleService.pageList(param)));
    }

    @PostMapping("/addRole")
    @Operation(summary = "新增角色")
    public CommonResult<Void> addRole(@RequestBody @Valid RoleAddParam param) {
        roleService.addRole(param);
        return CommonResult.success();
    }

    @PutMapping("/updateRole")
    @Operation(summary = "修改角色")
    public CommonResult<Void> updateRole(@RequestBody @Valid RoleUpdateParam param) {
        roleService.updateRole(param);
        return CommonResult.success();
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("/deleteRole/{roleId:[\\d]+}")
    public CommonResult<Void> deleteRole(@PathVariable(name = "roleId") Long roleId) {
        roleService.deleteRole(roleId);
        return CommonResult.success();
    }

}
