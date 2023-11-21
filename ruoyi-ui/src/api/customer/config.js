import request from "@/utils/request";


/*
*
* 移入公海规则
*
*/

export function getPoolRuleList() {
    return request({
        url: '/customer/customer/settings/publicleads/rules/list',
        method: 'get'
    })
}

/*
*
* 客户分组设置
*
*/

export function getSettingsPacket() {
    return request({
        url: '/customer/customer/settings/packet/list',
        method: 'get'
    })
}

/*
*
* 客户标签管理
*
*/
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

export function setCompanyTag(data) {
    return request({
        url: '/customer/tag/set/as/company/tag',
        method: 'post',
        data
    })
}

/*
*
* 客户导入文档
*
*/

export function getImportDocumentList() {
    return request({
        url: '/customer/customer/document/list',
        method: 'get'
    })
}

// 上传文档
export function uploadMultipleDocument(data) {
    return request({
        url: '/customer/customer/document/uploadMultiple',
        method: 'post',
        data
    })
}

// 下载文档
export function downloadImportDocument(id) {
    return request({
        url: '/customer/customer/document/download/' + id,
        method: 'get'
    })
}

export function deleteImportDocument(data) {
    return request({
        url: '/customer/customer/document/delete',
        method: 'post',
        data
    })
}
