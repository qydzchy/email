<template>
  <div class="page-customer-detail">
    <div class="fs-20">客户详情</div>
    <div class="user-info card-bg px-16 py-16 mt-20"></div>
    <el-row type="flex" :gutter="10">
      <el-col :span="18" class="card-bg mt-16 pt-8 pb-16 px-8">
        <TableRowTabs :options="options"/>
      </el-col>
      <el-col :span="6">
        <div class="card-bg  mt-16 px-16 py-16">
          <div class="flex-middle space-between">
            <div class="fs-14 bold">计划日程</div>
            <el-tooltip content="添加日程">
              <i class="el-icon-circle-plus-outline"></i>
            </el-tooltip>
          </div>
          <ul class="plan-ul">
            <li class="plan-li pointer">
              <div class="circle"></div>
              <span class="date">10-18</span>
              <span class="content">客户生日：测试公司001-王五</span>
            </li>
          </ul>
        </div>
        <div class="card-bg  mt-16 px-16 py-16">
          <div class="contact flex-middle space-between">
            <div class="black-text">联系人</div>
            <div>
              <i class="el-icon-search"></i>
              <i class="el-icon-edit pl-10"></i>
            </div>
          </div>
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
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import TableRowTabs from '../TableRowTabs.vue'

export default {
  components: {
    TableRowTabs
  },
  data() {
    return {
      options: {
        isShowSchedule: false,
        isTabSetHeight: false,
        isShowInfo: false
      },
      contactList: [
        {
          id: 1,
          checked: true
        },
      ],
    }
  },
  mounted() {
    this.curId = this.$route.params?.id
  },
  methods: {
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
.page-customer-detail {
  padding: 10px 20px;
  height: 100%;
  background-color: rgba(245, 245, 245);
}

.card-bg {
  border-radius: 8px;
  background-color: rgba(255, 255, 255)
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
