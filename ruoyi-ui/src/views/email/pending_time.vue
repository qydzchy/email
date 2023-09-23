<template>
  <div class="normal-container">
    <ul class="pending-preset-time">
      <li v-for="preset in presetTimes"
          :key="preset.label"
          :class="{'preset-time-item': true, 'disabled': isTimePast(preset)}"
          @click="emitSelectedTime(preset)">
        <span class="name bold">{{ preset.label }}</span>
        <span class="format-time">{{ preset.date | dayOfWeek }} {{ preset.time }}</span>
      </li>
    </ul>

    <div class="footer bold" @click="showCustomTime">自定义时间</div>
  </div>
</template>
<script>
import { EventBus } from "@/api/email/event-bus";
export default {
  data() {
    return {};
  },
  computed: {
    presetTimes() {
      const today = new Date();
      const tomorrow = new Date(today);
      tomorrow.setDate(today.getDate() + 1);
      const weekEnd = new Date(today);
      weekEnd.setDate(today.getDate() + (6 - today.getDay()));
      const nextWeek = new Date(today);
      nextWeek.setDate(today.getDate() + 7);

      return [
        { label: '今天稍晚', date: today, time: '16:00' },
        { label: '明天', date: tomorrow, time: '08:00' },
        { label: '本周稍晚', date: new Date(today.getFullYear(), today.getMonth(), today.getDate() + (5 - today.getDay())), time: '08:00' },
        { label: '本周末', date: weekEnd, time: '08:00' },
        { label: '下周', date: nextWeek, time: '08:00' },
      ];
    }
  },
  methods: {
    showCustomTime() {
      this.$emit('show-custom-time');
    },
    isTimePast(preset) {
      const now = new Date();

      const presetDateTime = new Date(preset.date);
      const [hours, minutes] = preset.time.split(':');
      presetDateTime.setHours(hours);
      presetDateTime.setMinutes(minutes);

      return presetDateTime < now;
    },
    formatDate(d) {
      const yyyy = d.getFullYear();
      const MM = String(d.getMonth() + 1).padStart(2, '0');  // 因为月份是从0开始的
      const dd = String(d.getDate()).padStart(2, '0');
      const HH = String(d.getHours()).padStart(2, '0');
      const mm = String(d.getMinutes()).padStart(2, '0');

      return `${yyyy}-${MM}-${dd} ${HH}:${mm}:00`;
    },

    emitSelectedTime(preset) {
      if (!this.isTimePast(preset)) {
        const selectedDate = new Date(preset.date);
        const [hours, minutes] = preset.time.split(':');
        selectedDate.setHours(hours);
        selectedDate.setMinutes(minutes);

        const formattedDate = this.formatDate(selectedDate);
        // 触发自定义事件并传递选择的日期和时间
        this.$emit('time-selected', formattedDate);
      }
    },
},
  filters: {
    dayOfWeek(date) {
      const days = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
      return days[date.getDay()];
    }
  }

}
</script>

