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
      <TableNext :list="poolRuleList" :columns="poolRuleColumns" :extra-option="{height:'260'}"/>
    </div>
    <el-dialog
      width="500px"
      destroy-on-close
      :title="poolRuleDialogTitle"
      :visible.sync="poolRuleDialog"
    >
      <el-form v-model="poolRuleFormSecond">
        <el-form-item label="规则名称">
          <el-input v-model="poolRuleFormSecond.ruleName" placeholder="请输入规则名称"/>
        </el-form-item>
        <el-form-item label="生效客群">
          <div class="form-item">
            <el-cascader
              style="width: 100%"
              v-model="poolRuleFormSecond.effectGroup"
              :options="[]"
              :props="{ multiple: true }"
              collapse-tags
              clearable filterable/>
          </div>
        </el-form-item>
        <el-form-item label="客户状态" required>
          <div class="form-item flex-middle">
            <el-input-number style="width: 40%" v-model="poolRuleFormSecond.intoConditionDay"
                             controls-position="right"/>
            <span class="px-6">天内</span>
            <el-select style="width: 40%" v-model="poolRuleFormSecond.intoConditionType">
              <el-option
                v-for="item in intoTypeOption"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
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
        <el-button type="primary" round>确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import {EmptyStr} from "@/utils/tools";
import DelPopover from "@/views/company/customer/DelPopover.vue";

const initPoolRuleForm2 = {
  status: false,
  ruleName: '',
  intoCondition: '',
  effectGroup: '',
  startTime: '',
  intoConditionDay: '',
  intoConditionType: 0
}
export default {
  components: {
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
      poolRuleList: [
        {
          id: 1,
          status: true,
          ruleName: 'tjm公海管理',
          effectGroup: ['未分组', '未分组2', '未分组3', '未分组4', '未分组5', '未分6'],
          startTime: '2023-10-17',
          intoConditionDay: 10,
          intoConditionType: 0,
        }
      ],
      poolRuleColumns: [
        {
          label: '启用状态',
          field: 'status',
          render: (row, field) => {
            return <el-switch value={field} onChange={() => this.handleRowStatus(row)}></el-switch>
          },
        },
        {
          label: '规则名称',
          field: 'ruleName',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '移入条件',
          field: 'intoCondition',
          render: (row, _field) => {
            const {intoConditionDay, intoConditionType} = row
            const type = this.intoTypeOptionMap[intoConditionType]
            return <div>
              {intoConditionDay}天{type}
            </div>

          },
        },
        {
          label: '生效客群',
          field: 'effectGroup',
          showOverflowTooltip: true,
          render: (_row, field) => {
            return field.length ?
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
            const disabled = row?.status
            return (
              <el-tooltip disabled={!disabled} content="公海规则开启后，不允许编辑和删除">
                <el-row>
                  <el-button type='text' disabled={disabled} onClick={() => this.onModifyPoolRule(row)}>
                    编辑
                  </el-button>
                  <DelPopover id={row.id} btnDisabled={disabled}/>

                </el-row>
              </el-tooltip>

            );
          },
        },
      ],
      poolRuleDialog: false,
      poolRuleDialogTitle: '新建移入公海规则',
      poolRuleFormSecond: initPoolRuleForm2,
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
    }
  },
  methods: {
    // table switch
    handleRowStatus(row) {
      this.poolRuleList.map((val) => {
        if (val.id === row.id) {
          val.status = !row.status
        }
        return val
      })
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
