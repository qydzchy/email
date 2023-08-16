<template>
  <div class="mm-tabs-header mm-tabs-header__top">
    <div class="mm-tabs-nav-wrap mm-tabs-nav-wrap__top">
      <div class="mm-tabs-nav-scroll">
        <div class="mm-tabs-nav" role="tablist">
          <div
            class="mm-tabs-item mm-tabs-item__top"
            :class="{ 'mm-tabs-item--active': tab === activeTab }"
            v-for="(tab, index) in tabs"
            :key="tab"
            :ref="`tab-${index}`"
            @click="emitChange(tab)"
          >
            <div>{{ tab }}</div>
          </div>

          <div
            class="mm-tabs-active-bar__top mm-tabs-active-bar"
            :style="computeActiveBarStyle()"
          ></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {  // 注意，这里是一个函数，不是一个对象
    return {
      tabs: ['常规', '模板', '邮箱管理', '收发件规则', '快速文本', '文件夹', '标签', '黑名单', '导入邮件', '其他配置'],
      activeTab: '常规',
      defaultWidth: '68px',
      defaultDistance: 0
    };
  },
  methods: {
    emitChange(tabName) {
      this.activeTab = tabName;  // 更新当前激活的标签
      this.$emit('change-tab', tabName);
    },

    computeActiveBarStyle() {
      let index = this.tabs.indexOf(this.activeTab);
      if (index === 0) {
        // 如果没有匹配的标签，则返回默认值
        return {
          width: this.defaultWidth,
          transform: `translateX(${this.defaultDistance}px)`
        };
      }

      // 计算选项前面所有选项的宽度之和
      let accumulatedWidth = this.tabs.slice(0, index).reduce((acc, tab) => {
        return acc + this.getTextWidth(tab);
      }, 0);

      // 加上每个选项的固定间距
      let distance = accumulatedWidth; // 86px是间距

      let width = this.getTextWidth(this.activeTab);
      return {
        width: `${width}px`,
        transform: `translateX(${distance}px)`
      };
    },

    getTextWidth(tabName) {
      let index = this.tabs.indexOf(tabName);
      let element = this.$refs[`tab-${index}`] ? this.$refs[`tab-${index}`][0] : null;

      if (!element) return 0;

      return element.getBoundingClientRect().width;
    }
  }
};
</script>

