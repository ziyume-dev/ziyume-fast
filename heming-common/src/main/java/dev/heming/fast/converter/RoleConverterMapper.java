package dev.heming.fast.converter;

import dev.heming.fast.entity.Role;
import dev.heming.fast.param.role.RoleAddParam;
import dev.heming.fast.param.role.RoleUpdateParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description 角色对象转换器
 * @Author Bess Croft
 * @Date 2023/5/29 17:33
 */
@Mapper(componentModel = "spring")
public interface RoleConverterMapper {

    RoleConverterMapper INSTANCE = Mappers.getMapper(RoleConverterMapper.class);

    Role AddParamToRole(RoleAddParam param);

    Role UpdateParamToRole(RoleUpdateParam param);

}
