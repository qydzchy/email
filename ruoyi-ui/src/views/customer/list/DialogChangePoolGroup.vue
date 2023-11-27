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
          <el-select v-model="changePoolValue" filterable style="width: 100%">
            <el-option
                v-for="(item,index) in poolOption"
                :key="index"
                :label="item.name"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button round :loading="btnLoading" @click="onCancel">取 消</el-button>
        <el-button type="primary" :loading="btnLoading" :disabled="!changePoolValue" round @click="onConfirm">确 定
        </el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import {changeGroupsCustomer} from "@/api/customer/publicleads";

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
      required: false
    },
    poolOption: {
      type: Array,
      default: () => [],
      required: false
    }
  },
  data() {
    return {
      groupDialog: false,
      title: '',
      changePoolValue: '',
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
        this.title = newVal?.companyName ? `将【${newVal?.companyName}】移动至公海分组` : '移动至公海分组'
      },
      immediate: true,
      deep: true,
    },
  },
  methods: {
    async onConfirm() {
      try {
        this.btnLoading = true
        const res = await changeGroupsCustomer({
          id: this.row?.customerId,
          publicleadsGroupsId: this.changePoolValue
        }).finally(() => {
          this.btnLoading = false
        })
        if (res.code === 200) {
          this.$message.success("变更公海分组成功")
          this.onCancel()
        }
      } catch (e) {
        console.error(e)
      }
    },
    onCancel() {
      this.title = ''
      this.changePoolValue = ''
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
