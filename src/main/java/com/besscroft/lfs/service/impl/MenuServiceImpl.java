package com.besscroft.lfs.service.impl;

import com.besscroft.lfs.entity.AuthMenu;
import com.besscroft.lfs.model.MetaVo;
import com.besscroft.lfs.model.RouterVo;
import com.besscroft.lfs.repository.MenuRepository;
import com.besscroft.lfs.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Bess Croft
 * @Time 2021/7/8 15:33
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<RouterVo> getMenuList(Long userId) {
        List<AuthMenu> menuList = menuRepository.findParentAllByUserId(userId);
        menuList.forEach(menu -> {
            List<AuthMenu> childListById = menuRepository.findChildAllByUserIdAndMenuId(userId, menu.getId());
            menu.setChildren(childListById);
        });
        List<RouterVo> routerVoList = new LinkedList<>();
        menuList.forEach(menu -> {
            RouterVo routerVo = new RouterVo();
            routerVo.setName(menu.getName());
            routerVo.setPath(menu.getPath());
            routerVo.setHidden(menu.getHidden() == 0);
            routerVo.setComponent(menu.getComponent());
            routerVo.setMeta(new MetaVo(menu.getTitle(), menu.getIcon(), false));
            List<RouterVo> list = new ArrayList<>();
            if (menu.getChildren().size() > 0 && !menu.getChildren().isEmpty()) {
                routerVo.setAlwaysShow(true);
                routerVo.setRedirect("noRedirect");
                menu.getChildren().forEach(child -> {
                    RouterVo router = new RouterVo();
                    router.setPath(child.getPath());
                    router.setName(child.getName());
                    router.setComponent(child.getComponent());
                    router.setMeta(new MetaVo(child.getTitle(), child.getIcon(), false));
                    router.setHidden(child.getHidden() == 0);
                    list.add(router);
                });
                routerVo.setChildren(list);
            }
            routerVoList.add(routerVo);
        });
        return routerVoList;
    }

}
