<template>
  <div className="dashboard-container">
    <el-card className="box-card" shadow="hover">
      <span>欢迎：{{ name }}</span>
      <i style="float: right; padding: 3px 0" className="el-icon-user-solid"></i>
    </el-card>

    <el-card className="box-card" style="width: 42%; margin-top: 30px">
      <div slot="header" className="clearfix">
        <span>公告</span>
        <el-button style="float: right; padding: 3px 0" type="text">更新公告</el-button>
      </div>
      <div>
        <a href="https://github.com/besscroft/pisces-lfs" target="_blank">Pisces Lfs</a> 是一个基于 SpringBoot3 + Vue + Element UI 的快速启动框架，可基于此框架快速构建系统！ 框架提供了基础的基于 RBAC 思想开发的权限管理模块，可以让您专注于业务开发。
        <br />
        <br />
        v2版现已发布！
        <a @click="updateLog()">--> 版本日志 <--</a>
      </div>
    </el-card>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  methods: {
    updateLog() {
      this.$router.push({ path: '/system/version' })
    },
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }

  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
