package dev.heming.fast.converter;

import dev.heming.fast.entity.Menu;
import dev.heming.fast.param.menu.MenuAddParam;
import dev.heming.fast.param.menu.MenuUpdateParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description 菜单对象转换器
 * @Author Bess Croft
 * @Date 2023/5/29 17:33
 */
@Mapper(componentModel = "spring")
public interface MenuConverterMapper {

    MenuConverterMapper INSTANCE = Mappers.getMapper(MenuConverterMapper.class);

    Menu AddParamToUser(MenuAddParam param);

    Menu UpdateParamToUser(MenuUpdateParam param);

}
