package com.besscroft.lfs.system.service;

import com.besscroft.lfs.entity.AuthResource;
import com.besscroft.lfs.model.ResourceTree;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author Bess Croft
 * @Time 2021/7/7 16:53
 */
public interface ResourceService {

    /**
     * 查询用户的所有资源集合
     * @param userId 用户id
     * @return 资源集合
     */
    List<AuthResource> getResourceList(Long userId);

    /**
     * 查询所有资源
     * @return 资源集合
     */
    List<AuthResource> listAll();

    /**
     * 分页查询资源列表
     * @param pageNum 页码
     * @param pageSize 页大小
     * @param keyword 关键字
     * @return 分页资源列表
     */
    Page<AuthResource> getResourcePageList(Integer pageNum, Integer pageSize, String keyword);

    /**
     * 根据id获取资源详情
     * @param id 资源id
     * @return 资源实体
     */
    AuthResource getResourceById(Long id);

    /**
     * 新增资源
     * @param authResource 资源实体
     * @return
     */
    void addResource(AuthResource authResource);

    /**
     * 更新资源信息
     * @param authResource 资源实体
     * @return
     */
    void updateResource(AuthResource authResource);

    /**
     * 根据id删除资源
     * @param ids 资源id集合
     * @return
     */
    void delResource(List<Long> ids);

    /**
     * 获取所有资源的资源树
     * @return 所有的资源树集合
     */
    List<ResourceTree> getAllResourceTree();

    /**
     * 根据角色id获取资源树数组
     * @param id 角色id
     * @return 资源树数组
     */
    List<Long> getResourceTreeById(Long id);

    /**
     * 批量更新资源树
     * @param resourceIds 资源id集合
     * @param id 角色id
     * @return
     */
    void updateResourceTree(List<Long> resourceIds, Long id);

}
