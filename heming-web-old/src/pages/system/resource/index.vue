<script setup lang="ts">
import type { DropdownDividerOption, DropdownGroupOption, DropdownOption, DropdownRenderOption } from 'naive-ui'
import { DataTableColumns, NButton } from 'naive-ui'
import { resourcePage } from '~/api/system/resource'

const dataList = ref([])
const message = useMessage()
const valueRef = ref('')

interface Title {
  name: string
  url: string
  description: string
  typeCode: string
  routeKey: string
}

const data = reactive({
  form: {},
  queryParam: {
    pageNum: 1,
    pageSize: 10,
    name: '',
    url: '',
    typeCode: '',
    routeKey: '',
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
      title: '资源名称',
      key: 'name',
      width: 136,
    },
    {
      title: '资源路径',
      key: 'url',
      width: 136,
    },
    {
      title: '资源类型 code',
      key: 'typeCode',
      width: 136,
    },
    {
      title: '路由分配 key',
      key: 'routeKey',
      width: 136,
    },
    {
      title: '资源描述',
      key: 'description',
      width: 136,
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
  resourcePage(data.queryParam).then(res => {
    dataList.value = res.data.list
  })
}

const resetSearch = () => {
  data.queryParam.name = ''
  data.queryParam.url = ''
  data.queryParam.typeCode = ''
  data.queryParam.routeKey = ''
  resourcePage(data.queryParam).then(res => {
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
    <n-page-header subtitle="资源管理">
      <n-grid :x-gap="12" :y-gap="8" cols="1 400:2 600:4">
        <n-gi>
          资源名称
          <n-input v-model:value="data.queryParam.name" type="text" placeholder="请输入资源名称" clearable />
        </n-gi>
        <n-gi>
          资源路径
          <n-input v-model:value="data.queryParam.url" type="text" placeholder="请输入资源编码" clearable />
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
