import { otherConfigInfo } from '@/api/email/otherConfig'

const state = {
    otherSetting: {
        id: '',
        delayedMailDelivery: null,
        localRealTimeTime: null,
        emailRemindersFlag: null,
        emailTranslationFunctionFlag: null,
        pendingClose: null,
        automationPending: null,
        abnormalMailboxDetection: null,
    }
}

const mutations = {
    SET_SETTINGS: (state, data) => {
        state.otherSetting = data
    },
}

const actions = {
    async GetOtherInfo({ commit }) {
        const res = await otherConfigInfo()
        if (res.code === 200) {
            commit('SET_SETTINGS', res.data)
        }
    },
}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}
