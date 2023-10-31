import request from "@/utils/request";

export function getDuplicateList(data) {
    return request({
        url: '/customer/duplication/settings/list',
        method: 'get',
    })
}

export function duplicateEdit(data) {
    return request({
        url: '/customer/duplication/settings/edit/activeFlag',
        method: 'post',
        data
    })
}
