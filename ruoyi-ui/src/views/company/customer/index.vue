<template>
  <div class="page-company">
    <div class="bold fs-30 pb-4">
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
import group from './group.vue'
import status from './status.vue'
import poolRule from './poolRule.vue'
import follow from './follow.vue'
import blacklist from './blacklist.vue'
import openSea from './openSea.vue'
import search from './search.vue'
import followText from './followText.vue'

export default {
  components: {
    group,
    status,
    poolRule,
    follow,
    blacklist,
    openSea,
    search,
    followText
  },
  data() {
    return {
      curTab: '',
      tabsList: [
        {
          label: '客户分组',
          name: 'group',
        },
        {
          label: '客户阶段',
          name: 'status',
        },
        {
          label: '公海管理',
          name: 'poolRule',
        },
        {
          label: '客户跟进规则',
          name: 'follow',
        },
        {
          label: '建档黑名单',
          name: 'blacklist',
        },
        {
          label: '客户上限',
          name: 'openSea',
        },
        {
          label: '客户查重设置',
          name: 'search',
        },
        {
          label: '写跟进测试',
          name: 'followText',
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
      this.curTab = tab || 'group'
      this.$router.replace(`/company/customer-setting?tab=${this.curTab}`)
    }
  }
}
</script>

<style lang="scss" scoped>
.page-company{
  height: inherit;
}

::v-deep .el-tabs__content{
  height: calc(100% - 100px);
}
</style>
