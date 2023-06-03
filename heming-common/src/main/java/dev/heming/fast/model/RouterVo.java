package dev.heming.fast.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Description 前端路由对象
 * @Author Bess Croft
 * @Date 2023/5/25 20:42
 */
@Data
public class RouterVo {

    /** 菜单名称 */
    @Schema(title = "菜单名称", type = "String")
    private String title;

    /** 前端名称 */
    @Schema(title = "前端名称", type = "String")
    private String name;

    /** 路由地址 */
    @Schema(title = "路由地址", type = "String")
    private String path;

    /** 重定向地址 */
    @Schema(title = "重定向地址", type = "String")
    private String redirect;

    /** 组件路径 */
    @Schema(title = "组件路径", type = "String")
    private String component;

    /** 路由元数据 */
    private RouteMeta meta;

    /** 子路由 */
    private List<RouterVo> children;

}
