<template>
  <div class="pool-rule">
    <!--  移入公海规则  -->
    <IntoPoolRule/>
    <!-- 移入公海白名单 -->
    <IntoPoolWhitelist/>
    <!--  公海领取规则  -->
    <PoolReceiveRule/>
    <!--  领取上限  -->
    <PoolReceiveMax/>
    <!--  公海显示设置  -->
    <PoolShowSetting/>
    <!--  公海分组  -->
    <PoolGroup/>
    <!--  移入公海原因  -->
    <PoolReason/>
  </div>
</template>

<script>
import IntoPoolRule from './pool-rule/into-pool-rule.vue'
import IntoPoolWhitelist from "./pool-rule/into-pool-whitelist.vue";
import PoolReceiveRule from "./pool-rule/pool-receive-rule.vue";
import PoolReceiveMax from "./pool-rule/pool-receive-max.vue";
import PoolShowSetting from "./pool-rule/pool-show-setting.vue";
import PoolGroup from "./pool-rule/pool-group.vue";
import PoolReason from "./pool-rule/pool-reason.vue";

export default {
  components: {
    IntoPoolRule,
    IntoPoolWhitelist,
    PoolReceiveRule,
    PoolReceiveMax,
    PoolShowSetting,
    PoolGroup,
    PoolReason
  },
}
</script>

<style lang="scss">
.add-rule-btn {
  color: #0064ff;
  border-color: #0064ff;
}
.form-item {
  width: 100%;
  display: inline-block;
}
</style>
