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
