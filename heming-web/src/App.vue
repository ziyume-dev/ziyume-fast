<script lang="ts" setup>
import { darkTheme, dateZhCN, zhCN } from 'naive-ui'
import { useDesignSettingStore } from '~/stores/modules/designSetting'
import { lighten } from '~/utils'

const route = useRoute()
const designStore = useDesignSettingStore()

/**
 * @type import('naive-ui').GlobalThemeOverrides
 */
const getThemeOverrides = computed(() => {
  const appTheme = designStore.appTheme
  const lightenStr = lighten(designStore.appTheme, 6)
  return {
    common: {
      primaryColor: appTheme,
      primaryColorHover: lightenStr,
      primaryColorPressed: lightenStr,
      primaryColorSuppl: appTheme,
    },
    LoadingBar: {
      colorLoading: appTheme,
    },
  }
})

const getDarkTheme = computed(() => (designStore.darkTheme ? darkTheme : undefined))
</script>

<template>
  <NConfigProvider
    :locale="zhCN"
    :theme="getDarkTheme"
    :theme-overrides="getThemeOverrides"
    :date-locale="dateZhCN"
  >
    <Application>
      <RouterView />
    </Application>
  </NConfigProvider>
</template>

<style lang="less">
@import 'styles/index.less';
</style>
