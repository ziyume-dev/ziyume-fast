package com.besscroft.lfs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @Author Bess Croft
 * @Time 2021/7/2 15:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginParam implements Serializable {

    @NotEmpty
    @Schema(title = "用户名",required = true)
    private String username;

    @NotEmpty
    @Schema(title = "密码",required = true)
    private String password;

}
