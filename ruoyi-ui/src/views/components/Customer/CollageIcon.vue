<template>
  <el-tooltip placement="top" :content="isCollage?'取消关注':'关注'">
    <span class="wrap">
      <svg-icon
        class="like-icon pointer fs-18"
        :style="{display:defaultHide?'':'block'}"
        :class="{'active':isCollage}" icon-class="like"
        @click="onClick"/>
    </span>

  </el-tooltip>
</template>

<script>
export default {
  props: {
    show: {
      type: Boolean,
      default: false,
      required: true
    },
    defaultHide: {
      type: Boolean,
      default: true,
      required: false
    },
  },
  data() {
    return {
      isCollage: false
    }
  },
  watch: {
    show: {
      handler(newVal) {
        this.isCollage = newVal
      },
      immediate: true
    }
  },
  methods: {
    onClick() {
      this.$emit('click', this.isCollage)
    }
  }
}
</script>

<style lang="scss" scoped>
.wrap {
  width: 100%;
  height: 20px;

  &:hover > .like-icon {
    color: red;
    display: block;
  }

  .like-icon {
    display: none;
  }
}

.like-icon.active {
  color: red;
  display: block;

  &:hover {
    color: #72767b;
  }
}
</style>
