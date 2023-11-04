<template>
  <el-dropdown trigger="click" placement="bottom">
    <span>
      <i class="operate-more pointer el-icon-more-outline" style="transform: rotate(90deg)"></i>
    </span>

    <el-dropdown-menu slot="dropdown">
      <el-dropdown-item>黄金糕</el-dropdown-item>
      <el-dropdown-item>狮子头</el-dropdown-item>
      <el-dropdown-item>螺蛳粉</el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>
</template>

<script>
import Vue from 'vue'

export default {
  props: {
    row: {
      type: Object,
      default: () => {
      },
      required: true
    }
  },
  data() {
    return {
      aa: 111
    }
  },
  methods: {}
}
</script>

<style lang="scss" scoped>
</style>
