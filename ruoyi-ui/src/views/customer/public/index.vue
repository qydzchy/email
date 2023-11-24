<template>
  <div class="page-customer-public pt-16">
    <div class="line flex-middle space-between py-10 px-20">
      <div class="bold fs-24 pb-4">
        公海客户
      </div>
      <el-dropdown trigger="click" split-button type="primary" round @click="onShowDrawer" @command="handleCommand">
        <span>新建客户</span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="import">导入客户</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <TableList ref="tableListRef" :index-opt="indexOpt"/>
    <CreatePublicCustomerDrawer :visible.sync="publicDrawerVisible" :index-opt="indexOpt" @load="reloadList"/>

  </div>
</template>

<script>
import CreatePublicCustomerDrawer from "./CreateCustomerDrawer.vue";
import TableRowDrawer from "@/views/customer/list/TableRowDrawer.vue";
import TableList from './TableList.vue'
import {targetBlank} from "@/utils/tools";
import {editFocusFlagCustomer, getPrivateSegmentMenu, searchGroupsCustomer} from "@/api/customer/publicleads";
import {packetList} from "@/api/company/group";
import {stageList} from "@/api/company/status";
import {getOriginList} from "@/api/company/origin";
import {reasonList} from "@/api/company/poolRule";
import {getCustomerTagList} from "@/api/customer/config";

export default {
  components: {TableRowDrawer, CreatePublicCustomerDrawer, TableList},
  data() {
    return {
      menuOptions: [],
      searchQuery: {
        group: '',
        parentId: null,
      },
      publicDrawerVisible: false,
      indexOpt: {
        groupOption: [],
        stageOption: [],
        originOption: [],
        poolGroupOption: [],
        poolReasonOption: [],
        privateOption: [],
      },

    }
  },
  mounted() {
    this.getGroupList()
    this.getStageList()
    this.getOriginList()
    this.getPoolList()
    this.getPoolReasonList()
    this.getTagList()
    // this.getMenuList()
  },
  methods: {
    async getGroupList() {
      try {
        const res = await packetList()
        if (res.code === 200) {
          this.indexOpt.groupOption = res.data
        }
      } catch {
      }
    },
    async getStageList() {
      try {
        const res = await stageList()
        if (res.code === 200) {
          this.indexOpt.stageOption = res.data
        }
      } catch (e) {
      }
    },
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
    // 私海菜单列表
    // async getMenuList() {
    //   try {
    //     const res = await getPrivateSegmentMenu()
    //     if (res.code === 200) {
    //       this.indexOpt.privateOption = res.data
    //     }
    //   } catch {
    //   }
    // },
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
    async onCollageIcon(id, scope) {
      try {
        const res = await editFocusFlagCustomer({id})
        if (res.code === 200) {
          const focusFlog = !this.list[scope.$index].focusFlag
          this.$message.success(focusFlog ? '关注成功' : '取消关注成功')
          this.$set(this.list, scope.$index, {...scope.row, focusFlag: focusFlog})
        }
      } catch {
      }

    },
    onCellClick(row) {
      this.rowDrawerData = row
      this.rowDrawerVisible = true
    },
    onCellEdit(rowId, field) {
      this.tableCell.tempValue = this.list.find(val => val.id === rowId)[field]
      this.tableCell.fieldName = field
      this.curEditId = rowId
    },
    onBlur() {
      this.confirmInput()
    },
    onInput(value, scope, field) {
      this.$set(this.list, scope.$index, {...scope.row, [field]: value})
    },
    onShowDrawer() {
      this.publicDrawerVisible = true
    },
    handleCommand(command) {
      if (command === 'import') {
        targetBlank('/customer/config/import-operate')
      }
    },
    // 创建客户回调
    reloadList() {
      this.$refs.tableListRef.reloadList()
      this.publicDrawerVisible = false
    },
  }
}
</script>

<style lang="scss" scoped>
.page-customer-public {
  .line {
    border-bottom: 1px solid #e6e6e6;
  }

  .table-list {
    ::v-deep .follow-cell {
      > .cell {
        padding: 0;
      }

      .follow-icon > i {
        &:hover {
          color: red;
        }
      }

      .follow-icon-active > svg {
        color: red;

        &:hover {
          color: unset;
        }
      }
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

}
</style>
