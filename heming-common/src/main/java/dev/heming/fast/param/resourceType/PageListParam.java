package dev.heming.fast.param.resourceType;

import dev.heming.fast.param.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description 资源类别分页参数
 * @Author Bess Croft
 * @Date 2023/5/29 16:27
 */
@Data
@Schema(title = "资源类别分页参数")
@EqualsAndHashCode(callSuper = true)
public class PageListParam extends PageParam {

    @Schema(title = "名称", type = "String")
    private String name;

    @Schema(title = "编码", type = "String")
    private String code;

}
