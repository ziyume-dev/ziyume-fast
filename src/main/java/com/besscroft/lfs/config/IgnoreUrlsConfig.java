package com.besscroft.lfs.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 拦截器白名单配置
 *
 * @Author Bess Croft
 * @Time 2021/7/2 14:40
 */
@Data
@ConfigurationProperties(prefix="whitelist")
public class IgnoreUrlsConfig {

    private List<String> urls;

}
