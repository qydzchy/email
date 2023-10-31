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
        :loading="tableLoading"
        :extra-event="{'selection-change': handleSelectionChange}"
    />
    <el-dialog title="编辑" width="400px" style="margin-top: 25vh" :visible.sync="openSeaDialog"
               destroy-on-close>
      <div class="gray-text">客户上限</div>
      <el-radio-group class="flex-column" v-model="limitMaxRadio">
        <el-radio class="my-20" :label="1">不限</el-radio>
        <div>
          <el-radio :label="2">客户上限</el-radio>
          <el-input-number style="width: 240px" controls-position="right" :disabled="limitMaxRadio" v-model="limitMax"/>
        </div>

      </el-radio-group>
      <div slot="footer" class="dialog-footer">
        <el-button round @click="onCancel">取 消</el-button>
        <el-button type="primary" round @click="onConfirm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import {EmptyStr} from "@/utils/tools";
import {getOpenSeaList, openSeaEdit} from "@/api/company/openSea";

export default {
  components: {
    TableNext
  },
  data() {
    return {
      list: [],
      columns: [
        {type: 'selection', width: '50'},
        {
          label: '成员',
          field: 'nickName',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '部门',
          field: 'deptName',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '上限',
          field: 'content',
          // render: (row, _field) => {
          //   const limitText = row?.limit ? `上限${row.limit}` : '无上限'
          //   return <div>{`${row?.customer}/${limitText}`}</div>
          // },
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
      openSeaDialog: false,
      limitMaxRadio: 1,
      limitMax: 0,
      tableLoading: false
    }
  },
  mounted() {
  },
  methods: {
    async getList() {
      this.tableLoading = true
      try {
        const res = await getOpenSeaList().finally(() => {
          this.tableLoading = false
        })
        if (res.code === 200) {
          this.list = res.data
        }
      } catch {
      }
    },

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
    async onConfirm(){
      try{
        const res = await openSeaEdit({

        })

      }catch {}
    },
    onCancel() {
      this.limitMaxRadio = 1
      this.limitMax = 0
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
