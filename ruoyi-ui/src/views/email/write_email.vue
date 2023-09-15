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
                                    <button id="report-stat-edit-toolbar-send" @click.prevent="sendEmail" class="mm-button mm-button__primary mm__theme mm__theme-size__medium novice-tour-groupmail-send-button edit-toolbar">
                                      <!---->
                                      <!---->发送
                                      <!---->
                                    </button>
                                  </span>
                                  <!---->
                                </span>
            <span class="mm-tooltip btn-item">
                                  <span class="mm-tooltip-trigger">
                                    <button id="report-stat-edit-toolbar-draft" type="button" @click="saveAsDraft" class="mm-button mm__theme mm__theme-size__medium edit-toolbar">
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
                                    <button id="report-stat-edit-toolbar-close" @click="showCancelPopup = true" type="button" class="mm-button mm__theme mm__theme-size__medium edit-toolbar">
                                      <!---->
                                      <!---->取消
                                      <!---->
                                    </button>
                                  </span>
              <!---->
                                </span>
          </div>
          <el-form ref="elForm" :model="formData" size="medium" label-width="100px">
          <div class="mail-edit-wrapper">
            <div class="mail-edit-content">
              <ul>
                <li data-expose-tour="0" class="sender-row mail-edit-row">
                                      <span class="sender-row-name mail-edit-row-name">发件人
                                        <!---->
                                      </span>
                  <span class="mm__theme mm__theme-border__less mm-select sender-selector show-last-input editor-footer-sender">
                                        <div class="mm-selector--single mm-selector" tabindex="0" @click="toggleSenderDropdown">
                                          <!---->
                                          <div class="mm-selector-rendered">
                                            <div class="sender-selection">
                                              <div class="email-info" style="background: rgb(233, 233, 233); color: rgb(30, 33, 38);">
                                                <div class="mailbox-item ellipsis">
                                                  <!---->
                                                  <div class="public-mail-tag-wrapper ellipsis" slots="[object Object]">
                                                    <div class="public-mail-tag-content">
                                                      <el-dropdown @command="selectEmail" trigger="click">
                                                      <!---->
                                                      <span class="ellipsis">{{selectedAccount}}</span>

                                                       <el-dropdown-menu slot="dropdown">
                                                        <el-dropdown-item v-for="task in taskList" :key="task.id" :command="task">{{ task.account }}</el-dropdown-item>
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
                                        </div>
                                      </span>
                </li>
                <li class="receiver-row mail-edit-row novice-tour-groupmail-select-field">
                  <span class="receiver-row-name mail-edit-row-name active">收件人</span>
                  <ReceiverInput label="收件人" placeholder="请选择收件人或输入收件人邮箱" :initialEmails="receiver" @update="updateEmailList"></ReceiverInput>

                  <div class="cc-bcc-mass-wrapper">
                    <button type="button" @click="toggleCc" class="mm-button mm-button__text mm__theme mm__theme-size__small text-btn">
                      {{ showCc ? '取消抄送' : '抄送' }}
                    </button>
                    <button type="button" @click="toggleBcc" class="mm-button mm-button__text mm__theme mm__theme-size__small text-btn">
                      {{ showBcc ? '取消密送' : '密送' }}
                    </button>
                  </div>
                </li>
                <li class="cc-row mail-edit-row" v-if="showCc">
                  <span class="cc-row-name mail-edit-row-name active">抄送人</span>
                  <ReceiverInput label="抄送人" @update="updateEmailList"></ReceiverInput>
                </li>
                <li class="bcc-row mail-edit-row" v-if="showBcc">
                  <span class="bcc-row-name mail-edit-row-name active">密送</span>
                  <ReceiverInput label="密送人" @update="updateEmailList"></ReceiverInput>
                </li>
                <li data-expose-tour="1" class="subject-row mail-edit-row novice-tour-groupmail-title-field">
                                      <span class="subject-row-name mail-edit-row-name subject">主题
                                        <!---->
                                      </span>
                  <div class="value">
                    <input name="subject" v-model="formData.title" autocomplete="on" class="subject" value="">
                  </div>
                </li>
                <!---->
                <!-- 展示附件 -->
                <li class="add-toolbar-attach">
                  <ul class="component-add-toolbar-attach">
                    <li v-for="file in uploadedFiles" :key="file.name" class="">
                      <span class="okki-icon-wrap attachment-icon" color="#263551">​
                        <!-- SVG icon for attachment here -->
                      </span>
                      <p v-if="!file.isRenaming" class="file-name">
                        <span class="mm-tooltip name">
                          <span class="mm-tooltip-trigger">{{ file.name }}</span>
                        </span>
                        <span :title="file.size" class="size ellipsis"> ({{ formatSize(file.size) }}) </span>
                      </p>

                      <p v-if="file.isRenaming" class="file-name">
                        <span class="mm__theme mm__theme-size__small mm-input file-name-input">
                          <span class="mm-input-affix-wrapper">
                            <input v-model="file.newName" type="text" class="mm-input-inner">
                          </span>
                        </span>
                        <span>.{{ getFileExtension(file.name) }}</span>
                      </p>

                      <div class="btns" v-if="!file.isRenaming">
                        <a>预览</a>
                        <a @click="toggleRename(file)">重命名</a>
                        <a @click="deleteFile(file)">删除</a>
                      </div>
                      <div class="btns" v-if="file.isRenaming">
                        <a @click="saveRename(file)">保存</a>
                        <a @click="cancelRename(file)">取消</a>
                      </div>
                    </li>
                  </ul>
                </li>

                <li class="mail-editor-wrapper">
                  <Toolbar
                    ref="editorInstance"
                    style="border-bottom: 1px solid #ccc"
                    :editor="editor"
                    :defaultConfig="toolbarConfig"
                  />
                  <!-- 编辑器 -->
                  <Editor
                    ref="editorInstance"
                    style="height: 500px; overflow-y: hidden"
                    v-model="formData.content"
                    @onChange="onChange"
                    @onCreated="onCreated"
                    mode="default"
                  />
                  <div class="component-add-toolbar">
                    <div class="mail-tool flex items-center">
                      <!---->
                      <span class="mm-tooltip tool-item flex items-center" @change="handleFileUpload">
                          <span class="mm-tooltip-trigger">
                              <div class="tool-item flex items-center" title="">
                                  <img src="https://cdn.xiaoman.cn/crm_web/ks/prod/mail_subapp/static/img/appendix.f49c01fe.svg" style="width: 14px; height: 14px; margin-right: 4px;">
                                  <span @click="triggerFileInput">附件</span>
                                  <input id="attachInput" type="file" multiple ref="fileInput" style="display:none;">
                              </div>
                          </span>
                      </span>
<!--                      <div class="tool-item flex items-center">
                        <img data-savepage-src="https://cdn.xiaoman.cn/crm_web/ks/prod/mail_subapp/static/img/cloud.bd4a8ece.svg" src="" style="width: 14px; height: 14px; margin-right: 4px;"> 云文档</div>
                      <div class="mm-dropdown hover:bg-gray-200 w-8 h-7 flex items-center justify-center mail-toolbar-dropdown">
                        <div class="mm-dropdown-trigger">
                          <div class="cursor-pointer flex items-center justify-center w-full h-full">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
                              <path fill-rule="evenodd" clip-rule="evenodd" d="M12 15.5a1 1 0 00.707-.293l6-6a1 1 0 00-1.414-1.414L12 13.086 6.707 7.793a1 1 0 00-1.414 1.414l6 6A1 1 0 0012 15.5z"></path>
                            </svg>
                          </div>
                        </div>
                        &lt;!&ndash;&ndash;&gt;
                      </div>
                      <div class="mm-divider-vertical solid"></div>
                      &lt;!&ndash;&ndash;&gt;
                      <div class="tool-item novice-tour-groupmail-content-field flex items-center">
                        <img data-savepage-src="https://cdn.xiaoman.cn/crm_web/ks/prod/mail_subapp/static/img/template.b98c6551.svg" src="" style="width: 14px; height: 14px; margin-right: 4px;"> 模板</div>
                      &lt;!&ndash;&ndash;&gt;
                      <div class="tool-item flex items-center">
                        <img data-savepage-src="https://cdn.xiaoman.cn/crm_web/ks/prod/mail_subapp/static/img/image.fb78e8ce.svg" src="" style="width: 14px; height: 14px; margin-right: 4px;">
                        <span>图片</span>
                        <input title="未选择任何文件" type="file" multiple="" value="">
                      </div>
                      <div class="tool-item flex items-center">
                        <img data-savepage-src="https://cdn.xiaoman.cn/crm_web/ks/prod/mail_subapp/static/img/fast-text.573b9be5.svg" src="" style="width: 14px; height: 14px; margin-right: 4px;"> 快速文本</div>
                      <div class="mm-divider-vertical solid"></div>
                      <div class="tool-item flex items-center">
                        <img data-savepage-src="https://cdn.xiaoman.cn/crm_web/ks/prod/mail_subapp/static/img/product.db4fd779.svg" src="" style="width: 14px; height: 14px; margin-right: 4px;"> 产品</div>
                      <div class="mm-dropdown hover:bg-gray-200 h-7 flex items-center justify-center mail-toolbar-dropdown">
                        <div class="mm-dropdown-trigger">
                          <div class="cursor-pointer text-xs text-black flex items-center">
                            <svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" spin="false" rtl="false" viewBox="0 0 48 48" preserveAspectRatio="xMidYMid meet" fill="none" role="presentation" data-v-d394b89c="" style="vertical-align: middle;">
                              <g data-v-d394b89c="">
                                <path stroke-linejoin="round" stroke-linecap="round" stroke-width="4" d="M10 6a2 2 0 0 1 2-2h24a2 2 0 0 1 2 2v38l-7-5-7 5-7-5-7 5V6Zm8 16h12m-12 8h12M18 14h12" data-follow-stroke="currentColor" data-v-d394b89c="" stroke="currentColor"></path>
                              </g>
                            </svg> 单据
                          </div>
                        </div>
                        &lt;!&ndash;&ndash;&gt;
                      </div>
                      <div class="mm-dropdown hover:bg-gray-200 h-7 flex items-center justify-center mail-toolbar-dropdown">
                        <div class="mm-dropdown-trigger">
                          <div class="cursor-pointer text-xs text-black paypal-tool flex items-center">
                            <svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" spin="false" rtl="false" viewBox="0 0 48 48" preserveAspectRatio="xMidYMid meet" fill="none" role="presentation" data-v-64bcfa07="" style="vertical-align: middle;">
                              <g data-v-64bcfa07="">
                                <path stroke-linejoin="round" stroke-linecap="round" stroke-width="4" d="M31 34h12m0-8V10a3 3 0 0 0-3-3H8a3 3 0 0 0-3 3v28a3 3 0 0 0 3 3h20.47" data-follow-stroke="currentColor" stroke="#2a2a2a" data-v-64bcfa07=""></path>
                                <path stroke-linejoin="round" stroke-linecap="round" stroke-width="4" d="m36 39-5-5 5-5M15 15l5 6 5-6M14 27h12m-12-6h12m-6 0v12" data-follow-stroke="currentColor" stroke="#2a2a2a" data-v-64bcfa07=""></path>
                              </g>
                            </svg> 收款
                          </div>
                        </div>
                        &lt;!&ndash;&ndash;&gt;
                      </div>-->
                    </div>
<!--                    <span class="mail-size">邮件大小：506 B</span>-->
                  </div>
                </li>
              </ul>
              <!---->
              <div class="editor-footer">

<!--                <div>
                  <span class="select-label">签名：</span>
                  <span class="mm-select mail-sign-selector novice-tour-set-signature-button" style="width: 178px;">
                                        <div class="mm-selector&#45;&#45;single mm-selector" tabindex="0">
                                          &lt;!&ndash;&ndash;&gt;
                                          <div class="mm-selector-rendered">
                                            <span title="不使用" class="mm-selector-selected-value" style="opacity: 1;">不使用</span>
                                            &lt;!&ndash;&ndash;&gt;
                                          </div>
                                          &lt;!&ndash;&ndash;&gt;
                                          <span class="mm-selector-suffix-icon">
                                            <svg class="mm-icon mm-icon-chevrondown mm-selector-arrow" viewBox="0 0 24 24" name="chevrondown" fill="currentColor" style="height: 12px; width: 12px; outline: none;">
                                              <path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
                                            </svg>
                                          </span>
                                        </div>
                    &lt;!&ndash;&ndash;&gt;
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
                </div>-->
                <div class="split"></div>
                <div class="select">
                  <label class="mm-checkbox">
                    <input true-value="true" v-model="formData.traceFlag" type="checkbox" checked="">
                    <span class="mm-checkbox-input"></span>
                    <span class="mm-checkbox-label">追踪邮件</span>
                  </label>
                </div>
                <div class="select">
              <label class="mm-checkbox">
                <input true-value="true" v-model="formData.delayedTxFlag" type="checkbox">
                <span class="mm-checkbox-input"></span>
                <span class="mm-checkbox-label">定时发送</span>
              </label>
            </div>
              <div class="split"></div>
              <div class="select-handle-time" @click="togglePendingTime">
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
                <PendingTimePopover v-if="showPendingTime" @showCustom="showCustomTimeComponent"></PendingTimePopover>
<!--                <CustomTimePopover v-if="showCustomTime"></CustomTimePopover>-->
              </div>
            </div>
            </div>
          </div>
          </el-form>
        </div>
      </form>
      <template>
        <div class="mm-modal--mask mm-modal edit-close-dialog" id="report-stat-mail-edit" style="z-index: 1000;" v-portal="" v-portal-fixed="" v-if="showCancelPopup">
          <div class="mm-modal-mask"></div>
          <div class="mm-modal-wrapper" style="padding-top: 15vh;">
            <div class="mm-modal-content" style="width: 424px; border-color: transparent;">
              <svg class="mm-icon mm-icon-close mm-modal-close" @click="showCancelPopup = false" viewBox="0 0 24 24" name="close" fill="currentColor" style="height: 14px; width: 14px;">
                <path d="M14.3 11.7l6-6c.3-.3.3-.7 0-1l-.9-1c-.3-.2-.7-.2-1 0l-6 6.1c-.2.2-.5.2-.7 0l-6-6.1c-.3-.3-.7-.3-1 0l-1 1c-.2.2-.2.7 0 .9l6.1 6.1c.2.2.2.4 0 .6l-6.1 6.1c-.3.3-.3.7 0 1l1 1c.2.2.7.2.9 0l6.1-6.1c.2-.2.4-.2.6 0l6.1 6.1c.2.2.7.2.9 0l1-1c.3-.3.3-.7 0-1l-6-6c-.2-.2-.2-.5 0-.7z"></path>
              </svg>
              <div class="mm-modal-header">
                <div class="mm-modal-title">
                  <span>离开确认</span>
                </div>
              </div>
              <div class="mm-modal-body">
                <h3 class="close-confirm-title">将此邮件保存为草稿吗？</h3>
                <div class="content">
                  <p>如果不保存为草稿，编辑的内容将会丢失</p>
                </div>
              </div>
              <div class="mm-modal-footer">
                <div class="footer">
                  <button type="button" @click="showCancelPopup = false" class="mm-button">
                    <!---->
                    <!---->
                    <span>取消</span>
                    <!---->
                  </button>
                  <button type="button" @click="noSavePopup" class="mm-button">
                    <!---->
                    <!---->
                    <span>不保存</span>
                    <!---->
                  </button>
                  <button type="button" @click="saveAsDraftPopup" class="mm-button mm-button__primary">
                    <!---->
                    <!---->
                    <span>存草稿</span>
                    <!---->
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </template>

    </div>
</template>
<style lang="scss">
.dropdown-email-list {
  z-index: 1000;
  position: absolute;
}

.mm-outside.mail-pending-popover {
  position: absolute;
  will-change: top, left;
  transform-origin: 100% bottom;
  top: -225px;
  left: 75px;
}

@import '../../static/scss/email/index/92270.59786a6e.css';
@import '../../static/scss/email/index/43960.b19a070c.css';
@import '../../static/scss/email/index/64147.480300b1.css';
@import '../../static/scss/email/index/86683.ec9c38a2.css';
@import '../../static/scss/email/index/skin.min.css';
@import '../../static/scss/email/index/48517.0d1db0ea.css';
</style>

<style src="@wangeditor/editor/dist/css/style.css"></style>


<script>
import { Editor, Toolbar } from "@wangeditor/editor-for-vue";
import {uploadAttachments, listAttachment, renameAttachment, deleteAttachment} from "@/api/email/attachment";
import {listTaskPull} from "@/api/email/task";
import {saveSendEmail, sendEmail} from "@/api/email/email";
import { EventBus } from '@/api/email/event-bus.js';
import ReceiverInput from './write_email_receiver_input.vue';
import PendingTimePopover from './pending_time.vue';
import CustomTimePopover from './custom_time.vue';
export default {
  components: { Editor, Toolbar, ReceiverInput, PendingTimePopover, CustomTimePopover },
  data() {
    return {
      formData: {},
      taskList: [],
      isDropdownVisible: false,
      selectedAccount: '',
      showCc: false,
      showBcc: false,
      selectedFiles: [],
      uploadedFiles: [],
      taskId: null,
      attachmentIds: [],
      editor: null,
      toolbarConfig: {},
      showCancelPopup: false,
      emailData: {
        '收件人': [],
        '抄送人': [],
        '密送人': []
      },
      receiver: [],
      cc: [],
      bcc: [],
      showCustomTime: true,
      showPendingTime: false,
      messageId: null,
      htmlText: '',
    };
  },
  props: {
    selectedEmail: {
      type: Object,
      default: null
    }
  },
  methods: {
    triggerFileInput() {
      this.$refs.fileInput.click();
    },

    async toggleSenderDropdown() {
      this.isDropdownVisible = !this.isDropdownVisible;
    },

    async fetchTaskList() {
      try {
        const response = await listTaskPull();
        this.taskList = response.rows;

        // 设置默认的邮件地址为taskList中的第一个邮件地址
        if (!this.selectedAccount && this.taskList.length > 0) {
          this.selectedAccount = this.taskList[0].account;
          this.taskId = this.taskList[0].id;
        }
        return response;
      } catch (error) {
        console.error('Failed to fetch emails:', error);
      }
    },

    selectEmail(task) {
      this.selectedAccount = task.account;
      this.taskId = task.id;
      this.isDropdownVisible = false;
    },

    toggleCc() {
      this.showCc = !this.showCc;
    },

    toggleBcc() {
      this.showBcc = !this.showBcc;
    },

    onCreated(editor) {
      this.editor = Object.seal(editor); // 【注意】一定要用 Object.seal() 否则会报错
      setTimeout(() => {
        this.editor.dangerouslyInsertHtml(this.htmlText);

        // 设置光标到内容开头
        const range = this.editor.selection.getRange();
        range.setStart(this.editor.txt.$textElem[0], 0);
        range.collapse(true);
        this.editor.selection.restoreSelection();
      }, 500);
    },


    onChange(editor) {
      //console.log("onChange", editor.getHtml()); // onChange 时获取编辑器最新内容
    },

    async handleFileUpload(event) {
      const selectedFiles = [...event.target.files];
      const formData = new FormData();

      selectedFiles.forEach(file => {
        formData.append('files', file);
      });

      try {
        const response = await uploadAttachments(formData);
        if (response.code === 200) {
          this.uploadedFiles.push(...response.data);

          const ids = response.data.map(file => file.id);
          this.attachmentIds.push(...ids);

          this.$refs.fileInput.value = null;
        } else {
          console.error('上传失败');
        }
      } catch (error) {
        console.error('上传过程中出现错误:', error);
      }
    },

    formatSize(size) {
      return size + ' B';
    },

    async saveSendEmail() {
      // 格式化收件人、抄送人、和密送人
      let receiver;
      let cc;
      let bcc;
      if (this.receiver != null && this.receiver != undefined && this.receiver != '') {
        receiver = JSON.stringify(this.formatEmails(this.receiver));
      }
      if (this.cc != null && this.cc != undefined && this.cc != '') {
        cc = JSON.stringify(this.formatEmails(this.cc));
      }
      if (this.bcc != null && this.bcc != undefined && this.bcc != '') {
        bcc = JSON.stringify(this.formatEmails(this.bcc));
      }

      this.formData.taskId = this.taskId;
      this.formData.fromer = this.selectedAccount;

      const data = {
        "taskId": this.taskId,
        "fromer": this.selectedAccount,
        "receiver": receiver,
        "cc": cc,
        "bcc": bcc,
        "title": this.formData.title,
        "content": this.formData.content,
        "attachmentIdList": this.attachmentIds,
        "delayedTxFlag": this.formData.delayedTxFlag,
        "traceFlag": this.formData.traceFlag,
        "pullEmailId": this.formData.id,
      }

      try {
        const response = await saveSendEmail(data);
        if (response.code === 200) {
          return response; // 返回整个响应对象
        } else {
          throw new Error('上传失败');
        }
      } catch (error) {
        console.error('上传过程中出现错误:', error);
        throw error;
      }
    },

    async sendEmail() {
      // 校验发件人、收件人、主题和内容是否为空
      if (!this.receiver) {
        this.$message.error("收件人不能为空");
        return;
      }
      if (!this.formData.title) {
        this.$message.error("主题不能为空");
        return;
      }
      if (!this.formData.content || this.formData.content.trim() === "") {
        this.$message.error("邮件内容不能为空");
        return;
      }

      try {
        const response = await this.saveSendEmail();
        if (response.code === 200) {
           const id = response.data;
           console.log("id = " + id);

           const data = {
             "id": id
           };
           const sendResponse = await sendEmail(data);
          if (sendResponse.code === 200) {
            EventBus.$emit('switch-send-success');  // 发出事件
            return response;
          } else {
            console.error('发送失败');
            this.$message.success('发送失败');
          }
        }
      } catch (error) {
        console.error("保存邮件过程中出现错误:", error);
      }
    },

    formatEmails(email) {
      if (!email) return [];
      return email.map(e => {
        e = e.trim();
        return {
          email: e,
          name: e
        };
      });
    },

    // 存草稿
    async saveAsDraft() {
      const response = await this.saveSendEmail();
      if (response.code === 200) {
         this.$message.success("邮件保存成功");
      }
    },

    // 弹窗-存草稿
    async saveAsDraftPopup() {
      const response = await this.saveSendEmail();
      if (response.code === 200) {
        EventBus.$emit('switch-index');  // 发出事件
      } else {
        this.$message.error("执行失败");
      }
    },

    // 弹窗-不保存
    noSavePopup() {
      EventBus.$emit('switch-index');  // 发出事件
    },

    updateEmailList({ label, emails }) {
      if (label === '收件人') {
        this.receiver = emails;
      } else if (label === '抄送人') {
        this.cc = emails;
      } else if (label === '密送人') {
        this.bcc = emails;
      }
    },

    toggleRename(file) {
      this.$set(file, 'isRenaming', true);
      this.$set(file, 'newName', this.getFileNameWithoutExtension(file.name));
    },

    async saveRename(file) {
      console.log(file.newName);
      if (!file.newName || file.newName.trim() === '') {
        this.$message.error("附件名称不能为空");
        return;
      }

      const data = {
        "id": file.id,
        "name": file.newName
      };
      try {
        const response = await renameAttachment(data);
        if (response.code !== 200) {
          this.$message.error("执行失败");
          return;
        }
      } catch (error) {
        console.error('重命名过程中出现错误:', error);
        throw error;
      }

      this.$set(file, 'name', `${file.newName}.${this.getFileExtension(file.name)}`);
      this.$set(file, 'isRenaming', false);
    },

    async deleteFile(file) {
      const index = this.uploadedFiles.indexOf(file);
      if (index > -1) {
        try {
          const data = {
            "id": file.id
          };
          const response = await deleteAttachment(data);
          if (response.code !== 200) {
            this.$message.error("执行失败");
            return;
          }
        } catch (error) {
          console.error('删除过程中出现错误:', error);
          throw error;
        }
        this.uploadedFiles.splice(index, 1);
      }
    },

    cancelRename(file) {
      file.isRenaming = false;
      file.newName = '';
    },
    getFileNameWithoutExtension(filename) {
      return filename.replace(/\.[^/.]+$/, "");
    },
    getFileExtension(filename) {
      return filename.split('.').pop();
    },

    togglePendingTime() {
      this.showPendingTime = !this.showPendingTime;
      this.showCustomTime = false;
    },
    showCustomTimeComponent() {
      this.showCustomTime = true;
      this.showPendingTime = false;
    },
    formattedEmailContent() {
      let emailContent = `
      <div style="font-size: 12px;background:#efefef;padding:8px;">
        <div><b>From: </b>&nbsp;<a href="mailto:${this.selectedEmail.fromer}" style="color: #1e7bf9; text-decoration: none;" target="_blank">${this.selectedEmail.fromer}</a></div>
        <div><b>Send time: </b>&nbsp;${this.formatDate(this.selectedEmail.sendDate)}</div>
        <div>${this.formatEmailRecipients('To', this.parseEmailString(this.selectedEmail.receiver))}</div>
    `;

      if (this.selectedEmail.cc.length) {
        emailContent += `<div>${this.formatEmailRecipients('Cc', this.parseEmailString(this.selectedEmail.cc))}</div>`;
      }

      emailContent += `
        <div><b>Subject: </b>&nbsp;${this.selectedEmail.title}</div>
      </div>
    `;

      return emailContent;
    },

    formatEmailRecipients(label, emails) {
      return emails.map(email => `
      <b>${label}: </b>&nbsp;<a href="mailto:${email}" style="color: #1e7bf9; text-decoration: none;" target="_blank">${email}</a>
    `).join('; ');
    },

    parseEmailString(emailString) {
      try {
        const emailObjects = JSON.parse(emailString);
        return emailObjects.map(obj => obj.email);
      } catch (error) {
        console.error("Error parsing email string:", error);
        return [];
      }
    },
    formatDate(dateString) {
      const days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
      const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

      const date = new Date(dateString);

      const dayName = days[date.getDay()];
      const monthName = months[date.getMonth()];
      const dayOfMonth = date.getDate();
      const year = date.getFullYear();

      // Format time as 12-hour clock with AM/PM
      let hours = date.getHours();
      const ampm = hours >= 12 ? 'PM' : 'AM';
      hours = hours % 12;
      hours = hours ? hours : 12; // the hour '0' should be '12'
      const minutes = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes();

      return `${dayName}, ${monthName} ${dayOfMonth}, ${year} ${hours}:${minutes} ${ampm}`;
    }
  },
  mounted() {
    this.fetchTaskList().then(() => {
      // 在这里，fetchTaskList 已经完成
      // 所以你可以对列表进行遍历和匹配
      const foundTask = this.taskList.find(task => task.id === this.selectedEmail.taskId);
      if (foundTask) {
        this.selectedAccount = foundTask.account;
      }
    }).catch(error => {
      console.error("Error while fetching task list:", error);
    });
  },

  beforeDestroy() {
    const editor = this.editor;
    if (editor == null) return;
    editor.destroy();
  },

  created() {
    this.formData.title = "Re: " + this.selectedEmail.title;
    this.formData.id = this.selectedEmail.id;
    this.receiver.push(this.selectedEmail.fromer);
    let original = "<br/><div style=\"font-size: 12px;font-family: Arial Narrow,serif;padding:2px 0 2px 0;\">------------------&nbsp;Original&nbsp;------------------</div>";
    this.htmlText = original + this.formattedEmailContent() + this.selectedEmail.content;
  }
};
</script>

