package dev.heming.fast.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Description 登录请求参数
 * @Author Bess Croft
 * @Date 2022/12/15 15:12
 */
@Data
@Schema(title = "登录请求参数")
public class LoginParam {

    @NotBlank(message = "用户名未填！")
    @Schema(title = "用户名", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @NotBlank(message = "密码未填！")
    @Schema(title = "密码", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

}
