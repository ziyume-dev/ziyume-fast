package com.besscroft.lfs.repository;

import com.besscroft.lfs.model.RoleResourceRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author Bess Croft
 * @Time 2021/7/8 18:13
 */
public interface RoleResourceRelationRepository extends JpaRepository<RoleResourceRelation, Long>, JpaSpecificationExecutor<RoleResourceRelation> {

}
