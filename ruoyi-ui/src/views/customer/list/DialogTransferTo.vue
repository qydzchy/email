<template>
  <div>
    <el-dialog width="600px" :visible.sync="transferDialog" :close-on-click-modal="false" :append-to-body="true"
               :title="title"
               @close="$emit('update:visible',false)">
      <el-form>
        <span>转移客户后，转移人将会互见客户的全部详细资料。</span>
        <el-select placeholder="请选择" filterable></el-select>
        <el-row>
         <el-col>
           <el-checkbox>共享客户商机</el-checkbox>
         </el-col>
          <el-col>
            <el-checkbox>共享客户订单</el-checkbox>
          </el-col>
          <el-col>
            <el-checkbox>共享客户邮件</el-checkbox>
            <span>共享后，新的跟进人将在邮箱收到原跟进人和该客户半年内的往来邮件</span>
          </el-col>
          <el-col>
            <el-checkbox>共享客户日程</el-checkbox>
          </el-col>
        </el-row>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
export default {
  props: {
    visible: {
      type: Boolean,
      default: false,
      required: false
    }
  },
  data() {
    return {
      transferDialog: false,
      title: '选择与 测试公司001 合并的客户'
    }
  },
  watch: {
    visible: {
      handler(newVal) {
        this.transferDialog = newVal
      },
      immediate: true
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
