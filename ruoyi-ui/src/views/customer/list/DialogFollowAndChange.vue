<template>
  <div>
    <el-dialog width="600px" :visible.sync="groupDialog" :close-on-click-modal="false" :append-to-body="true"
               :title="title"
               @close="$emit('update:visible',false)">
      <el-form>
        <el-input placeholder="请输入业务员昵称"></el-input>
        <el-row class="wrapper">
          <el-tree>

          </el-tree>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button>取 消</el-button>
        <el-button type="primary">确 定</el-button>
      </div>
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
      groupDialog: false,
      title: '是否将【测试公司001】移入公海'
    }
  },
  watch: {
    visible: {
      handler(newVal) {
        this.groupDialog = newVal
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
