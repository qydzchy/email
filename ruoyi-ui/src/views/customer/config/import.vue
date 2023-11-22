<template>
  <div>
    <div class="my-20 flex-end">
      <el-row>
        <el-button type="text" @click="onRefresh">
          <span>刷新</span>
          <i class="el-icon-refresh"></i></el-button>
        <el-button type="primary" round @click="targetBlank('/customer/config/import-operate')">新增导入</el-button>
      </el-row>
    </div>
    <TableNext :loading="tableLoading" :columns="columns" :list="list" :paginate-option="paginationOption"
               :paginate-event="paginationEvent"/>
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import {EmptyStr, targetBlank} from "@/utils/tools";
import {getImportCustomerList} from "@/api/customer/config";

export default {
  components: {
    TableNext
  },
  data() {
    return {
      list: [],
      columns: [
        {
          label: '导入时间',
          field: 'createTime',
          fixed: 'left',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '导入类型',
          field: 'importType',
          render: (_row, field) => {
            const mapImportType = {
              1: '客户列表',
              2: '公海客户',
            }
            return <div>{mapImportType[field] || '---'}</div>
          },
        },
        {
          label: '导入状态',
          field: 'importStatus',
          render: (_row, field) => {
            const mapImportStatus = {
              1: '进行中',
              2: '成功',
              3: '失败',
            }
            return <div>{mapImportStatus[field] || '---'}</div>
          },
        },
        {
          label: '预计导入数',
          field: 'expectedImportCount',
          render: (_row, field) => <div>{field}</div>,
        },
        {
          label: '导入成功数',
          field: 'successImportCount',
          render: (_row, field) => <div>{field}</div>,
        },
        {
          label: '导入失败数',
          field: 'failedImportCount',
          render: (_row, field) => <div>{field}</div>,
        },
        {
          label: '操作人',
          field: 'createBy',
          render: (_row, field) => EmptyStr(field),
        },
      ],
      paginationOption: {
        total: 0,
        currentPage: 1,
        pageSize: 10,
        pageSizes: [10, 20, 50, 100],
        layout: 'total,prev,pager,next,sizes'
      },
      paginationEvent: {
        'size-change': this.handleSizeChange,
        'current-change': this.handleCurrentChange,
      },
      tableLoading: false,
    }
  },
  mounted() {
    const {current = 1, pageSize = 10} = this.$route.query
    this.generateRoute(current, pageSize)
    this.paginationOption = {
      ...this.paginationOption,
      currentPage: current,
      pageSize: pageSize
    }
    this.getList()
  },
  methods: {
    async getList() {
      try {
        this.tableLoading = true
        const {currentPage, pageSize} = this.paginationOption
        const res = await getImportCustomerList({
          pageNum: currentPage,
          pageSize
        }).finally(() => {
          this.tableLoading = false
        })
        if (res.code === 200) {
          this.list = res.rows
          this.paginationOption.total = res.total
        }
      } catch {
      }
    },
    onRefresh() {
      this.getList()
    },
    handleSizeChange(value) {
      this.paginationOption.pageSize = value
      this.generateRoute(this.paginationOption.currentPage, value)
    },
    handleCurrentChange(value) {
      this.paginationOption.currentPage = value
      this.generateRoute(value, this.paginationOption.pageSize)
    },
    generateRoute(current, pageSize) {
      this.$router.replace(`/customer/config?tab=import&current=${current}&pageSize=${pageSize}`)
    },
    targetBlank
  }
}
</script>

<style lang="scss" scoped>

</style>
