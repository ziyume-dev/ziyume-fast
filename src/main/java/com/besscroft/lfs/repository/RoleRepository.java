package com.besscroft.lfs.repository;

import com.besscroft.lfs.entity.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author Bess Croft
 * @Time 2021/7/8 15:38
 */
public interface RoleRepository extends JpaRepository<AuthRole, Long> {

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

}
