<template>
  <div class="pool-receive mb-20">
    <div class="space-between">
      <div class="flex-middle">
        <span class="fs-13 bold">领取上限</span>

        <span class="fs-13 ml-10 gray-text">没有指定领取上限的成员，默认为不限制领取数量</span>
      </div>
      <div class="mr-10">
        <el-button class="add-rule-btn" round size="mini" @click="receiveMaxDialog=true">添加规则</el-button>
      </div>
    </div>
    <div class="mt-10">
      <TableNext :list="receiveMaxList" :columns="receiveMaxColumn" :loading="tableLoading"
                 :extra-option="{height:'260'}"/>
    </div>

    <el-dialog
        :visible.sync="receiveMaxDialog"
        width="400px"
        :title="receiveMaxDialogTitle"
        destroy-on-close
        @close="onCancel"
    >
      <el-form :model="receiveMaxForm">
        <el-form-item label="选择人员" required>
          <div class="form-item">
            <TreeSelectNext
                :default-props="defaultProps"
                :tree-data="memberOption"
                :echo-data.sync="receiveMaxForm.userIdList"
                :disabled-list="disabledList"
                :disabled-key="disabledKey"
                echo-name="nickName"
            />
          </div>
        </el-form-item>
        <el-form-item label="领取上限" required>
          <div class="form-item">
            <div class="flex-middle">
              <el-input-number style="width:80%" v-model="receiveMaxForm.claimLimit" :min="1"
                               controls-position="right"></el-input-number>
              <span class="fs-12 lineH-24 ml-4">个公海客户</span>
            </div>
          </div>

        </el-form-item>
        <el-form-item label="上限周期" required>
          <div class="form-item">
            <el-select
                style="width: 100%"
                v-model="receiveMaxForm.claimPeriod">
              <el-option
                  v-for="(item,index) in periodOption"
                  :key="index"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
          </div>

        </el-form-item>
      </el-form>


      <div slot="footer" class="dialog-footer">
        <el-button round :loading="btnLoading" @click="onCancel">取 消</el-button>
        <el-button type="primary" round :loading="btnLoading" @click="onConfirm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import DelPopover from '../DelPopover.vue'
import {EmptyStr} from "@/utils/tools";
import {treeList} from "@/mock";
import {claimLimitAdd, claimLimitDelete, claimLimitEdit, claimLimitList} from "@/api/company/poolRule";
import TreeSelectNext from "@/components/TreeSelectNext/index.vue";
import {listDeptUsersTree} from "@/api/system/dept";

const initReceiveMaxForm = {
  claimLimit: 1,
  claimPeriod: '',
  userIdList: [],
}
export default {
  components: {TreeSelectNext, TableNext, DelPopover},
  data() {
    return {
      receiveMaxList: [],
      receiveMaxColumn: [
        {
          label: '成员',
          field: 'nickName',
          align: 'left',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '领取上限',
          field: 'claimLimit',
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
          field: 'claimPeriod',
          width: '140',
          render: (_row, field) => <div>
            {this.periodOptionMap[field]}
          </div>,
        },
        {
          label: '操作',
          field: 'operate',
          width: '140',
          render: (row) => {
            return (
                <DelPopover id={row?.id} on={{onDelete: (id) => this.onDelete(id)}}/>
            );
          },
        },
      ],
      receiveMaxDialog: false,
      receiveMaxDialogTitle: '添加领取上限规则',
      receiveMaxForm: {...initReceiveMaxForm},
      emptyOption: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      data: treeList,
      periodOption: [
        {label: '每日', value: 1},
        {label: '每周', value: 2},
        {label: '每月', value: 3},
        {label: '每年', value: 4},
      ],
      periodOptionMap: {
        1: '每日',
        2: '每周',
        3: '每月',
        4: '每年'
      },
      memberOption: [],
      disabledList: [],
      disabledKey: 'id',
      tableLoading: false,
      btnLoading: false,
    }
  },
  mounted() {
    this.getList()
    this.getCommonTree()
  },
  methods: {
    async getList() {
      this.tableLoading = true
      try {
        const res = await claimLimitList().finally(() => {
          this.tableLoading = false
        })
        if (res.code === 200) {
          let disabledList = []
          this.receiveMaxList = res.data
          this.receiveMaxList.forEach(val => {
            disabledList.push(val.userId)
          })
          this.disabledList = disabledList
        }
      } catch {
        this.tableLoading = false
      }
    },
    async getCommonTree() {
      try {
        const res = await listDeptUsersTree()
        if (res.code === 200) {
          this.memberOption = res.data
        }
      } catch {
      }
    },
    // 编辑
    onEdit(row) {
      this.receiveMaxDialogTitle = '编辑客户分组'
      this.receiveMaxForm = {
        ...row,
        userIdList: [row.userId]
      }
      console.log(this.receiveMaxForm)
      this.receiveMaxDialog = true
    },

    async onDelete(id) {
      try {
        const res = await claimLimitDelete({id})
        if (res.code === 200) {
          this.$message({
            type: 'success',
            message: '删除成功'
          })
          await this.getList()
        }
      } catch {
      }
    },

    async claimAddReq(row) {
      try {
        const res = await claimLimitAdd({...row}).finally(() => {
          this.btnLoading = false
        })
        if (res.code === 200) {
          this.$message({
            type: 'success',
            message: '添加成功'
          })
          await this.getList()
          this.onCancel()
        }
      } catch {
      }
    },
    async claimEditReq(row) {
      try {
        const res = await claimLimitEdit({...row}).finally(() => {
          this.btnLoading = false
        })
        if (res.code === 200) {
          this.$message({
            type: 'success',
            message: '修改成功'
          })
          await this.getList()
          this.onCancel()
        }
      } catch {
      }
    },

    onConfirm() {
      this.btnLoading = true
      const formData = this.receiveMaxForm
      if (!formData.id) {
        this.claimAddReq(formData)
      } else {
        this.claimEditReq(formData)
      }
    },
    // 取消
    onCancel() {
      this.receiveMaxDialogTitle = '新建客户分组'
      this.receiveMaxForm = {...initReceiveMaxForm}
      this.receiveMaxDialog = false
    },
  }

}
</script>

<style lang="scss">
.pool-receive {
  .select-tree {
    .el-select-dropdown__item {
      padding: 0 !important;
      overflow-y: auto;
    }
  }
}
</style>
