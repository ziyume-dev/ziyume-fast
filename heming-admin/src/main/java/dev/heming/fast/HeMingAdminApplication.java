package dev.heming.fast;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@Slf4j
@EnableCaching
@MapperScan("dev.heming.fast.mapper")
@SpringBootApplication(scanBasePackages = "dev.heming.fast")
public class HeMingAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeMingAdminApplication.class, args);
        log.info("(♥◠‿◠)ﾉﾞ  鹤鸣 Fast 服务启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }

}
