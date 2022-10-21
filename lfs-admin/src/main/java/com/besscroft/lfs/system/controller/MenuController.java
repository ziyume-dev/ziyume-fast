package com.besscroft.lfs.system.controller;

import com.besscroft.lfs.annotation.WebLog;
import com.besscroft.lfs.entity.AuthMenu;
import com.besscroft.lfs.entity.AuthUser;
import com.besscroft.lfs.result.AjaxResult;
import com.besscroft.lfs.result.CommonResult;
import com.besscroft.lfs.system.service.MenuService;
import com.besscroft.lfs.system.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Time 2021/12/10 16:00
 */
@Slf4j
@Tag(name = "管理系统菜单接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;
    private final UserService userService;

    @WebLog(description = "获取当前用户管理系统菜单")
    @Operation(summary = "获取当前用户管理系统菜单")
    @GetMapping(value = "/getMenu")
    public CommonResult<List<AuthMenu>> getRouter() {
        AuthUser currentAdmin = userService.getCurrentAdmin();
        List<AuthMenu> list = menuService.getMenuListById(currentAdmin.getId());
        log.info("菜单：{}",list);
        return CommonResult.success(list);
    }

    @WebLog(description = "查询后台管理菜单列表")
    @Operation(summary = "查询后台管理菜单列表")
    @Parameters({
            @Parameter(name = "pageNum", description = "第几页", required = true),
            @Parameter(name = "pageSize", description = "多少条", required = true)
    })
    @GetMapping("/list")
    public CommonResult<Page<AuthMenu>> list(@RequestParam("pageNum") Integer pageNum,
                                             @RequestParam("pageSize") Integer pageSize) {
        Page<AuthMenu> pageList = menuService.getMenuPageList(pageNum, pageSize, null);
        return CommonResult.success(pageList);
    }

    @WebLog(description = "获取所有父菜单")
    @Operation(summary = "获取所有父菜单")
    @GetMapping("/getParentMenu")
    public CommonResult<List<AuthMenu>> getParentMenu() {
        List<AuthMenu> list = menuService.getParentMenu();
        return CommonResult.success(list);
    }

    @WebLog(description = "查询菜单详情")
    @Operation(summary = "查询菜单详情")
    @Parameter(name = "id", description = "菜单id", required = true)
    @GetMapping("/getMenu/{id}")
    public CommonResult<AuthMenu> getMenu(@PathVariable("id") Long id) {
        AuthMenu menu = menuService.getMenuById(id);
        return CommonResult.success(menu);
    }

    @WebLog(description = "修改菜单")
    @Operation(summary = "修改菜单")
    @PutMapping("/updateMenu")
    public AjaxResult updateMenu(@Validated @RequestBody AuthMenu authMenu) {
        menuService.updateMenu(authMenu);
        return AjaxResult.success("更新成功！");
    }

    @WebLog(description = "菜单是否显示状态更新")
    @Operation(summary = "菜单是否显示状态更新")
    @Parameters({
            @Parameter(name = "hidden", description = "显示状态", required = true),
            @Parameter(name = "id", description = "菜单id", required = true)
    })
    @PutMapping("/changeSwitch")
    public AjaxResult changeSwitch(@RequestParam("hidden") boolean hidden,
                                   @RequestParam("id") Long id) {
        AuthUser currentAdmin = userService.getCurrentAdmin();
        menuService.changeSwitch(hidden, id, currentAdmin.getId());
        return AjaxResult.success("修改成功");
    }

    @WebLog(description = "删除菜单")
    @Operation(summary = "删除菜单")
    @Parameter(name = "id", description = "菜单id", required = true)
    @DeleteMapping("/delMenu/{id}")
    public AjaxResult delMenu(@PathVariable("id") List<Long> ids) {
        menuService.delMenu(ids);
        return AjaxResult.success("删除成功！");
    }

    @WebLog(description = "新增菜单")
    @Operation(summary = "新增菜单")
    @PostMapping("/addMenu")
    public AjaxResult addUser(@RequestBody AuthMenu authMenu) {
        menuService.addMenu(authMenu);
        return AjaxResult.success("添加成功！");
    }

    @WebLog(description = "根据角色id获取菜单树")
    @Operation(summary = "根据角色id获取菜单树")
    @Parameter(name = "id", description = "角色id", required = true)
    @GetMapping("/getMenuTreeById/{id}")
    public CommonResult<List<Long>> getMenuTreeById(@PathVariable("id") Long id) {
        List<Long> tree = menuService.getMenuTreeById(id);
        return CommonResult.success(tree);
    }

    @WebLog(description = "获取所有菜单的菜单树")
    @Operation(summary = "获取所有菜单的菜单树")
    @GetMapping("/getAllMenuTree")
    public CommonResult<List<AuthMenu>> getAllMenuTree() {
        List<AuthMenu> tree = menuService.getAllMenuTree();
        return CommonResult.success(tree);
    }

    @WebLog(description = "更新菜单树")
    @Operation(summary = "更新菜单树")
    @Parameters({
            @Parameter(name = "data", description = "菜单树数据", required = true),
            @Parameter(name = "id", description = "角色id", required = true)
    })
    @PutMapping("/updateMenuTree")
    public AjaxResult updateMenuTree(@RequestBody List<Long> data,
                                     @RequestParam("id") Long id) {
        menuService.updateMenuTree(data, id);
        return AjaxResult.success("更新成功！");
    }

}
