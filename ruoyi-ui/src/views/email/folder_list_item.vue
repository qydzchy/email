<template>
  <ul role="menu" class="mm-menu mm-menu--inline" style="" data-old-padding-top="" data-old-padding-bottom="" data-old-overflow="">
    <li v-for="folder in folders" :key="folder.id" class="mm-submenu mm-submenu--opened mail-sidebar-submenu" role="menuitem" aria-haspopup="true" nativeondragover="function(e){return(0,te.CV)(e,t)}" nativeondragleave="function(e){return(0,te.aB)(e,t)}" nativeondrop="function(e){return(0,te.LQ)(e,t)}" aria-expanded="true">
      <!---->
      <!---->
      <div class="mm-submenu-title" :style="{ 'padding-left': paddingLeft, 'padding-right': '14px' }">
        <!---->
        <span class="mm-menu-title">
					<div class="right-click-menu-handler mail-menu-item-title ellipsis">
						<span class="flex items-center">{{folder.name}}</span>
					</div>
          <!---->
					<span>
						<div class="mm-dropdown mail-nav-item-dropdown">
							<div class="mm-dropdown-trigger">
								<span class="okki-icon-wrap menu-item-operation-icon">​<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
										<path d="M10 12a2 2 0 104 0 2 2 0 00-4 0zm0 7a2 2 0 104 0 2 2 0 00-4 0zm0-14a2 2 0 104 0 2 2 0 00-4 0z"></path>
									</svg>
								</span>
							</div>
              <!---->
						</div>
					</span>
				</span>
        <svg class="mm-icon mm-icon-chevrondown mm-submenu-icon-arrow" viewBox="0 0 24 24" name="chevrondown" fill="currentColor" style="height: 12px; width: 12px;">
          <path d="M22 8.2l-9.5 9.6c-.3.2-.7.2-1 0L2 8.2c-.2-.3-.2-.7 0-1l1-1c.3-.3.8-.3 1.1 0l7.4 7.5c.3.3.7.3 1 0l7.4-7.5c.3-.2.8-.2 1.1 0l1 1c.2.3.2.7 0 1z"></path>
        </svg>
      </div>

      <FolderTree v-if="folder.children && folder.children.length > 0" :folders="folder.children" :depth="depth+1"></FolderTree>

    </li>
  </ul>
</template>
<script>
import FolderTree from './folder_list_item.vue'

export default {
  props: {
    folders: {
      type: Array,
      required: true
    },
    depth: {
      type: Number,
      default: 1
    }
  },
  name: 'FolderTree',
  components: {
    FolderTree
  },
  computed: {
    paddingLeft() {
      return 35 + this.depth * 21 + 'px';
    }
  }
}
</script>
