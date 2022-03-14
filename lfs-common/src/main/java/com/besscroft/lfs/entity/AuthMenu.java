package com.besscroft.lfs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "权限管理模块菜单对象")
public class AuthMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "菜单id", dataType = "Long")
    private Long id;

    /** 父级ID */
    @ApiModelProperty(value = "父级ID", dataType = "Long")
    @Column(name = "parent_id")
    private Long parentId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间", dataType = "Date")
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /** 菜单名称 */
    @ApiModelProperty(value = "菜单名称", dataType = "String")
    @Column(name = "title")
    private String title;

    /** 父菜单名称 */
    @ApiModelProperty(value = "父菜单名称", dataType = "String")
    @Column(name = "parent_title")
    private String parentTitle;

    /** 菜单级数 */
    @ApiModelProperty(value = "菜单级数", dataType = "Integer")
    @Column(name = "level")
    private Integer level;

    /** 菜单排序 */
    @ApiModelProperty(value = "菜单排序", dataType = "Integer")
    @Column(name = "sort")
    private Integer sort;

    /** 前端名称 */
    @ApiModelProperty(value = "前端名称", dataType = "String")
    @Column(name = "name")
    private String name;

    /** 路由地址 */
    @ApiModelProperty(value = "路由地址", dataType = "String")
    @Column(name = "path")
    private String path;

    /** 前端图标 */
    @ApiModelProperty(value = "前端图标", dataType = "String")
    @Column(name = "icon")
    private String icon;

    /** 显示状态：0->不显示；1->显示 */
    @ApiModelProperty(value = "显示状态", dataType = "Integer")
    @Column(name = "hidden")
    private Integer hidden;

    /** 组件路径 */
    @ApiModelProperty(value = "组件路径", dataType = "String")
    @Column(name = "component")
    private String component;

    /** 子菜单 **/
    @ApiModelProperty(value = "子菜单")
    @Transient
    private List<AuthMenu> children = new ArrayList<>();

}
