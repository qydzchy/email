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
              <button id="report-stat-edit-toolbar-send" @click.prevent="sendEmail"
                class="mm-button mm-button__primary mm__theme mm__theme-size__medium novice-tour-groupmail-send-button edit-toolbar">
                <!---->
                <!---->发送
                <!---->
              </button>
            </span>
            <!---->
          </span>
          <span class="mm-tooltip btn-item">
            <span class="mm-tooltip-trigger">
              <button id="report-stat-edit-toolbar-draft" type="button" @click="saveAsDraft"
                class="mm-button mm__theme mm__theme-size__medium edit-toolbar">
                <!---->
                <!---->存草稿
                <!---->
              </button>
            </span>
            <!---->
          </span>
          <span class="mm-tooltip btn-item">
            <span class="mm-tooltip-trigger">
              <button id="report-stat-edit-toolbar-review" type="button"
                class="mm-button mm__theme mm__theme-size__medium edit-toolbar">
                <!---->
                <!---->预览
                <!---->
              </button>
            </span>
            <!---->
          </span>
          <span class="mm-tooltip btn-item">
            <span class="mm-tooltip-trigger">
              <button id="report-stat-edit-toolbar-close" @click="showCancelPopup = true" type="button"
                class="mm-button mm__theme mm__theme-size__medium edit-toolbar">
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
                  <span
                    class="mm__theme mm__theme-border__less mm-select sender-selector show-last-input editor-footer-sender">
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
                                    <span class="ellipsis">{{ selectedAccount }}</span>

                                    <el-dropdown-menu slot="dropdown">
                                      <el-dropdown-item v-for="task in taskList" :key="task.id" :command="task">{{
                                        task.account }}</el-dropdown-item>
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
                  <ReceiverInput label="收件人" placeholder="请选择收件人或输入收件人邮箱" :initialEmails="receiver"
                    @update="updateEmailList"></ReceiverInput>

                  <div class="cc-bcc-mass-wrapper">
                    <button type="button" @click="toggleCc"
                      class="mm-button mm-button__text mm__theme mm__theme-size__small text-btn">
                      {{ showCc ? '取消抄送' : '抄送' }}
                    </button>
                    <button type="button" @click="toggleBcc"
                      class="mm-button mm-button__text mm__theme mm__theme-size__small text-btn">
                      {{ showBcc ? '取消密送' : '密送' }}
                    </button>
                  </div>
                </li>
                <li class="cc-row mail-edit-row" v-if="showCc">
                  <span class="cc-row-name mail-edit-row-name active">抄送人</span>
                  <ReceiverInput :initialEmails="cc" label="抄送人" @update="updateEmailList"></ReceiverInput>
                </li>
                <li class="bcc-row mail-edit-row" v-if="showBcc">
                  <span class="bcc-row-name mail-edit-row-name active">密送</span>
                  <ReceiverInput :initialEmails="bcc" label="密送人" @update="updateEmailList"></ReceiverInput>
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

                <li class="editor-wrapper">
                  <Toolbar ref="editorInstance" style="border-bottom: 1px solid #ccc" :editor="editor"
                    :defaultConfig="toolbarConfig" />
                  <!-- 编辑器 -->
                  <Editor ref="editorInstanceEdit" style="height: 500px; overflow-y: hidden" v-model="formData.content"
                    :defaultContent="defaultContent" @onChange="onChange" @onCreated="onCreated" mode="simple" />
                  <div class="component-add-toolbar">
                    <div class="mail-tool flex items-center">
                      <!---->
                      <span class="mm-tooltip tool-item flex items-center" @change="handleFileUpload">
                        <span class="mm-tooltip-trigger">
                          <div class="tool-item flex items-center" title="">
                            <img src="https://cdn.xiaoman.cn/crm_web/ks/prod/mail_subapp/static/img/appendix.f49c01fe.svg"
                              style="width: 14px; height: 14px; margin-right: 4px;">
                            <span @click="triggerFileInput">附件</span>
                            <input id="attachInput" type="file" multiple ref="fileInput" style="display:none;">
                          </div>
                        </span>
                      </span>
                      <div class="mm-divider-vertical solid"></div>
                      <div class="tool-item novice-tour-groupmail-content-field flex items-center"
                        @click="onShowTemplateDialog">
                        <img src="https://cdn.xiaoman.cn/crm_web/ks/grey/mail_subapp/static/img/template.b98c6551.svg"
                          style="width: 14px; height: 14px; margin-right: 4px;"> 模板
                      </div>
                      <div class="tool-item flex items-center" @click="onShowQuickTextDialog"><img
                          src="https://cdn.xiaoman.cn/crm_web/ks/grey/mail_subapp/static/img/fast-text.573b9be5.svg"
                          style="width: 14px; height: 14px; margin-right: 4px;"> 快速文本</div>
                    </div>
                  </div>
                </li>
              </ul>
              <!---->
              <div class="editor-footer">
                <div>
                  签名：
                  <el-select style="width:100px;" size="small" v-model="formData.signatureId">
                    <el-option v-for="(item, index) in signatureOption" :key="index" :label="item.title"
                      :value="item.id"></el-option>
                  </el-select>
                </div>
                <div class="split"></div>
                <div class="select">
                  <label class="mm-checkbox">
                    <input true-value="true" v-model="formData.traceFlag" @click="handleDelayedTxShow" type="checkbox"
                      checked="">
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

                  <DelayedTxlLayout v-if="delayedTxShow"></DelayedTxlLayout>
                </div>
                <div class="split"></div>
                <div class="select-handle-time">
                  <div class="mm-popover">
                    <div>
                      <span class="time" @click.stop="showPending">
                        <span class="okki-icon-wrap time-icon" color="#0064ff" v-if="pendingTime">
                          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24"
                            aria-hidden="true" class="okki-svg-icon" fill="#0064ff">
                            <path fill-rule="evenodd" clip-rule="evenodd"
                              d="M22 12c0 5.523-4.477 10-10 10S2 17.523 2 12 6.477 2 12 2s10 4.477 10 10z"></path>
                            <path
                              d="M12 7a1 1 0 011 1v4.423l2.964 1.711a1 1 0 01-1 1.732l-3.447-1.99a1.01 1.01 0 01-.384-.377.992.992 0 01-.133-.518V8a1 1 0 011-1z"
                              fill="#fff"></path>
                          </svg>
                          <span>{{ pendingTime }}</span>
                        </span>
                        <span class="okki-icon-wrap time-icon" v-else>
                          <!-- 如果ependingTime存在，显示选中的时间 -->
                          <!-- 否则，显示SVG图标 -->
                          <span>
                            ​<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24"
                              aria-hidden="true" class="okki-svg-icon" fill="currentColor">
                              <path
                                d="M12 6a1 1 0 011 1v4.423l2.964 1.711a1 1 0 11-1 1.732l-3.447-1.99A1 1 0 0111 11.98V7a1 1 0 011-1z">
                              </path>
                              <path fill-rule="evenodd" clip-rule="evenodd"
                                d="M22 12c0 5.523-4.477 10-10 10S2 17.523 2 12 6.477 2 12 2s10 4.477 10 10zm-2 0a8 8 0 11-16 0 8 8 0 0116 0z">
                              </path>
                            </svg>
                          </span> 标记为待处理
                        </span>
                      </span>
                    </div>
                    <!---->
                  </div>
                  <div class="mm-outside mail-pending-popover mm-popover-popper" x-placement="top-end"
                    v-if="showPendingTime || showCustomTime"
                    style="position: absolute; top: -220px; left: 95px; will-change: top, left; transform-origin: 100% bottom;">
                    <!---->
                    <div>
                      <!---->
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
              </div>
            </div>
          </div>
        </el-form>
      </div>
    </form>
    <template>
      <div class="mm-modal--mask mm-modal edit-close-dialog" id="report-stat-mail-edit" style="z-index: 1004;"
        v-if="showCancelPopup">
        <div class="mm-modal-mask"></div>
        <div class="mm-modal-wrapper" style="padding-top: 15vh;">
          <div class="mm-modal-content" style="width: 424px; border-color: transparent;">
            <svg class="mm-icon mm-icon-close mm-modal-close" @click="showCancelPopup = false" viewBox="0 0 24 24"
              name="close" fill="currentColor" style="height: 14px; width: 14px;">
              <path
                d="M14.3 11.7l6-6c.3-.3.3-.7 0-1l-.9-1c-.3-.2-.7-.2-1 0l-6 6.1c-.2.2-.5.2-.7 0l-6-6.1c-.3-.3-.7-.3-1 0l-1 1c-.2.2-.2.7 0 .9l6.1 6.1c.2.2.2.4 0 .6l-6.1 6.1c-.3.3-.3.7 0 1l1 1c.2.2.7.2.9 0l6.1-6.1c.2-.2.4-.2.6 0l6.1 6.1c.2.2.7.2.9 0l1-1c.3-.3.3-.7 0-1l-6-6c-.2-.2-.2-.5 0-.7z">
              </path>
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
    <DialogTemplate v-if="templateDialogVisible" :dialogVisible.sync="templateDialogVisible"
      @onConfirm="onTemplateConfirm" />
    <DialogQuickText v-if="quickDialogVisible" :dialogVisible.sync="quickDialogVisible" @onConfirm="onQuickTextConfirm" />
  </div>
</template>
<style lang="scss">
.dropdown-email-list {
  z-index: 1004;
  position: absolute;
}

.mm-outside.mail-pending-popover {
  position: absolute;
  will-change: top, left;
  transform-origin: 100% bottom;
  top: -225px;
  left: 75px;
}

.editor-wrapper {
  z-index: 1002 !important;
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
import { mapState } from 'vuex'
import { Editor, Toolbar } from "@wangeditor/editor-for-vue";
import { uploadAttachments, renameAttachment, deleteAttachment } from "@/api/email/attachment";
import { listTaskPull } from "@/api/email/task";
import { saveSendEmail, sendEmail, uploadAttachment } from "@/api/email/email";
import { EventBus } from '@/api/email/event-bus.js';
import ReceiverInput from './write_email_receiver_input.vue';
import PendingTimePopover from './pending_time.vue';
import CustomTimePopover from './custom_time.vue';
import DelayedTxlLayout from './write_email_delayed_tx.vue';
import { fontSizeList, fontFamilyList, colors } from '@/constant/editorOption'
import { getSignatureList } from '@/api/email/usually'
// 邮箱详情
import { getCustomerDetail } from '@/api/email/customer'

import DialogTemplate from './write_email/DialogTemplate.vue';
import DialogQuickText from './write_email/DialogQuickText.vue';

export default {
  components: { Editor, Toolbar, ReceiverInput, PendingTimePopover, CustomTimePopover, DelayedTxlLayout, DialogTemplate, DialogQuickText },
  data() {
    return {
      formData: {
        signatureId: ''
      },
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
      defaultContent: [
        {
          type: 'paragraph',
          children: [
            {
              text: '',
              fontFamily: this.emailDefaultOption?.defaultFont || '',
              fontSize: this.emailDefaultOption?.fontSize || '',
              color: this.emailDefaultOption?.fontColor || '',
            }
          ]
        },
      ],
      showCancelPopup: false,
      emailData: {
        '收件人': [],
        '抄送人': [],
        '密送人': []
      },
      receiver: [],
      cc: [],
      bcc: [],
      messageId: null,
      htmlText: '',
      showPendingTime: false,
      showCustomTime: false,
      pendingTime: null,
      pendingFlag: false,
      delayedTxShow: false,
      defaultOption: {},
      signatureOption: [],
      toolbarConfig: {
        excludeKeys: [
          'group-video',
          'group-image'
        ]
      },
      templateDialogVisible: false,
      quickDialogVisible: false,
    };
  },
  props: {
    selectedEmail: {
      type: Object,
      default: null
    },
    writeEmailType: {
      type: String,
      default: null
    },
    // 邮件默认配置项
    emailDefaultOption: {
      type: Object,
      default: () => { },
      required: false
    }
  },
  watch: {
    'formData.signatureId': {
      handler(newVal) {

        if (this.editor) {
          const defaultSpan = `
          <span style=" font-size:${this.emailDefaultOption?.fontSize || ''};
              font-family:${this.emailDefaultOption?.defaultFont || ''};
              color:${this.emailDefaultOption?.fontColor};">&ZeroWidthSpace;</span>\n\n\n`
          if (newVal === -1) {
            this.formData.content = `<span style=" font-size:${this.emailDefaultOption?.fontSize || ''};
              font-family:${this.emailDefaultOption?.defaultFont || ''};
              color:${this.emailDefaultOption?.fontColor};">&ZeroWidthSpace;</span>`
            return
          }
          let content = this.signatureOption.find(val => val.id === newVal)?.content || ''
          // 默认内容
          this.formData.content = `<p>${defaultSpan}${content}</p > `
        }
      }
    },
    "$route.query": {
      handler(newVal) {
        if (newVal?.id) {
          this.getEmailInfo(newVal.id)
        }
      },
      immediate: true
    },
    selectedAccount: {
      handler(newVal) {
        if (newVal) {
          this.$watch('emailList', (list) => {
            if (list?.length) {
              const item = list.find(val => val.taskName === newVal)
              // 抄送
              this.showCc = Boolean(item.defaultCcFlag)
              this.updateEmailList({
                label: '抄送人',
                emails: item.defaultCc?.split(';') || [],
              })
              // 密送
              this.showBcc = Boolean(item.defaultBccFlag)
              this.updateEmailList({
                label: '密送人',
                emails: item.defaultBcc?.split(';') || [],
              })
              this.formData.signatureId = item?.defaultSignatureId || null
            }
          }, {
            deep: true,
            immediate: true
          })
        }
      },
    }
  },
  computed: {
    ...mapState({
      defaultTaskId: state => state.emailSetting.usuallySetting?.defaultTaskId,
      emailList: state => state.emailList.emailList
    }),
  },
  methods: {
    async getEmailInfo(id) {
      try {
        const res = await getCustomerDetail({
          id
        })
        if (res.code === 200) {
          this.handleReplyEmail(res.data)
        }
      } catch (e) {
        console.error(e.message);
      }
    },
    async getSignatureOption() {
      try {
        const res = await getSignatureList()
        if (res.code === 200) {
          this.signatureOption = [{ id: -1, title: '不使用', content: '' }, ...res.data]
          this.$watch('emailDefaultOption.signatureId', (newVal) => {
            this.formData.signatureId = newVal || ''
          })
        }
      } catch { }
    },
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

        // 如果taskId已经存在，则尝试找到对应的任务
        if (this.taskId) {
          const task = this.taskList.find(task => task.id === this.taskId);
          if (task) {
            this.selectedAccount = task.account;
          }
        }

        // 如果selectedAccount不存在（意味着上面没有找到对应的任务，或者taskId本来就为空），则使用第一个任务的信息
        if (!this.selectedAccount && this.taskList.length > 0) {
          this.$watch('defaultTaskId', (newVal) => {
            const item = this.taskList.find(val => val.id === newVal)
            this.selectedAccount = item?.account || ''
            this.taskId = item?.id || ''
          }, {
            immediate: true
          })

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
      this.editor = Object.seal(editor);
      this.editor.getConfig().MENU_CONF = {
        ...this.editor.getConfig().MENU_CONF,
        fontSize: {
          fontSizeList
        },
        fontFamily: {
          fontFamilyList
        },
        color: {
          colors
        }
      }
      setTimeout(() => {
        this.editor.dangerouslyInsertHtml(this?.htmlText);
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
    onShowTemplateDialog() {
      this.templateDialogVisible = true
    },
    onShowQuickTextDialog() {
      this.quickDialogVisible = true
    },
    onTemplateConfirm(item) {
      this.editor.restoreSelection()
      this.editor.dangerouslyInsertHtml(item.content)
      this.templateDialogVisible = false
    },
    onQuickTextConfirm(item) {
      this.editor.restoreSelection()
      this.editor.dangerouslyInsertHtml(item.html)
      this.quickDialogVisible = false
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

      this.uploadedFiles.forEach(uploadedFile => {
        this.attachmentIds.push(uploadedFile.id);
      });

      const data = {
        "taskId": this.formData.taskId,
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
        "pendingFlag": this.pendingFlag,
        "pendingTime": this.pendingTime,
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
        // EventBus.$emit('switch-index');  // 发出事件
        this.$router.push('/email/index?type=default')
      } else {
        this.$message.error("执行失败");
      }
    },

    // 弹窗-不保存
    noSavePopup() {
      this.$router.push('/email/index?type=default')
      // EventBus.$emit('switch-index');  // 发出事件
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

      this.$set(file, 'name', `${file.newName}.${this.getFileExtension(file.name)} `);
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
    formattedEmailContent() {
      let emailContent = `
  < div style = "font-size: 12px;background:#efefef;padding:8px;" >
        <div><b>From: </b>&nbsp;<a href="mailto:${this.selectedEmail.fromer}" style="color: #1e7bf9; text-decoration: none;" target="_blank">${this.selectedEmail.fromer}</a></div>
        <div><b>Send time: </b>${this.formatDate(this.selectedEmail.sendDate)}</div>
`;

      if (this.selectedEmail.receiver) {
        emailContent += `< div > <b>To: </b> & nbsp;${this.formatEmailRecipients(this.parseEmailString(this.selectedEmail.receiver))}</div > `;
      }

      if (this.selectedEmail.cc) {
        emailContent += `< div > <b>Cc: </b> & nbsp;${this.formatEmailRecipients(this.parseEmailString(this.selectedEmail.cc))}</div > `;
      }

      if (this.selectedEmail.title) {
        emailContent += `< div > <b>Subject: </b> & nbsp;${this.selectedEmail.title}</div > `;
      }

      emailContent += '</div>';
      return emailContent;
    },

    formatEmailRecipients(emails) {
      return emails.map(email => `< a href = "mailto:${email}" style = "color: #1e7bf9; text-decoration: none;" target = "_blank" > ${email}</a > `).join('; ');
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

      return `${dayName}, ${monthName} ${dayOfMonth}, ${year} ${hours}:${minutes} ${ampm} `;
    },

    showPending() {
      if (this.showPendingTime || this.showCustomTime) {
        this.showPendingTime = false;
      } else {
        this.showPendingTime = true;
      }

      this.showCustomTime = false;
    },

    handleCustomTime() {
      this.showPendingTime = false;
      this.showCustomTime = true;
    },

    handlePendingTime() {
      this.showPendingTime = true;
      this.showCustomTime = false;
    },

    handleDelayedTxShow() {
      this.delayedTxShow = !this.delayedTxShow;
      console.log(this.delayedTxShow);
    },

    // 回复，回显数据处理
    handleReply() {
      if (this.selectedEmail.title) {
        this.formData.title = "Re: " + this.selectedEmail.title;
      }

      if (this.selectedEmail.id) {
        this.formData.id = this.selectedEmail.id;
        this.taskId = this.selectedEmail.taskId;
      }

      if (this.selectedEmail.fromer) {
        this.receiver.push(this.selectedEmail.fromer);
      }

      if (this.selectedEmail.content) {
        let original = "<br/><div style=\"font-size: 12px;font-family: Arial Narrow,serif;padding:2px 0 2px 0;\">------------------&nbsp;Original&nbsp;------------------</div>";
        this.htmlText = original + this.formattedEmailContent() + this.selectedEmail.content;
      }
    },
    handleReplyEmail(data) {
      if (data.id) {
        this.formData.id = data.id;
        this.taskId = data.taskId;
      }
      if (data.contactList && data.contactList.length) {
        const receiver = data.contactList?.map(val => val.email)
        receiver.forEach(email => {
          if (email !== this.selectedAccount) {
            this.receiver.push(email)
          }
        })
      }
      // if (data.receiver) {
      //   this.receiver.push(data.fromer);
      //   this.receiverEmails = JSON.parse(data.receiver);
      //   this.receiverEmails.forEach(receiver => {
      //     const email = receiver.email;
      //     // 有问题 this.selectedAccount获取不到。
      //     if (email !== this.selectedAccount) {
      //       this.receiver.push(email);
      //     }
      //   });
      // }

      // if (data.cc) {
      //   this.ccEmails = JSON.parse(data.cc);
      //   this.ccEmails.forEach(receiver => {
      //     const email = receiver.email;
      //     if (email !== this.selectedAccount) {
      //       this.cc.push(email);
      //     }
      //   });
      // }

    },

    // 回复全部
    handleReplyAll() {
      if (this.selectedEmail.title) {
        this.formData.title = "Re: " + this.selectedEmail.title;
      }

      if (this.selectedEmail.id) {
        this.formData.id = this.selectedEmail.id;
        this.taskId = this.selectedEmail.taskId;
      }

      if (this.selectedEmail.receiver) {
        this.receiver.push(this.selectedEmail.fromer);
        this.receiverEmails = JSON.parse(this.selectedEmail.receiver);
        this.receiverEmails.forEach(receiver => {
          const email = receiver.email;
          // 有问题 this.selectedAccount获取不到。
          if (email !== this.selectedAccount) {
            this.receiver.push(email);
          }
        });
      }

      if (this.selectedEmail.cc) {
        this.ccEmails = JSON.parse(this.selectedEmail.cc);
        this.ccEmails.forEach(receiver => {
          const email = receiver.email;
          if (email !== this.selectedAccount) {
            this.cc.push(email);
          }
        });
      }

      if (this.selectedEmail.content) {
        let original = "<br/><div style=\"font-size: 12px;font-family: Arial Narrow,serif;padding:2px 0 2px 0;\">------------------&nbsp;Original&nbsp;------------------</div>";
        this.htmlText = original + this.formattedEmailContent() + this.selectedEmail.content;
      }
    },

    // 带附件回复
    handleReplyWithAttachments() {
      this.handleReply();
      this.uploadedFiles = this.selectedEmail.emailAttachmentList;
    },

    // 带附件回复全部
    handleReplyAllWithAttachments() {
      this.handleReplyAll();
      this.uploadedFiles = this.selectedEmail.emailAttachmentList;
    },

    // 转发
    handleForward() {
      if (this.selectedEmail.title) {
        this.formData.title = "Re: " + this.selectedEmail.title;
      }

      if (this.selectedEmail.id) {
        this.formData.id = this.selectedEmail.id;
        this.taskId = this.selectedEmail.taskId;
      }

      if (this.selectedEmail.content) {
        let original = "<br/><div style=\"font-size: 12px;font-family: Arial Narrow,serif;padding:2px 0 2px 0;\">------------------&nbsp;Original&nbsp;------------------</div>";
        this.htmlText = original + this.formattedEmailContent() + this.selectedEmail.content;
      }

      this.uploadedFiles = this.selectedEmail.emailAttachmentList;
    },

    handleForwardAsAttachment() {
      if (this.selectedEmail.title) {
        this.formData.title = "Re: " + this.selectedEmail.title;
      }

      if (this.selectedEmail.id) {
        this.formData.id = this.selectedEmail.id;
        this.taskId = this.selectedEmail.taskId;
      }

      if (this.selectedEmail.id) {
        // 上传邮件eml附件
        this.uploadAttachment(this.selectedEmail.id);
      }
    },

    // 上传邮件eml附件
    async uploadAttachment(id) {
      const data = {
        "id": id
      };
      try {
        const response = await uploadAttachment(data);
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

    handleSelectedTime(time) {
      this.showPendingTime = false;
      this.showCustomTime = false;
      this.pendingTime = time;
      this.pendingFlag = true;
    }
  },
  mounted() {
    this.fetchTaskList().then(() => {
      if (this.selectedEmail && this.selectedEmail.taskId !== undefined) {
        const foundTask = this.taskList.find(task => task.id === this.selectedEmail.taskId);
        if (foundTask) {
          this.selectedAccount = foundTask.account;
        }
      }
    }).catch(error => {
      console.error("Error while fetching task list:", error);
    });
    this.getSignatureOption()
  },

  beforeDestroy() {
    const editor = this.editor;
    if (editor == null) return;
    editor.destroy();
  },

  created() {
    // 回复
    if (this.writeEmailType === 'reply') {
      this.handleReply();
      // 回复全部
    } else if (this.writeEmailType === 'reply_all') {
      this.handleReplyAll();
      // 带附件回复
    } else if (this.writeEmailType === 'reply_with_attachments') {
      this.handleReplyWithAttachments();
      // 带附件回复全部
    } else if (this.writeEmailType === 'reply_all_with_attachments') {
      this.handleReplyAllWithAttachments();
      // 转发
    } else if (this.writeEmailType === 'forward') {
      this.handleForward();
      // 作为附件转发
    } else if (this.writeEmailType === 'forward_as_attachment') {
      this.handleForwardAsAttachment();
    }
  }
};
</script>

