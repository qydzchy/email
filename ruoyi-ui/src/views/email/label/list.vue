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
            <div class="text-left-content">
              <p class="text-name">标签</p>
            </div>
            <div class="btn-list">
              <span>操作</span>
            </div>
          </li>
          <div class="mail-tag-list-body-wrapper">
            <li v-for="(label, index) in labels" :key="label.id" class="mail-tag-list-item" :class="{ 'system-tag-handle': label.type === 1 }" data-draggable="true">
              <div class="text-left-content">
                <a class="e-move">
                  <span class="m-icon icon-move-btn"></span>
                </a>
                <div class="mm-dropdown color-picker-dropdown-wrap" @click="labelColorBtn(index, label.color)">
                  <div class="mm-dropdown-trigger">
                    <div class="color-picker-display">
                      <i class="color-block" :style="{background: `rgb(${label.color})`}"></i>
                      <svg class="mm-icon mm-icon-switch" viewBox="0 0 24 24" name="switch" fill="currentColor" style="height: 12px; width: 12px;">
                        <path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.3.8-.3 1.1 0l1 1c.2.3.2.7 0 1z"></path>
                      </svg>
                    </div>
                  </div>
                  <!---->
                </div>
                <span v-if="editingLabelId !== label.id" title="label.name" class="tag-name ellipsis" :style="{background: `rgba(${label.color},0.2)`, color: `rgb(${label.color})`}">
                  {{ label.name }}
                </span>

                <input
                  v-else
                  :ref="'inputFocus' + label.id"
                  v-model="name"
                  @blur="stopEditing(label)"
                  class="tag-name-input mm-input-inner"
                  type="text"
                >

              </div>
              <div v-if="label.type !== 1">
                <button type="button" class="mm-button mm-button__text btn-list-item" @click="startEditing(label)">
                  <!---->
                  <!---->编辑
                  <!---->
                </button>
                <button type="button" class="mm-button mm-button__text btn-list-item" @click="deleteLabel(label)">
                  <!---->
                  <!---->删除
                  <!---->
                </button>
              </div>
            </li>
          </div>
        </ul>
      </div>
    </div>

    <addLabelTemplate ref="addLabel"></addLabelTemplate>

    <div
      v-if="labelColorPage"
      class="mm-outside mm-dropdown-popper"
      x-placement="bottom-start"
      :style="computeStyle(selectedLabelIndex)"
    >
      <labelColorTemplate :initialColor="initialColor" @color-selected="updateColor"></labelColorTemplate>
    </div>
  </div>
</template>
<style lang="scss">
@import '../../../static/scss/email/label/37013.cad33088.css';
@import '../../../static/scss/email/label/39201.59786a6e.css';
@import '../../../static/scss/email/label/43960.0bc86f30.css';
</style>
<script>
import addLabelTemplate from './add.vue';
import {listLabel, editLabelName, editLabelColor, deleteLabel} from "@/api/email/label";
import {EventBus} from "@/api/email/event-bus";
import labelColorTemplate from "@/views/email/label/color.vue";

export default {
  components: {labelColorTemplate, addLabelTemplate},
  data() {
    return {
      labels: [], // 标签列表数据
      showAddPopup: false, //显示新增标签弹窗
      editingLabelId: null,
      name: '',
      labelColorPage: false,
      selectedLabelIndex: 0,
      initialColor: '97, 188, 129',
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

    startEditing(label) {
      this.editingLabelId = label.id;
      this.name = label.name;
      this.$nextTick(() => {
        this.$refs["inputFocus" + label.id][0].focus();
      });
    },

    // 编辑标签名称
    async stopEditing(label) {
      if (this.name === undefined || this.name.trim() === '') {
        this.$message.error("标签名称不能为空");
        return;
      }

      if (this.name === label.name) {
        this.editingLabelId = null;
        return;
      }

      const data = {
        "id": label.id,
        "name": this.name
      };
      try {
        const response = await editLabelName(data);
        if (response.code === 200) {
          this.$message.success("编辑成功");
          label.name = this.name;
          this.editingLabelId = null;
          EventBus.$emit('refresh-index-label-list');
        } else {
          this.$message.error("编辑失败");
        }
      } catch (error) {
        console.error('Error edit the label:', error);
      }

      this.editingLabelId = null;
    },

    // 删除标签
    async deleteLabel(label) {
      const data = {
        "id": label.id
      };

      try {
        const response = await deleteLabel(data);
        if (response.code === 200) {
          this.$message.success("删除成功");
          this.refreshLabelList();
          EventBus.$emit('refresh-index-label-list');
        } else {
          this.$message.error("删除失败");
        }
      } catch (error) {
        console.error('Error delete the label:', error);
      }
    },

    computeStyle(index) {
      return {
        position: 'absolute',
        top: `${index * 44}px`,
        left: '45px'
      };
    },

    // 修改颜色
    async updateColor(color) {
      const label = this.labels[this.selectedLabelIndex];
      if (!label) {
        console.error('No label found for selected index:', this.selectedLabelIndex);
        return;
      }

      if (label.color === color) {
        this.labelColorPage = false;
        return;
      }

      const data = {
        'id': label.id,
        "color": color
      };

      try {
        const response = await editLabelColor(data);
        if (response.code === 200) {
          this.$message.success("编辑成功");cd
          this.refreshLabelList();
          EventBus.$emit('refresh-index-label-list');
        } else {
          this.$message.error("编辑失败");
        }
      } catch (error) {
        console.error('Error edit the label color:', error);
      }
    },

    labelColorBtn(index, labelColor) {
      this.initialColor = labelColor;
      this.selectedLabelIndex = index;
      this.labelColorPage = !this.labelColorPage;
    },
  },

  mounted() {
    EventBus.$on('refresh-label-list', this.refreshLabelList);
  },

  beforeDestroy() {
    EventBus.$off('refresh-label-list', this.refreshLabelList);
  },

  created() {
    this.refreshLabelList();
  }
};
</script>
