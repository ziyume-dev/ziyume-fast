import request from '@/utils/request'

// 后台管理系统用户登录
export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}

// 获取用户信息
export function getInfo() {
  return request({
    url: '/user/info',
    method: 'get'
  })
}

// 后台管理系统用户退出登录
export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}

// 查询权限管理模块用户列表
export function listUser(listQuery) {
  return request({
    url: '/user/list',
    method: 'get',
    params: {
      pageNum: listQuery.pageNum,
      pageSize: listQuery.pageSize
    }
  })
}

// 查询权限管理模块用户详细
export function getUser(id) {
  return request({
    url: '/user/getUser/' + id,
    method: 'get'
  })
}

// 新增权限管理模块用户
export function addUser(data) {
  return request({
    url: '/user/addUser',
    method: 'post',
    data: data
  })
}

// 修改权限管理模块用户
export function updateUser(data) {
  return request({
    url: '/user/updateUser',
    method: 'put',
    data: data
  })
}

// 删除权限管理模块用户
export function delUser(id) {
  return request({
    url: '/user/delUser/' + id,
    method: 'delete'
  })
}

// 导出权限管理模块用户
export function exportUser(query) {
  return request({
    url: '/user/exportUser',
    method: 'get',
    params: query
  })
}

// 用户账户启用状态更新
export function changeSwitch(row) {
  return request({
    url: '/user/changeSwitch',
    method: 'put',
    params: {
      status: row.status,
      id: row.id
    }
  })
}
