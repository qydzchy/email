<template>
  <div>
    <div class="mb-20">
      <div class="fs-14 gray-text pb-6">显示或隐藏客户查重列表的字段</div>
      <div class="card">
        <div class="head flex-middle space-between">
          <div class="check-all">
            <el-checkbox v-model="checkAll" :indeterminate="isIndeterminate" :disabled="!showBtnGroup"
                         @change="handleCheckAll">全选
            </el-checkbox>
          </div>
          <div>
            <el-row v-if="showBtnGroup">
              <el-button round size="mini" @click="showBtnGroup=false">取消</el-button>
              <el-button round size="mini" type="primary" @click="onSave">保存</el-button>
            </el-row>
            <el-button v-else type="primary" round size="mini" @click="showBtnGroup=true">编辑</el-button>
          </div>
        </div>
        <div class="body" v-loading="cardLoading">
          <el-checkbox-group v-if="list.length" v-model="checkedList" @change="handleChecked">
            <el-checkbox class="my-6" v-for="(item,index) in list" :label="item.id" :key="index"
                         :disabled="!showBtnGroup">
              {{ item.columnAlias }}
            </el-checkbox>
          </el-checkbox-group>
          <el-empty v-else :image-size="100"></el-empty>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {duplicateEdit, getDuplicateList} from "@/api/company/search";

export default {
  data() {
    return {
      checkAll: false,
      showBtnGroup: false,
      isIndeterminate: false,
      checkedList: [],
      list: [],
      cardLoading: false
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    async getList() {
      this.cardLoading = true
      try {
        const res = await getDuplicateList().finally(() => {
          this.cardLoading = false
        })
        if (res.code === 200) {
          this.list = res.data
          this.list.forEach((val) => {
            if (val.activeFlag) {
              this.checkedList.push(val.id)
            }
          })
          this.handleChecked(this.checkedList)
        }
      } catch {
      }
    },
    handleCheckAll(val) {
      let checked = val ? this.list.filter(val => !val.disabled).map(val => val.id) : [];
      this.checkedList = checked
      if (!checked.length || this.isIndeterminate) {
        this.checkAll = false
        this.isIndeterminate = false
        return
      }
      if (checked.length !== this.list.length) {
        this.isIndeterminate = true;
      }
    },
    handleChecked(value) {
      let checkedCount = value.length;
      this.checkAll = checkedCount === this.list.length;
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.list.length;
    },
    async onSave() {
      let config = []
      this.list.map(val => {
        const isValid = this.checkedList.includes(val.id)
        config.push({id: val.id, activeFlag: isValid})
      })
      try {
        const res = await duplicateEdit(JSON.stringify(config))
        if (res.code === 200) {
          this.$message({
            type: 'success',
            message: '修改成功'
          })
          this.showBtnGroup = false
        }
      } catch {
      }
    },
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
