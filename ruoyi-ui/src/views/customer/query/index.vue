<template>
  <div class="page-customer-query mx-20 pt-16">
    <div class="bold fs-26 pb-4">
      客户查重
    </div>
    <el-card shadow="always" class="my-20">
      <div class="py-10 flex-middle flex-center">
        <el-select class="custom-select" v-model="querySearch.key" placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        <el-input
          class="custom-input"
          placeholder="搜索公司名称/简称、客户编号、邮箱地址、邮箱后缀、联系人名称、电话、社交账号"
          v-model="querySearch.value"
        ></el-input>
        <el-button class="custom-btn radius-0" type="primary">查询</el-button>
      </div>
    </el-card>
    <el-card shadow="always" class="container mb-20">
      <!--      <TableNext v-if="list.length" :list="list" :columns="columns" :extra-option="{height:'60vh'}"/>-->
      <div v-if="list.length">
        <TableNext :list="list" :columns="columns" :extra-option="{height:'56vh'}" :paginate-option="paginateOption"/>
      </div>
      <div v-else>
        <el-empty
          >
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

export default {
  components: {
    TableNext
  },
  data() {
    return {
      querySearch: {
        key: 'all',
        value: ''
      },
      options: [
        {
          value: 'all',
          label: '全部'
        }, {
          value: 'companyName',
          label: '公司名称/简称'
        }, {
          value: 'customerNo',
          label: '客户编号'
        }, {
          value: 'email',
          label: '邮箱'
        }, {
          value: 'emailSuffix',
          label: '邮箱后缀'
        }, {
          value: 'contactName',
          label: '联系人名称'
        }, {
          value: 'contactTel',
          label: '联系电话'
        }, {
          value: 'communityAccount',
          label: '社交账号'
        }
      ],
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
      paginateOption: {
        total: 0,
        layout: 'total, sizes, prev, pager, next',
        pageSize: 20,
        pageSizes: [10, 20, 50, 100]
      },

    }
  },
  mounted() {

  }
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
