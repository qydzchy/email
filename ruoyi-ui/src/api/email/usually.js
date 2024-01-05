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
export function editUsuallyInfo(data) {
    return request({
        url: '/email/general/edit',
        method: 'post',
        data
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
export function editSingleEmailSetting(data) {
    return request({
        url: '/email/setting/edit',
        method: 'post',
        data
    })
}
// 签名
export function getSignatureList() {
    return request({
        url: '/email/signature/list',
        method: 'get',
    })
}
// 新增签名
export function addSignature(data) {
    return request({
        url: '/email/signature/add',
        method: 'post',
        data
    })
}
// 修改签名
export function editSignature(data) {
    return request({
        url: '/email/signature/edit',
        method: 'post',
        data
    })
}
// 删除标签
export function deleteSignature(data) {
    return request({
        url: '/email/signature/delete',
        method: 'post',
        data
    })
}