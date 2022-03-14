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
@ApiModel(value = "权限管理模块权限对象")
public class AuthPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "权限id", dataType = "Long")
    private Long id;

    /** 父级权限id */
    @ApiModelProperty(value = "父级权限id", dataType = "Long")
    @Column(name = "pid")
    private Long pid;

    /** 名称 */
    @ApiModelProperty(value = "名称", dataType = "String")
    @Column(name = "name")
    private String name;

    /** 权限值 */
    @ApiModelProperty(value = "权限值", dataType = "String")
    @Column(name = "value")
    private String value;

    /** 图标 */
    @ApiModelProperty(value = "图标", dataType = "String")
    @Column(name = "icon")
    private String icon;

    /** 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限） */
    @ApiModelProperty(value = "父级ID", dataType = "Long")
    @Column(name = "type")
    private Integer type;

    /** 前端资源路径 */
    @ApiModelProperty(value = "父级ID", dataType = "Long")
    @Column(name = "uri")
    private String uri;

    /** 启用状态；0->禁用；1->启用 */
    @ApiModelProperty(value = "启用状态", dataType = "Integer")
    @Column(name = "status")
    private Integer status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间", dataType = "Date")
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /** 排序 */
    @ApiModelProperty(value = "排序", dataType = "Long")
    @Column(name = "sort")
    private Long sort;

}
