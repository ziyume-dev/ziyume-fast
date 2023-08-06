<script setup lang="ts">
import type { DropdownDividerOption, DropdownGroupOption, DropdownOption, DropdownRenderOption } from 'naive-ui'
import { DataTableColumns, NAvatar, NButton } from 'naive-ui'
import { deleteUser, userPage } from '~/api/system/user'

const dataList = ref([])
const message = useMessage()
const valueRef = ref('')

interface Title {
  username: string
  avatar: string
  email: string
  name: string
  telephone: string
  status: string
  Optional: string
}

const data = reactive({
  form: {},
  queryParam: {
    pageNum: 1,
    pageSize: 10,
    username: '',
    name: '',
    telephone: '',
    email: '',
  },
})

const handleDelete = (row: any) => {
  deleteUser(row.id).then(res => {
    message.success('删除成功')
    handleSearch()
  })
}

const handleUpdate = (row: any) => {
  message.info('handleUpdate')
}

const createColumns = (): DataTableColumns<Title> => {
  return [
    {
      title: '账号(用户名)',
      key: 'username',
      width: 136,
    },
    {
      title: '头像',
      key: 'avatar',
      width: 136,
      render (row) {
        return h(
          NAvatar,
          {
            size: 36,
            src: row.avatar,
            round: true,
          },
        )
      },
    },
    {
      title: '邮箱',
      key: 'email',
      width: 136,
    },
    {
      title: '昵称',
      key: 'name',
      width: 136,
    },
    {
      title: '手机',
      key: 'telephone',
      width: 136,
    },
    {
      title: '帐号启用状态',
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

const emailOptions = computed(() => {
  return ['@gmail.com', '@163.com', '@qq.com'].map((suffix) => {
    const prefix = valueRef.value.split('@')[0]
    return {
      label: prefix + suffix,
      value: prefix + suffix
    }
  })
})

const handleSearch = () => {
  userPage(data.queryParam).then(res => {
    dataList.value = res.data.list
  })
}

const resetSearch = () => {
  data.queryParam.username = ''
  data.queryParam.name = ''
  data.queryParam.telephone = ''
  data.queryParam.email = ''
  userPage(data.queryParam).then(res => {
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
    <n-page-header subtitle="用户管理">
      <n-grid :x-gap="12" :y-gap="8" cols="1 400:2 600:4">
        <n-gi>
          用户名
          <n-input v-model:value="data.queryParam.username" type="text" placeholder="请输入用户名" clearable />
        </n-gi>
        <n-gi>
          昵称
          <n-input v-model:value="data.queryParam.name" type="text" placeholder="请输入昵称" clearable />
        </n-gi>
        <n-gi>
          手机号
          <n-input-group>
            <n-input-group-label>+86</n-input-group-label>
            <n-input v-model:value="data.queryParam.telephone" clearable />
          </n-input-group>
        </n-gi>
        <n-gi>
          邮箱
          <n-auto-complete
            v-model:value="data.queryParam.email"
            :input-props="{ autocomplete: 'disabled' }"
            :options="emailOptions"
            placeholder="请输入邮箱"
            clearable
          />
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
