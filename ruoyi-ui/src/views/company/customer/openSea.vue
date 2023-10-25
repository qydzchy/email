<template>
  <div>
    <div class="mb-8">
      <el-button type="primary" icon="el-icon-edit" size="small" :disabled="multiple" @click="onSelectModify">
        批量修改
      </el-button>
    </div>
    <TableNext
        :list="list"
        :columns="columns"
        :extra-event="{'selection-change': handleSelectionChange}"
    />
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import {EmptyStr} from "@/utils/tools";

export default {
  components: {
    TableNext
  },
  data() {
    return {
      list: [
        {id: 1, group: '1',},
        {id: 2, group: '2',},
        {id: 3, group: '3',}
      ],
      columns: [
        {type: 'selection', width: '50'},
        {
          label: '成员',
          field: 'group',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '部门',
          field: 'dept',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '上限',
          field: 'upper',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '操作',
          field: 'operate',
          width: '200',
          fixed: 'right',
          render: (row) => {
            return (
                <el-button type='text' onClick={() => this.onModify(row)}>
                  修改
                </el-button>
            );
          },
        },
      ],
      ids: [],  // 选中数组
      single: true, // 非单个禁用
      multiple: true, // 非多个禁用
    }
  },
  mounted() {
  },
  methods: {
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    // 单个修改
    onModify(row) {
      console.log(row)
    },
    // 选择修改
    onSelectModify() {
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
