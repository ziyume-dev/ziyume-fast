import { acceptHMRUpdate, defineStore } from 'pinia'
import piniaPersistConfig from '~/config/piniaPersist'
import type { UserStore } from '~/types/user'
import { UserEnum } from '~/enums/commonEnum'
import { getUserInfo, login } from '~/api/system/user'
import { ResultEnum } from '~/enums/httpEnum'

export const useUserStore = defineStore('user', {
  state: (): UserStore => ({
    // token
    token: '',
    // token 前缀
    tokenHead: '',
    // 刷新 token
    refreshToken: '',
    // 用户名称
    userName: '',
    // 头像
    avatar: '',
    // i18n 语言
    language: '',
    // 暗黑模式
    theme: '',
    // 网站标题
    title: '',
    // 备案信息
    beian: '',
    // 角色 Code
    roleCode: [],
    // 菜单
    menus: [],
  }),
  actions: {
    /** 设置用户名称 */
    setUserName(name: string) {
      this.userName = name
    },
    /** 设置 token */
    setToken(token: string) {
      this.token = token
    },
    /** 设置 token 前缀 */
    setTokenHead(tokenHead: string) {
      this.tokenHead = tokenHead
    },
    /** 设置刷新 token */
    setRefreshToken(refreshToken: string) {
      this.refreshToken = refreshToken
    },
    /** 设置头像 */
    setAvatar(avatar: string) {
      this.avatar = avatar
    },
    /** 设置 i18n 语言 */
    setLanguage(language: string) {
      this.language = language
    },
    /** 设置暗黑模式 */
    setTheme(theme: string) {
      this.theme = theme
    },
    /** 设置网站标题 */
    setTitle(title: string) {
      this.title = title
    },
    /** 设置备案信息 */
    setBeian(beian: string) {
      this.beian = beian
    },
    /** 设置角色 Code */
    setRoleCode(roleCode: string[]) {
      this.roleCode = roleCode
    },
    /** 设置菜单 */
    setMenus(menus: any[]) {
      this.menus = menus
    },
    // 登录
    async login(params: any) {
      const response = await login(params)
      const { code, data } = response
      if (code === ResultEnum.SUCCESS) {
        const ex = 7 * 24 * 60 * 60
        // @ts-ignore
        localStorage.setItem(UserEnum.TOKEN, data.tokenValue, ex)
        // @ts-ignore
        this.setToken(data.tokenValue)
      }
      return response
    },

    // 获取用户信息
    async getInfo() {
      const { data } = await getUserInfo()
      this.setAvatar(data.avatar)
      this.setMenus(data.menus)
      this.setRoleCode(data.role)
      this.setUserName(data.userName)
      return data
    },

    // 登出
    async logout() {
      this.setToken('')
      this.setUserName('')
      this.setAvatar('')
      this.setMenus([])
      this.setAvatar('')
      this.setRoleCode([])
      localStorage.setItem(UserEnum.TOKEN, '')
    },
  },
  persist: piniaPersistConfig('user'),
})

if (import.meta.hot)
  import.meta.hot.accept(acceptHMRUpdate(useUserStore, import.meta.hot))
