<script setup lang="ts">
</script>

<template>
  <div class="flow-root">
    <ul class="-my-1 px-1 divide-y divide-gray-100">
      <li class="py-2">
        <ul class="space-y-1">
          <li>
            <NuxtLink
                block rounded-lg px-4 py-2 text-sm font-medium text-gray-700
                :class="$route.fullPath === '/' ? 'bg-green-300' : 'bg-gray-100'"
                to="/"
            >首页</NuxtLink>
          </li>
          <li>
            <NuxtLink
              block rounded-lg px-4 py-2 text-sm font-medium text-gray-700
              :class="$route.fullPath === '/user' ? 'bg-green-300' : 'bg-gray-100'"
              to="/user"
            >用户</NuxtLink>
          </li>
          <li>
            <NuxtLink
              block rounded-lg px-4 py-2 text-sm font-medium text-gray-700
              :class="$route.fullPath === '/user/1' ? 'bg-green-300' : 'bg-gray-100'"
              to="/user/1"
            >用户1</NuxtLink>
          </li>
          <li>
            <NuxtLink
              block rounded-lg px-4 py-2 text-sm font-medium text-gray-700
              :class="$route.fullPath === '/user/2' ? 'bg-green-300' : 'bg-gray-100'"
              to="/user/2"
            >用户2</NuxtLink>
          </li>
        </ul>
      </li>
    </ul>
  </div>
</template>

<style scoped>

</style>
