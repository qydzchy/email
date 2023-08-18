<template>
  <div class="mm-tabs-content">
    <div class="mail-plus-folder config-page-item">
      <div class="mail-config-header">
        <div>
          <h3 class="config-page-title">文件夹</h3>
          <span class="tips">管理您的个人文件夹</span>
        </div>
        <button type="button" class="mm-button mm-button__primary" @click="startAddFolder">
          <!---->
          <!---->新建文件夹
          <!---->
        </button>
      </div>
      <div class="mail-folder-list">
        <div class="tree-wrap">
          <div class="list-header">
            <div style="width: 0px; flex: 1 1 0%;">文件夹名称</div>
            <div style="width: 120px; text-align: right; flex: 0 0 auto;">操作</div>
          </div>
          <div class="select-wrapper"></div>

          <FolderInput v-if="addingFolder" @save="handleSave" @cancel="cancelAdd" ref="inputComponent"></FolderInput>
          <FolderItem :folders="folders"></FolderItem>

          <span id="dragImage" class="dragging-tag"></span>
        </div>
      </div>
      <span id="dragImage" class="dragging-tag"></span>
    </div>
  </div>
</template>

<style lang="scss">
.folder-input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
}

@import '../../../static/scss/email/folder/1886.aec5c8e5.css';
@import '../../../static/scss/email/folder/8983.72cc3cdf.css';
@import '../../../static/scss/email/folder/amumu-ui.25304f98.css';
</style>

<script>
import FolderItem from './list_item.vue';
import FolderInput from './list_input.vue';
import { listTask } from "@/api/email/folder";

export default {
  components: {
    FolderItem,
    FolderInput
  },
  data() {
    return {
      folders: [],
      openFolders: {},
      addingFolder: false,
      newFolderName: ''
    };
  },
  methods: {
    refreshFolderList() {
      listTask().then((response) => {
        this.folders = response.data;
      });
    },
    startAddFolder() {
      this.addingFolder = true; // 修改这里
      this.$nextTick(() => {
        this.$refs.inputComponent.focusInput();
      });
    },
    handleSave(newFolder) {
      this.folders.unshift(newFolder);
      this.addingFolder = false;
    },
    cancelAdd() {
      this.addingFolder = false;
    }
  },
  created() {
    this.refreshFolderList();
  }
};
</script>
