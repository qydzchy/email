import request from '@/utils/request'

// 查询邮箱任务列表
export function listTask(query) {
  return request({
    url: '/email/folder/list',
    method: 'get',
    params: query
  })
}
