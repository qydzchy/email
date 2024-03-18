<template>
  <div v-if="searchPage">
    <div class="okki-modal-root">
      <div class="okki-modal-mask"></div>
      <div tabindex="-1" class="okki-modal-wrap okki-modal-centered" role="dialog" aria-labelledby="vcDialogTitle1">
        <div class="okki-modal main-toolbar-search-advanced-search" role="document" style="width: 500px; transform-origin: 1076px 45px;">
          <div tabindex="0" aria-hidden="true" style="width: 0px; height: 0px; overflow: hidden; outline: none;"></div>
          <div class="okki-modal-content">
            <button type="button" aria-label="Close" class="okki-modal-close" @click="close">
                <span class="okki-modal-close-x">
                  <span role="img" aria-label="close" class="anticon anticon-close okki-modal-close-icon okki-modal-close-icon" icon="[object Object]" spin="false">
                    <svg focusable="false" class="" data-icon="close" width="1em" height="1em" fill="currentColor" aria-hidden="true" viewBox="64 64 896 896">
                      <path d="M563.8 512l262.5-312.9c4.4-5.2.7-13.1-6.1-13.1h-79.8c-4.7 0-9.2 2.1-12.3 5.7L511.6 449.8 295.1 191.7c-3-3.6-7.5-5.7-12.3-5.7H203c-6.8 0-10.5 7.9-6.1 13.1L459.4 512 196.9 824.9A7.95 7.95 0 00203 838h79.8c4.7 0 9.2-2.1 12.3-5.7l216.5-258.1 216.5 258.1c3 3.6 7.5 5.7 12.3 5.7h79.8c6.8 0 10.5-7.9 6.1-13.1L563.8 512z"></path>
                    </svg>
                  </span>
                </span>
            </button>
            <div class="okki-modal-header">
              <div class="okki-modal-title" id="vcDialogTitle1">邮件高级搜索</div>
            </div>
            <div class="okki-modal-body" style="height: calc(-100px + 100vh);">
              <form class="okki-form okki-form-vertical">
                <!---->
                <!---->
                <div class="okki-row okki-form-item" style="row-gap: 0px;">
                  <div class="okki-col okki-form-item-label" style="width: 7em;">
                    <label class="" title="">
                      <span class="text-medium-8">关键字</span>
                      <!---->
                    </label>
                  </div>
                  <div class="okki-col okki-form-item-control">
                    <div class="okki-form-item-control-input">
                      <div class="okki-form-item-control-input-content">
                          <span class="okki-input-group okki-input-group-compact">
                            <div inlinetitle="" bordered="true" filterborderless="false" class="okki-select okki-select-single okki-select-show-arrow" style="width: 20%;">
                              <!---->
                              <div class="okki-select-selector">
                                <span class="okki-select-selection-search" style="--select-single-inline-title-width: 0px;">
                                  <input type="search" id="rc_select_4" autocomplete="off" class="okki-select-selection-search-input" role="combobox" aria-haspopup="listbox" aria-owns="rc_select_4_list" aria-autocomplete="list" aria-controls="rc_select_4_list" aria-activedescendant="rc_select_4_list_0" readonly="" unselectable="on" style="opacity: 0;" aria-expanded="false" value="">
                                </span>
                                <span class="okki-select-selection-item" title="全部">全部</span>
                                <!---->
                              </div>
                              <span class="okki-select-arrow" unselectable="on" aria-hidden="true" style="user-select: none;">
                                <span role="img" class="anticon okki-select-suffix">
                                  <svg width="10" height="12" viewBox="0 0 10 6" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M0.86201 0.528636C1.12236 0.268287 1.54447 0.268287 1.80482 0.528636L5.33341 4.05723L8.86201 0.528636C9.12236 0.268287 9.54447 0.268287 9.80482 0.528636C10.0652 0.788986 10.0652 1.2111 9.80482 1.47145L5.80482 5.47145C5.54447 5.73179 5.12236 5.73179 4.86201 5.47145L0.86201 1.47145C0.601661 1.2111 0.601661 0.788986 0.86201 0.528636Z" fill="currentColor"></path>
                                  </svg>
                                </span>
                              </span>
                              <!---->
                              <div style="position: absolute; top: 0px; left: 0px; width: 100%;">
                                <div>
                                  <!---->
                                  <div class="okki-select-dropdown okki-select-dropdown-placement-bottomLeft" style="min-width: 87px; width: 87px; left: -265px; top: -828px; display: none;">
                                    <div>
                                      <div role="listbox" id="rc_select_4_list" style="height: 0px; width: 0px; overflow: hidden;">
                                        <!---->
                                        <div aria-label="全部" role="option" id="rc_select_4_list_0" aria-selected="true"></div>
                                        <div aria-label="主题" role="option" id="rc_select_4_list_1" aria-selected="false">1</div>
                                      </div>
                                      <div class="rc-virtual-list" style="position: relative;">
                                        <div class="rc-virtual-list-holder" style="max-height: 256px; overflow-y: hidden; overflow-anchor: none;">
                                          <div>
                                            <div class="rc-virtual-list-holder-inner" style="display: flex; flex-direction: column;">
                                              <div placeholder="请输入邮件主题/正文/附件名" aria-selected="true" class="okki-select-item okki-select-item-option okki-select-item-option-active okki-select-item-option-selected" title="全部">
                                                <div class="okki-select-item-option-content">全部</div>
                                                <!---->
                                                <span class="okki-select-item-option-state" unselectable="on" aria-hidden="true" style="user-select: none;">
                                                  <!---->
                                                </span>
                                              </div>
                                              <div placeholder="请输入邮件主题" aria-selected="false" class="okki-select-item okki-select-item-option" title="主题">
                                                <div class="okki-select-item-option-content">主题</div>
                                                <!---->
                                                <span class="okki-select-item-option-state" unselectable="on" aria-hidden="true" style="user-select: none;">
                                                  <!---->
                                                </span>
                                              </div>
                                              <div placeholder="请输入邮件正文" aria-selected="false" class="okki-select-item okki-select-item-option" title="正文">
                                                <div class="okki-select-item-option-content">正文</div>
                                                <!---->
                                                <span class="okki-select-item-option-state" unselectable="on" aria-hidden="true" style="user-select: none;">
                                                  <!---->
                                                </span>
                                              </div>
                                              <div placeholder="请输入附件名" aria-selected="false" class="okki-select-item okki-select-item-option" title="附件名">
                                                <div class="okki-select-item-option-content">附件名</div>
                                                <!---->
                                                <span class="okki-select-item-option-state" unselectable="on" aria-hidden="true" style="user-select: none;">
                                                  <!---->
                                                </span>
                                              </div>
                                            </div>
                                          </div>
                                        </div>
                                        <div class="rc-virtual-list-scrollbar" style="width: 8px; top: 0px; bottom: 0px; right: 0px; position: absolute; display: none;">
                                          <div class="rc-virtual-list-scrollbar-thumb" style="width: 100%; height: 128px; top: 0px; left: 0px; position: absolute; background: rgba(0, 0, 0, 0.5); border-radius: 99px; cursor: pointer; user-select: none;"></div>
                                        </div>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <input placeholder="请输入邮件主题/正文/附件名" type="text" class="okki-input truncate" style="width: 80%;" data-listener-added_b659e4c8="true" value="">
                          </span>
                      </div>
                      <!---->
                    </div>
                    <!---->
                    <!---->
                  </div>
                </div>
                <!---->
                <div class="okki-row okki-form-item" style="row-gap: 0px;">
                  <div class="okki-col okki-form-item-label" style="width: 7em;">
                    <label class="" title="">
                      <span class="text-medium-8">客户</span>
                      <!---->
                    </label>
                  </div>
                  <div class="okki-col okki-form-item-control">
                    <div class="okki-form-item-control-input">
                      <div class="okki-form-item-control-input-content">
                        <div inlinetitle="" bordered="true" filterborderless="false" class="okki-select okki-select-single okki-select-show-search">
                          <!---->
                          <!---->
                          <div class="okki-select-selector">
                              <span class="okki-select-selection-search" style="--select-single-inline-title-width: 0px;">
                                <input type="search" id="rc_select_5" autocomplete="off" class="okki-select-selection-search-input" role="combobox" aria-haspopup="listbox" aria-owns="rc_select_5_list" aria-autocomplete="list" aria-controls="rc_select_5_list" aria-activedescendant="rc_select_5_list_0" aria-expanded="false" data-listener-added_b659e4c8="true" value="">
                              </span>
                            <!---->
                            <span class="okki-select-selection-placeholder">请输入客户名称/编号/邮箱/联系人昵称</span>
                          </div>
                          <!---->
                          <!---->
                        </div>
                      </div>
                      <!---->
                    </div>
                    <!---->
                    <!---->
                  </div>
                </div>
                <div class="okki-row okki-form-item" style="row-gap: 0px;">
                  <div class="okki-col okki-form-item-label" style="width: 7em;">
                    <label class="" title="">
                      <span class="text-medium-8">客户联系人</span>
                      <!---->
                    </label>
                  </div>
                  <div class="okki-col okki-form-item-control">
                    <div class="okki-form-item-control-input">
                      <div class="okki-form-item-control-input-content">
                        <div inlinetitle="" bordered="true" filterborderless="false" class="okki-select okki-select-multiple okki-select-disabled okki-select-show-search">
                          <!---->
                          <!---->
                          <div class="okki-select-selector">
                            <div class="okki-select-selection-overflow">
                              <!---->
                              <div class="okki-select-selection-overflow-item okki-select-selection-overflow-item-suffix" style="opacity: 1;">
                                <div class="okki-select-selection-search" style="width: 4px;">
                                  <input type="search" id="rc_select_6" disabled="" autocomplete="off" class="okki-select-selection-search-input" role="combobox" aria-expanded="false" aria-haspopup="listbox" aria-owns="rc_select_6_list" aria-autocomplete="list" aria-controls="rc_select_6_list" aria-activedescendant="rc_select_6_list_0" readonly="" unselectable="on" style="opacity: 0;" value="">
                                  <span class="okki-select-selection-search-mirror" aria-hidden="true"> </span>
                                </div>
                              </div>
                              <!---->
                            </div>
                            <span class="okki-select-selection-placeholder" style="--select-multiple-inline-title-width: 0px;">请选择客户联系人</span>
                          </div>
                          <!---->
                          <!---->
                        </div>
                      </div>
                      <!---->
                    </div>
                    <!---->
                    <!---->
                  </div>
                </div>
                <div class="okki-row okki-form-item" style="row-gap: 0px;">
                  <div class="okki-col okki-form-item-label" style="width: 7em;">
                    <label class="" title="">
                      <span class="text-medium-8">收发件人</span>
                      <!---->
                    </label>
                  </div>
                  <div class="okki-col okki-form-item-control">
                    <div class="okki-form-item-control-input">
                      <div class="okki-form-item-control-input-content">
                        <div class="okki-radio-group okki-radio-group-outline mb-4px">
                          <label class="okki-radio-wrapper">
                              <span class="okki-radio">
                                <input type="radio" class="okki-radio-input" value="0">
                                <span class="okki-radio-inner"></span>
                              </span>
                            <span>发件人或收件人</span>
                          </label>
                          <label class="okki-radio-wrapper okki-radio-wrapper-checked">
                              <span class="okki-radio okki-radio-checked">
                                <input type="radio" class="okki-radio-input" value="1" checked="">
                                <span class="okki-radio-inner"></span>
                              </span>
                            <span>发件人和收件人</span>
                          </label>
                        </div>
                        <!---->
                        <div class="think-input mb-4px">
                          <input placeholder="请填写收件人" type="text" class="okki-input think-input-keyword truncate" value="">
                          <!---->
                        </div>
                        <div class="think-input">
                          <input placeholder="请填写发件人" type="text" class="okki-input think-input-keyword truncate" value="">
                          <!---->
                        </div>
                      </div>
                      <!---->
                    </div>
                    <!---->
                    <!---->
                  </div>
                </div>
                <div class="okki-row okki-form-item" style="row-gap: 0px;">
                  <div class="okki-col okki-form-item-label" style="width: 7em;">
                    <label class="" title="">
                      <span class="text-medium-8">邮件标签</span>
                      <!---->
                    </label>
                  </div>
                  <div class="okki-col okki-form-item-control">
                    <div class="okki-form-item-control-input">
                      <div class="okki-form-item-control-input-content">
                        <div class="okki-radio-group okki-radio-group-outline mb-4px">
                          <label class="okki-radio-wrapper okki-radio-wrapper-checked">
                              <span class="okki-radio okki-radio-checked">
                                <input type="radio" class="okki-radio-input" value="0" checked="">
                                <span class="okki-radio-inner"></span>
                              </span>
                            <span>包含任一标签</span>
                          </label>
                          <label class="okki-radio-wrapper">
                              <span class="okki-radio">
                                <input type="radio" class="okki-radio-input" value="1">
                                <span class="okki-radio-inner"></span>
                              </span>
                            <span>包含全部标签</span>
                          </label>
                        </div>
                        <div inlinetitle="" bordered="true" filterborderless="false" class="okki-select okki-select-multiple okki-select-show-search">
                          <!---->
                          <div class="okki-select-selector">
                            <div class="okki-select-selection-overflow">
                              <!---->
                              <div class="okki-select-selection-overflow-item okki-select-selection-overflow-item-suffix" style="opacity: 1;">
                                <div class="okki-select-selection-search" style="width: 4px;">
                                  <input type="search" id="rc_select_7" autocomplete="off" class="okki-select-selection-search-input" role="combobox" aria-haspopup="listbox" aria-owns="rc_select_7_list" aria-autocomplete="list" aria-controls="rc_select_7_list" aria-activedescendant="rc_select_7_list_0" style="opacity: 0;" aria-expanded="false" data-listener-added_b659e4c8="true" readonly="" unselectable="on" value="">
                                  <span class="okki-select-selection-search-mirror" aria-hidden="true"> </span>
                                </div>
                              </div>
                              <!---->
                            </div>
                            <span class="okki-select-selection-placeholder" style="--select-multiple-inline-title-width: 0px;">请选择标签</span>
                          </div>
                          <!---->
                          <!---->
                          <div style="position: absolute; top: 0px; left: 0px; width: 100%;">
                            <div>
                              <!---->
                              <div class="okki-select-dropdown okki-select-dropdown-placement-bottomLeft" style="min-width: 435px; width: 435px; left: -265px; top: -444px; display: none;">
                                <div>
                                  <div role="listbox" id="rc_select_7_list" style="height: 0px; width: 0px; overflow: hidden;">
                                    <!---->
                                    <div aria-label="已分发邮件" role="option" id="rc_select_7_list_0" aria-selected="false">1</div>
                                    <div aria-label="接收分发邮件" role="option" id="rc_select_7_list_1" aria-selected="false">2</div>
                                  </div>
                                  <div class="rc-virtual-list" style="position: relative;">
                                    <div class="rc-virtual-list-holder" style="max-height: 256px; overflow-y: hidden; overflow-anchor: none;">
                                      <div style="height: 420px; position: relative; overflow: hidden;">
                                        <div class="rc-virtual-list-holder-inner" style="display: flex; flex-direction: column; transform: translateY(0px); position: absolute; left: 0px; right: 0px; top: 0px;">
                                          <div tag_color="#8D8D8D" aria-selected="false" class="okki-select-item okki-select-item-option okki-select-item-option-active" title="已分发邮件">
                                            <div class="okki-select-item-option-content">
                                                <span title="已分发邮件" class="tag-wrapper" style="background: rgba(141, 141, 141, 0.2); color: rgb(141, 141, 141);">
                                                  <span class="ellipsis">已分发邮件</span>
                                                </span>
                                            </div>
                                            <!---->
                                            <!---->
                                          </div>
                                          <div tag_color="#8D8D8D" aria-selected="false" class="okki-select-item okki-select-item-option" title="接收分发邮件">
                                            <div class="okki-select-item-option-content">
                                                <span title="接收分发邮件" class="tag-wrapper" style="background: rgba(141, 141, 141, 0.2); color: rgb(141, 141, 141);">
                                                  <span class="ellipsis">接收分发邮件</span>
                                                </span>
                                            </div>
                                            <!---->
                                            <!---->
                                          </div>
                                          <div tag_color="#8D8D8D" aria-selected="false" class="okki-select-item okki-select-item-option" title="开发信">
                                            <div class="okki-select-item-option-content">
                                                <span title="开发信" class="tag-wrapper" style="background: rgba(141, 141, 141, 0.2); color: rgb(141, 141, 141);">
                                                  <span class="ellipsis">开发信</span>
                                                </span>
                                            </div>
                                            <!---->
                                            <!---->
                                          </div>
                                          <div tag_color="#8D8D8D" aria-selected="false" class="okki-select-item okki-select-item-option" title="平台询盘">
                                            <div class="okki-select-item-option-content">
                                                <span title="平台询盘" class="tag-wrapper" style="background: rgba(141, 141, 141, 0.2); color: rgb(141, 141, 141);">
                                                  <span class="ellipsis">平台询盘</span>
                                                </span>
                                            </div>
                                            <!---->
                                            <!---->
                                          </div>
                                          <div tag_color="#8D8D8D" aria-selected="false" class="okki-select-item okki-select-item-option" title="报价">
                                            <div class="okki-select-item-option-content">
                                                <span title="报价" class="tag-wrapper" style="background: rgba(141, 141, 141, 0.2); color: rgb(141, 141, 141);">
                                                  <span class="ellipsis">报价</span>
                                                </span>
                                            </div>
                                            <!---->
                                            <!---->
                                          </div>
                                          <div tag_color="#8D8D8D" aria-selected="false" class="okki-select-item okki-select-item-option" title="PO">
                                            <div class="okki-select-item-option-content">
                                                <span title="PO" class="tag-wrapper" style="background: rgba(141, 141, 141, 0.2); color: rgb(141, 141, 141);">
                                                  <span class="ellipsis">PO</span>
                                                </span>
                                            </div>
                                            <!---->
                                            <!---->
                                          </div>
                                          <div tag_color="#8D8D8D" aria-selected="false" class="okki-select-item okki-select-item-option" title="PI">
                                            <div class="okki-select-item-option-content">
                                                <span title="PI" class="tag-wrapper" style="background: rgba(141, 141, 141, 0.2); color: rgb(141, 141, 141);">
                                                  <span class="ellipsis">PI</span>
                                                </span>
                                            </div>
                                            <!---->
                                            <!---->
                                          </div>
                                          <div tag_color="#8D8D8D" aria-selected="false" class="okki-select-item okki-select-item-option" title="PL">
                                            <div class="okki-select-item-option-content">
                                                <span title="PL" class="tag-wrapper" style="background: rgba(141, 141, 141, 0.2); color: rgb(141, 141, 141);">
                                                  <span class="ellipsis">PL</span>
                                                </span>
                                            </div>
                                            <!---->
                                            <!---->
                                          </div>
                                          <div tag_color="#8D8D8D" aria-selected="false" class="okki-select-item okki-select-item-option" title="CI">
                                            <div class="okki-select-item-option-content">
                                                <span title="CI" class="tag-wrapper" style="background: rgba(141, 141, 141, 0.2); color: rgb(141, 141, 141);">
                                                  <span class="ellipsis">CI</span>
                                                </span>
                                            </div>
                                            <!---->
                                            <!---->
                                          </div>
                                        </div>
                                      </div>
                                    </div>
                                    <div class="rc-virtual-list-scrollbar rc-virtual-list-scrollbar-show" style="width: 8px; top: 0px; bottom: 0px; right: 0px; position: absolute; display: none;">
                                      <div class="rc-virtual-list-scrollbar-thumb" style="width: 100%; height: 128px; top: 0px; left: 0px; position: absolute; background: rgba(0, 0, 0, 0.5); border-radius: 99px; cursor: pointer; user-select: none;"></div>
                                    </div>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                      <!---->
                    </div>
                    <!---->
                    <!---->
                  </div>
                </div>
                <div class="okki-row okki-form-item" style="row-gap: 0px;">
                  <div class="okki-col okki-form-item-label" style="width: 7em;">
                    <label class="" title="">
                      <span class="text-medium-8">时间</span>
                      <!---->
                    </label>
                  </div>
                  <div class="okki-col okki-form-item-control">
                    <div class="okki-form-item-control-input">
                      <div class="okki-form-item-control-input-content">
                        <div class="okki-picker okki-picker-range" style="width: 100%;">
                          <div class="okki-picker-input okki-picker-input-active">
                            <input readonly="" placeholder="开始时间" size="12" autocomplete="off" value="">
                          </div>
                          <div class="okki-picker-range-separator">
                              <span aria-label="to" class="okki-picker-separator">
                                <span role="img" aria-label="swap-right" class="anticon anticon-swap-right" icon="[object Object]" spin="false">
                                  <svg focusable="false" class="" data-icon="swap-right" width="1em" height="1em" fill="currentColor" aria-hidden="true" viewBox="0 0 1024 1024">
                                    <path d="M873.1 596.2l-164-208A32 32 0 00684 376h-64.8c-6.7 0-10.4 7.7-6.3 13l144.3 183H152c-4.4 0-8 3.6-8 8v60c0 4.4 3.6 8 8 8h695.9c26.8 0 41.7-30.8 25.2-51.8z"></path>
                                  </svg>
                                </span>
                              </span>
                          </div>
                          <div class="okki-picker-input">
                            <input readonly="" placeholder="结束时间" size="12" autocomplete="off" value="">
                          </div>
                          <div class="okki-picker-active-bar" style="left: 0px; width: 180px; position: absolute;"></div>
                          <span class="okki-picker-suffix">
                              <span role="img" aria-label="calendar" class="anticon anticon-calendar" icon="[object Object]" spin="false">
                                <svg focusable="false" class="" data-icon="calendar" width="1em" height="1em" fill="currentColor" aria-hidden="true" viewBox="64 64 896 896">
                                  <path d="M880 184H712v-64c0-4.4-3.6-8-8-8h-56c-4.4 0-8 3.6-8 8v64H384v-64c0-4.4-3.6-8-8-8h-56c-4.4 0-8 3.6-8 8v64H144c-17.7 0-32 14.3-32 32v664c0 17.7 14.3 32 32 32h736c17.7 0 32-14.3 32-32V216c0-17.7-14.3-32-32-32zm-40 656H184V460h656v380zM184 392V256h128v48c0 4.4 3.6 8 8 8h56c4.4 0 8-3.6 8-8v-48h256v48c0 4.4 3.6 8 8 8h56c4.4 0 8-3.6 8-8v-48h128v136H184z"></path>
                                </svg>
                              </span>
                            </span>
                          <!---->
                          <!---->
                        </div>
                      </div>
                      <!---->
                    </div>
                    <!---->
                    <!---->
                  </div>
                </div>
                <div class="okki-row okki-form-item" style="row-gap: 0px;">
                  <div class="okki-col okki-form-item-label" style="width: 7em;">
                    <label class="" title="">
                      <span>文件夹</span>
                      <!---->
                      <svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" spin="false" rtl="false" viewBox="0 0 48 48" preserveAspectRatio="xMidYMid meet" fill="none" role="presentation" class="ml-4px" data-v-9c1d5ba6="">
                        <g data-v-9c1d5ba6="">
                          <path d="M0 0h48v48H0z" fill="#fff" fill-opacity=".01" data-v-9c1d5ba6=""></path>
                          <path data-follow-stroke="currentColor" data-follow-fill="currentColor" d="M24 44a19.937 19.937 0 0 0 14.142-5.858A19.937 19.937 0 0 0 44 24a19.938 19.938 0 0 0-5.858-14.142A19.937 19.937 0 0 0 24 4 19.938 19.938 0 0 0 9.858 9.858 19.938 19.938 0 0 0 4 24a19.937 19.937 0 0 0 5.858 14.142A19.938 19.938 0 0 0 24 44Z" stroke-width="4" stroke-linejoin="round" fill="#BFBFBF" stroke="#BFBFBF" data-v-9c1d5ba6=""></path>
                          <path d="M24 28.625v-4a6 6 0 1 0-6-6" stroke="#FFF" stroke-width="4" stroke-linecap="round" stroke-linejoin="round" data-v-9c1d5ba6=""></path>
                          <path fill-rule="evenodd" clip-rule="evenodd" d="M24 37.625a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5Z" fill="#FFF" data-v-9c1d5ba6=""></path>
                        </g>
                      </svg>
                      <!---->
                    </label>
                  </div>
                  <div class="okki-col okki-form-item-control">
                    <div class="okki-form-item-control-input">
                      <div class="okki-form-item-control-input-content">
                        <div class="okki-radio-group okki-radio-group-outline mb-4px">
                          <label class="okki-radio-wrapper okki-radio-wrapper-checked">
                              <span class="okki-radio okki-radio-checked">
                                <input type="radio" class="okki-radio-input" value="0" checked="">
                                <span class="okki-radio-inner"></span>
                              </span>
                            <span>全部</span>
                          </label>
                          <label class="okki-radio-wrapper">
                              <span class="okki-radio">
                                <input type="radio" class="okki-radio-input" value="1">
                                <span class="okki-radio-inner"></span>
                              </span>
                            <span>当前文件夹</span>
                          </label>
                          <label class="okki-radio-wrapper">
                              <span class="okki-radio">
                                <input type="radio" class="okki-radio-input" value="2">
                                <span class="okki-radio-inner"></span>
                              </span>
                            <span>指定文件夹</span>
                          </label>
                        </div>
                        <!---->
                      </div>
                      <!---->
                    </div>
                    <!---->
                    <!---->
                  </div>
                </div>
                <div class="okki-row okki-form-item" style="row-gap: 0px;">
                  <div class="okki-col okki-form-item-label" style="width: 7em;">
                    <label class="" title="">
                      <span class="text-medium-8">附件</span>
                      <!---->
                    </label>
                  </div>
                  <div class="okki-col okki-form-item-control">
                    <div class="okki-form-item-control-input">
                      <div class="okki-form-item-control-input-content">
                        <div class="okki-radio-group okki-radio-group-outline mb-4px">
                          <label class="okki-radio-wrapper okki-radio-wrapper-checked">
                              <span class="okki-radio okki-radio-checked">
                                <input type="radio" class="okki-radio-input" value="" checked="">
                                <span class="okki-radio-inner"></span>
                              </span>
                            <span>不限</span>
                          </label>
                          <label class="okki-radio-wrapper">
                              <span class="okki-radio">
                                <input type="radio" class="okki-radio-input" value="1">
                                <span class="okki-radio-inner"></span>
                              </span>
                            <span>含附件</span>
                          </label>
                          <label class="okki-radio-wrapper">
                              <span class="okki-radio">
                                <input type="radio" class="okki-radio-input" value="0">
                                <span class="okki-radio-inner"></span>
                              </span>
                            <span>不含附件</span>
                          </label>
                        </div>
                        <!---->
                        <!---->
                      </div>
                      <!---->
                    </div>
                    <!---->
                    <!---->
                  </div>
                </div>
                <div class="okki-row okki-form-item" style="row-gap: 0px;">
                  <div class="okki-col okki-form-item-label" style="width: 7em;">
                    <label class="" title="">
                      <span class="text-medium-8">打钉</span>
                      <!---->
                    </label>
                  </div>
                  <div class="okki-col okki-form-item-control">
                    <div class="okki-form-item-control-input">
                      <div class="okki-form-item-control-input-content">
                        <div class="okki-radio-group okki-radio-group-outline">
                          <label class="okki-radio-wrapper okki-radio-wrapper-checked">
                              <span class="okki-radio okki-radio-checked">
                                <input type="radio" class="okki-radio-input" value="" checked="">
                                <span class="okki-radio-inner"></span>
                              </span>
                            <span>不限</span>
                          </label>
                          <label class="okki-radio-wrapper">
                              <span class="okki-radio">
                                <input type="radio" class="okki-radio-input" value="1">
                                <span class="okki-radio-inner"></span>
                              </span>
                            <span>有</span>
                          </label>
                          <label class="okki-radio-wrapper">
                              <span class="okki-radio">
                                <input type="radio" class="okki-radio-input" value="0">
                                <span class="okki-radio-inner"></span>
                              </span>
                            <span>无</span>
                          </label>
                        </div>
                      </div>
                      <!---->
                    </div>
                    <!---->
                    <!---->
                  </div>
                </div>
                <div class="okki-row okki-form-item" style="row-gap: 0px;">
                  <div class="okki-col okki-form-item-label" style="width: 7em;">
                    <label class="" title="">
                      <span class="text-medium-8">收发类型</span>
                      <!---->
                    </label>
                  </div>
                  <div class="okki-col okki-form-item-control">
                    <div class="okki-form-item-control-input">
                      <div class="okki-form-item-control-input-content">
                        <div class="okki-radio-group okki-radio-group-outline">
                          <label class="okki-radio-wrapper okki-radio-wrapper-checked">
                              <span class="okki-radio okki-radio-checked">
                                <input type="radio" class="okki-radio-input" value="" checked="">
                                <span class="okki-radio-inner"></span>
                              </span>
                            <span>不限</span>
                          </label>
                          <label class="okki-radio-wrapper">
                              <span class="okki-radio">
                                <input type="radio" class="okki-radio-input" value="1">
                                <span class="okki-radio-inner"></span>
                              </span>
                            <span>收取的</span>
                          </label>
                          <label class="okki-radio-wrapper">
                              <span class="okki-radio">
                                <input type="radio" class="okki-radio-input" value="2">
                                <span class="okki-radio-inner"></span>
                              </span>
                            <span>发送的</span>
                          </label>
                        </div>
                      </div>
                      <!---->
                    </div>
                    <!---->
                    <!---->
                  </div>
                </div>
                <div class="okki-row okki-form-item" style="row-gap: 0px;">
                  <div class="okki-col okki-form-item-label" style="width: 7em;">
                    <label class="" title="">
                      <span class="text-medium-8">回复情况</span>
                      <svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" spin="false" rtl="false" viewBox="0 0 48 48" preserveAspectRatio="xMidYMid meet" fill="none" role="presentation" class="ml-4px" data-v-9c1d5ba6="">
                        <g data-v-9c1d5ba6="">
                          <path d="M0 0h48v48H0z" fill="#fff" fill-opacity=".01" data-v-9c1d5ba6=""></path>
                          <path data-follow-stroke="currentColor" data-follow-fill="currentColor" d="M24 44a19.937 19.937 0 0 0 14.142-5.858A19.937 19.937 0 0 0 44 24a19.938 19.938 0 0 0-5.858-14.142A19.937 19.937 0 0 0 24 4 19.938 19.938 0 0 0 9.858 9.858 19.938 19.938 0 0 0 4 24a19.937 19.937 0 0 0 5.858 14.142A19.938 19.938 0 0 0 24 44Z" stroke-width="4" stroke-linejoin="round" fill="#BFBFBF" stroke="#BFBFBF" data-v-9c1d5ba6=""></path>
                          <path d="M24 28.625v-4a6 6 0 1 0-6-6" stroke="#FFF" stroke-width="4" stroke-linecap="round" stroke-linejoin="round" data-v-9c1d5ba6=""></path>
                          <path fill-rule="evenodd" clip-rule="evenodd" d="M24 37.625a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5Z" fill="#FFF" data-v-9c1d5ba6=""></path>
                        </g>
                      </svg>
                      <!---->
                    </label>
                  </div>
                  <div class="okki-col okki-form-item-control">
                    <div class="okki-form-item-control-input">
                      <div class="okki-form-item-control-input-content">
                        <div class="okki-radio-group okki-radio-group-outline">
                          <label class="okki-radio-wrapper okki-radio-wrapper-checked okki-radio-wrapper-disabled">
                              <span class="okki-radio okki-radio-checked okki-radio-disabled">
                                <input type="radio" disabled="" class="okki-radio-input" value="" checked="">
                                <span class="okki-radio-inner"></span>
                              </span>
                            <span>不限</span>
                          </label>
                          <label class="okki-radio-wrapper okki-radio-wrapper-disabled">
                              <span class="okki-radio okki-radio-disabled">
                                <input type="radio" disabled="" class="okki-radio-input" value="1">
                                <span class="okki-radio-inner"></span>
                              </span>
                            <span>我方已回复</span>
                          </label>
                          <label class="okki-radio-wrapper okki-radio-wrapper-disabled">
                              <span class="okki-radio okki-radio-disabled">
                                <input type="radio" disabled="" class="okki-radio-input" value="2">
                                <span class="okki-radio-inner"></span>
                              </span>
                            <span>我方未回复</span>
                          </label>
                        </div>
                      </div>
                      <!---->
                    </div>
                    <!---->
                    <!---->
                  </div>
                </div>
                <div class="okki-row okki-form-item" style="row-gap: 0px;">
                  <div class="okki-col okki-form-item-label" style="width: 7em;">
                    <label class="" title="">
                      <span class="text-medium-8">发送情况</span>
                      <!---->
                    </label>
                  </div>
                  <div class="okki-col okki-form-item-control">
                    <div class="okki-form-item-control-input">
                      <div class="okki-form-item-control-input-content">
                        <div class="okki-radio-group okki-radio-group-outline">
                          <label class="okki-radio-wrapper okki-radio-wrapper-checked">
                              <span class="okki-radio okki-radio-checked">
                                <input type="radio" class="okki-radio-input" value="" checked="">
                                <span class="okki-radio-inner"></span>
                              </span>
                            <span>不限</span>
                          </label>
                          <label class="okki-radio-wrapper">
                              <span class="okki-radio">
                                <input type="radio" class="okki-radio-input" value="1">
                                <span class="okki-radio-inner"></span>
                              </span>
                            <span>发送失败</span>
                          </label>
                          <label class="okki-radio-wrapper">
                              <span class="okki-radio">
                                <input type="radio" class="okki-radio-input" value="2">
                                <span class="okki-radio-inner"></span>
                              </span>
                            <span>发送成功</span>
                          </label>
                        </div>
                      </div>
                      <!---->
                    </div>
                    <!---->
                    <!---->
                  </div>
                </div>
              </form>
            </div>
            <div class="okki-modal-footer">
              <button class="okki-btn okki-btn-primary okki-btn-round okki-btn-background-ghost" type="button">
                <!---->
                <span>清 空</span>
              </button>
              <button class="okki-btn okki-btn-primary okki-btn-round do-search" type="button">
                <!---->
                <span>筛 选</span>
              </button>
            </div>
          </div>
          <div tabindex="0" aria-hidden="true" style="width: 0px; height: 0px; overflow: hidden; outline: none;"></div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import {EventBus} from "@/api/email/event-bus";

export default {
  data() {
    return {
      searchPage: false,
    }
  },

  methods: {
    open() {
      this.searchPage = true;
    },

    close() {
      this.searchPage = false;
    },
  }
};
</script>
