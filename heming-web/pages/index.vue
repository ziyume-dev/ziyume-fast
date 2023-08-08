<script setup lang="ts">
const router = useRouter()

onBeforeMount(() => {
  router.push('/admin')
})
</script>

<template>
  <div>
    <h1>首页</h1>
  </div>
</template>

<style scoped>

</style>
