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
																					<div class="sender-selection">
																						<div class="email-info" style="background: rgb(233, 233, 233); color: rgb(30, 33, 38);">
																							<div class="mailbox-item ellipsis">
																								<!---->
																								<div class="public-mail-tag-wrapper ellipsis" slots="[object Object]">
																									<div class="public-mail-tag-content">
																										<!---->
																										<span title="Joy<sales18@allxchips.com>" class="ellipsis">Joy&lt;sales18@allxchips.com&gt;</span>
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
                <span class="okki-icon-wrap add-icon add-receiver">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																				<path fill-rule="evenodd" clip-rule="evenodd" d="M12 2a10 10 0 100 20 10 10 0 000-20zm0 18a8 8 0 110-16.001A8 8 0 0112 20z"></path>
																				<path d="M13 8a1 1 0 10-2 0v3H8a1 1 0 100 2h3v3a1 1 0 102 0v-3h3a1 1 0 100-2h-3V8z"></path>
																			</svg>
																		</span>
                <div class="cc-bcc-mass-wrapper">
                  <button type="button" class="mm-button mm-button__text mm__theme mm__theme-size__small text-btn">
                    <!---->
                    <!---->取消抄送
                    <!---->
                  </button>
                  <button type="button" class="mm-button mm-button__text mm__theme mm__theme-size__small text-btn">
                    <!---->
                    <!---->取消密送
                    <!---->
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
              <li class="cc-row mail-edit-row">
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
                <span class="okki-icon-wrap add-icon">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																				<path fill-rule="evenodd" clip-rule="evenodd" d="M12 2a10 10 0 100 20 10 10 0 000-20zm0 18a8 8 0 110-16.001A8 8 0 0112 20z"></path>
																				<path d="M13 8a1 1 0 10-2 0v3H8a1 1 0 100 2h3v3a1 1 0 102 0v-3h3a1 1 0 100-2h-3V8z"></path>
																			</svg>
																		</span>
              </li>
              <li class="bcc-row mail-edit-row">
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
                <span class="okki-icon-wrap add-icon">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																				<path fill-rule="evenodd" clip-rule="evenodd" d="M12 2a10 10 0 100 20 10 10 0 000-20zm0 18a8 8 0 110-16.001A8 8 0 0112 20z"></path>
																				<path d="M13 8a1 1 0 10-2 0v3H8a1 1 0 100 2h3v3a1 1 0 102 0v-3h3a1 1 0 100-2h-3V8z"></path>
																			</svg>
																		</span>
              </li>
              <!---->
              <li class="receiver-number-row">
                <div class="tips" style="color: rgb(144, 144, 144);">0 /100 收件人、抄送人和密送的联系人总数不可超过100</div>
              </li>
              <!---->
              <li data-expose-tour="1" class="subject-row mail-edit-row novice-tour-groupmail-title-field">
																		<span class="subject-row-name mail-edit-row-name subject">主题
                                      <!---->
																		</span>
                <div class="value">
                  <input name="subject" autocomplete="on" class="subject" value="">
                </div>
                <div>
                  <button type="button" class="mm-button mm-button__text mm__theme mm__theme-size__small text-btn meeting-mail-btn">
                    <!---->
                    <!---->会议邀约
                    <!---->
                  </button>
                </div>
              </li>
              <!---->
              <li class="add-toolbar-attach">
                <ul class="component-add-toolbar-attach">
                  <!---->
                </ul>
              </li>
              <li class="mail-editor-wrapper">
                <div class="mail-editor" style="position: relative;">
                  <textarea id="mail_editor" placeholder="" style="display: none;" aria-hidden="true"></textarea>
                  <div role="application" class="tox tox-tinymce" style="visibility: hidden; height: 100%;">
                    <div class="tox-editor-container">
                      <div data-alloy-vertical-dir="toptobottom" class="tox-editor-header">
                        <div class="tox-toolbar-overlord">
                          <div role="group" class="tox-toolbar">
                            <div title="" role="toolbar" data-alloy-tabstop="true" tabindex="-1" class="tox-toolbar__group">
                              <button aria-label="撤消" title="撤消" type="button" tabindex="-1" class="tox-tbtn tox-tbtn--disabled" aria-disabled="true">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M6.4 8H12c3.7 0 6.2 2 6.8 5.1.6 2.7-.4 5.6-2.3 6.8a1 1 0 0 1-1-1.8c1.1-.6 1.8-2.7 1.4-4.6-.5-2.1-2.1-3.5-4.9-3.5H6.4l3.3 3.3a1 1 0 1 1-1.4 1.4l-5-5a1 1 0 0 1 0-1.4l5-5a1 1 0 0 1 1.4 1.4L6.4 8z" fill-rule="nonzero"></path>
																											</svg>
																										</span>
                              </button>
                              <button aria-label="重复" title="重复" type="button" tabindex="-1" class="tox-tbtn tox-tbtn--disabled" aria-disabled="true">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M17.6 10H12c-2.8 0-4.4 1.4-4.9 3.5-.4 2 .3 4 1.4 4.6a1 1 0 1 1-1 1.8c-2-1.2-2.9-4.1-2.3-6.8.6-3 3-5.1 6.8-5.1h5.6l-3.3-3.3a1 1 0 1 1 1.4-1.4l5 5a1 1 0 0 1 0 1.4l-5 5a1 1 0 0 1-1.4-1.4l3.3-3.3z" fill-rule="nonzero"></path>
																											</svg>
																										</span>
                              </button>
                              <button aria-label="格式刷" title="格式刷" type="button" tabindex="-1" class="tox-tbtn" aria-pressed="false">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M18 5V4c0-.5-.4-1-1-1H5a1 1 0 0 0-1 1v4c0 .6.5 1 1 1h12c.6 0 1-.4 1-1V7h1v4H9v9c0 .6.4 1 1 1h2c.6 0 1-.4 1-1v-7h8V5h-3z" fill-rule="nonzero"></path>
																											</svg>
																										</span>
                              </button>
                              <button aria-label="清除格式" title="清除格式" type="button" tabindex="-1" class="tox-tbtn">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M13.2 6a1 1 0 0 1 0 .2l-2.6 10a1 1 0 0 1-1 .8h-.2a.8.8 0 0 1-.8-1l2.6-10H8a1 1 0 1 1 0-2h9a1 1 0 0 1 0 2h-3.8zM5 18h7a1 1 0 0 1 0 2H5a1 1 0 0 1 0-2zm13 1.5L16.5 18 15 19.5a.7.7 0 0 1-1-1l1.5-1.5-1.5-1.5a.7.7 0 0 1 1-1l1.5 1.5 1.5-1.5a.7.7 0 0 1 1 1L17.5 17l1.5 1.5a.7.7 0 0 1-1 1z" fill-rule="evenodd"></path>
																											</svg>
																										</span>
                              </button>
                            </div>
                            <div title="" role="toolbar" data-alloy-tabstop="true" tabindex="-1" class="tox-toolbar__group">
                              <button title="Fonts" aria-label="Fonts" aria-haspopup="true" type="button" unselectable="on" tabindex="-1" class="tox-tbtn tox-tbtn--select tox-tbtn--bespoke" aria-expanded="false" style="user-select: none;">
                                <span class="tox-tbtn__select-label">Verdana</span>
                                <div class="tox-tbtn__select-chevron">
                                  <svg width="10" height="10">
                                    <path d="M8.7 2.2c.3-.3.8-.3 1 0 .4.4.4.9 0 1.2L5.7 7.8c-.3.3-.9.3-1.2 0L.2 3.4a.8.8 0 0 1 0-1.2c.3-.3.8-.3 1.1 0L5 6l3.7-3.8z" fill-rule="nonzero"></path>
                                  </svg>
                                </div>
                              </button>
                            </div>
                            <div title="" role="toolbar" data-alloy-tabstop="true" tabindex="-1" class="tox-toolbar__group">
                              <button title="字号" aria-label="字号" aria-haspopup="true" type="button" unselectable="on" tabindex="-1" class="tox-tbtn tox-tbtn--select tox-tbtn--bespoke" aria-expanded="false" style="user-select: none;">
                                <span class="tox-tbtn__select-label">14px</span>
                                <div class="tox-tbtn__select-chevron">
                                  <svg width="10" height="10">
                                    <path d="M8.7 2.2c.3-.3.8-.3 1 0 .4.4.4.9 0 1.2L5.7 7.8c-.3.3-.9.3-1.2 0L.2 3.4a.8.8 0 0 1 0-1.2c.3-.3.8-.3 1.1 0L5 6l3.7-3.8z" fill-rule="nonzero"></path>
                                  </svg>
                                </div>
                              </button>
                            </div>
                            <div title="" role="toolbar" data-alloy-tabstop="true" tabindex="-1" class="tox-toolbar__group">
                              <button aria-label="粗体" title="粗体" type="button" tabindex="-1" class="tox-tbtn" aria-pressed="false">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M7.8 19c-.3 0-.5 0-.6-.2l-.2-.5V5.7c0-.2 0-.4.2-.5l.6-.2h5c1.5 0 2.7.3 3.5 1 .7.6 1.1 1.4 1.1 2.5a3 3 0 0 1-.6 1.9c-.4.6-1 1-1.6 1.2.4.1.9.3 1.3.6s.8.7 1 1.2c.4.4.5 1 .5 1.6 0 1.3-.4 2.3-1.3 3-.8.7-2.1 1-3.8 1H7.8zm5-8.3c.6 0 1.2-.1 1.6-.5.4-.3.6-.7.6-1.3 0-1.1-.8-1.7-2.3-1.7H9.3v3.5h3.4zm.5 6c.7 0 1.3-.1 1.7-.4.4-.4.6-.9.6-1.5s-.2-1-.7-1.4c-.4-.3-1-.4-2-.4H9.4v3.8h4z" fill-rule="evenodd"></path>
																											</svg>
																										</span>
                              </button>
                              <button aria-label="斜体" title="斜体" type="button" tabindex="-1" class="tox-tbtn" aria-pressed="false">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M16.7 4.7l-.1.9h-.3c-.6 0-1 0-1.4.3-.3.3-.4.6-.5 1.1l-2.1 9.8v.6c0 .5.4.8 1.4.8h.2l-.2.8H8l.2-.8h.2c1.1 0 1.8-.5 2-1.5l2-9.8.1-.5c0-.6-.4-.8-1.4-.8h-.3l.2-.9h5.8z" fill-rule="evenodd"></path>
																											</svg>
																										</span>
                              </button>
                              <button aria-label="下划线" title="下划线" type="button" tabindex="-1" class="tox-tbtn" aria-pressed="false">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M16 5c.6 0 1 .4 1 1v5.5a4 4 0 0 1-.4 1.8l-1 1.4a5.3 5.3 0 0 1-5.5 1 5 5 0 0 1-1.6-1c-.5-.4-.8-.9-1.1-1.4a4 4 0 0 1-.4-1.8V6c0-.6.4-1 1-1s1 .4 1 1v5.5c0 .3 0 .6.2 1l.6.7a3.3 3.3 0 0 0 2.2.8 3.4 3.4 0 0 0 2.2-.8c.3-.2.4-.5.6-.8l.2-.9V6c0-.6.4-1 1-1zM8 17h8c.6 0 1 .4 1 1s-.4 1-1 1H8a1 1 0 0 1 0-2z" fill-rule="evenodd"></path>
																											</svg>
																										</span>
                              </button>
                            </div>
                            <div title="" role="toolbar" data-alloy-tabstop="true" tabindex="-1" class="tox-toolbar__group">
                              <div aria-pressed="false" aria-label="文字颜色" title="文字颜色" role="button" aria-haspopup="true" unselectable="on" tabindex="-1" class="tox-split-button" aria-expanded="false" aria-describedby="aria_161907892031693298863779" style="user-select: none;">
																										<span role="presentation" tabindex="-1" class="tox-tbtn">
																											<span class="tox-icon tox-tbtn__icon-wrap">
																												<svg width="19" height="19" viewBox="0 0 24 24">
																													<g fill-rule="evenodd">
																														<path id="tox-icon-text-color__color" d="M3 18h18v3H3z"></path>
																														<path d="M8.7 16h-.8a.5.5 0 0 1-.5-.6l2.7-9c.1-.3.3-.4.5-.4h2.8c.2 0 .4.1.5.4l2.7 9a.5.5 0 0 1-.5.6h-.8a.5.5 0 0 1-.4-.4l-.7-2.2c0-.3-.3-.4-.5-.4h-3.4c-.2 0-.4.1-.5.4l-.7 2.2c0 .3-.2.4-.4.4zm2.6-7.6l-.6 2a.5.5 0 0 0 .5.6h1.6a.5.5 0 0 0 .5-.6l-.6-2c0-.3-.3-.4-.5-.4h-.4c-.2 0-.4.1-.5.4z"></path>
																													</g>
																												</svg>
																											</span>
																										</span>
                                <span role="presentation" tabindex="-1" class="tox-tbtn tox-split-button__chevron">
																											<svg width="10" height="10">
																												<path d="M8.7 2.2c.3-.3.8-.3 1 0 .4.4.4.9 0 1.2L5.7 7.8c-.3.3-.9.3-1.2 0L.2 3.4a.8.8 0 0 1 0-1.2c.3-.3.8-.3 1.1 0L5 6l3.7-3.8z" fill-rule="nonzero"></path>
																											</svg>
																										</span>
                                <span aria-hidden="true" id="aria_161907892031693298863779" style="display: none;">To open the popup, press Shift+Enter</span>
                              </div>
                              <div aria-pressed="false" aria-label="背景色" title="背景色" role="button" aria-haspopup="true" unselectable="on" tabindex="-1" class="tox-split-button" aria-expanded="false" aria-describedby="aria_5170751542051693298863780" style="user-select: none;">
																										<span role="presentation" tabindex="-1" class="tox-tbtn">
																											<span class="tox-icon tox-tbtn__icon-wrap">
																												<svg width="19" height="19" viewBox="0 0 24 24">
																													<g fill-rule="evenodd">
																														<path id="tox-icon-highlight-bg-color__color" d="M3 18h18v3H3z"></path>
																														<path fill-rule="nonzero" d="M7.7 16.7H3l3.3-3.3-.7-.8L10.2 8l4 4.1-4 4.2c-.2.2-.6.2-.8 0l-.6-.7-1.1 1.1zm5-7.5L11 7.4l3-2.9a2 2 0 0 1 2.6 0L18 6c.7.7.7 2 0 2.7l-2.9 2.9-1.8-1.8-.5-.6"></path>
																													</g>
																												</svg>
																											</span>
																										</span>
                                <span role="presentation" tabindex="-1" class="tox-tbtn tox-split-button__chevron">
																											<svg width="10" height="10">
																												<path d="M8.7 2.2c.3-.3.8-.3 1 0 .4.4.4.9 0 1.2L5.7 7.8c-.3.3-.9.3-1.2 0L.2 3.4a.8.8 0 0 1 0-1.2c.3-.3.8-.3 1.1 0L5 6l3.7-3.8z" fill-rule="nonzero"></path>
																											</svg>
																										</span>
                                <span aria-hidden="true" id="aria_5170751542051693298863780" style="display: none;">To open the popup, press Shift+Enter</span>
                              </div>
                            </div>
                            <div title="" role="toolbar" data-alloy-tabstop="true" tabindex="-1" class="tox-toolbar__group">
                              <button aria-label="对齐方式" title="对齐方式" aria-haspopup="true" type="button" tabindex="-1" class="tox-tbtn" aria-expanded="false">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M5 5h14c.6 0 1 .4 1 1s-.4 1-1 1H5a1 1 0 1 1 0-2zm0 4h8c.6 0 1 .4 1 1s-.4 1-1 1H5a1 1 0 1 1 0-2zm0 8h8c.6 0 1 .4 1 1s-.4 1-1 1H5a1 1 0 0 1 0-2zm0-4h14c.6 0 1 .4 1 1s-.4 1-1 1H5a1 1 0 0 1 0-2z" fill-rule="evenodd"></path>
																											</svg>
																										</span>
                              </button>
                              <button title="行间距" aria-label="行间距" aria-haspopup="true" type="button" data-alloy-tabstop="true" unselectable="on" tabindex="-1" class="tox-tbtn tox-tbtn--select" aria-expanded="false" style="user-select: none;">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24" focusable="false">
																												<path d="M21 5a1 1 0 01.1 2H13a1 1 0 01-.1-2H21zm0 4a1 1 0 01.1 2H13a1 1 0 01-.1-2H21zm0 4a1 1 0 01.1 2H13a1 1 0 01-.1-2H21zm0 4a1 1 0 01.1 2H13a1 1 0 01-.1-2H21zM7 3.6l3.7 3.7a1 1 0 01-1.3 1.5h-.1L8 7.3v9.2l1.3-1.3a1 1 0 011.3 0h.1c.4.4.4 1 0 1.3v.1L7 20.4l-3.7-3.7a1 1 0 011.3-1.5h.1L6 16.7V7.4L4.7 8.7a1 1 0 01-1.3 0h-.1a1 1 0 010-1.3v-.1L7 3.6z"></path>
																											</svg>
																										</span>
                                <div class="tox-tbtn__select-chevron">
                                  <svg width="10" height="10">
                                    <path d="M8.7 2.2c.3-.3.8-.3 1 0 .4.4.4.9 0 1.2L5.7 7.8c-.3.3-.9.3-1.2 0L.2 3.4a.8.8 0 0 1 0-1.2c.3-.3.8-.3 1.1 0L5 6l3.7-3.8z" fill-rule="nonzero"></path>
                                  </svg>
                                </div>
                              </button>
                            </div>
                            <div title="" role="toolbar" data-alloy-tabstop="true" tabindex="-1" class="tox-toolbar__group">
                              <button aria-label="插入/编辑链接" title="插入/编辑链接" type="button" tabindex="-1" class="tox-tbtn" aria-pressed="false">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M6.2 12.3a1 1 0 0 1 1.4 1.4l-2.1 2a2 2 0 1 0 2.7 2.8l4.8-4.8a1 1 0 0 0 0-1.4 1 1 0 1 1 1.4-1.3 2.9 2.9 0 0 1 0 4L9.6 20a3.9 3.9 0 0 1-5.5-5.5l2-2zm11.6-.6a1 1 0 0 1-1.4-1.4l2-2a2 2 0 1 0-2.6-2.8L11 10.3a1 1 0 0 0 0 1.4A1 1 0 1 1 9.6 13a2.9 2.9 0 0 1 0-4L14.4 4a3.9 3.9 0 0 1 5.5 5.5l-2 2z" fill-rule="nonzero"></path>
																											</svg>
																										</span>
                              </button>
                              <button aria-label="twemoji" title="twemoji" type="button" tabindex="-1" class="tox-tbtn">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M9 11c.6 0 1-.4 1-1s-.4-1-1-1a1 1 0 0 0-1 1c0 .6.4 1 1 1zm6 0c.6 0 1-.4 1-1s-.4-1-1-1a1 1 0 0 0-1 1c0 .6.4 1 1 1zm-3 5.5c2.1 0 4-1.5 4.4-3.5H7.6c.5 2 2.3 3.5 4.4 3.5zM12 4a8 8 0 1 0 0 16 8 8 0 0 0 0-16zm0 14.5a6.5 6.5 0 1 1 0-13 6.5 6.5 0 0 1 0 13z" fill-rule="nonzero"></path>
																											</svg>
																										</span>
                              </button>
                              <button title="表格" aria-label="表格" aria-haspopup="true" type="button" data-alloy-tabstop="true" unselectable="on" tabindex="-1" class="tox-tbtn tox-tbtn--select" aria-expanded="false" style="user-select: none;">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path fill-rule="nonzero" d="M19 4a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V6c0-1.1.9-2 2-2h14zM5 14v4h6v-4H5zm14 0h-6v4h6v-4zm0-6h-6v4h6V8zM5 12h6V8H5v4z"></path>
																											</svg>
																										</span>
                                <div class="tox-tbtn__select-chevron">
                                  <svg width="10" height="10">
                                    <path d="M8.7 2.2c.3-.3.8-.3 1 0 .4.4.4.9 0 1.2L5.7 7.8c-.3.3-.9.3-1.2 0L.2 3.4a.8.8 0 0 1 0-1.2c.3-.3.8-.3 1.1 0L5 6l3.7-3.8z" fill-rule="nonzero"></path>
                                  </svg>
                                </div>
                              </button>
                            </div>
                            <div title="" role="toolbar" data-alloy-tabstop="true" tabindex="-1" class="tox-toolbar__group">
                              <div aria-pressed="false" aria-label="项目符号" title="项目符号" role="button" aria-haspopup="true" unselectable="on" tabindex="-1" class="tox-split-button" aria-expanded="false" aria-describedby="aria_6998570792071693298863781" style="user-select: none;">
																										<span role="presentation" tabindex="-1" class="tox-tbtn">
																											<span class="tox-icon tox-tbtn__icon-wrap">
																												<svg width="19" height="19" viewBox="0 0 24 24">
																													<path d="M11 5h8c.6 0 1 .4 1 1s-.4 1-1 1h-8a1 1 0 0 1 0-2zm0 6h8c.6 0 1 .4 1 1s-.4 1-1 1h-8a1 1 0 0 1 0-2zm0 6h8c.6 0 1 .4 1 1s-.4 1-1 1h-8a1 1 0 0 1 0-2zM4.5 6c0-.4.1-.8.4-1 .3-.4.7-.5 1.1-.5.4 0 .8.1 1 .4.4.3.5.7.5 1.1 0 .4-.1.8-.4 1-.3.4-.7.5-1.1.5-.4 0-.8-.1-1-.4-.4-.3-.5-.7-.5-1.1zm0 6c0-.4.1-.8.4-1 .3-.4.7-.5 1.1-.5.4 0 .8.1 1 .4.4.3.5.7.5 1.1 0 .4-.1.8-.4 1-.3.4-.7.5-1.1.5-.4 0-.8-.1-1-.4-.4-.3-.5-.7-.5-1.1zm0 6c0-.4.1-.8.4-1 .3-.4.7-.5 1.1-.5.4 0 .8.1 1 .4.4.3.5.7.5 1.1 0 .4-.1.8-.4 1-.3.4-.7.5-1.1.5-.4 0-.8-.1-1-.4-.4-.3-.5-.7-.5-1.1z" fill-rule="evenodd"></path>
																												</svg>
																											</span>
																										</span>
                                <span role="presentation" tabindex="-1" class="tox-tbtn tox-split-button__chevron">
																											<svg width="10" height="10">
																												<path d="M8.7 2.2c.3-.3.8-.3 1 0 .4.4.4.9 0 1.2L5.7 7.8c-.3.3-.9.3-1.2 0L.2 3.4a.8.8 0 0 1 0-1.2c.3-.3.8-.3 1.1 0L5 6l3.7-3.8z" fill-rule="nonzero"></path>
																											</svg>
																										</span>
                                <span aria-hidden="true" id="aria_6998570792071693298863781" style="display: none;">To open the popup, press Shift+Enter</span>
                              </div>
                              <div aria-pressed="false" aria-label="编号列表" title="编号列表" role="button" aria-haspopup="true" unselectable="on" tabindex="-1" class="tox-split-button" aria-expanded="false" aria-describedby="aria_9197271732091693298863782" style="user-select: none;">
																										<span role="presentation" tabindex="-1" class="tox-tbtn">
																											<span class="tox-icon tox-tbtn__icon-wrap">
																												<svg width="19" height="19" viewBox="0 0 24 24">
																													<path d="M10 17h8c.6 0 1 .4 1 1s-.4 1-1 1h-8a1 1 0 0 1 0-2zm0-6h8c.6 0 1 .4 1 1s-.4 1-1 1h-8a1 1 0 0 1 0-2zm0-6h8c.6 0 1 .4 1 1s-.4 1-1 1h-8a1 1 0 1 1 0-2zM6 4v3.5c0 .3-.2.5-.5.5a.5.5 0 0 1-.5-.5V5h-.5a.5.5 0 0 1 0-1H6zm-1 8.8l.2.2h1.3c.3 0 .5.2.5.5s-.2.5-.5.5H4.9a1 1 0 0 1-.9-1V13c0-.4.3-.8.6-1l1.2-.4.2-.3a.2.2 0 0 0-.2-.2H4.5a.5.5 0 0 1-.5-.5c0-.3.2-.5.5-.5h1.6c.5 0 .9.4.9 1v.1c0 .4-.3.8-.6 1l-1.2.4-.2.3zM7 17v2c0 .6-.4 1-1 1H4.5a.5.5 0 0 1 0-1h1.2c.2 0 .3-.1.3-.3 0-.2-.1-.3-.3-.3H4.4a.4.4 0 1 1 0-.8h1.3c.2 0 .3-.1.3-.3 0-.2-.1-.3-.3-.3H4.5a.5.5 0 1 1 0-1H6c.6 0 1 .4 1 1z" fill-rule="evenodd"></path>
																												</svg>
																											</span>
																										</span>
                                <span role="presentation" tabindex="-1" class="tox-tbtn tox-split-button__chevron">
																											<svg width="10" height="10">
																												<path d="M8.7 2.2c.3-.3.8-.3 1 0 .4.4.4.9 0 1.2L5.7 7.8c-.3.3-.9.3-1.2 0L.2 3.4a.8.8 0 0 1 0-1.2c.3-.3.8-.3 1.1 0L5 6l3.7-3.8z" fill-rule="nonzero"></path>
																											</svg>
																										</span>
                                <span aria-hidden="true" id="aria_9197271732091693298863782" style="display: none;">To open the popup, press Shift+Enter</span>
                              </div>
                            </div>
                            <div title="" role="toolbar" data-alloy-tabstop="true" tabindex="-1" class="tox-toolbar__group">
                              <button aria-label="预览" title="预览" type="button" tabindex="-1" class="tox-tbtn">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M3.5 12.5c.5.8 1.1 1.6 1.8 2.3 2 2 4.2 3.2 6.7 3.2s4.7-1.2 6.7-3.2a16.2 16.2 0 0 0 2.1-2.8 15.7 15.7 0 0 0-2.1-2.8c-2-2-4.2-3.2-6.7-3.2a9.3 9.3 0 0 0-6.7 3.2A16.2 16.2 0 0 0 3.2 12c0 .2.2.3.3.5zm-2.4-1l.7-1.2L4 7.8C6.2 5.4 8.9 4 12 4c3 0 5.8 1.4 8.1 3.8a18.2 18.2 0 0 1 2.8 3.7v1l-.7 1.2-2.1 2.5c-2.3 2.4-5 3.8-8.1 3.8-3 0-5.8-1.4-8.1-3.8a18.2 18.2 0 0 1-2.8-3.7 1 1 0 0 1 0-1zm12-3.3a2 2 0 1 0 2.7 2.6 4 4 0 1 1-2.6-2.6z" fill-rule="nonzero"></path>
																											</svg>
																										</span>
                              </button>
                              <button aria-label="全屏" title="全屏" type="button" tabindex="-1" class="tox-tbtn" aria-pressed="false">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M15.3 10l-1.2-1.3 2.9-3h-2.3a.9.9 0 1 1 0-1.7H19c.5 0 .9.4.9.9v4.4a.9.9 0 1 1-1.8 0V7l-2.9 3zm0 4l3 3v-2.3a.9.9 0 1 1 1.7 0V19c0 .5-.4.9-.9.9h-4.4a.9.9 0 1 1 0-1.8H17l-3-2.9 1.3-1.2zM10 15.4l-2.9 3h2.3a.9.9 0 1 1 0 1.7H5a.9.9 0 0 1-.9-.9v-4.4a.9.9 0 1 1 1.8 0V17l2.9-3 1.2 1.3zM8.7 10L5.7 7v2.3a.9.9 0 0 1-1.7 0V5c0-.5.4-.9.9-.9h4.4a.9.9 0 0 1 0 1.8H7l3 2.9-1.3 1.2z" fill-rule="nonzero"></path>
																											</svg>
																										</span>
                              </button>
                              <button type="button" tabindex="-1" class="tox-tbtn" aria-pressed="false">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path fill-rule="nonzero" d="M5.7 7.3a1 1 0 0 0-1.4 1.4l7.7 7.7 7.7-7.7a1 1 0 1 0-1.4-1.4L12 13.6 5.7 7.3z"></path>
																											</svg>
																										</span>
                              </button>
                            </div>
                          </div>
                          <div role="group" class="tox-toolbar">
                            <div title="" role="toolbar" data-alloy-tabstop="true" tabindex="-1" class="tox-toolbar__group">
                              <button title="格式" aria-label="格式" aria-haspopup="true" type="button" unselectable="on" tabindex="-1" class="tox-tbtn tox-tbtn--select tox-tbtn--bespoke" aria-expanded="false" style="user-select: none;">
                                <span class="tox-tbtn__select-label">Div区块</span>
                                <div class="tox-tbtn__select-chevron">
                                  <svg width="10" height="10">
                                    <path d="M8.7 2.2c.3-.3.8-.3 1 0 .4.4.4.9 0 1.2L5.7 7.8c-.3.3-.9.3-1.2 0L.2 3.4a.8.8 0 0 1 0-1.2c.3-.3.8-.3 1.1 0L5 6l3.7-3.8z" fill-rule="nonzero"></path>
                                  </svg>
                                </div>
                              </button>
                              <button title="区块" aria-label="区块" aria-haspopup="true" type="button" unselectable="on" tabindex="-1" class="tox-tbtn tox-tbtn--select tox-tbtn--bespoke" aria-expanded="false" style="user-select: none;">
                                <span class="tox-tbtn__select-label">段落</span>
                                <div class="tox-tbtn__select-chevron">
                                  <svg width="10" height="10">
                                    <path d="M8.7 2.2c.3-.3.8-.3 1 0 .4.4.4.9 0 1.2L5.7 7.8c-.3.3-.9.3-1.2 0L.2 3.4a.8.8 0 0 1 0-1.2c.3-.3.8-.3 1.1 0L5 6l3.7-3.8z" fill-rule="nonzero"></path>
                                  </svg>
                                </div>
                              </button>
                            </div>
                            <div title="" role="toolbar" data-alloy-tabstop="true" tabindex="-1" class="tox-toolbar__group">
                              <button aria-label="减少缩进" title="减少缩进" type="button" tabindex="-1" class="tox-tbtn tox-tbtn--disabled" aria-disabled="true">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M7 5h12c.6 0 1 .4 1 1s-.4 1-1 1H7a1 1 0 1 1 0-2zm5 4h7c.6 0 1 .4 1 1s-.4 1-1 1h-7a1 1 0 0 1 0-2zm0 4h7c.6 0 1 .4 1 1s-.4 1-1 1h-7a1 1 0 0 1 0-2zm-5 4h12a1 1 0 0 1 0 2H7a1 1 0 0 1 0-2zm1.6-3.8a1 1 0 0 1-1.2 1.6l-3-2a1 1 0 0 1 0-1.6l3-2a1 1 0 0 1 1.2 1.6L6.8 12l1.8 1.2z" fill-rule="evenodd"></path>
																											</svg>
																										</span>
                              </button>
                              <button aria-label="增加缩进" title="增加缩进" type="button" tabindex="-1" class="tox-tbtn">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M7 5h12c.6 0 1 .4 1 1s-.4 1-1 1H7a1 1 0 1 1 0-2zm5 4h7c.6 0 1 .4 1 1s-.4 1-1 1h-7a1 1 0 0 1 0-2zm0 4h7c.6 0 1 .4 1 1s-.4 1-1 1h-7a1 1 0 0 1 0-2zm-5 4h12a1 1 0 0 1 0 2H7a1 1 0 0 1 0-2zm-2.6-3.8L6.2 12l-1.8-1.2a1 1 0 0 1 1.2-1.6l3 2a1 1 0 0 1 0 1.6l-3 2a1 1 0 1 1-1.2-1.6z" fill-rule="evenodd"></path>
																											</svg>
																										</span>
                              </button>
                            </div>
                            <div title="" role="toolbar" data-alloy-tabstop="true" tabindex="-1" class="tox-toolbar__group">
                              <button aria-label="下标" title="下标" type="button" tabindex="-1" class="tox-tbtn" aria-pressed="false">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M10.4 10l4.6 4.6-1.4 1.4L9 11.4 4.4 16 3 14.6 7.6 10 3 5.4 4.4 4 9 8.6 13.6 4 15 5.4 10.4 10zM21 19h-5v-1l1-.8 1.7-1.6c.3-.4.5-.8.5-1.2 0-.3 0-.6-.2-.7-.2-.2-.5-.3-.9-.3a2 2 0 0 0-.8.2l-.7.3-.4-1.1 1-.6 1.2-.2c.8 0 1.4.3 1.8.7.4.4.6.9.6 1.5s-.2 1.1-.5 1.6a8 8 0 0 1-1.3 1.3l-.6.6h2.6V19z" fill-rule="nonzero"></path>
																											</svg>
																										</span>
                              </button>
                              <button aria-label="上标" title="上标" type="button" tabindex="-1" class="tox-tbtn" aria-pressed="false">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M15 9.4L10.4 14l4.6 4.6-1.4 1.4L9 15.4 4.4 20 3 18.6 7.6 14 3 9.4 4.4 8 9 12.6 13.6 8 15 9.4zm5.9 1.6h-5v-1l1-.8 1.7-1.6c.3-.5.5-.9.5-1.3 0-.3 0-.5-.2-.7-.2-.2-.5-.3-.9-.3l-.8.2-.7.4-.4-1.2c.2-.2.5-.4 1-.5.3-.2.8-.2 1.2-.2.8 0 1.4.2 1.8.6.4.4.6 1 .6 1.6 0 .5-.2 1-.5 1.5l-1.3 1.4-.6.5h2.6V11z" fill-rule="nonzero"></path>
																											</svg>
																										</span>
                              </button>
                              <button aria-label="删除线" title="删除线" type="button" tabindex="-1" class="tox-tbtn" aria-pressed="false">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<g fill-rule="evenodd">
																													<path d="M15.6 8.5c-.5-.7-1-1.1-1.3-1.3-.6-.4-1.3-.6-2-.6-2.7 0-2.8 1.7-2.8 2.1 0 1.6 1.8 2 3.2 2.3 4.4.9 4.6 2.8 4.6 3.9 0 1.4-.7 4.1-5 4.1A6.2 6.2 0 0 1 7 16.4l1.5-1.1c.4.6 1.6 2 3.7 2 1.6 0 2.5-.4 3-1.2.4-.8.3-2-.8-2.6-.7-.4-1.6-.7-2.9-1-1-.2-3.9-.8-3.9-3.6C7.6 6 10.3 5 12.4 5c2.9 0 4.2 1.6 4.7 2.4l-1.5 1.1z"></path>
																													<path d="M5 11h14a1 1 0 0 1 0 2H5a1 1 0 0 1 0-2z" fill-rule="nonzero"></path>
																												</g>
																											</svg>
																										</span>
                              </button>
                              <button aria-label="引用" title="引用" type="button" tabindex="-1" class="tox-tbtn" aria-pressed="false">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M7.5 17h.9c.4 0 .7-.2.9-.6L11 13V8c0-.6-.4-1-1-1H6a1 1 0 0 0-1 1v4c0 .6.4 1 1 1h2l-1.3 2.7a1 1 0 0 0 .8 1.3zm8 0h.9c.4 0 .7-.2.9-.6L19 13V8c0-.6-.4-1-1-1h-4a1 1 0 0 0-1 1v4c0 .6.4 1 1 1h2l-1.3 2.7a1 1 0 0 0 .8 1.3z" fill-rule="nonzero"></path>
																											</svg>
																										</span>
                              </button>
                              <button aria-label="特殊符号" title="特殊符号" type="button" tabindex="-1" class="tox-tbtn">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M15 18h4l1-2v4h-6v-3.3l1.4-1a6 6 0 0 0 1.8-2.9 6.3 6.3 0 0 0-.1-4.1 5.8 5.8 0 0 0-3-3.2c-.6-.3-1.3-.5-2.1-.5a5.1 5.1 0 0 0-3.9 1.8 6.3 6.3 0 0 0-1.3 6 6.2 6.2 0 0 0 1.8 3l1.4.9V20H4v-4l1 2h4v-.5l-2-1L5.4 15A6.5 6.5 0 0 1 4 11c0-1 .2-1.9.6-2.7A7 7 0 0 1 6.3 6C7.1 5.4 8 5 9 4.5c1-.3 2-.5 3.1-.5a8.8 8.8 0 0 1 5.7 2 7 7 0 0 1 1.7 2.3 6 6 0 0 1 .2 4.8c-.2.7-.6 1.3-1 1.9a7.6 7.6 0 0 1-3.6 2.5v.5z" fill-rule="evenodd"></path>
																											</svg>
																										</span>
                              </button>
                            </div>
                            <div title="" role="toolbar" data-alloy-tabstop="true" tabindex="-1" class="tox-toolbar__group">
                              <button aria-label="从左到右" title="从左到右" type="button" tabindex="-1" class="tox-tbtn tox-tbtn--enabled" aria-pressed="true">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M11 5h7a1 1 0 0 1 0 2h-1v11a1 1 0 0 1-2 0V7h-2v11a1 1 0 0 1-2 0v-6c-.5 0-1 0-1.4-.3A3.4 3.4 0 0 1 7.8 10a3.3 3.3 0 0 1 0-2.8 3.4 3.4 0 0 1 1.8-1.8L11 5zM4.4 16.2L6.2 15l-1.8-1.2a1 1 0 0 1 1.2-1.6l3 2a1 1 0 0 1 0 1.6l-3 2a1 1 0 1 1-1.2-1.6z" fill-rule="evenodd"></path>
																											</svg>
																										</span>
                              </button>
                              <button aria-label="从右到左" title="从右到左" type="button" tabindex="-1" class="tox-tbtn" aria-pressed="false">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M8 5h8v2h-2v12h-2V7h-2v12H8v-7c-.5 0-1 0-1.4-.3A3.4 3.4 0 0 1 4.8 10a3.3 3.3 0 0 1 0-2.8 3.4 3.4 0 0 1 1.8-1.8L8 5zm12 11.2a1 1 0 1 1-1 1.6l-3-2a1 1 0 0 1 0-1.6l3-2a1 1 0 1 1 1 1.6L18.4 15l1.8 1.2z" fill-rule="evenodd"></path>
																											</svg>
																										</span>
                              </button>
                            </div>
                            <div title="" role="toolbar" data-alloy-tabstop="true" tabindex="-1" class="tox-toolbar__group">
                              <button aria-label="显示不可见字符" title="显示不可见字符" type="button" tabindex="-1" class="tox-tbtn" aria-pressed="false">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M10 5h7a1 1 0 0 1 0 2h-1v11a1 1 0 0 1-2 0V7h-2v11a1 1 0 0 1-2 0v-6c-.5 0-1 0-1.4-.3A3.4 3.4 0 0 1 6.8 10a3.3 3.3 0 0 1 0-2.8 3.4 3.4 0 0 1 1.8-1.8L10 5z" fill-rule="evenodd"></path>
																											</svg>
																										</span>
                              </button>
                              <button aria-label="显示区块边框" title="显示区块边框" type="button" tabindex="-1" class="tox-tbtn" aria-pressed="false">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M9 19v2H7v-2h2zm-4 0v2a2 2 0 0 1-2-2h2zm8 0v2h-2v-2h2zm8 0a2 2 0 0 1-2 2v-2h2zm-4 0v2h-2v-2h2zM15 7a1 1 0 0 1 0 2v7a1 1 0 0 1-2 0V9h-1v7a1 1 0 0 1-2 0v-4a2.5 2.5 0 0 1-.2-5H15zM5 15v2H3v-2h2zm16 0v2h-2v-2h2zM5 11v2H3v-2h2zm16 0v2h-2v-2h2zM5 7v2H3V7h2zm16 0v2h-2V7h2zM5 3v2H3c0-1.1.9-2 2-2zm8 0v2h-2V3h2zm6 0a2 2 0 0 1 2 2h-2V3zM9 3v2H7V3h2zm8 0v2h-2V3h2z" fill-rule="evenodd"></path>
																											</svg>
																										</span>
                              </button>
                            </div>
                            <div title="" role="toolbar" data-alloy-tabstop="true" tabindex="-1" class="tox-toolbar__group">
                              <button aria-label="水平分割线" title="水平分割线" type="button" tabindex="-1" class="tox-tbtn">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M4 11h16v2H4z" fill-rule="evenodd"></path>
																											</svg>
																										</span>
                              </button>
                              <button aria-label="不间断空格" title="不间断空格" type="button" tabindex="-1" class="tox-tbtn">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M11 11H8a1 1 0 1 1 0-2h3V6c0-.6.4-1 1-1s1 .4 1 1v3h3c.6 0 1 .4 1 1s-.4 1-1 1h-3v3c0 .6-.4 1-1 1a1 1 0 0 1-1-1v-3zm10 4v5H3v-5c0-.6.4-1 1-1s1 .4 1 1v3h14v-3c0-.6.4-1 1-1s1 .4 1 1z" fill-rule="evenodd"></path>
																											</svg>
																										</span>
                              </button>
                              <button aria-label="分页符" title="分页符" type="button" tabindex="-1" class="tox-tbtn">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<g fill-rule="evenodd">
																													<path d="M5 11c.6 0 1 .4 1 1s-.4 1-1 1a1 1 0 0 1 0-2zm3 0h1c.6 0 1 .4 1 1s-.4 1-1 1H8a1 1 0 0 1 0-2zm4 0c.6 0 1 .4 1 1s-.4 1-1 1a1 1 0 0 1 0-2zm3 0h1c.6 0 1 .4 1 1s-.4 1-1 1h-1a1 1 0 0 1 0-2zm4 0c.6 0 1 .4 1 1s-.4 1-1 1a1 1 0 0 1 0-2zM7 3v5h10V3c0-.6.4-1 1-1s1 .4 1 1v7H5V3c0-.6.4-1 1-1s1 .4 1 1zM6 22a1 1 0 0 1-1-1v-7h14v7c0 .6-.4 1-1 1a1 1 0 0 1-1-1v-5H7v5c0 .6-.4 1-1 1z"></path>
																												</g>
																											</svg>
																										</span>
                              </button>
                            </div>
                            <div title="" role="toolbar" data-alloy-tabstop="true" tabindex="-1" class="tox-toolbar__group">
                              <button aria-label="剪切" title="剪切" type="button" tabindex="-1" class="tox-tbtn">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M18 15c.6.7 1 1.4 1 2.3 0 .8-.2 1.5-.7 2l-.8.5-1 .2c-.4 0-.8 0-1.2-.3a3.9 3.9 0 0 1-2.1-2.2c-.2-.5-.3-1-.2-1.5l-1-1-1 1c0 .5 0 1-.2 1.5-.1.5-.4 1-.9 1.4-.3.4-.7.6-1.2.8l-1.2.3c-.4 0-.7 0-1-.2-.3 0-.6-.3-.8-.5-.5-.5-.8-1.2-.7-2 0-.9.4-1.6 1-2.2A3.7 3.7 0 0 1 8.6 14H9l1-1-4-4-.5-1a3.3 3.3 0 0 1 0-2c0-.4.3-.7.5-1l6 6 6-6 .5 1a3.3 3.3 0 0 1 0 2c0 .4-.3.7-.5 1l-4 4 1 1h.5c.4 0 .8 0 1.2.3.5.2.9.4 1.2.8zm-8.5 2.2l.1-.4v-.3-.4a1 1 0 0 0-.2-.5 1 1 0 0 0-.4-.2 1.6 1.6 0 0 0-.8 0 2.6 2.6 0 0 0-.8.3 2.5 2.5 0 0 0-.9 1.1l-.1.4v.7l.2.5.5.2h.7a2.5 2.5 0 0 0 .8-.3 2.8 2.8 0 0 0 1-1zm2.5-2.8c.4 0 .7-.1 1-.4.3-.3.4-.6.4-1s-.1-.7-.4-1c-.3-.3-.6-.4-1-.4s-.7.1-1 .4c-.3.3-.4.6-.4 1s.1.7.4 1c.3.3.6.4 1 .4zm5.4 4l.2-.5v-.4-.3a2.6 2.6 0 0 0-.3-.8 2.4 2.4 0 0 0-.7-.7 2.5 2.5 0 0 0-.8-.3 1.5 1.5 0 0 0-.8 0 1 1 0 0 0-.4.2 1 1 0 0 0-.2.5 1.5 1.5 0 0 0 0 .7v.4l.3.4.3.4a2.8 2.8 0 0 0 .8.5l.4.1h.7l.5-.2z" fill-rule="evenodd"></path>
																											</svg>
																										</span>
                              </button>
                              <button aria-label="复制" title="复制" type="button" tabindex="-1" class="tox-tbtn">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M16 3H6a2 2 0 0 0-2 2v11h2V5h10V3zm1 4a2 2 0 0 1 2 2v10a2 2 0 0 1-2 2h-7a2 2 0 0 1-2-2V9c0-1.2.9-2 2-2h7zm0 12V9h-7v10h7z" fill-rule="nonzero"></path>
																											</svg>
																										</span>
                              </button>
                              <button aria-label="粘贴" title="粘贴" type="button" tabindex="-1" class="tox-tbtn">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M18 9V5h-2v1c0 .6-.4 1-1 1H9a1 1 0 0 1-1-1V5H6v13h3V9h9zM9 20H6a2 2 0 0 1-2-2V5c0-1.1.9-2 2-2h3.2A3 3 0 0 1 12 1a3 3 0 0 1 2.8 2H18a2 2 0 0 1 2 2v4h1v12H9v-1zm1.5-9.5v9h9v-9h-9zM12 3a1 1 0 0 0-1 1c0 .5.4 1 1 1s1-.5 1-1-.4-1-1-1z" fill-rule="nonzero"></path>
																											</svg>
																										</span>
                              </button>
                              <button aria-label="粘贴为文本" title="粘贴为文本" type="button" tabindex="-1" class="tox-tbtn" aria-pressed="false">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M18 9V5h-2v1c0 .6-.4 1-1 1H9a1 1 0 0 1-1-1V5H6v13h3V9h9zM9 20H6a2 2 0 0 1-2-2V5c0-1.1.9-2 2-2h3.2A3 3 0 0 1 12 1a3 3 0 0 1 2.8 2H18a2 2 0 0 1 2 2v4h1v12H9v-1zm1.5-9.5v9h9v-9h-9zM12 3a1 1 0 0 0-1 1c0 .5.4 1 1 1s1-.5 1-1-.4-1-1-1zm0 9h6v2h-.5l-.5-1h-1v4h.8v1h-3.6v-1h.8v-4h-1l-.5 1H12v-2z" fill-rule="nonzero"></path>
																											</svg>
																										</span>
                              </button>
                            </div>
                            <div title="" role="toolbar" data-alloy-tabstop="true" tabindex="-1" class="tox-toolbar__group">
                              <button aria-label="源代码" title="源代码" type="button" tabindex="-1" class="tox-tbtn">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<g fill-rule="nonzero">
																													<path d="M9.8 15.7c.3.3.3.8 0 1-.3.4-.9.4-1.2 0l-4.4-4.1a.8.8 0 0 1 0-1.2l4.4-4.2c.3-.3.9-.3 1.2 0 .3.3.3.8 0 1.1L6 12l3.8 3.7zM14.2 15.7c-.3.3-.3.8 0 1 .4.4.9.4 1.2 0l4.4-4.1c.3-.3.3-.9 0-1.2l-4.4-4.2a.8.8 0 0 0-1.2 0c-.3.3-.3.8 0 1.1L18 12l-3.8 3.7z"></path>
																												</g>
																											</svg>
																										</span>
                              </button>
                              <button aria-label="打印" title="打印" type="button" tabindex="-1" class="tox-tbtn">
																										<span class="tox-icon tox-tbtn__icon-wrap">
																											<svg width="19" height="19" viewBox="0 0 24 24">
																												<path d="M18 8H6a3 3 0 0 0-3 3v6h2v3h14v-3h2v-6a3 3 0 0 0-3-3zm-1 10H7v-4h10v4zm.5-5c-.8 0-1.5-.7-1.5-1.5s.7-1.5 1.5-1.5 1.5.7 1.5 1.5-.7 1.5-1.5 1.5zm.5-8H6v2h12V5z" fill-rule="nonzero"></path>
																											</svg>
																										</span>
                              </button>
                              <div aria-pressed="false" aria-label="插入日期/时间" title="插入日期/时间" role="button" aria-haspopup="true" unselectable="on" tabindex="-1" class="tox-split-button" aria-expanded="false" aria-describedby="aria_1027602222341693298863806" style="user-select: none;">
																										<span role="presentation" tabindex="-1" class="tox-tbtn">
																											<span class="tox-icon tox-tbtn__icon-wrap">
																												<svg width="19" height="19" viewBox="0 0 24 24">
																													<g fill-rule="nonzero">
																														<path d="M12 19a7 7 0 1 0 0-14 7 7 0 0 0 0 14zm0 2a9 9 0 1 1 0-18 9 9 0 0 1 0 18z"></path>
																														<path d="M16 12h-3V7c0-.6-.4-1-1-1a1 1 0 0 0-1 1v7h5c.6 0 1-.4 1-1s-.4-1-1-1z"></path>
																													</g>
																												</svg>
																											</span>
																										</span>
                                <span role="presentation" tabindex="-1" class="tox-tbtn tox-split-button__chevron">
																											<svg width="10" height="10">
																												<path d="M8.7 2.2c.3-.3.8-.3 1 0 .4.4.4.9 0 1.2L5.7 7.8c-.3.3-.9.3-1.2 0L.2 3.4a.8.8 0 0 1 0-1.2c.3-.3.8-.3 1.1 0L5 6l3.7-3.8z" fill-rule="nonzero"></path>
																											</svg>
																										</span>
                                <span aria-hidden="true" id="aria_1027602222341693298863806" style="display: none;">To open the popup, press Shift+Enter</span>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="tox-anchorbar"></div>
                      </div>
                      <div class="tox-sidebar-wrap">
                        <div class="tox-edit-area">
                          <iframe srcdoc="<!DOCTYPE html><html><body id=&quot;tinymce&quot; class=&quot;mce-content-body &quot; data-id=&quot;mail_editor&quot; contenteditable=&quot;true&quot; spellcheck=&quot;false&quot;><div class=&quot;reply-body&quot; spellcheck=&quot;true&quot;><div style=&quot;font-family: Segoe UI; font-size: 16px; color: #000000;&quot; data-mce-style=&quot;font-family: Segoe UI; font-size: 16px; color: #000000;&quot;><div><br></div><div><br></div><div><br></div></div><!-- CHECK_ATTACHMENT_SPLIT_TAG --></div><div style=&quot;clear: both; font-size: 0; height: 0px; overflow: hidden;&quot; data-mce-style=&quot;clear: both; font-size: 0; height: 0px; overflow: hidden;&quot;><br></div><div class=&quot;xiaoman_kuai_mail_sign&quot; spellcheck=&quot;true&quot;><br></div></body></html>" data-savepage-sameorigin="" id="mail_editor_ifr" frameborder="0" allowtransparency="true" title="Rich Text Area. Press ALT-0 for help." class="tox-edit-area__iframe" data-savepage-key="0-0"></iframe>
                        </div>
                        <div role="complementary" class="tox-sidebar">
                          <div data-alloy-tabstop="true" tabindex="-1" class="tox-sidebar__slider tox-sidebar--sliding-closed" style="width: 0px;">
                            <div class="tox-sidebar__pane-container"></div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div aria-hidden="true" class="tox-throbber" style="display: none;"></div>
                  </div>
                  <div class="mail-plus-drag-upload" style="display: none;">
                    <div class="i_dragUpload_top top-area" style="outline: none;">
                      <div>将文件拖拽至此区域，将以附件方式上传。</div>
                    </div>
                    <div class="i_dragUpload_bottom bottom-area" style="outline: none;">
                      <div>将图片拖拽至此区域，可直接添加至正文。</div>
                    </div>
                  </div>
                </div>
                <div class="component-add-toolbar">
                  <div class="mail-tool flex items-center">
                    <!---->
                    <span class="mm-tooltip tool-item flex items-center">
																					<span class="mm-tooltip-trigger">
																						<div class="tool-item flex items-center" title="">
																							<img data-savepage-src="https://cdn.xiaoman.cn/crm_web/ks/prod/mail_subapp/static/img/appendix.f49c01fe.svg" src="" style="width: 14px; height: 14px; margin-right: 4px;">
																							<span>附件</span>
																							<input id="attachInput" type="file" multiple="" value="">
																						</div>
																					</span>
                      <!---->
																				</span>
                    <div class="tool-item flex items-center">
                      <img data-savepage-src="https://cdn.xiaoman.cn/crm_web/ks/prod/mail_subapp/static/img/cloud.bd4a8ece.svg" src="" style="width: 14px; height: 14px; margin-right: 4px;"> 云文档</div>
                    <div class="mm-dropdown hover:bg-gray-200 w-8 h-7 flex items-center justify-center mail-toolbar-dropdown">
                      <div class="mm-dropdown-trigger">
                        <div class="cursor-pointer flex items-center justify-center w-full h-full">
                          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
                            <path fill-rule="evenodd" clip-rule="evenodd" d="M12 15.5a1 1 0 00.707-.293l6-6a1 1 0 00-1.414-1.414L12 13.086 6.707 7.793a1 1 0 00-1.414 1.414l6 6A1 1 0 0012 15.5z"></path>
                          </svg>
                        </div>
                      </div>
                      <!---->
                    </div>
                    <div class="mm-divider-vertical solid"></div>
                    <!---->
                    <div class="tool-item novice-tour-groupmail-content-field flex items-center">
                      <img data-savepage-src="https://cdn.xiaoman.cn/crm_web/ks/prod/mail_subapp/static/img/template.b98c6551.svg" src="" style="width: 14px; height: 14px; margin-right: 4px;"> 模板</div>
                    <!---->
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
                      <!---->
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
                      <!---->
                    </div>
                  </div>
                  <span class="mail-size">邮件大小：506 B</span>
                  <!---->
                  <!---->
                  <!---->
                  <!---->
                  <!---->
                  <!---->
                  <!---->
                </div>
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
@import '../../static/scss/email/index/92270.59786a6e.css';
@import '../../static/scss/email/index/43960.b19a070c.css';
@import '../../static/scss/email/index/64147.480300b1.css';
@import '../../static/scss/email/index/86683.ec9c38a2.css';
@import '../../static/scss/email/index/skin.min.css';
@import '../../static/scss/email/index/48517.0d1db0ea.css';
</style>
<script>
</script>
