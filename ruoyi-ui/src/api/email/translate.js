import request from '@/utils/request'

// 查询模板类型列表
export function translate(query) {
    return request({
        url: '/email/info/translate',
        method: 'get',
        params: query
    })
}