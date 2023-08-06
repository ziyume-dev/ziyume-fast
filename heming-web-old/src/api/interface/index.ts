// 请求响应参数
export interface BasicResponseModel<T = any> {
  code: number
  message: string
  data: T
}

// 分页请求响应参数
export interface PageResultData<T = any> extends BasicResponseModel {
  list: T[]
  pageNum: number
  pageSize: number
  total: number
  totalPage: number
}

// 分页请求参数
export interface ReqPage {
  pageNum: number
  pageSize: number
}
