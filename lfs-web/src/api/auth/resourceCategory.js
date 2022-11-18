import request from '@/utils/request'

// 获取资源类别列表
export function listResourceCategory(listQuery) {
  return request({
    url: '/resourceCategory/list',
    method: 'get',
    params: {
      pageNum: listQuery.pageNum,
      pageSize: listQuery.pageSize
    }
  })
}

// 查询资源类别详细
export function getResourceCategory(id) {
  return request({
    url: '/resourceCategory/getResourceCategory/' + id,
    method: 'get'
  })
}

// 新增资源类别
export function addResourceCategory(data) {
  return request({
    url: '/resourceCategory/addResourceCategory',
    method: 'post',
    data: data
  })
}

// 修改资源类别
export function updateResourceCategory(data) {
  return request({
    url: '/resourceCategory/updateResourceCategory',
    method: 'put',
    data: data
  })
}

// 删除资源类别
export function delResourceCategory(id) {
  return request({
    url: '/resourceCategory/delResourceCategory/' + id,
    method: 'delete'
  })
}

// 导出资源类别列表
export function exportResourceCategory(query) {
  return request({
    url: '/resourceCategory/exportResourceCategory',
    method: 'get',
    params: query
  })
}
