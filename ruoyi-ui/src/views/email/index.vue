<template>
  <div class="new-layout-right">
    <!---->
    <div class="riven-layout-container" data-v-app="">
      <div class="view-content-wrapper layout-extraSidebar" sidebar-collapse="">
        <div class="view-content-teleport-anchor"></div>
        <div :class="['mm-split', 'mail-layout-split', isLeftPaneVisible ? 'expanding' : 'collapsing']">
          <div class="mm-split-horizontal">
            <div class="mm-split-pane mm-split-pane__left"
              :style="{ right: isLeftPaneVisible ? '87.7165%' : '98.9177%' }">
              <div class="layout-extraSidebar-sidebar">
                <nav v-if="isLeftPaneVisible" class="main-plus-nav">
                  <div class="mail-nav" transition="menu">
                    <div class="mail-nav-switch">
                      <a :class="[isMailNavNormalContainerOpen ? 'active' : '']" @click="toggleType(true)">
                        <span class="okki-icon-wrap">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                            viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
                            <path fill-rule="evenodd" clip-rule="evenodd"
                              d="M2.6 5.2A2.995 2.995 0 015 4h14c.981 0 1.852.47 2.4 1.199L12 11.591 2.6 5.199zM2 7.21V17a3 3 0 003 3h14a3 3 0 003-3V7.21l-9.438 6.417a1 1 0 01-1.124 0L2 7.209z">
                            </path>
                          </svg>
                        </span>
                      </a>
                      <a :class="[!isMailNavNormalContainerOpen ? 'active' : '']" @click="toggleType(false)">
                        <span class="okki-icon-wrap">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                            viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
                            <path fill-rule="evenodd" clip-rule="evenodd"
                              d="M12 2a5 5 0 100 10 5 5 0 000-10zM8.296 13a6 6 0 00-6 6v2a1 1 0 001 1h17.408a1 1 0 001-1v-2a6 6 0 00-6-6H8.296z">
                            </path>
                          </svg>
                        </span>
                      </a>
                    </div>
                    <!-- 写信 -->
                    <div class="main-nav-head-pro">
                      <div class="mm-dropdown">
                        <div>
                          <div class="mm-button-group mm-dropdown-button-group">
                            <button type="button" class="mm-button mm-button__primary" @click="switchWriteEmailPage">
                              <!---->
                              <!---->写信
                              <!---->
                            </button>
                            <button type="button" class="mm-button mm-button__primary mm-button--icon">
                              <svg class="mm-icon mm-icon-switch" viewBox="0 0 24 24" name="switch" fill="currentColor"
                                style="height: 12px; width: 12px;">
                                <path
                                  d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.3.8-.3 1.1 0l1 1c.2.3.2.7 0 1z">
                                </path>
                              </svg>
                              <!---->
                              <!---->
                            </button>
                          </div>
                        </div>
                        <!---->
                      </div>
                    </div>
                    <!-- 邮件 -->
                    <div class="mail-nav-normal-container" v-if="isMailNavNormalContainerOpen">
                      <ul role="menubar" class="mm-menu mail-sidebar-menu">
                        <li :class="['mm-submenu', isInboxOpen ? 'mm-submenu--opened' : '', 'mail-sidebar-submenu']"
                          role="menuitem" aria-haspopup="true" aria-expanded="true"
                          nativeondragover="function(e){return(0,te.CV)(e,t)}"
                          nativeondragleave="function(e){return(0,te.aB)(e,t)}"
                          nativeondrop="function(e){return(0,te.LQ)(e,t)}">
                          <div class="mm-submenu-title" style="padding-left: 14px; padding-right: 14px;"
                            @click="toggleInbox">
                            <!---->
                            <span class="mm-menu-title">
                              <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                                <span class="flex items-center" title="收件箱">收件箱</span>
                              </div>
                              <!---->
                              <!---->
                            </span>
                            <svg class="mm-icon mm-icon-chevrondown mm-submenu-icon-arrow" viewBox="0 0 24 24"
                              name="chevrondown" fill="currentColor" style="height: 12px; width: 12px;">
                              <path
                                d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z">
                              </path>
                            </svg>
                          </div>
                          <ul role="menu" class="mm-menu mm-menu--inline" v-show="isInboxOpen">
                            <li class="mm-menu-item mail-sidebar-menu-item"
                              :class="{ 'mm-menu-item--active': activeMenuItem === 'ALL_RECEIVED' }"
                              @click.prevent="allReceivedClick" role="menuitem" tabindex="-1"
                              nativeonclick="function(e){e.stopPropagation(),n.gotoUpdate(M),M===w.e.MASS_BOX&&(0,h.M)(&quot;Email_catalogue_MassBox_view&quot;)}"
                              style="padding-left: 35px; padding-right: 14px;">
                              <!---->
                              <!---->
                              <div class="mail-sidebar-menu-item">
                                <!---->
                                <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                                  <span class="flex items-center" title="全部收件">全部收件</span>
                                </div>
                                <span class="mm-tooltip mail-menu-item-count">
                                  <span class="mm-tooltip-trigger">{{ menuCount.allReceivedCount }}</span>
                                  <!---->
                                </span>
                                <!---->
                              </div>
                            </li>
                            <li v-for="task in pullTaskList" :key="task.id" class="mm-menu-item mail-sidebar-menu-item"
                              :class="{ 'mm-menu-item--active': activeMenuItem === 'PULL_' + task.id }"
                              @click="taskPullClick(task.id)" role="menuitem" tabindex="-1"
                              style="padding-left: 35px; padding-right: 14px;">
                              <!---->
                              <!---->
                              <div class="mail-sidebar-menu-item">
                                <div v-if="task.connStatus === 1"
                                  class="right-click-menu-handler mail-menu-item-title ellipsis">
                                  <!-- 正常状态的内容 -->
                                  <div class="mailbox-item ellipsis">
                                    <div class="public-mail-tag-wrapper ellipsis" slots="[object Object]">
                                      <div class="public-mail-tag-content">
                                        <span class="ellipsis">{{ task.account }}</span>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                                <div v-else class="right-click-menu-handler mail-menu-item-title ellipsis">
                                  <!-- 异常状态的内容 -->
                                  <div class="mailbox-item ellipsis">
                                    <span class="mm-tooltip mailbox-item-error-icon">
                                      <span class="mm-tooltip-trigger">
                                        <svg class="mm-icon mm-icon-info default-error-icon" viewBox="0 0 24 24"
                                          name="info" fill="#DD3C3C" style="height: 15px; width: 15px;">
                                          <path
                                            d="M12 .9C5.9.9.9 5.9.9 12s5 11.1 11.1 11.1 11.1-5 11.1-11.1S18.1.9 12 .9zm0 5.6c.8 0 1.4.6 1.4 1.4s-.6 1.4-1.4 1.4-1.4-.6-1.4-1.4.6-1.4 1.4-1.4zm2.3 9.7c0 .2-.2.4-.5.4h-3.6c-.3 0-.5-.1-.5-.4v-.9c0-.3.2-.5.5-.5.2 0 .4-.2.4-.4v-1.9c0-.2-.2-.5-.4-.5-.3 0-.5-.1-.5-.4v-.9c0-.3.2-.5.5-.5h2.7c.3 0 .5.2.5.5v3.7c0 .2.2.4.4.4.3 0 .5.2.5.5v.9z">
                                          </path>
                                        </svg>
                                      </span>
                                    </span>
                                    <div class="public-mail-tag-wrapper ellipsis" slots="[object Object]">
                                      <div class="public-mail-tag-content">
                                        <span class="mm-tooltip mailbox-item-email-error-tip">
                                          <span class="mm-tooltip-trigger">
                                            <span class="ellipsis">{{ task.account }}</span>
                                          </span>
                                        </span>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                                <span class="mm-tooltip mail-menu-item-count"><span class="mm-tooltip-trigger">{{
                                  getTaskCount(task.id) }}</span></span>
                                <!---->
                              </div>
                            </li>
                          </ul>
                        </li>
                        <li class="mm-menu-item mail-sidebar-menu-item"
                          :class="{ 'mm-menu-item--active': activeMenuItem === 'PENDING_MAIL' }" @click="pendingMailClick"
                          role="menuitem" tabindex="-1"
                          nativeonclick="function(e){e.stopPropagation(),n.gotoUpdate(M),M===w.e.MASS_BOX&&(0,h.M)(&quot;Email_catalogue_MassBox_view&quot;)}"
                          style="padding-left: 14px; padding-right: 14px;">
                          <!---->
                          <!---->
                          <div class="mail-sidebar-menu-item">
                            <!---->
                            <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                              <span class="flex items-center">待处理邮件</span>
                            </div>
                            <span class="mm-tooltip mail-menu-item-count">
                              <span class="mm-tooltip-trigger">{{ menuCount.pendingMailCount }}</span>
                              <!---->
                            </span>
                            <!---->
                          </div>
                        </li>
                        <li class="mm-menu-item mail-sidebar-menu-item"
                          :class="{ 'mm-menu-item--active': activeMenuItem === 'AN_UNREAD_MAIL' }"
                          @click="anUnreadMailClick" role="menuitem" tabindex="-1"
                          nativeonclick="function(e){e.stopPropagation(),n.gotoUpdate(M),M===w.e.MASS_BOX&&(0,h.M)(&quot;Email_catalogue_MassBox_view&quot;)}"
                          style="padding-left: 14px; padding-right: 14px;">
                          <!---->
                          <!---->
                          <div class="mail-sidebar-menu-item">
                            <!---->
                            <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                              <span class="flex items-center" title="未读邮件">未读邮件</span>
                            </div>
                            <span class="mm-tooltip mail-menu-item-count">
                              <span class="mm-tooltip-trigger">{{ menuCount.anUnreadMailCount }}</span>
                              <!---->
                            </span>
                            <!---->
                          </div>
                        </li>
                        <li class="mm-menu-item mail-sidebar-menu-item"
                          :class="{ 'mm-menu-item--active': activeMenuItem === 'DRAFTS' }" @click="draftsClick"
                          role="menuitem" tabindex="-1"
                          nativeonclick="function(e){e.stopPropagation(),n.gotoUpdate(M),M===w.e.MASS_BOX&&(0,h.M)(&quot;Email_catalogue_MassBox_view&quot;)}"
                          style="padding-left: 14px; padding-right: 14px;">
                          <!---->
                          <!---->
                          <div class="mail-sidebar-menu-item">
                            <!---->
                            <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                              <span class="flex items-center" title="草稿箱">草稿箱</span>
                            </div>
                            <span class="mm-tooltip mail-menu-item-count">
                              <span class="mm-tooltip-trigger">{{ menuCount.draftsCount }}</span>
                              <!---->
                            </span>
                            <!---->
                          </div>
                        </li>
                        <li :class="['mm-submenu', isOutboxOpen ? 'mm-submenu--opened' : '', 'mail-sidebar-submenu']"
                          role="menuitem" aria-haspopup="true" aria-expanded="true"
                          nativeondragover="function(e){return(0,te.CV)(e,t)}"
                          nativeondragleave="function(e){return(0,te.aB)(e,t)}"
                          nativeondrop="function(e){return(0,te.LQ)(e,t)}">
                          <div class="mm-submenu-title" style="padding-left: 14px; padding-right: 14px;"
                            @click="toggleOutbox">
                            <!---->
                            <span class="mm-menu-title">
                              <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                                <span class="flex items-center" title="发件箱">发件箱</span>
                              </div>
                              <!---->
                              <!---->
                            </span>
                            <svg class="mm-icon mm-icon-chevrondown mm-submenu-icon-arrow" viewBox="0 0 24 24"
                              name="chevrondown" fill="currentColor" style="height: 12px; width: 12px;">
                              <path
                                d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z">
                              </path>
                            </svg>
                          </div>
                          <ul role="menu" class="mm-menu mm-menu--inline" v-show="isOutboxOpen">
                            <li class="mm-menu-item mail-sidebar-menu-item novice-tour-enter-outbox-click"
                              :class="{ 'mm-menu-item--active': activeMenuItem === 'COMPLETE_SHIPMENT' }"
                              @click="completeShipmentClick" role="menuitem" tabindex="-1"
                              nativeonclick="function(e){e.stopPropagation(),n.gotoUpdate(M),M===w.e.MASS_BOX&&(0,h.M)(&quot;Email_catalogue_MassBox_view&quot;)}"
                              style="padding-left: 35px; padding-right: 14px;">
                              <!---->
                              <!---->
                              <div class="mail-sidebar-menu-item">
                                <!---->
                                <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                                  <span class="flex items-center" title="全部发件">全部发件</span>
                                </div>
                                <!---->
                                <!---->
                              </div>
                            </li>
                            <li v-for="task in sendTaskList" :key="task.id"
                              class="mm-menu-item mail-sidebar-menu-item novice-tour-enter-outbox-click"
                              :class="{ 'mm-menu-item--active': activeMenuItem === 'SEND_' + task.id }"
                              @click="taskSendClick(task.id)" role="menuitem" tabindex="-1"
                              nativeonclick="function(e){e.stopPropagation(),n.gotoUpdate(M),M===w.e.MASS_BOX&&(0,h.M)(&quot;Email_catalogue_MassBox_view&quot;)}"
                              style="padding-left: 35px; padding-right: 14px;">
                              <!---->
                              <!---->
                              <div class="mail-sidebar-menu-item">
                                <!---->
                                <div v-if="task.connStatus === 1"
                                  class="right-click-menu-handler mail-menu-item-title ellipsis">
                                  <!-- 正常状态的内容 -->
                                  <div class="mailbox-item ellipsis">
                                    <div class="public-mail-tag-wrapper ellipsis" slots="[object Object]">
                                      <div class="public-mail-tag-content">
                                        <span class="ellipsis">{{ task.account }}</span>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                                <div v-else class="right-click-menu-handler mail-menu-item-title ellipsis">
                                  <!-- 异常状态的内容 -->
                                  <div class="mailbox-item ellipsis">
                                    <span class="mm-tooltip mailbox-item-error-icon">
                                      <span class="mm-tooltip-trigger">
                                        <svg class="mm-icon mm-icon-info default-error-icon" viewBox="0 0 24 24"
                                          name="info" fill="#DD3C3C" style="height: 15px; width: 15px;">
                                          <path
                                            d="M12 .9C5.9.9.9 5.9.9 12s5 11.1 11.1 11.1 11.1-5 11.1-11.1S18.1.9 12 .9zm0 5.6c.8 0 1.4.6 1.4 1.4s-.6 1.4-1.4 1.4-1.4-.6-1.4-1.4.6-1.4 1.4-1.4zm2.3 9.7c0 .2-.2.4-.5.4h-3.6c-.3 0-.5-.1-.5-.4v-.9c0-.3.2-.5.5-.5.2 0 .4-.2.4-.4v-1.9c0-.2-.2-.5-.4-.5-.3 0-.5-.1-.5-.4v-.9c0-.3.2-.5.5-.5h2.7c.3 0 .5.2.5.5v3.7c0 .2.2.4.4.4.3 0 .5.2.5.5v.9z">
                                          </path>
                                        </svg>
                                      </span>
                                    </span>
                                    <div class="public-mail-tag-wrapper ellipsis" slots="[object Object]">
                                      <div class="public-mail-tag-content">
                                        <span class="mm-tooltip mailbox-item-email-error-tip">
                                          <span class="mm-tooltip-trigger">
                                            <span class="ellipsis">{{ task.account }}</span>
                                          </span>
                                        </span>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                                <!---->
                                <!---->
                              </div>
                            </li>
                          </ul>
                        </li>

                        <li class="mm-submenu mm-submenu--opened top-divided for-folder-tour mail-sidebar-submenu"
                          role="menuitem" aria-haspopup="true" aria-expanded="true"
                          nativeondragover="function(e){return(0,te.CV)(e,t)}"
                          nativeondragleave="function(e){return(0,te.aB)(e,t)}"
                          nativeondrop="function(e){return(0,te.LQ)(e,t)}">
                          <div class="mm-submenu-title" style="padding-left: 14px; padding-right: 14px;">
                            <!---->
                            <span class="mm-menu-title">
                              <div class="right-click-menu-handler mail-menu-item-title ellipsis" @click="toggleFolder">
                                <span class="flex items-center" title="文件夹">文件夹</span>
                                <div>
                                  <div class="menu-item-operation-search">
                                    <div class="search-icon">
                                      <span class="okki-icon-wrap">​<svg xmlns="http://www.w3.org/2000/svg" width="16"
                                          height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
                                          fill="currentColor">
                                          <path fill-rule="evenodd" clip-rule="evenodd"
                                            d="M17 10.5a6.5 6.5 0 11-13 0 6.5 6.5 0 0113 0zm-1.473 6.855a8.5 8.5 0 111.463-1.365l3.303 3.303a1 1 0 01-1.414 1.414l-3.352-3.352z">
                                          </path>
                                        </svg>
                                      </span>
                                    </div>
                                  </div>
                                </div>
                              </div>
                              <!---->
                              <span>
                                <div class="mm-dropdown mail-nav-item-dropdown">
                                  <div class="mm-dropdown-trigger">
                                    <span class="okki-icon-wrap menu-item-operation-icon">​<svg
                                        xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24"
                                        aria-hidden="true" class="okki-svg-icon" fill="currentColor">
                                        <path
                                          d="M10 12a2 2 0 104 0 2 2 0 00-4 0zm0 7a2 2 0 104 0 2 2 0 00-4 0zm0-14a2 2 0 104 0 2 2 0 00-4 0z">
                                        </path>
                                      </svg>
                                    </span>
                                  </div>
                                  <!---->
                                </div>
                              </span>
                            </span>
                            <svg class="mm-icon mm-icon-chevrondown mm-submenu-icon-arrow" viewBox="0 0 24 24"
                              name="chevrondown" fill="currentColor" style="height: 12px; width: 12px;">
                              <path
                                d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z">
                              </path>
                            </svg>
                          </div>
                          <FolderTree :folders="folders" v-show="isFolderOpen" @folder-selected="folderClick">
                          </FolderTree>
                        </li>


                        <li :class="['mm-submenu', isLabelOpen ? 'mm-submenu--opened' : '', 'mail-sidebar-submenu']"
                          role="menuitem" aria-haspopup="true" aria-expanded="true">
                          <div class="mm-submenu-title" style="padding-left: 14px; padding-right: 14px;"
                            @click="toggleLabel">
                            <span class="mm-menu-title">
                              <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                                <span class="flex items-center" title="标签邮件">标签邮件</span>
                              </div>
                            </span>
                            <svg class="mm-icon mm-icon-chevrondown mm-submenu-icon-arrow" viewBox="0 0 24 24"
                              name="chevrondown" style="height: 12px; width: 12px;">
                              <path
                                d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z">
                              </path>
                            </svg>
                          </div>
                          <ul role="menu" class="mm-menu mm-menu--inline" v-show="isLabelOpen">
                            <!-- 系统标签 Section -->
                            <li
                              :class="['mm-submenu', isLabelSystemOpen ? 'mm-submenu--opened' : '', 'mail-sidebar-submenu']"
                              role="menuitem">
                              <div class="mm-submenu-title" style="padding-left: 35px; padding-right: 14px;"
                                @click="toggleLabelSystem">
                                <span class="mm-menu-title">
                                  <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                                    <span class="flex items-center" title="系统标签">系统标签</span>
                                  </div>
                                </span>
                                <svg class="mm-icon mm-icon-chevrondown mm-submenu-icon-arrow" viewBox="0 0 24 24"
                                  name="chevrondown" style="height: 12px; width: 12px;">
                                  <path
                                    d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z">
                                  </path>
                                </svg>
                              </div>
                              <ul role="menu" class="mm-menu mm-menu--inline" v-show="isLabelSystemOpen">
                                <li v-for="label in systemLabels" :key="label.id"
                                  class="mm-menu-item mail-sidebar-menu-item"
                                  :class="{ 'mm-menu-item--active': activeMenuItem === 'LABEL_' + label.id }"
                                  @click="labelClick(label.id)">
                                  <div class="mail-sidebar-menu-item">
                                    <span class="mail-menu-item-tag-color"
                                      :style="{ 'background-color': `rgb(${label.color})` }"></span>
                                    <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                                      <span class="flex items-center" :title="label.name">{{ label.name }}</span>
                                    </div>
                                  </div>
                                </li>
                              </ul>
                            </li>

                            <!-- 自定义标签 Section -->
                            <li
                              :class="['mm-submenu', isLabelCustomOpen ? 'mm-submenu--opened' : '', 'mail-sidebar-submenu']"
                              role="menuitem">
                              <div class="mm-submenu-title" style="padding-left: 35px; padding-right: 14px;"
                                @click="toggleLabelCustom">
                                <span class="mm-menu-title">
                                  <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                                    <span class="flex items-center" title="自定义标签">自定义标签</span>
                                  </div>
                                </span>
                                <svg class="mm-icon mm-icon-chevrondown mm-submenu-icon-arrow" viewBox="0 0 24 24"
                                  name="chevrondown" style="height: 12px; width: 12px;">
                                  <path
                                    d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z">
                                  </path>
                                </svg>
                              </div>
                              <ul role="menu" class="mm-menu mm-menu--inline" v-show="isLabelCustomOpen">
                                <li v-for="label in customLabels" :key="label.id"
                                  class="mm-menu-item mail-sidebar-menu-item"
                                  :class="{ 'mm-menu-item--active': activeMenuItem === 'LABEL_' + label.id }"
                                  @click="labelClick(label.id)">
                                  <div class="mail-sidebar-menu-item">
                                    <span class="mail-menu-item-tag-color"
                                      :style="{ 'background-color': `rgb(${label.color})` }"></span>
                                    <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                                      <span class="flex items-center" :title="label.name">{{ label.name }}</span>
                                    </div>
                                  </div>
                                </li>
                              </ul>
                            </li>
                          </ul>
                        </li>

                        <li :class="['mm-submenu', isShowMoreOpen ? 'mm-submenu--opened' : '', 'mail-sidebar-submenu']"
                          role="menuitem" aria-haspopup="true" aria-expanded="true"
                          nativeondragover="function(e){return(0,te.CV)(e,t)}"
                          nativeondragleave="function(e){return(0,te.aB)(e,t)}"
                          nativeondrop="function(e){return(0,te.LQ)(e,t)}">
                          <div class="mm-submenu-title" style="padding-left: 14px; padding-right: 14px;"
                            @click="toggleShowMore">
                            <!---->
                            <span class="mm-menu-title">
                              <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                                <span class="flex items-center" title="显示更多">显示更多</span>
                              </div>
                              <!---->
                              <!---->
                            </span>
                            <svg class="mm-icon mm-icon-chevrondown mm-submenu-icon-arrow" viewBox="0 0 24 24"
                              name="chevrondown" fill="currentColor" style="height: 12px; width: 12px;">
                              <path
                                d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z">
                              </path>
                            </svg>
                          </div>
                          <ul role="menu" class="mm-menu mm-menu--inline" v-show="isShowMoreOpen">
                            <li class="mm-menu-item mail-sidebar-menu-item"
                              :class="{ 'mm-menu-item--active': activeMenuItem === 'DELETED_MAIL' }"
                              @click="deletedMailClick" role="menuitem" tabindex="-1"
                              nativeonclick="function(e){e.stopPropagation(),n.gotoUpdate(M),M===w.e.MASS_BOX&&(0,h.M)(&quot;Email_catalogue_MassBox_view&quot;)}"
                              style="padding-left: 35px; padding-right: 14px;">
                              <!---->
                              <!---->
                              <div class="mail-sidebar-menu-item">
                                <!---->
                                <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                                  <span class="flex items-center" title="已删除邮件">已删除邮件</span>
                                </div>
                                <!---->
                                <!---->
                              </div>
                            </li>
                            <li class="mm-menu-item mail-sidebar-menu-item"
                              :class="{ 'mm-menu-item--active': activeMenuItem === 'SPAM' }" @click="spamMailClick"
                              role="menuitem" tabindex="-1"
                              nativeonclick="function(e){e.stopPropagation(),n.gotoUpdate(M),M===w.e.MASS_BOX&&(0,h.M)(&quot;Email_catalogue_MassBox_view&quot;)}"
                              style="padding-left: 35px; padding-right: 14px;">
                              <!---->
                              <!---->
                              <div class="mail-sidebar-menu-item">
                                <!---->
                                <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                                  <span class="flex items-center" title="垃圾邮件">垃圾邮件</span>
                                </div>
                                <!---->
                                <!---->
                              </div>
                            </li>
                            <li class="mm-menu-item mail-sidebar-menu-item"
                              :class="{ 'mm-menu-item--active': activeMenuItem === 'TRACK_INFORMATION' }"
                              @click="traceInformationClick" role="menuitem" tabindex="-1"
                              nativeonclick="function(e){e.stopPropagation(),n.gotoUpdate(M),M===w.e.MASS_BOX&&(0,h.M)(&quot;Email_catalogue_MassBox_view&quot;)}"
                              style="padding-left: 35px; padding-right: 14px;">
                              <!---->
                              <!---->
                              <div class="mail-sidebar-menu-item">
                                <!---->
                                <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                                  <span class="flex items-center" title="追踪信息">追踪信息</span>
                                </div>
                                <!---->
                                <!---->
                              </div>
                            </li>
                          </ul>
                        </li>
                      </ul>
                    </div>
                    <!-- 客户 -->
                    <div class="customer-mail-nav" isbindemail="true" v-else>
                      <div class="search-wrapper">
                        <span class="mm-input--prefix mm-input search-input">
                          <!---->
                          <span class="mm-input-affix-wrapper">
                            <span class="mm-input-prefix--icon mm-input-prefix">
                              <svg class="mm-icon mm-icon-search" viewBox="0 0 24 24" name="search" fill="#b0adab">
                                <path
                                  d="M22.9 20.9l-6.2-6.1c1.3-1.8 1.9-4 1.6-6.4-.6-3.9-3.8-7.1-7.8-7.4C5 .4.4 5 1 10.5c.3 4 3.5 7.3 7.4 7.8 2.4.3 4.6-.3 6.4-1.5l6.1 6.1c.3.3.7.3 1 0l.9-1c.3-.3.3-.7.1-1zM3.7 9.6c0-3.2 2.7-5.9 5.9-5.9 3.3 0 6 2.7 6 5.9 0 3.3-2.7 6-6 6-3.2 0-5.9-2.6-5.9-6z">
                                </path>
                              </svg>
                            </span>
                            <input placeholder="客户名称/编号/邮箱/联系人昵称" type="text" class="mm-input-inner"
                              v-model="searchCustomer" />
                            <!---->
                          </span>
                          <!---->
                        </span>
                      </div>
                      <div class="select-wrapper" v-show="searchCustomerList && !searchCustomerList.length">
                        <el-select v-model="selectedLabelTypeValue" placeholder="请选择"
                          @change="handleLabelTypeSelectChange">
                          <el-option v-for="item in labelTypeOptions" :key="item.value" :label="item.label"
                            :value="item.value">
                          </el-option>
                        </el-select>
                      </div>
                      <div class="nav-wrap">
                        <div v-show="searchCustomerList && !searchCustomerList.length"
                          class="mm-tree--highlight-current mm-tree customer-mail-nav-tree" role="tree">
                          <div v-for="generalLabelTypeData in generalLabelTypeDatas" :key="generalLabelTypeData.id"
                            :class="['is-focusable mm-tree-node', generalLabelTypeData.showGeneralChildren ? 'is-expanded' : '']"
                            @click="toggleGeneralChildren(generalLabelTypeData)" aria-disabled="" draggable="false"
                            role="treeitem" tabindex="0">
                            <div class="mm-tree-node-content" style="padding-left: 14px;">
                              <span class="mm-tree-node-expand-icon-wrapper">
                                <svg
                                  :class="['mm-icon mm-icon-chevrondown tree-expand-icon', generalLabelTypeData.showGeneralChildren ? 'expanded' : '']"
                                  viewBox="0 0 24 24" name="chevrondown" fill="currentColor"
                                  style="height: 12px; width: 12px;">
                                  <path
                                    d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z">
                                  </path>
                                </svg>
                              </span>
                              <!---->
                              <!---->
                              <span class="mm-tree-node-label-wrap">
                                <span class="tree-node-text">
                                  <span class="ellipsis name">{{ generalLabelTypeData.name }}</span>
                                  <span class="mm-tooltip count">
                                    <span class="mm-tooltip-trigger">
                                      <span>{{ generalLabelTypeData.customerCount }},{{
                                        generalLabelTypeData.unReadEmailCount }}</span>
                                    </span>
                                    <!---->
                                  </span>
                                </span>
                              </span>
                            </div>
                            <div v-if="generalLabelTypeData.showGeneralChildren" class="mm-tree-node-children"
                              role="group">
                              <div v-for="customer in generalLabelTypeData.customerList" :key="customer.id"
                                class="is-focusable mm-tree-node"
                                :class="{ 'is-current': selectedTaskId === customer.id }" aria-disabled=""
                                draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
                                  <span class="mm-tree-node-expand-icon-wrapper">
                                    <svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24"
                                      name="chevrondown" fill="currentColor" style="height: 12px; width: 12px;">
                                      <path
                                        d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z">
                                      </path>
                                    </svg>
                                  </span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
                                    <a @click.stop="onCustomerSearch(customer.id)" class="link ellipsis tree-node-text">
                                      <div class="mm-popover empty-item-tooltip" props="[object Object]">
                                        <div>
                                          <span class="ellipsis name">{{ customer.name }}</span>
                                        </div>
                                        <!---->
                                      </div>
                                      <!---->
                                    </a>
                                  </span>
                                </div>
                              </div>
                            </div>
                          </div>

                          <div v-for="labelTypeData in labelTypeDatas" :key="labelTypeData.id"
                            :class="['is-focusable mm-tree-node', labelTypeData.showChildren ? 'is-expanded' : '']"
                            @click="toggleLabelTypeChildren(labelTypeData)" aria-disabled="" draggable="false"
                            role="treeitem" tabindex="-1">
                            <div class="mm-tree-node-content" style="padding-left: 14px;">
                              <span class="mm-tree-node-expand-icon-wrapper">
                                <svg
                                  :class="['mm-icon mm-icon-chevrondown tree-expand-icon', labelTypeData.showChildren ? 'expanded' : '']"
                                  viewBox="0 0 24 24" name="chevrondown" fill="currentColor"
                                  style="height: 12px; width: 12px;">
                                  <path
                                    d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z">
                                  </path>
                                </svg>
                              </span>
                              <!---->
                              <!---->
                              <span class="mm-tree-node-label-wrap">
                                <div class="tree-node-text" v-if="selectedLabelTypeValue == 5">
                                  <span v-for="number in Array.from({ length: labelTypeData.name }).fill()" :key="number"
                                    class="okki-icon-wrap" color="#F7BA2A">​<svg xmlns="http://www.w3.org/2000/svg"
                                      width="14" height="14" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
                                      fill="#F7BA2A">
                                      <path
                                        d="M22 9.67a1 1 0 00-.86-.67l-5.69-.83L12.9 3a1 1 0 00-1.8 0L8.55 8.16 2.86 9a1 1 0 00-.81.68 1 1 0 00.25 1l4.13 4-1 5.68a1 1 0 001.45 1.07L12 18.76l5.1 2.68c.14.08.299.12.46.12a1 1 0 00.59-.19 1 1 0 00.4-1l-1-5.68 4.13-4A1 1 0 0022 9.67z">
                                      </path>
                                    </svg>
                                  </span>
                                  <span
                                    v-for="number in Array.from({ length: Math.max(0, 5 - labelTypeData.name) }).fill()"
                                    :key="number" class="okki-icon-wrap" color="#e8e8e8">​<svg
                                      xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24"
                                      aria-hidden="true" class="okki-svg-icon" fill="#e8e8e8">
                                      <path
                                        d="M22 9.67a1 1 0 00-.86-.67l-5.69-.83L12.9 3a1 1 0 00-1.8 0L8.55 8.16 2.86 9a1 1 0 00-.81.68 1 1 0 00.25 1l4.13 4-1 5.68a1 1 0 001.45 1.07L12 18.76l5.1 2.68c.14.08.299.12.46.12a1 1 0 00.59-.19 1 1 0 00.4-1l-1-5.68 4.13-4A1 1 0 0022 9.67z">
                                      </path>
                                    </svg>
                                  </span>
                                </div>
                                <div class="tree-node-text" v-else>
                                  <span class="ellipsis name">{{ labelTypeData.name }}</span>
                                  <span class="mm-tooltip count">
                                    <span class="mm-tooltip-trigger">
                                      <span>{{ labelTypeData.customerCount }},{{ labelTypeData.unReadEmailCount }}</span>
                                    </span>
                                  </span>
                                </div>
                              </span>
                            </div>
                            <div v-if="labelTypeData.showChildren" class="mm-tree-node-children" role="group">
                              <div v-for="customer in labelTypeData.customerList" :key="customer.id"
                                class="is-focusable mm-tree-node"
                                :class="{ 'is-current': selectedTaskId === customer.id }" aria-disabled=""
                                aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
                                  <span class="mm-tree-node-expand-icon-wrapper">
                                    <svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24"
                                      name="chevrondown" fill="currentColor" style="height: 12px; width: 12px;">
                                      <path
                                        d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z">
                                      </path>
                                    </svg>
                                  </span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
                                    <a @click.stop="onCustomerSearch(customer.id)" class="link ellipsis tree-node-text">
                                      <div class="mm-popover empty-item-tooltip" props="[object Object]">
                                        <div>
                                          <span class="ellipsis name">{{ customer.name }}</span>
                                        </div>
                                        <!---->
                                      </div>
                                      <!---->
                                    </a>
                                  </span>
                                </div>
                                <!---->
                              </div>
                            </div>
                          </div>
                          <!---->
                          <div class="mm-tree-drop-indicator" style="display: none;"></div>
                        </div>
                        <ul v-show="searchCustomerList && searchCustomerList.length" class="search-res-list">
                          <li class="search-res-item" v-for="(item, index) in searchCustomerList" :key="index">
                            <div class="mm-popover" props="[object Object]" style="width: 100%;">
                              <div>
                                <a aria-current="page"
                                  class="router-link-active router-link-exact-active customer-group-item link ellipsis"
                                  @click="onCustomerSearch(item.id)">
                                  {{ item.name }} （{{ item.customerCount }}）
                                </a>
                              </div>
                            </div>
                          </li>
                        </ul>
                      </div>
                      <div class="nav-max-customer-tip ellipsis" title="为了性能，每组最多展示200个客户，客户过多时建议直接搜索">
                        为了性能，每组最多展示200个客户，客户过多时建议直接搜索</div>
                    </div>
                  </div>
                </nav>
                <span :class="['position-toogle', isLeftPaneVisible ? 'expanding' : 'collapsing']"
                  @click="toggleLeftPane">
                  <i class="m-icon icon-left-small"></i>
                </span>
              </div>
            </div>
            <div class="mm-split-trigger-wrap" :style="{ left: isLeftPaneVisible ? '12.2835%' : '1.08225%' }">
              <div class="mm-split-trigger mm-split-trigger-vertical">
                <div class="mm-split-trigger-bar-wrap">
                  <i class="mm-split-trigger-bar"></i>
                  <i class="mm-split-trigger-bar"></i>
                  <i class="mm-split-trigger-bar"></i>
                  <i class="mm-split-trigger-bar"></i>
                  <i class="mm-split-trigger-bar"></i>
                  <i class="mm-split-trigger-bar"></i>
                  <i class="mm-split-trigger-bar"></i>
                  <i class="mm-split-trigger-bar"></i>
                </div>
              </div>
            </div>

            <!-- 使用动态组件切换BC布局 -->
            <component v-if="currentLayout" :is="currentLayout" :key="currentLayout" :selectedEmail="selectedEmail"
              :emailData="emailData" :emailTotal="emailTotal" :selectedTaskId="selectedTaskId"
              :emailType="currentEmailType" :writeEmailType="writeEmailType" :type="isMailNavNormalContainerOpen"
              :emailDefaultOption="emailDefaultOption" @switch="switchLayout"></component>

          </div>
        </div>
      </div>
    </div>
    <!---->
    <div class="side-chat-button-wrap" style="display: none;">
      <div class="side-chat-button">
        <span class="close-btn">
          <svg class="svg-icon" aria-hidden="true" color="rgba(0,0,0,0.5)" style="width: 16px; height: 16px;">
            <use xlink:href="#common-iconfont-close-btn"></use>
          </svg>
        </span>
        <i class="common-iconfont common-iconfont-chat1" style="font-size: 18px; color: rgb(255, 255, 255);"></i>
        <div class="chat-text">客服</div>
      </div>
    </div>
    <!---->
    <div class="novice-wrapper">
      <!---->
      <!--portal-->
    </div>
    <div style="position: fixed; top: 0px; left: 0px; width: 100%; height: 100%; z-index: 999999; pointer-events: none;">
    </div>
  </div>
</template>

<style lang="scss">
@import '../../static/scss/email/index/427.6511c6eb.css';
@import '../../static/scss/email/index/9755.4df34767.css';
@import '../../static/scss/email/index/224.72cc3cdf.css';
@import '../../static/scss/email/index/MailLayout.ff9335f7.css';
@import '../../static/scss/email/index/5778.5cdc5d42.css';
@import '../../static/scss/email/index/MailNavNormal.197e2dea.css';
@import '../../static/scss/email/index/ViewModeThree.2d81e965.css';
@import '../../static/scss/email/index/DetailSidebar.5d0e9eed.css';
@import '../../static/scss/email/index/2577.9a58297c.css';
@import '../../static/scss/email/index/5731.fedefcf6.css';
@import '../../static/scss/email/index/MailInformationExtension.679153d2.css';
@import '../../static/scss/email/index/43960.b19a070c.css';

@import '../../static/scss/email/index/43704.9ab0f947.css';
@import '../../static/scss/email/index/windi.8eb4264d.css';
@import '../../static/scss/email/index/79963.409615c1.css';
@import '../../static/scss/email/index/95397.02268278.css';
@import '../../static/scss/email/index/79750.533776e6.css';
@import '../../static/scss/email/index/51644.b769e6aa.css';
@import '../../static/scss/email/index/36103.1da850cf.css';
</style>

<script>
import emailHeaderLayout from './email_header.vue';
import emailContentLayout from './email_content.vue';
import writeEmailLayout from './write_email.vue';
import sendSuccessLayout from './send_success.vue';
import setup from './setup.vue';
import FolderTree from './folder_list_item.vue'
import CustomerEmailLayout from './customer_email/index.vue'
import { listTaskPull, listTaskSend } from "@/api/email/task";
import { listFolder } from "@/api/email/folder";
import { listLabel } from "@/api/email/label";
import { countMenu,getEmailInfo } from "@/api/email/email";
import { generalList, publicleadsGroupsList, packetList, sourceList, stageList, ratingList, activityList } from "@/api/customer/email";
import { EventBus } from "@/api/email/event-bus";
import { getUsuallyInfo } from '@/api/email/usually'
import { getCustomerSearchList } from '@/api/email/customer'
import { debounce } from '@/utils'
import { mapState } from "vuex";
export default {
  data() {
    return {
      pullTaskList: [],
      sendTaskList: [],
      emailList: [],
      folders: [],
      labels: [],
      activeMenuItem: null,
      currentLayout: '',
      isLeftPaneVisible: true,
      selectedTaskId: null,
      currentEmailType: '',
      emailTotal: 0,
      emailData: [],
      writeEmailType: '',
      selectedEmail: {},
      isInboxOpen: true,
      isOutboxOpen: true,
      isShowMoreOpen: true,
      isFolderOpen: true,
      isLabelOpen: true,
      isLabelSystemOpen: true,
      isLabelCustomOpen: true,
      isDropdownShown: false,
      menuCount: {},
      isMailNavNormalContainerOpen: true,
      selectedLabelTypeValue: null,
      generalLabelTypeDatas: null,
      labelTypeDatas: null,
      labelTypeOptions: [{
        value: 1,
        label: '公海分组'
      }, {
        value: 2,
        label: '客户分组'
      }, {
        value: 3,
        label: '客户来源'
      }, {
        value: 4,
        label: '客户状态'
      }, {
        value: 5,
        label: '客户星级'
      }, {
        value: 6,
        label: '客户活跃度'
      }],
      searchCustomer: '',//查询客户名称
      searchCustomerList: [],
    };
  },

  components: {
    'email_header': emailHeaderLayout,
    'email_content': emailContentLayout,
    'write_email': writeEmailLayout,
    'send_success': sendSuccessLayout,
    'setup': setup,
    'FolderTree': FolderTree,
    'customer_email': CustomerEmailLayout
  },
  methods: {
    async getCustomerOptionList() {
      try {
        const res = await getCustomerSearchList({
          keyword: this.searchCustomer
        })
        if (res.code === 200) {
          this.searchCustomerList = res.data
        }
      } catch { }
    },
    switchLayout(layoutName, email, emailData, emailTotal, currentEmailType) {
      this.currentLayout = layoutName;
      this.selectedEmail = email;
      this.emailData = emailData;
      this.emailTotal = emailTotal;
      this.selectedTaskId = this.selectedTaskId;
      this.currentEmailType = currentEmailType;
    },

    toggleLeftPane() {
      this.isLeftPaneVisible = !this.isLeftPaneVisible;
    },

    refreshFolderList() {
      listFolder().then((response) => {
        this.folders = response.data;
      });
    },

    refreshLabelList() {
      listLabel().then((response) => {
        this.labels = response.data;
      });
    },

    refreshMenuCount() {
      countMenu().then((response) => {
        this.menuCount = response.data;
      });
    },

    refreshPullEmailList() {
      listTaskPull().then((response) => {
        this.pullTaskList = response.rows;
      });
    },

    refreshSendEmailList() {
      listTaskSend().then((response) => {
        this.sendTaskList = response.rows;
      });
    },

    setActive(item) {
      this.activeMenuItem = item;
    },

    // 任务收件箱
    taskPullClick(taskId) {
      const active = 'PULL_' + taskId;
      this.triggerEmailHeaderEvent(active);
    },

    // 任务发件箱
    taskSendClick(taskId) {
      const active = 'SEND_' + taskId;
      this.triggerEmailHeaderEvent(active);
    },

    // 文件夹
    folderClick(folderId) {
      const active = 'FOLDER_' + folderId;
      this.triggerEmailHeaderEvent(active);
    },

    // 任务发件箱
    labelClick(labelId) {
      const active = 'LABEL_' + labelId;
      this.triggerEmailHeaderEvent(active);
    },

    // 全部收件
    allReceivedClick() {
      this.triggerEmailHeaderEvent('ALL_RECEIVED');
    },

    // 全部发件
    completeShipmentClick() {
      this.triggerEmailHeaderEvent('COMPLETE_SHIPMENT');
    },

    // 待处理邮件
    pendingMailClick() {
      this.triggerEmailHeaderEvent('PENDING_MAIL');
    },

    // 未读邮件
    anUnreadMailClick() {
      this.triggerEmailHeaderEvent('AN_UNREAD_MAIL');
    },

    // 草稿箱
    draftsClick() {
      this.triggerEmailHeaderEvent('DRAFTS');
    },

    // 已删除邮件
    deletedMailClick() {
      this.triggerEmailHeaderEvent('DELETED_MAIL');
    },

    // 垃圾邮件
    spamMailClick() {
      this.triggerEmailHeaderEvent('SPAM_MAIL');
    },

    // 追踪邮件
    traceInformationClick() {
      this.triggerEmailHeaderEvent('TRACK_INFORMATION');
    },

    switchWriteEmailPage() {
      this.$router.push('/email/index?type=write_email')
    },

    triggerEmailHeaderEvent(emailType, currentPage) {
      this.currentEmailType = emailType;
      this.setActive(emailType);
      this.switchLayout('email_header');
      this.$nextTick(() => {
        EventBus.$emit('email-header', emailType, currentPage, this.labels);
      })
    },

    toggleInbox() {
      this.isInboxOpen = !this.isInboxOpen;
    },

    toggleOutbox() {
      this.isOutboxOpen = !this.isOutboxOpen;
    },

    toggleLabel() {
      this.isLabelOpen = !this.isLabelOpen;
    },

    toggleLabelSystem() {
      this.isLabelSystemOpen = !this.isLabelSystemOpen;
    },

    toggleLabelCustom() {
      this.isLabelCustomOpen = !this.isLabelCustomOpen;
    },

    toggleShowMore() {
      this.isShowMoreOpen = !this.isShowMoreOpen;
    },

    toggleFolder() {
      this.isFolderOpen = !this.isFolderOpen;
    },

    toggleGeneralChildren(generalLabelTypeData) {
      console.log(generalLabelTypeData);
      if (!generalLabelTypeData.hasOwnProperty('showGeneralChildren')) {
        this.$set(generalLabelTypeData, 'showGeneralChildren', false);
      }

      generalLabelTypeData.showGeneralChildren = !generalLabelTypeData.showGeneralChildren;
    },

    toggleLabelTypeChildren(labelTypeData) {
      if (!labelTypeData.hasOwnProperty('showChildren')) {
        this.$set(labelTypeData, 'showChildren', false);
      }

      labelTypeData.showChildren = !labelTypeData.showChildren;
    },

    toggleType(isMailNavNormalContainerOpen) {

      this.$router.push(`/email/index?type=${isMailNavNormalContainerOpen ? 'default' : 'customer_email'}`)
      // this.isMailNavNormalContainerOpen = isMailNavNormalContainerOpen;
      // if (!this.isMailNavNormalContainerOpen) {
      //   this.currentLayout = 'customer_email'

      // } else {
      //   this.allReceivedClick()
      // }
    },

    getTaskCount(taskId) {
      if (taskId === undefined || taskId === null) {
        return '';
      }

      const task = this.menuCount.menuInboxTaskCountList?.find(t => t.taskId === taskId) || [];
      return task ? task.count : '';
    },

    handleLabelTypeSelectChange(selectedValue) {
      this.selectedLabelTypeValue = selectedValue;
      this.labelTypeDataList(selectedValue);
    },

    labelTypeDataList(selectedValue) {
      if (selectedValue == 1) {
        publicleadsGroupsList().then((response) => {
          this.labelTypeDatas = response.data
        });
      } else if (selectedValue == 2) {
        packetList().then((response) => {
          this.labelTypeDatas = response.data
        });
      } else if (selectedValue == 3) {
        sourceList().then((response) => {
          this.labelTypeDatas = response.data
        });
      } else if (selectedValue == 4) {
        stageList().then((response) => {
          this.labelTypeDatas = response.data
        });
      } else if (selectedValue == 5) {
        ratingList().then((response) => {
          this.labelTypeDatas = response.data
        });
      } else if (selectedValue == 6) {
        activityList().then((response) => {
          this.labelTypeDatas = response.data
        });
      }

      generalList().then((response) => {
        this.generalLabelTypeDatas = response.data?.map(val => {
          val.showGeneralChildren = true
          return val
        })
        this.selectedTaskId = this.generalLabelTypeDatas?.[0]?.customerList?.[0]?.id || ''
      });
    },
    onCustomerSearch(id) {
      this.selectedTaskId = id
    },
    // 初始化状态
    initStore() {
      this.$store.dispatch('emailSetting/GetUsuallyInfo')
      this.$store.dispatch('otherSetting/GetOtherInfo')
      this.$store.dispatch('emailList/GetEmailList')
    },
  },

  computed: {
    ...mapState({
      emailDefaultOption: state => state.emailSetting.usuallySetting
    }),
    systemLabels() {
      return this.labels.filter(label => label.type === 1);
    },
    customLabels() {
      return this.labels.filter(label => label.type === 2);
    }
  },

  watch: {
    "$route.query": {
      handler(newVal) {
        switch (newVal.type) {
          case "write_email":
            this.currentLayout = newVal.type;
            break;
          case "default":
            this.isMailNavNormalContainerOpen = true
            this.currentLayout = 'email_header'
            this.allReceivedClick();  // 触发事件
            break;
          case "customer_email":
            this.isMailNavNormalContainerOpen = false
            this.currentLayout = newVal.type
            if (this.labelTypeOptions.length > 0) {
              this.selectedLabelTypeValue = this.labelTypeOptions[0].value;
              this.labelTypeDataList(this.selectedLabelTypeValue);
            }
            break;
          case "setting_email":
            this.currentLayout = 'setup'
            break;
          default:
            this.$router.push('/email/index?type=default')
            break;
        }
      },
      deep: true,
      immediate: true,
    },
    searchCustomer: {
      handler(newVal) {
        if (newVal) {
          this.getCustomerOptionList()
        } else {
          this.searchCustomerList = []
        }
      }
    }
  },

  mounted() {
    this.refreshPullEmailList();
    this.refreshSendEmailList();
    this.refreshFolderList();
    this.refreshLabelList();
    this.refreshMenuCount();
    this.initStore()


    EventBus.$on('switch-send-success', (id) => {
      this.selectedEmail = {};
      this.selectedEmail.id = id;
      this.currentLayout = 'send_success';
    });

    EventBus.$on('switch-index', () => {
      this.allReceivedClick();
    });

    EventBus.$on('switch-write-email', (replyEmail, type) => {
      this.selectedEmail = replyEmail;
      this.writeEmailType = type;
      this.switchWriteEmailPage();
    });

    EventBus.$on('switch-email-header', (emailType, currentPage) => {
      this.triggerEmailHeaderEvent(emailType, currentPage)
    });

    EventBus.$on('refresh-index-task-list', () => {
      this.refreshPullEmailList();
      this.refreshSendEmailList();
    });

    EventBus.$on('refresh-index-label-list', () => {
      this.refreshLabelList();
    });

    EventBus.$on('refresh-index-folder-list', () => {
      this.refreshFolderList();
    });
  },

  beforeDestroy() {
    EventBus.$off('switch-send-success');
    EventBus.$off('switch-index');
    EventBus.$off('switch-write-email');
    EventBus.$off('switch-email-header');
    EventBus.$off('refresh-index-task-list');
    EventBus.$off('refresh-index-folder-list');
  },
};
</script>
