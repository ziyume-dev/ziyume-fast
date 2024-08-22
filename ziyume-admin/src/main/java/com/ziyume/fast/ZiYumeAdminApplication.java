package com.ziyume.fast;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@Slf4j
@EnableCaching
@MapperScan("com.ziyume.fast.mapper")
@SpringBootApplication(scanBasePackages = "com.ziyume.fast")
public class ZiYumeAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZiYumeAdminApplication.class, args);
        log.info("(♥◠‿◠)ﾉﾞ  紫梦 Fast 服务启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }

}
