import {getSettings, settingsEdit} from "@/api/company/common";

const state = {
    settings: {
        mandatorySubgroupFlag: null, //子分组必选 0.未选 1.已选
        advanceFlag: null, //提前标识 0.关闭 1.启动
        advanceDays: null, //提前几天
        accountDisabledFlag: null, //排除禁用账号 0.未选 1.已选
        claimLimitFlag: null, //领取限制 1.限制 2.不限制
        claimLimitDays: null, //限制几天内，原跟进人不能领取同一个客户
        opportunityViewRule: null, // 商机查看规则1.领取公海客户后，不能查看客户历史商机2.领取公海客户后，可查看客户历史商机
        showPublicLeadsFlag: null, //是否显示公海联系人 0.否 1.是
        followupTime: null, // 写跟进时间1.提交跟进记录时默认提交时间，不允许修改2.提交跟进记录时默认提交时间，可手动修改为历史时间
    }
}

const mutations = {
    SET_SETTINGS: (state, data) => {
        state.settings = data
    },
}

const actions = {
    async GetCompanyCustomerSettings({commit}) {
        const res = await getSettings()
        if (res.code === 200) {
            commit('SET_SETTINGS', res.data)
        }
    },
    EditCompanyCustomerSettings({commit, dispatch}, payload) {
        return new Promise(async resolve => {
            const res = await settingsEdit(payload)
            if (res.code === 200) {
                resolve(true)
                await dispatch('GetCompanyCustomerSettings')
            }
            resolve(false)
        })

    }
}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}
