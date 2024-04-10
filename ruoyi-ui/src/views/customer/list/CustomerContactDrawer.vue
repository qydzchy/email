<template>
  <div>
    <el-drawer
        title="筛选条件"
        :visible.sync="visible"
        :direction="direction"
        :append-to-body="true"
        destroy-on-close
        @close="handleClose">
      <template #title>
        <div class="header flex-middle space-between py-20 px-20">
          <div class="black-text">
            编辑联系人
          </div>
        </div>
      </template>
      <div class="container">
        <CustomerContactCard ref="contact-card" :contact-list.sync="rowData.contactList"/>
      </div>

      <!--   operate     -->
      <div class="drawer-operate">
        <div class="wrap flex-middle flex-end">
          <el-button round :loading="btnLoading" @click="handleClose">取消</el-button>
          <el-button type="primary" :loading="btnLoading" round @click="onConfirm">确认</el-button>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import CustomerContactCard from "./CustomerContactCard.vue";
import {editCustomer} from "@/api/customer/publicleads";

export default {
  props: {
    visible: {
      type: Boolean,
      default: false,
      required: false,
    },
    rowData: {
      type: Object,
      default: () => {
        return {
          contactList: []
        }
      },
      required: false,
    }
  },
  components: {
    CustomerContactCard
  },
  data() {
    return {
      direction: 'rtl',
      btnLoading: false
    }
  },
  methods: {
    async editCustomerPrivate(data) {
      this.btnLoading = true
      try {
        const res = await editCustomer({...data}).finally(() => {
          this.btnLoading = false
        })
        if (res.code === 200) {
          this.$message.success('修改成功')
          this.$emit('onConfirm')
        }
      } catch {
      }
    },
    handleClose() {
      this.$emit('update:visible', false)
    },
    onConfirm() {
      let contactList = this.$refs['contact-card'].getInnerData()
      if (!contactList) {
        return
      }
      contactList = contactList.map((val, index) => { // 在这里添加 'index' 参数
        delete val.show
        // 假设你想根据索引设置 primaryContactFlag
        val.primaryContactFlag = +!index
        return val
      })
      let config = {
        id: this.rowData.id,
        contactList
      }
      this.editCustomerPrivate(config)
    },
  }
}
</script>

<style lang="scss" scoped>
::v-deep .el-drawer.rtl {
  width: 600px !important;
}

.header {
  border-bottom: 1px solid #f0f0f0;
}

::v-deep .el-drawer__header {
  padding: 0;
  margin: 0;
}


.container {
  padding: 0 20px;

  .collapse {
    height: 40px;
    color: rgba(104, 108, 115);
    background-color: rgba(247, 248, 251);
  }
}

::v-deep .el-drawer__body {
  padding-bottom: 100px;
  position: relative;
  width: inherit;

  .drawer-operate {
    width: inherit;
    box-sizing: border-box;
    position: fixed;
    bottom: 0;
    padding: 16px 24px;
    background-color: #ffffff;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .drawer-operate > .wrap {
    width: 100%;
  }
}

</style>
