package dev.heming.fast.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.heming.fast.dto.RoleResourceDto;
import dev.heming.fast.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 角色 Mapper
 * @Author Bess Croft
 * @Date 2023/5/25 19:51
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户 id 查询所有角色 code
     * @param userId 用户 id
     * @return 角色 code 集合
     */
    List<String> selectAllCodeByUserId(@Param("userId") Long userId);

    /**
     * 获取角色资源关系
     * @return 角色资源关系
     */
    List<RoleResourceDto> findRoleResource();

    /**
     * 查询角色列表
     * @param name 角色名称
     * @param code 角色编码
     * @return 角色列表
     */
    List<Role> selectRoleList(@Param("name") String name,
                              @Param("code") String code);

}
