import request from "@/utils/request";

export function getScheduleList(params) {
  return request({
    url: '/customer/customer/schedule/list',
    method: 'get',
    params
  })
}

export function addSchedule(data) {
  return request({
    url: '/customer/customer/schedule/add',
    method: 'post',
    data
  })
}

export function editSchedule(data) {
  return request({
    url: '/customer/customer/schedule/edit',
    method: 'post',
    data
  })
}

export function deleteSchedule(data) {
  return request({
    url: '/customer/customer/schedule/delete',
    method: 'post',
    data
  })
}
