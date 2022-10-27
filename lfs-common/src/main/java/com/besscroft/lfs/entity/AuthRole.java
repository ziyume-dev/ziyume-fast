package com.besscroft.lfs.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.*;

/**
 * 权限管理模块角色对象
 *
 * @Author Bess Croft
 * @Date 2021/6/9 16:06
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auth_role")
@Schema(title = "权限管理模块角色对象")
@Where(clause = "del = 1")
@SQLDelete(sql = "UPDATE auth_role SET del = 0 WHERE id = ?")
public class AuthRole extends BaseEntity {

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
    @Column(name = "user_count")
    private Long userCount;

    /** 启用状态：0->禁用；1->启用 */
    @Schema(title = "启用状态", type = "Integer")
    @Column(name = "status")
    private Integer status;

    /** 排序 */
    @Schema(title = "排序", type = "Long")
    @Column(name = "sort")
    private Long sort;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @Column(name = "del")
    @Schema(title = "逻辑删除：0->删除状态；1->可用状态", type = "Integer")
    private Integer del;

}
