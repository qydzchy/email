<template>
  <div>
    <el-dialog width="600px" :visible.sync="mergeDialog" :close-on-click-modal="false" :append-to-body="true"
               :title="title"
               @close="$emit('update:visible',false)">
      <el-form>
        <span>注释：仅支持有查看权限的私海客户</span>
        <el-input placeholder="请输入分组"></el-input>
        <el-row class="wrapper">
          <el-empty />
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
      mergeDialog: false,
      title: '选择与 测试公司001 合并的客户'
    }
  },
  watch: {
    visible: {
      handler(newVal) {
        this.mergeDialog = newVal
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
