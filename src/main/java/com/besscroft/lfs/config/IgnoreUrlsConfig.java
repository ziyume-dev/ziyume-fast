package com.besscroft.lfs.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 拦截器白名单配置
 *
 * @Author Bess Croft
 * @Time 2021/7/2 14:40
 */
@Data
@Component
@ConfigurationProperties(prefix="whitelist")
public class IgnoreUrlsConfig {

    private List<String> urls;

}
