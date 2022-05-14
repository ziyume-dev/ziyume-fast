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
@Schema(title = "权限管理模块角色对象")
public class AuthRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(title = "角色id", type = "Long")
    private Long id;

    /** 名称 */
    @Schema(title = "名称", type = "String")
    @Column(name = "name")
    private String name;

    /** 描述 */
    @Schema(title = "描述", type = "String")
    @Column(name = "description")
    private String description;

    /** 用户数量 */
    @Schema(title = "用户数量", type = "Long")
    @Column(name = "admin_count")
    private Long adminCount;

    /** 创建时间 */
    @Schema(title = "创建时间", type = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /** 启用状态：0->禁用；1->启用 */
    @Schema(title = "启用状态", type = "Integer")
    @Column(name = "status")
    private Integer status;

    /** 排序 */
    @Schema(title = "排序", type = "Long")
    @Column(name = "sort")
    private Long sort;

    /** 假删除：0->删除状态；1->可用状态 */
    @Schema(title = "假删除", type = "Integer")
    @Column(name = "del")
    private Integer del;

}
