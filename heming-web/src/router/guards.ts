import type { RouteRecordRaw, Router } from 'vue-router'
import { isNavigationFailure } from 'vue-router'
import { useAsyncRoute } from '~/stores/modules/asyncRoute'
import { ErrorPageRoute } from '~/router/base'
import { UserEnum } from '~/enums/commonEnum'

const LOGIN_PATH = '/login'

export function createRouterGuards(router: Router) {
  const asyncRouteStore = useAsyncRoute()
  router.beforeEach(async (to, from, next) => {
    const user = useUserStore()
    // @ts-ignore
    const Loading = window['$loading'] || null
    Loading && Loading.start()
    if (to.path === '/login') {
      return next()
    }
    if (from.path === LOGIN_PATH && to.name === 'errorPage') {
      return next('/')
    }

    const token = user.token || localStorage.getItem(UserEnum.TOKEN)
    if (!token) {
      // You can access without permissions. You need to set the routing meta.ignoreAuth to true
      if (to.meta.ignoreAuth) {
        return next({ path: '/login', replace: true })
      }
      return next({ path: '/login', replace: true })
    }

    if (asyncRouteStore.getIsDynamicRouteAdded) {
      return next()
    }

    const userInfo = await user.getInfo()

    const routes = await asyncRouteStore.generateRoutes(userInfo)
    // 动态添加可访问路由表
    routes?.forEach((item) => {
      router.addRoute(item as unknown as RouteRecordRaw)
    })

    // 添加 404
    const isErrorPage = router.getRoutes().findIndex((item) => item.name === ErrorPageRoute.name)
    if (isErrorPage === -1) {
      router.addRoute(ErrorPageRoute as unknown as RouteRecordRaw)
    }
    const redirectPath = (from.query.redirect || to.path) as string
    const redirect = decodeURIComponent(redirectPath)
    const nextData = to.path === redirect ? { ...to, replace: true } : { path: redirect }
    asyncRouteStore.setDynamicRouteAdded(true)
    next(nextData)
    Loading && Loading.finish()
  })

  router.afterEach((to, _, failure) => {
    document.title = (to?.meta?.title as string) || document.title
    if (isNavigationFailure(failure)) {
      // console.log('failed navigation', failure)
    }
    const asyncRouteStore = useAsyncRoute()
    // 在这里设置需要缓存的组件名称
    const keepAliveComponents = asyncRouteStore.keepAliveComponents
    const currentComName: any = to.matched.find((item) => item.name === to.name)?.name
    if (currentComName && !keepAliveComponents.includes(currentComName) && to.meta?.keepAlive) {
      // 需要缓存的组件
      keepAliveComponents.push(currentComName)
    } else if (!to.meta?.keepAlive || to.name === 'Redirect') {
      // 不需要缓存的组件
      const index = asyncRouteStore.keepAliveComponents.findIndex((name) => name === currentComName)
      if (index !== -1) {
        keepAliveComponents.splice(index, 1)
      }
    }
    asyncRouteStore.setKeepAliveComponents(keepAliveComponents)
    // @ts-ignore
    const Loading = window['$loading'] || null
    Loading && Loading.finish()
  })

  router.onError((error) => {
    console.log(error, '路由错误')
  })
}
