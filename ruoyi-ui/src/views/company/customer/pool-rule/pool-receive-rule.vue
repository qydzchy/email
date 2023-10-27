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
          <el-radio-group v-model="receiveLimit">
            <el-radio :label="1">不限制</el-radio>
            <el-radio :label="0">限制</el-radio>
          </el-radio-group>
          <div class="flex-middle">
            <el-input-number v-model="receiveLimitDay" :disabled="!!receiveLimit" :controls="false"
                             class="ml-8" style="width: 100px"></el-input-number>
            <span class="fs-14">天内，原跟进人不能领取同一个客户</span>
          </div>
        </div>
      </div>
      <div class="flex-column">
        <div class="fs-14 my-16">商机查看规则</div>
        <div class="flex-middle">
          <el-radio-group v-model="viewRule">
            <el-radio :label="1">领取公海客户后，不能查看客户历史商机</el-radio>
            <el-radio :label="0">领取公海客户后</el-radio>
          </el-radio-group>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      receiveLimit: 1,
      receiveLimitDay: 2,
      viewRule: 1,
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
