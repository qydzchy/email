import request from "@/utils/request";

// 客户跟进规则
export function getFollowRulesList(data) {
    return request({
        url: '/customer/follow/up/rules/list',
        method: 'get',
    })
}

export function followRulesEdit(data) {
    return request({
        url: '/customer/follow/up/rules/edit/activeFlag',
        method: 'post',
        data
    })
}
