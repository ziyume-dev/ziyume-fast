import { useDesignSettingStore } from '~/stores/modules/designSetting'

export function useDesignSetting() {
  const designStore = useDesignSettingStore()

  const getDarkTheme = computed(() => designStore.darkTheme)

  const getAppTheme = computed(() => designStore.appTheme)

  const getAppThemeList = computed(() => designStore.appThemeList)

  return {
    getDarkTheme,
    getAppTheme,
    getAppThemeList,
  }
}
