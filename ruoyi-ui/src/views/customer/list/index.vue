<template>
  <div class="page-customer-public pt-16">
    <div class="bold fs-24 space-between px-30 pb-10 header">
      <div class="flex-middle">
        <span>客户列表</span>
        <div class="line mx-10"></div>
        <el-radio-group v-model="listType">
          <el-radio-button :label="1">我的客户</el-radio-button>
          <el-radio-button :label="2">团队客户</el-radio-button>
        </el-radio-group>
        <template v-if="listType===2">
          <el-select class="ml-10" v-model="userId" placeholder="选择部门或业务员" clearable>
            <el-option
                v-for="item in indexOpt.teamMemberOption"
                :key="item.userId"
                :value="item.userId"
                :label="item.nickName">

            </el-option>
          </el-select>
        </template>
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
                        :class="{'active':item.id === curMenuActive}"
                        :key="item.id"
                        v-if="!item.children.length"
                        @click="curMenuActive = item.id"
                    >
                      <span>{{ item.name }}</span>
                      <span>{{ item.customerCount }}</span>

                    </div>
                    <div class="pt-4" v-else>
                      <el-collapse-item :key="item.id" :name="item.id">
                        <template #title>
                          <div class="pl-6 fs-14">
                            {{ item.name }}
                            <!--                            <i class="header-icon el-icon-info"></i>-->
                          </div>

                        </template>
                        <div
                            class="menu-item flex-middle space-between my-4 pl-20 pr-10"
                            :class="{'active':subItem.id === curMenuActive}"
                            v-for="subItem in item.children"
                            :key="subItem.id"
                            @click="curMenuActive = subItem.id">
                          <span>{{ subItem.name }}</span>
                          <span>{{ subItem.customerCount }}</span>
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
            <TableList
                ref="tableListRef"
                :params="{listType:listType,segmentId:curMenuActive,userId:userId}"
                :index-opt="indexOpt"/>
          </div>

        </template>
      </split-pane>

    </div>
    <CreateCustomerDrawer :visible.sync="customerVisible" :index-opt="indexOpt" @load="reloadList"/>
  </div>
</template>

<script>
import FilterDrawer from './FilterDrawer.vue'
import CellOperate from './CellOperate.vue'
import HeaderFilter from './HeaderFilter.vue'
import TableList from './TableList.vue'
import CreateCustomerDrawer from "./CreateCustomerDrawer.vue";
import {targetBlank} from "@/utils/tools";
import {packetList} from "@/api/company/group";
import {stageList} from "@/api/company/status";
import {getOriginList} from "@/api/company/origin";
import {reasonList} from "@/api/company/poolRule";
import {getPrivateSegmentMenu, getTeamMembers, searchGroupsCustomer} from "@/api/customer/publicleads";
import TableRowDrawer from "@/views/customer/list/TableRowDrawer.vue";
import {getCustomerTagList} from "@/api/customer/config";
import {listDeptUsersTree} from "@/api/system/dept";

export default {
  components: {
    TableRowDrawer,
    FilterDrawer,
    CellOperate,
    HeaderFilter,
    TableList,
    CreateCustomerDrawer
  },
  data() {
    return {
      listType: 1,
      userId: null,
      collapsed: false,
      percent: 16,
      curMenuActive: null,
      activeNames: ['group'],
      menuList: [],
      menuOptions: [],
      customerVisible: false,
      indexOpt: {
        groupOption: [],
        stageOption: [],
        originOption: [],
        poolGroupOption: [],
        poolReasonOption: [],
        tagOption: [],
        teamMemberOption: [],
      }

    }
  },
  mounted() {
    this.init()
  },
  methods: {
    init() {
      this.getMenuList()
      this.getGroupList()
      this.getStageList()
      this.getOriginList()
      this.getPoolList()
      this.getPoolReasonList()
      this.getTagList()
      this.getMemberList()
      this.getCommonTree()
    },
    // 菜单列表
    async getMenuList() {
      try {
        const res = await getPrivateSegmentMenu()
        if (res.code === 200) {
          this.menuList = res.data
          if (this.menuList.length) {
            this.curMenuActive = this.menuList[0].id
          }
        }
      } catch {
      }
    },
    // 分组选项
    async getGroupList() {
      try {
        const res = await packetList()
        if (res.code === 200) {
          this.indexOpt.groupOption = res.data
        }
      } catch {
      }
    },
    // 阶段选项
    async getStageList() {
      try {
        const res = await stageList()
        if (res.code === 200) {
          this.indexOpt.stageOption = res.data
        }
      } catch (e) {
      }
    },
    // 来源选项
    async getOriginList() {
      try {
        const res = await getOriginList()
        if (res.code === 200) {
          this.indexOpt.originOption = res.data
        }
      } catch {
      }
    },
    // 公海分组选项
    async getPoolList() {
      try {
        const res = await searchGroupsCustomer()
        if (res.code === 200) {
          this.indexOpt.poolGroupOption = res.data
        }
      } catch {
      }
    },
    // 移入公海原因
    async getPoolReasonList() {
      try {
        const res = await reasonList()
        if (res.code === 200) {
          this.indexOpt.poolReasonOption = res.data
        }
      } catch {

      }
    },
    // 客户标签选项
    async getTagList() {
      try {
        const res = await getCustomerTagList()
        if (res.code === 200) {
          this.indexOpt.tagOption = res.data
        }
      } catch {
      }
    },
    // 获取选择的成员
    async getMemberList() {
      try {
        const res = await getTeamMembers()
        if (res.code === 200) {
          this.indexOpt.teamMemberOption = res.data
        }
      } catch {
      }
    },
    // 获取用户列表
    async getCommonTree() {
      try {
        const res = await listDeptUsersTree()
        if (res.code === 200) {
          this.indexOpt.memberOption = res.data
        }
      } catch {
      }
    },
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
    // 创建客户回调
    reloadList() {
      this.getMenuList()
      this.$refs.tableListRef.reloadList()
      this.customerVisible = false
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
