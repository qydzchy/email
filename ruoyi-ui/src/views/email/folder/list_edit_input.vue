<template>
  <div class="folder-item" draggable="true">
    <div class="mm-collapse">
      <div class="mm-collapse-item">
        <div class="mm-collapse-item-header-wrap">
          <div class="mm-collapse-item-header--active mm-collapse-item-header">
            <span class="folder-icon">
              <!-- 新增的文本框 -->
              <span>
                <input
                  class="mm-input-inner"
                  :value="value"
                  @input="updateValue"
                  @blur="handleBlur"
                  ref="nameInput"
                />
              </span>
            </span>
            </div>
          </div>
        </div>
    </div>
  </div>
</template>

<script>
import {updateFolder} from "@/api/email/folder";

export default {
  data() {
    return {
      folderName: ''
    };
  },
  props: {
    id: {
      type: [String, Number],
      default: null
    },
    value: {}
  },
  methods: {
    updateValue(event) {
      this.folderName = event.target.value; // 更新本地状态
      this.$emit('input', event.target.value);
    },
    async handleBlur() {
      console.log("id = " + this.id);
      console.log("folderName = " + this.folderName);
      if (this.folderName.trim()) {
        const data = {
          "id": this.id,
          "name": this.folderName
        };

        try {
          const response = await updateFolder(data);
          if (response.code === 200) {
            this.$emit('input', this.folderName);
            this.$emit('edit-success');
            this.folderName = '';
          } else {
            console.error('Failed to add folder:', response.code);
          }
        } catch (error) {
          console.error('Error saving the folder:', error);
        }

      } else {
        this.$emit('cancel');
      }
    },
    focus() {  // 这个方法应该存在于FolderEditInput组件里
      this.$refs.nameInput.focus();
    }
  }
};
</script>
