<template>
  <div id="mailDetailScrollContainer" class="scroll-container" data-v-155e8e5c="">
    <div class="components-container">
      <div class="mail-detail-info-wrapper">
        <div class="state-row">
          <div class="state-row--icon__wrapper"></div>
          <!---->
          <div class="mail-plus-tag-list state-row--tag" style="display: none;"></div>
        </div>
        <div class="mail-detail--info" v-if="!showDetailInfo">
          <div class="mail-info--sender">
																										<span class="mm-tooltip all-type-avatar-wrapper client-stranger small">
                                                      <span class="mm-tooltip-trigger">
                                                        <i class="m-icon avatar-icon icon-new-customer-avatar"></i>
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
																													<span class="mm-tooltip all-type-avatar-wrapper client-stranger small">
                                                            <span class="mm-tooltip-trigger">
                                                              <i class="m-icon avatar-icon icon-new-customer-avatar"></i>
                                                            </span>
                                                                                                              <!---->
                                                          </span>
                  <span style="color: rgb(42, 42, 42);">{{currentEmailDetail.receiverName}}</span>
                  <span style="color: rgb(144, 144, 144); margin-right: 8px;">&lt;{{currentEmailDetail.receiverEmail}}&gt;</span>
                </div>
              </li>
            </ul>
          </div>
          <button type="button" class="mm-button mm-button__text mm__theme mm__theme-size__small mail-contact-info--toggler" @click="toggleDetailInfo">
            <!---->
            <!---->详细信息 <i class="m-icon icon-packUp mail-contact-info--toggler--icon" style="font-size: 12px; color: rgb(0, 100, 255);"></i>
            <!---->
          </button>
        </div>

        <div class="mail-detail--info mail-detail--info__detail" data-v-155e8e5c="" v-if="showDetailInfo">
          <div style="min-width: 0px;">
            <div class="mail-info--row" style="overflow: hidden;">
              <span class="mail-info--label">发件人：</span>
              <span class="mail-info--value">
				<span class="mm-tooltip all-type-avatar-wrapper client-stranger small">
					<span class="mm-tooltip-trigger">
						<i class="m-icon avatar-icon icon-new-customer-avatar"></i>
					</span>
          <!---->
				</span>
				<span>
					<span style="color: #2a2a2a;">{{currentEmailDetail.fromName}}</span>&lt;{{currentEmailDetail.fromer}}&gt;
				</span>
			</span>
            </div>
            <div class="mail-info--row">
              <span class="mail-info--label">收件人：</span>
              <div style="width: auto; flex: 1 1 0%; margin-right: 20px;">
                <ul style="display: inline-flex; flex-wrap: wrap; overflow: hidden; max-width: 100%;">
                  <li>
                    <div>
							<span class="mm-tooltip all-type-avatar-wrapper client-stranger small">
                <span class="mm-tooltip-trigger">
                  <i class="m-icon avatar-icon icon-new-customer-avatar"></i>
                </span>
                      <!---->
              </span>
                      <span style="color: rgb(42, 42, 42);">{{currentEmailDetail.receiverName}}</span>
                      <span style="color: rgb(144, 144, 144); margin-right: 8px;">&lt;{{currentEmailDetail.receiverEmail}}&gt;;</span>
                    </div>
                  </li>
                </ul>
              </div>
            </div>
            <div class="mail-info--row" v-if="currentEmailDetail.ccName && currentEmailDetail.ccName !== '' && currentEmailDetail.ccEmail && currentEmailDetail.ccEmail !== ''">
              <span class="mail-info--label">抄送人：</span>
              <div style="width: auto; flex: 1 1 0%; margin-right: 20px;">
                <ul style="display: inline-flex; flex-wrap: wrap; overflow: hidden; max-width: 100%;">
                  <li>
                    <div>
							<span class="mm-tooltip all-type-avatar-wrapper client-stranger small">
                <span class="mm-tooltip-trigger">
                  <i class="m-icon avatar-icon icon-new-customer-avatar"></i>
                </span>
                      <!---->
              </span>
                      <span style="color: rgb(42, 42, 42);">{{currentEmailDetail.ccName}}</span>
                      <span style="color: rgb(144, 144, 144); margin-right: 8px;">&lt;{{currentEmailDetail.ccEmail}}&gt;;</span>
                    </div>
                  </li>
                </ul>
              </div>
            </div>
            <div class="mail-info--row" v-if="currentEmailDetail.emailAttachmentList && currentEmailDetail.emailAttachmentList.length > 0">
              <span class="mail-info--label">附件：</span>
              <span class="mail-info--value">{{ currentEmailDetail.emailAttachmentList.length }} 个 <span style="margin-left: 8px; white-space: initial;">(
                <button v-for="(attachment, index) in currentEmailDetail.emailAttachmentList" :key="index" type="button" class="mm-button mm-button__text mm__theme mm__theme-size__small ellipsis" style="min-width: unset; padding: 0px;">
                  {{ attachment.name }}
                         <!-- 如果不是最后一个附件，则添加逗号和空格 -->
                  <span v-if="index < currentEmailDetail.emailAttachmentList.length - 1">,&nbsp;&nbsp;</span>
                </button>
                )
				</span>
			</span>
            </div>
<!--            <div class="mail-info&#45;&#45;row">
              <span class="mail-info&#45;&#45;label">大小：</span>
              <span class="mail-info&#45;&#45;value">2.82 KB</span>
            </div>-->
          </div>
          <button type="button" class="mm-button mm-button__text mm__theme mm__theme-size__small mail-contact-info--toggler" @click="toggleDetailInfo">
            <!---->
            <!---->详细信息 <i class="m-icon icon-packUp mail-contact-info--toggler--icon mail-contact-info--toggler--icon__rotate" style="font-size: 12px; color: rgb(0, 100, 255);"></i>
            <!---->
          </button>
        </div>
      </div>
      <!---->
      <div class="mail-content-body mail-detail--content">
        <!---->
        <iframe :srcdoc="currentEmailDetail.content" data-savepage-sameorigin="" id="mail-content-iframe-1690875340592" style="width: 661px; height: 640px;" data-savepage-key="0-0"></iframe>
        <!---->
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      showDetailInfo: false,
    };
  },

  props: ['currentEmailDetail'],

  methods: {
    toggleDetailInfo() {
      this.showDetailInfo = !this.showDetailInfo;
    },
  }
}

</script>
