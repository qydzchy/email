import request from '@/utils/request'

// 查询标签列表
export function listLabel(query) {
  return request({
    url: '/email/label/list',
    method: 'get',
    params: query
  })
}


// 新增标签
export function addLabel(data) {
  return request({
    url: '/email/label/add',
    method: 'post',
    data: data
  })
}

// 编辑标签名称
export function editLabelName(data) {
  return request({
    url: '/email/label/edit/name',
    method: 'post',
    data: data
  })
}

// 编辑标签颜色
export function editLabelColor(data) {
  return request({
    url: '/email/label/edit/color',
    method: 'post',
    data: data
  })
}


// 删除标签
export function deleteLabel(data) {
  return request({
    url: '/email/label/delete',
    method: 'post',
    data: data
  })
}
