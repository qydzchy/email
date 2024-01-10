<template>
    <div class="mail-list-container" :class="{ 'only-mail-list': showHeader }">
        <div class="mm-split mail-three-mode-split">
            <div class="mm-split-horizontal">
                <!-- 列表 -->
                <div class="mm-split-pane mm-split-pane__left" :style="`right: ${showHeader ? '29.1545%' : '64.7959%;'}`">

                    <div class="mail-list-scroll-container mail-list-wrap" v-loading="loading">
                        <div class="header-wrapper" style="">
                            <div class="mail-list-header-tool-wrapper">
                                <div class="mm-tabs mm-tabs__top mm-tabs__align-left mail-list-toggle-tab header-tab">
                                    <div class="mm-tabs-header mm-tabs-header__top">
                                        <div class="mm-tabs-nav-wrap mm-tabs-nav-wrap__top">
                                            <div class="mm-tabs-nav-scroll">
                                                <div class="mm-tabs-nav" role="tablist" style="transform: translateX(0px);">
                                                    <div class="mm-tabs-active-bar__top mm-tabs-active-bar"
                                                        style="width: 96px; transform: translateX(0px);"></div>
                                                    <div class="mm-tabs-item mm-tabs-item__top mm-tabs-item--active"
                                                        id="tab-all" aria-controls="pane-all" role="tab"
                                                        aria-selected="true" tabindex="0" refinfor="true"><span
                                                            class="mm-tooltip"><span
                                                                class="mm-tooltip-trigger">全部</span></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="mm-tabs-content">
                                        <div id="pane-all" aria-hidden="false" aria-labelledby="tab-all" class="mm-tab-pane"
                                            role="tabpanel"></div>
                                        <div id="pane-1" aria-hidden="true" aria-labelledby="tab-1" class="mm-tab-pane"
                                            role="tabpanel" style="display: none;"></div>
                                        <div id="pane-2" aria-hidden="true" aria-labelledby="tab-2" class="mm-tab-pane"
                                            role="tabpanel" style="display: none;"></div>
                                        <div id="pane-3" aria-hidden="true" aria-labelledby="tab-3" class="mm-tab-pane"
                                            role="tabpanel" style="display: none;"></div>
                                        <div id="pane-0" aria-hidden="true" aria-labelledby="tab-0" class="mm-tab-pane"
                                            role="tabpanel" style="display: none;"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="mail-list-scroller">
                            <div v-if="list && list.length" class="mail-list-scroll-container">
                                <ul class="grouped-list-container bordered mail-grouped-list">
                                    <li v-for="(data, index) in list" :key="index" class="grouped-list-item">
                                        <h2 class="grouped-list-item-title"><span class="time-title">{{
                                            Object.keys(data)[0] }}</span>
                                            <span class="mail-count">
                                                ( {{ data[Object.keys(data)[0]].length }} 封)
                                            </span>
                                        </h2>
                                        <ul class="plain-list-container bordered">
                                            <li class="plain-list-item" v-for="(email, index) in data[Object.keys(data)[0]]"
                                                :class="{ 'active': email.id === activeEmailId }" :key="index + '_email'"
                                                @click="() => {
                                                    onShowLabel(false);
                                                    activeEmailId = email.id;
                                                    getInfo(email.id)
                                                }">
                                                <div class="right-click-menu-handler">
                                                    <div class="mail-item-container mail-list-item-wrapper" draggable="true"
                                                        mail-info-icon-map="[object Object]">
                                                        <div class="mt-[-2px]" style="display: none;">
                                                            <div class="flex-shrink-0 flex items-center h-24px">
                                                                <div class="mx-8px w-8px h-8px rounded-1 read-flag"></div>
                                                            </div>
                                                        </div>
                                                        <div class="flex-grow flex-shrink-0">
                                                            <div class="mail-list-item-content">
                                                                <div @click.stop style="min-height:40px"><label
                                                                        v-show="showHeader"
                                                                        class="mm-checkbox mail-list-item-checkbox pr-0">
                                                                        <input true-value="true" type="checkbox"><span
                                                                            class="mm-checkbox-input"></span></label>
                                                                </div>
                                                                <div>
                                                                    <div class="flex-shrink-0 flex items-center h-24px">
                                                                        <div class="mx-8px w-8px h-8px rounded-1 read-flag">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="addr-info-container customer-mail-layout">
                                                                    <div :class="{ 'unread': !email.readFlag }"
                                                                        class="mail-list-item-addr-contact-info"><span
                                                                            class="email-addr-box"><span
                                                                                class="mm-tooltip all-type-avatar-wrapper client-self small mail-tag-avatar">
                                                                                <span class="mm-tooltip-trigger">
                                                                                    <i
                                                                                        class="m-icon avatar-icon icon-new-customer-avatar"></i>
                                                                                </span>
                                                                            </span>
                                                                            <i class="m-icon contact-icon"
                                                                                :class="email.type === 2 ? 'icon-mail-sent' : 'icon-mail-receive'"
                                                                                :data-tips="generateSendType(email.type)"></i>
                                                                            <span :title="email.title"
                                                                                class="email-nickname ellipsis customer-mail-name">
                                                                                {{ email.title }}
                                                                            </span>
                                                                        </span>
                                                                        <div
                                                                            class="flex flex-nowrap flex-shrink-0 justify-end align-center mail-info-icons">
                                                                            <span class="mail-info-icon-item">
                                                                                <i v-show="email.type === 2"
                                                                                    class="m-icon icon-checked-circle-line"
                                                                                    data-tips="发送成功"
                                                                                    style="color: rgb(40, 183, 121);"></i>
                                                                            </span>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="mail-list-item-overview-content">
                                                                    <div class="con-wrap">
                                                                        <span class="subject-summary-wrap">
                                                                            <span class="summary summary-content ellipsis">
                                                                                <span :title="email.extractTextFromContent"
                                                                                    class="ellipsis">
                                                                                    {{ email.extractTextFromContent }}
                                                                                </span>
                                                                            </span>
                                                                        </span>
                                                                    </div>
                                                                </div>
                                                                <div></div>
                                                                <div class="right-operations-container">
                                                                    <div class="mail-list-item-list-right">
                                                                        <span class="time ellipsis">
                                                                            {{ email.sendTime }}
                                                                        </span>
                                                                        <div class="pending" v-show="showHeader">
                                                                            <div class="mm-popover">
                                                                                <div>
                                                                                    <span
                                                                                        class="okki-icon-wrap pending-icon">
                                                                                        &ZeroWidthSpace;
                                                                                        <svg xmlns="http://www.w3.org/2000/svg"
                                                                                            width="16" height="16"
                                                                                            viewBox="0 0 24 24"
                                                                                            aria-hidden="true"
                                                                                            class="okki-svg-icon"
                                                                                            fill="currentColor">
                                                                                            <path
                                                                                                d="M12 6a1 1 0 011 1v4.423l2.964 1.711a1 1 0 11-1 1.732l-3.447-1.99A1 1 0 0111 11.98V7a1 1 0 011-1z">
                                                                                            </path>
                                                                                            <path fill-rule="evenodd"
                                                                                                clip-rule="evenodd"
                                                                                                d="M22 12c0 5.523-4.477 10-10 10S2 17.523 2 12 6.477 2 12 2s10 4.477 10 10zm-2 0a8 8 0 11-16 0 8 8 0 0116 0z">
                                                                                            </path>
                                                                                        </svg>
                                                                                    </span>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="mail-pin-wrapper">
                                                                            <span class="okki-icon-wrap mail-pin-icon">
                                                                                &ZeroWidthSpace;
                                                                                <el-tooltip content="固定" placement="bottom">
                                                                                    <svg xmlns="http://www.w3.org/2000/svg"
                                                                                        width="18" height="18"
                                                                                        viewBox="0 0 25 25"
                                                                                        aria-hidden="true"
                                                                                        class="okki-svg-icon"
                                                                                        fill="currentColor">
                                                                                        <path fill-rule="evenodd"
                                                                                            clip-rule="evenodd"
                                                                                            d="M16.606 3.292a1 1 0 00-1.414 0 4.508 4.508 0 00-1.314 3.428l-3.094 2.253c-2.258-.538-4.729.057-6.484 1.812a1 1 0 000 1.415l3.545 3.544-2.828 2.829a1 1 0 101.414 1.414l2.828-2.828 3.536 3.535a1 1 0 001.414 0c1.774-1.774 2.363-4.279 1.794-6.557l2.198-3.025a4.51 4.51 0 003.502-1.31 1 1 0 000-1.414l-5.097-5.096zm-6.634 11.75L13.43 18.5a4.93 4.93 0 00.529-4.24 1 1 0 01.14-.902l2.837-3.904a1 1 0 011.02-.39 2.57 2.57 0 001.479-.115L16.046 5.56c-.177.453-.22.95-.125 1.431a1 1 0 01-.392 1.001l-3.957 2.882a1 1 0 01-.892.144 4.929 4.929 0 00-4.185.548l3.466 3.466.006.005a.226.226 0 01.005.006z">
                                                                                        </path>
                                                                                    </svg>
                                                                                </el-tooltip>

                                                                            </span>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="attachment-list-container"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </li>

                                </ul>

                            </div>
                            <div v-else-if="list && !list.length && !loading" class="mm-empty mail-normal-list-empty">
                                <div class="empty-custom-render">
                                    <div class="empty-custom-image"><img
                                            src="data:image/png;base64,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"
                                            width="190" height="133"></div>
                                    <div class="empty-custom-desc"><span>暂无数据</span></div>
                                </div>
                            </div>
                        </div>
                        <div class="mail-paging list-page-wrap" v-if="!showHeader && totalPages">
                            <span class="total-count ellipsis">共 {{ total }} 封</span>
                            <div class="quick-jumper">
                                <input :max="totalPages" class="mail-paging-input" type="number" :value.sync="currentPage"
                                    @blur="handlePageInputBlur" @input="handlePageInputChange">
                                <span class="mail-paging-slash"> / </span>
                                <span class="page-unit">{{ totalPages }}  页</span>
                            </div>
                            <div :class="['mail-paging-btn', 'left-btn', { 'disabled': currentPage === 1 }]"
                                @click="prevPage">
                                <span class="okki-icon-wrap m-icon">​<svg xmlns="http://www.w3.org/2000/svg" width="22"
                                        height="22" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
                                        fill="currentColor">
                                        <path fill-rule="evenodd" clip-rule="evenodd"
                                            d="M15.707 5.293a1 1 0 010 1.414L10.414 12l5.293 5.293a1 1 0 01-1.414 1.414l-6-6a1 1 0 010-1.414l6-6a1 1 0 011.414 0z">
                                        </path>
                                    </svg>
                                </span>
                            </div>
                            <div :class="['mail-paging-btn', 'right-btn', { 'disabled': currentPage === totalPages }]"
                                @click="nextPage">
                                <span class="okki-icon-wrap m-icon">​<svg xmlns="http://www.w3.org/2000/svg" width="22"
                                        height="22" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
                                        fill="currentColor">
                                        <path fill-rule="evenodd" clip-rule="evenodd"
                                            d="M8.293 5.293a1 1 0 011.414 0l6 6a1 1 0 010 1.414l-6 6a1 1 0 01-1.414-1.414L13.586 12 8.293 6.707a1 1 0 010-1.414z">
                                        </path>
                                    </svg>
                                </span>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- 分割线 -->
                <div class="mm-split-trigger-wrap" :style="`left: ${showHeader ? '70.8455%' : '35.2041%'}`">
                    <div class="mm-split-trigger mm-split-trigger-vertical">
                        <div class="mm-split-trigger-bar-wrap"><i class="mm-split-trigger-bar"></i><i
                                class="mm-split-trigger-bar"></i><i class="mm-split-trigger-bar"></i><i
                                class="mm-split-trigger-bar"></i><i class="mm-split-trigger-bar"></i><i
                                class="mm-split-trigger-bar"></i><i class="mm-split-trigger-bar"></i><i
                                class="mm-split-trigger-bar"></i></div>
                    </div>
                </div>
                <!-- 侧边 -->
                <div class="mm-split-pane mm-split-pane__right" :style="`left:${showHeader ? '70.8455%' : '35.2041%;'}`">
                    <template v-if="!showHeader">
                        <!-- 快捷邮件 -->
                        <FastWrite :info="fastInfo" @showLabel="onShowLabel" />
                        <!-- 抽屉 -->
                        <div v-if="Boolean(emailReadingModeFlag)" class="mail-side-card slide-fade"
                            :class="isRightPanelExpanded ? 'expanding' : 'collapsing'">
                            <span class="mail-position-toogle" @click="toggleRightPanel">
                                <i class="m-icon icon-left-small"></i>
                            </span>
                            <!-- 内容 -->
                            <PrviateListRow :row="{ id: selectedTaskId }" :indexOpt="indexOpt" />
                        </div>
                    </template>

                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { mapState } from "vuex";
import FastWrite from './FastWrite.vue'
import PrviateListRow from '@/components/CustomerTableRow/PrviateListRow'
import { getCustomerEmailList, getCustomerEmailInfo } from '@/api/email/customer'
// 客户列表选项
import { stageList } from "@/api/company/status";
import { getOriginList } from "@/api/company/origin";
import { reasonList } from "@/api/company/poolRule";
import { getCustomerTagList } from "@/api/customer/config";
import { getPrivateSegmentMenu, getTeamMembers, searchGroupsCustomer, getSetPacketList } from "@/api/customer/publicleads";

export default {
    props: {
        selectedTaskId: {
            type: String | Number,
            default: '',
            required: false
        },
        total: {
            type: Number,
            default: 0,
            required: true
        },
        currentPage: {
            type: Number,
            default: 1,
            required: true
        },
    },
    components: {
        FastWrite,
        PrviateListRow
    },
    data() {
        return {
            loading: false,
            showHeader: true,
            isRightPanelExpanded: true,
            activeEmailId: null,
            list: [],
            customerId: '',
            indexOpt: {
                groupOption: [],
                stageOption: [],
                originOption: [],
                poolGroupOption: [],
                poolReasonOption: [],
                tagOption: [],
                teamMemberOption: [],
            },
            fastInfo: {},
            firstReq: true
        }
    },
    computed: {
        ...mapState({
            pageSize: state => state.emailSetting.usuallySetting?.maxPerPage,
            emailReadingModeFlag: state => state.emailSetting.usuallySetting?.emailReadingModeFlag
        }),
        totalPages() {
            return Math.ceil(this.total / this.pageSize);
        },
    },
    watch: {
        selectedTaskId: {
            handler(newVal) {
                if (newVal) {
                    this.onShowLabel(true)
                    this.customerId = newVal
                    this.$watch('pageSize', (newVal, oldVal) => {
                        if (!newVal && !oldVal) {
                            return
                        }
                        this.getList()
                        this.firstReq = false
                    }, { immediate: true })

                }
            },
            deep: true,
            immediate: true
        },
        currentPage: {
            handler(newVal) {
                if (!this.firstReq) {
                    this.getList()
                }
            }
        }
    },
    mounted() {
        this.init()
    },
    methods: {
        // 列表
        async getList() {
            try {
                this.loading = true
                const res = await getCustomerEmailList({
                    customerId: this.customerId,
                    fixedFlag: true,
                    pageNum: this.currentPage,
                    pageSize: this.pageSize
                }).finally(() => {
                    this.loading = false
                })
                if (res.code === 200) {
                    this.list = res.rows
                    this.$emit('update:total', res.total)
                }
            } catch { }
        },
        // 详情
        async getInfo(id) {
            try {
                const res = await getCustomerEmailInfo({
                    id
                })
                if (res.code === 200) {
                    this.fastInfo = res.data
                }
            } catch (e) {
                console.error(e.message);
            }
        },
        init() {
            this.getGroupList()
            this.getStageList()
            this.getOriginList()
            this.getPoolList()
            this.getPoolReasonList()
            this.getTagList()
            this.getMemberList()
            this.getCommonTree()
        },
        // 分组选项
        async getGroupList() {
            try {
                const res = await getSetPacketList()
                if (res.code === 200) {
                    this.indexOpt.groupOption = res.data
                }
            } catch {
            }
        },
        // 阶段选项
        async getStageList() {
            try {
                const res = await stageList()
                if (res.code === 200) {
                    this.indexOpt.stageOption = res.data
                }
            } catch (e) {
            }
        },
        // 来源选项
        async getOriginList() {
            try {
                const res = await getOriginList()
                if (res.code === 200) {
                    this.indexOpt.originOption = res.data
                }
            } catch {
            }
        },
        // 公海分组选项
        async getPoolList() {
            try {
                const res = await searchGroupsCustomer()
                if (res.code === 200) {
                    this.indexOpt.poolGroupOption = res.data
                }
            } catch {
            }
        },
        // 移入公海原因
        async getPoolReasonList() {
            try {
                const res = await reasonList()
                if (res.code === 200) {
                    this.indexOpt.poolReasonOption = res.data
                }
            } catch {

            }
        },
        // 客户标签选项
        async getTagList() {
            try {
                const res = await getCustomerTagList()
                if (res.code === 200) {
                    this.indexOpt.tagOption = res.data
                }
            } catch {
            }
        },
        // 获取选择的成员
        async getMemberList() {
            try {
                const res = await getTeamMembers()
                if (res.code === 200) {
                    this.indexOpt.teamMemberOption = res.data
                }
            } catch {
            }
        },
        // 获取用户列表
        async getCommonTree() {
            try {
                const res = await listDeptUsersTree()
                if (res.code === 200) {
                    this.indexOpt.memberOption = res.data
                }
            } catch {
            }
        },
        onShowLabel(bool) {
            this.showHeader = bool
            this.$emit('handlerHeader', !bool)
        },
        nextPage() {
            if (this.currentPage < this.totalPages) {
                const currentPage = Number(this.currentPage) + 1;
                this.handleCurrentPage(currentPage)
            }
        },

        prevPage() {
            if (this.currentPage > 1) {
                const currentPage = Number(this.currentPage) - 1;
                this.handleCurrentPage(currentPage)
            }
        },
        handlePageInputBlur() {
            if (!this.currentPage) {
                this.handleCurrentPage(1)
            }
        },
        handlePageInputChange(event) {
            let inputValue = parseInt(+event.target.value, 10);
            if (!inputValue) {
                return
            }
            // 检查输入值是否超出范围，并进行调整
            if (inputValue > this.totalPages) {
                this.handleCurrentPage(this.totalPages)
            } else {
                this.handleCurrentPage(inputValue)
            }
        },
        toggleRightPanel() {
            this.isRightPanelExpanded = !this.isRightPanelExpanded;
        },
        generateSendType(type) {
            const sendType = {
                1: '收件',
                2: '发件'
            }
            return sendType[type]
        },
        handleCurrentPage(value) {
            this.$emit('update:currentPage', value)
        },
    }
}
</script>

<style lang="scss" scoped>
.mail-list-container {
    .quick-jumper {

        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button {
            -webkit-appearance: none !important;
            margin: 0;
        }

        input[type="number"] {
            -moz-appearance: textfield;
        }
    }
}
</style>