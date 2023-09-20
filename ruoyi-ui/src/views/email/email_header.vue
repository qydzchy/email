<template>
  <div class="mm-split-pane mm-split-pane__right" style="left: 12.2835%;">
    <div class="page-mail-list layout-extraSidebar-content" style="background-color: white;">
												<span>
													<div class="view-mode-three component-mail-list relative list-with-toolbar">
														<div class="promotion-wrapper"></div>

														<div class="mail-toolbar-wrapper">
															<div class="mail-toolbar-left">
																<span class="mail-toolbar-btn-item">
																	<label class="mm-checkbox">
																		<input v-model="selectAll" @change="toggleAllEmails" true-value="true" type="checkbox">
																		<span class="mm-checkbox-input"></span>
																	</label>
																</span>

																<span class="mm-tooltip mail-toolbar-btn-item" v-if="isIconsToggled" @click="refresh">
																	<span class="mm-tooltip-trigger">
																		<span>
																			<span class="okki-icon-wrap tool-bar-icon-item">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																					<line x1="21" y1="4" x2="21" y2="12"></line>
																					<line x1="3" y1="12" x2="3" y2="20"></line>
																					<path fill-rule="evenodd" clip-rule="evenodd" d="M12 4a7.993 7.993 0 00-6.762 3.723A1 1 0 113.55 6.652 9.993 9.993 0 0112 2a9.985 9.985 0 018 3.999V4a1 1 0 112 0v8c0 .149-.003.296-.01.443a1 1 0 11-1.998-.087A8 8 0 0012 4zM3.174 9.967a1 1 0 01.879 1.108 8 8 0 0014.997 4.71 1 1 0 011.76.948A9.998 9.998 0 0112 22a9.985 9.985 0 01-8-3.999V20a1 1 0 11-2 0v-8c0-.39.022-.775.066-1.154a1 1 0 011.108-.879z"></path>
																				</svg>
																			</span>
																		</span>
																	</span>
                                  <!---->
																</span>
																<span class="mm-tooltip mail-toolbar-btn-item" v-if="isIconsToggled">
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

                                <span class="mm-tooltip mail-toolbar-btn-item" v-if="!isIconsToggled">
                                  <span class="mm-tooltip-trigger">
                                    <div class="mm-popover">
                                      <div>
                                        <span>
                                          <span class="okki-icon-wrap tool-bar-icon-item">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" fill="currentColor" class="okki-svg-icon">
                                              <path d="M12 6a1 1 0 011 1v4.423l2.964 1.711a1 1 0 11-1 1.732l-3.447-1.99A1 1 0 0111 11.98V7a1 1 0 011-1z"></path>
                                              <path fill-rule="evenodd" clip-rule="evenodd" d="M22 12c0 5.523-4.477 10-10 10S2 17.523 2 12 6.477 2 12 2s10 4.477 10 10zm-2 0a8 8 0 11-16 0 8 8 0 0116 0z"></path>
                                            </svg>
                                          </span>
                                        </span>
                                      </div>
                                    </div>
                                  </span>
                                </span>
                                <span class="mm-tooltip mail-toolbar-btn-item" v-if="!isIconsToggled" @click="deleteEmails">
                                  <span class="mm-tooltip-trigger">
                                    <span>
                                      <span class="okki-icon-wrap tool-bar-icon-item">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" fill="currentColor" class="okki-svg-icon">
                                          <path fill-rule="evenodd" clip-rule="evenodd" d="M10.2 2a2 2 0 00-1.765 1.059L7.4 5H3a1 1 0 000 2h1.5v12a3 3 0 003 3h9a3 3 0 003-3V7H21a1 1 0 100-2h-4.4l-1.035-1.941A2 2 0 0013.8 2h-3.6zm7.3 5h-11v12a1 1 0 001 1h9a1 1 0 001-1V7zm-3.167-2L13.8 4h-3.6l-.533 1h4.666zM10 9.5a1 1 0 011 1v6a1 1 0 11-2 0v-6a1 1 0 011-1zm5 1a1 1 0 10-2 0v6a1 1 0 102 0v-6z"></path>
                                        </svg>
                                      </span>
                                    </span>
                                  </span>
                                </span>
                                <span class="mm-tooltip mail-toolbar-btn-item" v-if="!isIconsToggled">
                                  <span class="mm-tooltip-trigger">
                                    <span>
                                      <span class="okki-icon-wrap tool-bar-icon-item">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" fill="currentColor" class="okki-svg-icon">
                                          <path d="M8 10a2 2 0 100-4 2 2 0 000 4z"></path>
                                          <path fill-rule="evenodd" clip-rule="evenodd" d="M4.033 4.033l7.115.093 8.561 8.561a1 1 0 010 1.414l-5.608 5.608a1 1 0 01-1.414 0l-8.56-8.561-.094-7.115zm8.242-1.609a1 1 0 00-.694-.292l-7.522-.099A2 2 0 002.033 4.06l.099 7.522a1 1 0 00.292.694l8.849 8.848a3 3 0 004.243 0l5.607-5.607a3 3 0 000-4.243l-8.848-8.849zM8 10a2 2 0 100-4 2 2 0 000 4z"></path>
                                        </svg>
                                      </span>
                                    </span>
                                  </span>
                                </span>
                                <span class="mm-tooltip mail-toolbar-btn-item" v-if="!isIconsToggled">
                                  <span class="mm-tooltip-trigger">
                                    <span>
                                      <span class="okki-icon-wrap tool-bar-icon-item">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" fill="currentColor" class="okki-svg-icon">
                                          <path fill-rule="evenodd" clip-rule="evenodd" d="M2 6a3 3 0 013-3h4.379a3 3 0 012.108.866l1.824 1.8H19a3 3 0 013 3V18a3 3 0 01-3 3H5a3 3 0 01-3-3V6zm2 0a1 1 0 011-1h4.379a1 1 0 01.703.289l1.823 1.8a2 2 0 001.406.578H19a1 1 0 011 1V10H4V6zm16 6v6a1 1 0 01-1 1H5a1 1 0 01-1-1v-6h16z"></path>
                                        </svg>
                                      </span>
                                    </span>
                                  </span>
                                </span>
                                <span class="mm-tooltip mail-toolbar-btn-item" v-if="!isIconsToggled" @click="toggleDropdown">
                                  <span class="mm-tooltip-trigger">
                                    <span>
                                      <span class="okki-icon-wrap tool-bar-icon-item">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" fill="currentColor" class="okki-svg-icon">
                                          <path d="M10 12a2 2 0 104 0 2 2 0 00-4 0zm0 7a2 2 0 104 0 2 2 0 00-4 0zm0-14a2 2 0 104 0 2 2 0 00-4 0z"></path>
                                        </svg>
                                      </span>
                                    </span>
                                  </span>
                                </span>

															</div>
															<div class="mail-toolbar-right">
																<!---->
																<div class="mail-paging tool-bar-paging">
																	<span class="total-count ellipsis">共 {{total}} 封</span>
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
																<span class="mm-tooltip mail-toolbar-btn-item">
																	<span class="mm-tooltip-trigger">
																		<span class="okki-icon-wrap filter-icon">​<svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																				<path fill-rule="evenodd" clip-rule="evenodd" d="M5.055 5l5.507 7.388a1 1 0 01.198.598v5.45l2.48 1.407v-6.857a1 1 0 01.198-.598L18.945 5H5.055zm-1.91-.837C3.403 3.518 4.017 3 4.8 3h14.4c.783 0 1.397.518 1.655 1.163a2.028 2.028 0 01-.246 1.951l-5.369 7.203v6.764c0 .603-.27 1.218-.797 1.595-.546.39-1.259.434-1.85.099l-2.88-1.634c-.628-.356-.953-1.034-.953-1.694v-5.13L3.39 6.114a2.028 2.028 0 01-.245-1.951l.928.372-.928-.372z"></path>
																			</svg>
																		</span>
																	</span>
                                  <!---->
																</span>
																<span class="mm-tooltip mail-toolbar-btn-item">
																	<span class="mm-tooltip-trigger">
																		<span class="okki-icon-wrap active switch-attach-icon">​<svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 25 25" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																				<path fill-rule="evenodd" clip-rule="evenodd" d="M4.493 11.392a6.25 6.25 0 108.959 8.718l6.8-6.988a1 1 0 10-1.434-1.395l-6.8 6.988a4.25 4.25 0 11-6.091-5.928l6.8-6.988 1.046-1.075a2.5 2.5 0 113.583 3.487l-5.928 6.092-.872.896a.75.75 0 11-1.075-1.046l5.056-5.196a1 1 0 10-1.433-1.395l-5.056 5.196a2.75 2.75 0 103.941 3.836l5.057-5.196.001-.002 1.742-1.79a4.5 4.5 0 10-6.45-6.277l-1.046 1.075-4.184 4.3-2.616 2.688z"></path>
																			</svg>
																		</span>
																	</span>
                                  <!---->
																</span>
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
                                <div
                                  :class="['show', 'main-toolbar-search-pin', 'mail-toolbar-btn-item', { 'active': fixedFlag }]"
                                  @click="switchFixed"
                                >
                                  <div class="main-toolbar-search-pin-bg">
                                    <a class="main-toolbar-search-pin-handle">
                                      <span :class="['okki-icon-wrap', 'pin-icon', { 'pin-icon-active': fixedFlag }]">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="#ffffff">
                                          <path fill-rule="evenodd" clip-rule="evenodd" d="M8.382 2a.963.963 0 00-.97.956c0 1.33.582 2.526 1.506 3.353l-.606 3.865C6.325 11.39 5 13.564 5 16.044c0 .528.434.956.97.956H11v3.914c0 .6.448 1.086 1 1.086s1-.486 1-1.086V17h5.03c.536 0 .97-.428.97-.956 0-2.507-1.354-4.7-3.376-5.91l-.597-3.777a4.49 4.49 0 001.56-3.4.963.963 0 00-.969-.957H8.382z"></path>
                                        </svg>
                                      </span>
                                    </a>
                                  </div>
                                </div>
                                <!---->
															</div>


															<div class="mail-drop-menu-wrapper" style="width: 220px; top: 40px; left: 215px;" :style="dropdownStyle">
																<ul class="mail-drop-menu" style="height: 96px;">
                                  <li
                                    v-for="(item, index) in menuItems"
                                    :key="index"
                                    :class="[
                                      'mail-drop-menu-item',
                                      `DROPMENU_55587_ITEM_${index}`,
                                      { 'mail-drop-menu-item-active': hoveredItem === index }
                                    ]"
                                    @click="readOrSpanEmails(index)"
                                    @mouseover="hoveredItem = index"
                                    @mouseleave="hoveredItem = null"
                                  >
                                    <span class="mail-drop-menu-text ellipsis">
                                        <!---->
                                        <span class="">{{item}}</span>
                                      </span>
                                      <span>
                                        <!---->
                                      </span>
                                  </li>
                                </ul>
															</div>
														</div>
														<div class="top-block">
															<div class="multi-select" style="display: none;">
																<div class="multi-select-content">
																	<span>已选择此页面上所有 <span class="num">  20  </span> 封邮件</span> ， <a class="all">选择全部 <a class="num">  1121  </a> 封邮件</a>
																</div>
															</div>
                              <!---->
                              <!---->
                              <!---->
														</div>
														<div class="only-mail-list mail-list-container">
															<div class="mm-split mail-three-mode-split">
																<div class="mm-split-horizontal">
																	<div class="mm-split-pane mm-split-pane__left" style="right: 19.5695%;">
																		<div class="mail-list-scroll-container mail-list-wrap">
																			<div class="header-wrapper">
																				<div class="mail-list-header-tool-wrapper">
																					<div class="mm-tabs mm-tabs__top mm-tabs__align-left mail-list-toggle-tab header-tab">
																						<div class="mm-tabs-header mm-tabs-header__top">
																							<!---->
																							<div class="mm-tabs-nav-wrap mm-tabs-nav-wrap__top">
																								<!---->
																								<div class="mm-tabs-nav-scroll">
																									<div class="mm-tabs-nav" role="tablist" style="transform: translateX(0px);">
																										<div class="mm-tabs-active-bar__top mm-tabs-active-bar" style="width: 103px; transform: translateX(0px);"></div>
																										<div class="mm-tabs-item mm-tabs-item__top mm-tabs-item--active" id="tab-all" aria-controls="pane-all" role="tab" aria-selected="true" tabindex="0" refinfor="true">
																											<span class="mm-tooltip">
																												<span class="mm-tooltip-trigger">全部<span class="mail-tab-unread">({{total}})</span>
																												</span>
                                                        <!---->
																											</span>
                                                      <!---->
																										</div>
<!--																										<div class="mm-tabs-item mm-tabs-item__top" id="tab-1" aria-controls="pane-1" role="tab" aria-selected="false" tabindex="-1" refinfor="true">客户
                                                      &lt;!&ndash;&ndash;&gt;
																										</div>
																										<div class="mm-tabs-item mm-tabs-item__top" id="tab-2" aria-controls="pane-2" role="tab" aria-selected="false" tabindex="-1" refinfor="true">同事
                                                      &lt;!&ndash;&ndash;&gt;
																										</div>
																										<div class="mm-tabs-item mm-tabs-item__top" id="tab-3" aria-controls="pane-3" role="tab" aria-selected="false" tabindex="-1" refinfor="true">通讯录
                                                      &lt;!&ndash;&ndash;&gt;
																										</div>
																										<div class="mm-tabs-item mm-tabs-item__top" id="tab-0" aria-controls="pane-0" role="tab" aria-selected="false" tabindex="-1" refinfor="true">
																											<span class="mm-tooltip">
																												<span class="mm-tooltip-trigger">其他<span class="mail-tab-unread">(982)</span>
																												</span>
                                                        &lt;!&ndash;&ndash;&gt;
																											</span>
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
                                          <!---->
																				</div>
																			</div>
																			<div class="mail-list-scroller">
																				<!---->
																				<div class="mail-list-scroll-container">
																					<ul class="grouped-list-container mail-grouped-list">
																						<li v-for="monthData in localEmailList" :key="Object.keys(monthData)[0]" class="grouped-list-item">
																							<h2 class="grouped-list-item-title">
																								<span class="time-title">{{ Object.keys(monthData)[0] }}</span>
																								<span class="mail-count"> ( {{monthData[Object.keys(monthData)[0]].length}} 封) </span>
																							</h2>
																							<ul class="plain-list-container">
																								<li
                                                  v-for="email in monthData[Object.keys(monthData)[0]]"
                                                  :key="email.id"
                                                  :class="{'plain-list-item': true, 'active': email.id === activeEmailId}"
                                                  @click="toggleActive(email)">
																									<div class="right-click-menu-handler">
																										<div class="mail-item-container mail-list-item-wrapper" draggable="true" mail-info-icon-map="[object Object]">
																											<div class="mail-list-item-content">
																												<div>
																													<label class="mm-checkbox mail-list-item-checkbox" style="" @click.stop>
																														<input v-model="email.selected" @change="toggleEmailSelection(email)" true-value="true" type="checkbox">
																														<span class="mm-checkbox-input"></span>
                                                            <!---->
																													</label>
																												</div>
																												<div class="addr-info-container" style="">
																													<div class="mail-list-item-addr-contact-info">
																														<span class="email-addr-box">
																															<span class="mm-tooltip all-type-avatar-wrapper client-stranger small mail-tag-avatar">
																																<span class="mm-tooltip-trigger">
																																	<i class="m-icon avatar-icon icon-new-customer-avatar"></i>
																																</span>
                                                                <!---->
																															</span>
                                                              <!---->
                                                              <!---->
																															<span title="系统管理员 " class="email-nickname ellipsis">{{email.title}} </span>
																															<span class="seperator"></span>
																															<span class="customer-name ellipsis">{{email.fromer}}</span>
																														</span>
<!--																														<div class="mail-info-icons">
																															<span class="mail-info-icon-item">
																																<i class="m-icon icon-repeated-line" data-tips="已回复" style="color: rgb(144, 144, 144);"></i>
                                                                &lt;!&ndash;&ndash;&gt;
																															</span>
																															<span class="mail-info-icon-item">
																																<i class="m-icon icon-attachment-line" data-tips="有附件" style="color: rgb(144, 144, 144);"></i>
                                                                &lt;!&ndash;&ndash;&gt;
																															</span>
																														</div>-->
																													</div>
																												</div>
																												<div class="addr-info-operations-wrapper" style="display: none;">
																													<div class="addr-info-container open">
																														<div class="mail-list-item-addr-contact-info open">
																															<span class="email-addr-box">
																																<span class="mm-tooltip all-type-avatar-wrapper client-stranger small mail-tag-avatar">
																																	<span class="mm-tooltip-trigger">
																																		<i class="m-icon avatar-icon icon-new-customer-avatar"></i>
																																	</span>
                                                                  <!---->
																																</span>
                                                                <!---->
                                                                <!---->
																																<span class="email-nickname ellipsis">{{email.fromer}} </span>
                                                                <!---->
																															</span>
																															<div class="mail-info-icons">
																																<span class="mail-info-icon-item">
																																	<i class="m-icon icon-attachment-line" data-tips="有附件" style="color: rgb(144, 144, 144);"></i>
                                                                  <!---->
																																</span>
																																<span class="mail-info-icon-item">
																																	<i class="m-icon icon-repeated-line" data-tips="已回复" style="color: rgb(144, 144, 144);"></i>
                                                                  <!---->
																																</span>
																															</div>
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
																												<div class="mail-list-item-overview-content">
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
																												<div style=""></div>
																												<div class="right-operations-container" style="">
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
                                                                <span class="okki-icon-wrap mail-pin-icon" data-tips="取消固定" color="#0064ff" @click="toggleFixed(email, $event)">​<svg data-tips="取消固定" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="#0064ff">
                                                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M8.382 2a.963.963 0 00-.97.956c0 1.33.582 2.526 1.506 3.353l-.606 3.865C6.325 11.39 5 13.564 5 16.044c0 .528.434.956.97.956H11v3.914c0 .6.448 1.086 1 1.086s1-.486 1-1.086V17h5.03c.536 0 .97-.428.97-.956 0-2.507-1.354-4.7-3.376-5.91l-.597-3.777a4.49 4.49 0 001.56-3.4.963.963 0 00-.969-.957H8.382z"></path>
                                                                  </svg>
                                                                </span>
                                                              </div>

																														<div v-else class="mail-pin-wrapper">
																															<span class="okki-icon-wrap mail-pin-icon" data-tips="固定" @click="toggleFixed(email, $event)">​<svg data-tips="固定" xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 25 25" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																																	<path fill-rule="evenodd" clip-rule="evenodd" d="M16.606 3.292a1 1 0 00-1.414 0 4.508 4.508 0 00-1.314 3.428l-3.094 2.253c-2.258-.538-4.729.057-6.484 1.812a1 1 0 000 1.415l3.545 3.544-2.828 2.829a1 1 0 101.414 1.414l2.828-2.828 3.536 3.535a1 1 0 001.414 0c1.774-1.774 2.363-4.279 1.794-6.557l2.198-3.025a4.51 4.51 0 003.502-1.31 1 1 0 000-1.414l-5.097-5.096zm-6.634 11.75L13.43 18.5a4.93 4.93 0 00.529-4.24 1 1 0 01.14-.902l2.837-3.904a1 1 0 011.02-.39 2.57 2.57 0 001.479-.115L16.046 5.56c-.177.453-.22.95-.125 1.431a1 1 0 01-.392 1.001l-3.957 2.882a1 1 0 01-.892.144 4.929 4.929 0 00-4.185.548l3.466 3.466.006.005a.226.226 0 01.005.006z"></path>
																																</svg>
																															</span>
																														</div>
																													</div>
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
																							<img src="data:image/png;base64,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" width="190" height="133">
																						</div>
																						<div class="empty-custom-desc">
																							<span>暂无数据</span>
																						</div>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
																	<div class="mm-split-trigger-wrap" style="left: 80.4305%;">
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
																	<div class="mm-split-pane mm-split-pane__right" style="left: 80.4305%;"></div>
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
.grouped-list-item-title {
  font-weight: 400;
  font-size: 12px;
  color: #909090;
  margin-bottom: 5px;
  margin-top: 14px;
  padding-left: 14px;
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
@import '../../static/scss/email/index/11575.99cf6a6f.css';
@import '../../static/scss/email/index/33619.c3cd13e2.css';
@import '../../static/scss/email/index/9597.5cdc5d42.css';
</style>
<script>
import { EventBus } from "@/api/email/event-bus";
import {list, fixedEmail, readEmail, spamEmail, moveFolder, deleteEmail} from "@/api/email/email";
import emailContentLayout from "@/views/email/email_content.vue";
import {renameAttachment} from "@/api/email/attachment";

export default {
  data() {
    return {
      activeEmailId: null,
      currentPage: 1,
      pageSize: 30,
      total: 0,
      taskId: null,
      type: null,
      readFlag: null,
      pendingFlag: null,
      delFlag: null,
      draftsFlag: null,
      spamFlag: null,
      localEmailList: [],
      currentEmailDetail: {},
      currentEmailType: '',
      selectAll: false,
      isIconsToggled: true,  // 用于控制图标的显示状态
      isDropdownShown: false,  // 保存下拉菜单的显示状态

      selectedItem: null,
      hoveredItem: null, // 当前悬停的列表项的索引
      menuItems: [
        '标为已读',
        '标为未读',
        '标为垃圾邮件',
      ],
      fixedFlag: false
    }
  },
  props: {
    emailList: Array
  },
  computed: {
    totalPages() {
      return Math.ceil(this.total / this.pageSize);
    },
    dropdownStyle() {
      return this.isDropdownShown ? '' : 'display: none;';
    }
  },

  // 在B组件中
  created() {
    EventBus.$on('task-selected', this.fetchEmailData);
    EventBus.$on('folder-selected', this.fetchEmailData);
    EventBus.$on('all-received-selected', this.fetchEmailData);
    EventBus.$on('pending-mail-selected', this.fetchEmailData);
    EventBus.$on('an-unread-mail-selected', this.fetchEmailData);
    EventBus.$on('deleted-mail-selected', this.fetchEmailData);
    EventBus.$on('drafts-selected', this.fetchEmailData);
    EventBus.$on('spam-mail-selected', this.fetchEmailData);
    EventBus.$on('trace-information-selected', this.fetchEmailData);
    EventBus.$on('complete-shipment-selected', this.fetchEmailData);
    if (this.emailList === undefined || this.emailList === null || this.emailList.length === 0) {
      this.localEmailList = [];
    } else {
      this.localEmailList = [...this.emailList];
    }
  },

  watch: {
    emailList(newList) {
      this.localEmailList = [...newList];
    },

    masterCheckbox(newValue) {
      this.setSelected(newValue);
    }
  },

  beforeDestroy() {
    EventBus.$off('task-selected', this.fetchEmailData);
    EventBus.$off('folder-selected', this.fetchEmailData);
    EventBus.$off('all-received-selected', this.fetchEmailData);
    EventBus.$off('pending-mail-selected', this.fetchEmailData);
    EventBus.$off('an-unread-mail-selected', this.fetchEmailData);
    EventBus.$off('deleted-mail-selected', this.fetchEmailData);
    EventBus.$off('drafts-selected', this.fetchEmailData);
    EventBus.$off('spam-mail-selected', this.fetchEmailData);
    EventBus.$off('trace-information-selected', this.fetchEmailData);
    EventBus.$off('complete-shipment-selected', this.fetchEmailData);
  },
  methods: {
    fetchEmailList(taskId, type, readFlag, pendingFlag, delFlag, draftsFlag, spamFlag, traceFlag, folderId) {
      this.taskId = taskId;
      this.type = type;
      const query = {
        taskId: this.taskId,
        // 邮件类型 1.收取 2.发送
        type: this.type,
        readFlag: readFlag,
        pendingFlag: pendingFlag,
        delFlag: delFlag,
        draftsFlag: draftsFlag,
        spamFlag: spamFlag,
        traceFlag: traceFlag,
        folderId: folderId,
        fixedFlag: this.fixedFlag,
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
      this.$emit('switch', 'email_content', email, this.localEmailList, this.total, this.currentEmailType);
    },

    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage = Number(this.currentPage) + 1;
        this.fetchEmailData(this.currentEmailType);
      }
    },

    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage = Number(this.currentPage) - 1;
        this.fetchEmailData(this.currentEmailType);
      }
    },

    handlePageInputBlur() {
      if (this.currentPage === undefined || this.currentPage === null || this.currentPage === '') {
        this.currentPage = 1;
      }

      this.fetchEmailList(this.taskId, this.type);
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
      this.currentEmailType = selectedEmailType;
      if (selectedEmailType === 'ALL_RECEIVED') {
        this.fetchEmailList(null, 1, null, null, null, null, null, null);
      } else if (selectedEmailType === 'COMPLETE_SHIPMENT') {
        this.fetchEmailList(null, 2, null, null, null, null, null, null);
      } else if (selectedEmailType === 'PENDING_MAIL') {
        this.fetchEmailList(null, null, null, true, null, null, null, null);
      } else if (selectedEmailType === 'AN_UNREAD_MAIL') {
        this.fetchEmailList(null, 1, false, null, null, null, null, null);
      } else if (selectedEmailType === 'DELETED_MAIL') {
        this.fetchEmailList(null, null, null, null, true, null, null, null);
      } else if (selectedEmailType === 'DRAFTS') {
        this.fetchEmailList(null, 2, null, null, null,true, null, null);
      } else if (selectedEmailType === 'SPAM_MAIL') {
        this.fetchEmailList(null, null, null, null, null, null, true, null);
      } else if (selectedEmailType === 'TRACE_INFORMATION') {
        this.fetchEmailList(null, null, null, null, null, null, null, true);
      } else if (/^PULL_(.+)$/.test(selectedEmailType)) {
        const taskId = RegExp.$1;
        this.fetchEmailList(taskId, 1, null, null, null, null, null, null);
      } else if (/^SEND_(.+)$/.test(selectedEmailType)) {
        const taskId = RegExp.$1;
        this.fetchEmailList(taskId, 2, null, null, null, null, null, null);
      } else if (/^FOLDER_(.+)$/.test(selectedEmailType)) {
        const folderId = RegExp.$1;
        this.fetchEmailList(null, null, null, null, null, null, null, folderId);
      }
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

    toggleAllEmails() {
      const shouldSelectAll = this.selectAll;
      this.localEmailList.forEach(dateGroup => {
        for (const date in dateGroup) {
          if (dateGroup.hasOwnProperty(date)) {
            dateGroup[date].forEach(email => {
              email.selected = shouldSelectAll;
            });
          }
        }
      });

      this.isIconsToggled = !this.isIconsToggled;
    },

    // 存在选中的邮件
    existSelectedEmail() {
      let hasSelected = false; // 创建一个变量来跟踪是否存在选中的邮件

      this.localEmailList.forEach(dateGroup => {
        for (const date in dateGroup) {
          if (dateGroup.hasOwnProperty(date)) {
            dateGroup[date].forEach(email => {
              if (email.selected) {
                hasSelected = true;
              }
            });
          }
        }
      });

      return hasSelected; // 返回这个变量
    },

    setSelected(newValue) {
      this.localEmailList.forEach(dateGroup => {
        for (const date in dateGroup) {
          if (dateGroup.hasOwnProperty(date)) {
            dateGroup[date].forEach(email => {
              email.selected = newValue;
            });
          }
        }
      });
    },

    refresh() {
      this.$message.info("正在加载");
      this.currentPage = 1;
      this.fetchEmailData(this.currentEmailType);
    },

    toggleDropdown() {
      this.isDropdownShown = !this.isDropdownShown;
    },

    readOrSpanEmails(index) {
      this.selectedItem = index;

      const selectedEmails = this.getSelectedEmailIds();
      if (selectedEmails.length) {
        if (this.menuItems[index] === '标为已读') {
          this.readEmails(selectedEmails);
        } else if (this.menuItems[index] === '标为未读') {
          this.unReadEmails(selectedEmails);
        } else if (this.menuItems[index] === '标为垃圾邮件') {
          this.spamEmails(selectedEmails);
        }
      }
    },

    closeSelected() {
      this.selectAll = false;
      this.isDropdownShown = false;
      this.toggleAllEmails();
      // 刷新邮件列表
      this.currentPage = 1;
      this.fetchEmailData(this.currentEmailType);
    },

    getSelectedEmailIds() {
      const selectedIds = [];
      this.localEmailList.forEach(dateGroup => {
        for (const date in dateGroup) {
          if (dateGroup.hasOwnProperty(date)) {
            dateGroup[date].forEach(email => {
              if (email.selected) {
                selectedIds.push(email.id);
              }
            });
          }
        }
      });
      return selectedIds;
    },

    // 标记为已读文件
    async readEmails(emailIds) {
      const data = {
        "ids": emailIds,
        "readFlag": true
      };
      try {
        const response = await readEmail(data);
        if (response.code === 200) {
          this.$message.success("成功标记为已读");
          this.closeSelected();
          return;
        }
      } catch (error) {
        console.error('标记为已读出现错误:', error);
        throw error;
      }
    },

    // 标记为未读邮件
    async unReadEmails(emailIds) {
      const data = {
        "ids": emailIds,
        "readFlag": false
      };
      try {
        const response = await readEmail(data);
        if (response.code === 200) {
          this.$message.success("成功标记为未读");
          return;
        }
      } catch (error) {
        console.error('标记为未读出现错误:', error);
        throw error;
      }
    },

    // 标识为垃圾邮件
    async spamEmails(emailIds) {
      const data = {
        "ids": emailIds,
        "spamFlag": true
      };
      try {
        const response = await spamEmail(data);
        if (response.code === 200) {
          this.$message.success("垃圾邮件标记成功");
          this.closeSelected();
          return;
        }
      } catch (error) {
        console.error('垃圾邮件标记出现错误:', error);
        throw error;
      }
    },

    // 删除邮件
    async deleteEmails() {
      const emailIds = this.getSelectedEmailIds();
      if (emailIds.length) {
        const data = {
          "ids": emailIds
        };
        try {
          const response = await deleteEmail(data);
          if (response.code === 200) {
            this.$message.success("邮件删除成功");
            this.closeSelected();
            return;
          }
        } catch (error) {
          console.error('邮件删除出现错误:', error);
          throw error;
        }
      }
    },

    toggleEmailSelection() {
      let existSelected = this.existSelectedEmail();
      if (existSelected) {
        this.selectAll = true;
        this.isIconsToggled = false;
      } else {
        this.selectAll = false;
        this.isIconsToggled = true;
      }
    },

    // 固定总开关
    switchFixed() {
      this.fixedFlag = !this.fixedFlag;
      this.currentPage = 1;
      this.fetchEmailData(this.currentEmailType);
    }
  }
}
</script>
