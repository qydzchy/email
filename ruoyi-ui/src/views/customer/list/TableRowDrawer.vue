<template>
  <div>
    <el-drawer :visible.sync="visible" :modal="false" :wrapperClosable="false" destroy-on-close :show-close="false"
               @close="onHideDrawer">
      <template #title>
        <div class="header flex-middle space-between py-20 px-20">
          <div>
            <i class="pointer el-icon-right" @click="onHideDrawer"></i>
          </div>
          <el-row class="flex-middle">
            <el-row class="flex-middle gap-16">
              <el-tooltip placement="top" content="关注">
                <i class="el-icon-time pointer"></i>
              </el-tooltip>
              <el-tooltip placement="top" content="写邮件">
                <i class="el-icon-message pointer"></i>
              </el-tooltip>
              <el-tooltip placement="top" content="编辑">
                <i class="el-icon-edit pointer"></i>
              </el-tooltip>
            </el-row>

            <el-dropdown trigger="click" class="ml-10">
             <span>
               <el-tooltip placement="left" content="更多操作">
                <i class="operate-more pointer el-icon-more-outline" style="transform: rotate(90deg)"></i>
              </el-tooltip>

            </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>移入公海</el-dropdown-item>
                <el-dropdown-item>重新分配</el-dropdown-item>
                <el-dropdown-item>共享客户</el-dropdown-item>
                <el-dropdown-item>合并客户</el-dropdown-item>
                <el-dropdown-item>新增报价单</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-row>
        </div>
      </template>
      <div class="container">
        <div class="base-info flex-start px-30">
          <el-avatar shape="square"></el-avatar>

          <div class="pl-10 fs-14">
            <label>测试公司</label>
            <div class="my-10">
              <span>11248</span>
              <span class="ml-10">冰岛</span>
            </div>
            <div class="mb-10">跟进入: admin</div>
            <div class="flex-middle">
              <el-tag class="customer-tag" closable>标签1</el-tag>
              <el-button class="ml-10" size="mini" icon="el-icon-plus"></el-button>
            </div>
          </div>
        </div>
        <div class="tabs mt-10">
          <el-tabs v-model="curTab" v-loading="cardLoading">
            <el-tab-pane v-for="tab in tabList" :key="tab.name" :label="tab.label" :name="tab.name">
              <component :is="tab.is"/>
            </el-tab-pane>

          </el-tabs>
        </div>
      </div>
      <el-backtop target=".el-tabs__content" :visibility-height="100"/>
    </el-drawer>

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
    visible: {
      type: Boolean,
      default: false,
      required: false,
    }
  },
  components: {
    TableRowActivityTab, TableRowDatumTab, TableRowTradeTab, TableRowTipsTab, TableRowDocTab
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
  },
  methods: {
    onHideDrawer() {
      this.$emit('update:visible', false)
    },
  }

}
</script>

<style lang="scss" scoped>
::v-deep .el-drawer.rtl {
  width: 600px !important;
}


::v-deep .el-drawer__header {
  padding: 0;
}

.header {
  border-bottom: 1px solid #f0f0f0;
}

::v-deep .el-tabs__header {
  margin: 0 30px;
}

::v-deep .el-tabs__content {
  height: 70vh;
  overflow-y: auto;

  .el-tab-pane {
    padding: 10px 30px 0 30px;
  }
}

.container {
  ::v-deep .el-tabs__nav-wrap::after {
    height: 1px;
    background-color: #f0f0f0;
  }

  ::v-deep .customer-tag {
    .el-tag__close:before {
      display: none;
    }

    &:hover .el-tag__close:before {
      display: block;
    }
  }
}
</style>
