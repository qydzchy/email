<template>
    <div class="dispatcher-page">
        <div class="mail-plus-rule config-page-item">
            <!-- 列表 -->
            <div class="mail-rule-block" v-show="!showForm">
                <div class="mail-rule-content">
                    <div class="mail-rule-content-list">
                        <div class="mail-config-header">
                            <div>
                                <h3 class="config-page-title">收发件规则</h3><span class="tips">收件规则将按从上到下依次执行，可拖拽调整规则顺序</span>
                            </div><span class="mm-tooltip"><span class="mm-tooltip-trigger"><button type="button"
                                        class="mm-button mm-button__primary" @click="onShowForm">新建规则</button></span></span>
                        </div>
                        <ul class="mail-rule-list">
                            <div class="mm-table-wrapper">
                                <div class="mm-table mm-table__medium mm-table__horizontal-bordered">
                                    <div class="mm-thead" style="margin-right: -8px; margin-bottom: -8px;">
                                        <table cellpadding="0" cellspacing="0" border="0">
                                            <colgroup>
                                                <col width="20">
                                                <col width="60">
                                                <col width="60">
                                                <col width="200">
                                                <col width="100">
                                            </colgroup>
                                            <thead>
                                                <tr class="">
                                                    <th class="">
                                                        <div class="mm-table-cell" title="" style="text-align: left;">

                                                        </div>
                                                        <div class="mm-thead-dragbar__next mm-thead-dragbar"></div>
                                                    </th>
                                                    <th class="">
                                                        <div class="mm-table-cell" title="" style="text-align: left;">
                                                            类型</div>
                                                        <div class="mm-thead-dragbar__next mm-thead-dragbar"></div>
                                                    </th>
                                                    <th class="">
                                                        <div class="mm-table-cell" title="" style="text-align: left;">
                                                            规则名称</div>
                                                        <div class="mm-thead-dragbar__next mm-thead-dragbar"></div>
                                                    </th>
                                                    <th class="">
                                                        <div class="mm-table-cell" title="" style="text-align: left;">
                                                            执行规则</div>
                                                        <div class="mm-thead-dragbar__next mm-thead-dragbar"></div>
                                                    </th>
                                                    <th class="">
                                                        <div class="mm-table-cell" title="" style="text-align: right;">
                                                            操作</div>
                                                        <div class="mm-thead-dragbar__next mm-thead-dragbar"></div>
                                                    </th>
                                                </tr>
                                            </thead>
                                        </table>
                                    </div>
                                    <div class="mm-tbody"
                                        style="margin-bottom: -8px; margin-right: -8px; max-height: 682px;">
                                        <table cellpadding="0" cellspacing="0" border="0">
                                            <colgroup>
                                                <col width="20">
                                                <col width="60">
                                                <col width="60">
                                                <col width="200">
                                                <col width="100">
                                            </colgroup>
                                            <tbody>
                                                <tr class="mm-tbody-tr" draggable="true" v-for="(item, idx) in list"
                                                    :key="idx">
                                                    <td>
                                                        <div class="mm-table-cell" title="" style="text-align: left;">
                                                            <a class="e-move">
                                                                <span class="m-icon icon-move-btn"></span>
                                                            </a>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="mm-table-cell" title="" style="text-align: left;"><span
                                                                class="mm-tag__success mm-tag__mini mm-tag">{{
                                                                    ruleMap[item.ruleType]
                                                                }}</span>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="mm-table-cell" title="" style="text-align: left;">{{
                                                            item.ruleName }}
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="mm-table-cell" title="" style="text-align: left;">
                                                            <div :title="item.executionRule" class="rule-desc ellipsis">
                                                                {{ item.executionRule }}</div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="mm-table-cell" title="" style="text-align: right;">
                                                            <div>
                                                                <el-switch v-model="item.status"
                                                                    @change="handleStatus(item)"></el-switch>
                                                                <button type="button"
                                                                    class="mm-button mm-button__text btn-list-item"
                                                                    @click="onEdit(item.id)">编辑</button>
                                                                <button type="button"
                                                                    class="mm-button mm-button__text btn-list-item"
                                                                    style="padding-right: 0px;"
                                                                    @click="onDelete(item.id)">删除</button>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="mm-spinner" style="display: none;">
                                    <div class="mm-spinner-mask"></div>
                                    <div class="mm-spinner-wrapper">
                                        <div class="mm-loading"
                                            style="height: 40px; width: 40px; color: rgb(38, 132, 255);">
                                            <svg class="circular" viewBox="25 25 50 50">
                                                <circle stroke-width="2" class="path" cx="50" cy="50" r="20" fill="none"
                                                    stroke-miterlimit="10"></circle>
                                            </svg>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </ul>
                    </div>
                </div>
                <div class="c-page-loading loading-more" style="display: none;"><span></span></div>
            </div>
            <!-- 表单 -->
            <div class="mail-rule-edit" v-show="showForm">
                <div class="filter-condition">
                    <div class="mail-config-header">
                        <h3 class="config-page-title">新建收发件规则</h3><span class="tips">设置过滤条件，方便对邮件进行快速的分类或处理</span>
                    </div>
                    <form class="v-form v-form-vertical">
                        <div class="mail-rule-edit-content">
                            <div class="basic-info">
                                <h4 class="section-title">基本信息</h4>
                                <div class="field-item" name="type" component="ui-radio-group"
                                    component-props="[object Object]">
                                    <div class="content">
                                        <div class="v-form-item">
                                            <div class="v-form-item-label">
                                                <p class="label">规则类型</p>
                                            </div>
                                            <el-radio-group v-model="ruleData.ruleType">
                                                <el-radio :label="1">收件规则</el-radio>
                                                <el-radio :label="2">发件规则</el-radio>
                                            </el-radio-group>
                                        </div>
                                    </div>
                                </div>
                                <div class="field-item rule-name" name="rule_name" component="ui-input"
                                    component-props="[object Object]">
                                    <div class="content">
                                        <div class="v-form-item">
                                            <div class="v-form-item-label">
                                                <p class="label">规则名称</p>
                                            </div>
                                            <el-input style="width:480px" :maxlength="256" placeholder="请输入"
                                                v-model="ruleData.ruleName"></el-input>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="action-rule-info">
                                <h4 class="section-title">执行规则</h4>
                                <div class="field-item" name="condition" component="ui-radio-group"
                                    component-props="[object Object]">
                                    <div class="content">
                                        <div class="v-form-item v-form-item--required">
                                            <div class="v-form-item-label">
                                                <p class="label">执行条件 <span class="tips">收到的邮件包含以下信息，则执行相应操作</span>
                                                <div class="mm-popover">
                                                    <div><i class="m-icon icon-nav-help"></i></div>
                                                </div>
                                                </p>
                                            </div>
                                            <el-radio-group v-model="ruleData.executionCondition">
                                                <el-radio :label="1">满足以下所有条件</el-radio>
                                                <el-radio :label="2">满足以下任一条件</el-radio>
                                            </el-radio-group>

                                        </div>
                                        <div class="mail-operation field-block">
                                            <div class="item-value" v-for="(item, index) in oddList" :key="index">
                                                <div class="mail-rule-edit-filterSettings-item">
                                                    <span class="num">{{ index + 1 }}</span>
                                                    <el-select style="width:240px;margin-left:12px;"
                                                        v-model="item.oddsFirst">
                                                        <el-option label="如果发件人" :value="1"></el-option>
                                                        <el-option label="如果收件人" :value="2"></el-option>
                                                        <el-option label="如果抄送人" :value="3"></el-option>
                                                        <el-option label="如果主题中" :value="4"></el-option>
                                                        <el-option label="如果正文中（不包含引用文）" :value="5"></el-option>
                                                    </el-select>
                                                    <el-select style="width:240px;margin-left:12px;"
                                                        v-model="item.oddsSecond">
                                                        <el-option v-for="(opt, idx) in oddsOption(item.oddsFirst).option"
                                                            :key="idx" :value="opt.value" :label="opt.label"></el-option>
                                                    </el-select>
                                                    <el-input style="width:240px;margin-left:12px;" v-model="item.oddsThird"
                                                        :placeholder="oddsOption(item.oddsFirst).placeholder">
                                                    </el-input>
                                                    <div class="mail-rule-edit-filterSettings-btn pointer">
                                                        <span class="okki-icon-wrap" color="#7A8599"
                                                            v-if="oddList.length !== 1"
                                                            @click="onOddsReduce(item.id)">&ZeroWidthSpace;
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                                                                viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
                                                                fill="#7A8599">
                                                                <path d="M8 11a1 1 0 100 2h8a1 1 0 100-2H8z"></path>
                                                                <path fill-rule="evenodd" clip-rule="evenodd"
                                                                    d="M6.444 3.685a10 10 0 1111.112 16.63A10 10 0 016.444 3.685zm1.111 14.967a8 8 0 108.89-13.305 8 8 0 00-8.89 13.305z">
                                                                </path>
                                                            </svg>
                                                        </span>
                                                        <span v-if="oddList.length === index + 1" class="okki-icon-wrap"
                                                            color="#7A8599" @click="onOddsAdd">&ZeroWidthSpace;
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                                                                viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
                                                                fill="#7A8599">
                                                                <path fill-rule="evenodd" clip-rule="evenodd"
                                                                    d="M12 2a10 10 0 100 20 10 10 0 000-20zm0 18a8 8 0 110-16.001A8 8 0 0112 20z">
                                                                </path>
                                                                <path
                                                                    d="M13 8a1 1 0 10-2 0v3H8a1 1 0 100 2h3v3a1 1 0 102 0v-3h3a1 1 0 100-2h-3V8z">
                                                                </path>
                                                            </svg>
                                                        </span>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="field-item" name="trash_flag" component="ui-radio-group" trigger-type="onBlur"
                                    component-props="[object Object]">
                                    <div class="content">
                                        <div class="v-form-item v-form-item--required">
                                            <div class="v-form-item-label">
                                                <p class="label">执行操作</p>
                                            </div>
                                            <el-radio-group v-model="ruleData.operate">
                                                <el-radio :label="0">执行以下操作</el-radio>
                                                <el-radio :label="1">移动到【已删除邮件】</el-radio>
                                            </el-radio-group>

                                        </div>
                                        <div class="operation-list block field-block">
                                            <div class="item-value">
                                                <el-checkbox v-model="ruleData.picker">打钉</el-checkbox>
                                            </div>
                                            <div class="item-value">
                                                <el-checkbox v-model="ruleData.read">标记为【已读】</el-checkbox>
                                            </div>
                                            <div class="item-value">
                                                <el-checkbox v-model="ruleData.tag">标记为</el-checkbox>
                                                <el-select style="width:240px;margin-left:6px;" v-model="ruleData.selectTag"
                                                    placeholder="请选择分类">
                                                </el-select>
                                            </div>
                                            <div class="item-value">
                                                <el-checkbox v-model="ruleData.tag">移动到</el-checkbox>
                                                <el-select style="width:240px;margin-left:6px;" v-model="ruleData.selectTag"
                                                    placeholder="请选择文件夹">
                                                </el-select>
                                            </div>
                                            <div class="item-value review-role-wrap">
                                                <el-checkbox v-model="ruleData.transfer">转发至</el-checkbox>
                                                <el-input style="width:240px;margin-left:6px;"
                                                    v-model="ruleData.inputTransfer" placeholder="请输入邮箱地址"></el-input>
                                            </div>
                                            <div class="item-value mark-pending review-role-wrap">
                                                <div class="filter-name">
                                                    <el-checkbox
                                                        v-model="ruleData.marker">标记为【待处理邮件】并设置稍后处理时间为：</el-checkbox>
                                                    <div class="mark-pending-radio special-radio">
                                                        <el-radio-group v-model="ruleData.emailReceipt">
                                                            <el-radio :label="1">邮件接收时间</el-radio>
                                                            <el-radio :label="2">邮件接收时间之后的第</el-radio>
                                                        </el-radio-group>
                                                        <span style="padding-left: 30px;">
                                                            <el-input-number v-model="ruleData.receiptDay"
                                                                controls-position="right"></el-input-number>
                                                        </span>
                                                        <span style="padding: 0px 8px;">天</span>
                                                        <el-time-picker v-model="ruleData.receiptTime"
                                                            placeholder="请选择"></el-time-picker>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="item-value review-role-wrap">
                                                <div class="filter-name auto-reply-box">
                                                    <div class="auto-reply-filter">
                                                        <span class="auto-reply-label">
                                                            <el-checkbox v-model="ruleData.autoReply">自动回复</el-checkbox>
                                                            <span class="tips">若 4 天内收到同一邮箱的多封邮件，系统只进行一次自动回复</span>
                                                        </span>
                                                        <div class="editor-box">
                                                            <Toolbar ref="editorInstance"
                                                                style="border-bottom: 1px solid #ccc" :editor="editor"
                                                                :defaultConfig="toolbarConfig" />
                                                            <!-- 编辑器 -->
                                                            <Editor ref="editorInstance"
                                                                style="height:200px; overflow-y: hidden"
                                                                v-model="ruleData.replyContent" @onCreated="onCreated"
                                                                mode="default" />
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="field-item special-radio" name="history_flag" component="ui-radio-group"
                                    components-props="[object Object]">
                                    <div class="content">
                                        <div class="v-form-item v-form-item--required" components-props="[object Object]">
                                            <div class="v-form-item-label">
                                                <p class="label">应用于历史邮件 <span class="okki-icon-wrap">&ZeroWidthSpace;<svg
                                                            xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                            viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
                                                            fill="currentColor">
                                                            <path fill-rule="evenodd" clip-rule="evenodd"
                                                                d="M4 12a8 8 0 1116 0 8 8 0 01-16 0zm8-10C6.477 2 2 6.477 2 12s4.477 10 10 10 10-4.477 10-10S17.523 2 12 2zm1.2 4.75a1.25 1.25 0 11-2.5 0 1.25 1.25 0 012.5 0zM11.2 9a1 1 0 100 2v5h-.9a1 1 0 100 2h3.5a1 1 0 100-2h-.6v-6a1 1 0 00-1-1h-1z">
                                                            </path>
                                                        </svg></span></p>
                                            </div>
                                            <el-radio-group v-model="ruleData.historyEmail">
                                                <el-radio :label="1">是</el-radio>
                                                <el-radio :label="0">否</el-radio>
                                            </el-radio-group>
                                        </div>
                                        <div class="item-value history-folder-id-selector field-block"
                                            style="display: none;">
                                            <div class="mm-radio-group">
                                                <label class="mm-radio"><input name="mm-radio-group-291" type="radio"
                                                        value="1"><span class="mm-radio-faux"><span
                                                            class="mm-radio-input"></span><span
                                                            class="mm-radio-label">针对收件箱的历史邮件
                                                        </span></span></label><label class="mm-radio"><input
                                                        name="mm-radio-group-291" type="radio" value="-1"><span
                                                        class="mm-radio-faux"><span class="mm-radio-input"></span><span
                                                            class="mm-radio-label">针对收件箱及所有文件夹的历史邮件（不包括已删除）
                                                        </span></span></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="field-item" component="ui-select" name="user_mail_id"
                                    component-props="[object Object]">
                                    <div class="content">
                                        <div class="v-form-item">
                                            <div class="v-form-item-label">
                                                <p class="label">执行邮箱 <span class="tips">选择「全部邮箱」后，无论绑定邮箱是否更改，都启用该规则</span>
                                                </p>
                                            </div>

                                            <el-select v-model="ruleData.executeEmail">
                                            </el-select>
                                        </div>
                                    </div>
                                </div>
                                <div class="field-item special-radio" name="execute_next" component="ui-radio-group">
                                    <div class="content">
                                        <div class="v-form-item v-form-item--required">
                                            <div class="v-form-item-label">
                                                <p class="label">其他收件规则</p>
                                            </div>
                                            <el-radio-group v-model="ruleData.otherRule">
                                                <el-radio :label="1">继续执行</el-radio>
                                                <el-radio :label="0">不再执行<span
                                                        class="tips">一旦邮件满足了本条规则，将不再执行后续收件规则。</span></el-radio>
                                            </el-radio-group>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="btn-bottom">
                                <div class="mm-space mm-space__horizontal">
                                    <div class="mm-space-item" style="margin-right: 16px;"><button type="button"
                                            class="mm-button" @click="onHideForm"><span>取消</span></button></div>
                                    <div class="mm-space-item"><button type="button"
                                            class="mm-button mm-button__primary"><span>保存</span></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
import { Editor, Toolbar } from "@wangeditor/editor-for-vue";
import { getDispatcherRuleList, updateDispatcherRuleInfo, dispatcherRuleInfo, deleteDispatcherRuleInfo } from '@/api/email/dispatcherRules'
export default {
    components: {
        Editor,
        Toolbar,
    },
    data() {
        return {
            showForm: false,
            list: [],
            ruleMap: {
                1: '收件规则',
                2: '发件规则'
            },
            ruleData: {
                id: '',
                ruleType: 1,
                ruleName: '',
                executionCondition: 1,
                operate: 0,
                picker: false,
                read: false,
                tag: false,
                selectTag: '',
                move: false,
                selectMove: '',
                transfer: false,
                inputTransfer: '',
                marker: false,
                emailReceipt: null,
                receiptDay: 2,
                receiptTime: new Date(0, 0, 0, 9, 0),
                autoReply: false,
                replyContent: '',
                historyEmail: null,
                executeEmail: '',
                otherRule: null,
            },
            oddList: [
                {
                    id: 1,
                    oddsFirst: 1,
                    oddsSecond: 1,
                    oddsThird: '',
                }
            ],
            editor: null,
            toolbarConfig: {
                excludeKeys: [
                    'group-video',
                    'group-image'
                ]
            },
        }
    },
    mounted() {
        this.getList()
    },
    methods: {
        async getList() {
            try {
                const res = await getDispatcherRuleList()
                if (res.code === 200) {
                    this.list = res.data.map(val => {
                        val.status = Boolean(val.status)
                        return val
                    })
                }
            } catch { }
        },
        async handleStatus(item) {
            try {
                const res = await updateDispatcherRuleInfo({
                    id: item.id,
                })
                if (res.code === 200) {
                    this.getList()
                }
            } catch { }
        },
        async onEdit(id) {
            try {
                const res = await dispatcherRuleInfo(id)
                if (res.code === 200) {
                    this.ruleData = res.data
                    this.showForm = true
                }
            } catch { }
        },
        onDelete(id) {
            this.$confirm('是否删除?', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                roundButton: true,
                beforeClose: async (action, instance, done) => {
                    if (action === 'confirm') {
                        instance.confirmButtonLoading = true;
                        instance.confirmButtonText = '执行中...';
                        try {
                            const res = await deleteDispatcherRuleInfo({ id: id }).finally(() => {
                                instance.confirmButtonLoading = false
                                done()
                            })
                            if (res.code === 200) {
                                this.$message.success('删除成功')
                                this.getList()
                            }
                        } catch {
                        }
                    } else {
                        done();
                    }
                }
            }).then(_action => {
            });
        },
        onCreated(editor) {
            let that = this
            this.editor = Object.seal(editor);
            this.editor.getConfig().MENU_CONF['uploadImage'] = {
                fieldName: 'file',
                server: '/common/upload',
                maxFileSize: 500 * 1024, //500kb
                base64LimitSize: 500 * 1024,
                allowedFileTypes: ['image/*'],
                onError(file, err, _res) {
                    let errType = ''
                    err?.message?.includes('500 KB') && (errType = 'picture')
                    switch (errType) {
                        case 'picture':
                            that.$message.error('邮件图片不能大于 500K')
                            break;
                        default:
                            break;
                    }
                }
            }
        },

        oddsOption(oddsFirst) {
            let result = {
                option: [],
                placeholder: ''
            }
            const optionData = [
                {
                    value: 1,
                    label: '包含'
                },
                {
                    value: 2,
                    label: '不包含'
                },
                {
                    value: 3,
                    label: '隶属于'
                },
                {
                    value: 4,
                    label: '等于'
                },
                {
                    value: 5,
                    label: '不等于'
                },
            ]
            switch (oddsFirst) {
                case 1:
                    result.option = optionData.filter(val => [1, 2, 3, 4, 5].includes(val.value))
                    result.placeholder = '例如：test、@xiaomcn.cn'
                    break;
                case 2:
                    result.option = optionData.filter(val => [1, 2, 4, 5].includes(val.value))
                    result.placeholder = '例如：test、@xiaomcn.cn'
                    break;
                case 3:
                    result.option = optionData.filter(val => [1, 2, 4, 5].includes(val.value))
                    result.placeholder = '例如：test、@xiaomcn.cn'
                    break;
                case 4:
                    result.option = optionData.filter(val => [1, 2].includes(val.value))
                    result.placeholder = '例如：test、@xiaomcn.cn'
                    break;
                case 5:
                    result.option = optionData.filter(val => [1, 2].includes(val.value))
                    result.placeholder = '例如：price'
                    break;
                default:
                    break;
            }
            return result
        },
        onOddsAdd() {
            this.oddList.push({
                id: +new Date(),
                oddsFirst: 1,
                oddsSecond: 1,
                oddsThird: '',
            })
        },
        onOddsReduce(id) {
            this.oddList = this.oddList.filter(val => val.id !== id)
        },
        onShowForm(type, data) {
            this.showForm = true
        },
        onHideForm() {
            this.showForm = false
        }
    }
}
</script>

<style lang="scss" scoped>
@import url('./css/index.css');

.dispatcher-page {
    width: 100%;
    overflow: hidden;
    position: relative;
}

::v-deep .item-value .editor-box {
    z-index: 2000 !important;
}
</style>
<style src="@wangeditor/editor/dist/css/style.css"></style>
