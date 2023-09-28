<template>
  <div class="mail-drop-menu-wrapper">
  <ul class="mail-drop-menu" v-if="folders && folders.length">
    <li>
      <h4 title="移动至" class="mail-drop-title">移动至</h4>
    </li>
    <li v-for="folder in folders" :key="folder.name" class="mail-drop-menu-item">
      <!-- 有子文件夹则显示icon -->
      <span v-if="folder.children && folder.children.length" @click="toggleChildren(folder)">
        <i class="m-icon" :class="folder.showChildren ? 'icon-down-thin' : 'icon-right-thin'"></i>
      </span>
      <span v-html="folder.name" class="mail-drop-menu-text ellipsis"></span>
      <!-- 递归渲染子文件夹 -->
      <FolderComponent :folders="folder.children"/>
    </li>
  </ul>
  </div>
</template>
<script>
import FolderComponent from "@/views/email/email_content_folder_tree.vue";
import { listFolder } from "@/api/email/folder";
export default {
  data() {
    return {
      folders: [],
    };
  },

  components: {
    FolderComponent
  },

  methods: {
    refreshFolderList() {
      listFolder().then((response) => {
        this.folders = response.data;
      });
    },

    toggleChildren(folder) {
      folder.showChildren = !folder.showChildren;
    }
  },

  created() {
    this.refreshFolderList();
  }

}

</script>
