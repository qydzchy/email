<template>
  <el-popover
      class="ml-10"
      :width="width"
      trigger="click"
      :placement="placement"
      v-model="visible"
  >
    <div class="flex-column">
      <div>
        <i class="el-icon-info" style="color: #E6A23C"></i>
        <span class="pl-4">{{ content }}</span>
      </div>
      <div class="flex-middle flex-end mt-16">
        <el-button round size="small" @click="visible=false">取消</el-button>
        <el-button type="danger" round size="small" @click="confirmDelete">删除</el-button>
      </div>
    </div>
    <template v-if="selfSlot" #reference>
      <slot/>
    </template>
    <el-button v-else size="mini" type="text" slot="reference" :disabled="btnDisabled">
      删除
    </el-button>
  </el-popover>
</template>

<script>
export default {
  props: {
    id: {
      type: String | Number,
      required: false,
    },
    placement: {
      type: String | Number,
      default: 'left-start',
      required: false
    },
    width: {
      type: String | Number,
      default: '200',
      required: false
    },
    content: {
      type: String,
      default: '确定要删除吗？',
      required: false
    },
    btnDisabled: {
      type: Boolean,
      default: false,
      required: false,
    },
    selfSlot: {
      type: Boolean,
      default: false,
      required: false
    },
  },
  data() {
    return {
      visible: false
    }
  },
  methods: {
    confirmDelete() {
      this.visible = false
      this.$emit('onDelete', this.id)
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
