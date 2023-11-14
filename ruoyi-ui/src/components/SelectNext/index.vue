<template>
  <el-select style="width:100%" :value.sync="selectValue" size="small" v-bind="selectOptions" @change="handleChange"
             @visible-change="visibleChange">
    <el-option v-for="(opt,index) in selectOptions.options" :key="index" :label="opt.label" :value="opt.value">
      <span v-if="!opt.bgColor">{{ opt.label }}</span>
      <span v-else class="white-text px-4 py-4 radius-4" :style="`background-color:${opt.bgColor}`">{{
          opt.label
        }}</span>
    </el-option>
  </el-select>
</template>

<script>
export default {
  props: {
    value: {
      type: String | Number,
      default: '',
      required: true
    },
    selectOptions: {
      type: Object,
      default: () => {
        return {
          options: []
        }
      },
      required: false
    }
  },
  data() {
    return {
      selectValue: '',
    }
  },
  watch: {
    value: {
      handler(newVal) {
        this.selectValue = newVal
      },
      immediate: true
    }
  },
  methods: {
    handleChange(value) {
      this.$emit('change', value)
    },
    visibleChange(bool) {
      this.$emit('visibleChange', bool)
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
