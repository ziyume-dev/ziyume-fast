<script setup lang="ts">
import { appName } from '../constants'
</script>

<template>
  <div flex justify-start justify-center items-center h-full mx-2>
    <p mr-auto font-serif text-xl font-normal>{{ appName }}</p>
    <DarkToggle />
  </div>
</template>

<style scoped>

</style>
