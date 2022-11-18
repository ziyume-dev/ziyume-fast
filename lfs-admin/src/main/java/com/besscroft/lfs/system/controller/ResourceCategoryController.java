package com.besscroft.lfs.system.controller;

import com.besscroft.lfs.entity.AuthResourceCategory;
import com.besscroft.lfs.result.AjaxResult;
import com.besscroft.lfs.result.CommonResult;
import com.besscroft.lfs.system.service.ResourceCategoryService;
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
@Tag(name = "管理系统资源类别接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/resourceCategory")
public class ResourceCategoryController {

    private final ResourceCategoryService resourceCategoryService;

    @Operation(summary = "查询后台管理资源列表")
    @Parameters({
            @Parameter(name = "pageNum", description = "第几页", required = true),
            @Parameter(name = "pageSize", description = "多少条", required = true)
    })
    @GetMapping("/list")
    public CommonResult<Page<AuthResourceCategory>> list(@RequestParam("pageNum") Integer pageNum,
                                                         @RequestParam("pageSize") Integer pageSize) {
        Page<AuthResourceCategory> pageList = resourceCategoryService.getResourcePageList(pageNum, pageSize, null);
        return CommonResult.success(pageList);
    }

    @Operation(summary = "获取资源详情")
    @Parameter(name = "id", description = "资源id", required = true)
    @GetMapping("/getResourceCategory/{id}")
    public CommonResult<AuthResourceCategory> getResourceCategory(@PathVariable("id") Long id) {
        AuthResourceCategory resourceCategory = resourceCategoryService.getResourceCategoryById(id);
        return CommonResult.success(resourceCategory);
    }

    @Operation(summary = "新增资源")
    @PostMapping("/addResourceCategory")
    public AjaxResult addResourceCategory(@RequestBody AuthResourceCategory authResourceCategory) {
        resourceCategoryService.addResourceCategory(authResourceCategory);
        return AjaxResult.success("新增成功！");
    }

    @Operation(summary = "更新资源")
    @PutMapping("/updateResourceCategory")
    public AjaxResult updateResourceCategory(@RequestBody AuthResourceCategory authResourceCategory) {
        resourceCategoryService.updateResourceCategory(authResourceCategory);
        return AjaxResult.success("更新成功！");
    }

    @Operation(summary = "删除资源")
    @Parameter(name = "id", description = "资源id", required = true)
    @DeleteMapping("/delResourceCategory/{id}")
    public AjaxResult delResourceCategory(@PathVariable("id") List<Long> ids) {
        resourceCategoryService.delResourceCategory(ids);
        return AjaxResult.success("删除成功！");
    }

}
