<template>
    <div class="detail-with-tollbar">
        <div class="promotion-wrapper"></div>
        <div class="mail-toolbar-wrapper open-detail">
            <div class="mail-toolbar-left">

                <span class="mm-tooltip mail-toolbar-btn-item" @click="toggleEmailHeader">
                    <span class="mm-tooltip-trigger">
                        <span>
                            <span class="okki-icon-wrap tool-bar-icon-item">​<svg xmlns="http://www.w3.org/2000/svg"
                                    width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
                                    fill="currentColor">
                                    <path fill-rule="evenodd" clip-rule="evenodd"
                                        d="M9.707 5.293a1 1 0 010 1.414L4.414 12l5.293 5.293a1 1 0 01-1.414 1.414l-6-6a1 1 0 010-1.414l6-6a1 1 0 011.414 0z">
                                    </path>
                                    <path fill-rule="evenodd" clip-rule="evenodd"
                                        d="M3.5 12a1 1 0 011-1H21a1 1 0 110 2H4.5a1 1 0 01-1-1z"></path>
                                </svg>
                            </span>
                        </span>
                    </span>

                </span>
                <span class="mm-tooltip mail-toolbar-btn-item">
                    <span class="mm-tooltip-trigger">
                        <div class="mm-popover">
                            <div>
                                <span class="">
                                    <span class="okki-icon-wrap tool-bar-icon-item" @click.stop="handlePendingTime">​<svg
                                            xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24"
                                            aria-hidden="true" class="okki-svg-icon" fill="currentColor">
                                            <path
                                                d="M12 6a1 1 0 011 1v4.423l2.964 1.711a1 1 0 11-1 1.732l-3.447-1.99A1 1 0 0111 11.98V7a1 1 0 011-1z">
                                            </path>
                                            <path fill-rule="evenodd" clip-rule="evenodd"
                                                d="M22 12c0 5.523-4.477 10-10 10S2 17.523 2 12 6.477 2 12 2s10 4.477 10 10zm-2 0a8 8 0 11-16 0 8 8 0 0116 0z">
                                            </path>
                                        </svg>
                                    </span>
                                </span>
                            </div>
                        </div>
                    </span>

                </span>

                <span class="mm-tooltip mail-toolbar-btn-item" @click="deleteEmails">
                    <span class="mm-tooltip-trigger">
                        <span>
                            <span class="okki-icon-wrap tool-bar-icon-item">​<svg xmlns="http://www.w3.org/2000/svg"
                                    width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
                                    fill="currentColor">
                                    <path fill-rule="evenodd" clip-rule="evenodd"
                                        d="M10.2 2a2 2 0 00-1.765 1.059L7.4 5H3a1 1 0 000 2h1.5v12a3 3 0 003 3h9a3 3 0 003-3V7H21a1 1 0 100-2h-4.4l-1.035-1.941A2 2 0 0013.8 2h-3.6zm7.3 5h-11v12a1 1 0 001 1h9a1 1 0 001-1V7zm-3.167-2L13.8 4h-3.6l-.533 1h4.666zM10 9.5a1 1 0 011 1v6a1 1 0 11-2 0v-6a1 1 0 011-1zm5 1a1 1 0 10-2 0v6a1 1 0 102 0v-6z">
                                    </path>
                                </svg>
                            </span>
                        </span>
                    </span>

                </span>
                <span class="mm-tooltip mail-toolbar-btn-item" @click="handleLabel">
                    <span class="mm-tooltip-trigger">
                        <span>
                            <span class="okki-icon-wrap tool-bar-icon-item">​<svg xmlns="http://www.w3.org/2000/svg"
                                    width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
                                    fill="currentColor">
                                    <path d="M8 10a2 2 0 100-4 2 2 0 000 4z"></path>
                                    <path fill-rule="evenodd" clip-rule="evenodd"
                                        d="M4.033 4.033l7.115.093 8.561 8.561a1 1 0 010 1.414l-5.608 5.608a1 1 0 01-1.414 0l-8.56-8.561-.094-7.115zm8.242-1.609a1 1 0 00-.694-.292l-7.522-.099A2 2 0 002.033 4.06l.099 7.522a1 1 0 00.292.694l8.849 8.848a3 3 0 004.243 0l5.607-5.607a3 3 0 000-4.243l-8.848-8.849zM8 10a2 2 0 100-4 2 2 0 000 4z">
                                    </path>
                                </svg>
                            </span>
                        </span>
                    </span>

                </span>
                <span class="mm-tooltip mail-toolbar-btn-item">
                    <span class="mm-tooltip-trigger">
                        <span>
                            <span class="okki-icon-wrap tool-bar-icon-item">​<svg xmlns="http://www.w3.org/2000/svg"
                                    width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
                                    fill="currentColor">
                                    <path fill-rule="evenodd" clip-rule="evenodd"
                                        d="M2 6a3 3 0 013-3h4.379a3 3 0 012.108.866l1.824 1.8H19a3 3 0 013 3V18a3 3 0 01-3 3H5a3 3 0 01-3-3V6zm2 0a1 1 0 011-1h4.379a1 1 0 01.703.289l1.823 1.8a2 2 0 001.406.578H19a1 1 0 011 1V10H4V6zm16 6v6a1 1 0 01-1 1H5a1 1 0 01-1-1v-6h16z">
                                    </path>
                                </svg>
                            </span>
                        </span>
                    </span>

                </span>
                <span class="mm-tooltip mail-toolbar-btn-item" @click="toggleDropdown">
                    <span class="mm-tooltip-trigger">
                        <span>
                            <span class="okki-icon-wrap tool-bar-icon-item">​<svg xmlns="http://www.w3.org/2000/svg"
                                    width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
                                    fill="currentColor">
                                    <path
                                        d="M10 12a2 2 0 104 0 2 2 0 00-4 0zm0 7a2 2 0 104 0 2 2 0 00-4 0zm0-14a2 2 0 104 0 2 2 0 00-4 0z">
                                    </path>
                                </svg>
                            </span>
                        </span>
                    </span>

                </span>
            </div>
            <div class="mail-toolbar-right">

                <div class="mail-paging tool-bar-paging">
                    <!-- <span class="total-count ellipsis" title="共 759 封">共 759 封</span> -->

                    <div class="mail-paging-btn left-btn disabled">
                        <span class="okki-icon-wrap m-icon">​<svg xmlns="http://www.w3.org/2000/svg" width="22" height="22"
                                viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
                                <path fill-rule="evenodd" clip-rule="evenodd"
                                    d="M15.707 5.293a1 1 0 010 1.414L10.414 12l5.293 5.293a1 1 0 01-1.414 1.414l-6-6a1 1 0 010-1.414l6-6a1 1 0 011.414 0z">
                                </path>
                            </svg>
                        </span>
                    </div>
                    <div class="mail-paging-btn right-btn">
                        <span class="okki-icon-wrap m-icon">​<svg xmlns="http://www.w3.org/2000/svg" width="22" height="22"
                                viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
                                <path fill-rule="evenodd" clip-rule="evenodd"
                                    d="M8.293 5.293a1 1 0 011.414 0l6 6a1 1 0 010 1.414l-6 6a1 1 0 01-1.414-1.414L13.586 12 8.293 6.707a1 1 0 010-1.414z">
                                </path>
                            </svg>
                        </span>
                    </div>
                </div>

                <span class="mm-tooltip mail-toolbar-btn-item">
                    <span class="mm-tooltip-trigger">
                        <a href="#" @click.prevent="onSwitchSetup" class="mail-toolbar-btn-item noright" tag="div">
                            <span class="okki-icon-wrap setting-icon">​<svg xmlns="http://www.w3.org/2000/svg" width="18"
                                    height="18" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
                                    fill="currentColor">
                                    <path fill-rule="evenodd" clip-rule="evenodd"
                                        d="M10.783 3.287c-.439-.45-1.319-1.022-2.319-.644a9.99 9.99 0 00-2.799 1.619c-.826.677-.771 1.723-.602 2.33.12.426.074.895-.164 1.308a1.692 1.692 0 01-1.052.797c-.61.156-1.546.632-1.717 1.689a10.063 10.063 0 000 3.228c.171 1.056 1.107 1.533 1.717 1.689.43.11.813.384 1.052.797.238.413.284.882.164 1.309-.17.606-.224 1.652.602 2.329a9.99 9.99 0 002.8 1.619c1 .378 1.879-.194 2.318-.645.31-.317.74-.512 1.217-.512.477 0 .907.195 1.217.512.439.45 1.319 1.023 2.319.645a9.99 9.99 0 002.799-1.62c.826-.676.771-1.722.602-2.328a1.692 1.692 0 01.164-1.31 1.692 1.692 0 011.052-.796c.61-.156 1.546-.633 1.717-1.69a10.064 10.064 0 000-3.227c-.171-1.057-1.107-1.533-1.717-1.689a1.692 1.692 0 01-1.052-.797 1.692 1.692 0 01-.164-1.309c.17-.606.224-1.652-.602-2.329a9.99 9.99 0 00-2.8-1.619c-1-.378-1.879.194-2.318.644-.31.318-.74.513-1.217.513-.477 0-.907-.195-1.217-.513zm-3.82 2.497a7.99 7.99 0 012.171-1.256h.002c.048.019.13.067.216.156A3.692 3.692 0 0012 5.8a3.692 3.692 0 002.648-1.116.651.651 0 01.216-.155h.002c.791.303 1.523.73 2.17 1.255l.001.002a.654.654 0 01-.026.266c-.259.923-.16 1.95.358 2.848a3.691 3.691 0 002.289 1.735c.12.03.203.077.243.11h.001a8.063 8.063 0 010 2.509v.001a.652.652 0 01-.244.11c-.93.237-1.77.837-2.289 1.735a3.692 3.692 0 00-.358 2.848c.034.12.035.215.026.265v.003a7.989 7.989 0 01-2.171 1.255h-.002a.652.652 0 01-.216-.155A3.692 3.692 0 0012 18.2a3.692 3.692 0 00-2.648 1.116.653.653 0 01-.216.155h-.002a7.988 7.988 0 01-2.17-1.255l-.001-.003a.652.652 0 01.026-.265c.259-.924.16-1.95-.358-2.848a3.692 3.692 0 00-2.289-1.735.652.652 0 01-.243-.11l-.001-.001a8.067 8.067 0 010-2.508v-.001a.653.653 0 01.244-.11A3.692 3.692 0 006.631 8.9a3.692 3.692 0 00.358-2.848.652.652 0 01-.026-.266v-.002zm2.887 6.178a2.15 2.15 0 114.3 0 2.15 2.15 0 01-4.3 0zM12 7.812a4.15 4.15 0 100 8.3 4.15 4.15 0 000-8.3z">
                                    </path>
                                </svg>
                            </span>
                        </a>
                    </span>

                </span>


            </div>
            <div class="mail-drop-menu-wrapper" style="width: 220px; top: 40px; left: 205px;" :style="dropdownStyle">
                <ul class="mail-drop-menu" style="height: 160px;">
                    <li v-for="(item, index) in menuItems" :key="index" :class="[
                        'mail-drop-menu-item',
                        `DROPMENU_55587_ITEM_${index}`,
                        { 'mail-drop-menu-item-active': hoveredItem === index }
                    ]" @click="moreItemClick(index)" @mouseover="hoveredItem = index" @mouseleave="hoveredItem = null">
                        <span class="mail-drop-menu-text ellipsis">

                            <span class="">{{ item }}</span>
                        </span>
                        <span>

                        </span>
                    </li>
                </ul>
            </div>
        </div>
        <div class="mail-detail-component">
            <div class="mm-spinner" style="display: none;">
                <div class="mm-spinner-mask"></div>
                <div class="mm-spinner-wrapper">
                    <div class="mm-loading" style="height: 40px; width: 40px; color: rgb(38, 132, 255);">
                        <svg class="circular" viewBox="25 25 50 50">
                            <circle stroke-width="2" class="path" cx="50" cy="50" r="20" fill="none" stroke-miterlimit="10">
                            </circle>
                        </svg>
                    </div>

                </div>
            </div>

            <div class="mail-detail-fixed-header mail-detail-header">
                <h1 class="mail-detail-fixed-header--title ellipsis">{{ currentEmailDetail.title }}</h1>
                <div class="mail-detail-fixed-header--opts__left">
                    <span v-if="currentEmailDetail.fixedFlag" class="okki-icon-wrap mail-pin-icon" data-tips="取消固定"
                        color="#0064ff" @click="toggleFixed(currentEmailDetail, $event)">​<svg data-tips="取消固定"
                            xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true"
                            class="okki-svg-icon" fill="#0064ff">
                            <path fill-rule="evenodd" clip-rule="evenodd"
                                d="M8.382 2a.963.963 0 00-.97.956c0 1.33.582 2.526 1.506 3.353l-.606 3.865C6.325 11.39 5 13.564 5 16.044c0 .528.434.956.97.956H11v3.914c0 .6.448 1.086 1 1.086s1-.486 1-1.086V17h5.03c.536 0 .97-.428.97-.956 0-2.507-1.354-4.7-3.376-5.91l-.597-3.777a4.49 4.49 0 001.56-3.4.963.963 0 00-.969-.957H8.382z">
                            </path>
                        </svg>
                    </span>

                    <span v-else class="okki-icon-wrap mail-pin-icon" data-tips="固定"
                        @click="toggleFixed(currentEmailDetail, $event)">​<svg data-tips="固定"
                            xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 25 25" aria-hidden="true"
                            class="okki-svg-icon" fill="currentColor">
                            <path fill-rule="evenodd" clip-rule="evenodd"
                                d="M16.606 3.292a1 1 0 00-1.414 0 4.508 4.508 0 00-1.314 3.428l-3.094 2.253c-2.258-.538-4.729.057-6.484 1.812a1 1 0 000 1.415l3.545 3.544-2.828 2.829a1 1 0 101.414 1.414l2.828-2.828 3.536 3.535a1 1 0 001.414 0c1.774-1.774 2.363-4.279 1.794-6.557l2.198-3.025a4.51 4.51 0 003.502-1.31 1 1 0 000-1.414l-5.097-5.096zm-6.634 11.75L13.43 18.5a4.93 4.93 0 00.529-4.24 1 1 0 01.14-.902l2.837-3.904a1 1 0 011.02-.39 2.57 2.57 0 001.479-.115L16.046 5.56c-.177.453-.22.95-.125 1.431a1 1 0 01-.392 1.001l-3.957 2.882a1 1 0 01-.892.144 4.929 4.929 0 00-4.185.548l3.466 3.466.006.005a.226.226 0 01.005.006z">
                            </path>
                        </svg>
                    </span>

                </div>

                <div class="mail-detail-fixed-header--opts__right">

                    <div class="pending-tool" v-if="currentEmailDetail.pendingFlag">
                        <span>待处理邮件</span>
                        <span>
                            <span class="okki-icon-wrap">​<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                    viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
                                    <path fill-rule="evenodd" clip-rule="evenodd"
                                        d="M21.205 6.29a1 1 0 01.004 1.415l-10.928 11a1 1 0 01-1.419 0l-6.071-6.111a1 1 0 011.418-1.41l5.362 5.397 10.22-10.286a1 1 0 011.414-.004z">
                                    </path>
                                </svg>
                            </span>
                            <span>
                                <div class="mm-popover" @click="handlePendingTime">
                                    <div>
                                        <span class="okki-icon-wrap pending-chooser-icon">​<svg
                                                xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
                                                fill="currentColor">
                                                <path fill-rule="evenodd" clip-rule="evenodd"
                                                    d="M22 12c0 5.523-4.477 10-10 10S2 17.523 2 12 6.477 2 12 2s10 4.477 10 10z">
                                                </path>
                                                <path
                                                    d="M12 7a1 1 0 011 1v4.423l2.964 1.711a1 1 0 01-1 1.732l-3.447-1.99a1.01 1.01 0 01-.384-.377.992.992 0 01-.133-.518V8a1 1 0 011-1z"
                                                    fill="#fff"></path>
                                            </svg>
                                        </span>
                                    </div>

                                </div>
                            </span>
                            <span class="time">{{ currentEmailDetail.pendingTime }}</span>
                        </span>
                    </div>




                    <div class="mail-detail-operations">
                        <div class="selected">
                            <span class="ellipsis value time">{{ currentEmailDetail.sendDate }}</span>
                            <div class="all-border detail-operations-btn">
                                <i class="icon-reply-line m-icon"
                                    @click="toggleWriteEmail(currentEmailDetail, 'reply')"></i>
                                <i class="icon-reply-all-line m-icon"
                                    @click="toggleWriteEmail(currentEmailDetail, 'reply_all')"></i>


                            </div>
                            <div class="mm-popover" @click="toggleEmailDropdown">
                                <div>
                                    <span class="icon-caret-down m-icon"></span>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>

                <div class="mm-outside mail-detail-tool-popover mm-popover-popper" x-placement="bottom-end"
                    style="position: absolute; will-change: top, left; transform-origin: 100% top; top: 100px; left: 600px;"
                    :style="dropdownEmailStyle">

                    <div>

                        <div class="mail-drop-menu-wrapper" style="width: 220px; top: 0px; left: 0px;">
                            <ul class="mail-drop-menu">

                                <li :class="[
                                    'mail-drop-menu-item',
                                    `DROPMENU_90471_ITEM_0`,
                                    { 'mail-drop-menu-item-active': emailHoveredItem === '回复' }
                                ]" @mouseover="emailHoveredItem = '回复'" @mouseleave="emailHoveredItem = null"
                                    @click="toggleWriteEmail(currentEmailDetail, 'reply')">

                                    <span class="mail-drop-menu-text ellipsis">

                                        <span title="回复" class="">回复</span>
                                    </span>
                                    <span>

                                    </span>
                                </li>
                                <li :class="[
                                    'mail-drop-menu-item',
                                    `DROPMENU_90471_ITEM_1`,
                                    { 'mail-drop-menu-item-active': emailHoveredItem === '回复全部' }
                                ]" @mouseover="emailHoveredItem = '回复全部'" @mouseleave="emailHoveredItem = null"
                                    @click="toggleWriteEmail(currentEmailDetail, 'reply_all')">

                                    <span class="mail-drop-menu-text ellipsis">

                                        <span title="回复全部" class="">回复全部</span>
                                    </span>
                                    <span>

                                    </span>
                                </li>
                                <li :class="[
                                    'mail-drop-menu-item',
                                    `DROPMENU_90471_ITEM_2`,
                                    { 'mail-drop-menu-item-active': emailHoveredItem === '带附件回复' }
                                ]" @mouseover="emailHoveredItem = '带附件回复'" @mouseleave="emailHoveredItem = null"
                                    @click="toggleWriteEmail(currentEmailDetail, 'reply_with_attachments')">

                                    <span class="mail-drop-menu-text ellipsis">

                                        <span title="带附件回复" class="">带附件回复</span>
                                    </span>
                                    <span>

                                    </span>
                                </li>
                                <li :class="[
                                    'mail-drop-menu-item',
                                    `DROPMENU_90471_ITEM_3`,
                                    { 'mail-drop-menu-item-active': emailHoveredItem === '带附件回复全部' }
                                ]" @mouseover="emailHoveredItem = '带附件回复全部'" @mouseleave="emailHoveredItem = null"
                                    @click="toggleWriteEmail(currentEmailDetail, 'reply_all_with_attachments')">

                                    <span class="mail-drop-menu-text ellipsis">

                                        <span title="带附件回复全部" class="">带附件回复全部</span>
                                    </span>
                                    <span>

                                    </span>
                                </li>
                                <li :class="[
                                    'mail-drop-menu-item',
                                    `DROPMENU_90471_ITEM_4`,
                                    { 'mail-drop-menu-item-active': emailHoveredItem === '转发' }
                                ]" @mouseover="emailHoveredItem = '转发'" @mouseleave="emailHoveredItem = null"
                                    @click="toggleWriteEmail(currentEmailDetail, 'forward')">

                                    <span class="mail-drop-menu-text ellipsis">

                                        <span title="转发" class="">转发</span>
                                    </span>
                                    <span>

                                    </span>
                                </li>
                                <li :class="[
                                    'mail-drop-menu-item',
                                    `DROPMENU_90471_ITEM_5`,
                                    { 'mail-drop-menu-item-active': emailHoveredItem === '作为附件转发' }
                                ]" @mouseover="emailHoveredItem = '作为附件转发'" @mouseleave="emailHoveredItem = null"
                                    @click="toggleWriteEmail(currentEmailDetail, 'forward_as_attachment')">

                                    <span class="mail-drop-menu-text ellipsis">

                                        <span title="作为附件转发" class="">作为附件转发</span>
                                    </span>
                                    <span>

                                    </span>
                                </li>
                                <li :class="[
                                    'mail-drop-menu-item',
                                    `DROPMENU_90471_ITEM_7`,
                                    { 'mail-drop-menu-item-active': emailHoveredItem === '待处理' }
                                ]" @mouseover="emailHoveredItem = '待处理'" @mouseleave="emailHoveredItem = null">
                                    <span class="mail-drop-menu-divider"></span>
                                    <span class="mail-drop-menu-text ellipsis">

                                        <span title="待处理" class="">待处理</span>
                                    </span>
                                    <span>
                                        <i class="m-icon icon-right-thin"></i>
                                    </span>
                                </li>
                                <li :class="[
                                    'mail-drop-menu-item',
                                    `DROPMENU_90471_ITEM_8`,
                                    { 'mail-drop-menu-item-active': emailHoveredItem === '标为未读' }
                                ]" @mouseover="emailHoveredItem = '标为未读'" @mouseleave="emailHoveredItem = null"
                                    @click="handleUnReadEmail">

                                    <span class="mail-drop-menu-text ellipsis">

                                        <span title="标为未读" class="">标为未读</span>
                                    </span>
                                    <span>

                                    </span>
                                </li>
                                <li :class="[
                                    'mail-drop-menu-item',
                                    `DROPMENU_90471_ITEM_11`,
                                    { 'mail-drop-menu-item-active': emailHoveredItem === '移动到' }
                                ]" @mouseover="emailHoveredItem = '移动到'" @mouseleave="emailHoveredItem = null">

                                    <span class="mail-drop-menu-text ellipsis">

                                        <span title="移动到" class="">移动到</span>
                                    </span>
                                    <span>
                                        <i class="m-icon icon-right-thin"></i>
                                    </span>
                                </li>
                                <li :class="[
                                    'mail-drop-menu-item',
                                    `DROPMENU_90471_ITEM_12`,
                                    { 'mail-drop-menu-item-active': emailHoveredItem === '导出邮件' }
                                ]" @mouseover="emailHoveredItem = '导出邮件'" @mouseleave="emailHoveredItem = null"
                                    @click="handleExportEmail">
                                    <span class="mail-drop-menu-divider"></span>
                                    <span class="mail-drop-menu-text ellipsis">

                                        <span title="导出邮件" class="">导出邮件</span>
                                    </span>
                                    <span>

                                    </span>
                                </li>
                                <li :class="[
                                    'mail-drop-menu-item',
                                    `DROPMENU_90471_ITEM_15`,
                                    { 'mail-drop-menu-item-active': emailHoveredItem === '标为垃圾邮件' }
                                ]" @mouseover="emailHoveredItem = '标为垃圾邮件'" @mouseleave="emailHoveredItem = null"
                                    @click="handleSpamEmail">
                                    <span class="mail-drop-menu-divider"></span>
                                    <span class="mail-drop-menu-text ellipsis">

                                        <span title="标为垃圾邮件" class="">标为垃圾邮件</span>
                                    </span>
                                    <span>

                                    </span>
                                </li>
                                <li :class="[
                                    'mail-drop-menu-item',
                                    `DROPMENU_90471_ITEM_16`,
                                    { 'mail-drop-menu-item-active': emailHoveredItem === '删除' }
                                ]" @mouseover="emailHoveredItem = '删除'" @mouseleave="emailHoveredItem = null"
                                    @click="deleteEmails">

                                    <span class="mail-drop-menu-text ellipsis">

                                        <span title="删除" class="">删除</span>
                                    </span>
                                    <span>

                                    </span>
                                </li>
                            </ul>
                        </div>
                    </div>

                </div>

            </div>

            <email_content_detail_info :currentEmailDetail="currentEmailDetail"></email_content_detail_info>

            <div class="go-top-or-bottom">
                <i class="m-icon icon-top go-top" style=""></i>
                <i class="m-icon icon-top go-bottom" style="display: none;"></i>
            </div>
            <div class="quick-reply-container" contact-list="[object Object]">

                <div v-if="!isReplying" class="mail-detail-quick-reply" @click="toggleQuickReply">
                    <div class="expand-trigger">快速回复</div>
                </div>

                <div v-if="isReplying" class="mail-detail-quick-reply">
                    <span class="mm-textarea-wrap">
                        <textarea placeholder="快速回复" class="mm-textarea" v-model="replyContent"
                            style="resize: none; height: 120px; min-height: 120px; max-height: 358px;"></textarea>
                    </span>
                    <button type="button" class="mm-button quick-reply-btn" @click="toggleQuickReply">
                        <span>取消</span>
                    </button>
                    <button id="report-stat-quick-reply-btn-confirm" :disabled="isSending" type="button"
                        class="mm-button mm-button__primary quick-reply-btn" @click="quickReply">
                        <span>发送</span>
                    </button>
                </div>
            </div>

        </div>
        <div class="mm-outside mail-pending-popover mm-popover-popper" x-placement="top-end"
            v-if="showPendingTime || showCustomTime" v-clickOutside="closeTimeModal"
            style="position: absolute; top: 40px; left: 50px; will-change: top, left; transform-origin: 100% bottom;">

            <div>

                <div class="mail-pending-handler">
                    <div class="title" v-if="showPendingTime">
                        <span>请选择稍后处理时间: </span>
                    </div>
                    <div class="title" v-if="showCustomTime">
                        <span class="bold back-block">
                            <i class="m-icon icon-left-thin" @click="handlePendingTime"></i> 自定义时间
                        </span>
                    </div>
                    <PendingTimePopover v-if="showPendingTime" @show-custom-time="handleCustomTime"
                        @time-selected="handleSelectedTime"></PendingTimePopover>
                    <CustomTimePopover v-if="showCustomTime" @time-selected="handleSelectedTime"></CustomTimePopover>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import emailContentDetailInfoLayout from '../email_content_detail_info';
import CustomTimePopover from "@/views/email/custom_time.vue";
import PendingTimePopover from "@/views/email/pending_time.vue";
import { fixedEmail, list, quickReply, readEmail, spamEmail, pendingEmail, moveEmailToFolder, moveEmailToLabel, deleteEmail, exportEmail } from "@/api/email/email";
export default {
    props: {
        info: {
            type: Object,
            default: () => {
            },
            required: false
        }
    },
    components: {
        CustomTimePopover,
        PendingTimePopover,
        'email_content_detail_info': emailContentDetailInfoLayout
    },
    data() {
        return {
            activeEmailId: null,
            currentEmailDetail: {},
            isReplying: false,
            isSending: false,
            replyContent: '',
            isDropdownShown: false,
            isDropdownEmailShown: false,
            hoveredItem: null,
            emailHoveredItem: null,
            currentEmailType: '',
            showPendingTime: false,
            showCustomTime: false,
            showLabel: false,
            pendingTime: null,
            menuItems: [
                '标为未读',
                '作为附件转发',
                '导出邮件',
                '新建日程',
                '标为垃圾邮件'
            ]

        }
    },
    computed: {
        dropdownStyle() {
            return this.isDropdownShown ? '' : 'display: none;';
        },
        dropdownEmailStyle() {
            return this.isDropdownEmailShown ? '' : 'display: none;';
        }
    },
    watch: {
        info: {
            handler(newVal) {
                this.currentEmailDetail = newVal
            },
            deep: true,
            immediate: true
        }
    },
    methods: {
        // 标记为未读邮件
        async unReadEmails(emailIds) {
            const data = {
                "ids": emailIds,
                "readFlag": false
            };
            try {
                const response = await readEmail(data);
                if (response.code === 200) {
                    this.$message.success("成功标记为未读");
                    this.isDropdownShown = false;
                    this.isDropdownEmailShown = false;
                    return;
                }
            } catch (error) {
                console.error('标记为未读出现错误:', error);
                throw error;
            }
        },
        // 标为未读邮件
        handleUnReadEmail() {
            const ids = [];
            ids.push(this.activeEmailId);
            if (ids.length) {
                this.unReadEmails(ids);
            }
        },
        // 标识为垃圾邮件
        async spamEmails(emailIds) {
            const data = {
                "ids": emailIds,
                "spamFlag": true
            };
            try {
                const response = await spamEmail(data);
                if (response.code === 200) {
                    this.$message.success("垃圾邮件标记成功");
                    this.isDropdownShown = false;
                    this.isDropdownEmailShown = false;
                    return;
                }
            } catch (error) {
                console.error('垃圾邮件标记出现错误:', error);
                throw error;
            }
        },
        // 导出邮件
        handleExportEmail() {
            exportEmail(this.activeEmailId).then((response) => {
                // 处理文件下载逻辑，比如使用 blob 来下载
                const url = window.URL.createObjectURL(new Blob([response]));
                const link = document.createElement('a');
                link.href = url;
                let filename = 'email.eml';
                if (this.currentEmailDetail.title) {
                    filename = this.currentEmailDetail.title + '.eml';
                }
                link.setAttribute('download', filename);
                document.body.appendChild(link);
                link.click();

                this.isDropdownShown = false;
                this.isDropdownEmailShown = false;
            });
        },
        toggleEmailHeader() {
            this.$emit('showLabel', true)
        },
        // 跳转到写信页面
        toggleWriteEmail(email, writeEmailType) {
            // 触发事件并传递参数
            console.log(email);
            // EventBus.$emit('switch-write-email', email, writeEmailType);
        },
        // 固定
        async toggleFixed(email, event) {
            event.stopPropagation();
            const data = {
                "id": email.id,
                "fixedFlag": !email.fixedFlag
            };
            try {
                const response = await fixedEmail(data);
                if (response.code !== 200) {
                    this.$message.error("执行失败");
                    return;
                }

                email.fixedFlag = !email.fixedFlag;
            } catch (error) {
                console.error('固定邮件出现错误:', error);
                throw error;
            }
        },
        handleCustomTime() {
            this.showPendingTime = false;
            this.showCustomTime = true;
        },
        handlePendingTime() {
            this.showPendingTime = true;
            this.showCustomTime = false;
        },
        handleSelectedTime(time) {
            this.pendingEmail(this.currentEmailDetail, true, time);
        },
        closeTimeModal() {
            this.showPendingTime = false;
            this.showCustomTime = false;
        },
        // 快速回复-富文本框
        toggleQuickReply() {
            this.isReplying = !this.isReplying;
        },

        // 快速回复
        async quickReply(emailId) {
            const data = {
                "id": this.activeEmailId,
                "content": this.replyContent
            };
            try {
                const response = await quickReply(data);
                if (response.code !== 200) {
                    this.$message.error("执行失败");
                    return;
                }

                this.$message.success("发送成功");
                this.isSending = true;
                this.replyContent = '';
            } catch (error) {
                console.error('快速回复出现错误:', error);
                throw error;
            }
        },
        // 标为垃圾邮件
        handleSpamEmail() {
            const ids = [];
            ids.push(this.activeEmailId);
            if (ids.length) {
                this.spamEmails(ids);
            }
        },
        // 标记待处理
        async pendingEmail(email, pendingFlag, pendingTime) {
            const data = {
                "id": email.id,
                "pendingFlag": pendingFlag,
                "pendingTime": pendingTime
            };
            try {
                const response = await pendingEmail(data);
                if (response.code === 200) {
                    this.showPendingTime = false;
                    this.showCustomTime = false;
                    this.$set(email, 'pendingFlag', pendingFlag);
                    this.$set(email, 'pendingTime', pendingTime);
                    return;
                }
            } catch (error) {
                console.error('标记为待处理出现错误:', error);
                throw error;
            }
        },
        // 删除邮件
        async deleteEmails() {
            const emailIds = [];
            emailIds.push(this.activeEmailId);
            if (emailIds.length) {
                const data = {
                    "ids": emailIds
                };
                try {
                    const response = await deleteEmail(data);
                    if (response.code === 200) {
                        let found = false;
                        for (let groupIndex = 0; groupIndex < this.localEmailList.length; groupIndex++) {
                            const monthGroup = this.localEmailList[groupIndex];
                            for (const month in monthGroup) {
                                const emails = monthGroup[month];
                                const index = emails.findIndex(email => email.id === this.activeEmailId);
                                if (index > -1) {
                                    emails.splice(index, 1);
                                    this.total -= 1;

                                    if (emails.length === 0) {
                                        // 如果该monthGroup没有邮件了，从localEmailList中移除
                                        this.localEmailList.splice(groupIndex, 1);

                                        // 尝试从下一个monthGroup获取最新邮件
                                        if (this.localEmailList[groupIndex]) {
                                            this.currentEmailDetail = this.localEmailList[groupIndex][Object.keys(this.localEmailList[groupIndex])[0]][0] || {};
                                        } else {
                                            this.currentEmailDetail = {};
                                        }
                                    } else if (emails[index]) {
                                        this.currentEmailDetail = emails[index];
                                    } else if (emails[index - 1]) {
                                        this.currentEmailDetail = emails[index - 1];
                                    } else {
                                        this.currentEmailDetail = {};
                                    }

                                    this.activeEmailId = this.currentEmailDetail.id || null;
                                    found = true;
                                    break;
                                }
                            }
                            if (found) break;
                        }

                        this.isDropdownEmailShown = false;
                    } else {
                        this.$message.error('删除失败');
                    }
                } catch (error) {
                    console.error('邮件删除出现错误:', error);
                    throw error;
                }
            }
        },
        handleLabel() {
            this.showLabel = !this.showLabel;
        },
        toggleDropdown() {
            this.isDropdownShown = !this.isDropdownShown;
        },

        toggleEmailDropdown() {
            this.isDropdownEmailShown = !this.isDropdownEmailShown;
        },

        moreItemClick(index) {
            const ids = [];
            ids.push(this.activeEmailId);

            if (ids.length) {
                switch (this.menuItems[index]) {
                    case '标为未读':
                        this.unReadEmails(ids);
                        break;
                    case '作为附件转发':
                        this.toggleWriteEmail(this.currentEmailDetail, 'forward_as_attachment');
                        break;
                    case '导出邮件':
                        this.handleExportEmail();
                        break;
                    case '新建日程':
                        break;
                    case '标为垃圾邮件':
                        this.spamEmails(ids);
                        break;
                    default:
                        console.warn("Unknown menu item");
                }
            }
        },
        // 切换到设置页
        onSwitchSetup() {
            this.$router.push('/email/index?type=setting_email')
        },

    }
}
</script>

<style lang="scss" scoped></style>