package com.besscroft.lfs.system.service.impl;

import com.besscroft.lfs.entity.AuthResourceSort;
import com.besscroft.lfs.system.repository.ResourceSortRepository;
import com.besscroft.lfs.system.service.ResourceSortService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ResourceSortServiceImpl implements ResourceSortService {

    private final ResourceSortRepository resourceSortRepository;

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
        resourceSortRepository.save(authResourceSort);
        return true;
    }

    @Override
    public boolean updateResourceSort(AuthResourceSort authResourceSort) {
        authResourceSort.setCreateTime(LocalDateTime.now());
        resourceSortRepository.save(authResourceSort);
        return true;
    }

    @Override
    public boolean delResourceSort(List<Long> ids) {
        resourceSortRepository.deleteAllById(ids);
        return true;
    }
}
