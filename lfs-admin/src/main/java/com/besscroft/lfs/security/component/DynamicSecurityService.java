package com.besscroft.lfs.security.component;

import com.besscroft.lfs.entity.AuthResource;
import com.besscroft.lfs.system.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 动态权限相关业务类
 * @Author Bess Croft
 * @Time 2021/7/7 17:12
 */
@Component
public class DynamicSecurityService {

    @Autowired
    private ResourceService resourceService;

    public Map<String, ConfigAttribute> loadDataSource() {
        Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
        List<AuthResource> resourceList = resourceService.listAll();
        for (AuthResource resource : resourceList) {
            map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
        }
        return map;
    }

}
