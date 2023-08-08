<script setup lang="ts">
import { appName } from '~/constants'
import { darkTheme } from 'naive-ui'

useHead({
  title: appName,
})
</script>

<template>
  <VitePwaManifest />
  <n-config-provider :theme="isDark ? darkTheme : undefined">
    <n-message-provider>
      <NuxtLayout>
        <NuxtPage />
      </NuxtLayout>
    </n-message-provider>
  </n-config-provider>
</template>

<style>
html, body , #__nuxt{
  height: 100vh;
  margin: 0;
  padding: 0;
}

html.dark {
  background: #222;
  color: white;
}
</style>
