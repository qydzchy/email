<template>
    <div class="dispatcher-page">
        <div class="mail-plus-rule config-page-item">
            <div class="mail-rule-block" v-show="!showForm">
                <div class="mail-rule-content">
                    <div class="mail-rule-content-list">
                        <div class="mail-config-header">
                            <div>
                                <h3 class="config-page-title">收发件规则</h3><span class="tips">收件规则将按从上到下依次执行，可拖拽调整规则顺序</span>
                            </div><span class="mm-tooltip"><span class="mm-tooltip-trigger"><button type="button"
                                        class="mm-button mm-button__primary"
                                        @click="onShowForm"><!----><!---->新建规则<!----></button></span><!----></span>
                        </div>
                        <ul class="mail-rule-list">
                            <div class="mm-table-wrapper"><!---->
                                <div class="mm-table mm-table__medium mm-table__horizontal-bordered">
                                    <div class="mm-thead" style="margin-right: -8px; margin-bottom: -8px;">
                                        <table cellpadding="0" cellspacing="0" border="0">
                                            <colgroup><!---->
                                                <col width="20">
                                                <col width="60">
                                                <col width="60">
                                                <col width="200">
                                                <col width="100">
                                            </colgroup>
                                            <thead>
                                                <tr class=""><!---->
                                                    <th class="">
                                                        <div class="mm-table-cell" title="" style="text-align: left;">
                                                            <!----><!---->
                                                        </div>
                                                        <div class="mm-thead-dragbar__next mm-thead-dragbar"></div>
                                                    </th>
                                                    <th class="">
                                                        <div class="mm-table-cell" title="" style="text-align: left;">
                                                            类型<!----><!----></div>
                                                        <div class="mm-thead-dragbar__next mm-thead-dragbar"></div>
                                                    </th>
                                                    <th class="">
                                                        <div class="mm-table-cell" title="" style="text-align: left;">
                                                            规则名称<!----><!----></div>
                                                        <div class="mm-thead-dragbar__next mm-thead-dragbar"></div>
                                                    </th>
                                                    <th class="">
                                                        <div class="mm-table-cell" title="" style="text-align: left;">
                                                            执行规则<!----><!----></div>
                                                        <div class="mm-thead-dragbar__next mm-thead-dragbar"></div>
                                                    </th>
                                                    <th class="">
                                                        <div class="mm-table-cell" title="" style="text-align: right;">
                                                            操作<!----><!----></div>
                                                        <div class="mm-thead-dragbar__next mm-thead-dragbar"></div>
                                                    </th>
                                                </tr>
                                            </thead>
                                        </table>
                                    </div>
                                    <div class="mm-tbody"
                                        style="margin-bottom: -8px; margin-right: -8px; max-height: 682px;">
                                        <table cellpadding="0" cellspacing="0" border="0">
                                            <colgroup><!---->
                                                <col width="20">
                                                <col width="60">
                                                <col width="60">
                                                <col width="200">
                                                <col width="100">
                                            </colgroup><!---->
                                            <tbody>
                                                <tr class="mm-tbody-tr" draggable="true"><!---->
                                                    <td>
                                                        <div class="mm-table-cell" title="" style="text-align: left;"><a
                                                                class="e-move"><span
                                                                    class="m-icon icon-move-btn"></span></a>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="mm-table-cell" title="" style="text-align: left;"><span
                                                                class="mm-tag__success mm-tag__mini mm-tag">收件<!----></span>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="mm-table-cell" title="" style="text-align: left;">测试
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="mm-table-cell" title="" style="text-align: left;">
                                                            <div title="若同时满足： 发件人 包含test@qq.com，  则：标记为待处理邮件并设置稍后处理时间"
                                                                class="rule-desc ellipsis">若同时满足： 发件人 包含test@qq.com，
                                                                则：标记为待处理邮件并设置稍后处理时间</div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="mm-table-cell" title="" style="text-align: right;">
                                                            <div><label class="mm-switch__mini mm-switch"><input
                                                                        type="checkbox">
                                                                    <div class="mm-switch-faux">
                                                                        <div class="mm-switch-input"><span
                                                                                class="mm-switch-checked-box"></span><span
                                                                                class="mm-switch-icon-wrap"><!----><!----></span><span
                                                                                class="mm-switch-unchecked-box"></span>
                                                                        </div>
                                                                        <div class="mm-switch__pending-hint"></div>
                                                                    </div>
                                                                </label><!----><button type="button"
                                                                    class="mm-button mm-button__text btn-list-item"><!----><!---->编辑<!----></button><button
                                                                    type="button"
                                                                    class="mm-button mm-button__text btn-list-item"
                                                                    style="padding-right: 0px;"><!----><!---->删除<!----></button>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table><!---->
                                    </div><!----><!----><!---->
                                </div><!----><!---->
                                <div class="mm-spinner" style="display: none;">
                                    <div class="mm-spinner-mask"></div>
                                    <div class="mm-spinner-wrapper">
                                        <div class="mm-loading"
                                            style="height: 40px; width: 40px; color: rgb(38, 132, 255);">
                                            <svg class="circular" viewBox="25 25 50 50">
                                                <circle stroke-width="2" class="path" cx="50" cy="50" r="20" fill="none"
                                                    stroke-miterlimit="10"></circle>
                                            </svg>
                                        </div><!---->
                                    </div>
                                </div>
                            </div><!---->
                        </ul>
                    </div>
                </div>
                <div class="c-page-loading loading-more" style="display: none;"><span></span></div>
            </div>
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
                                            <div class="v-form-item-control"><span class="v-form-item-children">
                                                    <div class="mm-radio-group"><label class="mm-radio"><input
                                                                name="mm-radio-group-287" type="radio" value="1"><span
                                                                class="mm-radio-faux"><span
                                                                    class="mm-radio-input"></span><span
                                                                    class="mm-radio-label">收件规则</span></span></label><label
                                                            class="mm-radio"><input name="mm-radio-group-287" type="radio"
                                                                value="2"><span class="mm-radio-faux"><span
                                                                    class="mm-radio-input"></span><span
                                                                    class="mm-radio-label">发件规则</span></span></label></div>
                                                </span><!----></div>
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
                                            <div class="v-form-item-control"><span class="v-form-item-children"><span
                                                        class="mm-input"><!----><span
                                                            class="mm-input-affix-wrapper"><!----><input placeholder="请输入"
                                                                maxlength="256" type="text"
                                                                class="mm-input-inner"><!----></span><!----></span></span><!---->
                                            </div>
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
                                                    <div><i class="m-icon icon-nav-help"></i></div><!---->
                                                </div>
                                                </p>
                                            </div>
                                            <div class="v-form-item-control"><span class="v-form-item-children">
                                                    <div class="mm-radio-group"><label class="mm-radio"><input
                                                                name="mm-radio-group-288" type="radio" value="1"><span
                                                                class="mm-radio-faux"><span
                                                                    class="mm-radio-input"></span><span
                                                                    class="mm-radio-label">满足以下所有条件</span></span></label><label
                                                            class="mm-radio"><input name="mm-radio-group-288" type="radio"
                                                                value="2"><span class="mm-radio-faux"><span
                                                                    class="mm-radio-input"></span><span
                                                                    class="mm-radio-label">满足以下任一条件</span></span></label>
                                                    </div>
                                                </span><!----></div>
                                        </div>
                                        <div class="mail-operation field-block">
                                            <div class="item-value">
                                                <div class="mail-rule-edit-filterSettings-item"><span
                                                        class="num">1</span><span class="mm-select">
                                                        <div class="mm-selector--single mm-selector" tabindex="0"><!---->
                                                            <div class="mm-selector-rendered"><span title="如果发件人"
                                                                    class="mm-selector-selected-value"
                                                                    style="opacity: 1;">如果发件人</span><!----></div>
                                                            <!----><span class="mm-selector-suffix-icon"><svg
                                                                    class="mm-icon mm-icon-chevrondown mm-selector-arrow"
                                                                    viewBox="0 0 24 24" name="chevrondown" xmlns="XMLNS"
                                                                    fill="currentColor"
                                                                    style="height: 12px; width: 12px; outline: none;">
                                                                    <path
                                                                        d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z">
                                                                    </path>
                                                                </svg></span>
                                                        </div><!---->
                                                    </span><span
                                                        class="mm-select filter-setting-select filter-setting-field">
                                                        <div class="mm-selector--single mm-selector" tabindex="0"><!---->
                                                            <div class="mm-selector-rendered"><span title="包含"
                                                                    class="mm-selector-selected-value"
                                                                    style="opacity: 1;">包含</span><!----></div><!----><span
                                                                class="mm-selector-suffix-icon"><svg
                                                                    class="mm-icon mm-icon-chevrondown mm-selector-arrow"
                                                                    viewBox="0 0 24 24" name="chevrondown" xmlns="XMLNS"
                                                                    fill="currentColor"
                                                                    style="height: 12px; width: 12px; outline: none;">
                                                                    <path
                                                                        d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z">
                                                                    </path>
                                                                </svg></span>
                                                        </div><!---->
                                                    </span>
                                                    <div class="v-form-item" is_public="" type="1" id="286"><!---->
                                                        <div class="v-form-item-control"><span
                                                                class="v-form-item-children"><span
                                                                    class="mm-input filter-setting-field"><!----><span
                                                                        class="mm-input-affix-wrapper"><!----><input
                                                                            placeholder="例如：test、@xiaomcn.cn" type="text"
                                                                            class="mm-input-inner"><!----></span><!----></span><!----></span><!---->
                                                        </div>
                                                    </div>
                                                    <div class="mail-rule-edit-filterSettings-btn"><!----><span
                                                            class="okki-icon-wrap" color="#7A8599">&ZeroWidthSpace;<svg
                                                                xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                                                                viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
                                                                fill="#7A8599">
                                                                <path fill-rule="evenodd" clip-rule="evenodd"
                                                                    d="M12 2a10 10 0 100 20 10 10 0 000-20zm0 18a8 8 0 110-16.001A8 8 0 0112 20z">
                                                                </path>
                                                                <path
                                                                    d="M13 8a1 1 0 10-2 0v3H8a1 1 0 100 2h3v3a1 1 0 102 0v-3h3a1 1 0 100-2h-3V8z">
                                                                </path>
                                                            </svg></span></div>
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
                                            <div class="v-form-item-control"><span class="v-form-item-children">
                                                    <div class="mm-radio-group"><label class="mm-radio"><input
                                                                name="mm-radio-group-289" type="radio" value="0"><span
                                                                class="mm-radio-faux"><span
                                                                    class="mm-radio-input"></span><span
                                                                    class="mm-radio-label">执行以下操作</span></span></label><label
                                                            class="mm-radio"><input name="mm-radio-group-289" type="radio"
                                                                value="1"><span class="mm-radio-faux"><span
                                                                    class="mm-radio-input"></span><span
                                                                    class="mm-radio-label">移动到【已删除邮件】</span></span></label>
                                                    </div>
                                                </span><!----></div>
                                        </div>
                                        <div class="operation-list block field-block">
                                            <div class="item-value">
                                                <div class="filter-name"><label class="mm-checkbox"><input true-value="1"
                                                            false-value="0" type="checkbox"><span
                                                            class="mm-checkbox-input"></span><span
                                                            class="mm-checkbox-label"><span>打钉</span></span></label></div>
                                            </div>
                                            <div class="item-value">
                                                <div class="mail-rule-edit-operation-markAsRead">
                                                    <div class="filter-name"><label class="mm-checkbox"><input
                                                                true-value="1" false-value="0" type="checkbox"><span
                                                                class="mm-checkbox-input"></span><span
                                                                class="mm-checkbox-label"><span>标记为【已读】</span></span></label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="item-value">
                                                <div class="filter-name"><label class="mm-checkbox"><input true-value="1"
                                                            false-value="0" type="checkbox"><span
                                                            class="mm-checkbox-input"></span><span
                                                            class="mm-checkbox-label"><span>标记为</span></span></label></div>
                                                <span class="mm-select">
                                                    <div class="mm-selector--single mm-selector" tabindex="0"><span
                                                            class="mm-selector__placeholder">请选择分类</span>
                                                        <div class="mm-selector-rendered"></div><!----><span
                                                            class="mm-selector-suffix-icon"><svg
                                                                class="mm-icon mm-icon-chevrondown mm-selector-arrow"
                                                                viewBox="0 0 24 24" name="chevrondown" xmlns="XMLNS"
                                                                fill="currentColor"
                                                                style="height: 12px; width: 12px; outline: none;">
                                                                <path
                                                                    d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z">
                                                                </path>
                                                            </svg></span>
                                                    </div><!---->
                                                </span>
                                            </div>
                                            <div class="item-value">
                                                <div class="filter-name"><label class="mm-checkbox"><input true-value="1"
                                                            false-value="0" type="checkbox"><span
                                                            class="mm-checkbox-input"></span><span
                                                            class="mm-checkbox-label"><span>移动到</span></span></label></div>
                                                <div class="mm-tree-select">
                                                    <div class="mm-selector--single mm-selector" tabindex="0"><span
                                                            class="mm-selector__placeholder">请选择文件夹</span>
                                                        <div class="mm-selector-rendered"><span title=""
                                                                class="mm-selector-selected-value"
                                                                style="opacity: 1;"></span><!----></div><!----><span
                                                            class="mm-selector-suffix-icon"><svg
                                                                class="mm-icon mm-icon-chevrondown mm-selector-arrow"
                                                                viewBox="0 0 24 24" name="chevrondown" xmlns="XMLNS"
                                                                fill="currentColor"
                                                                style="height: 12px; width: 12px; outline: none;">
                                                                <path
                                                                    d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z">
                                                                </path>
                                                            </svg></span>
                                                    </div><!---->
                                                </div>
                                            </div>
                                            <div class="item-value review-role-wrap">
                                                <div class="filter-name"><label class="mm-checkbox"><input true-value="1"
                                                            false-value="0" type="checkbox"><span
                                                            class="mm-checkbox-input"></span><span
                                                            class="mm-checkbox-label"><span>转发至</span></span></label></div>
                                                <span class="mm-input"><!----><span
                                                        class="mm-input-affix-wrapper"><!----><input placeholder="请输入邮箱地址"
                                                            type="text" class="mm-input-inner"><!----></span><!----></span>
                                            </div>
                                            <div class="item-value mark-pending review-role-wrap">
                                                <div class="filter-name"><label class="mm-checkbox"><input true-value="true"
                                                            type="checkbox"><span class="mm-checkbox-input"></span><span
                                                            class="mm-checkbox-label"><span>标记为【待处理邮件】并设置稍后处理时间为：</span></span></label>
                                                    <div class="mark-pending-radio special-radio">
                                                        <div class="mm-radio-group"><label class="mm-radio"><input
                                                                    name="mm-radio-group-293" type="radio" value="1"><span
                                                                    class="mm-radio-faux"><span
                                                                        class="mm-radio-input"></span><span
                                                                        class="mm-radio-label">邮件接收时间</span></span></label><label
                                                                class="mm-radio"><input name="mm-radio-group-293"
                                                                    type="radio" value="2"><span class="mm-radio-faux"><span
                                                                        class="mm-radio-input"></span><span
                                                                        class="mm-radio-label">邮件接收时间之后的第</span></span></label>
                                                        </div><span
                                                            class="mm-input--suffix mm-input mm-input-number"><!----><span
                                                                class="mm-input-affix-wrapper"><!----><input type="text"
                                                                    class="mm-input-inner"><span
                                                                    class="mm-input-suffix"><!---->
                                                                    <div class="mm-input-number-control-wrapper"><span
                                                                            class="mm-input-number-control"><svg
                                                                                class="mm-icon mm-icon-down"
                                                                                viewBox="0 0 24 24" name="down"
                                                                                xmlns="XMLNS" fill="#606266"
                                                                                style="height: 12px; width: 12px;">
                                                                                <path
                                                                                    d="M3.8 6.5h16.4c.4 0 .8.6.4 1l-8 9.8c-.3.3-.9.3-1.2 0l-8-9.8c-.4-.4-.1-1 .4-1z">
                                                                                </path>
                                                                            </svg></span><span
                                                                            class="mm-input-number-control"><svg
                                                                                class="mm-icon mm-icon-up"
                                                                                viewBox="0 0 24 24" name="up" xmlns="XMLNS"
                                                                                fill="#606266"
                                                                                style="height: 12px; width: 12px;">
                                                                                <path
                                                                                    d="M20.2 17.5H3.8c-.4 0-.8-.6-.4-1l8-9.8c.3-.3.9-.3 1.2 0l8 9.8c.4.4.1 1-.4 1z">
                                                                                </path>
                                                                            </svg></span></div>
                                                                </span></span><!----></span><span
                                                            style="padding: 0px 8px;">天</span><span class="mm-time-picker">
                                                            <div class="mm-selector--single mm-selector" tabindex="0">
                                                                <!---->
                                                                <div class="mm-selector-rendered"><span title="09:00:00"
                                                                        class="mm-selector-selected-value"
                                                                        style="opacity: 1;">09:00:00</span><!----></div>
                                                                <!----><span class="mm-selector-suffix-icon"><svg
                                                                        class="mm-icon mm-icon-clock" viewBox="0 0 24 24"
                                                                        name="clock" xmlns="XMLNS" fill="currentColor"
                                                                        style="height: 12px; width: 12px;">
                                                                        <path
                                                                            d="M12 .9C5.9.9.9 5.9.9 12s5 11.1 11.1 11.1 11.1-5 11.1-11.1S18.1.9 12 .9zm0 19.4c-4.6 0-8.3-3.7-8.3-8.3S7.4 3.7 12 3.7s8.3 3.7 8.3 8.3-3.7 8.3-8.3 8.3zm1.6-8.2c-.2-.1-.2-.3-.2-.5V7.2c0-.4-.3-.7-.7-.7h-1.4c-.4 0-.7.3-.7.7v5.5c0 .2.1.4.2.5l3.4 3.5c.3.2.7.2 1 0l1-1c.2-.3.2-.7 0-1l-2.6-2.6z">
                                                                        </path>
                                                                    </svg></span>
                                                            </div><!---->
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="item-value review-role-wrap">
                                                <div class="filter-name auto-reply-box"><label class="mm-checkbox"
                                                        style="display: flex; align-items: baseline;"><input true-value="1"
                                                            false-value="0" type="checkbox"><span
                                                            class="mm-checkbox-input"></span><span
                                                            class="mm-checkbox-label">
                                                            <div class="auto-reply-filter"><span
                                                                    class="auto-reply-label">自动回复 <span class="tips">若 4
                                                                        天内收到同一邮箱的多封邮件，系统只进行一次自动回复</span></span><textarea
                                                                    id="mail_editor" placeholder="" style="display: none;"
                                                                    aria-hidden="true"></textarea>
                                                                <div role="application" class="tox tox-tinymce"
                                                                    style="visibility: hidden; height: 200px;">
                                                                    <div class="tox-editor-container">
                                                                        <div data-alloy-vertical-dir="toptobottom"
                                                                            class="tox-editor-header">
                                                                            <div class="tox-toolbar-overlord">
                                                                                <div role="group" class="tox-toolbar">
                                                                                    <div title="" role="toolbar"
                                                                                        data-alloy-tabstop="true"
                                                                                        tabindex="-1"
                                                                                        class="tox-toolbar__group"><button
                                                                                            aria-label="撤消" title="撤消"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn tox-tbtn--disabled"
                                                                                            aria-disabled="true"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M6.4 8H12c3.7 0 6.2 2 6.8 5.1.6 2.7-.4 5.6-2.3 6.8a1 1 0 0 1-1-1.8c1.1-.6 1.8-2.7 1.4-4.6-.5-2.1-2.1-3.5-4.9-3.5H6.4l3.3 3.3a1 1 0 1 1-1.4 1.4l-5-5a1 1 0 0 1 0-1.4l5-5a1 1 0 0 1 1.4 1.4L6.4 8z"
                                                                                                        fill-rule="nonzero">
                                                                                                    </path>
                                                                                                </svg></span></button><button
                                                                                            aria-label="重复" title="重复"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn tox-tbtn--disabled"
                                                                                            aria-disabled="true"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M17.6 10H12c-2.8 0-4.4 1.4-4.9 3.5-.4 2 .3 4 1.4 4.6a1 1 0 1 1-1 1.8c-2-1.2-2.9-4.1-2.3-6.8.6-3 3-5.1 6.8-5.1h5.6l-3.3-3.3a1 1 0 1 1 1.4-1.4l5 5a1 1 0 0 1 0 1.4l-5 5a1 1 0 0 1-1.4-1.4l3.3-3.3z"
                                                                                                        fill-rule="nonzero">
                                                                                                    </path>
                                                                                                </svg></span></button><button
                                                                                            aria-label="格式刷" title="格式刷"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn"
                                                                                            aria-pressed="false"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M18 5V4c0-.5-.4-1-1-1H5a1 1 0 0 0-1 1v4c0 .6.5 1 1 1h12c.6 0 1-.4 1-1V7h1v4H9v9c0 .6.4 1 1 1h2c.6 0 1-.4 1-1v-7h8V5h-3z"
                                                                                                        fill-rule="nonzero">
                                                                                                    </path>
                                                                                                </svg></span></button><button
                                                                                            aria-label="清除格式" title="清除格式"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M13.2 6a1 1 0 0 1 0 .2l-2.6 10a1 1 0 0 1-1 .8h-.2a.8.8 0 0 1-.8-1l2.6-10H8a1 1 0 1 1 0-2h9a1 1 0 0 1 0 2h-3.8zM5 18h7a1 1 0 0 1 0 2H5a1 1 0 0 1 0-2zm13 1.5L16.5 18 15 19.5a.7.7 0 0 1-1-1l1.5-1.5-1.5-1.5a.7.7 0 0 1 1-1l1.5 1.5 1.5-1.5a.7.7 0 0 1 1 1L17.5 17l1.5 1.5a.7.7 0 0 1-1 1z"
                                                                                                        fill-rule="evenodd">
                                                                                                    </path>
                                                                                                </svg></span></button></div>
                                                                                    <div title="" role="toolbar"
                                                                                        data-alloy-tabstop="true"
                                                                                        tabindex="-1"
                                                                                        class="tox-toolbar__group"><button
                                                                                            title="Fonts" aria-label="Fonts"
                                                                                            aria-haspopup="true"
                                                                                            type="button" unselectable="on"
                                                                                            tabindex="-1"
                                                                                            class="tox-tbtn tox-tbtn--select tox-tbtn--bespoke"
                                                                                            aria-expanded="false"
                                                                                            style="user-select: none;"><span
                                                                                                class="tox-tbtn__select-label">Verdana</span>
                                                                                            <div
                                                                                                class="tox-tbtn__select-chevron">
                                                                                                <svg width="10" height="10">
                                                                                                    <path
                                                                                                        d="M8.7 2.2c.3-.3.8-.3 1 0 .4.4.4.9 0 1.2L5.7 7.8c-.3.3-.9.3-1.2 0L.2 3.4a.8.8 0 0 1 0-1.2c.3-.3.8-.3 1.1 0L5 6l3.7-3.8z"
                                                                                                        fill-rule="nonzero">
                                                                                                    </path>
                                                                                                </svg>
                                                                                            </div>
                                                                                        </button></div>
                                                                                    <div title="" role="toolbar"
                                                                                        data-alloy-tabstop="true"
                                                                                        tabindex="-1"
                                                                                        class="tox-toolbar__group"><button
                                                                                            title="字号" aria-label="字号"
                                                                                            aria-haspopup="true"
                                                                                            type="button" unselectable="on"
                                                                                            tabindex="-1"
                                                                                            class="tox-tbtn tox-tbtn--select tox-tbtn--bespoke"
                                                                                            aria-expanded="false"
                                                                                            style="user-select: none;"><span
                                                                                                class="tox-tbtn__select-label">14px</span>
                                                                                            <div
                                                                                                class="tox-tbtn__select-chevron">
                                                                                                <svg width="10" height="10">
                                                                                                    <path
                                                                                                        d="M8.7 2.2c.3-.3.8-.3 1 0 .4.4.4.9 0 1.2L5.7 7.8c-.3.3-.9.3-1.2 0L.2 3.4a.8.8 0 0 1 0-1.2c.3-.3.8-.3 1.1 0L5 6l3.7-3.8z"
                                                                                                        fill-rule="nonzero">
                                                                                                    </path>
                                                                                                </svg>
                                                                                            </div>
                                                                                        </button></div>
                                                                                    <div title="" role="toolbar"
                                                                                        data-alloy-tabstop="true"
                                                                                        tabindex="-1"
                                                                                        class="tox-toolbar__group"><button
                                                                                            aria-label="粗体" title="粗体"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn"
                                                                                            aria-pressed="false"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M7.8 19c-.3 0-.5 0-.6-.2l-.2-.5V5.7c0-.2 0-.4.2-.5l.6-.2h5c1.5 0 2.7.3 3.5 1 .7.6 1.1 1.4 1.1 2.5a3 3 0 0 1-.6 1.9c-.4.6-1 1-1.6 1.2.4.1.9.3 1.3.6s.8.7 1 1.2c.4.4.5 1 .5 1.6 0 1.3-.4 2.3-1.3 3-.8.7-2.1 1-3.8 1H7.8zm5-8.3c.6 0 1.2-.1 1.6-.5.4-.3.6-.7.6-1.3 0-1.1-.8-1.7-2.3-1.7H9.3v3.5h3.4zm.5 6c.7 0 1.3-.1 1.7-.4.4-.4.6-.9.6-1.5s-.2-1-.7-1.4c-.4-.3-1-.4-2-.4H9.4v3.8h4z"
                                                                                                        fill-rule="evenodd">
                                                                                                    </path>
                                                                                                </svg></span></button><button
                                                                                            aria-label="斜体" title="斜体"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn"
                                                                                            aria-pressed="false"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M16.7 4.7l-.1.9h-.3c-.6 0-1 0-1.4.3-.3.3-.4.6-.5 1.1l-2.1 9.8v.6c0 .5.4.8 1.4.8h.2l-.2.8H8l.2-.8h.2c1.1 0 1.8-.5 2-1.5l2-9.8.1-.5c0-.6-.4-.8-1.4-.8h-.3l.2-.9h5.8z"
                                                                                                        fill-rule="evenodd">
                                                                                                    </path>
                                                                                                </svg></span></button><button
                                                                                            aria-label="下划线" title="下划线"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn"
                                                                                            aria-pressed="false"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M16 5c.6 0 1 .4 1 1v5.5a4 4 0 0 1-.4 1.8l-1 1.4a5.3 5.3 0 0 1-5.5 1 5 5 0 0 1-1.6-1c-.5-.4-.8-.9-1.1-1.4a4 4 0 0 1-.4-1.8V6c0-.6.4-1 1-1s1 .4 1 1v5.5c0 .3 0 .6.2 1l.6.7a3.3 3.3 0 0 0 2.2.8 3.4 3.4 0 0 0 2.2-.8c.3-.2.4-.5.6-.8l.2-.9V6c0-.6.4-1 1-1zM8 17h8c.6 0 1 .4 1 1s-.4 1-1 1H8a1 1 0 0 1 0-2z"
                                                                                                        fill-rule="evenodd">
                                                                                                    </path>
                                                                                                </svg></span></button></div>
                                                                                    <div title="" role="toolbar"
                                                                                        data-alloy-tabstop="true"
                                                                                        tabindex="-1"
                                                                                        class="tox-toolbar__group">
                                                                                        <div aria-pressed="false"
                                                                                            aria-label="文字颜色" title="文字颜色"
                                                                                            role="button"
                                                                                            aria-haspopup="true"
                                                                                            unselectable="on" tabindex="-1"
                                                                                            class="tox-split-button"
                                                                                            aria-expanded="false"
                                                                                            aria-describedby="aria_96107099851701703746383440"
                                                                                            style="user-select: none;"><span
                                                                                                role="presentation"
                                                                                                tabindex="-1"
                                                                                                class="tox-tbtn"><span
                                                                                                    class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                        width="19"
                                                                                                        height="19"
                                                                                                        viewBox="0 0 24 24">
                                                                                                        <g
                                                                                                            fill-rule="evenodd">
                                                                                                            <path
                                                                                                                id="tox-icon-text-color__color"
                                                                                                                d="M3 18h18v3H3z">
                                                                                                            </path>
                                                                                                            <path
                                                                                                                d="M8.7 16h-.8a.5.5 0 0 1-.5-.6l2.7-9c.1-.3.3-.4.5-.4h2.8c.2 0 .4.1.5.4l2.7 9a.5.5 0 0 1-.5.6h-.8a.5.5 0 0 1-.4-.4l-.7-2.2c0-.3-.3-.4-.5-.4h-3.4c-.2 0-.4.1-.5.4l-.7 2.2c0 .3-.2.4-.4.4zm2.6-7.6l-.6 2a.5.5 0 0 0 .5.6h1.6a.5.5 0 0 0 .5-.6l-.6-2c0-.3-.3-.4-.5-.4h-.4c-.2 0-.4.1-.5.4z">
                                                                                                            </path>
                                                                                                        </g>
                                                                                                    </svg></span></span><span
                                                                                                role="presentation"
                                                                                                tabindex="-1"
                                                                                                class="tox-tbtn tox-split-button__chevron"><svg
                                                                                                    width="10" height="10">
                                                                                                    <path
                                                                                                        d="M8.7 2.2c.3-.3.8-.3 1 0 .4.4.4.9 0 1.2L5.7 7.8c-.3.3-.9.3-1.2 0L.2 3.4a.8.8 0 0 1 0-1.2c.3-.3.8-.3 1.1 0L5 6l3.7-3.8z"
                                                                                                        fill-rule="nonzero">
                                                                                                    </path>
                                                                                                </svg></span><span
                                                                                                aria-hidden="true"
                                                                                                id="aria_96107099851701703746383440"
                                                                                                style="display: none;">To
                                                                                                open the popup, press
                                                                                                Shift+Enter</span></div>
                                                                                        <div aria-pressed="false"
                                                                                            aria-label="背景色" title="背景色"
                                                                                            role="button"
                                                                                            aria-haspopup="true"
                                                                                            unselectable="on" tabindex="-1"
                                                                                            class="tox-split-button"
                                                                                            aria-expanded="false"
                                                                                            aria-describedby="aria_73014372351721703746383441"
                                                                                            style="user-select: none;"><span
                                                                                                role="presentation"
                                                                                                tabindex="-1"
                                                                                                class="tox-tbtn"><span
                                                                                                    class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                        width="19"
                                                                                                        height="19"
                                                                                                        viewBox="0 0 24 24">
                                                                                                        <g
                                                                                                            fill-rule="evenodd">
                                                                                                            <path
                                                                                                                id="tox-icon-highlight-bg-color__color"
                                                                                                                d="M3 18h18v3H3z">
                                                                                                            </path>
                                                                                                            <path
                                                                                                                fill-rule="nonzero"
                                                                                                                d="M7.7 16.7H3l3.3-3.3-.7-.8L10.2 8l4 4.1-4 4.2c-.2.2-.6.2-.8 0l-.6-.7-1.1 1.1zm5-7.5L11 7.4l3-2.9a2 2 0 0 1 2.6 0L18 6c.7.7.7 2 0 2.7l-2.9 2.9-1.8-1.8-.5-.6">
                                                                                                            </path>
                                                                                                        </g>
                                                                                                    </svg></span></span><span
                                                                                                role="presentation"
                                                                                                tabindex="-1"
                                                                                                class="tox-tbtn tox-split-button__chevron"><svg
                                                                                                    width="10" height="10">
                                                                                                    <path
                                                                                                        d="M8.7 2.2c.3-.3.8-.3 1 0 .4.4.4.9 0 1.2L5.7 7.8c-.3.3-.9.3-1.2 0L.2 3.4a.8.8 0 0 1 0-1.2c.3-.3.8-.3 1.1 0L5 6l3.7-3.8z"
                                                                                                        fill-rule="nonzero">
                                                                                                    </path>
                                                                                                </svg></span><span
                                                                                                aria-hidden="true"
                                                                                                id="aria_73014372351721703746383441"
                                                                                                style="display: none;">To
                                                                                                open the popup, press
                                                                                                Shift+Enter</span></div>
                                                                                    </div>
                                                                                    <div title="" role="toolbar"
                                                                                        data-alloy-tabstop="true"
                                                                                        tabindex="-1"
                                                                                        class="tox-toolbar__group"><button
                                                                                            aria-label="对齐方式" title="对齐方式"
                                                                                            aria-haspopup="true"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn"
                                                                                            aria-expanded="false"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M5 5h14c.6 0 1 .4 1 1s-.4 1-1 1H5a1 1 0 1 1 0-2zm0 4h8c.6 0 1 .4 1 1s-.4 1-1 1H5a1 1 0 1 1 0-2zm0 8h8c.6 0 1 .4 1 1s-.4 1-1 1H5a1 1 0 0 1 0-2zm0-4h14c.6 0 1 .4 1 1s-.4 1-1 1H5a1 1 0 0 1 0-2z"
                                                                                                        fill-rule="evenodd">
                                                                                                    </path>
                                                                                                </svg></span></button><button
                                                                                            title="行间距" aria-label="行间距"
                                                                                            aria-haspopup="true"
                                                                                            type="button"
                                                                                            data-alloy-tabstop="true"
                                                                                            unselectable="on" tabindex="-1"
                                                                                            class="tox-tbtn tox-tbtn--select"
                                                                                            aria-expanded="false"
                                                                                            style="user-select: none;"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24"
                                                                                                    focusable="false">
                                                                                                    <path
                                                                                                        d="M21 5a1 1 0 01.1 2H13a1 1 0 01-.1-2H21zm0 4a1 1 0 01.1 2H13a1 1 0 01-.1-2H21zm0 4a1 1 0 01.1 2H13a1 1 0 01-.1-2H21zm0 4a1 1 0 01.1 2H13a1 1 0 01-.1-2H21zM7 3.6l3.7 3.7a1 1 0 01-1.3 1.5h-.1L8 7.3v9.2l1.3-1.3a1 1 0 011.3 0h.1c.4.4.4 1 0 1.3v.1L7 20.4l-3.7-3.7a1 1 0 011.3-1.5h.1L6 16.7V7.4L4.7 8.7a1 1 0 01-1.3 0h-.1a1 1 0 010-1.3v-.1L7 3.6z">
                                                                                                    </path>
                                                                                                </svg></span>
                                                                                            <div
                                                                                                class="tox-tbtn__select-chevron">
                                                                                                <svg width="10" height="10">
                                                                                                    <path
                                                                                                        d="M8.7 2.2c.3-.3.8-.3 1 0 .4.4.4.9 0 1.2L5.7 7.8c-.3.3-.9.3-1.2 0L.2 3.4a.8.8 0 0 1 0-1.2c.3-.3.8-.3 1.1 0L5 6l3.7-3.8z"
                                                                                                        fill-rule="nonzero">
                                                                                                    </path>
                                                                                                </svg>
                                                                                            </div>
                                                                                        </button></div>
                                                                                    <div title="" role="toolbar"
                                                                                        data-alloy-tabstop="true"
                                                                                        tabindex="-1"
                                                                                        class="tox-toolbar__group"><button
                                                                                            aria-label="插入/编辑链接"
                                                                                            title="插入/编辑链接" type="button"
                                                                                            tabindex="-1" class="tox-tbtn"
                                                                                            aria-pressed="false"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M6.2 12.3a1 1 0 0 1 1.4 1.4l-2.1 2a2 2 0 1 0 2.7 2.8l4.8-4.8a1 1 0 0 0 0-1.4 1 1 0 1 1 1.4-1.3 2.9 2.9 0 0 1 0 4L9.6 20a3.9 3.9 0 0 1-5.5-5.5l2-2zm11.6-.6a1 1 0 0 1-1.4-1.4l2-2a2 2 0 1 0-2.6-2.8L11 10.3a1 1 0 0 0 0 1.4A1 1 0 1 1 9.6 13a2.9 2.9 0 0 1 0-4L14.4 4a3.9 3.9 0 0 1 5.5 5.5l-2 2z"
                                                                                                        fill-rule="nonzero">
                                                                                                    </path>
                                                                                                </svg></span></button><button
                                                                                            aria-label="twemoji"
                                                                                            title="twemoji" type="button"
                                                                                            tabindex="-1"
                                                                                            class="tox-tbtn"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M9 11c.6 0 1-.4 1-1s-.4-1-1-1a1 1 0 0 0-1 1c0 .6.4 1 1 1zm6 0c.6 0 1-.4 1-1s-.4-1-1-1a1 1 0 0 0-1 1c0 .6.4 1 1 1zm-3 5.5c2.1 0 4-1.5 4.4-3.5H7.6c.5 2 2.3 3.5 4.4 3.5zM12 4a8 8 0 1 0 0 16 8 8 0 0 0 0-16zm0 14.5a6.5 6.5 0 1 1 0-13 6.5 6.5 0 0 1 0 13z"
                                                                                                        fill-rule="nonzero">
                                                                                                    </path>
                                                                                                </svg></span></button><button
                                                                                            title="表格" aria-label="表格"
                                                                                            aria-haspopup="true"
                                                                                            type="button"
                                                                                            data-alloy-tabstop="true"
                                                                                            unselectable="on" tabindex="-1"
                                                                                            class="tox-tbtn tox-tbtn--select"
                                                                                            aria-expanded="false"
                                                                                            style="user-select: none;"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        fill-rule="nonzero"
                                                                                                        d="M19 4a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V6c0-1.1.9-2 2-2h14zM5 14v4h6v-4H5zm14 0h-6v4h6v-4zm0-6h-6v4h6V8zM5 12h6V8H5v4z">
                                                                                                    </path>
                                                                                                </svg></span>
                                                                                            <div
                                                                                                class="tox-tbtn__select-chevron">
                                                                                                <svg width="10" height="10">
                                                                                                    <path
                                                                                                        d="M8.7 2.2c.3-.3.8-.3 1 0 .4.4.4.9 0 1.2L5.7 7.8c-.3.3-.9.3-1.2 0L.2 3.4a.8.8 0 0 1 0-1.2c.3-.3.8-.3 1.1 0L5 6l3.7-3.8z"
                                                                                                        fill-rule="nonzero">
                                                                                                    </path>
                                                                                                </svg>
                                                                                            </div>
                                                                                        </button></div>
                                                                                    <div title="" role="toolbar"
                                                                                        data-alloy-tabstop="true"
                                                                                        tabindex="-1"
                                                                                        class="tox-toolbar__group">
                                                                                        <div aria-pressed="false"
                                                                                            aria-label="项目符号" title="项目符号"
                                                                                            role="button"
                                                                                            aria-haspopup="true"
                                                                                            unselectable="on" tabindex="-1"
                                                                                            class="tox-split-button"
                                                                                            aria-expanded="false"
                                                                                            aria-describedby="aria_36170581251741703746383442"
                                                                                            style="user-select: none;"><span
                                                                                                role="presentation"
                                                                                                tabindex="-1"
                                                                                                class="tox-tbtn"><span
                                                                                                    class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                        width="19"
                                                                                                        height="19"
                                                                                                        viewBox="0 0 24 24">
                                                                                                        <path
                                                                                                            d="M11 5h8c.6 0 1 .4 1 1s-.4 1-1 1h-8a1 1 0 0 1 0-2zm0 6h8c.6 0 1 .4 1 1s-.4 1-1 1h-8a1 1 0 0 1 0-2zm0 6h8c.6 0 1 .4 1 1s-.4 1-1 1h-8a1 1 0 0 1 0-2zM4.5 6c0-.4.1-.8.4-1 .3-.4.7-.5 1.1-.5.4 0 .8.1 1 .4.4.3.5.7.5 1.1 0 .4-.1.8-.4 1-.3.4-.7.5-1.1.5-.4 0-.8-.1-1-.4-.4-.3-.5-.7-.5-1.1zm0 6c0-.4.1-.8.4-1 .3-.4.7-.5 1.1-.5.4 0 .8.1 1 .4.4.3.5.7.5 1.1 0 .4-.1.8-.4 1-.3.4-.7.5-1.1.5-.4 0-.8-.1-1-.4-.4-.3-.5-.7-.5-1.1zm0 6c0-.4.1-.8.4-1 .3-.4.7-.5 1.1-.5.4 0 .8.1 1 .4.4.3.5.7.5 1.1 0 .4-.1.8-.4 1-.3.4-.7.5-1.1.5-.4 0-.8-.1-1-.4-.4-.3-.5-.7-.5-1.1z"
                                                                                                            fill-rule="evenodd">
                                                                                                        </path>
                                                                                                    </svg></span></span><span
                                                                                                role="presentation"
                                                                                                tabindex="-1"
                                                                                                class="tox-tbtn tox-split-button__chevron"><svg
                                                                                                    width="10" height="10">
                                                                                                    <path
                                                                                                        d="M8.7 2.2c.3-.3.8-.3 1 0 .4.4.4.9 0 1.2L5.7 7.8c-.3.3-.9.3-1.2 0L.2 3.4a.8.8 0 0 1 0-1.2c.3-.3.8-.3 1.1 0L5 6l3.7-3.8z"
                                                                                                        fill-rule="nonzero">
                                                                                                    </path>
                                                                                                </svg></span><span
                                                                                                aria-hidden="true"
                                                                                                id="aria_36170581251741703746383442"
                                                                                                style="display: none;">To
                                                                                                open the popup, press
                                                                                                Shift+Enter</span></div>
                                                                                        <div aria-pressed="false"
                                                                                            aria-label="编号列表" title="编号列表"
                                                                                            role="button"
                                                                                            aria-haspopup="true"
                                                                                            unselectable="on" tabindex="-1"
                                                                                            class="tox-split-button"
                                                                                            aria-expanded="false"
                                                                                            aria-describedby="aria_61806371151761703746383443"
                                                                                            style="user-select: none;"><span
                                                                                                role="presentation"
                                                                                                tabindex="-1"
                                                                                                class="tox-tbtn"><span
                                                                                                    class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                        width="19"
                                                                                                        height="19"
                                                                                                        viewBox="0 0 24 24">
                                                                                                        <path
                                                                                                            d="M10 17h8c.6 0 1 .4 1 1s-.4 1-1 1h-8a1 1 0 0 1 0-2zm0-6h8c.6 0 1 .4 1 1s-.4 1-1 1h-8a1 1 0 0 1 0-2zm0-6h8c.6 0 1 .4 1 1s-.4 1-1 1h-8a1 1 0 1 1 0-2zM6 4v3.5c0 .3-.2.5-.5.5a.5.5 0 0 1-.5-.5V5h-.5a.5.5 0 0 1 0-1H6zm-1 8.8l.2.2h1.3c.3 0 .5.2.5.5s-.2.5-.5.5H4.9a1 1 0 0 1-.9-1V13c0-.4.3-.8.6-1l1.2-.4.2-.3a.2.2 0 0 0-.2-.2H4.5a.5.5 0 0 1-.5-.5c0-.3.2-.5.5-.5h1.6c.5 0 .9.4.9 1v.1c0 .4-.3.8-.6 1l-1.2.4-.2.3zM7 17v2c0 .6-.4 1-1 1H4.5a.5.5 0 0 1 0-1h1.2c.2 0 .3-.1.3-.3 0-.2-.1-.3-.3-.3H4.4a.4.4 0 1 1 0-.8h1.3c.2 0 .3-.1.3-.3 0-.2-.1-.3-.3-.3H4.5a.5.5 0 1 1 0-1H6c.6 0 1 .4 1 1z"
                                                                                                            fill-rule="evenodd">
                                                                                                        </path>
                                                                                                    </svg></span></span><span
                                                                                                role="presentation"
                                                                                                tabindex="-1"
                                                                                                class="tox-tbtn tox-split-button__chevron"><svg
                                                                                                    width="10" height="10">
                                                                                                    <path
                                                                                                        d="M8.7 2.2c.3-.3.8-.3 1 0 .4.4.4.9 0 1.2L5.7 7.8c-.3.3-.9.3-1.2 0L.2 3.4a.8.8 0 0 1 0-1.2c.3-.3.8-.3 1.1 0L5 6l3.7-3.8z"
                                                                                                        fill-rule="nonzero">
                                                                                                    </path>
                                                                                                </svg></span><span
                                                                                                aria-hidden="true"
                                                                                                id="aria_61806371151761703746383443"
                                                                                                style="display: none;">To
                                                                                                open the popup, press
                                                                                                Shift+Enter</span></div>
                                                                                    </div>
                                                                                    <div title="" role="toolbar"
                                                                                        data-alloy-tabstop="true"
                                                                                        tabindex="-1"
                                                                                        class="tox-toolbar__group"><button
                                                                                            aria-label="预览" title="预览"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M3.5 12.5c.5.8 1.1 1.6 1.8 2.3 2 2 4.2 3.2 6.7 3.2s4.7-1.2 6.7-3.2a16.2 16.2 0 0 0 2.1-2.8 15.7 15.7 0 0 0-2.1-2.8c-2-2-4.2-3.2-6.7-3.2a9.3 9.3 0 0 0-6.7 3.2A16.2 16.2 0 0 0 3.2 12c0 .2.2.3.3.5zm-2.4-1l.7-1.2L4 7.8C6.2 5.4 8.9 4 12 4c3 0 5.8 1.4 8.1 3.8a18.2 18.2 0 0 1 2.8 3.7v1l-.7 1.2-2.1 2.5c-2.3 2.4-5 3.8-8.1 3.8-3 0-5.8-1.4-8.1-3.8a18.2 18.2 0 0 1-2.8-3.7 1 1 0 0 1 0-1zm12-3.3a2 2 0 1 0 2.7 2.6 4 4 0 1 1-2.6-2.6z"
                                                                                                        fill-rule="nonzero">
                                                                                                    </path>
                                                                                                </svg></span></button><button
                                                                                            aria-label="全屏" title="全屏"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn"
                                                                                            aria-pressed="false"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M15.3 10l-1.2-1.3 2.9-3h-2.3a.9.9 0 1 1 0-1.7H19c.5 0 .9.4.9.9v4.4a.9.9 0 1 1-1.8 0V7l-2.9 3zm0 4l3 3v-2.3a.9.9 0 1 1 1.7 0V19c0 .5-.4.9-.9.9h-4.4a.9.9 0 1 1 0-1.8H17l-3-2.9 1.3-1.2zM10 15.4l-2.9 3h2.3a.9.9 0 1 1 0 1.7H5a.9.9 0 0 1-.9-.9v-4.4a.9.9 0 1 1 1.8 0V17l2.9-3 1.2 1.3zM8.7 10L5.7 7v2.3a.9.9 0 0 1-1.7 0V5c0-.5.4-.9.9-.9h4.4a.9.9 0 0 1 0 1.8H7l3 2.9-1.3 1.2z"
                                                                                                        fill-rule="nonzero">
                                                                                                    </path>
                                                                                                </svg></span></button><button
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn"
                                                                                            aria-pressed="false"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        fill-rule="nonzero"
                                                                                                        d="M5.7 7.3a1 1 0 0 0-1.4 1.4l7.7 7.7 7.7-7.7a1 1 0 1 0-1.4-1.4L12 13.6 5.7 7.3z">
                                                                                                    </path>
                                                                                                </svg></span></button></div>
                                                                                </div>
                                                                                <div role="group" class="tox-toolbar">
                                                                                    <div title="" role="toolbar"
                                                                                        data-alloy-tabstop="true"
                                                                                        tabindex="-1"
                                                                                        class="tox-toolbar__group"><button
                                                                                            title="格式" aria-label="格式"
                                                                                            aria-haspopup="true"
                                                                                            type="button" unselectable="on"
                                                                                            tabindex="-1"
                                                                                            class="tox-tbtn tox-tbtn--select tox-tbtn--bespoke"
                                                                                            aria-expanded="false"
                                                                                            style="user-select: none;"><span
                                                                                                class="tox-tbtn__select-label">段落</span>
                                                                                            <div
                                                                                                class="tox-tbtn__select-chevron">
                                                                                                <svg width="10" height="10">
                                                                                                    <path
                                                                                                        d="M8.7 2.2c.3-.3.8-.3 1 0 .4.4.4.9 0 1.2L5.7 7.8c-.3.3-.9.3-1.2 0L.2 3.4a.8.8 0 0 1 0-1.2c.3-.3.8-.3 1.1 0L5 6l3.7-3.8z"
                                                                                                        fill-rule="nonzero">
                                                                                                    </path>
                                                                                                </svg>
                                                                                            </div>
                                                                                        </button><button title="区块"
                                                                                            aria-label="区块"
                                                                                            aria-haspopup="true"
                                                                                            type="button" unselectable="on"
                                                                                            tabindex="-1"
                                                                                            class="tox-tbtn tox-tbtn--select tox-tbtn--bespoke"
                                                                                            aria-expanded="false"
                                                                                            style="user-select: none;"><span
                                                                                                class="tox-tbtn__select-label">段落</span>
                                                                                            <div
                                                                                                class="tox-tbtn__select-chevron">
                                                                                                <svg width="10" height="10">
                                                                                                    <path
                                                                                                        d="M8.7 2.2c.3-.3.8-.3 1 0 .4.4.4.9 0 1.2L5.7 7.8c-.3.3-.9.3-1.2 0L.2 3.4a.8.8 0 0 1 0-1.2c.3-.3.8-.3 1.1 0L5 6l3.7-3.8z"
                                                                                                        fill-rule="nonzero">
                                                                                                    </path>
                                                                                                </svg>
                                                                                            </div>
                                                                                        </button></div>
                                                                                    <div title="" role="toolbar"
                                                                                        data-alloy-tabstop="true"
                                                                                        tabindex="-1"
                                                                                        class="tox-toolbar__group"><button
                                                                                            aria-label="减少缩进" title="减少缩进"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn tox-tbtn--disabled"
                                                                                            aria-disabled="true"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M7 5h12c.6 0 1 .4 1 1s-.4 1-1 1H7a1 1 0 1 1 0-2zm5 4h7c.6 0 1 .4 1 1s-.4 1-1 1h-7a1 1 0 0 1 0-2zm0 4h7c.6 0 1 .4 1 1s-.4 1-1 1h-7a1 1 0 0 1 0-2zm-5 4h12a1 1 0 0 1 0 2H7a1 1 0 0 1 0-2zm1.6-3.8a1 1 0 0 1-1.2 1.6l-3-2a1 1 0 0 1 0-1.6l3-2a1 1 0 0 1 1.2 1.6L6.8 12l1.8 1.2z"
                                                                                                        fill-rule="evenodd">
                                                                                                    </path>
                                                                                                </svg></span></button><button
                                                                                            aria-label="增加缩进" title="增加缩进"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M7 5h12c.6 0 1 .4 1 1s-.4 1-1 1H7a1 1 0 1 1 0-2zm5 4h7c.6 0 1 .4 1 1s-.4 1-1 1h-7a1 1 0 0 1 0-2zm0 4h7c.6 0 1 .4 1 1s-.4 1-1 1h-7a1 1 0 0 1 0-2zm-5 4h12a1 1 0 0 1 0 2H7a1 1 0 0 1 0-2zm-2.6-3.8L6.2 12l-1.8-1.2a1 1 0 0 1 1.2-1.6l3 2a1 1 0 0 1 0 1.6l-3 2a1 1 0 1 1-1.2-1.6z"
                                                                                                        fill-rule="evenodd">
                                                                                                    </path>
                                                                                                </svg></span></button></div>
                                                                                    <div title="" role="toolbar"
                                                                                        data-alloy-tabstop="true"
                                                                                        tabindex="-1"
                                                                                        class="tox-toolbar__group"><button
                                                                                            aria-label="下标" title="下标"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn"
                                                                                            aria-pressed="false"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M10.4 10l4.6 4.6-1.4 1.4L9 11.4 4.4 16 3 14.6 7.6 10 3 5.4 4.4 4 9 8.6 13.6 4 15 5.4 10.4 10zM21 19h-5v-1l1-.8 1.7-1.6c.3-.4.5-.8.5-1.2 0-.3 0-.6-.2-.7-.2-.2-.5-.3-.9-.3a2 2 0 0 0-.8.2l-.7.3-.4-1.1 1-.6 1.2-.2c.8 0 1.4.3 1.8.7.4.4.6.9.6 1.5s-.2 1.1-.5 1.6a8 8 0 0 1-1.3 1.3l-.6.6h2.6V19z"
                                                                                                        fill-rule="nonzero">
                                                                                                    </path>
                                                                                                </svg></span></button><button
                                                                                            aria-label="上标" title="上标"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn"
                                                                                            aria-pressed="false"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M15 9.4L10.4 14l4.6 4.6-1.4 1.4L9 15.4 4.4 20 3 18.6 7.6 14 3 9.4 4.4 8 9 12.6 13.6 8 15 9.4zm5.9 1.6h-5v-1l1-.8 1.7-1.6c.3-.5.5-.9.5-1.3 0-.3 0-.5-.2-.7-.2-.2-.5-.3-.9-.3l-.8.2-.7.4-.4-1.2c.2-.2.5-.4 1-.5.3-.2.8-.2 1.2-.2.8 0 1.4.2 1.8.6.4.4.6 1 .6 1.6 0 .5-.2 1-.5 1.5l-1.3 1.4-.6.5h2.6V11z"
                                                                                                        fill-rule="nonzero">
                                                                                                    </path>
                                                                                                </svg></span></button><button
                                                                                            aria-label="删除线" title="删除线"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn"
                                                                                            aria-pressed="false"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <g fill-rule="evenodd">
                                                                                                        <path
                                                                                                            d="M15.6 8.5c-.5-.7-1-1.1-1.3-1.3-.6-.4-1.3-.6-2-.6-2.7 0-2.8 1.7-2.8 2.1 0 1.6 1.8 2 3.2 2.3 4.4.9 4.6 2.8 4.6 3.9 0 1.4-.7 4.1-5 4.1A6.2 6.2 0 0 1 7 16.4l1.5-1.1c.4.6 1.6 2 3.7 2 1.6 0 2.5-.4 3-1.2.4-.8.3-2-.8-2.6-.7-.4-1.6-.7-2.9-1-1-.2-3.9-.8-3.9-3.6C7.6 6 10.3 5 12.4 5c2.9 0 4.2 1.6 4.7 2.4l-1.5 1.1z">
                                                                                                        </path>
                                                                                                        <path
                                                                                                            d="M5 11h14a1 1 0 0 1 0 2H5a1 1 0 0 1 0-2z"
                                                                                                            fill-rule="nonzero">
                                                                                                        </path>
                                                                                                    </g>
                                                                                                </svg></span></button><button
                                                                                            aria-label="引用" title="引用"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn"
                                                                                            aria-pressed="false"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M7.5 17h.9c.4 0 .7-.2.9-.6L11 13V8c0-.6-.4-1-1-1H6a1 1 0 0 0-1 1v4c0 .6.4 1 1 1h2l-1.3 2.7a1 1 0 0 0 .8 1.3zm8 0h.9c.4 0 .7-.2.9-.6L19 13V8c0-.6-.4-1-1-1h-4a1 1 0 0 0-1 1v4c0 .6.4 1 1 1h2l-1.3 2.7a1 1 0 0 0 .8 1.3z"
                                                                                                        fill-rule="nonzero">
                                                                                                    </path>
                                                                                                </svg></span></button><button
                                                                                            aria-label="特殊符号" title="特殊符号"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M15 18h4l1-2v4h-6v-3.3l1.4-1a6 6 0 0 0 1.8-2.9 6.3 6.3 0 0 0-.1-4.1 5.8 5.8 0 0 0-3-3.2c-.6-.3-1.3-.5-2.1-.5a5.1 5.1 0 0 0-3.9 1.8 6.3 6.3 0 0 0-1.3 6 6.2 6.2 0 0 0 1.8 3l1.4.9V20H4v-4l1 2h4v-.5l-2-1L5.4 15A6.5 6.5 0 0 1 4 11c0-1 .2-1.9.6-2.7A7 7 0 0 1 6.3 6C7.1 5.4 8 5 9 4.5c1-.3 2-.5 3.1-.5a8.8 8.8 0 0 1 5.7 2 7 7 0 0 1 1.7 2.3 6 6 0 0 1 .2 4.8c-.2.7-.6 1.3-1 1.9a7.6 7.6 0 0 1-3.6 2.5v.5z"
                                                                                                        fill-rule="evenodd">
                                                                                                    </path>
                                                                                                </svg></span></button></div>
                                                                                    <div title="" role="toolbar"
                                                                                        data-alloy-tabstop="true"
                                                                                        tabindex="-1"
                                                                                        class="tox-toolbar__group"><button
                                                                                            aria-label="从左到右" title="从左到右"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn tox-tbtn--enabled"
                                                                                            aria-pressed="true"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M11 5h7a1 1 0 0 1 0 2h-1v11a1 1 0 0 1-2 0V7h-2v11a1 1 0 0 1-2 0v-6c-.5 0-1 0-1.4-.3A3.4 3.4 0 0 1 7.8 10a3.3 3.3 0 0 1 0-2.8 3.4 3.4 0 0 1 1.8-1.8L11 5zM4.4 16.2L6.2 15l-1.8-1.2a1 1 0 0 1 1.2-1.6l3 2a1 1 0 0 1 0 1.6l-3 2a1 1 0 1 1-1.2-1.6z"
                                                                                                        fill-rule="evenodd">
                                                                                                    </path>
                                                                                                </svg></span></button><button
                                                                                            aria-label="从右到左" title="从右到左"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn"
                                                                                            aria-pressed="false"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M8 5h8v2h-2v12h-2V7h-2v12H8v-7c-.5 0-1 0-1.4-.3A3.4 3.4 0 0 1 4.8 10a3.3 3.3 0 0 1 0-2.8 3.4 3.4 0 0 1 1.8-1.8L8 5zm12 11.2a1 1 0 1 1-1 1.6l-3-2a1 1 0 0 1 0-1.6l3-2a1 1 0 1 1 1 1.6L18.4 15l1.8 1.2z"
                                                                                                        fill-rule="evenodd">
                                                                                                    </path>
                                                                                                </svg></span></button></div>
                                                                                    <div title="" role="toolbar"
                                                                                        data-alloy-tabstop="true"
                                                                                        tabindex="-1"
                                                                                        class="tox-toolbar__group"><button
                                                                                            aria-label="显示不可见字符"
                                                                                            title="显示不可见字符" type="button"
                                                                                            tabindex="-1" class="tox-tbtn"
                                                                                            aria-pressed="false"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M10 5h7a1 1 0 0 1 0 2h-1v11a1 1 0 0 1-2 0V7h-2v11a1 1 0 0 1-2 0v-6c-.5 0-1 0-1.4-.3A3.4 3.4 0 0 1 6.8 10a3.3 3.3 0 0 1 0-2.8 3.4 3.4 0 0 1 1.8-1.8L10 5z"
                                                                                                        fill-rule="evenodd">
                                                                                                    </path>
                                                                                                </svg></span></button><button
                                                                                            aria-label="显示区块边框"
                                                                                            title="显示区块边框" type="button"
                                                                                            tabindex="-1" class="tox-tbtn"
                                                                                            aria-pressed="false"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M9 19v2H7v-2h2zm-4 0v2a2 2 0 0 1-2-2h2zm8 0v2h-2v-2h2zm8 0a2 2 0 0 1-2 2v-2h2zm-4 0v2h-2v-2h2zM15 7a1 1 0 0 1 0 2v7a1 1 0 0 1-2 0V9h-1v7a1 1 0 0 1-2 0v-4a2.5 2.5 0 0 1-.2-5H15zM5 15v2H3v-2h2zm16 0v2h-2v-2h2zM5 11v2H3v-2h2zm16 0v2h-2v-2h2zM5 7v2H3V7h2zm16 0v2h-2V7h2zM5 3v2H3c0-1.1.9-2 2-2zm8 0v2h-2V3h2zm6 0a2 2 0 0 1 2 2h-2V3zM9 3v2H7V3h2zm8 0v2h-2V3h2z"
                                                                                                        fill-rule="evenodd">
                                                                                                    </path>
                                                                                                </svg></span></button></div>
                                                                                    <div title="" role="toolbar"
                                                                                        data-alloy-tabstop="true"
                                                                                        tabindex="-1"
                                                                                        class="tox-toolbar__group"><button
                                                                                            aria-label="水平分割线" title="水平分割线"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path d="M4 11h16v2H4z"
                                                                                                        fill-rule="evenodd">
                                                                                                    </path>
                                                                                                </svg></span></button><button
                                                                                            aria-label="不间断空格" title="不间断空格"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M11 11H8a1 1 0 1 1 0-2h3V6c0-.6.4-1 1-1s1 .4 1 1v3h3c.6 0 1 .4 1 1s-.4 1-1 1h-3v3c0 .6-.4 1-1 1a1 1 0 0 1-1-1v-3zm10 4v5H3v-5c0-.6.4-1 1-1s1 .4 1 1v3h14v-3c0-.6.4-1 1-1s1 .4 1 1z"
                                                                                                        fill-rule="evenodd">
                                                                                                    </path>
                                                                                                </svg></span></button><button
                                                                                            aria-label="分页符" title="分页符"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <g fill-rule="evenodd">
                                                                                                        <path
                                                                                                            d="M5 11c.6 0 1 .4 1 1s-.4 1-1 1a1 1 0 0 1 0-2zm3 0h1c.6 0 1 .4 1 1s-.4 1-1 1H8a1 1 0 0 1 0-2zm4 0c.6 0 1 .4 1 1s-.4 1-1 1a1 1 0 0 1 0-2zm3 0h1c.6 0 1 .4 1 1s-.4 1-1 1h-1a1 1 0 0 1 0-2zm4 0c.6 0 1 .4 1 1s-.4 1-1 1a1 1 0 0 1 0-2zM7 3v5h10V3c0-.6.4-1 1-1s1 .4 1 1v7H5V3c0-.6.4-1 1-1s1 .4 1 1zM6 22a1 1 0 0 1-1-1v-7h14v7c0 .6-.4 1-1 1a1 1 0 0 1-1-1v-5H7v5c0 .6-.4 1-1 1z">
                                                                                                        </path>
                                                                                                    </g>
                                                                                                </svg></span></button></div>
                                                                                    <div title="" role="toolbar"
                                                                                        data-alloy-tabstop="true"
                                                                                        tabindex="-1"
                                                                                        class="tox-toolbar__group"><button
                                                                                            aria-label="剪切" title="剪切"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M18 15c.6.7 1 1.4 1 2.3 0 .8-.2 1.5-.7 2l-.8.5-1 .2c-.4 0-.8 0-1.2-.3a3.9 3.9 0 0 1-2.1-2.2c-.2-.5-.3-1-.2-1.5l-1-1-1 1c0 .5 0 1-.2 1.5-.1.5-.4 1-.9 1.4-.3.4-.7.6-1.2.8l-1.2.3c-.4 0-.7 0-1-.2-.3 0-.6-.3-.8-.5-.5-.5-.8-1.2-.7-2 0-.9.4-1.6 1-2.2A3.7 3.7 0 0 1 8.6 14H9l1-1-4-4-.5-1a3.3 3.3 0 0 1 0-2c0-.4.3-.7.5-1l6 6 6-6 .5 1a3.3 3.3 0 0 1 0 2c0 .4-.3.7-.5 1l-4 4 1 1h.5c.4 0 .8 0 1.2.3.5.2.9.4 1.2.8zm-8.5 2.2l.1-.4v-.3-.4a1 1 0 0 0-.2-.5 1 1 0 0 0-.4-.2 1.6 1.6 0 0 0-.8 0 2.6 2.6 0 0 0-.8.3 2.5 2.5 0 0 0-.9 1.1l-.1.4v.7l.2.5.5.2h.7a2.5 2.5 0 0 0 .8-.3 2.8 2.8 0 0 0 1-1zm2.5-2.8c.4 0 .7-.1 1-.4.3-.3.4-.6.4-1s-.1-.7-.4-1c-.3-.3-.6-.4-1-.4s-.7.1-1 .4c-.3.3-.4.6-.4 1s.1.7.4 1c.3.3.6.4 1 .4zm5.4 4l.2-.5v-.4-.3a2.6 2.6 0 0 0-.3-.8 2.4 2.4 0 0 0-.7-.7 2.5 2.5 0 0 0-.8-.3 1.5 1.5 0 0 0-.8 0 1 1 0 0 0-.4.2 1 1 0 0 0-.2.5 1.5 1.5 0 0 0 0 .7v.4l.3.4.3.4a2.8 2.8 0 0 0 .8.5l.4.1h.7l.5-.2z"
                                                                                                        fill-rule="evenodd">
                                                                                                    </path>
                                                                                                </svg></span></button><button
                                                                                            aria-label="复制" title="复制"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M16 3H6a2 2 0 0 0-2 2v11h2V5h10V3zm1 4a2 2 0 0 1 2 2v10a2 2 0 0 1-2 2h-7a2 2 0 0 1-2-2V9c0-1.2.9-2 2-2h7zm0 12V9h-7v10h7z"
                                                                                                        fill-rule="nonzero">
                                                                                                    </path>
                                                                                                </svg></span></button><button
                                                                                            aria-label="粘贴" title="粘贴"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M18 9V5h-2v1c0 .6-.4 1-1 1H9a1 1 0 0 1-1-1V5H6v13h3V9h9zM9 20H6a2 2 0 0 1-2-2V5c0-1.1.9-2 2-2h3.2A3 3 0 0 1 12 1a3 3 0 0 1 2.8 2H18a2 2 0 0 1 2 2v4h1v12H9v-1zm1.5-9.5v9h9v-9h-9zM12 3a1 1 0 0 0-1 1c0 .5.4 1 1 1s1-.5 1-1-.4-1-1-1z"
                                                                                                        fill-rule="nonzero">
                                                                                                    </path>
                                                                                                </svg></span></button><button
                                                                                            aria-label="粘贴为文本" title="粘贴为文本"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn"
                                                                                            aria-pressed="false"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M18 9V5h-2v1c0 .6-.4 1-1 1H9a1 1 0 0 1-1-1V5H6v13h3V9h9zM9 20H6a2 2 0 0 1-2-2V5c0-1.1.9-2 2-2h3.2A3 3 0 0 1 12 1a3 3 0 0 1 2.8 2H18a2 2 0 0 1 2 2v4h1v12H9v-1zm1.5-9.5v9h9v-9h-9zM12 3a1 1 0 0 0-1 1c0 .5.4 1 1 1s1-.5 1-1-.4-1-1-1zm0 9h6v2h-.5l-.5-1h-1v4h.8v1h-3.6v-1h.8v-4h-1l-.5 1H12v-2z"
                                                                                                        fill-rule="nonzero">
                                                                                                    </path>
                                                                                                </svg></span></button></div>
                                                                                    <div title="" role="toolbar"
                                                                                        data-alloy-tabstop="true"
                                                                                        tabindex="-1"
                                                                                        class="tox-toolbar__group"><button
                                                                                            aria-label="源代码" title="源代码"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <g fill-rule="nonzero">
                                                                                                        <path
                                                                                                            d="M9.8 15.7c.3.3.3.8 0 1-.3.4-.9.4-1.2 0l-4.4-4.1a.8.8 0 0 1 0-1.2l4.4-4.2c.3-.3.9-.3 1.2 0 .3.3.3.8 0 1.1L6 12l3.8 3.7zM14.2 15.7c-.3.3-.3.8 0 1 .4.4.9.4 1.2 0l4.4-4.1c.3-.3.3-.9 0-1.2l-4.4-4.2a.8.8 0 0 0-1.2 0c-.3.3-.3.8 0 1.1L18 12l-3.8 3.7z">
                                                                                                        </path>
                                                                                                    </g>
                                                                                                </svg></span></button><button
                                                                                            aria-label="打印" title="打印"
                                                                                            type="button" tabindex="-1"
                                                                                            class="tox-tbtn"><span
                                                                                                class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                    width="19" height="19"
                                                                                                    viewBox="0 0 24 24">
                                                                                                    <path
                                                                                                        d="M18 8H6a3 3 0 0 0-3 3v6h2v3h14v-3h2v-6a3 3 0 0 0-3-3zm-1 10H7v-4h10v4zm.5-5c-.8 0-1.5-.7-1.5-1.5s.7-1.5 1.5-1.5 1.5.7 1.5 1.5-.7 1.5-1.5 1.5zm.5-8H6v2h12V5z"
                                                                                                        fill-rule="nonzero">
                                                                                                    </path>
                                                                                                </svg></span></button>
                                                                                        <div aria-pressed="false"
                                                                                            aria-label="插入日期/时间"
                                                                                            title="插入日期/时间" role="button"
                                                                                            aria-haspopup="true"
                                                                                            unselectable="on" tabindex="-1"
                                                                                            class="tox-split-button"
                                                                                            aria-expanded="false"
                                                                                            aria-describedby="aria_76737512552011703746383464"
                                                                                            style="user-select: none;"><span
                                                                                                role="presentation"
                                                                                                tabindex="-1"
                                                                                                class="tox-tbtn"><span
                                                                                                    class="tox-icon tox-tbtn__icon-wrap"><svg
                                                                                                        width="19"
                                                                                                        height="19"
                                                                                                        viewBox="0 0 24 24">
                                                                                                        <g
                                                                                                            fill-rule="nonzero">
                                                                                                            <path
                                                                                                                d="M12 19a7 7 0 1 0 0-14 7 7 0 0 0 0 14zm0 2a9 9 0 1 1 0-18 9 9 0 0 1 0 18z">
                                                                                                            </path>
                                                                                                            <path
                                                                                                                d="M16 12h-3V7c0-.6-.4-1-1-1a1 1 0 0 0-1 1v7h5c.6 0 1-.4 1-1s-.4-1-1-1z">
                                                                                                            </path>
                                                                                                        </g>
                                                                                                    </svg></span></span><span
                                                                                                role="presentation"
                                                                                                tabindex="-1"
                                                                                                class="tox-tbtn tox-split-button__chevron"><svg
                                                                                                    width="10" height="10">
                                                                                                    <path
                                                                                                        d="M8.7 2.2c.3-.3.8-.3 1 0 .4.4.4.9 0 1.2L5.7 7.8c-.3.3-.9.3-1.2 0L.2 3.4a.8.8 0 0 1 0-1.2c.3-.3.8-.3 1.1 0L5 6l3.7-3.8z"
                                                                                                        fill-rule="nonzero">
                                                                                                    </path>
                                                                                                </svg></span><span
                                                                                                aria-hidden="true"
                                                                                                id="aria_76737512552011703746383464"
                                                                                                style="display: none;">To
                                                                                                open the popup, press
                                                                                                Shift+Enter</span></div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                            <div class="tox-anchorbar"></div>
                                                                        </div>
                                                                        <div class="tox-sidebar-wrap">
                                                                            <div class="tox-edit-area"><iframe
                                                                                    id="mail_editor_ifr" frameborder="0"
                                                                                    allowtransparency="true"
                                                                                    title="Rich Text Area. Press ALT-0 for help."
                                                                                    class="tox-edit-area__iframe"></iframe>
                                                                            </div>
                                                                            <div role="complementary" class="tox-sidebar">
                                                                                <div data-alloy-tabstop="true" tabindex="-1"
                                                                                    class="tox-sidebar__slider tox-sidebar--sliding-closed"
                                                                                    style="width: 0px;">
                                                                                    <div
                                                                                        class="tox-sidebar__pane-container">
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div aria-hidden="true" class="tox-throbber"
                                                                        style="display: none;"></div>
                                                                </div>
                                                            </div>
                                                        </span></label></div>
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
                                            <div class="v-form-item-control"><span class="v-form-item-children">
                                                    <div class="mm-radio-group"><label class="mm-radio"><input
                                                                tips="（保存成功后，需等候10分钟才能查看结果）" name="mm-radio-group-290"
                                                                type="radio" value="1"><span class="mm-radio-faux"><span
                                                                    class="mm-radio-input"></span><span
                                                                    class="mm-radio-label">是 <span
                                                                        class="tips">（保存成功后，需等候10分钟才能查看结果）</span></span></span></label><label
                                                            class="mm-radio"><input name="mm-radio-group-290" type="radio"
                                                                value="0"><span class="mm-radio-faux"><span
                                                                    class="mm-radio-input"></span><span
                                                                    class="mm-radio-label">否 <!----></span></span></label>
                                                    </div>
                                                </span><!----></div>
                                        </div>
                                        <div class="item-value history-folder-id-selector field-block"
                                            style="display: none;">
                                            <div class="mm-radio-group"><label class="mm-radio"><input
                                                        name="mm-radio-group-291" type="radio" value="1"><span
                                                        class="mm-radio-faux"><span class="mm-radio-input"></span><span
                                                            class="mm-radio-label">针对收件箱的历史邮件
                                                            <!----></span></span></label><label class="mm-radio"><input
                                                        name="mm-radio-group-291" type="radio" value="-1"><span
                                                        class="mm-radio-faux"><span class="mm-radio-input"></span><span
                                                            class="mm-radio-label">针对收件箱及所有文件夹的历史邮件（不包括已删除）
                                                            <!----></span></span></label></div>
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
                                            <div class="v-form-item-control"><span class="v-form-item-children"><span
                                                        class="mm-select">
                                                        <div class="mm-selector--single mm-selector" tabindex="0"><!---->
                                                            <div class="mm-selector-rendered"><span title="全部邮箱"
                                                                    class="mm-selector-selected-value"
                                                                    style="opacity: 1;">全部邮箱</span><!----></div><!----><span
                                                                class="mm-selector-suffix-icon"><svg
                                                                    class="mm-icon mm-icon-chevrondown mm-selector-arrow"
                                                                    viewBox="0 0 24 24" name="chevrondown" xmlns="XMLNS"
                                                                    fill="currentColor"
                                                                    style="height: 12px; width: 12px; outline: none;">
                                                                    <path
                                                                        d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z">
                                                                    </path>
                                                                </svg></span>
                                                        </div><!---->
                                                    </span></span><!----></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="field-item special-radio" name="execute_next" component="ui-radio-group">
                                    <div class="content">
                                        <div class="v-form-item v-form-item--required">
                                            <div class="v-form-item-label">
                                                <p class="label">其他收件规则</p>
                                            </div>
                                            <div class="v-form-item-control"><span class="v-form-item-children">
                                                    <div class="mm-radio-group"><label class="mm-radio"><input
                                                                name="mm-radio-group-292" type="radio" value="1"><span
                                                                class="mm-radio-faux"><span
                                                                    class="mm-radio-input"></span><span
                                                                    class="mm-radio-label">继续执行
                                                                    <!----></span></span></label><label
                                                            class="mm-radio"><input tips="一旦邮件满足了本条规则，将不再执行后续收件规则。"
                                                                name="mm-radio-group-292" type="radio" value="0"><span
                                                                class="mm-radio-faux"><span
                                                                    class="mm-radio-input"></span><span
                                                                    class="mm-radio-label">不再执行 <span
                                                                        class="tips">一旦邮件满足了本条规则，将不再执行后续收件规则。</span></span></span></label>
                                                    </div>
                                                </span><!----></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="btn-bottom">
                                <div class="mm-space mm-space__horizontal">
                                    <div class="mm-space-item" style="margin-right: 16px;"><button type="button"
                                            class="mm-button" @click="onHideForm"><!----><!----><span>取消</span><!----></button></div>
                                    <div class="mm-space-item"><button type="button"
                                            class="mm-button mm-button__primary"><!----><!----><span>保存</span><!----></button>
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
export default {
    data() {
        return {
            showForm: false
        }
    },
    methods: {
        onShowForm(type, data) {
            this.showForm = true
        },
        onHideForm(){
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
</style>