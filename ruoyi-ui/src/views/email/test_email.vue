<template>
  <el-dialog :visible="testEmailPage"
              width="0%"
              :append-to-body="true"
              @close="close">
    <el-form ref="elForm" :model="formData" size="medium" label-width="100px">
      <div class="mm-modal--mask mm-modal" v-portal="" v-portal-fixed="" style="z-index: 99;">
        <div class="mm-modal-mask"></div>
        <div class="mm-modal-wrapper" style="padding-top: 15vh;">
          <div class="mm-modal-content" style="width: 560px; border-color: transparent;">
            <svg class="mm-icon mm-icon-close mm-modal-close" viewBox="0 0 24 24" name="close" fill="currentColor" style="height: 14px; width: 14px;" @click="close">
              <path d="M14.3 11.7l6-6c.3-.3.3-.7 0-1l-.9-1c-.3-.2-.7-.2-1 0l-6 6.1c-.2.2-.5.2-.7 0l-6-6.1c-.3-.3-.7-.3-1 0l-1 1c-.2.2-.2.7 0 .9l6.1 6.1c.2.2.2.4 0 .6l-6.1 6.1c-.3.3-.3.7 0 1l1 1c.2.2.7.2.9 0l6.1-6.1c.2-.2.4-.2.6 0l6.1 6.1c.2.2.7.2.9 0l1-1c.3-.3.3-.7 0-1l-6-6c-.2-.2-.2-.5 0-.7z"></path>
            </svg>
            <div class="mm-modal-header">
              <div class="mm-modal-title">
                <span>邮箱检测</span>
              </div>
            </div>
            <div class="mm-modal-body">
						<span>
							<div class="checkEmailSettingWrapper">
								<div class="check-mail-content">
									<form class="mm-form-horizontal mm-form mailDetailList">
										<div class="mm-form-field">
											<div class="mm-form-field-label" style="width: 120px;">
												<span>邮箱</span>
											</div>
											<div class="mm-form-field-control" style="margin-left: 120px;">
												<span class="mm-form-field-children">
													<div class="itemValue">
														<span>{{formData.account}}}
                              <!---->
														</span>
                            <!---->
													</div>
												</span>
                        <!---->
                        <!---->
											</div>
										</div>
										<div class="mm-form-field">
											<div class="mm-form-field-label" style="width: 120px;">
												<span>邮箱密码</span>
											</div>
											<div class="mm-form-field-control" style="margin-left: 120px;">
												<span class="mm-form-field-children">
													<div class="itemValue" style="color: rgb(0, 156, 81);">
														<span>********
                              <!---->
														</span>
														<span class="result">
															<span class="okki-icon-wrap" color="#009C51">​<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="#009C51">
																	<path d="M12 20a8 8 0 100-16 8 8 0 000 16zm10-8c0 5.523-4.477 10-10 10S2 17.523 2 12 6.477 2 12 2s10 4.477 10 10z"></path>
																	<path fill-rule="evenodd" clip-rule="evenodd" d="M17.194 8.28a1 1 0 01.026 1.414l-5.786 6a1 1 0 01-1.44 0L6.78 12.361a1 1 0 011.44-1.389l2.494 2.587 5.066-5.253a1 1 0 011.414-.026z"></path>
																</svg>
															</span>
														</span>
													</div>
												</span>
                        <!---->
                        <!---->
											</div>
										</div>
										<div class="mm-form-field">
											<div class="mm-form-field-label" style="width: 120px;">
												<span>协议类型</span>
											</div>
											<div class="mm-form-field-control" style="margin-left: 120px;">
												<span class="mm-form-field-children">
													<div class="itemValue">
														<span>IMAP
                              <!---->
														</span>
                            <!---->
													</div>
												</span>
                        <!---->
                        <!---->
											</div>
										</div>
										<div class="mm-form-field">
											<div class="mm-form-field-label" style="width: 120px;">
												<span>收邮件服务器</span>
											</div>
											<div class="mm-form-field-control" style="margin-left: 120px;">
												<span class="mm-form-field-children">
													<div class="itemValue" style="color: rgb(0, 156, 81);">
														<span>{{formData.receivingServer}}
                              <!---->
														</span>
														<span class="result">
															<span class="okki-icon-wrap" color="#009C51">​<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="#009C51">
																	<path d="M12 20a8 8 0 100-16 8 8 0 000 16zm10-8c0 5.523-4.477 10-10 10S2 17.523 2 12 6.477 2 12 2s10 4.477 10 10z"></path>
																	<path fill-rule="evenodd" clip-rule="evenodd" d="M17.194 8.28a1 1 0 01.026 1.414l-5.786 6a1 1 0 01-1.44 0L6.78 12.361a1 1 0 011.44-1.389l2.494 2.587 5.066-5.253a1 1 0 011.414-.026z"></path>
																</svg>
															</span>
														</span>
													</div>
												</span>
                        <!---->
                        <!---->
											</div>
										</div>
										<div class="mm-form-field">
											<div class="mm-form-field-label" style="width: 120px;">
												<span>发邮件服务器</span>
											</div>
											<div class="mm-form-field-control" style="margin-left: 120px;">
												<span class="mm-form-field-children">
													<div class="itemValue" style="color: rgb(0, 156, 81);">
														<span>{{formData.outgoingServer}}
                              <!---->
														</span>
														<span class="result">
															<span class="okki-icon-wrap" color="#009C51">​<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="#009C51">
																	<path d="M12 20a8 8 0 100-16 8 8 0 000 16zm10-8c0 5.523-4.477 10-10 10S2 17.523 2 12 6.477 2 12 2s10 4.477 10 10z"></path>
																	<path fill-rule="evenodd" clip-rule="evenodd" d="M17.194 8.28a1 1 0 01.026 1.414l-5.786 6a1 1 0 01-1.44 0L6.78 12.361a1 1 0 011.44-1.389l2.494 2.587 5.066-5.253a1 1 0 011.414-.026z"></path>
																</svg>
															</span>
														</span>
													</div>
												</span>
                        <!---->
                        <!---->
											</div>
										</div>
										<div class="mm-form-field">
											<div class="mm-form-field-label" style="width: 120px;">
												<span>自定义代理</span>
											</div>
											<div class="mm-form-field-control" style="margin-left: 120px;">
												<span class="mm-form-field-children">
													<div class="itemValue">
														<span>{{ formData.customProxyFlag === 1 ? '开启' : '关闭' }}<span class="mm-tooltip tip-icon">
																<span class="mm-tooltip-trigger">
																	<span class="okki-icon-wrap" color="#aeaeae">​<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="#aeaeae">
																			<path fill-rule="evenodd" clip-rule="evenodd" d="M6.444 3.685a10 10 0 1111.112 16.63A10 10 0 016.444 3.685zm1.111 14.967a8 8 0 108.89-13.305 8 8 0 00-8.89 13.305zM12.072 6.93c-.43 0-.827.046-1.192.142a3.829 3.829 0 00-1.078.479c-.26.157-.442.394-.442.711 0 .213.083.4.232.548a.76.76 0 00.548.232.831.831 0 00.461-.143 2.14 2.14 0 01.618-.25l.006-.001c.247-.065.497-.098.751-.098.542 0 .894.105 1.105.266.196.149.311.377.311.742a.622.622 0 01-.148.41 4.175 4.175 0 01-.626.579l-.002.001a7.704 7.704 0 00-.725.65 2.81 2.81 0 00-.498.752c-.15.32-.221.68-.221 1.076 0 .213.083.4.232.548l.009.009.01.008c.164.14.363.203.577.203.21 0 .405-.066.566-.203l.01-.009a.743.743 0 00.252-.556c0-.265.068-.465.188-.62.156-.197.396-.429.73-.692a7.65 7.65 0 00.662-.576l.005-.005c.184-.193.337-.42.461-.677l.002-.003c.133-.286.196-.607.196-.955 0-.777-.281-1.417-.852-1.887-.556-.464-1.282-.681-2.148-.681zm-.096 8.116a.99.99 0 00-.716.304.97.97 0 00-.292.704c0 .276.097.522.286.722l.006.006.006.006c.198.187.439.286.71.286a.984.984 0 00.716-.292c.2-.199.304-.447.304-.728a.95.95 0 00-.304-.704.965.965 0 00-.716-.304z"></path>
																		</svg>
																	</span>
																</span>
                              <!---->
															</span>
														</span>
                            <!---->
													</div>
												</span>
                        <!---->
                        <!---->
											</div>
										</div>
										<div class="mm-form-field">
											<div class="mm-form-field-label" style="width: 120px;">
												<span>同步文件夹</span>
											</div>
											<div class="mm-form-field-control" style="margin-left: 120px;">
												<span class="mm-form-field-children">
													<div class="itemValue">
														<span>{{ formData.synchronizeFolderFlag === 1 ? '开启' : '关闭' }}<span class="mm-tooltip tip-icon">
																<span class="mm-tooltip-trigger">
																	<span class="okki-icon-wrap" color="#aeaeae">​<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="#aeaeae">
																			<path fill-rule="evenodd" clip-rule="evenodd" d="M6.444 3.685a10 10 0 1111.112 16.63A10 10 0 016.444 3.685zm1.111 14.967a8 8 0 108.89-13.305 8 8 0 00-8.89 13.305zM12.072 6.93c-.43 0-.827.046-1.192.142a3.829 3.829 0 00-1.078.479c-.26.157-.442.394-.442.711 0 .213.083.4.232.548a.76.76 0 00.548.232.831.831 0 00.461-.143 2.14 2.14 0 01.618-.25l.006-.001c.247-.065.497-.098.751-.098.542 0 .894.105 1.105.266.196.149.311.377.311.742a.622.622 0 01-.148.41 4.175 4.175 0 01-.626.579l-.002.001a7.704 7.704 0 00-.725.65 2.81 2.81 0 00-.498.752c-.15.32-.221.68-.221 1.076 0 .213.083.4.232.548l.009.009.01.008c.164.14.363.203.577.203.21 0 .405-.066.566-.203l.01-.009a.743.743 0 00.252-.556c0-.265.068-.465.188-.62.156-.197.396-.429.73-.692a7.65 7.65 0 00.662-.576l.005-.005c.184-.193.337-.42.461-.677l.002-.003c.133-.286.196-.607.196-.955 0-.777-.281-1.417-.852-1.887-.556-.464-1.282-.681-2.148-.681zm-.096 8.116a.99.99 0 00-.716.304.97.97 0 00-.292.704c0 .276.097.522.286.722l.006.006.006.006c.198.187.439.286.71.286a.984.984 0 00.716-.292c.2-.199.304-.447.304-.728a.95.95 0 00-.304-.704.965.965 0 00-.716-.304z"></path>
																		</svg>
																	</span>
																</span>
                              <!---->
															</span>
														</span>
                            <!---->
													</div>
												</span>
                        <!---->
                        <!---->
											</div>
										</div>
									</form>
                  <!---->
								</div>
								<div class="mailFooterAction">
									<div>
										<div class="mm-space mm-space__horizontal">
											<div class="mm-space-item">
												<button type="button" class="mm-button mm-button__primary ames-btn">
													<!---->
                          <!---->重新检测
                          <!---->
												</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</span>
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

@import '../../static/scss/email/email_management/1972.59786a6e.css';
@import '../../static/scss/email/email_management/8372.43beae95.css';
</style>
<script>
import {testTask} from "@/api/email/task";

export default {
  components: {},
  data() {
    return {
      formData: {},
      testEmailPage: false,
    }
  },
  watch: {
    emailData: {
      immediate: true,
      handler(newValue) {
        this.formData = { ...newValue };
      }
    }
  },
  methods: {
    open(email) {
      this.formData = email;
      this.testEmailPage = true;

      const data = {
        id: this.formData.id
      }
      testTask(data).then((response) => {
        this.addEmailPage = false;
      });
    },

    close() {
      this.testEmailPage = false;
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
