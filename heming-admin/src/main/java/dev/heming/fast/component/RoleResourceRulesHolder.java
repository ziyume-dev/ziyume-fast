package dev.heming.fast.component;

import dev.heming.fast.service.ResourceService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Description 角色资源规则初始化 处理
 * @Author Bess Croft
 * @Date 2023/5/26 16:14
 */
@Component
@RequiredArgsConstructor
public class RoleResourceRulesHolder {

    private final ResourceService resourceService;

    @PostConstruct
    public void initRoleResourceMap() {
        resourceService.initRoleResourceMap();
    }

}
