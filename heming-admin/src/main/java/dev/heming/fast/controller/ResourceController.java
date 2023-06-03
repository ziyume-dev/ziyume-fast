package dev.heming.fast.controller;

import dev.heming.fast.entity.Resource;
import dev.heming.fast.param.resource.ResourceAddParam;
import dev.heming.fast.param.resource.ResourceUpdateParam;
import dev.heming.fast.param.resource.PageListParam;
import dev.heming.fast.result.CommonResult;
import dev.heming.fast.service.ResourceService;
import dev.heming.fast.util.CommonPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 资源接口
 * @Author Bess Croft
 * @Date 2023/5/29 17:40
 */
@Tag(name = "资源接口")
@RequestMapping("/resource")
@RestController
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping("/pageList")
    @Operation(summary = "分页查询")
    public CommonResult<CommonPage<Resource>> pageList(@RequestBody @Valid PageListParam param) {
        return CommonResult.success(CommonPage.restPage(resourceService.pageList(param)));
    }

    @PostMapping("/addResource")
    @Operation(summary = "新增资源")
    public CommonResult<Void> addResource(@RequestBody @Valid ResourceAddParam param) {
        resourceService.addResource(param);
        return CommonResult.success();
    }

    @PutMapping("/updateResource")
    @Operation(summary = "修改资源")
    public CommonResult<Void> updateResource(@RequestBody @Valid ResourceUpdateParam param) {
        resourceService.updateResource(param);
        return CommonResult.success();
    }

    @Operation(summary = "删除资源")
    @DeleteMapping("/deleteResource/{resourceId:[\\d]+}")
    public CommonResult<Void> deleteResource(@PathVariable(name = "resourceId") Long resourceId) {
        resourceService.deleteResource(resourceId);
        return CommonResult.success();
    }

}
