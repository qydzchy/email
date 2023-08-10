<template>
  <div class="mm-split-pane mm-split-pane__right" style="left: 12.2835%;">
    <div class="mail-plus-config layout-extraSidebar-content" mail-type="" folder-id="">
      <div class="mm-tabs mm-tabs__top mm-tabs__align-left m-tab">
        <div class="mm-tabs-header mm-tabs-header__top">
          <!---->
          <div class="mm-tabs-nav-wrap mm-tabs-nav-wrap__top">
            <!---->
            <div class="mm-tabs-nav-scroll">
              <div class="mm-tabs-nav" role="tablist" style="transform: translateX(0px);">
                <div class="mm-tabs-active-bar__top mm-tabs-active-bar" style="width: 96px; transform: translateX(136px);"></div>
                <div class="mm-tabs-item mm-tabs-item__top" id="tab-0" aria-controls="pane-0" role="tab" aria-selected="false" tabindex="-1" refinfor="true">
                  <div>常规
                    <!---->
                  </div>
                  <!---->
                </div>
                <div class="mm-tabs-item mm-tabs-item__top" id="tab-1" aria-controls="pane-1" role="tab" aria-selected="false" tabindex="-1" refinfor="true">
                  <div>模板
                    <!---->
                  </div>
                  <!---->
                </div>
                <div class="mm-tabs-item mm-tabs-item__top mm-tabs-item--active" id="tab-2" aria-controls="pane-2" role="tab" aria-selected="true" tabindex="0" refinfor="true">
                  <div>邮箱管理
                    <!---->
                  </div>
                  <!---->
                </div>
                <div class="mm-tabs-item mm-tabs-item__top" id="tab-3" aria-controls="pane-3" role="tab" aria-selected="false" tabindex="-1" refinfor="true">
                  <div>收发件规则
                    <!---->
                  </div>
                  <!---->
                </div>
                <div class="mm-tabs-item mm-tabs-item__top" id="tab-4" aria-controls="pane-4" role="tab" aria-selected="false" tabindex="-1" refinfor="true">
                  <div>快速文本
                    <!---->
                  </div>
                  <!---->
                </div>
                <div class="mm-tabs-item mm-tabs-item__top" id="tab-5" aria-controls="pane-5" role="tab" aria-selected="false" tabindex="-1" refinfor="true">
                  <div>文件夹
                    <!---->
                  </div>
                  <!---->
                </div>
                <div class="mm-tabs-item mm-tabs-item__top" id="tab-6" aria-controls="pane-6" role="tab" aria-selected="false" tabindex="-1" refinfor="true">
                  <div>标签
                    <!---->
                  </div>
                  <!---->
                </div>
                <div class="mm-tabs-item mm-tabs-item__top" id="tab-7" aria-controls="pane-7" role="tab" aria-selected="false" tabindex="-1" refinfor="true">
                  <div>黑名单
                    <!---->
                  </div>
                  <!---->
                </div>
                <div class="mm-tabs-item mm-tabs-item__top" id="tab-8" aria-controls="pane-8" role="tab" aria-selected="false" tabindex="-1" refinfor="true">
                  <div>导入邮件
                    <!---->
                  </div>
                  <!---->
                </div>
                <div class="mm-tabs-item mm-tabs-item__top" id="tab-9" aria-controls="pane-9" role="tab" aria-selected="false" tabindex="-1" refinfor="true">
                  <div>其他配置
                    <!---->
                  </div>
                  <!---->
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="mm-tabs-content">
          <div id="pane-0" aria-hidden="true" aria-labelledby="tab-0" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
          <div id="pane-1" aria-hidden="true" aria-labelledby="tab-1" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
          <div id="pane-2" aria-hidden="false" aria-labelledby="tab-2" class="mm-tab-pane" role="tabpanel" style=""></div>
          <div id="pane-3" aria-hidden="true" aria-labelledby="tab-3" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
          <div id="pane-4" aria-hidden="true" aria-labelledby="tab-4" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
          <div id="pane-5" aria-hidden="true" aria-labelledby="tab-5" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
          <div id="pane-6" aria-hidden="true" aria-labelledby="tab-6" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
          <div id="pane-7" aria-hidden="true" aria-labelledby="tab-7" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
          <div id="pane-8" aria-hidden="true" aria-labelledby="tab-8" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
          <div id="pane-9" aria-hidden="true" aria-labelledby="tab-9" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
          <div class="okki-galio-valar-app okki-galio-mail-manange config-page-item">
            <!---->
            <div class="okki-galio-valar-app okki-galio-mail-manange" data-v-app="">
              <div class="w-full space-y-5">
                <header class="flex justify-between items-center">
                  <p class="text-xl font-semibold">绑定邮箱</p>
                  <div class="okki-space okki-space-horizontal okki-space-align-center" style="gap: 8px;">
                    <div class="okki-space-item" style="">
                      <button class="okki-btn okki-btn-default okki-btn-round" type="button">
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
                                  <div tabindex="-1" role="cell" class="surely-table-cell surely-table-header-cell" colspan="1" colstart="0" colend="0" style="width: 646px; height: 55px; left: 0px;">
																												<span class="surely-table-column-title surely-table-cell-box">
																													<div class="surely-table-header-cell-title">
																														<span class="surely-table-header-cell-title-inner surely-table-cell-text-ellipsis" title="" style="">邮箱名称</span>
                                                            <!--v-if-->
																													</div>
																												</span>
                                    <!--v-if-->
                                    <!--v-if-->
                                  </div>
                                  <div tabindex="-1" role="cell" class="surely-table-cell surely-table-header-cell" colspan="1" colstart="1" colend="1" style="width: 647px; height: 55px; left: 646px;">
																												<span class="surely-table-column-title surely-table-cell-box">
																													<div class="surely-table-header-cell-title">
																														<span class="surely-table-header-cell-title-inner surely-table-cell-text-ellipsis" title="" style="">邮箱状态</span>
                                                            <!--v-if-->
																													</div>
																												</span>
                                    <!--v-if-->
                                    <!--v-if-->
                                  </div>
                                  <div tabindex="-1" role="cell" class="surely-table-cell surely-table-header-cell" colspan="1" colstart="2" colend="2" style="width: 280px; height: 55px; left: 1293px;">
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
<!--                                <div class="surely-table-row surely-table-row-level-0 surely-table-row-odd surely-table-no-height" role="row" data-row-key="8" style="opacity: 1; transform: translateY(385px); height: 55px;">
                                  <div colspan="1" rowspan="1" tabindex="-1" role="cell" class="surely-table-cell" style="overflow: initial; width: 646px; left: 0px;">
																												<span class="surely-table-drag-handle" aria-grabbed="false" aria-hidden="true" data-scroll-top="385" data-height="55" data-row-key="8" data-row-key-type="string" unselectable="on">
																													<svg viewBox="64 64 896 896" focusable="false" data-icon="holder" width="1em" height="1em" fill="currentColor" aria-hidden="true">
																														<path d="M300 276.5a56 56 0 1056-97 56 56 0 00-56 97zm0 284a56 56 0 1056-97 56 56 0 00-56 97zM640 228a56 56 0 10112 0 56 56 0 00-112 0zm0 284a56 56 0 10112 0 56 56 0 00-112 0zM300 844.5a56 56 0 1056-97 56 56 0 00-56 97zM640 796a56 56 0 10112 0 56 56 0 00-112 0z"></path>
																													</svg>
																												</span>
                                    <div class="surely-table-cell-inner" style="">
                                      <div class="surely-table-cell-content">
                                        &lt;!&ndash;&ndash;&gt;
                                        <p>sharon@htesemi.com</p>
                                      </div>
                                    </div>
                                  </div>
                                  <div colspan="1" rowspan="1" tabindex="-1" role="cell" class="surely-table-cell" style="overflow: initial; width: 647px; left: 646px;">
                                    &lt;!&ndash;&ndash;&gt;
                                    <div class="surely-table-cell-inner" style="">
                                      <div class="surely-table-cell-content">
                                        &lt;!&ndash;&ndash;&gt;
                                        <div class="flex items-center">
																															<span class="okki-tag" style="background-color: rgb(217, 246, 235); color: rgb(0, 123, 75); border: none; border-radius: 4px;">正常
                                                                &lt;!&ndash;&ndash;&gt;
																															</span>
                                        </div>
                                      </div>
                                    </div>
                                  </div>
                                  <div colspan="1" rowspan="1" tabindex="-1" role="cell" class="surely-table-cell" style="overflow: initial; width: 280px; left: 1293px;">
                                    &lt;!&ndash;&ndash;&gt;
                                    <div class="surely-table-cell-inner" style="">
                                      <div class="surely-table-cell-content">
                                        &lt;!&ndash;&ndash;&gt;
                                        <div class="space-x-2 flex w-full justify-end items-center">
                                          &lt;!&ndash;&ndash;&gt;
                                          <button class="okki-btn okki-btn-link okki-btn-round" type="button">
                                            &lt;!&ndash;&ndash;&gt;
                                            <span>添加别名邮箱</span>
                                          </button>
                                          <button class="okki-btn okki-btn-link okki-btn-round" type="button">
                                            &lt;!&ndash;&ndash;&gt;
                                            <span>检测</span>
                                          </button>
                                          <button class="okki-btn okki-btn-link okki-btn-round" type="button">
                                            &lt;!&ndash;&ndash;&gt;
                                            <span>修改</span>
                                          </button>
                                          <button class="okki-btn okki-btn-link okki-btn-round" type="button">
                                            &lt;!&ndash;&ndash;&gt;
                                            <span>解绑</span>
                                          </button>
                                        </div>
                                      </div>
                                    </div>
                                  </div>
                                  &lt;!&ndash;v-if&ndash;&gt;
                                </div>-->

                                <div v-for="email in emailList" :key="email.id" class="surely-table-row surely-table-row-level-0 surely-table-row-odd surely-table-no-height">
                                  <!-- 邮箱名称 Email Name -->
                                  <div class="surely-table-cell">
                                    <p>{{ email.name }}</p>
                                  </div>
                                  <!-- 邮箱状态 Email Status -->
                                  <div class="surely-table-cell">
                                    <span class="okki-tag">{{ email.status }}</span>
                                  </div>
                                  <!-- 操作 Actions -->
                                  <div class="surely-table-cell">
                                    <div class="surely-table-cell-inner" style="">
                                      <div class="surely-table-cell-content">
                                        &lt;!&ndash;&ndash;&gt;
                                        <div class="space-x-2 flex w-full justify-end items-center">
                                          &lt;!&ndash;&ndash;&gt;
                                          <button class="okki-btn okki-btn-link okki-btn-round" type="button">
                                            &lt;!&ndash;&ndash;&gt;
                                            <span>添加别名邮箱</span>
                                          </button>
                                          <button class="okki-btn okki-btn-link okki-btn-round" type="button">
                                            &lt;!&ndash;&ndash;&gt;
                                            <span>检测</span>
                                          </button>
                                          <button class="okki-btn okki-btn-link okki-btn-round" type="button">
                                            &lt;!&ndash;&ndash;&gt;
                                            <span>修改</span>
                                          </button>
                                          <button class="okki-btn okki-btn-link okki-btn-round" type="button">
                                            &lt;!&ndash;&ndash;&gt;
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
        </div>
      </div>
      <div class="component-feature-introduce video-btn">
        <div>
          <div class="mm-badge component-feature-introduce-badge">
																<span class="mm-tooltip">
																	<span class="mm-tooltip-trigger">
																		<span class="okki-icon-wrap">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																				<path fill-rule="evenodd" clip-rule="evenodd" d="M18.635 8.289L12 5.29 5.368 8.29 12 11.287l6.634-2.998zm-5.81-4.821a2 2 0 00-1.648 0l-8.649 3.91c-.784.354-.784 1.468 0 1.822l2.277 1.03v5.33a3 3 0 001.694 2.7l5.045 2.44a1 1 0 00.87 0l5.045-2.44a3 3 0 001.695-2.7v-5.31l.798-.362v.001l1.408-.637.114-.052c.785-.354.784-1.468 0-1.822l-8.649-3.91zM6.805 15.56v-4.427l4.372 1.977a2 2 0 001.648 0l4.329-1.957v4.407a1 1 0 01-.565.9l-4.61 2.23-4.61-2.23a1 1 0 01-.564-.9zm13.113-1.237v-3.428l1.988-.9a1 1 0 01.012.154v4.174a1 1 0 11-2 0z"></path>
																			</svg>
																		</span>
																	</span>
                                  <!---->
																</span>
            <sup class="mm-badge-content mm-badge-content__dot mm-badge-content--error mm-badge-content__zero">
              <!---->
            </sup>
          </div>
        </div>
        <!--portal-->
      </div>
    </div>

    <addEmailTemplate ref="addEmail"></addEmailTemplate>
  </div>

</template>

<style lang="scss">
@import '../../static/scss/email/email_management/2309.72cc3cdf.css';
@import '../../static/scss/email/email_management/9755.4df34767.css';
@import '../../static/scss/email/email_management/windi.9edd44a6.css';
</style>
<script>
import addEmailTemplate from './add_email.vue';
import {addTask} from "@/api/email/task";

export default {
  data() {
    return {
      dialogVisible: false,  // 控制 Dialog 的显示与隐藏
    };
  },
  components: {
    addEmailTemplate, // 注册 MyTemplate 组件
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
    fetchEmailList() {
      emailList(this.formData).then((response) => {
        this.$message.success("新增成功");
        this.addEmailPage = false;
      });
    }
  }
}
</script>
