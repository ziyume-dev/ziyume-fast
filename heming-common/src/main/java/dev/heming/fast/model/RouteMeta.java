package dev.heming.fast.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Description 路由元数据
 * @Author Bess Croft
 * @Date 2023/5/25 20:44
 */
@Data
public class RouteMeta {

    /** 菜单名称 */
    @Schema(title = "菜单名称", type = "String")
    private String title;

    /** 禁用状态 */
    @Schema(title = "禁用状态", type = "Integer")
    private Boolean disabled;

    /** 菜单显示状态 */
    @Schema(title = "菜单显示状态", type = "Integer")
    private Boolean hidden;

    /** 是否缓存 */
    @Schema(title = "是否缓存", type = "Integer")
    private Boolean keepAlive;

    /** 取消自动计算根路由模式 */
    @Schema(title = "取消自动计算根路由模式", type = "Integer")
    private Boolean alwaysShow;

    /** 是否跟路由：0->否；1->是 */
    @Schema(title = "是否跟路由", type = "Integer")
    private Boolean isRoot;

    /** 内联外部地址 */
    @Schema(title = "高亮路由", type = "String")
    private String frameSrc;

    /** 是否固定 */
    @Schema(title = "是否固定", type = "Integer")
    private Boolean affix;

    /** 排序 */
    @Schema(title = "排序", type = "Integer")
    private Integer sort;

    /** 菜单图标名称 */
    @Schema(title = "菜单图标名称", type = "String")
    private String icon;

}
