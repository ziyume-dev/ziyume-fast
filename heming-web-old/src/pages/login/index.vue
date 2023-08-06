<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useMessage } from 'naive-ui'
import { LockClosedOutline, PersonOutline } from '@vicons/ionicons5'
import { ResultEnum } from '~/enums/httpEnum'
import { websiteConfig } from '~/config/website.config'

interface FormState {
  username: string
  password: string
}

const formRef = ref()
const message = useMessage()
const loading = ref(false)
const autoLogin = ref(true)
const LOGIN_NAME = 'Login'

const formInline = reactive({
  username: 'heming',
  password: '666666',
  isCaptcha: true,
})

const rules = {
  username: { required: true, message: '请输入用户名', trigger: 'blur' },
  password: { required: true, message: '请输入密码', trigger: 'blur' },
}

const user = useUserStore()

const router = useRouter()
const route = useRoute()

function handleSubmit(e) {
  e.preventDefault()
  formRef.value.validate(async (errors) => {
    if (!errors) {
      const { username, password } = formInline
      message.loading('登录中...')
      loading.value = true

      const params: FormState = {
        username,
        password,
      }

      try {
        const { code, message: msg } = await user.login(params)
        message.destroyAll()
        if (code === ResultEnum.SUCCESS) {
          const toPath = decodeURIComponent((route.query?.redirect || '/') as string)
          message.success('登录成功，即将进入系统')
          if (route.name === LOGIN_NAME) {
            router.replace('/')
          } else router.replace(toPath)
        } else {
          message.info(msg || '登录失败')
        }
      } finally {
        loading.value = false
      }
    } else {
      message.error('请填写完整信息，并且进行验证码校验')
    }
  })
}
</script>

<template>
  <div class="view-account">
    <div class="view-account-header" />
    <div class="view-account-container">
      <div class="view-account-top">
        <div class="view-account-top-logo">
          <img :src="websiteConfig.loginImage" alt="">
        </div>
        <div class="view-account-top-desc">
          {{ websiteConfig.loginDesc }}
        </div>
      </div>
      <div class="view-account-form">
        <n-form
          ref="formRef"
          label-placement="left"
          size="large"
          :model="formInline"
          :rules="rules"
        >
          <n-form-item path="username">
            <n-input v-model:value="formInline.username" placeholder="请输入用户名">
              <template #prefix>
                <n-icon size="18" color="#808695">
                  <PersonOutline />
                </n-icon>
              </template>
            </n-input>
          </n-form-item>
          <n-form-item path="password">
            <n-input
              v-model:value="formInline.password"
              type="password"
              show-password-on="click"
              placeholder="请输入密码"
            >
              <template #prefix>
                <n-icon size="18" color="#808695">
                  <LockClosedOutline />
                </n-icon>
              </template>
            </n-input>
          </n-form-item>
          <n-form-item class="default-color">
            <div class="flex justify-between">
              <div class="flex-initial">
                <n-checkbox v-model:checked="autoLogin">
                  自动登录
                </n-checkbox>
              </div>
            </div>
          </n-form-item>
          <n-form-item>
            <n-button type="primary" size="large" :loading="loading" block @click="handleSubmit">
              登录
            </n-button>
          </n-form-item>
        </n-form>
      </div>
    </div>
  </div>
</template>

<style lang="less" scoped>
.view-account {
  display: flex;
  flex-direction: column;
  height: 100vh;
  overflow: auto;

  &-container {
    flex: 1;
    padding: 32px 12px;
    max-width: 384px;
    min-width: 320px;
    margin: 0 auto;
  }

  &-top {
    padding: 32px 0;
    text-align: center;

    &-desc {
      font-size: 14px;
      color: #808695;
    }
  }

  &-other {
    width: 100%;
  }

  .default-color {
    color: #515a6e;

    .ant-checkbox-wrapper {
      color: #515a6e;
    }
  }
}

@media (min-width: 768px) {
  .view-account {
    background-image: url('../../assets/images/login.svg');
    background-repeat: no-repeat;
    background-position: 50%;
    background-size: 100%;
  }

  .page-account-container {
    padding: 32px 0 24px 0;
  }
}
</style>
