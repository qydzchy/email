<template>
  <div class="black-list">
    <div class="top flex-middle space-between">
      <div class="fs-14" style="color: #8c8c8c">被加为建档黑名单的邮箱，将无法被公司内同事建档为联系人</div>
      <el-button round type="primary" size="small" @click="addBlackList">添加到黑名单</el-button>
    </div>
    <div class="main mt-16">
      <div class="card">
        <div class="head flex-middle space-between">
          <div class="check-all">
            <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAll">全选
            </el-checkbox>
          </div>
          <div class="checked-count">
            已选中{{ checkedEmailCount }}个邮箱
          </div>
          <div class="checked-delete">
            <el-popover
                placement="left-start"
                width="200"
                v-model="deleteEmailsPopover">
              <p><i class="el-icon-warning pr-4" style="color: #E6A23C"></i>确认删除选中邮箱地址？</p>
              <div style="text-align: right; margin: 0">
                <el-button round size="mini" @click="deleteEmailsPopover = false">取消</el-button>
                <el-button round type="primary" size="mini" @click="confirmDeleteEmail">确定</el-button>
              </div>
              <el-button slot="reference" type="primary" round size="mini" :disabled="!checkedEmailCount">删除
              </el-button>
            </el-popover>

          </div>
        </div>
        <div class="body">
          <el-checkbox-group v-model="checkedEmail" @change="handleCheckedEmail">
            <el-checkbox class="my-6" v-for="(email,index) in emails" :label="email.label" :key="index"
                         :disabled="email.disabled"></el-checkbox>
          </el-checkbox-group>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      checkAll: false,
      checkedEmail: [],
      emails: [
        {
          label: 'w0r1d_space@tom.com',
          disabled: false,
        },
        {
          label: 'w0r1d_space@sina.com',
          disabled: false,
        },
        {
          label: 'service@intl.paypal.com',
          disabled: true,
        },
      ],
      isIndeterminate: false,
      deleteEmailsPopover: false
    }
  },
  computed: {
    checkedEmailCount() {
      return this.checkedEmail.length
    },
  },
  methods: {
    // 添加黑名单
    addBlackList() {
      const h = this.$createElement;
      // 强制转换为string类型
      const vNode = h('div', {class: 'flex-middle'}, [
        h('span', {class: 'pr-4'}, '设置建档黑名单'),
        h('el-tooltip', {
          class: 'pointer',
          props: {
            effect: 'dark',
            placement: 'top-start',
            content: '邮箱的标准格式为： 邮箱后缀的标准格式： @***.***'
          }
        }, [
          h('i', {class: 'el-icon-warning'})
        ])
      ])
      this.$prompt(vNode, '建档黑名单', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
        inputErrorMessage: '邮箱格式不正确',
        inputPlaceholder: '邮箱/邮箱后缀',
        roundButton: true,
        beforeClose: (action, instance, done) => {
          if (action === 'confirm') {
            instance.confirmButtonLoading = true;
            instance.confirmButtonText = '执行中...';
            setTimeout(() => {
              done();
              setTimeout(() => {
                instance.confirmButtonLoading = false;
              }, 300);
            }, 3000);
          } else {
            done();
          }
        }
      }).then(action => {
        console.log(action)
      });
    },
    // 全选
    handleCheckAll(val) {
      let checked = val ? this.emails.filter(val => !val.disabled).map(val => val.label) : [];
      this.checkedEmail = checked
      if (!checked.length || this.isIndeterminate) {
        this.checkAll = false
        this.isIndeterminate = false
        return
      }
      if (checked.length !== this.emails.length) {
        this.isIndeterminate = true;
      }
    },
    // 勾选
    handleCheckedEmail(value) {
      let checkedCount = value.length;
      this.checkAll = checkedCount === this.emails.length;
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.emails.length;
    },
    // 确认删除邮箱
    confirmDeleteEmail() {
      this.deleteEmailsPopover = false
    }
  }
}
</script>

<style lang="scss" scoped>
.black-list {
  .top {

  }

  .main {
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
  }
}
</style>
