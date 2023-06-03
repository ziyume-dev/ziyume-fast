package dev.heming.fast.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import dev.heming.fast.converter.RoleConverterMapper;
import dev.heming.fast.entity.Role;
import dev.heming.fast.mapper.RoleMapper;
import dev.heming.fast.param.role.RoleAddParam;
import dev.heming.fast.param.role.RoleUpdateParam;
import dev.heming.fast.param.role.PageListParam;
import dev.heming.fast.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 角色 Service 实现类
 * @Author Bess Croft
 * @Date 2023/5/25 19:54
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<String> getAllRoleCodeByCurrentUser(Long userId) {
        return this.baseMapper.selectAllCodeByUserId(userId);
    }

    @Override
    public List<Role> pageList(PageListParam param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        return this.baseMapper.selectRoleList(param.getName(), param.getCode());
    }

    @Override
    public void addRole(RoleAddParam param) {
        Role role = RoleConverterMapper.INSTANCE.AddParamToRole(param);
        this.baseMapper.insert(role);
    }

    @Override
    public void updateRole(RoleUpdateParam param) {
        Role role = RoleConverterMapper.INSTANCE.UpdateParamToRole(param);
        this.baseMapper.updateById(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        this.baseMapper.deleteById(roleId);
    }

}
