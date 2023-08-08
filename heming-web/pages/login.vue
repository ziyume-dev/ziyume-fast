<script setup lang="ts">
import type { FormInst, FormRules } from 'naive-ui'
import { Login } from '~/types'
import { useMessage } from 'naive-ui'
import { getBaseUrl } from '~/utils/api'

const message = useMessage()
const user = useUserStore()
const router = useRouter()
const formRef = ref<FormInst | null>(null)

const loginForm = reactive<Login.ReqLoginForm>({
  username: '',
  password: '',
})

const rules: FormRules = {
  username: [
    { required: true, message: '用户名不能为空！', trigger: 'blur' },
    { min: 6, max: 20, message: '用户名长度在 6~20 之间', trigger: 'blur' },
  ],
  password: [
    { required: true, message: '密码不能为空！', trigger: 'blur' },
    { min: 6, max: 20, message: '密码长度在 6~20 之间', trigger: 'blur' },
  ],
}

const handleSubmitClick = () => {
  formRef.value?.validate(async (errors) => {
    if (!errors) {
      await $fetch('/user/login', {
        baseURL: getBaseUrl(),
        method: 'POST',
        body: { username: loginForm.username, password: loginForm.password },
        parseResponse: JSON.parse,
      }).then((res: any) => {
        if (res.code === 200) {
          message.success('登录成功')
          user.setToken(res.data.tokenValue)
          user.setTokenName(res.data.tokenName)
          // 等待一秒
          setTimeout(() => {
            router.push('/admin')
          }, 1000)
        } else {
          message.error(res.message)
        }
      })
    } else {
      console.log(errors)
    }
  })
}

const handleRegisterClick = () => {
  message.info('还没写呢')
}
</script>

<template>
  <section bg-white>
    <div lg:grid lg:min-h-screen lg:grid-cols-12>
      <section relative flex h-32 items-end bg-gray-900 lg:col-span-5 lg:h-full xl:col-span-6>
        <img
            alt="Night"
            src="https://images.unsplash.com/photo-1617195737496-bc30194e3a19?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"
            absolute inset-0 h-full w-full object-cover opacity-80
        />

        <div chidden lg:relative lg:block lg:p-12>

          <h2 mt-6 text-2xl font-bold text-white sm:text-3xl md:text-4xl>
            HeMing Fast
          </h2>

          <p mt-4 leading-relaxed  class="text-white/90">
            HeMing 工作室出品，一款基于 SpringBoot 3 和 Nuxt3 的脚手架
          </p>
        </div>
      </section>

      <main flex flex-col items-center justify-center px-4 py-4 sm:px-12 lg:col-span-7 lg:px-16 lg:py-12 xl:col-span-6>
        <h2 mt-6 text-2xl font-bold sm:text-3xl md:text-4xl>
          HeMing Fast
        </h2>
        <n-form
            mt-8 w-80
            ref="formRef"
            :model="loginForm"
            :rules="rules"
        >
          <n-form-item label="用户名" path="username" required>
            <n-input v-model:value="loginForm.username" placeholder="输入用户名" />
          </n-form-item>
          <n-form-item label="密码" path="password" required>
            <n-input v-model:value="loginForm.password" placeholder="输入密码" />
          </n-form-item>
          演示账号：demo/666666
          <n-form-item>
            <n-button attr-type="button" @click="handleSubmitClick">
              登录
            </n-button>
            <n-button ml-2 attr-type="button" @click="handleRegisterClick">
              注册
            </n-button>
          </n-form-item>
        </n-form>
      </main>
    </div>
  </section>
</template>

<style scoped>

</style>
