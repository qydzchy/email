<template>
  <div class="mb-20">
    <div class="space-between">
      <div class="flex-middle">
        <span class="fs-13 bold">公海分组</span>
        <span
          class="fs-13 ml-10 gray-text">可以通过公海分组来隔离公司的客户资源；公海分组中的客户被移入公海后，仅对公海分组的成员可见</span>
      </div>
      <div class="mr-10">
        <el-button class="add-rule-btn" round size="mini">新增分组</el-button>
      </div>
    </div>
    <div class="mt-10">
      <TableNext :list="poolGroupByList" :columns="poolGroupByColumns" :extra-option="{height:'260'}"
      />
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
      poolGroupByList: [],
      poolGroupByColumns: [
        {
          label: '名称',
          field: 'name',
          align: 'left',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '成员',
          field: 'member',
          align: 'left',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '操作',
          field: 'operate',
          width: '120',
          render: (row) => {
            return (
              <div>
                <div class="flex-middle" v-if="false">
                  <el-button>查看</el-button>
                  <el-tooltip placement="top">
                    <div slot="content">
                      <div className="pool-rule-tooltip fs-14 lineH-24">
                        默认公海分组不支持编辑
                      </div>
                    </div>
                    <i className="el-icon-warning-outline ml-6"></i>
                  </el-tooltip>
                </div>
                <el-row v-else>
                  <el-button type='text'>
                    编辑
                  </el-button>
                  <el-button type='text'>
                    删除
                  </el-button>
                </el-row>
              </div>

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
