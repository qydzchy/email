import request from '@/utils/request'

// 查询邮箱任务列表
export function listTask(query) {
  return request({
    url: '/email/task/list',
    method: 'get',
    params: query
  })
}

// 新增邮箱任务
export function addTask(data) {
  return request({
    url: '/email/task/add',
    method: 'post',
    data: data
  })
}

// 邮箱编辑
export function editTask(data) {
  return request({
    url: '/email/task/edit',
    method: 'post',
    data: data
  })
}

// 邮箱测试
export function testTask(id) {
  return request({
    url: '/email/task/test/' + id,
    method: 'get'
  })
}

// 邮箱解绑
export function unbindTask(data) {
  return request({
    url: '/email/task/unbind',
    method: 'post',
    params: data
  })
}

// 查询邮箱邮件列表
export function headerList(query) {
  return request({
    url: '/mailbox/task/header/list',
    method: 'get',
    params: query
  })
}

// 查询邮箱邮件详情
export function headerDetail(headerId) {
  return request({
    url: '/mailbox/task/header/detail/' + headerId,
    method: 'get'
  })
}

// 发送邮件
export function sendMail(data) {
  return request({
    url: '/mailbox/task/mail/send',
    method: 'post',
    data: data
  })
}

// 首页邮箱列表（拉取）
export function listTaskPull(query) {
  return request({
    url: '/email/task/pull/list',
    method: 'get',
    params: query
  })
}

