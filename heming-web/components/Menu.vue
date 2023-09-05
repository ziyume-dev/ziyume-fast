<script setup lang="ts">
import { useUserStore } from '../stores/user'

const nuxtApp = useNuxtApp()
const toast = useToast()
const router = useRouter()
const user = useUserStore()
const activeItem = ref<string | null>(null)
const open = ref([''])

const handleOpen = (item: string) => {
  if (item === '/admin/menu') {
    open.value = ['Menu']
  } else if (item === '/admin/user') {
    open.value = ['System']
  } else if (item === '/admin/menu/menu11' || item === '/admin/menu/menu12') {
    open.value = ['Menu', 'MenuChild']
  }
}

const logout = async () => {
  const json = await nuxtApp.$api.post('/@fast-api/user/logout').json();
  user.$reset()
  toast.add({title: '退出成功!', timeout: 1000, ui: {width: 'w-full sm:w-96'}})
  router.push('/login')
}

watch(router.currentRoute, (val) => {
  activeItem.value = val.path
  handleOpen(val.path)
})

onMounted(() => {
  activeItem.value = router.currentRoute.value.path
  handleOpen(router.currentRoute.value.path)
})
</script>

<template>
  <div flex flex-col h-full data-te-sidenav-menu-ref>
    <div flex flex-col items-center justify-center px-2 pt-4>
      <v-avatar color="info" size="x-large">
        <v-img :src="user.avatar" alt="用户头像"></v-img>
      </v-avatar>
      <p font-ark>{{ user.userName }}</p>
      <p font-ark>{{ user.email || '暂未绑定邮箱' }}</p>
      <div flex items-center justify-center m-2 space-x-2>
        <v-tooltip text="退出">
          <template v-slot:activator="{ props }">
            <v-icon v-bind="props" icon="mdi-logout-variant" cursor-pointer @click="logout"></v-icon>
          </template>
        </v-tooltip>
      </div>
    </div>
    <div h-full>
      <v-list
        density="compact"
        v-model:opened="open"
        nav
      >
        <v-list-item
          prepend-icon="mdi-view-dashboard"
          title="控制台"
          value="admin"
          :active="activeItem === '/'"
          @click="router.push('/')"
        ></v-list-item>
        <v-list-group value="System">
          <template v-slot:activator="{ props }">
            <v-list-item
              v-bind="props"
              prepend-icon="mdi-account-cog"
              title="系统管理"
            ></v-list-item>
          </template>
          <v-list-item
            prepend-icon="mdi-account-multiple-outline"
            title="用户管理"
            value="user"
            :active="activeItem === '/admin/user'"
            @click="router.push('/admin/user')"
          ></v-list-item>
        </v-list-group>
        <v-list-group value="Menu">
          <template v-slot:activator="{ props }">
            <v-list-item
              v-bind="props"
              prepend-icon="mdi-puzzle-outline"
              title="嵌套菜单"
            ></v-list-item>
          </template>
          <v-list-item
            prepend-icon="mdi-puzzle-outline"
            title="菜单1"
            value="menu"
            :active="activeItem === '/admin/menu'"
            @click="router.push('/admin/menu')"
          ></v-list-item>
          <v-list-group value="MenuChild">
            <template v-slot:activator="{ props }">
              <v-list-item
                  v-bind="props"
                  prepend-icon="mdi-puzzle-outline"
                  title="子菜单"
              ></v-list-item>
            </template>
            <v-list-item
                prepend-icon="mdi-puzzle-outline"
                title="菜单1-1"
                value="menu11"
                :active="activeItem === '/admin/menu/menu11'"
                @click="router.push('/admin/menu/menu11')"
            ></v-list-item>
            <v-list-item
                prepend-icon="mdi-puzzle-outline"
                title="菜单1-2"
                value="menu12"
                :active="activeItem === '/admin/menu/menu12'"
                @click="router.push('/admin/menu/menu12')"
            ></v-list-item>
          </v-list-group>
        </v-list-group>
      </v-list>
    </div>
    <div items-end p-2>
      <NuxtLink target="_blank" to="https://heming.dev">
        <h1 font-serif text-3xl>
          <ruby>鹤
            <rp>(</rp>
            <rt>He</rt>
            <rp>)</rp>
          </ruby>
          <ruby>鸣
            <rp>(</rp>
            <rt>Ming</rt>
            <rp>)</rp>
          </ruby>
          <span
              justify-center items-center inline px-2 text-2xl border-4 light:border-pink-100
          >工作室</span>
          <span text-2xl>出品</span>
        </h1>
      </NuxtLink>
      <span font-serif>Made with ❤ by <NuxtLink target="_blank" to="https://besscroft.com">云淑鹤鸣真君</NuxtLink>.</span>
    </div>
  </div>
</template>

<style scoped>

</style>