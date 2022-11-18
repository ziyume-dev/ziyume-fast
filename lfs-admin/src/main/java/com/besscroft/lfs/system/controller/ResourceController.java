package com.besscroft.lfs.system.controller;

import com.besscroft.lfs.entity.AuthResource;
import com.besscroft.lfs.model.ResourceTree;
import com.besscroft.lfs.result.AjaxResult;
import com.besscroft.lfs.result.CommonResult;
import com.besscroft.lfs.system.service.ResourceService;
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
 * @Time 2021/12/10 16:00
 */
@Slf4j
@Tag(name = "管理系统资源接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/resource")
public class ResourceController {

    private final ResourceService resourceService;

    @Operation(summary = "查询后台管理资源列表")
    @Parameters({
            @Parameter(name = "pageNum", description = "第几页", required = true),
            @Parameter(name = "pageSize", description = "多少条", required = true)
    })
    @GetMapping("/list")
    public CommonResult<Page<AuthResource>> list(@RequestParam("pageNum") Integer pageNum,
                                                 @RequestParam("pageSize") Integer pageSize) {
        Page<AuthResource> pageList = resourceService.getResourcePageList(pageNum, pageSize, null);
        return CommonResult.success(pageList);
    }

    @Operation(summary = "获取资源详情")
    @Parameter(name = "id", description = "资源id", required = true)
    @GetMapping("/getResource/{id}")
    public CommonResult<AuthResource> getResource(@PathVariable("id") Long id) {
        AuthResource resource = resourceService.getResourceById(id);
        return CommonResult.success(resource);
    }

    @Operation(summary = "新增资源")
    @PostMapping("/addResource")
    public AjaxResult addResource(@RequestBody AuthResource authResource) {
        resourceService.addResource(authResource);
        return AjaxResult.success("新增成功！");
    }

    @Operation(summary = "更新资源")
    @PutMapping("/updateResource")
    public AjaxResult updateResource(@RequestBody AuthResource authResource) {
        resourceService.updateResource(authResource);
        return AjaxResult.success("更新成功！");
    }

    @Operation(summary = "删除资源")
    @Parameter(name = "id", description = "资源id", required = true)
    @DeleteMapping("/delResource/{id}")
    public AjaxResult delResource(@PathVariable("id") List<Long> ids) {
        resourceService.delResource(ids);
        return AjaxResult.success("删除成功！");
    }

    @Operation(summary = "获取所有资源的资源树")
    @GetMapping("/getAllResourceTree")
    public CommonResult<List<ResourceTree>> getAllResourceTree() {
        List<ResourceTree> tree = resourceService.getAllResourceTree();
        return CommonResult.success(tree);
    }

    @Operation(summary = "根据角色id获取资源树数组")
    @GetMapping("/getResourceTreeById/{id}")
    public CommonResult<List<Long>> getResourceTreeById(@PathVariable("id") Long id) {
        List<Long> tree = resourceService.getResourceTreeById(id);
        return CommonResult.success(tree);
    }

    @Operation(summary = "更新资源树")
    @Parameters({
            @Parameter(name = "data", description = "资源树数据", required = true),
            @Parameter(name = "id", description = "角色id", required = true)
    })
    @PutMapping("/updateResourceTree")
    public AjaxResult updateResourceTree(@RequestBody List<Long> data,
                                         @RequestParam("id") Long id) {
        resourceService.updateResourceTree(data, id);
        return AjaxResult.success("更新成功！");
    }

}
