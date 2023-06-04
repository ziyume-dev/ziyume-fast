import type { AxiosError, AxiosInstance, AxiosRequestConfig, AxiosResponse } from 'axios'
import axios from 'axios'
import { AxiosCanceler } from './helper/axiosCancel'
import type { BasicResponseModel } from '~/api/interface'
import { ResultEnum } from '~/enums/httpEnum'
import { checkStatus } from '~/api/helper/checkStatus'
import { createPinia } from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import App from '~/App.vue'
import { UserEnum } from '~/enums/commonEnum'

/**
 * pinia 错误使用说明示例
 * https://github.com/vuejs/pinia/discussions/971
 * https://github.com/vuejs/pinia/discussions/664#discussioncomment-1329898
 * https://pinia.vuejs.org/core-concepts/outside-component-usage.html#single-page-applications
 */
const app = createApp(App)
const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)
app.use(pinia)
const axiosCanceler = new AxiosCanceler()

const config = {
  // 默认地址请求地址，可在 .env 开头文件中修改
  baseURL: import.meta.env.VITE_API_URL as string,
  // 设置超时时间
  timeout: ResultEnum.TIMEOUT as number,
  // 跨域时候允许携带凭证
  withCredentials: true,
}

class RequestHttp {
  service: AxiosInstance
  public constructor(config: AxiosRequestConfig) {
    // 实例化axios
    this.service = axios.create(config)

    // @ts-ignore
    /**
     * @description 请求拦截器
     * 客户端发送请求 -> [请求拦截器] -> 服务器
     * token校验(JWT) : 接收服务器返回的 token,存储到 pinia / 本地储存当中
     */
    this.service.interceptors.request.use(
      // @ts-ignore
      (config: AxiosRequestConfig) => {
        // 将当前请求添加到 pending 中
        axiosCanceler.addPending(config)
        // 如果当前请求不需要显示 loading,在 api 服务中通过指定的第三个参数: { headers: { noLoading: true } }来控制不显示loading
        // eslint-disable-next-line no-unused-expressions
        config.headers!.noLoading
        const tokenValue = localStorage.getItem('heming-token')
        const token = `Bearer ${tokenValue}`
        return { ...config, headers: { ...config.headers, Authorization: token } }
      },
      (error: AxiosError) => {
        return Promise.reject(error)
      },
    )

    /**
     * @description 响应拦截器
     *  服务器换返回信息 -> [拦截统一处理] -> 客户端JS获取到信息
     */
    this.service.interceptors.response.use(
      (response: AxiosResponse) => {
        const user = useUserStore(pinia)
        const { data, config } = response
        // 在请求结束后，移除本次请求
        axiosCanceler.removePending(config)
        // https://stackoverflow.com/questions/3297048/403-forbidden-vs-401-unauthorized-http-responses
        // 登陆失效（code == 401）
        if (data.code === ResultEnum.UNAUTHORIZED) {
          window.$message.error('登陆失效，请重新登陆')
          window.location.href = '/login'
          localStorage.setItem(UserEnum.TOKEN, '')
          user.setUserName('')
          user.setToken('')
          return Promise.reject(data)
        }
        // 没有权限（code == 403）
        if (data.code === ResultEnum.FORBIDDEN) {
          window.$message.error(data.message)
          return Promise.reject(data)
        }
        // 全局错误信息拦截（防止下载文件得时候返回数据流，没有code，直接报错）
        if (data.code && data.code !== ResultEnum.SUCCESS) {
          // message.error(data.message)
          return Promise.reject(data)
        }
        // 成功请求（在页面上除非特殊情况，否则不用处理失败逻辑）
        return data
      },
      async (error: AxiosError) => {
        const { response } = error
        // 请求超时单独判断，因为请求超时没有 response
        if (error.message.includes('timeout')) {
          window.$message.error('请求超时，请刷新重试')
          return Promise.reject(error)
        }
        // 根据响应的错误状态码，做不同的处理
        if (response) {
          checkStatus(response.status)
        }
        // 服务器结果都没有返回(可能服务器错误可能客户端断网)，断网处理:可以跳转到断网页面
        if (!window.navigator.onLine) {
          window.location.href = '/500'
        }
        return Promise.reject(error)
      },
    )
  }

  // * 常用请求方法封装
  get<T>(url: string, params?: object, _object = {}): Promise<BasicResponseModel<T>> {
    return this.service.get(url, { params, ..._object })
  }

  post<T>(url: string, params?: object, _object = {}): Promise<BasicResponseModel<T>> {
    return this.service.post(url, params, _object)
  }

  put<T>(url: string, params?: object, _object = {}): Promise<BasicResponseModel<T>> {
    return this.service.put(url, params, _object)
  }

  delete<T>(url: string, params?: any, _object = {}): Promise<BasicResponseModel<T>> {
    return this.service.delete(url, { params, ..._object })
  }
}

export default new RequestHttp(config)