<template>
  <el-form class="create-customer-form" :model.sync="customerForm">
    <el-form-item label="客群名称" required>
      <el-input placeholder="请输入" size="small" :value.sync="customerForm.name"
                @input="(value)=>onInput('name',value)" type="text" maxlength="32"
                show-word-limit></el-input>
    </el-form-item>
    <template v-if="showRange">
      <el-row class="fs-14 flex-column">
        <el-col>
          <span>使用范围</span>
          <el-tooltip>
            <template #content>
              <div class="lineH-20" style="width:200px">
                公司统一标准请选择【公司共享】，个人使用习惯可选择【个人使用】。客群保存成功后，使用范围不可修改。
              </div>
            </template>
            <i class="el-icon-question ml-8"></i>
          </el-tooltip>
        </el-col>
        <el-col class="mt-16 mb-20">
          <el-radio-group :value.sync="customerForm.usageScope" @input="(value)=>onInput('usageScope',value)">
            <el-radio :label="1">公司共享</el-radio>
            <el-radio :label="2">个人使用</el-radio>
          </el-radio-group>
        </el-col>
      </el-row>
      <el-form-item label="可见范围" v-if="customerForm.usageScope===1">
        <el-select-tree
            :value.sync="customerForm.visibilityScope"
            :data="visibilityOption"
            :props="{value:'id',label:'name'}"
            :default-expand-all="true"
            collapse-tags
            filterable
            multiple
            show-checkbox
            clearable
            @change="(value)=>onInput('visibilityScope',value)"></el-select-tree>
        <div>注：公司共享的客群默认选择全公司可见，若只选择部门/人员后，该客群不可被应用于移入公海规则。</div>
      </el-form-item>
    </template>

    <el-form-item label="条件规则">
      <el-row style="width:100%;height:28px" class="flex-middle space-between">
        <el-radio-group class="flex1" :value.sync="customerForm.conditionRuleType"
                        @input="(value)=>onInput('conditionRuleType',value)">
          <el-radio :label="1">满足全部条件</el-radio>
          <el-radio :label="2">满足任一条件</el-radio>
          <el-radio :label="3" :disabled="true">
            <span>自定义</span>
            <el-tooltip placement="top"
                        content="满足全部条件使用AND，满足任一条件使用OR，优先运算使用（），条件直接引用序号">
              <i class="el-icon-question"></i>
            </el-tooltip>
          </el-radio>
        </el-radio-group>
        <el-radio-group size="mini" v-if="customerForm.conditionRuleType===3" :value.sync="customerForm.mode"
                        @input="(value)=>onInput('mode',value)">
          <el-radio-button label="simple">
            <el-tooltip placement="top" content="简易模式">
              <i class="el-icon-menu"></i>
            </el-tooltip>
          </el-radio-button>
          <el-radio-button label="high">
            <el-tooltip placement="top" content="高级模式">
              <i class="el-icon-s-operation"></i>
            </el-tooltip>
          </el-radio-button>
        </el-radio-group>
      </el-row>

      <template v-if="isRuleAutoType">
        <el-row class="my-10" v-if="customerForm.conditionRuleType===3 && customerForm.mode==='high'">
          <el-input :value.sync="customerForm.andOr" size="small"
                    placeholder="例如：( 1 and 2 ) or ( 3 and 4 )"></el-input>
        </el-row>
        <el-row class="rule-list mt-10 flex-middle space-between"
                v-for="(item,index) in customerForm.conditionRuleContent"
                :key="index">
          <div class="flex1 flex-middle">
            <span class="mr-10">{{ index + 1 }}</span>
            <el-row type="flex" :gutter="10">
              <!--      字段        -->
              <el-col style="width: 160px">
                <el-cascader
                    :value.sync="item.columnName"
                    size="small"
                    filterable
                    :show-all-levels="false"
                    :emitPath="false"
                    :options="customerRuleOption"
                    :props="{value:'columnName',label:'nickName',children:'children'}"
                    :checkStrictly="true"
                    @change="(value)=>{
                      item.conditionType = null
                      item.value= null
                      handleRuleContent(item.ruleId,'columnName',value)
                    }"
                >
                </el-cascader>
              </el-col>
              <!--       运算       -->
              <el-col style="width: 140px" v-if="item.columnName && item.columnName.length">
                <el-select
                    placeholder="运算"
                    :value.sync="item.conditionType"
                    @change="(value)=>{
                      item.value = null
                      item.dateType = null
                      item.timeRange = null
                      handleRuleContent(item.ruleId,'conditionType',value)
                    }">
                  <el-option
                      :key="index"
                      v-for="(opt,index) in filterConditionTypeOption(item.columnName)"
                      :label="opt.name"
                      :value="opt.key">
                    {{ opt.name }}
                  </el-option>
                </el-select>
              </el-col>
              <template v-if="item.conditionType && ![3,4].includes(item.conditionType)">
                <!--        选项        -->
                <el-col
                    v-if="validTimeType(item.columnName)">
                  <template v-if="filterConditionSecondType(item.columnName) ==='select'">
                    <el-select
                        :value.sync="item.value"
                        placeholder="选项"
                        :multiple="[5,6].includes(item.conditionType) ? true : false"
                        :collapse-tags="[5,6].includes(item.conditionType) ? true : false"
                        @change="(value)=>handleRuleContent(item.ruleId,'value',value)">
                      <el-option
                          v-for="(opt,index) in filterConditionSecondOption(item.columnName)"
                          :key="index"
                          :label="opt.label"
                          :value="opt.value">
                      </el-option>
                    </el-select>
                  </template>
                  <template v-else-if="filterConditionSecondType(item.columnName) ==='country'">
                    <SelectCountry :value.sync="item.value"
                                   @input="(value)=>handleRuleContent(item.ruleId,'value',value)"/>
                  </template>
                  <template v-else>
                    <el-input :value.sync="item.value" placeholder="请输入"
                              @input="(value)=>handleRuleContent(item.ruleId,'value',value)"/>
                  </template>

                </el-col>
                <!--        时间范围       -->
                <el-col v-else style="width: 120px">
                  <!--        时间范围时间选项          -->
                  <template v-if="[1,2].includes(item.conditionType)">
                    <el-select :value.sync="item.dateType" @change="(value)=>{
                      item.timeRange = null
                      handleRuleContent(item.ruleId,'dateType',value)
                    }">
                      <el-option :value="1" label="具体日期范围"></el-option>
                      <el-option :value="2" label="动态日期范围"></el-option>
                    </el-select>
                  </template>
                  <!--        时间范围时间范围          -->
                  <template v-if="[7,8].includes(item.conditionType)">
                    <el-select :value.sync="item.dateType" @change="(value)=>{
                      item.timeRange = null
                      handleRuleContent(item.ruleId,'dateType',value)
                    }">
                      <el-option :value="3" label="当天之前"></el-option>
                      <el-option :value="4" label="当天之后"></el-option>
                      <el-option :value="5" label="具体日期"></el-option>
                      <el-option :value="6" label="日期字段"></el-option>
                    </el-select>
                  </template>

                </el-col>
              </template>
              <template>
                <el-col :span="10">
                  <el-date-picker
                      v-if="item.dateType===1"
                      :value.sync="item.timeRange"
                      size="small"
                      type="daterange"
                      range-separator="至"
                      start-placeholder="开始日期"
                      end-placeholder="结束日期"
                      clearable
                      format="yyyy-MM-dd"
                      value-format="yyyy-MM-dd"
                      align="left"
                      @input="(value)=>handleRuleContent(item.ruleId,'timeRange',value)">
                  </el-date-picker>
                  <el-select
                      v-if="item.dateType === 2"
                      :value.sync="item.timeRange"
                      @change="(value)=>handleRuleContent(item.ruleId,'timeRange',value)">
                    <el-option
                        v-for="(opt,index) in CustomerOperateData.timeRangeOption"
                        :key="index"
                        :value="opt.key"
                        :label="opt.name">
                    </el-option>
                  </el-select>
                </el-col>

              </template>
            </el-row>

          </div>
          <div>
            <el-tooltip placement="top" content="删除">
              <i class="del-icon pointer el-icon-remove-outline" @click="delRuleItem(item.ruleId)"></i>
            </el-tooltip>
          </div>
        </el-row>
        <el-row class="ml-16">
          <el-button type="text" icon="el-icon-circle-plus-outline" @click="addRuleItem">添加条件</el-button>
        </el-row>
      </template>
      <template v-else>
        <div class="flex-start">
          <div class="special-rule-content flex1 flex-middle">
            <template v-show="customerForm.conditionRuleContent.length>1">
              <div class="left-wrap flex-center flex-middle mr-8">
                <div class="line"></div>
                <div>
                  <el-radio-group size="mini" class="radio-btn">
                    <el-radio-button label="and">且</el-radio-button>
                    <el-radio-button label="or">或</el-radio-button>
                  </el-radio-group>
                </div>
              </div>
            </template>
            <div class="flex-column flex1">
              <el-row class="rule-list mt-10 flex-middle space-between flex1"
                      v-for="(item,index) in specialList"
                      :key="index">
                <div class="flex1 flex-middle">
                  <div class="ml-10">
                    <el-cascader
                        size="small"
                        :show-all-levels="false"
                        :options="customerRuleOption"
                        :props="{value:'columnName',label:'nickName',children:'children'}">
                    </el-cascader>
                  </div>
                </div>
                <div>
                  <el-tooltip placement="top" content="删除">
                    <i class="del-icon pointer el-icon-remove-outline" @click="delSpecialRuleItem(item.ruleId)"></i>
                  </el-tooltip>
                </div>
              </el-row>
              <el-row class="ml-16">
                <el-button type="text" icon="el-icon-circle-plus-outline" @click="addSpecialRuleItem">添加条件
                </el-button>
              </el-row>
            </div>

          </div>
        </div>


      </template>

    </el-form-item>
  </el-form>
</template>

<script>
import CustomerOperateData from '@/assets/data/customer-operate.json'
import {timeZoneList} from "@/assets/data/countryData";
import SelectCountry from "@/components/SelectCountry/index.vue";

export default {
  props: {
    showRange: {
      type: Boolean,
      default: false,
      required: false,
    },
    customerForm: {
      type: Object,
      default: () => {
        return {
          name: '',
          usageScope: 1,
        }
      },
      required: true,
    },
    ruleOption: {
      type: Array,
      default: () => [],
      required: false
    },
    visibilityOption: {
      type: Array,
      default: () => [],
      required: false
    },
    indexOpt: {
      type: Object,
      default: () => {
        return {
          groupOption: [],
          stageOption: [],
          originOption: [],
          poolGroupOption: [],
          poolReasonOption: [],
          tagOption: [],
        }
      },
      required: false,
    },
  },
  components: {
    SelectCountry
  },
  data() {
    return {
      CustomerOperateData,
      customerRuleOption: [],
      specialList: [
        {ruleId: 1},
        {ruleId: 2}
      ],
    }
  },
  computed: {
    isRuleAutoType() {
      const rule = this.customerForm.conditionRuleType
      if (rule !== 3) {
        return true
      }
      return this.customerForm.mode !== 'simple'
    },
  },
  watch: {
    ruleOption: {
      handler(newVal) {
        this.customerRuleOption = newVal
      },
      deep: true,
      immediate: true,
    },
    indexOpt: {
      handler(newVal) {
        this.CustomerOperateData.fieldList.map(val => {
          if (val['field_type'] === 'customer_packet') {
            val.options = newVal.groupOption.map(val => {
              return {
                value: val.id,
                label: val.name,
              }
            })
          } else if (val['field_type'] === 'customer_stage') {
            val.options = newVal.stageOption
          } else if (val['field_type'] === 'customer_source') {
            val.options = newVal.originOption || []
          } else if (val['field_type'] === 'publicleads_groups') {
            const valid = !newVal.poolGroupOption && !newVal.poolGroupOption.length
            if (valid) {
              return
            }
            val.value = newVal.poolGroupOption[0]?.id
            val.options = newVal.poolGroupOption.map(val => {
              return {
                value: val.id,
                label: val.name
              }
            })
          } else if (val['field_type'] === 'customer_tag') {
            const valid = !newVal.tagOption && !newVal.tagOption.length
            if (valid) {
              return
            }
            val.options = newVal.tagOption.map(val => {
              return {
                value: val.id,
                label: val.name,
              }
            })
          }

          return val
        })
      },
      deep: true,
      immediate: true,
    }
  },
  created() {
    this.CustomerOperateData.fieldList.map(val => {
      if (val['field_type'] === 'timezone') {
        val.options = timeZoneList
      }
    })
  },
  methods: {
    onInput(field, value) {
      this.updateFormValue({...this.customerForm, [field]: value})
    },
    updateFormValue(value) {
      this.$emit('update:customerForm', value)
    },
    handleRuleContent(ruleId, field, value) {
      let tempList = this.customerForm
      tempList.conditionRuleContent.map(val => {
        if (val.ruleId === ruleId) {
          val[field] = value
        }
        return val
      })
      this.updateFormValue(tempList)
    },
    addRuleItem() {
      let newList = this.customerForm
      newList.conditionRuleContent.push({
        ruleId: +new Date(),
        columnName: [],
        conditionType: null,
        dateType: null,
        timeRange: null,
        value: null
      })
      this.updateFormValue(newList)
    },
    delRuleItem(id) {
      let delList = this.customerForm
      delList.conditionRuleContent = delList.conditionRuleContent.filter(val => val.ruleId !== id)
      this.updateFormValue(delList)
    },
    addSpecialRuleItem() {
      this.specialList.push({
        ruleId: +new Date()
      })
    },
    delSpecialRuleItem(id) {
      this.specialList = this.specialList.filter(val => val.ruleId !== id)
    },
    filterConditionTypeOption(value) {
      const targetValue = this.generateConditionValue(value)
      if (!targetValue.length) {
        return []
      }
      const filterType = this.CustomerOperateData['fieldList'].filter(val => val.field_type === targetValue)[0]?.conditionType
      if (!filterType?.length) {
        return []
      }
      let conditionOption = []
      filterType.forEach(type => {
        this.CustomerOperateData['conditionTypeOption'].forEach(val => {
          if (type === val.key) {
            conditionOption.push(val)
          }
        })
      })
      return conditionOption
    },
    filterConditionSecondOption(value) {
      const targetValue = this.generateConditionValue(value)
      const filterOption = this.CustomerOperateData['fieldList'].filter(val => val.field_type === targetValue)[0]?.options
      if (!filterOption?.length) {
        return []
      }
      return filterOption
    },
    filterConditionSecondType(value) {
      const targetValue = this.generateConditionValue(value)
      if (!targetValue.length) {
        return []
      }
      const filterType = this.CustomerOperateData['fieldList'].filter(val => val.field_type === targetValue)[0]?.type
      if (!filterType) {
        return 'input'
      }
      return filterType
    },
    validTimeType(field) {
      const validArr = ['create_time', 'data_update_time', 'last_contacted_at', 'last_followup_at', 'last_follow_up_date']
      const fieldValid = Array.isArray(field) ? field[1] : field
      return !validArr.includes(fieldValid)
    },
    generateConditionValue(value) {
      let targetValue = ''
      if (Array.isArray(value)) {
        targetValue = value[value?.length - 1] || ''
      } else {
        targetValue = value
      }
      if (!targetValue) {
        return []
      }
      return targetValue
    }
  }
}
</script>

<style lang="scss" scoped>
.create-customer-form {
  ::v-deep .el-button--mini {
    padding: 7px 10px;
  }

  .special-rule-content {
    height: 100%;
    background: rgb(250, 250, 250);
    box-sizing: border-box;
    margin: 10px 0;
    padding: 6px;

    .left-wrap {
      width: 24px;
      position: relative;

      .line {
        width: 1px;
        height: 100%;
        position: absolute;
        background: rgb(191, 191, 191);
        top: 0;
        left: 50%;
      }

      .radio-btn {
        display: flex;
        flex-direction: column;

        ::v-deep .el-radio-button__inner {
          padding: 2px;
          border-radius: 0;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
