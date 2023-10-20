<template>
  <div v-if="templateTypePage" class="mm-modal--mask mm-modal template-group-dialog" style="z-index: 1000;">
    <div class="mm-modal-mask"></div>
    <div class="mm-modal-wrapper" style="padding-top: 15vh;">
      <div class="mm-modal-content" style="width: 520px; border-color: transparent;">
        <svg @click="close" class="mm-icon mm-icon-close mm-modal-close" viewBox="0 0 24 24" name="close" fill="currentColor" style="height: 14px; width: 14px;">
          <path d="M14.3 11.7l6-6c.3-.3.3-.7 0-1l-.9-1c-.3-.2-.7-.2-1 0l-6 6.1c-.2.2-.5.2-.7 0l-6-6.1c-.3-.3-.7-.3-1 0l-1 1c-.2.2-.2.7 0 .9l6.1 6.1c.2.2.2.4 0 .6l-6.1 6.1c-.3.3-.3.7 0 1l1 1c.2.2.7.2.9 0l6.1-6.1c.2-.2.4-.2.6 0l6.1 6.1c.2.2.7.2.9 0l1-1c.3-.3.3-.7 0-1l-6-6c-.2-.2-.2-.5 0-.7z"></path>
        </svg>
        <div class="mm-modal-header">
          <div class="mm-modal-title">
            <span>编辑模板类型</span>
          </div>
        </div>
        <div class="mm-modal-body">
          <div class="template-group-content">
            <div class="template-group-list">
              <li v-for="(templateType, index) in templateTypes" :key="templateType.id" class="drag-item" data-draggable="true">
								<span class="okki-icon-wrap move-btn" color="#D9D9D9">​<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="#D9D9D9">
										<path fill-rule="evenodd" clip-rule="evenodd" d="M8 6a2 2 0 100-4 2 2 0 000 4zm8 0a2 2 0 100-4 2 2 0 000 4zm2 6a2 2 0 11-4 0 2 2 0 014 0zM8 14a2 2 0 100-4 2 2 0 000 4zm10 6a2 2 0 11-4 0 2 2 0 014 0zM8 22a2 2 0 100-4 2 2 0 000 4z"></path>
									</svg>
								</span>
                <div class="item-content">
                  <div class="left-content">
                    <span class="num">{{ index + 1 }}</span>
                    <!-- 当不在编辑模式时，显示名称 -->
                    <span class="name ellipsis" v-if="editingTemplateTypeId !== templateType.id">{{templateType.name}}</span>

                    <!-- 当在编辑模式时，显示文本框 -->
                    <span class="mm-input name-input" v-if="editingTemplateTypeId === templateType.id">
                      <!---->
                      <span class="mm-input-affix-wrapper">
                        <!---->
                        <input  v-model="newTemplateTypeName" @blur="saveEditedTemplateType" maxlength="255" type="text" class="mm-input-inner"/>
                        <!---->
                      </span>
                                          <!---->
                    </span>
                  </div>
                  <div class="btn-list">
										<span @click="editTemplateType(templateType)" class="okki-icon-wrap" color="#686C73">​<svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="#686C73">
												<path fill-rule="evenodd" clip-rule="evenodd" d="M13.586 3a2 2 0 012.828 0l2.118 2.118a2 2 0 010 2.829l-9.03 9.03a1 1 0 01-.707.293H5.262a1 1 0 01-1-1v-3.532a1 1 0 01.293-.707L13.585 3zm3.532 3.533L15 4.414l-8.738 8.738v2.118H8.38l8.738-8.737z"></path>
												<path d="M2.5 20a1 1 0 011-1h17a1 1 0 110 2h-17a1 1 0 01-1-1z"></path>
											</svg>
										</span>
                    <span @click="deleteTemplateType(templateType.id)" class="okki-icon-wrap" color="#686C73">​<svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="#686C73">
												<path fill-rule="evenodd" clip-rule="evenodd" d="M10.2 2a2 2 0 00-1.765 1.059L7.4 5H3a1 1 0 000 2h1.5v12a3 3 0 003 3h9a3 3 0 003-3V7H21a1 1 0 100-2h-4.4l-1.035-1.941A2 2 0 0013.8 2h-3.6zm7.3 5h-11v12a1 1 0 001 1h9a1 1 0 001-1V7zm-3.167-2L13.8 4h-3.6l-.533 1h4.666zM10 9.5a1 1 0 011 1v6a1 1 0 11-2 0v-6a1 1 0 011-1zm5 1a1 1 0 10-2 0v6a1 1 0 102 0v-6z"></path>
											</svg>
										</span>
                  </div>
                </div>
              </li>

              <li v-if="showNewTemplateTypeInput" class="drag-item" data-draggable="true">
                <span class="okki-icon-wrap move-btn" color="#D9D9D9">​<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="#D9D9D9">
                    <path fill-rule="evenodd" clip-rule="evenodd" d="M8 6a2 2 0 100-4 2 2 0 000 4zm8 0a2 2 0 100-4 2 2 0 000 4zm2 6a2 2 0 11-4 0 2 2 0 014 0zM8 14a2 2 0 100-4 2 2 0 000 4zm10 6a2 2 0 11-4 0 2 2 0 014 0zM8 22a2 2 0 100-4 2 2 0 000 4z"></path>
                  </svg>
                </span>
                <div class="item-content">
                  <div class="left-content">
                    <span class="num">{{ templateTypes.length + 1 }}</span>
                    <span class="mm-input name-input">
                    <!---->
                    <span class="mm-input-affix-wrapper">
                      <!---->
                      <input v-model="newTemplateTypeName" @blur="saveNewTemplateType" maxlength="255" type="text" class="mm-input-inner">
                      <!---->
                    </span>
                      <!---->
			            </span>
                    <!---->
                  </div>
                  <div class="btn-list">
                    <!---->
                    <span @click="deleteNewTemplateTypeInput" class="okki-icon-wrap" color="#686C73">​<svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="#686C73">
                      <path fill-rule="evenodd" clip-rule="evenodd" d="M10.2 2a2 2 0 00-1.765 1.059L7.4 5H3a1 1 0 000 2h1.5v12a3 3 0 003 3h9a3 3 0 003-3V7H21a1 1 0 100-2h-4.4l-1.035-1.941A2 2 0 0013.8 2h-3.6zm7.3 5h-11v12a1 1 0 001 1h9a1 1 0 001-1V7zm-3.167-2L13.8 4h-3.6l-.533 1h4.666zM10 9.5a1 1 0 011 1v6a1 1 0 11-2 0v-6a1 1 0 011-1zm5 1a1 1 0 10-2 0v6a1 1 0 102 0v-6z"></path>
                    </svg>
                  </span>
                  </div>
                </div>
              </li>
            </div>
          </div>
        </div>
        <div class="mm-modal-footer">
          <div class="add-btn" @click="addTemplateType">添加类型</div>
          <button type="button" class="mm-button cancel" @click="close">
            <!---->
            <!---->取消
            <!---->
          </button>
<!--          <button type="button" class="mm-button mm-button__primary">
            &lt;!&ndash;&ndash;&gt;
            &lt;!&ndash;&ndash;&gt;确定
            &lt;!&ndash;&ndash;&gt;
          </button>-->
        </div>
      </div>
    </div>
  </div>
</template>
<style>
@import '../../../static/scss/email/label/39201.59786a6e.css';
</style>

<script>
import {listTemplateType, addTemplateType, editTemplateType, deleteTemplateType} from "@/api/email/templateType";
import {EventBus} from "@/api/email/event-bus";

export default {
  data() {
    return {
      templateTypePage: false,
      templateTypes: [],
      newTemplateTypeName: '',
      showNewTemplateTypeInput: false,
      editingTemplateTypeId: null,

    }
  },

  methods: {
    refreshTemplateTypeList() {
      listTemplateType().then(res => {
        this.templateTypes = res.data;
      });
    },
    open() {
      this.templateTypePage = true;
    },

    close() {
      this.templateTypePage = false;
      EventBus.$emit('refresh-template-type-list');
    },

    addTemplateType() {
      this.showNewTemplateTypeInput = true;
    },

    saveNewTemplateType() {
      if(this.newTemplateTypeName.trim()) {
        addTemplateType({ name: this.newTemplateTypeName }).then(() => {
          this.newTemplateTypeName = '';
          this.showNewTemplateTypeInput = false;
          this.refreshTemplateTypeList();
        });
      } else {
        this.showNewTemplateTypeInput = false;
      }
    },

    deleteNewTemplateTypeInput() {
      this.newTemplateTypeName = '';
      this.showNewTemplateTypeInput = false;
    },

    editTemplateType(templateType) {
      this.editingTemplateTypeId = templateType.id;
      this.newTemplateTypeName = templateType.name;  // 用于填充文本框
    },

    saveEditedTemplateType() {
      if (this.newTemplateTypeName.trim()) {
        editTemplateType({ id: this.editingTemplateTypeId, name: this.newTemplateTypeName }).then(() => {
          this.editingTemplateTypeId = null;
          this.newTemplateTypeName = '';
          this.refreshTemplateTypeList();
        });
      } else {
        this.editingTemplateTypeId = null;
      }
    },

    deleteTemplateType(templateId) {
      deleteTemplateType({ id: templateId }).then(() => {
        this.refreshTemplateTypeList();
      });
    }
  },

  created() {
    this.refreshTemplateTypeList();
  }
};
</script>
