package com.besscroft.lfs.system.config;

import cn.hutool.core.util.StrUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/**
 * @Description sql 注入配置，注入创建人和修改人
 * @Author Bess Croft
 * @Date 2022/10/27 16:20
 */
@Configuration
public class AuditorConfig implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(StrUtil.hasEmpty(username)){
            throw new UsernameNotFoundException("暂未登录或token已经过期");
        }
        return Optional.ofNullable(username);
    }

}
