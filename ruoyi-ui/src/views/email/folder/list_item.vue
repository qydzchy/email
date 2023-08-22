<template>
  <div>
  <div v-for="folder in folders" :key="folder.id" class="folder-item" draggable="true">
    <div class="mm-collapse">
      <div class="mm-collapse-item">
        <div class="mm-collapse-item-header-wrap">
          <div class="mm-collapse-item-header--active mm-collapse-item-header" @click="toggleFolder(folder, $event)">
            <div class="folder-icon">
              <span class="okki-icon-wrap folder-drag-icon" color="#9EA1A8">​
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="#9EA1A8">
									<path fill-rule="evenodd" clip-rule="evenodd" d="M8 6a2 2 0 100-4 2 2 0 000 4zm8 0a2 2 0 100-4 2 2 0 000 4zm2 6a2 2 0 11-4 0 2 2 0 014 0zM8 14a2 2 0 100-4 2 2 0 000 4zm10 6a2 2 0 11-4 0 2 2 0 014 0zM8 22a2 2 0 100-4 2 2 0 000 4z"></path>
                </svg>
							</span>

              <span
                v-if="folder.children && folder.children.length > 0"
                @click.stop="toggleFolder(folder.name)"
                class="okki-icon-wrap"
                :class="{'expand': isFolderOpen(folder.name)}"
                color="#9EA1A8">

                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="#9EA1A8">
                  <path fill-rule="evenodd" clip-rule="evenodd" d="M8.293 5.293a1 1 0 011.414 0l6 6a1 1 0 010 1.414l-6 6a1 1 0 01-1.414-1.414L13.586 12 8.293 6.707a1 1 0 010-1.414z"></path>
                </svg>

              </span>
            </div>
            <div class="folder-columns">
              <div class="folder-columns-item" style="width: 0px; flex: 1 1 0%;">
                <span v-if="editingFolder !== folder.id">{{ folder.name }}</span>

                <FolderEditInput
                  :id="folder.id"
                  v-if="editingFolder === folder.id"
                  @edit-success="editingFolder = null"
                  :value="folder.name"
                  @input="updateFolderName(folder, $event)"
                />

              </div>
              <div class="folder-columns-item" style="width: 120px; text-align: right; flex: 0 0 auto;">
                <div class="btn-list">
                  <button type="button" class="mm-button mm-button__text btn-list-item" @click.stop="startAddSubfolder(folder)">
                    <!---->
                    <!---->添加子文件夹
                    <!---->
                  </button>
                  <button type="button" class="mm-button mm-button__text btn-list-item" @click.stop="startEditing(folder)">
                    <!---->
                    <!---->编辑
                    <!---->
                  </button>
                  <button type="button" class="mm-button mm-button__text btn-list-item" @click.stop="handleDeleteFolder(folder.id)">
                    <!---->
                    <!---->删除
                    <!---->
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="mm-collapse-item-wrap" v-if="openFolders[folder.name]" style="" data-old-padding-top="" data-old-padding-bottom="" data-old-overflow="">
          <div class="mm-collapse-item-content">
            <FolderInput
              v-if="folderAddingSubfolder === folder.id"
              :parentFolderId="folder.id"
              @save="handleSaveSubfolder"
              @cancel="cancelAddSubfolder"
              ref="inputSubComponent"
            ></FolderInput>

            <FolderItem v-if="folder.children && folder.children.length && openFolders[folder.name]" :folders="folder.children" />
          </div>
        </div>

      </div>
    </div>
  </div>
  </div>
</template>

<script>
import FolderItem from './list_item.vue';
import FolderInput from './list_input.vue';
import FolderEditInput from './list_edit_input.vue';

import { deleteFolder } from "@/api/email/folder";

export default {
  data() {
    return {
      openFolders: [], // 用于跟踪哪些文件夹是打开的
      folderAddingSubfolder: null,
      editingFolder: null,     // 正在编辑的文件夹的ID
      editingName: '',         // 编辑中的文件夹名称
      editingFolderId: null,   // 编辑中的文件夹ID
    };
  },
  name: 'FolderItem',
  components: {
    FolderItem,
    FolderInput,
    FolderEditInput
  },
  props: ['folders'],
  methods: {
    toggleFolder(folder) {
      // 如果当前文件夹正在添加子文件夹，则不进行展开/收起操作
      if (this.folderAddingSubfolder === folder.id) return;
      this.$set(this.openFolders, folder.name, !this.openFolders[folder.name]);
    },


    isFolderOpen(folderName) {
      return !!this.openFolders[folderName];
    },

    startAddSubfolder(folder) {
      // 强制展开文件夹
      this.$set(this.openFolders, folder.name, true);
      this.folderAddingSubfolder = folder.id;
      this.$nextTick(() => {
        this.$refs.inputSubComponent.focusInput();
      });
    },

    handleSaveSubfolder(newSubfolder) {
      const parentFolder = this.folders.find(f => f.id === this.folderAddingSubfolder);
      if (parentFolder) {
        if (!parentFolder.children) {
          this.$set(parentFolder, 'children', []);
        }
        // 使用 unshift 插入新文件夹到 children 数组的开头
        parentFolder.children.unshift(newSubfolder);
      }
      this.folderAddingSubfolder = null;
    },

    cancelAddSubfolder() {
      this.folderAddingSubfolder = null;
    },

    startEditing(folder) {
      this.editingFolder = folder.id;
      this.editingName = folder.name;

      this.$nextTick(() => {
        if (this.$refs.editingInput && typeof this.$refs.editingInput.focus === 'function') {
          this.$refs.editingInput.focus();
        }
      });
    },

    finishEditing(folder) {
      if (this.editingName.trim()) {
        this.$set(folder, 'name', this.editingName.trim());
      }
      this.editingFolder = null;
    },

    updateFolderName(folder, newName) {
      const data = this.folders.find(f => f.id === folder.id);
      console.log("data = " + data);
      if (data) {
        data.name = newName;
      }
    },

    removeFolderFromList(folderId, list) {
      for (let i = 0; i < list.length; i++) {
        if (list[i].id === folderId) {
          list.splice(i, 1);  // 如果找到了匹配的文件夹ID，从数组中移除它
          return true;
        } else if (list[i].children && list[i].children.length > 0) {
          // 递归地查找子文件夹
          if (this.removeFolderFromList(folderId, list[i].children)) {
            return true;  // 如果子文件夹中找到并删除了匹配的文件夹，停止查找
          }
        }
      }
      return false;
    },

    handleDeleteFolder(folderId) {
      this.$confirm('此操作将永久删除该文件夹, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        try {
          const data = {
            "id": folderId
          };

          deleteFolder(data);
          this.removeFolderFromList(folderId, this.folders);
        } catch (error) {
          console.error("Failed to delete folder:", error);
          // 或者向用户显示错误消息
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    }
  }
};
</script>
