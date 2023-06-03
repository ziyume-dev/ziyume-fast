package dev.heming.fast.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.heming.fast.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 菜单 Mapper
 * @Author Bess Croft
 * @Date 2023/5/25 19:51
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户 id 获取菜单
     * @param userId 用户 id
     * @return 菜单列表
     */
    List<Menu> selectAllByUserId(@Param("userId") Long userId);

    /**
     * 查询菜单列表
     * @param title 菜单名称
     * @param name 前端名称
     * @param path 路由地址
     * @return
     */
    List<Menu> selectMenuList(@Param("title") String title,
                              @Param("name") String name,
                              @Param("path") String path);

}
