package com.besscroft.lfs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 权限管理模块用户对象
 *
 * @Author Besscroft
 * @Date 2021/6/9 16:06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auth_user")
@ApiModel(value = "权限管理模块用户对象")
public class AuthUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 用户id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "用户id", dataType = "Long")
    private Long id;

    /** 用户名 */
    @ApiModelProperty(value = "用户名", dataType = "String")
    @Column(name = "username")
    private String username;

    /** 密码 */
    @ApiModelProperty(value = "密码", dataType = "String")
    @Column(name = "password")
    private String password;

    /** 头像 */
    @ApiModelProperty(value = "头像", dataType = "String")
    @Column(name = "icon")
    private String icon;

    /** 邮箱 */
    @ApiModelProperty(value = "邮箱", dataType = "String")
    @Column(name = "email")
    private String email;

    /** 手机 */
    @ApiModelProperty(value = "手机", dataType = "String")
    @Column(name = "phone")
    private String phone;

    /** 昵称 */
    @ApiModelProperty(value = "昵称", dataType = "String")
    @Column(name = "nick_name")
    private String nickName;

    /** 备注信息 */
    @ApiModelProperty(value = "备注信息", dataType = "String")
    @Column(name = "note")
    private String note;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间", dataType = "Date")
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /** 最后登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最后登录时间", dataType = "Date")
    @Column(name = "login_time")
    private LocalDateTime loginTime;

    /** 帐号启用状态：0->禁用；1->启用 */
    @ApiModelProperty(value = "帐号启用状态", dataType = "Integer")
    @Column(name = "status")
    private Integer status;

    /** 假删除：0->删除状态；1->可用状态 */
    @ApiModelProperty(value = "假删除", dataType = "Integer")
    @Column(name = "del")
    private Integer del;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name = "auth_user_role_relation",joinColumns = @JoinColumn(name="user_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private List<AuthRole> roles;

}
