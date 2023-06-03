package dev.heming.fast.param.resource;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Description 资源更新参数
 * @Author Bess Croft
 * @Date 2023/5/29 16:30
 */
@Data
@Schema(title = "资源更新参数")
public class ResourceUpdateParam {

    @Schema(title = "id", type = "Long", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "id不能为空")
    private Long id;

    @Schema(title = "资源名称", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "资源名称不能为空")
    private String name;

    @Schema(title = "资源路径", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "资源路径不能为空")
    private String url;

    @Schema(title = "资源描述", type = "String")
    private String description;

    @Schema(title = "资源类型 code", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "资源类型 code 不能为空")
    private String typeCode;

    @Schema(title = "路由分配 key", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "路由分配 key 不能为空")
    private String routeKey;

    @Schema(title = "排序", type = "Integer")
    private Integer sort;

}
