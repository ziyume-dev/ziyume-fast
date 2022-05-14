package com.besscroft.lfs.doc.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/5/11 17:08
 */
@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Pisces Lfs")
                        .description("Spring shop sample application")
                        .version("v1.1.0")
                        .license(new License().name("Apache 2.0").url("https://github.com/besscroft/pisces-lfs/blob/main/LICENSE")))
                .externalDocs(new ExternalDocumentation()
                        .description("Pisces Lfs 文档")
                        .url("https://developer.besscroft.com/lfs/"));
    }

}
