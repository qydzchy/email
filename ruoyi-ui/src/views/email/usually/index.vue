<template>
	<div class="mail-plus-conventional config-page-item">
        <div class="mail-plus-conventional-content">
            <div class="conventional-content">
                <h3 class="config-page-title">常规</h3>
                <div class="section-title">账号</div>
                <div class="section-wrapper account">
                    <span class="label">默认邮箱:</span>
                    <div class="section-inner">
                        <span
                            class="mm-select field-value"
                            style="width: 378px"
                            ><div
                                class="mm-selector--single mm-selector"
                                tabindex="0">
                                <!---->
                                <div class="mm-selector-rendered">
                                    <div
                                        class="public-mail-tag-wrapper field-selection">
                                        <div class="public-mail-tag-content">
                                            <!---->sales17@allxchips.com
                                        </div>
                                    </div>
                                </div>
                                <!----><span class="mm-selector-suffix-icon"
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
                                ></span></div
                        ></span>
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
                        <span class="label">每页显示邮件数量:</span
                        ><span class="mm-select"
                            ><div
                                class="mm-selector--single mm-selector"
                                tabindex="0">
                                <!---->
                                <div class="mm-selector-rendered">
                                    <span
                                        title="20"
                                        class="mm-selector-selected-value"
                                        style="opacity: 1"
                                        >20</span
                                    ><!---->
                                </div>
                                <!----><span class="mm-selector-suffix-icon"
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
                                ></span></div
                        ></span>
                    </div>
                    <div class="section-wrapper">
                        <div class="label">邮件阅读模式:</div>
                        <div class="section-inner">
                            <label class="mm-radio"
                                ><input
                                    type="radio"
                                    value="3" /><span class="mm-radio-faux"
                                    ><span class="mm-radio-input"></span
                                    ><span class="mm-radio-label"
                                        >启用 (适合大屏用户，可快速切换邮件)</span
                                    ></span
                                ></label
                            ><label class="mm-radio"
                                ><input
                                    type="radio"
                                    value="2" /><span class="mm-radio-faux"
                                    ><span class="mm-radio-input"></span
                                    ><span class="mm-radio-label"
                                        >停用
                                        (适合小屏用户，更多空间查看邮件详情)</span
                                    ></span
                                ></label
                            >
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
                            <label class="mm-radio"
                                ><input
                                    name="read_next_mail"
                                    type="radio"
                                    value="1" /><span class="mm-radio-faux"
                                    ><span class="mm-radio-input"></span
                                    ><span class="mm-radio-label"
                                        >阅读下一封邮件(推荐)</span
                                    ></span
                                ></label
                            ><label class="mm-radio"
                                ><input
                                    name="read_next_mail"
                                    type="radio"
                                    value="0" /><span class="mm-radio-faux"
                                    ><span class="mm-radio-input"></span
                                    ><span class="mm-radio-label"
                                        >回到当前邮件列表</span
                                    ></span
                                ></label
                            >
                        </div>
                    </div>
                    <div class="section-wrapper">
                        <span class="label">语言:</span>
                        <div class="section-inner">
                            <div class="mm-radio-group">
                                <label class="mm-radio"
                                    ><input
                                        tips="设置为中文时，回复转发将显示为「回复：」、「转发：」，引用内容也将用中文显示；"
                                        name="mm-radio-group-2"
                                        type="radio"
                                        value="zh" /><span class="mm-radio-faux"
                                        ><span class="mm-radio-input"></span
                                        ><span class="mm-radio-label"
                                            ><span>简体中文</span
                                            ><span class="okki-icon-wrap"
                                                >&ZeroWidthSpace;<svg
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
                                                        d="M4 12a8 8 0 1116 0 8 8 0 01-16 0zm8-10C6.477 2 2 6.477 2 12s4.477 10 10 10 10-4.477 10-10S17.523 2 12 2zm1.2 4.75a1.25 1.25 0 11-2.5 0 1.25 1.25 0 012.5 0zM11.2 9a1 1 0 100 2v5h-.9a1 1 0 100 2h3.5a1 1 0 100-2h-.6v-6a1 1 0 00-1-1h-1z"></path></svg></span></span></span></label
                                ><label class="mm-radio"
                                    ><input
                                        tips="设置为繁体中文时，回复转发将显示为「回復：」、「轉寄：」，引用内容也将用繁体中文显示；"
                                        name="mm-radio-group-2"
                                        type="radio"
                                        value="zh-TW" /><span class="mm-radio-faux"
                                        ><span class="mm-radio-input"></span
                                        ><span class="mm-radio-label"
                                            ><span>繁体中文</span
                                            ><span class="okki-icon-wrap"
                                                >&ZeroWidthSpace;<svg
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
                                                        d="M4 12a8 8 0 1116 0 8 8 0 01-16 0zm8-10C6.477 2 2 6.477 2 12s4.477 10 10 10 10-4.477 10-10S17.523 2 12 2zm1.2 4.75a1.25 1.25 0 11-2.5 0 1.25 1.25 0 012.5 0zM11.2 9a1 1 0 100 2v5h-.9a1 1 0 100 2h3.5a1 1 0 100-2h-.6v-6a1 1 0 00-1-1h-1z"></path></svg></span></span></span></label
                                ><label class="mm-radio"
                                    ><input
                                        tips="设置为英文时，回复转发将显示为「Re：」、「Fwd：」，引用内容也将用英文显示。"
                                        name="mm-radio-group-2"
                                        type="radio"
                                        value="en" /><span class="mm-radio-faux"
                                        ><span class="mm-radio-input"></span
                                        ><span class="mm-radio-label"
                                            ><span>英文</span
                                            ><span class="okki-icon-wrap"
                                                >&ZeroWidthSpace;<svg
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
                                                        d="M4 12a8 8 0 1116 0 8 8 0 01-16 0zm8-10C6.477 2 2 6.477 2 12s4.477 10 10 10 10-4.477 10-10S17.523 2 12 2zm1.2 4.75a1.25 1.25 0 11-2.5 0 1.25 1.25 0 012.5 0zM11.2 9a1 1 0 100 2v5h-.9a1 1 0 100 2h3.5a1 1 0 100-2h-.6v-6a1 1 0 00-1-1h-1z"></path></svg></span></span></span
                                ></label>
                            </div>
                        </div>
                    </div>
                    <div class="section-wrapper">
                        <span class="label">默认字体:</span>
                        <div class="section-inner">
                            <span
                                class="mm-select"
                                style="min-width: 85px"
                                ><div
                                    class="mm-selector--single mm-selector"
                                    tabindex="0">
                                    <!---->
                                    <div class="mm-selector-rendered">
                                        <span
                                            title="Segoe UI"
                                            class="mm-selector-selected-value"
                                            style="opacity: 1"
                                            >Segoe UI</span
                                        ><!---->
                                    </div>
                                    <!----><span class="mm-selector-suffix-icon"
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
                                <!----></span
                            >
                            <div class="tips">
                                默认字体的最终显示依赖于收件人的操作系统与浏览器设置。
                            </div>
                        </div>
                    </div>
                    <div class="section-wrapper align-center">
                        <span class="label">文字大小:</span
                        ><span class="mm-select"
                            ><div
                                class="mm-selector--single mm-selector"
                                tabindex="0">
                                <!---->
                                <div class="mm-selector-rendered">
                                    <span
                                        title="16px"
                                        class="mm-selector-selected-value"
                                        style="opacity: 1"
                                        >16px</span
                                    ><!---->
                                </div>
                                <!----><span class="mm-selector-suffix-icon"
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
                            <!----></span
                        >
                    </div>
                    <div class="section-wrapper">
                        <span class="label">文字颜色:</span>
                        <div
                            class="mm-color-picker-box"
                            style="width: 378px">
                            <div class="mm-color-picker-toggle">
                                <span
                                    class="mm-color-picker-toggle-value"
                                    style="background: rgb(0, 0, 0)"
                                    ><svg
                                        class="mm-icon mm-icon-chevrondown mm-color-picker-toggle-icon"
                                        viewBox="0 0 24 24"
                                        name="chevrondown"
                                        xmlns="XMLNS"
                                        fill="#fff"
                                        style="height: 12px; width: 12px">
                                        <path
                                            d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path></svg
                                ></span>
                            </div>
                        </div>
                    </div>
                    <div class="section-wrapper">
                        <span class="label"
                            >群发箱视图<span class="okki-icon-wrap"
                                >&ZeroWidthSpace;<svg
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
                                        d="M6.444 3.685a10 10 0 1111.112 16.63A10 10 0 016.444 3.685zm1.111 14.967a8 8 0 108.89-13.305 8 8 0 00-8.89 13.305zM12.072 6.93c-.43 0-.827.046-1.192.142a3.829 3.829 0 00-1.078.479c-.26.157-.442.394-.442.711 0 .213.083.4.232.548a.76.76 0 00.548.232.831.831 0 00.461-.143 2.14 2.14 0 01.618-.25l.006-.001c.247-.065.497-.098.751-.098.542 0 .894.105 1.105.266.196.149.311.377.311.742a.622.622 0 01-.148.41 4.175 4.175 0 01-.626.579l-.002.001a7.704 7.704 0 00-.725.65 2.81 2.81 0 00-.498.752c-.15.32-.221.68-.221 1.076 0 .213.083.4.232.548l.009.009.01.008c.164.14.363.203.577.203.21 0 .405-.066.566-.203l.01-.009a.743.743 0 00.252-.556c0-.265.068-.465.188-.62.156-.197.396-.429.73-.692a7.65 7.65 0 00.662-.576l.005-.005c.184-.193.337-.42.461-.677l.002-.003c.133-.286.196-.607.196-.955 0-.777-.281-1.417-.852-1.887-.556-.464-1.282-.681-2.148-.681zm-.096 8.116a.99.99 0 00-.716.304.97.97 0 00-.292.704c0 .276.097.522.286.722l.006.006.006.006c.198.187.439.286.71.286a.984.984 0 00.716-.292c.2-.199.304-.447.304-.728a.95.95 0 00-.304-.704.965.965 0 00-.716-.304z"></path></svg></span
                            >:</span
                        >
                        <div class="section-inner">
                            <div class="mm-radio-group">
                                <label class="mm-radio"
                                    ><input
                                        name="mm-radio-group-3"
                                        type="radio"
                                        value="0" /><span class="mm-radio-faux"
                                        ><span class="mm-radio-input"></span
                                        ><span class="mm-radio-label"
                                            >任务视图</span
                                        ></span
                                    ></label
                                ><label class="mm-radio"
                                    ><input
                                        name="mm-radio-group-3"
                                        type="radio"
                                        value="1" /><span class="mm-radio-faux"
                                        ><span class="mm-radio-input"></span
                                        ><span class="mm-radio-label"
                                            >邮件视图</span
                                        ></span
                                    ></label
                                >
                            </div>
                        </div>
                    </div>
                    <div class="section-wrapper">
                        <span class="label">群发邮件展示:</span>
                        <div class="section-inner">
                            <div
                                class="okki-radio-group okki-radio-group-outline okki-radio-group-default">
                                <label class="okki-radio-wrapper"
                                    ><span class="okki-radio"
                                        ><input
                                            type="radio"
                                            class="okki-radio-input"
                                            value="0" /><span
                                            class="okki-radio-inner"></span></span
                                    ><span>仅在群发箱</span></label
                                ><label
                                    class="okki-radio-wrapper okki-radio-wrapper-checked"
                                    ><span class="okki-radio okki-radio-checked"
                                        ><input
                                            type="radio"
                                            class="okki-radio-input"
                                            value="1" /><span
                                            class="okki-radio-inner"></span></span
                                    ><span>发件箱与群发箱</span></label
                                >
                            </div>
                        </div>
                    </div>
                </div>
                <section class="section-block">
                    <h3>提醒</h3>
                    <div class="section-wrapper mail-remind">
                        <div class="label">提醒:</div>
                        <div class="section-inner">
                            <label class="mm-checkbox"
                                ><input
                                    true-value="1"
                                    false-value="0"
                                    type="checkbox" /><span
                                    class="mm-checkbox-input"></span
                                ><span class="mm-checkbox-label"
                                    >禁止新邮件到达时在页面弹出通知
                                    <span class="okki-icon-wrap"
                                        >&ZeroWidthSpace;<svg
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
                                                d="M4 12a8 8 0 1116 0 8 8 0 01-16 0zm8-10C6.477 2 2 6.477 2 12s4.477 10 10 10 10-4.477 10-10S17.523 2 12 2zm1.2 4.75a1.25 1.25 0 11-2.5 0 1.25 1.25 0 012.5 0zM11.2 9a1 1 0 100 2v5h-.9a1 1 0 100 2h3.5a1 1 0 100-2h-.6v-6a1 1 0 00-1-1h-1z"></path></svg></span></span></label
                            ><label class="mm-checkbox"
                                ><input
                                    true-value="1"
                                    false-value="0"
                                    type="checkbox" /><span
                                    class="mm-checkbox-input"></span
                                ><span class="mm-checkbox-label"
                                    >禁止发送邮件在页面弹出附件提醒
                                    <span class="okki-icon-wrap"
                                        >&ZeroWidthSpace;<svg
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
                                                d="M4 12a8 8 0 1116 0 8 8 0 01-16 0zm8-10C6.477 2 2 6.477 2 12s4.477 10 10 10 10-4.477 10-10S17.523 2 12 2zm1.2 4.75a1.25 1.25 0 11-2.5 0 1.25 1.25 0 012.5 0zM11.2 9a1 1 0 100 2v5h-.9a1 1 0 100 2h3.5a1 1 0 100-2h-.6v-6a1 1 0 00-1-1h-1z"></path></svg></span></span
                            ></label>
                        </div>
                    </div>
                </section>
                <h3>提醒</h3>
                <div class="section-wrapper mail-remind">
                    <div class="label">提醒:</div>
                    <div class="section-inner">
                        <label class="mm-checkbox"
                            ><input
                                true-value="1"
                                false-value="0"
                                type="checkbox" /><span
                                class="mm-checkbox-input"></span
                            ><span class="mm-checkbox-label"
                                >禁止新邮件到达时在页面弹出通知
                                <span class="okki-icon-wrap"
                                    >&ZeroWidthSpace;<svg
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
                                            d="M4 12a8 8 0 1116 0 8 8 0 01-16 0zm8-10C6.477 2 2 6.477 2 12s4.477 10 10 10 10-4.477 10-10S17.523 2 12 2zm1.2 4.75a1.25 1.25 0 11-2.5 0 1.25 1.25 0 012.5 0zM11.2 9a1 1 0 100 2v5h-.9a1 1 0 100 2h3.5a1 1 0 100-2h-.6v-6a1 1 0 00-1-1h-1z"></path></svg></span></span></label
                        ><label class="mm-checkbox"
                            ><input
                                true-value="1"
                                false-value="0"
                                type="checkbox" /><span
                                class="mm-checkbox-input"></span
                            ><span class="mm-checkbox-label"
                                >禁止发送邮件在页面弹出附件提醒
                                <span class="okki-icon-wrap"
                                    >&ZeroWidthSpace;<svg
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
                                            d="M4 12a8 8 0 1116 0 8 8 0 01-16 0zm8-10C6.477 2 2 6.477 2 12s4.477 10 10 10 10-4.477 10-10S17.523 2 12 2zm1.2 4.75a1.25 1.25 0 11-2.5 0 1.25 1.25 0 012.5 0zM11.2 9a1 1 0 100 2v5h-.9a1 1 0 100 2h3.5a1 1 0 100-2h-.6v-6a1 1 0 00-1-1h-1z"></path></svg></span></span
                        ></label>
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
                                        <!---->
                                        <div class="mm-selector-rendered">
                                            <span
                                                title="Maesie"
                                                class="mm-selector-selected-value"
                                                style="opacity: 1"
                                                >Maesie</span
                                            ><!---->
                                        </div>
                                        <!----><span class="mm-selector-suffix-icon"
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
                                    <!----></span
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
                <div><div class="section-wrapper"><span class="label">写信：</span><div class="section-inner"><label class="mm-checkbox mr-5"><input true-value="1" false-value="0" type="checkbox"><span class="mm-checkbox-input"></span><span class="mm-checkbox-label">正文拼写检查 ( 编辑器会以红色波浪线提示正文中的拼写错误) </span></label><label class="mm-checkbox mr-5"><input true-value="1" false-value="0" type="checkbox"><span class="mm-checkbox-input"></span><span class="mm-checkbox-label">新窗口写信 ( 将在下次打开邮件时生效) </span></label><label class="mm-checkbox mt-3"><input true-value="1" false-value="0" type="checkbox"><span class="mm-checkbox-input"></span><span class="mm-checkbox-label"> 发送后返回上一页 </span></label></div></div><div class="section-wrapper"><span class="label"> 粘贴格式 </span><div class="section-inner"><label class="mm-radio special-radio"><input name="paste_format" type="radio" value="1"><span class="mm-radio-faux"><span class="mm-radio-input"></span><span class="mm-radio-label">始终移除格式</span></span></label><label class="mm-radio special-radio"><input name="paste_format" type="radio" value="2"><span class="mm-radio-faux"><span class="mm-radio-input"></span><span class="mm-radio-label">始终保持格式</span></span></label><label class="mm-radio special-radio"><input name="paste_format" type="radio" value="0"><span class="mm-radio-faux"><span class="mm-radio-input"></span><span class="mm-radio-label">每次粘贴提醒</span></span></label></div></div><div class="section-wrapper"><span class="label">单个邮箱设置:</span><div class="mail-box"><div class="mail-list-header"><div class="mail-list" style=""><div class="active mail-item"><p title="sales17@allxchips.com" class="ellipsis"><div class="public-mail-tag-wrapper"><div class="public-mail-tag-content"><!---->sales17@allxchips.com</div></div></p></div><div class="mail-item"><p title="sales18@allxchips.com" class="ellipsis"><div class="public-mail-tag-wrapper"><div class="public-mail-tag-content"><!---->sales18@allxchips.com</div></div></p></div><div class="mail-item"><p title="sales19@allxchips.com" class="ellipsis"><div class="public-mail-tag-wrapper"><div class="public-mail-tag-content"><!---->sales19@allxchips.com</div></div></p></div><div class="mail-item"><p title="sales22@allxchips.com" class="ellipsis"><div class="public-mail-tag-wrapper"><div class="public-mail-tag-content"><!---->sales22@allxchips.com</div></div></p></div><div class="mail-item"><p title="sales23@allxchips.com" class="ellipsis"><div class="public-mail-tag-wrapper"><div class="public-mail-tag-content"><!---->sales23@allxchips.com</div></div></p></div><div class="mail-item"><p title="sales24@allxchips.com" class="ellipsis"><div class="public-mail-tag-wrapper"><div class="public-mail-tag-content"><!---->sales24@allxchips.com</div></div></p></div><div class="mail-item"><p title="sales25@allxchips.com" class="ellipsis"><div class="public-mail-tag-wrapper"><div class="public-mail-tag-content"><!---->sales25@allxchips.com</div></div></p></div><div class="mail-item"><p title="sharon@htesemi.com" class="ellipsis"><div class="public-mail-tag-wrapper"><div class="public-mail-tag-content"><!---->sharon@htesemi.com</div></div></p></div><div class="mail-item"><p title="w0r1d_space@tom.com" class="ellipsis"><div class="public-mail-tag-wrapper"><div class="public-mail-tag-content"><!---->w0r1d_space@tom.com</div></div></p></div></div><div class="change-btn"><div class="change-btn-list"><i class="disable m-icon icon-left-thin"></i><i class="m-icon icon-right-thin"></i></div></div></div><div class="mail-content"><div class="config-item"><div class="left">写信昵称</div><div class="right"><input type="text" class="m-input nickname"></div></div><div class="config-item"><div class="left">默认签名</div><div class="right"><span class="mm-select sign-select"><div class="mm-selector--single mm-selector" tabindex="0"><!----><div class="mm-selector-rendered"><span title="joy" class="mm-selector-selected-value" style="opacity: 1;">joy</span><!----></div><!----><span class="mm-selector-suffix-icon"><svg class="mm-icon mm-icon-chevrondown mm-selector-arrow" viewBox="0 0 24 24" name="chevrondown" xmlns="XMLNS" fill="currentColor" style="height: 12px; width: 12px; outline: none;"><path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path></svg></span></div><!----></span></div></div><div class="config-item"><div class="left">回复/转发签名</div><div class="right"><span class="mm-select sign-select"><div class="mm-selector--single mm-selector" tabindex="0"><!----><div class="mm-selector-rendered"><span title="joy" class="mm-selector-selected-value" style="opacity: 1;">joy</span><!----></div><!----><span class="mm-selector-suffix-icon"><svg class="mm-icon mm-icon-chevrondown mm-selector-arrow" viewBox="0 0 24 24" name="chevrondown" xmlns="XMLNS" fill="currentColor" style="height: 12px; width: 12px; outline: none;"><path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path></svg></span></div><!----></span></div></div><div class="config-item"><div class="left"><label><input true-value="1" false-value="0" type="checkbox" style="margin-right: 4px;"><span class="text">默认抄送：</span></label></div><div class="right"><input placeholder="请使用 ';' 分隔多个邮箱" class="m-input long-input" disabled="" type="text"></div></div><div class="config-item"><div class="left"><label><input true-value="1" false-value="0" type="checkbox" style="margin-right: 4px;"><span class="text">默认密送：</span></label></div><div class="right"><input placeholder="请使用 ';' 分隔多个邮箱" class="m-input long-input" disabled="" type="text"></div></div><div class="config-item"><div class="left">是否追踪邮件</div><div class="right"><label style="margin-right: 20px;"><input type="radio" value="1"> 是</label><label><input type="radio" value="0"> 否</label></div></div><div class="config-item"><div class="left">是否回执</div><div class="right"><label style="margin-right: 20px;"><input type="radio" value="1"> 是</label><label><input type="radio" value="0"> 否</label></div></div></div><!----><!----><!----><!----><!----><!----><!----><!----></div></div></div>
    
                <div class="section-title">外出自动回复</div>
    
                <div class="holiday-wrapper">
                    <div class="section-wrapper"><span class="label">自动回复:</span><div class="section-inner"><div><label class="mm-radio special-radio"><input name="read_mode" type="radio" value="1"><span class="mm-radio-faux"><span class="mm-radio-input"></span><span class="mm-radio-label">启用 (同一邮箱给你发送多封邮件时，4天内最多对该邮箱自动回复一次) </span></span></label><label class="mm-radio special-radio"><input name="read_mode" type="radio" value="0"><span class="mm-radio-faux"><span class="mm-radio-input"></span><span class="mm-radio-label">停用</span></span></label></div><div style="display: flex;"><div class="date-selector-item"><span class="date-label">开始时间</span><div class="mm-datepicker date-picker-item"><div class="mm-selector--single mm-selector--disabled mm-selector" tabindex="0"><span class="mm-selector__placeholder">请选择</span><div class="mm-selector-rendered"><span title="" class="mm-selector-selected-value" style="opacity: 1;"></span><!----></div><!----><span class="mm-selector-suffix-icon"><svg class="mm-icon mm-icon-event" viewBox="0 0 24 24" name="event" xmlns="XMLNS" fill="currentColor" style="height: 12px; width: 12px;"><path d="M21.5 9.2h-19c-.3 0-.7.4-.7.7v11.3c0 1 .9 1.9 1.9 1.9h16.6c1 0 1.9-.9 1.9-1.9V9.9c0-.3-.4-.7-.7-.7zM8.8 19.4c0 .3-.2.4-.5.4H6.5c-.3 0-.5-.1-.5-.4v-1.9c0-.2.2-.4.5-.4h1.8c.3 0 .5.2.5.4v1.9zm0-4.6c0 .2-.2.4-.5.4H6.5c-.3 0-.5-.2-.5-.4v-1.9c0-.3.2-.4.5-.4h1.8c.3 0 .5.1.5.4v1.9zm4.6 4.6c0 .3-.2.4-.5.4h-1.8c-.3 0-.5-.1-.5-.4v-1.9c0-.2.2-.4.5-.4h1.8c.3 0 .5.2.5.4v1.9zm0-4.6c0 .2-.2.4-.5.4h-1.8c-.3 0-.5-.2-.5-.4v-1.9c0-.3.2-.4.5-.4h1.8c.3 0 .5.1.5.4v1.9zm4.6 4.6c0 .3-.2.4-.5.4h-1.8c-.3 0-.5-.1-.5-.4v-1.9c0-.2.2-.4.5-.4h1.8c.3 0 .5.2.5.4v1.9zm0-4.6c0 .2-.2.4-.5.4h-1.8c-.3 0-.5-.2-.5-.4v-1.9c0-.3.2-.4.5-.4h1.8c.3 0 .5.1.5.4v1.9zm2.3-11.6H18v-.9c0-.7-.6-1.4-1.4-1.4-.7 0-1.4.6-1.4 1.4v.9H8.8v-.9c0-.7-.6-1.4-1.4-1.4C6.6.9 6 1.5 6 2.3v.9H3.7c-1 0-1.9.9-1.9 1.9v1.1c0 .4.4.7.7.7h19c.3 0 .7-.3.7-.7V5.1c0-1-.9-1.9-1.9-1.9z"></path></svg></span></div><!----></div></div><div class="date-selector-item"><label class="mm-checkbox date-label"><input true-value="true" type="checkbox"><span class="mm-checkbox-input"></span><span class="mm-checkbox-label">最后一天</span></label><div class="mm-datepicker date-picker-item"><div class="mm-selector--single mm-selector--disabled mm-selector" tabindex="0"><span class="mm-selector__placeholder">可选</span><div class="mm-selector-rendered"><span title="" class="mm-selector-selected-value" style="opacity: 1;"></span><!----></div><!----><span class="mm-selector-suffix-icon"><svg class="mm-icon mm-icon-event" viewBox="0 0 24 24" name="event" xmlns="XMLNS" fill="currentColor" style="height: 12px; width: 12px;"><path d="M21.5 9.2h-19c-.3 0-.7.4-.7.7v11.3c0 1 .9 1.9 1.9 1.9h16.6c1 0 1.9-.9 1.9-1.9V9.9c0-.3-.4-.7-.7-.7zM8.8 19.4c0 .3-.2.4-.5.4H6.5c-.3 0-.5-.1-.5-.4v-1.9c0-.2.2-.4.5-.4h1.8c.3 0 .5.2.5.4v1.9zm0-4.6c0 .2-.2.4-.5.4H6.5c-.3 0-.5-.2-.5-.4v-1.9c0-.3.2-.4.5-.4h1.8c.3 0 .5.1.5.4v1.9zm4.6 4.6c0 .3-.2.4-.5.4h-1.8c-.3 0-.5-.1-.5-.4v-1.9c0-.2.2-.4.5-.4h1.8c.3 0 .5.2.5.4v1.9zm0-4.6c0 .2-.2.4-.5.4h-1.8c-.3 0-.5-.2-.5-.4v-1.9c0-.3.2-.4.5-.4h1.8c.3 0 .5.1.5.4v1.9zm4.6 4.6c0 .3-.2.4-.5.4h-1.8c-.3 0-.5-.1-.5-.4v-1.9c0-.2.2-.4.5-.4h1.8c.3 0 .5.2.5.4v1.9zm0-4.6c0 .2-.2.4-.5.4h-1.8c-.3 0-.5-.2-.5-.4v-1.9c0-.3.2-.4.5-.4h1.8c.3 0 .5.1.5.4v1.9zm2.3-11.6H18v-.9c0-.7-.6-1.4-1.4-1.4-.7 0-1.4.6-1.4 1.4v.9H8.8v-.9c0-.7-.6-1.4-1.4-1.4C6.6.9 6 1.5 6 2.3v.9H3.7c-1 0-1.9.9-1.9 1.9v1.1c0 .4.4.7.7.7h19c.3 0 .7-.3.7-.7V5.1c0-1-.9-1.9-1.9-1.9z"></path></svg></span></div><!----></div></div></div></div></div>
    
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
                        <button type="button" class="mm-button" @click="onCancel"><!----><!---->取消<!----></button>
                    </div>
                    <div class="mm-space-item">
                        <button type="button" class="mm-button mm-button__primary" @click="onSave"><!----><!---->保存<!----></button>
                    </div>
                </div>
            </div>
        </div>
		
	</div>
</template>

<script>
	export default {
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
</style>
