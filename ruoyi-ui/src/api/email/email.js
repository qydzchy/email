import request from '@/utils/request'

// 查询邮箱任务列表
export function list(query) {
  return request({
    url: '/email/info/list',
    method: 'get',
    params: query
  })
}

// 保存发送邮件
export function saveSendEmail(data) {
  return request({
    url: '/email/info/save',
    method: 'post',
    data: data
  })
}


// 发送邮件
export function sendEmail(data) {
  return request({
    url: '/email/info/send',
    method: 'post',
    data: data
  })
}

// 固定邮件
export function fixedEmail(data) {
  return request({
    url: '/email/info/fixed',
    method: 'post',
    data: data
  })
}


// 快速回复
export function quickReply(data) {
  return request({
    url: '/email/info/quick/reply',
    method: 'post',
    data: data
  })
}

// 更新邮件是否为已读邮件
export function readEmail(data) {
  return request({
    url: '/email/info/read',
    method: 'post',
    data: data
  })
}

// 更新邮件是否为垃圾邮件
export function spamEmail(data) {
  return request({
    url: '/email/info/spam',
    method: 'post',
    data: data
  })
}

// 移动文件夹
export function moveFolder(data) {
  return request({
    url: '/email/info/move/folder',
    method: 'post',
    data: data
  })
}

// 删除邮件
export function deleteEmail(data) {
  return request({
    url: '/email/info/delete',
    method: 'post',
    data: data
  })
}

// 导出邮件
export function exportEmail(data) {
  return request({
    url: '/email/info/export/'+ data,
    method: 'get',
    responseType: 'blob'
  })
}
