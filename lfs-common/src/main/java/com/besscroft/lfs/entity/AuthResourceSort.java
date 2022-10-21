package com.besscroft.lfs.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * 权限管理模块资源类别管理对象
 *
 * @Author Bess Croft
 * @Date 2021/6/9 16:05
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auth_resource_sort")
@Schema(title = "权限管理模块资源类别管理对象")
@Where(clause = "del = 1")
@SQLDelete(sql = "UPDATE auth_resource_sort SET del = 0 WHERE id = ?")
public class AuthResourceSort extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(title = "类别管理id", type = "Long")
    private Long id;

    /** 资源类别名称 */
    @Schema(title = "资源类别名称", type = "String")
    @Column(name = "category_name")
    private String categoryName;

    /** 资源描述 */
    @Schema(title = "资源描述", type = "String")
    @Column(name = "description")
    private String description;

    /** 排序 */
    @Schema(title = "排序", type = "Long")
    @Column(name = "sort")
    private Long sort;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @Column(name = "del")
    @Schema(title = "逻辑删除：0->删除状态；1->可用状态", type = "Integer")
    private Integer del;

}
