package com.besscroft.lfs.repository;

import com.besscroft.lfs.entity.WebLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Bess Croft
 * @Time 2021/7/24 12:30
 */
public interface LogRepository extends JpaRepository<WebLog, String> {
}
