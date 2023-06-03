package dev.heming.fast.controller;

import dev.heming.fast.entity.Menu;
import dev.heming.fast.param.menu.MenuAddParam;
import dev.heming.fast.param.menu.MenuUpdateParam;
import dev.heming.fast.param.menu.PageListParam;
import dev.heming.fast.result.CommonResult;
import dev.heming.fast.service.MenuService;
import dev.heming.fast.util.CommonPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 菜单接口
 * @Author Bess Croft
 * @Date 2023/5/29 17:40
 */
@Tag(name = "菜单接口")
@RequestMapping("/menu")
@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/pageList")
    @Operation(summary = "分页查询")
    public CommonResult<CommonPage<Menu>> pageList(@RequestBody @Valid PageListParam param) {
        return CommonResult.success(CommonPage.restPage(menuService.pageList(param)));
    }

    @PostMapping("/addMenu")
    @Operation(summary = "新增菜单")
    public CommonResult<Void> addMenu(@RequestBody @Valid MenuAddParam param) {
        menuService.addMenu(param);
        return CommonResult.success();
    }

    @PutMapping("/updateMenu")
    @Operation(summary = "修改菜单")
    public CommonResult<Void> updateMenu(@RequestBody @Valid MenuUpdateParam param) {
        menuService.updateMenu(param);
        return CommonResult.success();
    }

    @Operation(summary = "删除菜单")
    @DeleteMapping("/deleteMenu/{menuId:[\\d]+}")
    public CommonResult<Void> deleteMenu(@PathVariable(name = "menuId") Long menuId) {
        menuService.deleteMenu(menuId);
        return CommonResult.success();
    }

}
