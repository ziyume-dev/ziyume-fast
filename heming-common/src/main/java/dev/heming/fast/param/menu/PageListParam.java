package dev.heming.fast.param.menu;

import dev.heming.fast.param.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description 菜单分页参数
 * @Author Bess Croft
 * @Date 2023/5/29 16:27
 */
@Data
@Schema(title = "菜单分页参数")
@EqualsAndHashCode(callSuper = true)
public class PageListParam extends PageParam {

    @Schema(title = "菜单名称", type = "String")
    private String title;

    @Schema(title = "前端名称", type = "String")
    private String name;

    @Schema(title = "路由地址", type = "String")
    private String path;

}
