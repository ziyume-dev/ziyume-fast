package com.besscroft.lfs.service.impl;

import com.besscroft.lfs.entity.AuthResource;
import com.besscroft.lfs.repository.ResourceRepository;
import com.besscroft.lfs.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Bess Croft
 * @Time 2021/7/7 16:53
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public List<AuthResource> getResourceList(Long userId) {
        List<AuthResource> resourceList = resourceRepository.findAllByUserId(userId);
        return resourceList;
    }
}
