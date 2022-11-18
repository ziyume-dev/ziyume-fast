package com.besscroft.lfs.system.service;

import com.besscroft.lfs.entity.AuthResourceCategory;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Time 2021/12/10 16:06
 */
public interface ResourceCategoryService {

    /**
     * 分页查询资源类别列表
     * @param pageNum 页码
     * @param pageSize 页大小
     * @param keyword 关键字
     * @return 分页资源类型列表
     */
    Page<AuthResourceCategory> getResourcePageList(Integer pageNum, Integer pageSize, String keyword);

    /**
     * 根据id获取资源类别详情
     * @param id 资源类别id
     * @return 资源类别实体
     */
    AuthResourceCategory getResourceCategoryById(Long id);

    /**
     * 新增资源类别
     * @param authResourceCategory 资源类别实体
     * @return
     */
    void addResourceCategory(AuthResourceCategory authResourceCategory);

    /**
     * 修改资源类别
     * @param authResourceCategory 资源类别实体
     * @return
     */
    void updateResourceCategory(AuthResourceCategory authResourceCategory);

    /**
     * 删除资源类别
     * @param ids 资源类别id集合
     * @return
     */
    void delResourceCategory(List<Long> ids);

}
