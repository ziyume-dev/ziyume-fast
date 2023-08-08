import { getBaseUrl } from '~/utils/api'

export default defineNuxtRouteMiddleware((to, from) => {
    const user = useUserStore()
    if (to.path.startsWith('/admin')) {
        if (process.client) {
            if (!user.token) {
                return navigateTo('/login')
            }
            if (!user.userName) {
                $fetch('/user/info', {
                    headers: {
                        Authorization: 'Bearer ' + user.token
                    },
                    baseURL: getBaseUrl(),
                    method: 'GET',
                    parseResponse: JSON.parse,
                }).then((res: any) => {
                    if (res.code === 200) {
                        user.setUserName(res.data.username)
                        user.setAvatar(res.data.avatar)
                    } else {
                        return navigateTo('/login')
                    }
                })
            }
        }
    }
    if (to.path === '/')
        return navigateTo('/admin')
})
