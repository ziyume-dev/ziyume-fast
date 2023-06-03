package dev.heming.fast.converter;

import dev.heming.fast.entity.User;
import dev.heming.fast.param.user.UserAddParam;
import dev.heming.fast.param.user.UserUpdateParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description 用户对象转换器
 * @Author Bess Croft
 * @Date 2023/5/29 16:19
 */
@Mapper(componentModel = "spring")
public interface UserConverterMapper {

    UserConverterMapper INSTANCE = Mappers.getMapper(UserConverterMapper.class);

    User AddParamToUser(UserAddParam param);

    User UpdateParamToUser(UserUpdateParam param);

}
