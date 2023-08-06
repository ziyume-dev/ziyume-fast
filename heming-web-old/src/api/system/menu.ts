import { API_URL } from '~/config/config'
import http from '~/api'
import { BasicResponseModel } from '~/api/interface'

/**
 * @name 菜单模块
 */
enum Api {
  MenuPage = '/menu/pageList',
  MenuDelete = '/menu/deleteMenu',
  MenuAdd = '/menu/addMenu',
  MenuUpdate = '/menu/updateMenu',
}

/**
 * @description: 菜单分页
 */
export function menuPage(data: any) {
  return http.post<BasicResponseModel>(API_URL + Api.MenuPage, data)
}
