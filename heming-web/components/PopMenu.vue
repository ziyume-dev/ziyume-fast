<script setup lang="ts">
import type { MenuOption } from 'naive-ui'
import { renderIcon } from '~/utils/renderUtil'
import { NuxtLink } from '#components'

const route = useRoute()
const router = useRouter()
const emit = defineEmits(['changeCollapsed'])
const props = defineProps({
  collapsed: {
    type: Boolean,
    required: true,
  }
})
const activeItem = ref<string | null>(null)
const expandedKeys = ref<Array<string>>([])

const changeCollapsed = () => {
  emit('changeCollapsed')
}

const menuOptions: MenuOption[] = [
  {
    label: () =>
        h(
            NuxtLink,
            {
              to: '/',
            },
            { default: () => '首页' }
        ),
    key: 'home',
    icon: () => renderIcon('<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 32 32"><path d="M26 2h4v4h-4z" fill="currentColor"></path><path d="M26 8h4v4h-4z" fill="currentColor"></path><path d="M20 2h4v4h-4z" fill="currentColor"></path><path d="M20 8h4v4h-4z" fill="currentColor"></path><path d="M28 16v6H4V6h12V4H4a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h8v4H8v2h16v-2h-4v-4h8a2 2 0 0 0 2-2v-6zM18 28h-4v-4h4z" fill="currentColor"></path></svg>')
  },
  {
    label: '用户',
    key: 'user',
    icon: () => renderIcon('<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 32 32"><path d="M16 4a5 5 0 1 1-5 5a5 5 0 0 1 5-5m0-2a7 7 0 1 0 7 7a7 7 0 0 0-7-7z" fill="currentColor"></path><path d="M26 30h-2v-5a5 5 0 0 0-5-5h-6a5 5 0 0 0-5 5v5H6v-5a7 7 0 0 1 7-7h6a7 7 0 0 1 7 7z" fill="currentColor"></path></svg>'),
    children: [
      {
        label: () =>
            h(
                NuxtLink,
                {
                  to: '/user',
                },
                { default: () => '用户' }
            ),
        key: 'users',
        icon: () => renderIcon('<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 32 32"><path d="M16 4a5 5 0 1 1-5 5a5 5 0 0 1 5-5m0-2a7 7 0 1 0 7 7a7 7 0 0 0-7-7z" fill="currentColor"></path><path d="M26 30h-2v-5a5 5 0 0 0-5-5h-6a5 5 0 0 0-5 5v5H6v-5a7 7 0 0 1 7-7h6a7 7 0 0 1 7 7z" fill="currentColor"></path></svg>')
      },
      {
        label: () =>
            h(
                NuxtLink,
                {
                  to: '/user/1',
                },
                { default: () => '用户1' }
            ),
        key: 'user1',
        icon: () => renderIcon('<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 32 32"><path d="M16 4a5 5 0 1 1-5 5a5 5 0 0 1 5-5m0-2a7 7 0 1 0 7 7a7 7 0 0 0-7-7z" fill="currentColor"></path><path d="M26 30h-2v-5a5 5 0 0 0-5-5h-6a5 5 0 0 0-5 5v5H6v-5a7 7 0 0 1 7-7h6a7 7 0 0 1 7 7z" fill="currentColor"></path></svg>')
      },
      {
        label: () =>
            h(
                NuxtLink,
                {
                  to: '/user/2',
                },
                { default: () => '用户2' }
            ),
        key: 'user2',
        icon: () => renderIcon('<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 32 32"><path d="M16 4a5 5 0 1 1-5 5a5 5 0 0 1 5-5m0-2a7 7 0 1 0 7 7a7 7 0 0 0-7-7z" fill="currentColor"></path><path d="M26 30h-2v-5a5 5 0 0 0-5-5h-6a5 5 0 0 0-5 5v5H6v-5a7 7 0 0 1 7-7h6a7 7 0 0 1 7 7z" fill="currentColor"></path></svg>')
      }
    ]
  }
]

const activeItemHandle = (path: string) => {
  if (path === '/') {
    activeItem.value = 'home'
    expandedKeys.value = ['home']
  } else if (path === '/user') {
    activeItem.value = 'users'
    expandedKeys.value = ['user','users']
  } else if (path === '/user/1') {
    activeItem.value = 'user1'
    expandedKeys.value = ['user','user1']
  } else if (path === '/user/2') {
    activeItem.value = 'user2'
    expandedKeys.value = ['user','user2']
  }
}

watch(router.currentRoute, (val) => {
  activeItemHandle(val.path)
})

onMounted(() => {
  const path = route.path
  activeItemHandle(path)
})
</script>

<template>
  <div flex flex-col h-full>
    <n-menu
        w-full
        v-model:value="activeItem"
        v-model:expanded-keys="expandedKeys"
        :collapsed="props.collapsed"
        :collapsed-width="64"
        :collapsed-icon-size="22"
        :options="menuOptions"
    />
    <div flex flex-col mta space-y-1>
      <div flex flex-row-reverse>
        <n-icon size="32" cursor-pointer @click="changeCollapsed">
          <svg v-if="props.collapsed" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 32 32"><path d="M22 16L12 26l-1.4-1.4l8.6-8.6l-8.6-8.6L12 6z" fill="currentColor"></path></svg>
          <svg v-else xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 32 32"><path d="M10 16L20 6l1.4 1.4l-8.6 8.6l8.6 8.6L20 26z" fill="currentColor"></path></svg>
        </n-icon>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>
