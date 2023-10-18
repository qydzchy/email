<template>
  <div class="mm-tabs-content">
  <div id="pane-0" aria-hidden="true" aria-labelledby="tab-0" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
  <div id="pane-1" aria-hidden="true" aria-labelledby="tab-1" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
  <div id="pane-2" aria-hidden="true" aria-labelledby="tab-2" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
  <div id="pane-3" aria-hidden="true" aria-labelledby="tab-3" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
  <div id="pane-4" aria-hidden="false" aria-labelledby="tab-4" class="mm-tab-pane" role="tabpanel" style=""></div>
  <div id="pane-5" aria-hidden="true" aria-labelledby="tab-5" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
  <div id="pane-6" aria-hidden="true" aria-labelledby="tab-6" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
  <div id="pane-7" aria-hidden="true" aria-labelledby="tab-7" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
  <div id="pane-8" aria-hidden="true" aria-labelledby="tab-8" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
  <div id="pane-9" aria-hidden="true" aria-labelledby="tab-9" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
  <div id="pane-10" aria-hidden="true" aria-labelledby="tab-10" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
  <div class="mail-plus-quick-text config-page-item">
    <div class="quick-text-content">
      <div class="mail-config-header">
        <div>
          <h3 class="config-page-title">快速文本</h3>
          <span class="tips">作为模板使用，在写信时快速创建内容</span>
        </div>
        <button type="button" class="mm-button mm-button__primary" @click="addQuickTextBtn">
          <!---->
          <!---->新建文本
          <!---->
        </button>
      </div>
      <div class="quick-text-list">
        <div class="mm-table-wrapper">
          <!---->
          <div class="mm-table mm-table__medium mm-table__horizontal-bordered">
            <div class="mm-thead" style="margin-right: -17px; margin-bottom: -6px;">
              <table cellpadding="0" cellspacing="0" border="0" style="width: 100%;">
                <colgroup>
                  <!---->
                  <col width="5%">
                  <col width="15%">
                  <col width="60%">
                  <col width="20%">
                </colgroup>
                <thead>
                <tr class="">
                  <!---->
                  <th class="">
                    <div class="mm-table-cell" title="" style="text-align: left;">
                      <!---->
                      <!---->
                    </div>
                    <div class="mm-thead-dragbar__next mm-thead-dragbar"></div>
                  </th>
                  <th class="">
                    <div class="mm-table-cell" title="" style="text-align: left;">标题
                      <!---->
                      <!---->
                    </div>
                    <div class="mm-thead-dragbar__next mm-thead-dragbar"></div>
                  </th>
                  <th class="">
                    <div class="mm-table-cell" title="" style="text-align: left;">内容
                      <!---->
                      <!---->
                    </div>
                    <div class="mm-thead-dragbar__next mm-thead-dragbar"></div>
                  </th>
                  <th class="">
                    <div class="mm-table-cell" title="" style="text-align: right;">操作
                      <!---->
                      <!---->
                    </div>
                    <div class="mm-thead-dragbar__next mm-thead-dragbar"></div>
                  </th>
                </tr>
                </thead>
              </table>
            </div>
            <div class="mm-tbody" style="margin-bottom: -6px; margin-right: -17px; max-height: 722px;">
              <table cellpadding="0" cellspacing="0" border="0" style="width: 100%">
                <colgroup>
                  <!---->
                  <col width="5%">
                  <col width="15%">
                  <col width="60%">
                  <col width="20%">
                </colgroup>
                <!---->
                <tbody>
                <tr v-for="quickText in quickTexts" :key="quickText.id" class="mm-tbody-tr" draggable="true">
                  <!---->
                  <td>
                    <div class="mm-table-cell" title="" style="text-align: left;">
                      <a class="e-move">
                        <span class="m-icon icon-move-btn"></span>
                      </a>
                    </div>
                  </td>
                  <td>
                    <div class="mm-table-cell" title="" style="text-align: left;">{{quickText.title}}</div>
                  </td>
                  <td>
                    <div class="mm-table-cell" title="" style="text-align: left;">
                      <p class="text-content ellipsis">{{quickText.content}}</p>
                    </div>
                  </td>
                  <td>
                    <div class="mm-table-cell" title="" style="text-align: right;">
                      <div>
                        <button type="button" class="mm-button mm-button__text btn-list-item" @click="editQuickTextBtn(quickText)">
                          <!---->
                          <!---->编辑
                          <!---->
                        </button>
                        <button type="button" class="mm-button mm-button__text btn-list-item" style="padding-right: 0px;" @click="deleteQuickText(quickText.id)">
                          <!---->
                          <!---->删除
                          <!---->
                        </button>
                      </div>
                    </div>
                  </td>
                </tr>
                </tbody>
              </table>
              <!---->
            </div>
            <!---->
            <!---->
            <!---->
          </div>
          <!---->
          <!---->
          <div class="mm-spinner" style="display: none;">
            <div class="mm-spinner-mask"></div>
            <div class="mm-spinner-wrapper">
              <div class="mm-loading" style="height: 40px; width: 40px; color: rgb(38, 132, 255);">
                <svg class="circular" viewBox="25 25 50 50">
                  <circle stroke-width="2" class="path" cx="50" cy="50" r="20" fill="none" stroke-miterlimit="10"></circle>
                </svg>
              </div>
              <!---->
            </div>
          </div>
        </div>
      </div>
      <div class="pagination-container">
        <!---->
      </div>
    </div>
  </div>
    <saveQuickTextTemplate ref="addQuickText"></saveQuickTextTemplate>
  </div>

</template>
<style lang="scss">
@import '../../../static/scss/email/quick_text/90116.e03c2208.css';
</style>

<script>
import saveQuickTextTemplate from './save.vue';
import {addQuickText, deleteQuickText, listQuickText} from "@/api/email/quickText";
import {EventBus} from "@/api/email/event-bus";

export default {
  data() {
    return {
      quickTexts: [], // 快速文本列表数据
    };
  },
  components: {saveQuickTextTemplate},
  methods: {
    refreshQuickTextList() {
      listQuickText().then((response) => {
        this.quickTexts = response.data;
      });
    },

    addQuickTextBtn() {
      this.$refs.addQuickText.open(null);
    },

    editQuickTextBtn(quickText) {
      this.$refs.addQuickText.open(quickText);
    },

    async deleteQuickText(id) {
      if (id == null || id === "") {
        this.$message.error("id不能为空");
        return;
      }

      const data = {
        id: id
      };

      try {
        const response = await deleteQuickText(data);
        if (response.code === 200) {
          this.$message.success("删除成功");
          // 刷新列表
          this.refreshQuickTextList();
        } else {
          this.$message.error("删除失败");
        }
      } catch (error) {
        console.error('Error delete the quick text:', error);
      }
    }
  },

  created() {
    this.refreshQuickTextList();
  },

  mounted() {
    EventBus.$on('refresh-quick-text-list', this.refreshQuickTextList);
  },

  beforeDestroy() {
    EventBus.$off('refresh-quick-text-list', this.refreshQuickTextList);
  },
};
</script>
