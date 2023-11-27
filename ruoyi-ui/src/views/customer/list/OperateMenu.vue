<template>
  <div>
    <el-dropdown trigger="click" @command="handleCommand">
    <span>
      <slot></slot>
    </span>
      <el-dropdown-menu slot="dropdown">
        <template v-for="(item) in itemList">
          <el-dropdown-item
              v-if="commandList.includes(item.command)"
              :key="item.command"
              :command="item.command">
            {{ item.label }}
          </el-dropdown-item>
        </template>

      </el-dropdown-menu>
    </el-dropdown>
    <!--  写跟进  -->
    <DialogTemplateFollow
        v-if="templateVisible"
        :visible.sync="templateVisible"
        :row="row"
        @close="hideTemplateDialog"
        @onConfirm="hideTemplateDialog"
    />
    <!--  日程  -->
    <DialogSchedule
        v-if="scheduleVisible"
        :visible.sync="scheduleVisible"
        :formData="row"
    />
    <!--  移动至分组  -->
    <DialogMoveToGroup
        v-if="moveGroupVisible"
        :visible.sync="moveGroupVisible"
        :row="row"
        :groupOption="indexOpt.groupOption"/>
    <!--  合并  -->
    <DialogMergeCustomer
        v-if="mergeVisible"
        :visible.sync="mergeVisible"/>
    <!--  公共弹框：转移给、共享、重新分配  -->
    <DialogCommonOperate
        v-if="commonVisible"
        :cur-type="commonType"
        :row="row"
        :visible.sync="commonVisible"
        @onHideDialog="onHideCommonDialog"/>
    <!--  移除  -->
    <DialogRemoveFollow
        v-if="followVisible"
        :row="row"
        :visible.sync="followVisible"/>
    <!--  移入公海  -->
    <DialogMoveToPool
        v-if="moveToPoolVisible"
        :row="row"
        :visible.sync="moveToPoolVisible"
        :poolOption="indexOpt.poolGroupOption"
        :reasonOption="indexOpt.poolReasonOption"
        @onConfirm="onMoveToPollConfirm"
    />
    <!--  改变公海分组  -->
    <DialogChangePoolGroup
        v-if="changePoolVisible"
        :row="row"
        :visible.sync="changePoolVisible"
        :poolOption="indexOpt.poolGroupOption"
    />
  </div>
</template>

<script>
import DialogSchedule from "./DialogSchedule.vue";
import DialogMoveToGroup from "./DialogMoveToGroup.vue";
import DialogMergeCustomer from "./DialogMergeCustomer.vue";
import DialogRemoveFollow from "./DialogRemoveFollow.vue";
import DialogMoveToPool from "./DialogMoveToPool.vue";
import DialogChangePoolGroup from "./DialogChangePoolGroup.vue";
import DialogCommonOperate from "./DialogCommonOperate.vue";
import DialogTemplateFollow from "./DialogTemplateFollow.vue"
import {followCancelCustomer} from "@/api/customer/publicleads";

export default {
  props: {
    row: {
      type: Object,
      default: () => {
      },
      required: false
    },
    indexOpt: {
      type: Object,
      default: () => {
        return {}
      },
      required: false
    },
    commandList: {
      type: Array,
      default: () => {
        return ['follow', 'write', 'schedule', 'moveGroup', 'mergeCustomer', 'transfer', 'share', 'cancel', 'movePool', 'reassign', 'removeAndInto', 'changePoolGroup']
      },
      required: false
    },

  },
  components: {
    DialogTemplateFollow,
    DialogSchedule,
    DialogMoveToGroup,
    DialogMergeCustomer,
    DialogRemoveFollow,
    DialogMoveToPool,
    DialogChangePoolGroup,
    DialogCommonOperate,
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
          command: 'reassign',
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
      templateVisible: false,
      scheduleVisible: false,
      moveGroupVisible: false,
      mergeVisible: false,
      followVisible: false,
      moveToPoolVisible: false,
      changePoolVisible: false,
      commonVisible: false,
      commonType: ''
    }
  },
  methods: {
    handleCommand(value) {
      switch (value) {
        case "follow":
          this.templateVisible = true
          break;
        case "schedule":
          this.scheduleVisible = true
          break;
        case "moveGroup":
          this.moveGroupVisible = true
          break;
        case "mergeCustomer":
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
                  const res = await followCancelCustomer({id: this.row?.customerId}).finally(() => {
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
          }).then(_action => {
          });
          break;
        case "removeAndInto":
          this.followVisible = true
          break;
        case "movePool":
          this.moveToPoolVisible = true
          break;
        case "changePoolGroup":
          this.changePoolVisible = true
          break;
        case "transfer":
          this.commonType = value
          this.commonVisible = true
          break;
        case "share":
          this.commonType = value
          this.commonVisible = true
          break;
        case "reassign":
          this.commonType = value
          this.commonVisible = true
          break;
      }
    },
    hideTemplateDialog() {
      this.templateVisible = false
    },
    onHideCommonDialog() {
      this.commonType = ''
      this.commonVisible = false
    },
    onMoveToPollConfirm() {
      this.$emit('load')
    },
  }
}
</script>

<style lang="scss" scoped>
</style>
