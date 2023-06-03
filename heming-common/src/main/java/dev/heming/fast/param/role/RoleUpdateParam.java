package dev.heming.fast.param.role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Description 角色更新参数
 * @Author Bess Croft
 * @Date 2023/5/29 16:29
 */
@Data
@Schema(title = "角色更新参数")
public class RoleUpdateParam {

    @Schema(title = "id", type = "Long", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "id不能为空")
    private Long id;

    @Schema(title = "角色名称", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "角色名称不能为空")
    private String name;

    @Schema(title = "角色编码", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "角色编码不能为空")
    private String code;

    @Schema(title = "描述", type = "String")
    private String description;

}
