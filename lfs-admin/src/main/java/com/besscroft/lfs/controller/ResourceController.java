package com.besscroft.lfs.controller;

import com.besscroft.lfs.annotation.WebLog;
import com.besscroft.lfs.entity.AuthResource;
import com.besscroft.lfs.model.ResourceParam;
import com.besscroft.lfs.result.AjaxResult;
import com.besscroft.lfs.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "管理系统资源接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/resource")
public class ResourceController {

    private final ResourceService resourceService;

    @WebLog(description = "查询后台管理资源列表")
    @ApiOperation("查询后台管理资源列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页",required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "多少条",required = true, dataType = "Integer")
    })
    @GetMapping("/list")
    public AjaxResult list(@RequestParam("pageNum") Integer pageNum,
                           @RequestParam("pageSize") Integer pageSize) {
        Page<AuthResource> pageList = resourceService.getResourcePageList(pageNum, pageSize, null);
        return AjaxResult.success(pageList);
    }

    @WebLog(description = "获取资源详情")
    @ApiOperation("获取资源详情")
    @ApiImplicitParam(name = "id", value = "资源id",required = true, dataType = "Long")
    @GetMapping("/getResource/{id}")
    public AjaxResult getResource(@PathVariable("id") Long id) {
        AuthResource resource = resourceService.getResourceById(id);
        return AjaxResult.success(resource);
    }

    @WebLog(description = "新增资源")
    @ApiOperation("新增资源")
    @PostMapping("/addResource")
    public AjaxResult addResource(@RequestBody AuthResource authResource) {
        boolean b = resourceService.addResource(authResource);
        if (b) {
            return AjaxResult.success("新增成功！");
        }
        return AjaxResult.error("哎呀，新增失败了！");
    }

    @WebLog(description = "更新资源")
    @ApiOperation("更新资源")
    @PutMapping("/updateResource")
    public AjaxResult updateResource(@RequestBody AuthResource authResource) {
        boolean b = resourceService.updateResource(authResource);
        if (b) {
            return AjaxResult.success("更新成功！");
        }
        return AjaxResult.error("哎呀，更新失败了！");
    }

    @WebLog(description = "删除资源")
    @ApiOperation("删除资源")
    @ApiImplicitParam(name = "id", value = "资源id",required = true, dataType = "Long")
    @DeleteMapping("/delResource/{id}")
    public AjaxResult delResource(@PathVariable("id") List<Long> ids) {
        boolean b = resourceService.delResource(ids);
        if (b) {
            return AjaxResult.success("删除成功！");
        }
        return AjaxResult.error("哎呀，删除失败了");
    }

    @WebLog(description = "获取所有资源的资源树")
    @ApiOperation("获取所有资源的资源树")
    @GetMapping("/getAllResourceTree")
    public AjaxResult getAllResourceTree() {
        List<ResourceParam> tree = resourceService.getAllResourceTree();
        return AjaxResult.success(tree);
    }

    @WebLog(description = "根据角色id获取资源树数组")
    @ApiOperation("根据角色id获取资源树数组")
    @GetMapping("/getResourceTreeById/{id}")
    public AjaxResult getResourceTreeById(@PathVariable("id") Long id) {
        List<Long> tree = resourceService.getResourceTreeById(id);
        return AjaxResult.success(tree);
    }

    @WebLog(description = "更新资源树")
    @ApiOperation("更新资源树")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "data", value = "资源树数据",required = true, dataType = "Long"),
            @ApiImplicitParam(name = "id", value = "角色id",required = true, dataType = "Long")
    })
    @PutMapping("/updateResourceTree")
    public AjaxResult updateResourceTree(@RequestBody List<Long> data,
                                         @RequestParam("id") Long id) {
        boolean b = resourceService.updateResourceTree(data, id);
        if (b) {
            return AjaxResult.success("更新成功！");
        }
        return AjaxResult.error("哎呀，更新失败了呢！");
    }

}
