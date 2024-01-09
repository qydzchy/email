import request from '@/utils/request'

// 客户邮件列表
export function getCustomerEmailList(params) {
    return request({
        url: '/email/customer/email/list',
        method: 'get',
        params
    })
}

// 客户邮件详情
export function getCustomerEmailInfo(params) {
    return request({
        url: '/email/info/detail',
        method: 'get',
        params
    })
}

// 客户邮件-搜索列表
export function getCustomerSearchList(query) {
    return request({
        url: '/email/customer/search/list',
        method: 'get',
        params: query
    })
}