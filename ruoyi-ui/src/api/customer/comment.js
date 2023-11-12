import request from "@/utils/request";

export function addRecordsComment(data) {
  return request({
    url: '/customer/customer/follow/up/records/comment/add',
    method: 'post',
    data
  })
}

export function editRecordsComment(data) {
  return request({
    url: '/customer/customer/follow/up/records/comment/edit',
    method: 'post',
    data
  })
}

export function deleteRecordsComment(data) {
  return request({
    url: '/customer/customer/follow/up/records/comment/delete',
    method: 'post',
    data
  })
}
