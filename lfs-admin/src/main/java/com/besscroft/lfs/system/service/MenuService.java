package com.besscroft.lfs.system.service;

import com.besscroft.lfs.entity.AuthMenu;
import com.besscroft.lfs.model.RouterVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author Bess Croft
 * @Time 2021/7/8 15:33
 */
public interface MenuService {

    /**
     * 获取用户的路由树
     * @param userId 用户id
     * @return 路由树
     */
    List<RouterVo> getMenuList(Long userId);

    /**
     * 获取当前用户管理系统菜单
     * @param adminId 用户id
     * @return 当前用户的菜单
     */
    List<AuthMenu> getMenuListById(Long adminId);

    /**
     * 分页查询菜单
     * @param pageNum 页码
     * @param pageSize 页大小
     * @param keyword 关键字
     * @return 分页菜单
     */
    Page<AuthMenu> getMenuPageList(Integer pageNum, Integer pageSize, String keyword);

    /**
     * 获取所有父菜单
     * @return 所有父菜单
     */
    List<AuthMenu> getParentMenu();

    /**
     * 根据id获取菜单详细信息
     * @param id 菜单id
     * @return 菜单详细信息
     */
    AuthMenu getMenuById(Long id);

    /**
     * 更新菜单信息
     * @param authMenu 菜单实体
     * @return
     */
    void updateMenu(AuthMenu authMenu);

    /**
     * 修改菜单显示状态
     * @param hidden 显示状态
     * @param id 菜单id
     * @param adminId 用户id
     * @return
     */
    void changeSwitch(boolean hidden, Long id, Long adminId);

    /**
     * 根据id删除菜单
     * @param ids id集合
     * @return
     */
    void delMenu(List<Long> ids);

    /**
     * 新增菜单
     * @param authMenu 菜单实体
     * @return
     */
    void addMenu(AuthMenu authMenu);

    /**
     * 根据角色id获取菜单树数组
     * @param id 菜单id
     * @return 菜单树数组
     */
    List<Long> getMenuTreeById(Long id);

    /**
     * 获取所有菜单的菜单树
     * @return 所有菜单的菜单树
     */
    List<AuthMenu> getAllMenuTree();

    /**
     * 批量更新菜单树
     * @param menuIds 菜单id集合
     * @param id 角色id
     * @return
     */
    void updateMenuTree(List<Long> menuIds, Long id);

}
