<template>
  <div>
    <div class="wrap pt-10  px-20 flex-middle space-between">
      <div>共 {{ paginateOption.total }} 个客户</div>
    </div>
    <div class="table-list mt-20">
      <TableNext :list="list" :columns="columns" :extra-option="{height:'68vh'}" :paginate-option="paginateOption"/>
    </div>
    <TableRowDrawer
        :row="rowDrawerData"
        :visible.sync="rowDrawerVisible"
        :externalOpt="{groupOption:indexOpt.groupOption}"
        @load="reloadList"
    />
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import TableRowDrawer from "./TableRowDrawer.vue";
import CollageIcon from "@/views/components/Customer/CollageIcon.vue";
import CellOperate from './CellOperate.vue'
import {EmptyStr} from "@/utils/tools";
import {getPublicLeadsList} from "@/api/customer/publicleads";

export default {
  props: {
    indexOpt: {
      type: Object,
      default: () => {
        return {}
      },
      required: false
    }
  },
  components: {TableRowDrawer, TableNext},
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
          align: 'left',
          render: (row, field) => {
            return <div className="pointer" onClick={() => this.onShowTableRowDrawer(row)}>{field || '---'}</div>
          }
        },
        {
          label: '最近跟进',
          field: 'originalFollowUp',
          align: 'left',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '最近动态',
          field: 'recentActivity',
          align: 'left',
          render: (row, field, _scope) => {
            return EmptyStr(field)
          }
        }, {
          label: '原跟进人',
          field: 'originalFollowUp',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '国家地区',
          field: 'countryRegion',
          align: 'left',
          width: '240',
          render: (row, field, scope) => {
            const {rowId, fieldName, showEditIcon} = this.tableCell
            const propName = scope.column.property
            const isShow = showEditIcon && rowId === row?.id && fieldName === propName
            const isShowForm = this.curEditId === row?.id && fieldName === propName
            return <CellOperate
                showForm={isShowForm}
                type="country"
                curValue={field}
                text={field}
                visible={isShow}
                on={{
                  onEdit: () => this.onCellEdit(row?.id, propName),
                  click: () => this.onCellClick(row),
                  onBlur: () => this.onBlur(),
                }}
            >
            </CellOperate>
          }
        },
        {
          label: '客户类型',
          field: 'phone',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '客户评分',
          field: 'rating',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '最近联系时间',
          field: 'lastContactedAt',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '时区',
          field: 'timezone',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '社交平台',
          field: 'contact',
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
      // 行内容编辑
      tableCell: {
        rowId: '',
        showEditIcon: false,
        tempValue: '',
        fieldName: '',
      },
      rowDrawerData: {},//抽屉回显的数据
      rowDrawerVisible: false, //点击显示详情抽屉
    }
  },
  mounted() {
    this.getList()
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
          this.list = res.rows
          this.paginateOption.total = res.total
        }
      } catch {
      }
    },
    onShowTableRowDrawer(row) {
      this.rowDrawerData = {...row}
      this.rowDrawerVisible = true
    },
    reloadList() {
      this.getList()
    }
  }
}
</script>

<style lang="scss" scoped>

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

::v-deep .el-table__body {
  .el-table__cell {
    border-right: none;
  }
}

::v-deep .el-table-column--selection {
  border-right: none !important;
}

::v-deep .el-table__fixed-right {
  z-index: 0;
}


</style>
