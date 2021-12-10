package com.besscroft.lfs.service.impl;

import com.besscroft.lfs.repository.ResourceSortRepository;
import com.besscroft.lfs.service.ResourceSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author Bess Croft
 * @Time 2021/12/10 16:06
 */
@Service
public class ResourceSortServiceImpl implements ResourceSortService {

    @Autowired
    private ResourceSortRepository resourceSortRepository;

}
