import request from '@/utils/request'

// 查询邮箱列表
export function getEmailTaskList() {
    return request({
        url: '/email/general/default/task/list',
        method: 'get'
    })
}
// 常规数据
export function getUsuallyInfo() {
    return request({
        url: '/email/general/get/info',
        method: 'get'
    })
}

// 常规数据修改
export function editUsuallyInfo() {
    return request({
        url: '/email/general/edit',
        method: 'get'
    })
}

// 单个邮箱
export function singleEmailSetting() {
    return request({
        url: '/email/setting/list',
        method: 'get'
    })
}
// 编辑单个邮箱
export function editSingleEmailSetting() {
    return request({
        url: '/email/setting/edit',
        method: 'get'
    })
}
// 签名
export function getSignatureList() {
    return request({
        url: '/email/signature/list',
        method: 'get'
    })
}
// 新增签名
export function addSignature() {
    return request({
        url: '/email/signature/add',
        method: 'post'
    })
}
// 修改签名
export function editSignature() {
    return request({
        url: '/email/signature/edit',
        method: 'post'
    })
}
// 删除标签
export function deleteSignature() {
    return request({
        url: '/email/signature/delete',
        method: 'post'
    })
}