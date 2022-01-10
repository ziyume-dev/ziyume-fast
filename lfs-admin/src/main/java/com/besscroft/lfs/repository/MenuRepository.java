package com.besscroft.lfs.repository;

import com.besscroft.lfs.entity.AuthMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author Bess Croft
 * @Time 2021/7/8 15:52
 */
public interface MenuRepository extends JpaRepository<AuthMenu, Long>, JpaSpecificationExecutor<AuthMenu> {

    /**
     * 获取当前用户的父菜单
     * @param userId 用户id
     * @return 父菜单集合
     */
    @Query(value = "select" +
            "            m.*" +
            "        from auth_menu m" +
            "                 inner join auth_role_menu_relation rm on m.id = rm.menu_id" +
            "                 inner join auth_role r on rm.role_id = r.id" +
            "                 inner join auth_user_role_relation ur on r.id = ur.role_id" +
            "                 inner join auth_user u on ur.admin_id = u.id" +
            "        where u.id =:userId and m.hidden = 1 and m.parent_id = 0" +
            "        order by m.sort", nativeQuery = true)
    List<AuthMenu> findParentAllByUserId(Long userId);

    /**
     * 根据父菜单id获取当前用户的子菜单
     * @param userId 用户id
     * @param menuId 菜单id
     * @return 子菜单集合
     */
    @Query(value = "select" +
            "            m.*" +
            "        from auth_menu m" +
            "                 inner join auth_role_menu_relation rm on m.id = rm.menu_id" +
            "                 inner join auth_role r on rm.role_id = r.id" +
            "                 inner join auth_user_role_relation ur on r.id = ur.role_id" +
            "                 inner join auth_user u on ur.admin_id = u.id" +
            "        where u.id =:userId and m.hidden = 1 and m.parent_id =:menuId" +
            "        order by m.sort", nativeQuery = true)
    List<AuthMenu> findChildAllByUserIdAndMenuId(Long userId, Long menuId);

    /**
     * 获取所有父菜单
     * @return 所有父菜单集合
     */
    @Query(value = "select " +
            "           id, parent_id, create_time, title, parent_title, level, sort, name, path, icon, hidden, component" +
            "       from" +
            "           auth_menu" +
            "       where" +
            "           parent_id = 0", nativeQuery = true)
    List<AuthMenu> getParentMenu();

    /**
     * 根据父菜单id获取所有的子菜单
     * @param parentId 父菜单id
     * @return 父菜单对应的子菜单集合
     */
    @Query(value = "select id, parent_id, create_time, title, parent_title, level, sort, name, path, icon, hidden, component" +
            "       from" +
            "           auth_menu" +
            "       where" +
            "           parent_id =:parentId", nativeQuery = true)
    List<AuthMenu> getChildList(Long parentId);

    /**
     * 修改菜单显示状态
     * @param hidden 显示状态
     * @param id 菜单id
     * @return
     */
    @Modifying
    @Query(value = "update" +
            "           auth_menu" +
            "       set" +
            "           hidden = ?1" +
            "       where" +
            "           id = ?2", nativeQuery = true)
    int changeSwitch(Integer hidden, Long id);

    /**
     * 根据角色id查询已绑定的菜单数组
     * @param id 角色id
     * @return 角色的菜单数组
     */
    @Query(value = "select" +
            "           menu_id" +
            "       from" +
            "           auth_role_menu_relation" +
            "       where" +
            "           role_id =:id", nativeQuery = true)
    List<Long> selectMenuTreeById(Long id);

    /**
     * 根据角色id删除角色菜单关系
     * @param id 角色id
     * @return
     */
    @Modifying
    @Query(value = "delete" +
            "       from" +
            "           auth_role_menu_relation" +
            "       where" +
            "           role_id =:id", nativeQuery = true)
    int deleteRoleMenuRelation(Long id);

    /**
     * 插入角色和菜单对应关系
     * @param menuId 菜单id
     * @param roleId 角色id
     * @return
     */
    @Modifying
    @Query(value = "insert into" +
            "          auth_role_menu_relation" +
            "            (role_id, menu_id)" +
            "       values" +
            "           (?2 , ?1)", nativeQuery = true)
    int insertRoleMenuRelation(Long menuId, Long roleId);

}
