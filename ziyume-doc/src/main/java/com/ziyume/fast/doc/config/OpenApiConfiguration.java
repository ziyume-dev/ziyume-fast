package com.ziyume.fast.doc.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description OpenApi 配置
 * @Author Bess Croft
 * @Date 2022/5/11 17:08
 */
@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("ZiYume Fast")
                        .description("ziyume-fast 是一个基于 SpringBoot 3 的 infra 快速启动框架，可基于此框架快速构建系统！")
                        .version("v1.0.0")
                        .license(new License().name("MIT License").url("https://github.com/ziyume-dev/ziyume-fast/blob/main/LICENSE")));
    }

}
