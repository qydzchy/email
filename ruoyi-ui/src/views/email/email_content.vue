<template>
  <div class="mm-split-pane mm-split-pane__right" style="left: 12.2835%;">
    <div class="page-mail-list layout-extraSidebar-content" style="background-color: white;">
													<span>
														<div class="view-mode-three open component-mail-list relative list-with-toolbar">
															<!---->
															<div class="top-block">
																<!---->
                                <!---->
                                <!---->
                                <!---->
															</div>
															<div class="mail-list-container">
																<div class="mm-split mail-three-mode-split">
																	<div class="mm-split-horizontal">
																		<div class="mm-split-pane mm-split-pane__left" style="right: 75%;">
																			<div class="mail-list-scroll-container mail-list-wrap">
																				<div class="header-wrapper" style="">
																					<div class="mail-list-header-tool-wrapper">
																						<div class="mm-tabs mm-tabs__top mm-tabs__align-left mail-list-toggle-tab header-tab">
																							<div class="mm-tabs-header mm-tabs-header__top">
																								<!---->
																								<div class="mm-tabs-nav-wrap mm-tabs-nav-wrap__top">
																									<!---->
																									<div class="mm-tabs-nav-scroll">
																										<div class="mm-tabs-nav" role="tablist" style="transform: translateX(0px);">
																											<div class="mm-tabs-active-bar__top mm-tabs-active-bar" style="width: 68px; transform: translateX(0px);"></div>
																											<div class="mm-tabs-item mm-tabs-item__top mm-tabs-item--active" id="tab-all" aria-controls="pane-all" role="tab" aria-selected="true" tabindex="0" refinfor="true">全部
                                                        <!---->
																											</div>
                                                      <!--																											<div class="mm-tabs-item mm-tabs-item__top" id="tab-1" aria-controls="pane-1" role="tab" aria-selected="false" tabindex="-1" refinfor="true">客户
                                                                                                              &lt;!&ndash;&ndash;&gt;
                                                                                                            </div>
                                                                                                            <div class="mm-tabs-item mm-tabs-item__top" id="tab-2" aria-controls="pane-2" role="tab" aria-selected="false" tabindex="-1" refinfor="true">同事
                                                                                                              &lt;!&ndash;&ndash;&gt;
                                                                                                            </div>
                                                                                                            <div class="mm-tabs-item mm-tabs-item__top" id="tab-3" aria-controls="pane-3" role="tab" aria-selected="false" tabindex="-1" refinfor="true">通讯录
                                                                                                              &lt;!&ndash;&ndash;&gt;
                                                                                                            </div>
                                                                                                            <div class="mm-tabs-item mm-tabs-item__top" id="tab-0" aria-controls="pane-0" role="tab" aria-selected="false" tabindex="-1" refinfor="true">其他
                                                                                                              &lt;!&ndash;&ndash;&gt;
                                                                                                            </div>-->
																										</div>
																									</div>
																								</div>
																							</div>
																							<div class="mm-tabs-content">
																								<div id="pane-all" aria-hidden="false" aria-labelledby="tab-all" class="mm-tab-pane" role="tabpanel"></div>
																								<div id="pane-1" aria-hidden="true" aria-labelledby="tab-1" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
																								<div id="pane-2" aria-hidden="true" aria-labelledby="tab-2" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
																								<div id="pane-3" aria-hidden="true" aria-labelledby="tab-3" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
																								<div id="pane-0" aria-hidden="true" aria-labelledby="tab-0" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
																							</div>
																						</div>
                                            <!---->
                                            <!---->
																						<div class="mm-dropdown more-opt-dropdown">
																							<div class="mm-dropdown-trigger">
																								<span class="okki-icon-wrap more-icon">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																										<path d="M10 12a2 2 0 104 0 2 2 0 00-4 0zm0 7a2 2 0 104 0 2 2 0 00-4 0zm0-14a2 2 0 104 0 2 2 0 00-4 0z"></path>
																									</svg>
																								</span>
																							</div>
                                              <!---->
																						</div>
																					</div>
																				</div>
																				<div class="mail-list-scroller">
																					<!---->
																					<div class="mail-list-scroll-container">
																						<ul class="grouped-list-container bordered mail-grouped-list open">
																							<li  v-for="monthData in localEmailList" :key="Object.keys(monthData)[0]" class="grouped-list-item yesterday">
																								<h2 class="grouped-list-item-title">
																									<span class="time-title">{{ Object.keys(monthData)[0] }}</span>
																									<span class="mail-count"> ( {{monthData[Object.keys(monthData)[0]].length}} 封) </span>
																								</h2>
																								<ul class="plain-list-container bordered">
																									<li
                                                    v-for="email in monthData[Object.keys(monthData)[0]]"
                                                    :key="email.id"
                                                    :class="{'plain-list-item': true, 'active': email.id === activeEmailId}"
                                                    @click="toggleActive(email)">
																										<div class="right-click-menu-handler">
																											<div class="mail-list-item-placeholder-wrapper">
																												<div class="mail-item-container mail-list-item-wrapper open mail-list-item-wrapper-padding" draggable="true" mail-info-icon-map="[object Object]">
																													<div class="mail-list-item-content">
																														<div>
																															<label class="mm-checkbox mail-list-item-checkbox" style="display: none;">
																																<input true-value="true" type="checkbox">
																																<span class="mm-checkbox-input"></span>
                                                                <!---->
																															</label>
																														</div>
																														<div class="addr-info-container" style="display: none;">
																															<div class="mail-list-item-addr-contact-info">
																																<span class="email-addr-box">
                                                                  <span class="mm-tooltip all-type-avatar-wrapper client-stranger small mail-tag-avatar">
                                                                    <span class="mm-tooltip-trigger">
                                                                      <i class="m-icon avatar-icon icon-new-customer-avatar">
                                                                      </i>
                                                                    </span>
                                                                  </span>
                                                                  <!---->
                                                                  <!---->
																																	<span class="email-nickname ellipsis">{{email.title}}</span>
																																	<span class="seperator"></span>
																																	<span class="customer-name ellipsis">{{email.fromer}}</span>
																																</span>
																																<div class="mail-info-icons"></div>
																															</div>
																														</div>
																														<div class="addr-info-operations-wrapper" style="">
																															<div class="addr-info-container open">
																																<div class="mail-list-item-addr-contact-info open">
																																	<span class="email-addr-box">
																																		<span class="mm-tooltip all-type-avatar-wrapper client-stranger small mail-tag-avatar">
                                                                    <span class="mm-tooltip-trigger">
                                                                      <i class="m-icon avatar-icon icon-new-customer-avatar">
                                                                      </i>
                                                                    </span>
                                                                  </span>
                                                                    <!---->
                                                                    <!---->
																																		<span class="email-nickname ellipsis">{{email.fromer}} </span>
                                                                    <!---->
																																	</span>
																																	<div class="mail-info-icons"></div>
																																</div>
																															</div>
																															<div class="right-operations-container open">
																																<div class="mail-list-item-list-right open">
																																	<span class="time ellipsis">{{email.sendTime}}</span>
                                                                  <!---->
                                                                  <!---->
																																	<div v-if="email.fixedFlag" class="mail-pin-wrapper">
                                                                    <span class="okki-icon-wrap mail-pin-icon" data-tips="取消固定" color="#0064ff" @click="toggleFixed(email,  $event)">​<svg data-tips="取消固定" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="#0064ff">
                                                                        <path fill-rule="evenodd" clip-rule="evenodd" d="M8.382 2a.963.963 0 00-.97.956c0 1.33.582 2.526 1.506 3.353l-.606 3.865C6.325 11.39 5 13.564 5 16.044c0 .528.434.956.97.956H11v3.914c0 .6.448 1.086 1 1.086s1-.486 1-1.086V17h5.03c.536 0 .97-.428.97-.956 0-2.507-1.354-4.7-3.376-5.91l-.597-3.777a4.49 4.49 0 001.56-3.4.963.963 0 00-.969-.957H8.382z"></path>
                                                                      </svg>
                                                                    </span>
                                                                  </div>

                                                                  <div v-else class="mail-pin-wrapper">
                                                                    <span class="okki-icon-wrap mail-pin-icon" data-tips="固定" @click="toggleFixed(email,  $event)">​<svg data-tips="固定" xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 25 25" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
                                                                        <path fill-rule="evenodd" clip-rule="evenodd" d="M16.606 3.292a1 1 0 00-1.414 0 4.508 4.508 0 00-1.314 3.428l-3.094 2.253c-2.258-.538-4.729.057-6.484 1.812a1 1 0 000 1.415l3.545 3.544-2.828 2.829a1 1 0 101.414 1.414l2.828-2.828 3.536 3.535a1 1 0 001.414 0c1.774-1.774 2.363-4.279 1.794-6.557l2.198-3.025a4.51 4.51 0 003.502-1.31 1 1 0 000-1.414l-5.097-5.096zm-6.634 11.75L13.43 18.5a4.93 4.93 0 00.529-4.24 1 1 0 01.14-.902l2.837-3.904a1 1 0 011.02-.39 2.57 2.57 0 001.479-.115L16.046 5.56c-.177.453-.22.95-.125 1.431a1 1 0 01-.392 1.001l-3.957 2.882a1 1 0 01-.892.144 4.929 4.929 0 00-4.185.548l3.466 3.466.006.005a.226.226 0 01.005.006z"></path>
                                                                      </svg>
                                                                    </span>
                                                                  </div>
																																</div>
																															</div>
																														</div>
																														<div class="mail-list-item-overview-content open">
																															<div class="con-wrap">
																																<!---->
																																<span class="subject-summary-wrap">
																																	<span class="subject ellipsis">{{email.extractTextFromContent}}</span>
																																	<span class="summary summary-content ellipsis">
																																		<span class="concat-line"> - </span>
																																		<span class="ellipsis">{{ email.extractTextFromContent }}</span>
																																	</span>
																																</span>
																															</div>
																														</div>
																														<div style="display: none;"></div>
																														<div class="right-operations-container" style="display: none;">
																															<div class="mail-list-item-list-right">
																																<span class="time ellipsis">{{email.sendTime}}</span>
                                                                <!---->
																																<div class="pending">
																																	<div class="mm-popover">
																																		<div>
																																			<span class="okki-icon-wrap pending-icon">​<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																																					<path d="M12 6a1 1 0 011 1v4.423l2.964 1.711a1 1 0 11-1 1.732l-3.447-1.99A1 1 0 0111 11.98V7a1 1 0 011-1z"></path>
																																					<path fill-rule="evenodd" clip-rule="evenodd" d="M22 12c0 5.523-4.477 10-10 10S2 17.523 2 12 6.477 2 12 2s10 4.477 10 10zm-2 0a8 8 0 11-16 0 8 8 0 0116 0z"></path>
																																				</svg>
																																			</span>
																																		</div>
                                                                    <!---->
																																	</div>
																																</div>
																																<div v-if="email.fixedFlag" class="mail-pin-wrapper">
                                                                <span class="okki-icon-wrap mail-pin-icon" data-tips="取消固定" color="#0064ff" @click="toggleFixed(email,  $event)">​<svg data-tips="取消固定" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="#0064ff">
                                                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M8.382 2a.963.963 0 00-.97.956c0 1.33.582 2.526 1.506 3.353l-.606 3.865C6.325 11.39 5 13.564 5 16.044c0 .528.434.956.97.956H11v3.914c0 .6.448 1.086 1 1.086s1-.486 1-1.086V17h5.03c.536 0 .97-.428.97-.956 0-2.507-1.354-4.7-3.376-5.91l-.597-3.777a4.49 4.49 0 001.56-3.4.963.963 0 00-.969-.957H8.382z"></path>
                                                                  </svg>
                                                                </span>
                                                              </div>

																														<div v-else class="mail-pin-wrapper">
																															<span class="okki-icon-wrap mail-pin-icon" data-tips="固定" @click="toggleFixed(email,  $event)">​<svg data-tips="固定" xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 25 25" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																																	<path fill-rule="evenodd" clip-rule="evenodd" d="M16.606 3.292a1 1 0 00-1.414 0 4.508 4.508 0 00-1.314 3.428l-3.094 2.253c-2.258-.538-4.729.057-6.484 1.812a1 1 0 000 1.415l3.545 3.544-2.828 2.829a1 1 0 101.414 1.414l2.828-2.828 3.536 3.535a1 1 0 001.414 0c1.774-1.774 2.363-4.279 1.794-6.557l2.198-3.025a4.51 4.51 0 003.502-1.31 1 1 0 000-1.414l-5.097-5.096zm-6.634 11.75L13.43 18.5a4.93 4.93 0 00.529-4.24 1 1 0 01.14-.902l2.837-3.904a1 1 0 011.02-.39 2.57 2.57 0 001.479-.115L16.046 5.56c-.177.453-.22.95-.125 1.431a1 1 0 01-.392 1.001l-3.957 2.882a1 1 0 01-.892.144 4.929 4.929 0 00-4.185.548l3.466 3.466.006.005a.226.226 0 01.005.006z"></path>
																																</svg>
																															</span>
																														</div>
																															</div>
																														</div>
																													</div>
																													<div class="attachment-list-container" style="display: none;">
																														<!---->
																													</div>
																												</div>
																											</div>
																										</div>
																									</li>
																								</ul>
																							</li>
																						</ul>
																					</div>
																					<div class="mm-empty mail-normal-list-empty" style="display: none;">
																						<div class="empty-custom-render">
																							<div class="empty-custom-image">
																								<img src="data:image/png;base64,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">
																							</div>
																							<div class="empty-custom-desc">
																								<span>暂无数据</span>
																							</div>
																						</div>
																					</div>
																				</div>
																				<div class="mail-paging list-page-wrap">
																					<span class="total-count ellipsis" >共 {{total}} 封</span>
																					<div class="quick-jumper">
																						<input
                                              :max="totalPages"
                                              class="mail-paging-input"
                                              v-model="currentPage"
                                              @blur="handlePageInputBlur"
                                              @input="handlePageInputChange">
																						<span class="mail-paging-slash"> / </span>
																						<span class="page-unit">{{totalPages}}  页</span>
																					</div>
																					<div :class="['mail-paging-btn', 'left-btn', { 'disabled': currentPage === 1 }]" @click="prevPage">
																						<span class="okki-icon-wrap m-icon">​<svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																								<path fill-rule="evenodd" clip-rule="evenodd" d="M15.707 5.293a1 1 0 010 1.414L10.414 12l5.293 5.293a1 1 0 01-1.414 1.414l-6-6a1 1 0 010-1.414l6-6a1 1 0 011.414 0z"></path>
																							</svg>
																						</span>
																					</div>
																					<div :class="['mail-paging-btn', 'right-btn', { 'disabled': currentPage === totalPages }]" @click="nextPage">
																						<span class="okki-icon-wrap m-icon">​<svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																								<path fill-rule="evenodd" clip-rule="evenodd" d="M8.293 5.293a1 1 0 011.414 0l6 6a1 1 0 010 1.414l-6 6a1 1 0 01-1.414-1.414L13.586 12 8.293 6.707a1 1 0 010-1.414z"></path>
																							</svg>
																						</span>
																					</div>
																				</div>
																			</div>
																		</div>
																		<div class="mm-split-trigger-wrap" style="left: 25%;">
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
																		<div class="mm-split-pane mm-split-pane__right" style="left: 25%;">
																			<div class="detail-with-tollbar">
																				<div class="promotion-wrapper"></div>
																				<div class="mail-toolbar-wrapper open-detail">
																					<div class="mail-toolbar-left">
																						<!---->
																						<span class="mm-tooltip mail-toolbar-btn-item">
																							<span class="mm-tooltip-trigger">
																								<span>
																									<span class="okki-icon-wrap tool-bar-icon-item">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																											<path fill-rule="evenodd" clip-rule="evenodd" d="M9.707 5.293a1 1 0 010 1.414L4.414 12l5.293 5.293a1 1 0 01-1.414 1.414l-6-6a1 1 0 010-1.414l6-6a1 1 0 011.414 0z"></path>
																											<path fill-rule="evenodd" clip-rule="evenodd" d="M3.5 12a1 1 0 011-1H21a1 1 0 110 2H4.5a1 1 0 01-1-1z"></path>
																										</svg>
																									</span>
																								</span>
																							</span>
                                              <!---->
																						</span>
																						<span class="mm-tooltip mail-toolbar-btn-item">
																							<span class="mm-tooltip-trigger">
																								<span>
																									<span class="okki-icon-wrap tool-bar-icon-item">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																											<path fill-rule="evenodd" clip-rule="evenodd" d="M10 2a4 4 0 00-4 4v2a4 4 0 00-4 4v6a4 4 0 004 4h6a4 4 0 004-4h2a4 4 0 004-4V6a4 4 0 00-4-4h-8zm2 18a2 2 0 002-2h-2a1 1 0 110-2h6a2 2 0 002-2V6a2 2 0 00-2-2h-8a2 2 0 00-2 2v6a1 1 0 11-2 0v-2a2 2 0 00-2 2v6a2 2 0 002 2h6zm2.889-12l-8.146 7.331a1 1 0 101.338 1.487L16 9.69V11a1 1 0 102 0V7a1 1 0 00-1-1h-4a1 1 0 100 2h1.889z"></path>
																										</svg>
																									</span>
																								</span>
																							</span>
                                              <!---->
																						</span>
																						<span class="mm-tooltip mail-toolbar-btn-item">
																							<span class="mm-tooltip-trigger">
																								<div class="mm-popover">
																									<div>
																										<span class="">
																											<span class="okki-icon-wrap tool-bar-icon-item">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																													<path d="M12 6a1 1 0 011 1v4.423l2.964 1.711a1 1 0 11-1 1.732l-3.447-1.99A1 1 0 0111 11.98V7a1 1 0 011-1z"></path>
																													<path fill-rule="evenodd" clip-rule="evenodd" d="M22 12c0 5.523-4.477 10-10 10S2 17.523 2 12 6.477 2 12 2s10 4.477 10 10zm-2 0a8 8 0 11-16 0 8 8 0 0116 0z"></path>
																												</svg>
																											</span>
																										</span>
																									</div>
                                                  <!---->
																								</div>
																							</span>
                                              <!---->
																						</span>
																						<span class="mm-tooltip mail-toolbar-btn-item">
																							<span class="mm-tooltip-trigger">
																								<span>
																									<span class="okki-icon-wrap tool-bar-icon-item">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																											<path fill-rule="evenodd" clip-rule="evenodd" d="M10.2 2a2 2 0 00-1.765 1.059L7.4 5H3a1 1 0 000 2h1.5v12a3 3 0 003 3h9a3 3 0 003-3V7H21a1 1 0 100-2h-4.4l-1.035-1.941A2 2 0 0013.8 2h-3.6zm7.3 5h-11v12a1 1 0 001 1h9a1 1 0 001-1V7zm-3.167-2L13.8 4h-3.6l-.533 1h4.666zM10 9.5a1 1 0 011 1v6a1 1 0 11-2 0v-6a1 1 0 011-1zm5 1a1 1 0 10-2 0v6a1 1 0 102 0v-6z"></path>
																										</svg>
																									</span>
																								</span>
																							</span>
                                              <!---->
																						</span>
																						<span class="mm-tooltip mail-toolbar-btn-item">
																							<span class="mm-tooltip-trigger">
																								<span>
																									<span class="okki-icon-wrap tool-bar-icon-item">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																											<path d="M8 10a2 2 0 100-4 2 2 0 000 4z"></path>
																											<path fill-rule="evenodd" clip-rule="evenodd" d="M4.033 4.033l7.115.093 8.561 8.561a1 1 0 010 1.414l-5.608 5.608a1 1 0 01-1.414 0l-8.56-8.561-.094-7.115zm8.242-1.609a1 1 0 00-.694-.292l-7.522-.099A2 2 0 002.033 4.06l.099 7.522a1 1 0 00.292.694l8.849 8.848a3 3 0 004.243 0l5.607-5.607a3 3 0 000-4.243l-8.848-8.849zM8 10a2 2 0 100-4 2 2 0 000 4z"></path>
																										</svg>
																									</span>
																								</span>
																							</span>
                                              <!---->
																						</span>
																						<span class="mm-tooltip mail-toolbar-btn-item">
																							<span class="mm-tooltip-trigger">
																								<span>
																									<span class="okki-icon-wrap tool-bar-icon-item">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																											<path fill-rule="evenodd" clip-rule="evenodd" d="M2 6a3 3 0 013-3h4.379a3 3 0 012.108.866l1.824 1.8H19a3 3 0 013 3V18a3 3 0 01-3 3H5a3 3 0 01-3-3V6zm2 0a1 1 0 011-1h4.379a1 1 0 01.703.289l1.823 1.8a2 2 0 001.406.578H19a1 1 0 011 1V10H4V6zm16 6v6a1 1 0 01-1 1H5a1 1 0 01-1-1v-6h16z"></path>
																										</svg>
																									</span>
																								</span>
																							</span>
                                              <!---->
																						</span>
																						<span class="mm-tooltip mail-toolbar-btn-item">
																							<span class="mm-tooltip-trigger">
																								<span>
																									<span class="okki-icon-wrap tool-bar-icon-item">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																											<path d="M10 12a2 2 0 104 0 2 2 0 00-4 0zm0 7a2 2 0 104 0 2 2 0 00-4 0zm0-14a2 2 0 104 0 2 2 0 00-4 0z"></path>
																										</svg>
																									</span>
																								</span>
																							</span>
                                              <!---->
																						</span>
																					</div>
																					<div class="mail-toolbar-right">
																						<!---->
																						<div class="mail-paging tool-bar-paging">
																							<span class="total-count ellipsis" title="共 759 封">共 759 封</span>
                                              <!---->
																							<div class="mail-paging-btn left-btn disabled">
																								<span class="okki-icon-wrap m-icon">​<svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																										<path fill-rule="evenodd" clip-rule="evenodd" d="M15.707 5.293a1 1 0 010 1.414L10.414 12l5.293 5.293a1 1 0 01-1.414 1.414l-6-6a1 1 0 010-1.414l6-6a1 1 0 011.414 0z"></path>
																									</svg>
																								</span>
																							</div>
																							<div class="mail-paging-btn right-btn">
																								<span class="okki-icon-wrap m-icon">​<svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																										<path fill-rule="evenodd" clip-rule="evenodd" d="M8.293 5.293a1 1 0 011.414 0l6 6a1 1 0 010 1.414l-6 6a1 1 0 01-1.414-1.414L13.586 12 8.293 6.707a1 1 0 010-1.414z"></path>
																									</svg>
																								</span>
																							</div>
																						</div>
                                            <!---->
																						<span class="mm-tooltip mail-toolbar-btn-item">
																							<span class="mm-tooltip-trigger">
																								<a href="#" @click.prevent="$emit('switch', 'setup')" class="mail-toolbar-btn-item noright" tag="div">
																									<span class="okki-icon-wrap setting-icon">​<svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																											<path fill-rule="evenodd" clip-rule="evenodd" d="M10.783 3.287c-.439-.45-1.319-1.022-2.319-.644a9.99 9.99 0 00-2.799 1.619c-.826.677-.771 1.723-.602 2.33.12.426.074.895-.164 1.308a1.692 1.692 0 01-1.052.797c-.61.156-1.546.632-1.717 1.689a10.063 10.063 0 000 3.228c.171 1.056 1.107 1.533 1.717 1.689.43.11.813.384 1.052.797.238.413.284.882.164 1.309-.17.606-.224 1.652.602 2.329a9.99 9.99 0 002.8 1.619c1 .378 1.879-.194 2.318-.645.31-.317.74-.512 1.217-.512.477 0 .907.195 1.217.512.439.45 1.319 1.023 2.319.645a9.99 9.99 0 002.799-1.62c.826-.676.771-1.722.602-2.328a1.692 1.692 0 01.164-1.31 1.692 1.692 0 011.052-.796c.61-.156 1.546-.633 1.717-1.69a10.064 10.064 0 000-3.227c-.171-1.057-1.107-1.533-1.717-1.689a1.692 1.692 0 01-1.052-.797 1.692 1.692 0 01-.164-1.309c.17-.606.224-1.652-.602-2.329a9.99 9.99 0 00-2.8-1.619c-1-.378-1.879.194-2.318.644-.31.318-.74.513-1.217.513-.477 0-.907-.195-1.217-.513zm-3.82 2.497a7.99 7.99 0 012.171-1.256h.002c.048.019.13.067.216.156A3.692 3.692 0 0012 5.8a3.692 3.692 0 002.648-1.116.651.651 0 01.216-.155h.002c.791.303 1.523.73 2.17 1.255l.001.002a.654.654 0 01-.026.266c-.259.923-.16 1.95.358 2.848a3.691 3.691 0 002.289 1.735c.12.03.203.077.243.11h.001a8.063 8.063 0 010 2.509v.001a.652.652 0 01-.244.11c-.93.237-1.77.837-2.289 1.735a3.692 3.692 0 00-.358 2.848c.034.12.035.215.026.265v.003a7.989 7.989 0 01-2.171 1.255h-.002a.652.652 0 01-.216-.155A3.692 3.692 0 0012 18.2a3.692 3.692 0 00-2.648 1.116.653.653 0 01-.216.155h-.002a7.988 7.988 0 01-2.17-1.255l-.001-.003a.652.652 0 01.026-.265c.259-.924.16-1.95-.358-2.848a3.692 3.692 0 00-2.289-1.735.652.652 0 01-.243-.11l-.001-.001a8.067 8.067 0 010-2.508v-.001a.653.653 0 01.244-.11A3.692 3.692 0 006.631 8.9a3.692 3.692 0 00.358-2.848.652.652 0 01-.026-.266v-.002zm2.887 6.178a2.15 2.15 0 114.3 0 2.15 2.15 0 01-4.3 0zM12 7.812a4.15 4.15 0 100 8.3 4.15 4.15 0 000-8.3z"></path>
																										</svg>
																									</span>
																								</a>
																							</span>
                                              <!---->
																						</span>
                                            <!---->
                                            <!---->
																					</div>
																					<div class="mail-drop-menu-wrapper" style="width: 220px; top: 0px; left: 0px; display: none;">
																						<ul class="mail-drop-menu">
																							<!---->
																						</ul>
                                            <!---->
                                            <!---->
																					</div>
																				</div>
																				<div class="mail-detail-component">
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
                                          <!---->
																					<div class="mail-detail-fixed-header mail-detail-header">
																						<h1 class="mail-detail-fixed-header--title ellipsis">{{currentEmailDetail.title}}</h1>
																						<div class="mail-detail-fixed-header--opts__left">
                                              <span v-if="currentEmailDetail.fixedFlag" class="okki-icon-wrap mail-pin-icon" data-tips="取消固定" color="#0064ff" @click="toggleFixed(currentEmailDetail,  $event)">​<svg data-tips="取消固定" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="#0064ff">
                                                                        <path fill-rule="evenodd" clip-rule="evenodd" d="M8.382 2a.963.963 0 00-.97.956c0 1.33.582 2.526 1.506 3.353l-.606 3.865C6.325 11.39 5 13.564 5 16.044c0 .528.434.956.97.956H11v3.914c0 .6.448 1.086 1 1.086s1-.486 1-1.086V17h5.03c.536 0 .97-.428.97-.956 0-2.507-1.354-4.7-3.376-5.91l-.597-3.777a4.49 4.49 0 001.56-3.4.963.963 0 00-.969-.957H8.382z"></path>
                                                                      </svg>
                                                                    </span>

                                              <span v-else class="okki-icon-wrap mail-pin-icon" data-tips="固定" @click="toggleFixed(currentEmailDetail,  $event)">​<svg data-tips="固定" xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 25 25" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
                                                                        <path fill-rule="evenodd" clip-rule="evenodd" d="M16.606 3.292a1 1 0 00-1.414 0 4.508 4.508 0 00-1.314 3.428l-3.094 2.253c-2.258-.538-4.729.057-6.484 1.812a1 1 0 000 1.415l3.545 3.544-2.828 2.829a1 1 0 101.414 1.414l2.828-2.828 3.536 3.535a1 1 0 001.414 0c1.774-1.774 2.363-4.279 1.794-6.557l2.198-3.025a4.51 4.51 0 003.502-1.31 1 1 0 000-1.414l-5.097-5.096zm-6.634 11.75L13.43 18.5a4.93 4.93 0 00.529-4.24 1 1 0 01.14-.902l2.837-3.904a1 1 0 011.02-.39 2.57 2.57 0 001.479-.115L16.046 5.56c-.177.453-.22.95-.125 1.431a1 1 0 01-.392 1.001l-3.957 2.882a1 1 0 01-.892.144 4.929 4.929 0 00-4.185.548l3.466 3.466.006.005a.226.226 0 01.005.006z"></path>
                                                                      </svg>
                                                                    </span>

<!--																							<a v-else class="mail-detail-fixed-header&#45;&#45;opts__left&#45;&#45;item" data-savepage-href="/pro/mail/detail?name=mailProUnread&mail_id=9442921655850&from_ui_type=1&skipViewPrivilege=0" href="https://crm.xiaoman.cn/pro/mail/detail?name=mailProUnread&mail_id=9442921655850&from_ui_type=1&skipViewPrivilege=0" target="_blank">
																								<span class="mm-tooltip">
																									<span class="mm-tooltip-trigger">
																										<span class="okki-icon-wrap">​<svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																												<path d="M6 4a2 2 0 00-2 2v12a2 2 0 002 2h12a2 2 0 002-2v-3.4a1 1 0 112 0V18a4 4 0 01-4 4H6a4 4 0 01-4-4V6a4 4 0 014-4h4.3a1 1 0 110 2H6z"></path>
																												<path d="M20.97 2.243a1 1 0 011 1v6.485a1 1 0 11-2 0V5.639l-7.273 7.078a1 1 0 11-1.394-1.434l7.235-7.04h-4.053a1 1 0 110-2h6.486z"></path>
																											</svg>
																										</span>
																									</span>
                                                  &lt;!&ndash;&ndash;&gt;
																								</span>
																							</a>-->
                                              <!---->
																						</div>

																						<div class="mail-detail-fixed-header--opts__right">
																							<!---->
                                              <!---->
																							<div class="mail-detail-operations">
																								<div class="selected">
																									<span class="ellipsis value time">{{currentEmailDetail.sendDate}}</span>
																									<div class="all-border detail-operations-btn">
																										<i class="icon-reply-line m-icon" @click="toggleReply(currentEmailDetail)"></i>
																										<i class="icon-reply-all-line m-icon"></i>
                                                    <!---->
                                                    <!---->
																									</div>
																									<div class="mm-popover">
																										<div>
																											<span class="icon-caret-down m-icon"></span>
																										</div>
                                                    <!---->
																									</div>
																								</div>
																							</div>
																						</div>
																					</div>
																					<div id="mailDetailScrollContainer" class="scroll-container">
																						<div class="components-container">
																							<div class="mail-detail-info-wrapper">
																								<div class="state-row">
																									<div class="state-row--icon__wrapper"></div>
                                                  <!---->
																									<div class="mail-plus-tag-list state-row--tag" style="display: none;"></div>
																								</div>
																								<div class="mail-detail--info">
																									<div class="mail-info--sender">
																										<span class="mm-tooltip all-type-avatar-wrapper client-xiaoman-system-avatar small">
																											<span class="mm-tooltip-trigger">
																												<i class="m-icon avatar-icon icon-xiaoman-avatar"></i>
																											</span>
                                                      <!---->
																										</span>
																										<span style="margin: 0px 4px;">
																											<span>
																												<span style="color: #2a2a2a;">{{currentEmailDetail.fromName}} </span>&lt;{{currentEmailDetail.fromer}}&gt;
																											</span> 发给
																										</span>
																									</div>
																									<div style="width: 0px; flex: 1 1 0%; margin-right: 20px;">
																										<ul style="display: inline-flex; flex-wrap: unset; overflow: hidden; max-width: 100%;">
																											<li>
																												<div>
																													<span class="mm-tooltip all-type-avatar-wrapper client-colleague small" style="margin-right: 4px;">
																														<span class="mm-tooltip-trigger">
																															<i class="m-icon avatar-icon icon-f-colleague"></i>
																														</span>
                                                            <!---->
																													</span>
																													<span style="color: rgb(42, 42, 42);">{{currentEmailDetail.receiverName}}</span>
																													<span style="color: rgb(144, 144, 144); margin-right: 8px;">&lt;{{currentEmailDetail.receiverEmail}}&gt;</span>
																												</div>
																											</li>
																										</ul>
																									</div>
																									<button type="button" class="mm-button mm-button__text mm__theme mm__theme-size__small mail-contact-info--toggler">
																										<!---->
                                                    <!---->详细信息 <i class="m-icon icon-packUp mail-contact-info--toggler--icon" style="font-size: 12px; color: rgb(0, 100, 255);"></i>
                                                    <!---->
																									</button>
																								</div>
<!--																								<div class="mail-detail-status-bars">
																									&lt;!&ndash;&ndash;&gt;
																									<div class="mail-timezone mail-detail-status-bar-item">&lt;noreply@k.xiaomanmail.com&gt; <span>暂未查询到该客户的当地时间</span>
                                                    &lt;!&ndash;&ndash;&gt;
																									</div>
                                                  &lt;!&ndash;&ndash;&gt;
                                                  &lt;!&ndash;&ndash;&gt;
																									<div class="mail-detail&#45;&#45;sent-status-bar" update-list="function () { [native code] }">
																										&lt;!&ndash;&ndash;&gt;
                                                    &lt;!&ndash;&ndash;&gt;
                                                    &lt;!&ndash;&ndash;&gt;
																									</div>
																									<div class="mail-detail-status-bar-wrapper">
																										&lt;!&ndash;&ndash;&gt;
                                                    &lt;!&ndash;&ndash;&gt;
																									</div>
                                                  &lt;!&ndash;&ndash;&gt;
																								</div>
																								<div class="mail-detail-translate">
																									<div class="translate-content">
																										<span class="mm__theme mm__theme-size__small mm__theme-border__less mm-select">
																											<div class="mm-selector&#45;&#45;single mm-selector" tabindex="0">
																												&lt;!&ndash;&ndash;&gt;
																												<div class="mm-selector-rendered">
																													<span title="英文" class="mm-selector-selected-value" style="opacity: 1;">英文</span>
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
																										<span style="color: rgb(144, 144, 144); margin: 0px 12px;">&gt;</span>
																										<span class="mm__theme mm__theme-size__small mm__theme-border__less mm-select">
																											<div class="mm-selector&#45;&#45;single mm-selector" tabindex="0">
																												&lt;!&ndash;&ndash;&gt;
																												<div class="mm-selector-rendered">
																													<span title="俄语" class="mm-selector-selected-value" style="opacity: 1;">俄语</span>
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
																									<button type="button" class="mm-button mm-button__text mm__theme mm__theme-size__small translate-btn">
																										&lt;!&ndash;&ndash;&gt;
                                                    &lt;!&ndash;&ndash;&gt;翻译邮件
                                                    &lt;!&ndash;&ndash;&gt;
																									</button>
																								</div>-->
																							</div>
                                              <!---->
																							<div class="mail-content-body mail-detail--content">
																								<!---->
																								<iframe :srcdoc="currentEmailDetail.content" data-savepage-sameorigin="" id="mail-content-iframe-1690875340592" style="width: 661px; height: 640px;" data-savepage-key="0-0"></iframe>
                                                <!---->
																							</div>
																						</div>
																					</div>
																					<div class="go-top-or-bottom">
																						<i class="m-icon icon-top go-top" style=""></i>
																						<i class="m-icon icon-top go-bottom" style="display: none;"></i>
																					</div>
																					<div class="quick-reply-container" contact-list="[object Object]">
																						<!---->
																						<div v-if="!isReplying" class="mail-detail-quick-reply" @click="toggleQuickReply">
																							<div class="expand-trigger">快速回复</div>
																						</div>

                                            <div v-if="isReplying" class="mail-detail-quick-reply">
                                              <span class="mm-textarea-wrap">
                                                <textarea placeholder="快速回复" class="mm-textarea" v-model="replyContent" style="resize: none; height: 120px; min-height: 120px; max-height: 358px;"></textarea>
                                              </span>
                                              <button type="button" class="mm-button quick-reply-btn" @click="toggleQuickReply">
                                                <span>取消</span>
                                              </button>
                                              <button id="report-stat-quick-reply-btn-confirm" :disabled="isSending" type="button" class="mm-button mm-button__primary quick-reply-btn" @click="quickReply">
                                                <span>发送</span>
                                              </button>
                                            </div>
																					</div>
                                          <!---->
																				</div>
																			</div>
																			<div
                                        class="mail-side-card slide-fade"
                                        :class="isRightPanelExpanded ? 'expanding' : 'collapsing'">
																				<span class="mail-position-toogle" @click="toggleRightPanel">
																					<i class="m-icon icon-left-small"></i>
																				</span>
																				<div cur-active-mail-id="" style="height: 100%;">
																					<div class="mail-information-extension">
																						<div class="contact-info" cur-active-mail-id="">
																							<!---->
                                              <!---->
                                              <!---->
                                              <!--																							<div class="contact-actions-wrap" cur-active-mail-id="" time-show="">
                                                                                              <div class="assistant-logo">
                                                                                                <img data-savepage-src="https://cdn.xiaoman.cn/crm_web/ks/prod/mail_subapp/static/img/assistant-logo.5597975b..png" src="data:image/webp;base64,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">
                                                                                              </div>
                                                                                              &lt;!&ndash;&ndash;&gt;
                                                                                              <div class="mm-dropdown">
                                                                                                <div class="mm-dropdown-trigger">
                                                                                                  <span class="okki-icon-wrap actions-icon">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
                                                                                                      <path d="M13 11h3a1 1 0 110 2h-3v3a1 1 0 11-2 0v-3H8a1 1 0 010-2h3V8a1 1 0 112 0v3z"></path>
                                                                                                      <path fill-rule="evenodd" clip-rule="evenodd" d="M5 2a3 3 0 00-3 3v14a3 3 0 003 3h14a3 3 0 003-3V5a3 3 0 00-3-3H5zm14 2H5a1 1 0 00-1 1v14a1 1 0 001 1h14a1 1 0 001-1V5a1 1 0 00-1-1z"></path>
                                                                                                    </svg>
                                                                                                  </span>
                                                                                                </div>
                                                                                                &lt;!&ndash;&ndash;&gt;
                                                                                              </div>
                                                                                              &lt;!&ndash;&ndash;&gt;
                                                                                            </div>-->
																							<div>
																								<!---->
																								<div class="contact-info-user">
																									<span class="mm-tooltip all-type-avatar-wrapper client-xiaoman-system-avatar large">
																										<span class="mm-tooltip-trigger">
																											<i class="m-icon avatar-icon icon-xiaoman-avatar"></i>
																										</span>
                                                    <!---->
																									</span>
																									<div class="contact-info-user-info">
																										<p class="" title="noreply">
																											<span class="info-text ellipsis">noreply</span>
                                                      <!---->
																										</p>
																										<p title="noreply@k.xiaomanmail.com">
																											<!---->
																											<span>
																												<a title="noreply@k.xiaomanmail.com" data-savepage-href="/pro/mail/edit?receiver=noreply@k.xiaomanmail.com" href="https://crm.xiaoman.cn/pro/mail/edit?receiver=noreply@k.xiaomanmail.com" class="c-link info-text ellipsis email-text" target="_blank">noreply@k.xiaomanmail.com</a>
                                                        <!---->
																											</span>
																										</p>
																									</div>
																								</div>
																							</div>
																							<div class="sync" style="display: none;">
																								<div class="sync-btn">
																									<img class="sync-btn-icon" data-savepage-src="https://cdn.xiaoman.cn/crm_web/ks/prod/mail_subapp/static/img/refresh.28c2d1e5..png" src="data:image/webp;base64,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">
																								</div>
																								<span> 最近同步：--， <a data-savepage-href="/customer/personal/undefined?mm_tab_active=main-chat-record" href="https://crm.xiaoman.cn/customer/personal/undefined?mm_tab_active=main-chat-record" target="_blank">点击查看</a>
																								</span>
																							</div>
																							<div class="sync" style="display: none;">
																								<span> 最近同步：--， <a data-savepage-href="/customer/personal/undefined?mm_tab_active=main-chat-record" href="https://crm.xiaoman.cn/customer/personal/undefined?mm_tab_active=main-chat-record" target="_blank">点击查看</a>
																								</span>
																							</div>
                                              <!---->
																						</div>
																						<div class="okki-tabs okki-tabs-top okki-tabs-small information-extension-tabs" keep-tabs-alive="false">
																							<div role="tablist" class="okki-tabs-nav">
																								<!---->
																								<div class="okki-tabs-nav-switch-arrow okki-tabs-nav-switch-arrow-hidden">
																									<span role="img" aria-label="left" class="anticon anticon-left" icon="[object Object]" spin="false">
																										<svg focusable="false" class="" data-icon="left" width="1em" height="1em" fill="currentColor" aria-hidden="true" viewBox="64 64 896 896">
																											<path d="M724 218.3V141c0-6.7-7.7-10.4-12.9-6.3L260.3 486.8a31.86 31.86 0 000 50.3l450.8 352.1c5.3 4.1 12.9.4 12.9-6.3v-77.3c0-4.9-2.3-9.6-6.1-12.6l-360-281 360-281.1c3.8-3 6.1-7.7 6.1-12.6z"></path>
																										</svg>
																									</span>
																								</div>
																								<div class="okki-tabs-nav-wrap">
																									<div class="okki-tabs-nav-list" style="transform: translate(0px, 0px);">
                                                    <div class="okki-tabs-tab okki-tabs-tab-active">
                                                      <div role="tab" aria-selected="true" id="rc-tabs-0-tab-orderGuide" class="okki-tabs-tab-btn" aria-controls="rc-tabs-0-panel-orderGuide" tabindex="0">
                                                        <span>往来邮件</span>
                                                      </div>
                                                      <!---->
                                                    </div>
                                                    <!---->
                                                    <div class="okki-tabs-ink-bar okki-tabs-ink-bar-animated" style="left: 0px; width: 56px;"></div>
                                                  </div>
																								</div>
																								<div class="okki-tabs-nav-switch-arrow okki-tabs-nav-switch-arrow-hidden">
																									<span role="img" aria-label="right" class="anticon anticon-right" icon="[object Object]" spin="false">
																										<svg focusable="false" class="" data-icon="right" width="1em" height="1em" fill="currentColor" aria-hidden="true" viewBox="64 64 896 896">
																											<path d="M765.7 486.8L314.9 134.7A7.97 7.97 0 00302 141v77.3c0 4.9 2.3 9.6 6.1 12.6l360 281.1-360 281.1c-3.9 3-6.1 7.7-6.1 12.6V883c0 6.7 7.7 10.4 12.9 6.3l450.8-352.1a31.96 31.96 0 000-50.4z"></path>
																										</svg>
																									</span>
																								</div>
																								<div class="okki-tabs-nav-operations okki-tabs-nav-operations-hidden">
																									<!---->
																									<button type="button" class="okki-tabs-nav-more" tabindex="-1" aria-hidden="true" aria-haspopup="listbox" aria-controls="rc-tabs-0-more-popup" id="rc-tabs-0-more" aria-expanded="false" style="visibility: hidden; order: 1;">
																										<span role="img" aria-label="ellipsis" class="anticon anticon-ellipsis" icon="[object Object]" spin="false">
																											<svg focusable="false" class="" data-icon="ellipsis" width="1em" height="1em" fill="currentColor" aria-hidden="true" viewBox="64 64 896 896">
																												<path d="M176 511a56 56 0 10112 0 56 56 0 10-112 0zm280 0a56 56 0 10112 0 56 56 0 10-112 0zm280 0a56 56 0 10112 0 56 56 0 10-112 0z"></path>
																											</svg>
																										</span>
																									</button>
                                                  <!---->
																								</div>
																								<div class="okki-tabs-extra-content">
																									<!---->
																									<svg class="sort-setting-reference okki-svg-icon sort-setting-reference" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" fill="currentColor">
																										<path fill-rule="evenodd" clip-rule="evenodd" d="M10.783 3.287c-.439-.45-1.319-1.022-2.319-.644a9.99 9.99 0 00-2.799 1.619c-.826.677-.771 1.723-.602 2.33.12.426.074.895-.164 1.308a1.692 1.692 0 01-1.052.797c-.61.156-1.546.632-1.717 1.689a10.063 10.063 0 000 3.228c.171 1.056 1.107 1.533 1.717 1.689.43.11.813.384 1.052.797.238.413.284.882.164 1.309-.17.606-.224 1.652.602 2.329a9.99 9.99 0 002.8 1.619c1 .378 1.879-.194 2.318-.645.31-.317.74-.512 1.217-.512.477 0 .907.195 1.217.512.439.45 1.319 1.023 2.319.645a9.99 9.99 0 002.799-1.62c.826-.676.771-1.722.602-2.328a1.692 1.692 0 01.164-1.31 1.692 1.692 0 011.052-.796c.61-.156 1.546-.633 1.717-1.69a10.064 10.064 0 000-3.227c-.171-1.057-1.107-1.533-1.717-1.689a1.692 1.692 0 01-1.052-.797 1.692 1.692 0 01-.164-1.309c.17-.606.224-1.652-.602-2.329a9.99 9.99 0 00-2.8-1.619c-1-.378-1.879.194-2.318.644-.31.318-.74.513-1.217.513-.477 0-.907-.195-1.217-.513zm-3.82 2.497a7.99 7.99 0 012.171-1.256h.002c.048.019.13.067.216.156A3.692 3.692 0 0012 5.8a3.692 3.692 0 002.648-1.116.651.651 0 01.216-.155h.002c.791.303 1.523.73 2.17 1.255l.001.002a.654.654 0 01-.026.266c-.259.923-.16 1.95.358 2.848a3.691 3.691 0 002.289 1.735c.12.03.203.077.243.11h.001a8.063 8.063 0 010 2.509v.001a.652.652 0 01-.244.11c-.93.237-1.77.837-2.289 1.735a3.692 3.692 0 00-.358 2.848c.034.12.035.215.026.265v.003a7.989 7.989 0 01-2.171 1.255h-.002a.652.652 0 01-.216-.155A3.692 3.692 0 0012 18.2a3.692 3.692 0 00-2.648 1.116.653.653 0 01-.216.155h-.002a7.988 7.988 0 01-2.17-1.255l-.001-.003a.652.652 0 01.026-.265c.259-.924.16-1.95-.358-2.848a3.692 3.692 0 00-2.289-1.735.652.652 0 01-.243-.11l-.001-.001a8.067 8.067 0 010-2.508v-.001a.653.653 0 01.244-.11A3.692 3.692 0 006.631 8.9a3.692 3.692 0 00.358-2.848.652.652 0 01-.026-.266v-.002zm2.887 6.178a2.15 2.15 0 114.3 0 2.15 2.15 0 01-4.3 0zM12 7.812a4.15 4.15 0 100 8.3 4.15 4.15 0 000-8.3z"></path>
																									</svg>
																								</div>
                                                <!---->
																							</div>
																							<div class="okki-tabs-content-holder">
																								<div class="okki-tabs-content okki-tabs-content-top">
																									<div id="rc-tabs-0-panel-orderGuide" role="tabpanel" tabindex="-1" aria-labelledby="rc-tabs-0-tab-orderGuide" aria-hidden="true" class="okki-tabs-tabpane" style="display: none;">
																										<!---->
																									</div>
																									<div id="rc-tabs-0-panel-tradeDocumentRecord" role="tabpanel" tabindex="-1" aria-labelledby="rc-tabs-0-tab-tradeDocumentRecord" aria-hidden="true" class="okki-tabs-tabpane" style="display: none;">
																										<!---->
																									</div>
																									<div role="tabpanel" tabindex="-1" aria-hidden="true" class="okki-tabs-tabpane" id="rc-tabs-0-panel-aliLogisticsPrice" aria-labelledby="rc-tabs-0-tab-aliLogisticsPrice" style="display: none;">
																										<!---->
																									</div>
																									<div role="tabpanel" tabindex="0" aria-hidden="false" class="okki-tabs-tabpane okki-tabs-tabpane-active" id="rc-tabs-0-panel-attachment" aria-labelledby="rc-tabs-0-tab-attachment">
																										<!---->
																										<div class="contact-mail-tab-container" cur-active-mail-id="" size="mini" module="mail" company-fields="[object Object]" is-owner="true" flow-link="[object Object]" inner_whatsapp="false" contact-info="[object Object]" company-info="[object Object]" is-new-tab-component="false" new-tips-count="0" has-new-tips="false" identity-id="2339700037308" first-identity-id="2339700037308">
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
																											<div class="list">
																												<div class="total">
																													<span>共44封邮件</span>
																													<span class="okki-icon-wrap attachment-btn">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 25 25" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																															<path fill-rule="evenodd" clip-rule="evenodd" d="M4.493 11.392a6.25 6.25 0 108.959 8.718l6.8-6.988a1 1 0 10-1.434-1.395l-6.8 6.988a4.25 4.25 0 11-6.091-5.928l6.8-6.988 1.046-1.075a2.5 2.5 0 113.583 3.487l-5.928 6.092-.872.896a.75.75 0 11-1.075-1.046l5.056-5.196a1 1 0 10-1.433-1.395l-5.056 5.196a2.75 2.75 0 103.941 3.836l5.057-5.196.001-.002 1.742-1.79a4.5 4.5 0 10-6.45-6.277l-1.046 1.075-4.184 4.3-2.616 2.688z"></path>
																														</svg>
																													</span>
																												</div>
																												<ul class="contact-mail-list" cur-active-mail-id="" size="mini" module="mail" company-fields="[object Object]" is-owner="true" flow-link="[object Object]" contact-info="[object Object]" company-info="[object Object]" new-tips-count="0" identity-id="2339700037308" first-identity-id="2339700037308">
																													<li class="contact-mail-item">
																														<div class="title-wrapper">
																															<h1 class="ellipsis" title="小满账号异常登录提醒">小满账号异常登录提醒</h1>
																															<i class="contact-icon m-icon icon-mail-receive" title="收件"></i>
																														</div>
																														<div class="info-wrapper">
																															<div class="summary ellipsis">Email Rox! 亲爱的小满用户 您好：
																																您的小满账号 sales17@allxchips.com 有异常登录行为
																																登录时间 登录IP 登录地址 操作系统 浏览器
																																2023-08-01 15:</div>
																															<div class="icons">
																																<!---->
                                                                <!---->
																															</div>
																															<div class="time">15:17</div>
																														</div>
                                                            <!---->
																													</li>
																												</ul>
																												<div class="show-more">查看更多</div>
																											</div>
                                                      <!---->
																										</div>
																									</div>
																									<div id="rc-tabs-0-panel-tips" role="tabpanel" tabindex="-1" aria-labelledby="rc-tabs-0-tab-tips" aria-hidden="true" class="okki-tabs-tabpane" style="display: none;">
																										<!---->
																									</div>
																								</div>
																							</div>
																						</div>
                                            <!---->
                                            <!---->
																					</div>
																					<div id="crm-app" class="okki-anivia-crm-app"></div>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
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
														</div>
													</span>
    </div>
  </div>
</template>
<style lang="scss">
.components-container {
  margin: 0;
  position: static;
}

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
import { EventBus } from "@/api/email/event-bus";
import writeEmailLayout from './write_email.vue';
import {fixedEmail, list, quickReply} from "@/api/email/email";
import emailHeaderLayout from "@/views/email/email_header.vue";
import sendSuccessLayout from "@/views/email/send_success.vue";
import setup from "@/views/email/setup.vue";
import FolderTree from "@/views/email/folder_list_item.vue";

export default {
  data() {
    return {
      activeEmailId: null,
      currentPage: 1,
      pageSize: 30,
      total: 0,
      taskId: null,
      readFlag: null,
      pendingFlag: null,
      localEmailList: [],
      currentEmailDetail: {},
      emailId: null,
      isRightPanelExpanded: true,
      isReplying: false,
      isSending: false,
      replyContent: ''
    }
  },
  components: {
    'write_email': writeEmailLayout
  },
  props: {
    emailList: Array,
    emailData: Array,
    selectedEmail: {
      type: Object,
      default: null
    },
    emailTotal: {
      type: Number,
      default: null
    },
    selectedTaskId: {
      type: Number,
      default: null
    }
  },
  computed: {
    totalPages() {
      return Math.ceil(this.total / this.pageSize);
    }
  },

  // 在B组件中
  created() {
    this.activeEmailId = this.selectedEmail.id;
    this.currentEmailDetail = this.selectedEmail;
    this.localEmailList = this.emailData;
    this.total = this.emailTotal;
    this.taskId = this.selectedTaskId;
  },

  watch: {
    emailList(newList) {
      this.localEmailList = [...newList];
    }
  },

  methods: {
    fetchEmailsForTask(taskId) {
      this.currentPage = 1;
      this.readFlag = null;
      this.pendingFlag = null;
      this.fetchEmailList(taskId);
    },

    fetchEmailList(taskId) {
      this.taskId = taskId;
      const query = {
        taskId: this.taskId,
        // 邮件类型 1.收取 2.发送
        type: 1,
        readFlag: this.readFlag,
        pendingFlag: this.pendingFlag,
        pageNum: this.currentPage,
        pageSize: this.pageSize
      }

      list(query).then(response => {
        this.localEmailList = response.rows;
        this.total = response.total;
      }).catch(error => {
        console.error("Failed to fetch emails:", error);
      });
    },

    toggleActive(email) {
      this.activeEmailId = email.id;
      this.currentEmailDetail = email;
    },

    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage = Number(this.currentPage) + 1;
        this.fetchEmailList(this.taskId);
      }
    },

    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage = Number(this.currentPage) - 1;
        this.fetchEmailList(this.taskId);
      }
    },

    handlePageInputBlur() {
      if (this.currentPage === undefined || this.currentPage === null || this.currentPage === '') {
        this.currentPage = 1;
      }

      this.fetchEmailList(this.taskId);
    },

    handlePageInputChange(event) {
      let inputValue = parseInt(event.target.value, 10);

      // 检查输入值是否超出范围，并进行调整
      if (inputValue > this.totalPages) {
        this.currentPage = this.totalPages;
      } else if (inputValue < 1) {
        this.currentPage = 1;
      }
    },

    fetchEmailData(selectedEmailType) {
      if (selectedEmailType === 'ALL_RECEIVED') {
        this.readFlag = null;
        this.pendingFlag = null;
        this.currentPage = 1;
        this.fetchEmailList(null);
      } else if (selectedEmailType === 'PENDING_MAIL') {
        this.readFlag = null;
        this.pendingFlag = true;
        this.currentPage = 1;
        this.fetchEmailList(null);
      } else if (selectedEmailType === 'AN_UNREAD_MAIL') {
        this.readFlag = false;
        this.pendingFlag = null;
        this.currentPage = 1;
        this.fetchEmailList(null);
      }
    },
    toggleRightPanel() {
      this.isRightPanelExpanded = !this.isRightPanelExpanded;
    },

    async toggleFixed(email, event) {
      event.stopPropagation();
      const data = {
        "id": email.id,
        "fixedFlag": !email.fixedFlag
      };
      try {
        const response = await fixedEmail(data);
        if (response.code !== 200) {
          this.$message.error("执行失败");
          return;
        }

        email.fixedFlag = !email.fixedFlag;
      } catch (error) {
        console.error('固定邮件出现错误:', error);
        throw error;
      }
    },

    // 快速回复-富文本框
    toggleQuickReply() {
      this.isReplying = !this.isReplying;
    },

    // 快速回复
    async quickReply(emailId) {
      const data = {
        "id": this.activeEmailId,
        "content": this.replyContent
      };
      try {
        const response = await quickReply(data);
        if (response.code !== 200) {
          this.$message.error("执行失败");
          return;
        }

        this.$message.success("发送成功");
        this.isSending = true;
        this.replyContent = '';
      } catch (error) {
        console.error('快速回复出现错误:', error);
        throw error;
      }
    },

    toggleReply(email) {
      // 触发事件并传递参数
      EventBus.$emit('switch-to-reply-email', email);
    }
  }
}
</script>
