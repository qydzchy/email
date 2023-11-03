<template>
  <div>
    <el-button round @click="drawer=true">更多筛选</el-button>
    <el-drawer
      title="筛选条件"
      :visible.sync="drawer"
      :direction="direction"
      @close="handleClose">
      <div class="pt-32">
        <div class="flex-center">
          <el-radio-group v-model="filterType" @change="handleFilter">
            <el-radio-button label="ordinary">普通筛选</el-radio-button>
            <el-radio-button label="senior">高级筛选</el-radio-button>
          </el-radio-group>
        </div>
        <!--    filter    -->
        <div class="pt-10 px-20">
          <formCreate
            v-show="filterType==='ordinary'"
            v-model="fApi"
            :rule="rule"
            :option="option"/>
          <formCreate v-loading="formLoading" v-show="filterType==='senior'" v-model="fApi2" :rule="rule2"
                      :option="option2"/>
        </div>
        <!--   operate     -->
        <div class="drawer-operate">
          <div class="flex-middle space-between" style="width: 660px">
            <el-button round>设置常用筛选项</el-button>
            <el-row>
              <el-button round>将所选条件生成为客群</el-button>
              <el-button round>清空</el-button>
              <el-button type="primary" round>确认</el-button>
            </el-row>
          </div>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import {ordinaryRuleForm} from './FilterFormOption'
import {formOption} from "@/constant/form"

export default {
  data() {
    return {
      drawer: false,
      direction: 'rtl',
      filterType: 'ordinary',
      fApi: {},
      rule: [...ordinaryRuleForm],
      option: {
        ...formOption, form: {
          ...formOption.form,
          inline: true,
          labelWidth: '100%',
          labelPosition: 'top',
          width: '100%'
        }
      },
      formLoading: false,
      fApi2: {},
      rule2: [],
      option2: {}
    }
  },
  methods: {
    onClick() {
      console.log('click')
    },
    handleFilter(value) {
      if (value === 'ordinary') {
        this.fApi2.resetFields()
      } else if (value === 'senior') {
        // this.formLoading = true
        setTimeout(() => {
          // this.formLoading = false
        }, 2000)
        this.fApi.resetFields()
      }
    },
    handleClose() {

    },
  }
}
</script>

<style lang="scss" scoped>
::v-deep .el-drawer.rtl {
  width: 700px !important;
}

::v-deep .el-drawer__header {
  border-bottom: 1px solid #f0f0f0;
  margin-bottom: 0;
  padding-bottom: 14px;

  > span {
    color: #000000;
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
}

::v-deep .el-form--label-top .el-form-item__label {
  padding: 0;
}

::v-deep .el-form-item {
  margin-bottom: 0;
}

::v-deep .el-form-item,
::v-deep .el-select,
::v-deep .el-range-editor.el-input__inner,
::v-deep .el-cascader {
  width: 100%;
}
</style>
