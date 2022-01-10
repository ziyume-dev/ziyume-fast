package com.besscroft.lfs.service;

import com.besscroft.lfs.TestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Bess Croft
 * @Time 2021/7/7 18:14
 */
@Slf4j
public class UserServiceTest extends TestBase {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        String token = userService.login("admin", "666666");
        log.info("token:{}", token);
    }

}
