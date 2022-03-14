package com.besscroft.lfs.service.impl;

import com.besscroft.lfs.entity.AuthResource;
import com.besscroft.lfs.entity.AuthResourceSort;
import com.besscroft.lfs.model.ResourceParam;
import com.besscroft.lfs.repository.ResourceRepository;
import com.besscroft.lfs.repository.ResourceSortRepository;
import com.besscroft.lfs.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author Bess Croft
 * @Time 2021/7/7 16:53
 */
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;
    private final ResourceSortRepository resourceSortRepository;

    @Override
    public List<AuthResource> getResourceList(Long userId) {
        return resourceRepository.findAllByUserId(userId);
    }

    @Override
    public List<AuthResource> listAll() {
        return resourceRepository.findAll();
    }

    @Override
    public Page<AuthResource> getResourcePageList(Integer pageNum, Integer pageSize, String keyword) {
        return resourceRepository.findAll(PageRequest.of(Objects.equals(pageNum, 0) ? 0 : pageNum - 1, pageSize));
    }

    @Override
    public AuthResource getResourceById(Long id) {
        return resourceRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addResource(AuthResource authResource) {
        authResource.setCreateTime(LocalDateTime.now());
        return resourceRepository.save(authResource) != null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateResource(AuthResource authResource) {
        return resourceRepository.save(authResource) != null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delResource(List<Long> ids) {
        resourceRepository.deleteAllById(ids);
        return true;
    }

    @Override
    public List<ResourceParam> getAllResourceTree() {
        List<ResourceParam> list = new ArrayList<>();
        List<AuthResourceSort> resourceSorts = resourceSortRepository.findAll();
        resourceSorts.forEach(r -> {
            ResourceParam resourceParam = new ResourceParam();
            List<AuthResource> resources = resourceRepository.findAllByCategoryId(r.getId());
            resourceParam.setName(r.getCategoryName());
            resourceParam.setDisabled(true);
            resourceParam.setChildren(resources);
            list.add(resourceParam);
        });
        return list;
    }

    @Override
    public List<Long> getResourceTreeById(Long id) {
        return resourceRepository.selectResourceTreeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateResourceTree(List<Long> resourceIds, Long id) {
        int i = resourceRepository.deleteRoleResourceRelation(id);
        if (i > 0) {
            for (Long resourceId : resourceIds) {
                resourceRepository.insertRoleResourceRelation(resourceId, id);
            }
        }
        return true;
    }
}
