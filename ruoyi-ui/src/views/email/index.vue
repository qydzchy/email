<template>
  <div class="new-layout-right">
    <!---->
    <div class="riven-layout-container" data-v-app="">
      <div class="view-content-wrapper layout-extraSidebar" sidebar-collapse="">
        <div class="view-content-teleport-anchor"></div>
        <div :class="['mm-split', 'mail-layout-split', isLeftPaneVisible ? 'expanding' : 'collapsing']">
          <div class="mm-split-horizontal">
            <div class="mm-split-pane mm-split-pane__left" :style="{right: isLeftPaneVisible ? '87.7165%' : '98.9177%'}">
              <div class="layout-extraSidebar-sidebar">
                <nav v-if="isLeftPaneVisible" class="main-plus-nav">
                  <div class="mail-nav" transition="menu">
                    <div class="mail-nav-switch">
                      <a :class="[isMailNavNormalContainerOpen ? 'active' : '']" @click="toggleType(true)">
																	<span class="okki-icon-wrap">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																			<path fill-rule="evenodd" clip-rule="evenodd" d="M2.6 5.2A2.995 2.995 0 015 4h14c.981 0 1.852.47 2.4 1.199L12 11.591 2.6 5.199zM2 7.21V17a3 3 0 003 3h14a3 3 0 003-3V7.21l-9.438 6.417a1 1 0 01-1.124 0L2 7.209z"></path>
																		</svg>
																	</span>
                      </a>
                      <a :class="[!isMailNavNormalContainerOpen ? 'active' : '']" @click="toggleType(false)">
                        <span class="okki-icon-wrap">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
                            <path fill-rule="evenodd" clip-rule="evenodd" d="M12 2a5 5 0 100 10 5 5 0 000-10zM8.296 13a6 6 0 00-6 6v2a1 1 0 001 1h17.408a1 1 0 001-1v-2a6 6 0 00-6-6H8.296z"></path>
                          </svg>
                        </span>
                      </a>
                    </div>
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
                              <svg class="mm-icon mm-icon-switch" viewBox="0 0 24 24" name="switch" fill="currentColor" style="height: 12px; width: 12px;">
                                <path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.3.8-.3 1.1 0l1 1c.2.3.2.7 0 1z"></path>
                              </svg>
                              <!---->
                              <!---->
                            </button>
                          </div>
                        </div>
                        <!---->
                      </div>
                    </div>
                    <div class="mail-nav-normal-container" v-if="isMailNavNormalContainerOpen">
                      <ul role="menubar" class="mm-menu mail-sidebar-menu">
                        <li :class="['mm-submenu', isInboxOpen ? 'mm-submenu--opened' : '', 'mail-sidebar-submenu']" role="menuitem" aria-haspopup="true" aria-expanded="true" nativeondragover="function(e){return(0,te.CV)(e,t)}" nativeondragleave="function(e){return(0,te.aB)(e,t)}" nativeondrop="function(e){return(0,te.LQ)(e,t)}">
                          <div class="mm-submenu-title" style="padding-left: 14px; padding-right: 14px;" @click="toggleInbox">
                            <!---->
                            <span class="mm-menu-title">
																				<div class="right-click-menu-handler mail-menu-item-title ellipsis">
																					<span class="flex items-center" title="收件箱">收件箱</span>
																				</div>
                              <!---->
                              <!---->
																			</span>
                            <svg class="mm-icon mm-icon-chevrondown mm-submenu-icon-arrow" viewBox="0 0 24 24" name="chevrondown" fill="currentColor" style="height: 12px; width: 12px;">
                              <path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
                            </svg>
                          </div>
                          <ul role="menu" class="mm-menu mm-menu--inline" v-show="isInboxOpen">
                            <li class="mm-menu-item mail-sidebar-menu-item"
                                :class="{ 'mm-menu-item--active': activeMenuItem === 'ALL_RECEIVED' }"
                                @click.prevent="allReceivedClick"
                                role="menuitem" tabindex="-1" nativeonclick="function(e){e.stopPropagation(),n.gotoUpdate(M),M===w.e.MASS_BOX&&(0,h.M)(&quot;Email_catalogue_MassBox_view&quot;)}" style="padding-left: 35px; padding-right: 14px;">
                              <!---->
                              <!---->
                              <div class="mail-sidebar-menu-item">
                                <!---->
                                <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                                  <span class="flex items-center" title="全部收件">全部收件</span>
                                </div>
                                <span class="mm-tooltip mail-menu-item-count">
																						<span class="mm-tooltip-trigger">{{menuCount.allReceivedCount}}</span>
                                  <!---->
																					</span>
                                <!---->
                              </div>
                            </li>
                            <li v-for="task in pullTaskList" :key="task.id" class="mm-menu-item mail-sidebar-menu-item"
                                :class="{ 'mm-menu-item--active': activeMenuItem === 'PULL_'+task.id }"
                                @click="taskPullClick(task.id)"
                                role="menuitem" tabindex="-1" style="padding-left: 35px; padding-right: 14px;">
                              <!---->
                              <!---->
                              <div class="mail-sidebar-menu-item">
                                <div v-if="task.connStatus === 1" class="right-click-menu-handler mail-menu-item-title ellipsis">
                                  <!-- 正常状态的内容 -->
                                  <div class="mailbox-item ellipsis">
                                    <div class="public-mail-tag-wrapper ellipsis" slots="[object Object]">
                                      <div class="public-mail-tag-content">
                                        <span class="ellipsis">{{task.account}}</span>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                                <div v-else class="right-click-menu-handler mail-menu-item-title ellipsis">
                                  <!-- 异常状态的内容 -->
                                  <div class="mailbox-item ellipsis">
																							<span class="mm-tooltip mailbox-item-error-icon">
																								<span class="mm-tooltip-trigger">
																									<svg class="mm-icon mm-icon-info default-error-icon" viewBox="0 0 24 24" name="info" fill="#DD3C3C" style="height: 15px; width: 15px;">
																										<path d="M12 .9C5.9.9.9 5.9.9 12s5 11.1 11.1 11.1 11.1-5 11.1-11.1S18.1.9 12 .9zm0 5.6c.8 0 1.4.6 1.4 1.4s-.6 1.4-1.4 1.4-1.4-.6-1.4-1.4.6-1.4 1.4-1.4zm2.3 9.7c0 .2-.2.4-.5.4h-3.6c-.3 0-.5-.1-.5-.4v-.9c0-.3.2-.5.5-.5.2 0 .4-.2.4-.4v-1.9c0-.2-.2-.5-.4-.5-.3 0-.5-.1-.5-.4v-.9c0-.3.2-.5.5-.5h2.7c.3 0 .5.2.5.5v3.7c0 .2.2.4.4.4.3 0 .5.2.5.5v.9z"></path>
																									</svg>
																								</span>
																							</span>
                                    <div class="public-mail-tag-wrapper ellipsis" slots="[object Object]">
                                      <div class="public-mail-tag-content">
                                        <span class="mm-tooltip mailbox-item-email-error-tip">
																										<span class="mm-tooltip-trigger">
																											<span class="ellipsis">{{task.account}}</span>
																										</span>
																									</span>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                                <span class="mm-tooltip mail-menu-item-count"><span class="mm-tooltip-trigger">{{ getTaskCount(task.id) }}</span></span>
                                <!---->
                              </div>
                            </li>
                          </ul>
                        </li>
                        <li class="mm-menu-item mail-sidebar-menu-item"
                            :class="{ 'mm-menu-item--active': activeMenuItem === 'PENDING_MAIL' }"
                            @click="pendingMailClick"
                            role="menuitem" tabindex="-1" nativeonclick="function(e){e.stopPropagation(),n.gotoUpdate(M),M===w.e.MASS_BOX&&(0,h.M)(&quot;Email_catalogue_MassBox_view&quot;)}" style="padding-left: 14px; padding-right: 14px;">
                          <!---->
                          <!---->
                          <div class="mail-sidebar-menu-item">
                            <!---->
                            <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                              <span class="flex items-center">待处理邮件</span>
                            </div>
                            <span class="mm-tooltip mail-menu-item-count">
																				<span class="mm-tooltip-trigger">{{menuCount.pendingMailCount}}</span>
                              <!---->
																			</span>
                            <!---->
                          </div>
                        </li>
                        <li class="mm-menu-item mail-sidebar-menu-item"
                            :class="{ 'mm-menu-item--active': activeMenuItem === 'AN_UNREAD_MAIL' }"
                            @click="anUnreadMailClick"
                            role="menuitem" tabindex="-1" nativeonclick="function(e){e.stopPropagation(),n.gotoUpdate(M),M===w.e.MASS_BOX&&(0,h.M)(&quot;Email_catalogue_MassBox_view&quot;)}" style="padding-left: 14px; padding-right: 14px;">
                          <!---->
                          <!---->
                          <div class="mail-sidebar-menu-item">
                            <!---->
                            <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                              <span class="flex items-center" title="未读邮件">未读邮件</span>
                            </div>
                            <span class="mm-tooltip mail-menu-item-count">
																				<span class="mm-tooltip-trigger">{{menuCount.anUnreadMailCount}}</span>
                              <!---->
																			</span>
                            <!---->
                          </div>
                        </li>
                        <li class="mm-menu-item mail-sidebar-menu-item"
                            :class="{ 'mm-menu-item--active': activeMenuItem === 'DRAFTS' }"
                            @click="draftsClick"
                            role="menuitem" tabindex="-1" nativeonclick="function(e){e.stopPropagation(),n.gotoUpdate(M),M===w.e.MASS_BOX&&(0,h.M)(&quot;Email_catalogue_MassBox_view&quot;)}" style="padding-left: 14px; padding-right: 14px;">
                          <!---->
                          <!---->
                          <div class="mail-sidebar-menu-item">
                            <!---->
                            <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                              <span class="flex items-center" title="草稿箱">草稿箱</span>
                            </div>
                            <span class="mm-tooltip mail-menu-item-count">
																				<span class="mm-tooltip-trigger">{{menuCount.draftsCount}}</span>
                              <!---->
																			</span>
                            <!---->
                          </div>
                        </li>
                        <li :class="['mm-submenu', isOutboxOpen ? 'mm-submenu--opened' : '', 'mail-sidebar-submenu']" role="menuitem" aria-haspopup="true" aria-expanded="true" nativeondragover="function(e){return(0,te.CV)(e,t)}" nativeondragleave="function(e){return(0,te.aB)(e,t)}" nativeondrop="function(e){return(0,te.LQ)(e,t)}">
                          <div class="mm-submenu-title" style="padding-left: 14px; padding-right: 14px;" @click="toggleOutbox">
                            <!---->
                            <span class="mm-menu-title">
																				<div class="right-click-menu-handler mail-menu-item-title ellipsis">
																					<span class="flex items-center" title="发件箱">发件箱</span>
																				</div>
                              <!---->
                              <!---->
																			</span>
                            <svg class="mm-icon mm-icon-chevrondown mm-submenu-icon-arrow" viewBox="0 0 24 24" name="chevrondown" fill="currentColor" style="height: 12px; width: 12px;">
                              <path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
                            </svg>
                          </div>
                          <ul role="menu" class="mm-menu mm-menu--inline" v-show="isOutboxOpen">
                            <li class="mm-menu-item mail-sidebar-menu-item novice-tour-enter-outbox-click"
                                :class="{ 'mm-menu-item--active': activeMenuItem === 'COMPLETE_SHIPMENT' }"
                                @click="completeShipmentClick"
                                role="menuitem" tabindex="-1" nativeonclick="function(e){e.stopPropagation(),n.gotoUpdate(M),M===w.e.MASS_BOX&&(0,h.M)(&quot;Email_catalogue_MassBox_view&quot;)}" style="padding-left: 35px; padding-right: 14px;">
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
                            <li v-for="task in sendTaskList" :key="task.id" class="mm-menu-item mail-sidebar-menu-item novice-tour-enter-outbox-click"
                                :class="{ 'mm-menu-item--active': activeMenuItem === 'SEND_'+task.id }"
                                @click="taskSendClick(task.id)"
                                role="menuitem" tabindex="-1" nativeonclick="function(e){e.stopPropagation(),n.gotoUpdate(M),M===w.e.MASS_BOX&&(0,h.M)(&quot;Email_catalogue_MassBox_view&quot;)}" style="padding-left: 35px; padding-right: 14px;">
                              <!---->
                              <!---->
                              <div class="mail-sidebar-menu-item">
                                <!---->
                                <div v-if="task.connStatus === 1" class="right-click-menu-handler mail-menu-item-title ellipsis">
                                  <!-- 正常状态的内容 -->
                                  <div class="mailbox-item ellipsis">
                                    <div class="public-mail-tag-wrapper ellipsis" slots="[object Object]">
                                      <div class="public-mail-tag-content">
                                        <span class="ellipsis">{{task.account}}</span>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                                <div v-else class="right-click-menu-handler mail-menu-item-title ellipsis">
                                  <!-- 异常状态的内容 -->
                                  <div class="mailbox-item ellipsis">
																							<span class="mm-tooltip mailbox-item-error-icon">
																								<span class="mm-tooltip-trigger">
																									<svg class="mm-icon mm-icon-info default-error-icon" viewBox="0 0 24 24" name="info" fill="#DD3C3C" style="height: 15px; width: 15px;">
																										<path d="M12 .9C5.9.9.9 5.9.9 12s5 11.1 11.1 11.1 11.1-5 11.1-11.1S18.1.9 12 .9zm0 5.6c.8 0 1.4.6 1.4 1.4s-.6 1.4-1.4 1.4-1.4-.6-1.4-1.4.6-1.4 1.4-1.4zm2.3 9.7c0 .2-.2.4-.5.4h-3.6c-.3 0-.5-.1-.5-.4v-.9c0-.3.2-.5.5-.5.2 0 .4-.2.4-.4v-1.9c0-.2-.2-.5-.4-.5-.3 0-.5-.1-.5-.4v-.9c0-.3.2-.5.5-.5h2.7c.3 0 .5.2.5.5v3.7c0 .2.2.4.4.4.3 0 .5.2.5.5v.9z"></path>
																									</svg>
																								</span>
																							</span>
                                    <div class="public-mail-tag-wrapper ellipsis" slots="[object Object]">
                                      <div class="public-mail-tag-content">
                                        <span class="mm-tooltip mailbox-item-email-error-tip">
																										<span class="mm-tooltip-trigger">
																											<span class="ellipsis">{{task.account}}</span>
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

                        <li class="mm-submenu mm-submenu--opened top-divided for-folder-tour mail-sidebar-submenu" role="menuitem" aria-haspopup="true" aria-expanded="true" nativeondragover="function(e){return(0,te.CV)(e,t)}" nativeondragleave="function(e){return(0,te.aB)(e,t)}" nativeondrop="function(e){return(0,te.LQ)(e,t)}">
                          <div class="mm-submenu-title" style="padding-left: 14px; padding-right: 14px;">
                            <!---->
                            <span class="mm-menu-title">
																				<div class="right-click-menu-handler mail-menu-item-title ellipsis" @click="toggleFolder">
																					<span class="flex items-center" title="文件夹">文件夹</span>
																					<div>
																						<div class="menu-item-operation-search">
																							<div class="search-icon">
																								<span class="okki-icon-wrap">​<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																										<path fill-rule="evenodd" clip-rule="evenodd" d="M17 10.5a6.5 6.5 0 11-13 0 6.5 6.5 0 0113 0zm-1.473 6.855a8.5 8.5 0 111.463-1.365l3.303 3.303a1 1 0 01-1.414 1.414l-3.352-3.352z"></path>
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
																							<span class="okki-icon-wrap menu-item-operation-icon">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																									<path d="M10 12a2 2 0 104 0 2 2 0 00-4 0zm0 7a2 2 0 104 0 2 2 0 00-4 0zm0-14a2 2 0 104 0 2 2 0 00-4 0z"></path>
																								</svg>
																							</span>
																						</div>
                                            <!---->
																					</div>
																				</span>
																			</span>
                            <svg class="mm-icon mm-icon-chevrondown mm-submenu-icon-arrow" viewBox="0 0 24 24" name="chevrondown" fill="currentColor" style="height: 12px; width: 12px;">
                              <path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
                            </svg>
                          </div>
                          <FolderTree :folders="folders" v-show="isFolderOpen" @folder-selected="folderClick"></FolderTree>
                        </li>


                        <li :class="['mm-submenu', isLabelOpen ? 'mm-submenu--opened' : '', 'mail-sidebar-submenu']"  role="menuitem" aria-haspopup="true" aria-expanded="true">
                          <div class="mm-submenu-title" style="padding-left: 14px; padding-right: 14px;" @click="toggleLabel">
                            <span class="mm-menu-title">
                              <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                                <span class="flex items-center" title="标签邮件">标签邮件</span>
                              </div>
                            </span>
                            <svg class="mm-icon mm-icon-chevrondown mm-submenu-icon-arrow" viewBox="0 0 24 24" name="chevrondown" style="height: 12px; width: 12px;">
                              <path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
                            </svg>
                          </div>
                          <ul role="menu" class="mm-menu mm-menu--inline" v-show="isLabelOpen">
                            <!-- 系统标签 Section -->
                            <li :class="['mm-submenu', isLabelSystemOpen ? 'mm-submenu--opened' : '', 'mail-sidebar-submenu']" role="menuitem">
                              <div class="mm-submenu-title" style="padding-left: 35px; padding-right: 14px;" @click="toggleLabelSystem">
                                <span class="mm-menu-title">
                                  <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                                    <span class="flex items-center" title="系统标签">系统标签</span>
                                  </div>
                                </span>
                                <svg class="mm-icon mm-icon-chevrondown mm-submenu-icon-arrow" viewBox="0 0 24 24" name="chevrondown" style="height: 12px; width: 12px;">
                                  <path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
                                </svg>
                              </div>
                              <ul role="menu" class="mm-menu mm-menu--inline" v-show="isLabelSystemOpen">
                                <li v-for="label in systemLabels" :key="label.id" class="mm-menu-item mail-sidebar-menu-item"
                                    :class="{ 'mm-menu-item--active': activeMenuItem === 'LABEL_'+label.id }"
                                    @click="labelClick(label.id)"
                                >
                                  <div class="mail-sidebar-menu-item">
                                    <span class="mail-menu-item-tag-color" :style="{ 'background-color': `rgb(${label.color})` }"></span>
                                    <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                                      <span class="flex items-center" :title="label.name">{{ label.name }}</span>
                                    </div>
                                  </div>
                                </li>
                              </ul>
                            </li>

                            <!-- 自定义标签 Section -->
                            <li :class="['mm-submenu', isLabelCustomOpen ? 'mm-submenu--opened' : '', 'mail-sidebar-submenu']" role="menuitem">
                              <div class="mm-submenu-title" style="padding-left: 35px; padding-right: 14px;" @click="toggleLabelCustom">
                                <span class="mm-menu-title">
                                  <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                                    <span class="flex items-center" title="自定义标签">自定义标签</span>
                                  </div>
                                </span>
                                <svg class="mm-icon mm-icon-chevrondown mm-submenu-icon-arrow" viewBox="0 0 24 24" name="chevrondown" style="height: 12px; width: 12px;">
                                  <path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
                                </svg>
                              </div>
                              <ul role="menu" class="mm-menu mm-menu--inline" v-show="isLabelCustomOpen">
                                <li v-for="label in customLabels" :key="label.id" class="mm-menu-item mail-sidebar-menu-item"
                                    :class="{ 'mm-menu-item--active': activeMenuItem === 'LABEL_'+label.id }"
                                    @click="labelClick(label.id)">
                                  <div class="mail-sidebar-menu-item">
                                    <span class="mail-menu-item-tag-color" :style="{ 'background-color': `rgb(${label.color})` }"></span>
                                    <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                                      <span class="flex items-center" :title="label.name">{{ label.name }}</span>
                                    </div>
                                  </div>
                                </li>
                              </ul>
                            </li>
                          </ul>
                        </li>

                    <li :class="['mm-submenu', isShowMoreOpen ? 'mm-submenu--opened' : '', 'mail-sidebar-submenu']" role="menuitem" aria-haspopup="true" aria-expanded="true" nativeondragover="function(e){return(0,te.CV)(e,t)}" nativeondragleave="function(e){return(0,te.aB)(e,t)}" nativeondrop="function(e){return(0,te.LQ)(e,t)}">
                    <div class="mm-submenu-title" style="padding-left: 14px; padding-right: 14px;" @click="toggleShowMore">
                      <!---->
                      <span class="mm-menu-title">
                                                            <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                                                              <span class="flex items-center" title="显示更多">显示更多</span>
                                                            </div>
                        <!---->
                        <!---->
                                                          </span>
                      <svg class="mm-icon mm-icon-chevrondown mm-submenu-icon-arrow" viewBox="0 0 24 24" name="chevrondown" fill="currentColor" style="height: 12px; width: 12px;">
                        <path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
                      </svg>
                    </div>
                    <ul role="menu" class="mm-menu mm-menu--inline" v-show="isShowMoreOpen">
                      <li class="mm-menu-item mail-sidebar-menu-item"
                          :class="{ 'mm-menu-item--active': activeMenuItem === 'DELETED_MAIL' }"
                          @click="deletedMailClick"
                          role="menuitem" tabindex="-1" nativeonclick="function(e){e.stopPropagation(),n.gotoUpdate(M),M===w.e.MASS_BOX&&(0,h.M)(&quot;Email_catalogue_MassBox_view&quot;)}" style="padding-left: 35px; padding-right: 14px;">
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
                          :class="{ 'mm-menu-item--active': activeMenuItem === 'SPAM' }"
                          @click="spamMailClick"
                          role="menuitem" tabindex="-1" nativeonclick="function(e){e.stopPropagation(),n.gotoUpdate(M),M===w.e.MASS_BOX&&(0,h.M)(&quot;Email_catalogue_MassBox_view&quot;)}" style="padding-left: 35px; padding-right: 14px;">
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
                          @click="traceInformationClick"
                          role="menuitem" tabindex="-1" nativeonclick="function(e){e.stopPropagation(),n.gotoUpdate(M),M===w.e.MASS_BOX&&(0,h.M)(&quot;Email_catalogue_MassBox_view&quot;)}" style="padding-left: 35px; padding-right: 14px;">
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

                    <div class="customer-mail-nav" isbindemail="true" v-if="!isMailNavNormalContainerOpen">
                      <div class="search-wrapper">
																<span class="mm-input--prefix mm-input search-input">
																	<!---->
																	<span class="mm-input-affix-wrapper">
																		<span class="mm-input-prefix--icon mm-input-prefix">
																			<svg class="mm-icon mm-icon-search" viewBox="0 0 24 24" name="search"  fill="#b0adab">
																				<path d="M22.9 20.9l-6.2-6.1c1.3-1.8 1.9-4 1.6-6.4-.6-3.9-3.8-7.1-7.8-7.4C5 .4.4 5 1 10.5c.3 4 3.5 7.3 7.4 7.8 2.4.3 4.6-.3 6.4-1.5l6.1 6.1c.3.3.7.3 1 0l.9-1c.3-.3.3-.7.1-1zM3.7 9.6c0-3.2 2.7-5.9 5.9-5.9 3.3 0 6 2.7 6 5.9 0 3.3-2.7 6-6 6-3.2 0-5.9-2.6-5.9-6z"></path>
																			</svg>
																		</span>
																		<input placeholder="客户名称/编号/邮箱/联系人昵称" type="text" class="mm-input-inner" value="">
                                    <!---->
																	</span>
                                  <!---->
																</span>
                      </div>
                      <div class="select-wrapper">
<!--																<span class="mm-select">
																	<div class="mm-selector&#45;&#45;single mm-selector" tabindex="0">
																		&lt;!&ndash;&ndash;&gt;
																		<div class="mm-selector-rendered">
																			<span title="公海分组" class="mm-selector-selected-value" style="opacity: 1;">公海分组</span>
                                      &lt;!&ndash;&ndash;&gt;
																		</div>
                                    &lt;!&ndash;&ndash;&gt;
																		<span class="mm-selector-suffix-icon">
																			<svg class="mm-icon mm-icon-chevrondown mm-selector-arrow" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px; outline: none;">
																				<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																			</svg>
																		</span>
																	</div>
																</span>-->

                        <el-select v-model="selectedLabelTypeValue" placeholder="请选择" @change="handleLabelTypeSelectChange">
                          <el-option
                            v-for="item in labelTypeOptions"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                          </el-option>
                        </el-select>
                      </div>
                      <div class="nav-wrap">
                        <div class="mm-tree--highlight-current mm-tree customer-mail-nav-tree" role="tree">
                          <div v-for="labelTypeData in labelTypeDatas" :key="labelTypeData.id" class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="0">
                            <div class="mm-tree-node-content" style="padding-left: 14px;">
																			<span class="mm-tree-node-expand-icon-wrapper">
																				<svg class="mm-icon mm-icon-chevrondown tree-expand-icon" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																					<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																				</svg>
																			</span>
                              <!---->
                              <!---->
                              <span class="mm-tree-node-label-wrap">
																				<span class="tree-node-text">
																					<span class="ellipsis name">{{labelTypeData.name}}</span>
																					<span class="mm-tooltip count">
																						<span class="mm-tooltip-trigger">
																							<span>{{ labelTypeData.customerCount }},{{ labelTypeData.emailCount }}</span>
																						</span>
                                            <!---->
																					</span>
																				</span>
																			</span>
                            </div>
                            <div class="mm-tree-node-children" role="group" style="display: none;"></div>
                          </div>


                          <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                            <div class="mm-tree-node-content" style="padding-left: 14px;">
																			<span class="mm-tree-node-expand-icon-wrapper">
																				<svg class="mm-icon mm-icon-chevrondown tree-expand-icon" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																					<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																				</svg>
																			</span>
                              <!---->
                              <!---->
                              <span class="mm-tree-node-label-wrap">
																				<span class="tree-node-text">
																					<span title="公共公海分组" class="ellipsis name">公共公海分组</span>
																					<span class="mm-tooltip count">
																						<span class="mm-tooltip-trigger">
																							<span>35,0</span>
																						</span>
                                            <!---->
																					</span>
																				</span>
																			</span>
                            </div>
                            <div class="mm-tree-node-children" role="group" style="display: none;">
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=13952243948271" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=13952243948271" title="precisionelectronics.net" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">precisionelectronics.net</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=1697266388112" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=1697266388112" title="UIG DEDEKTOR SAN.VE TIC.LTD.STI" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">UIG DEDEKTOR SAN.VE TIC.LTD.STI</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=5873436570347" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=5873436570347" title="Alexey Bushuev" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">Alexey Bushuev</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=1579135505006" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=1579135505006" title="Tech Quality co.,Ltd" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">Tech Quality co.,Ltd</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=3335605332722" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=3335605332722" title="Apadturk TM" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">Apadturk TM</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=3695495346974" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=3695495346974" title="Solution Tech" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">Solution Tech</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=2955669374763" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=2955669374763" title="aoonamm@gmail.com（6）" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">aoonamm@gmail.com（6）</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=2282478918397" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=2282478918397" title="Tentech Corporation（8）" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">Tentech Corporation（8）</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown" fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=3335763802154" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=3335763802154" title="No Company Name_975483422" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">No Company Name_975483422</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown" fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=3335766740449" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=3335766740449" title="No Company Name_978178257(14)" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">No Company Name_978178257(14)</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown" fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=3930463165021" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=3930463165021" title="ilumac.com.br（4）" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">ilumac.com.br（4）</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown" fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=13206552247283" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=13206552247283" title="all（7）" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">all（7）</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=3335579673099" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=3335579673099" title="NoCompanyName_1161033168" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">NoCompanyName_1161033168</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=2448975263357" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=2448975263357" title="NoCompanyName_1466249189（12）" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">NoCompanyName_1466249189（12）</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=5304350758965" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=5304350758965" title="Tony Buldakov（11）" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">Tony Buldakov（11）</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=5640213394356" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=5640213394356" title="Nick TV（9）" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">Nick TV（9）</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=4573290488897" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=4573290488897" title="Larbsopha" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">Larbsopha</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=3335744905294" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=3335744905294" title="Tiago Cavadas（10）" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">Tiago Cavadas（10）</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=13203994129551" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=13203994129551" title="viktor.olvir@yandex.ru(3)" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">viktor.olvir@yandex.ru(3)</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=2282477394623" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=2282477394623" title="NA" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">NA</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=3335734036360" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=3335734036360" title="Christian Villamil(5)" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">Christian Villamil(5)</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=2654162512886" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=2654162512886" title="cedrixsongore@hotmail.com" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">cedrixsongore@hotmail.com</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=3335614317514" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=3335614317514" title="Total imex corporation Co.,Ltd." class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">Total imex corporation Co.,Ltd.</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=3335753350229" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=3335753350229" title="Speedlight Electronics Importing LLC" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">Speedlight Electronics Importing LLC</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=2976241297142" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=2976241297142" title="COMEL SRL" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">COMEL SRL</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=13285671447452" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=13285671447452" title="DRUID LLC" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">DRUID LLC</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=1232118145087" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=1232118145087" title="MPI-NEO CO.,LTD." class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">MPI-NEO CO.,LTD.</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=13206569561783" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=13206569561783" title="Trainser（2）" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">Trainser（2）</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=3344182248718" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=3344182248718" title="Complex Invest LLC（1）" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">Complex Invest LLC（1）</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=3335760303955" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=3335760303955" title="Walnut Hills Product Innovations, LLC" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">Walnut Hills Product Innovations, LLC</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=4650380424233" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=4650380424233" title="Ubicom Technology Co.,Ltd." class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">Ubicom Technology Co.,Ltd.</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=3335749873203" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=3335749873203" title="kazem taghdareh" class="link ellipsis tree-node-text">
																							<div class="mm-popover empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">kazem taghdareh</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=13285552533675" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=13285552533675" title="Aliyainnovation.co" class="link ellipsis tree-node-text">
																							<div class="mm-popover is-empty empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">Aliyainnovation.co</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=13744321738559" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=13744321738559" title="NoCompanyName_1758690318" class="link ellipsis tree-node-text">
																							<div class="mm-popover is-empty empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">NoCompanyName_1758690318</span>
																								</div>
                                                <!---->
																							</div>
                                              <!---->
																						</a>
																					</span>
                                </div>
                                <!---->
                              </div>
                              <div class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
                                <div class="mm-tree-node-content" style="padding-left: 34px;">
																					<span class="mm-tree-node-expand-icon-wrapper">
																						<svg class="mm-icon mm-icon-chevrondown tree-expand-icon is-leaf" viewBox="0 0 24 24" name="chevrondown"  fill="currentColor" style="height: 12px; width: 12px;">
																							<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																						</svg>
																					</span>
                                  <!---->
                                  <!---->
                                  <span class="mm-tree-node-label-wrap">
																						<a data-savepage-href="/pro/mail/customer?company_id=13955826089170" href="https://crm.xiaoman.cn/pro/mail/customer?company_id=13955826089170" title="ceshi001" class="link ellipsis tree-node-text">
																							<div class="mm-popover is-empty empty-item-tooltip" props="[object Object]">
																								<div>
																									<span class="ellipsis name">ceshi001</span>
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
                      </div>
                      <div class="nav-max-customer-tip ellipsis" title="为了性能，每组最多展示200个客户，客户过多时建议直接搜索">为了性能，每组最多展示200个客户，客户过多时建议直接搜索</div>
                    </div>
</div>
</nav>
<span :class="['position-toogle', isLeftPaneVisible ? 'expanding' : 'collapsing']" @click="toggleLeftPane">
														<i class="m-icon icon-left-small"></i>
													</span>
</div>
</div>
<div class="mm-split-trigger-wrap" :style="{left: isLeftPaneVisible ? '12.2835%' : '1.08225%'}">
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
            <component
              :is="currentLayout"
              :key="currentLayout"
              :selectedEmail="selectedEmail"
              :emailData="emailData"
              :emailTotal="emailTotal"
              :selectedTaskId="selectedTaskId"
              :emailType="currentEmailType"
              :writeEmailType="writeEmailType"
              @switch="switchLayout"
            ></component>

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
<div style="position: fixed; top: 0px; left: 0px; width: 100%; height: 100%; z-index: 999999; pointer-events: none;"></div>
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
import {listTaskPull, listTaskSend} from "@/api/email/task";
import { listFolder } from "@/api/email/folder";
import { listLabel } from "@/api/email/label";
import { countMenu } from "@/api/email/email";
import { generalList, publicleadsGroupsList, packetList, sourceList, stageList, ratingList, activityList} from "@/api/customer/email";
import { EventBus } from "@/api/email/event-bus";

export default {
  data() {
    return {
      pullTaskList: [],
      sendTaskList: [],
      emailList: [],
      folders: [],
      labels: [],
      activeMenuItem: null,
      currentLayout: 'email_header',
      isLeftPaneVisible: true,
      selectedTaskId: null,
      currentEmailType: '',
      emailTotal: 0,
      emailData: [],
      selectedEmail: {},
      writeEmailType: '',
      isInboxOpen: true,
      isOutboxOpen: true,
      isShowMoreOpen: true,
      isFolderOpen: true,
      isLabelOpen: true,
      isLabelSystemOpen: true,
      isLabelCustomOpen: true,
      isDropdownShown: false,
      menuCount: {},
      isMailNavNormalContainerOpen : true,
      selectedLabelTypeValue: null,
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
    };
  },
  components: {
    'email_header': emailHeaderLayout,
    'email_content': emailContentLayout,
    'write_email': writeEmailLayout,
    'send_success': sendSuccessLayout,
    'setup': setup,
    'FolderTree': FolderTree
  },

  methods: {
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
      this.triggerEmailHeaderEvent('SPAM');
    },

    // 追踪邮件
    traceInformationClick() {
      this.triggerEmailHeaderEvent('TRACK_INFORMATION');
    },

    switchWriteEmailPage() {
      this.currentLayout = 'write_email';
    },

    triggerEmailHeaderEvent(emailType, currentPage) {
      this.setActive(emailType);
      this.switchLayout('email_header');
      this.$nextTick(()=>{
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

    toggleType(isMailNavNormalContainerOpen) {
      this.isMailNavNormalContainerOpen = isMailNavNormalContainerOpen;

      if (!this.isMailNavNormalContainerOpen) {
        if (this.labelTypeOptions.length > 0) {
          this.selectedLabelTypeValue = this.labelTypeOptions[0].value;
        }
      }
    },

    getTaskCount(taskId) {
      const task = this.menuCount.menuInboxTaskCountList.find(t => t.taskId === taskId);
      return task ? task.count : '';
    },

    handleLabelTypeSelectChange(selectedValue) {
      this.selectedLabelTypeValue = selectedValue;

    },

    labelTypeDataList(selectedValue) {
      if (selectedValue == 1) {
        publicleadsGroupsList().then((response) => {
          this.labelTypeData = response.data;
        });
      } else if (selectedValue == 2) {

      } else if (selectedValue == 3) {

      } else if (selectedValue == 4) {

      } else if (selectedValue == 5) {

      } else if (selectedValue == 6) {

      }
    }
  },

  computed: {
    systemLabels() {
      return this.labels.filter(label => label.type === 1);
    },
    customLabels() {
      return this.labels.filter(label => label.type === 2);
    }
  },

  mounted() {
    this.allReceivedClick();  // 触发事件
    this.refreshPullEmailList();
    this.refreshSendEmailList();
    this.refreshFolderList();
    this.refreshLabelList();
    this.refreshMenuCount();

    EventBus.$on('switch-send-success', () => {
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
      this.triggerEmailHeaderEvent(emailType,currentPage)
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
