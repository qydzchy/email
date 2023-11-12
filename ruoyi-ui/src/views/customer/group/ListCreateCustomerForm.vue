<template>
  <el-form class="create-customer-form" :model="innerForm">
    <el-form-item label="客群名称">
      <el-input placeholder="请输入" size="small" v-model="innerForm.name" type="text" maxlength="32"
                show-word-limit></el-input>
    </el-form-item>
    <template v-if="showRange">
      <el-row class="fs-14 flex-column">
        <el-col>
          <span>使用范围</span>
          <el-tooltip
              content="公司统一标准请选择【公司共享】，个人使用习惯可选择【个人使用】。客群保存成功后，使用范围不可修改。">
            <i class="el-icon-question ml-8"></i>
          </el-tooltip>
        </el-col>
        <el-col class="mt-16 mb-20">
          <el-radio-group v-model="innerForm.area">
            <el-radio :label="1">公司共享</el-radio>
            <el-radio :label="2">个人使用</el-radio>
          </el-radio-group>
        </el-col>
      </el-row>
      <el-form-item label="可见范围" v-if="innerForm.area==='company'">
        <TreeSelectNext
            :default-props="{ children: 'children',label: 'label'}"
            :echo-data="[]" :tree-data="[]"/>
        <div>注：公司共享的客群默认选择全公司可见，若只选择部门/人员后，该客群不可被应用于移入公海规则。</div>
      </el-form-item>
    </template>

    <el-form-item label="条件规则">
      <el-row style="width:100%;height:28px" class="flex-middle space-between">
        <el-radio-group v-model="customerForm.rule" class="flex1">
          <el-radio label="all">满足全部条件</el-radio>
          <el-radio label="my">满足任一条件</el-radio>
          <el-radio label="auto">
            <span>自定义</span>
            <el-tooltip placement="top"
                        content="满足全部条件使用AND，满足任一条件使用OR，优先运算使用（），条件直接引用序号">
              <i class="el-icon-question"></i>
            </el-tooltip>
          </el-radio>
        </el-radio-group>
        <el-radio-group size="mini" v-if="customerForm.rule==='auto'" v-model="customerForm.mode">
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
        <el-row class="my-10" v-if="innerForm.rule==='auto'&& innerForm.mode==='high'">
          <el-input size="small" placeholder="例如：( 1 and 2 ) or ( 3 and 4 )"></el-input>
        </el-row>
        <el-row class="rule-list mt-10 flex-middle space-between" v-for="(item,index) in innerForm.ruleList"
                :key="index">
          <div class="flex1 flex-middle">
            <span class="mr-10">{{ index + 1 }}</span>
            <el-row type="flex" :gutter="10">
              <el-col style="width: 140px">
                <el-cascader
                    v-model="item.ruleStep1"
                    size="small"
                    filterable
                    :show-all-levels="false"
                    :emitPath="false"
                    :options="CustomerRuleOption"
                    :props="{value:'id',label:'name',children:'children'}"
                    @change="()=>{
                    item.ruleStep2 = ''
                  }"
                >
                </el-cascader>
              </el-col>
              <el-col style="width: 140px" v-if="item.ruleStep1">
                <el-select v-model="item.ruleStep2" placeholder="运算">
                  <el-option v-for="(opt,index) in filterOption(item.ruleStep1)" :key="index" :label="opt.name"
                             :value="opt.key">
                    {{ opt.name }}
                  </el-option>
                </el-select>
              </el-col>
            </el-row>
          </div>
          <div>
            <el-tooltip placement="top" content="删除">
              <i class="del-icon pointer el-icon-remove-outline" @click="delRuleItem(item.id)"></i>
            </el-tooltip>
          </div>
        </el-row>
        <el-row class="ml-16">
          <el-button type="text" icon="el-icon-circle-plus-outline" @click="addRuleItem">添加条件</el-button>
        </el-row>
      </template>
      <template v-else>
        <el-row class="rule-list mt-10 flex-middle space-between" v-for="(item,index) in innerForm.ruleList"
                :key="index">
          <div class="flex1 flex-middle">
            <span>|</span>
            <div class="ml-10">
              <el-cascader
                  size="small"
                  :show-all-levels="false"
                  :options="CustomerRuleOption"
                  :props="{value:'id',label:'name',children:'children'}"></el-cascader>

            </div>
          </div>
          <div>
            <el-tooltip placement="top" content="删除">
              <i class="del-icon pointer el-icon-remove-outline" @click="delRuleItem(item.id)"></i>
            </el-tooltip>
          </div>
        </el-row>
        <el-row class="ml-16">
          <el-button type="text" icon="el-icon-circle-plus-outline" @click="addRuleItem">添加条件</el-button>
        </el-row>
      </template>

    </el-form-item>
  </el-form>
</template>

<script>
import TreeSelectNext from "@/components/TreeSelectNext/index.vue";
import CustomerRuleOption from '@/assets/data/customer.json'
import CustomerOperateData from '@/assets/data/customer-operate.json'

const innerFormValue = {
  name: '',
  area: 'company',
  rule: 'all',
  mode: 'simple',
  ruleList: [
    {
      id: +new Date(),
      ruleStep1: '',
      ruleStep2: '',
      ruleStep3: '',
      ruleStep4: '',
    }
  ]
}
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
          area: 'company',
          rule: 'all',
          mode: 'simple'
        }
      },
      required: true,
    },
  },
  components: {
    TreeSelectNext,
  },
  data() {
    return {
      innerForm: {},
      CustomerRuleOption,
      CustomerOperateData
    }
  },
  computed: {
    isRuleAutoType() {
      const rule = this.innerForm.rule
      if (rule !== 'auto') {
        return true
      }
      return this.innerForm.mode !== 'simple'
    },
  },
  watch: {
    customerForm: {
      handler(newVal) {
        this.innerForm = {
          ruleList: [
            {id: +new Date()}
          ],
          ...innerFormValue,
          ...newVal,
        }
      },
      deep: true,
      immediate: true
    }
  },
  methods: {
    addRuleItem() {
      this.innerForm.ruleList.push({
        id: +new Date(),
        ruleStep1: '',
        ruleStep2: '',
        ruleStep3: '',
        ruleStep4: '',
      })
    },
    delRuleItem(id) {
      this.innerForm.ruleList = this.innerForm.ruleList.filter(val => val.id !== id)
    },
    filterOption(value) {
      const targetValue = value[value.length - 1]
      let step2Type = ''
      const deepSearchType = arr => {
        arr.forEach(val => {
          if (val.id === targetValue) {
            step2Type = +val?.field_type || ''
            return
          }
          if (val.children && val.children.length) {
            deepSearchType(val.children)
          }
        })
      }
      deepSearchType(CustomerRuleOption)
      if (!step2Type) {
        return []
      }
      const filterType = this.CustomerOperateData['field_type_operator_map'].filter(val => val.field_type === step2Type)?.[0]?.operators
      if (!filterType?.length) {
        return []
      }
      return this.CustomerOperateData['operators'].filter(val => [...filterType].includes(val.key))
    }
  }
}
</script>

<style lang="scss" scoped>
.create-customer-form {
  ::v-deep .el-button--mini {
    padding: 7px 10px;
  }
}
</style>
