<template>
    <div class="contact-mail-tab-container">
        <div class="list">
            <div class="total">
                <span v-if="!accActive">共{{ emailList.length }}封邮件</span>
                <span v-else>共{{ attachLen }}个附件</span>
                <span class="okki-icon-wrap attachment-btn pointer" :class="{ 'actived': accActive }"
                    @click="accActive = !accActive">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                        viewBox="0 0 25 25" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
                        <path fill-rule="evenodd" clip-rule="evenodd"
                            d="M4.493 11.392a6.25 6.25 0 108.959 8.718l6.8-6.988a1 1 0 10-1.434-1.395l-6.8 6.988a4.25 4.25 0 11-6.091-5.928l6.8-6.988 1.046-1.075a2.5 2.5 0 113.583 3.487l-5.928 6.092-.872.896a.75.75 0 11-1.075-1.046l5.056-5.196a1 1 0 10-1.433-1.395l-5.056 5.196a2.75 2.75 0 103.941 3.836l5.057-5.196.001-.002 1.742-1.79a4.5 4.5 0 10-6.45-6.277l-1.046 1.075-4.184 4.3-2.616 2.688z">
                        </path>
                    </svg>
                </span>
            </div>
            <ul v-if="!accActive" class="contact-mail-list" cur-active-mail-id="" size="mini" module="mail"
                company-fields="[object Object]" is-owner="true" flow-link="[object Object]" contact-info="[object Object]"
                company-info="[object Object]" new-tips-count="0" identity-id="2339700037308"
                first-identity-id="2339700037308">
                <li class="contact-mail-item" v-for="(item, index) in emailList" :key="index"
                    @click="onSelectEmail(item.id)">
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
            <ul v-else class="contact-attach-list">
                <template v-if="emailList && emailList.length">
                    <li v-for="(item, index) in emailList" :key="index"
                        title="Need Quote for Cycloidal Reduction Gear - FAS25-119" class="contact-attach-wrapper">
                        <span class="time">{{ item.sendDate }}</span>
                        <div class="contact-attach-item" v-for="(attach, idx) in item.emailAttachmentList" :key="idx">
                            <svg class="mm-icon mm-icon-file-jpg file-type" viewBox="0 0 200 200" name="file_jpg"
                                xmlns="XMLNS" fill="currentColor" style="height: 35px; width: 35px;">
                                <path
                                    d="M115.3,80.3h34.4c13.7,0,18.1,9.2,18.1,19.1c0,9.4-5.5,18.9-18,18.9H127v23.4h-11.7V80.3z M127,108.8h18.9  c6.7,0,10.3-2,10.3-9.2c0-7.5-4.8-9.4-9.4-9.4H127V108.8z"
                                    fill="#FFFFFF"></path>
                                <path
                                    d="M32.2,6.2c-2.3,0-4.8,0.9-6.6,2.8c-1.7,1.9-2.8,4.2-2.8,6.6v168.8c0,2.3,0.9,4.8,2.8,6.6  c1.9,1.9,4.2,2.8,6.6,2.8h137.5c2.3,0,4.8-0.9,6.6-2.8c1.9-1.9,2.8-4.2,2.8-6.6v-125L126,6.2H32.2z"
                                    fill="#6CCBFF"></path>
                                <path d="M179.1,59.4h-43.8c-2.3,0-4.8-0.9-6.6-2.8c-1.9-1.7-2.8-4.2-2.8-6.6V6.2L179.1,59.4z"
                                    fill="#C4EAFF"></path>
                                <path
                                    d="M155.1,144.7l-21.9-34c-0.7-1.1-1.8-1.6-3-1.6c-1.2,0-2.3,0.7-3,1.6L115.5,129L89.2,86.4  c-0.7-1.1-1.8-1.6-3-1.6s-2.3,0.7-3,1.6l-36.1,58.4c-0.7,1.1-0.7,2.5,0,3.5c0.7,1.2,1.8,1.8,3,1.8h102c1.2,0,2.5-0.7,3.2-1.8  C155.8,147,155.8,145.6,155.1,144.7L155.1,144.7z"
                                    fill="#FFFFFF"></path>
                                <path
                                    d="M125.3,80.4c0,4.8,3.9,8.8,8.8,8.8c4.8,0,8.8-3.9,8.8-8.8s-3.9-8.8-8.8-8.8C129.2,71.7,125.3,75.6,125.3,80.4z"
                                    fill="#FFFFFF"></path>
                            </svg>
                            <div class="file-info">
                                <h1 :title="attach.name" class="ellipsis">{{ attach.name }}</h1>
                                <div>
                                    <span class="file-size ellipsis">{{ attach.size }}</span>
                                    <span class="operations">
                                        <a class="c-link" @click="downloadFile(attach)">下载</a>
                                        <!-- <a class="c-link">预览</a> -->
                                    </span>
                                </div>
                            </div>
                        </div>
                    </li>
                </template>


            </ul>
            <!-- <div class="show-more">查看更多</div> -->
        </div>
    </div>
</template>

<script>
import { getCustomerEmailList, downloadAttachment } from '@/api/customer/email'
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
            accActive: false,
            attachLen: 0
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
                    res.data.forEach(val => {
                        if (val.emailAttachmentList.length) {
                            this.attachLen++
                        }
                    })
                }
            } catch { }
        },
        generateEmailType(type) {
            const mapType = {
                1: '收件',
                2: '发件'
            }
            return mapType[type] || '---'
        },
        // 下载往来邮件附件
        async downloadFile(item) {
            try {
                const res = await downloadAttachment(item.id)
                // 处理文件下载逻辑，比如使用 blob 来下载
                const url = window.URL.createObjectURL(new Blob([res]));
                const link = document.createElement('a');
                link.href = url;
                let filename = item.name;
                link.setAttribute('download', filename);
                document.body.appendChild(link);
                link.click();
            } catch (e) {
                console.error(e.message);
            }
        },
        onSelectEmail(id) {
            this.$emit('emailInfo', id)
        },

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

.contact-mail-tab-container .list .total .contact-mail-list {
    min-height: 100px;
}
</style>