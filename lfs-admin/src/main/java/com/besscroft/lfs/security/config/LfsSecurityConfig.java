package com.besscroft.lfs.security.config;

import com.besscroft.lfs.security.component.DynamicAccessDecisionManager;
import com.besscroft.lfs.security.component.DynamicSecurityMetadataSource;
import com.besscroft.lfs.security.filter.AuthenticationFilter;
import com.besscroft.lfs.security.filter.DynamicSecurityFilter;
import com.besscroft.lfs.security.handler.LfsDeniedHandler;
import com.besscroft.lfs.security.handler.LfsEntryPoint;
import com.besscroft.lfs.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author Bess Croft
 * @Time 2021/7/8 17:45
 */
@Configuration
public class LfsSecurityConfig {
    @Autowired
    private UserService userService;

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return userService::loadUserByUsername;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity
                .authorizeRequests();
        //不需要保护的资源路径允许访问
        for (String url : ignoreUrlsConfig().getUrls()) {
            registry.requestMatchers(url).permitAll();
        }
        //允许跨域请求的OPTIONS请求
        registry.requestMatchers(HttpMethod.OPTIONS)
                .permitAll();
        httpSecurity
                // 关闭跨站请求防护及不使用 session
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest()// 除上面外的所有请求全部需要鉴权认证
                .authenticated();
        // 禁用缓存
        httpSecurity.headers().cacheControl();
        // 自定义权限拦截器JWT过滤器
        httpSecurity.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        // 指定认证错误处理器
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(lfsDeniedHandler())
                .authenticationEntryPoint(lfsEntryPoint());
        // 动态权限校验过滤器
        registry.and().addFilterBefore(dynamicSecurityFilter(), FilterSecurityInterceptor.class);
        return httpSecurity.build();
    }

    @Bean
    public LfsDeniedHandler lfsDeniedHandler() {
        return new LfsDeniedHandler();
    }

    @Bean
    public LfsEntryPoint lfsEntryPoint() {
        return new LfsEntryPoint();
    }

    @Bean
    public AuthenticationFilter authenticationFilter() {
        return new AuthenticationFilter();
    }

    @Bean
    public IgnoreUrlsConfig ignoreUrlsConfig() {
        return new IgnoreUrlsConfig();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DynamicAccessDecisionManager dynamicAccessDecisionManager() {
        return new DynamicAccessDecisionManager();
    }

    @Bean
    public DynamicSecurityFilter dynamicSecurityFilter() {
        return new DynamicSecurityFilter();
    }

    @Bean
    public DynamicSecurityMetadataSource dynamicSecurityMetadataSource() {
        return new DynamicSecurityMetadataSource();
    }

}
