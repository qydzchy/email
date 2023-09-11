<template>
  <div class="mm-outside mail-pending-popover mm-popover-popper" x-placement="top-end">
    <!---->
    <div>
      <!---->
      <div class="mail-pending-handler">
        <div class="title">
          <span>请选择稍后处理时间: </span>
        </div>
        <div class="normal-container">
          <ul class="pending-preset-time">
            <li class="preset-time-item">
              <span class="name bold">今天稍晚</span>
              <span class="format-time">16:00</span>
            </li>
            <li class="preset-time-item">
              <span class="name bold">明天</span>
              <span class="format-time">星期五 08:00</span>
            </li>
            <li class="preset-time-item">
              <span class="name bold">本周稍晚</span>
              <span class="format-time">星期五 08:00</span>
            </li>
            <li class="preset-time-item">
              <span class="name bold">本周末</span>
              <span class="format-time">星期六 08:00</span>
            </li>
            <li class="preset-time-item">
              <span class="name bold">下周</span>
              <span class="format-time">星期一 08:00</span>
            </li>
          </ul>
          <div class="footer bold" @click="$emit('showCustom')">自定义时间</div>
        </div>
      </div>
    </div>
    <!---->
  </div>
</template>
<script>
export default {
  data() {
    return {

    };
  },
}
</script>
