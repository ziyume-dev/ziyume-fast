<script setup lang="ts">
definePageMeta({
  title: '用户',
  layout: 'admin',
  middleware: ['route']
})
</script>

<template>
  用户 id -> {{ $route.params.id }}
</template>

<style scoped>

</style>
