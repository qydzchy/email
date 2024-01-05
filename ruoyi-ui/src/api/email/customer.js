import request from '@/utils/request'

// 客户邮件列表
export function getCustomerEmailList(params) {
    return request({
        url: '/email/customer/email/list',
        method: 'get',
        params
    })
}