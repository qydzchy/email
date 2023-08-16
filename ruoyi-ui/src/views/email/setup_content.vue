<template>
  <div>
    <div v-if="selectedTab === '常规'">常规内容</div>
    <div v-else-if="selectedTab === '模板'">模板内容</div>
    <component :is="dynamicComponent"></component>
    <!-- 其他条件... -->
  </div>
</template>

<script>
import EmailManagementComponent from './email_management/list.vue';

export default {
  data() {
    return {
      currentTab: '常规'
    };
  },
  props: ['selectedTab'],
  computed: {
    dynamicComponent() {
      if (this.selectedTab === '邮箱管理') {
        return EmailManagementComponent;
      }
      // ... 可以为其他选项项返回其他组件
      return null;
    }
  }
};
</script>
