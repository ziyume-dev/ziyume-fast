<script lang="ts" setup>
import { useDialog, useMessage } from 'naive-ui'
import { Moon, SunnySharp } from '@vicons/ionicons5'
import {
  CheckOutlined,
  MenuFoldOutlined,
  MenuUnfoldOutlined,
  ReloadOutlined,
  SettingOutlined,
} from '@vicons/antd'
import { TABS_ROUTES } from '~/enums/commonEnum'
import { useProjectSetting } from '~/hooks/setting/useProjectSetting'
import { websiteConfig } from '~/config/website.config'
import { useDesignSettingStore } from '~/stores/modules/designSetting'
import { useProjectSettingStore } from '~/stores/modules/projectSetting'
import { animates as animateOptions } from '~/settings/animateSetting'

const props = defineProps({
  collapsed: {
    type: Boolean,
  },
  inverted: {
    type: Boolean,
  },
  title: {
    type: String,
    default: '项目配置',
  },
  width: {
    type: Number,
    default: 280,
  },
})
const settingStore = useProjectSettingStore()
const designStore = useDesignSettingStore()
const width = props.width
const title = props.title
const isDrawer = ref(false)
const placement = ref('right')
const alertText = ref('该功能主要实时预览各种布局效果，更多完整配置在 projectSetting.ts 中设置，建议在生产环境关闭该布局预览功能。')
const appThemeList = designStore.appThemeList
const user = useUserStore()
const message = useMessage()
const dialog = useDialog()
const { navMode, navTheme, headerSetting, menuSetting, crumbsSetting } = useProjectSetting()
const { name } = user?.info || {}

const getInverted = computed(() => {
  return ['light', 'header-dark'].includes(unref(navTheme))
    ? props.inverted
    : !props.inverted
})

const mixMenu = computed(() => {
  return unref(menuSetting).mixMenu
})

function openDrawer() {
  isDrawer.value = true
}

function togNavTheme(theme) {
  settingStore.navTheme = theme
  if (settingStore.navMode === 'horizontal' && ['light'].includes(theme)) {
    settingStore.navTheme = 'dark'
  }
}

function togTheme(color) {
  designStore.appTheme = color
}

function togNavMode(mode) {
  settingStore.navMode = mode
  settingStore.menuSetting.mixMenu = false
}

const getMenuLocation = computed(() => {
  return 'header'
})

const router = useRouter()
const route = useRoute()

const generator: any = (routerMap) => {
  return routerMap.map((item) => {
    const currentMenu = {
      ...item,
      label: item.meta.title,
      key: item.name,
      disabled: item.path === '/',
    }
    // 是否有子菜单，并递归处理
    if (item.children && item.children.length > 0) {
      // Recursion
      currentMenu.children = generator(item.children, currentMenu)
    }
    return currentMenu
  })
}

const breadcrumbList = computed(() => {
  return generator(route.matched)
})

function dropdownSelect(key) {
  router.push({ name: key })
}

// 刷新页面
function reloadPage() {
  router.push({
    path: `/redirect${unref(route).fullPath}`,
  })
}

// 退出登录
function doLogout() {
  dialog.info({
    title: '提示',
    content: '您确定要退出登录吗',
    positiveText: '确定',
    negativeText: '取消',
    onPositiveClick: () => {
      user.logout().then(() => {
        message.success('成功退出登录')
        // 移除标签页
        localStorage.removeItem(TABS_ROUTES)
        router
          .replace({
            name: 'Login',
            query: {
              redirect: route.fullPath,
            },
          })
          .finally(() => location.reload())
      })
    },
    onNegativeClick: () => {},
  })
}

const avatarOptions = [
  {
    label: '个人设置',
    key: 1,
  },
  {
    label: '退出登录',
    key: 2,
  },
]

// 头像下拉菜单
function avatarSelect(key) {
  switch (key) {
    case 1:
      router.push({ path: '/setting/person' })
      break
    case 2:
      doLogout()
      break
  }
}

function openSetting() {
  openDrawer()
}

watch(
  () => designStore.darkTheme,
  (to) => {
    settingStore.navTheme = to ? 'header-dark' : 'dark'
  },
)
</script>

<template>
  <div class="layout-header">
    <!-- 顶部菜单 -->
    <div
      v-if="navMode === 'horizontal' || (navMode === 'horizontal-mix' && mixMenu)"
      class="layout-header-left"
    >
      <div v-if="navMode === 'horizontal'" class="logo">
        <img :src="websiteConfig.logo" alt="">
        <h2 v-show="!props.collapsed" class="title">
          {{ websiteConfig.title }}
        </h2>
      </div>
      <AsideMenu
        v-model:collapsed="props.collapsed"
        v-model:location="getMenuLocation"
        :inverted="getInverted"
        mode="horizontal"
      />
    </div>
    <!-- 左侧菜单 -->
    <div v-else class="layout-header-left">
      <!-- 菜单收起 -->
      <div
        class="layout-header-trigger layout-header-trigger-min ml-1"
        @click="() => $emit('update:collapsed', !props.collapsed)"
      >
        <n-icon v-if="props.collapsed" size="18">
          <MenuUnfoldOutlined />
        </n-icon>
        <n-icon v-else size="18">
          <MenuFoldOutlined />
        </n-icon>
      </div>
      <!-- 刷新 -->
      <div
        v-if="headerSetting.isReload"
        class="layout-header-trigger layout-header-trigger-min mr-1"
        @click="reloadPage"
      >
        <n-icon size="18">
          <ReloadOutlined />
        </n-icon>
      </div>
      <!-- 面包屑 -->
      <n-breadcrumb v-if="crumbsSetting.show">
        <template
          v-for="routeItem in breadcrumbList"
          :key="routeItem.name === 'Redirect' ? void 0 : routeItem.name"
        >
          <n-breadcrumb-item v-if="routeItem.meta.title">
            <n-dropdown
              v-if="routeItem.children.length"
              :options="routeItem.children"
              @select="dropdownSelect"
            >
              <span class="link-text">
                <component
                  :is="routeItem.meta.icon"
                  v-if="crumbsSetting.showIcon && routeItem.meta.icon"
                />
                {{ routeItem.meta.title }}
              </span>
            </n-dropdown>
            <span v-else class="link-text">
              <component
                :is="routeItem.meta.icon"
                v-if="crumbsSetting.showIcon && routeItem.meta.icon"
              />
              {{ routeItem.meta.title }}
            </span>
          </n-breadcrumb-item>
        </template>
      </n-breadcrumb>
    </div>
    <div class="layout-header-right">
      <!-- 个人中心 -->
      <div class="layout-header-trigger layout-header-trigger-min">
        <n-dropdown trigger="hover" :options="avatarOptions" @select="avatarSelect">
          <div class="avatar">
            <n-avatar
              round
              :src="user.avatar"
            />
          </div>
        </n-dropdown>
      </div>
      <!-- 设置 -->
      <div class="layout-header-trigger layout-header-trigger-min" @click="openSetting">
        <n-tooltip placement="bottom-end">
          <template #trigger>
            <n-icon size="18" style="font-weight: bold">
              <SettingOutlined />
            </n-icon>
          </template>
          <span>项目配置</span>
        </n-tooltip>
      </div>
    </div>
  </div>
  <!-- 项目配置 -->
  <n-drawer v-model:show="isDrawer" :width="width" :placement="placement">
    <n-drawer-content :title="title" :native-scrollbar="false">
      <div class="drawer">
        <n-divider title-placement="center">
          主题
        </n-divider>

        <div class="drawer-setting-item dark-switch justify-center">
          <n-tooltip placement="bottom">
            <template #trigger>
              <n-switch v-model:value="designStore.darkTheme" class="dark-theme-switch">
                <template #checked>
                  <n-icon size="14" color="#ffd93b">
                    <SunnySharp />
                  </n-icon>
                </template>
                <template #unchecked>
                  <n-icon size="14" color="#ffd93b">
                    <Moon />
                  </n-icon>
                </template>
              </n-switch>
            </template>
            <span>{{ designStore.darkTheme ? '深' : '浅' }}色主题</span>
          </n-tooltip>
        </div>

        <n-divider title-placement="center">
          系统主题
        </n-divider>

        <div class="drawer-setting-item align-items-top">
          <span
            v-for="(item, index) in appThemeList"
            :key="index"
            class="theme-item"
            :style="{ 'background-color': item }"
            @click="togTheme(item)"
          >
            <n-icon v-if="item === designStore.appTheme" size="12">
              <CheckOutlined />
            </n-icon>
          </span>
        </div>

        <n-divider title-placement="center">
          导航栏模式
        </n-divider>

        <div class="drawer-setting-item align-items-top">
          <div class="drawer-setting-item-style align-items-top">
            <n-tooltip placement="top">
              <template #trigger>
                <img
                  src="../assets/images/nav-theme-dark.svg"
                  alt="左侧菜单模式"
                  @click="togNavMode('vertical')"
                >
              </template>
              <span>左侧菜单模式</span>
            </n-tooltip>
            <n-badge v-show="settingStore.navMode === 'vertical'" dot color="#19be6b" />
          </div>

          <div class="drawer-setting-item-style">
            <n-tooltip placement="top">
              <template #trigger>
                <img
                  src="../assets/images/nav-horizontal.svg"
                  alt="顶部菜单模式"
                  @click="togNavMode('horizontal')"
                >
              </template>
              <span>顶部菜单模式</span>
            </n-tooltip>
            <n-badge v-show="settingStore.navMode === 'horizontal'" dot color="#19be6b" />
          </div>

          <div class="drawer-setting-item-style">
            <n-tooltip placement="top">
              <template #trigger>
                <img
                  src="../assets/images/nav-horizontal-mix.svg"
                  alt="顶部菜单混合模式"
                  @click="togNavMode('horizontal-mix')"
                >
              </template>
              <span>顶部菜单混合模式</span>
            </n-tooltip>
            <n-badge v-show="settingStore.navMode === 'horizontal-mix'" dot color="#19be6b" />
          </div>
        </div>

        <n-divider title-placement="center">
          导航栏风格
        </n-divider>

        <div class="drawer-setting-item align-items-top">
          <div class="drawer-setting-item-style align-items-top">
            <n-tooltip placement="top">
              <template #trigger>
                <img
                  src="../assets/images/nav-theme-dark.svg"
                  alt="暗色侧边栏"
                  @click="togNavTheme('dark')"
                >
              </template>
              <span>暗色侧边栏</span>
            </n-tooltip>
            <n-badge v-if="settingStore.navTheme === 'dark'" dot color="#19be6b" />
          </div>

          <div class="drawer-setting-item-style">
            <n-tooltip placement="top">
              <template #trigger>
                <img
                  src="../assets/images/nav-theme-light.svg"
                  alt="白色侧边栏"
                  @click="togNavTheme('light')"
                >
              </template>
              <span>白色侧边栏</span>
            </n-tooltip>
            <n-badge v-if="settingStore.navTheme === 'light'" dot color="#19be6b" />
          </div>

          <div class="drawer-setting-item-style">
            <n-tooltip placement="top">
              <template #trigger>
                <img
                  src="../assets/images/header-theme-dark.svg"
                  alt="暗色顶栏"
                  @click="togNavTheme('header-dark')"
                >
              </template>
              <span>暗色顶栏</span>
            </n-tooltip>
            <n-badge v-if="settingStore.navTheme === 'header-dark'" dot color="#19be6b" />
          </div>
        </div>
        <n-divider title-placement="center">
          界面功能
        </n-divider>

        <div class="drawer-setting-item">
          <div class="drawer-setting-item-title">
            分割菜单
          </div>
          <div class="drawer-setting-item-action">
            <n-switch
              v-model:value="settingStore.menuSetting.mixMenu"
              :disabled="settingStore.navMode !== 'horizontal-mix'"
            />
          </div>
        </div>

        <div class="drawer-setting-item">
          <div class="drawer-setting-item-title">
            固定顶栏
          </div>
          <div class="drawer-setting-item-action">
            <n-switch v-model:value="settingStore.headerSetting.fixed" />
          </div>
        </div>

        <div class="drawer-setting-item">
          <div class="drawer-setting-item-title">
            固定多页签
          </div>
          <div class="drawer-setting-item-action">
            <n-switch v-model:value="settingStore.multiTabsSetting.fixed" />
          </div>
        </div>

        <n-divider title-placement="center">
          界面显示
        </n-divider>

        <div class="drawer-setting-item">
          <div class="drawer-setting-item-title">
            显示重载页面按钮
          </div>
          <div class="drawer-setting-item-action">
            <n-switch v-model:value="settingStore.headerSetting.isReload" />
          </div>
        </div>

        <div class="drawer-setting-item">
          <div class="drawer-setting-item-title">
            显示面包屑导航
          </div>
          <div class="drawer-setting-item-action">
            <n-switch v-model:value="settingStore.crumbsSetting.show" />
          </div>
        </div>

        <div class="drawer-setting-item">
          <div class="drawer-setting-item-title">
            显示面包屑显示图标
          </div>
          <div class="drawer-setting-item-action">
            <n-switch v-model:value="settingStore.crumbsSetting.showIcon" />
          </div>
        </div>

        <div class="drawer-setting-item">
          <div class="drawer-setting-item-title">
            显示多页签
          </div>
          <div class="drawer-setting-item-action">
            <n-switch v-model:value="settingStore.multiTabsSetting.show" />
          </div>
        </div>

        <n-divider title-placement="center">
          动画
        </n-divider>

        <div class="drawer-setting-item">
          <div class="drawer-setting-item-title">
            禁用动画
          </div>
          <div class="drawer-setting-item-action">
            <n-switch v-model:value="settingStore.isPageAnimate" />
          </div>
        </div>

        <div class="drawer-setting-item">
          <div class="drawer-setting-item-title">
            动画类型
          </div>
          <div class="drawer-setting-item-select">
            <n-select v-model:value="settingStore.pageAnimateType" :options="animateOptions" />
          </div>
        </div>

        <div class="drawer-setting-item">
          <n-alert type="warning" :show-icon="false">
            <p>{{ alertText }}</p>
          </n-alert>
        </div>
      </div>
    </n-drawer-content>
  </n-drawer>
</template>

<style lang="less" scoped>
.layout-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0;
  height: 64px;
  box-shadow: 0 1px 4px rgb(0 21 41 / 8%);
  transition: all 0.2s ease-in-out;
  width: 100%;
  z-index: 11;

  &-left {
    display: flex;
    align-items: center;

    .logo {
      display: flex;
      align-items: center;
      justify-content: center;
      height: 64px;
      line-height: 64px;
      overflow: hidden;
      white-space: nowrap;
      padding-left: 10px;

      img {
        width: auto;
        height: 32px;
        margin-right: 10px;
      }

      .title {
        margin-bottom: 0;
      }
    }

    ::v-deep(.ant-breadcrumb span:last-child .link-text) {
      color: #515a6e;
    }

    .n-breadcrumb {
      display: inline-block;
    }

    &-menu {
      color: var(--text-color);
    }
  }

  &-right {
    display: flex;
    align-items: center;
    margin-right: 20px;

    .avatar {
      display: flex;
      align-items: center;
      height: 64px;
    }

    > * {
      cursor: pointer;
    }
  }

  &-trigger {
    display: inline-block;
    width: 64px;
    height: 64px;
    text-align: center;
    cursor: pointer;
    transition: all 0.2s ease-in-out;

    .n-icon {
      display: flex;
      align-items: center;
      height: 64px;
      line-height: 64px;
    }

    &:hover {
      background: hsla(0, 0%, 100%, 0.08);
    }

    .anticon {
      font-size: 16px;
      color: #515a6e;
    }
  }

  &-trigger-min {
    width: auto;
    padding: 0 12px;
  }
}

.layout-header-light {
  background: #fff;
  color: #515a6e;

  .n-icon {
    color: #515a6e;
  }

  .layout-header-left {
    ::v-deep(.n-breadcrumb .n-breadcrumb-item:last-child .n-breadcrumb-item__link) {
      color: #515a6e;
    }
  }

  .layout-header-trigger {
    &:hover {
      background: #f8f8f9;
    }
  }
}

.layout-header-fix {
  position: fixed;
  top: 0;
  right: 0;
  left: 200px;
  z-index: 11;
}

.drawer {
  .n-divider:not(.n-divider--vertical) {
    margin: 10px 0;
  }

  &-setting-item {
    display: flex;
    align-items: center;
    padding: 12px 0;
    flex-wrap: wrap;

    &-style {
      display: inline-block;
      position: relative;
      margin-right: 16px;
      cursor: pointer;
      text-align: center;
    }

    &-title {
      flex: 1 1;
      font-size: 14px;
    }

    &-action {
      flex: 0 0 auto;
    }

    &-select {
      flex: 1;
    }

    .theme-item {
      width: 20px;
      min-width: 20px;
      height: 20px;
      cursor: pointer;
      border: 1px solid #eee;
      border-radius: 2px;
      margin: 0 5px 5px 0;
      text-align: center;
      line-height: 14px;

      .n-icon {
        color: #fff;
      }
    }
  }

  .align-items-top {
    align-items: flex-start;
    padding: 2px 0;
  }

  .justify-center {
    justify-content: center;
  }

  .dark-switch .n-switch {
    ::v-deep(.n-switch__rail) {
      background-color: #000e1c;
    }
  }
}
</style>
