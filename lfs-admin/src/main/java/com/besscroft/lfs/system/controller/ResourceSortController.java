package com.besscroft.lfs.system.controller;

import com.besscroft.lfs.annotation.WebLog;
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
import org.springframework.util.Assert;
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

    @WebLog(description = "查询后台管理资源列表")
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

    @WebLog(description = "获取资源详情")
    @Operation(summary = "获取资源详情")
    @Parameter(name = "id", description = "资源id", required = true)
    @GetMapping("/getResourceSort/{id}")
    public CommonResult<AuthResourceSort> getResourceSort(@PathVariable("id") Long id) {
        AuthResourceSort resourceSort = resourceSortService.getResourceSortById(id);
        return CommonResult.success(resourceSort);
    }

    @WebLog(description = "新增资源")
    @Operation(summary = "新增资源")
    @PostMapping("/addResourceSort")
    public AjaxResult addResourceSort(@RequestBody AuthResourceSort authResourceSort) {
        boolean b = resourceSortService.addResourceSort(authResourceSort);
        Assert.isTrue(b, "哎呀，新增失败了！");
        return AjaxResult.success("新增成功！");
    }

    @WebLog(description = "更新资源")
    @Operation(summary = "更新资源")
    @PutMapping("/updateResourceSort")
    public AjaxResult updateResourceSort(@RequestBody AuthResourceSort authResourceSort) {
        boolean b = resourceSortService.updateResourceSort(authResourceSort);
        Assert.isTrue(b, "哎呀，更新失败了！");
        return AjaxResult.success("更新成功！");
    }

    @WebLog(description = "删除资源")
    @Operation(summary = "删除资源")
    @Parameter(name = "id", description = "资源id", required = true)
    @DeleteMapping("/delResourceSort/{id}")
    public AjaxResult delResourceSort(@PathVariable("id") List<Long> ids) {
        boolean b = resourceSortService.delResourceSort(ids);
        Assert.isTrue(b, "哎呀，删除失败了！");
        return AjaxResult.success("删除成功！");
    }

}
