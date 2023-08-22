<template>
  <div class="folder-item" draggable="true">
    <div class="mm-collapse">
      <div class="mm-collapse-item">
        <div class="mm-collapse-item-header-wrap">
          <div class="mm-collapse-item-header--active mm-collapse-item-header">
            <span class="folder-icon">
              <span class="okki-icon-wrap folder-drag-icon" color="#9EA1A8">​
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="#9EA1A8">
									<path fill-rule="evenodd" clip-rule="evenodd" d="M8 6a2 2 0 100-4 2 2 0 000 4zm8 0a2 2 0 100-4 2 2 0 000 4zm2 6a2 2 0 11-4 0 2 2 0 014 0zM8 14a2 2 0 100-4 2 2 0 000 4zm10 6a2 2 0 11-4 0 2 2 0 014 0zM8 22a2 2 0 100-4 2 2 0 000 4z"></path>
                </svg>
							</span>

              <!-- 新增的文本框 -->
              <span>
                <input type="text" v-model="newFolderName" @blur="checkAndSave" class="mm-input-inner" placeholder="输入文件夹名称" ref="folderInput">
              </span>
            </span>
            </div>
          </div>
        </div>
    </div>
  </div>
</template>

<script>
import { addFolder } from "@/api/email/folder";
export default {
  data() {
    return {
      newFolderName: ''
    };
  },
  methods: {

    focusInput() {
      this.$refs.folderInput.focus();
    },
    async checkAndSave() {

      if (this.newFolderName.trim()) {
        const data = {
          "parentFolderId": this.parentFolderId === null || this.parentFolderId === undefined ? -1 : this.parentFolderId,
          "name": this.newFolderName
        };

        try {
          const response = await addFolder(data);  // 使用await等待请求完成
          if (response.code === 200) {  // 检查响应状态
            this.$emit('save', { name: this.newFolderName, children: [] });
            this.newFolderName = '';
          } else {
            console.error('Failed to add folder:', response.code);
          }

        } catch (error) {
          console.error('Error saving the folder:', error);
        }

      } else {
        this.$emit('cancel');
      }
    }

  },

  props: {
    parentFolderId: {
      type: [String, Number],
      default: null
    }
  }
};
</script>
