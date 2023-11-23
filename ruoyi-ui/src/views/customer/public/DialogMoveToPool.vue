<template>
  <div>
    <el-dialog
        width="400px"
        style="margin-top: 25vh"
        :visible.sync="groupDialog"
        :close-on-click-modal="false"
        :append-to-body="true"
        :title="title"
        @close="$emit('update:visible',false)">
      <el-form label-position="top">
        <el-form-item label="公海分组" required>
          <el-select v-model="movePoolGroup" style="width: 100%" filterable>
            <el-option
                v-for="(pool,index) in poolOption"
                :key="index"
                :label="pool.name"
                :value="pool.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="移入公海原因">
          <el-select v-model="movePoolReason" style="width: 100%" filterable>
            <el-option
                v-for="(reason,index) in reasonOption"
                :key="index"
                :label="reason.reason"
                :value="reason.id">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button round :loading="btnLoading" @click="onCancel">取 消</el-button>
        <el-button type="primary" round :loading="btnLoading" :disabled="!movePoolGroup" @click="onConfirm">确 定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {moveToPublicLeads} from "@/api/customer/publicleads";

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
    poolOption: {
      type: Array,
      default: () => [],
      required: false
    },
    reasonOption: {
      type: Array,
      default: () => [],
      required: false
    }
  },
  data() {
    return {
      groupDialog: false,
      title: '',
      movePoolGroup: '',
      movePoolReason: '',
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
        this.title = newVal?.companyName ? `是否将【${newVal?.companyName}】移入公海` : '移入公海'
      },
      immediate: true,
      deep: true,
    },
  },
  methods: {
    async onConfirm() {
      try {
        this.btnLoading = true
        const res = await moveToPublicLeads({
          id: this.row?.id,
          publicleadsGroupsId: this.movePoolGroup,
          publicleadsReasonId: this.movePoolReason,
        }).finally(() => {
          this.btnLoading = false
        })
        if (res.code === 200) {
          this.$message.success("移入公海成功")
          this.onCancel()
          this.$emit('onConfirm')
        }
      } catch (e) {
        console.error(e)
      }
    },
    onCancel() {
      this.title = ''
      this.movePoolGroup = ''
      this.movePoolReason = ''
      this.$emit('update:visible', false)
    }
  },
}
</script>

<style lang="scss" scoped>
.wrapper {
  height: 300px;
  overflow-y: auto;
}
</style>
