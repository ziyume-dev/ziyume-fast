<script lang="ts" setup>
import { useAsyncRouteStore } from '~/stores/modules/asyncRoute'
import { useProjectSetting } from '~/hooks/setting/useProjectSetting'

const props = defineProps({
  notNeedKey: {
    type: Boolean,
    default: false,
  },
  animate: {
    type: Boolean,
    default: true,
  },
})
const { isPageAnimate, pageAnimateType } = useProjectSetting()
const asyncRouteStore = useAsyncRouteStore()
// 需要缓存的路由组件
const keepAliveComponents = computed(() => asyncRouteStore.keepAliveComponents)

const getTransitionName = computed(() => {
  return unref(isPageAnimate) ? unref(pageAnimateType) : ''
})
</script>

<template>
  <RouterView>
    <template #default="{ Component, route }">
      <transition :name="getTransitionName" appear>
<!--      <transition :name="getTransitionName" mode="out-in" appear>-->
        <keep-alive v-if="keepAliveComponents.length" :include="keepAliveComponents">
          <component :is="Component" :key="route.name" />
        </keep-alive>
        <component :is="Component" v-else :key="route.name" />
      </transition>
    </template>
  </RouterView>
</template>
