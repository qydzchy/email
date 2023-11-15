<template>
  <div class="datum-tab">
    <el-row class="py-10 bottom-line" v-if="options.isShowInfo">
      <CollapseWrap isCollapse :height="0">
        <template #header>
          <div class="flex-middle space-between">
            <div>主要联系人信息</div>
            <div>
              <el-tooltip content="添加/编辑">
                <i class="el-icon-edit pointer" @click="contactVisible=true"></i>
              </el-tooltip>
            </div>
          </div>
        </template>
        <div class="collapse-content-box">
          <div class="container py-8" v-for="(item,index) in contactList" :key="index">
            <div class="main px-16 py-12">
              <div class="flex-middle space-between">
                <span class="fs-14 bold">测试</span>
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
              <div class="fs-14 my-10 flex-start flex-wrap" v-show="item.checked">
                <div class="wrap">
                  <div>邮箱</div>
                  <div class="py-10 email-copy flex-middle">
                    wangwu@163.com
                    <i class="el-icon-copy-document pl-4" @click="onCopy('wangwu@163.com')"></i>
                  </div>

                </div>
                <div class="wrap">
                  <div>职级</div>
                  <div class="py-10">普通职员</div>
                </div>
                <div class="wrap">
                  <div>生日</div>
                  <div class="py-10">10-18</div>
                </div>
                <div class="wrap">
                  <div>性别</div>
                  <div class="py-10">男</div>
                </div>
              </div>
            </div>
            <div class="footer flex-middle flex-center fs-12 mt-10">
            <span class="pointer" @click="onCollapseContact(item.id)">
              {{ item.checked ? '收起' : '展开' }}
              <i :class="item.checked ? 'el-icon-arrow-up':'el-icon-arrow-down'"></i></span>
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
                  @onEnter="onShowForm('usuallyInfo',usually.field,false)"
                  @onBlur="onShowForm('usuallyInfo',usually.field,false)"
                  @onEdit="onShowForm('usuallyInfo',usually.field,true)">
              </CellOperate>
            </el-col>
          </el-row>
        </div>
      </CollapseWrap>

    </el-row>
    <el-row class="py-10 bottom-line">
      <CollapseWrap isCollapse :height="450">
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
                  @onEnter="onShowForm('otherInfo',other.field,false)"
                  @onBlur="onShowForm('otherInfo',other.field,false)"
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
                  <div slot="content" v-html="follow.tooltipText"></div>
                  <i class="el-icon-question"></i>
                </el-tooltip>
              </div>
              <div class="flex-middle space-between py-5">
                <span>--</span>
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
                  <div slot="content" v-html="sys.tooltipText"></div>
                  <i class="el-icon-question"></i>
                </el-tooltip>
              </div>
              <div class="flex-middle space-between py-5">
                <span>--</span>
              </div>
            </el-col>
          </el-row>
        </div>
      </CollapseWrap>
    </el-row>
    <CustomerContactDrawer :visible.sync="contactVisible"/>
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import CustomerContactDrawer from "./CustomerContactDrawer.vue";
import CellOperate from "./CellOperate.vue";
import CollapseWrap from "@/components/CollapseWrap";
import Vue from 'vue'

export default {
  props: {
    row: {
      type: Object,
      default: () => {
        return {
          focusFlag: false
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
      contactList: [
        {
          id: 1,
          checked: true
        },
      ],
      usuallyInfo: [
        {
          field: '1',
          label: '公司网址',
          type: 'input',
          show: false,
          value: '',
          options: {},
        },
        {
          field: '2',
          label: '公司名称',
          type: 'input',
          show: false,
          value: '',
          options: {},
        },
        {
          field: '3',
          label: '简称',
          type: 'input',
          show: false,
          value: '',
          options: {},
        },
        {
          field: '4',
          label: '国家地区',
          type: 'input',
          show: false,
          value: '',
          options: {},
        },
        {
          field: '5',
          label: '客户来源',
          type: 'input',
          show: false,
          value: '',
          options: {},
        },
        {
          field: '6',
          label: '分组',
          type: 'tree',
          show: false,
          value: '7',
          options: {
            size: 'small',
            filterable: true,
            clearable: true,
            data: this.options.groupOption
          },
        },
        {
          field: '7',
          label: '客户阶段',
          type: 'select',
          show: false,
          value: '',
          options: {
            options: [
              {label: 'test', value: '1', bgColor: '#fea112'},
              {label: 'test2', value: '2'},
              {label: 'test3', value: '3'},
            ]
          },
        },
        {
          field: '8',
          label: '客户星级',
          type: 'rate',
          show: true,
          value: null,
          options: {},
        },
        {
          field: '9',
          label: '客户编号',
          type: 'input',
          show: false,
          value: '',
          options: {},
        },
        {
          field: '10',
          label: '公海分组',
          type: 'select',
          show: false,
          showTooltip: true,
          tooltipText: '移入公海时，所属的公海分组',
          value: '1',
          options: {
            clearable: true,
            options: [
              {label: '公共公海分组', value: '1'},
              {label: '公海2组', value: '2'},
            ]
          },
        },
        {
          field: '11',
          label: '座机',
          type: 'tel',
          show: false,
          value: {
            phone_prefix: '',
            phone: '',
          },
        },
      ],
      otherInfo: [
        {
          field: '1',
          label: '主营产品',
        },
        {
          field: '2',
          label: '客户类型',
        },
        {
          field: '3',
          label: '年采购额',
        },
        {
          field: '4',
          label: '采购意向',
        },
        {
          field: '5',
          label: '时区',
        },
        {
          field: '6',
          label: '规模',
        },
        {
          field: '7',
          label: '产品分组',
        },
        {
          field: '8',
          label: '传真',
        },
        {
          field: '9',
          label: '详细地址',
        },
        {
          field: '10',
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
          field: '11',
          label: '公司logo',
          otherEcho: true,
          slot: '111'
        },
      ],
      followInfo: [
        {
          field: '',
          label: '最近联系时间',
        },
        {
          field: '',
          label: '最近跟进时间',
        },
        {
          field: '',
          label: '下次移入公海日期',
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
          field: '',
          label: '创建人',
        },
        {
          field: '',
          label: '创建时间',
        },
        {
          field: '',
          label: '最近修改人',
        },
        {
          field: '',
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
      contactVisible: false
    }
  },
  methods: {
    onCollapseContact(id) {
      this.contactList.map(val => {
        if (val.id === id) {
          val.checked = !val.checked
        }
        return val
      })
    },
    onShowForm(listType, field, bool) {
      this[listType].map(val => {
        if (val.field === field) {
          val.show = bool
        }
        return val
      })
    },
    handleSetValue(listType, field, value) {
      this[listType].forEach((val, index) => {
        if (val.field === field) {
          this.$set(this[listType], index, {...val, value})
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

      .email-copy {
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
