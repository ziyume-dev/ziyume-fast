package com.besscroft.lfs.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.besscroft.lfs.entity.AuthMenu;
import com.besscroft.lfs.entity.AuthUser;
import com.besscroft.lfs.model.MetaVo;
import com.besscroft.lfs.model.RouterVo;
import com.besscroft.lfs.repository.MenuRepository;
import com.besscroft.lfs.service.MenuService;
import com.besscroft.lfs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @Author Bess Croft
 * @Time 2021/7/8 15:33
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserService userService;

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
            routerVo.setHidden(menu.getHidden() == 1);
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
                    router.setHidden(child.getHidden() == 1);
                    list.add(router);
                });
                routerVo.setChildren(list);
            }
            routerVoList.add(routerVo);
        });
        return routerVoList;
    }

    @Override
    public List<AuthMenu> getMenuListById(Long adminId) {
        List<AuthMenu> authMenuList = menuRepository.findParentAllByUserId(adminId);
        authMenuList.forEach(menu -> {
            List<AuthMenu> childListById = menuRepository.findChildAllByUserIdAndMenuId(adminId, menu.getId());
            menu.setChildren(childListById);
        });
        return authMenuList;
    }

    @Override
    public Page<AuthMenu> getMenuPageList(Integer pageNum, Integer pageSize, String keyword) {
        return menuRepository.findAll(PageRequest.of(Objects.equals(pageNum, 0) ? 0 : pageNum - 1, pageSize));
    }

    @Override
    public List<AuthMenu> getParentMenu() {
        return menuRepository.getParentMenu();
    }

    @Override
    public AuthMenu getMenuById(Long id) {
        return menuRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateMenu(AuthMenu authMenu) {
        return menuRepository.save(authMenu) != null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean changeSwitch(boolean hidden, Long id, Long adminId) {
        if (hidden) {
            return menuRepository.changeSwitch(1, id) > 0;
        }
        return menuRepository.changeSwitch(0, id) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delMenu(List<Long> ids) {
        menuRepository.deleteAllByIdInBatch(ids);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addMenu(AuthMenu authMenu) {
        authMenu.setCreateTime(LocalDateTime.now());
        return menuRepository.save(authMenu) != null;
    }

    @Override
    public List<Long> getMenuTreeById(Long id) {
        return menuRepository.selectMenuTreeById(id);
    }

    @Override
    public List<AuthMenu> getAllMenuTree() {
        List<AuthMenu> parentMenu = menuRepository.getParentMenu();
        parentMenu.forEach(menu -> {
            List<AuthMenu> childList = menuRepository.getChildList(menu.getId());
            menu.setChildren(childList);
        });
        return parentMenu;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateMenuTree(List<Long> menuIds, Long id) {
        AuthUser currentAdmin = userService.getCurrentAdmin();
        if (ObjectUtil.isNotEmpty(currentAdmin) && Objects.equals(id, currentAdmin.getId())) {
            // 超级管理员，默认拥有所有菜单，不允许更改！
            return false;
        }
        int i = menuRepository.deleteRoleMenuRelation(id);
        if (i > 0) {
            for (int j = 0; j < menuIds.size(); j++) {
                menuRepository.insertRoleMenuRelation(menuIds.get(j), id);
            }
        }
        return true;
    }

}
