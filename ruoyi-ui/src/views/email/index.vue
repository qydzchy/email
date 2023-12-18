<template>
  <div class="new-layout-right">
    <!---->
    <div class="riven-layout-container" data-v-app="">
      <div class="view-content-wrapper layout-extraSidebar" sidebar-collapse="">
        <div class="view-content-teleport-anchor"></div>
        <div :class="['mm-split', 'mail-layout-split', isLeftPaneVisible ? 'expanding' : 'collapsing']">
          <div class="mm-split-horizontal">
            <div class="mm-split-pane mm-split-pane__left" :style="{right: isLeftPaneVisible ? '87.7165%' : '98.9177%'}">
              <div class="layout-extraSidebar-sidebar">
                <nav v-if="isLeftPaneVisible" class="main-plus-nav">
                  <div class="mail-nav" transition="menu">
                    <div class="mail-nav-switch">
                      <a class="active">
																	<span class="okki-icon-wrap">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																			<path fill-rule="evenodd" clip-rule="evenodd" d="M2.6 5.2A2.995 2.995 0 015 4h14c.981 0 1.852.47 2.4 1.199L12 11.591 2.6 5.199zM2 7.21V17a3 3 0 003 3h14a3 3 0 003-3V7.21l-9.438 6.417a1 1 0 01-1.124 0L2 7.209z"></path>
																		</svg>
																	</span>
                      </a>
                      <a class="active">
                        <span class="okki-icon-wrap">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
                            <path fill-rule="evenodd" clip-rule="evenodd" d="M12 2a5 5 0 100 10 5 5 0 000-10zM8.296 13a6 6 0 00-6 6v2a1 1 0 001 1h17.408a1 1 0 001-1v-2a6 6 0 00-6-6H8.296z"></path>
                          </svg>
                        </span>
                      </a>
                    </div>
                    <div class="main-nav-head-pro">
                      <div class="mm-dropdown">
                        <div>
                          <div class="mm-button-group mm-dropdown-button-group">
                            <button type="button" class="mm-button mm-button__primary" @click="switchWriteEmailPage">
                              <!---->
                              <!---->写信
                              <!---->
                            </button>
                            <button type="button" class="mm-button mm-button__primary mm-button--icon">
                              <svg class="mm-icon mm-icon-switch" viewBox="0 0 24 24" name="switch" fill="currentColor" style="height: 12px; width: 12px;">
                                <path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.3.8-.3 1.1 0l1 1c.2.3.2.7 0 1z"></path>
                              </svg>
                              <!---->
                              <!---->
                            </button>
                          </div>
                        </div>
                        <!---->
                      </div>
                    </div>
                    <component :is="emailIndexLayout"></component>
              </div>
            </nav>
          <span :class="['position-toogle', isLeftPaneVisible ? 'expanding' : 'collapsing']" @click="toggleLeftPane">
						 <i class="m-icon icon-left-small"></i>
					</span>
        </div>
</div>
<div class="mm-split-trigger-wrap" :style="{left: isLeftPaneVisible ? '12.2835%' : '1.08225%'}">
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

<!-- 使用动态组件切换BC布局 -->
            <component
              :is="currentLayout"
              :key="currentLayout"
              :selectedEmail="selectedEmail"
              :emailData="emailData"
              :emailTotal="emailTotal"
              :selectedTaskId="selectedTaskId"
              :emailType="currentEmailType"
              :writeEmailType="writeEmailType"
              @switch="switchLayout"
            ></component>

</div>
</div>
</div>
</div>
<!---->
<div class="side-chat-button-wrap" style="display: none;">
<div class="side-chat-button">
									<span class="close-btn">
										<svg class="svg-icon" aria-hidden="true" color="rgba(0,0,0,0.5)" style="width: 16px; height: 16px;">
											<use xlink:href="#common-iconfont-close-btn"></use>
										</svg>
									</span>
  <i class="common-iconfont common-iconfont-chat1" style="font-size: 18px; color: rgb(255, 255, 255);"></i>
  <div class="chat-text">客服</div>
</div>
</div>
<!---->
<div class="novice-wrapper">
<!---->
<!--portal-->
</div>
<div style="position: fixed; top: 0px; left: 0px; width: 100%; height: 100%; z-index: 999999; pointer-events: none;"></div>
</div>
</template>

<style lang="scss">
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
import emailHeaderLayout from './email_header.vue';
import emailContentLayout from './email_content.vue';
import writeEmailLayout from './write_email.vue';
import emailIndexLayout from './email_index.vue';
import sendSuccessLayout from './send_success.vue';
import setup from './setup.vue';
import FolderTree from './folder_list_item.vue'
import {listTaskPull, listTaskSend} from "@/api/email/task";
import { listFolder } from "@/api/email/folder";
import { listLabel } from "@/api/email/label";
import { countMenu } from "@/api/email/email";
import { EventBus } from "@/api/email/event-bus";

export default {
  data() {
    return {
      pullTaskList: [],
      sendTaskList: [],
      emailList: [],
      folders: [],
      labels: [],
      activeMenuItem: null,
      currentLayout: 'email_header',
      isLeftPaneVisible: true,
      selectedTaskId: null,
      currentEmailType: '',
      emailTotal: 0,
      emailData: [],
      selectedEmail: {},
      writeEmailType: '',
      isInboxOpen: true,
      isOutboxOpen: true,
      isShowMoreOpen: true,
      isFolderOpen: true,
      isLabelOpen: true,
      isLabelSystemOpen: true,
      isLabelCustomOpen: true,
      isDropdownShown: false,
      menuCount: {},
    };
  },
  components: {
    'email_header': emailHeaderLayout,
    'email_content': emailContentLayout,
    'email_index': emailIndexLayout,
    'write_email': writeEmailLayout,
    'send_success': sendSuccessLayout,
    'setup': setup,
    'FolderTree': FolderTree
  },

  methods: {
    switchLayout(layoutName, email, emailData, emailTotal, currentEmailType) {
      this.currentLayout = layoutName;
      this.selectedEmail = email;
      this.emailData = emailData;
      this.emailTotal = emailTotal;
      this.selectedTaskId = this.selectedTaskId;
      this.currentEmailType = currentEmailType;
    },

    toggleLeftPane() {
      this.isLeftPaneVisible = !this.isLeftPaneVisible;
    }
  }
};
</script>
