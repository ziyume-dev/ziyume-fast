package com.besscroft.lfs.service.impl;

import com.besscroft.lfs.entity.AuthResourceSort;
import com.besscroft.lfs.repository.ResourceSortRepository;
import com.besscroft.lfs.service.ResourceSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @Description
 * @Author Bess Croft
 * @Time 2021/12/10 16:06
 */
@Service
public class ResourceSortServiceImpl implements ResourceSortService {

    @Autowired
    private ResourceSortRepository resourceSortRepository;

    @Override
    public Page<AuthResourceSort> getResourcePageList(Integer pageNum, Integer pageSize, String keyword) {
        return resourceSortRepository.findAll(PageRequest.of(Objects.equals(pageNum, 0) ? 0 : pageNum - 1, pageSize));
    }

    @Override
    public AuthResourceSort getResourceSortById(Long id) {
        return resourceSortRepository.findById(id).orElse(null);
    }

    @Override
    public boolean addResourceSort(AuthResourceSort authResourceSort) {
        authResourceSort.setCreateTime(LocalDateTime.now());
        return resourceSortRepository.save(authResourceSort) != null;
    }

    @Override
    public boolean updateResourceSort(AuthResourceSort authResourceSort) {
        authResourceSort.setCreateTime(LocalDateTime.now());
        return resourceSortRepository.save(authResourceSort) != null;
    }

    @Override
    public boolean delResourceSort(List<Long> ids) {
        resourceSortRepository.deleteAllById(ids);
        return true;
    }
}
