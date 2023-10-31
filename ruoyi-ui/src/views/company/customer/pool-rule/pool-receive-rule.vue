<template>
  <div class="mb-20">
    <div class="space-between">
      <div class="flex-middle">
        <span class="fs-13 bold">公海领取规则</span>
        <span class="fs-13 ml-10 gray-text">把客户从公海移至私海需遵守的规则</span>
      </div>
    </div>
    <div>
      <div class="flex-column">
        <div class="fs-14 my-16">领取限制</div>
        <div class="flex-middle">
          <el-radio-group v-model="receiveLimit" @change="editSettings">
            <el-radio :label="1">不限制</el-radio>
            <el-radio :label="0">限制</el-radio>
          </el-radio-group>
          <div class="flex-middle">
            <el-input-number
                class="ml-8"
                style="width: 100px"
                v-model="receiveLimitDay"
                :disabled="!!receiveLimit"
                :controls="false"
                @blur="editSettings"></el-input-number>
            <span class="fs-14">天内，原跟进人不能领取同一个客户</span>
          </div>
        </div>
      </div>
      <div class="flex-column">
        <div class="fs-14 my-16">商机查看规则</div>
        <div class="flex-middle">
          <el-radio-group v-model="viewRule" @change="editSettings">
            <el-radio :label="1">领取公海客户后，不能查看客户历史商机</el-radio>
            <el-radio :label="2">领取公海客户后</el-radio>
          </el-radio-group>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  data() {
    return {
      receiveLimit: 1,
      receiveLimitDay: 2,
      viewRule: 1,
    }
  },
  computed: {
    ...mapState({
      settings: state => state.company.settings
    })
  },
  mounted() {
    this.$watch('settings', (newVal) => {
      const {claimLimitFlag, claimLimitDays, opportunityViewRule} = newVal
      this.receiveLimit = claimLimitFlag
      this.receiveLimitDay = claimLimitDays
      this.viewRule = opportunityViewRule
    }, {immediate:true})
  },
  methods: {
    editSettings() {
      this.$store.dispatch('company/EditCompanyCustomerSettings', {
        ...this.settings,
        claimLimitFlag: this.receiveLimit,
        claimLimitDays: this.receiveLimitDay,
        opportunityViewRule: this.viewRule
      }).then(res => {
        if (res) {
          this.$message({
            type: 'success',
            message: '修改成功'
          })
        }
      })
    },
  }
}
</script>

<style lang="scss" scoped>

</style>
