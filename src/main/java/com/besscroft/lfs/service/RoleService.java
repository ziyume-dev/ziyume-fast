package com.besscroft.lfs.service;

import com.besscroft.lfs.entity.AuthRole;

import java.util.List;

/**
 * @Author Bess Croft
 * @Time 2021/7/8 18:09
 */
public interface RoleService {

    /**
     * 获取所有角色
     * @return
     */
    List<AuthRole> listAll();

}
