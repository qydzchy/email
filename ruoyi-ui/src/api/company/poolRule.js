import request from "@/utils/request";

/** 规则 **/

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

/** 白名单 **/

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

export function whiteListDelete(data) {
    return request({
        url: '/customer/public/leads/white/list/delete',
        method: 'post',
        data
    })
}

/** 领取上限 **/

export function claimLimitList() {
    return request({
        url: '/customer/public/leads/claim/limit/list',
        method: 'get'
    })
}

export function claimLimitAdd(data) {
    return request({
        url: '/customer/public/leads/claim/limit/add',
        method: 'post',
        data
    })
}

export function claimLimitEdit(data) {
    return request({
        url: '/customer/public/leads/claim/limit/edit',
        method: 'post',
        data
    })
}

export function claimLimitDelete(data) {
    return request({
        url: '/customer/public/leads/claim/limit/delete',
        method: 'post',
        data
    })
}

/** 分组 **/

export function groupsList() {
    return request({
        url: '/customer/public/leads/groups/list',
        method: 'get',
    })
}

export function groupsAdd(data) {
    return request({
        url: '/customer/public/leads/groups/add',
        method: 'post',
        data
    })
}

export function groupsEdit(data) {
    return request({
        url: '/customer/public/leads/groups/edit',
        method: 'post',
        data
    })
}

export function groupsDelete(data) {
    return request({
        url: '/customer/public/leads/groups/delete',
        method: 'post',
        data
    })
}

/** 原因 **/

export function reasonList() {
    return request({
        url: '/customer/public/leads/reason/list',
        method: 'get',
    })
}

export function reasonAdd(data) {
    return request({
        url: '/customer/public/leads/reason/add',
        method: 'post',
        data
    })
}

export function reasonEdit(data) {
    return request({
        url: '/customer/public/leads/reason/edit',
        method: 'post',
        data
    })
}

export function reasonDelete(data) {
    return request({
        url: '/customer/public/leads/reason/delete',
        method: 'post',
        data
    })
}
