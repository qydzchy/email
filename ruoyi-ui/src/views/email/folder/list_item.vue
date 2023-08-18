<template>
  <div>
  <div v-for="folder in folders" :key="folder.id" class="folder-item" draggable="true">
    <div class="mm-collapse">
      <div class="mm-collapse-item">
        <div class="mm-collapse-item-header-wrap">
          <div class="mm-collapse-item-header--active mm-collapse-item-header" @click="toggleFolder(folder.name)">
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
                <div class="folder-name ellipsis">{{ folder.name }}</div>
              </div>
              <div class="folder-columns-item" style="width: 120px; text-align: right; flex: 0 0 auto;">
                <div class="btn-list">
                  <button type="button" class="mm-button mm-button__text btn-list-item" @click="startAddSubfolder(folder.id)">
                    <!---->
                    <!---->添加子文件夹
                    <!---->
                  </button>
                  <button type="button" class="mm-button mm-button__text btn-list-item">
                    <!---->
                    <!---->编辑
                    <!---->
                  </button>
                  <button type="button" class="mm-button mm-button__text btn-list-item">
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

export default {
  data() {
    return {
      openFolders: [], // 用于跟踪哪些文件夹是打开的
      folderAddingSubfolder: null
    };
  },
  name: 'FolderItem',
  components: {
    FolderItem,
    FolderInput
  },
  props: ['folders'],
  methods: {
    toggleFolder(folderName) {
      // 当文件夹被点击时，更改其打开状态
      this.$set(this.openFolders, folderName, !this.openFolders[folderName]);
    },
    isFolderOpen(folderName) {
      return !!this.openFolders[folderName];
    },
    startAddSubfolder(folderId) {
      this.folderAddingSubfolder = folderId;
    },

/*    async checkAndSave() {
      if (this.newFolderName.trim()) {
        const data = {
          "parentFolderId": this.parentFolderId,  // 使用从父组件传递的属性
          "name": this.newFolderName
        };

        try {
          const response = await this.addFolder(data);
          if (response.code === 200) {
            this.$emit('save', { name: this.newFolderName, children: [] });
            this.newFolderName = '';
          } else {
            console.error('Failed to add folder:', response.message);
          }
        } catch (error) {
          console.error('Error saving the folder:', error);
        }

      } else {
        this.$emit('cancel');
      }
    },*/

    cancelAddSubfolder() {
      this.folderAddingSubfolder = null;
    },

    created() {
      console.log("FolderInput created with parentFolderId:", this.parentFolderId);  // 添加调试信息
    }

  }
};
</script>
