package com.besscroft.lfs.repository;

import com.besscroft.lfs.entity.AuthResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author Bess Croft
 * @Time 2021/7/2 11:25
 */
public interface ResourceRepository extends JpaRepository<AuthResource, Long> {

    @Query(value = "select r.* from auth_resource r " +
            "inner join auth_role_resource_relation rrr on r.id = rrr.resource_id " +
            "inner join auth_role re on rrr.role_id = re.id " +
            "inner join auth_user_role_relation urr on rrr.id = urr.role_id " +
            "inner join auth_user u on urr.admin_id = u.id " +
            "where u.id =:userId", nativeQuery = true)
    List<AuthResource> findAllByUserId(Long userId);

}
