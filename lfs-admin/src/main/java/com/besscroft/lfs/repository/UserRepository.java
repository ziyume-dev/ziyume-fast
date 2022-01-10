package com.besscroft.lfs.repository;

import com.besscroft.lfs.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * @Author Bess Croft
 * @Time 2021/7/2 9:29
 */
public interface UserRepository extends JpaRepository<AuthUser, Long>, JpaSpecificationExecutor<AuthUser> {

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户
     */
    AuthUser findByUsername(String username);

    /**
     * 登录成功后设置登录时间
     * @param loginTime 登录时间
     * @param id 用户id
     * @return
     */
    @Modifying
    @Query(value = "update auth_user" +
                    " set login_time =:loginTime" +
                    " where id =:id", nativeQuery = true)
    int updateLoginTime(Date loginTime, Long id);

    /**
     * 更新用户账户的可用状态
     * @param status 可以状态
     * @param id 用户id
     * @return
     */
    @Modifying
    @Query(value = "update" +
            "           auth_user" +
            "       set" +
            "           status = ?1" +
            "       where" +
            "           id = ?2" +
            "       and" +
            "           del = '1'", nativeQuery = true)
    int changeSwitch(Integer status, Long id);

}
