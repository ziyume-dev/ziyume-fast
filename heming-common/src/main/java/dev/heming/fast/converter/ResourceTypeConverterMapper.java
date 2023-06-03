package dev.heming.fast.converter;

import dev.heming.fast.entity.ResourceType;
import dev.heming.fast.param.resourceType.ResourceTypeAddParam;
import dev.heming.fast.param.resourceType.ResourceTypeUpdateParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description 资源类别对象转换器
 * @Author Bess Croft
 * @Date 2023/5/29 17:33
 */
@Mapper(componentModel = "spring")
public interface ResourceTypeConverterMapper {

    ResourceTypeConverterMapper INSTANCE = Mappers.getMapper(ResourceTypeConverterMapper.class);

    ResourceType AddParamToUser(ResourceTypeAddParam param);

    ResourceType UpdateParamToUser(ResourceTypeUpdateParam param);

}
