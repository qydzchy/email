<template>
  <div>
    <el-dropdown trigger="click" @command="handleCommand">
    <span>
      <slot></slot>
    </span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item
          v-for="(item) in itemList"
          :key="item.command"
          :command="item.command">
          {{ item.label }}
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <ScheduleDialog :visible.sync="scheduleVisible"/>
  </div>
</template>

<script>
import ScheduleDialog from "./ScheduleDialog.vue";

export default {
  props: {
    row: {
      type: Object,
      default: () => {
      },
      required: false
    }
  },
  components: {
    ScheduleDialog
  },
  mounted() {

  },
  data() {
    return {
      itemList: [
        {
          command: 'follow',
          label: '写跟进'
        },
        {
          command: 'write',
          label: '写邮件'
        },
        {
          command: 'schedule',
          label: '新建日程'
        },
        {
          command: 'moveGroup',
          label: '移动至分组'
        },
        {
          command: 'mergeCustomer',
          label: '合并客户'
        },
        {
          command: 'transfer',
          label: '转移给'
        },
        {
          command: 'share',
          label: '共享给'
        },
        {
          command: 'cancel',
          label: '取消跟进'
        },
        {
          command: 'movePool',
          label: '移入公海'
        },
        {
          command: 'reallocate',
          label: '重新分配'
        },
        {
          command: 'removeAndInto',
          label: '移除跟进入'
        },
        {
          command: 'changePoolGroup',
          label: '变更公海分组'
        },
      ],
      scheduleVisible: false
    }
  },
  methods: {
    handleCommand(value) {
      switch (value) {
        case "schedule":
          this.scheduleVisible = true
          break;
      }
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
