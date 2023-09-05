import { useUserStore } from '../stores/user'

export default defineNuxtRouteMiddleware((to, from) => {
    const user = useUserStore()
    if (to.path === '/login')
        return
    if (to.path !== '/login' && process.client && !user.token) {
        return navigateTo('/login')
    }
})
