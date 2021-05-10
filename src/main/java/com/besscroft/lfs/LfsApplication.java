package com.besscroft.lfs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.besscroft.lfs.mapper")
public class LfsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LfsApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  LFS服务启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }

}
