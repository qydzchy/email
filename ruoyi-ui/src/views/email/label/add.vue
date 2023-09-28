<template>
  <el-dialog :visible="addLabelPage"
             width="0%"
             :append-to-body="true">
    <div class="mm-modal__overflow-auto mm-modal--mask mm-modal mm-dialog create-tag-dialog" id="report-stat-crm-create-tag" v-portal="" v-portal-fixed="" style="z-index: 1000;">
      <div class="mm-modal-mask"></div>
      <div class="mm-modal-wrapper" style="padding-top: 15vh;">
        <div class="mm-modal-content" style="width: 470px; border-color: transparent;">
          <svg class="mm-icon mm-icon-close mm-modal-close" viewBox="0 0 24 24" name="close" :fill="currentColor" style="height: 14px; width: 14px;" @click="close">
            <path d="M14.3 11.7l6-6c.3-.3.3-.7 0-1l-.9-1c-.3-.2-.7-.2-1 0l-6 6.1c-.2.2-.5.2-.7 0l-6-6.1c-.3-.3-.7-.3-1 0l-1 1c-.2.2-.2.7 0 .9l6.1 6.1c.2.2.2.4 0 .6l-6.1 6.1c-.3.3-.3.7 0 1l1 1c.2.2.7.2.9 0l6.1-6.1c.2-.2.4-.2.6 0l6.1 6.1c.2.2.7.2.9 0l1-1c.3-.3.3-.7 0-1l-6-6c-.2-.2-.2-.5 0-.7z"></path>
          </svg>
          <div class="mm-modal-header">
            <div class="mm-modal-title">
              <span>新建标签</span>
            </div>
          </div>
          <div class="mm-modal-body">
            <div class="tag-wrapper">
              <div class="tag-field">
                <label>标签颜色</label>
                <div class="tag-name-wrapper">
                  <div class="mm-dropdown color-picker-dropdown-wrap">
                    <div class="mm-dropdown-trigger">
                      <div class="color-picker-display">
                        <i class="color-block" style="background: rgb(97, 188, 129);"></i>
                        <svg class="mm-icon mm-icon-switch" viewBox="0 0 24 24" name="switch" :fill="currentColor" style="height: 12px; width: 12px;">
                          <path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.3.8-.3 1.1 0l1 1c.2.3.2.7 0 1z"></path>
                        </svg>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="tag-field">
                <label>标签名称</label>
                <div class="tag-name-wrapper">
								<span class="mm-input">
									<!---->
									<span class="mm-input-affix-wrapper">
										<!---->
										<input maxlength="100" type="text" class="mm-input-inner" value="">
                    <!---->
									</span>
                  <!---->
								</span>
                  <!---->
                </div>
              </div>
            </div>
          </div>
          <div class="mm-modal-footer">
            <div>
              <button type="button" class="mm-button" @click="close">
                <!---->
                <!---->
                <span>取消</span>
                <!---->
              </button>
              <button type="button" class="mm-button mm-button__primary">
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
  </el-dialog>
</template>
<script>

export default {
  components: {},
  data() {
    return {
      addLabelPage: false,
    }
  },
  methods: {
    open() {
      this.addLabelPage = true;
    },

    close() {
      this.addLabelPage = false;
      this.formData = {};
    },
  }
};
</script>
