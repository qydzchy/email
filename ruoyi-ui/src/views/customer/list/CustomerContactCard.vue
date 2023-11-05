<template>
  <div class="contact-card">
    <div class="fs-16 my-10">联系人{{ formList.length }}</div>
    <div>
      <div class="card" v-for="(item,index) in formList" :key="index">
        <div class="flex-middle space-between">
          <div class="card-title">
            <span>{{ item.name || `联系人${index + 1}` }}</span>
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
            <el-input v-model="item.name" placeholder="请输入"/>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input placeholder="请输入"/>
          </el-form-item>
          <el-form-item label="社交平台"/>
          <el-form-item class="special-item" v-for="(contact,idxP) in item.platformList" :key="idxP">
            <el-row type="flex" :gutter="4">
              <el-col :span="8">
                <el-select placeholder="社交平台"></el-select>
              </el-col>
              <el-col :span="13">
                <el-input placeholder="请输入"/>
              </el-col>
              <el-col :span="3" class="flex-middle">
                <i v-if="idxP!==0" class="fs-20 pl-6 el-icon-remove-outline pointer"
                   @click="onReduce(contact.id,'platformList')"></i>
                <i class="fs-20 pl-6 el-icon-circle-plus-outline pointer" @click="onAdd('platformList')"></i>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item label="联系电话"/>
          <el-form-item class="special-item" v-for="(contact,idxC) in item.contactList" :key="idxC">
            <el-row type="flex" :gutter="4">
              <el-col :span="8">
                <el-select placeholder="电话区号"></el-select>
              </el-col>
              <el-col :span="13">
                <el-input placeholder="请输入"/>
              </el-col>
              <el-col :span="3" class="flex-middle">
                <i v-if="idxC!==0" class="fs-20 pl-6 el-icon-remove-outline pointer"
                   @click="onReduce(contact.id,'contactList')"></i>
                <i class="fs-20 pl-6 el-icon-circle-plus-outline pointer" @click="onAdd('contactList')"></i>
              </el-col>
            </el-row>
          </el-form-item>

          <el-row v-if="item.show">
            <el-form-item label="职级">
              <el-select></el-select>
            </el-form-item>
            <el-form-item label="职位" style="width: 210px;">
              <el-select style="width:220px"></el-select>
            </el-form-item>
            <el-form-item label="生日" style="width: 210px;">
              <el-date-picker></el-date-picker>
            </el-form-item>
            <el-form-item label="性别" style="width: 210px;">
              <el-row>
                <el-radio-group>
                  <el-radio value="0">不限</el-radio>
                  <el-radio value="1">男</el-radio>
                  <el-radio value="2">女</el-radio>
                </el-radio-group>
              </el-row>
            </el-form-item>
            <el-form-item label="头像/名片">
              <el-upload action="" list-type="picture"/>
            </el-form-item>
            <el-form-item label="联系人备注">
              <el-input type="textarea" :rows="3"></el-input>
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
const addConstruct = {
  id: +new Date(),
  show: false,
  platformList: [
    {id: +new Date()}
  ],
  contactList: [
    {id: +new Date()},
  ]
}

export default {
  data() {
    return {
      formList: [
        {
          id: +new Date(),
          name: '',
          show: false,
          platformList: [
            {id: +new Date()}
          ],
          contactList: [
            {id: +new Date()},
          ]
        },
      ]
    }
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
      this.formList.push({...addConstruct})
    },
    onDelContactForm(id) {
      this.formList = this.formList.filter(val => val.id !== id)
    },
    onSortContactForm(id) {
      let tempValue = {}
      let filterResList = this.formList.filter(val => {
        if (val.id !== id) {
          return val
        } else {
          Object.assign(tempValue, val)
        }
      })
      filterResList.unshift(tempValue)
      this.formList = filterResList
    },
    onAdd(type) {
      this.formList.map(val => {
        val[type].push({
          id: +new Date()
        })
        return val
      })
    },
    onReduce(id, type) {
      this.formList.map(val => {
        val[type] = val[type].filter(val => val.id !== id)
        return val
      })
    }
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
