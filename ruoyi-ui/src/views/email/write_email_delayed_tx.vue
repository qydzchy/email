<template>
  <div class="mm-modal-content" style="width: 400px; border-color: transparent;">
    <svg class="mm-icon mm-icon-close mm-modal-close" viewBox="0 0 24 24" name="close" fill="currentColor" style="height: 14px; width: 14px;">
      <path d="M14.3 11.7l6-6c.3-.3.3-.7 0-1l-.9-1c-.3-.2-.7-.2-1 0l-6 6.1c-.2.2-.5.2-.7 0l-6-6.1c-.3-.3-.7-.3-1 0l-1 1c-.2.2-.2.7 0 .9l6.1 6.1c.2.2.2.4 0 .6l-6.1 6.1c-.3.3-.3.7 0 1l1 1c.2.2.7.2.9 0l6.1-6.1c.2-.2.4-.2.6 0l6.1 6.1c.2.2.7.2.9 0l1-1c.3-.3.3-.7 0-1l-6-6c-.2-.2-.2-.5 0-.7z"></path>
    </svg>
    <div class="mm-modal-header">
      <div class="mm-modal-title">
        <span>定时发送</span>
      </div>
    </div>
    <div class="mm-modal-body">
      <div class="send-on-time-content">
        <div class="body">
          <!---->
          <div class="mm-row mm-row__align-middle mm-row--flex time-picker">
            <div class="mm-col mm-col-7">收件人当地时间</div>
            <div class="mm-col mm-col-17">
              <el-date-picker
                v-model="recipientLocalTime"
                type="datetime"
                @input="$forceUpdate()"
                placeholder="选择日期时间">
              </el-date-picker>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="mm-modal-footer">
      <button type="button" class="mm-button">
        <!---->
        <!---->取消
        <!---->
      </button>
      <button id="report-stat-send-on-time" type="button" class="mm-button mm-button__primary">
        <!---->
        <!---->确定
        <!---->
      </button>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      recipientLocalTime: null,
    };
  }
}
</script>
