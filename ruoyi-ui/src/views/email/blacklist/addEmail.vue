<template>
  <div v-if="addEmailPage">
    <div class="mm-modal__overflow-auto mm-modal--mask mm-modal mm-dialog mail-black-dialog" v-portal="" v-portal-fixed="" style="z-index: 1003;">
      <div class="mm-modal-mask"></div>
      <div class="mm-modal-wrapper" style="padding-top: 15vh;">
        <div class="mm-modal-content" style="border-color: transparent;">
          <svg class="mm-icon mm-icon-close mm-modal-close" viewBox="0 0 24 24" name="close" fill="currentColor" style="height: 14px; width: 14px;" @click="close">
            <path d="M14.3 11.7l6-6c.3-.3.3-.7 0-1l-.9-1c-.3-.2-.7-.2-1 0l-6 6.1c-.2.2-.5.2-.7 0l-6-6.1c-.3-.3-.7-.3-1 0l-1 1c-.2.2-.2.7 0 .9l6.1 6.1c.2.2.2.4 0 .6l-6.1 6.1c-.3.3-.3.7 0 1l1 1c.2.2.7.2.9 0l6.1-6.1c.2-.2.4-.2.6 0l6.1 6.1c.2.2.7.2.9 0l1-1c.3-.3.3-.7 0-1l-6-6c-.2-.2-.2-.5 0-.7z"></path>
          </svg>
          <div class="mm-modal-header">
            <div class="mm-modal-title">
              <span>添加邮箱地址黑名单</span>
            </div>
          </div>
          <div class="mm-modal-body">
            <form class="mm-form-horizontal mm-form">
              <div class="mm-form-field--required mm-form-field">
                <div class="mm-form-field-label" style="width: 80px;">
                  <span>邮箱地址:</span>
                </div>
                <div class="mm-form-field-control" style="margin-left: 80px;">
                <span class="mm-form-field-children">
                  <span class="mm-input" style="width: 400px;">
                    <!---->
                    <span class="mm-input-affix-wrapper">
                      <!---->
                      <input type="text" class="mm-input-inner">
                      <!---->
                    </span>
                    <!---->
                  </span>
                </span>
                  <!---->
                  <!---->
                </div>
              </div>
            </form>
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
  </div>
</template>
<script>
import {EventBus} from "@/api/email/event-bus";

export default {
  data() {
    return {
      addEmailPage: false,
      name: ''
    }
  },

  methods: {
    open() {
      this.addEmailPage = true;
    },

    close() {
      this.addEmailPage = false;
      this.name = '';
    },

    // 保存标签
    async confirm() {
      if (!this.selectedColor) {
        this.$message.error("标签颜色不能为空");
        return;
      }

      if (!this.name) {
        this.$message.error("标签名称不能为空");
        return;
      }

      const data = {
        "color": this.selectedColor,
        "name": this.name
      };

      try {
        const response = await addLabel(data);
        if (response.code === 200) {
          this.$message.success("新增成功");
          this.close();
          // 刷新列表
          EventBus.$emit('refresh-label-list');
          EventBus.$emit('refresh-index-label-list');
        } else {
          this.$message.error("新增失败");
        }
      } catch (error) {
        console.error('Error saving the label:', error);
      }
    }
  }
};
</script>
