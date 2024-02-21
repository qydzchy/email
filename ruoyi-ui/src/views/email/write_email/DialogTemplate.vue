<template>
    <div>
        <el-dialog title="选择模板" :visible.sync="dialogVisible" width="900px" style="z-index:2011" :before-close="onCancel"
            :append-to-body="false">
            <el-tabs v-model="curTab">
                <el-tab-pane v-for="(item, index) in tabsList" :key="index" :disabled="item.disabled" :label="item.title"
                    :name="item.name">
                    <div class="mail-tpl-content">
                        <div class="flex-middle space-between">
                            <div class="flex-middle">
                                <el-input style="width:180px" placeholder="请输入模板名称"></el-input>
                                <div class="flex-middle ml-20">
                                    <span>排序规则:</span>
                                    <el-select v-model="formSearch.orderRule" style="width:160px" class="ml-10">
                                    </el-select>
                                </div>
                                <div class="flex-middle ml-20">
                                    <span>模板类型:</span>
                                    <el-select v-model="formSearch.templateType" style="width:160px" class="ml-10">
                                    </el-select>
                                </div>
                            </div>
                            <div>
                                <el-button round plain
                                    @click="targetBlank('/email/index?type=setting_email&tab=template')">个人模板管理</el-button>
                            </div>

                        </div>
                        <div class="tpl-lists" v-loading="loading">
                            <div class="tpl-item" v-for="(template, idx) in list" :key="idx"
                                @click="selectTemplate = template.id">
                                <p class="tpl-item-top-banner">
                                    <img src="https://v4client.oss-cn-hangzhou.aliyuncs.com/mail_template/img/55943495/7ed00927312414ba0a7b0e347e7013754054e54f69f9e7775c7b1508aa37b0db.jpg"
                                        class="tpl-item-pic"><!---->
                                </p>
                                <p>
                                    <el-radio v-model="selectTemplate" :label="template.id"> {{ template.name }}</el-radio>
                                </p>
                            </div>

                        </div>
                    </div>

                </el-tab-pane>
            </el-tabs>


            <span slot="footer" class="dialog-footer">
                <div class="flex-middle space-between">
                    <div>
                    </div>
                    <div>
                        <el-button @click="onCancel">取 消</el-button>
                        <el-button type="primary" @click="onConfirm">确 定</el-button>
                    </div>
                </div>

            </span>
        </el-dialog>

    </div>
</template>

<script>
import QuickTextSave from '@/views/email/quick_text/save.vue'
import { listTemplate } from "@/api/email/template";
import { targetBlank } from "@/utils/tools";
export default {
    props: {
        dialogVisible: {
            type: Boolean,
            default: false,
            required: true
        }
    },
    components: {
        QuickTextSave
    },
    data() {
        return {
            curTab: '1',
            formSearch: {
                orderRule: '',
                templateType: ''
            },
            tabsList: [
                { name: '1', title: '个人模板', disabled: false },
                { name: '2', title: '企业模板', disabled: true },
                { name: '3', title: '系统模板', disabled: true },
            ],
            list: [],
            selectTemplate: '',
            loading: false
        };
    },
    mounted() {
        this.getList()
    },
    methods: {
        async getList() {
            this.loading = true
            try {
                const res = await listTemplate().finally(() => {
                    this.loading = false
                })
                if (res.code === 200) {
                    this.list = res.data
                }
            } catch { }
        },
        onConfirm() {
            const item = this.list.find(val => val.id === this.selectTemplate)
            this.$emit('onConfirm', item)
        },
        onCancel() {
            this.$emit('update:dialogVisible', false)
        },
        targetBlank
    }
};
</script>

<style lang="scss" scoped>
::v-deep .el-tab-pane {
    .mail-tpl-content .tpl-lists {
        min-height: 150px;
        height: 320px;
        overflow-y: auto;
        margin: 10px 0px 5px;
        display: flex;
        flex-wrap: wrap;
    }

    .mail-tpl-content .tpl-lists .tpl-item {
        position: relative;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        height: 200px;
        width: 16.6%;
        margin-bottom: 16px;
        text-align: center;
        line-height: 18px;
        cursor: pointer;
    }

    .mail-tpl-content .tpl-lists .tpl-item .tpl-item-top-banner {
        position: relative;
    }

    .mail-tpl-content .tpl-lists .tpl-item .tpl-item-top-banner .tpl-item-pic {
        width: 130px;
        height: 150px;
        object-fit: cover;
        object-position: center top;
    }

    .mail-tpl-content .tpl-lists .tpl-item .tpl-description {
        display: none;
    }

    .mail-tpl-content .tpl-lists .tpl-item .new-template-mark {
        position: absolute;
        width: 30px;
        height: 30px;
        left: 0px;
        top: -3px;
        background-color: transparent;
    }

    .mail-tpl-content .tpl-lists .tpl-item .tpl-item-top-banner:hover {
        position: relative;
    }

    .mail-tpl-content .tpl-lists .tpl-item .tpl-item-top-banner:hover .tpl-description {
        display: inline-block;
        position: absolute;
        left: 0px;
        bottom: 5px;
        font-size: 8px;
        padding: 6px;
        box-sizing: border-box;
        width: 100%;
        height: auto;
        line-height: 14px;
        background: rgba(0, 0, 0, 0.5);
        color: rgb(255, 255, 255);
        text-align: left;
    }

    .mail-tpl-content .tpl-lists .tpl-item label {
        width: 120px;
        display: block;
    }

    .mail-tpl-content .tpl-lists .tpl-item img {
        width: 130px;
        height: 150px;
        transition: all 0.2s linear 0s;
        background-color: rgb(236, 236, 236);
        margin-bottom: 10px;
    }

    .mail-tpl-content .tpl-lists .tpl-item img:hover {
        box-shadow: rgba(0, 0, 0, 0.1) 0px 15px 30px;
        transform: translate3d(0px, -2px, 0px);
    }

    .mail-tpl-content .tpl-lists .tpl-item .radio {
        margin-right: 4px;
        vertical-align: top;
        margin-top: 2px;
    }

    .mail-tpl-content .loading-wrap {
        height: 100%;
    }

    .mail-tpl-content .empty-area,
    .mail-tpl-content .loading-wrap {
        display: flex;
        width: 100%;
        align-items: center;
        justify-content: center;
    }

    .mail-tpl-content .empty-area p {
        color: rgb(158, 161, 168);
    }

    .mail-tpl-content .empty-area .empty-area-button {
        margin-top: 12px;
    }

    .in-ai-mail .dialog-wrap .dialog-container {
        box-sizing: border-box;
    }

    .in-ai-mail .dialog-wrap .dialog-container .dialog-content {
        padding: 0px;
    }
}
</style>
