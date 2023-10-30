<template>
  <el-select
      class="select-tree"
      style="width:100%"
      multiple
      collapse-tags
      clearable
      v-model="checkedData"
      :popper-append-to-body="false"
      @remove-tag="removeTag"
      @clear="clearAll">
    <el-option :value="selectTree" style="height:auto">
      <el-option v-show="false" v-for="item in selectTree" :value="item.id"
                 :label="item[defaultProps.label]"></el-option>
      <el-tree
          ref="tree"
          :data="treeData"
          show-checkbox
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
    }
  },
  data() {
    return {
      selectTree: [],
      checkedData: this.echoData
    }
  },
  watch: {},
  methods: {
    removeTag(item) {
      this.selectTree = this.selectTree.filter(val => val.id !== item)
      this.$nextTick(() => {
        this.$refs.tree.setChecked(item, false)
      })

    },
    clearAll() {
      this.$nextTick(() => {
        this.selectTree = []
        this.$refs.tree.setCheckedNodes([])
      })
    },
    handleNodeClick(data, _self, _child) {
      let dataList = this.$refs.tree.getCheckedNodes()
      this.selectTree = []
      this.checkedData = []
      dataList.forEach((item) => {
        if (!item.children.length) {
          this.selectTree.push({
            id: item.id,
            parentId: item.parentId,
            [this.defaultProps.label]: item[this.defaultProps.label]
          })
          this.checkedData.push(item.id)
        }
      })
      this.$emit('update:echoData', this.checkedData)
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
