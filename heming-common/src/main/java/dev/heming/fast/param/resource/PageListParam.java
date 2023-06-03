package dev.heming.fast.param.resource;

import dev.heming.fast.param.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description 资源分页参数
 * @Author Bess Croft
 * @Date 2023/5/29 16:27
 */
@Data
@Schema(title = "资源分页参数")
@EqualsAndHashCode(callSuper = true)
public class PageListParam extends PageParam {

    @Schema(title = "资源名称", type = "String")
    private String name;

    @Schema(title = "资源路径", type = "String")
    private String url;

    @Schema(title = "资源类型 code", type = "String")
    private String typeCode;

    @Schema(title = "路由分配 key", type = "String")
    private String routeKey;

}
