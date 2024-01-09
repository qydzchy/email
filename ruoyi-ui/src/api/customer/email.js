import request from '@/utils/request'

// 通用列表
export function generalList(query) {
  return request({
    url: '/email/customer/general/list',
    method: 'get',
    params: query
  })
}

// 公海分组列表
export function publicleadsGroupsList(query) {
  return request({
    url: '/email/customer/publicleads/groups/list',
    method: 'get',
    params: query
  })
}

// 客户分组列表
export function packetList(query) {
  return request({
    url: '/email/customer/packet/list',
    method: 'get',
    params: query
  })
}

// 客户来源列表
export function sourceList(query) {
  return request({
    url: '/email/customer/source/list',
    method: 'get',
    params: query
  })
}

// 客户状态列表
export function stageList(query) {
  return request({
    url: '/email/customer/stage/list',
    method: 'get',
    params: query
  })
}

// 客户星级列表
export function ratingList(query) {
  return request({
    url: '/email/customer/rating/list',
    method: 'get',
    params: query
  })
}

// 客户活跃度列表
export function activityList(query) {
  return request({
    url: '/email/customer/activity/list',
    method: 'get',
    params: query
  })
}

// 客户邮件列表
export function customerEmailList(query) {
  return request({
    url: '/email/customer/email/list',
    method: 'get',
    params: query
  })
}

// 往来邮件
export function getCustomerEmailList(params) {
  return request({
    url: '/email/customer/dealing/email/list',
    method: 'get',
    params
  })
}

