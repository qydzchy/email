import request from '@/utils/request'

// 查询模板类型列表
export function listTemplateType(query) {
  return request({
    url: '/email/template/type/list',
    method: 'get',
    params: query
  })
}


// 新增模板类型
export function addTemplateType(data) {
  return request({
    url: '/email/template/type/add',
    method: 'post',
    data: data
  })
}

// 编辑模板类型
export function editTemplateType(data) {
  return request({
    url: '/email/template/type/edit',
    method: 'post',
    data: data
  })
}


// 删除模板类型
export function deleteTemplateType(data) {
  return request({
    url: '/email/template/type/delete',
    method: 'post',
    data: data
  })
}
