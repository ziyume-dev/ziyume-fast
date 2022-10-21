package com.besscroft.lfs.system.repository;

import com.besscroft.lfs.entity.AuthResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author Bess Croft
 * @Time 2021/7/2 11:25
 */
public interface ResourceRepository extends JpaRepository<AuthResource, Long>, JpaSpecificationExecutor<AuthResource> {

    /**
     * 根据用户id，查询所有资源
     * @param userId 用户id
     * @return 资源集合
     */
    @Query(value = "select ar.* from auth_resource ar\n" +
            "inner join auth_role_resource_relation arrr on ar.id = arrr.resource_id\n" +
            "inner join auth_role re on arrr.role_id = re.id \n" +
            "inner join auth_user_role_relation aurr on re.id = aurr.role_id\n" +
            "inner join auth_user au on aurr.user_id = au.id\n" +
            "where au.id =:userId", nativeQuery = true)
    List<AuthResource> findAllByUserId(Long userId);

    /**
     * 查询所有资源
     * @return 资源集合
     */
    List<AuthResource> findAll();

    /**
     * 根据资源类别 id 查询资源
     * @param categoryId 资源类别 id
     * @return
     */
    List<AuthResource> findAllByCategoryId(Long categoryId);

    /**
     * 根据角色id获取资源树数组
     * @param id 角色id
     * @return 角色的资源树数组
     */
    @Query(value = "select" +
            "           resource_id" +
            "       from" +
            "           auth_role_resource_relation" +
            "       where" +
            "           role_id =:id", nativeQuery = true)
    List<Long> selectResourceTreeById(Long id);

    /**
     * 根据角色id删除角色资源关系
     * @param id 角色id
     * @return
     */
    @Modifying
    @Query(value = "delete" +
            "       from" +
            "           auth_role_resource_relation" +
            "       where" +
            "           role_id =:id", nativeQuery = true)
    int deleteRoleResourceRelation(Long id);

    /**
     * 插入角色和资源对应关系
     * @param resourceId 资源id
     * @param roleId 角色id
     * @return
     */
    @Modifying
    @Query(value = "insert into" +
            "       auth_role_resource_relation" +
            "            (role_id, resource_id)" +
            "        values" +
            "            (?2 , ?1)", nativeQuery = true)
    int insertRoleResourceRelation(Long resourceId, Long roleId);

    /**
     * 批量逻辑删除角色
     * @param ids 角色id集合
     */
    @Modifying
    @Query(value = "UPDATE auth_role SET del = 0 WHERE id IN :ids", nativeQuery = true)
    void deleteAllById(List<Long> ids);

}
