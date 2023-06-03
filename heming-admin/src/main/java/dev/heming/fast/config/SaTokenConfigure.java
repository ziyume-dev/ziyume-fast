package dev.heming.fast.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import dev.heming.fast.constant.CacheConstants;
import dev.heming.fast.exception.HeMingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Description Sa-Token 注解鉴权注册拦截器配置
 * @see https://sa-token.cc/doc.html#/use/route-check
 * @Author Bess Croft
 * @Date 2023/1/15 19:55
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class SaTokenConfigure implements WebMvcConfigurer {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，打开注解式鉴权功能
        registry.addInterceptor(new SaInterceptor(handle -> {
            // 登录校验，登录后才允许放行
            StpUtil.checkLogin();
            // 权限校验
            if (StpUtil.isLogin()) {
                List<String> roleList = StpUtil.getRoleList();
                List<Object> objectList = List.copyOf(roleList);
                String requestPath = SaHolder.getRequest().getRequestPath();
                List<String> roleResourceList = CollUtil.newArrayList();
                List<Object> objects = redisTemplate.opsForHash().multiGet(CacheConstants.PERMISSION_RULES_KEY, objectList);
                for (Object o: objects) {
                    roleResourceList.addAll(JSONUtil.toList(JSONUtil.parseArray(o), String.class));
                }
                if (CollUtil.isNotEmpty(roleResourceList)) {
                    log.info("当前用户所有角色允许的路径：{}", JSONUtil.toJsonStr(roleResourceList));
                    // 判断路径是否在角色允许的路径中
                    if (!roleResourceList.contains(requestPath)) {
                        throw new HeMingException(HttpStatus.FORBIDDEN.value(), "暂无权限！");
                    }
                }
            }
        })).addPathPatterns("/**")
        .excludePathPatterns(
                "/v3/**",
                "/api-docs/**",
                "/swagger-ui.html/**",
                "/swagger-ui/**",
                "/doc.html/**",
                "/error",
                "/favicon.ico",
                "/actuator/**",
                "/api-docs.yaml"
        );
    }

}
