package dev.heming.fast.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @Description 资源实体
 * @Author Bess Croft
 * @Date 2023/5/25 17:44
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("heming_resource")
@Schema(title = "资源实体")
public class Resource extends BaseEntity {

    @TableId(type = IdType.AUTO)
    @Schema(title = "id", type = "Long")
    private Long id;

    /** 资源名称 */
    @TableField(value = "name")
    @Schema(title = "资源名称", type = "String")
    private String name;

    /** 资源路径 */
    @TableField(value = "url")
    @Schema(title = "资源路径", type = "String")
    private String url;

    /** 资源描述 */
    @TableField(value = "description")
    @Schema(title = "资源描述", type = "String")
    private String description;

    /** 资源类型 code */
    @TableField(value = "type_code")
    @Schema(title = "资源类型 code", type = "String")
    private String typeCode;

    /** 路由分配 key */
    @TableField(value = "route_key")
    @Schema(title = "路由分配 key", type = "String")
    private String routeKey;

    /** 排序 */
    @TableField(value = "sort")
    @Schema(title = "排序", type = "Integer")
    private Integer sort;

}
