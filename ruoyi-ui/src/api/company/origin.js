import request from "@/utils/request";

export function getOriginList(data) {
    return request({
        url: '/customer/source/list',
        method: 'get',
    })
}

export function originAdd(data) {
    return request({
        url: '/customer/source/add',
        method: 'post',
        data
    })
}

export function originEdit(data) {
    return request({
        url: '/customer/source/edit',
        method: 'post',
        data
    })
}

export function originDelete(data) {
    return request({
        url: '/customer/source/delete',
        method: 'post',
        data
    })
}
