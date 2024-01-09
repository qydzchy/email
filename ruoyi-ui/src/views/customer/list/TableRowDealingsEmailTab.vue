<template>
    <div class="contact-mail-tab-container">
        <div class="list">
            <div class="total">
                <span>共{{ emailList.length }}封邮件</span>
                <span class="okki-icon-wrap attachment-btn">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                        viewBox="0 0 25 25" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
                        <path fill-rule="evenodd" clip-rule="evenodd"
                            d="M4.493 11.392a6.25 6.25 0 108.959 8.718l6.8-6.988a1 1 0 10-1.434-1.395l-6.8 6.988a4.25 4.25 0 11-6.091-5.928l6.8-6.988 1.046-1.075a2.5 2.5 0 113.583 3.487l-5.928 6.092-.872.896a.75.75 0 11-1.075-1.046l5.056-5.196a1 1 0 10-1.433-1.395l-5.056 5.196a2.75 2.75 0 103.941 3.836l5.057-5.196.001-.002 1.742-1.79a4.5 4.5 0 10-6.45-6.277l-1.046 1.075-4.184 4.3-2.616 2.688z">
                        </path>
                    </svg>
                </span>
            </div>
            <ul class="contact-mail-list" cur-active-mail-id="" size="mini" module="mail" company-fields="[object Object]"
                is-owner="true" flow-link="[object Object]" contact-info="[object Object]" company-info="[object Object]"
                new-tips-count="0" identity-id="2339700037308" first-identity-id="2339700037308">
                <li class="contact-mail-item" v-for="(item, index) in emailList" :key="index">
                    <div class="title-wrapper">
                        <h1 class="ellipsis" :title="item.title">{{ item.title }}</h1>
                        <i class="contact-icon m-icon icon-mail-receive" :title="generateEmailType(item.type)"></i>
                    </div>
                    <div class="info-wrapper">
                        <div class="summary ellipsis">{{ item.extractTextFormContent }}</div>
                        <div class="icons">
                            <!---->
                            <!---->
                        </div>
                        <div class="time">{{ item.sendDate }}</div>
                    </div>
                    <!---->
                </li>
            </ul>
            <!-- <div class="show-more">查看更多</div> -->
        </div>
    </div>
</template>

<script>
import { getCustomerEmailList } from '@/api/customer/email'
export default {
    props: {
        row: {
            type: Object,
            default: () => {
            },
            required: false
        },
        options: {
            type: Object,
            default: () => {
                return {
                    isShowSchedule: true
                }
            },
            required: false
        }
    },
    data() {
        return {
            emailList: [],
        }
    },
    watch: {
        row: {
            handler(newVal) {
                if (!newVal?.customerId) {
                    return
                }
                this.getList(newVal.customerId)
            },
            deep: true,
            immediate: true
        }
    },
    methods: {
        async getList(customerId) {
            try {
                const res = await getCustomerEmailList({
                    customerId
                })
                if (res.code === 200) {
                    this.emailList = res.data
                }
            } catch { }
        },
        generateEmailType(type) {
            const mapType = {
                1: '收件',
                2: '发件'
            }
            return mapType[type] || '---'
        }
    }
}
</script>

<style lang="scss" scoped>
.contact-mail-tab-container {
    display: flex;
    overflow-y: auto;
    flex-direction: column;
    height: 100%;
    padding: 0 22px
}

.contact-mail-tab-container .empty {
    display: flex;
    flex: 1;
    align-items: center;
    justify-content: center
}

.contact-mail-tab-container .list .total {
    display: flex;
    padding: 15px 0;
    border-bottom: 1px solid #d9d9d9;
    color: #2a2a2a;
    justify-content: space-between;
    align-items: center
}

.contact-mail-tab-container .list .total .attachment-btn {
    width: 21px;
    height: 21px;
    border: 1px solid transparent;
    line-height: 21px;
    border-radius: 2px;
    text-align: center
}

.contact-mail-tab-container .list .total .attachment-btn.actived {
    border: 1px solid #0884f3;
    color: #0884f3
}
</style>