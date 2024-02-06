<template>
  <div class="mm-split-pane mm-split-pane__right" style="left: 12.2835%;">
    <div class="page-mail-success layout-extraSidebar-content" mail-type="" folder-id="">
      <div class="text-with-suggest">
        <div class="mail-success-text">
          <i class="m-icon icon-send-on-time"></i>
          <section>
            <h1 class="title">邮件定时发送设置成功！</h1>
            <p class="mail-saved-tip">
              <span>邮件已暂时为您保存至</span>
              <!---->
              <!---->
              <!---->
              <a data-savepage-href="/pro/mail/draft" href="https://crm.xiaoman.cn/pro/mail/draft" class="mail-success-link"> 「草稿箱」 </a>
              <!---->
              <span>并在指定时间为您发出。</span>
              <!---->
              <!---->
            </p>
            <!---->
            <div class="mm-space mm-space__horizontal mail-actions">
              <div class="mm-space-item" style="margin-right: 12px;">
                <button type="button" class="mm-button mm-button__primary mm__theme mm__theme-size__small" @click="back">
                  <!---->
                  <!---->返回列表
                  <!---->
                </button>
              </div>
              <div class="mm-space-item" style="margin-right: 12px;">
                <a target="_blank" class="mm-button mm__theme mm__theme-size__small" @click="viewThisEmail">
                  <!---->
                  <!---->查看此邮件
                  <!---->
                </a>
              </div>
              <div class="mm-space-item" style="margin-right: 12px;">
                <button type="button" class="mm-button mm__theme mm__theme-size__small" @click="writeEmail">
                  <!---->
                  <!---->再写一封
                  <!---->
                </button>
              </div>
              <div class="mm-space-item">
                <button type="button" class="mm-button mm__theme mm__theme-size__small">
                  <!---->
                  <!---->新建日程
                  <!---->
                </button>
              </div>
            </div>
          </section>
        </div>
<!--        <div class="mail-profile-suggest">
          <p class="suggest-archive">你还可以将以下收件人建档：</p>
          <ul>
            <li class="suggest-item">
              <span title="w0r1d_space@sohu.com" class="email ellipsis">w0r1d_space@sohu.com</span>
              <a>添加客户</a>
              <a>添加到已有客户</a>
              <a>添加到通讯录</a>
            </li>
          </ul>
          &lt;!&ndash;&ndash;&gt;
        </div>-->
      </div>
      <!---->
      <!---->
      <!---->
    </div>
  </div>
</template>
<style lang="scss">
.dropdown-email-list {
  z-index: 1000;
  position: absolute;
}

@import '../../static/scss/email/index/430.489bfeef.css';
@import '../../static/scss/email/index/25242.59786a6e.css';
@import '../../static/scss/email/index/57314.b06a5e97.css';
</style>
<script>
import { targetBlank } from '@/utils/tools'
import { EventBus } from "@/api/email/event-bus";
export default {
  data() {
    return {

    }
  },

  props: {
    selectedEmail: {
      type: Object,
      default: {}
    },
  },

  methods: {
    // 返回列表
    back() {
      targetBlank(`/email/index?type=email_header`);
    },

    // 查看此邮件
    viewThisEmail() {
      targetBlank(`/email/index?type=write_email&emailId=` + this.selectedEmail.id);
    },

    // 再写一封
    writeEmail() {
      targetBlank(`/email/index?type=write_email`);
    },
  },
}
</script>
