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
        <TableRowTabs :options="options" :row="{id:customerId}"/>
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
            <div>
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
                    <div class="fs-14 my-10 flex-column">
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
                </div>
              </div>
            </div>
          </CollapseWrap>


        </div>
      </el-col>
    </el-row>
    <DialogSchedule :visible.sync="dialogSchedule"/>
    <CustomerContactDrawer :visible.sync="contactVisible"/>
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
import {formatMonthAndDay} from "@/utils";

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
      contactList: [
        {
          id: 1,
          checked: true
        },
      ],
      contactSearchValue: '',
      contactSearch: false,
      contactVisible: false,
      dialogSchedule: false,
      scheduleList: [],
    }
  },
  mounted() {
    this.customerId = this.$route.params?.id
    this.getFollowUpRecordList()
  },
  methods: {
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
    align-items: center;

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

</style>
