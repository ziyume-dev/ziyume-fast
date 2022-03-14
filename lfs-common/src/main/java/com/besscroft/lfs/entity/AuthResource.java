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
@ApiModel(value = "权限管理模块资源对象")
public class AuthResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "资源id", dataType = "Long")
    private Long id;

    /** 资源名称 */
    @ApiModelProperty(value = "资源名称", dataType = "String")
    @Column(name = "name")
    private String name;

    /** 资源路径 */
    @ApiModelProperty(value = "资源路径", dataType = "String")
    @Column(name = "url")
    private String url;

    /** 资源描述 */
    @ApiModelProperty(value = "资源描述", dataType = "String")
    @Column(name = "description")
    private String description;

    /** 创建时间 */
    @ApiModelProperty(value = "创建时间", dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /** 资源类别ID */
    @ApiModelProperty(value = "资源类别ID", dataType = "Long")
    @Column(name = "category_id")
    private Long categoryId;

}
