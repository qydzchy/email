<template>
  <div>
    <el-dialog
      width="400px"
      style="margin-top: 25vh"
      :visible.sync="transferDialog"
      :close-on-click-modal="false"
      :append-to-body="true"
      :title="generateTitle"
      @close="onCancel">
      <el-form label-position="top">
        <span class="fs-13">{{ generateDescription }}</span>
        <!--    转移给和共享    -->
        <el-select class="my-10" v-if="curType==='transfer'" key="default" v-model="singleValue" filterable
                   style="width: 100%">
          <el-option
            v-for="(item,index) in followPersonList"
            :key="index"
            :label="item.nickName"
            :value="item.userId">
          </el-option>
        </el-select>
        <!--    重新分配    -->
        <el-select class="my-10" v-else key="reassign" v-model="commonValue" multiple filterable style="width: 100%">
          <el-option
            v-for="(item,index) in followPersonList"
            :key="index"
            :label="item.nickName"
            :value="item.userId">
          </el-option>
        </el-select>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button round :loading="btnLoading" @click="onCancel">取 消</el-button>
        <el-button type="primary" round :loading="btnLoading" :disabled="!commonValue && !reassignValue.length"
                   @click="onConfirm">确 定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  getAllUser,
  reassignToCustomer,
  searchFollowerCustomer,
  shareToCustomer,
  transferredToCustomer
} from "@/api/customer/publicleads";

export default {
  props: {
    row: {
      type: Object,
      default: () => {
      },
      required: false
    },
    curType: {
      type: "transfer" | "share" | "reassign",
      default: "",
      required: false
    },
    visible: {
      type: Boolean,
      default: false,
      required: false
    }
  },
  data() {
    return {
      transferDialog: false,
      singleValue: '',
      commonValue: [],
      followPersonList: [],
      btnLoading: false
    }
  },
  computed: {
    generateTitle() {
      const mapTitlePrefix = {
        transfer: '转移给',
        share: '共享给',
        reassign: '重新分配'
      }
      return this.row?.companyName ? `选择与【${this.row.companyName}】${mapTitlePrefix[this.curType]}客户` : mapTitlePrefix[this.curType]
    },
    generateDescription() {
      const mapTitlePrefix = {
        transfer: '转移',
        share: '共享',
        reassign: '重新分配'
      }
      return `${mapTitlePrefix[this.curType]}客户后，${mapTitlePrefix[this.curType]}人将会互见客户的全部详细资料。`
    },
  },
  watch: {
    visible: {
      handler(newVal) {
        this.transferDialog = newVal
      },
      immediate: true
    }
  },
  mounted() {
    this.getAllUserList()
  },
  methods: {
    async getAllUserList() {
      if (!this.row.id) return
      try {
        const res = await getAllUser()
        if (res.code === 200) {
          this.followPersonList = res.data
        }
      } catch {
      }
    },
    async addTransferTo() {
      try {
        this.btnLoading = true
        const res = await transferredToCustomer({
          id: this.row.id,
          userId: this.singleValue
        }).finally(() => {
          this.btnLoading = false
        })
        if (res.code === 200) {
          this.$message.success('转移成功')
          this.onCancel()
        }
      } catch {
      }
    },
    async addShareTo() {
      try {
        this.btnLoading = true
        const res = await shareToCustomer({
          id: this.row.id,
          userIds: this.commonValue
        }).finally(() => {
          this.btnLoading = false
        })
        if (res.code === 200) {
          this.$message.success('共享成功')
          this.onCancel()
        }
      } catch {
      }
    },
    async addReassign() {
      try {
        this.btnLoading = true
        const res = await reassignToCustomer({
          id: this.row.id,
          userIds: this.commonValue
        }).finally(() => {
          this.btnLoading = false
        })
        if (res.code === 200) {
          this.$message.success('重新分配成功')
          this.onCancel()
        }
      } catch {
      }
    },
    onConfirm() {
      switch (this.curType) {
        case 'transfer':
          this.addTransferTo()
          break;
        case 'share':
          this.addShareTo()
          break;
        case 'reassign':
          this.addReassign()
          break;
      }
    },
    onCancel() {
      this.singleValue = []
      this.commonValue = []
      this.$emit('onHideDialog', false)
    },
  },
}
</script>

<style lang="scss" scoped>
.wrapper {
  height: 300px;
  overflow-y: auto;
}
</style>
