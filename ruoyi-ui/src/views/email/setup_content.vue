<template>
  <div>
    <div v-if="selectedTab === '常规'">常规内容</div>
    <div v-else-if="selectedTab === '模板'">模板内容</div>
    <div v-else-if="selectedTab === '邮箱管理'">邮箱管理</div>
    <!-- 其他条件... -->
  </div>
</template>

<script>
export default {
  props: ['selectedTab']
};
</script>
