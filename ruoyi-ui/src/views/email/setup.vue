<template>
  <div class="mm-split-pane mm-split-pane__right" style="left: 12.2835%;">
    <div class="mail-plus-config layout-extraSidebar-content" mail-type="" folder-id="">
      <div class="mm-tabs mm-tabs__top mm-tabs__align-left m-tab">
        <SetUpHeader ref="headerTabRef" @change-tab="updateTab"></SetUpHeader>
        <SetUpContent :selectedTab="currentTab" @backToHeader="backToHeader" @changeTab="changeTab"></SetUpContent>
      </div>
    </div>
  </div>
</template>

<script>
import { EventBus } from "@/api/email/event-bus";
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
  watch: {
    "$route.query": {
      handler(newVal) {
        if (!newVal?.tab) {
          return
        }
        const mapTab = {
          'template': '模板',
          'usually': '常规',
        }
        const curTab = mapTab[newVal.tab]
        this.changeTab(curTab)
      },
      deep: true,
      immediate: true
    }
  },
  methods: {
    updateTab(tabName) {
      this.currentTab = tabName;
    },
    backToHeader() {
      EventBus.$emit('switch-email-header', 'ALL_RECEIVED');

    },
    changeTab(value) {
      this.currentTab = value
      this.$nextTick(() => {
        this.$refs.headerTabRef.emitChange(value)
      })

    }
  },
};
</script>
