<template>
  <div class="page-customer-query">
    <div class="bold fs-26 pb-4">
      客户查重
    </div>
    <el-card shadow="always" class="my-20">
      <div class="py-10 flex-middle flex-center">
        <el-select class="custom-select" v-model="querySearch.key" placeholder="请选择">
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-select>
        <el-input
            class="custom-input"
            placeholder="搜索公司名称/简称、客户编号、邮箱地址、邮箱后缀、联系人名称、电话、社交账号"
            v-model="querySearch.value"
        ></el-input>
        <el-button class="custom-btn radius-0" type="primary">查询</el-button>
      </div>
    </el-card>
    <el-card shadow="always" class="container mb-20"></el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      querySearch: {
        key: '111',
        value: ''
      },
      options: [{
        value: '111',
        label: '黄金糕'
      }, {
        value: '选项2',
        label: '双皮奶'
      }, {
        value: '选项3',
        label: '蚵仔煎'
      }, {
        value: '选项4',
        label: '龙须面'
      }, {
        value: '选项5',
        label: '北京烤鸭'
      }],
    }
  },
  mounted() {

  }
}
</script>

<style lang="scss" scoped>
.custom-select, .custom-input, .custom-btn {

}

.custom-select {
  width: 140px;

  ::v-deep .el-input__inner {
    border-top-left-radius: 4px;
    border-bottom-left-radius: 4px;
  }
}

.custom-input {
  width: 60%;

  ::v-deep .el-input__inner {
    border-left-width: 0 !important;
  }
}

.custom-btn {
  height: 42px;
  border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
}

.container {
  min-height: 66vh;
}

::v-deep .el-input__inner {
  height: 42px;
  border-radius: unset;
  border: 1px solid #1890ff;
}

::v-deep .el-card__body {
  width: 100%;
}

</style>
