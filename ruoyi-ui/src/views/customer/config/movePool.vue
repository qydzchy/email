<template>
  <div v-loading="dataLoading">
    <div class="space-between">
      <div>
        <div class="fs-14 gray-text">
          <span>我的客户数：{{ rowData.customerCount }}/{{ isCustomerLimit }}</span>
  <!--        <el-tooltip>-->
  <!--          <template #content>-->
  <!--            <div>-->
  <!--              <p class="lineH-12">共享：0</p>-->
  <!--              <p class="lineH-12">未共享：8</p>-->
  <!--              <p class="lineH-12">(共享客户占0.5个名额，未共享客户占1个名额; 实际占客户上限数8个名额)</p>-->
  <!--            </div>-->
  <!--          </template>-->
  <!--          <i class="el-icon-warning-outline ml-4"/>-->
  <!--        </el-tooltip>-->
        </div>
        <div class="flex-middle mt-10">
          <div class="fs-14 gray-text">
            <span>符合移入公海条件时，系统会提前7天发送邮件提醒</span>
            <el-tooltip>
              <template #content>
                <div class="fs-15 lineH-24" style="width:240px">
                  下次移入公海日期根据客户当前生效客群匹配的移入公海规则计算，计算时间为T+1。当客户进入新客群后，会根据新的生效客群匹配的移入公海规则重新计算下次移入公海日期。有多个日期时，默认显示距今最近的日期。
                </div>
              </template>
              <i class="el-icon-warning-outline ml-4"/>
            </el-tooltip>
          </div>
          <div class="fs-14 gray-text ml-4">
            <span>最近联系日期参照事件</span>
            <el-tooltip>
              <template #content>
                <div style="width:240px">
                  <div class="white-color fs-16 bold">什么是最近联系日期参照事件？</div>
                  <div class="fs-14 my-10">
                    例如：参照事件包含“发送邮件”，则给客户发送邮件时，会更新与此客户的最近联系日期。
                  </div>
                  <div class="white-color fs-16 bold">参照事件：</div>
                  <ul class="tooltip-ul">
                    <li>发送邮件 (客户+商机)</li>
                    <li>接收邮件 (客户+商机)</li>
                    <li>营销邮件 (客户+商机)</li>
                    <li>编辑客户</li>
                    <li>新建跟进 (客户+商机)</li>
                    <li>新建/编辑 报价单</li>
                    <li>新建/编辑 销售订单</li>
                    <li>上传客户文档</li>
                    <li>新建/编辑 商机</li>
                    <li>发送聊天消息 (TM+WhatsApp)</li>
                    <li>接收聊天消息 (TM+WhatsApp)</li>
                  </ul>
                </div>
              </template>
              <i class="el-icon-warning-outline ml-4"/>
            </el-tooltip>
          </div>
          <div class="fs-14 gray-text ml-4">
            <span>最近成交日期参照事件</span>
            <el-tooltip>
              <template #content>
                <div style="width: 240px">
                  <div class="white-color fs-16 bold">什么是最近成交日期参照事件？</div>
                  <div class="fs-15 my-4 lineH-24">从客户所有符合绩效规则“成交订单金额”的订单中，取最近的订单日期</div>
                </div>
              </template>
              <i class="el-icon-warning-outline ml-4"/>
            </el-tooltip>
          </div>
        </div>
      </div>
      <el-button class="mr-20" type="primary" round size="small" @click="$router.push('/company/customer-setting?tab=poolRule')">移入公海规则设置</el-button>
    </div>
    <div class="container px-24 py-24 gap-24" v-if="rowData.customerPublicleadsRulesList.length">
      <div class="move-pool-card" v-for="(item,index) in rowData.customerPublicleadsRulesList" :key="index">
        <span class="bold">{{ item.name }}</span>
        <div class="flex-column gap-12 mt-12">
          <div class="fs-14">
            <span class="text-color">移入条件：</span>
            <span>{{ item.segmentNames || '---' }}</span>
          </div>
          <div class="fs-14">
            <span class="text-color">生效时间：</span>
            <span>{{ item.startTime || '---' }}</span>
          </div>
          <el-tooltip :disabled="!item.group" placement="bottom-start">
            <template #content>
              <div>
                <div class="fs-16 white-color bold">生效客群：</div>
                <ul class="tooltip-ul">
                  <li v-for="(item,key) in item.group" :key="key">{{ item }}</li>
                </ul>
              </div>
            </template>
            <div class="fs-14">
              <span class="text-color">生效客群：</span>
              <span>{{ item.group | groupJoin }}</span>
            </div>
          </el-tooltip>


        </div>
      </div>
    </div>
    <el-empty class="mt-50" v-if="!dataLoading && !rowData.customerPublicleadsRulesList.length" :image-size="100"/>
    
  </div>
</template>

<script>
import {getPoolRuleList} from "@/api/customer/config";

export default {

  data() {
    return {
      rowData: {
        customerCount: 0,
        type: null,
        limits: null,
        customerPublicleadsRulesList: [],
      },
      dataLoading:false
    }
  },
  computed: {
    isCustomerLimit() {
      return this.rowData.type === 1 ? '无上限' :(this.rowData.limits && `${this.rowData.limits} (上限)` || 0)
    }
  },
  filters:{
    groupJoin(value){
      return value?.join('，') || '---'
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    async getList() {
      try {
        this.dataLoading = true
        const res = await getPoolRuleList().finally(()=>{
          this.dataLoading = false
        })
        if (res.code == 200) {
          this.rowData = res.data
        }
      } catch {
      }
    }
  },
}
</script>

<style lang="scss" scoped>
.text-color {
  color: #3d3d3d;
}

.white-color {
  color: #ffffff;
}

.tooltip-ul {
  padding-inline-start: 20px;

  li {
    margin: 4px 0px;
    font-size: 14px;
  }
}

.container {
  display: grid;
  grid-template-columns: repeat(2, minmax(0, 1fr));

  .move-pool-card {
    background-color: rgb(247, 248, 251);
    padding: 24px 16px;
    border-radius: 10px;

  }
}
</style>
