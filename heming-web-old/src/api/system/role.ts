import { API_URL } from '~/config/config'
import http from '~/api'
import { BasicResponseModel } from '~/api/interface'

/**
 * @name 角色模块
 */
enum Api {
  RolePage = '/role/pageList',
  RoleDelete = '/role/deleteRole',
  RoleAdd = '/role/addRole',
  RoleUpdate = '/role/updateRole',
}

/**
 * @description: 角色分页
 */
export function rolePage(data: any) {
  return http.post<BasicResponseModel>(API_URL + Api.RolePage, data)
}
