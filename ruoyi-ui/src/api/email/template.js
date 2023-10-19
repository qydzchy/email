import request from '@/utils/request'

// 查询模板列表
export function listTemplate(query) {
  return request({
    url: '/email/template/list',
    method: 'get',
    params: query
  })
}


// 新增模板
export function addTemplate(data) {
  return request({
    url: '/email/template/add',
    method: 'post',
    data: data
  })
}

// 编辑模板
export function editTemplate(data) {
  return request({
    url: '/email/template/edit',
    method: 'post',
    data: data
  })
}


// 删除模板
export function deleteTemplate(data) {
  return request({
    url: '/email/template/delete',
    method: 'post',
    data: data
  })
}
