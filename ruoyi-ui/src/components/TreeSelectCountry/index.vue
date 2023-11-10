<template>
  <el-select
    class="select-tree"
    style="width:100%"
    collapse-tags
    clearable
    v-model="checkedData"
    :popper-append-to-body="false"
    @clear="clearAll"
    :disabled="disabled"
  >
    <el-option value="empty" style="height:auto">
      <el-option v-show="false" v-for="item in selectTree" :value="item[disabledKey]"
                 :label="item[defaultProps.label]"></el-option>
      <el-tree
        ref="tree"
        :data="curTreeData"
        node-key="id"
        highlight-current
        :default-expand-all="true"
        :props="defaultProps"
        :expand-on-click-node="false"
        @check-change="handleNodeClick"
      ></el-tree>
    </el-option>
  </el-select>
</template>

<script>
export default {
  props: {
    treeData: {
      type: Array,
      default: () => [],
      required: true
    },
    defaultProps: {
      type: Object,
      default: () => {
        return {
          children: 'children',
          label: 'label'
        }
      },
      required: true
    },
    echoData: {
      type: Array,
      default: () => [],
      required: true
    },
    disabledList: {
      type: Array,
      default: () => [],
      required: false
    },
    disabledKey: {
      type: String,
      default: 'id',
      required: false
    },
    disabled: {
      type: Boolean,
      default: false,
      required: false
    },
  },
  data() {
    return {
      selectTree: [],
      checkedData: [],
    }
  },
  mounted() {
    this.generateOption()
  },
  watch: {
    echoData: {
      handler(newVal) {
        this.checkedData = newVal
        this.$nextTick(() => {
          this.$refs.tree.setCheckedKeys(newVal, true)
        })
      },
      immediate: true
    }
  },
  computed: {
    curTreeData() {
      const newMemberOption = arr => arr.map((val) => {
        val.disabled = this.disabledList.includes(val[this.disabledKey])
        val[this.defaultProps.children] = val[this.defaultProps.children].length ? newMemberOption(val[this.defaultProps.children]) : []
        return val
      })
      return newMemberOption(this.treeData)
    },
  },
  methods: {
    removeTag(item) {
      this.$nextTick(() => {
        this.$refs.tree.setChecked(item, false)
      })
    },
    clearAll() {
      this.$nextTick(() => {
        this.$refs.tree.setCheckedNodes([])
      })
    },
    handleNodeClick(data, _self, _child) {
      let dataList = this.$refs.tree.getCheckedNodes()
      this.checkedData = []
      dataList.forEach((item) => {
        if (!item.children.length) {
          this.checkedData.push(item.id)
        }
      })
      this.$emit('input', this.checkedData)
      this.$emit('update:echoData', this.checkedData)
    },
    generateOption() {
      let newOption = []
      const flatOption = arr => arr.forEach(val => {
        newOption.push({
          [this.disabledKey]: val[this.disabledKey],
          [this.defaultProps.label]: val[this.defaultProps.label]
        })
        val[this.defaultProps.children] && flatOption(val[this.defaultProps.children])
      })
      flatOption(this.treeData)
      this.selectTree = newOption
    },
  }
}
</script>

<style lang="scss" scoped>
.select-tree {
  .el-select-dropdown__item {
    padding: 0 !important;
    overflow-y: auto;
  }
}
</style>
