<template>
  <div class="flex-middle space-between" @click="onClick">
    <span class="span-style" :title="content">
      <span>
         <slot name="content">
        {{ content || '---' }}
      </slot>
      </span>

    </span>
    <el-row
      type="flex"
      class="pl-10 gap-8"
      v-show="innerVisible"
    >
      <i v-if="disabledEdit" class="el-icon-edit pointer" @click.stop="$emit('onEdit')"></i>
      <i class="el-icon-copy-document pointer" @click.stop="onCopy"></i>
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
    },
    disabledEdit: {
      type: Boolean,
      default: true,
      required: false
    }
  },
  data() {
    return {
      content: '',
      innerVisible: false,
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
    },
    onClick() {
      this.$emit('click')
    },
  },
}
</script>

<style lang="scss" scoped>
.span-style {
  cursor: default;

  > span {
    width: auto;
  }
}
</style>
