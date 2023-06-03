package dev.heming.fast.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @Description 菜单实体
 * @Author Bess Croft
 * @Date 2023/5/25 17:44
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("heming_role")
@Schema(title = "菜单实体")
public class Menu extends BaseEntity {

    @TableId(type = IdType.AUTO)
    @Schema(title = "id", type = "Long")
    private Long id;

    /** 父级 id */
    @TableField(value = "parent_id")
    @Schema(title = "父级 id", type = "Long")
    private Long parentId;

    /** 菜单名称 */
    @TableField(value = "title")
    @Schema(title = "菜单名称", type = "String")
    private String title;

    /** 前端名称 */
    @TableField(value = "name")
    @Schema(title = "前端名称", type = "String")
    private String name;

    /** 路由地址 */
    @TableField(value = "path")
    @Schema(title = "路由地址", type = "String")
    private String path;

    /** 重定向地址 */
    @TableField(value = "redirect")
    @Schema(title = "重定向地址", type = "String")
    private String redirect;

    /** 组件路径 */
    @TableField(value = "component")
    @Schema(title = "组件路径", type = "String")
    private String component;

    /** 菜单图标名称 */
    @TableField(value = "icon")
    @Schema(title = "菜单图标名称", type = "String")
    private String icon;

    /** 禁用状态：0->禁用；1->启用 */
    @TableField(value = "disabled")
    @Schema(title = "禁用状态：0->禁用；1->启用", type = "Integer")
    private Integer disabled;

    /** 菜单显示状态：0->禁用；1->启用 */
    @TableField(value = "hidden")
    @Schema(title = "菜单显示状态：0->禁用；1->启用", type = "Integer")
    private Integer hidden;

    /** 是否缓存：0->否；1->是 */
    @TableField(value = "keep_alive")
    @Schema(title = "是否缓存：0->否；1->是", type = "Integer")
    private Integer keepAlive;

    /** 取消自动计算根路由模式：0->否；1->是 */
    @TableField(value = "always_show")
    @Schema(title = "取消自动计算根路由模式：0->否；1->是", type = "Integer")
    private Integer alwaysShow;

    /** 是否跟路由：0->否；1->是 */
    @TableField(value = "is_root")
    @Schema(title = "是否跟路由：0->否；1->是", type = "Integer")
    private Integer isRoot;

    /** 内联外部地址 */
    @TableField(value = "frame_src")
    @Schema(title = "高亮路由", type = "String")
    private String frameSrc;

    /** 是否固定：0->否；1->是 */
    @TableField(value = "affix")
    @Schema(title = "是否固定：0->否；1->是", type = "Integer")
    private Integer affix;

    /** 排序 */
    @TableField(value = "sort")
    @Schema(title = "排序", type = "Integer")
    private Integer sort;

}
