<template>
    <div>
        <el-dialog title="选择文本" :visible.sync="dialogVisible" width="60%" style="z-index:2011" :before-close="onCancel"
            :append-to-body="false">
            <div class="quick-text-picker">
                <ul class="quick-text-list" v-loading="loading">
                    <li class="text-item pointer" data-draggable="true" v-for="(item, index) in list" :key="index"
                        @click="selectQuickTextId = item.id">
                        <i class="m-icon icon-move-btn"></i>
                        <el-radio v-model="selectQuickTextId" :label="item.id">
                            {{ item.title }}
                        </el-radio>
                        <span title="内容" class="text-item-content ellipsis" v-html="item.content"></span>
                        <!-- 编辑 -->
                        <svg class="mm-icon mm-icon-edit operation-icon" viewBox="0 0 24 24" name="edit" xmlns="XMLNS"
                            fill="currentColor" @click="openQuickTextSave(item)">
                            <path
                                d="M4.4 15.4l4.1 4.1c.2.2.5.2.6 0L19.4 9.2c.2-.2.2-.4 0-.6l-4.1-4.1c-.2-.2-.4-.2-.6 0L4.4 14.8c-.2.2-.2.5 0 .6zM16.7 2.6c-.2.2-.2.5 0 .7l4 4c.2.2.5.2.7 0l1.1-1.1c.8-.7.8-1.8 0-2.6l-2.1-2.1c-.8-.8-1.9-.8-2.7 0l-1 1.1zM1 22.2c-.1.5.3.9.8.8l5-1.2c.2 0 .3-.1.4-.2l.1-.1c.1-.1.1-.4-.1-.6l-4.1-4.1c-.2-.2-.5-.2-.6-.1l-.1.1c-.1.1-.2.3-.2.4l-1.2 5z">
                            </path>
                        </svg>
                        <!-- 删除 -->
                        <svg class="mm-icon mm-icon-delete operation-icon" viewBox="0 0 24 24" name="delete" xmlns="XMLNS"
                            fill="currentColor" @click="delQuickText(item.id)">
                            <path
                                d="M21 4.6h-5.8V2.8c0-1-.8-1.9-1.8-1.9h-2.8c-1 0-1.8.9-1.8 1.9v1.8H3c-.4 0-.7.3-.7.7v1.4c0 .4.3.7.7.7h18c.4 0 .7-.3.7-.7V5.3c0-.4-.3-.7-.7-.7zM10.6 3.2c0-.2.2-.4.5-.4h1.8c.3 0 .5.2.5.4v1.4h-2.8V3.2zm8.6 6H4.8c-.3 0-.6.4-.6.7v10.9c0 1.3 1 2.3 2.3 2.3h11c1.3 0 2.3-1 2.3-2.3V9.9c0-.3-.3-.7-.6-.7zm-8.6 10.2c0 .3-.2.4-.4.4h-1c-.2 0-.4-.1-.4-.4v-6.5c0-.3.2-.4.4-.4h1c.2 0 .4.1.4.4v6.5zm4.6 0c0 .3-.2.4-.4.4h-1c-.2 0-.4-.1-.4-.4v-6.5c0-.3.2-.4.4-.4h1c.2 0 .4.1.4.4v6.5z">
                            </path>
                        </svg>
                    </li>
                </ul>
            </div>

            <span slot="footer" class="dialog-footer">
                <div class="flex-middle space-between">
                    <div>
                        <el-button icon="el-icon-plus" type="text" @click="openQuickTextSave(null)">新建</el-button>
                    </div>
                    <div>
                        <el-button @click="onCancel">取 消</el-button>
                        <el-button type="primary" @click="onConfirm">确 定</el-button>
                    </div>
                </div>

            </span>
        </el-dialog>
        <QuickTextSave ref="addQuickText" @refresh="getList" />
    </div>
</template>

<script>
import QuickTextSave from '@/views/email/quick_text/save.vue'
import { listQuickText, deleteQuickText } from '@/api/email/quickText'
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
            list: [],
            selectQuickTextId: '',
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
                const res = await listQuickText().finally(() => {
                    this.loading = false
                })
                if (res.code === 200) {
                    this.list = res.data
                }
            } catch { }
        },
        openQuickTextSave(quickText) {
            this.$refs.addQuickText.open(quickText);
        },
        async delQuickText(id) {
            try {
                const res = await deleteQuickText({ id })
                if (res.code === 200) {
                    this.$message.success('删除成功')
                    this.getList()
                }
            } catch (e) {
                console.error(e.message);
            }
        },
        onConfirm() {
            const item = this.list.find(val => val.id === this.selectQuickTextId)
            this.$emit('onConfirm', item)
        },
        onCancel() {
            this.$emit('update:dialogVisible', false)
        }
    }
};
</script>
<style lang="scss" scoped>
::v-deep .quick-text-picker {
    .quick-text-list {
        position: relative;
        min-height: 205px;
        max-height: 500px;
        overflow: auto;
    }

    .text-empty,
    .text-item {
        display: flex;
        align-items: center;
        width: 100%;
        height: 40px;
        border-bottom: 1px solid rgb(204, 204, 204);
        position: relative;
        padding-left: 1em;
        box-sizing: border-box;
    }

    .text-empty .icon-move-btn,
    .text-item .icon-move-btn {
        position: absolute;
        display: none;
        left: 0px;
        transform: rotate(90deg);
    }

    .text-empty:hover .icon-move-btn,
    .text-item:hover .icon-move-btn {
        display: unset;
    }

    .text-item-name {
        display: inline-block;
        width: 160px;
        vertical-align: top;
    }

    .text-item-content {
        flex: 1 1 0%;
        color: rgb(144, 144, 144);
    }

    .operation-icon {
        color: rgb(144, 144, 144);
        cursor: pointer;
        margin-left: 20px;
    }

    .operation-icon:hover {
        color: rgb(0, 100, 255);
    }

    .text-empty {
        border-bottom: none;
        justify-content: center;
        min-height: 205px;
    }

    .quick-text-footer {
        position: relative;
        display: flex;
        width: 100%;
        justify-content: space-between;
    }

    .el-radio__label {
        color: black;
    }
}

::v-deep .el-dialog__wrapper {
    z-index: 2011 !important;
}
</style>