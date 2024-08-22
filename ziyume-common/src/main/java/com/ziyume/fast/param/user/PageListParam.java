package com.ziyume.fast.param.user;

import com.ziyume.fast.param.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description 用户分页参数
 * @Author Bess Croft
 * @Date 2023/5/29 15:49
 */
@Data
@Schema(title = "用户分页参数")
@EqualsAndHashCode(callSuper = true)
public class PageListParam extends PageParam {

    @Schema(title = "账号(用户名)", type = "String")
    private String username;

    @Schema(title = "昵称", type = "String")
    private String name;

    @Schema(title = "手机", type = "String")
    private String telephone;

    @Schema(title = "邮箱", type = "String")
    private String email;

}
