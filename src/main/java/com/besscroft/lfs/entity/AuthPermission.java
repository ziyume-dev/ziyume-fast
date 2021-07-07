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
 * 权限管理模块权限对象
 *
 * @Author Besscroft
 * @Date 2021/6/9 16:05
 */
@Data
@Entity
@Table(name = "auth_permission")
@ApiModel(value = "权限管理模块权限对象")
public class AuthPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @ApiModelProperty(value = "权限id", dataType = "Long")
    private Long id;

    /** 父级权限id */
    @ApiModelProperty(value = "父级权限id", dataType = "Long")
    private Long pid;

    /** 名称 */
    @ApiModelProperty(value = "名称", dataType = "String")
    private String name;

    /** 权限值 */
    @ApiModelProperty(value = "权限值", dataType = "String")
    private String value;

    /** 图标 */
    @ApiModelProperty(value = "图标", dataType = "String")
    private String icon;

    /** 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限） */
    @ApiModelProperty(value = "父级ID", dataType = "Long")
    private Integer type;

    /** 前端资源路径 */
    @ApiModelProperty(value = "父级ID", dataType = "Long")
    private String uri;

    /** 启用状态；0->禁用；1->启用 */
    @ApiModelProperty(value = "启用状态", dataType = "Integer")
    private Integer status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间", dataType = "Date")
    private Date createTime;

    /** 排序 */
    @ApiModelProperty(value = "排序", dataType = "Long")
    private Long sort;

}
