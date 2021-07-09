package com.besscroft.lfs.service;

import com.besscroft.lfs.model.RouterVo;

import java.util.List;

/**
 * @Author Bess Croft
 * @Time 2021/7/8 15:33
 */
public interface MenuService {

    /**
     * 获取用户的路由树
     * @param userId 用户id
     * @return 路由树
     */
    List<RouterVo> getMenuList(Long userId);

}
