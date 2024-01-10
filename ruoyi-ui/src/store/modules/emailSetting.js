import { getUsuallyInfo } from '@/api/email/usually'

const state = {
    usuallySetting: {
        id: '',
        defaultTaskId: '',
        maxPerPage: null,
        emailReadingModeFlag: null,
        moveDeleteReport: null,
        language: null,
        defaultFont: '',
        fontSize: '',
        fontColor: '',
        groupBoxView: null,
        massEmailDisplay: null,
        remind: [],
        signatureId: '',
        writeLetter: '',
        pasteFormat: null,
        autoResponseFlag: null,
        startTime: '',
        lastDay: '',
        lastDayFlag: null,
        reContent: '',
    }
}

const mutations = {
    SET_SETTINGS: (state, data) => {
        state.usuallySetting = data
    },
}

const actions = {
    async GetUsuallyInfo({ commit }) {
        const res = await getUsuallyInfo()
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
