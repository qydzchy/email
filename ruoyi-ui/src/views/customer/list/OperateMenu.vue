<template>
  <div>
    <el-popover
      width="100"
      placement="bottom"
      :visible-arrow="false"
    >
      <i class="operate-more pointer el-icon-more-outline" style="transform: rotate(90deg)" slot="reference"></i>
    </el-popover>
  </div>
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
