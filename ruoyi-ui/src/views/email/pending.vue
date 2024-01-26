<template>
  <div>
    <!---->
    <div class="mail-pending-handler">
      <div class="title" v-if="showPendingTime">
        <span>请选择稍后处理时间: </span>
      </div>
      <div class="title" v-if="showCustomTime">
                                                                <span class="bold back-block">
                                                                  <i class="m-icon icon-left-thin" @click="handlePendingTime"></i> 自定义时间
                                                                </span>
      </div>
      <PendingTimePopover v-if="showPendingTime" @show-custom-time="handleCustomTime" @time-selected="handleSelectedTime"></PendingTimePopover>
      <CustomTimePopover v-if="showCustomTime" @time-selected="handleSelectedTime"></CustomTimePopover>
    </div>
  </div>
</template>
<script>
import {EventBus} from "@/api/email/event-bus";
import PendingTimePopover from "@/views/email/pending_time.vue";
import CustomTimePopover from "@/views/email/custom_time.vue";
import {
  pendingEmail,
} from "@/api/email/email";
import emailHeaderLabelLayout from "@/views/email/email_content_label.vue";
import PopoverSelectFolder from "@/views/email/customer_email/PopoverSelectFolder.vue";
export default {
  data() {
    return {
      emailIds: [],
      showPendingTime: false,
      showCustomTime: false,
    }
  },
  components: {
    CustomTimePopover,
    PendingTimePopover
  },
  methods: {
    handlePendingTime() {
      this.showPendingTime = false
      this.showCustomTime = true
    },
    handleCustomTime() {
      this.showPendingTime = true
      this.showCustomTime = false
    },
    handleSelectedTime(time) {
      this.pendingEmail(this.emailIds, true, time);
      this.$emit('time-selected', time)
    },

    // 标记待处理
    async pendingEmail(emailIds, pendingFlag, pendingTime) {
      const data = {
        "ids": emailIds,
        "pendingFlag": pendingFlag,
        "pendingTime": pendingTime
      };
      try {
        const response = await pendingEmail(data);
        if (response.code === 200) {
          this.showPendingTime = false;
          this.showCustomTime = false;
          this.fetchEmailData(this.currentEmailType);
          return;
        }
      } catch (error) {
        console.error('标记为待处理出现错误:', error);
        throw error;
      }
    },

    open() {
      this.showPendingTime = true;
      this.showCustomTime = false;
    },

    close() {
      this.showPendingTime = false;
      this.showCustomTime = false;
    }
  }
}
</script>



