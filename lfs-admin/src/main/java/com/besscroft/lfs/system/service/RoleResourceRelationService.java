package com.besscroft.lfs.system.service;

import com.besscroft.lfs.model.RoleResourceRelation;

import java.util.List;

/**
 * @Author Bess Croft
 * @Time 2021/7/8 18:17
 */
public interface RoleResourceRelationService {

    /**
     * 获取所有角色和资源关系
     * @return
     */
    List<RoleResourceRelation> selectAll();

}
