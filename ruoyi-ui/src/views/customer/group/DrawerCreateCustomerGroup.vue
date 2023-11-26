<template>
  <div>
    <el-drawer :visible.sync="customerVisible" :wrapperClosable="false" destroy-on-close :show-close="false">
      <template #title>
        <div class="header flex-middle space-between py-20 px-20">
          <div class="black-text">
            {{ formData.id ? '编辑' : '新建' }}自定义客户
          </div>
          <el-row class="flex-middle">
            <i class="el-icon-close pointer fs-16" @click="onHideDrawer"></i>
          </el-row>
        </div>
      </template>
      <div class="container">
        <ListCreateCustomerForm
            key="single"
            ref="single"
            :showRange="true"
            :customer-form="formData"
            :rule-option="customerRuleOption"
            :visibility-option="visibilityScopeOption"
            :index-opt="indexOpt"
            @update:customerForm="onUpdate"
        />
        <div>
          <el-checkbox v-model="formData.subgroupFlag">添加二级客群</el-checkbox>
          <div class="fs-14 mt-6 gray-text">二级客群是在以上条件规则上，附加更多条件组生成的客群</div>
          <el-form v-if="formData.subgroupFlag" :model="formData" ref="segmentFormRef" :rules="segmentFormRules">
            <el-form-item label="添加规则">
              <el-radio-group style="width: 100%" v-model="formData.additionRule" @change="handleAddRule">
                <el-radio :label="1">自动生成</el-radio>
                <el-radio :label="2">手动生成</el-radio>
              </el-radio-group>
              <div v-show="formData.additionRule===1">
                根据单选或多选字段的选项生成二级客群
              </div>
            </el-form-item>
            <div v-show="formData.additionRule===1">
              <el-form-item label="二级分群字段" prop="subGroupColumn">
                <el-select v-model="formData.subGroupColumn">
                  <el-option v-for="(sub,index) in secondChildFieldOption" :key="index" :value="sub.columnName" :label="sub.nickName"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item>
                <el-row v-if="secondChildFieldList.length">
                  <el-col>根据「关注人」的选项，共生成 {{ secondChildFieldList.length }} 个二级客群，客户数为 0 时不显示
                  </el-col>
                  <el-col v-for="group in secondChildFieldList" :key="group.id">
                    {{ group.name }}
                  </el-col>
                </el-row>
              </el-form-item>
            </div>
            <div v-show="formData.additionRule===2">
              <el-row class="second-card mb-20" v-for="(item,index) in formData.children" :key="index">
                <div class="fs-15 mb-10">二级客群{{ index + 1 }}</div>
                <ListCreateCustomerForm
                    :key="'multiple'+index"
                    :ref="'multiple'+index"
                    :customer-form="item"
                    :rule-option="customerRuleOption"
                    :visibility-option="visibilityScopeOption"
                    :index-opt.sync="indexOpt"
                    @update:customerForm="(value)=>onUpdateChild(item.ruleId,value)"
                />
                <el-tooltip placement="top" content="删除">
                  <i class="del-icon pointer el-icon-remove-outline" @click="delSecondCustomer(item.ruleId)"></i>
                </el-tooltip>
              </el-row>
              <el-button icon="el-icon-circle-plus-outline" round @click="addSecondCustomer">二级客群</el-button>
            </div>
          </el-form>
        </div>
      </div>
      <!--   operate     -->
      <div class="drawer-operate">
        <div class="wrap flex-middle space-between">
          <div>
            <el-button type="text" round plain>计算客群数</el-button>
            <el-button v-if="formData.customerCount" :disabled="true" type="text" round>{{ formData.customerCount }}个</el-button>
          </div>
          <div>
            <el-button round :loading="btnLoading" @click="onHideDrawer">取消</el-button>
            <el-button type="primary" round :loading="btnLoading" @click="onConfirm">确认</el-button>
          </div>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import ListCreateCustomerForm from "./ListCreateCustomerForm.vue";
import {addSegment, editSegment, getRuleField, getSubgroupColumn, getSubgroupColumnList} from "@/api/customer/segment";
import {deepClone} from "@/utils";
import {listDeptUsersTree} from "@/api/system/dept";
import {timeZoneList} from "@/assets/data/countryData";

const initFormData = {
  name: '',//客群名称
  usageScope: 1,//使用范围 1.公司共享 2.个人使用
  visibilityScope: null, //可见范围
  conditionRuleType: 1,//条件规则类型 1.满足全部条件 2.满足任一条件 3.自定义条件
  subgroupFlag: false,  //添加二级客群 0.未选 1.选中
  conditionRuleContent: [],//条件规则内容
  additionRule: 1, //添加规则 1.自动生成 2.手动添加
  customerCount: 0,//客户数量
  subGroupColumn: '',
  mode: 'simple'
}
export default {
  props: {
    visible: {
      type: Boolean,
      default: false,
      required: false,
    },
    row: {
      type: Object,
      default: () => {
      },
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
    ListCreateCustomerForm
  },
  data() {
    return {
      customerVisible: false,
      formData: {},
      customerRuleOption: [],
      visibilityScopeOption: [],
      tempAllDept: [],
      tempAllUser: [],
      btnLoading: false,
      defaultChooseIds: [],
      secondChildFieldOption: [],
      secondChildFieldList: [],//生成客群列表
      segmentFormRules:{
        subGroupColumn:[
          {required:true,message:'请选择二级分群字段',trigger:'change'}
        ]
      },
    }
  },
  watch: {
    visible: {
      handler(newVal) {
        this.customerVisible = newVal
      },
      immediate: true
    },
    row: {
      handler(newVal) {
        try {
          let children = newVal?.children || []
          let ruleContent = newVal.conditionRuleContent || '[]'
          ruleContent = JSON?.parse(ruleContent)
          ruleContent = (ruleContent && !ruleContent?.length) ? this.generateRuleContentDefault() : this.generateRuleContentSetRuleId(ruleContent)
          children = !children.length ? this.generateChildListDefault() : this.generateChildListSetRuleId(children)
          let visibilityScope = newVal.visibilityScope || null
          visibilityScope = this.generateVisibilityScopeValue(visibilityScope)
          let curRowData = {
            ...newVal,
            ruleId: +new Date(),
            visibilityScope: visibilityScope,
            conditionRuleContent: ruleContent,
            children: children
          }
          this.formData = {
            ...this.generateInitValue(),
            ...deepClone(curRowData)
          }
        } catch (e) {
          console.error(e)
        }
      },
      deep: true,
      immediate: true
    },
    "formData.subGroupColumn": {
      handler(newVal) {
        if (newVal) {
          this.getSubGroupList()
        }
      },
    }
  },
  mounted() {
    this.getRuleColumn()
    this.getCommonTree()
    this.getSubGroup()
  },
  methods: {
    // 获取规则
    async getRuleColumn() {
      try {
        const res = await getRuleField()
        if (res.code === 200) {
          this.customerRuleOption = res.data
        }
      } catch (e) {
        console.error(e)
      }
    },
    // 人员和部门数据
    async getCommonTree() {
      try {
        const res = await listDeptUsersTree()
        if (res.code === 200) {
          this.visibilityScopeOption = this.generateVisibilityScopeOption(res.data)
        }
      } catch {
      }
    },
    // 获取二级分群字段
    async getSubGroup() {
      try {
        const res = await getSubgroupColumn()
        if (res.code === 200) {
          this.secondChildFieldOption = res.data
        }
      } catch {
      }
    },
    async getSubGroupList() {
      if (this.formData.subGroupColumn === 'timezone') {
        this.secondChildFieldList = timeZoneList.map(val => {
          return {
            id: val.value,
            name: val.label
          }
        })
        return
      }
      try {
        const res = await getSubgroupColumnList({
          columnName: this.formData.subGroupColumn
        })
        if (res.code === 200) {
          this.secondChildFieldList = res.data
        }
      } catch {
      }
    },
    // 添加客群规则请求
    async addSegmentForm(config) {
      try {
        this.btnLoading = true
        const res = await addSegment({...config}).finally(() => {
          this.btnLoading = false
        })
        if (res.code === 200) {
          this.$message.success('添加客群成功')
          this.$emit('onConfirm')
        }
      } catch {
      }
    },
    // 编辑客群规则请求
    async editSegmentForm(config) {
      try {
        const res = await editSegment({...config})
        if (res.code === 200) {
          this.$message.success('修改客群成功')
          this.$emit('onConfirm')
        }
      } catch {
      }
    },
    // 校验表单
    async validForm(){
      let validList = []
      const {subgroupFlag, additionRule, children} = this.formData
      const resolve = await this.$refs.single.handleValidForm()
      validList.push(resolve)

      if(subgroupFlag){
        if(additionRule===1){
          this.$refs.segmentFormRef.validate(valid=>{
            validList.push(valid)
          })
        }else if(additionRule===2){
          if(Array.isArray(children) && children.length){
            for(let i = 0;i < children.length;i++){
              const subResolve = await this.$refs['multiple' + i]?.[0].handleValidForm()
              validList.push(subResolve)
            }
          }
        }
      }
      
      return validList.every(val=>val)
    },
    onConfirm() {
      const validResult = this.validForm()
      if(!validResult){
        return
      }
      const {
        id,
        name,
        visibilityScope,
        usageScope,
        conditionRuleType,
        subGroupColumn,
        subgroupFlag,
        conditionRuleContent,
        additionRule,
        children,
        customerCount,
      } = this.formData
      let config = {
        name,
        usageScope,
        conditionRuleType,
        customerCount,
        visibilityScope: this.generateVisibilityScopeFormat(visibilityScope),
        subgroupFlag: Number(subgroupFlag),
        conditionRuleContent: this.generateConditionRuleContent(conditionRuleContent, conditionRuleType),
      }
      // 勾选添加二级客群
      if (subgroupFlag) {
        let newChildren = deepClone([...children])
        newChildren.map(val => {
          delete val.ruleId
          delete val.mode
          delete val.customerCount
          val.subgroupFlag = Number(val.subgroupFlag)
          val.conditionRuleContent = this.generateConditionRuleContent(val.conditionRuleContent, val.conditionRuleType)
          return val
        })
        config = {
          ...config,
          additionRule,// 1自动添加,2手动添加
          subGroupColumn:additionRule===1 ? subGroupColumn : null,
          children: additionRule===2 ? newChildren : [],
        }
      }
      if (!this.row?.id) {
        config = {...config, parentId: -1}
        this.addSegmentForm(config)
        return
      }
      config = {...config, id}
      this.editSegmentForm(config)
    },
    handleAddRule(value) {
      if (value === 2 && !this.formData.children.length) {
        this.formData.children.push({...this.generateInitValue()})
      }
    },
    addSecondCustomer() {
      let data = {...this.generateInitValue()}
      delete data.visibilityScope
      this.formData.children.push({...data})
    },
    onUpdate(value) {
      this.formData = Object.assign({}, this.formData, value)
    },
    onUpdateChild(id, value) {
      const idx = this.formData.children.findIndex(val => val.ruleId === id)
      this.$set(this.formData.children, idx, {...value})
    },
    delSecondCustomer(id) {
      this.formData.children = this.formData.children.filter(val => val.ruleId !== id)
    },
    // 隐藏抽屉
    onHideDrawer() {
      this.$emit('onCancel')
    },
    generateRuleContentDefault() {
      return [{ruleId: +new Date()}]
    },
    generateRuleContentSetRuleId(arr) {
      return arr.map((val, index) => {
        val.ruleId = index
        return val
      })
    },
    generateChildListDefault() {
      let childFormData = deepClone(initFormData)
      delete childFormData.visibilityScope
      return [{
        ...childFormData,
        ruleId: +new Date(),
        conditionRuleContent: [{
          ruleId: +new Date(),
          columnName: null,
          conditionType: null,
          dateType: null,
          timeRange: null,
          value: null
        }],
      }]
    },
    generateChildListSetRuleId(arr) {
      return arr.map((val, index) => {
        val.ruleId = index
        let ruleContentChild = val.conditionRuleContent || '[]'
        ruleContentChild = JSON?.parse(ruleContentChild)
        val.conditionRuleContent = (ruleContentChild && !ruleContentChild?.length) ? this.generateRuleContentDefault(ruleContentChild) : this.generateRuleContentSetRuleId(ruleContentChild)
        return val
      })
    },
    generateInitValue() {
      return {
        ...deepClone(initFormData),
        ruleId: +new Date(),
        visibilityScope: this.defaultChooseIds,
        conditionRuleContent: [
          {
            ruleId: +new Date(),
            columnName: null,
            conditionType: null,
            dateType: null,
            timeRange: null,
            value: null
          }
        ],
      }
    },
    generateConditionRuleContent(arr, type) {
      const mapType = {
        1: 'and',
        2: 'or',
        3: '',
      }
      let newArr = []
      arr.forEach(val => {
        delete val.ruleId
        val.andOr = mapType[type]
        if (Array.isArray(val.columnName)) {
          val.columnName = val.columnName[val.columnName.length - 1]
        }
        newArr.push(val)
      })
      return JSON.stringify(newArr)
    },
    generateVisibilityScopeOption(list) {
      let ids = []
      let allDept = {
        id: 'allDept',
        name: '全部部门',
        children: []
      }
      let allUser = {
        id: 'allUser',
        name: '全部人员',
        children: []
      }
      const deepSearch = arr => arr.forEach(val => {
        ids.push(val.id)
        if (val.type === 1) {
          allDept.children.push({
            id: val.id,
            name: val.name
          })
        } else if (val.type === 2) {
          allUser.children.push({
            id: val.id,
            name: val.name
          })
        }
        if (val.children && val.children.length) {
          deepSearch(val.children)
        }
      })
      deepSearch(list)
      this.tempAllDept = allDept.children
      this.tempAllUser = allUser.children
      this.defaultChooseIds = ids
      this.formData.visibilityScope = this.defaultChooseIds
      return [{
        id: 'all',
        name: '全公司可见',
        children: [allDept, allUser]
      }]
    },
    generateVisibilityScopeValue(scopeData) {
      let scope = JSON?.parse(scopeData)
      if (!scope) {
        return []
      }
      // 部门
      let dept = []
      if (scope?.dept?.allFlag) {
        this.tempAllDept.forEach(val => {
          dept.push(val.id)
        })
      } else {
        dept = scope?.dept?.deptIds || []
      }
      // 用户
      let user = []
      if (scope?.user?.allFlag) {
        this.tempAllUser.forEach(val => {
          user.push(val.id)
        })
      } else {
        dept = scope?.user?.userIds || []
      }
      return [...dept, ...user]

    },
    generateVisibilityScopeFormat(ids) {
      let templateScope = {
        "dept": { //部门
          "allFlag": true, // true所有部门 false指定部门
          "deptIds": [] //指定部门ID
        },
        "user": { //用户
          "allFlag": true, //true所有用户 false指定用户
          "userIds": [] //指定用户ID
        }
      }
      // 校验勾选部门
      const validDept = this.tempAllDept.every(val => ids.includes(val.id))
      if (validDept) {
        templateScope.dept.allFlag = true
      } else {
        templateScope.dept.allFlag = false
        let deptIds = this.tempAllDept.filter(val => ids.includes(val.id))
        templateScope.dept.deptIds = deptIds.map(val => val.id)
      }
      // 校验勾选用户
      const validUser = this.tempAllUser.every(val => ids.includes(val.id))
      if (validUser) {
        templateScope.user.allFlag = true
      } else {
        templateScope.user.allFlag = false
        let userIds = this.tempAllUser.filter(val => ids.includes(val.id))
        templateScope.user.userIds = userIds.map(val => val.id)
      }
      return JSON.stringify(templateScope)
    },
  }
}
</script>

<style lang="scss" scoped>

.container {
  .second-card {
    padding: 16px;
    background-color: rgb(247, 248, 251);
    position: relative;
    cursor: pointer;

    .del-icon {
      position: absolute;
      right: 20px;
      top: 25px;
      color: #0a6aff;
      display: none;
    }

    &:hover {
      background-color: rgb(236, 249, 255);

      .del-icon {
        display: block;
      }
    }
  }
}

::v-deep .el-drawer.rtl {
  width: 840px !important;
}

.header {
  border-bottom: 1px solid #f0f0f0;
}

::v-deep .el-drawer__header {
  padding: 0;
  margin: 0;
}


.container {
  padding: 0 20px;

  .collapse {
    height: 40px;
    color: rgba(104, 108, 115);
    background-color: rgba(247, 248, 251);
  }
}

::v-deep .el-drawer__body {
  padding-bottom: 100px;
  position: relative;
  width: inherit;

  .drawer-operate {
    width: inherit;
    box-sizing: border-box;
    position: fixed;
    bottom: 0;
    padding: 16px 24px;
    background-color: #ffffff;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .drawer-operate > .wrap {
    width: 100%;
  }
}

::v-deep .el-form-item__label {
  padding: 0;
}

::v-deep .el-form-item {
  margin-bottom: 10px;
}

::v-deep .el-form-item__content {
  .el-select, .el-cascader {
    width: 100%;
  }
}


</style>
