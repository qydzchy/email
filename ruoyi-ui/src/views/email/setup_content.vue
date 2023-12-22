<template>
  <div>
    <div v-if="selectedTab === '常规'"></div>
    <div v-else-if="selectedTab === '模板'"></div>
    <div v-else-if="selectedTab === '标签'"></div>
    <component :is="dynamicComponent"></component>
    <!-- 其他条件... -->
  </div>
</template>

<script>
import TemplateComponent from './template/list.vue';
import EmailManagementComponent from './email_management/list.vue';
import FolderComponent from './folder/list.vue';
import LabelComponent from './label/list.vue';
import QuickTextComponent from './quick_text/list.vue';
import BlacklistComponent from './blacklist/list.vue';

export default {
  data() {
    return {
      currentTab: '常规'
    };
  },
  props: ['selectedTab'],
  computed: {
    dynamicComponent() {
      if (this.selectedTab === '模板') {
        return TemplateComponent;
      } else if (this.selectedTab === '邮箱管理') {
        return EmailManagementComponent;
      } else if (this.selectedTab === '文件夹') {
        return FolderComponent;
      } else if (this.selectedTab === '标签') {
        return LabelComponent;
      } else if (this.selectedTab === '快速文本') {
        return QuickTextComponent;
      } else if (this.selectedTab === '黑名单') {
        return BlacklistComponent;
      }
      // ... 可以为其他选项项返回其他组件
      return null;
    }
  }
};
</script>
