import type { RouteRecordRaw } from 'vue-router'
import { Layout, ParentLayout } from '~/router/base'
import type { AppRouteRecordRaw } from '~/router/types'
import { constantRouterIcon } from '~/router/icons'

const pagesModules = import.meta.glob('../pages/**/*.{vue,tsx}')
const Iframe = () => import('~/pages/iframe/index.vue')

/**
 * 格式化 后端 结构信息并递归生成层级路由表
 * @param routerMap
 * @returns {*}
 */
export function generateRoutes(routerMap: any): any[] {
  return routerMap.map((item: any) => {
    const currentRoute: any = {
      // 路由地址
      path: item.path,
      // 路由名称
      name: item.name ?? '',
      // 该路由对应页面的 组件
      component: item.component,
      // meta: 页面标题, 菜单图标
      meta: {
        ...item.meta,
        label: item.meta.title,
        // @ts-ignore
        icon: constantRouterIcon[item.meta?.icon],
      },
    }
    // 为了防止出现后端返回结果不规范，处理有可能出现拼接出两个 反斜杠
    currentRoute.path = currentRoute.path.replace('//', '/')
    // 重定向
    item.redirect && (currentRoute.redirect = item.redirect)
    // 是否有子菜单，并递归处理
    if (item.children && item.children.length > 0) {
      // 如果未定义 redirect 默认第一个子路由为 redirect
      !item.redirect && (currentRoute.redirect = `${item.path}/${item.children[0].path}`)
      // Recursion
      currentRoute.children = generateRoutes(item.children)
    }
    return currentRoute
  })
}

/**
 * 动态生成菜单
 * @returns {Promise<Router>}
 */
export async function generateDynamicRoutes(menus: any[]): Promise<RouteRecordRaw[]> {
  const router = generateRoutes(menus)
  router.map((item: any) => {
    return asyncImportRoute(item)
  })
  return router
}

export function asyncImportRoute(item: AppRouteRecordRaw): void {
  if (!item.component && item.meta?.frameSrc) {
    item.component = Iframe
  }
  const { component, name } = item
  if (component) {
    item.component = component === 'Layout' ? Layout : pagesModules[`../pages${component}.vue`]
  } else if (name) {
    item.component = ParentLayout
  }
  // 是否有子菜单，并递归处理
  if (item.children && item.children.length > 0) {
    item.children.map((item: any) => {
      return asyncImportRoute(item)
    })
  }
}
