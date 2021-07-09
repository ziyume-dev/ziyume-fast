package com.besscroft.lfs.service;

import com.besscroft.lfs.entity.AuthResource;

import java.util.List;

/**
 * @Author Bess Croft
 * @Time 2021/7/7 16:53
 */
public interface ResourceService {

    /**
     * 查询用户的所有资源集合
     * @param userId 用户id
     * @return 资源集合
     */
    List<AuthResource> getResourceList(Long userId);

    /**
     * 查询所有资源
     * @return 资源集合
     */
    List<AuthResource> listAll();

}
