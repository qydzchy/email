<template>
  <div class="mt-20">
    <el-row>
      <el-radio-group v-model="chooseRadio">
        <el-radio v-for="(radio,index) in radioList" :key="index" :label="radio.label">{{ radio.name }}</el-radio>
      </el-radio-group>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      chooseRadio: '0',
      radioList: [
        {
          label: '0',
          name: '全部'
        },
        {
          label: '1',
          name: '营销盘活'
        },
        {
          label: '2',
          name: '日常跟进'
        },
        {
          label: '3',
          name: '常规分类'
        },
        {
          label: '4',
          name: '国际站'
        },
        {
          label: '5',
          name: '交易'
        },

      ]
    }
  },
  mounted() {
  }
}
</script>

<style lang="scss" scoped>

</style>
