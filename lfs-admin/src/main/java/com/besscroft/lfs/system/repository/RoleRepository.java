package com.besscroft.lfs.system.repository;

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
     * 角色可用状态变更
     * @param status 可用状态
     * @param id 角色id
     * @return
     */
    @Modifying
    @Query(value = """
        UPDATE 
            auth_role
        SET 
            status =:status
        WHERE 
            id =:id
    """, nativeQuery = true)
    int changeSwitch(Integer status, Long id);

    /**
     * 根据用户id删除用户和角色对应关系
     * @param userId 用户id
     * @return
     */
    @Modifying
    @Query(value = """
        DELETE FROM auth_user_role
        WHERE 
            user_id =:userId
    """, nativeQuery = true)
    int deleteUserRoleRelationById(Long userId);

    /**
     * 更新用户和角色对应关系
     * @param userId 用户id
     * @param roleId 角色id
     * @return
     */
    @Modifying
    @Query(value = """
        INSERT INTO 
            auth_user_role (user_id, role_id)
        VALUES 
            (?1, ?2)
    """, nativeQuery = true)
    int insertUserRoleRelation(Long userId, Long roleId);

    /**
     * 批量逻辑删除角色
     * @param ids 角色id集合
     */
    @Modifying
    @Query(value = """
        UPDATE 
            auth_resource 
        SET 
            del = 0
        WHERE 
            id IN :ids
    """, nativeQuery = true)
    void deleteAllByIdInBatch(List<Long> ids);

}
