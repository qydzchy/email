<template>
    <div class="view-mode-three open component-mail-list relative list-with-toolbar">
        <div v-show="!isHide">
            <div class="promotion-wrapper"></div>

            <div class="mail-toolbar-wrapper">
                <div class="mail-toolbar-left">
                    <span class="mail-toolbar-btn-item">
                        <label class="mm-checkbox">
                            <input v-model="selectAll" @change="toggleAllEmails" true-value="true" type="checkbox">
                            <span class="mm-checkbox-input"></span>
                        </label>
                    </span>

                    <span class="mm-tooltip mail-toolbar-btn-item" v-if="isIconsToggled" @click="refresh">
                        <span class="mm-tooltip-trigger">
                            <span>
                                <span class="okki-icon-wrap tool-bar-icon-item">​<svg xmlns="http://www.w3.org/2000/svg"
                                        width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
                                        fill="currentColor">
                                        <line x1="21" y1="4" x2="21" y2="12"></line>
                                        <line x1="3" y1="12" x2="3" y2="20"></line>
                                        <path fill-rule="evenodd" clip-rule="evenodd"
                                            d="M12 4a7.993 7.993 0 00-6.762 3.723A1 1 0 113.55 6.652 9.993 9.993 0 0112 2a9.985 9.985 0 018 3.999V4a1 1 0 112 0v8c0 .149-.003.296-.01.443a1 1 0 11-1.998-.087A8 8 0 0012 4zM3.174 9.967a1 1 0 01.879 1.108 8 8 0 0014.997 4.71 1 1 0 011.76.948A9.998 9.998 0 0112 22a9.985 9.985 0 01-8-3.999V20a1 1 0 11-2 0v-8c0-.39.022-.775.066-1.154a1 1 0 011.108-.879z">
                                        </path>
                                    </svg>
                                </span>
                            </span>
                        </span>

                    </span>
                    <span class="mm-tooltip mail-toolbar-btn-item" v-if="isIconsToggled">
                        <span class="mm-tooltip-trigger">
                            <span>
                                <span class="okki-icon-wrap tool-bar-icon-item">​<svg xmlns="http://www.w3.org/2000/svg"
                                        width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
                                        fill="currentColor">
                                        <path
                                            d="M10 12a2 2 0 104 0 2 2 0 00-4 0zm0 7a2 2 0 104 0 2 2 0 00-4 0zm0-14a2 2 0 104 0 2 2 0 00-4 0z">
                                        </path>
                                    </svg>
                                </span>
                            </span>
                        </span>

                    </span>

                    <span class="mm-tooltip mail-toolbar-btn-item" v-if="!isIconsToggled">
                        <span class="mm-tooltip-trigger">
                            <div class="mm-popover">
                                <div>
                                    <span>
                                        <span class="okki-icon-wrap tool-bar-icon-item">​<svg
                                                xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                                                viewBox="0 0 24 24" aria-hidden="true" fill="currentColor"
                                                class="okki-svg-icon">
                                                <path
                                                    d="M12 6a1 1 0 011 1v4.423l2.964 1.711a1 1 0 11-1 1.732l-3.447-1.99A1 1 0 0111 11.98V7a1 1 0 011-1z">
                                                </path>
                                                <path fill-rule="evenodd" clip-rule="evenodd"
                                                    d="M22 12c0 5.523-4.477 10-10 10S2 17.523 2 12 6.477 2 12 2s10 4.477 10 10zm-2 0a8 8 0 11-16 0 8 8 0 0116 0z">
                                                </path>
                                            </svg>
                                        </span>
                                    </span>
                                </div>
                            </div>
                        </span>
                    </span>
                    <span class="mm-tooltip mail-toolbar-btn-item" v-if="!isIconsToggled" @click="deleteEmails">
                        <span class="mm-tooltip-trigger">
                            <span>
                                <span class="okki-icon-wrap tool-bar-icon-item">​<svg xmlns="http://www.w3.org/2000/svg"
                                        width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" fill="currentColor"
                                        class="okki-svg-icon">
                                        <path fill-rule="evenodd" clip-rule="evenodd"
                                            d="M10.2 2a2 2 0 00-1.765 1.059L7.4 5H3a1 1 0 000 2h1.5v12a3 3 0 003 3h9a3 3 0 003-3V7H21a1 1 0 100-2h-4.4l-1.035-1.941A2 2 0 0013.8 2h-3.6zm7.3 5h-11v12a1 1 0 001 1h9a1 1 0 001-1V7zm-3.167-2L13.8 4h-3.6l-.533 1h4.666zM10 9.5a1 1 0 011 1v6a1 1 0 11-2 0v-6a1 1 0 011-1zm5 1a1 1 0 10-2 0v6a1 1 0 102 0v-6z">
                                        </path>
                                    </svg>
                                </span>
                            </span>
                        </span>
                    </span>
                    <span class="mm-tooltip mail-toolbar-btn-item" v-if="!isIconsToggled" @click="removeEmailToLabel">
                        <span class="mm-tooltip-trigger">
                            <span>
                                <span class="okki-icon-wrap tool-bar-icon-item">​<svg xmlns="http://www.w3.org/2000/svg"
                                        width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" fill="currentColor"
                                        class="okki-svg-icon">
                                        <path d="M8 10a2 2 0 100-4 2 2 0 000 4z"></path>
                                        <path fill-rule="evenodd" clip-rule="evenodd"
                                            d="M4.033 4.033l7.115.093 8.561 8.561a1 1 0 010 1.414l-5.608 5.608a1 1 0 01-1.414 0l-8.56-8.561-.094-7.115zm8.242-1.609a1 1 0 00-.694-.292l-7.522-.099A2 2 0 002.033 4.06l.099 7.522a1 1 0 00.292.694l8.849 8.848a3 3 0 004.243 0l5.607-5.607a3 3 0 000-4.243l-8.848-8.849zM8 10a2 2 0 100-4 2 2 0 000 4z">
                                        </path>
                                    </svg>
                                </span>
                            </span>
                        </span>
                    </span>
                    <span class="mm-tooltip mail-toolbar-btn-item" v-if="!isIconsToggled">
                        <span class="mm-tooltip-trigger">
                            <span>
                                <span class="okki-icon-wrap tool-bar-icon-item">​<svg xmlns="http://www.w3.org/2000/svg"
                                        width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" fill="currentColor"
                                        class="okki-svg-icon">
                                        <path fill-rule="evenodd" clip-rule="evenodd"
                                            d="M2 6a3 3 0 013-3h4.379a3 3 0 012.108.866l1.824 1.8H19a3 3 0 013 3V18a3 3 0 01-3 3H5a3 3 0 01-3-3V6zm2 0a1 1 0 011-1h4.379a1 1 0 01.703.289l1.823 1.8a2 2 0 001.406.578H19a1 1 0 011 1V10H4V6zm16 6v6a1 1 0 01-1 1H5a1 1 0 01-1-1v-6h16z">
                                        </path>
                                    </svg>
                                </span>
                            </span>
                        </span>
                    </span>
                    <span class="mm-tooltip mail-toolbar-btn-item" v-if="!isIconsToggled" @click="toggleDropdown">
                        <span class="mm-tooltip-trigger">
                            <span>
                                <span class="okki-icon-wrap tool-bar-icon-item">​<svg xmlns="http://www.w3.org/2000/svg"
                                        width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" fill="currentColor"
                                        class="okki-svg-icon">
                                        <path
                                            d="M10 12a2 2 0 104 0 2 2 0 00-4 0zm0 7a2 2 0 104 0 2 2 0 00-4 0zm0-14a2 2 0 104 0 2 2 0 00-4 0z">
                                        </path>
                                    </svg>
                                </span>
                            </span>
                        </span>
                    </span>

                </div>
                <div class="mail-toolbar-right">

                    <div class="mail-paging tool-bar-paging">
                        <span class="total-count ellipsis">共 {{ total }} 封</span>
                        <div class="quick-jumper">
                            <input :max="totalPages" class="mail-paging-input" :value.sync="currentPage" type="number"
                                @blur="handlePageInputBlur" @input="handlePageInputChange">
                            <span class="mail-paging-slash"> / </span>
                            <span class="page-unit">{{ totalPages }}  页</span>
                        </div>
                        <div :class="['mail-paging-btn', 'left-btn', { 'disabled': currentPage === 1 }]" @click="prevPage">
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
                    <span class="mm-tooltip mail-toolbar-btn-item">
                        <span class="mm-tooltip-trigger">
                            <span class="okki-icon-wrap filter-icon">​<svg xmlns="http://www.w3.org/2000/svg" width="18"
                                    height="18" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
                                    fill="currentColor">
                                    <path fill-rule="evenodd" clip-rule="evenodd"
                                        d="M5.055 5l5.507 7.388a1 1 0 01.198.598v5.45l2.48 1.407v-6.857a1 1 0 01.198-.598L18.945 5H5.055zm-1.91-.837C3.403 3.518 4.017 3 4.8 3h14.4c.783 0 1.397.518 1.655 1.163a2.028 2.028 0 01-.246 1.951l-5.369 7.203v6.764c0 .603-.27 1.218-.797 1.595-.546.39-1.259.434-1.85.099l-2.88-1.634c-.628-.356-.953-1.034-.953-1.694v-5.13L3.39 6.114a2.028 2.028 0 01-.245-1.951l.928.372-.928-.372z">
                                    </path>
                                </svg>
                            </span>
                        </span>

                    </span>
                    <span class="mm-tooltip mail-toolbar-btn-item">
                        <span class="mm-tooltip-trigger">
                            <span :class="['okki-icon-wrap', 'switch-attach-icon', { 'active': !attachmentFlag }]"
                                @click="switchAttachment">​<svg xmlns="http://www.w3.org/2000/svg" width="18" height="18"
                                    viewBox="0 0 25 25" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
                                    <path fill-rule="evenodd" clip-rule="evenodd"
                                        d="M4.493 11.392a6.25 6.25 0 108.959 8.718l6.8-6.988a1 1 0 10-1.434-1.395l-6.8 6.988a4.25 4.25 0 11-6.091-5.928l6.8-6.988 1.046-1.075a2.5 2.5 0 113.583 3.487l-5.928 6.092-.872.896a.75.75 0 11-1.075-1.046l5.056-5.196a1 1 0 10-1.433-1.395l-5.056 5.196a2.75 2.75 0 103.941 3.836l5.057-5.196.001-.002 1.742-1.79a4.5 4.5 0 10-6.45-6.277l-1.046 1.075-4.184 4.3-2.616 2.688z">
                                    </path>
                                </svg>
                            </span>
                        </span>

                    </span>
                    <span class="mm-tooltip mail-toolbar-btn-item">
                        <span class="mm-tooltip-trigger">
                            <a href="#" @click.prevent="onSwitchSetup" class="mail-toolbar-btn-item noright" tag="div">
                                <span class="okki-icon-wrap setting-icon">​<svg xmlns="http://www.w3.org/2000/svg"
                                        width="18" height="18" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
                                        fill="currentColor">
                                        <path fill-rule="evenodd" clip-rule="evenodd"
                                            d="M10.783 3.287c-.439-.45-1.319-1.022-2.319-.644a9.99 9.99 0 00-2.799 1.619c-.826.677-.771 1.723-.602 2.33.12.426.074.895-.164 1.308a1.692 1.692 0 01-1.052.797c-.61.156-1.546.632-1.717 1.689a10.063 10.063 0 000 3.228c.171 1.056 1.107 1.533 1.717 1.689.43.11.813.384 1.052.797.238.413.284.882.164 1.309-.17.606-.224 1.652.602 2.329a9.99 9.99 0 002.8 1.619c1 .378 1.879-.194 2.318-.645.31-.317.74-.512 1.217-.512.477 0 .907.195 1.217.512.439.45 1.319 1.023 2.319.645a9.99 9.99 0 002.799-1.62c.826-.676.771-1.722.602-2.328a1.692 1.692 0 01.164-1.31 1.692 1.692 0 011.052-.796c.61-.156 1.546-.633 1.717-1.69a10.064 10.064 0 000-3.227c-.171-1.057-1.107-1.533-1.717-1.689a1.692 1.692 0 01-1.052-.797 1.692 1.692 0 01-.164-1.309c.17-.606.224-1.652-.602-2.329a9.99 9.99 0 00-2.8-1.619c-1-.378-1.879.194-2.318.644-.31.318-.74.513-1.217.513-.477 0-.907-.195-1.217-.513zm-3.82 2.497a7.99 7.99 0 012.171-1.256h.002c.048.019.13.067.216.156A3.692 3.692 0 0012 5.8a3.692 3.692 0 002.648-1.116.651.651 0 01.216-.155h.002c.791.303 1.523.73 2.17 1.255l.001.002a.654.654 0 01-.026.266c-.259.923-.16 1.95.358 2.848a3.691 3.691 0 002.289 1.735c.12.03.203.077.243.11h.001a8.063 8.063 0 010 2.509v.001a.652.652 0 01-.244.11c-.93.237-1.77.837-2.289 1.735a3.692 3.692 0 00-.358 2.848c.034.12.035.215.026.265v.003a7.989 7.989 0 01-2.171 1.255h-.002a.652.652 0 01-.216-.155A3.692 3.692 0 0012 18.2a3.692 3.692 0 00-2.648 1.116.653.653 0 01-.216.155h-.002a7.988 7.988 0 01-2.17-1.255l-.001-.003a.652.652 0 01.026-.265c.259-.924.16-1.95-.358-2.848a3.692 3.692 0 00-2.289-1.735.652.652 0 01-.243-.11l-.001-.001a8.067 8.067 0 010-2.508v-.001a.653.653 0 01.244-.11A3.692 3.692 0 006.631 8.9a3.692 3.692 0 00.358-2.848.652.652 0 01-.026-.266v-.002zm2.887 6.178a2.15 2.15 0 114.3 0 2.15 2.15 0 01-4.3 0zM12 7.812a4.15 4.15 0 100 8.3 4.15 4.15 0 000-8.3z">
                                        </path>
                                    </svg>
                                </span>
                            </a>
                        </span>

                    </span>
                    <div :class="['show', 'main-toolbar-search-pin', 'mail-toolbar-btn-item', { 'active': fixedFlag }]"
                        @click="switchFixed">
                        <div class="main-toolbar-search-pin-bg">
                            <a class="main-toolbar-search-pin-handle">
                                <span :class="['okki-icon-wrap', 'pin-icon', { 'pin-icon-active': fixedFlag }]">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24"
                                        aria-hidden="true" class="okki-svg-icon" fill="#ffffff">
                                        <path fill-rule="evenodd" clip-rule="evenodd"
                                            d="M8.382 2a.963.963 0 00-.97.956c0 1.33.582 2.526 1.506 3.353l-.606 3.865C6.325 11.39 5 13.564 5 16.044c0 .528.434.956.97.956H11v3.914c0 .6.448 1.086 1 1.086s1-.486 1-1.086V17h5.03c.536 0 .97-.428.97-.956 0-2.507-1.354-4.7-3.376-5.91l-.597-3.777a4.49 4.49 0 001.56-3.4.963.963 0 00-.969-.957H8.382z">
                                        </path>
                                    </svg>
                                </span>
                            </a>
                        </div>
                    </div>

                </div>


                <div class="mail-drop-menu-wrapper" style="width: 220px; top: 40px; left: 215px;" :style="dropdownStyle">
                    <ul class="mail-drop-menu" style="height: 96px;">
                        <li v-for="(item, index) in menuItems" :key="index" :class="[
                            'mail-drop-menu-item',
                            `DROPMENU_55587_ITEM_${index}`,
                            { 'mail-drop-menu-item-active': hoveredItem === index }
                        ]" @click="readOrSpanEmails(index)" @mouseover="hoveredItem = index"
                            @mouseleave="hoveredItem = null">
                            <span class="mail-drop-menu-text ellipsis">

                                <span class="">{{ item }}</span>
                            </span>
                            <span>

                            </span>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="top-block">
                <div class="multi-select" style="display: none;">
                    <div class="multi-select-content">
                        <span>已选择此页面上所有 <span class="num">  20  </span> 封邮件</span> ， <a class="all">选择全部 <a class="num">
                                 1121 
                            </a> 封邮件</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- 内容 -->
        <slot></slot>
        <div class="mm-spinner" style="display: none;">
            <div class="mm-spinner-mask"></div>
            <div class="mm-spinner-wrapper">
                <div class="mm-loading" style="height: 40px; width: 40px; color: rgb(38, 132, 255);">
                    <svg class="circular" viewBox="25 25 50 50">
                        <circle stroke-width="2" class="path" cx="50" cy="50" r="20" fill="none" stroke-miterlimit="10">
                        </circle>
                    </svg>
                </div>

            </div>
        </div>
    </div>
</template>
<script>
import { EventBus } from "@/api/email/event-bus";
import {
    fixedEmail,
    readEmail,
    spamEmail,
    deleteEmail,
} from "@/api/email/email";
import {
    customerEmailList
} from "@/api/customer/email";
import { mapState } from "vuex";

export default {
    props: {
        isHide: {
            type: Boolean,
            default: false
        },
        emailList: Array,
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
    data() {
        return {
            activeEmailId: null,
            taskId: null,
            type: null,
            readFlag: null,
            pendingFlag: null,
            delFlag: null,
            draftsFlag: null,
            spamFlag: null,
            localEmailList: [],
            currentEmailDetail: {},
            currentEmailType: '',
            selectAll: false,
            isIconsToggled: true,  // 用于控制图标的显示状态
            isDropdownShown: false,  // 保存下拉菜单的显示状态
            selectedItem: null,
            hoveredItem: null, // 当前悬停的列表项的索引
            menuItems: [
                '标为已读',
                '标为未读',
                '标为垃圾邮件',
            ],
            fixedFlag: false,
            attachmentFlag: false,
            emailSlideStatus: {}
        }
    },

    computed: {
        ...mapState({
            pageSize: state => state.emailSetting.usuallySetting?.maxPerPage
        }),
        totalPages() {
            return Math.ceil(this.total / this.pageSize);
        },
        dropdownStyle() {
            return this.isDropdownShown ? '' : 'display: none;';
        },
        getLeftSlideStatus() {
            return (id) => {
                if (this.emailSlideStatus[id] && this.emailSlideStatus[id].left !== undefined) {
                    return this.emailSlideStatus[id].left;
                }
                return 0;
            }
        }
    },
    watch: {
        emailList(newList) {
            this.localEmailList = [...newList];
        },

        masterCheckbox(newValue) {
            this.setSelected(newValue);
        }
    },

    methods: {

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
                const currentPage = this.totalPages;
                this.handleCurrentPage(currentPage)
            } else {
                this.handleCurrentPage(inputValue)
            }
        },

        async toggleFixed(email, event) {
            event.stopPropagation();
            const data = {
                "id": email.id,
                "fixedFlag": !email.fixedFlag
            };
            try {
                const response = await fixedEmail(data);
                if (response.code !== 200) {
                    this.$message.error("执行失败");
                    return;
                }

                email.fixedFlag = !email.fixedFlag;
            } catch (error) {
                console.error('固定邮件出现错误:', error);
                throw error;
            }
        },

        toggleAllEmails() {
            const shouldSelectAll = this.selectAll;
            this.localEmailList.forEach(dateGroup => {
                for (const date in dateGroup) {
                    if (dateGroup.hasOwnProperty(date)) {
                        dateGroup[date].forEach(email => {
                            email.selected = shouldSelectAll;
                        });
                    }
                }
            });

            this.isIconsToggled = !this.isIconsToggled;
        },

        // 存在选中的邮件
        existSelectedEmail() {
            let hasSelected = false; // 创建一个变量来跟踪是否存在选中的邮件

            this.localEmailList.forEach(dateGroup => {
                for (const date in dateGroup) {
                    if (dateGroup.hasOwnProperty(date)) {
                        dateGroup[date].forEach(email => {
                            if (email.selected) {
                                hasSelected = true;
                            }
                        });
                    }
                }
            });

            return hasSelected; // 返回这个变量
        },

        setSelected(newValue) {
            this.localEmailList.forEach(dateGroup => {
                for (const date in dateGroup) {
                    if (dateGroup.hasOwnProperty(date)) {
                        dateGroup[date].forEach(email => {
                            email.selected = newValue;
                        });
                    }
                }
            });
        },

        refresh() {
            this.$emit('reloadList')
        },

        toggleDropdown() {
            this.isDropdownShown = !this.isDropdownShown;
        },

        readOrSpanEmails(index) {
            this.selectedItem = index;

            const selectedEmails = this.getSelectedEmailIds();
            if (selectedEmails.length) {
                if (this.menuItems[index] === '标为已读') {
                    this.readEmails(selectedEmails);
                } else if (this.menuItems[index] === '标为未读') {
                    this.unReadEmails(selectedEmails);
                } else if (this.menuItems[index] === '标为垃圾邮件') {
                    this.spamEmails(selectedEmails);
                }
            }
        },

        closeSelected() {
            this.selectAll = false;
            this.isDropdownShown = false;
            this.toggleAllEmails();
            // 刷新邮件列表
            this.currentPage = 1;
        },

        getSelectedEmailIds() {
            const selectedIds = [];
            this.localEmailList.forEach(dateGroup => {
                for (const date in dateGroup) {
                    if (dateGroup.hasOwnProperty(date)) {
                        dateGroup[date].forEach(email => {
                            if (email.selected) {
                                selectedIds.push(email.id);
                            }
                        });
                    }
                }
            });
            return selectedIds;
        },

        // 标记为已读文件
        async readEmails(emailIds) {
            const data = {
                "ids": emailIds,
                "readFlag": true
            };
            try {
                const response = await readEmail(data);
                if (response.code === 200) {
                    this.$message.success("成功标记为已读");
                    this.closeSelected();
                    return;
                }
            } catch (error) {
                console.error('标记为已读出现错误:', error);
                throw error;
            }
        },

        // 标记为已读文件
        async readEmail(email) {
            const emailIds = [];
            emailIds.push(email.id);
            const data = {
                "ids": emailIds,
                "readFlag": true
            };
            try {
                const response = await readEmail(data);
                if (response.code === 200) {
                    this.$set(email, 'readFlag', true);
                    return;
                }
            } catch (error) {
                console.error('标记为已读出现错误:', error);
                throw error;
            }
        },

        // 标记为未读邮件
        async unReadEmails(emailIds) {
            const data = {
                "ids": emailIds,
                "readFlag": false
            };
            try {
                const response = await readEmail(data);
                if (response.code === 200) {
                    this.$message.success("成功标记为未读");
                    return;
                }
            } catch (error) {
                console.error('标记为未读出现错误:', error);
                throw error;
            }
        },

        // 标识为垃圾邮件
        async spamEmails(emailIds) {
            const data = {
                "ids": emailIds,
                "spamFlag": true
            };
            try {
                const response = await spamEmail(data);
                if (response.code === 200) {
                    this.$message.success("垃圾邮件标记成功");
                    this.closeSelected();
                    return;
                }
            } catch (error) {
                console.error('垃圾邮件标记出现错误:', error);
                throw error;
            }
        },

        // 删除邮件
        async deleteEmails() {
            const emailIds = this.getSelectedEmailIds();
            if (emailIds.length) {
                const data = {
                    "ids": emailIds
                };
                try {
                    const response = await deleteEmail(data);
                    if (response.code === 200) {
                        this.$message.success("邮件删除成功");
                        this.closeSelected();
                        return;
                    }
                } catch (error) {
                    console.error('邮件删除出现错误:', error);
                    throw error;
                }
            }
        },

        // 移动邮件到标签
        async removeEmailToLabel() {

        },

        toggleEmailSelection() {
            let existSelected = this.existSelectedEmail();
            if (existSelected) {
                this.selectAll = true;
                this.isIconsToggled = false;
            } else {
                this.selectAll = false;
                this.isIconsToggled = true;
            }
        },

        // 固定总开关
        switchFixed() {
            this.fixedFlag = !this.fixedFlag;
            this.handleCurrentPage(1)
        },

        // 附件总开关
        switchAttachment() {
            this.attachmentFlag = !this.attachmentFlag;
            this.handleCurrentPage(1)
        },

        toggleSlide(emailId, direction) {
            if (!this.emailSlideStatus[emailId]) {
                this.$set(this.emailSlideStatus, emailId, {
                    left: 0
                });
            }

            if (direction === 'right') {
                this.emailSlideStatus[emailId].left = -100;
            } else {
                this.emailSlideStatus[emailId].left = 0;
            }
        },
        // 切换到设置页
        onSwitchSetup() {
            this.$router.replace('/email/index?type=setting_email')
        },
        handleCurrentPage(value) {
            this.$emit('update:currentPage', value)
        },
    }
}
</script>
<style lang="scss">
.grouped-list-item-title {
    font-weight: 400;
    font-size: 12px;
    color: #909090;
    margin-bottom: 5px;
    margin-top: 14px;
    padding-left: 14px;
}

.view-mode-three {
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