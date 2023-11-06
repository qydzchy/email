<template>
  <div class="table-list">
    <div class="mt-16" v-show="ids.length">
      <HeaderOperate :ids="ids"/>
    </div>
    <div class="mt-20">
      <TableNext
        :list="list"
        :columns="columns"
        :extra-option="extraOption"
        :extra-event="extraEvent"
        :paginate-option="paginateOption"/>
    </div>
    <TableRowDrawer :visible.sync="rowDrawerVisible"/>
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import TableRowDrawer from "./TableRowDrawer.vue";
import OperateMenu from './OperateMenu.vue'
import CellOperate from './CellOperate.vue'
import HeaderOperate from "./HeaderOperate.vue";
import {EmptyStr} from "@/utils/tools";

export default {
  components: {HeaderOperate, TableRowDrawer, TableNext, OperateMenu, CellOperate},
  data() {
    return {
      extraOption: {
        height: '66vh',
        border: true,
        defaultExpandAll: true
      },
      extraEvent: {
        'cell-mouse-enter': (row, column, cell) => this.onCellMouseEvent(row, column, cell),
        'cell-mouse-leave': (row) => this.onCellMouseLeave(row),
        'selection-change': (value) => this.handleSelectionChange(value)
      },
      paginateOption: {
        total: 0,
        layout: 'total, sizes, prev, pager, next',
        pageSize: 20,
        pageSizes: [10, 20, 50, 100]
      },
      list: [{
        id: 1,
        companyName: '111',
        nearly: '111',
        isFollow: true
      },
        {
          id: 2,
          companyName: '222',
          nearly: '222',
          isFollow: false
        }],
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
            const isShow = (fieldName === propName && rowId === row.id) || field
            return <div class={`follow-icon flex-miidle flex-center ${field && 'follow-icon-active'}`}>
              <el-tooltip placement="top" content={field ? '取消关注' : '关注'}>
                <svg-icon icon-class="like"  style={{display: isShow ? 'block' : 'none'}}/>
              </el-tooltip>
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
            return <div>
              {this.curEditId === row?.id && fieldName === propName
                ?
                <el-input
                  size="small"
                  value={field}
                  clearable
                  onInput={(value) => this.onInput(value, scope, 'companyName')}
                  on={{
                    blur: () => this.onBlur()
                  }}
                  nativeOnKeydown={(e) => this.inputKeydown(e, row)}/>
                : <CellOperate
                  text={field}
                  visible={isShow}
                  on={{
                    onEdit: () => this.onCellEdit(row?.id, 'companyName'),
                    click: () => this.onCellClick()
                  }}
                />}
            </div>
          }
        },
        {
          label: '最近跟进',
          field: 'nearly',
          align: 'left',
          width: '200',
          render: (row, field, scope) => {
            const {rowId, fieldName, showEditIcon} = this.tableCell
            const propName = scope.column.property
            const isShow = showEditIcon && rowId === row?.id && fieldName === propName
            return <div>
              {this.curEditId === row?.id && fieldName === propName
                ?
                <el-input
                  size="small"
                  value={field}
                  clearable
                  onInput={(value) => this.onInput(value, scope, propName)}
                  on={{
                    blur: () => this.onBlur()
                  }}
                  nativeOnKeydown={(e) => this.inputKeydown(e, row)}/>
                : <CellOperate
                  text={field}
                  visible={isShow}
                  on={{
                    onEdit: () => this.onCellEdit(row?.id, propName),
                    click: () => this.onCellClick()
                  }}
                />}
            </div>
          },
        }, {
          label: '最近动态',
          field: 'new',
          align: 'left',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '原跟进人',
          field: 'contactName',
          align: 'left',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '国家地区',
          field: 'email',
          align: 'left',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '客户类型',
          field: 'phone',
          align: 'left',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '客户评分',
          field: 'telOrigin',
          align: 'left',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '最近联系时间',
          field: 'area',
          align: 'left',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '时区',
          field: 'followMan',
          align: 'left',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '社交平台',
          field: 'department',
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
  methods: {
    onCellMouseEvent(row, column, cell) {
      if (!this.curEditId) {
        this.tableCell = {
          showEditIcon: true,
          rowId: row.id,
          fieldName: column.property
        }
      }

    },
    onCellMouseLeave(_value) {
      if (this.curEditId) {
        return
      }
      this.tableCell = {
        showEditIcon: false,
        rowId: '',
        fieldName: ''
      }
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
    inputKeydown(e, scope) {
      // 回车输入
      if (e.keyCode === 13) {
        this.confirmInput()
      }
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
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.postId)
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
