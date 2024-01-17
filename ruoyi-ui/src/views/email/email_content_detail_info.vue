<template>
  <div id="mailDetailScrollContainer" class="scroll-container" style="overflow:hidden" data-v-155e8e5c="">
    <div class="components-container">
      <div class="mail-detail-info-wrapper">

        <div class="state-row" data-v-155e8e5c="">
          <div class="state-row--icon__wrapper" data-v-155e8e5c=""></div>
          <!---->
          <div class="mail-plus-tag-list state-row--tag" data-v-155e8e5c="">
            <span v-for="label in currentEmailDetail.emailLabelList" :key="label.id" class="tag-wrapper"
              :style="{ background: `rgba(${label.color},0.2)`, color: `rgb(${label.color})` }">
              <span class="ai-stamp-container">
                <!---->
                <a class="ellipsis">{{ label.name }}</a>
                <span class="okki-icon-wrap delete-tag-icon">​
                  <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" aria-hidden="true"
                    class="okki-svg-icon" fill="currentColor" @click="deleteEmailLabel(label)">
                    <path fill-rule="evenodd" clip-rule="evenodd"
                      d="M3.626 5.201c-.346-.345-.31-.942.081-1.332.39-.391.987-.427 1.333-.082l6.96 6.96 6.96-6.96c.346-.345.942-.309 1.333.082.39.39.427.987.082 1.332l-6.96 6.96 6.637 6.638c.346.346.31.942-.081 1.333-.391.39-.988.427-1.333.082L12 13.576l-6.638 6.638c-.345.345-.942.309-1.333-.082-.39-.39-.427-.987-.081-1.333l6.638-6.637-6.96-6.96z">
                    </path>
                  </svg>
                </span>
              </span>
            </span>
          </div>
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
                <span style="color: #2a2a2a;">{{ currentEmailDetail.fromName }} </span>&lt;{{ currentEmailDetail.fromer
                }}&gt;
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
                  <span style="color: rgb(42, 42, 42);">{{ currentEmailDetail.receiverName }}</span>
                  <span style="color: rgb(144, 144, 144); margin-right: 8px;">&lt;{{ currentEmailDetail.receiverEmail
                  }}&gt;</span>
                </div>
              </li>
            </ul>
          </div>
          <button type="button"
            class="mm-button mm-button__text mm__theme mm__theme-size__small mail-contact-info--toggler"
            @click="toggleDetailInfo">
            <!---->
            <!---->详细信息 <i class="m-icon icon-packUp mail-contact-info--toggler--icon"
              style="font-size: 12px; color: rgb(0, 100, 255);"></i>
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
                  <span style="color: #2a2a2a;">{{ currentEmailDetail.fromName }}</span>&lt;{{ currentEmailDetail.fromer
                  }}&gt;
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
                      <span style="color: rgb(42, 42, 42);">{{ currentEmailDetail.receiverName }}</span>
                      <span style="color: rgb(144, 144, 144); margin-right: 8px;">&lt;{{ currentEmailDetail.receiverEmail
                      }}&gt;;</span>
                    </div>
                  </li>
                </ul>
              </div>
            </div>
            <div class="mail-info--row"
              v-if="currentEmailDetail.ccName && currentEmailDetail.ccName !== '' && currentEmailDetail.ccEmail && currentEmailDetail.ccEmail !== ''">
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
                      <span style="color: rgb(42, 42, 42);">{{ currentEmailDetail.ccName }}</span>
                      <span style="color: rgb(144, 144, 144); margin-right: 8px;">&lt;{{ currentEmailDetail.ccEmail
                      }}&gt;;</span>
                    </div>
                  </li>
                </ul>
              </div>
            </div>
            <div class="mail-info--row"
              v-if="currentEmailDetail.emailAttachmentList && currentEmailDetail.emailAttachmentList.length > 0">
              <span class="mail-info--label">附件：</span>
              <span class="mail-info--value">{{ currentEmailDetail.emailAttachmentList.length }} 个 <span
                  style="margin-left: 8px; white-space: initial;">(
                  <button v-for="(attachment, index) in currentEmailDetail.emailAttachmentList" :key="index" type="button"
                    class="mm-button mm-button__text mm__theme mm__theme-size__small ellipsis"
                    style="min-width: unset; padding: 0px;">
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
          <button type="button"
            class="mm-button mm-button__text mm__theme mm__theme-size__small mail-contact-info--toggler"
            @click="toggleDetailInfo">
            <!---->
            <!---->详细信息 <i
              class="m-icon icon-packUp mail-contact-info--toggler--icon mail-contact-info--toggler--icon__rotate"
              style="font-size: 12px; color: rgb(0, 100, 255);"></i>
            <!---->
          </button>
        </div>
        <!-- 翻译 -->
        <div class="mail-detail-translate my-6">
          <div class="translate-content">
            <el-select v-model="sourceLanguage" style="width:100px" size="small">
              <el-option v-for="language in languageOption" :key="language.value" :label="language.label"
                :value="language.value"></el-option>
            </el-select>
            <span style="color: rgb(144, 144, 144); margin: 0px 12px;">&gt;</span>
            <el-select v-model="targetLanguage" style="width:100px" size="small">
              <el-option v-for="language in languageOption" :key="language.value" :label="language.label"
                :value="language.value"></el-option>
            </el-select>
          </div>
          <button type="button" class="mm-button mm-button__text mm__theme mm__theme-size__small translate-btn"
            @click="onTranslate" v-loading="translateLoading">{{ !translateContent ? '翻译邮件' : '显示原文' }}</button>
        </div>
      </div>

      <email_content_detail_attachment :emailId="currentEmailDetail.id" :emailTitle="currentEmailDetail.title"
        :emailAttachmentList="this.currentEmailDetail.emailAttachmentList"
        v-if="currentEmailDetail.emailAttachmentList && currentEmailDetail.emailAttachmentList.length > 0">
      </email_content_detail_attachment>

      <!---->
      <div class="mail-content-body mail-detail--content">
        <!---->
        <iframe :srcdoc="translateContent || currentEmailDetail.content" data-savepage-sameorigin=""
          id="mail-content-iframe-1690875340592" style="width: 661px; height: 640px;" data-savepage-key="0-0"></iframe>
        <!---->
      </div>
    </div>
  </div>
</template>
<script>
import { mapState } from 'vuex'
import emailContentDetailAttachmentLayout from './email_content_detail_attachment.vue';
import { deleteEmailLabel } from "@/api/email/email";
export default {
  data() {
    return {
      showDetailInfo: false,
      sourceLanguage: 'zh',
      targetLanguage: 'en',
      languageOption: [
        { label: '英文', value: 'en' },
        { label: '中文', value: 'zh' },
        { label: '俄语', value: 'ru' },
        { label: '西班牙语', value: 'es' },
        { label: '葡萄牙语', value: 'pt' },
        { label: '法语', value: 'fr' },
        { label: '日语', value: 'jp' },
        { label: '土耳其语', value: 'tr' },
        { label: '阿拉伯语', value: 'ar' },
        { label: '泰语', value: 'th' },
        { label: '越南语', value: 'vi' },
        { label: '印尼语', value: 'id' },
      ],
      translateContent: '',
      translateLoading: false
    };
  },

  components: {
    'email_content_detail_attachment': emailContentDetailAttachmentLayout
  },

  props: {
    currentEmailDetail: {
      type: Object,
      default: () => { }
    },
  },
  computed: {
    ...mapState({
      translate: state => state.otherSetting.otherSetting?.emailTranslationFunctionFlag
    })
  },

  methods: {
    toggleDetailInfo() {
      this.showDetailInfo = !this.showDetailInfo;
    },

    deleteEmailLabel(label) {
      const params = {
        emailId: this.currentEmailDetail.id,
        labelId: label.id,
      };
      deleteEmailLabel(params).then(res => {
        if (res.code === 200) {
          // 删除标签后在currentEmailDetail移除该标签
          const index = this.currentEmailDetail.emailLabelList.findIndex(item => item.id === label.id);
          this.currentEmailDetail.emailLabelList.splice(index, 1);
        } else {
          this.$message.error(res.message);
        }
      });
    },
    onTranslate() {
      if (translateContent) {
        this.translateContent = ''
        return
      }
    }
  },
}

</script>
<style lang="scss" scoped>
::v-deep .mail-detail-translate {

  .el-input,
  input {
    background-color: transparent !important;
    border: unset !important;
  }
}
</style>
