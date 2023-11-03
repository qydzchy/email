<template>
  <div class="flex-middle space-between">
    <el-row>
      <span :title="content">{{ content || '---'}}</span>
    </el-row>
    <el-row
      type="flex"
      class="pl-10 gap-8"
      v-show="innerVisible"
    >
      <i class="el-icon-edit pointer" @click="$emit('onEdit')"></i>
      <i class="el-icon-document-copy pointer" @click="onCopy"></i>
    </el-row>

  </div>

</template>

<script>

export default {
  props: {
    text: {
      type: String,
      default: '',
      required: false
    },
    visible: {
      type: Boolean,
      default: false,
      required: true
    }
  },
  data() {
    return {
      content: '',
      innerVisible: false
    }
  },
  watch: {
    text: {
      handler(newVal) {
        this.content = newVal
      },
      immediate: true
    },
    visible: {
      handler(newVal) {
        this.innerVisible = newVal
      },
      immediate: true
    }
  },
  methods: {
    onCopy() {
      if (!this.content) {
        this.$message.info('复制内容不能为空')
        return
      }
      this.$copyText(this.content).then(() => {
        this.$message.success('复制成功')
      })
    }
  },
}
</script>

<style lang="scss" scoped>

</style>
