package com.besscroft.lfs.repository;

import com.besscroft.lfs.entity.AuthRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author Bess Croft
 * @Time 2021/7/8 15:38
 */
public interface RoleRepository extends JpaRepository<AuthRole, Long>, JpaSpecificationExecutor<AuthRole> {

    /**
     * 根据用户id查询所有角色
     * @param userId 用户id
     * @return 角色集合
     */
    @Query(value = "select r.* from auth_role r " +
            "inner join auth_user_role_relation urr on r.id = urr.role_id " +
            "inner join auth_user u on urr.admin_id = u.id " +
            "where u.id =:userId", nativeQuery = true)
    List<AuthRole> findAllByUserId(Long userId);

    /**
     * 查询所有角色
     * @return 角色集合
     */
    List<AuthRole> findAll();

    /**
     * 分页查询角色列表
     * @param pageable
     * @return
     */
    @Query(value = "select" +
            "           id, name, description, admin_count, create_time, status, sort, del" +
            "       from" +
            "           auth_role" +
            "       where" +
            "           del = 1" +
            "       order by id", nativeQuery = true)
    Page<AuthRole> findAll(Pageable pageable);

    /**
     * 角色可用状态变更
     * @param status 可用状态
     * @param id 角色id
     * @return
     */
    @Modifying
    @Query(value = "update" +
            "           auth_role" +
            "        set" +
            "           status =:status" +
            "        where" +
            "            id =:id", nativeQuery = true)
    int changeSwitch(Integer status, Long id);

    /**
     * 根据用户id删除用户和角色对应关系
     * @param userId 用户id
     * @return
     */
    @Modifying
    @Query(value = "delete from" +
            "           auth_user_role_relation" +
            "           where" +
            "               admin_id =:userId", nativeQuery = true)
    int deleteUserRoleRelationById(Long userId);

    /**
     * 更新用户和角色对应关系
     * @param userId 用户id
     * @param roleId 角色id
     * @return
     */
    @Modifying
    @Query(value = "insert into" +
            "           auth_user_role_relation" +
            "            (admin_id, role_id)" +
            "        values" +
            "            (?1, ?2)", nativeQuery = true)
    int insertUserRoleRelation(Long userId, Long roleId);

}
