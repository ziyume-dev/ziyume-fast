import { defineStore } from 'pinia'
import type { ICrumbsSetting, IHeaderSetting, IMenuSetting, IMultiTabsSetting } from '~/types/config'
import projectSetting from '~/settings/projectSetting'

const {
  navMode,
  navTheme,
  isMobile,
  headerSetting,
  showFooter,
  menuSetting,
  multiTabsSetting,
  crumbsSetting,
  permissionMode,
  isPageAnimate,
  pageAnimateType,
} = projectSetting

interface ProjectSettingState {
  // 导航模式
  navMode: string
  // 导航风格
  navTheme: string
  // 顶部设置
  headerSetting: IHeaderSetting
  // 页脚
  showFooter: boolean
  // 多标签
  menuSetting: IMenuSetting
  // 多标签
  multiTabsSetting: IMultiTabsSetting
  // 面包屑
  crumbsSetting: ICrumbsSetting
  // 权限模式
  permissionMode: string
  // 是否开启路由动画
  isPageAnimate: boolean
  // 路由动画类型
  pageAnimateType: string
  // 是否处于移动端模式
  isMobile: boolean
}

export const useProjectSettingStore = defineStore({
  id: 'app-project-setting',
  state: (): ProjectSettingState => ({
    navMode,
    navTheme,
    isMobile,
    headerSetting,
    showFooter,
    menuSetting,
    multiTabsSetting,
    crumbsSetting,
    permissionMode,
    isPageAnimate,
    pageAnimateType,
  }),
  getters: {
    getNavMode(): string {
      return this.navMode
    },
    getNavTheme(): string {
      return this.navTheme
    },
    getIsMobile(): boolean {
      return this.isMobile
    },
    getHeaderSetting(): object {
      return this.headerSetting
    },
    getShowFooter(): boolean {
      return this.showFooter
    },
    getMenuSetting(): object {
      return this.menuSetting
    },
    getMultiTabsSetting(): object {
      return this.multiTabsSetting
    },
    getCrumbsSetting(): object {
      return this.crumbsSetting
    },
    getPermissionMode(): string {
      return this.permissionMode
    },
    getIsPageAnimate(): boolean {
      return this.isPageAnimate
    },
    getPageAnimateType(): string {
      return this.pageAnimateType
    },
  },
  actions: {
    setNavTheme(value: string): void {
      this.navTheme = value
    },
    setIsMobile(value: boolean): void {
      this.isMobile = value
    },
  },
})
