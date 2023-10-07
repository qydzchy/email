<template>
  <div class="mm-tabs-content">
    <div id="pane-0" aria-hidden="true" aria-labelledby="tab-0" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
    <div id="pane-1" aria-hidden="true" aria-labelledby="tab-1" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
    <div id="pane-2" aria-hidden="true" aria-labelledby="tab-2" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
    <div id="pane-3" aria-hidden="true" aria-labelledby="tab-3" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
    <div id="pane-4" aria-hidden="true" aria-labelledby="tab-4" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
    <div id="pane-5" aria-hidden="true" aria-labelledby="tab-5" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
    <div id="pane-6" aria-hidden="false" aria-labelledby="tab-6" class="mm-tab-pane" role="tabpanel" style=""></div>
    <div id="pane-7" aria-hidden="true" aria-labelledby="tab-7" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
    <div id="pane-8" aria-hidden="true" aria-labelledby="tab-8" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
    <div id="pane-9" aria-hidden="true" aria-labelledby="tab-9" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
    <div class="mail-plus-tag config-page-item">
      <div class="mail-plus-tag-conten">
        <div class="mail-config-header">
          <div>
            <h3 class="config-page-title">我的标签</h3>
            <span class="tips">管理您的所有个人标签</span>
          </div>
          <button type="button" class="mm-button mm-button__primary" @click="addLabelBtn">
            <!---->
            <!---->新建标签
            <!---->
          </button>
        </div>
        <ul class="mail-tag-list">
          <li class="mail-tag-list-item mail-tag-header">
            <!-- ... 其他头部内容 -->
          </li>
          <div class="mail-tag-list-body-wrapper">
            <li v-for="label in labels" :key="label.title" class="mail-tag-list-item" :class="{ 'system-tag-handle': label.type === 1 }">
              <div class="text-left-content">
                <!-- ...  其他图标内容 -->
                <span :title="label.title" class="tag-name ellipsis" :style="{background: `${label.color}20`, color: label.color}">{{ label.title }}</span>
              </div>
              <div v-if="label.type !== 1">
                <button type="button" class="mm-button mm-button__text btn-list-item">编辑</button>
                <button type="button" class="mm-button mm-button__text btn-list-item">删除</button>
              </div>
            </li>
          </div>
        </ul>
      </div>
    </div>

    <addLabelTemplate ref="addLabel"></addLabelTemplate>
  </div>
</template>
<style lang="scss">
@import '../../../static/scss/email/label/37013.cad33088.css';
@import '../../../static/scss/email/label/39201.59786a6e.css';
@import '../../../static/scss/email/label/43960.0bc86f30.css';
</style>
<script>
import addLabelTemplate from './add.vue';
import { listLabel } from "@/api/email/label";

export default {
  components: {addLabelTemplate},
  data() {
    return {
      labels: [], // 标签列表数据
      showAddPopup: false, //显示新增标签弹窗
    };
  },
  methods: {
    refreshLabelList() {
      listLabel().then((response) => {
        this.labels = response.data;
      });
    },

    addLabelBtn() {
      this.$refs.addLabel.open();
    },


  },
  created() {
    this.refreshLabelList();
  }
};
</script>
