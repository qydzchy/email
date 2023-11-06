<template>
  <div>
    <el-input v-if="innerType==='input'"></el-input>
    <el-input-number v-if="innerType==='input-number'"></el-input-number>
    <el-select v-if="innerType==='select'"></el-select>
  </div>
</template>

<script>
export default {
  props: {
    type: {
      type: String,
      default: "input",
      required: true
    }
  },
  data() {
    return {
      innerType: ''
    }
  },
  watch: {
    type: {
      handler(newVal) {
        this.innerType = newVal
      },
      immediate: true
    }
  },
}
</script>

<style lang="scss" scoped>

</style>
