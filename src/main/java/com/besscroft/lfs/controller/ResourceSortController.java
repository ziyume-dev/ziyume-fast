package com.besscroft.lfs.controller;

import com.besscroft.lfs.service.ResourceSortService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author Bess Croft
 * @Time 2021/12/10 16:01
 */
@Slf4j
@Api(tags = "管理系统资源类别接口")
@RestController
@RequestMapping("/resourceSort")
public class ResourceSortController {

    @Autowired
    private ResourceSortService resourceSortService;

}
