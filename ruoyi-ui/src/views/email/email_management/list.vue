<template>
  <div class="mm-tabs-content">
    <div class="okki-galio-valar-app okki-galio-mail-manange config-page-item">
      <!---->
      <div class="okki-galio-valar-app okki-galio-mail-manange" data-v-app="">
        <div class="w-full space-y-5">
          <header class="flex justify-between items-center">
            <p class="text-xl font-semibold">绑定邮箱</p>
            <div class="okki-space okki-space-horizontal okki-space-align-center" style="gap: 8px;">
              <div class="okki-space-item" style="">
                <button class="okki-btn okki-btn-default okki-btn-round" type="button" @click="checkAllEmails">
                  <!---->
                  <span>检查全部邮箱</span>
                </button>
              </div>
              <!---->
              <div class="okki-space-item">
                <!---->
                <button class="okki-btn okki-btn-primary okki-btn-round" type="button" @click="addEmailBtn">
                  <!---->
                  <span>新建邮箱</span>
                </button>
              </div>
              <!---->
            </div>
          </header>
          <div class="surely-table-wrapper mail-manange-tree-table" tooltip="false">
            <div class="okki-spin-nested-loading">
              <!---->
              <div class="okki-spin-container">
                <div class="surely-table surely-table-support-sticky surely-table-default surely-table-no-ping">
                  <!--v-if-->
                  <div class="surely-table-header" style="height: 55px;">
                    <div class="surely-table-center-viewport" style="height: calc(100% + 0px);">
                      <div class="surely-table-header-container">
                        <div class="surely-table-fix-left surely-table-row-wrapper surely-table-no-columns" style="width: 0px; min-width: 0px; max-width: 0px;">
                          <div class="surely-table-cell-shadow-left"></div>
                          <span class="surely-table-drag-column-placeholder" style="display: none;"></span>
                        </div>
                        <div class="surely-table-center surely-table-row-wrapper surely-table-last-columns">
                          <div class="surely-table-center-container" style="width: 1573px;">
                            <div tabindex="-1" role="cell" class="surely-table-cell surely-table-header-cell" colspan="1" colstart="0" colend="0" style="width: 636px; height: 55px; left: 0px;">
																												<span class="surely-table-column-title surely-table-cell-box">
																													<div class="surely-table-header-cell-title">
																														<span class="surely-table-header-cell-title-inner surely-table-cell-text-ellipsis" title="" style="">邮箱名称</span>
                                                            <!--v-if-->
																													</div>
																												</span>
                              <!--v-if-->
                              <!--v-if-->
                            </div>
                            <div tabindex="-1" role="cell" class="surely-table-cell surely-table-header-cell" colspan="1" colstart="1" colend="1" style="width: 567px; height: 55px; left: 626px;">
																												<span class="surely-table-column-title surely-table-cell-box">
																													<div class="surely-table-header-cell-title">
																														<span class="surely-table-header-cell-title-inner surely-table-cell-text-ellipsis" title="" style="">邮箱状态</span>
                                                            <!--v-if-->
																													</div>
																												</span>
                              <!--v-if-->
                              <!--v-if-->
                            </div>
                            <div tabindex="-1" role="cell" class="surely-table-cell surely-table-header-cell" colspan="1" colstart="2" colend="2" style="width: 280px; height: 55px; left: 1183px;">
																												<span class="surely-table-column-title surely-table-cell-box">
																													<div class="surely-table-header-cell-title">
																														<span class="surely-table-header-cell-title-inner surely-table-cell-text-ellipsis" title="" style="">操作</span>
                                                            <!--v-if-->
																													</div>
																												</span>
                              <!--v-if-->
                              <!--v-if-->
                            </div>
                          </div>
                          <span class="surely-table-drag-column-placeholder"></span>
                        </div>
                        <div class="surely-table-fix-right surely-table-row-wrapper surely-table-no-columns" style="width: 0px; min-width: 0px; max-width: 0px;">
                          <div class="surely-table-cell-shadow-right" style="display: none;"></div>
                          <span class="surely-table-drag-column-placeholder" style="display: none;"></span>
                        </div>
                      </div>
                    </div>
                    <div class="surely-table-header-scrollbar" style="display: none; width: 0px; min-width: 0px; max-width: 0px; position: sticky; right: 0px;"></div>
                  </div>
                  <!--v-if-->
                  <div class="surely-table-body" style="overflow-y: hidden;">
                    <div class="surely-table-body-container" style="height: 495px; z-index: unset !important;">
                      <div class="surely-table-fix-left surely-table-no-columns" style="width: 0px; min-width: 0px; max-width: 0px;">
                        <div class="surely-table-cell-shadow-left"></div>
                        <!--v-if-->
                        <!--v-if-->
                        <div class="surely-table-body-contextmenu-container">
                          <div class="surely-table-body-contextmenu-container-inner">
                            <!--v-if-->
                          </div>
                        </div>
                      </div>
                      <div class="surely-table-center">
                        <div class="surely-table-center-container" style="width: 1573px; height: 495px;">
                          <div
                            v-for="(email, index) in emails"
                            :key="email.id"
                            :style="{ transform: `translateY(${index * 55}px)` }"
                            class="surely-table-row surely-table-row-level-0 surely-table-row-odd surely-table-no-height" role="row" data-row-key="8" style="opacity: 1; transform: translateY(0px); height: 55px;">
                            <div colspan="1" rowspan="1" tabindex="-1" role="cell" class="surely-table-cell" style="overflow: initial; width: 646px; left: 0px;">
																												<span class="surely-table-drag-handle" aria-grabbed="false" aria-hidden="true" data-scroll-top="385" data-height="55" data-row-key="8" data-row-key-type="string" unselectable="on">
																													<svg viewBox="64 64 896 896" focusable="false" data-icon="holder" width="1em" height="1em" fill="currentColor" aria-hidden="true">
																														<path d="M300 276.5a56 56 0 1056-97 56 56 0 00-56 97zm0 284a56 56 0 1056-97 56 56 0 00-56 97zM640 228a56 56 0 10112 0 56 56 0 00-112 0zm0 284a56 56 0 10112 0 56 56 0 00-112 0zM300 844.5a56 56 0 1056-97 56 56 0 00-56 97zM640 796a56 56 0 10112 0 56 56 0 00-112 0z"></path>
																													</svg>
																												</span>
                              <div class="surely-table-cell-inner" style="">
                                <div class="surely-table-cell-content">
                                  <p style="margin-bottom: 0em">{{ email.account }}</p>
                                </div>
                              </div>
                            </div>
                            <div colspan="1" rowspan="1" tabindex="-1" role="cell" class="surely-table-cell" style="overflow: initial; width: 647px; left: 626px;">
                              <div class="surely-table-cell-inner" style="">
                                <div class="surely-table-cell-content">
                                  <div class="flex items-center">
                                    <span v-if="checkingStatus.includes(email.id)" class="loading-icon"></span>
                                    <span v-else class="okki-tag"
                                          :style="{
                                                   'background-color': email.connStatus === 1 ? 'rgb(217, 246, 235)' : 'rgb(255, 226, 224)',
                                                    color: email.connStatus === 1 ? 'rgb(0, 123, 75)' : 'rgb(182, 31, 31)',
                                                    border: 'none',
                                                    borderRadius: '4px'
                                                }">
                                                {{ email.connStatus === 1 ? '正常' : '异常' }}
																					</span>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div colspan="1" rowspan="1" tabindex="-1" role="cell" class="surely-table-cell" style="overflow: initial; width: 280px; left: 1183px;">
                              <div class="surely-table-cell-inner" style="">
                                <div class="surely-table-cell-content">
                                  <div>
                                    <!--                                          <button class="okki-btn okki-btn-link okki-btn-round" type="button">
                                                                                <span>添加别名邮箱</span>
                                                                              </button>-->
                                    <button
                                      class="okki-btn okki-btn-link okki-btn-round"
                                      type="button"
                                      @click="testEmailBtn(email)"
                                    >
                                      <span>检测</span>
                                    </button>
                                    <button
                                      class="okki-btn okki-btn-link okki-btn-round"
                                      type="button"
                                      @click="editEmailBtn(email)"
                                    >
                                      <span>修改</span>
                                    </button>
                                    <button
                                      class="okki-btn okki-btn-link okki-btn-round"
                                      type="button"
                                      @click="unbindEmailBtn(email)"
                                    >
                                      <span>解绑</span>
                                    </button>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>

                          <div class="surely-table-body-contextmenu-container">
                            <div class="surely-table-body-contextmenu-container-inner">
                              <!--v-if-->
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="surely-table-fix-right surely-table-no-columns" style="width: 0px; min-width: 0px; max-width: 0px;">
                        <div class="surely-table-cell-shadow-right"></div>
                        <div class="surely-table-body-contextmenu-container">
                          <div class="surely-table-body-contextmenu-container-inner">
                            <!--v-if-->
                          </div>
                        </div>
                      </div>
                    </div>
                    <div style="width: 100%; height: 100% !important; position: absolute !important; font-size: 100px !important; opacity: 0.1 !important; text-align: center !important; top: 0px !important; left: 0px !important; pointer-events: none; display: block !important; color: rgb(0, 0, 0) !important; margin: 0px !important; padding: 0px !important; transform: unset !important;"></div>
                    <div class="surely-table-body-scroll-measure" style="min-width: 1573px;"></div>
                    <div class="surely-table-body-inner-measure" style=""></div>
                    <div class="surely-table-scroll-layer" role="presentation" unselectable="on" style="height: 495px; width: 1573px; min-width: 100%;">
                      <div class="surely-table-scroll-layer-inner"></div>
                    </div>
                    <span class="surely-table-drag-placeholder"></span>
                  </div>
                  <!--v-if-->
                  <!--v-if-->
                </div>
              </div>
            </div>
            <div style="position: absolute; top: 0px; left: 0px; pointer-events: none; height: 0px; overflow: hidden; z-index: -999; padding: 0px; margin: 0px; width: 1px;"></div>
          </div>
        </div>
      </div>
    </div>
    <addEmailTemplate ref="addEmail"></addEmailTemplate>
    <editEmailTemplate ref="editEmail"></editEmailTemplate>
    <testEmailTemplate ref="testEmail"></testEmailTemplate>

  </div>
</template>

<style lang="scss">
.surely-table-wrapper, .surely-table {
  width: 100%;
}

.surely-table-center-container {
  display: flex;
  justify-content: space-between;
}

.surely-table-cell {
  flex: 1;
  min-width: 0;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

@media (max-width: 768px) {
  .surely-table-center-container {
    flex-direction: column;
  }

  .surely-table-cell {
    width: 100%;
    box-sizing: border-box;
    padding: 5px 10px;
  }
}

@media (max-width: 480px) {
  .surely-table-cell-content .okki-btn {
    padding: 5px 8px;
    font-size: 12px;
  }
}

@import '../../../static/scss/email/email_management/2309.72cc3cdf.css';
@import '../../../static/scss/email/email_management/9755.4df34767.css';
@import '../../../static/scss/email/email_management/windi.9edd44a6.css';
</style>
<script>
import addEmailTemplate from './add.vue';
import editEmailTemplate from './edit.vue';
import testEmailTemplate from './test.vue';
import {listTask, testTask, unbindTask} from "@/api/email/task";

export default {
  data() {
    return {
      emails: [],
      dialogVisible: false,  // 控制 Dialog 的显示与隐藏
      checkingStatus: [],
    };
  },
  components: {
    addEmailTemplate,
    editEmailTemplate,
    testEmailTemplate,
  },
  methods: {
    openDialog() {
      this.dialogVisible = true; // 打开 Dialog
    },
    closeDialog() {
      this.dialogVisible = false; // 关闭 Dialog
    },
    //点击弹窗新建邮箱弹窗
    addEmailBtn() {
      this.$refs.addEmail.open();
    },
    //点击弹窗编辑邮箱弹窗
    editEmailBtn(email) {
      this.$refs.editEmail.open(email);
    },
    //点击弹窗编辑邮箱弹窗
    testEmailBtn(email) {
      this.$refs.testEmail.open(email);
    },
    //解绑
    unbindEmailBtn(email) {
      const data = {
        "id": email.id
      }
      unbindTask(data).then((response) => {
        this.$message.success("解绑成功");
        this.refreshEmailList();
      })
    },
    refreshEmailList() {
      listTask().then((response) => {
        this.emails = response.rows;
        this.emails.forEach((email) => {
          email.id = email.id;
          email.account = email.account;
          email.connStatus = email.connStatus;
        });
      });
    },
    async checkAllEmails() {
      // Step 1: Mark all emails as being checked
      this.emails.forEach(email => {
        this.checkingStatus.push(email.id);
      });

      // Step 2: Check each email sequentially (or in parallel, depending on your needs)
      const checkPromises = this.emails.map(async email => {
        try {
          await testTask(email.id); // Assume testTask is an asynchronous method
        } catch (error) {
          console.error("Error checking email", error);
        } finally {
          // Remove the email from the checking list regardless of whether the check succeeded or failed
          this.checkingStatus = this.checkingStatus.filter(id => id !== email.id);
        }
      });

      // Wait for all checks to complete
      await Promise.all(checkPromises);

      // Step 3: After all emails have been checked, refresh the list
      this.refreshEmailList();
    }
  },

  created() {
    this.refreshEmailList();
  }
}
</script>
