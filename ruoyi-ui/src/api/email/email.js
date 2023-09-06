import request from '@/utils/request'

// 查询邮箱任务列表
export function listPullHeader(query) {
  return request({
    url: '/email/info/list/pull/header',
    method: 'get',
    params: query
  })
}

export function listSendHeader(query) {
  return request({
    url: '/email/info/list/send/header',
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
