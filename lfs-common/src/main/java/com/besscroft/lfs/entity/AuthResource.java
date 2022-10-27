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
 * 权限管理模块资源对象
 *
 * @Author Bess Croft
 * @Date 2021/6/9 16:05
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auth_resource")
@Schema(title = "权限管理模块资源对象")
@Where(clause = "del = 1")
@SQLDelete(sql = "UPDATE auth_resource SET del = 0 WHERE id = ?")
public class AuthResource extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(title = "资源id", type = "Long")
    private Long id;

    /** 资源名称 */
    @Schema(title = "资源名称", type = "String")
    @Column(name = "name")
    private String name;

    /** 资源路径 */
    @Schema(title = "资源路径", type = "String")
    @Column(name = "url")
    private String url;

    /** 资源描述 */
    @Schema(title = "资源描述", type = "String")
    @Column(name = "description")
    private String description;

    /** 资源类别ID */
    @Schema(title = "资源类别ID", type = "Long")
    @Column(name = "category_id")
    private Long categoryId;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @Column(name = "del")
    @Schema(title = "逻辑删除：0->删除状态；1->可用状态", type = "Integer")
    private Integer del;

}
