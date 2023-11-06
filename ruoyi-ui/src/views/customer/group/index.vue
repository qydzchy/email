<template>
  <div class="mx-20">
    <el-tabs v-model="curTab">
      <el-tab-pane v-for="tab in tabList" :key="tab.name" :label="tab.label" :name="tab.name">
        <component :is="tab.is"/>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import List from './list.vue'
import Templates from './templates.vue'

export default {
  components: {
    List,
    Templates
  },
  data() {
    return {
      curTab: '',
      tabList: [
        {
          name: 'list',
          label: '客户列表',
          is: 'List'
        },
        {
          name: 'template',
          label: '客户模板',
          is: 'Templates'
        }
      ]
    }
  },
  mounted() {
    const tab = this.$route.query?.tab
    this.generateTab(tab)
  },
  methods: {
    generateTab(tab) {
      this.curTab = tab || 'list'
      this.$router.replace(`/customer/group?tab=${this.curTab}`)
    }
  }
}
</script>

<style lang="scss" scoped>
::v-deep .el-tabs__header {
  margin: 0;
}

::v-deep .el-tabs__nav-wrap::after{
  height: 1px;
}
</style>
