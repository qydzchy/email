<template>
    <div class="mail-plus-conventional config-page-item">
        <div class="mail-plus-conventional-content">
            <div class="conventional-content">
                <h3 class="config-page-title">常规</h3>
                <div class="section-title">账号</div>
                <div class="section-wrapper account">
                    <span class="label">默认邮箱:</span>
                    <div class="section-inner">
                        <span>
                            <el-select style="width:378px;" v-model="formData.defaultTaskId" :popper-append-to-body="false">
                                <el-option v-for="(email, index) in emailOption" :key="index" :value="email.id"
                                    :label="email.account"></el-option>
                            </el-select>
                        </span>
                        <div class="tips">
                            在绑定多个邮箱的情况下，发信时默认选择该邮箱。
                        </div>
                    </div>
                </div>
                <div class="section-title">显示</div>
                <div class="conventional-look">
                    <div class="section-wrapper" style="align-items: center">
                        <span class="label">每页显示邮件数量:</span>
                        <el-select style="width:378px;" v-model="formData.maxPerPage" :popper-append-to-body="false">
                            <el-option v-for="(count, index) in emailCountOption" :key="index" :value="count.value"
                                :label="count.label"></el-option>
                        </el-select>
                    </div>
                    <div class="section-wrapper">
                        <div class="label">邮件阅读模式:</div>
                        <div class="section-inner">
                            <el-radio-group v-model="formData.emailReadingModeFlag">
                                <el-radio :label="1">启用 (适合大屏用户，可快速切换邮件)</el-radio>
                                <el-radio :label="0">停用
                                    (适合小屏用户，更多空间查看邮件详情)</el-radio>
                            </el-radio-group>
                            <div class="tips" style="padding-left: 22px">
                                启用阅读模式后，查看邮件详情时，将保持左侧列表展示
                            </div>
                        </div>
                    </div>
                    <div class="section-wrapper">
                        <span class="label">移动/删除/举报后:</span>
                        <div class="section-inner">
                            <el-radio-group v-model="formData.moveDeleteReport">
                                <el-radio :label="1">阅读下一封邮件(推荐)</el-radio>
                                <el-radio :label="2">回到当前邮件列表</el-radio>
                            </el-radio-group>
                        </div>
                    </div>
                    <div class="section-wrapper">
                        <span class="label">语言:</span>
                        <div class="section-inner">
                            <el-radio-group v-model="formData.language">
                                <el-radio :label="1">简体中文
                                    <el-tooltip style="width:200px" placement="top"
                                        content="设置为中文时，回复转发将显示为「回复：」、「转发：」，引用内容也将用中文显示；">
                                        <span>
                                            <i class="el-icon-warning-outline"></i>
                                        </span>
                                    </el-tooltip>
                                </el-radio>
                                <el-radio :label="2">繁体中文
                                    <el-tooltip style="width:200px" placement="top"
                                        content="设置为繁体中文时，回复转发将显示为「回復：」、「轉寄：」，引用内容也将用繁体中文显示；">
                                        <span>
                                            <i class="el-icon-warning-outline"></i>
                                        </span>
                                    </el-tooltip>
                                </el-radio>
                                <el-radio :label="3">英文
                                    <el-tooltip style="width:200px" placement="top"
                                        content="设置为英文时，回复转发将显示为「Re：」、「Fwd：」，引用内容也将用英文显示。">
                                        <span>
                                            <i class="el-icon-warning-outline"></i>
                                        </span>
                                    </el-tooltip>
                                </el-radio>
                            </el-radio-group>
                        </div>
                    </div>
                    <div class="section-wrapper">
                        <span class="label">默认字体:</span>
                        <div class="section-inner">
                            <el-select style="width:378px;" v-model="formData.defaultFont" :popper-append-to-body="false">
                                <el-option v-for="(ff, index) in fontFamilyOption" :key="index" :value="ff"
                                    :label="ff"></el-option>
                            </el-select>
                            <div class="tips">
                                默认字体的最终显示依赖于收件人的操作系统与浏览器设置。
                            </div>
                        </div>
                    </div>
                    <div class="section-wrapper align-center">
                        <span class="label">文字大小:</span>
                        <el-select style="width:378px;" v-model="formData.fontSize" :popper-append-to-body="false">
                            <el-option v-for="(fs, index) in fontOption" :key="index" :value="fs" :label="fs"></el-option>
                        </el-select>
                    </div>
                    <div class="section-wrapper font-color">
                        <span class="label">文字颜色:</span>
                        <el-select style="width:378px;" v-model="formData.fontColor" placeholder="请选择"
                            :popper-append-to-body="false">
                            <el-option v-for="(color, index) in colorOption" :key="index" :value="color">
                                <span
                                    :style="{ width: '10px', height: '10px', display: 'inline-block', backgroundColor: color }"></span>
                                <span class="ml-4">{{ color }}</span>
                            </el-option>
                        </el-select>
                        <!-- <el-color-picker v-model="formData.fontColor" color-format="rgb"></el-color-picker> -->
                    </div>
                    <div class="section-wrapper">
                        <span class="label">群发箱视图 <el-tooltip style="width:200px" placement="top"
                                content="任务视图以群发为单位展示，邮件列表以邮件为单位展示">
                                <span>
                                    <i class="el-icon-question"></i>
                                </span>
                            </el-tooltip>
                            <span>:</span>
                        </span>

                        <div class="section-inner">
                            <el-radio-group v-model="formData.groupBoxView">
                                <el-radio :label="1">任务视图</el-radio>
                                <el-radio :label="2">邮件视图</el-radio>
                            </el-radio-group>
                        </div>
                    </div>
                    <div class="section-wrapper">
                        <span class="label">群发邮件展示:</span>
                        <div class="section-inner">
                            <el-radio-group v-model="formData.massEmailDisplay">
                                <el-radio :label="1">仅在群发箱</el-radio>
                                <el-radio :label="2">发件箱与群发箱</el-radio>
                            </el-radio-group>
                        </div>
                    </div>
                </div>
                <section class="section-block">
                    <h3>提醒</h3>
                    <div class="section-wrapper mail-remind">
                        <div class="label">提醒:</div>
                        <el-checkbox-group v-model="formData.remind">
                            <el-checkbox label="1">禁止新邮件到达时在页面弹出通知
                                <el-tooltip content="勾选后不再弹窗进行新邮件提醒" placement="top">
                                    <span class="okki-icon-wrap">
                                        &ZeroWidthSpace;<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                            viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
                                            fill="currentColor">
                                            <path fill-rule="evenodd" clip-rule="evenodd"
                                                d="M4 12a8 8 0 1116 0 8 8 0 01-16 0zm8-10C6.477 2 2 6.477 2 12s4.477 10 10 10 10-4.477 10-10S17.523 2 12 2zm1.2 4.75a1.25 1.25 0 11-2.5 0 1.25 1.25 0 012.5 0zM11.2 9a1 1 0 100 2v5h-.9a1 1 0 100 2h3.5a1 1 0 100-2h-.6v-6a1 1 0 00-1-1h-1z">
                                            </path>
                                        </svg>
                                    </span>
                                </el-tooltip>
                            </el-checkbox>
                            <el-checkbox label="2">禁止发送邮件在页面弹出附件提醒
                                <el-tooltip content="勾选后不再弹窗进行附件提醒" placement="top">
                                    <span class="okki-icon-wrap">
                                        &ZeroWidthSpace;<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                            viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
                                            fill="currentColor">
                                            <path fill-rule="evenodd" clip-rule="evenodd"
                                                d="M4 12a8 8 0 1116 0 8 8 0 01-16 0zm8-10C6.477 2 2 6.477 2 12s4.477 10 10 10 10-4.477 10-10S17.523 2 12 2zm1.2 4.75a1.25 1.25 0 11-2.5 0 1.25 1.25 0 012.5 0zM11.2 9a1 1 0 100 2v5h-.9a1 1 0 100 2h3.5a1 1 0 100-2h-.6v-6a1 1 0 00-1-1h-1z">
                                            </path>
                                        </svg>
                                    </span>
                                </el-tooltip>
                            </el-checkbox>
                        </el-checkbox-group>
                    </div>
                </section>
                <section id="sign" class="section-block">
                    <h3>签名</h3>
                    <div class="section-wrapper">
                        <span class="label">个性签名:</span>
                        <div class="section-inner">
                            <div>
                                <el-select style="width:378px;" v-model="formData.signatureId"
                                    :popper-append-to-body="false">
                                    <el-option v-for="(item, index) in signatureOption" :key="index" :label="item.title"
                                        :value="item.id"></el-option>
                                </el-select>
                                <span class="okki-icon-wrap ml-2 pointer" @click="showDialogSignature">&ZeroWidthSpace;
                                    <el-tooltip content="添加新的签名" placement="top">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24"
                                            aria-hidden="true" class="okki-svg-icon" fill="currentColor">
                                            <path fill-rule="evenodd" clip-rule="evenodd"
                                                d="M12 2a10 10 0 100 20 10 10 0 000-20zm0 18a8 8 0 110-16.001A8 8 0 0112 20z">
                                            </path>
                                            <path
                                                d="M13 8a1 1 0 10-2 0v3H8a1 1 0 100 2h3v3a1 1 0 102 0v-3h3a1 1 0 100-2h-3V8z">
                                            </path>
                                        </svg>
                                    </el-tooltip>
                                </span>
                            </div>
                            <div class="sign-box">
                                <div class="sign-content">
                                    <div v-html="signatureContent"></div>
                                </div>
                                <div class="buttonset">
                                    <button class="m-btn"
                                        @click="showDialogSignature(formData.signatureId)"><span>编辑</span></button><button
                                        class="m-btn gray">
                                        <span @click="deleteSignature(formData.signatureId)">删除</span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
                <div class="section-title">
                    写信
                    <span class="font-normal text-medium-8">(更改设置后将在下次打开邮件时生效)</span>
                </div>
                <div>
                    <div class="section-wrapper">
                        <span class="label">写信：</span>
                        <div class="section-inner">
                            <el-checkbox v-model="writeLetterList[0]">正文拼写检查 ( 编辑器会以红色波浪线提示正文中的拼写错误) </el-checkbox>
                            <el-checkbox v-model="writeLetterList[1]"
                                @change="(value) => value && !writeLetterList[2]">新窗口写信 ( 将在下次打开邮件时生效)</el-checkbox>
                            <el-checkbox v-show="!writeLetterList[1]" v-model="writeLetterList[2]"> 发送后返回上一页 </el-checkbox>
                        </div>
                    </div>
                    <div class="section-wrapper">
                        <span class="label"> 粘贴格式: </span>
                        <div class="section-inner">
                            <el-radio-group v-model="formData.pasteFormat">
                                <el-radio :label="1">粘贴格式</el-radio>
                                <el-radio :label="2">始终保持格式</el-radio>
                                <el-radio :label="3">每次粘贴提醒</el-radio>
                            </el-radio-group>
                        </div>
                    </div>
                    <div class="section-wrapper">
                        <span class="label">单个邮箱设置:</span>
                        <div class="mail-box">
                            <div class="mail-list-header">
                                <div class="mail-list" :style="translateLeft">
                                    <div class="mail-item pointer" :class="{ 'active': curSet === index }"
                                        v-for="(item, index) in emailSetList" :key="index" @click="curSet = index">
                                        <p :title="item.taskName" class="ellipsis">
                                        <div class="public-mail-tag-wrapper">
                                            <div class="public-mail-tag-content">
                                                {{ item.taskName }}
                                            </div>
                                        </div>
                                        </p>
                                    </div>
                                </div>
                                <div class="change-btn">
                                    <div class="change-btn-list">
                                        <i class="m-icon icon-left-thin" :class="{ 'disable': !curSet }"
                                            @click="curSet && curSet--"></i>
                                        <i class="m-icon icon-right-thin"
                                            :class="{ 'disable': curSet === emailSetList.length - 1 }"
                                            @click="curSet !== emailSetList.length - 1 && curSet++"></i>
                                    </div>
                                </div>
                            </div>
                            <div class="mail-content" v-if="emailSetList && emailSetList.length">
                                <div class="config-item">
                                    <div class="left">写信昵称</div>
                                    <div class="right">
                                        <input v-model="emailSetList[curSet].letterNickname" type="text"
                                            class="m-input nickname" />
                                    </div>
                                </div>
                                <div class="config-item">
                                    <div class="left">默认签名</div>
                                    <div class="right">
                                        <el-select style="width:378px;" v-model="emailSetList[curSet].defaultSignatureId"
                                            :popper-append-to-body="false">
                                            <el-option label="不使用" :value="0"></el-option>
                                            <el-option v-for="(item, index) in signatureOption" :key="index"
                                                :label="item.title" :value="item.id"></el-option>
                                        </el-select>
                                    </div>
                                </div>
                                <div class="config-item">
                                    <div class="left">回复/转发签名</div>
                                    <div class="right">
                                        <el-select style="width:378px;" v-model="emailSetList[curSet].replySignatureId"
                                            :popper-append-to-body="false">
                                            <el-option label="不使用" :value="0"></el-option>
                                            <el-option v-for="(item, index) in signatureOption" :key="index"
                                                :label="item.title" :value="item.id"></el-option>
                                        </el-select>
                                    </div>
                                </div>
                                <div class="config-item">
                                    <div class="left">
                                        <el-checkbox v-model="emailSetList[curSet].defaultCcFlag">默认抄送：</el-checkbox>
                                    </div>
                                    <div class="right">
                                        <input v-model="emailSetList[curSet].defaultCc" placeholder="请使用 ';' 分隔多个邮箱"
                                            class="m-input long-input" :disabled="!emailSetList[curSet].defaultCcFlag"
                                            type="text" />
                                    </div>
                                </div>
                                <div class="config-item">
                                    <div class="left">
                                        <el-checkbox v-model="emailSetList[curSet].defaultBccFlag">默认密送：</el-checkbox>
                                    </div>
                                    <div class="right">
                                        <input v-model="emailSetList[curSet].defaultBcc" placeholder="请使用 ';' 分隔多个邮箱"
                                            class="m-input long-input" :disabled="!emailSetList[curSet].defaultBccFlag"
                                            type="text" />
                                    </div>
                                </div>
                                <div class="config-item">
                                    <div class="left">是否追踪邮件</div>
                                    <div class="right">
                                        <el-radio-group v-model="emailSetList[curSet].traceFlag">
                                            <el-radio :label="1">是</el-radio>
                                            <el-radio :label="0">否</el-radio>
                                        </el-radio-group>
                                    </div>
                                </div>
                                <div class="config-item">
                                    <div class="left">是否回执</div>
                                    <div class="right">
                                        <el-radio-group v-model="emailSetList[curSet].returnReceiptFlag">
                                            <el-radio :label="1">是</el-radio>
                                            <el-radio :label="0">否</el-radio>
                                        </el-radio-group>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="section-title">外出自动回复</div>

                <div class="holiday-wrapper">
                    <div class="section-wrapper">
                        <span class="label">自动回复:</span>
                        <div class="section-inner">
                            <div>
                                <el-radio-group v-model="formData.autoResponseFlag">
                                    <el-radio :label="1">启用 (同一邮箱给你发送多封邮件时，4天内最多对该邮箱自动回复一次)</el-radio>
                                    <el-radio :label="0">停用</el-radio>
                                </el-radio-group>
                            </div>
                            <div style="display: flex;">
                                <div class="date-selector-item">
                                    <span class="date-label">开始时间</span>
                                    <el-date-picker v-model="formData.startTime" :disabled="formData.autoResponseFlag !== 1"
                                        clearable type="date" placeholder="选择日期">
                                    </el-date-picker>
                                </div>
                                <div class="date-selector-item">
                                    <span class="date-label">
                                        <el-checkbox v-model="formData.lastDayFlag">最后一天</el-checkbox>
                                    </span>
                                    <el-date-picker v-model="formData.lastDay" clearable
                                        :disabled="formData.autoResponseFlag !== 1 || !formData.lastDayFlag" type="date"
                                        placeholder="选择日期">
                                    </el-date-picker>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="section-wrapper">
                        <span class="label">
                            回复内容:
                        </span>
                        <div class="editor-box">
                            <Toolbar ref="editorInstance" style="border-bottom: 1px solid #ccc" :editor="editor"
                                :defaultConfig="toolbarConfig" />
                            <!-- 编辑器 -->
                            <Editor ref="editorInstance" style="height: 500px; overflow-y: hidden"
                                v-model="formData.reContent" @onCreated="onCreated" mode="default" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="btn-bottom">
                <div class="mm-space mm-space__horizontal">
                    <div class="mm-space-item" style="margin-right: 16px;">
                        <button type="button" class="mm-button" @click="onCancel">取消</button>
                    </div>
                    <div class="mm-space-item">
                        <button type="button" class="mm-button mm-button__primary" @click="onSave">保存</button>
                    </div>
                </div>
            </div>
        </div>
        <DialogSignature :dialogVisible.sync="dialogVisible" :signatureData="signatureData" @onCancel="hideSignatureDialog"
            @onConfirm="signatureDialogConfirm" />
    </div>
</template>

<script>
import { Editor, Toolbar } from "@wangeditor/editor-for-vue";
import DialogSignature from "./DialogSignature.vue";
import { getUsuallyInfo, getEmailTaskList, getSignatureList, deleteSignature, singleEmailSetting, editUsuallyInfo, editSingleEmailSetting } from '@/api/email/usually'
import { deepClone } from '@/utils'
import { fontSizeList, fontFamilyList, colors } from '@/constant/editorOption'
export default {
    components: {
        Editor,
        Toolbar,
        DialogSignature,
    },
    data() {
        return {
            formData: {
                id: '',
                defaultTaskId: '',
                maxPerPage: 20,
                emailReadingModeFlag: 0,
                moveDeleteReport: 1,
                language: 1,
                defaultFont: '宋体',
                fontSize: '16px',
                fontColor: '',
                groupBoxView: 1,
                massEmailDisplay: 1,
                remind: [],
                signatureId: '',
                writeLetter: '',
                pasteFormat: 1,
                autoResponseFlag: 1,
                startTime: '',
                lastDay: '',
                lastDayFlag: false,
                reContent: '',
            },
            writeLetterList: [false, false, false],
            signatureOption: [],
            emailOption: [],
            emailCountOption: [
                { value: 20, label: '20' },
                { value: 30, label: '30' },
                { value: 50, label: '50' },
                { value: 100, label: '100' },
            ],
            fontFamilyOption: fontFamilyList,
            fontOption: fontSizeList,
            colorOption: colors,
            dialogVisible: false,
            signatureData: {},
            curSet: 0,
            emailSetList: [],
            editor: null,
            toolbarConfig: {
                excludeKeys: [
                    'group-video'
                ]
            },
        }
    },
    computed: {
        translateLeft() {
            return this.curSet ? `margin-left:${(this.curSet - 1) * (-180)}px` : ''
        },
        signatureContent() {
            let content = ''
            this.signatureOption.forEach(val => {
                if (val.id === this.formData.signatureId) {
                    content = val.content
                }
            })
            return content
        },
    },
    mounted() {
        this.init()
    },
    methods: {
        init() {
            this.getSignatureOption()
            this.getEmailOption()
            this.getSettingList()
            this.getInfo()
        },
        async getEmailOption() {
            try {
                const res = await getEmailTaskList()
                if (res.code === 200) {
                    this.emailOption = res.data
                }
            } catch { }
        },
        async getSignatureOption() {
            try {
                const res = await getSignatureList()
                if (res.code === 200) {
                    this.signatureOption = res.data
                }
            } catch { }
        },
        async getSettingList() {
            try {
                const res = await singleEmailSetting()
                if (res.code === 200) {
                    this.emailSetList = res.data.map(val => {
                        val.defaultCcFlag = Boolean(val.defaultCcFlag)
                        val.defaultBccFlag = Boolean(val.defaultBccFlag)
                        return val
                    })
                }
            } catch { }
        },
        async getInfo() {
            try {
                const res = await getUsuallyInfo()
                if (res.code === 200) {
                    let data = res.data
                    data.remind = !data.remind ? [] : String(data.remind)?.split(',')
                    this.writeLetter = !data.writeLetter ? [] : String(data.writeLetter)?.split(',')
                    this.formData = { ...data }
                }
            } catch (e) {
                console.error(e.message);
            }
        },
        onCreated(editor) {
            let that = this
            this.editor = Object.seal(editor);
            this.editor.getConfig().MENU_CONF = {
                ...this.editor.getConfig().MENU_CONF,
                fontSize: {
                    fontSizeList
                },
                fontFamily: {
                    fontFamilyList
                },
                color: {
                    colors
                },
                uploadImage: {
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
            }
        },
        async showDialogSignature(id) {
            let data = {
                id: '',
                title: '',
                content: ''
            }
            if (id) {
                this.signatureOption.forEach(val => {
                    if (val.id === id) {
                        data = val
                    }
                })
            }
            this.signatureData = data
            this.dialogVisible = true
        },
        async deleteSignature(id) {
            try {
                const res = await deleteSignature({ id })
                if (res.code === 200) {
                    this.$message.success('删除成功')
                    this.formData.signatureId = this.signatureOption?.[0]?.id || ''
                    this.getSignatureOption()
                }
            } catch { }
        },
        hideSignatureDialog() {
            this.dialogVisible = false
            this.signatureData = {}
        },
        signatureDialogConfirm() {
            this.hideSignatureDialog()
            this.getSignatureOption()
        },
        // 保存配置
        async editInfoReq(data) {
            try {
                const res = await editUsuallyInfo(data)
                if (res.code === 200) {
                    this.$store.dispatch('emailSetting/GetUsuallyInfo')
                    let emailData = deepClone(this.emailSetList)
                    emailData = emailData.map(val => {
                        val.defaultBccFlag = +val.defaultBccFlag
                        val.defaultCcFlag = +val.defaultCcFlag
                        return val
                    })
                    // 保存单个邮箱设置
                    const emailSetting = await editSingleEmailSetting(emailData)
                    if (emailSetting.code === 200) {
                        this.$message.success('修改成功')
                    }
                }
            } catch (e) {
                console.error(e.message);
            }
        },
        onSave() {
            const data = {
                ...this.formData,
            }
            data.remind = data.remind.join(',')
            let writeLetter = deepClone(this.writeLetter)
            data.writeLetter = writeLetter.map(val => +val).join(',')
            this.editInfoReq(data)
        },
        onCancel() {
            if (window.history.length <= 1) {
                this.$router.push('/email/index?type=default')
                return false
            } else {
                this.$router.go(-1)
            }
        },
    }
};
</script>

<style lang="scss" scoped>
@import url('./css/index.css');

.font-color {
    display: flex;
    align-items: center !important;

    ::v-deep .el-color-picker__trigger {
        width: 375px;

        .el-color-picker__icon {
            left: 94%;
        }
    }
}

::v-deep .holiday-wrapper .editor-box {
    z-index: 1002 !important;
}
</style>
<style src="@wangeditor/editor/dist/css/style.css"></style>
