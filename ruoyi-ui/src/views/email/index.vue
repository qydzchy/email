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
                      <a class="active">
																	<span class="okki-icon-wrap">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																			<path fill-rule="evenodd" clip-rule="evenodd" d="M2.6 5.2A2.995 2.995 0 015 4h14c.981 0 1.852.47 2.4 1.199L12 11.591 2.6 5.199zM2 7.21V17a3 3 0 003 3h14a3 3 0 003-3V7.21l-9.438 6.417a1 1 0 01-1.124 0L2 7.209z"></path>
																		</svg>
																	</span>
                      </a>
<!--                      <a class="">
																	<span class="okki-icon-wrap">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																			<path fill-rule="evenodd" clip-rule="evenodd" d="M9 7a3 3 0 116 0 3 3 0 01-6 0zm3-5a5 5 0 100 10 5 5 0 000-10zM8.296 13a6 6 0 00-6 6v2a1 1 0 001 1h17.408a1 1 0 001-1v-2a6 6 0 00-6-6H8.296zm-4 6a4 4 0 014-4h7.408a4 4 0 014 4v1H4.296v-1z"></path>
																		</svg>
																	</span>
                      </a>-->
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
                    <div class="mail-nav-normal-container">
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
                                @click="allReceivedClick"
                                role="menuitem" tabindex="-1" nativeonclick="function(e){e.stopPropagation(),n.gotoUpdate(M),M===w.e.MASS_BOX&&(0,h.M)(&quot;Email_catalogue_MassBox_view&quot;)}" style="padding-left: 35px; padding-right: 14px;">
                              <!---->
                              <!---->
                              <div class="mail-sidebar-menu-item">
                                <!---->
                                <div class="right-click-menu-handler mail-menu-item-title ellipsis">
                                  <span class="flex items-center" title="全部收件">全部收件</span>
                                </div>
                                <span class="mm-tooltip mail-menu-item-count">
																						<span class="mm-tooltip-trigger">758</span>
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
																				<span class="mm-tooltip-trigger">2</span>
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
																				<span class="mm-tooltip-trigger">758</span>
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
																				<span class="mm-tooltip-trigger">61</span>
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
import { EventBus } from "@/api/email/event-bus";

export default {
  data() {
    return {
      pullTaskList: [],
      sendTaskList: [],
      emailList: [],
      folders: [],
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
      isDropdownShown: false,
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

  watch: {
    currentLayout(newLayout, oldLayout) {
      if (newLayout === 'email_header' && oldLayout !== 'email_header') {
        this.triggerAllReceivedEvent();
      }
    }
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
      this.selectedTaskId = taskId;
      this.switchLayout('email_header');
      const active = 'PULL_' + taskId;
      this.onTaskClick(active);
      this.setActive(active);
    },

    // 任务发件箱
    taskSendClick(taskId) {
      this.switchLayout('email_header');
      const active = 'SEND_' + taskId;
      this.onTaskClick(active);
      this.setActive(active);
    },

    // 文件夹
    folderClick(folderId) {
      this.switchLayout('email_header');
      const active = 'FOLDER_' + folderId;
      this.onFolderClick(active);
      this.setActive(active);
    },

    // 全部收件
    allReceivedClick() {
      this.selectedTaskId = null;
      this.switchLayout('email_header');
      this.setActive('ALL_RECEIVED');
      this.triggerAllReceivedEvent();
    },

    // 全部发件
    completeShipmentClick() {
      this.selectedTaskId = null;
      this.switchLayout('email_header');
      this.setActive('COMPLETE_SHIPMENT');
      this.triggerCompleteShipmentEvent();
    },

    // 待处理邮件
    pendingMailClick() {
      this.selectedTaskId = null;
      this.switchLayout('email_header');
      this.setActive('PENDING_MAIL');
      this.triggerPendingMailEvent();
    },

    // 未读邮件
    anUnreadMailClick() {
      this.selectedTaskId = null;
      this.currentLayout = 'email_header';
      this.setActive('AN_UNREAD_MAIL');
      this.triggerAnUnreadMailEvent();
    },

    // 草稿箱
    draftsClick() {
      this.switchLayout('email_header');
      this.setActive('DRAFTS');
      this.triggerDraftsEvent();
    },

    // 已删除邮件
    deletedMailClick() {
      this.switchLayout('email_header');
      this.setActive('DELETED_MAIL');
      this.triggerDeletedMailEvent();
    },

    // 垃圾邮件
    spamMailClick() {
      this.switchLayout('email_header');
      this.setActive('SPAM');
      this.triggerSpamMailEvent();
    },

    // 追踪邮件
    traceInformationClick() {
      this.switchLayout('email_header');
      this.setActive('TRACK_INFORMATION');
      this.triggerTraceInformationEvent();
    },

    switchWriteEmailPage() {
      this.currentLayout = 'write_email';
    },

    onTaskClick(emailType) {
      EventBus.$emit('task-selected', emailType);
    },

    onFolderClick(emailType) {
      EventBus.$emit('folder-selected', emailType);
    },

    triggerAllReceivedEvent() {
      EventBus.$emit('all-received-selected', 'ALL_RECEIVED');
    },

    triggerCompleteShipmentEvent() {
      EventBus.$emit('complete-shipment-selected', 'COMPLETE_SHIPMENT');
    },

    triggerPendingMailEvent() {
      EventBus.$emit('pending-mail-selected', 'PENDING_MAIL');
    },

    triggerAnUnreadMailEvent() {
      EventBus.$emit('an-unread-mail-selected', 'AN_UNREAD_MAIL');
    },

    triggerDeletedMailEvent() {
      EventBus.$emit('deleted-mail-selected', 'DELETED_MAIL');
    },

    triggerSpamMailEvent() {
      EventBus.$emit('spam-mail-selected', 'SPAM_MAIL');
    },

    triggerTraceInformationEvent() {
      EventBus.$emit('trace-information-selected', 'TRACK_INFORMATION');
    },

    triggerDraftsEvent() {
      EventBus.$emit('drafts-selected', 'DRAFTS');
    },

    toggleInbox() {
      this.isInboxOpen = !this.isInboxOpen;
    },

    toggleOutbox() {
      this.isOutboxOpen = !this.isOutboxOpen;
    },

    toggleShowMore() {
      this.isShowMoreOpen = !this.isShowMoreOpen;
    },

    toggleFolder() {
      this.isFolderOpen = !this.isFolderOpen;
    }
  },

  mounted() {
    this.setActive('ALL_RECEIVED');
    this.triggerAllReceivedEvent();  // 触发事件
    this.refreshPullEmailList();
    this.refreshSendEmailList();
    this.refreshFolderList();

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
  },

  beforeDestroy() {
    EventBus.$off('switch-send-success');
    EventBus.$off('switch-index');
    EventBus.$off('switch-write-email');
  },
};
</script>
