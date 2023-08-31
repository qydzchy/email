import request from '@/utils/request'

// 查询邮箱任务列表
export function listPullHeader(query) {
  return request({
    url: '/email/info/list/pull/header',
    method: 'get',
    params: query
  })
}

// 上传附件
export function uploadAttachments(data) {
  return request({
    url: '/email/info/upload/attachments',
    method: 'post',
    data: data
  })
}

