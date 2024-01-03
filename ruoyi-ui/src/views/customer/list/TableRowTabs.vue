<template>
  <div class="row-tabs">
    <el-tabs v-model="curTab" v-loading="cardLoading" :class="options.isTabSetHeight ? 'tabs-height' : ''">
      <el-tab-pane v-for="tab in tabList" :key="tab.is" :label="tab.label" :name="tab.is">
        <component :row="row" :options="options" :is="tab.is" @reload="reload" />
      </el-tab-pane>
    </el-tabs>
    <!--    <div class="operate-history flex-middle pointer" @click="operateHistoryVisible=true">-->
    <!--      <i class="el-icon-time fs-13"></i>-->
    <!--      <span class="fs-12 pl-6">操作历史</span>-->
    <!--    </div>-->
    <!--    <CustomerOperateHistoryDrawer :visible.sync="operateHistoryVisible"/>-->
  </div>
</template>

<script>
import TableRowActivityTab from "./TableRowActivityTab.vue";
import TableRowDatumTab from "./TableRowDatumTab.vue";
import TableRowTradeTab from "./TableRowTradeTab.vue";
import TableRowTipsTab from "./TableRowTipsTab.vue";
import TableRowDocTab from "./TableRowDocTab.vue";
import TableRowDealingsEmailTab from './TableRowDealingsEmailTab.vue'
import CustomerOperateHistoryDrawer from "./CustomerOperateHistoryDrawer.vue";

export default {
  props: {
    row: {
      type: Object,
      default: () => {
      },
      required: false
    },
    options: {
      type: Object,
      default: () => {
        return {
          isTabSetHeight: true
        }
      },
      required: false
    },
    defaultCurTab: {
      type: String,
      default: 'TableRowActivityTab',
      required: false
    },
    defaultTabs: {
      type: Array,
      default: () => {
        return ['TableRowActivityTab', 'TableRowDatumTab', 'TableRowTradeTab', 'TableRowTipsTab', 'TableRowDocTab']
      },
      required: false
    }
  },
  components: {
    TableRowActivityTab,
    TableRowDatumTab,
    TableRowTradeTab,
    TableRowTipsTab,
    TableRowDocTab,
    TableRowDealingsEmailTab,
    CustomerOperateHistoryDrawer
  },
  data() {
    return {
      curTab: this.defaultCurTab,
      tabList: [],
      cardLoading: false,
      operateHistoryVisible: false,
    }
  },
  created() {
    const tabList = [{
      label: '动态',
      is: 'TableRowActivityTab',
    },
    {
      label: '资料',
      is: 'TableRowDatumTab',
    },
    {
      label: '商机&交易',
      is: 'TableRowTradeTab',
    },
    {
      label: 'Tips',
      is: 'TableRowTipsTab',
    },
    {
      label: '文档',
      is: 'TableRowDocTab',
    },
    {
      label: '往来邮件',
      is: 'TableRowDealingsEmailTab',
    }]
    this.tabList = tabList.filter(val => this.defaultTabs.includes(val.is))
  },
  methods: {
    reload() {
      this.$emit('reload')
    }
  },
}
</script>

<style lang="scss" scoped>
.row-tabs {
  position: relative;

  .operate-history {
    position: absolute;
    top: 10px;
    right: 20px;
    padding: 2px 6px;

    &:hover {
      border-radius: 10px;
      background-color: #f5f5f5;
    }
  }

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

  .tabs-main {}
}
</style>
