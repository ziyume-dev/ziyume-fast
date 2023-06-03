package dev.heming.fast.param.role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Description 角色新增参数
 * @Author Bess Croft
 * @Date 2023/5/29 16:29
 */
@Data
@Schema(title = "角色新增参数")
public class RoleAddParam {

    @Schema(title = "角色名称", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "角色名称不能为空")
    private String name;

    @Schema(title = "角色编码", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "角色编码不能为空")
    private String code;

    @Schema(title = "描述", type = "String")
    private String description;

}
