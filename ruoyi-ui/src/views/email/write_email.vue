<template>
  <div class="mm-split-pane mm-split-pane__right" style="left: 12.2835%;">
    <form class="mail-edit-container layout-extraSidebar-content" mail-type="" folder-id="">
      <div class="mm-spinner" style="display: none;">
        <div class="mm-spinner-mask"></div>
        <div class="mm-spinner-wrapper">
          <div class="mm-loading" style="height: 40px; width: 40px; color: rgb(38, 132, 255);">
            <svg class="circular" viewBox="25 25 50 50">
              <circle stroke-width="2" class="path" cx="50" cy="50" r="20" fill="none" stroke-miterlimit="10"></circle>
            </svg>
          </div>
          <!---->
        </div>
      </div>
      <div class="toolbar-editor-wrapper">
        <div class="edit-toolbar-wrapper">
															<span class="mm-tooltip btn-item">
																<span class="mm-tooltip-trigger">
																	<button id="report-stat-edit-toolbar-send" type="submit" class="mm-button mm-button__primary mm__theme mm__theme-size__medium novice-tour-groupmail-send-button edit-toolbar">
																		<!---->
                                    <!---->发送
                                    <!---->
																	</button>
																</span>
                                <!---->
															</span>
          <span class="mm-tooltip btn-item">
																<span class="mm-tooltip-trigger">
																	<button id="report-stat-edit-toolbar-draft" type="button" class="mm-button mm__theme mm__theme-size__medium edit-toolbar">
																		<!---->
                                    <!---->存草稿
                                    <!---->
																	</button>
																</span>
            <!---->
															</span>
          <span class="mm-tooltip btn-item">
																<span class="mm-tooltip-trigger">
																	<button id="report-stat-edit-toolbar-review" type="button" class="mm-button mm__theme mm__theme-size__medium edit-toolbar">
																		<!---->
                                    <!---->预览
                                    <!---->
																	</button>
																</span>
            <!---->
															</span>
          <span class="mm-tooltip btn-item">
																<span class="mm-tooltip-trigger">
																	<button id="report-stat-edit-toolbar-approval" type="button" class="mm-button mm__theme mm__theme-size__medium edit-toolbar">
																		<!---->
                                    <!---->提交审批
                                    <!---->
																	</button>
																</span>
            <!---->
															</span>
          <span class="mm-tooltip btn-item">
																<span class="mm-tooltip-trigger">
																	<button id="report-stat-edit-toolbar-close" type="button" class="mm-button mm__theme mm__theme-size__medium edit-toolbar">
																		<!---->
                                    <!---->取消
                                    <!---->
																	</button>
																</span>
            <!---->
															</span>
        </div>
        <div class="mail-edit-wrapper">
          <div class="mail-edit-content">
            <ul>
              <li data-expose-tour="0" class="sender-row mail-edit-row">
																		<span class="sender-row-name mail-edit-row-name">发件人
                                      <!---->
																		</span>
                <span class="mm__theme mm__theme-border__less mm-select sender-selector show-last-input editor-footer-sender">
																			<div class="mm-selector--single mm-selector" tabindex="0">
																				<!---->
																				<div class="mm-selector-rendered">
																					<div class="sender-selection" @click="toggleSenderDropdown">
																						<div class="email-info" style="background: rgb(233, 233, 233); color: rgb(30, 33, 38);">
																							<div class="mailbox-item ellipsis">
																								<!---->
																								<div class="public-mail-tag-wrapper ellipsis" slots="[object Object]">
																									<div class="public-mail-tag-content">
                                                    <el-dropdown @command="selectEmail" trigger="click">
																										<!---->
																										<span class="ellipsis">{{selectedEmail}}</span>

                                                     <el-dropdown-menu slot="dropdown">
                                                      <el-dropdown-item v-for="task in taskList" :key="task.id" :command="task.account">{{ task.account }}</el-dropdown-item>
                                                    </el-dropdown-menu>

                                                    </el-dropdown>
																									</div>
																								</div>
																							</div>
																							<span class="m-icon icon-cross-thin" style="color: rgb(157, 157, 157);"></span>
																						</div>
																					</div>
																					<div class="total-email-info" style="background: rgb(233, 233, 233); display: none;">
																						<span>共 1 个</span>
																					</div>
																				</div>
                                        <!---->
																				<span class="mm-selector-suffix-icon">
																					<svg class="mm-icon mm-icon-chevrondown mm-selector-arrow" viewBox="0 0 24 24" name="chevrondown" fill="currentColor" style="height: 12px; width: 12px; outline: none;">
																						<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																					</svg>
																				</span>
																			</div>
																		</span>
              </li>
              <li class="receiver-row mail-edit-row novice-tour-groupmail-select-field">
                <span class="receiver-row-name mail-edit-row-name active">收件人</span>
                <div class="value">
                  <div class="component-email-input clearfix receiver">
                    <div class="email-wrap show-last-input">
                      <div class="email-input last">
                        <input type="text" placeholder="请选择收件人或输入收件人邮箱" class="" data-listener-added_3b8984f2="true" value="">
                      </div>
                    </div>
                    <div class="email-item total-email-info" style="display: none;">
                      <span class="email-info">共 0 个</span>
                    </div>
                    <!---->
                  </div>
                </div>
                <div class="cc-bcc-mass-wrapper">
                  <button type="button" @click="toggleCc" class="mm-button mm-button__text mm__theme mm__theme-size__small text-btn">
                    {{ showCc ? '取消抄送' : '抄送' }}
                  </button>
                  <button type="button" @click="toggleBcc" class="mm-button mm-button__text mm__theme mm__theme-size__small text-btn">
                    {{ showBcc ? '取消密送' : '密送' }}
                  </button>
                  <span class="mm-tooltip">
																				<span class="mm-tooltip-trigger">
																					<button type="button" class="mm-button mm-button__text mm__theme mm__theme-size__small text-btn novice-tour-add-groupmail-button">
																						<!---->
                                            <!---->群发单显
                                            <!---->
																					</button>
																				</span>
                    <!---->
																			</span>
                </div>
              </li>
              <!---->
              <li class="cc-row mail-edit-row" v-if="showCc">
                <span class="cc-row-name mail-edit-row-name active">抄送人</span>
                <div class="value">
                  <div class="component-email-input clearfix copy">
                    <div class="email-wrap show-last-input">
                      <div class="email-input last">
                        <input type="text" placeholder="" class="" data-listener-added_3b8984f2="true" value="">
                      </div>
                    </div>
                    <div class="email-item total-email-info" style="display: none;">
                      <span class="email-info">共 0 个</span>
                    </div>
                    <!---->
                  </div>
                </div>
<!--                <span class="okki-icon-wrap add-icon">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																				<path fill-rule="evenodd" clip-rule="evenodd" d="M12 2a10 10 0 100 20 10 10 0 000-20zm0 18a8 8 0 110-16.001A8 8 0 0112 20z"></path>
																				<path d="M13 8a1 1 0 10-2 0v3H8a1 1 0 100 2h3v3a1 1 0 102 0v-3h3a1 1 0 100-2h-3V8z"></path>
																			</svg>
																		</span>-->
              </li>
              <li class="bcc-row mail-edit-row" v-if="showBcc">
                <span class="bcc-row-name mail-edit-row-name active">密送</span>
                <div class="value">
                  <div class="component-email-input clearfix secret">
                    <div class="email-wrap show-last-input">
                      <div class="email-input last">
                        <input type="text" placeholder="" class="" value="">
                      </div>
                    </div>
                    <div class="email-item total-email-info" style="display: none;">
                      <span class="email-info">共 0 个</span>
                    </div>
                    <!---->
                  </div>
                </div>
<!--                <span class="okki-icon-wrap add-icon">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																				<path fill-rule="evenodd" clip-rule="evenodd" d="M12 2a10 10 0 100 20 10 10 0 000-20zm0 18a8 8 0 110-16.001A8 8 0 0112 20z"></path>
																				<path d="M13 8a1 1 0 10-2 0v3H8a1 1 0 100 2h3v3a1 1 0 102 0v-3h3a1 1 0 100-2h-3V8z"></path>
																			</svg>
																		</span>-->
              </li>
              <!---->
<!--              <li class="receiver-number-row">
                <div class="tips" style="color: rgb(144, 144, 144);">0 /100 收件人、抄送人和密送的联系人总数不可超过100</div>
              </li>-->
              <!---->
              <li data-expose-tour="1" class="subject-row mail-edit-row novice-tour-groupmail-title-field">
																		<span class="subject-row-name mail-edit-row-name subject">主题
                                      <!---->
																		</span>
                <div class="value">
                  <input name="subject" autocomplete="on" class="subject" value="">
                </div>
<!--                <div>
                  <button type="button" class="mm-button mm-button__text mm__theme mm__theme-size__small text-btn meeting-mail-btn">
                    &lt;!&ndash;&ndash;&gt;
                    &lt;!&ndash;&ndash;&gt;会议邀约
                    &lt;!&ndash;&ndash;&gt;
                  </button>
                </div>-->
              </li>
              <!---->
              <li class="add-toolbar-attach">
                <ul class="component-add-toolbar-attach">
                  <!---->
                </ul>
              </li>
            </ul>
            <!---->
            <div class="editor-footer">
              <div>
                <span class="select-label">签名：</span>
                <span class="mm-select mail-sign-selector novice-tour-set-signature-button" style="width: 178px;">
																			<div class="mm-selector--single mm-selector" tabindex="0">
																				<!---->
																				<div class="mm-selector-rendered">
																					<span title="不使用" class="mm-selector-selected-value" style="opacity: 1;">不使用</span>
                                          <!---->
																				</div>
                                        <!---->
																				<span class="mm-selector-suffix-icon">
																					<svg class="mm-icon mm-icon-chevrondown mm-selector-arrow" viewBox="0 0 24 24" name="chevrondown" fill="currentColor" style="height: 12px; width: 12px; outline: none;">
																						<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																					</svg>
																				</span>
																			</div>
                  <!---->
																		</span>
              </div>
              <div class="mx-4 divier"></div>
              <div class="select">
                <label class="mm-checkbox">
                  <input true-value="true" type="checkbox">
                  <span class="mm-checkbox-input"></span>
                  <span class="mm-checkbox-label">紧急</span>
                </label>
              </div>
              <div class="select">
                <label class="mm-checkbox">
                  <input true-value="true" type="checkbox">
                  <span class="mm-checkbox-input"></span>
                  <span class="mm-checkbox-label">回执</span>
                </label>
              </div>
              <div class="split"></div>
              <div class="select">
                <label class="mm-checkbox">
                  <input true-value="true" type="checkbox" checked="">
                  <span class="mm-checkbox-input"></span>
                  <span class="mm-checkbox-label">追踪邮件</span>
                </label>
              </div>
              <div class="select">
                <label class="mm-checkbox">
                  <input true-value="true" type="checkbox">
                  <span class="mm-checkbox-input"></span>
                  <span class="mm-checkbox-label">定时发送</span>
                </label>
              </div>
              <div class="split"></div>
              <div class="select-handle-time">
                <div class="mm-popover">
                  <div>
																				<span class="time">
																					<span class="okki-icon-wrap time-icon">​<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																							<path d="M12 6a1 1 0 011 1v4.423l2.964 1.711a1 1 0 11-1 1.732l-3.447-1.99A1 1 0 0111 11.98V7a1 1 0 011-1z"></path>
																							<path fill-rule="evenodd" clip-rule="evenodd" d="M22 12c0 5.523-4.477 10-10 10S2 17.523 2 12 6.477 2 12 2s10 4.477 10 10zm-2 0a8 8 0 11-16 0 8 8 0 0116 0z"></path>
																						</svg>
																					</span> 标记为待处理
																				</span>
                  </div>
                  <!---->
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </form>
  </div>
</template>
<style lang="scss">
.dropdown-email-list {
  z-index: 1000; // 使用一个较高的值确保它在最上层
  position: absolute; // 确保它能够悬浮在页面上
}

@import '../../static/scss/email/index/92270.59786a6e.css';
@import '../../static/scss/email/index/43960.b19a070c.css';
@import '../../static/scss/email/index/64147.480300b1.css';
@import '../../static/scss/email/index/86683.ec9c38a2.css';
@import '../../static/scss/email/index/skin.min.css';
@import '../../static/scss/email/index/48517.0d1db0ea.css';
</style>

<script>
import {listTaskPull} from "@/api/email/task";
export default {
  data() {
    return {
      taskList: [],
      isDropdownVisible: false,
      selectedEmail: '',
      showCc: true,
      showBcc: true
    };
  },
  methods: {
    async toggleSenderDropdown() {
      this.isDropdownVisible = !this.isDropdownVisible;
    },

    async fetchTaskList() {
      try {
        listTaskPull().then((response) => {
          this.taskList = response.rows;
          // 设置默认的邮件地址为taskList中的第一个邮件地址
          if (!this.selectedEmail && this.taskList.length > 0) {
            this.selectedEmail = this.taskList[0].account;
          }
        });
      } catch (error) {
        console.error('Failed to fetch emails:', error);
      }
    },

    // 新增的选择邮件地址的方法
    selectEmail(email) {
      this.selectedEmail = email;
      this.isDropdownVisible = false;
    },

    toggleCc() {
      this.showCc = !this.showCc;
    },

    toggleBcc() {
      this.showBcc = !this.showBcc;
    }
  },
  mounted() {
    this.fetchTaskList();
  },

};
</script>

