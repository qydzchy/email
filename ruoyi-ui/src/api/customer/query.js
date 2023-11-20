import request from "@/utils/request";

export function getQueryFieldColumn() {
    return request({
        url: '/customer/customer/duplication/filter/column/list',
        method: 'get'
    })
}

export function searchDuplicationQuery(params) {
    return request({
        url: '/customer/customer/duplication/list',
        method: 'get',
        params
    })
}
