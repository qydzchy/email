<template>
  <div>
    <el-drawer :visible.sync="customerVisible" :wrapperClosable="false" destroy-on-close :show-close="false"
               @close="onHideDrawer">
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
        <ListCreateCustomerForm :showRange="true" :customer-form="formData"/>
        <div>
          <el-checkbox v-model="addSecondChecked">添加二级客群</el-checkbox>
          <div class="fs-14 mt-6 gray-text">二级客群是在以上条件规则上，附加更多条件组生成的客群</div>
          <el-form v-if="addSecondChecked">
            <el-form-item label="添加规则">
              <el-radio-group style="width: 100%" v-model="formData.secondRule" @change="handleAddRule">
                <el-radio label="auto">自动生成</el-radio>
                <el-radio label="manual">手动生成</el-radio>
              </el-radio-group>
              <div v-show="formData.secondRule==='auto'">
                根据单选或多选字段的选项生成二级客群
              </div>
            </el-form-item>
            <div v-show="formData.secondRule==='auto'">
              <el-form-item label="二级分群字段">
                <el-select></el-select>
              </el-form-item>
            </div>
            <div v-show="formData.secondRule==='manual'">
              <el-row class="second-card mb-20" v-for="(item,index) in manualFormList" :key="index">
                <div class="fs-15 mb-10">二级客群{{ index + 1 }}</div>
                <ListCreateCustomerForm :customer-form="item"/>
                <el-tooltip placement="top" content="删除">
                  <i class="del-icon pointer el-icon-remove-outline" @click="delSecondCustomer(item.id)"></i>
                </el-tooltip>
              </el-row>
              <el-button icon="el-icon-circle-plus-outline" round @click="addSecondCustomer">二级客群</el-button>
            </div>
          </el-form>
        </div>
      </div>
      <!--   operate     -->
      <div class="drawer-operate">
        <div class="wrap flex-middle flex-end">
          <el-button round @click="onHideDrawer">取消</el-button>
          <el-button type="primary" round>确认</el-button>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import ListCreateCustomerForm from "./ListCreateCustomerForm.vue";

const initFormData = {
  name: '',
  area: 'company',
  secondRule: 'auto',
  rule: 'all',
  mode: 'simple'
}
const initManualItem = {
  name: '',
  secondRule: 'auto',
  rule: 'all',
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
    }
  },
  components: {
    ListCreateCustomerForm
  },
  data() {
    return {
      customerVisible: false,
      formData: {
        ...initFormData,
        id: +new Date()
      },
      manualFormList: [
        {...initManualItem, id: +new Date()}
      ],
      addSecondChecked: false,
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
        this.formData = {
          ...this.formData,
          ...newVal
        }
      },
      deep: true,
      immediate: true
    },
  },
  methods: {
    handleAddRule(value) {
      if (value === 'manual' && !this.manualFormList.length) {
        this.manualFormList.push({...initManualItem, id: +new Date()})
      }
    },
    addSecondCustomer() {
      this.manualFormList.push({...initManualItem, id: +new Date()})
    },
    delSecondCustomer(id) {
      this.manualFormList = this.manualFormList.filter(val => val.id !== id)
    },
    onHideDrawer() {
      this.$emit('update:visible', false)
      this.$emit('onCancel')
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
