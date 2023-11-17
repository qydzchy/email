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
    <div class="wrap pt-10  px-20 flex-middle space-between">
      <div>共 {{ paginateOption.total }} 个客户</div>
      <!--      <div class="search-group flex-middle gap-8">-->
      <!--        <el-select style="width:200px" placeholder="请选择" v-model="searchQuery.group"></el-select>-->
      <!--        <TreeSelect-->
      <!--            style="width: 200px"-->
      <!--            v-model="searchQuery.parentId"-->
      <!--            :options="menuOptions"-->
      <!--            :normalizer="normalizer"-->
      <!--            :show-count="true"-->
      <!--            :multiple="true"-->
      <!--            placeholder="全部分组"-->
      <!--        />-->
      <!--      </div>-->
    </div>
    <div class="mt-20">
      <TableNext :list="list" :columns="columns" :extra-option="{height:'70vh'}" :paginate-option="paginateOption"/>
    </div>
    <CreatePublicCustomerDrawer :visible.sync="publicDrawerVisible" :index-opt="indexOpt"/>
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import TreeSelect from "@riophae/vue-treeselect";
import CreatePublicCustomerDrawer from "./CreateCustomerDrawer.vue";
import {EmptyStr, targetBlank} from "@/utils/tools";
import {getPublicLeadsList} from "@/api/customer/publicleads";
import {packetList} from "@/api/company/group";
import {stageList} from "@/api/company/status";
import {getOriginList} from "@/api/company/origin";

export default {
  components: {TreeSelect, TableNext, CreatePublicCustomerDrawer},
  data() {
    return {
      list: [],
      columns: [
        {type: 'selection', width: '50'},
        {
          label: '',
          field: 'focusFlag',
          fixed: 'left',
          align: 'left',
          width: '20',
          resizable: false,
          className: 'follow-cell',
          render: (row, field, scope) => {
            return <div class={`follow-icon flex-miidle flex-center ${field && 'follow-icon-active'}`}>
              <CollageIcon
                  show={!!field}
                  onClick={() => this.onCollageIcon(row?.id, scope)}>
              </CollageIcon>
            </div>

          }
        },
        {
          label: '公司名称',
          field: 'companyName',
          fixed: 'left',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '最近跟进',
          field: 'nearly',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '最近动态',
          field: 'companyName',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '原跟进人',
          field: 'contactName',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '国家地区',
          field: 'email',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '客户类型',
          field: 'phone',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '客户评分',
          field: 'telOrigin',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '最近联系时间',
          field: 'area',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '时区',
          field: 'followMan',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '社交平台',
          field: 'department',
          render: (_row, field) => EmptyStr(field),
        }
      ],
      paginateOption: {
        total: 0,
        layout: 'total, sizes, prev, pager, next',
        currentPage: 1,
        pageSize: 20,
        pageSizes: [10, 20, 50, 100]
      },
      menuOptions: [],
      searchQuery: {
        group: '',
        parentId: null,
      },
      publicDrawerVisible: false,
      indexOpt: {
        groupOption: [],
        stageOption: [],
        originOption: []
      }
    }
  },
  mounted() {
    this.getList()
    this.getGroupList()
    this.getStageList()
    this.getOriginList()
  },
  methods: {
    async getList() {
      this.tableLoading = true
      try {
        const {currentPage, pageSize} = this.paginateOption
        const res = await getPublicLeadsList({
          pageNum: currentPage,
          pageSize: pageSize
        }).finally(() => {
          this.tableLoading = false
        })
        if (res.code === 200) {
          this.list = res.data
        }
      } catch {
      }
    },
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
    onShowDrawer() {
      this.publicDrawerVisible = true
    },
    handleCommand(command) {
      if (command === 'import') {
        targetBlank('/customer/config/import-operate')
      }
    },
  }
}
</script>

<style lang="scss" scoped>
.page-customer-public {
  .line {
    border-bottom: 1px solid #e6e6e6;
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
