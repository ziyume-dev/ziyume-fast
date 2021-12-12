import request from '@/utils/request'

// 获取菜单列表
export function listMenu(listQuery) {
  return request({
    url: '/menu/list',
    method: 'get',
    params: {
      pageNum: listQuery.pageNum,
      pageSize: listQuery.pageSize
    }
  })
}

// 查询权限管理模块菜单详细
export function getMenu(id) {
  return request({
    url: '/menu/getMenu/' + id,
    method: 'get'
  })
}

// 获取所有父菜单
export function getParentMenu() {
  return request({
    url: '/menu/getParentMenu',
    method: 'get'
  })
}

// 新增权限管理模块菜单
export function addMenu(data) {
  return request({
    url: '/menu/addMenu',
    method: 'post',
    data: data
  })
}

// 修改权限管理模块菜单
export function updateMenu(data) {
  return request({
    url: '/menu/updateMenu',
    method: 'put',
    data: data
  })
}

// 删除权限管理模块菜单
export function delMenu(id) {
  return request({
    url: '/menu/delMenu/' + id,
    method: 'delete'
  })
}

// 导出权限管理模块菜单
export function exportMenu(query) {
  return request({
    url: '/menu/exportMenu',
    method: 'get',
    params: query
  })
}

// 菜单启用状态更新
export function changeSwitch(row) {
  return request({
    url: '/menu/changeSwitch',
    method: 'put',
    params: {
      hidden: row.hidden,
      id: row.id
    }
  })
}

// 获取所有菜单的菜单树
export function getAllMenuTree() {
  return request({
    url: '/menu/getAllMenuTree',
    method: 'get'
  })
}

// 根据角色id获取菜单树数组
export function getMenuTreeById(id) {
  return request({
    url: '/menu/getMenuTreeById/' + id,
    method: 'get'
  })
}

// 更新菜单树
export function updateMenuTree(row) {
  return request({
    url: '/menu/updateMenuTree',
    method: 'put',
    params: {
      id: row.id
    },
    data: row.data
  })
}
