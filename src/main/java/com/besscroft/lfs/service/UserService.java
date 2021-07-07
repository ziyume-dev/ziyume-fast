package com.besscroft.lfs.service;

/**
 * @Author Bess Croft
 * @Time 2021/7/7 15:54
 */
public interface UserService {

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    String login(String username, String password);

}
