import { API_URL } from '~/config/config'
import http from '~/api'
import { BasicResponseModel } from '~/api/interface'

/**
 * @name 用户模块
 */
enum Api {
  Login = '/user/login',
  GetUserInfo = '/user/info',
  LoginOut = '/user/loginOut',
  UserPage = '/user/pageList',
  UserDelete = '/user/deleteUser',
  UserAdd = '/user/addUser',
  UserUpdate = '/user/updateUser',
}

/**
 * @description: 获取用户信息
 */
export function getUserInfo() {
  return http.get<BasicResponseModel>(API_URL + Api.GetUserInfo)
}

/**
 * @description: 用户登录
 */
export function login(params: any) {
  return http.post(API_URL + Api.Login, params)
}

/**
 * @description: 用户登出
 */
export function logout(params: any) {
  return http.post(API_URL + Api.LoginOut, params)
}

/**
 * @description: 用户分页
 */
export function userPage(params: any) {
  return http.post(API_URL + Api.UserPage, params)
}

/**
 * @description: 用户删除
 */
export function deleteUser(userId: number) {
  return http.delete(API_URL + Api.UserDelete + `/${userId}`)
}
