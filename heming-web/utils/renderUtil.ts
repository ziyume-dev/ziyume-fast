import { NIcon } from 'naive-ui'

// 渲染图标
export function renderIcon(svg: string) {
  return h(NIcon, { innerHTML: svg })
}
