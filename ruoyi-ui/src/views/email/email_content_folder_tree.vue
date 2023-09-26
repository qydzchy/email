<template>
  <div class="mail-drop-menu-wrapper">
    <ul class="mail-drop-menu">
      <li>
        <h4 title="移动至" class="mail-drop-title">移动至</h4>
      </li>
      <li v-for="folder in folders" :key="folder.id" class="mail-drop-menu-item">
        <span class="mail-drop-menu-text ellipsis">
          <span>{{ folder.name }}</span>
        </span>
        <div v-if="folder.children && folder.children.length" class="mail-drop-menu-wrapper children-menu">
          <FolderComponent :folders="folder.children"/>
        </div>
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
    }
  },

  created() {
    this.refreshFolderList();
  }

}

</script>
