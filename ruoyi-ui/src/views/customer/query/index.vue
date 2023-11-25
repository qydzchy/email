<template>
  <div class="page-customer-query mx-20 pt-16">
    <div class="bold fs-26 pb-4">
      客户查重
    </div>
    <el-card shadow="always" class="my-20">
      <div class="py-10 flex-middle flex-center">
        <el-select class="custom-select" v-model="querySearch.columnName" placeholder="请选择">
          <el-option
              v-for="item in options"
              :key="item.columnName"
              :label="item.columnAlias"
              :value="item.columnName">
          </el-option>
        </el-select>
        <el-input
            class="custom-input"
            placeholder="搜索公司名称/简称、客户编号、邮箱地址、邮箱后缀、联系人名称、电话、社交账号"
            v-model="querySearch.searchText"
            @keydown.enter.native="searchList"
        ></el-input>
        <el-button class="custom-btn radius-0" type="primary" :disabled="!querySearch.searchText" @click="searchList">
          查询
        </el-button>
      </div>
    </el-card>
    <el-card shadow="always" class="container mb-20">
      <div v-if="list.length">
        <TableNext :loading="tableLoading" :list="list" :columns="columns" :extra-option="{height:'56vh'}"
                   :paginate-option="paginateOption"
                   :paginate-event="paginateEvent"/>
      </div>
      <div v-else>
        <el-empty v-loading="tableLoading">
          <template #description>
            <p>为了避免销售冲突，可以通过客户查重来查看客户的归属情况</p>
            <p>客户查重是基于全公司所有客户资料的全局查找</p>
          </template>
        </el-empty>
      </div>
    </el-card>
  </div>
</template>

<script>
import TableNext from '@/components/TableNext'
import {EmptyStr} from "@/utils/tools";
import {getQueryFieldColumn, searchDuplicationQuery} from "@/api/customer/query";

export default {
  components: {
    TableNext
  },
  data() {
    return {
      querySearch: {
        columnName: '',
        searchText: ''
      },
      options: [],
      list: [],
      columns: [
        {
          label: '客户编号',
          field: 'id',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '客户阶段',
          field: 'stage',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '公司名称/简称',
          field: 'companyName',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '联系人名',
          field: 'contactName',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '客户邮箱',
          field: 'email',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '客户电话',
          field: 'phone',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '电话来源',
          field: 'telOrigin',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '国家地区',
          field: 'area',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '原跟进人',
          field: 'followMan',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '当前所属',
          field: 'department',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '创建时间',
          field: 'crateAt',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '社交账号',
          field: 'socialAccount',
          render: (_row, field) => EmptyStr(field),
        },
      ],
      tableLoading: false,
      paginateOption: {
        total: 0,
        currentPage: 1,
        pageSize: 10,
        pageSizes: [10, 20, 50, 100],
        layout: 'total, sizes, prev, pager, next',
      },
      paginateEvent: {
        'size-change': (value) => this.handlePagination('size', value),
        'current-change': (value) => this.handlePagination('current', value)
      },
    }
  },
  mounted() {
    this.getQueryField()
  },
  methods: {
    async getQueryField() {
      try {
        const res = await getQueryFieldColumn()
        if (res.code === 200) {
          this.options = res.data
          if (this.options && this.options?.length) {
            this.querySearch.columnName = this.options[0].columnName
          }

        }
      } catch {
      }
    },
    async searchList() {
      try {
        const {columnName, searchText} = this.querySearch
        const {currentPage, pageSize} = this.paginateOption
        const data = {
          columnName,
          searchText,
          pageNum: currentPage,
          pageSize
        }
        this.tableLoading = true
        const res = await searchDuplicationQuery({...data}).finally(() => {
          this.tableLoading = false
        })
        if (res.code === 200) {
          this.list = res.rows
        }
      } catch {
      }
    },
    handlePagination(type, value) {
      if (type === 'size') {
        this.paginateOption = {...this.paginateOption, pageSize: value}
      } else if (type === 'current') {
        this.paginateOption = {...this.paginateOption, currentPage: value}
      }
      this.searchList()
    },
  },
}
</script>

<style lang="scss" scoped>

.custom-select {
  width: 140px;

  ::v-deep .el-input__inner {
    height: 42px;
    border: 1px solid #1890ff;
    border-radius: 4px 0 0 4px;
  }
}

.custom-input {
  width: 60%;

  ::v-deep .el-input__inner {
    height: 42px;
    border-radius: unset;
    border: 1px solid #1890ff;
    border-left-width: 0 !important;
  }
}

.custom-btn {
  height: 42px;
  border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
}

.container {
  min-height: 66vh;

  .query-table {

  }
}

::v-deep .el-input__inner {

}

::v-deep .el-card__body {
  width: 100%;
}

</style>
