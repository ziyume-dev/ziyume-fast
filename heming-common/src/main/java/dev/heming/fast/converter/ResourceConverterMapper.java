package dev.heming.fast.converter;

import dev.heming.fast.entity.Resource;
import dev.heming.fast.param.resource.ResourceAddParam;
import dev.heming.fast.param.resource.ResourceUpdateParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description 资源对象转换器
 * @Author Bess Croft
 * @Date 2023/5/29 17:33
 */
@Mapper(componentModel = "spring")
public interface ResourceConverterMapper {

    ResourceConverterMapper INSTANCE = Mappers.getMapper(ResourceConverterMapper.class);

    Resource AddParamToUser(ResourceAddParam param);

    Resource UpdateParamToUser(ResourceUpdateParam param);

}
