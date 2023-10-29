import request from "@/utils/request";

export function stageList(data) {
    return request({
        url: '/customer/stage/list',
        method: 'get',
    })
}

export function stageAdd(data) {
    return request({
        url: '/customer/stage/add',
        method: 'post',
        data
    })
}

export function stageEdit(data) {
    return request({
        url: '/customer/stage/edit',
        method: 'post',
        data
    })
}

export function stageDelete(data) {
    return request({
        url: '/customer/stage/delete',
        method: 'post',
        data
    })
}

