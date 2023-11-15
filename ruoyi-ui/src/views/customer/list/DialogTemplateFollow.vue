<template>
  <div>
    <el-dialog
        width="860px"
        :visible.sync="templateVisible"
        :show-close="false"
        :append-to-body="true"
        :close-on-click-modal="false"
        @close="onCancel">
      <template #title>
      </template>
      <div class="template-follow">
        <div class="left-wrap py-16 pl-16">
          <div>
            <span class="bold fs-16">模板</span>
            <span class="fs-12 pl-10">点击选择模板，可自动填充到输入框</span>
          </div>
          <el-row class="template-card mt-16 mr-8 borderBox">
            <el-col class="borderBox py-8 px-16 pointer flex-column">
              <span class="bold fs-14" title="模板名称测试">模板名称测试</span>
              <span class="fs-12 mt-8">模板内容测试</span>
            </el-col>
          </el-row>
        </div>
        <div class="right-wrap">
          <div class="right-title px-16 flex-middle space-between">
            <span>写跟进</span>
            <i class="el-icon-close pointer" @click="onCancel"></i>
          </div>
          <div class="right-main">
            <WriteFollow/>
          </div>

        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import WriteFollow from "./WriteFollow.vue";

export default {
  props: {
    visible: {
      type: Boolean,
      default: false,
      required: false
    },
    row: {
      type: Object,
      default: () => {
      },
      required: false
    }
  },
  components: {
    WriteFollow
  },
  data() {
    return {
      templateVisible: false,
    }
  },
  watch: {
    visible: {
      handler(newVal) {
        this.templateVisible = newVal
      },
      immediate: true
    }
  },
  methods: {
    onCancel() {
      this.$emit('close')
    }
  }
}
</script>

<style lang="scss" scoped>
::v-deep .el-dialog__body,
::v-deep .el-dialog__header {
  padding: 0;
}

::v-deep .el-dialog {
  margin-top: 15vh !important;
  overflow: hidden;
  border-radius: 10px;
}


.template-follow {
  min-height: 460px;
  background-color: #ffffff;
  display: flex;

  .left-wrap {
    width: 40%;
    background-color: rgb(247, 248, 251);

    .template-card {
      background-color: #ffffff;
    }

    .template-card:hover {
      background-color: rgb(236, 249, 255);
    }
  }

  .right-wrap {
    width: 60%;
    height: 100%;

    .right-title {
      height: 64px;
      border-bottom: 1px solid rgb(240, 240, 240);
    }

    .right-main {
      max-height: 560px;
      overflow-y: auto;
    }
  }
}
</style>
