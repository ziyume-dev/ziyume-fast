import { App } from 'vue'

import copy from '~/directives/copy'
import debounce from '~/directives/debounce'
import throttle from '~/directives/throttle'
import draggable from '~/directives/draggable'

/**
 * 注册全局自定义指令
 * @param app
 */
export function setupDirectives(app: App) {
  // 复制指令
  app.directive('copy', copy)
  // 防抖指令
  app.directive('debounce', debounce)
  // 节流指令
  app.directive('throttle', throttle)
  // 拖拽指令
  app.directive('draggable', draggable)
}
