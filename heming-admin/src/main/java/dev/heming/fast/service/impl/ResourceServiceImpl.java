package dev.heming.fast.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import dev.heming.fast.constant.CacheConstants;
import dev.heming.fast.converter.ResourceConverterMapper;
import dev.heming.fast.dto.RoleResourceDto;
import dev.heming.fast.entity.Resource;
import dev.heming.fast.entity.Role;
import dev.heming.fast.mapper.ResourceMapper;
import dev.heming.fast.mapper.RoleMapper;
import dev.heming.fast.param.resource.ResourceAddParam;
import dev.heming.fast.param.resource.ResourceUpdateParam;
import dev.heming.fast.param.resource.PageListParam;
import dev.heming.fast.service.ResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @Description 资源 Service 实现类
 * @Author Bess Croft
 * @Date 2023/5/25 19:54
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    private final static ObjectMapper objectMapper = new ObjectMapper();
    private final RedisTemplate<String, Object> redisTemplate;
    private final RoleMapper roleMapper;

    @Override
    public Map<String, List<String>> initRoleResourceMap() {
        Map<String,List<String>> roleResourceMap = new TreeMap<>();
        List<Resource> resourceList = this.list();
        List<Role> roleList = roleMapper.selectList(new QueryWrapper<>());
        List<RoleResourceDto> roleResourceList = roleMapper.findRoleResource();
        for (Role role: roleList) {
            Set<Long> resourceIds = roleResourceList.stream().filter(item ->
                            item.getRoleId().equals(role.getId())
                    ).map(RoleResourceDto::getResourceId)
                    .collect(Collectors.toSet());
            List<String> urlList = resourceList.stream().filter(item ->
                    resourceIds.contains(item.getId())
            ).map(Resource::getUrl).collect(Collectors.toList());
            // key 为角色 code，value 为访问路径/资源路径
            roleResourceMap.put(role.getCode(), urlList);
        }
        redisTemplate.delete(CacheConstants.PERMISSION_RULES_KEY);
        redisTemplate.opsForHash().putAll(CacheConstants.PERMISSION_RULES_KEY, roleResourceMap);
        try {
            log.info("权限初始化成功.[RoleResourceMap={}]", objectMapper.writeValueAsString(roleResourceMap));
        } catch (JsonProcessingException e) {
            log.warn("权限初始化对象解析失败.[RoleResourceMap={}]", roleResourceMap);
        }
        return roleResourceMap;
    }

    @Override
    public List<Resource> pageList(PageListParam param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        return this.baseMapper.selectResourceList(param.getName(), param.getUrl(), param.getTypeCode(), param.getRouteKey());
    }

    @Override
    public void addResource(ResourceAddParam param) {
        Resource resource = ResourceConverterMapper.INSTANCE.AddParamToUser(param);
        this.baseMapper.insert(resource);
    }

    @Override
    public void updateResource(ResourceUpdateParam param) {
        Resource resource = ResourceConverterMapper.INSTANCE.UpdateParamToUser(param);
        this.baseMapper.updateById(resource);
    }

    @Override
    public void deleteResource(Long resourceId) {
        this.baseMapper.deleteById(resourceId);
    }

}
