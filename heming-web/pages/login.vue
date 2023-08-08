<script setup lang="ts">
import { FormInst } from 'naive-ui'

const formRef = ref<FormInst | null>(null)

const formValue = ref({
  username: '',
  password: ''
})

const rules = {
  username: {
    required: true,
    message: '请输入用户名',
    trigger: 'input'
  },
  password: {
    required: true,
    message: '请输入密码',
    trigger: 'input'
  }
}

const handleSubmitClick = () => {
  formRef.value?.validate((errors) => {
    if (!errors) {
      console.log(formValue.value)
    } else {
      console.log(errors)
    }
  })
}
</script>

<template>
  <section class="bg-white">
    <div class="lg:grid lg:min-h-screen lg:grid-cols-12">
      <section
          class="relative flex h-32 items-end bg-gray-900 lg:col-span-5 lg:h-full xl:col-span-6"
      >
        <img
            alt="Night"
            src="https://images.unsplash.com/photo-1617195737496-bc30194e3a19?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"
            class="absolute inset-0 h-full w-full object-cover opacity-80"
        />

        <div class="hidden lg:relative lg:block lg:p-12">

          <h2 class="mt-6 text-2xl font-bold text-white sm:text-3xl md:text-4xl">
            HeMing Fast
          </h2>

          <p class="mt-4 leading-relaxed text-white/90">
            HeMing 脚手架
          </p>
        </div>
      </section>

      <main
          class="flex items-center justify-center px-8 py-8 sm:px-12 lg:col-span-7 lg:px-16 lg:py-12 xl:col-span-6"
      >
        <div class="max-w-xl lg:max-w-3xl">
          <n-form
              ref="formRef"
              inline
              :label-width="80"
              :model="formValue"
              :rules="rules"
          >
            <n-form-item label="用户名" path="user.username">
              <n-input v-model:value="formValue.username" placeholder="输入用户名" />
            </n-form-item>
            <n-form-item label="密码" path="user.password">
              <n-input v-model:value="formValue.password" placeholder="输入密码" />
            </n-form-item>
            <n-form-item>
              <n-button attr-type="button" @click="handleSubmitClick">
                登录
              </n-button>
            </n-form-item>
          </n-form>
          <form action="#" class="mt-8 grid grid-cols-6 gap-6">
            <div class="col-span-6 sm:flex sm:items-center sm:gap-4">
              <button
                  class="inline-block shrink-0 rounded-md border border-blue-600 bg-blue-600 px-12 py-3 text-sm font-medium text-white transition hover:bg-transparent hover:text-blue-600 focus:outline-none focus:ring active:text-blue-500"
              >
                登录
              </button>
              <button
                  class="inline-block shrink-0 rounded-md border border-blue-600 bg-blue-600 px-12 py-3 text-sm font-medium text-white transition hover:bg-transparent hover:text-blue-600 focus:outline-none focus:ring active:text-blue-500"
              >
                注册
              </button>
            </div>
          </form>
        </div>
      </main>
    </div>
  </section>
</template>

<style scoped>

</style>
