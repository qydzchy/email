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
import CollageIcon from "@/views/components/Customer/CollageIcon.vue";
import {EmptyStr, targetBlank} from "@/utils/tools";

export default {
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
        total: 2,
        layout: 'total, prev, pager, next , sizes',
        pageSize: 20,
        pageSizes: [10, 20, 50, 100]
      },
      list: [{
        id: 1,
        companyName: '111',
        companyTag: '111',
        isFollow: true
      },
        {
          id: 2,
          companyName: '222',
          companyTag: '222',
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
              value={field}
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
              <div slot="content" className="pointer" onClick={(e) => this.jumpPersonalDetail(e)}>
                {field}
              </div>
            </CellOperate>
          }
        },
        {
          label: '客户标签',
          field: 'companyTag',
          align: 'left',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '客户分组',
          field: 'companyGroup',
          align: 'left',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '客户阶段',
          field: 'companyStage',
          align: 'left',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '主要联系人',
          field: 'companyStage',
          align: 'left',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '最近动态',
          field: 'new',
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
              value={field}
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
              <div slot="content" class="pointer" onClick={(e) => this.jumpPersonalDetail(e)}>
                {field}
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
    onCollageIcon(id) {
      this.list.map(val => {
        if (val.id === id) {
          val.isFollow = !val.isFollow
        }
        return val
      })
    }
    ,
    onCellClick() {
      this.rowDrawerVisible = true
    }
    ,
    onCellEdit(rowId, field) {
      this.tableCell.tempValue = this.list.find(val => val.id === rowId)[field]
      this.tableCell.fieldName = field
      this.curEditId = rowId
    }
    ,
    onBlur() {
      this.confirmInput()
    }
    ,
    onInput(value, scope, field) {
      this.$set(this.list, scope.$index, {...scope.row, [field]: value})
    }
    ,
    inputEnter(scope) {
      this.confirmInput()
    }
    ,
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
    }
    ,
    jumpPersonalDetail(e) {
      e.stopPropagation()
      targetBlank('/customer/personal/1')
    }
    ,
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
