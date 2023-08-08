<script setup lang="ts">
import { appName } from '../../constants'

const user = useUserStore()
const router = useRouter()
const formattedValue = ref('2023.08.08 12:08:08')
const inputValue = ref(null)
const show = ref(true)

const dropdownOptions = [
  {
    label: '滨海湾金沙，新加坡',
    key: 'marina bay sands',
    disabled: true
  },
  {
    label: '布朗酒店，伦敦',
    key: "brown's hotel, london"
  },
  {
    label: '亚特兰蒂斯巴哈马，拿骚',
    key: 'atlantis nahamas, nassau'
  },
  {
    label: '比佛利山庄酒店，洛杉矶',
    key: 'the beverly hills hotel, los angeles'
  }
]

onMounted(() => {
  useFetch('/api/user',
      {
        headers: {
          Authorization: 'Bearer ' + user.token
        },
        method: 'GET',
      }).then((res: any) => {
        console.log(res)
      }).catch((err: any) => {
        // console.log(err)
      })
})

definePageMeta({
  title: '首页',
  layout: 'admin',
  middleware: ['route']
})
</script>

<template>
  <div>
    <p mr-auto font-serif text-xl font-normal>{{ appName }}</p>
    <n-divider title-placement="left">
      路由信息
    </n-divider>
    <div>{{ router }}</div>
    <n-divider title-placement="left">
      组件
    </n-divider>
    <div flex space-x-1>
      <n-button dashed>
        Default
      </n-button>
      <n-dropdown trigger="hover" :options="dropdownOptions">
        <n-button>找个地方休息</n-button>
      </n-dropdown>
      <n-tag> 爱在西元前 </n-tag>
      <n-color-picker w-80 />
      <div>
        <pre>{{ formattedValue }}</pre>
        <n-date-picker
            v-model:formatted-value="formattedValue"
            value-format="yyyy.MM.dd HH:mm:ss"
            type="datetime"
            clearable
        />
      </div>
      <n-input w-80 v-model:value="inputValue" type="text" placeholder="基本的 Input" />
    </div>
    <n-space vertical>
      <n-switch v-model:value="show">
        <template #checked>
          展开
        </template>
        <template #unchecked>
          折叠
        </template>
      </n-switch>
      <n-collapse-transition :show="show">
        感知度，方法论，组合拳，引爆点，点线面，精细化，差异化，平台化，结构化
        <br />
        影响力，耦合性，便捷性，一致性，端到端，短平快，护城河，体验感，颗粒度
      </n-collapse-transition>
    </n-space>
  </div>
</template>
