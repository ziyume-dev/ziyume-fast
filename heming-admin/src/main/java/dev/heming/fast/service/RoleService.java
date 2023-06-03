package dev.heming.fast.service;

import com.baomidou.mybatisplus.extension.service.IService;
import dev.heming.fast.entity.Role;
import dev.heming.fast.param.role.RoleAddParam;
import dev.heming.fast.param.role.RoleUpdateParam;
import dev.heming.fast.param.role.PageListParam;

import java.util.List;

/**
 * @Description 角色 Service
 * @Author Bess Croft
 * @Date 2023/5/25 19:54
 */
public interface RoleService extends IService<Role> {

    /**
     * 获取用户所有角色 code
     * @param userId 用户 id
     * @return 角色 code 集合
     */
    List<String> getAllRoleCodeByCurrentUser(Long userId);

    /**
     * 分页查询
     * @param param 分页参数
     * @return 分页结果
     */
    List<Role> pageList(PageListParam param);

    /**
     * 新增角色
     * @param param 角色新增参数
     */
    void addRole(RoleAddParam param);

    /**
     * 更新角色
     * @param param 角色更新参数
     */
    void updateRole(RoleUpdateParam param);

    /**
     * 删除角色
     * @param roleId 角色 id
     */
    void deleteRole(Long roleId);

}
