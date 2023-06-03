package dev.heming.fast.controller;

import dev.heming.fast.entity.ResourceType;
import dev.heming.fast.param.resourceType.ResourceTypeAddParam;
import dev.heming.fast.param.resourceType.ResourceTypeUpdateParam;
import dev.heming.fast.param.resourceType.PageListParam;
import dev.heming.fast.result.CommonResult;
import dev.heming.fast.service.ResourceTypeService;
import dev.heming.fast.util.CommonPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 资源类别接口
 * @Author Bess Croft
 * @Date 2023/5/29 17:40
 */
@Tag(name = "资源类别接口")
@RequestMapping("/resourceType")
@RestController
@RequiredArgsConstructor
public class ResourceTypeController {

    private final ResourceTypeService resourceTypeService;

    @PostMapping("/pageList")
    @Operation(summary = "分页查询")
    public CommonResult<CommonPage<ResourceType>> pageList(@RequestBody @Valid PageListParam param) {
        return CommonResult.success(CommonPage.restPage(resourceTypeService.pageList(param)));
    }

    @PostMapping("/addResourceType")
    @Operation(summary = "新增资源类别")
    public CommonResult<Void> addResourceType(@RequestBody @Valid ResourceTypeAddParam param) {
        resourceTypeService.addResourceType(param);
        return CommonResult.success();
    }

    @PutMapping("/updateResourceType")
    @Operation(summary = "修改资源类别")
    public CommonResult<Void> updateResourceType(@RequestBody @Valid ResourceTypeUpdateParam param) {
        resourceTypeService.updateResourceType(param);
        return CommonResult.success();
    }

    @Operation(summary = "删除资源类别")
    @DeleteMapping("/deleteResourceType/{resourceTypeId:[\\d]+}")
    public CommonResult<Void> deleteResourceType(@PathVariable(name = "resourceTypeId") Long resourceTypeId) {
        resourceTypeService.deleteResourceType(resourceTypeId);
        return CommonResult.success();
    }

}
