<template>
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
    <div class="footer bold" @click="showCustomTime">自定义时间</div>
  </div>
</template>
<script>
export default {
  data() {
    return {

    };
  },
  methods: {
    showCustomTime() {
      this.$emit('show-custom-time');
    }
  }
}
</script>
