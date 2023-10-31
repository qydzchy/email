import request from "@/utils/request";

/** 客户设置 **/

export function getSettings() {
    return request({
        url: '/customer/settings/get',
        method: 'get'
    })
}

export function settingsEdit(data) {
    return request({
        url: '/customer/settings/edit',
        method: 'post',
        data
    })
}
