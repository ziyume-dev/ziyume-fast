package dev.heming.fast.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import dev.heming.fast.converter.ResourceTypeConverterMapper;
import dev.heming.fast.entity.ResourceType;
import dev.heming.fast.mapper.ResourceTypeMapper;
import dev.heming.fast.param.resourceType.ResourceTypeAddParam;
import dev.heming.fast.param.resourceType.ResourceTypeUpdateParam;
import dev.heming.fast.param.resourceType.PageListParam;
import dev.heming.fast.service.ResourceTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 资源类型 Service 实现类
 * @Author Bess Croft
 * @Date 2023/5/25 19:54
 */
@Service
public class ResourceTypeServiceImpl extends ServiceImpl<ResourceTypeMapper, ResourceType> implements ResourceTypeService {

    @Override
    public List<ResourceType> pageList(PageListParam param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        return this.baseMapper.selectResourceTypeList(param.getName(), param.getCode());
    }

    @Override
    public void addResourceType(ResourceTypeAddParam param) {
        ResourceType resourceType = ResourceTypeConverterMapper.INSTANCE.AddParamToUser(param);
        this.baseMapper.insert(resourceType);
    }

    @Override
    public void updateResourceType(ResourceTypeUpdateParam param) {
        ResourceType resourceType = ResourceTypeConverterMapper.INSTANCE.UpdateParamToUser(param);
        this.baseMapper.updateById(resourceType);
    }

    @Override
    public void deleteResourceType(Long resourceTypeId) {
        this.baseMapper.deleteById(resourceTypeId);
    }

}
