<template>
  <div>
    <el-dialog
        width="400px"
        style="margin-top: 25vh"
        :visible.sync="groupDialog"
        :close-on-click-modal="false"
        :append-to-body="true"
        :title="title"
        @close="onCancel">
      <el-form class="py-20" style="height: 100px;">
        <el-form-item>
          <el-select-tree
              style="width: 100%"
              v-model="moveValue"
              :data="groupOption"
              :props="{value:'id',label:'name'}"
              :default-expand-all="true"
              :check-strictly="true">
          </el-select-tree>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button round :loading="btnLoading" @click="onCancel">取 消</el-button>
        <el-button type="primary" :loading="btnLoading" :disabled="!moveValue" round @click="onConfirm">确 定
        </el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import {movePacketCustomer} from "@/api/customer/publicleads";

export default {
  props: {
    row: {
      type: Object,
      default: () => {
      },
      required: false
    },
    visible: {
      type: Boolean,
      default: false,
      required: true
    },
    groupOption: {
      type: Array,
      default: () => [],
      required: false
    }
  },
  data() {
    return {
      groupDialog: false,
      title: '',
      moveValue: '',
      btnLoading: false
    }
  },
  watch: {
    visible: {
      handler(newVal) {
        this.groupDialog = newVal
      },
      immediate: true
    },
    row: {
      handler(newVal) {
        this.title = newVal?.companyName ? `将【${newVal?.companyName}】移动至分组` : '移动至分组'
      },
      immediate: true,
      deep: true,
    },
  },
  methods: {
    async onConfirm() {
      try {
        const ids =  Array.isArray(this.row?.id) ? this.row?.id : [this.row?.id]
        this.btnLoading = true
        const res = await movePacketCustomer({
          ids:ids,
          packetId: this.moveValue
        }).finally(() => {
          this.btnLoading = false
        })
        if (res.code === 200) {
          this.$message.success("移入分组成功")
          this.onCancel()
        }
      } catch (e) {
        console.error(e)
      }
    },
    onCancel() {
      this.title = ''
      this.moveValue = ''
      this.$emit('update:visible', false)
    }
  }
}
</script>

<style lang="scss" scoped>
.wrapper {
  height: 300px;
  overflow-y: auto;
}
</style>
