<template>
  <div>
    <div class="mb-20">
      <div class="fs-14 gray-text pb-6">显示或隐藏客户查重列表的字段</div>
      <div class="card">
        <div class="head flex-middle space-between">
          <div class="check-all">
            <el-checkbox v-model="checkAll" :disabled="!showBtnGroup" @change="handleCheckAll">全选
            </el-checkbox>
          </div>
          <div>
            <el-row v-if="showBtnGroup">
              <el-button round size="mini" @click="showBtnGroup=false">取消</el-button>
              <el-button round size="mini" type="primary">保存</el-button>
            </el-row>
            <el-button v-else type="primary" round size="mini" @click="showBtnGroup=true">编辑</el-button>
          </div>
        </div>
        <div class="body">
          <el-checkbox-group v-model="checkedList" @change="handleChecked">
            <el-checkbox class="my-6" v-for="(item,index) in list" :label="item.label" :key="index"
                         :disabled="!showBtnGroup">
              {{ item.text }}
            </el-checkbox>
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
      showBtnGroup: false,
      showLoading: false,
      checkedList: ['2', '3', '4', '5', '6'],
      list: [
        {
          label: '1',
          text: '客户编号',
          disabled: false,
        },
        {
          label: '2',
          text: '客户阶段',
          disabled: false,
        },
        {
          label: '3',
          text: '公司名称/简称',
          disabled: false,
        },
        {
          label: '4',
          text: '联系人名',
          disabled: false,
        },
        {
          label: '5',
          text: '客户邮箱',
          disabled: false,
        },
        {
          label: '6',
          text: '客户电话',
          disabled: false,
        },
        {
          label: '7',
          text: '客户来源',
          disabled: false,
        },
        {
          label: '8',
          text: '国家地区',
          disabled: false,
        },
        {
          label: '9',
          text: '原跟进人',
          disabled: false,
        },
        {
          label: '10',
          text: '当前归属',
          disabled: false,
        },
        {
          label: '11',
          text: '创建时间',
          disabled: false,
        },
        {
          label: '12',
          text: '最近联系时间',
          disabled: false,
        },
        {
          label: '13',
          text: '社交账号',
          disabled: false,
        },
      ]
    }
  },
  methods: {
    handleCheckAll() {

    },
    handleChecked() {
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
