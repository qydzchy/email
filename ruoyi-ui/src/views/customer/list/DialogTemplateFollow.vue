<template>
  <div>
    <el-dialog :visible.sync="templateVisible" :append-to-body="true">

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
}
</script>

<style lang="scss" scoped>

</style>
