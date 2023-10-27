<template>
  <div class="mb-20">
    <div class="space-between">
      <div class="flex-middle">
        <span class="fs-13 bold">移入公海白名单</span>

        <span class="fs-13 ml-10 gray-text">白名单人员的客户不受移入公海规则的限制</span>
      </div>
      <div class="mr-10">
        <el-button class="add-rule-btn" round size="mini">添加名单</el-button>
      </div>
    </div>
    <div class="mt-10">
      <el-checkbox>排除禁用账号</el-checkbox>
      <el-tooltip placement="top">
        <div slot="content">
          <div class="pool-rule-tooltip fs-14 lineH-24">
            禁用账号是在企业设置 - 组织架构 - 个人信息卡片中进行「禁用CRM」操作的账号
          </div>
        </div>
        <i class="el-icon-warning-outline ml-6"></i>
      </el-tooltip>
    </div>
    <div class="mt-10">
      <TableNext :list="whitelist" :columns="whiteListColumn" :extra-option="{height:'260'}"
                 :customer-empty="customerEmpty"/>
    </div>
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import {EmptyStr} from "@/utils/tools";

export default {
  components: {TableNext},
  data() {
    return {
      // 移入公海白名单
      whitelist: [],
      whiteListColumn: [
        {
          label: '成员',
          field: 'startTime',
          align: 'left',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '操作',
          field: 'operate',
          width: '140',
          render: (row) => {
            return (
              <el-row>
                <el-button type='text'>
                  查看
                </el-button>
              </el-row>
            );
          },
        },
      ],
    }
  }

}
</script>

<style lang="scss" scoped>

</style>
