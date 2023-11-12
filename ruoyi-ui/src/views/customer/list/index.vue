<template>
  <div class="page-customer-public pt-16">
    <div class="bold fs-24 space-between px-30 pb-10 header">
      <div class="flex-middle">
        <span>客户列表</span>
        <div class="line mx-10"></div>
        <el-radio-group v-model="listType">
          <el-radio-button :label="0">我的客户</el-radio-button>
          <el-radio-button :label="1">团队客户</el-radio-button>
        </el-radio-group>
        <!--        <el-select class="ml-6"></el-select>-->
      </div>
      <div>
        <el-dropdown trigger="click" split-button type="primary" round @click="onShowDrawer" @command="handleCommand">
          <span>新建客户</span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="import">导入客户</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>

    <div class="container">
      <split-pane @resize="resize" :min-percent='16' :default-percent='16' split="vertical" ref="splitPane"
                  :class="{'cur-collapsed':collapsed}"
      >
        <template #paneL>
          <div class="left-wrap">
            <div class="flex-column" :class="{'is-collapsed':collapsed}" style="height: inherit;">
              <div class="menu">
                <el-collapse v-model="activeNames" @change="handleChange">
                  <template v-for="item in menuList">
                    <div
                      class="menu-item px-10 flex-middle space-between fs-14"
                      :class="{'active':item.key === curMenuActive}"
                      :key="item.key"
                      v-if="!item.children"
                      @click="curMenuActive = item.key"
                    >
                      <span>{{ item.name }}</span>
                      <span>{{ item.count }}</span>

                    </div>
                    <div class="pt-4" v-else>
                      <el-collapse-item :key="item.key" :name="item.key">
                        <template #title>
                          <div class="pl-6 fs-14">
                            {{ item.name }}
                            <!--                            <i class="header-icon el-icon-info"></i>-->
                          </div>

                        </template>
                        <div
                          class="menu-item flex-middle space-between my-4 pl-20 pr-10"
                          :class="{'active':subItem.key === curMenuActive}"
                          v-for="subItem in item.children"
                          :key="subItem.key"
                          @click="curMenuActive = subItem.key">
                          <span>{{ subItem.name }}</span>
                          <span>{{ subItem.count }}</span>
                        </div>
                      </el-collapse-item>
                    </div>

                  </template>

                </el-collapse>
              </div>
              <div class="fixed-operate flex-middle flex-center">
                <div class="hover-color fs-14 pointer" @click="$router.push('/customer/group?tab=list')">客群管理</div>
                <el-tooltip>
                  <template #content>
                    查看&nbsp;<a style="text-decoration: underline">客群功能详细说明</a>
                  </template>
                  <i class="hover-color el-icon-question pl-6 pointer"></i>
                </el-tooltip>
              </div>
              <span class="right-wrap-icon pointer" @click="onCollapsed">
              <i class="el-icon-d-arrow-left"></i>
            </span>
            </div>
            <div class="left-wrap-icon pointer" :class="{'arrow-right':collapsed}" @click="onLeftCollapsed">
              <i class="el-icon-d-arrow-right fs-14"></i>
            </div>

          </div>
        </template>
        <template #paneR>
          <div class="right-wrap">
            <TableList/>
          </div>

        </template>
      </split-pane>

    </div>
    <CreateCustomerDrawer :visible.sync="customerVisible"/>
  </div>
</template>

<script>
import FilterDrawer from './FilterDrawer.vue'
import CellOperate from './CellOperate.vue'
import HeaderFilter from './HeaderFilter.vue'
import TableList from './TableList.vue'
import CreateCustomerDrawer from "./CreateCustomerDrawer.vue";
import {listMenu} from "@/api/system/menu";
import {targetBlank} from "@/utils/tools";

export default {
  components: {
    FilterDrawer,
    CellOperate,
    HeaderFilter,
    TableList,
    CreateCustomerDrawer
  },
  data() {
    return {
      listType: 0,
      collapsed: false,
      percent: 16,
      curMenuActive: 'all',
      activeNames: ['group'],
      menuList: [
        {name: '全部', key: 'all', count: 6},
        {name: '我的关注', key: 'follow', count: 0},
        {
          name: '分组',
          key: 'group',
          children: [
            {name: '全部', key: 'group-all', count: 1},
            {name: '未分组', key: 'group-un', count: 0},
          ]
        },
      ],
      menuOptions: [],
      customerVisible: false
    }
  },
  mounted() {
  },
  methods: {
    onShowDrawer() {
      this.customerVisible = true
    },
    onCollapsed() {
      this.collapsed = true
      this.$refs.splitPane.percent = 0
    },
    onLeftCollapsed() {
      this.collapsed = false
      this.$refs.splitPane.percent = this.percent
    },
    resize(value) {
      if (value > 50) {
        this.$refs.splitPane.percent = 50
        this.percent = 50
        return
      }
      this.percent = value
    },
    handleCommand(command) {
      if (command === 'import') {
        targetBlank('/customer/config/import-operate')
      }
    },
    handleChange(e) {

    },
  }
}
</script>

<style lang="scss" scoped>
.page-customer-public {
  height: 100%;

  .header {
    border-bottom: 1px solid #f0f0f0;
  }

  .line {
    width: 1px;
    height: 16px;
    background-color: rgba(194, 197, 204);
  }
}

.container {
  height: calc(100% - 50px);

  .cur-collapsed {
    ::v-deep .splitter-pane-resizer {
      display: none;
    }
  }

  .is-collapsed {
    width: 0;
    display: none;
  }

  .left-wrap {
    position: relative;
    width: 100%;
    height: 100%;
    border-left: 1px solid #f0f0f0;
    background: #f7f8fb;
    z-index: 10;

    .menu {
      overflow-y: auto;
      flex: 1;

      &-item {
        height: 40px;
        cursor: pointer;

        &:hover {
          background-color: #f3f3f3;
        }
      }

      .menu-item.active {
        color: #0064ff;
        background-color: #f0f8ff;
      }


      ::v-deep .el-collapse-item__header,
      ::v-deep .el-collapse-item__wrap {
        background-color: transparent;
      }

      ::v-deep .el-collapse-item__wrap {
        padding: 0;
      }
    }

    .fixed-operate {
      height: 40px;
      border-top: 1px solid #f0f0f0;

      .hover-color {
        &:hover {
          color: #0a6aff;
        }
      }
    }

    &:hover {
      .right-wrap-icon {
        visibility: visible;
      }
    }

    .right-wrap-icon {
      width: 22px;
      height: 22px;
      position: absolute;
      top: 10px;
      right: -22px;
      display: flex;
      align-items: center;
      justify-content: center;
      border: 1px solid rgba(223, 225, 229);
      border-top-right-radius: 9999px;
      border-bottom-right-radius: 9999px;
      visibility: hidden;
      cursor: pointer;
      z-index: 10;
    }

    .left-wrap-icon {
      position: absolute;
      justify-content: center;
      align-items: center;
      height: 28px;
      color: #666;
      z-index: 15;
      text-align: center;
      background: #fafafa;
      top: 16px;
      left: 0;
      width: 20px;
      border: 1px solid #f0f0f0;
      box-shadow: 4px 0 4px rgba(0, 0, 0, .08);
      border-radius: 0 100px 100px 0;
      display: none;
    }

    .arrow-right {
      display: flex;
    }
  }

  .right-wrap {
    box-sizing: border-box;
    margin-right: 20px;
  }

  ::v-deep .splitter-pane-resizer.vertical {
    width: 4px;
    border-left: 0 solid hsla(0, 0%, 100%, 0);
    border-right: 0 solid hsla(0, 0%, 100%, 0);
    border-radius: 2px;
    margin-left: 1px;
    z-index: 4;
  }

  ::v-deep .splitter-pane-resizer {
    background-color: transparent;
    opacity: unset;

    &:hover, &:active {
      background-color: #0a6aff;
    }
  }

  ::v-deep .splitter-paneL {
    display: inline;
    padding-right: 0 !important;
  }

  ::v-deep .splitter-paneR {
    padding: 0;
  }
}

::v-deep .el-dropdown {
  .el-button-group {
    button:nth-child(1) {
      border-bottom-left-radius: 20px;
      border-top-left-radius: 20px;
    }

    button:nth-child(2) {
      border-bottom-right-radius: 20px;
      border-top-right-radius: 20px;
    }
  }
}


</style>
