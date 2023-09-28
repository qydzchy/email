import request from '@/utils/request'

// 查询标签列表
export function listLabel(query) {
  return request({
    url: '/email/label/list',
    method: 'get',
    params: query
  })
}
