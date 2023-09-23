<template>
  <div class="pending-custom-item">
    <div>
      <label for="customDate">日期</label>
      <el-date-picker
        v-model="date"
        type="date"
        @input="$forceUpdate()"
        placeholder="选择日期">
      </el-date-picker>
    </div>
    <div>
      <label for="customTime">时间</label>
      <el-time-picker
        v-model="time"
        @input="$forceUpdate()"
        placeholder="任意时间点">
      </el-time-picker>
    </div>
    <div class="submit">
      <button type="button" class="mm-button mm-button__primary sure" @click="handleConfirm" :disabled="!canConfirm">
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
      date: '',
      time: ''
    };
  },
  methods: {
    handleConfirm() {
      if (this.date && this.time) {
        const finalDate = new Date(this.date);  // 从this.date获取年月日

        // 从this.time获取时分秒
        finalDate.setHours(this.time.getHours());
        finalDate.setMinutes(this.time.getMinutes());
        finalDate.setSeconds(this.time.getSeconds());

        // 格式化为所需的格式
        const formattedDate = this.formatDate(finalDate);
        this.$emit('time-selected', formattedDate);
      }
    },
    formatDate(dateTime) {
      const yyyy = dateTime.getFullYear();
      const MM = String(dateTime.getMonth() + 1).padStart(2, '0');  // 因为月份是从0开始的
      const dd = String(dateTime.getDate()).padStart(2, '0');
      const HH = String(dateTime.getHours()).padStart(2, '0');
      const mm = String(dateTime.getMinutes()).padStart(2, '0');

      return `${yyyy}-${MM}-${dd} ${HH}:${mm}:00`;
    }

  },
  computed: {
    canConfirm() {
      return this.date && this.time;
    }
  }

}
</script>
