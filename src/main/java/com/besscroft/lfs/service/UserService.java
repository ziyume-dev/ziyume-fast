package com.besscroft.lfs.service;

import com.besscroft.lfs.entity.AuthRole;
import com.besscroft.lfs.entity.AuthUser;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

/**
 * @Author Bess Croft
 * @Time 2021/7/7 15:54
 */
public interface UserService {

    /**
     * 获取用户信息
     * @param username 用户名
     * @return 用户信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    String login(String username, String password);

    /**
     * 获取当前登录后台用户
     * @param username 用户名
     * @return 用户
     */
    AuthUser getCurrentAdminByUserName(String username);

    /**
     * 获取用户所有的角色
     * @param userId 用户id
     * @return 所有的角色列表
     */
    List<AuthRole> getRoleList(Long userId);

    /**
     * 登录成功后设置登录时间
     * @param loginTime 登录时间
     * @return
     */
    boolean setLoginTime(Date loginTime, Long id);

    /**
     * 系统用户退出登录处理
     * @param adminId
     * @return
     */
    boolean logout(Long adminId);

}
