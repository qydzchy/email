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

// 标记邮件为待处理
export function pendingEmail(data) {
  return request({
    url: '/email/info/pending',
    method: 'post',
    data: data
  })
}

// 移动邮件到文件夹
export function moveEmailToFolder(data) {
  return request({
    url: '/email/info/move/email/to/folder',
    method: 'post',
    data: data
  })
}

// 移动邮件到标签
export function moveEmailToLabel(data) {
  return request({
    url: '/email/info/move/email/to/label',
    method: 'post',
    data: data
  })
}

// 删除邮件标签
export function deleteEmailLabel(data) {
  return request({
    url: '/email/info/delete/label',
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

// 更新邮件操作类型
export function updateOperationType(data) {
  return request({
    url: '/email/info/update/operation/type',
    method: 'post',
    data: data
  })
}

// 导出邮件
export function exportEmail(data) {
  return request({
    url: '/email/info/export/' + data,
    method: 'get',
    responseType: 'blob'
  })
}

// 上传邮件文件附件
export function uploadAttachment(data) {
  return request({
    url: '/email/info/upload/attachment',
    method: 'post',
    data: data
  })
}

// 下载邮件附件
export function downloadEmailAttachment(data) {
  return request({
    url: '/email/info/attachment/download/' + data,
    method: 'get',
    responseType: 'blob'
  })
}

// 统计菜单邮件数量
export function countMenu(query) {
  return request({
    url: '/email/info/count/menu',
    method: 'get',
    params: query
  })
}

// 移动邮件到文件夹
export function emailMoveFolder(data) {
  return request({
    url: '/email/info/move/email/to/folder',
    method: 'post',
    data
  })
}

// 往来邮件列表
export function dealingEmailList(query) {
  return request({
    url: '/email/info/dealing/email/list',
    method: 'get',
    params: query
  })
}
