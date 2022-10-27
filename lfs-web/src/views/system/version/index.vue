<template>
  <div class="dashboard-container">
    <el-card class="box-card" shadow="hover">
      <span>版本更新/发布日志</span>
      <i style="float: right; padding: 3px 0" class="el-icon-s-promotion"></i>
    </el-card>

    <el-card class="box-card" shadow="hover" style="margin-top: 1.25em">
      <el-collapse v-model="activeNames" v-for="item in items" :key="item.name">
        <el-collapse-item :name="item.name">
          <template slot="title">
            <i :class="item.titleIcon" style="margin-left: 5px;"></i>&nbsp;{{item.title}}
            <el-tag :type="item.titleType" style="margin-left: 5px;">{{item.titleTagInfo}}</el-tag>
            <el-tag style="margin-left: 5px;">{{item.titleTag}}</el-tag>
            <el-tag style="margin-left: 5px;" effect="dark">{{item.titleTime}}</el-tag>
          </template>
          <ul v-for="itail in item.detail" :key="itail.id">
            <li>{{itail.body}}</li>
          </ul>
        </el-collapse-item>
      </el-collapse>
    </el-card>

  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'version',
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  data() {
    return {
      // 默认展开的面板的name值
      activeNames: ['1'],
      // 这里放数据
      items: [
        // 这里是模板，可复制一份填写（数据必须是json格式，否则将解析错误）
        // {
        //     name: "3",           // 这个是name，对应上面的activeNames，如果有相应的值，会默认展开
        //     title: "v.0.0.3",    // 这个将显示版本号
        //     titleIcon: "el-icon-s-promotion",    // 这个将显示图标https://element.eleme.cn/#/zh-CN/component/icon
        //     titleType: "warning",                // 这个是第一个tag的样式，可选值：success，info，warning，danger
        //     titleTagInfo: "样式更新",             // 这个是第一个tag的内容
        //     titleTag: "稳定版",                   // 这个是第二个tag的内容
        //     titleTime: "这里放时间",                       // 这里是第三个tag的内容
        //     detail: [                // 这里是列表项要显示的内容，为一个数组
        //         {
        //             id: 1,           // 列表项子项的id
        //             body: "内容"      // 列表项子项的内容
        //         }
        //     ]
        // }
        {
          name: "1",
          title: "v2.0.0-rc0",
          titleIcon: "el-icon-s-promotion",
          titleType: "success",
          titleTagInfo: "新版本发布",
          titleTag: "Preview",
          titleTime: "2022-10-27",
          detail: [
            {
              id: 1,
              body: "[update] JDK 从 1.8 升级为 17"
            },
            {
              id: 2,
              body: "[build] Docker 基础镜像版本升级"
            },
            {
              id: 3,
              body: "[remove] 移除 Spring Boot Admin 支持，后续将支持 Grafana"
            },
            {
              id: 4,
              body: "[update] 升级 SpringBoot 版本为 3.0.0-RC1，依赖从 javax 切换到 jakarta"
            },
            {
              id: 5,
              body: "[refactor] 重写基于 Spring Security 的权限验证模块"
            },
            {
              id: 6,
              body: "[refactor] Druid 暂不支持，换成了 Hikari"
            },
            {
              id: 7,
              body: "[fix] 修复了一些问题，提升了性能"
            },
          ]
        },
        {
          name: "2",
          title: "v1.3.1-fix",
          titleIcon: "el-icon-s-promotion",
          titleType: "success",
          titleTagInfo: "数据库更新",
          titleTag: "General Availability",
          titleTime: "2022-10-21",
          detail: [
            {
              id: 1,
              body: "[doc] JDK 1.8 最后一个支持版本"
            },
            {
              id: 2,
              body: "[fix] 修复了一些问题，提升了性能"
            }
          ]
        },
        {
          name: "3",
          title: "v1.2.0",
          titleIcon: "el-icon-s-promotion",
          titleType: "success",
          titleTagInfo: "数据库更新",
          titleTag: "Preview",
          titleTime: "2022-07-05",
          detail: [
            {
              id: 1,
              body: "[update] 数据库从 mysql 更改为 postgresql"
            },
            {
              id: 2,
              body: "[fix] 修复了一些问题，提升了性能"
            }
          ]
        },
        {
          name: "4",
          title: "v1.0.0",
          titleIcon: "el-icon-s-promotion",
          titleType: "success",
          titleTagInfo: "预览版发布",
          titleTag: "Preview",
          titleTime: "2021-12-12",
          detail: [
            {
              id: 1,
              body: "[feat] 预览版现已推出"
            },
            {
              id: 2,
              body: "[update] 数据库更新"
            },
            {
              id: 3,
              body: "[fix] 修复了样式处理效果，提升了性能"
            }
          ]
        }
      ]
    }
  },
  created() {
    this.getList();
  },
  methods: {
    /** 获取版本日志列表 */
    // getList() {
    //   this.loading = true;
    //   listUpdateLog().then(response => {
    //     console.log(response.data)
    //     const data = response.data;
    //     this.items = data;
    //     this.activeNames = data.length;
    //     this.loading = false;
    //   });
    // }
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
