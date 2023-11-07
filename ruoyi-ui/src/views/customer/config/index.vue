<template>
  <div class="mx-20 pt-16">
    <div class="bold fs-26 pb-4">
      客户设置
    </div>
    <div class="tabs">
      <el-tabs v-model="curTab" @tab-click="handleTabs">
        <el-tab-pane v-for="(tab,index) in tabsList" :key="index" :label="tab.label" :name="tab.name" lazy>
          <component v-if="curTab===tab.name" :is="curTab"/>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
import MovePool from './movePool.vue'
import Group from './group.vue'
import Tags from './tags.vue'
import Origin from './origin.vue'
import Import from './import.vue'
import {param} from "@/utils";

export default {
  components: {
    'move-tool': MovePool, Group, Tags, Origin, Import
  },
  data() {
    return {
      curTab: '',
      tabsList: [
        {
          label: '移入公海规则',
          name: 'move-tool',
        },
        {
          label: '分组设置',
          name: 'group',
        },
        {
          label: '标签管理',
          name: 'tags',
        },
        {
          label: '客户来源',
          name: 'origin',
        },
        {
          label: '客户导入',
          name: 'import',
        },
      ]
    }
  },
  mounted() {
    const tab = this.$route.query?.tab
    this.generateTab(tab)
  },
  methods: {
    handleTabs(tab, _event) {
      this.generateTab(tab.name)
    },
    generateTab(tab) {
      this.curTab = tab || 'move-tool'
      if (this.curTab === 'import') {
        this.$router.replace(`/customer/config?${param(this.$route.query)}`)
        return
      }
      this.$router.replace(`/customer/config?tab=${this.curTab}`)
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
