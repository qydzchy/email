import request from "@/utils/request";

export function getCustomerTagList(params) {
    return request({
        url: '/customer/tag/list',
        method: 'get',
        params
    })
}

// 个人标签 //
export function addPersonalTag(data) {
    return request({
        url: '/customer/tag/personal/add',
        method: 'post',
        data
    })
}

export function editPersonalTag(data) {
    return request({
        url: '/customer/tag/personal/edit',
        method: 'post',
        data
    })
}

export function deletePersonalTag(data) {
    return request({
        url: '/customer/tag/personal/delete',
        method: 'post',
        data
    })
}

// 公司标签 //
export function addCompanyTag(data) {
    return request({
        url: '/customer/tag/company/add',
        method: 'post',
        data
    })
}

export function editCompanyTag(data) {
    return request({
        url: '/customer/tag/company/edit',
        method: 'post',
        data
    })
}

export function deleteCompanyTag(data) {
    return request({
        url: '/customer/tag/company/delete',
        method: 'post',
        data
    })
}
