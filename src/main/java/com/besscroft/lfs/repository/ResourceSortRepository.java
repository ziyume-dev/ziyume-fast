package com.besscroft.lfs.repository;

import com.besscroft.lfs.entity.AuthResourceSort;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description
 * @Author Bess Croft
 * @Time 2021/12/10 16:07
 */
public interface ResourceSortRepository extends JpaRepository<AuthResourceSort, Long> {
}
