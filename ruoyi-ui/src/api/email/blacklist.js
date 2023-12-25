import request from '@/utils/request'

// 查询黑名单列表
export function listBlacklist(query) {
  return request({
    url: '/email/blacklist/list',
    method: 'get',
    params: query
  })
}


// 新增黑名单
export function addBlacklist(data) {
  return request({
    url: '/email/blacklist/add',
    method: 'post',
    data: data
  })
}

// 编辑黑名单
export function editBlacklist(data) {
  return request({
    url: '/email/blacklist/edit',
    method: 'post',
    data: data
  })
}

// 删除黑名单
export function deleteBlacklist(data) {
  return request({
    url: '/email/blacklist/delete',
    method: 'post',
    data: data
  })
}
