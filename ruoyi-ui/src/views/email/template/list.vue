<template>
  <div class="mail-plus-tpl config-page-item">
    <div class="mail-plus-tpl-block" style="">
      <div class="tpl-list-content">
        <div class="mail-config-header" v-show="!addTemplatePage">
          <div>
            <h3 class="config-page-title">个人模版</h3>
            <button type="button" class="mm-button mm-button__text">
              <!---->
              <!---->企业模板管理
              <!---->
            </button>
          </div>
          <div style="display: flex; align-items: center;">
            <button type="button" class="mm-button mm-button__primary" @click="addTemplateBtn">
              <!---->
              <!---->新建模板
              <!---->
            </button>
          </div>
        </div>
        <div v-show="!addTemplatePage">
																		<span class="okki-input-affix-wrapper" style="width: 216px;">
																			<span class="okki-input-prefix">
																				<svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" spin="false" rtl="false" viewBox="0 0 16 16" preserveAspectRatio="xMidYMid meet" fill="none" role="presentation" data-v-946e7517="">
																					<g data-v-946e7517="">
																						<path d="M.667 7a6.333 6.333 0 1 1 11.258 3.982l2.449 2.449a.667.667 0 1 1-.943.943l-2.449-2.449A6.333 6.333 0 0 1 .666 7ZM7 2a5 5 0 1 0 0 10A5 5 0 0 0 7 2Z" clip-rule="evenodd" fill-rule="evenodd" data-follow-fill="#333" fill="#BFBFBF" data-v-946e7517=""></path>
																					</g>
																				</svg>
																			</span>
																			<input placeholder="请输入模版名称" width="0" maxlength="50" type="text" class="okki-input truncate" value="">
																			<span class="okki-input-suffix">
																				<span role="button" aria-label="close-circle" tabindex="-1" class="anticon anticon-close-circle okki-input-clear-icon-hidden okki-input-clear-icon-has-suffix okki-input-clear-icon okki-input-clear-icon-hidden okki-input-clear-icon-has-suffix okki-input-clear-icon" icon="[object Object]" spin="false">
																					<svg focusable="false" class="" data-icon="close-circle" width="1em" height="1em" fill="currentColor" aria-hidden="true" viewBox="64 64 896 896">
																						<path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm165.4 618.2l-66-.3L512 563.4l-99.3 118.4-66.1.3c-4.4 0-8-3.5-8-8 0-1.9.7-3.7 1.9-5.2l130.1-155L340.5 359a8.32 8.32 0 01-1.9-5.2c0-4.4 3.6-8 8-8l66.1.3L512 464.6l99.3-118.4 66-.3c4.4 0 8 3.5 8 8 0 1.9-.7 3.7-1.9 5.2L553.5 514l130 155c1.2 1.5 1.9 3.3 1.9 5.2 0 4.4-3.6 8-8 8z"></path>
																					</svg>
																				</span>
                                        <!---->
																			</span>
																		</span>
          <span class="ml-20px">排序规则： </span>
          <span class="mm-select mail-config-select">
																			<div class="mm-selector--single mm-selector" tabindex="0">
																				<!---->
																				<div class="mm-selector-rendered">
																					<span title="最近使用时间优先" class="mm-selector-selected-value" style="opacity: 1;">最近使用时间优先</span>
                                          <!---->
																				</div>
                                        <!---->
																				<span class="mm-selector-suffix-icon">
																					<svg class="mm-icon mm-icon-chevrondown mm-selector-arrow" viewBox="0 0 24 24" name="chevrondown" fill="currentColor" style="height: 12px; width: 12px; outline: none;">
																						<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																					</svg>
																				</span>
																			</div>
																		</span>
          <span class="ml-20px">模板类型： </span>
          <span class="mm-select mail-config-select">
																			<div class="mm-selector--single mm-selector" tabindex="0">
																				<!---->
																				<div class="mm-selector-rendered">

																				</div>
                                        <!---->
																				<span class="mm-selector-suffix-icon">
																					<svg class="mm-icon mm-icon-chevrondown mm-selector-arrow" viewBox="0 0 24 24" name="chevrondown" fill="currentColor" style="height: 12px; width: 12px; outline: none;">
																						<path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
																					</svg>
																				</span>
																			</div>
																		</span>
        </div>
        <div class="tpl-content" v-show="!addTemplatePage">
          <div class="tpl-list">
            <div v-for="template in templates" :key="template.id" class="tpl-list-item rounded-8">
              <div class="icon-cross-p">
																					<span class="okki-icon-wrap">​<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																							<path fill-rule="evenodd" clip-rule="evenodd" d="M3.626 5.201c-.346-.345-.31-.942.081-1.332.39-.391.987-.427 1.333-.082l6.96 6.96 6.96-6.96c.346-.345.942-.309 1.333.082.39.39.427.987.082 1.332l-6.96 6.96 6.637 6.638c.346.346.31.942-.081 1.333-.391.39-.988.427-1.333.082L12 13.576l-6.638 6.638c-.345.345-.942.309-1.333-.082-.39-.39-.427-.987-.081-1.333l6.638-6.637-6.96-6.96z"></path>
																						</svg>
																					</span>
              </div>
              <p>
              </p>
              <div class="icon-edit-p">
																					<span class="okki-icon-wrap">​<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																							<path fill-rule="evenodd" clip-rule="evenodd" d="M13.586 3a2 2 0 012.828 0l2.118 2.118a2 2 0 010 2.829l-9.03 9.03a1 1 0 01-.707.293H5.262a1 1 0 01-1-1v-3.532a1 1 0 01.293-.707L13.585 3zm3.532 3.533L15 4.414l-8.738 8.738v2.118H8.38l8.738-8.737z"></path>
																							<path d="M2.5 20a1 1 0 011-1h17a1 1 0 110 2h-17a1 1 0 01-1-1z"></path>
																						</svg>
																					</span>
              </div>
              <p class="tpl-name">
                <label class="ellipsis">{{template.name}}</label>
              </p>
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


        <div class="tpl-edit" v-show="addTemplatePage">
          <div class="edit-field">
            <label class="m-label">名称：</label>
            <span class="mm-input">
                                      <!---->
                                      <span class="mm-input-affix-wrapper">
                                        <!---->
                                        <input type="text" class="mm-input-inner">
                                        <!---->
                                      </span>
              <!---->
                                    </span>
          </div>
          <div class="edit-field">
            <label class="m-label">模板类型：</label>
            <el-select v-model="templateTypeId" style="width: 20%" placeholder="请选择">
              <el-option
                v-for="templateType in templateTypes"
                :key="templateType.id"
                :label="templateType.name"
                :value="templateType.id">
              </el-option>
            </el-select>
            <span class="edit-btn" @click="addTemplateTypeBtn">
                                      <span class="okki-icon-wrap" color="#909090">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="#909090">
                                          <path fill-rule="evenodd" clip-rule="evenodd" d="M13.586 3a2 2 0 012.828 0l2.118 2.118a2 2 0 010 2.829l-9.03 9.03a1 1 0 01-.707.293H5.262a1 1 0 01-1-1v-3.532a1 1 0 01.293-.707L13.585 3zm3.532 3.533L15 4.414l-8.738 8.738v2.118H8.38l8.738-8.737z"></path>
                                          <path d="M2.5 20a1 1 0 011-1h17a1 1 0 110 2h-17a1 1 0 01-1-1z"></path>
                                        </svg>
                                      </span>
                                    </span>

          </div>
          <div class="edit-field">
            <label class="m-label">主题：</label>
            <span class="mm-input">
                                      <!---->
                                      <span class="mm-input-affix-wrapper">
                                        <!---->
                                        <input type="text" class="mm-input-inner">
                                        <!---->
                                      </span>
              <!---->
                                    </span>
          </div>
          <div class="edit-field">
            <label class="m-label"></label>
            <ul class="component-add-toolbar-attach">
              <!---->
            </ul>
          </div>
          <div class="edit-field">
            <label class="m-label">模版：</label>
            <div class="mail-editor" style="position: relative;">
              <Toolbar
                ref="editorInstance"
                style="border-bottom: 1px solid #ccc"
                :editor="editor"
                :defaultConfig="toolbarConfig"
              />
              <!-- 编辑器 -->
              <Editor
                ref="editorInstance"
                style="height: 350px; overflow-y: hidden"
                v-model="html"
                @onChange="onChange"
                @onCreated="onCreated"
                mode="default"
              />
            </div>
          </div>
          <div class="edit-field">
            <label class="m-label"></label>
            <div class="component-add-toolbar add-toolbar" save-edit="function () { [native code] }">
              <div class="mail-tool flex items-center">
                <!---->
                <span class="mm-tooltip tool-item flex items-center">
                                          <span class="mm-tooltip-trigger">
                                            <div class="tool-item flex items-center" title="">
                                              <img data-savepage-src="https://cdn.xiaoman.cn/crm_web/ks/prod/mail_subapp/static/img/appendix.f49c01fe.svg" src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTQiIGhlaWdodD0iMTQiIHZpZXdCb3g9IjAgMCAxNCAxNCIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPHJlY3Qgb3BhY2l0eT0iMC4wMSIgd2lkdGg9IjE0IiBoZWlnaHQ9IjE0IiBmaWxsPSIjM0QzRDNEIi8+CjxwYXRoIGQ9Ik0xMi4yMDk5IDYuMjUwMDdMNi44NzExIDExLjYyMDNDNi4zMzQwNiAxMi4xNjIgNS42MDI4NiAxMi40NjY4IDQuODQwMDUgMTIuNDY2OEM0LjA3NzI0IDEyLjQ2NjggMy4zNDYwNCAxMi4xNjIgMi44MDkwMSAxMS42MjAzQzEuNjg1MzUgMTAuNDg5OCAxLjY4NTM1IDguNjU0ODYgMi44MDkwMSA3LjUyNDM2TDguMjI3NTQgMi4wNzM2QzguNTUxNCAxLjc0NjgxIDguOTkyNDEgMS41NjI5NiA5LjQ1MjQ5IDEuNTYyOTZDOS45MTI1OCAxLjU2Mjk2IDEwLjM1MzYgMS43NDY4MSAxMC42Nzc0IDIuMDczNkMxMS4zNTY0IDIuNzU4NjkgMTEuMzU2NCAzLjg2Mjk4IDEwLjY3NzQgNC41NDgwN0w1LjQxNDc3IDkuODQxMzVDNS4zMDIzNCA5Ljk1NTEzIDUuMTQ5MDMgMTAuMDE5MiA0Ljk4OTA3IDEwLjAxOTJDNC44MjkxMSAxMC4wMTkyIDQuNjc1OCA5Ljk1NTEzIDQuNTYzMzcgOS44NDEzNUM0LjMyNTg2IDkuNjAxMjYgNC4zMjU4NiA5LjIxNDczIDQuNTYzMzcgOC45NzQ2NEw5LjI4NTU1IDQuMjIzODZMOC40Mjg1MSAzLjM3MjA1TDMuNzA2MzMgOC4xMjI4NEMzLjAwMDEgOC44MzQyMyAzLjAwMDEgOS45ODIxNiAzLjcwNjMzIDEwLjY5MzZDNC4wNDU1NiAxMS4wMzU2IDQuNTA3MzQgMTEuMjI4IDQuOTg5MDcgMTEuMjI4QzUuNDcwOCAxMS4yMjggNS45MzI1NyAxMS4wMzU2IDYuMjcxODEgMTAuNjkzNkwxMS41MzM3IDUuMzk5ODhDMTIuNjgxMSA0LjI0MzUzIDEyLjY4MTEgMi4zNzgxNCAxMS41MzM3IDEuMjIxOEMxMC45ODMxIDAuNjY3MDA5IDEwLjIzMzcgMC4zNTQ5OCA5LjQ1MjA5IDAuMzU0OThDOC42NzA0NCAwLjM1NDk4IDcuOTIxMTMgMC42NjcwMDkgNy4zNzA1IDEuMjIxOEwxLjk1MTk3IDYuNjcyMTVDMC4zNTkxMSA4LjI3NDI3IDAuMzU5MTEgMTAuODcwNCAxLjk1MTk3IDEyLjQ3MjFDMi43MTU3NyAxMy4yNDE4IDMuNzU1MjcgMTMuNjc0NyA0LjgzOTY1IDEzLjY3NDdDNS45MjQwMyAxMy42NzQ3IDYuOTYzNTMgMTMuMjQxOCA3LjcyNzMzIDEyLjQ3MjFMMTMuMDY2OSA3LjEwMTg4TDEyLjIwOTkgNi4yNTAwN1oiIGZpbGw9IiMzRDNEM0QiLz4KPC9zdmc+Cg==" style="width: 14px; height: 14px; margin-right: 4px;">
                                              <span>附件</span>
                                              <input id="attachInput" type="file" multiple="" value="">
                                            </div>
                                          </span>
                  <!---->
                                        </span>
                <div class="tool-item flex items-center">
                  <img data-savepage-src="https://cdn.xiaoman.cn/crm_web/ks/prod/mail_subapp/static/img/cloud.bd4a8ece.svg" src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTQiIGhlaWdodD0iMTQiIHZpZXdCb3g9IjAgMCAxNCAxNCIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPHBhdGggZmlsbC1ydWxlPSJldmVub2RkIiBjbGlwLXJ1bGU9ImV2ZW5vZGQiIGQ9Ik02Ljk5OTk4IDIuNjI0NzRDNi40Mjc5OCAyLjYyNDc0IDUuOTA2NDcgMi44MTU5MiA1LjUyNjk5IDMuMTk4MzVDNS4yNjM1MiAzLjQ2Mzg2IDUuMDkyNDMgMy43OTc0MiA1LjAxMjU5IDQuMTY3ODhDNC4zNTQ5MSA0LjM2MDgzIDMuNzkxNzEgNC45MDU0MyAzLjc5MTcxIDUuNjkwOTdDMy43OTE3MSA2LjI0NzI0IDQuMDQwODUgNi42ODUzNyA0LjQ1Mzg3IDYuOTU4MjJDNC44MzM2NCA3LjIwOTExIDUuMzA1MDkgNy4yOTEzNCA1Ljc0NTA5IDcuMjkxMzRMOC4xNjY3MSA3LjI5MTRMOC4xNjc3NiA3LjI5MTM0SDguMjUwNjJDOC42OTA2NCA3LjI5MTM0IDkuMTYyNTkgNy4yMDkwOSA5LjU0MzAzIDYuOTU4ODdDOS45NTY2NCA2LjY4Njg0IDEwLjIwODQgNi4yNDg5NSAxMC4yMDg0IDUuNjkwOTdDMTAuMjA4NCA1LjE1ODc1IDkuOTk3NTggNC43MjUyOSA5LjYyNDI4IDQuNDQyNTVDOS40Mjk1MSA0LjI5NTA0IDkuMjA5MzMgNC4yMDI1NSA4Ljk4NTkxIDQuMTQ5NjJDOC45MDM0MSAzLjc4OTU0IDguNzMyODkgMy40NjQ1NiA4LjQ3NTYgMy4yMDQ0NEM4LjA5NzM1IDIuODIyMDMgNy41NzU5MSAyLjYyNDc0IDYuOTk5OTggMi42MjQ3NFpNNy44NjI5NCA2LjEyNDc0SDYuMTI2NjJMNi4xMTgyNyA2LjEyNDY4SDUuNzQ1MDlDNS40MjU5OSA2LjEyNDY4IDUuMjEyNDIgNi4wNjEwOCA1LjA5Njk0IDUuOTg0NzlDNS4wMTQ3IDUuOTMwNDYgNC45NTgzNyA1Ljg2MDA4IDQuOTU4MzcgNS42OTA5N0M0Ljk1ODM3IDUuNTE2NTQgNS4xNDY0IDUuMjU3MjkgNS41NDM0NiA1LjI1NzI5QzUuODY1NjIgNS4yNTcyOSA2LjEyNjc5IDQuOTk2MTIgNi4xMjY3OSA0LjY3Mzk2QzYuMTI2NzkgNC4zNjAxIDYuMjI2MzIgNC4xNDk5MyA2LjM1NTEzIDQuMDIwMTJDNi40ODM0NiAzLjg5MDc5IDYuNjkwMjEgMy43OTE0IDYuOTk5OTggMy43OTE0QzcuMzA1ODUgMy43OTE0IDcuNTE0NjMgMy44OTE5MiA3LjY0NjE0IDQuMDI0ODhDNy43Nzg0OSA0LjE1ODY4IDcuODc3MDggNC4zNzA0NiA3Ljg3NzA4IDQuNjczOTZDNy44NzcwOCA0Ljk5NjEyIDguMTM4MjUgNS4yNTcyOSA4LjQ2MDQyIDUuMjU3MjlDOC42ODc1MSA1LjI1NzI5IDguODM5MzUgNS4zMTE1OCA4LjkxOTg4IDUuMzcyNTdDOC45NzU5OSA1LjQxNTA3IDkuMDQxNzEgNS40OTAxMiA5LjA0MTcxIDUuNjkwOTdDOS4wNDE3MSA1Ljg1ODM2IDguOTg1NzcgNS45MjkgOC45MDE5MyA1Ljk4NDE0QzguNzg0OTIgNi4wNjExIDguNTY5NjYgNi4xMjQ2OCA4LjI1MDYyIDYuMTI0NjhINy44NzEyOEw3Ljg2Mjk0IDYuMTI0NzRaIiBmaWxsPSIjM0QzRDNEIi8+CjxwYXRoIGQ9Ik0xMS4wODM0IDEwLjM1NDJDMTEuMDgzNCAxMC43NTY5IDEwLjc1NjkgMTEuMDgzNCAxMC4zNTQyIDExLjA4MzRDOS45NTE1IDExLjA4MzQgOS42MjUwNCAxMC43NTY5IDkuNjI1MDQgMTAuMzU0MkM5LjYyNTA0IDkuOTUxNDggOS45NTE1IDkuNjI1MDIgMTAuMzU0MiA5LjYyNTAyQzEwLjc1NjkgOS42MjUwMiAxMS4wODM0IDkuOTUxNDggMTEuMDgzNCAxMC4zNTQyWiIgZmlsbD0iIzNEM0QzRCIvPgo8cGF0aCBmaWxsLXJ1bGU9ImV2ZW5vZGQiIGNsaXAtcnVsZT0iZXZlbm9kZCIgZD0iTTIuNjM2MjMgMC44NzQ0NTFDMi4zNjAyNSAwLjg3NDQ1MSAyLjEyMjA0IDEuMDY3ODUgMi4wNjUzNCAxLjMzNzk0TDAuNTk1ODE3IDguMzM4MjNDMC41ODc1NDQgOC4zNzc2NCAwLjU4MzM3NCA4LjQxNzggMC41ODMzNzQgOC40NTgwN1YxMi4yNDk3QzAuNTgzMzc0IDEyLjU3MTkgMC44NDQ1NDEgMTIuODMzMSAxLjE2NjcxIDEyLjgzMzFIMTIuODMzNEMxMy4xNTU1IDEyLjgzMzEgMTMuNDE2NyAxMi41NzE5IDEzLjQxNjcgMTIuMjQ5N1Y4LjQ1ODA3QzEzLjQxNjcgOC40MTgyNiAxMy40MTI2IDguMzc4NTUgMTMuNDA0NSA4LjMzOTU3TDExLjk1MjIgMS4zMzkyOEMxMS44OTYgMS4wNjg1NiAxMS42NTc1IDAuODc0NDUxIDExLjM4MSAwLjg3NDQ1MUgyLjYzNjIzWk0xLjg4NTIxIDcuODc0NzRMMy4xMDk4MiAyLjA0MTEySDEwLjkwNjNMMTIuMTE2NiA3Ljg3NDc0SDEuODg1MjFaTTEuNzUwMDQgOS4wNDE0SDEyLjI1VjExLjY2NjRIMS43NTAwNFY5LjA0MTRaIiBmaWxsPSIjM0QzRDNEIi8+Cjwvc3ZnPgo=" style="width: 14px; height: 14px; margin-right: 4px;"> 云文档</div>
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
                  <img data-savepage-src="https://cdn.xiaoman.cn/crm_web/ks/prod/mail_subapp/static/img/template.b98c6551.svg" src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTQiIGhlaWdodD0iMTQiIHZpZXdCb3g9IjAgMCAxNCAxNCIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPHBhdGggZD0iTTExLjkwODUgMC45NTg4MDFDMTIuMjA4OSAwLjk1ODgwMSAxMi40OTcgMS4wNzgxNCAxMi43MDk1IDEuMjkwNTdDMTIuOTIxOSAxLjUwMjk5IDEzLjA0MTIgMS43OTExMSAxMy4wNDEyIDIuMDkxNTJWMTEuOTA4NEMxMy4wNDEyIDEyLjIwODkgMTIuOTIxOSAxMi40OTcgMTIuNzA5NSAxMi43MDk0QzEyLjQ5NyAxMi45MjE4IDEyLjIwODkgMTMuMDQxMiAxMS45MDg1IDEzLjA0MTJIMi4wOTE1OEMxLjc5MTE3IDEzLjA0MTIgMS41MDMwNSAxMi45MjE4IDEuMjkwNjMgMTIuNzA5NEMxLjA3ODIgMTIuNDk3IDAuOTU4ODYyIDEyLjIwODkgMC45NTg4NjIgMTEuOTA4NFYyLjA5MTUyQzAuOTU4ODYyIDEuNzkxMTEgMS4wNzgyIDEuNTAyOTkgMS4yOTA2MyAxLjI5MDU3QzEuNTAzMDUgMS4wNzgxNCAxLjc5MTE3IDAuOTU4ODAxIDIuMDkxNTggMC45NTg4MDFIMTEuOTA4NVpNMTEuOTA4NSAyLjA5MTUySDIuMDkxNThWMTEuOTA4NEgxMS45MDg1VjIuMDkxNTJaTTExLjE1MzQgOC44ODc4NVYxMC4wMjA2SDIuODQ2NzNWOC44ODc4NUgxMS4xNTM0Wk02LjI0NDg5IDMuNjAxODJDNi41NDUzMSAzLjYwMTgyIDYuODMzNDIgMy43MjExNiA3LjA0NTg1IDMuOTMzNThDNy4yNTgyNyA0LjE0NjAxIDcuMzc3NjEgNC40MzQxMiA3LjM3NzYxIDQuNzM0NTRWNi42MjI0MUM3LjM3NzYxIDYuOTIyODIgNy4yNTgyNyA3LjIxMDkzIDcuMDQ1ODUgNy40MjMzNkM2LjgzMzQyIDcuNjM1NzkgNi41NDUzMSA3Ljc1NTEzIDYuMjQ0ODkgNy43NTUxM0gzLjk3OTQ1QzMuNjc5MDQgNy43NTUxMyAzLjM5MDkyIDcuNjM1NzkgMy4xNzg1IDcuNDIzMzZDMi45NjYwNyA3LjIxMDkzIDIuODQ2NzMgNi45MjI4MiAyLjg0NjczIDYuNjIyNDFWNC43MzQ1NEMyLjg0NjczIDQuNDM0MTIgMi45NjYwNyA0LjE0NjAxIDMuMTc4NSAzLjkzMzU4QzMuMzkwOTIgMy43MjExNiAzLjY3OTA0IDMuNjAxODIgMy45Nzk0NSAzLjYwMTgySDYuMjQ0ODlaTTExLjE1MzQgNi42MjI0MVY3Ljc1NTEzSDguMTMyNzZWNi42MjI0MUgxMS4xNTM0Wk02LjI0NDg5IDQuNzM0NTRIMy45Nzk0NVY2LjYyMjQxSDYuMjQ0ODlWNC43MzQ1NFoiIGZpbGw9IiMzRDNEM0QiLz4KPC9zdmc+Cg==" style="width: 14px; height: 14px; margin-right: 4px;"> 模板</div>
                <!---->
                <div class="tool-item flex items-center">
                  <img data-savepage-src="https://cdn.xiaoman.cn/crm_web/ks/prod/mail_subapp/static/img/image.fb78e8ce.svg" src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTQiIGhlaWdodD0iMTQiIHZpZXdCb3g9IjAgMCAxNCAxNCIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KPHBhdGggZmlsbC1ydWxlPSJldmVub2RkIiBjbGlwLXJ1bGU9ImV2ZW5vZGQiIGQ9Ik0zLjIwODMzIDUuMjUwMDJDMy4yMDgzMyA0LjEyMjQ1IDQuMTIyNDIgMy4yMDgzNSA1LjI0OTk5IDMuMjA4MzVDNi4zNzc1NyAzLjIwODM1IDcuMjkxNjYgNC4xMjI0NSA3LjI5MTY2IDUuMjUwMDJDNy4yOTE2NiA2LjM3NzU5IDYuMzc3NTcgNy4yOTE2OSA1LjI0OTk5IDcuMjkxNjlDNC4xMjI0MiA3LjI5MTY5IDMuMjA4MzMgNi4zNzc1OSAzLjIwODMzIDUuMjUwMDJaTTUuMjQ5OTkgNC4zNzUwMkM0Ljc2Njc1IDQuMzc1MDIgNC4zNzQ5OSA0Ljc2Njc4IDQuMzc0OTkgNS4yNTAwMkM0LjM3NDk5IDUuNzMzMjYgNC43NjY3NSA2LjEyNTAyIDUuMjQ5OTkgNi4xMjUwMkM1LjczMzIzIDYuMTI1MDIgNi4xMjQ5OSA1LjczMzI2IDYuMTI0OTkgNS4yNTAwMkM2LjEyNDk5IDQuNzY2NzggNS43MzMyMyA0LjM3NTAyIDUuMjQ5OTkgNC4zNzUwMloiIGZpbGw9IiMzRDNEM0QiLz4KPHBhdGggZmlsbC1ydWxlPSJldmVub2RkIiBjbGlwLXJ1bGU9ImV2ZW5vZGQiIGQ9Ik0xLjE2NjY2IDIuNjI1MDJDMS4xNjY2NiAxLjgxOTYxIDEuODE5NTggMS4xNjY2OSAyLjYyNDk5IDEuMTY2NjlIMTEuMzc1QzEyLjE4MDQgMS4xNjY2OSAxMi44MzMzIDEuODE5NjEgMTIuODMzMyAyLjYyNTAyVjEwLjQ5NzdDMTIuODMzMyAxMC40OTk0IDEyLjgzMzMgMTAuNTAxIDEyLjgzMzMgMTAuNTAyN1YxMS4zNzVDMTIuODMzMyAxMi4xODA0IDEyLjE4MDQgMTIuODMzNCAxMS4zNzUgMTIuODMzNEgyLjYyNDk5QzEuODE5NTggMTIuODMzNCAxLjE2NjY2IDEyLjE4MDUgMS4xNjY2NiAxMS4zNzVWMTAuMjE1MUMxLjE2NjYxIDEwLjIxMDkgMS4xNjY2MSAxMC4yMDY3IDEuMTY2NjYgMTAuMjAyNVYyLjYyNTAyWk0yLjMzMzMzIDEwLjVWMTEuMzc1QzIuMzMzMzMgMTEuNTM2MSAyLjQ2MzkgMTEuNjY2NyAyLjYyNDk5IDExLjY2NjdIMTEuMzc1QzExLjUzNjEgMTEuNjY2NyAxMS42NjY3IDExLjUzNjEgMTEuNjY2NyAxMS4zNzVWMTAuNzU4MUw5LjAzOTY5IDguMzY5OTJMNi41MTUyMiAxMC42NDE5QzYuMjk3MzggMTAuODM4IDUuOTY3ODkgMTAuODQyIDUuNzQ1MzcgMTAuNjUxM0w0LjA1ODk4IDkuMjA1NzhMMi4zMzMzMyAxMC41Wk0xMS42NjY3IDkuMTgxMzZMOS40MzQwNSA3LjE1MTcyQzkuMjEyMzggNi45NTAyIDguODc0MSA2Ljk0OTM2IDguNjUxNDMgNy4xNDk3N0w2LjExNTYgOS40MzIwMUw0LjQ2Mjk1IDguMDE1NDVDNC4yNTUzNyA3LjgzNzUyIDMuOTUyMDUgNy44Mjc2NCAzLjczMzMzIDcuOTkxNjlMMi4zMzMzMyA5LjA0MTY5VjIuNjI1MDJDMi4zMzMzMyAyLjQ2Mzk0IDIuNDYzOTEgMi4zMzMzNSAyLjYyNDk5IDIuMzMzMzVIMTEuMzc1QzExLjUzNjEgMi4zMzMzNSAxMS42NjY3IDIuNDYzOTMgMTEuNjY2NyAyLjYyNTAyVjkuMTgxMzZaIiBmaWxsPSIjM0QzRDNEIi8+Cjwvc3ZnPgo=" style="width: 14px; height: 14px; margin-right: 4px;">
                  <span>图片</span>
                  <input title="未选择任何文件" type="file" multiple="" value="">
                </div>
                <!---->
                <!---->
                <!---->
                <!---->
                <!---->
              </div>
              <span class="mail-size">邮件大小：3 KB</span>
              <!---->
              <!---->
              <!---->
              <!---->
              <!---->
              <!---->
              <!---->
            </div>
          </div>
          <div class="edit-field edit-btns">
            <button type="button" class="mm-button">
              <!---->
              <!---->取消
              <!---->
            </button>
            <button type="button" class="mm-button mm-button__primary">
              <!---->
              <!---->
              <span>保存</span>
              <!---->
            </button>
          </div>
          <templateTypeTemplate ref="templateType"></templateTypeTemplate>
        </div>
      </div>
    </div>
  </div>
</template>
<style lang="scss">
.ml-20px {
  margin-left: 20px;
}
@import '../../../static/scss/email/template/75024.5dafeb01.css';
</style>

<style src="@wangeditor/editor/dist/css/style.css"></style>
<script>
import { Editor, Toolbar } from "@wangeditor/editor-for-vue";
import {listTemplate} from "@/api/email/template";
import {listTemplateType} from "@/api/email/templateType";
import {EventBus} from "@/api/email/event-bus";
import templateTypeTemplate from './templateType.vue';

export default {
  data() {
    return {
      templates: [], // 模板列表数据
      addTemplatePage: false, // 添加模板页面
      html: null,
      editor: null,
      toolbarConfig: {},
      templateTypeId: null, //模板类型id
      templateTypes: [], //模板类型列表
    };
  },

  components: { Editor, Toolbar, templateTypeTemplate},

  methods: {
    refreshTemplateList() {
      listTemplate().then((response) => {
        this.templates = response.data;
      });
    },

    refreshTemplateTypeList() {
      listTemplateType().then((response) => {
        this.templateTypes = response.data;
      });
    },

    addTemplateBtn() {
      this.addTemplatePage = true;
    },

    addTemplateTypeBtn() {
      this.$refs.templateType.open();
    },

    onCreated(editor) {
      this.editor = Object.seal(editor);
      setTimeout(() => {
        this.editor.dangerouslyInsertHtml(this.htmlText);
      }, 500);
    },

    onChange(editor) {
    },
  },

  mounted() {
    EventBus.$on('refresh-template-list', this.refreshTemplateList);
  },

  beforeDestroy() {
    const editor = this.editor;
    if (editor == null) return;
    editor.destroy();

    EventBus.$off('refresh-template-list', this.refreshTemplateList);
  },

  created() {
    this.refreshTemplateList();
    this.refreshTemplateTypeList();
  },
};
</script>
