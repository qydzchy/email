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
    <DialogSchedule :visible.sync="scheduleVisible"/>
    <DialogMoveToGroup :visible.sync="moveGroupVisible"/>
    <DialogMergeCustomer :visible.sync="mergeVisible"/>
    <DialogTransferTo :visible.sync="transferVisible"/>
    <DialogFollowAndChange :visible.sync="followVisible"/>
  </div>
</template>

<script>
import DialogSchedule from "./DialogSchedule.vue";
import DialogMoveToGroup from "./DialogMoveToGroup.vue";
import DialogMergeCustomer from "./DialogMergeCustomer.vue";
import DialogTransferTo from "./DialogTransferTo.vue";
import DialogFollowAndChange from "./DialogFollowAndChange.vue";
import {followCancelCustomer} from "@/api/customer/publicleads";

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
    DialogSchedule,
    DialogMoveToGroup,
    DialogMergeCustomer,
    DialogTransferTo,
    DialogFollowAndChange,
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
      scheduleVisible: false,
      moveGroupVisible: false,
      mergeVisible: false,
      transferVisible: false,
      followVisible: false
    }
  },
  methods: {
    handleCommand(value) {
      switch (value) {
        case "schedule":
          this.scheduleVisible = true
          break;
        case "moveGroup":
          this.moveGroupVisible = true
          break;
        case "merge":
          this.mergeVisible = true
          break;
        case "cancel":
          this.$confirm(`是否取消跟进【${this.row?.companyName || ''}】`, '取消跟进', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
            roundButton: true,
            beforeClose: async (action, instance, done) => {
              if (action === 'confirm') {
                instance.confirmButtonLoading = true;
                instance.confirmButtonText = '执行中...';
                try {
                  const res = await followCancelCustomer({id: this.row?.id}).finally(() => {
                    instance.confirmButtonLoading = false
                    done()
                  })
                  if (res.code === 200) {
                    this.$message.success('取消跟进成功')
                    this.$emit('load')
                  }
                } catch {
                }
              } else {
                done();
              }
            }
          }).then(action => {
            console.log(action)
          });
          break;
        case "removeAndInto":
          this.followVisible = true
          break;
        case "changePoolGroup":
          this.followVisible = true
          break;
      }
    },
  }
}
</script>

<style lang="scss" scoped>
</style>
