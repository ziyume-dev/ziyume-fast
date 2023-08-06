<script lang="ts" setup>
import { NDialogProvider, NMessageProvider, NNotificationProvider } from 'naive-ui'
</script>

<template>
  <NDialogProvider>
    <NNotificationProvider>
      <NMessageProvider>
        <slot name="default" />
      </NMessageProvider>
    </NNotificationProvider>
  </NDialogProvider>
</template>
