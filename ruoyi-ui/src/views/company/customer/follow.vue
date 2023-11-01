<template>
  <div>
    <!--  最近联系时间  -->
    <div class="mb-20">
      <div class="fs-14 gray-text pb-6">发生以下行为会影响客户的“最近联系时间”，进而影响客户被自动移入公海的时间</div>
      <div class="card">
        <div class="head flex-middle space-between">
          <div class="check-all">
            <el-checkbox v-model="contact.checkAll" :indeterminate="contact.isIndeterminate"
                         :disabled="!contact.showBtnGroup" @change="handleCheckAllContact">全选
            </el-checkbox>
          </div>
          <div>
            <el-row v-if="contact.showBtnGroup">
              <el-button round size="mini" @click="contact.showBtnGroup=false">取消</el-button>
              <el-button round size="mini" type="primary" @click="onContactSave">保存</el-button>
            </el-row>
            <el-button v-else type="primary" round size="mini" @click="contact.showBtnGroup=true">编辑</el-button>
          </div>
        </div>
        <div class="body">
          <el-checkbox-group v-model="contact.checkedContacts" @change="handleCheckedContact">
            <el-checkbox class="my-6" v-for="(cItem,index) in contact.contactList" :label="cItem.id" :key="index"
                         :disabled="!contact.showBtnGroup">
              {{ cItem.name }}
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
            <el-checkbox v-model="follow.checkAll" :indeterminate="follow.isIndeterminate"
                         :disabled="!follow.showBtnGroup" @change="handleCheckAllFollow">全选
            </el-checkbox>
          </div>
          <div>
            <el-row v-if="follow.showBtnGroup">
              <el-button round size="mini" @click="follow.showBtnGroup=false">取消</el-button>
              <el-button round size="mini" type="primary" @click="onFollowSave">保存</el-button>
            </el-row>
            <el-button v-else type="primary" round size="mini" @click="follow.showBtnGroup=true">编辑</el-button>
          </div>
        </div>
        <div class="body">
          <el-checkbox-group v-model="follow.checkedFollows" @change="handleCheckedFollow">
            <el-checkbox class="my-6" v-for="(fItem,index) in follow.followList" :label="fItem.id" :key="index"
                         :disabled="!follow.showBtnGroup">{{ fItem.name }}
            </el-checkbox>
          </el-checkbox-group>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {followRulesEdit, getFollowRulesList} from "@/api/company/follow";

export default {
  data() {
    return {
      contact: {
        checkAll: false,
        isIndeterminate: false,
        showBtnGroup: false,
        showLoading: false,
        checkedContacts: [],
        contactList: []
      },
      follow: {
        checkAll: false,
        isIndeterminate: false,
        showBtnGroup: false,
        showLoading: false,
        checkedFollows: [],
        followList: []
      },
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    async getList(type) {
      try {
        const res = await getFollowRulesList()
        if (res.code === 200) {
          this.contact.contactList = []
          this.follow.followList = []
          res.data.forEach(val => {
            if (val.category === 1) {
              this.contact.contactList.push(val)
              val.activeFlag && this.contact.checkedContacts.push(val.id)
            } else if (val.category === 2) {
              this.follow.followList.push(val)
              val.activeFlag && this.follow.checkedFollows.push(val.id)
            }
          })
          this.handleCheckedContact(this.contact.checkedContacts)
          this.handleCheckedFollow(this.follow.checkedFollows)
        }
      } catch {
      }
    },
    editCommReq(list) {
      return new Promise(async (resolve) => {
        try {
          const res = await followRulesEdit(list)
          if (res.code === 200) {
            this.$message({
              type: 'success',
              message: '修改成功',
            })
            resolve(true)
          }
        } catch {
        }
      })
    },

    handleCheckAllContact(val) {
      const checkedLen = this.contact.checkedContacts.length
      const listLen = this.contact.contactList.length
      const checked = checkedLen === listLen
      this.contact.checkedContacts = !checked ? this.contact.contactList.map(val => val.id) : []
      this.contact.isIndeterminate = checkedLen > 0 && checkedLen < listLen
      this.contact.checkAll = val
    },

    handleCheckedContact(value) {
      const checkedLen = value.length
      const listLen = this.contact.contactList.length
      this.contact.checkAll = checkedLen === listLen
      this.contact.isIndeterminate = checkedLen > 0 && checkedLen < listLen
    },

    onContactSave() {
      let newArr = []
      this.contact.contactList.forEach((val) => {
        if (val.category === 1) {
          newArr.push({
            id: val.id,
            activeFlag: this.contact.checkedContacts.includes(val.id)
          })
        }

      })
      this.editCommReq(newArr).then(res => {
        if (res) {
          this.contact.showBtnGroup = false
          this.getList()
        }
      })
    },

    handleCheckAllFollow(val) {
      const checkedLen = this.follow.checkedFollows.length
      const listLen = this.follow.followList.length
      const checked = checkedLen === listLen
      this.follow.checkedFollows = !checked ? this.follow.followList.map(val => val.id) : []
      this.follow.isIndeterminate = checkedLen > 0 && checkedLen < listLen
      this.follow.checkAll = val
    },
    handleCheckedFollow(value) {
      const checkedLen = value.length
      const listLen = this.follow.followList.length
      this.follow.checkAll = checkedLen === listLen
      this.follow.isIndeterminate = checkedLen > 0 && checkedLen < listLen
    },
    onFollowSave() {
      let newArr = []
      this.follow.followList.forEach((val) => {
        if (val.category === 2) {
          newArr.push({
            id: val.id,
            activeFlag: this.follow.checkedFollows.includes(val.id)
          })
        }
      })
      this.editCommReq(newArr).then(res => {
        if (res) {
          this.follow.showBtnGroup = false
          this.getList()
        }
      })
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
