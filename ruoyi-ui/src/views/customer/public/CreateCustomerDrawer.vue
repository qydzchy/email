<template>
  <div>
    <el-drawer :visible.sync="customerVisible" :wrapperClosable="false" destroy-on-close :show-close="false"
               @close="onHideDrawer">
      <template #title>
        <div class="header flex-middle space-between py-20 px-20">
          <div class="black-text">
            {{ row.id ? '编辑' : '新建' }}公海客户
          </div>
          <el-row class="flex-middle">
            <el-row class="flex-middle gap-16">
              <el-tooltip placement="top" content="关注" v-if="row && row.id">
                <svg-icon class="pointer" icon-class="like"/>
              </el-tooltip>
              <i class="el-icon-close pointer fs-16" @click="onHideDrawer"></i>
            </el-row>
          </el-row>
        </div>
      </template>
      <div class="container" v-loading="containerLoading">
        <el-row type="flex">
          <!--    常用信息     -->
          <el-col :span="10">
            <div>
              <div class="fs-16 bold my-10">
                <span>公司常用信息</span>
              </div>
              <formCreate v-model="customerForm" :value.sync="customerFormValue" :rule="rule" :option="option"
                          @change="handleCountry"/>
            </div>
            <div v-show="showOtherForm">
              <div class="fs-16 bold my-10">
                <span>公司其他信息</span>
              </div>
              <formCreate v-model="customerOtherForm" :value.sync="customerOtherFormValue" :rule="otherRule"
                          :option="option"/>
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
            <ContactCard ref="contact-card" :contact-list="contactList"/>
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
import {addCustomer, editCustomer} from "@/api/customer/publicleads";
import {deepClone} from "@/utils";

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
        return {
          id: ''
        }
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
        }
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
      customerForm: {},
      customerFormValue: {},
      rule: [...UsuallyInfoRule],
      customerOtherForm: {},
      customerOtherFormValue: {},
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
      containerLoading: false,
      contactList: [],
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
          if (newVal?.id) {
            this.customerFormValue = {...deepClone(newVal)}
            this.customerOtherFormValue = {...deepClone(newVal)}
            this.contactList = deepClone(newVal.contactList)
          }
        } catch (e) {
          console.error(e)
        }
      },
      deep: true,
      immediate: true
    },
    indexOpt: {
      handler(newVal) {
        this.rule.map(val => {
          if (val.field === 'packetId') {
            val.props.data = newVal.groupOption || []
          } else if (val.field === 'stageId') {
            val.options = newVal.stageOption.map(val => {
              return {
                value: val.id,
                label: val.name,
                'slot': function ($h) {
                  return $h('div', {
                    style: {
                      width: 'max-content',
                      backgroundColor: val.color,
                      color: 'white',
                      padding: '0 10px',
                      borderRadius: '4px',
                    }
                  }, [val.name])
                }
              }
            })
          } else if (val.field === 'origin') {
            val.props.data = newVal.originOption || []
          } else if (val.field === 'poolGroup') {
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
          }
          return val
        })
      },
      deep: true
    }
  },
  methods: {
    async addCustomerPrivate(data) {
      try {
        this.btnLoading = true
        this.containerLoading = true
        const res = await addCustomer({...data}).finally(() => {
          this.btnLoading = false
          this.containerLoading = false
        })
        if (res.code === 200) {
          this.$message.success('添加成功')
          this.$emit('load')
        }
      } catch {
      }
    },
    async editCustomerPrivate(data) {
      this.btnLoading = true
      this.containerLoading = true
      try {
        const res = await editCustomer({...data}).finally(() => {
          this.btnLoading = false
          this.containerLoading = false
        })
        if (res.code === 200) {
          this.$message.success('修改成功')
          this.$emit('load')
        }
      } catch {
      }
    },
    onConfirm() {
      this.customerForm.validate(val => {
        if (val) {
          let contactList = this.$refs['contact-card'].getInnerData()
          contactList = contactList.map(val => {
            delete val.show
            val.primaryContactFlag = +val.primaryContactFlag
            return val
          })
          const customerForm = this.customerForm.formData()
          const otherForm = this.customerOtherForm.formData()
          let data = {
            ...customerForm,
            ...otherForm,
            contactList,
            seaType: 2,
            rating: +customerForm.rating,
            countryRegion: customerForm.countryRegion?.join('/') || undefined
          }
          if (!this.row.id) {
            data.contactList = data.contactList.map(val => {
              delete val.id
              return val
            })
            this.addCustomerPrivate(data)
          } else {
            this.editCustomerPrivate(data)
          }

        }
      })

    },
    onHideDrawer() {
      this.customerFormValue = {}
      this.customerOtherFormValue = {}
      this.$emit('update:visible', false)
    },
    handleCountry(filed, value) {
      const country = value || []
      if (!this.customerOtherForm.formData) {
        return
      }
      const mapTimeZone = {
        'CN': 8
      }
      const tempOpt = this.customerOtherForm.getRule('timezone').tempOptions
      if (country[0]) {
        this.customerOtherForm.updateRule('timezone', {
          options: tempOpt.filter(val => val.value === mapTimeZone[country]),
          value: mapTimeZone[country]
        })
      } else {
        this.customerOtherForm.updateRule('timezone', {options: tempOpt, value: ''})
      }
    },
    generateContactList(arr) {
      if (arr && !arr.length) {
        return []
      }
      return arr.map(val => {
        val.phone = val.phone ? JSON?.parse(val.phone) : []
        val.socialPlatform = val.socialPlatform ? JSON?.parse(val.socialPlatform) : []
        return val
      })
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
