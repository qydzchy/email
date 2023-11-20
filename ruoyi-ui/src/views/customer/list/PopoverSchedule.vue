<template>
  <el-popover v-model="popoverVisible" class="popover-schedule" :append-to-body="false">
    <template #default>
      <div style="width: 300px">
        <div class="main flex-column">
          <div>
            <div class="py-6">{{ formatDate(schedule.scheduleStartTime) }}</div>
            <div class="flex-middle">
              <div class="circle" :style="{color:schedule.color}"></div>
              <span class="pl-6 flex1">{{ schedule.scheduleContent }}</span>
            </div>
          </div>
          <el-divider class="my-4"></el-divider>
          <div class="py-6">
            <div class="fs-13 py-6">关联客户：</div>
            <div>{{ schedule.companyName }}</div>
          </div>
          <div class="py-6">
            <div class="fs-13 py-6">参与人：</div>
            <div>{{ schedule.participants }}</div>
          </div>
          <div class="py-6">
            <div class="fs-13 py-6">创建人：</div>
            <div>{{ schedule.createBy }}</div>
          </div>
        </div>
        <el-divider class="my-4"></el-divider>
        <div class="operate space-around">
          <span @click="onInnerClick">
            <slot name="edit"></slot>
          </span>
          <DelPopover placement="top" textColor="red" :id="schedule.id" @onDelete="onDeleteSchedule"/>
        </div>
      </div>
    </template>
    <template #reference>
      <div class="flex-middle" style="width: 100%">
        <div class="circle" :style="{color:schedule.color}"></div>
        <span class="date">{{ formatMonthAndDay(schedule.scheduleStartTime) }}</span>
        <span class="content flex1">{{ schedule.scheduleContent }}</span>
      </div>
    </template>
  </el-popover>
</template>

<script>
import DelPopover from "@/components/DevPopover/index.vue";
import {formatDate, formatMonthAndDay} from "@/utils";
import {deleteSchedule} from "@/api/customer/schedule";

export default {
  props: {
    schedule: {
      type: Object,
      default: () => {
        return {
          scheduleStartTime: '',
          color: '',
          scheduleContent: '',
          companyName: '',
          participants: '',
          createBy: '',
        }
      }
    }
  },
  components: {DelPopover},
  data() {
    return {
      popoverVisible: false
    }
  },
  methods: {
    onInnerClick() {
      this.popoverVisible = false
    },
    async onDeleteSchedule(id) {
      try {
        const res = await deleteSchedule({id})
        if (res.code === 200) {
          this.popoverVisible = false
          this.$emit('reload')
        }
      } catch {

      }

    },
    formatDate,
    formatMonthAndDay,
  },
}
</script>

<style lang="scss" scoped>
.popover-schedule {
  .circle {
    width: 8px;
    height: 8px;
    background: rgb(255, 51, 51);
    border-radius: 8px;
    padding: 0 4px;
  }

  .date {
    width: 102px;
    color: #0a6aff;
    word-wrap: break-word;
    padding-left: 4px;
  }

  .content {
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    color: rgba(37, 40, 46)
  }
}
</style>
