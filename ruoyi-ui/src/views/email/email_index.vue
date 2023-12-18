<template>
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
</template>
<script>
import FolderTree from './folder_list_item.vue'
import {listTaskPull, listTaskSend} from "@/api/email/task";
import { listFolder } from "@/api/email/folder";
import { listLabel } from "@/api/email/label";
import { countMenu } from "@/api/email/email";
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
    };
  },
  components: {
    'FolderTree': FolderTree
  },

  methods: {
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

    getTaskCount(taskId) {
      const task = this.menuCount.menuInboxTaskCountList.find(t => t.taskId === taskId);
      return task ? task.count : '';
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
