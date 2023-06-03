package dev.heming.fast.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @Description 角色实体
 * @Author Bess Croft
 * @Date 2023/5/25 17:40
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("heming_role")
@Schema(title = "角色实体")
public class Role extends BaseEntity {

    @TableId(type = IdType.AUTO)
    @Schema(title = "id", type = "Long")
    private Long id;

    /** 角色名称 */
    @TableField(value = "name")
    @Schema(title = "角色名称", type = "String")
    private String name;

    /** 角色编码 */
    @TableField(value = "code")
    @Schema(title = "角色编码", type = "String")
    private String code;

    /** 描述 */
    @TableField(value = "description")
    @Schema(title = "描述", type = "String")
    private String description;

    /** 排序 */
    @TableField(value = "sort")
    @Schema(title = "排序", type = "Integer")
    private Integer sort;

    /** 角色启用状态：0->禁用；1->启用 */
    @TableField(value = "status")
    @Schema(title = "角色启用状态：0->禁用；1->启用", type = "Integer")
    private Integer status;

}
