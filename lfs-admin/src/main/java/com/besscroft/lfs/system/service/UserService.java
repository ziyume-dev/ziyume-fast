package com.besscroft.lfs.system.service;

import com.besscroft.lfs.entity.AuthRole;
import com.besscroft.lfs.entity.AuthUser;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
     * @return 用户实体
     */
    AuthUser getCurrentAdmin();

    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 用户
     */
    AuthUser getCurrentAdminByUserName(String username);

    /**
     * 获取认证后的用户信息
     * @return
     */
    Map<String, Object> getUserInfo();

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
    void setLoginTime(Date loginTime, Long id);

    /**
     * 系统用户退出登录处理
     * @param adminId
     * @return
     */
    boolean logout(Long adminId);

    /**
     * 分页查询用户
     * @param pageNum 页码
     * @param pageSize 页大小
     * @param keyword 关键字
     * @return 分页用户集合
     */
    Page<AuthUser> getUserPageList(Integer pageNum, Integer pageSize, String keyword);

    /**
     * 根据id获取用户信息
     * @param id 用户id
     * @return 用户实体
     */
    AuthUser getUserById(Long id);

    /**
     * 更新用户信息
     * @param authUser 用户实体
     * @return
     */
    void updateUser(AuthUser authUser);

    /**
     * 用户账户启用状态更新
     * @param flag 启用状态
     * @param id 用户id
     * @return
     */
    void changeSwitch(boolean flag, Long id);

    /**
     * 删除权限管理模块用户
     * @param id 用户id
     * @return
     */
    void delUser(Long id);

    /**
     * 新增权限管理模块用户
     * @param authUser 用户实体
     * @return
     */
    void addUser(AuthUser authUser);

    /**
     * 导出选定用户数据方法
     * @param ids 用户id
     * @param response
     */
    void export(List<Long> ids, HttpServletResponse response);

}
