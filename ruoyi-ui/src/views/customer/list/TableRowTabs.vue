<template>
  <div class="row-tabs">
    <el-tabs v-model="curTab" v-loading="cardLoading" :class="options.isTabSetHeight ?'tabs-height':''">
      <el-tab-pane v-for="tab in tabList" :key="tab.name" :label="tab.label" :name="tab.name">
        <component :options="options" :is="tab.is"/>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import TableRowActivityTab from "./TableRowActivityTab.vue";
import TableRowDatumTab from "./TableRowDatumTab.vue";
import TableRowTradeTab from "./TableRowTradeTab.vue";
import TableRowTipsTab from "./TableRowTipsTab.vue";
import TableRowDocTab from "./TableRowDocTab.vue";

export default {
  props: {
    options: {
      type: Object,
      default: () => {
        return {
          isTabSetHeight: true
        }
      },
      required: false
    }
  },
  components: {
    TableRowActivityTab,
    TableRowDatumTab,
    TableRowTradeTab,
    TableRowTipsTab,
    TableRowDocTab
  },
  data() {
    return {
      curTab: "1",
      tabList: [
        {
          label: '动态',
          name: '1',
          is: 'TableRowActivityTab',
        },
        {
          label: '资料',
          name: '2',
          is: 'TableRowDatumTab',
        },
        {
          label: '商机&交易',
          name: '3',
          is: 'TableRowTradeTab',
        },
        {
          label: 'Tips',
          name: '4',
          is: 'TableRowTipsTab',
        },
        {
          label: '文档',
          name: '5',
          is: 'TableRowDocTab',
        },
      ],
      cardLoading: false,
    }
  }
}
</script>

<style lang="scss" scoped>
.row-tabs {
  ::v-deep .el-tabs__header {
    margin: 0 30px;
  }

  ::v-deep .el-tabs__nav-wrap::after {
    height: 1px;
    background-color: #f0f0f0;
  }

  .tabs-height {
    ::v-deep .el-tabs__content {
      height: 70vh;
      overflow-y: auto;
    }
  }

  ::v-deep .el-tabs__content {
    .el-tab-pane {
      padding: 10px 30px 0 30px;
    }
  }

  .tabs-main {

  }
}
</style>
