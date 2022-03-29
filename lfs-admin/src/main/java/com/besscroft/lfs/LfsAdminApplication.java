package com.besscroft.lfs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = "com.besscroft.lfs")
public class LfsAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(LfsAdminApplication.class, args);
        log.info("(♥◠‿◠)ﾉﾞ  LFS服务启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }

}
