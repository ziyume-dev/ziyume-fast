package dev.heming.fast.param.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Description 菜单新增参数
 * @Author Bess Croft
 * @Date 2023/5/29 16:29
 */
@Data
@Schema(title = "菜单新增参数")
public class MenuAddParam {

    @Schema(title = "父级 id", type = "Long", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "父级 id 不能为空")
    private Long parentId;

    @Schema(title = "菜单名称", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "菜单名称不能为空")
    private String title;

    @Schema(title = "前端名称", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "前端名称不能为空")
    private String name;

    @Schema(title = "路由地址", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "路由地址不能为空")
    private String path;

    @Schema(title = "重定向地址", type = "String")
    private String redirect;

    @Schema(title = "组件路径", type = "String", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "组件路径不能为空")
    private String component;

    @Schema(title = "菜单图标名称", type = "String")
    private String icon;

    @Schema(title = "禁用状态：0->禁用；1->启用", type = "Integer")
    private Integer disabled;

    @Schema(title = "菜单显示状态：0->禁用；1->启用", type = "Integer")
    private Integer hidden;

    @Schema(title = "是否缓存：0->否；1->是", type = "Integer", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否缓存不能为空")
    private Integer keepAlive;

    @Schema(title = "取消自动计算根路由模式：0->否；1->是", type = "Integer")
    private Integer alwaysShow;

    @Schema(title = "是否跟路由：0->否；1->是", type = "Integer")
    private Integer isRoot;

    @Schema(title = "高亮路由", type = "String")
    private String frameSrc;

    @Schema(title = "是否固定：0->否；1->是", type = "Integer")
    private Integer affix;

    @Schema(title = "排序", type = "Integer")
    private Integer sort;

}
