<template>
  <div class="tip-tab">
    <div v-infinite-scroll="load" :infinite-scroll-disabled="disabled" v-if="count">
      <el-card class="mx-16 mb-20 fs-12" v-for="item in count" :key="item">
        <el-row class="header">
          Valerio Brignoli Srl
        </el-row>
        <el-row class="main">
          国家：Italy - 意大利
          地址：Via dell’Industria, 535
          简介：主营行业为3553 Woodworking machinery，年销售额2.67百万美元，成立于1960年。
        </el-row>
        <el-row class="footer"></el-row>
      </el-card>
      <div style="text-align: center">
        <div v-if="loading">加载中...</div>
        <div v-if="noMore">没有更多了</div>
      </div>
    </div>
    <el-empty v-else></el-empty>


  </div>
</template>

<script>
export default {
  data() {
    return {
      loading: false,
      count: 0,
    }
  },
  computed: {
    noMore() {
      return this.count >= 20
    },
    disabled() {
      return this.loading || this.noMore
    }
  },
  mounted() {
    // this.load()
  },
  methods: {
    load() {
      console.log('loading')
      this.loading = true
      setTimeout(() => {
        this.count += 2
        this.loading = false
      }, 2000)
    }
  }
}
</script>

<style lang="scss" scoped>
.tip-tab {
  background: #fafafa;
  padding: 16px 0;
  min-height: 60vh;
  overflow: auto;

  .header {
    display: flex;
    align-items: center;
    line-height: 22px;
  }

  .main {
    line-height: 20px;
    padding: 14px 0;
    color: #5d5d5d;
  }

  .footer {
    line-height: 17px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-bottom: 16px;
    color: #909090;
  }
}
</style>
