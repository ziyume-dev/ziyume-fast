package com.besscroft.lfs.converter;

import com.besscroft.lfs.dto.AuthUserExcelDto;
import com.besscroft.lfs.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Time 2021/12/12 16:14
 */
@Mapper(componentModel = "spring")
public interface UserConverterMapper {

    UserConverterMapper INSTANCE = Mappers.getMapper(UserConverterMapper.class);

    @Mappings({
            @Mapping(target = "status", expression = "java(IntegerToString(user.getStatus()))"),
            @Mapping(target = "del", expression = "java(IntegerToString(user.getDel()))")
    })
    AuthUserExcelDto authUserToAuthUserExcelDto(AuthUser user);

    List<AuthUserExcelDto> authUserToAuthUserExcelListDto(List<AuthUser> user);

    default String IntegerToString(Integer source) {
        return String.valueOf(source);
    }

}
