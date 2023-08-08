<script setup lang="ts">
const color = useColorMode()

useHead({
  meta: [{
    id: 'theme-color',
    name: 'theme-color',
    content: () => color.value === 'dark' ? '#222222' : '#ffffff',
  }],
})

function currentToggleDark() {
  color.preference = color.value === 'dark' ? 'light' : 'dark'
  toggleDark()
}
</script>

<template>
  <button class="!outline-none" @click="currentToggleDark">
    <div class="i-carbon-sun dark:i-carbon-moon" />
  </button>
</template>
