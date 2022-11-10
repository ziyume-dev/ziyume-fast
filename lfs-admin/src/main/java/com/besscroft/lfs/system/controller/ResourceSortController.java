package com.besscroft.lfs.system.controller;

import com.besscroft.lfs.entity.AuthResourceSort;
import com.besscroft.lfs.result.AjaxResult;
import com.besscroft.lfs.result.CommonResult;
import com.besscroft.lfs.system.service.ResourceSortService;
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
@RequestMapping("/resourceSort")
public class ResourceSortController {

    private final ResourceSortService resourceSortService;

    @Operation(summary = "查询后台管理资源列表")
    @Parameters({
            @Parameter(name = "pageNum", description = "第几页", required = true),
            @Parameter(name = "pageSize", description = "多少条", required = true)
    })
    @GetMapping("/list")
    public CommonResult<Page<AuthResourceSort>> list(@RequestParam("pageNum") Integer pageNum,
                                                     @RequestParam("pageSize") Integer pageSize) {
        Page<AuthResourceSort> pageList = resourceSortService.getResourcePageList(pageNum, pageSize, null);
        return CommonResult.success(pageList);
    }

    @Operation(summary = "获取资源详情")
    @Parameter(name = "id", description = "资源id", required = true)
    @GetMapping("/getResourceSort/{id}")
    public CommonResult<AuthResourceSort> getResourceSort(@PathVariable("id") Long id) {
        AuthResourceSort resourceSort = resourceSortService.getResourceSortById(id);
        return CommonResult.success(resourceSort);
    }

    @Operation(summary = "新增资源")
    @PostMapping("/addResourceSort")
    public AjaxResult addResourceSort(@RequestBody AuthResourceSort authResourceSort) {
        resourceSortService.addResourceSort(authResourceSort);
        return AjaxResult.success("新增成功！");
    }

    @Operation(summary = "更新资源")
    @PutMapping("/updateResourceSort")
    public AjaxResult updateResourceSort(@RequestBody AuthResourceSort authResourceSort) {
        resourceSortService.updateResourceSort(authResourceSort);
        return AjaxResult.success("更新成功！");
    }

    @Operation(summary = "删除资源")
    @Parameter(name = "id", description = "资源id", required = true)
    @DeleteMapping("/delResourceSort/{id}")
    public AjaxResult delResourceSort(@PathVariable("id") List<Long> ids) {
        resourceSortService.delResourceSort(ids);
        return AjaxResult.success("删除成功！");
    }

}
