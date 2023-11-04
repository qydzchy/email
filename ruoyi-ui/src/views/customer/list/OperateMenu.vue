<template>
  <el-dropdown trigger="click">
    <span>
      <slot></slot>
    </span>
    <el-dropdown-menu slot="dropdown">
      <el-dropdown-item>写跟进</el-dropdown-item>
      <el-dropdown-item>写邮件</el-dropdown-item>
      <el-dropdown-item>新建日程</el-dropdown-item>
      <el-dropdown-item>移动至分组</el-dropdown-item>
      <el-dropdown-item>合并客户</el-dropdown-item>
      <el-dropdown-item>转移给</el-dropdown-item>
      <el-dropdown-item>共享给</el-dropdown-item>
      <el-dropdown-item>取消跟进</el-dropdown-item>
      <el-dropdown-item>移入公海</el-dropdown-item>
      <el-dropdown-item>重新分配</el-dropdown-item>
      <el-dropdown-item>移除跟进入</el-dropdown-item>
      <el-dropdown-item>变更公海分组</el-dropdown-item>
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
      required: false
    }
  },
  mounted() {

  },
  data() {
    return {}
  },
  methods: {}
}
</script>

<style lang="scss" scoped>
</style>
