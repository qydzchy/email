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
        <el-select v-model="removeFollowPerson" filterable style="width: 100%">
          <el-option
              v-for="(item,index) in followPersonList"
              :key="index"
              :label="item.nickName"
              :value="item.userId">
          </el-option>
        </el-select>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button>取 消</el-button>
        <el-button type="primary" :disabled="!removeFollowPerson" @click="removeFollow">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {searchFollowerCustomer, unassignFollowerCustomer} from "@/api/customer/publicleads";

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
    }
  },
  data() {
    return {
      title: '',
      groupDialog: false,
      removeFollowPerson: '',
      followPersonList: [],
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
        this.title = newVal?.companyName ? `移除【${newVal.companyName}】的跟进人` : '移除跟进人'
      },
      immediate: true,
      deep: true,
    },
  },
  mounted() {
    this.getFollowPerson()
  },
  methods: {
    async getFollowPerson() {
      if (!this.row.id) return
      try {
        const res = await searchFollowerCustomer({
          id: this.row.id
        })
        if (res.code === 200) {
          this.followPersonList = res.data
        }
      } catch {
      }
    },
    async removeFollow() {
      try {
        const res = await unassignFollowerCustomer({
          id: this.row.id,
          userId: this.removeFollowPerson
        })
        if (res.code === 200) {
          this.$message.success('取消跟进入成功')
          this.onCancel()
        }
      } catch {
      }
    },
    onCancel() {
      this.title = ''
      this.$emit('update:visible', false)
    },
  }
}
</script>

<style lang="scss" scoped>
.wrapper {
  height: 300px;
  overflow-y: auto;
}
</style>
