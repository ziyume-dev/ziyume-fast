package com.besscroft.lfs.repository;

import com.besscroft.lfs.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Bess Croft
 * @Time 2021/7/2 9:29
 */
public interface UserRepository extends JpaRepository<AuthUser, Long> {

    AuthUser findByUsername(String username);

}
