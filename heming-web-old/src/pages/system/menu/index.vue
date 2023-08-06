<script setup lang="ts">
import type { DropdownDividerOption, DropdownGroupOption, DropdownOption, DropdownRenderOption } from 'naive-ui'
import {DataTableColumns, NButton} from 'naive-ui'
import { menuPage } from '~/api/system/menu'

const dataList = ref([])
const message = useMessage()
const valueRef = ref('')

interface Title {
  title: string
  name: string
  path: string
  redirect: string
  component: string
  icon: string
  disabled: string
  hidden: string
  keepAlive: string
  frameSrc: string
  affix: string
}

const data = reactive({
  form: {},
  queryParam: {
    pageNum: 1,
    pageSize: 10,
    title: '',
    name: '',
    path: '',
  },
})

const handleDelete = (row: any) => {
  message.info('handleDelete')
}

const handleUpdate = (row: any) => {
  message.info('handleUpdate')
}

const createColumns = (): DataTableColumns<Title> => {
  return [
    {
      title: '菜单名称',
      key: 'title',
      width: 136,
    },
    {
      title: '前端名称',
      key: 'name',
      width: 136,
    },
    {
      title: '路由地址',
      key: 'path',
      width: 136,
    },
    {
      title: '重定向地址',
      key: 'redirect',
      width: 136,
    },
    {
      title: '组件路径',
      key: 'component',
      width: 136,
    },
    {
      title: '菜单图标名称',
      key: 'icon',
      width: 136,
    },
    {
      title: '禁用状态',
      key: 'disabled',
    },
    {
      title: '菜单显示状态',
      key: 'hidden',
    },
    {
      title: '是否缓存',
      key: 'keepAlive',
    },
    {
      title: '内联外部地址',
      key: 'frameSrc',
      width: 136,
    },
    {
      title: '是否固定',
      key: 'affix',
    },
    {
      title: '操作',
      key: 'Optional',
      width: 136,
      render (row) {
        return h(
          'div',
          {
            class: 'flex space-x-1'
          },
          [
            h(NButton,
              {
                onClick: () => handleUpdate(row),
                type: 'warning',
              },
              { default: () => '更新' }
            ),
            h(NButton,
              {
                onClick: () => handleDelete(row),
                type: 'error',
              },
              { default: () => '删除' }
            )
          ],
        )
      },
    },
  ]
}

const tableColumns = ref(createColumns())

const options = ref<Array<DropdownOption | DropdownGroupOption | DropdownDividerOption | DropdownRenderOption>>([])

const handleSearch = () => {
  menuPage(data.queryParam).then(res => {
    dataList.value = res.data.list
  })
}

const resetSearch = () => {
  data.queryParam.title = ''
  data.queryParam.name = ''
  data.queryParam.path = ''
  menuPage(data.queryParam).then(res => {
    dataList.value = res.data.list
  })
}

onMounted(() => {
  handleSearch()
})
</script>

<template>
  <n-card>
    <!-- 页头 -->
    <n-page-header subtitle="菜单管理">
      <n-grid :x-gap="12" :y-gap="8" cols="1 400:2 600:4">
        <n-gi>
          菜单名称
          <n-input v-model:value="data.queryParam.title" type="text" placeholder="请输入菜单名称" clearable />
        </n-gi>
        <n-gi>
          前端名称
          <n-input v-model:value="data.queryParam.name" type="text" placeholder="请输入前端名称" clearable />
        </n-gi>
        <n-gi>
          路由地址
          <n-input v-model:value="data.queryParam.path" type="text" placeholder="请输入路由地址" clearable />
        </n-gi>
      </n-grid>
      <template #extra>
        <n-space>
          <n-button @click="handleSearch" type="info">搜索</n-button>
          <n-button @click="resetSearch" strong secondary type="warning">重置</n-button>
          <n-button type="success">新增</n-button>
        </n-space>
      </template>
    </n-page-header>
    <!-- 列表 -->
    <n-data-table
      :columns="tableColumns"
      :data="dataList"
      :bordered="false"
      class="mt-4"
    />
  </n-card>
</template>

<style scoped lang="less">

</style>
