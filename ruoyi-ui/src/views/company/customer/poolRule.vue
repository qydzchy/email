<template>
  <div class="pool-rule">
    <!--  移入公海规则  -->
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
          <el-button class="add-rule-btn" round size="mini">添加规则</el-button>
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
        <TableNext :list="poolRuleList" :columns="poolRuleColumns" :extra-option="{height:'260'}"
                   :customer-empty="customerEmpty"/>
      </div>
    </div>
    <!-- 移入公海白名单 -->
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
    <!--  公海领取规则  -->
    <div class="mb-20">
      <div class="space-between">
        <div class="flex-middle">
          <span class="fs-13 bold">公海领取规则</span>

          <span class="fs-13 ml-10 gray-text">把客户从公海移至私海需遵守的规则</span>
        </div>
      </div>
      <div>
        <div class="flex-column">
          <div class="fs-14 my-16">领取限制</div>
          <div class="flex-middle">
            <el-radio-group v-model="receiveLimit">
              <el-radio :label="1">不限制</el-radio>
              <el-radio :label="0">限制</el-radio>
            </el-radio-group>
            <div class="flex-middle">
              <el-input-number v-model="receiveLimitDay" :disabled="!!receiveLimit" :controls="false"
                               class="ml-8" style="width: 100px"></el-input-number>
              <span class="fs-14">天内，原跟进人不能领取同一个客户</span>
            </div>
          </div>
        </div>
        <div class="flex-column">
          <div class="fs-14 my-16">商机查看规则</div>
          <div class="flex-middle">
            <el-radio-group v-model="viewRule">
              <el-radio :label="1">领取公海客户后，不能查看客户历史商机</el-radio>
              <el-radio :label="0">领取公海客户后</el-radio>
            </el-radio-group>
          </div>
        </div>
      </div>
    </div>
    <!--  领取上限  -->
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
        <TableNext :list="receiveMaxList" :columns="receiveMaxColumn" :extra-option="{height:'260'}"
        />
      </div>
    </div>
    <!--  公海显示设置  -->
    <div class="mb-20">
      <div class="space-between">
        <div class="flex-middle">
          <span class="fs-13 bold">公海显示设置</span>
          <span class="fs-13 ml-10 gray-text">客户落入公海后的显示设置</span>
        </div>

      </div>
      <div class="mt-10">
        <span class="fs-13 gray-text">是否显示公海联系人 （注：隐藏公海联系人后公海客户将不允许被编辑）</span>
      </div>
      <div class="mt-10">
        <el-radio-group v-model="showSettingRadio">
          <el-radio :label="1">是</el-radio>
          <el-radio :label="0">否</el-radio>
        </el-radio-group>
      </div>

    </div>
    <!--  公海分组  -->
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
    <!--  移入公海原因  -->
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
      customerEmpty: <div>暂无数据</div>,
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
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '规则名称',
          field: 'ruleName',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '移入条件',
          field: 'into',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '生效客群',
          field: 'group',
          render: (_row, field) => EmptyStr(field),
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
            return (
              <el-row>
                <el-button type='text'>
                  编辑
                </el-button>
                <el-button type='text'>
                  删除
                </el-button>
              </el-row>
            );
          },
        },
      ],
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
      // 公海领取规则
      receiveLimit: 1,
      receiveLimitDay: 2,
      viewRule: 1,
      // 领取上限
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
          slot:
            <div class="flex-middle flex-center">
              <span>上限周期</span>
              <el-tooltip placement="top">
                <div slot="content">
                  <div class="pool-rule-tooltip fs-14 lineH-24">
                    领取上限按周期计算。譬如：公海客户领取上限为10，周期为每周。即每周最多可从公海领取10个公海客户到私海中。
                  </div>
                </div>
                <i class="el-icon-warning-outline ml-6"></i>
              </el-tooltip>
            </div>,
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
      // 公海显示设置
      showSettingRadio: 1,
      // 公海分组
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
      // 移入公海原因
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
  },
  mounted() {

  }
}
</script>

<style lang="scss" scoped>
.pool-rule-tooltip {
  width: 210px;
}

.add-rule-btn {
  color: #0064ff;
  border-color: #0064ff;
}
</style>
