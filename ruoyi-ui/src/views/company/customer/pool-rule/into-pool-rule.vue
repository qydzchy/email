<template>
  <div class="mb-20">
    <div class="space-between">
      <div class="flex-middle">
        <span class="fs-13 bold">移入公海规则</span>
        <el-tooltip placement="top">
          <div slot="content">
            <div class="pool-rule-tooltip fs-14 lineH-24">
              下次移入公海日期根据客户当前生效客群匹配的移入公海规则计算，计算时间为T+1。当客户进入新客群后，会根据新的生效客群匹配的移入公海规则重新计算下次移入公海日期。有多个日期时，默认显示距今最近的日期。
            </div>
          </div>
          <i class="el-icon-warning-outline ml-6"></i>
        </el-tooltip>
        <span class="fs-13 ml-10 gray-text">客户自动移入公海客户的规则</span>
      </div>
      <div class="mr-10">
        <el-button class="add-rule-btn" round size="mini" @click="poolRuleDialog = true">添加规则</el-button>
      </div>
    </div>
    <div class="flex-middle mt-10">
      <el-form :inline="true" :model="poolRuleForm">
        <el-form-item>
          <el-checkbox v-model="poolRuleForm.beforehand">提前</el-checkbox>
        </el-form-item>
        <el-form-item>
          <el-input-number v-model="poolRuleForm.beforehandDay" style="width: 100px" :controls="false"
                           :disabled="!poolRuleForm.beforehand"/>
        </el-form-item>
        <el-form-item>
          <span>天发送邮件通知</span>
        </el-form-item>
      </el-form>
    </div>
    <div>
      <TableNext :loading="tableLoading" :list="poolRuleList" :columns="poolRuleColumns"
                 :extra-option="{height:'260'}"/>
    </div>
    <el-dialog
        width="500px"
        destroy-on-close
        :title="poolRuleDialogTitle"
        :visible.sync="poolRuleDialog"
        @close="onCancelPoolRule"
    >
      <el-form v-model="poolRuleFormSecond">
        <el-form-item label="规则名称">
          <el-input v-model="poolRuleFormSecond.name" placeholder="请输入规则名称"/>
        </el-form-item>
        <el-form-item label="生效客群">
          <div class="form-item">
            <TreeSelectNext
                :default-props="defaultProps"
                :tree-data="memberOption"
                :echo-data.sync="poolRuleFormSecond.customerSegmentId"
                :disabled-list="disabledList"
                :disabled-key="disabledKey"
                echo-name="nickName"
            />
          </div>
        </el-form-item>
        <el-form-item label="客户状态" required>
          <div class="form-item flex-middle">
            <el-input-number
                :min="1"
                style="width: 30%"
                controls-position="right"
                v-model="poolRuleFormSecond.days"
            />
            <span class="px-6">天内</span>
            <el-select style="width: 30%" v-model="poolRuleFormSecond.type">
              <el-option :value="1" label="未联系"></el-option>
              <el-option :value="2" label="未成交"></el-option>
            </el-select>
            <el-tooltip>
              <div slot="content" class="fs-12 lineH-24 tooltip-W-200">
                上次联系时间读取的是客户「最近联系时间」字段信息，详情可见“客户设置-客户跟进规则
              </div>
              <i class="el-icon-question pl-10"></i>
            </el-tooltip>
          </div>
        </el-form-item>
        <el-form-item label="开始时间">
          <div class="form-item">
            <el-date-picker
                type="date"
                style="width: 100%"
                placeholder="选择日期时间"
                clearable
                :picker-options="pickerOptions"
                v-model="poolRuleFormSecond.startTime"
            >
            </el-date-picker>
          </div>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button round @click="onCancelPoolRule">取 消</el-button>
        <el-button type="primary" round @click="onConfirm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import {EmptyStr} from "@/utils/tools";
import DelPopover from "@/views/company/customer/DelPopover.vue";
import {rulesAdd, rulesDelete, rulesEdit, rulesList} from "@/api/company/poolRule";
import TreeSelect from "@riophae/vue-treeselect";
import TreeSelectNext from "@/components/TreeSelectNext/index.vue";
import {listDeptUsersTree} from "@/api/system/dept";

const initPoolRuleForm2 = {
  id: '',
  name: '',
  customerSegmentId: [],
  days: 1,
  type: 1
}
export default {
  components: {
    TreeSelectNext,
    TreeSelect,
    TableNext,
    DelPopover
  },
  data() {
    return {
      // 移入公海规则
      poolRuleForm: {
        beforehand: true,
        beforehandDay: 7
      },
      poolRuleList: [],
      poolRuleColumns: [
        {
          label: '启用状态',
          field: 'status',
          render: (row, field) => {
            return <el-switch value={!!field} onChange={() => this.handleRowStatus(row)}></el-switch>
          },
        },
        {
          label: '规则名称',
          field: 'name',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '移入条件',
          field: 'inclusionConditions',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '生效客群',
          field: 'effectGroup',
          showOverflowTooltip: true,
          render: (_row, field) => {
            return field?.length ?
                <div>{field.join(',')}</div>
                : '---'
          },
        },
        {
          label: '开始时间',
          field: 'startTime',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '操作',
          field: 'operate',
          render: (row) => {
            const disabled = !!row?.status
            return (
                <el-tooltip disabled={!disabled} content="公海规则开启后，不允许编辑和删除">
                  <el-row>
                    <el-button type='text' disabled={disabled} onClick={() => this.onModifyPoolRule(row)}>
                      编辑
                    </el-button>
                    <DelPopover id={row.id} btnDisabled={disabled} on={{onDelete: (id) => this.onDelete(id)}}/>

                  </el-row>
                </el-tooltip>

            );
          },
        },
      ],
      poolRuleDialog: false,
      poolRuleDialogTitle: '新建移入公海规则',
      poolRuleFormSecond: {...initPoolRuleForm2},
      intoTypeOption: [
        {
          value: 0,
          label: '未联系'
        }, {
          value: 1,
          label: '未成交'
        },
      ],
      intoTypeOptionMap: {
        0: '未联系',
        1: '未成交',
      },
      pickerOptions: {
        disabledDate: this.disabledDate
      },
      defaultProps: {
        children: 'children',
        label: 'name'
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
        const res = await rulesList().finally(() => {
          this.tableLoading = false
        })
        if (res.code === 200) {
          let disabledList = []
          this.poolRuleList = res.data
          this.poolRuleList.forEach(val => {
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
    // table switch
    handleRowStatus(row) {
      this.intoPollRuleEditReq({
        ...row,
        status: +(!row.status)
      })
      // this.poolRuleList.map((val) => {
      //   if (val.id === row.id) {
      //     val.status = !row.status
      //   }
      //   return val
      // })
    },
    // 禁用之前的时间
    disabledDate(time) {
      return time.getTime() < Date.now()
    },
    onModifyPoolRule(row) {
      this.poolRuleDialogTitle = '编辑移入公海规则'
      this.poolRuleFormSecond = row
      this.poolRuleDialog = true
    },

    async intoPoolRuleAddReq(row) {
      try {
        const res = await rulesAdd({
          name: row?.name,
          customerSegmentId: row?.customerSegmentId.join(''),
          days: row?.days,
          type: row?.type
        }).finally(() => {
          this.btnLoading = false
        })
        if (res.code === 200) {
          this.$message({
            type: 'success',
            message: '添加成功'
          })
          await this.getList()
          this.onCancelPoolRule()
        }
      } catch {
      }
    },
    async intoPollRuleEditReq(row) {
      try {
        const res = await rulesEdit({...row}).finally(() => {
          this.btnLoading = false
        })
        if (res.code === 200) {
          this.$message({
            type: 'success',
            message: '修改成功'
          })
          await this.getList()
          this.onCancelPoolRule()
        }
      } catch {
      }
    },

    async onDelete(id) {
      try {
        const res = await rulesDelete({id})
        if (res.code === 200) {
          this.$message({
            type: 'success',
            message: '删除成功',
          })
          await this.getList()
        }
      } catch {
      }
    },
    onConfirm() {
      const formData = this.poolRuleFormSecond
      this.btnLoading = true
      if (!formData.id) {
        this.intoPoolRuleAddReq(formData)
      } else {
        this.intoPollRuleEditReq(formData)
      }
    },
    onCancelPoolRule() {
      this.poolRuleDialogTitle = '新建移入公海规则'
      this.poolRuleFormSecond = initPoolRuleForm2
      this.poolRuleDialog = false
    }
  }
}
</script>

<style lang="scss">
.pool-rule-tooltip {
  width: 210px;
}
</style>
