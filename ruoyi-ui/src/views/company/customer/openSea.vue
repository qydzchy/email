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
    <el-dialog title="编辑" width="400px" style="margin-top: 25vh" :visible.sync="openSeaDialog"
               destroy-on-close>
      <div class="gray-text">客户上限</div>
      <el-radio-group class="flex-column" v-model="limitMaxRadio">
        <el-radio class="my-20" :label="true">不限</el-radio>
        <div>
          <el-radio :label="false">客户上限</el-radio>
          <el-input-number style="width: 240px" controls-position="right" :disabled="limitMaxRadio" v-model="limitMax"/>
        </div>

      </el-radio-group>
      <div slot="footer" class="dialog-footer">
        <el-button round @click="onCancel">取 消</el-button>
        <el-button type="primary" round>确 定</el-button>
      </div>
    </el-dialog>
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
        {id: 1, group: 'jamby', dept: '我的企业', customer: '客户 44', limit: 0},
        {id: 2, group: 'jamby2', dept: '外部', customer: '客户 109', limit: 2},
        {id: 3, group: 'jamby3', dept: '公明', customer: '客户 332', limit: 0}
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
          render: (row, _field) => {
            const limitText = row?.limit ? `上限${row.limit}` : '无上限'
            return <div>{`${row?.customer}/${limitText}`}</div>
          },
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
      openSeaDialog: false,
      limitMaxRadio: true,
      limitMax: 0,
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
      this.ids = [row.id]
      this.limitMaxRadio = !row.limit
      this.limitMax = row.limit
      this.openSeaDialog = true
    },
    // 选择修改
    onSelectModify() {
      this.openSeaDialog = true
    },
    onCancel() {
      this.openSeaDialog = false
    },
  }
}
</script>

<style lang="scss" scoped>
::v-deep .el-dialog {
  border-radius: 14px;
}

::v-deep .el-dialog__header {
  border-bottom: 1px solid #dadada;

}

::v-deep .el-dialog__body {
  padding-top: 10px;
}
</style>
