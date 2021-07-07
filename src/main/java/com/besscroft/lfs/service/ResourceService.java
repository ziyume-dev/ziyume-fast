package com.besscroft.lfs.service;

import com.besscroft.lfs.entity.AuthResource;

import java.util.List;

/**
 * @Author Bess Croft
 * @Time 2021/7/7 16:53
 */
public interface ResourceService {

    List<AuthResource> getResourceList(Long userId);

}
