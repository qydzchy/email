<template>
  <div>
    <div class="wrap pt-10 flex-middle space-between ml-30">
      <div>
        <span class="bold">全部客户</span>
        <span class="gray-text ml-2">{{ paginateOption.total }} 个客户</span>
      </div>
      <!--      <HeaderFilter :index-opt="indexOpt" :query="searchQuery" @handleSearch="handleSearch"/>-->
    </div>
    <div class="table-list mt-20">
      <div class="mt-16" v-show="ids.length">
        <HeaderOperate :ids="ids"/>
      </div>
      <div class="mt-20">
        <TableNext
            :loading="tableLoading"
            :list="list"
            :columns="columns"
            :extra-option="extraOption"
            :extra-event="extraEvent"
            :paginate-option="paginateOption"
            :paginate-event="paginateEvent"/>
      </div>
      <TableRowDrawer
          :row="rowDrawerData"
          :visible.sync="rowDrawerVisible"
          @update:visible="onUpdateTableRowDrawer"
          :indexOpt="indexOpt"
          @load="reloadList"
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
import HeaderFilter from "./HeaderFilter.vue";
import CollageIcon from "@/views/components/Customer/CollageIcon.vue";
import PopoverCustomerTag from "./PopoverCustomerTag.vue";
import {EmptyStr, targetBlank} from "@/utils/tools";
import {editFocusFlagCustomer, getPrivateLeadsList,editCustomer} from "@/api/customer/publicleads";

const initCommandList =  ['follow', 'write', 'schedule', 'moveGroup', 'mergeCustomer', 'transfer', 'share', 'cancel', 'movePool', 'reassign', 'removeAndInto', 'changePoolGroup']
const groupCommandList = ['follow', 'write', 'schedule', 'moveGroup', 'mergeCustomer', 'share', 'cancel', 'movePool', 'reassign', 'removeAndInto', 'changePoolGroup']

export default {
  props: {
    params: {
      type: Object,
      default: () => {
        return {
          segmentId: null,
          listType: null,
          userId: null,
        }
      },
      required: true,
    },
    indexOpt: {
      type: Object,
      default: () => {
        return {}
      },
      required: false
    }
  },
  components: {HeaderOperate, TableRowDrawer, TableNext, OperateMenu, CellOperate, CollageIcon, HeaderFilter},
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
      paginateEvent: {
        'size-change': (value) => this.handlePagination('pageSize', value),
        'current-change': (value) => this.handlePagination('currentPage', value)
      },
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
          width: '200',
          sortable: true,
          className: 'text-hover',
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
                  click: () => this.onCellClick(row),
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
          label: '简称',
          field: 'shortName',
          align: 'left',
          width: '120',
          render: (row, field, scope) => {
            const {fieldName} = this.tableCell
            const propName = scope.column.property
            const isShowForm = this.curEditId === row?.id && fieldName === propName
            return <CellOperate
                    showForm={isShowForm}
                    type="input"
                    curValue={field}
                    text={field}
                    on={{
                      onEdit: () => this.onCellEdit(row?.id, propName),
                      click: () => this.onCellClick(row),
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
          label: '客户标签',
          field: 'tagList',
          align: 'left',
          width: '200',
          render: (row, field, scope) => {
            const {rowId, fieldName} = this.tableCell
            const propName = scope.column.property
            const isShowForm = this.curEditId === row?.id && fieldName === propName
            const wrap = () => {
              return field && field.length ? field?.map((val, idx) => {
                const colorOpacity = val.color ? val.color + '4d' : ''
                return val.name ?
                    <span title={val.name} key={idx} class="px-6 py-6 mx-2"
                          style={{color: val.color, backgroundColor: colorOpacity}}>
                    {val.name}
                  </span> : '---'
              }) : '---'
            }
            return <CellOperate
                type="customer-tag"
                text={field}
                showCopyIcon={false}
                showEditIcon={!isShowForm}
                on={{
                  onEdit: () => this.onCellEdit(row?.id, propName),
                  click: () => this.onCellClick(row),
                  onBlur: () => this.onBlur(),
                  onInput: (value) => this.onInput(value, scope, propName),
                  onEnter: () => this.inputEnter(row)
                }}
            >
            <template  slot="content">
                <PopoverCustomerTag 
                    id={rowId} 
                    visible={isShowForm} 
                    tagList={field} 
                    tagOption={this.indexOpt.tagOption} 
                    showBtn={true}
                    on={{
                      hide:()=> this.confirmInput(),
                      confirm:(value)=>this.tagPopoverConfirm(row,value),
                      reloadTag:()=>this.reloadTag()
                    }}>
                    <template slot="contentTag">
                      <el-popover trigger="hover">
                        <div slot="default">
                          {wrap()}
                        </div>
                        <div 
                          slot="reference" 
                          class="line-clamp1" 
                          style="width:144px;height:20px;" 
                          onClick={(e) => e.stopPropagation()}>
                          {wrap()}
                        </div>
                    </el-popover>
                    </template>
                </PopoverCustomerTag>
            </template>
            </CellOperate>
          },
        },
        {
          label: '客户分组',
          field: 'packetId',
          align: 'left',
          width: '200',
          render: (row, field, scope) => {
            const {fieldName} = this.tableCell
            const propName = scope.column.property
            const isShowForm = this.curEditId === row?.id && fieldName === propName
            return <CellOperate
                    showForm={isShowForm}
                    type="select"
                    curValue={field}
                    text={field}
                    formOption={{
                      options: this.indexOpt.groupOption,
                      props:{
                        value:'id',
                        label:'name'
                      }
                    }}
                    on={{
                      onEdit: () => this.onCellEdit(row?.id, propName),
                      onChange: (value) => this.onInput(value, scope, propName),
                      onBlur: () => this.onBlur(),
                    }}
                >
                </CellOperate>
          }
        },
        {
          label: '客户阶段',
          field: 'stageId',
          align: 'left',
          width: '200',
          render: (row, field, scope) => {
            const {name, color} = this.formatStage(field)
            const {fieldName} = this.tableCell
            const propName = scope.column.property
            const isShowForm = this.curEditId === row?.id && fieldName === propName
            return <CellOperate
                    showForm={isShowForm}
                    type="select"
                    curValue={field}
                    text={field}
                    formOption={{
                      options: this.indexOpt.stageOption,
                      props:{
                        value:'id',
                        label:'name'
                      }
                    }}
                    on={{
                      onEdit: () => this.onCellEdit(row?.id, propName),
                      onChange: (value) => this.onInput(value, scope, propName),
                      onBlur: () => this.onBlur(),
                    }}
                >
                <span 
                  slot="content" 
                  class="px-6 py-6 radius-8" 
                  style={{backgroundColor: color || '#c7bfbf', color: '#fffffa'}}
                >
                  {name || '无'}
                </span>
                </CellOperate>
          }
          // render: (_row, field) => {
          //   const {name, color} = this.formatStage(field)
          //   return <span class="px-6 py-6 radius-8"
          //                style={{backgroundColor: color || '#c7bfbf', color: '#fffffa'}}>{name || '无'}</span>
          // }
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
          render: (row, field, _scope) => {
            return <CellOperate
                text={field?.followUpContent}
                showEditIcon={false}
                showCopyIcon={false}
                on={{
                  click: () => this.onCellClick(row)
                }}
            >
              <div slot="content" class="pointer">
                {field?.followUpContent || '---'}
              </div>
            </CellOperate>
          }
        },
        {
          label: '原跟进人',
          field: 'contactName',
          align: 'left',
          width: '200',
          render: (row, _field) => {
            let operate = row?.recentActivity?.operator || '---'
            return <div>
              {operate}
            </div>
          },
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
                showCopyIcon={false}
                on={{
                  onEdit: () => this.onCellEdit(row?.id, propName),
                  click: () => this.onCellClick(row),
                  onChange: (value) => this.onInput(value, scope, propName),
                  onBlur: () => this.onCountryBlur(),
                }}
            >
            </CellOperate>
          }
        },
        {
          label: '客户编号',
          field: 'customerNo',
          align: 'left',
          width: '160',
          render: (_row, field) => EmptyStr(field),
        },
        // {
        //   label: '客户评分',
        //   field: 'rating',
        //   align: 'left',
        //   width: '200',
        //   render: (_row, field) => EmptyStr(field),
        // },
        {
          label: '最近联系时间',
          field: 'lastContactedAt',
          align: 'left',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '创建时间',
          field: 'createTime',
          align: 'left',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        },
        // {
        //   label: '时区',
        //   field: 'timeZone',
        //   align: 'left',
        //   width: '200',
        //   render: (_row, field) => EmptyStr(field),
        // },
        // {
        //   label: '社交平台',
        //   field: 'socialPlatform',
        //   align: 'left',
        //   render: (_row, field) => EmptyStr(field),
        // },
        {
          label: '操作',
          field: 'operate',
          fixed: 'right',
          render: (row, _field) => {
            let newRow = {...row, customerId: row.id}
            delete newRow.id
            return <OperateMenu row={newRow} indexOpt={this.indexOpt} commandList={this.commandList} on={{load: () => this.handleOperate()}}>
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
      rowDrawerData: {},//抽屉回显的数据
      ids: [],
      searchQuery: {
        columnName: '',
        value: ''
      },
      commandList: initCommandList,
    }
  },
  watch: {
    params: {
      handler(newVal) {
        if (newVal.segmentId) {
          this.getList()
        }
        this.commandList = newVal.listType === 1 ? initCommandList : groupCommandList
      },
      deep: true
    }
  },
  methods: {
    async getList() {
      this.tableLoading = true
      try {
        const {currentPage, pageSize} = this.paginateOption
        const res = await getPrivateLeadsList({
          segmentId: this.params.segmentId,
          type: this.params.listType,
          userId: this.params.userId,
          [this.searchQuery.columnName]: this.searchQuery.value,
          pageNum: currentPage,
          pageSize: pageSize
        }).finally(() => {
          this.tableLoading = false
        })
        if (res.code === 200) {
          this.list = res.rows.map((val) => {
            val.countryRegion = val.countryRegion?.split('/') || []
            return val
          })
          this.paginateOption.total = res.total

        }
      } catch {
      }
    },
    handleSearch(value) {
      console.log(value)
      this.searchQuery = value
      this.getList()
    },
    handleOperate() {
      this.reloadList()
      this.$emit('reloadMenu')
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
    editCustomer(data) {
      return new Promise(async resolve => {
        try {
          const res = await editCustomer({...data})
          if (res.code === 200) {
            this.$message.success('修改成功')
            resolve(true)
          }
          resolve(false)
        } catch {
          resolve(false)
        }
      })
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
    // 国家特殊处理
    onCountryBlur(){
      this.$nextTick(async()=>{
        const newVal = this.list.find(val => val.id === this.curEditId)
        let data = {
          id:this.curEditId,
          countryRegion: newVal[this.tableCell.fieldName].join('/')
        }
        const res = await this.editCustomer(data)
        if(!res) {
          let itemIdx = this.list.findIndex(val=>val.id===this.curEditId)
          this.$set(this.list, itemIdx, {...newVal, [this.tableCell.fieldName]: this.tableCell.tempValue})
        }
        this.clearTableCellData()
      })
    },
    onInput(value, scope, field) {
      this.$set(this.list, scope.$index, {...scope.row, [field]: value})
    },
    inputEnter(scope) {
      this.confirmInput()
    },
    async confirmInput() {
      const newVal = this.list.find(val => val.id === this.curEditId)
      if(newVal[this.tableCell.fieldName] !== this.tableCell.tempValue){
        let data = {
          id:this.curEditId,
          [this.tableCell.fieldName]: newVal[this.tableCell.fieldName]
        }
        
        const res = await this.editCustomer(data)
        if(!res) {
          let itemIdx = this.list.findIndex(val=>val.id===this.curEditId)
          this.$set(this.list, itemIdx, {...newVal, [this.tableCell.fieldName]: this.tableCell.tempValue})
        }
      }
      this.clearTableCellData()
    },
    clearTableCellData(){
      this.curEditId = ''
      this.tableCell = {
        rowId: '',
        columnId: '',
        showEditIcon: false,
        tempValue: '',
        fieldName: '',
      }
    },
    onUpdateTableRowDrawer(bool) {
      if (!bool) {
        this.rowDrawerData = {}
      }
    },
    jumpPersonalDetail(e, id) {
      e.stopPropagation()
      targetBlank('/customer/personal/' + id)
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
    },
    handlePagination(type, value) {
      this.paginateOption = {...this.paginateOption, [type]: value}
      this.getList()
    },
    formatGroup(id) {
      let groupName = ''
      this.indexOpt.groupOption.forEach(val => {
        if (val.id === id) {
          groupName = val.name
        }
      })
      return groupName
    },
    formatStage(id) {
      let stageRow = {}
      this.indexOpt.stageOption.forEach(val => {
        if (val.id === id) {
          stageRow = val
        }
      })
      return stageRow
    },
    tagPopoverConfirm(row,value){
      let data = {
        id:row.id,
        tagIds:value.map(val=>val.id)
      }
      this.editCustomer(data).then(res=>{
        if(res){
          this.confirmInput()
          setTimeout(()=>{
            this.getList()
          },400)
        }
      })
    },
    reloadList() {
      this.getList()
    },
    reloadTag(){
      this.$emit('reloadTag')
    },
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

  ::v-deep .text-hover {
    > .cell {
      .span-style:hover {
        color: #0a6aff;
      }
    }
  }

  ::v-deep .line-clamp1 {
    display: block;
    overflow: hidden;
    text-overflow: ellipsis;
    -o-text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;
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
