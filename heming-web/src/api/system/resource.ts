import { API_URL } from '~/config/config'
import http from '~/api'
import { BasicResponseModel } from '~/api/interface'

/**
 * @name 资源模块
 */
enum Api {
  ResourcePage = '/resource/pageList',
  ResourceDelete = '/resource/deleteResource',
  ResourceAdd = '/resource/addResource',
  ResourceUpdate = '/resource/updateResource',
}

/**
 * @description: 资源分页
 */
export function resourcePage(data: any) {
  return http.post<BasicResponseModel>(API_URL + Api.ResourcePage, data)
}
