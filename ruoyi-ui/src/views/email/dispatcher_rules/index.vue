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
                        <h3 class="config-page-title">{{ ruleData.id ? '编辑' : '新建' }}收发件规则</h3><span
                            class="tips">设置过滤条件，方便对邮件进行快速的分类或处理</span>
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
                                            <el-radio-group v-model="ruleData.executeCondition">
                                                <el-radio :label="1">满足以下所有条件</el-radio>
                                                <el-radio :label="2">满足以下任一条件</el-radio>
                                            </el-radio-group>

                                        </div>
                                        <div class="mail-operation field-block">
                                            <div class="item-value" v-for="(item, index) in executeConditionContent"
                                                :key="index">
                                                <div class="mail-rule-edit-filterSettings-item">
                                                    <span class="num">{{ index + 1 }}</span>
                                                    <el-select style="width:240px;margin-left:12px;"
                                                        v-model="item.columnName" @change="() => item.conditionType = 1">
                                                        <el-option label="如果发件人" value="sender"></el-option>
                                                        <el-option label="如果收件人" value="recipient"></el-option>
                                                        <el-option label="如果抄送人" value="cc"></el-option>
                                                        <el-option label="如果主题中" value="subject"></el-option>
                                                        <el-option label="如果正文中（不包含引用文）" value="body"></el-option>
                                                    </el-select>
                                                    <el-select style="width:240px;margin-left:12px;"
                                                        v-model="item.conditionType" @change="() => {
                                                            item.value = '';
                                                            item.packetId = '';
                                                        }">
                                                        <el-option
                                                            v-for="(opt, idx) in conditionTypeOption(item.columnName)"
                                                            :key="idx" :value="opt.value" :label="opt.label"></el-option>
                                                    </el-select>
                                                    <el-input v-if="item.conditionType !== 3"
                                                        style="width:240px;margin-left:12px;" v-model="item.value"
                                                        :placeholder="oddsPlaceholder(item.columnName, item.conditionType)">
                                                    </el-input>
                                                    <el-select v-else style="width:240px;margin-left:12px;"
                                                        v-model="item.value">
                                                        <el-option label="私海" :value="1"></el-option>
                                                        <el-option label="公海" :value="2"></el-option>
                                                    </el-select>
                                                    <el-select-tree v-if="item.conditionType === 3 && item.value === 1"
                                                        placeholder="请选择" style="width:240px;margin-left:12px;"
                                                        v-model="item.packetId" :data="packetOption"
                                                        :props="{ value: 'id', label: 'name' }" :check-strictly="true">
                                                    </el-select-tree>
                                                    <div class="mail-rule-edit-filterSettings-btn pointer">
                                                        <span class="okki-icon-wrap" color="#7A8599"
                                                            v-if="executeConditionContent.length !== 1"
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
                                                        <span v-if="executeConditionContent.length === index + 1"
                                                            class="okki-icon-wrap" color="#7A8599"
                                                            @click="onOddsAdd">&ZeroWidthSpace;
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
                                            <el-radio-group v-model="ruleData.executeOperation">
                                                <el-radio :label="0">执行以下操作</el-radio>
                                                <el-radio :label="1">移动到【已删除邮件】</el-radio>
                                            </el-radio-group>

                                        </div>
                                        <div class="operation-list block field-block">
                                            <div class="item-value">
                                                <el-checkbox v-model="ruleData.fixedFlag">打钉</el-checkbox>
                                            </div>
                                            <div class="item-value">
                                                <el-checkbox v-model="ruleData.readFlag">标记为【已读】</el-checkbox>
                                            </div>
                                            <div class="item-value">
                                                <el-checkbox v-model="ruleData.folderFlag">移动到</el-checkbox>
                                                <el-select-tree placeholder="选择目标文件夹" class="ml-6" style="width:240px;"
                                                    v-model="ruleData.folderId" :data="folderOption"
                                                    :props="{ value: 'id', label: 'name' }" :check-strictly="true">
                                                </el-select-tree>
                                            </div>
                                            <div class="item-value review-role-wrap">
                                                <el-checkbox v-model="ruleData.forwardToFlag">转发至</el-checkbox>
                                                <el-input style="width:240px;margin-left:6px;" v-model="ruleData.forwardTo"
                                                    placeholder="请输入邮箱地址"></el-input>
                                            </div>
                                            <div class="item-value mark-pending review-role-wrap">
                                                <div class="filter-name">
                                                    <el-checkbox
                                                        v-model="ruleData.pendingFlag">标记为【待处理邮件】并设置稍后处理时间为：</el-checkbox>
                                                    <div class="mark-pending-radio special-radio">
                                                        <el-radio-group v-model="ruleData.pendingType">
                                                            <el-radio :label="1">邮件接收时间</el-radio>
                                                            <el-radio :label="2">邮件接收时间之后的第</el-radio>
                                                        </el-radio-group>
                                                        <span style="padding-left: 30px;">
                                                            <el-input-number v-model="ruleData.pendingDay"
                                                                controls-position="right"></el-input-number>
                                                        </span>
                                                        <span style="padding: 0px 8px;">天</span>
                                                        <el-time-picker v-model="ruleData.pendingTime"
                                                            value-format="HH:mm:ss" placeholder="请选择"></el-time-picker>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="item-value review-role-wrap">
                                                <div class="filter-name auto-reply-box">
                                                    <div class="auto-reply-filter">
                                                        <span class="auto-reply-label">
                                                            <el-checkbox
                                                                v-model="ruleData.autoResponseFlag">自动回复</el-checkbox>
                                                            <span class="tips">若 4 天内收到同一邮箱的多封邮件，系统只进行一次自动回复</span>
                                                        </span>
                                                        <div class="editor-box">
                                                            <Toolbar ref="editorInstance"
                                                                style="border-bottom: 1px solid #ccc" :editor="editor"
                                                                :defaultConfig="toolbarConfig" />
                                                            <!-- 编辑器 -->
                                                            <Editor ref="editorInstance"
                                                                style="height:200px; overflow-y: hidden"
                                                                v-model="ruleData.autoResponse" @onCreated="onCreated"
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
                                                <p class="label">应用于历史邮件
                                                    <el-tooltip content="系统一律不会对历史邮件执行“转发至邮箱”和“自动回复”操作" placement="top">
                                                        <span class="okki-icon-wrap">&ZeroWidthSpace;<svg
                                                                xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                                viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
                                                                fill="currentColor">
                                                                <path fill-rule="evenodd" clip-rule="evenodd"
                                                                    d="M4 12a8 8 0 1116 0 8 8 0 01-16 0zm8-10C6.477 2 2 6.477 2 12s4.477 10 10 10 10-4.477 10-10S17.523 2 12 2zm1.2 4.75a1.25 1.25 0 11-2.5 0 1.25 1.25 0 012.5 0zM11.2 9a1 1 0 100 2v5h-.9a1 1 0 100 2h3.5a1 1 0 100-2h-.6v-6a1 1 0 00-1-1h-1z">
                                                                </path>
                                                            </svg>
                                                        </span>
                                                    </el-tooltip>
                                                </p>
                                            </div>
                                            <el-radio-group v-model="ruleData.applyToHistoryMailFlag"
                                                @change="value => ruleData.applyToHistoryMailTrueType = (!value ? '' : 1)">
                                                <el-radio :label="1">是</el-radio>
                                                <el-radio :label="0">否</el-radio>
                                            </el-radio-group>
                                        </div>
                                        <div class="item-value history-folder-id-selector field-block"
                                            v-if="ruleData.applyToHistoryMailFlag === 1">
                                            <el-radio-group v-model="ruleData.applyToHistoryMailTrueType">
                                                <el-radio :label="1">针对收件箱的历史邮件</el-radio>
                                                <el-radio :label="2">针对收件箱及所有文件夹的历史邮件（不包括已删除）</el-radio>
                                            </el-radio-group>
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

                                            <el-select v-model="ruleData.executeTaskId">
                                                <el-option v-for="(email, index) in emailOption" :key="index"
                                                    :value="email.id" :label="email.account"></el-option>
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
                                            <el-radio-group v-model="ruleData.otherSendingRules">
                                                <el-radio :label="1">继续执行</el-radio>
                                                <el-radio :label="0">不再执行
                                                    <span class="tips">一旦邮件满足了本条规则，将不再执行后续收件规则。</span>
                                                </el-radio>
                                            </el-radio-group>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="btn-bottom">
                                <div class="mm-space mm-space__horizontal">
                                    <div class="mm-space-item" style="margin-right: 16px;"><button type="button"
                                            class="mm-button" @click="onHideForm"><span>取消</span></button></div>
                                    <div class="mm-space-item"><button type="button" class="mm-button mm-button__primary"
                                            @click="onSave"><span>保存</span></button>
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
import { getEmailTaskList, getImportFolderList } from '@/api/email/emailImport'
import { getDispatcherRuleList, updateDispatcherRuleInfo, dispatcherRuleInfo, deleteDispatcherRuleInfo, getSetPacketList, addDispatcherRule, editDispatcherRule } from '@/api/email/dispatcherRules'
import { deepClone } from '@/utils'
const initData = {
    id: '',
    ruleType: 1,
    ruleName: '',
    executeCondition: 1,
    executeOperation: 0,
    executeConditionContent: [],
    fixedFlag: false,
    readFlag: false,
    folderFlag: false,
    folderId: '',
    forwardToFlag: false,
    forwardTo: '',
    pendingFlag: false,
    pendingType: null,
    pendingDay: 2,
    pendingTime: '09:00:00',
    autoResponseFlag: false,
    autoResponse: '',
    applyToHistoryMailFlag: null,
    applyToHistoryMailTrueType: null,
    executeTaskId: '',
    otherSendingRules: null,
    status: 0,
    orderNum: '',
}
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
            ruleData: deepClone(initData),
            emailOption: [],
            folderOption: [],
            packetOption: [],
            executeConditionContent: [
                {
                    oddId: +new Date(),
                    andOr: 'and',
                    columnName: 'sender',
                    conditionType: 1,
                    value: '',
                    packetId: ''
                }
            ],
            editor: null,
            toolbarConfig: {
                excludeKeys: [
                    'group-video',
                    'group-image'
                ]
            },
            flagFields: ['fixedFlag', 'readFlag', 'folderFlag', 'forwardToFlag', 'pendingFlag', 'autoResponseFlag', 'applyToHistoryMailFlag']
        }
    },
    mounted() {
        this.init()
    },
    methods: {
        init() {
            this.getList()
            this.getEmailOption()
            this.getFolderOption()
            this.getGroupList()
        },
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
        async getEmailOption() {
            try {
                const res = await getEmailTaskList()
                if (res.code === 200) {
                    this.emailOption = res.data
                }
            } catch { }
        },
        async getFolderOption() {
            try {
                const res = await getImportFolderList()
                if (res.code === 200) {
                    this.folderOption = res.data
                }
            } catch { }
        },
        // 分组选项
        async getGroupList() {
            try {
                const res = await getSetPacketList()
                if (res.code === 200) {
                    this.packetOption = res.data
                }
            } catch {
            }
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
                    Object.keys(this.ruleData).forEach((value) => {
                        if (this.flagFields.includes(value)) {
                            this.ruleData[value] = +(this.ruleData[value])
                        }
                    })

                    this.executeConditionContent = this.generateExecuteCondition(this.ruleData?.executeConditionContent)
                    this.showForm = true
                }
            } catch (e) {
                console.error(e.message);
            }
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
                            const res = await deleteDispatcherRuleInfo({ id }).finally(() => {
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

        conditionTypeOption(columnName) {
            let option = []
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
            switch (columnName) {
                case 'sender':
                    option = optionData.filter(val => [1, 2, 3, 4, 5].includes(val.value))
                    break;
                case 'recipient':
                    option = optionData.filter(val => [1, 2, 4, 5].includes(val.value))
                    break;
                case 'cc':
                    option = optionData.filter(val => [1, 2, 4, 5].includes(val.value))
                    break;
                case 'subject':
                    option = optionData.filter(val => [1, 2].includes(val.value))
                    break;
                case 'body':
                    option = optionData.filter(val => [1, 2].includes(val.value))
                    break;
                default:
                    break;
            }
            return option
        },
        oddsPlaceholder(columnName, conditionType) {
            let placeholder = ''
            if (columnName === 'body') {
                return '例如：price'
            }
            if ([1, 2].includes(conditionType)) {
                placeholder = '例如：test、@xiaomcn.cn'
            } else if ([4, 5].includes(conditionType)) {
                placeholder = '例如：test@xiaomcn.cn'
            }
            return placeholder
        },
        onOddsAdd() {
            this.executeConditionContent.push({
                oddId: +new Date(),
                columnName: 'sender',
                conditionType: 1,
                value: '',
                packetId: ''
            })
        },
        onOddsReduce(id) {
            this.executeConditionContent = this.executeConditionContent.filter(val => val.oddId !== id)
        },
        async addReq(data) {
            try {
                const res = await addDispatcherRule(data)
                if (res.code === 200) {
                    this.$message.success('新增成功')
                    this.getList()
                    this.onHideForm()
                }
            } catch { }
        },
        async editReq(data) {
            try {
                const res = await editDispatcherRule(data)
                if (res.code === 200) {
                    this.$message.success('修改成功')
                    this.getList()
                    this.onHideForm()
                }
            } catch { }
        },
        onSave() {
            let data = {
                ...deepClone(this.ruleData)
            }
            Object.keys(data).forEach((value) => {
                if (this.flagFields.includes(value)) {
                    data[value] = Boolean(data[value])
                }
            })
            delete data.orderNum
            data.executeConditionContent = JSON.stringify(data.executeConditionContent)
            // 执行条件
            const { executeCondition } = data
            if (executeCondition) {
                let conditionMap = {
                    1: 'and',
                    2: 'or'
                }
                this.executeConditionContent.map(val => {
                    delete val.oddId
                    val.andOr = conditionMap[executeCondition]
                    return val
                })
                data.executeConditionContent = JSON.stringify(this.executeConditionContent)
            }

            if (!data.id) {
                delete data.id
                this.addReq(data)
            } else {
                this.editReq(data)
            }
        },
        onShowForm(type, data) {
            this.showForm = true
        },
        onHideForm() {
            this.ruleData = deepClone(initData)
            this.showForm = false
        },
        generateExecuteCondition(strArr) {
            const arr = JSON.parse(strArr)
            if (Array.isArray(arr) && arr.length) {
                return arr
            } else {
                return [{
                    oddId: +new Date(),
                    columnName: 'sender',
                    conditionType: 1,
                    value: '',
                    packetId: ''
                }]
            }
        },
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
