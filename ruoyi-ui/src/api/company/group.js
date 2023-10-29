import request from "@/utils/request";

// 列表
export function packetList() {
    return request({
        url: '/customer/packet/list',
        method: 'get'
    })
}

// 添加
export function packetAdd(data) {
    return request({
        url: '/customer/packet/add',
        method: 'post',
        data
    })
}

// 编辑
export function packetEdit(data) {
    return request({
        url: '/customer/packet/edit',
        method: 'post',
        data
    })
}

// 删除
export function packetDelete(data) {
    return request({
        url: '/customer/packet/delete',
        method: 'post',
        data
    })
}
