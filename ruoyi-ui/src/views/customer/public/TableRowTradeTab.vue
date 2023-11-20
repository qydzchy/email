<template>
  <div v-loading="tableLoading">
    <el-row class="mb-10">
      <CollapseWrap>
        <template #header>
          <div class="flex-middle space-between">
            <div class="bold">商机</div>
            <div><i class="el-icon-circle-plus-outline"></i></div>
          </div>
        </template>
        <TableNext :list="businessList" :columns="businessColumns" :extra-option="extraOption"/>
      </CollapseWrap>
    </el-row>

    <el-row class="mb-10">
      <CollapseWrap>
        <template #header>
          <div class="flex-middle space-between">
            <div class="bold">销售订单</div>
            <div><i class="el-icon-circle-plus-outline"></i></div>
          </div>
        </template>
        <TableNext :list="tradeList" :columns="tradeColumns" :extra-option="extraOption"/>
      </CollapseWrap>
    </el-row>

    <el-row class="mb-10">
      <CollapseWrap>
        <template #header>
          <div class="flex-middle space-between">
            <div class="bold">产品</div>
            <div><i class="el-icon-circle-plus-outline"></i></div>
          </div>
        </template>
        <TableNext :list="productList" :columns="productColumns" :extra-option="extraOption"/>
      </CollapseWrap>
    </el-row>
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import CollapseWrap from "@/components/CollapseWrap/index.vue";

export default {
  components: {TableNext, CollapseWrap},
  data() {
    return {
      tableLoading: false,
      businessList: [],
      businessColumns: [
        {
          label: '商机名称',
          field: 'name',
          width: 200
        },
        {
          label: '当前阶段',
          field: 'stage'
        },
        {
          label: '销售金额',
          field: 'amount'
        },
        {
          label: '结束日期',
          field: 'end'
        },
        {
          label: '已生效回款金额',
          field: 'count',
          width: 200,
        },
        {
          label: '负责人',
          field: 'person'
        },
      ],
      tradeList: [],
      tradeColumns: [
        {
          label: '订单编号',
          field: 'id',
          width: 200
        },
        {
          label: '订单名称',
          field: 'name',
          width: 200
        },
        {
          label: '当前阶段',
          field: 'stage',
          width: 200
        },
        {
          label: '订单金额',
          field: 'amount',
          width: 200
        },
        {
          label: '订单日期',
          field: 'date',
          width: 200
        },
        {
          label: '产品项目数',
          field: 'count',
          width: 200
        },
        {
          label: '已生效回款金额',
          field: 'money',
          width: 200
        },
        {
          label: '未回款金额',
          field: 'hui',
          width: 200
        },
        {
          label: '符合绩效规则',
          field: 'rule',
          width: 200
        },
        {
          label: '创建人',
          field: 'create',
          width: 200
        },
        {
          label: '业绩归属人',
          field: 'belong',
          width: 200
        },
      ],
      productList: [],
      productColumns: [
        {
          label: '产品',
          field: 'product',
          width: 200
        },
        {
          label: '销售订单',
          field: 'order',
          width: 200
        },
        {
          label: '报价单',
          field: 'price',
          width: 200
        },
        {
          label: '商机',
          field: 'business',
          width: 200
        },
        {
          label: '操作',
          field: 'operate',
          fixed: 'right',
        },
      ],
      extraOption: {},
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
