<template>
  <div>
    <div class="wrap pt-10  px-20 flex-middle space-between">
      <div>
        <template v-if="!ids.length">
          共 {{ paginateOption.total }} 个客户
        </template>
        <template v-else>
          <span>已选择 {{ ids.length }} 个客户</span>
        </template>
      </div>
      <div>
        <el-select placeholder="全部公海分组" v-model="searchQuery.pool" clearable @change="handleSearch">
          <el-option v-for="(item, index) in indexOpt.poolGroupOption" :key="index" :value="item.id" :label="item.name">
          </el-option>
        </el-select>
        <el-select-tree class="ml-10" v-model="searchQuery.group" placeholder="全部分组" :data="indexOpt.groupOption"
          :props="{ value: 'id', label: 'name' }" :default-expand-all="true" multiple collapse-tags clearable
          :check-strictly="true" @change="handleSearch">
        </el-select-tree>
        &nbsp;&nbsp;&nbsp;
        <span class="okki-badge okki-badge-status" data-v-158b5110="" style="cursor: pointer;" @click="search">
              <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" spin="false"
                   rtl="false" viewBox="0 0 48 48" preserveAspectRatio="xMidYMid meet" fill=""
                   role="presentation" data-v-9e8d377d="" data-v-158b5110="">
                      <g data-v-9e8d377d="">
                          <path fill-rule="evenodd" fill="#FFF" fill-opacity=".01" d="M0 0h48v48H0z"
                                data-v-9e8d377d="">
                          </path>
                          <path stroke-linejoin="round" fill="none" stroke-width="4" d="m6 9 14.4 16.818v12.626L27.6 42V25.818L42 9z"
                                data-follow-stroke="currentColor" data-v-9e8d377d="" stroke="currentColor">
                          </path>
                      </g>
              </svg>
          </span>
        <el-button round class="ml-10" @click="moveToGroupVisible = true">移动到</el-button>
        <el-button round @click="moveToPrivateLeadsVisible = true">移入私海</el-button>
      </div>
    </div>
    <div class="table-list mt-20">
      <TableNext :loading="tableLoading" :list="list" :columns="columns" :extra-option="{ height: '68vh' }"
        :extra-event="extraEvent" :paginate-option="paginateOption" :paginate-event="paginateEvent" />
    </div>
    <TableRowDrawer :row="rowDrawerData" :visible.sync="rowDrawerVisible" :indexOpt="indexOpt" @load="reloadList" />
    <DialogMoveToGroup :visible.sync="moveToGroupVisible" :row="{ id: ids }" :groupOption="indexOpt.groupOption" />
    <DialogMoveToPrivateLeads :visible.sync="moveToPrivateLeadsVisible" :privateOption="indexOpt.groupOption"
      :row="{ id: ids }" @onConfirm="reloadList" />

    <CustomerSearch ref="customerSearch" @customerFilter="advancedSearchFilter"/>
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import TableRowDrawer from "./TableRowDrawer.vue";
import CollageIcon from "@/views/components/Customer/CollageIcon.vue";
import CellOperate from './CellOperate.vue'
import DialogMoveToGroup from "./DialogMoveToGroup.vue";
import DialogMoveToPrivateLeads from "./DialogMoveToPrivateLeads.vue";
import { EmptyStr, targetBlank } from "@/utils/tools";
import { getPublicLeadsList } from "@/api/customer/publicleads";
import CustomerSearch from "./CustomerSearch.vue";

export default {
  props: {
    indexOpt: {
      type: Object,
      default: () => {
        return {
          poolGroupOption: []
        }
      },
      required: false
    }
  },
  components: {CustomerSearch, TableRowDrawer, TableNext, DialogMoveToGroup, DialogMoveToPrivateLeads },
  data() {
    return {
      advancedSearchFormData: {},
      list: [],
      columns: [
        { type: 'selection', width: '50' },
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
          width: '160',
          render: (row, field) => {
            return <div class="flex-start" style="width:100%;" onClick={() => this.onShowTableRowDrawer(row)}>
              <span
                class="pointer highlight"
                onClick={(e) => {
                  e.stopPropagation();
                  this.jumpDetail(row.id)
                }}>
                {field || '---'}
                <i class="link-icon el-icon-link pl-6"></i>
              </span>
            </div>
          }
        },
        // {
        //   label: '简称',
        //   field: 'shortName',
        //   align: 'left',
        //   width: '200',
        //   render: (_row, field) => EmptyStr(field),
        // },
        // {
        //   label: '客户标签',
        //   field: 'tagList',
        //   align: 'left',
        //   width: '200',
        //   render: (_row, field, _scope) => {
        //     const wrap = () => {
        //       return field && field.length ? field?.map((val, idx) => {
        //         const colorOpacity = val.color ? val.color + '4d' : ''
        //         return val.name ?
        //             <span title={val.name} key={idx} class="px-6 py-6 mx-2"
        //                   style={{color: val.color, backgroundColor: colorOpacity}}>
        //             {val.name}
        //           </span> : '---'
        //       }) : '---'
        //     }
        //     return <el-popover trigger="hover">
        //                 <div slot="default">
        //                   {wrap()}
        //                 </div>
        //                 <div
        //                   slot="reference"
        //                   class="line-clamp1"
        //                   style="width:144px;height:20px;"
        //                   onClick={(e) => e.stopPropagation()}>
        //                   {wrap()}
        //                 </div>
        //           </el-popover>
        //   }
        // },
        {
          label: '最近跟进',
          field: 'recentFollowUp',
          align: 'left',
          width: '200',
          render: (row, field, _scope) => {
            return <CellOperate
              text={field?.followUpContent}
              showEditIcon={false}
              showCopyIcon={false}
            >
              <div slot="content" class="pointer">
                {field?.followUpContent || '---'}
              </div>
            </CellOperate>
          }
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
            >
              <div slot="content" class="pointer">
                {field?.followUpContent || '---'}
              </div>
            </CellOperate>
          }
        },
        {
          label: '原跟进人',
          field: 'originalFollowUp',
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
          render: (_row, field) => {
            return <CellOperate
              type="country"
              curValue={field}
              text={field}
              showEditIcon={false}
              showCopyIcon={false}
            >
            </CellOperate>
          }
        },
        {
          label: '客户类型',
          field: 'customerType',
          width: '120',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '客户评分',
          field: 'customerScore',
          width: '120',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '最近联系时间',
          field: 'lastContactedAt',
          align: 'left',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '时区',
          field: 'timezone',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '社交平台',
          field: 'contact',
          width: '200',
          render: (_row, field) => {
            let content = field?.socialPlatform || '[]'
            let socialPlatformList = JSON.parse(content)
            return Array.isArray(socialPlatformList) ? socialPlatformList.map((val, index) => {
              return <div key={index}>{`${val.type || '---'} ${val.account || '---'}`}</div>
            }) : '---'
          },
        },
        {
          label: '生日',
          field: 'birthday',
          width: '200',
          render: (row, _field) => <div>{row.contact?.birthday || '---'}</div>,
        },
        // {
        //   label: '客户分组',
        //   field: 'packetId',
        //   align: 'left',
        //   width: '200',
        //   render: (_row, field) => {
        //     return this.formatGroup(field) || '---'
        //   }
        // },
        // {
        //   label: '客户阶段',
        //   field: 'stageId',
        //   align: 'left',
        //   width: '200',
        //   render: (_row, field) => {
        //     const {name, color} = this.formatStage(field)
        //     return <span class="px-6 py-6 radius-8"
        //                  style={{backgroundColor: color || '#c7bfbf', color: '#fffffa'}}>{name || '无'}</span>
        //   }
        // },
        // {
        //   label: '主要联系人',
        //   field: 'primaryContact',
        //   align: 'left',
        //   width: '200',
        //   render: (_row, field) => EmptyStr(field),
        // },
        // {
        //   label: '客户编号',
        //   field: 'customerNo',
        //   align: 'left',
        //   width: '160',
        //   render: (_row, field) => EmptyStr(field),
        // },

        {
          label: '创建时间',
          field: 'createTime',
          align: 'left',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        },
        // {
        //   label: '星级',
        //   field: 'rating',
        //   width: '200',
        //   render:(_row,field)=>{
        //     return <CellOperate
        //               curValue={field}
        //               showForm={true}
        //               text={field}
        //               type="rate"
        //               showCopyIcon={false}
        //               on={{
        //                 onChange:(value)=>{}
        //               }}>
        //             </CellOperate>
        //   }
        // }
      ],
      extraEvent: {
        'selection-change': (value) => this.handleSelectionChange(value)
      },
      paginateOption: {
        total: 0,
        layout: 'total, sizes, prev, pager, next',
        currentPage: 1,
        pageSize: 20,
        pageSizes: [10, 20, 50, 100]
      },
      paginateEvent: {
        'size-change': (value) => this.handlePagination('pageSize', value),
        'current-change': (value) => this.handlePagination('currentPage', value)
      },
      tableLoading: false,
      // 行内容编辑
      tableCell: {
        rowId: '',
        showEditIcon: false,
        tempValue: '',
        fieldName: '',
      },
      rowDrawerData: {},//抽屉回显的数据
      rowDrawerVisible: false, //点击显示详情抽屉
      ids: [],
      searchQuery: {
        pool: '',
        group: ''
      },
      moveToGroupVisible: false,
      moveToPrivateLeadsVisible: false,
      noReq: false,
    }
  },
  watch: {
    'indexOpt.poolGroupOption': {
      handler(newVal) {
        if (!newVal.length) {
          this.noReq = true
        } else {
          this.noReq = false
          this.getList()
        }
      },
      deep: true,
      immediate: true,
    }
  },
  mounted() {

  },
  methods: {
    async getList() {
      if (this.noReq) {
        return
      }
      this.tableLoading = true
      try {
        const { currentPage, pageSize } = this.paginateOption;
        this.advancedSearchFormData.publicleadsGroupsId = this.searchQuery.pool;
        if (this.searchQuery.group && this.searchQuery.group.trim() !== '') {
          this.advancedSearchFormData.packetIdList = this.searchQuery.group;
        }
        this.advancedSearchFormData.pageNum = currentPage;
        this.advancedSearchFormData.pageSize = pageSize;
        const res = await getPublicLeadsList(this.advancedSearchFormData).finally(() => {
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
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
    },
    handlePagination(type, value) {
      this.paginateOption = { ...this.paginateOption, [type]: value }
      this.getList()
    },
    handleSearch() {
      this.getList()
    },
    onShowTableRowDrawer(row) {
      this.rowDrawerData = { ...row }
      this.rowDrawerVisible = true
    },
    jumpDetail(id) {
      targetBlank('/customer/public/personal/' + id)
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
    reloadList() {
      setTimeout(() => {
        this.getList()
      }, 400)
    },

    search() {
      this.$refs.customerSearch.openDrawer();
    },

    advancedSearchFilter(formData) {
      this.advancedSearchFormData = formData;
      this.getList();
    }
  }
}
</script>

<style lang="scss" scoped>
.table-list {
  ::v-deep .follow-cell {
    >.cell {
      padding: 0;
    }

    .follow-icon>i {
      &:hover {
        color: red;
      }
    }

    .follow-icon-active>svg {
      color: red;

      &:hover {
        color: unset;
      }
    }
  }

  ::v-deep .highlight {
    display: flex;
    align-items: center;
    width: max-content;

    .link-icon {
      display: none;
    }

    &:hover {
      color: #0a6aff;

      .link-icon {
        display: block;
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
