<template>
  <div>
    <div class="collapse-header" @click="isShow=!isShow">
      <div class="flex-middle pointer">
        <i class="collapse-icon el-icon-arrow-right fs-16" :class="{'collapse-icon-down':isShow}"></i>
        <div class="flex1 pl-4">
          <slot name="header"></slot>
        </div>
      </div>
    </div>
    <div class="collapse mt-10" :class="{'collapse-hide':!isShow}"
         :style="`height:${!height ? 'auto': (height + 'px')}`">
      <slot></slot>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Collapse',
  props: {
    isCollapse: {
      type: Boolean,
      default: false
    },
    height: {
      type: Number,
      default: 300,
    }
  },
  mounted() {
    this.isShow = this.isCollapse
  },
  data() {
    return {
      isShow: true
    }
  },
}
</script>

<style scoped>
.collapse-header {
  .collapse-icon {
    transform: rotate(0deg);
    transition: all 0.2s ease-in-out;
  }

  .collapse-icon-down {
    transform: rotate(90deg);
  }
}

.collapse {
  overflow: hidden;
  transition: all 0.3s ease-in-out;
}

.collapse-hide {
  height: 0 !important;
}

</style>
