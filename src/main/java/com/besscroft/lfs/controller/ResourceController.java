package com.besscroft.lfs.controller;

import com.besscroft.lfs.service.ResourceService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author Bess Croft
 * @Time 2021/12/10 16:00
 */
@Slf4j
@Api(tags = "管理系统资源接口")
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

}
