package com.besscroft.lfs.dto;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "用户名",required = true)
    private String username;

    @NotEmpty
    @ApiModelProperty(value = "密码",required = true)
    private String password;

}
