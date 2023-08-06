<script setup lang="ts">
import type { DropdownDividerOption, DropdownGroupOption, DropdownOption, DropdownRenderOption } from 'naive-ui'
import { DataTableColumns, NButton } from 'naive-ui'
import { rolePage } from '~/api/system/role'

const dataList = ref([])
const message = useMessage()
const valueRef = ref('')

interface Title {
  name: string
  code: string
  description: string
  status: string
}

const data = reactive({
  form: {},
  queryParam: {
    pageNum: 1,
    pageSize: 10,
    name: '',
    code: '',
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
      title: '角色名称',
      key: 'name',
      width: 136,
    },
    {
      title: '角色编码',
      key: 'code',
      width: 136,
    },
    {
      title: '描述',
      key: 'description',
      width: 136,
    },
    {
      title: '角色启用状态',
      key: 'status',
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
  rolePage(data.queryParam).then(res => {
    dataList.value = res.data.list
  })
}

const resetSearch = () => {
  data.queryParam.name = ''
  data.queryParam.code = ''
  rolePage(data.queryParam).then(res => {
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
    <n-page-header subtitle="角色管理">
      <n-grid :x-gap="12" :y-gap="8" cols="1 400:2 600:4">
        <n-gi>
          角色名称
          <n-input v-model:value="data.queryParam.name" type="text" placeholder="请输入角色名称" clearable />
        </n-gi>
        <n-gi>
          角色编码
          <n-input v-model:value="data.queryParam.code" type="text" placeholder="请输入角色编码" clearable />
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
