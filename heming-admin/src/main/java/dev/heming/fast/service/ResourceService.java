package dev.heming.fast.service;

import com.baomidou.mybatisplus.extension.service.IService;
import dev.heming.fast.entity.Resource;
import dev.heming.fast.param.resource.ResourceAddParam;
import dev.heming.fast.param.resource.ResourceUpdateParam;
import dev.heming.fast.param.resource.PageListParam;

import java.util.List;
import java.util.Map;

/**
 * @Description 资源 Service
 * @Author Bess Croft
 * @Date 2023/5/25 19:54
 */
public interface ResourceService extends IService<Resource> {

    /**
     * 初始化资源角色规则
     * @return 资源角色规则
     */
    Map<String, List<String>> initRoleResourceMap();

    /**
     * 分页查询
     * @param param 分页参数
     * @return 分页结果
     */
    List<Resource> pageList(PageListParam param);

    /**
     * 新增资源
     * @param param 资源新增参数
     */
    void addResource(ResourceAddParam param);

    /**
     * 更新资源
     * @param param 资源更新参数
     */
    void updateResource(ResourceUpdateParam param);

    /**
     * 删除资源
     * @param resourceId 资源 id
     */
    void deleteResource(Long resourceId);

}
