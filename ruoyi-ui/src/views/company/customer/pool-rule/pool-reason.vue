<template>
  <div class="mb-20">
    <div class="space-between">
      <div class="flex-middle">
        <span class="fs-13 bold">移入公海原因</span>
      </div>
      <div class="mr-10">
        <el-button class="add-rule-btn" round size="mini">添加原因</el-button>
      </div>
    </div>
    <div class="mt-10">
      <TableNext :list="poolReasonList" :columns="poolReasonColumns" :extra-option="{height:'260'}"
                 :customer-empty="customerEmpty"/>
    </div>
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import {EmptyStr} from "@/utils/tools";

export default {
  components: {TableNext},
  data(){
    return {
      poolReasonList: [],
      poolReasonColumns: [
        {
          label: '原因',
          field: 'reason',
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

<style lang="scss">

</style>
