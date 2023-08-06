<script lang="ts" setup>
import { websiteConfig } from '~/config/website.config'

const props = defineProps({
  collapsed: {
    type: Boolean,
  },
})
</script>

<template>
  <div class="logo">
    <img :src="websiteConfig.logo" alt="" :class="{ 'mr-2': !props.collapsed }">
    <h2 v-show="!props.collapsed" class="title">
      {{ websiteConfig.title }}
    </h2>
  </div>
</template>

<style lang="less" scoped>
.logo {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 64px;
  line-height: 64px;
  overflow: hidden;
  white-space: nowrap;

  img {
    width: auto;
    height: 32px;
  }

  .title {
    margin: 0;
  }
}
</style>
