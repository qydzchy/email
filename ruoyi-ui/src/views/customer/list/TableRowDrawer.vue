<template>
  <div>
    <div class="table-row-drawer">
      <el-drawer :visible.sync="visible" :modal="false" :wrapperClosable="false" destroy-on-close :show-close="false"
                 @close="onHideDrawer">
        <template #title>
          <div class="header flex-middle space-between py-20 px-20">
            <div>
              <i class="pointer el-icon-right" @click="onHideDrawer"></i>
            </div>
            <el-row class="flex-middle">
              <el-row class="flex-middle gap-16">
                <CollageIcon :show="focusFlag" :default-hide="false" @click="onCollageIcon"/>
                <el-tooltip placement="top" content="写邮件">
                  <i class="el-icon-message pointer"></i>
                </el-tooltip>
                <el-tooltip placement="top" content="编辑">
                  <i class="el-icon-edit pointer" @click="editVisible = true"></i>
                </el-tooltip>
              </el-row>

              <el-dropdown trigger="click" class="ml-10">
             <span>
               <el-tooltip placement="left" content="更多操作">
                <i class="operate-more pointer el-icon-more-outline" style="transform: rotate(90deg)"></i>
              </el-tooltip>

            </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item>取消跟进</el-dropdown-item>
                  <el-dropdown-item>移入公海</el-dropdown-item>
                  <el-dropdown-item>重新分配</el-dropdown-item>
                  <el-dropdown-item>共享客户</el-dropdown-item>
                  <el-dropdown-item>合并客户</el-dropdown-item>
                  <!--                  <el-dropdown-item>新增报价单</el-dropdown-item>-->
                </el-dropdown-menu>
              </el-dropdown>
            </el-row>
          </div>
        </template>
        <div class="container">
          <div class="base-info flex-start px-30">
            <el-avatar shape="square" :src="rowData.companyLogo"></el-avatar>

            <div class="pl-10 fs-14">
              <label>{{ rowData.companyName }}</label>
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
            <TableRowTabs :row="rowData" :options="options"/>
          </div>
        </div>
        <el-backtop target=".el-tabs__content" :visibility-height="100"/>
      </el-drawer>

    </div>
    <CreateCustomerDrawer :visible.sync="editVisible" :row.sync="rowData" @load="onHideCreateDrawer"/>
  </div>
</template>

<script>
import TableRowTabs from './TableRowTabs.vue'
import CreateCustomerDrawer from "./CreateCustomerDrawer.vue";
import CollageIcon from "@/views/components/Customer/CollageIcon.vue";
import {editFocusFlagCustomer, getCustomerDetail} from "@/api/customer/publicleads";

export default {
  props: {
    row: {
      type: Object,
      default: () => {
        return {
          focusFlag: false
        }
      },
      required: false
    },
    visible: {
      type: Boolean,
      default: false,
      required: false,
    },
    externalOpt: {
      type: Object,
      default: () => {
        return {
          groupOption: [],

        }
      }
    },
  },
  components: {
    TableRowTabs,
    CreateCustomerDrawer,
    CollageIcon
  },
  data() {
    return {
      editVisible: false,
      options: {
        isShowSchedule: true,
        isTabSetHeight: true,
        isShowInfo: true,
        groupOption: this.externalOpt.groupOption
      },
      focusFlag: false,
      rowData: {
        companyName: '',
        companyLogo: ''
      }
    }
  },
  watch: {
    row: {
      handler(newVal) {
        this.focusFlag = Boolean(newVal.focusFlag)
        if (newVal?.id) {
          this.getDetailData()
        }
      },
      deep: true,
      immediate: true,
    }
  },
  methods: {
    async getDetailData() {
      try {
        const res = await getCustomerDetail({
          id: this.row.id
        })
        if (res.code === 200) {
          this.rowData = res.data
          this.rowData.customerId = this.rowData.id
        }
      } catch (e) {
        console.error(e)
      }
    },
    async onCollageIcon(status) {
      if (!this.row.id) {
        return
      }
      try {
        const res = await editFocusFlagCustomer({id: this.row.id})
        if (res.code === 200) {
          this.focusFlag = !status
          this.$emit('load')
        }
      } catch {
      }
    },
    onHideDrawer() {
      this.rowData = {}
      this.$emit('update:visible', false)
    },
    onHideCreateDrawer() {
      this.editVisible = false
      setTimeout(() => {
        this.getDetailData()
        this.$emit('load')
      }, 400)
    },
  }

}
</script>

<style lang="scss" scoped>

.table-row-drawer {
  ::v-deep .el-drawer__wrapper {
    z-index: 2 !important;
  }

  ::v-deep .el-drawer.rtl {
    width: 600px !important;
  }


  ::v-deep .el-drawer__header {
    padding: 0;
  }

  .header {
    border-bottom: 1px solid #f0f0f0;
  }

  .container {
  }
}
</style>
