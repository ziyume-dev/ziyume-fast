package dev.heming.fast.param.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Description 用户新增参数
 * @Author Bess Croft
 * @Date 2023/5/29 15:56
 */
@Data
@Schema(title = "用户新增参数")
public class UserAddParam {

    @Schema(title = "账号(用户名)", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "账号不能为空")
    private String username;

    @Schema(title = "密码", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "密码不能为空")
    private String password;

    @Schema(title = "头像(地址)", type = "String")
    private String avatar;

    @Schema(title = "邮箱", type = "String")
    private String email;

    @Schema(title = "昵称", type = "String")
    private String name;

    @Schema(title = "手机", type = "String")
    private String telephone;

    @Schema(title = "备注", type = "String")
    private String remark;

}
