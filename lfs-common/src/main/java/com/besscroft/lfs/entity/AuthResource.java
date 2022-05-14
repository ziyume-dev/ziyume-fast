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
 * 权限管理模块资源对象
 *
 * @Author Besscroft
 * @Date 2021/6/9 16:05
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "auth_resource")
@Schema(title = "权限管理模块资源对象")
public class AuthResource implements Serializable {

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

    /** 创建时间 */
    @Schema(title = "创建时间", type = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /** 资源类别ID */
    @Schema(title = "资源类别ID", type = "Long")
    @Column(name = "category_id")
    private Long categoryId;

}
