import request from '@/utils/request'

// 通用列表
export function generalList(query) {
  return request({
    url: '/customer/email/general/list',
    method: 'get',
    params: query
  })
}

// 公海分组列表
export function publicleadsGroupsList(query) {
  return request({
    url: '/customer/email/publicleads/groups/list',
    method: 'get',
    params: query
  })
}

// 客户分组列表
export function packetList(query) {
  return request({
    url: '/customer/email/packet/list',
    method: 'get',
    params: query
  })
}

// 客户来源列表
export function sourceList(query) {
  return request({
    url: '/customer/email/source/list',
    method: 'get',
    params: query
  })
}

// 客户状态列表
export function stageList(query) {
  return request({
    url: '/customer/email/stage/list',
    method: 'get',
    params: query
  })
}

// 客户星级列表
export function ratingList(query) {
  return request({
    url: '/customer/email/rating/list',
    method: 'get',
    params: query
  })
}

// 客户活跃度列表
export function activityList(query) {
  return request({
    url: '/customer/email/activity/list',
    method: 'get',
    params: query
  })
}
