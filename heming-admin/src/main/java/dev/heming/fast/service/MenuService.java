package dev.heming.fast.service;

import com.baomidou.mybatisplus.extension.service.IService;
import dev.heming.fast.entity.Menu;
import dev.heming.fast.model.RouterVo;
import dev.heming.fast.param.menu.MenuAddParam;
import dev.heming.fast.param.menu.MenuUpdateParam;
import dev.heming.fast.param.menu.PageListParam;

import java.util.List;

/**
 * @Description 菜单 Service
 * @Author Bess Croft
 * @Date 2023/5/25 19:54
 */
public interface MenuService extends IService<Menu> {

    /**
     * 获取用户菜单路由
     * @param userId 用户 id
     * @return 菜单路由
     */
    List<RouterVo> getRouterByUserId(Long userId);

    /**
     * 分页查询
     * @param param 分页参数
     * @return 分页结果
     */
    List<Menu> pageList(PageListParam param);

    /**
     * 新增菜单
     * @param param 菜单新增参数
     */
    void addMenu(MenuAddParam param);

    /**
     * 更新菜单
     * @param param 菜单更新参数
     */
    void updateMenu(MenuUpdateParam param);

    /**
     * 删除菜单
     * @param menuId 菜单 id
     */
    void deleteMenu(Long menuId);

}
