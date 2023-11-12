<template>
  <div>
    <el-drawer :visible.sync="customerVisible" :wrapperClosable="false" destroy-on-close :show-close="false"
               @close="onHideDrawer">
      <template #title>
        <div class="header flex-middle space-between py-20 px-20">
          <div class="black-text">
            {{ formData.id ? '编辑' : '新建' }}客户
          </div>
          <el-row class="flex-middle">
            <el-row class="flex-middle gap-16">
              <el-tooltip placement="top" content="关注">
                <svg-icon icon-class="like"/>
              </el-tooltip>
              <i class="el-icon-close pointer fs-16" @click="onHideDrawer"></i>
            </el-row>
          </el-row>
        </div>
      </template>
      <div class="container">
        <el-row type="flex">
          <!--    常用信息     -->
          <el-col :span="10">
            <div>
              <div class="fs-16 bold my-10">
                <span>公司常用信息</span>
              </div>
              <formCreate v-model="customerForm" :rule="rule" :option="option"/>
            </div>
            <div v-show="showOtherForm">
              <div class="fs-16 bold my-10">
                <span>公司其他信息</span>
              </div>
              <formCreate v-model="customerOtherForm" :rule="otherRule" :option="option"/>
            </div>
            <div>
              <div class="collapse flex-middle flex-center fs-12 mt-10 pointer" @click="showOtherForm = !showOtherForm">
                {{ showOtherForm ? '收起' : '展开全部(选填)' }}
                <i class="ml-6" :class="showOtherForm ? 'el-icon-arrow-up':'el-icon-arrow-down'"></i>
              </div>
            </div>
          </el-col>
          <!--    联系人     -->
          <el-col :span="14" class="px-16">
            <ContactCard ref="contact-card" :contact-list="[]"/>
          </el-col>
        </el-row>
      </div>
      <!--   operate     -->
      <div class="drawer-operate">
        <div class="wrap flex-middle flex-end">
          <el-button round :loading="btnLoading" @click="onHideDrawer">取消</el-button>
          <el-button type="primary" round :loading="btnLoading" @click="onConfirm">确认</el-button>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import {UsuallyInfoRule, OtherInfoRule} from './CreateCustomerOption'
import {formOption} from "@/constant/form"
import ContactCard from './CustomerContactCard.vue'
import {addCustomer} from "@/api/customer/publicleads";

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
    ContactCard,
  },
  data() {
    return {
      customerVisible: false,
      formData: {
        id: ''
      },
      customerForm: {},
      rule: [...UsuallyInfoRule],
      customerOtherForm: {},
      otherRule: [...OtherInfoRule],
      option: {
        ...formOption,
        form: {
          ...formOption.form,
          labelPosition: 'top'
        }
      },
      showOtherForm: false,
      btnLoading: false,
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
    async addCustomerPrivate(data) {
      try {
        this.btnLoading = true
        const res = await addCustomer({...data}).finally(() => {
          this.btnLoading = false
        })
        if (res.code === 200) {
          console.log(res)
        }
      } catch {
      }
    },
    onConfirm() {
      this.customerForm.validate(val => {
        if (val) {
          const contactList = this.$refs['contact-card'].getInnerData()
          const customerForm = this.customerForm.formData()
          const otherForm = this.customerOtherForm.formData()
          const data = {
            ...customerForm,
            ...otherForm,
            contactList,
            seaType: 1,
            customerNoType: 1,
          }
          this.addCustomerPrivate(data)
        }
      })

    },
    onHideDrawer() {
      this.$emit('update:visible', false)
    },
  }
}
</script>

<style lang="scss" scoped>
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
