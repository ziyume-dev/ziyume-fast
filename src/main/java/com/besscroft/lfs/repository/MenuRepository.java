package com.besscroft.lfs.repository;

import com.besscroft.lfs.entity.AuthMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author Bess Croft
 * @Time 2021/7/8 15:52
 */
public interface MenuRepository extends JpaRepository<AuthMenu, Long> {

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

}
