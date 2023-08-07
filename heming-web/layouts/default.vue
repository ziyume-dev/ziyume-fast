<template>
  <div>
    <n-layout position="absolute">
      <n-layout-header style="height: 60px" bordered>
       <Header />
      </n-layout-header>
      <n-layout has-sider position="absolute" style="margin-top: 60px; height: calc(100% - 60px)">
        <n-layout-sider bordered collapse-mode="width" width="188">
          <PopMenu />
        </n-layout-sider>
        <n-layout h-full>
          <n-layout-content content-style="padding: 0.25rem;" style="height: calc(100% - 60px)">
            <RouterView />
          </n-layout-content>
          <n-layout-footer style="height: 60px">
            <Footer />
          </n-layout-footer>
        </n-layout>
      </n-layout>
    </n-layout>
  </div>
</template>
