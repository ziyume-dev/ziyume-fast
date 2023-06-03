import { API_URL } from '~/config/config'
import http from '~/api'
import { BasicResponseModel } from '~/api/interface'

/**
 * @name 资源类别模块
 */
enum Api {
  ResourceTypePage = '/resourceType/pageList',
  ResourceTypeDelete = '/resourceType/deleteResourceType',
  ResourceTypeAdd = '/resourceType/addResourceType',
  ResourceTypeUpdate = '/resourceType/updateResourceType',
}

/**
 * @description: 资源类别分页
 */
export function resourceTypePage(data: any) {
  return http.post<BasicResponseModel>(API_URL + Api.ResourceTypePage, data)
}
