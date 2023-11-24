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
        <el-form-item label="私海分组" required>
          <el-select-tree
              style="width: 100%"
              v-model="movePrivateGroup"
              :data="privateOption"
              clearable
              filterable
              :props="defaultProps"
              :render-after-expand="false">
          </el-select-tree>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button round :loading="btnLoading" @click="onCancel">取 消</el-button>
        <el-button type="primary" round :loading="btnLoading" :disabled="!movePrivateGroup" @click="onConfirm">确 定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {moveToPrivateLeads, moveToPublicLeads} from "@/api/customer/publicleads";

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
    privateOption: {
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
      movePrivateGroup: '',
      movePoolReason: '',
      btnLoading: false,
      defaultProps: {
        children: 'children',
        label: 'name',
        value: 'id'
      },
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
        this.title = newVal?.companyName ? `是否将【${newVal?.companyName}】移入私海` : '移入私海'
      },
      immediate: true,
      deep: true,
    },
  },
  methods: {
    async onConfirm() {
      try {
        this.btnLoading = true
        const res = await moveToPrivateLeads({
          id: this.row?.id,
          packetId:this.movePrivateGroup
        }).finally(() => {
          this.btnLoading = false
        })
        if (res.code === 200) {
          this.$message.success("移入私海成功")
          this.onCancel()
          this.$emit('onConfirm')
        }
      } catch (e) {
        console.error(e)
      }
    },
    onCancel() {
      this.title = ''
      this.movePrivateGroup = ''
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
