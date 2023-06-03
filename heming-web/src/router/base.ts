import type { RouteRecordRaw } from 'vue-router'

export const RedirectName = 'Redirect'
export const ErrorPage = () => import('~/pages/exception/404.vue')
export const Layout = () => import('~/layouts/index.vue')
export const ParentLayout = () => import('~/layouts/parentLayout.vue')

// 404 on a page
export const ErrorPageRoute: RouteRecordRaw = {
  path: '/:path(.*)*',
  name: 'ErrorPage',
  component: Layout,
  meta: {
    title: 'ErrorPage',
    hideBreadcrumb: true,
  },
  children: [
    {
      path: '/:path(.*)*',
      name: 'ErrorPageSon',
      component: ErrorPage,
      meta: {
        title: 'ErrorPage',
        hideBreadcrumb: true,
      },
    },
  ],
}

export const RedirectRoute: RouteRecordRaw = {
  path: '/redirect',
  name: RedirectName,
  component: Layout,
  meta: {
    title: RedirectName,
    hideBreadcrumb: true,
  },
  children: [
    {
      path: '/redirect/:path(.*)',
      name: RedirectName,
      component: () => import('~/pages/redirect/index.vue'),
      meta: {
        title: RedirectName,
        hideBreadcrumb: true,
      },
    },
  ],
}
