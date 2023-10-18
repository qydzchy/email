import request from '@/utils/request'

// 查询快速文本列表
export function listQuickText(query) {
  return request({
    url: '/email/quick/text/list',
    method: 'get',
    params: query
  })
}


// 新增快速文本
export function addQuickText(data) {
  return request({
    url: '/email/quick/text/add',
    method: 'post',
    data: data
  })
}

// 编辑快速文本
export function editQuickText(data) {
  return request({
    url: '/email/quick/text/edit',
    method: 'post',
    data: data
  })
}


// 删除快速文本
export function deleteQuickText(data) {
  return request({
    url: '/email/quick/text/delete',
    method: 'post',
    data: data
  })
}
