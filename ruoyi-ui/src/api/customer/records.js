import request from "@/utils/request";

export function getFollowUpRecordsList(params) {
  return request({
    url: '/customer/customer/follow/up/records/list',
    method: 'get',
    params
  })
}

export function addFollowUpRecords(data) {
  return request({
    url: '/customer/customer/follow/up/records/add',
    method: 'post',
    data
  })
}

export function editFollowUpRecords(data) {
  return request({
    url: '/customer/customer/follow/up/records/edit',
    method: 'post',
    data
  })
}

export function deleteFollowUpRecords(data) {
  return request({
    url: '/customer/customer/follow/up/records/delete',
    method: 'post',
    data
  })
}
