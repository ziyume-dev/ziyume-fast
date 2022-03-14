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
 * 权限管理模块角色对象
 *
 * @Author Besscroft
 * @Date 2021/6/9 16:06
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auth_role")
@ApiModel(value = "权限管理模块角色对象")
public class AuthRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "角色id", dataType = "Long")
    private Long id;

    /** 名称 */
    @ApiModelProperty(value = "名称", dataType = "String")
    @Column(name = "name")
    private String name;

    /** 描述 */
    @ApiModelProperty(value = "描述", dataType = "String")
    @Column(name = "description")
    private String description;

    /** 用户数量 */
    @ApiModelProperty(value = "用户数量", dataType = "Long")
    @Column(name = "admin_count")
    private Long adminCount;

    /** 创建时间 */
    @ApiModelProperty(value = "创建时间", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /** 启用状态：0->禁用；1->启用 */
    @ApiModelProperty(value = "启用状态", dataType = "Integer")
    @Column(name = "status")
    private Integer status;

    /** 排序 */
    @ApiModelProperty(value = "排序", dataType = "Long")
    @Column(name = "sort")
    private Long sort;

    /** 假删除：0->删除状态；1->可用状态 */
    @ApiModelProperty(value = "假删除", dataType = "Integer")
    @Column(name = "del")
    private Integer del;

}
