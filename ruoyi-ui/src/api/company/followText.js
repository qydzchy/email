import request from "@/utils/request";

/** 写跟进模板 **/
export function followTextTemplateList(data) {
    return request({
        url: '/customer/follow/up/templates/list',
        method: 'get',
    })
}

export function followTextTemplateAdd(data) {
    return request({
        url: '/customer/follow/up/templates/add',
        method: 'post',
        data
    })
}

export function followTextTemplateEdit(data) {
    return request({
        url: '/customer/follow/up/templates/edit',
        method: 'post',
        data
    })
}

export function followTextTemplateDelete(data) {
    return request({
        url: '/customer/follow/up/templates/delete',
        method: 'post',
        data
    })
}

/** 写跟进快捷文本 **/
export function followTextQuickList(data) {
    return request({
        url: '/customer/follow/up/quick/text/list',
        method: 'get',
    })
}

export function followTextQuickAdd(data) {
    return request({
        url: '/customer/follow/up/quick/text/add',
        method: 'post',
        data
    })
}

export function followTextQuickEdit(data) {
    return request({
        url: '/customer/follow/up/quick/text/edit',
        method: 'post',
        data
    })
}

export function followTextQuickDelete(data) {
    return request({
        url: '/customer/follow/up/quick/text/delete',
        method: 'post',
        data
    })
}
