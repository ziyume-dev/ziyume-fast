import type { App } from 'vue'
import type { RouteRecordRaw } from 'vue-router'
import { createRouter, createWebHistory } from 'vue-router'
import { createRouterGuards } from './guards'
import { RedirectRoute } from '~/router/base'

export const RootRoute: RouteRecordRaw = {
  path: '/',
  name: 'Root',
  redirect: '/dashboard/index',
  meta: {
    title: 'Root',
  },
}

export const LoginRoute: RouteRecordRaw = {
  path: '/login',
  name: 'Login',
  component: () => import('~/pages/login/index.vue'),
  meta: {
    title: '登录',
  },
}

// 普通路由 无需验证权限
export const constantRouter: RouteRecordRaw[] = [RootRoute, LoginRoute, RedirectRoute]

const router = createRouter({
  history: createWebHistory(),
  routes: constantRouter,
  strict: true,
  scrollBehavior: () => ({ left: 0, top: 0 }),
})

export function setupRouter(app: App) {
  app.use(router)
  // 创建路由守卫
  createRouterGuards(router)
}

export default router
