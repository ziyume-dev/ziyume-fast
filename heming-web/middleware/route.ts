export default defineNuxtRouteMiddleware((to, from) => {
    const user = useUserStore()
    if (to.path.startsWith('/admin')) {
        if (process.client && !user.token) {
            return navigateTo('/login')
        }
    }
    if (to.path === '/')
        return navigateTo('/admin')
})
