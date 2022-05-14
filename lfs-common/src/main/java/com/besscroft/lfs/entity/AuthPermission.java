package com.besscroft.lfs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 权限管理模块权限对象
 *
 * @Author Besscroft
 * @Date 2021/6/9 16:05
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auth_permission")
@Schema(title = "权限管理模块权限对象")
public class AuthPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(title = "权限id", type = "Long")
    private Long id;

    /** 父级权限id */
    @Schema(title = "父级权限id", type = "Long")
    @Column(name = "pid")
    private Long pid;

    /** 名称 */
    @Schema(title = "名称", type = "String")
    @Column(name = "name")
    private String name;

    /** 权限值 */
    @Schema(title = "权限值", type = "String")
    @Column(name = "value")
    private String value;

    /** 图标 */
    @Schema(title = "图标", type = "String")
    @Column(name = "icon")
    private String icon;

    /** 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限） */
    @Schema(title = "父级ID", type = "Long")
    @Column(name = "type")
    private Integer type;

    /** 前端资源路径 */
    @Schema(title = "父级ID", type = "Long")
    @Column(name = "uri")
    private String uri;

    /** 启用状态；0->禁用；1->启用 */
    @Schema(title = "启用状态", type = "Integer")
    @Column(name = "status")
    private Integer status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(title = "创建时间", type = "Date")
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /** 排序 */
    @Schema(title = "排序", type = "Long")
    @Column(name = "sort")
    private Long sort;

}
