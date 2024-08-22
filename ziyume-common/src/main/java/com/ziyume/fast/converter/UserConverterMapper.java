package com.ziyume.fast.converter;

import com.ziyume.fast.entity.User;
import com.ziyume.fast.param.user.UserAddParam;
import com.ziyume.fast.param.user.UserUpdateParam;
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
