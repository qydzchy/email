<template>
  <div class="mb-20">
    <div class="space-between">
      <div class="flex-middle">
        <span class="fs-13 bold">公海显示设置</span>
        <span class="fs-13 ml-10 gray-text">客户落入公海后的显示设置</span>
      </div>

    </div>
    <div class="mt-10">
      <span class="fs-13 gray-text">是否显示公海联系人 （注：隐藏公海联系人后公海客户将不允许被编辑）</span>
    </div>
    <div class="my-40">
      <el-radio-group v-model="showSettingRadio" @change="editSettings">
        <el-radio :label="1">是</el-radio>
        <el-radio :label="0">否</el-radio>
      </el-radio-group>
    </div>

  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  data() {
    return {
      showSettingRadio: 1,
    }
  },
  computed: {
    ...mapState({
      settings: state => state.company.settings
    })
  },
  mounted() {
    this.$watch('settings', (newVal) => {
      const {showPublicLeadsFlag} = newVal
      this.showSettingRadio = showPublicLeadsFlag
    }, {immediate:true})
  },
  methods: {
    editSettings() {
      this.$store.dispatch('company/EditCompanyCustomerSettings', {
        ...this.settings,
        showPublicLeadsFlag: this.showSettingRadio
      }).then(res => {
        if (res) {
          this.$message({
            type: 'success',
            message: '修改成功'
          })
        }
      })
    }
  }
}
</script>

<style lang="scss">

</style>
