import request from '@/utils/request'

// 查询邮箱任务列表
export function listTask(query) {
  return request({
    url: '/email/folder/list',
    method: 'get',
    params: query
  })
}

// 新增文件夹
export function addFolder(data) {
  return request({
    url: '/email/folder/add',
    method: 'post',
    data: data
  })
}