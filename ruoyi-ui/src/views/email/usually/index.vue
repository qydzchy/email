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
                            <el-select style="width:378px;" v-model="formData.defaultEmail">
                                <el-option v-for="(email, index) in emailOption" :key="index" :value="email.value"
                                    :label="email.label"></el-option>
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
                        <el-select style="width:378px;" v-model="formData.emailCount">
                            <el-option v-for="(count, index) in emailCountOption" :key="index" :value="count.value"
                                :label="count.label"></el-option>
                        </el-select>
                    </div>
                    <div class="section-wrapper">
                        <div class="label">邮件阅读模式:</div>
                        <div class="section-inner">
                            <el-radio-group v-model="formData.readMode">
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
                            <el-radio-group v-model="formData.handleAfter">
                                <el-radio :label="1">阅读下一封邮件(推荐)</el-radio>
                                <el-radio :label="0">回到当前邮件列表</el-radio>
                            </el-radio-group>
                        </div>
                    </div>
                    <div class="section-wrapper">
                        <span class="label">语言:</span>
                        <div class="section-inner">
                            <el-radio-group v-model="formData.language">
                                <el-radio label="zh">简体中文
                                    <el-tooltip style="width:200px" placement="top"
                                        content="设置为中文时，回复转发将显示为「回复：」、「转发：」，引用内容也将用中文显示；">
                                        <span>
                                            <i class="el-icon-warning-outline"></i>
                                        </span>
                                    </el-tooltip>
                                </el-radio>
                                <el-radio label="zh-TW">繁体中文
                                    <el-tooltip style="width:200px" placement="top"
                                        content="设置为繁体中文时，回复转发将显示为「回復：」、「轉寄：」，引用内容也将用繁体中文显示；">
                                        <span>
                                            <i class="el-icon-warning-outline"></i>
                                        </span>
                                    </el-tooltip>
                                </el-radio>
                                <el-radio label="en">英文
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
                            <el-select style="width:378px;" v-model="formData.fontFamily">
                                <el-option v-for="(ff, index) in fontFamilyOption" :key="index" :value="ff.value"
                                    :label="ff.label"></el-option>
                            </el-select>
                            <div class="tips">
                                默认字体的最终显示依赖于收件人的操作系统与浏览器设置。
                            </div>
                        </div>
                    </div>
                    <div class="section-wrapper align-center">
                        <span class="label">文字大小:</span>
                        <el-select style="width:378px;" v-model="formData.fontSize">
                            <el-option v-for="(fs, index) in fontOption" :key="index" :value="fs.value"
                                :label="fs.label"></el-option>
                        </el-select>
                    </div>
                    <div class="section-wrapper font-color">
                        <span class="label">文字颜色:</span>
                        <el-color-picker v-model="formData.fontColor"></el-color-picker>
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
                            <el-radio-group v-model="formData.view">
                                <el-radio :label="0">任务视图</el-radio>
                                <el-radio :label="1">邮件视图</el-radio>
                            </el-radio-group>
                        </div>
                    </div>
                    <div class="section-wrapper">
                        <span class="label">群发邮件展示:</span>
                        <div class="section-inner">
                            <el-radio-group v-model="formData.emailShow">
                                <el-radio :label="0">仅在群发箱</el-radio>
                                <el-radio :label="1">发件箱与群发箱</el-radio>
                            </el-radio-group>
                        </div>
                    </div>
                </div>
                <section id="sign" class="section-block">
                    <h3>签名</h3>
                    <div class="section-wrapper">
                        <span class="label">个性签名:</span>
                        <div class="section-inner">
                            <div>
                                <el-select style="width:378px;" v-model="formData.signatureForm.name">

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
                                    <div v-html="formData.signatureForm.content"></div>
                                </div>
                                <div class="buttonset">
                                    <button class="m-btn"
                                        @click="showDialogSignature(formData.signatureForm)"><span>编辑</span></button><button
                                        class="m-btn gray">
                                        <span @click="deleteSignature(formData.signatureForm.id)">删除</span>
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
                            <el-checkbox v-model="formData.check">正文拼写检查 ( 编辑器会以红色波浪线提示正文中的拼写错误) </el-checkbox>
                            <el-checkbox v-model="formData.newWindow"
                                @change="(value) => value && (formData.forward = false)">新窗口写信 ( 将在下次打开邮件时生效)</el-checkbox>
                            <el-checkbox v-show="!formData.newWindow" v-model="formData.forward"> 发送后返回上一页 </el-checkbox>
                        </div>
                    </div>
                    <div class="section-wrapper">
                        <span class="label"> 粘贴格式: </span>
                        <div class="section-inner">
                            <el-radio-group v-model="formData.format">
                                <el-radio :label="1">粘贴格式</el-radio>
                                <el-radio :label="2">始终保持格式</el-radio>
                                <el-radio :label="0">每次粘贴提醒</el-radio>
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
                                        <p :title="item.title" class="ellipsis">
                                        <div class="public-mail-tag-wrapper">
                                            <div class="public-mail-tag-content">
                                                {{ item.title }}
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
                            <div class="mail-content">
                                <div class="config-item">
                                    <div class="left">写信昵称</div>
                                    <div class="right">
                                        <input v-model="emailSetList[curSet].name" type="text" class="m-input nickname">
                                    </div>
                                </div>
                                <div class="config-item">
                                    <div class="left">默认签名</div>
                                    <div class="right">
                                        <el-select style="width:378px;" v-model="emailSetList[curSet].signature">

                                        </el-select>
                                    </div>
                                </div>
                                <div class="config-item">
                                    <div class="left">回复/转发签名</div>
                                    <div class="right">
                                        <el-select style="width:378px;" v-model="emailSetList[curSet].transferSignature">

                                        </el-select>
                                    </div>
                                </div>
                                <div class="config-item">
                                    <div class="left">
                                        <el-checkbox v-model="emailSetList[curSet].isDuplicate">默认抄送：</el-checkbox>
                                    </div>
                                    <div class="right">
                                        <input placeholder="请使用 ';' 分隔多个邮箱" class="m-input long-input"
                                            :disabled="!emailSetList[curSet].isDuplicate" type="text">
                                    </div>
                                </div>
                                <div class="config-item">
                                    <div class="left">
                                        <el-checkbox v-model="emailSetList[curSet].isBlind">默认密送：</el-checkbox>
                                    </div>
                                    <div class="right">
                                        <input placeholder="请使用 ';' 分隔多个邮箱" class="m-input long-input"
                                            :disabled="![curSet].isBlind" type="text">
                                    </div>
                                </div>
                                <div class="config-item">
                                    <div class="left">是否追踪邮件</div>
                                    <div class="right">
                                        <el-radio-group v-model="emailSetList[curSet].isTrace">
                                            <el-radio :label="1">是</el-radio>
                                            <el-radio :label="0">否</el-radio>
                                        </el-radio-group>
                                    </div>
                                </div>
                                <div class="config-item">
                                    <div class="left">是否回执</div>
                                    <div class="right">
                                        <el-radio-group v-model="emailSetList[curSet].isReceipt">
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
                                <el-radio-group v-model="formData.auto">
                                    <el-radio :label="1">启用 (同一邮箱给你发送多封邮件时，4天内最多对该邮箱自动回复一次)</el-radio>
                                    <el-radio :label="0">停用</el-radio>
                                </el-radio-group>
                            </div>
                            <div style="display: flex;">
                                <div class="date-selector-item">
                                    <span class="date-label">开始时间</span>
                                    <el-date-picker v-model="formData.startTime" :disabled="formData.auto !== 1" clearable
                                        type="date" placeholder="选择日期">
                                    </el-date-picker>
                                </div>
                                <div class="date-selector-item">
                                    <span class="date-label">
                                        <el-checkbox v-model="formData.lastDay">最后一天</el-checkbox>
                                    </span>
                                    <el-date-picker v-model="formData.endTime" clearable
                                        :disabled="formData.auto !== 1 || !formData.lastDay" type="date" placeholder="选择日期">
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
                                v-model="formData.replyContent" @onCreated="onCreated" mode="default" />
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
        <DialogSignature :dialogVisible.sync="dialogVisible" :signatureData="signatureData" />
    </div>
</template>

<script>
import { Editor, Toolbar } from "@wangeditor/editor-for-vue";
import DialogSignature from "./DialogSignature.vue";
export default {
    components: {
        Editor,
        Toolbar,
        DialogSignature,
    },
    data() {
        return {
            formData: {
                defaultEmail: '',
                emailCount: 20,
                readMode: 0,
                handleAfter: 0,
                language: 'en',
                fontSize: '16px',
                fontFamily: 'Segoe UI',
                fontColor: '#000000',
                view: 0,
                emailShow: 0,
                signatureForm: {
                    id: 1,
                    name: '',
                    content: ''
                },
                format: 1,
                check: true,
                newWindow: false,
                forward: false,
                auto: 1,
                startTime: '',
                endTime: '',
                lastDay: false,
                replyContent: '',
            },
            emailOption: [
                {
                    value: 'email', label: 'sales17@allxchips.com'
                }
            ],
            emailCountOption: [
                { value: 20, label: '20' },
                { value: 30, label: '30' },
                { value: 50, label: '50' },
                { value: 100, label: '100' },
            ],
            fontFamilyOption: [
                { value: 'Segoe UI', label: 'Segoe UI' },
            ],
            fontOption: [
                { value: '16px', label: '16px' },
                { value: '18px', label: '18px' },
                { value: '20px', label: '20px' },
                { value: '22px', label: '22px' },
                { value: '24px', label: '24px' },
            ],
            dialogVisible: false,
            signatureData: {},
            curSet: 0,
            emailSetList: [
                {
                    title: 'sales17@allxchips.com',
                    name: '1',
                    signature: '',
                    transferSignature: '',
                    isDuplicate: false,
                    duplicateEmail: '',
                    isBlind: false,
                    blindEmail: '',
                    isTrace: 1,
                    isReceipt: 0,
                },
                {
                    title: 'sales18@allxchips.com',
                    name: '',
                    signature: '',
                    transferSignature: '',
                    isDuplicate: false,
                    duplicateEmail: '',
                    isBlind: false,
                    blindEmail: '',
                    isTrace: 1,
                    isReceipt: 0,
                },
                {
                    title: 'sales19@allxchips.com',
                    name: '',
                    signature: '',
                    transferSignature: '',
                    isDuplicate: false,
                    duplicateEmail: '',
                    isBlind: false,
                    blindEmail: '',
                    isTrace: 1,
                    isReceipt: 0,
                },
                {
                    title: 'sales22@allxchips.com',
                    name: '',
                    signature: '',
                    transferSignature: '',
                    isDuplicate: false,
                    duplicateEmail: '',
                    isBlind: false,
                    blindEmail: '',
                    isTrace: 1,
                    isReceipt: 0,
                },
                {
                    title: 'sales23@allxchips.com',
                    name: '',
                    signature: '',
                    transferSignature: '',
                    isDuplicate: false,
                    duplicateEmail: '',
                    isBlind: false,
                    blindEmail: '',
                    isTrace: 1,
                    isReceipt: 0,
                },
                {
                    title: 'sales24@allxchips.com',
                    name: '',
                    signature: '',
                    transferSignature: '',
                    isDuplicate: false,
                    duplicateEmail: '',
                    isBlind: false,
                    blindEmail: '',
                    isTrace: 1,
                    isReceipt: 0,
                },
                {
                    title: 'sales25@allxchips.com',
                    name: '',
                    signature: '',
                    transferSignature: '',
                    isDuplicate: false,
                    duplicateEmail: '',
                    isBlind: false,
                    blindEmail: '',
                    isTrace: 1,
                    isReceipt: 0,
                },
                {
                    title: 'sharon@htesemi.com',
                    name: '',
                    signature: '',
                    transferSignature: '',
                    isDuplicate: false,
                    duplicateEmail: '',
                    isBlind: false,
                    blindEmail: '',
                    isTrace: 1,
                    isReceipt: 0,
                },
                {
                    title: 'w0r1d_space@tom.com',
                    name: '',
                    signature: '',
                    transferSignature: '',
                    isDuplicate: false,
                    duplicateEmail: '',
                    isBlind: false,
                    blindEmail: '',
                    isTrace: 1,
                    isReceipt: 0,
                },
            ],
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
        }
    },
    created() {
        this.formData.signatureForm.content = `<div>
            <div style="font-family: Calibri; font-size: 20px; color: #000000;"><strong><span style="font-family: verdana, geneva; font-size: 14px;">Maesie&nbsp;</span></strong></div>
            <div style="font-family: Calibri; font-size: 20px; color: #000000;">
            <div style="clear: both; font-size: 0; height: 0px; overflow: hidden;">&nbsp;</div>
            <div class="xiaoman_kuai_mail_sign" style="color: #000000; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 14px;" spellcheck="true">
            <div><span style="font-family: verdana, geneva; font-size: 14px;">--------------------------------------------</span></div>
            <div><span style="font-family: verdana, geneva; font-size: 14px;">Hitech(HK)&nbsp;International&nbsp;Limited.&nbsp;</span></div>
            <div><br><span style="font-family: verdana, geneva; font-size: 14px;">Email:&nbsp; &nbsp;sales21@allxchips.com&nbsp;</span></div>
            <div><br><span style="font-family: verdana, geneva; font-size: 14px;"><a href="https://www.componentsindustry.com/">https://www.componentsindustry.com</a>&nbsp;</span></div>
            </div>
            </div>
            </div>`
    },
    methods: {
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
        showDialogSignature(data) {
            this.signatureData = data?.id ? data : {}
            this.dialogVisible = true
        },
        deleteSignature(id) {
            console.log(id);
        },
        onSave() { },
        onCancel() {
            this.$emit('backToHeader')
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
    z-index: 9999 !important;
}
</style>
<style src="@wangeditor/editor/dist/css/style.css"></style>
