package com.besscroft.lfs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 权限管理模块用户对象
 *
 * @Author Besscroft
 * @Date 2021/6/9 16:06
 */
@Data
@Entity
@Table(name = "auth_user")
@ApiModel(value = "权限管理模块用户对象")
public class AuthUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 用户id */
    @Id
    @GeneratedValue
    @ApiModelProperty(value = "用户id", dataType = "Long")
    private Long id;

    /** 用户名 */
    @ApiModelProperty(value = "用户名", dataType = "String")
    private String username;

    /** 密码 */
    @ApiModelProperty(value = "密码", dataType = "String")
    private String password;

    /** 头像 */
    @ApiModelProperty(value = "头像", dataType = "String")
    private String icon;

    /** 邮箱 */
    @ApiModelProperty(value = "邮箱", dataType = "String")
    private String email;

    /** 手机 */
    @ApiModelProperty(value = "手机", dataType = "String")
    private String phone;

    /** 昵称 */
    @ApiModelProperty(value = "昵称", dataType = "String")
    private String nickName;

    /** 备注信息 */
    @ApiModelProperty(value = "备注信息", dataType = "String")
    private String note;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间", dataType = "Date")
    private Date createTime;

    /** 最后登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最后登录时间", dataType = "Date")
    private Date loginTime;

    /** 帐号启用状态：0->禁用；1->启用 */
    @ApiModelProperty(value = "帐号启用状态", dataType = "Integer")
    private Integer status;

    /** 假删除：0->删除状态；1->可用状态 */
    @ApiModelProperty(value = "假删除", dataType = "Integer")
    private Integer del;

}
