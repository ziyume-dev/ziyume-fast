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
        <a href="https://github.com/besscroft/aurora-drive" target="_blank">SpringBoot-LFS</a>
        自用的基于SpringBoot的解决方案😀目前还在开发中！
      </div>
    </el-card>

    <el-card className="box-card" style="width: 42%; margin-top: 30px;">
      <div slot="header" className="clearfix">
        <span>交流：<a target="_blank" href="https://qm.qq.com/cgi-bin/qm/qr?k=QGRHYDL2XE46mQMgl54WtIUl5pSuHVKP&jump_from=webapi"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="GitGitGo" title="GitGitGo"></a></span>
        <br />
        <span>关注：扫描下方二维码！</span>
      </div>
      <div>
        <span></span>
        <img src="https://imgcdn.besscroft.com/images/9ae613da046d8.png" style="width: 150px" />
      </div>
    </el-card>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'

export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name'
    ])
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
