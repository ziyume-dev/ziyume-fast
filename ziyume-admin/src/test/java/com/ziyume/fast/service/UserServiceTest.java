package com.ziyume.fast.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.hutool.json.JSONUtil;
import com.ziyume.fast.TestBase;
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
        SaTokenInfo tokenInfo = userService.login("admin", "666666");
        log.info("tokenInfo:{}", JSONUtil.toJsonStr(tokenInfo));
    }

}
