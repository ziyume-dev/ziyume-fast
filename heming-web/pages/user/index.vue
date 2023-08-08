<script setup lang="ts">
definePageMeta({
  title: '用户',
  layout: 'admin'
})
</script>

<template>
  用户
  <n-divider title-placement="left">
    路由信息
  </n-divider>
  <div>{{ $router.currentRoute }}</div>
</template>

<style scoped>

</style>
