<template>
  <div>
  <div class="mm-outside mail-pending-popover mm-popover-popper" x-placement="top-end" style="position: absolute; top: 188px; left: 906px; will-change: top, left; transform-origin: 100% bottom;">
    <!---->
    <div>
      <!---->
      <div class="mail-pending-handler">
        <div class="title">
				<span class="bold back-block">
					<i class="m-icon icon-left-thin"></i> 自定义时间
				</span>
        </div>
        <div class="pending-custom-item">
          <div>
            <label for="customDate">日期</label>
<!--            <el-date-picker
              @change="handleDateChange"
              v-model="date"
              type="date"
              placeholder="选择日期">
            </el-date-picker>-->
          </div>
          <div>
            <label for="customTime">时间</label>
            <el-time-picker
              @change="handleTimeChange"
              v-model="time"
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
      </div>
    </div>
    <!---->
  </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      date: '2016-10-10',
      time: '18:40:00'
    };
  },
  methods: {
    handleDateChange(newValue) {
      console.log('Selected date:', newValue);
    },
    handleTimeChange(newValue) {
      console.log('Selected time:', newValue);
    }
  }
}
