import request from "@/utils/request";

//  私海列表
export function getPrivateLeadsList(params) {
    return request({
        url: '/customer/customer/privateleads/list',
        method: 'get',
        params
    })
}

// 公海列表
export function getPublicLeadsList(params) {
    return request({
        url: '/customer/customer/publicleads/list',
        method: 'get',
        params
    })
}

// 私海列表菜单选项
export function getPrivateSegmentMenu() {
    return request({
        url: '/customer/customer/segment/list',
        method: 'get',
    })
}

export function addCustomer(data) {
    return request({
        url: '/customer/customer/add',
        method: 'post',
        data
    })
}

export function editCustomer(data) {
    return request({
        url: '/customer/customer/edit',
        method: 'post',
        data
    })
}

// 移入客户至分组
export function movePacketCustomer(data) {
    return request({
        url: '/customer/customer/move/customer/to/packet',
        method: 'post',
        data
    })
}

// 转移给
export function transferredToCustomer(data) {
    return request({
        url: '/customer/customer/transferred/to',
        method: 'post',
        data
    })
}

// 共享给
export function shareToCustomer(data) {
    return request({
        url: '/customer/customer/share/to',
        method: 'post',
        data
    })
}

// 取消跟进
export function followCancelCustomer(data) {
    return request({
        url: '/customer/customer/cancel/follow/up',
        method: 'post',
        data
    })
}

// 移入公海
export function moveToPublicLeads(data) {
    return request({
        url: '/customer/customer/move/to/publicleads',
        method: 'post',
        data
    })
}

// 重新分配
export function reassignToCustomer(data) {
    return request({
        url: '/customer/customer/reassign/to',
        method: 'post',
        data
    })
}

// 查询客户跟进人
export function searchFollowerCustomer(data) {
    return request({
        url: '/customer/customer/follow/up/personnel/list',
        method: 'post',
        data
    })
}

// 取消跟进人
export function unassignFollowerCustomer(data) {
    return request({
        url: '/customer/customer/unassign/follow/up',
        method: 'post',
        data
    })
}

// 修改重点关注
export function editFocusFlagCustomer(data) {
    return request({
        url: '/customer/customer/edit/focusFlag',
        method: 'post',
        data
    })
}

// 查询公海分组
export function searchGroupsCustomer(data) {
    return request({
        url: '/customer/customer/publicleads/groups/list',
        method: 'post',
        data
    })
}

// 变更公海分组
export function changeGroupsCustomer(data) {
    return request({
        url: '/customer/customer/change/publicleads/groups',
        method: 'post',
        data
    })
}

// 移入私海
export function moveToPrivateLeads(data) {
    return request({
        url: '/customer/customer/move/to/privateleads',
        method: 'post',
        data
    })
}
