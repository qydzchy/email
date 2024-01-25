import { singleEmailSetting } from '@/api/email/usually'

const state = {
    emailList: []
    // [
    //     {
    //         id: null,
    //         taskId: null,
    //         taskName: null,
    //         letterNickname: null,
    //         defaultSignatureId: null,
    //         replySignatureId: null,
    //         defaultCcFlag: null,
    //         defaultCc: null,
    //         defaultBccFlag: null,
    //         defaultBcc: null,
    //         traceFlag: null,
    //         returnReceiptFlag: null
    //     }
    // ]
}

const mutations = {
    SET_EMAIL_LIST: (state, data) => {
        state.emailList = data
    },
}

const actions = {
    async GetEmailList({ commit }) {
        const res = await singleEmailSetting()
        if (res.code === 200) {
            commit('SET_EMAIL_LIST', res.data)
        }
    },
}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}
