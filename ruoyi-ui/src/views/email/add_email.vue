<template>
  <el-dialog :visible="addEmailPage"
              width="0%"
              :append-to-body="true"
              @close="close">
    <el-form ref="elForm" :model="formData" size="medium" label-width="100px">
      <div class="mm-modal--mask mm-modal novice-tour-add-mail-dialog" id="report-stat-config-sub-routes-manage" modal="[object Object]" component="[object Object]" style="z-index: 100;">
        <div class="mm-modal-mask"></div>
        <div class="mm-modal-wrapper" style="padding-top: 15vh;">
          <div class="bind-mail-modal mm-modal-content" style="width: 560px; border-color: transparent; transition: transform 0.1s ease 0s; transform: translate(0px, 0px);">
            <svg class="mm-icon mm-icon-close mm-modal-close" viewBox="0 0 24 24" name="close" fill="currentColor" style="height: 14px; width: 14px;" @click="close">
              <path d="M14.3 11.7l6-6c.3-.3.3-.7 0-1l-.9-1c-.3-.2-.7-.2-1 0l-6 6.1c-.2.2-.5.2-.7 0l-6-6.1c-.3-.3-.7-.3-1 0l-1 1c-.2.2-.2.7 0 .9l6.1 6.1c.2.2.2.4 0 .6l-6.1 6.1c-.3.3-.3.7 0 1l1 1c.2.2.7.2.9 0l6.1-6.1c.2-.2.4-.2.6 0l6.1 6.1c.2.2.7.2.9 0l1-1c.3-.3.3-.7 0-1l-6-6c-.2-.2-.2-.5 0-.7z"></path>
            </svg>
            <div class="mm-modal-header">
              <div class="mm-modal-title">
                <span>新增邮箱</span>
              </div>
            </div>
            <div class="mm-modal-body">
              <div class="bind-email-wrapper" ischeckpwdsecurity="true">
                <div class="bind-email-dialog" ischeckpwdsecurity="true">
                  <div class="bind-email-content">
                    <form class="mm-form-vertical mm-form bind-email-dialog-form">
                      <!---->
                      <span class="mm-tooltip" style="width: 100%;">
                                        <span class="mm-tooltip-trigger"></span>
                        <!---->
                                    </span>
                      <!---->
    <!--                  <div class="mm-form-field">
                        <div class="mm-form-field-label">
                          <span>收发件服务器验证</span>
                        </div>
                        <div class="mm-form-field-control">
                                            <span class="mm-form-field-children">
                                                <span class="mm-select">
                                                    <div class="mm-selector&#45;&#45;single mm-selector" tabindex="0">
                                                        &lt;!&ndash;&ndash;&gt;
                                                        <div class="mm-selector-rendered">
                                                            <span title="与邮箱相同" class="mm-selector-selected-value" style="opacity: 1;">与邮箱相同</span>
                                                          &lt;!&ndash;&ndash;&gt;
                                                        </div>
                                                      &lt;!&ndash;&ndash;&gt;
                                                        <span class="mm-selector-suffix-icon">
                                                            <svg class="mm-icon mm-icon-chevrondown mm-selector-arrow" viewBox="0 0 24 24" name="chevrondown" fill="currentColor" style="height: 12px; width: 12px; outline: none;">
                                                                <path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
                                                            </svg>
                                                        </span>
                                                    </div>
                                                </span>
                                            </span>
                          &lt;!&ndash;&ndash;&gt;
                          &lt;!&ndash;&ndash;&gt;
                        </div>
                      </div>-->
                      <div class="mm-form-field--required mm-form-field work-email-field">
                        <div class="mm-form-field-label">
                          <span>工作邮箱</span>
                        </div>
                        <div class="mm-form-field-control">
                                            <span class="mm-form-field-children">
                                                <span class="mm-input">
                                                    <!---->
                                                    <span class="mm-input-affix-wrapper">
                                                        <!---->
                                                        <input v-model="formData.account" modelmodifiers="[object Object]" placeholder="请输入邮箱账号" type="text" class="mm-input-inner" value="">
                                                        <div v-if="errors.account" class="error-text">{{ errors.account }}</div>
                                                      <!---->
                                                        </span>
                                                  <!---->
                                                    </span>
                                                </span>
                          <!---->
                          <!---->
                        </div>
                      </div>
                      <!---->
                      <div class="mm-form-field--required mm-form-field email-password">
                        <div class="mm-form-field-label">
                          <span>邮箱密码</span>
                        </div>
                        <div class="mm-form-field-control">
                                                <span class="mm-form-field-children">
                                                    <div class="enter-code-guide">
                                                        <div class="mm-popover">
                                                            <div></div>
                                                          <!---->
                                                        </div>
                                                    </div>
                                                    <span class="mm-input">
                                                        <!---->
                                                        <span class="mm-input-affix-wrapper">
                                                            <!---->
                                                            <input v-model="formData.password" autocomplete="new-password"  placeholder="请输入邮箱密码或授权码" type="password" class="mm-input-inner" value="">
                                                            <div v-if="errors.password" class="error-text">{{ errors.password }}</div>
                                                          <!---->
                                                            </span>
                                                      <!---->
                                                        </span>
                                                    </span>
                          <!---->
                          <!---->
                        </div>
                      </div>
                      <div class="mm-form-field--required mm-form-field mail-bind-radio-field" v-if="showManualConfig">
                        <div class="mm-form-field-label">
                          <span>协议类型</span>
                        </div>
                        <div class="mm-form-field-control">
                                                    <span class="mm-form-field-children">
                                                        <!---->
                                                        <div class="mm-radio-group">
                                                            <label class="mm-radio">
                                                                <input v-model="formData.protocolType" show="function(){return!0}" name="mm-radio-group-60" type="radio" value="1" checked>
                                                                    <span class="mm-radio-faux">
                                                                        <span class="mm-radio-input"></span>
                                                                        <span class="mm-radio-label">IMAP</span>
                                                                    </span>
                                                                </label>
                                                                <label class="mm-radio">
                                                                    <input v-model="formData.protocolType" show="function(){return!0}" name="mm-radio-group-60" type="radio" value="2">
                                                                        <span class="mm-radio-faux">
                                                                            <span class="mm-radio-input"></span>
                                                                            <span class="mm-radio-label">POP3 (不推荐,无法同步发件)</span>
                                                                        </span>
                                                                    </label>
                                                                    <label class="mm-radio">
                                                                        <input v-model="formData.protocolType" show="function(e){return e.setting.multi_user_name_flag!==We.CUSTOM}" name="mm-radio-group-60" type="radio" value="3">
                                                                            <span class="mm-radio-faux">
                                                                                <span class="mm-radio-input"></span>
                                                                                <span class="mm-radio-label">Exchange</span>
                                                                            </span>
                                                                        </label>
                                                                    </div>
                                                                    <div class="protocol-tip">由于exchange协议支持问题，网易邮箱推荐使用IMAP协议。</div>
                                                                </span>
                          <!---->
                          <!---->
                        </div>
                      </div>
                      <div class="mm-form-field--required mm-form-field" v-if="showManualConfig">
                        <div class="mm-form-field-label">
                          <span>收邮件服务器</span>
                        </div>
                        <div class="mm-form-field-control">
                                                                <span class="mm-form-field-children">
                                                                    <div class="mm-input-group">
                                                                        <div class="mm-col mm-col-14">
                                                                            <span class="mm-input">
                                                                                <!---->
                                                                                <span class="mm-input-affix-wrapper">
                                                                                    <!---->
                                                                                    <input v-model="formData.receivingServer" modelmodifiers="[object Object]" placeholder="收邮件服务器" type="text" class="mm-input-inner" value="">
                                                                                  <!---->
                                                                                    </span>
                                                                              <!---->
                                                                                </span>
                                                                            </div>
                                                                            <div class="mm-col mm-col-1">
                                                                                <span>:</span>
                                                                            </div>
                                                                            <div class="mm-col mm-col-5">
                                                                                <span class="mm-input">
                                                                                    <!---->
                                                                                    <span class="mm-input-affix-wrapper">
                                                                                        <!---->
                                                                                        <input v-model="formData.receivingPort" modelmodifiers="[object Object]" placeholder="端口" type="text" class="mm-input-inner" value="">
                                                                                      <!---->
                                                                                        </span>
                                                                                  <!---->
                                                                                    </span>
                                                                                </div>
                                                                                <div class="mm-col mm-col-4">
                                                                                    <label class="mm-checkbox">
                                                                                        <input v-model="formData.receivingSslFlag" true-value="true" false-value="false" type="checkbox">
                                                                                            <span class="mm-checkbox-input"></span>
                                                                                            <span class="mm-checkbox-label"> SSL </span>
                                                                                        </label>
                                                                                    </div>
                                                                                </div>
                                                                            </span>
                          <!---->
                          <!---->
                        </div>
                      </div>
                      <!---->
                      <div class="mm-form-field--required mm-form-field" v-if="showManualConfig">
                        <div class="mm-form-field-label">
                          <span>发邮件服务器</span>
                        </div>
                        <div class="mm-form-field-control">
                                                                            <span class="mm-form-field-children">
                                                                                <div class="mm-input-group">
                                                                                    <div class="mm-col mm-col-14">
                                                                                        <span class="mm-input">
                                                                                            <!---->
                                                                                            <span class="mm-input-affix-wrapper">
                                                                                                <!---->
                                                                                                <input v-model="formData.outgoingServer" modelmodifiers="[object Object]" placeholder="发邮件服务器" type="text" class="mm-input-inner" value="">
                                                                                              <!---->
                                                                                                </span>
                                                                                          <!---->
                                                                                            </span>
                                                                                        </div>
                                                                                        <div class="mm-col mm-col-1">
                                                                                            <span>:</span>
                                                                                        </div>
                                                                                        <div class="mm-col mm-col-5">
                                                                                            <span class="mm-input">
                                                                                                <!---->
                                                                                                <span class="mm-input-affix-wrapper">
                                                                                                    <!---->
                                                                                                    <input v-model="formData.outgoingPort" modelmodifiers="[object Object]" placeholder="端口" type="text" class="mm-input-inner" value="">
                                                                                                  <!---->
                                                                                                    </span>
                                                                                              <!---->
                                                                                                </span>
                                                                                            </div>
                                                                                            <div class="mm-col mm-col-4">
                                                                                                <label class="mm-checkbox">
                                                                                                    <input v-model="formData.outgoingSslFlag" true-value="true" false-value="false" type="checkbox">
                                                                                                        <span class="mm-checkbox-input"></span>
                                                                                                        <span class="mm-checkbox-label"> SSL </span>
                                                                                                    </label>
                                                                                                </div>
                                                                                            </div>
                                                                                        </span>
                          <!---->
                          <!---->
                        </div>
                      </div>
                      <!---->
                      <div class="mm-form-field mail-bind-radio-field" v-if="showManualConfig">
                        <div class="mm-form-field-label">
                                                                                        <span class="mm-tooltip">
                                                                                            <span class="mm-tooltip-trigger">
                                                                                                <span class="okki-icon-wrap">​

                                                                                                    <svg
                                                                                                      xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
                                                                                                        <path fill-rule="evenodd" clip-rule="evenodd" d="M6.444 3.685a10 10 0 1111.112 16.63A10 10 0 016.444 3.685zm1.111 14.967a8 8 0 108.89-13.305 8 8 0 00-8.89 13.305zM12.072 6.93c-.43 0-.827.046-1.192.142a3.829 3.829 0 00-1.078.479c-.26.157-.442.394-.442.711 0 .213.083.4.232.548a.76.76 0 00.548.232.831.831 0 00.461-.143 2.14 2.14 0 01.618-.25l.006-.001c.247-.065.497-.098.751-.098.542 0 .894.105 1.105.266.196.149.311.377.311.742a.622.622 0 01-.148.41 4.175 4.175 0 01-.626.579l-.002.001a7.704 7.704 0 00-.725.65 2.81 2.81 0 00-.498.752c-.15.32-.221.68-.221 1.076 0 .213.083.4.232.548l.009.009.01.008c.164.14.363.203.577.203.21 0 .405-.066.566-.203l.01-.009a.743.743 0 00.252-.556c0-.265.068-.465.188-.62.156-.197.396-.429.73-.692a7.65 7.65 0 00.662-.576l.005-.005c.184-.193.337-.42.461-.677l.002-.003c.133-.286.196-.607.196-.955 0-.777-.281-1.417-.852-1.887-.556-.464-1.282-.681-2.148-.681zm-.096 8.116a.99.99 0 00-.716.304.97.97 0 00-.292.704c0 .276.097.522.286.722l.006.006.006.006c.198.187.439.286.71.286a.984.984 0 00.716-.292c.2-.199.304-.447.304-.728a.95.95 0 00-.304-.704.965.965 0 00-.716-.304z"></path>
                                                                                                    </svg>
                                                                                                </span>
                                                                                            </span>
                                                                                          <!---->
                                                                                        </span>
                          <span class="radio-label">自定义代理</span>
                        </div>
                        <div class="mm-form-field-control">
                                                                                        <span class="mm-form-field-children">
                                                                                            <div class="mm-radio-group">
                                                                                                <label class="mm-radio">
                                                                                                    <input v-model="formData.customProxyFlag" @change="toggleProxySettings(true)" name="mm-radio-group-61" type="radio" value="true" checked="">
                                                                                                        <span class="mm-radio-faux">
                                                                                                            <span class="mm-radio-input"></span>
                                                                                                            <span class="mm-radio-label">开启</span>
                                                                                                        </span>
                                                                                                    </label>
                                                                                                    <label class="mm-radio">
                                                                                                        <input v-model="formData.customProxyFlag" @change="toggleProxySettings(false)" name="mm-radio-group-61" type="radio" value="false">
                                                                                                            <span class="mm-radio-faux">
                                                                                                                <span class="mm-radio-input"></span>
                                                                                                                <span class="mm-radio-label">关闭</span>
                                                                                                            </span>
                                                                                                        </label>
                                                                                                    </div>
                                                                                                </span>
                          <!---->
                          <!---->
                        </div>
                      </div>
                      <div class="mm-form-field--required mm-form-field" v-if="formData.customProxyFlag && showManualConfig">
                        <div class="mm-form-field-label">
                          <span>服务器类型</span>
                        </div>
                        <div class="mm-form-field-control">
                          <el-select v-model="formData.proxyServerType" class="mm-select">
                            <el-option label="HTTP代理" value="1"></el-option>
                            <el-option label="SOCKS5" value="2"></el-option>
                          </el-select>
                        </div>
                      </div>
                      <div class="mm-form-field--required mm-form-field" v-if="formData.customProxyFlag && showManualConfig" >
                        <div class="mm-form-field-label">
                          <span>服务器</span>
                        </div>
                        <div class="mm-form-field-control">
                                                                                                <span class="mm-form-field-children">
                                                                                                    <div class="mm-input-group">
                                                                                                        <div class="mm-col mm-col-15">
                                                                                                            <span class="mm-input">
                                                                                                                <!---->
                                                                                                                <span class="mm-input-affix-wrapper">
                                                                                                                    <!---->
                                                                                                                    <input v-model="formData.proxyServer" modelmodifiers="[object Object]" placeholder="服务器" type="text" class="mm-input-inner" value="">
                                                                                                                  <!---->
                                                                                                                    </span>
                                                                                                              <!---->
                                                                                                                </span>
                                                                                                            </div>
                                                                                                            <div class="mm-col mm-col-1">
                                                                                                                <span>:</span>
                                                                                                            </div>
                                                                                                            <div class="mm-col mm-col-8">
                                                                                                                <span class="mm-input">
                                                                                                                    <!---->
                                                                                                                    <span class="mm-input-affix-wrapper">
                                                                                                                        <!---->
                                                                                                                        <input v-model="formData.proxyPort" modelmodifiers="[object Object]" placeholder="端口" type="text" class="mm-input-inner" value="">
                                                                                                                      <!---->
                                                                                                                        </span>
                                                                                                                  <!---->
                                                                                                                    </span>
                                                                                                                </div>
                                                                                                            </div>
                                                                                                        </span>
                          <!---->
                          <!---->
                        </div>
                      </div>
                      <div class="mm-form-field" v-if="formData.customProxyFlag && showManualConfig">
                        <div class="mm-form-field-label">
                          <span>用户名</span>
                        </div>
                        <div class="mm-form-field-control">
                                                                                                        <span class="mm-form-field-children">
                                                                                                            <span class="mm-input">
                                                                                                                <!---->
                                                                                                                <span class="mm-input-affix-wrapper">
                                                                                                                    <!---->
                                                                                                                    <input v-model="formData.proxyUsername" modelmodifiers="[object Object]" placeholder="请输入用户名" type="text" class="mm-input-inner" value="">
                                                                                                                  <!---->
                                                                                                                    </span>
                                                                                                              <!---->
                                                                                                                </span>
                                                                                                            </span>
                          <!---->
                          <!---->
                        </div>
                      </div>
                      <div class="mm-form-field" v-if="formData.customProxyFlag && showManualConfig">
                        <div class="mm-form-field-label">
                          <span>密码</span>
                        </div>
                        <div class="mm-form-field-control">
                                                                                                            <span class="mm-form-field-children">
                                                                                                                <span class="mm-input">
                                                                                                                    <!---->
                                                                                                                    <span class="mm-input-affix-wrapper">
                                                                                                                        <!---->
                                                                                                                        <input v-model="formData.proxyPassword" placeholder="请输入密码" type="password" class="mm-input-inner" value="">
                                                                                                                      <!---->
                                                                                                                        </span>
                                                                                                                  <!---->
                                                                                                                    </span>
                                                                                                                </span>
                          <!---->
                          <!---->
                        </div>
                      </div>
                      <div class="mm-form-field mail-bind-radio-field" v-if="showManualConfig">
                        <div class="mm-form-field-label">
                                                                                                                <span class="mm-tooltip">
                                                                                                                    <span class="mm-tooltip-trigger">
                                                                                                                        <span class="okki-icon-wrap">​

                                                                                                                            <svg
                                                                                                                              xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
                                                                                                                                <path fill-rule="evenodd" clip-rule="evenodd" d="M6.444 3.685a10 10 0 1111.112 16.63A10 10 0 016.444 3.685zm1.111 14.967a8 8 0 108.89-13.305 8 8 0 00-8.89 13.305zM12.072 6.93c-.43 0-.827.046-1.192.142a3.829 3.829 0 00-1.078.479c-.26.157-.442.394-.442.711 0 .213.083.4.232.548a.76.76 0 00.548.232.831.831 0 00.461-.143 2.14 2.14 0 01.618-.25l.006-.001c.247-.065.497-.098.751-.098.542 0 .894.105 1.105.266.196.149.311.377.311.742a.622.622 0 01-.148.41 4.175 4.175 0 01-.626.579l-.002.001a7.704 7.704 0 00-.725.65 2.81 2.81 0 00-.498.752c-.15.32-.221.68-.221 1.076 0 .213.083.4.232.548l.009.009.01.008c.164.14.363.203.577.203.21 0 .405-.066.566-.203l.01-.009a.743.743 0 00.252-.556c0-.265.068-.465.188-.62.156-.197.396-.429.73-.692a7.65 7.65 0 00.662-.576l.005-.005c.184-.193.337-.42.461-.677l.002-.003c.133-.286.196-.607.196-.955 0-.777-.281-1.417-.852-1.887-.556-.464-1.282-.681-2.148-.681zm-.096 8.116a.99.99 0 00-.716.304.97.97 0 00-.292.704c0 .276.097.522.286.722l.006.006.006.006c.198.187.439.286.71.286a.984.984 0 00.716-.292c.2-.199.304-.447.304-.728a.95.95 0 00-.304-.704.965.965 0 00-.716-.304z"></path>
                                                                                                                            </svg>
                                                                                                                        </span>
                                                                                                                    </span>
                                                                                                                  <!---->
                                                                                                                </span>
                          <span class="radio-label">同步文件夹</span>
                        </div>
                        <div class="mm-form-field-control">
                                                                                                                <span class="mm-form-field-children">
                                                                                                                    <div class="mm-radio-group">
                                                                                                                        <label class="mm-radio">
                                                                                                                            <input v-model="formData.synchronizeFolderFlag" name="mm-radio-group-62" type="radio" value="true" checked="">
                                                                                                                                <span class="mm-radio-faux">
                                                                                                                                    <span class="mm-radio-input"></span>
                                                                                                                                    <span class="mm-radio-label">开启</span>
                                                                                                                                </span>
                                                                                                                            </label>
                                                                                                                            <label class="mm-radio">
                                                                                                                                <input v-model="formData.synchronizeFolderFlag" name="mm-radio-group-62" type="radio" value="false">
                                                                                                                                    <span class="mm-radio-faux">
                                                                                                                                        <span class="mm-radio-input"></span>
                                                                                                                                        <span class="mm-radio-label">关闭</span>
                                                                                                                                    </span>
                                                                                                                                </label>
                                                                                                                            </div>
                                                                                                                        </span>
                          <!---->
                          <!---->
                        </div>
                      </div>
                      <div class="mm-form-field">
                        <div class="mm-form-field-label">
                          <div class="toggleMore">
                            <a @click="toggleManualConfig">{{ showManualConfig ? '收起手动配置' : '手动配置' }}</a>
                          </div>
                        </div>
                        <div class="mm-form-field-control">
                          <span class="mm-form-field-children"></span>
                          <!---->
                          <!---->
                        </div>
                      </div>
                    </form>
                    <div class="help-wrap">
                      <div class="bind-email-help-wrap">
                        <div class="help-title">帮助文档</div>
                        <ul class="help-list">
                          <li>
                            <a class="mm-typo-link help-link" target="_blank" href="https://www.yuque.com/help.xiaoman/qwwqei/vkr8p7">1、绑定邮箱失败的常见原因及解决方案

                              <!---->
                            </a>
                          </li>
                          <li>
                            <a class="mm-typo-link help-link" target="_blank" href="https://www.yuque.com/help.xiaoman/qwwqei/sl9xuk">2、常见几类邮箱的绑定方法

                              <!---->
                            </a>
                          </li>
                        </ul>
                      </div>
                      <div class="microsoft-login-tip">
                                                                                                                    <span class="okki-icon-wrap" color="#0072C6">​

                                                                                                                        <svg
                                                                                                                          xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 18 18" aria-hidden="true" class="okki-svg-icon" fill="#0072C6">
                                                                                                                            <path d="M10.96 4.464v3.081l1.077.678c.04.009.08.009.119 0l4.633-3.124a.66.66 0 00-.539-.635h-5.29z"></path>
                                                                                                                            <path d="M10.96 8.694l.982.675a.294.294 0 00.306 0c-.169.102 4.54-3.025 4.54-3.025v5.662a.79.79 0 01-.837.875h-4.992V8.694zm-5.088-1.42a.905.905 0 00-.798.471c-.214.38-.316.813-.296 1.248-.022.435.08.866.296 1.244a.9.9 0 001.558.013c.214-.377.314-.806.29-1.238a2.458 2.458 0 00-.281-1.283.864.864 0 00-.769-.455z"></path>
                                                                                                                            <path d="M1.211 2.9v12.052l9.169 1.923V1.125L1.21 2.9zm6.136 8.064a1.817 1.817 0 01-1.519.765 1.793 1.793 0 01-1.485-.741 3.07 3.07 0 01-.57-1.932 3.3 3.3 0 01.582-2.034 1.838 1.838 0 011.543-.778 1.753 1.753 0 011.469.743c.398.573.595 1.262.562 1.96a3.242 3.242 0 01-.582 2.017z"></path>
                                                                                                                        </svg>
                                                                                                                    </span>
                        <span>绑定 Microsoft 国际版邮箱（outlook/hotmail/live）</span>
                      </div>
                      <div class="microsoft-login-tip microsoft-login-bottom-tips">
                                                                                                                    <span class="okki-icon-wrap" color="#0072C6">​

                                                                                                                        <svg
                                                                                                                          xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 18 18" aria-hidden="true" class="okki-svg-icon" fill="#0072C6">
                                                                                                                            <path d="M10.96 4.464v3.081l1.077.678c.04.009.08.009.119 0l4.633-3.124a.66.66 0 00-.539-.635h-5.29z"></path>
                                                                                                                            <path d="M10.96 8.694l.982.675a.294.294 0 00.306 0c-.169.102 4.54-3.025 4.54-3.025v5.662a.79.79 0 01-.837.875h-4.992V8.694zm-5.088-1.42a.905.905 0 00-.798.471c-.214.38-.316.813-.296 1.248-.022.435.08.866.296 1.244a.9.9 0 001.558.013c.214-.377.314-.806.29-1.238a2.458 2.458 0 00-.281-1.283.864.864 0 00-.769-.455z"></path>
                                                                                                                            <path d="M1.211 2.9v12.052l9.169 1.923V1.125L1.21 2.9zm6.136 8.064a1.817 1.817 0 01-1.519.765 1.793 1.793 0 01-1.485-.741 3.07 3.07 0 01-.57-1.932 3.3 3.3 0 01.582-2.034 1.838 1.838 0 011.543-.778 1.753 1.753 0 011.469.743c.398.573.595 1.262.562 1.96a3.242 3.242 0 01-.582 2.017z"></path>
                                                                                                                        </svg>
                                                                                                                    </span>
                        <span>绑定 Microsoft 国内版邮箱（世纪互联）</span>
                      </div>
                    </div>
                  </div>
                  <div class="form-actions-wrapper">
                    <div class="mm-space mm-space__horizontal">
                      <div class="mm-space-item" style="margin-right: 20px;">
                        <!---->
                      </div>
                      <div class="mm-space-item">
                        <button type="button" class="mm-button mm-button__primary ames-btn" @click="confirm">
                          <!---->
                          <!---->
                          <span>确定</span>
                          <!---->
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!---->
          </div>
        </div>
      </div>
    </el-form>
  </el-dialog>
</template>
<style lang="scss">
label {
  font-weight: initial;
}

@import '../../static/scss/email/email_management/9755.4df34767.css';
@import '../../static/scss/email/email_management/MailInformationExtension.504babf1.css';
</style>
<script>
import {addTask} from "@/api/email/task";

export default {
  components: {},
  data() {
    return {
      showManualConfig: false,
      formData: {},
      addEmailPage: false,
      errors: {}
    }
  },
  methods: {
    open() {
      this.addEmailPage = true;
    },

    close() {
      this.addEmailPage = false;
      this.formData = {};
      this.errors = {};
    },

    toggleProxySettings(value) {
      this.formData.customProxyFlag = value;
    },

    toggleManualConfig() {
      this.showManualConfig = !this.showManualConfig;
      if (this.showManualConfig) {
        this.formData.protocolType = 1; // 默认选中 IMAP
        this.formData.customProxyFlag = false; // 自定义代理默认关闭
        this.formData.synchronizeFolderFlag = true; // 同步文件夹默认开启
      } else {
        this.formData.protocolType = undefined;
        this.formData.customProxyFlag = undefined;
        this.formData.synchronizeFolderFlag = undefined;
      }
    },

    validateForm() {
      let isValid = true;

      // 验证账号
      if (!this.formData.account) {
        this.errors.account = "账号不能为空";
        isValid = false;
      } else if (this.formData.account.length < 5) {
        this.errors.account = "账号长度必须至少为5个字符";
        isValid = false;
      } else {
        this.errors.account = null;
      }

      // 验证密码
      if (!this.formData.password) {
        this.errors.password = "密码不能为空";
        isValid = false;
      } else {
        this.errors.password = null;
      }

      return isValid;
    },

    confirm() {
      if (this.validateForm()) {
        addTask(this.formData).then((response) => {
          this.$message.success("新增成功");
          this.addEmailPage = false;
        });
      }

      this.errors = {};
    }
  }
};
</script>
