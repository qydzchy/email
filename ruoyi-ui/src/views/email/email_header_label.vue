<template>
  <div class="mail-drop-menu" style="max-height: 460px;">
    <div>
      <h4 title="标记为" class="mail-drop-title">标记为</h4>
    </div>
    <div class="mm-tree" role="tree">
      <div class="is-expanded mm-tree-node" aria-expanded="true" aria-disabled="true" draggable="false" role="treeitem" tabindex="-1">
        <div class="mm-tree-node-content" style="padding-left: 0px;">
					<span class="mm-tree-node-expand-icon-wrapper">
						<span class="expanded mm-tree-node-caret-right"></span>
					</span>
          <!---->
          <!---->
          <span class="mm-tree-node-label-wrap">
						<span class="tree-menu-item">
							<!---->
							<span class="ellipsis">
								<div class="mail-drop-menu-tag-title">系统标签</div>
							</span>
						</span>
					</span>
        </div>
        <div aria-expanded="true" class="mm-tree-node-children" role="group">
          <div v-for="label in systemLabels" :key="label.id" class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="0">
            <div class="mm-tree-node-content" style="padding-left: 18px;">
							<span class="mm-tree-node-expand-icon-wrapper">
								<span class="is-leaf mm-tree-node-caret-right"></span>
							</span>
              <!---->
              <!---->
              <span class="mm-tree-node-label-wrap">
								<span class="tree-menu-item">
									<!---->
									<span class="ellipsis">{{ label.name }}</span>
								</span>
							</span>
            </div>
            <!---->
          </div>
        </div>
      </div>
      <div class="is-expanded mm-tree-node" aria-expanded="true" aria-disabled="true" draggable="false" role="treeitem" tabindex="-1">
        <div class="mm-tree-node-content" style="padding-left: 0px;">
					<span class="mm-tree-node-expand-icon-wrapper">
						<span class="expanded mm-tree-node-caret-right"></span>
					</span>
          <!---->
          <!---->
          <span class="mm-tree-node-label-wrap">
						<span class="tree-menu-item">
							<!---->
							<span class="ellipsis">
								<div class="mail-drop-menu-tag-title">自定义标签</div>
							</span>
						</span>
					</span>
        </div>
        <div aria-expanded="true" class="mm-tree-node-children" role="group">
          <div v-for="label in customLabels" :key="label.id" class="is-focusable mm-tree-node" aria-disabled="" draggable="false" role="treeitem" tabindex="-1">
            <div class="mm-tree-node-content" style="padding-left: 18px;">
							<span class="mm-tree-node-expand-icon-wrapper">
								<span class="is-leaf mm-tree-node-caret-right"></span>
							</span>
              <!---->
              <!---->
              <span class="mm-tree-node-label-wrap">
								<span class="tree-menu-item">
									<!---->
									<span class="ellipsis">
										<i class="mail-drop-menu-left-icon" :style="{ 'background-color': label.color }"></i>{{ label.name }}
									</span>
								</span>
							</span>
            </div>
            <!---->
          </div>
        </div>
      </div>
      <!---->
      <div class="mm-tree-drop-indicator" style="display: none;"></div>
    </div>
  </div>
</template>
<script>
import {listLabel} from "@/api/email/label";

export default {
  data() {
    return {
      labels: []
    }
  },
  methods: {
    refreshLabelList() {
      listLabel().then((response) => {
        this.labels = response.data;
      });
    },
  },
  computed: {
    systemLabels() {
      return this.labels.filter(label => label.type === 1);
    },
    customLabels() {
      return this.labels.filter(label => label.type === 2);
    }
  },

  created() {
    this.refreshLabelList();
  }
}
</script>
