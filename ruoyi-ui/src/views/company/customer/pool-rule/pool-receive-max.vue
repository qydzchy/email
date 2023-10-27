<template>
  <div class="mb-20">
    <div class="space-between">
      <div class="flex-middle">
        <span class="fs-13 bold">领取上限</span>

        <span class="fs-13 ml-10 gray-text">没有指定领取上限的成员，默认为不限制领取数量</span>
      </div>
      <div class="mr-10">
        <el-button class="add-rule-btn" round size="mini">添加规则</el-button>
      </div>
    </div>
    <div class="mt-10">
      <TableNext :list="receiveMaxList" :columns="receiveMaxColumn" :extra-option="{height:'260'}"/>
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
      receiveMaxList: [],
      receiveMaxColumn: [
        {
          label: '成员',
          field: 'group',
          align: 'left',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '领取上限',
          field: 'startTime',
          width: '140',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '上限周期',
          'render-header': (h, {column}) => {
            return h('div', [
              h('span', column.label),
              h(
                'el-tooltip',
                {
                  props: {
                    effect: 'dark',
                    content: <div class="fs-12 lineH-24" style="width:210px">
                      领取上限按周期计算。譬如：公海客户领取上限为10，周期为每周。即每周最多可从公海领取10个公海客户到私海中。
                    </div>,
                  },
                },
                [
                  h('i', {
                    class: 'el-icon-question ml-5',
                  }),
                ]
              ),
            ])
          },
          field: 'startTime',
          width: '140',
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
