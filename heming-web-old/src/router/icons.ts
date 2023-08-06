import {
  DashboardOutlined,
  UserOutlined,
  TeamOutlined,
  TagOutlined,
  TagsOutlined,
  MenuOutlined,
  AppstoreOutlined,
} from '@vicons/antd'
import { h } from 'vue'
import { NIcon } from 'naive-ui'

export function renderIcon(icon: any) {
  return () => h(NIcon, null, { default: () => h(icon) })
}

// 前端路由图标映射表
export const constantRouterIcon = {
  DashboardOutlined: renderIcon(DashboardOutlined),
  UserOutlined: renderIcon(UserOutlined),
  TeamOutlined: renderIcon(TeamOutlined),
  TagOutlined: renderIcon(TagOutlined),
  TagsOutlined: renderIcon(TagsOutlined),
  MenuOutlined: renderIcon(MenuOutlined),
  AppstoreOutlined: renderIcon(AppstoreOutlined),
}
