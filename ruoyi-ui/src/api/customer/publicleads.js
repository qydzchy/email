import request from "@/utils/request";

//  私海列表
export function getPrivateLeadsList(data) {
    return request({
        url: '/customer/customer/privateleads/list',
        method: 'post',
        data
    })
}

// 公海列表
export function getPublicLeadsList(data) {
    return request({
        url: '/customer/customer/publicleads/list',
        method: 'post',
        data
    })
}

// 私海列表菜单选项
export function getPrivateSegmentMenu(params) {
    return request({
        url: '/customer/customer/segment/list',
        method: 'get',
        params
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
export function searchFollowerCustomer(params) {
    return request({
        url: '/customer/customer/follow/up/personnel/list',
        method: 'get',
        params
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
export function searchGroupsCustomer() {
    return request({
        url: '/customer/customer/publicleads/groups/list',
        method: 'get'
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

// 客户详情
export function getCustomerDetail(params) {
    return request({
        url: '/customer/customer/detail',
        method: 'get',
        params
    })
}

// 获取团队成员
export function getTeamMembers() {
    return request({
        url: '/customer/customer/get/team/members',
        method: 'get',
    })
}

// 获取所有用户
export function getAllUser() {
    return request({
        url: '/customer/customer/get/all/users',
        method: 'get'
    })
}

// 分组测试接口
export function getSetPacketList() {
    return request({
        url: '/customer/customer/settings/packet/list',
        method: 'get'
    })
}
