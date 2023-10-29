import request from "@/utils/request";

// --- 规则 --- //
export function rulesList(data) {
    return request({
        url: '/customer/public/leads/rules/list',
        method: 'get',
    })
}

export function rulesAdd(data) {
    return request({
        url: '/customer/public/leads/rules/add',
        method: 'post',
        data
    })
}

export function rulesEdit(data) {
    return request({
        url: '/customer/public/leads/rules/edit',
        method: 'post',
        data
    })
}

export function rulesDelete(data) {
    return request({
        url: '/customer/public/leads/rules/delete',
        method: 'post',
        data
    })
}

// --- 白名单 --- //

export function whiteList(data) {
    return request({
        url: '/customer/public/leads/white/list/list',
        method: 'get',
    })
}

export function whiteListAdd(data) {
    return request({
        url: '/customer/public/leads/white/list/add',
        method: 'post',
        data
    })
}

export function whiteListEdit(data) {
    return request({
        url: '/customer/public/leads/white/list/edit',
        method: 'post',
        data
    })
}

export function whiteListDelete(data) {
    return request({
        url: '/customer/public/leads/white/list/delete',
        method: 'post',
        data
    })
}
