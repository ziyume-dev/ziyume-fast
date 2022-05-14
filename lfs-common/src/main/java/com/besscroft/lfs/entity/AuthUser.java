package com.besscroft.lfs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(title = "权限管理模块用户对象")
public class AuthUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 用户id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(title = "用户id", type = "Long")
    private Long id;

    /** 用户名 */
    @Schema(title = "用户名", type = "String")
    @Column(name = "username")
    private String username;

    /** 密码 */
    @Schema(title = "密码", type = "String")
    @Column(name = "password")
    private String password;

    /** 头像 */
    @Schema(title = "头像", type = "String")
    @Column(name = "icon")
    private String icon;

    /** 邮箱 */
    @Schema(title = "邮箱", type = "String")
    @Column(name = "email")
    private String email;

    /** 手机 */
    @Schema(title = "手机", type = "String")
    @Column(name = "phone")
    private String phone;

    /** 昵称 */
    @Schema(title = "昵称", type = "String")
    @Column(name = "nick_name")
    private String nickName;

    /** 备注信息 */
    @Schema(title = "备注信息", type = "String")
    @Column(name = "note")
    private String note;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(title = "创建时间", type = "Date")
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /** 最后登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(title = "最后登录时间", type = "Date")
    @Column(name = "login_time")
    private LocalDateTime loginTime;

    /** 帐号启用状态：0->禁用；1->启用 */
    @Schema(title = "帐号启用状态", type = "Integer")
    @Column(name = "status")
    private Integer status;

    /** 假删除：0->删除状态；1->可用状态 */
    @Schema(title = "假删除", type = "Integer")
    @Column(name = "del")
    private Integer del;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name = "auth_user_role_relation",joinColumns = @JoinColumn(name="user_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private List<AuthRole> roles;

}
