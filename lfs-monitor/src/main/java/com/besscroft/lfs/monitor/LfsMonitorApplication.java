package com.besscroft.lfs.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@EnableAdminServer
@SpringBootApplication
public class LfsMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(LfsMonitorApplication.class, args);
        log.info("(♥◠‿◠)ﾉﾞ  LFS 监控 UI 启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }

}
