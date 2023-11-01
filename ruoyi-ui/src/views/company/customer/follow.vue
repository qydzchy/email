<template>
  <div>
    <!--  最近联系时间  -->
    <div class="mb-20">
      <div class="fs-14 gray-text pb-6">发生以下行为会影响客户的“最近联系时间”，进而影响客户被自动移入公海的时间</div>
      <div class="card">
        <div class="head flex-middle space-between">
          <div class="check-all">
            <el-checkbox v-model="contact.checkAll" :disabled="!contact.showBtnGroup" @change="handleCheckAllContact">全选
            </el-checkbox>
          </div>
          <div>
            <el-row v-if="contact.showBtnGroup">
              <el-button round size="mini" @click="contact.showBtnGroup=false">取消</el-button>
              <el-button round size="mini" type="primary">保存</el-button>
            </el-row>
            <el-button v-else type="primary" round size="mini" @click="contact.showBtnGroup=true">编辑</el-button>
          </div>
        </div>
        <div class="body">
          <el-checkbox-group v-model="contact.checkedContacts" @change="handleCheckedContact">
            <el-checkbox class="my-6" v-for="(cItem,index) in contact.contactList" :label="cItem.label" :key="index"
                         :disabled="!contact.showBtnGroup">
              {{ cItem.text }}
            </el-checkbox>
          </el-checkbox-group>
        </div>
      </div>
    </div>
    <!--  最近跟进时间  -->
    <div class="mb-20">
      <div class="fs-14 gray-text pb-6">发生以下行为会影响客户的“最近跟进时间”，进而影响跟进客户类型的任务截止时间</div>
      <div class="card">
        <div class="head  flex-middle space-between">
          <div class="check-all">
            <el-checkbox v-model="follow.checkAll" :disabled="!follow.showBtnGroup" @change="handleCheckAllFollow">全选
            </el-checkbox>
          </div>
          <div>
            <el-row v-if="follow.showBtnGroup">
              <el-button round size="mini" @click="follow.showBtnGroup=false">取消</el-button>
              <el-button round size="mini" type="primary">保存</el-button>
            </el-row>
            <el-button v-else type="primary" round size="mini" @click="follow.showBtnGroup=true">编辑</el-button>
          </div>
        </div>
        <div class="body">
          <el-checkbox-group v-model="follow.checkedFollows" @change="handleCheckedFollow">
            <el-checkbox class="my-6" v-for="(fItem,index) in follow.followList" :label="fItem.label" :key="index"
                         :disabled="!follow.showBtnGroup">{{ fItem.text }}
            </el-checkbox>
          </el-checkbox-group>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {getFollowRulesList} from "@/api/company/follow";

export default {
  data() {
    return {
      contact: {
        checkAll: false,
        showBtnGroup: false,
        showLoading: false,
        checkedContacts: ['2', '3', '4', '5', '6'],
        contactList: [
          {
            label: '1',
            text: '获取公海/同事客户',
            disabled: false,
          },
          {
            label: '2',
            text: '发送邮件（客户）',
            disabled: false,
          },
          {
            label: '3',
            text: '接收邮件（客户）',
            disabled: false,
          },
          {
            label: '4',
            text: '编辑客户',
            disabled: false,
          },
          {
            label: '5',
            text: '新建跟进',
            disabled: false,
          },
          {
            label: '6',
            text: '上次客户文档',
            disabled: false,
          },
        ]
      },
      follow: {
        checkAll: false,
        showBtnGroup: false,
        showLoading: false,
        checkedFollows: ['1'],
        followList: [
          {
            label: '1',
            text: '发送邮件（客户）',
            disabled: false,
          },
          {
            label: '2',
            text: '新建跟进（客户）',
            disabled: false,
          },
        ]
      },
    }
  },
  methods: {
    async getList(type){
      try{
        const res = await getFollowRulesList()
        if(res.code===200){

        }
      }catch{}
    },
    handleCheckAllContact() {
    },
    handleCheckAllFollow() {
    },
    handleCheckedContact() {
    },
    handleCheckedFollow() {
    }
  }
}
</script>

<style lang="scss" scoped>
.card {
  font-feature-settings: "tnum";
  background: #fff;
  border-radius: 4px;
  box-sizing: border-box;
  color: #333;
  font-size: 14px;
  font-variant: tabular-nums;
  line-height: 1.5715;
  list-style: none;
  margin: 0;
  padding: 0;
  position: relative;
  border: 1px solid #f0f0f0;
}

.card > .head {
  background: transparent;
  border-bottom: 1px solid #f0f0f0;
  border-radius: 4px 4px 0 0;
  color: rgba(0, 0, 0, .85);
  font-size: 16px;
  font-weight: 500;
  margin-bottom: -1px;
  min-height: 48px;
  padding: 0 24px;
}

.card > .body {
  padding: 24px;
}
</style>
