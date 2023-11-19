<template>
  <div>
    <el-drawer
        title="筛选条件"
        :visible.sync="visible"
        direction="rtl"
        :append-to-body="true"
        destroy-on-close
        @close="handleClose">
      <template #title>
        <div class="header flex-middle space-between py-20 px-20">
          <div class="black-text">
            操作历史
          </div>
        </div>
      </template>
      <div class="container">
        <el-timeline>
          <el-timeline-item timestamp="2018/4/12" placement="top">
            <el-card>
              <h4>更新 Github 模板</h4>
              <p>王小虎 提交于 2018/4/12 20:46</p>
            </el-card>
          </el-timeline-item>
          <el-timeline-item timestamp="2018/4/3" placement="top">
            <el-card>
              <h4>更新 Github 模板</h4>
              <p>王小虎 提交于 2018/4/3 20:46</p>
            </el-card>
          </el-timeline-item>
          <el-timeline-item timestamp="2018/4/2" placement="top">
            <el-card>
              <h4>更新 Github 模板</h4>
              <p>王小虎 提交于 2018/4/2 20:46</p>
            </el-card>
          </el-timeline-item>
        </el-timeline>
      </div>
      <!--   operate     -->
      <div class="drawer-operate">
        <div class="wrap flex-middle flex-end">
          <el-button round :loading="btnLoading" @click="handleClose">取消</el-button>
          <el-button type="primary" :loading="btnLoading" round @click="onConfirm">确认</el-button>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>

export default {
  props: {
    visible: {
      type: Boolean,
      default: false,
      required: false,
    },
  },
  data() {
    return {
      btnLoading: false
    }
  },
  methods: {
    handleClose() {
      this.$emit('update:visible', false)
    },
    onConfirm() {
    },
  }


}
</script>

<style lang="scss" scoped>
::v-deep .el-drawer.rtl {
  width: 700px !important;
}

.header {
  border-bottom: 1px solid #f0f0f0;
}

::v-deep .el-drawer__header {
  padding: 0;
  margin: 0;
}

.container {
  ::v-deep .el-timeline {
    margin-block-start: 0 !important;
    padding-inline-start: 16px !important;
  }
}

::v-deep .el-drawer__body {
  padding-bottom: 100px;
  position: relative;
  width: inherit;

  .drawer-operate {
    width: inherit;
    box-sizing: border-box;
    position: fixed;
    bottom: 0;
    padding: 16px 24px;
    background-color: #ffffff;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .drawer-operate > .wrap {
    width: 100%;
  }
}
</style>
