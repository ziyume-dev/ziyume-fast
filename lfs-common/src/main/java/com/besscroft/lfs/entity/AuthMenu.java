package com.besscroft.lfs.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限管理模块菜单对象
 *
 * @Author Bess Croft
 * @Date 2021/6/9 16:05
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auth_menu")
@Schema(title = "权限管理模块菜单对象")
@Where(clause = "del = 1")
@SQLDelete(sql = "UPDATE auth_menu SET del = 0 WHERE id = ?")
public class AuthMenu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(title = "菜单id", type = "Long")
    private Long id;

    /** 父级ID */
    @Schema(title = "父级ID", type = "Long")
    @Column(name = "parent_id")
    private Long parentId;

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

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @Column(name = "del")
    @Schema(title = "逻辑删除：0->删除状态；1->可用状态", type = "Integer")
    private Integer del;

    /** 子菜单 **/
    @Schema(title = "子菜单")
    @Transient
    private List<AuthMenu> children = new ArrayList<>();

}
