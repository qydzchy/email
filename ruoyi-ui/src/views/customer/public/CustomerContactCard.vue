<template>
  <div class="contact-card">
    <div class="fs-16 my-10">联系人{{ formList.length }}</div>
    <div>
      <div class="card" v-for="(item,index) in formList" :key="index">
        <div class="flex-middle space-between">
          <div class="card-title">
            <span>{{ item.nickName || `联系人${index + 1}` }}</span>
            <span v-if="index===0" class="pl-6">
               <svg-icon icon-class="important"/>
            </span>
          </div>
          <div v-if="index!==0">
            <el-tooltip placement="top" content="设置为主联系人">
              <svg-icon class="pointer" icon-class="important-gray" @click="onSortContactForm(item.id)"/>
            </el-tooltip>
            <el-tooltip placement="top" content="删除">
              <i class="el-icon-delete pl-10 pointer" @click="onDelContactForm(item.id)"></i>
            </el-tooltip>
          </div>
        </div>

        <el-form>
          <el-form-item label="昵称">
            <el-input v-model="item.nickName" placeholder="请输入"/>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="item.email" placeholder="请输入"/>
          </el-form-item>
          <el-form-item label="社交平台"/>
          <el-form-item class="special-item" v-for="(platform,idxP) in item.socialPlatform" :key="`platform-${idxP}`">
            <el-row type="flex" :gutter="4">
              <el-col :span="8">
                <el-select v-model="platform.type" placeholder="社交平台">
                  <el-option v-for="(opt,index) in platformOption" :key="index" :label="opt.label"
                             :value="opt.value">
                    <span>
                      <svg-icon :icon-class="opt.svg"/>
                      {{ opt.label }}
                    </span>
                  </el-option>
                </el-select>
              </el-col>
              <el-col :span="13">
                <el-input v-model="platform.account" placeholder="请输入"/>
              </el-col>
              <el-col :span="3" class="flex-middle">
                <el-tooltip placement="top" content="删除">
                  <i v-if="idxP!==0" class="fs-20 pl-6 el-icon-remove-outline pointer"
                     @click="onReduce(platform.id,'socialPlatform')"></i>
                </el-tooltip>
                <el-tooltip placement="top" content="添加">
                  <i class="fs-20 pl-6 el-icon-circle-plus-outline pointer" @click="onAdd('socialPlatform')"></i>
                </el-tooltip>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item label="联系电话"/>
          <el-form-item class="special-item" v-for="(contact,idxC) in item.phone" :key="`contact-${idxC}`">
            <el-row type="flex" :gutter="4">
              <el-col :span="8">
                <el-select v-model="contact.phone_prefix" placeholder="电话区号" filterable clearable>
                  <el-option v-for="(prefix,index) in phonePrefixList" :key="index" :label="prefix.label"
                             :value="prefix.value"></el-option>
                </el-select>
              </el-col>
              <el-col :span="13">
                <el-input v-model="contact.phone" placeholder="请输入"/>
              </el-col>
              <el-col :span="3" class="flex-middle">
                <el-tooltip placement="top" content="删除">
                  <i v-if="idxC!==0" class="fs-20 pl-6 el-icon-remove-outline pointer"
                     @click="onReduce(contact.id,'phone')"></i>
                </el-tooltip>
                <el-tooltip placement="top" content="添加">
                  <i class="fs-20 pl-6 el-icon-circle-plus-outline pointer" @click="onAdd('phone')"></i>
                </el-tooltip>
              </el-col>
            </el-row>
          </el-form-item>

          <el-row v-if="item.show">
            <el-form-item label="职级">
              <el-select v-model="item.rank">
                <el-option v-for="(opt,index) in rankOption" :key="index" :label="opt.label"
                           :value="opt.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="职位">
              <el-input v-model="item.position" placeholder="请输入"></el-input>
            </el-form-item>
            <el-form-item label="生日">
              <el-date-picker style="width: 100%"  v-model="item.birthday"></el-date-picker>
            </el-form-item>
            <el-form-item label="性别" style="width: 210px;">
              <el-row>
                <el-radio-group v-model="item.sex">
                  <el-radio :label="1">不限</el-radio>
                  <el-radio :label="2">男</el-radio>
                  <el-radio :label="3">女</el-radio>
                </el-radio-group>
              </el-row>
            </el-form-item>
            <el-form-item label="头像/名片">
              <el-row style="width:100%"/>
              <image-upload
                  :value.sync="item.avatarOrBusinessCard"
                  :limit="1"
                  :isShowTip="false"
              >
              </image-upload>
            </el-form-item>
            <el-form-item label="联系人备注">
              <el-input v-model="item.contactRemarks" type="textarea" resize="none" :rows="3"></el-input>
            </el-form-item>
          </el-row>
        </el-form>
        <div>
          <div class="collapse flex-middle flex-center fs-12 mt-10 pointer" @click="onCollapsed(item.id)">
            {{ item.show ? '收起' : '展开全部(选填)' }}
            <i class="ml-6" :class="item.show ? 'el-icon-arrow-up':'el-icon-arrow-down'"></i>
          </div>
        </div>
      </div>
    </div>

    <div class="add-contact flex-middle flex-center fs-12 mt-10 pointer" @click="onAddContactForm">
      <i class="el-icon-circle-plus-outline"></i>
      <span>添加联系人</span>
    </div>
  </div>
</template>

<script>
import {generatePhone} from "@/utils/tools";
import {deepClone} from "@/utils";
import {getToken} from "@/utils/auth";

const addConstruct = {
  id: +new Date(),
  show: false,
  nickName: '',
  email: '',
  rank: '',
  position: '',
  birthday: '',
  sex: 1,
  avatarOrBusinessCard: '',
  contactRemarks: '',
  primaryContactFlag: true,
  socialPlatform: [
    {
      id: +new Date(),
      type: '',
      account: '',
    }
  ],
  phone: [
    {
      id: +new Date(),
      phone_prefix: '',
      phone: ''
    },
  ],
}

export default {
  props: {
    contactList: {
      type: Array,
      default: () => [],
      required: true,
    }
  },
  data() {
    return {
      baseUrl: process.env.VUE_APP_BASE_API,
      token: getToken(),
      formList: [
        {...deepClone(addConstruct)}
      ],
      platformOption: [
        {label: 'Facebook', value: 'Facebook', svg: 'Facebook'},
        {label: 'LinkedIn', value: 'LinkedIn', svg: 'LinkedIn'},
        {label: '阿里TM', value: '阿里TM', svg: 'AliTM'},
        {label: 'WhatsApp', value: 'WhatsApp', svg: 'WhatsApp'},
        {label: 'Skype', value: 'Skype', svg: 'Skype'},
        {label: 'WeChat', value: 'WeChat', svg: 'WeChats'},
        {label: 'QQ', value: 'QQ', svg: 'QQs'},
        {label: 'Instagram', value: 'Instagram', svg: 'Instagram'},
        {label: 'Twitter', value: 'Twitter', svg: 'Twitter'},
        {label: 'YouTube', value: 'YouTube', svg: 'YouTube'},
        {label: 'Messenger', value: 'Messenger', svg: 'Messenger'},
        {label: 'Line', value: 'Line', svg: 'Line'},
        {label: 'VK', value: 'VK', svg: 'VK'},
        {label: 'Telegram', value: 'Telegram', svg: 'Telegram'},
        {label: 'CrunchBase', value: 'CrunchBase', svg: 'CrunchBase'},
        {label: 'AngelList', value: 'AngelList', svg: 'AngelList'},
        {label: 'Pinterest', value: 'Pinterest', svg: 'Pinterest'},
        {label: 'Tiktok', value: 'Tiktok', svg: 'Tiktok'},
        {label: 'Kakao Talk', value: 'Kakao Talk', svg: 'Kakao Talk'},
        {label: 'Zalo', value: 'Zalo', svg: 'Zalo'},
        {label: 'Etsy', value: 'Etsy', svg: 'Etsy'},
        {label: 'Reddit', value: 'Reddit', svg: 'Reddit'},
        {label: 'Red', value: 'Red', svg: 'Red'},
        {label: 'Shopee', value: 'Shopee', svg: 'Shopee'},
        {label: 'Viber', value: 'Vibe', svg: 'Viber'},
      ],
      rankOption: [
        {value: 1, label: '普通职员'},
        {value: 2, label: '中层管理者'},
        {value: 3, label: '高层管理者'},
      ],
      phonePrefixList: generatePhone(),
    }
  },
  watch: {
    contactList: {
      handler(newVal) {
        if (newVal && newVal.length) {
          this.formList = newVal
        }
      },
      deep: true,
      immediate: true,
    },
  },
  methods: {
    onCollapsed(id) {
      this.formList.map((val) => {
        if (val.id === id) {
          val.show = !val.show
        }
        return val
      })
    },
    onAddContactForm() {
      this.formList.push({...deepClone(addConstruct), id: +new Date()})
    },
    onDelContactForm(id) {
      this.formList = this.formList.filter(val => val.id !== id)
    },
    onSortContactForm(id) {
      let tempValue = {}
      let filterResList = this.formList.filter(val => {
        if (val.id !== id) {
          val.primaryContactFlag = false
          return val
        } else {
          val.primaryContactFlag = true
          Object.assign(tempValue, val)
        }
      })
      filterResList.unshift(tempValue)
      this.formList = filterResList
    },
    onAdd(type) {
      this.formList.map(val => {
        val[type].push({
          id: +new Date(),
        })
        return val
      })
    },
    onReduce(id, type) {
      this.formList.map(val => {
        val[type] = val[type].filter(val => val.id !== id)
        return val
      })
    },
    getInnerData() {
      let innerData = JSON.parse(JSON.stringify(this.formList))
      innerData.map(val => {
        val.socialPlatform = JSON.stringify(val.socialPlatform)
        val.phone = JSON.stringify(val.phone)
        return val
      })
      return innerData
    },
    // 上传图片
    handleAvatarSuccess(file, id) {
      this.formList.map(val => {
        if (val.id === id) {
          val.avatarOrBusinessCard = file.raw
        }
      })
    },
    beforeAvatarUpload(file) {
    },
  }
}
</script>

<style lang="scss" scoped>
.contact-card {
  .card {
    display: block;
    box-sizing: border-box;
    padding: 16px;
    margin-bottom: 20px;
    border-radius: 4px;
    background-color: rgba(247, 248, 251);

    &:hover {
      background-color: rgba(236, 249, 255);
    }

    .card-title {
      &:before {
        color: #ff4d4f;
        content: "*";
        display: inline-block;
        font-family: SimSun, sans-serif;
        font-size: 14px;
        line-height: 1;
        margin-right: 4px;
      }
    }

    ::v-deep .el-form-item {
      margin-bottom: 0;
      margin-top: 10px;
    }

    ::v-deep .special-item {
      margin-top: 0;
      margin-bottom: 10px;
    }

  }

  .collapse {
    height: 40px;
    color: rgba(104, 108, 115);
  }

  .add-contact {
    height: 40px;
    color: rgba(104, 108, 115);
    background-color: rgba(247, 248, 251);
  }

}
</style>
