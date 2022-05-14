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
import java.util.ArrayList;
import java.util.List;

/**
 * 权限管理模块菜单对象
 *
 * @Author Besscroft
 * @Date 2021/6/9 16:05
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auth_menu")
@Schema(title = "权限管理模块菜单对象")
public class AuthMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(title = "菜单id", type = "Long")
    private Long id;

    /** 父级ID */
    @Schema(title = "父级ID", type = "Long")
    @Column(name = "parent_id")
    private Long parentId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(title = "创建时间", type = "Date")
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /** 菜单名称 */
    @Schema(title = "菜单名称", type = "String")
    @Column(name = "title")
    private String title;

    /** 父菜单名称 */
    @Schema(title = "父菜单名称", type = "String")
    @Column(name = "parent_title")
    private String parentTitle;

    /** 菜单级数 */
    @Schema(title = "菜单级数", type = "Integer")
    @Column(name = "level")
    private Integer level;

    /** 菜单排序 */
    @Schema(title = "菜单排序", type = "Integer")
    @Column(name = "sort")
    private Integer sort;

    /** 前端名称 */
    @Schema(title = "前端名称", type = "String")
    @Column(name = "name")
    private String name;

    /** 路由地址 */
    @Schema(title = "路由地址", type = "String")
    @Column(name = "path")
    private String path;

    /** 前端图标 */
    @Schema(title = "前端图标", type = "String")
    @Column(name = "icon")
    private String icon;

    /** 显示状态：0->不显示；1->显示 */
    @Schema(title = "显示状态", type = "Integer")
    @Column(name = "hidden")
    private Integer hidden;

    /** 组件路径 */
    @Schema(title = "组件路径", type = "String")
    @Column(name = "component")
    private String component;

    /** 子菜单 **/
    @Schema(title = "子菜单")
    @Transient
    private List<AuthMenu> children = new ArrayList<>();

}
