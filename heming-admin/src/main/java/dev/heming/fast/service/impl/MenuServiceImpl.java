package dev.heming.fast.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import dev.heming.fast.converter.MenuConverterMapper;
import dev.heming.fast.entity.Menu;
import dev.heming.fast.mapper.MenuMapper;
import dev.heming.fast.model.RouteMeta;
import dev.heming.fast.model.RouterVo;
import dev.heming.fast.param.menu.MenuAddParam;
import dev.heming.fast.param.menu.MenuUpdateParam;
import dev.heming.fast.param.menu.PageListParam;
import dev.heming.fast.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description 菜单 Service 实现类
 * @Author Bess Croft
 * @Date 2023/5/25 19:54
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<RouterVo> getRouterByUserId(Long userId) {
        List<Menu> menuList = this.baseMapper.selectAllByUserId(userId);
        List<RouterVo> routerVoList = new ArrayList<>();
        if (CollUtil.isNotEmpty(menuList)) {
            // 获取所有根菜单
            List<Menu> rootMenu = menuList.stream().filter(menu -> menu.getParentId() == 0).collect(Collectors.toList());
            // 获取所有非根菜单
            List<Menu> childMenu = menuList.stream().filter(menu -> menu.getParentId() != 0).collect(Collectors.toList());
            // 转换根菜单为路由
            for (Menu menu: rootMenu) {
                routerVoList.add(convertMenuToRouter(menu, childMenu));
            }
        }
        return routerVoList;
    }

    @Override
    public List<Menu> pageList(PageListParam param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        return this.baseMapper.selectMenuList(param.getTitle(), param.getName(), param.getPath());
    }

    @Override
    public void addMenu(MenuAddParam param) {
        Menu menu = MenuConverterMapper.INSTANCE.AddParamToUser(param);
        this.baseMapper.insert(menu);
    }

    @Override
    public void updateMenu(MenuUpdateParam param) {
        Menu menu = MenuConverterMapper.INSTANCE.UpdateParamToUser(param);
        this.baseMapper.updateById(menu);
    }

    @Override
    public void deleteMenu(Long menuId) {
        this.baseMapper.deleteById(menuId);
    }

    /**
     * 转换菜单为路由
     * @param menu 菜单
     * @param menuList 菜单列表
     * @return 路由
     */
    private RouterVo convertMenuToRouter(Menu menu, List<Menu> menuList) {
        RouterVo routerVo = new RouterVo();
        routerVo.setTitle(menu.getTitle());
        routerVo.setName(menu.getName());
        routerVo.setPath(menu.getPath());
        routerVo.setRedirect(menu.getRedirect());
        routerVo.setComponent(menu.getComponent());
        // 路由元数据
        RouteMeta meta = new RouteMeta();
        meta.setTitle(menu.getTitle());
        meta.setIcon(menu.getIcon());
        meta.setDisabled(Objects.equals(1, menu.getDisabled()));
        meta.setHidden(Objects.equals(1, menu.getHidden()));
        meta.setKeepAlive(Objects.equals(1, menu.getKeepAlive()));
        meta.setAlwaysShow(Objects.equals(1, menu.getAlwaysShow()));
        meta.setIsRoot(Objects.equals(1, menu.getIsRoot()));
        meta.setAffix(Objects.equals(1, menu.getAffix()));
        meta.setSort(menu.getSort());
        routerVo.setMeta(meta);
        if (CollUtil.isNotEmpty(menuList)) {
            List<RouterVo> routerVoList = new ArrayList<>();
            // 获取当前菜单的子菜单
            List<Menu> childList = menuList.stream().filter(m -> Objects.equals(m.getParentId(), menu.getId())).collect(Collectors.toList());
            for (Menu child: childList) {
                routerVoList.add(convertMenuToRouter(child, menuList));
            }
            routerVo.setChildren(routerVoList);
        }
        return routerVo;
    }

}
