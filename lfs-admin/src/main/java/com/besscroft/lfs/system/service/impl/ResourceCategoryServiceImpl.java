package com.besscroft.lfs.system.service.impl;

import com.besscroft.lfs.entity.AuthResourceCategory;
import com.besscroft.lfs.system.repository.ResourceCategoryRepository;
import com.besscroft.lfs.system.service.ResourceCategoryService;
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
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    private final ResourceCategoryRepository resourceCategoryRepository;

    @Override
    public Page<AuthResourceCategory> getResourcePageList(Integer pageNum, Integer pageSize, String keyword) {
        return resourceCategoryRepository.findAll(PageRequest.of(Objects.equals(pageNum, 0) ? 0 : pageNum - 1, pageSize));
    }

    @Override
    public AuthResourceCategory getResourceCategoryById(@NonNull Long id) {
        return resourceCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public void addResourceCategory(@NonNull AuthResourceCategory authResourceCategory) {
        authResourceCategory.setCreateTime(LocalDateTime.now());
        resourceCategoryRepository.save(authResourceCategory);
    }

    @Override
    public void updateResourceCategory(@NonNull AuthResourceCategory authResourceCategory) {
        authResourceCategory.setCreateTime(LocalDateTime.now());
        resourceCategoryRepository.save(authResourceCategory);
    }

    @Override
    public void delResourceCategory(@NonNull List<Long> ids) {
        resourceCategoryRepository.deleteAllById(ids);
    }
}
