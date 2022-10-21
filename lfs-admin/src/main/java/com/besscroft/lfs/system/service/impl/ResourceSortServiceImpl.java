package com.besscroft.lfs.system.service.impl;

import com.besscroft.lfs.entity.AuthResourceSort;
import com.besscroft.lfs.system.repository.ResourceSortRepository;
import com.besscroft.lfs.system.service.ResourceSortService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.NonNull;
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
    public AuthResourceSort getResourceSortById(@NonNull Long id) {
        return resourceSortRepository.findById(id).orElse(null);
    }

    @Override
    public void addResourceSort(@NonNull AuthResourceSort authResourceSort) {
        authResourceSort.setCreateTime(LocalDateTime.now());
        resourceSortRepository.save(authResourceSort);
    }

    @Override
    public void updateResourceSort(@NonNull AuthResourceSort authResourceSort) {
        authResourceSort.setCreateTime(LocalDateTime.now());
        resourceSortRepository.save(authResourceSort);
    }

    @Override
    public void delResourceSort(@NonNull List<Long> ids) {
        resourceSortRepository.deleteAllById(ids);
    }
}
