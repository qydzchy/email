<template>
  <div v-if="addQuickTextPage">
    <div class="mm-drawer--right mm-drawer mail-config-dialog create-text" id="report-stat-sub-routes-text-create-text"
      style="z-index: 2024;">
      <div class="mm-drawer-mask"></div>
      <div class="mm-drawer-main" style="width: 600px;">
        <div class="mm-drawer-header">
          <div class="title">{{ id ? '编辑' : '新建' }}文本</div>
          <svg @click="close" class="mm-icon mm-icon-close mm-drawer-close" viewBox="0 0 24 24" fill="currentColor"
            style="height: 12px; width: 12px;">
            <path
              d="M14.3 11.7l6-6c.3-.3.3-.7 0-1l-.9-1c-.3-.2-.7-.2-1 0l-6 6.1c-.2.2-.5.2-.7 0l-6-6.1c-.3-.3-.7-.3-1 0l-1 1c-.2.2-.2.7 0 .9l6.1 6.1c.2.2.2.4 0 .6l-6.1 6.1c-.3.3-.3.7 0 1l1 1c.2.2.7.2.9 0l6.1-6.1c.2-.2.4-.2.6 0l6.1 6.1c.2.2.7.2.9 0l1-1c.3-.3.3-.7 0-1l-6-6c-.2-.2-.2-.5 0-.7z">
            </path>
          </svg>
        </div>
        <div class="mm-drawer-body">
          <div class="text-field">
            <div class="item-name"> 标题：</div>
            <div style="flex: 1 1 0%;">
              <span class="mm-input">
                <!---->
                <span class="mm-input-affix-wrapper">
                  <!---->
                  <input v-model="title" type="text" class="mm-input-inner">
                  <!---->
                </span>
                <!---->
              </span>
            </div>
          </div>
          <!---->
          <Toolbar ref="editorInstance" style="border-bottom: 1px solid #ccc" :editor="editor"
            :defaultConfig="toolbarConfig" />
          <!-- 编辑器 -->
          <Editor ref="editorInstance" style="height: 100%; overflow-y: hidden" v-model="html" @onChange="onChange"
            @onCreated="onCreated" mode="default" />
          <div style="margin-top: 8px; text-align: center;">
            <p>写邮件时，输入 <span style="color: red;">&amp;</span>标题关键词，联想出快速文本，快捷插入</p>
          </div>
        </div>
        <div class="mm-drawer-footer">
          <div class="mm-space mm-space__horizontal">
            <div class="mm-space-item" style="margin-right: 16px;">
              <button type="button" class="mm-button ames-btn" @click="close">
                <!---->
                <!---->取消
                <!---->
              </button>
            </div>
            <div class="mm-space-item">
              <button type="button" class="mm-button mm-button__primary ames-btn" @click="save">
                <!---->
                <!---->保存
                <!---->
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style src="@wangeditor/editor/dist/css/style.css"></style>
<script>
import { Editor, Toolbar } from "@wangeditor/editor-for-vue";
import { addQuickText, editQuickText } from "@/api/email/quickText";
import { EventBus } from "@/api/email/event-bus";

export default {
  data() {
    return {
      id: null,
      title: null,
      content: null,
      html: null,
      htmlText: null,
      addQuickTextPage: false,
      editor: null,
      toolbarConfig: {
        excludeKeys: [
          'group-video',
          'group-image'
        ]
      },
    }
  },

  components: { Editor, Toolbar },

  methods: {
    open(quickText) {
      this.addQuickTextPage = true;
      if (quickText != null) {
        this.id = quickText.id;
        this.title = quickText.title;
        this.content = quickText.content;
        this.html = quickText.html;
      }
    },

    close() {
      this.addQuickTextPage = false;
      this.id = null;
      this.title = null;
      this.content = null;
      this.html = null;
    },

    onCreated(editor) {
      this.editor = Object.seal(editor);
      setTimeout(() => {
        this.editor.dangerouslyInsertHtml(this.htmlText);
      }, 500);
    },

    onChange(editor) {
    },

    // 保存
    save() {
      if (this.id == null) {
        this.add();
      } else {
        this.edit();
      }
    },

    // 新增
    async add() {
      if (this.title == null || this.title === "") {
        this.$message.error("标题不能为空");
        return;
      }
      if (this.html == null || this.html === "") {
        this.$message.error("内容不能为空");
        return;
      }

      const data = {
        title: this.title,
        content: this.editor.getText(),
        html: this.html
      };

      try {
        const response = await addQuickText(data);
        if (response.code === 200) {
          this.$message.success("保存成功");
          this.close();
          // 刷新列表
          EventBus.$emit('refresh-quick-text-list');
          this.$emit('refresh')
        } else {
          this.$message.error("保存失败");
        }
      } catch (error) {
        console.error('Error saving the quick text:', error);
      }
    },

    // 编辑
    async edit() {
      if (this.title == null || this.title === "") {
        this.$message.error("标题不能为空");
        return;
      }
      if (this.html == null || this.html === "") {
        this.$message.error("内容不能为空");
        return;
      }

      const data = {
        id: this.id,
        title: this.title,
        content: this.editor.getText(),
        html: this.html
      };

      try {
        const response = await editQuickText(data);
        if (response.code === 200) {
          this.$message.success("保存成功");
          this.close();
          // 刷新列表
          EventBus.$emit('refresh-quick-text-list');
          this.$emit('refresh')
        } else {
          this.$message.error("保存失败");
        }
      } catch (error) {
        console.error('Error saving the quick text:', error);
      }
    }
  },

  beforeDestroy() {
    const editor = this.editor;
    if (editor == null) return;
    editor.destroy();
  },
}

</script>
