package dev.heming.fast.service;

import com.baomidou.mybatisplus.extension.service.IService;
import dev.heming.fast.entity.ResourceType;
import dev.heming.fast.param.resourceType.ResourceTypeAddParam;
import dev.heming.fast.param.resourceType.ResourceTypeUpdateParam;
import dev.heming.fast.param.resourceType.PageListParam;

import java.util.List;

/**
 * @Description 资源类型 Service
 * @Author Bess Croft
 * @Date 2023/5/25 19:54
 */
public interface ResourceTypeService extends IService<ResourceType> {

    /**
     * 分页查询
     * @param param 分页参数
     * @return 分页结果
     */
    List<ResourceType> pageList(PageListParam param);

    /**
     * 新增资源类型
     * @param param 资源类型新增参数
     */
    void addResourceType(ResourceTypeAddParam param);

    /**
     * 更新资源类型
     * @param param 资源类型更新参数
     */
    void updateResourceType(ResourceTypeUpdateParam param);

    /**
     * 删除资源类型
     * @param resourceTypeId 资源类型 id
     */
    void deleteResourceType(Long resourceTypeId);

}
