import request from "@/utils/request";

export function getBlackList(data) {
    return request({
        url: '/customer/black/list/records/list',
        method: 'get',
    })
}

export function blackListAdd(data) {
    return request({
        url: '/customer/black/list/records/add',
        method: 'post',
        data
    })
}

export function blackListDelete(data) {
    return request({
        url: '/customer/black/list/records/batch/delete',
        method: 'post',
        data
    })
}
