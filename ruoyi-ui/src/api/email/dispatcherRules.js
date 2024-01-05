import request from '@/utils/request'

// 收发规则列表
export function getDispatcherRuleList() {
    return request({
        url: '/email/rule/list',
        method: 'get'
    })
}
// 收发规则详情
export function dispatcherRuleInfo(id) {
    return request({
        url: `/email/rule/${id}`,
        method: 'get'
    })
}
// 新增收发规则
export function addDispatcherRule(data) {
    return request({
        url: '/email/rule/add',
        method: 'post',
        data
    })
}
// 修改收发规则
export function editDispatcherRule(data) {
    return request({
        url: '/email/rule/edit',
        method: 'post',
        data
    })
}
// 删除收发详情
export function deleteDispatcherRuleInfo(data) {
    return request({
        url: '/email/rule/delete',
        method: 'post',
        data
    })
}
// 更新收发规则状态
export function updateDispatcherRuleInfo(data) {
    return request({
        url: '/email/rule/update/status',
        method: 'post',
        data
    })
}

// 分组测试接口
export function getSetPacketList() {
    return request({
        url: '/customer/customer/settings/packet/list',
        method: 'get'
    })
}