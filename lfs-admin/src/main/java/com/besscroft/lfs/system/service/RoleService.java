package com.besscroft.lfs.system.service;

import com.besscroft.lfs.entity.AuthRole;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author Bess Croft
 * @Time 2021/7/8 18:09
 */
public interface RoleService {

    /**
     * 获取所有角色
     * @return
     */
    List<AuthRole> listAll();

    /**
     * 分页查询角色列表
     * @param pageNum 页码
     * @param pageSize 页大小
     * @param keyword 关键字
     * @return 分页角色列表
     */
    Page<AuthRole> getRolePageList(Integer pageNum, Integer pageSize, String keyword);

    /**
     * 根据id查询角色详情
     * @param id 角色id
     * @return 角色实体
     */
    AuthRole getRoleById(Long id);

    /**
     * 新增角色
     * @param authRole 角色实体
     * @return
     */
    void addRole(AuthRole authRole);

    /**
     * 更新角色信息
     * @param authRole 角色实体
     * @return
     */
    void updateRole(AuthRole authRole);

    /**
     * 根据角色id进行假删除
     * @param ids 角色id集合
     * @return
     */
    void delRoleById(List<Long> ids);

    /**
     * 角色是否可用状态更新
     * @param status 可以状态
     * @param id 角色id
     * @return
     */
    void changeSwitch(boolean status, Long id);

    /**
     * 根据用户id和角色id修改用户和角色的对应关系
     * @param userId 用户id
     * @param roleId 角色id
     * @return
     */
    void updateRoleById(Long userId, Long roleId);

}
