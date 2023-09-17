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
      <button type="button" class="mm-button mm-button__primary sure">
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
  }
}
</script>
