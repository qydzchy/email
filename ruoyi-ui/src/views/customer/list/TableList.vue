<template>
  <div>
    <div class="wrap pt-10 flex-middle space-between ml-30">
      <div>
        <span class="bold">全部客户</span>
        <span class="gray-text ml-2">{{ paginateOption.total }} 个客户</span>
      </div>
      <!--              <HeaderFilter/>-->
    </div>
    <div class="table-list mt-20">
      <div class="mt-16" v-show="ids.length">
        <HeaderOperate :ids="ids" @load="reloadList"/>
      </div>
      <div class="mt-20">
        <TableNext
            :loading="tableLoading"
            :list="list"
            :columns="columns"
            :extra-option="extraOption"
            :extra-event="extraEvent"
            :paginate-option="paginateOption"/>
      </div>
      <TableRowDrawer
          :visible.sync="rowDrawerVisible"
          :externalOpt="{groupOption:indexOpt.groupOption}"
      />
    </div>
  </div>

</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import TableRowDrawer from "./TableRowDrawer.vue";
import OperateMenu from './OperateMenu.vue'
import CellOperate from './CellOperate.vue'
import HeaderOperate from "./HeaderOperate.vue";
import CollageIcon from "@/views/components/Customer/CollageIcon.vue";
import {EmptyStr, targetBlank} from "@/utils/tools";
import {getPrivateLeadsList} from "@/api/customer/publicleads";
import {packetList} from "@/api/company/group";

export default {
  props: {
    segmentId: {
      type: Number | null,
      required: true
    },
    indexOpt: {
      type: Object,
      default: () => {
        return {}
      },
      required: false
    }
  },
  components: {HeaderOperate, TableRowDrawer, TableNext, OperateMenu, CellOperate, CollageIcon},
  data() {
    return {
      extraOption: {
        height: '66vh',
        border: true,
        defaultExpandAll: true
      },
      extraEvent: {
        'selection-change': (value) => this.handleSelectionChange(value)
      },
      paginateOption: {
        total: 0,
        layout: 'total, prev, pager, next , sizes',
        currentPage: 1,
        pageSize: 10,
        pageSizes: [10, 20, 50, 100],
      },
      list: [],
      columns: [
        {type: 'selection', width: '50'},
        {
          label: '',
          field: 'isFollow',
          fixed: 'left',
          align: 'left',
          width: '20',
          resizable: false,
          className: 'follow-cell',
          render: (row, field, scope) => {
            const {rowId, fieldName} = this.tableCell
            const propName = scope.column.property
            const isShow = (fieldName === propName && rowId === row?.id) || field
            return <div class={`follow-icon flex-miidle flex-center ${field && 'follow-icon-active'}`}>
              <CollageIcon
                  show={isShow}
                  onClick={() => this.onCollageIcon(row?.id)}>
              </CollageIcon>
            </div>

          }
        },
        {
          label: '公司名称',
          field: 'companyName',
          fixed: 'left',
          align: 'left',
          width: '200',
          sortable: true,
          render: (row, field, scope) => {
            const {rowId, fieldName, showEditIcon} = this.tableCell
            const propName = scope.column.property
            const isShow = showEditIcon && rowId === row?.id && fieldName === propName
            const isShowForm = this.curEditId === row?.id && fieldName === propName
            return <CellOperate
                showForm={isShowForm}
                type="input"
                curValue={field}
                text={field}
                visible={isShow}
                on={{
                  onEdit: () => this.onCellEdit(row?.id, propName),
                  click: () => this.onCellClick(),
                  onBlur: () => this.onBlur(),
                  onInput: (value) => this.onInput(value, scope, propName),
                  onEnter: () => this.inputEnter(row)
                }}
            >
              <div slot="content" class="pointer" onClick={(e) => this.jumpPersonalDetail(e, row?.id)}>
                {field}
              </div>
            </CellOperate>
          }
        },
        {
          label: '客户标签',
          field: 'tagList',
          align: 'left',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '客户分组',
          field: 'packetId',
          align: 'left',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '客户阶段',
          field: 'stageId',
          align: 'left',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '主要联系人',
          field: 'primaryContact',
          align: 'left',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '最近动态',
          field: 'recentActivity',
          align: 'left',
          width: '200',
          render: (row, field, scope) => {
            const {rowId, fieldName, showEditIcon} = this.tableCell
            const propName = scope.column.property
            const isShow = showEditIcon && rowId === row?.id && fieldName === propName
            const isShowForm = this.curEditId === row?.id && fieldName === propName
            return <CellOperate
                showForm={isShowForm}
                type="input"
                curValue={field}
                text={field}
                visible={isShow}
                on={{
                  onEdit: () => this.onCellEdit(row?.id, propName),
                  click: () => this.onCellClick(),
                  onBlur: () => this.onBlur(),
                  onInput: (value) => this.onInput(value, scope, propName),
                  onEnter: () => this.inputEnter(row)
                }}
            >
              <div slot="content" class="pointer" onClick={(e) => this.jumpPersonalDetail(e, row?.id)}>
                {field || '---'}
              </div>
            </CellOperate>
          }
        },
        {
          label: '原跟进人',
          field: 'contactName',
          align: 'left',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        }, {
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
                  click: () => this.onCellClick(),
                  onBlur: () => this.onBlur(),
                }}
            >
            </CellOperate>
          }
        }, {
          label: '客户类型',
          field: 'phone',
          align: 'left',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '客户评分',
          field: 'rating',
          align: 'left',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '最近联系时间',
          field: 'lastContactedAt',
          align: 'left',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '时区',
          field: 'timeZone',
          align: 'left',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '社交平台',
          field: 'socialPlatform',
          align: 'left',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '操作',
          field: 'operate',
          fixed: 'right',
          render: (row, _field) => {
            return <OperateMenu row={row}>
              <i class="operate-more pointer el-icon-more-outline" style="transform: rotate(90deg)"></i>
            </OperateMenu>
          }
        }
      ],
      tableLoading: false,
      // 行内容编辑
      tableCell: {
        rowId: '',
        showEditIcon: false,
        tempValue: '',
        fieldName: '',
      },
      curEditId: '',
      rowDrawerVisible: false, //点击显示详情抽屉
      ids: [],
    }
  },
  watch: {
    segmentId: {
      handler(newVal) {
        if (newVal) {
          this.getList(newVal)
        }
      }
    }
  },
  mounted() {
  },
  methods: {
    async getList(segmentId) {
      this.tableLoading = true
      try {
        const {currentPage, pageSize} = this.paginateOption
        const res = await getPrivateLeadsList({
          segmentId: segmentId,
          pageNum: currentPage,
          pageSize: pageSize
        }).finally(() => {
          this.tableLoading = false
        })
        if (res.code === 200) {
          this.list = res.rows
          this.paginateOption.total = res.total
          this.list.map((val) => {
            val.countryRegion = val.countryRegion.split('/')
            return val
          })

        }
      } catch {
      }
    },
    onCollageIcon(id) {
      this.list.map(val => {
        if (val.id === id) {
          val.isFollow = !val.isFollow
        }
        return val
      })
    },
    onCellClick() {
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
    inputEnter(scope) {
      this.confirmInput()
    },
    confirmInput() {
      // const newVal = this.list.find(val => val.id === this.curEditId)[this.tableCell.fieldName]
      this.curEditId = ''
      this.tableCell = {
        rowId: '',
        columnId: '',
        showEditIcon: false,
        tempValue: '',
        fieldName: '',
      }
    },
    jumpPersonalDetail(e, id) {
      e.stopPropagation()
      targetBlank('/customer/personal/' + id)
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.postId)
    },
    reloadList() {
      this.getList(this.segmentId)
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
