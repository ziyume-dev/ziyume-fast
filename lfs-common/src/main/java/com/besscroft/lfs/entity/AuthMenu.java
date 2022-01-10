package com.besscroft.lfs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
@Table(name = "auth_menu")
@ApiModel(value = "权限管理模块菜单对象")
public class AuthMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @ApiModelProperty(value = "菜单id", dataType = "Long")
    private Long id;

    /** 父级ID */
    @ApiModelProperty(value = "父级ID", dataType = "Long")
    private Long parentId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间", dataType = "Date")
    private LocalDateTime createTime;

    /** 菜单名称 */
    @ApiModelProperty(value = "菜单名称", dataType = "String")
    private String title;

    /** 父菜单名称 */
    @ApiModelProperty(value = "父菜单名称", dataType = "String")
    private String parentTitle;

    /** 菜单级数 */
    @ApiModelProperty(value = "菜单级数", dataType = "Integer")
    private Integer level;

    /** 菜单排序 */
    @ApiModelProperty(value = "菜单排序", dataType = "Integer")
    private Integer sort;

    /** 前端名称 */
    @ApiModelProperty(value = "前端名称", dataType = "String")
    private String name;

    /** 路由地址 */
    @ApiModelProperty(value = "路由地址", dataType = "String")
    private String path;

    /** 前端图标 */
    @ApiModelProperty(value = "前端图标", dataType = "String")
    private String icon;

    /** 显示状态：0->不显示；1->显示 */
    @ApiModelProperty(value = "显示状态", dataType = "Integer")
    private Integer hidden;

    /** 组件路径 */
    @ApiModelProperty(value = "组件路径", dataType = "String")
    private String component;

    /** 子菜单 **/
    @ApiModelProperty(value = "子菜单")
    @Transient
    private List<AuthMenu> children = new ArrayList<>();

}
