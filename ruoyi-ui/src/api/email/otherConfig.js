import request from '@/utils/request'

// 其他配置信息
export function otherConfigInfo() {
    return request({
        url: '/email/config/get/info',
        method: 'get'
    })
}

// 修改其他配置信息
export function otherConfigEdit(data) {
    return request({
        url: '/email/config/edit',
        method: 'post',
        data
    })
}
