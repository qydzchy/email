<template>
  <div class="mm-split-pane mm-split-pane__right" style="left: 12.2835%;">
    <div class="mail-plus-config layout-extraSidebar-content" mail-type="" folder-id="">
      <div class="mm-tabs mm-tabs__top mm-tabs__align-left m-tab">
        <SetUpHeader @change-tab="updateTab"></SetUpHeader>
        <SetUpContent :selectedTab="currentTab"></SetUpContent>
      </div>
    </div>
  </div>
</template>

<script>
import SetUpHeader from './setup_header.vue';
import SetUpContent from './setup_content.vue';

export default {
  components: {
    SetUpHeader,
    SetUpContent
  },
  data() {
    return {
      currentTab: '常规'  // 你可以设置为第一个标签的名字，例如 "常规"
    };
  },
  methods: {
    updateTab(tabName) {
      this.currentTab = tabName;
    }
  }
};
</script>
