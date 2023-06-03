package dev.heming.fast.param.role;

import dev.heming.fast.param.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description 角色分页参数
 * @Author Bess Croft
 * @Date 2023/5/29 16:27
 */
@Data
@Schema(title = "角色分页参数")
@EqualsAndHashCode(callSuper = true)
public class PageListParam extends PageParam {

    @Schema(title = "角色名称", type = "String")
    private String name;

    @Schema(title = "角色编码", type = "String")
    private String code;

}
