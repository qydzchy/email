<template>
  <div class="datum-tab">
    <el-row class="py-10 bottom-line" v-if="options.isShowInfo">
      <CollapseWrap isCollapse :height="0">
        <template #header>
          <div class="flex-middle space-between">
            <div>主要联系人信息</div>
            <div>
              <el-tooltip content="添加/编辑">
                <i class="el-icon-edit pointer" @click.stop="contactVisible=true"></i>
              </el-tooltip>
            </div>
          </div>
        </template>
        <div class="collapse-content-box">
          <div class="container py-8">
            <div class="main px-16 py-12">
              <div class="flex-middle space-between">
                <span class="fs-14 bold">{{ contactRow.nickName }}</span>
                <el-row type="flex" :gutter="8">
                  <el-col>
                    <el-tooltip placement="top" content="往来邮件">
                      <i class="el-icon-postcard"></i>
                    </el-tooltip>
                  </el-col>
                  <el-col>
                    <el-tooltip placement="top" content="发送邮件">
                      <i class="el-icon-message"></i>
                    </el-tooltip>
                  </el-col>
                </el-row>
              </div>
              <div class="fs-14 my-10 flex-start flex-wrap" v-show="contactRowChecked">
                <template v-for="(contact,index) in contactFieldList">
                  <div class="wrap"
                       v-if="contactRow[contact.field] && !['socialPlatform','phone'].includes(contact.field)"
                       :key="index">
                    <div>{{ contact.name }}</div>
                    <div class="flex-middle" :class="{'copy-text':contact.isCopy}">
                            <span v-if="contact.mapValue">
                              {{ contact.mapValue[contactRow[contact.field]] }}
                            </span>
                      <span v-else>
                              {{ contactRow[contact.field] }}
                            </span>
                      <i v-if="contact.isCopy" class="el-icon-copy-document pl-4"
                         @click="onCopy(contactRow[contact.field])"></i>
                    </div>
                  </div>
                  <div class="wrap" v-else-if="contactRow[contact.field]">
                    <div>{{ contact.name }}</div>
                    <div class="flex-column gap-10">
                      <div class="flex-start" :class="{'copy-text': subItem[contact.childField[1]]}"
                           v-for="(subItem,subIdx) in contactRow[contact.field]">
                              <span :key="subIdx">
                                {{ subItem[contact.childField[0]] || '---' }}
                              </span>
                        <span v-if="contact.field==='phone'">-</span>
                        <span v-else class="pl-10"></span>
                        <span>
                                 {{ subItem[contact.childField[1]] || '---' }}
                              </span>
                        <i v-if="subItem[contact.childField[1]]" class="el-icon-copy-document pl-4"
                           @click="onCopy(generateDiffCopy(contact.field,subItem[contact.childField[0]],subItem[contact.childField[1]]))"></i>
                      </div>
                    </div>
                  </div>
                </template>
              </div>
            </div>
            <div class="footer flex-middle flex-center fs-12 mt-10">
            <span class="pointer" @click="contactRowChecked=!contactRowChecked">
              {{ contactRowChecked ? '收起' : '展开' }}
              <i :class="contactRowChecked ? 'el-icon-arrow-up':'el-icon-arrow-down'"></i></span>
            </div>
          </div>
        </div>
      </CollapseWrap>


    </el-row>
    <el-row class="py-10 bottom-line">
      <CollapseWrap isCollapse :height="480">
        <template #header>
          <div class="flex-middle space-between">
            <div class="bold">公司常用信息</div>
            <div>
              <i class="el-icon-setting" @click.stop="()=>{}"></i>
            </div>
          </div>
        </template>
        <div class="my-10 info-card">
          <el-row class="flex-wrap" type="flex" :gutter="10">
            <el-col class="wrap" v-for="(usually,index) in usuallyInfo" :key="index">
              <div>
                {{ usually.label }}
                <el-tooltip v-if="usually.showTooltip" placement="top">
                  <div slot="content" v-html="usually.tooltipText"></div>
                  <i class="el-icon-question"></i>
                </el-tooltip>
              </div>
              <CellOperate
                  class="pt-6"
                  :text="usually.value"
                  :show-form="usually.show"
                  :type="usually.type"
                  :curValue.sync="usually.value"
                  :show-copy-icon="false"
                  :form-option="usually.options"
                  @onInput="(val)=>handleSetValue('usuallyInfo',usually.field,val)"
                  @onChange="(val)=>handleSetValue('usuallyInfo',usually.field,val)"
                  @onEnter="handleEnter('usuallyInfo',usually.field,false)"
                  @onBlur="handleEnter('usuallyInfo',usually.field,false)"
                  @onEdit="onShowForm('usuallyInfo',usually.field,true)">
              </CellOperate>
            </el-col>
          </el-row>
        </div>
      </CollapseWrap>

    </el-row>
    <el-row class="py-10 bottom-line">
      <CollapseWrap isCollapse :height="260">
        <template #header>
          <div class="flex-middle space-between">
            <div class="bold">公司其他信息</div>
          </div>
        </template>
        <div class="my-10 info-card">
          <el-row class="flex-wrap" type="flex" :gutter="10">
            <el-col class="wrap" v-for="(other,index) in otherInfo" :key="index">
              <div>{{ other.label }}</div>
              <CellOperate
                  :text="other.value"
                  :show-form="other.show"
                  :curValue.sync="other.value"
                  :type="other.type"
                  :show-copy-icon="false"
                  :form-option="other.options"
                  @onInput="(val)=>handleSetValue('otherInfo',other.field,val)"
                  @onChange="(val)=>handleSetValue('otherInfo',other.field,val)"
                  @onEnter="handleEnter('otherInfo',other.field,false)"
                  @onBlur="handleEnter('otherInfo',other.field,false)"
                  @onEdit="onShowForm('otherInfo',other.field,true)">
              </CellOperate>
            </el-col>
          </el-row>
        </div>
      </CollapseWrap>
    </el-row>
    <el-row class="py-10 bottom-line">
      <CollapseWrap isCollapse :height="400">
        <template #header>
          <div class="flex-middle space-between">
            <div class="bold">跟进信息</div>
          </div>
        </template>
        <div class="my-10 echo-info">
          <el-row class="flex-wrap mt-10" type="flex" :gutter="10">
            <el-col class="wrap" v-for="(follow,index) in followInfo" :key="index">
              <div>
                {{ follow.label }}
                <el-tooltip v-if="follow.showTooltip" placement="top">
                  <template slot="content">
                    <div v-html="follow.showTooltip"></div>
                  </template>
                  <i class="el-icon-question"></i>
                </el-tooltip>
              </div>
              <div class="flex-middle space-between py-5">
                <span>{{ row[follow.field] || '---' }}</span>
              </div>
            </el-col>
          </el-row>
        </div>
      </CollapseWrap>
    </el-row>
    <el-row class="py-10">
      <CollapseWrap :height="300">
        <template #header>
          <div class="flex-middle space-between">
            <div class="bold">系统信息</div>
          </div>
        </template>
        <div class="my-10 echo-info">
          <el-row class="flex-wrap mt-10" type="flex" :gutter="10">
            <el-col class="wrap" v-for="(sys,index) in sysInfo" :key="index">
              <div>
                {{ sys.label }}
                <el-tooltip v-if="sys.showTooltip" placement="top">
                  <template #content>
                    <div v-html="sys.tooltipText"></div>
                  </template>
                  <i class="el-icon-question"></i>
                </el-tooltip>
              </div>
              <div class="flex-middle space-between py-5"  v-if="!['createId','updateId'].includes(sys.field)">
                <span>{{ row[sys.field] || '---' }}</span>
              </div>
              <div v-else class="flex-middle space-between py-5" >
                <span>{{ generateMember(row[sys.field]) || '---' }}</span>
              </div>
            </el-col>
          </el-row>
        </div>
      </CollapseWrap>
    </el-row>
    <CustomerContactDrawer :visible.sync="contactVisible" :row-data="row" @onConfirm="onConfirmContact"/>
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import CustomerContactDrawer from "./CustomerContactDrawer.vue";
import CellOperate from "./CellOperate.vue";
import CollapseWrap from "@/components/CollapseWrap";
import {generateMapKey} from "@/utils/tools";
import {rankOption, sexRadio} from "@/constant/customer/ContactCard";
import {editCustomer} from "@/api/customer/publicleads";
import {timeZoneList} from "@/assets/data/countryData";

export default {
  props: {
    row: {
      type: Object,
      default: () => {
        return {
          focusFlag: false,
          contactList: {}
        }
      },
      required: false
    },
    options: {
      type: Object,
      default: () => {
        return {
          isShowInfo: true
        }
      },
      required: false
    }
  },
  components: {TableNext, CustomerContactDrawer, CollapseWrap, CellOperate},
  data() {
    return {
      contactRow: {
        nickName: '',
        email: '',
        rank: '',
        position: '',
        birthday: '',
        sex: '',
        avatarOrBusinessCard: '',
        contactRemarks: '',
      },
      contactRowChecked: false,
      contactFieldList: [
        {field: 'email', name: '邮箱', isCopy: true},
        {field: 'socialPlatform', name: '社交平台', isCopy: true, childField: ['type', 'account']},
        {field: 'phone', name: '手机号', isCopy: true, childField: ['phone_prefix', 'phone']},
        {field: 'rank', name: '职级', isCopy: false, mapValue: generateMapKey(rankOption)},
        {field: 'position', name: '职位', isCopy: false},
        {field: 'birthday', name: '生日', isCopy: false},
        {field: 'sex', name: '性别', isCopy: false, mapValue: generateMapKey(sexRadio)},
        {field: 'avatarOrBusinessCard', name: '头像/名片', isCopy: false},
        {field: 'contactRemarks', name: '联系人备注', isCopy: false}
      ],
      usuallyInfo: [
        {
          field: 'companyWebsite',
          label: '公司网址',
          type: 'input',
          show: false,
          value: '',
          options: {},
        },
        {
          field: 'companyName',
          label: '公司名称',
          type: 'input',
          show: false,
          value: '',
          options: {},
        },
        {
          field: 'shortName',
          label: '简称',
          type: 'input',
          show: false,
          value: '',
          options: {},
        },
        {
          field: 'countryRegion',
          label: '国家地区',
          type: 'country',
          show: false,
          value: '',
          options: {},
        },
        {
          field: 'sourceIds',
          label: '客户来源',
          type: 'tree',
          show: false,
          value: [],
          options: {
            data: [],
            props: {
              value: 'id',
              label: 'name',
            },
            showCheckbox: true,
            checkStrictly: true,
            multiple: true,
            collapseTags: true,
          },
        },
        {
          field: 'packetId',
          label: '分组',
          type: 'tree',
          show: false,
          value: '',
          options: {
            size: 'small',
            filterable: true,
            clearable: true,
            data: [],
            props: {
              value: 'id',
              label: 'name',
            },
          },
        },
        {
          field: 'stageId',
          label: '客户阶段',
          type: 'select',
          show: false,
          value: '',
          options: {
            options: [],
            props:{
              value:'id',
              label:'name'
            }
          },
        },
        {
          field: 'rating',
          label: '客户星级',
          type: 'rate',
          show: true,
          value: null,
          options: {},
        },
        {
          field: 'customerNo',
          label: '客户编号',
          type: 'input',
          show: false,
          value: '',
        },
        {
          field: 'poolGroup',
          label: '公海分组',
          type: 'select',
          show: false,
          showTooltip: true,
          tooltipText: '移入公海时，所属的公海分组',
          value: '',
          options: {
            clearable: true,
            options: [],
            props:{
              value:'id',
              label:'name'
            }
          },
        },
        {
          field: 'tel',
          label: '座机',
          type: 'tel',
          show: false,
          value: {
            phonePrefix: '',
            phone: '',
          },
        },
      ],
      otherInfo: [
        // {
        //   field: 'product',
        //   label: '主营产品',
        // },
        // {
        //   field: 'type',
        //   label: '客户类型',
        // },
        // {
        //   field: 'year',
        //   label: '年采购额',
        // },
        // {
        //   field: 'yx',
        //   label: '采购意向',
        // },
        {
          field: 'timezone',
          label: '时区',
          type: 'select',
          value: '',
          show: false,
          options: {
            options: timeZoneList,
            props: {
              filterable: true,
              clearable: true
            }
          },
        },
        {
          field: 'scale',
          label: '规模',
          type: 'select',
          value: '',
          options: {
            options: [
              {value: 1, label: '少于59人'},
              {value: 2, label: '60-149人'},
              {value: 3, label: '150-499人'},
              {value: 4, label: '500-999人'},
              {value: 5, label: '1000-4999人'},
              {value: 6, label: '5000人以上'},
            ],
            props:{
              value:'value',
              label:'label'
            }            
          }
        },
        // {
        //   field: 'productGroup',
        //   label: '产品分组',
        // },
        {
          field: 'fax',
          label: '传真',
          type: 'input',
          show: false,
          value: '',
          options: {},
        },
        {
          field: 'address',
          label: '详细地址',
          type: 'input',
          show: false,
          value: '',
          options: {},
        },
        {
          field: 'companyRemarks',
          label: '公司备注',
          show: false,
          value: '',
          type: 'input',
          options: {
            type: 'textarea',
            resize: 'none',
            rows: 3
          }
        },
        {
          field: 'companyLogo',
          label: '公司logo',
          type: 'picture',
          otherEcho: true,
          slot: ''
        },
      ],
      followInfo: [
        {
          field: 'lastContactedAt',
          label: '最近联系时间',
          showTooltip: '',
        },
        {
          field: 'lastFollowupAt',
          label: '最近跟进时间',
          showTooltip: '',
        },
        {
          field: '',
          label: '下次移入公海日期',
          showTooltip: '<span>根据移入公海规则计算得出，移入操作在凌晨进行</span>'
        },
        {
          field: '',
          label: '最近进入私海时间',
        },
        {
          field: '',
          label: '最近进入公海时间',
        },
        {
          field: '',
          label: '进入公海次数',
        },
        {
          field: '',
          label: '最近成交日期',
        },
        {
          field: '',
          label: '最近发件时间',
        },
        {
          field: '',
          label: '最近收件时间',
        },
        {
          field: '',
          label: '最近发EDM时间',
        },
        {
          field: '',
          label: '最近收到阿里TM消息时间',
        },
        {
          field: '',
          label: '最近WhatsApp沟通时间',
        },
        {
          field: '',
          label: 'Tips最近更新时间',
        },
        {
          field: '',
          label: '最近「写跟进」时间',
        },
        {
          field: '',
          label: '下次日程时间',
        },
        {
          field: '',
          label: '客户评分',
        },
      ],
      sysInfo: [
        {
          field: 'createId',
          label: '创建人',
        },
        {
          field: 'createTime',
          label: '创建时间',
        },
        {
          field: 'updateId',
          label: '最近修改人',
        },
        {
          field: 'updateTime',
          label: '资料更新时间',
        },
        {
          field: '',
          label: '原跟进人',
        },
        {
          field: '',
          label: '创建方式',
        },
        {
          field: '',
          label: '关联线索',
        },
        {
          field: '',
          label: '最近转化线索创建时间',
        },
        {
          field: '',
          label: '来源线索',
        },
        {
          field: '',
          label: '来源详情',
        },
        {
          field: '',
          label: '客群',
        },
      ],
      contactVisible: false,
    }
  },
  watch: {
    row: {
      handler(newVal) {
        if (!newVal?.customerId) {
          return
        }
        this.usuallyInfo.map(val => {
          if (val.field === 'tel') {
            val.value = {
              phonePrefix: newVal?.phonePrefix || '',
              phone_prefix: newVal?.phonePrefix || '',
              phone: newVal?.phone || '',
            }
          } else {
            val.value = newVal[val.field]
          }
          return val
        })
        this.otherInfo.map(val => {
          val.value = newVal[val.field]
          return val
        })
        if (newVal.contactList && newVal.contactList?.length) {
          this.contactRow = newVal.contactList[0]
        }

      },
      deep: true,
      immediate: true,
    },
    options: {
      handler(newVal) {
        const {indexOpt} = newVal
        this.usuallyInfo.map(val => {
          if (val.field === 'packetId') {
            val.options.data = indexOpt.groupOption || []
          } else if (val.field === 'stageId') {
            val.options.options = indexOpt.stageOption
          } else if (val.field === 'origin') {
            val.options.data = indexOpt.originOption || []
          } else if (val.field === 'poolGroup') {
            val.options.options = indexOpt.poolGroupOption || []
          } else if(val.field === 'sourceIds'){
            val.options.data = indexOpt.originOption || []
          }
          return val
        })

      },
      deep: true,
      immediate: true
    }
  },
  methods: {
    editCustomer(data) {
      return new Promise(async resolve => {
        try {
          const res = await editCustomer({...data})
          if (res.code === 200) {
            this.$message.success('修改成功')
            resolve(true)
          }
        } catch {
        }
      })
    },
    onShowForm(listType, field, bool) {
      this[listType].forEach((val, index) => {
        if (val.field === field) {
          this.$set(this[listType], index, {...val, show: bool})
        }
      })
    },
    handleEnter(listType, field, bool) {
      this[listType].forEach((val, index) => {
        if (val.field === field) {
          let data = {
            id: this.row.customerId,
            [field]: val.value
          }
          if (field === 'tel') {
            delete data[field]
            data = {
              ...data,
              phone: val.value.phone,
              phonePrefix: val.value.phone_prefix
            }
          }
          this.editCustomer(data).then(res => {
            if (res) {
              this.$set(this[listType], index, {...val, show: bool})
              this.$emit('reload')
            }
          })
        }
      })

    },
    handleSetValue(listType, field, value) {
      const validList = ['companyWebsite', 'shortName', 'companyName', 'tel', 'address', 'fax', 'companyRemarks','sourceIds']
      if (validList.includes(field)) {
        this[listType].forEach((val, index) => {
          if (val.field === field) {
            this.$set(this[listType], index, {...val, value})
          }
        })
        return
      }
      this.confirmSetValue(listType, field, value)
    },
    confirmSetValue(listType, field, value) {
      const data = {
        id: this.row.customerId,
        [field]: field === 'countryRegion' ? value.join('/') : value
      }
      this.editCustomer(data).then(res => {
        if (res) {
          this[listType].forEach((val, index) => {
            if (val.field === field) {
              let newValue = value
              this.$set(this[listType], index, {...val, show: field==='rating' ? true : false, value: newValue})
              this.$emit('reload')
            }
          })
        }
      })
    },
    onCopy(value) {
      if (!value) {
        this.$message.info('无内容可复制')
        return
      }
      this.$copyText(value).then(() => {
        this.$message.success('复制成功')
      })
    },
    onConfirmContact() {
      this.contactVisible = false
      this.$emit('reload')
    },
    generateDiffCopy(type, firstVal, secondVal) {
      if (type === 'phone') {
        return `${firstVal}-${secondVal}`
      }
      return secondVal
    },
    generateMember(id) {
      let name = ''
      try {
        const memberOption = this.options.indexOpt.memberOption
        const deepSearch = (arr) => arr.forEach(val => {
          if (val.id === id) {
            name = val.name
            // 跳出
            throw new Error('return false');
          }
          if (val.children && val.children.length) {
            deepSearch(val.children)
          }
        })
        deepSearch(memberOption)
      } catch (e) {
        return name
      }
      return name
    }
  }

}
</script>

<style lang="scss" scoped>

.datum-tab {
  .bottom-line {
    border-bottom: 1px solid #e0e0e0;
  }

  .collapse-content-box {
    background-color: transparent;
    border-top: 0;
    color: #333333;

    .main {
      background-color: rgba(247, 248, 251);
      border-radius: 4px;
    }

    .wrap {
      width: 50%;
      padding-top: 10px;

      .copy-text {
        cursor: pointer;

        > i {
          display: none;
        }

        &:hover {
          color: #0a6aff;
        }

        &:hover i {
          display: block;
        }
      }
    }
  }

  .info-card {
    .wrap {
      width: 50%;
      height: 100%;
      padding: 6px 10px !important;
      cursor: pointer;
      border-radius: 4px;
      font-size: 14px;
      margin: 5px 0;

      .edit-icon {
        display: none;
      }

      &:hover {
        background-color: rgba(245, 245, 245);
      }

      &:hover .edit-icon {
        display: block;
      }
    }
  }

  .echo-info {
    .wrap {
      width: 50%;
      font-size: 14px;
    }
  }
}


</style>
