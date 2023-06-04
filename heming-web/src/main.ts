import '@unocss/reset/tailwind.css'
import './styles/main.css'
import 'uno.css'
import { createApp } from 'vue'
import App from './App.vue'
import router, { setupRouter } from './router'
import { setupDirectives, setupNaive, setupNaiveDiscreteApi } from '~/plugins'
import { setupStore } from '~/stores'

async function bootstrap() {
  const app = createApp(App)

  // 挂载状态管理
  setupStore(app)

  // 注册全局常用的 naive-ui 组件
  setupNaive(app)

  // 挂载 naive-ui 脱离上下文的 Api
  setupNaiveDiscreteApi()

  // 注册全局自定义指令，如：v-permission权限指令
  setupDirectives(app)

  // 挂载路由
  setupRouter(app)

  // 路由准备就绪后挂载 APP 实例
  // https://router.vuejs.org/api/interfaces/router.html#isready
  await router.isReady()

  // https://www.naiveui.com/en-US/os-theme/docs/style-conflict#About-Tailwind's-Preflight-Style-Override
  const meta = document.createElement('meta')
  meta.name = 'naive-ui-style'
  document.head.appendChild(meta)

  app.mount('#app', true)
}

void bootstrap()