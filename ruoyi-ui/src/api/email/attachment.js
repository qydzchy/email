import request from '@/utils/request'

// 上传附件
export function uploadAttachments(data) {
  return request({
    url: '/email/attachment/upload',
    method: 'post',
    data: data
  })
}

// 附件列表
export function listAttachment(query) {
  return request({
    url: '/email/attachment/list',
    method: 'get',
    params: query
  })
}

// 重命名附件名称
export function renameAttachment(data) {
  return request({
    url: '/email/attachment/rename',
    method: 'post',
    data: data
  })
}

// 删除附件
export function deleteAttachment(data) {
  return request({
    url: '/email/attachment/delete',
    method: 'post',
    data: data
  })
}
