package com.besscroft.lfs.system.repository;

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
     * 获取当前用户的所有菜单
     * @param userId 用户id
     * @return 菜单集合
     */
    @Query(value = """
        SELECT
            m.*
        FROM
            auth_menu m
        INNER JOIN auth_role_menu rm ON m.id = rm.menu_id
        INNER JOIN auth_role r ON rm.role_id = r.id
        INNER JOIN auth_user_role ur ON r.id = ur.role_id
        INNER JOIN auth_user u ON ur.user_id = u.id
        WHERE 
            u.id =:userId
        ORDER BY m.sort
    """, nativeQuery = true)
    List<AuthMenu> findAllByUserId(Long userId);

    /**
     * 修改菜单显示状态
     * @param hidden 显示状态
     * @param id 菜单id
     * @return
     */
    @Modifying
    @Query(value = """
        UPDATE
            auth_menu
        SET
            hidden = ?1
        WHERE
            id = ?2
    """, nativeQuery = true)
    int changeSwitch(Integer hidden, Long id);

    /**
     * 根据角色id查询已绑定的菜单数组
     * @param id 角色id
     * @return 角色的菜单数组
     */
    @Query(value = """
        SELECT 
            menu_id
        FROM 
            auth_role_menu
        WHERE 
            role_id =:id
    """, nativeQuery = true)
    List<Long> selectMenuTreeById(Long id);

    /**
     * 根据角色id删除角色菜单关系
     * @param id 角色id
     * @return
     */
    @Modifying
    @Query(value = """
        DELETE FROM auth_role_menu
        WHERE 
            role_id =:id
    """, nativeQuery = true)
    int deleteRoleMenuRelation(Long id);

    /**
     * 插入角色和菜单对应关系
     * @param menuId 菜单id
     * @param roleId 角色id
     * @return
     */
    @Modifying
    @Query(value = """
        INSERT INTO
            auth_role_menu (role_id, menu_id)
        VALUES
            (?2 , ?1)
    """, nativeQuery = true)
    int insertRoleMenuRelation(Long menuId, Long roleId);

    /**
     * 根据父菜单id获取所有菜单
     * @param parentId 父菜单id
     * @return
     */
    List<AuthMenu> findAllByParentId(Long parentId);

    /**
     * 批量逻辑删除菜单
     * @param ids 菜单id集合
     */
    @Modifying
    @Query(value = """
        UPDATE 
            auth_menu 
        SET 
            del = 0 
        WHERE 
            id IN :ids
    """, nativeQuery = true)
    void deleteAllByIdInBatch(List<Long> ids);

}
