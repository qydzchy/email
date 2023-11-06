<template>
  <div class="search-group flex-middle gap-8">
    <el-select style="width:200px" placeholder="请选择" v-model="searchQuery.group" @change="handleSelect">
      <el-option v-for="(opt,index) in infoOption" :key="index" :label="opt.label" :value="opt.value">
      </el-option>
    </el-select>
    <el-input style="width:200px" placeholder="请输入"></el-input>
    <FilterDrawer/>
    <!--                <el-popover-->
    <!--                  trigger="click"-->
    <!--                  width="200"-->
    <!--                >-->
    <!--                  <el-button icon="el-icon-setting" round slot="reference">设置</el-button>-->
    <!--                </el-popover>-->
  </div>
</template>

<script>
import FilterDrawer from "./FilterDrawer.vue";

export default {
  components: {FilterDrawer},
  data() {
    return {
      infoOption: [
        {label: '公司名称', value: 'name', type: 'input'},
        {label: '邮箱', value: 'email', type: 'input'},
        {label: '客户标签', value: 'tag', type: 'select-tag'},
        {label: '分组', value: 'group', type: 'select-tree'},
        {label: '国家地区', value: 'area', type: 'select-tree'},
        {label: '客户来源', value: 'origin', type:'select-tree'},
        {label: '客户阶段', value: 'stage',type:'select-tag'},
        {label: '客户星级', value: 'star',type:'select'},
        {label: '最近联系时间', value: 'contactTime',},
        {label: '下次移入公海时间', value: 'movePoolTime'},
        {label: '是否关注', value: 'isFollow'},
        {label: '跟进人', value: 'follow'},
      ],
      searchQuery: {
        group: 'group',
        parentId: null,
      }
    }
  },
  methods: {
    handleSelect(value) {

    },
  }
}
</script>

<style lang="scss" scoped>

</style>
