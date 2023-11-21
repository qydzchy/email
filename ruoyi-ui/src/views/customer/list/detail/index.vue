<template>
  <div class="page-customer-detail">
    <div class="fs-20">客户详情</div>
    <div class="card-bg px-20 py-16 mt-20">
      <div class="base-info flex-start">
        <el-avatar :size="110" shape="square"></el-avatar>

        <el-row class="pl-10 fs-14">
          <div class="fs-20 bold">测试公司</div>
          <div class="my-10 flex-middle gap-10">
            <span>11248</span>
            <span>冰岛</span>
            <span>15:39 UTC+2</span>
          </div>
          <div class="mb-10">跟进入: admin</div>
          <div class="flex-middle">
            <el-tag class="customer-tag" closable>标签1</el-tag>
            <el-button class="ml-10" size="mini" icon="el-icon-plus"></el-button>
          </div>
        </el-row>
      </div>
      <div class="info-wrap">
        <div class="wrap flex-middle" v-for="item in infoRowList" :key="item.id">
          <span class="fs-14 mr-6">{{ item.label }}</span>
          <CellOperate :value.sync="item.value" :type="item.type" :show-form="item.show" :show-copy-icon="false"
                       @onEdit="onEdit(item.id)"/>
        </div>
      </div>
    </div>
    <el-row type="flex">
      <el-col :span="17" class="card-bg mt-16 pt-8 pb-16 px-8">
        <TableRowTabs :options="options" :row="{customerId:customerId}"/>
      </el-col>
      <el-col :span="7" class="ml-16">
        <div class="card-bg  mt-16 px-16 py-16">
          <CollapseWrap is-collapse :height="0">
            <template #header>
              <div class="flex-middle space-between">
                <div class="fs-14 bold">计划日程</div>
                <el-tooltip placement="top" content="添加日程">
                  <i class="el-icon-circle-plus-outline pointer" @click.stop="dialogSchedule=true"></i>
                </el-tooltip>
              </div>
            </template>
            <ul class="plan-ul" v-if="scheduleList && scheduleList.length">
              <li class="plan-li pointer" v-for="(schedule,index) in scheduleList" :key="index">
                <div class="circle" :style="{color:schedule.color}"></div>
                <span class="date">{{ formatMonthAndDay(schedule.scheduleStartTime) }}</span>
                <span class="content">{{ schedule.scheduleContent }}</span>
              </li>
            </ul>
            <el-empty v-else description="暂无日程" :image-size="40"/>
          </CollapseWrap>
        </div>
        <div class="card-bg  mt-16 px-16 py-16">
          <CollapseWrap is-collapse :height="0">
            <template #header>
              <div class="contact flex-middle space-between">
                <div class="fs-14 bold">联系人({{ contactList.length }})</div>
                <div class="flex-middle gap-20">
                  <el-row v-if="contactSearch">
                    <el-input size="mini" v-model="contactSearchValue" @click.native.stop @blur="handleBlurSearch"
                              clearable>
                      <el-button slot="append" icon="el-icon-search" size="mini" @click.stop></el-button>
                    </el-input>
                  </el-row>
                  <el-row v-else>
                    <el-tooltip placement="top" content="搜索">
                      <i class="el-icon-search pointer" @click.stop="contactSearch=true"></i>
                    </el-tooltip>
                  </el-row>
                  <el-tooltip placement="top" content="编辑">
                    <i class="el-icon-edit pointer" @click.stop="contactVisible=true"></i>
                  </el-tooltip>
                </div>
              </div>
            </template>
            <template>
              <div class="collapse-content-box" v-if="contactList.length">
                <div class="container py-8" v-for="(item,index) in contactList" :key="index">
                  <div class="main px-16 py-12">
                    <div class="flex-middle space-between">
                      <span class="fs-14 bold">{{ item.nickName || '---' }}</span>
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
                    <!--          联系人详情          -->
                    <div class="fs-14 my-10 flex-column">
                      <template v-for="(contact,index) in contactFieldList">
                        <div class="wrap"
                             v-if="item[contact.field] && !['socialPlatform','phone'].includes(contact.field)"
                             :key="index">
                          <div>{{ contact.name }}</div>
                          <div class="flex-middle" :class="{'copy-text':contact.isCopy}">
                            <span v-if="contact.mapValue">
                              {{ contact.mapValue[item[contact.field]] }}
                            </span>
                            <span v-else>
                              {{ item[contact.field] }}
                            </span>
                            <i v-if="contact.isCopy" class="el-icon-copy-document pl-4"
                               @click="onCopy(item[contact.field])"></i>
                          </div>
                        </div>
                        <div class="wrap" v-else-if="item[contact.field]">
                          <div>{{ contact.name }}</div>
                          <div class="flex-column gap-10">
                            <div class="flex-start" :class="{'copy-text': subItem[contact.childField[1]]}"
                                 v-for="(subItem,subIdx) in item[contact.field]">
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
                </div>
              </div>
              <el-empty v-else :image-size="40"></el-empty>
            </template>
          </CollapseWrap>


        </div>
      </el-col>
    </el-row>
    <DialogSchedule v-if="dialogSchedule" :visible.sync="dialogSchedule" :formData="rowData"/>
    <CustomerContactDrawer :visible.sync="contactVisible" :rowData="rowData" @onConfirm="onConfirm"/>
  </div>
</template>

<script>
import TableRowTabs from '../TableRowTabs.vue'
import DialogSchedule from "../DialogSchedule.vue";
import CustomerContactDrawer from "../CustomerContactDrawer.vue";
import CellOperate from "../CellOperate.vue";
import CollapseWrap from "@/components/CollapseWrap/index.vue";
import TableNext from "@/components/TableNext/index.vue";
import {getScheduleList} from "@/api/customer/schedule";
import {deepClone, formatMonthAndDay} from "@/utils";
import {getCustomerDetail} from "@/api/customer/publicleads";
import {generateMapKey} from "@/utils/tools";
import {rankOption, sexRadio} from "@/constant/customer/ContactCard";

export default {
  components: {
    TableNext,
    CustomerContactDrawer,
    DialogSchedule,
    TableRowTabs,
    CellOperate,
    CollapseWrap
  },
  data() {
    return {
      customerId: null,
      options: {},
      infoRowList: [
        {
          id: 1,
          label: '分组',
          value: '',
          type: 'input',
          show: false
        }
      ],
      contactList: [],
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
      contactSearchValue: '',
      contactSearch: false,
      contactVisible: false,
      dialogSchedule: false,
      scheduleList: [],
      rowData: {},
    }
  },
  mounted() {
    this.customerId = this.$route.params?.id
    if (!this.customerId) {
      return
    }
    this.getDetailData()
    this.getFollowUpRecordList()
  },
  methods: {
    async getDetailData() {
      try {
        const res = await getCustomerDetail({
          id: this.customerId
        })
        if (res.code === 200) {
          this.rowData = res.data
          this.rowData.countryRegion = this.rowData.countryRegion?.split('/') || []
          this.rowData.customerId = this.rowData.id
          let sourceList = deepClone(this.rowData.sourceList)
          this.rowData.sourceIds = sourceList?.map(val => val.id)
          let tagList = deepClone(this.rowData.tagList)
          this.rowData.tagIds = tagList?.map(val => val.id)
          this.rowData.stageId = this.rowData.stage?.id
          this.rowData.packetId = this.rowData.packet?.id
          this.rowData.timezone = +this.rowData.timezone
          this.rowData.contactList = this.generateContactList(this.rowData?.contactList)
          this.contactList = this.rowData.contactList
        }
      } catch {
      }
    },
    async getFollowUpRecordList() {
      try {
        const res = await getScheduleList({
          customerId: this.customerId,
          pageNum: 1,
          pageSize: 3,
        })
        if (res.code === 200) {
          this.scheduleList = res.data
        }
      } catch {
      }
    },
    onEdit(id) {
      this.infoRowList.map(val => {
        if (val.id === id) {
          val.show = true
        }
        return val
      })
    },
    handleBlurSearch() {
      if (!this.contactSearchValue) {
        this.contactSearch = false
        return
      }
      console.log('value', this.contactSearchValue)
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
    onConfirm() {
      this.contactVisible = false
      if (!this.customerId) {
        return
      }
      this.getDetailData()
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
    generateDiffCopy(type, firstVal, secondVal) {
      if (type === 'phone') {
        return `${firstVal}-${secondVal}`
      }
      return secondVal
    },
    formatMonthAndDay,
  }
}
</script>

<style lang="scss" scoped>
.page-customer-detail {
  padding: 10px 20px;
  height: 100%;
  min-width: 1300px;
  background-color: rgba(245, 245, 245);
}

.info-wrap {
  width: 100%;

  .wrap {
    width: max-content;
    box-sizing: border-box;
    padding: 6px 12px;

    &:hover {
      background-color: rgba(245, 245, 245);
    }
  }
}

.card-bg {
  border-radius: 8px;
  background-color: rgba(255, 255, 255)
}

.base-info {
  ::v-deep .customer-tag {

  }
}

.plan-ul {
  padding-inline-start: 0;
}

.plan-ul > .plan-li {
  list-style: none;
  display: flex;
  align-items: center;
  font-size: 14px;

  .circle {
    width: 8px;
    height: 8px;
    background: rgb(255, 51, 51);
    border-radius: 8px;
    padding: 0 4px;
  }

  .date {
    width: 102px;
    color: #0a6aff;
    word-wrap: break-word;
    padding-left: 4px;
  }

  .content {
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    color: rgba(37, 40, 46)
  }
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
    display: grid;
    grid-template-columns:88px 240px;
    grid-column-gap: 8px;
    column-gap: 8px;
    padding: 10px 0;

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

</style>
