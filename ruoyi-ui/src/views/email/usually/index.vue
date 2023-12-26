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
                            <el-select 
                            style="width:378px;" v-model="formData.defaultEmail">
                            <el-option v-for="(email,index) in emailOption" :key="index" :value="email.value" :label="email.label"></el-option>
                        </el-select>
                        </span>
                        <div class="tips">
                            在绑定多个邮箱的情况下，发信时默认选择该邮箱。
                        </div>
                    </div>
                </div>
                <div class="section-title">显示</div>
                <div class="conventional-look">
                    <div
                        class="section-wrapper"
                        style="align-items: center">
                        <span class="label">每页显示邮件数量:</span>
                        <el-select 
                        style="width:378px;" v-model="formData.emailCount">
                        <el-option v-for="(count,index) in emailCountOption" :key="index" :value="count.value" :label="count.label"></el-option>
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
                            <div
                                class="tips"
                                style="padding-left: 22px">
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
                                    <el-tooltip style="width:200px" placement="top" content="设置为中文时，回复转发将显示为「回复：」、「转发：」，引用内容也将用中文显示；">
                                        <span>
                                            <i class="el-icon-warning-outline"></i>
                                        </span>
                                    </el-tooltip>
                                </el-radio>
                                <el-radio label="zh-TW">繁体中文
                                    <el-tooltip style="width:200px" placement="top" content="设置为繁体中文时，回复转发将显示为「回復：」、「轉寄：」，引用内容也将用繁体中文显示；">
                                        <span>
                                            <i class="el-icon-warning-outline"></i>
                                        </span>
                                    </el-tooltip>
                                </el-radio>
                                <el-radio label="en">英文
                                    <el-tooltip style="width:200px" placement="top" content="设置为英文时，回复转发将显示为「Re：」、「Fwd：」，引用内容也将用英文显示。">
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
                            <el-select 
                            style="width:378px;" v-model="formData.fontFamily">
                            <el-option v-for="(ff,index) in fontFamilyOption" :key="index" :value="ff.value" :label="ff.label"></el-option>
                        </el-select>
                            <div class="tips">
                                默认字体的最终显示依赖于收件人的操作系统与浏览器设置。
                            </div>
                        </div>
                    </div>
                    <div class="section-wrapper align-center">
                        <span class="label">文字大小:</span
                        >
                        <el-select 
                            style="width:378px;" v-model="formData.fontSize">
                            <el-option v-for="(fs,index) in fontOption" :key="index" :value="fs.value" :label="fs.label"></el-option>
                        </el-select>
                    </div>
                    <div class="section-wrapper font-color">
                        <span class="label">文字颜色:</span>
                        <el-color-picker v-model="formData.fontColor"></el-color-picker>
                    </div>
                    <div class="section-wrapper">
                        <span class="label"
                            >群发箱视图  <el-tooltip style="width:200px" placement="top" content="任务视图以群发为单位展示，邮件列表以邮件为单位展示">
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
                <section
                    id="sign"
                    class="section-block">
                    <h3>签名</h3>
                    <div class="section-wrapper">
                        <span class="label">个性签名:</span>
                        <div class="section-inner">
                            <div>
                                <span class="mm-select"
                                    ><div
                                        class="mm-selector--single mm-selector"
                                        tabindex="0">
                                       
                                        <div class="mm-selector-rendered">
                                            <span
                                                title="Maesie"
                                                class="mm-selector-selected-value"
                                                style="opacity: 1"
                                                >Maesie</span
                                            >
                                        </div>
                                       <span class="mm-selector-suffix-icon"
                                            ><svg
                                                class="mm-icon mm-icon-chevrondown mm-selector-arrow"
                                                viewBox="0 0 24 24"
                                                name="chevrondown"
                                                xmlns="XMLNS"
                                                fill="currentColor"
                                                style="
                                                    height: 12px;
                                                    width: 12px;
                                                    outline: none;
                                                ">
                                                <path
                                                    d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path></svg
                                        ></span>
                                    </div>
                                   </span
                                >
                                <span class="okki-icon-wrap ml-2 pointer"
                                    >&ZeroWidthSpace;
                                    <el-tooltip content="添加新的签名" placement="top">
                                        <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        width="16"
                                        height="16"
                                        viewBox="0 0 24 24"
                                        aria-hidden="true"
                                        class="okki-svg-icon"
                                        fill="currentColor">
                                        <path
                                            fill-rule="evenodd"
                                            clip-rule="evenodd"
                                            d="M12 2a10 10 0 100 20 10 10 0 000-20zm0 18a8 8 0 110-16.001A8 8 0 0112 20z"></path>
                                        <path
                                            d="M13 8a1 1 0 10-2 0v3H8a1 1 0 100 2h3v3a1 1 0 102 0v-3h3a1 1 0 100-2h-3V8z"></path></svg
                                >
                                    </el-tooltip>
                                </span>
                            </div>
                            <div class="sign-box">
                                <div class="sign-content">
                                    <div>
                                        <div
                                            style="
                                                font-family: Calibri;
                                                font-size: 20px;
                                                color: #000000;
                                            ">
                                            <strong
                                                ><span
                                                    style="
                                                        font-family: verdana, geneva;
                                                        font-size: 14px;
                                                    "
                                                    >Maesie&nbsp;</span
                                                ></strong
                                            >
                                        </div>
                                        <div
                                            style="
                                                font-family: Calibri;
                                                font-size: 20px;
                                                color: #000000;
                                            ">
                                            <div
                                                style="
                                                    clear: both;
                                                    font-size: 0;
                                                    height: 0px;
                                                    overflow: hidden;
                                                ">
                                                &nbsp;
                                            </div>
                                            <div
                                                class="xiaoman_kuai_mail_sign"
                                                style="
                                                    color: #000000;
                                                    font-family: Verdana, Arial,
                                                        Helvetica, sans-serif;
                                                    font-size: 14px;
                                                "
                                                spellcheck="true">
                                                <div>
                                                    <span
                                                        style="
                                                            font-family: verdana,
                                                                geneva;
                                                            font-size: 14px;
                                                        "
                                                        >--------------------------------------------</span
                                                    >
                                                </div>
                                                <div>
                                                    <span
                                                        style="
                                                            font-family: verdana,
                                                                geneva;
                                                            font-size: 14px;
                                                        "
                                                        >Hitech(HK)&nbsp;International&nbsp;Limited.&nbsp;</span
                                                    >
                                                </div>
                                                <div>
                                                    <br /><span
                                                        style="
                                                            font-family: verdana,
                                                                geneva;
                                                            font-size: 14px;
                                                        "
                                                        >Email:&nbsp;
                                                        &nbsp;sales21@allxchips.com&nbsp;</span
                                                    >
                                                </div>
                                                <div>
                                                    <br /><span
                                                        style="
                                                            font-family: verdana,
                                                                geneva;
                                                            font-size: 14px;
                                                        "
                                                        ><a
                                                            href="https://www.componentsindustry.com/"
                                                            >https://www.componentsindustry.com</a
                                                        >&nbsp;</span
                                                    >
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="buttonset">
                                    <button class="m-btn"><span>编辑</span></button
                                    ><button class="m-btn gray">
                                        <span>删除</span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
                <div class="section-title">
                    写信
                    <span class="font-normal text-medium-8"
                        >(更改设置后将在下次打开邮件时生效)</span
                    >
                </div>
                <div><div class="section-wrapper">
                    <span class="label">写信：</span>
                    <div class="section-inner">
                        <el-checkbox v-model="formData.check">正文拼写检查 ( 编辑器会以红色波浪线提示正文中的拼写错误) </el-checkbox>
                        <el-checkbox  v-model="formData.newWindow"  @change="(value)=>value && (formData.forward = false)">新窗口写信 ( 将在下次打开邮件时生效)</el-checkbox>
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
                                <div class="mail-list" style="">
                                    <div class="active mail-item">
                                        <p title="sales17@allxchips.com" class="ellipsis">
                                            <div class="public-mail-tag-wrapper">
                                                <div class="public-mail-tag-content">
                                                   sales17@allxchips.com
                                                </div>
                                            </div>
                                        </p>
                                    </div>
                                    <div class="mail-item">
                                        <p title="sales18@allxchips.com" class="ellipsis">
                                            <div class="public-mail-tag-wrapper">
                                                <div class="public-mail-tag-content">
                                                   sales18@allxchips.com
                                                </div>
                                            </div>
                                        </p>
                                    </div>
                                    <div class="mail-item">
                                        <p title="sales19@allxchips.com" class="ellipsis">
                                            <div class="public-mail-tag-wrapper">
                                                <div class="public-mail-tag-content">
                                                   sales19@allxchips.com
                                                </div>
                                            </div>
                                        </p>
                                    </div>
                                    <div class="mail-item">
                                        <p title="sales22@allxchips.com" class="ellipsis">
                                            <div class="public-mail-tag-wrapper">
                                                <div class="public-mail-tag-content">
                                                   sales22@allxchips.com
                                                </div>
                                            </div>
                                        </p>
                                    </div>
                                    <div class="mail-item">
                                        <p title="sales23@allxchips.com" class="ellipsis">
                                            <div class="public-mail-tag-wrapper">
                                                <div class="public-mail-tag-content">
                                                   sales23@allxchips.com
                                                </div>
                                            </div>
                                        </p>
                                    </div>
                                    <div class="mail-item">
                                        <p title="sales24@allxchips.com" class="ellipsis">
                                            <div class="public-mail-tag-wrapper">
                                                <div class="public-mail-tag-content">
                                                   sales24@allxchips.com
                                                </div>
                                            </div>
                                        </p>
                                    </div>
                                    <div class="mail-item">
                                        <p title="sales25@allxchips.com" class="ellipsis">
                                            <div class="public-mail-tag-wrapper">
                                                <div class="public-mail-tag-content">
                                                   sales25@allxchips.com
                                                </div>
                                            </div>
                                        </p>
                                    </div>
                                    <div class="mail-item">
                                        <p title="sharon@htesemi.com" class="ellipsis">
                                            <div class="public-mail-tag-wrapper">
                                                <div class="public-mail-tag-content">
                                                   sharon@htesemi.com
                                                </div>
                                            </div>
                                        </p>
                                    </div>
                                    <div class="mail-item">
                                        <p title="w0r1d_space@tom.com" class="ellipsis">
                                            <div class="public-mail-tag-wrapper">
                                                <div class="public-mail-tag-content">
                                                   w0r1d_space@tom.com
                                                </div>
                                            </div>
                                        </p>
                                    </div>
                                </div>
                                <div class="change-btn">
                                    <div class="change-btn-list">
                                    <i class="disable m-icon icon-left-thin"></i>
                                    <i class="m-icon icon-right-thin"></i>
                                </div>
                            </div>
                        </div>
                        <div class="mail-content">
                            <div class="config-item">
                                <div class="left">写信昵称</div>
                                <div class="right">
                                    <input type="text" class="m-input nickname">
                                </div>
                            </div>
                            <div class="config-item">
                                <div class="left">默认签名</div>
                                <div class="right">
                                    <span class="mm-select sign-select">
                                        <div class="mm-selector--single mm-selector" tabindex="0">
                                           
                                            <div class="mm-selector-rendered">
                                                <span title="joy" class="mm-selector-selected-value" style="opacity: 1;">joy</span>
                                               
                                            </div>
                                            <span class="mm-selector-suffix-icon">
                                                <svg class="mm-icon mm-icon-chevrondown mm-selector-arrow" viewBox="0 0 24 24" name="chevrondown" xmlns="XMLNS" fill="currentColor" style="height: 12px; width: 12px; outline: none;">
                                                    <path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z">

                                                    </path>
                                                </svg>
                                            </span>
                                        </div>
                                    </span>
                                </div>
                            </div>
                            <div class="config-item">
                                <div class="left">回复/转发签名</div>
                                <div class="right">
                                    <span class="mm-select sign-select">
                                        <div class="mm-selector--single mm-selector" tabindex="0">
                                            <div class="mm-selector-rendered">
                                                <span title="joy" class="mm-selector-selected-value" style="opacity: 1;">joy</span>
                                            </div>
                                            <span class="mm-selector-suffix-icon">
                                                <svg class="mm-icon mm-icon-chevrondown mm-selector-arrow" viewBox="0 0 24 24" name="chevrondown" xmlns="XMLNS" fill="currentColor" style="height: 12px; width: 12px; outline: none;">
                                                    <path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
                                                </svg>
                                            </span>
                                        </div>
                                    </span>
                                </div>
                            </div>
                            <div class="config-item">
                                <div class="left">
                                    <label>
                                        <input true-value="1" false-value="0" type="checkbox" style="margin-right: 4px;">
                                        <span class="text">默认抄送：</span>
                                    </label>
                                </div>
                                <div class="right">
                                    <input placeholder="请使用 ';' 分隔多个邮箱" class="m-input long-input" disabled="" type="text">
                                </div>
                            </div>
                            <div class="config-item">
                                <div class="left">
                                    <label>
                                        <input true-value="1" false-value="0" type="checkbox" style="margin-right: 4px;">
                                        <span class="text">默认密送：</span>
                                    </label>
                                </div>
                                <div class="right">
                                    <input placeholder="请使用 ';' 分隔多个邮箱" class="m-input long-input" disabled="" type="text">
                                </div>
                            </div>
                            <div class="config-item">
                                <div class="left">是否追踪邮件</div>
                                <div class="right">
                                    <label style="margin-right: 20px;">
                                        <input type="radio" value="1"> 
                                        是
                                    </label>
                                    <label>
                                        <input type="radio" value="0">
                                         否
                                        </label>
                                    </div>
                                </div>
                                <div class="config-item">
                                    <div class="left">是否回执</div>
                                    <div class="right">
                                        <label style="margin-right: 20px;">
                                            <input type="radio" value="1"> 
                                            是
                                        </label>
                                        <label>
                                            <input type="radio" value="0"> 
                                            否
                                        </label>
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
                                    <el-date-picker
                                    v-model="formData.startTime"
                                    :disabled="formData.auto!==1"
                                    clearable
                                    type="date"
                                    placeholder="选择日期">
                                  </el-date-picker>
                                </div>
                                <div class="date-selector-item">
                                    <span class="date-label">
                                        <el-checkbox v-model="lastDay">最后一天</el-checkbox>
                                    </span>
                                    <el-date-picker
                                    v-model="formData.endTime"
                                    clearable
                                    :disabled="formData.auto!==1 || !lastDay"
                                    type="date"
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
		
	</div>
</template>

<script>
	export default {
        data(){
            return {
                formData:{
                    defaultEmail:'',
                    emailCount:20,
                    readMode:0,
                    handleAfter:0,
                    language:'en',
                    fontSize:'16px',
                    fontFamily:'Segoe UI',
                    fontColor:'#000000',
                    view:0,
                    emailShow:0,
                    format:1,
                    check:true,
                    newWindow:false,
                    forward:false,
                    auto:1,
                    startTime:'',
                    endTime:'',
                    lastDay:false
                },
                emailOption:[
                    {
                        value:'email',label:'sales17@allxchips.com'
                    }
                ],
                emailCountOption:[
                    {value:20,label:'20'},
                    {value:30,label:'30'},
                    {value:50,label:'50'},
                    {value:100,label:'100'},
                ],
                fontFamilyOption:[
                    {value:'Segoe UI',label:'Segoe UI'},
                ],
                fontOption:[
                    {value:'16px',label:'16px'},
                    {value:'18px',label:'18px'},
                    {value:'20px',label:'20px'},
                    {value:'22px',label:'22px'},
                    {value:'24px',label:'24px'},
                ],
            }
        },
        methods:{
            onSave(){},
            onCancel(){
                this.$emit('backToHeader')
            },
        }
    };
</script>

<style lang="scss" scoped>
	@import url('./css/index.css');
    .font-color{
        display:flex;
        align-items:center !important;
        ::v-deep .el-color-picker__trigger{
            width:375px;
            .el-color-picker__icon{
                left:94%;
            }
        }
    }
</style>
