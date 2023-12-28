<template>
	<div>
		<component :is="dynamicComponent" @backToHeader="backToHeader" @onChangeTab="onChangeTab"></component>
	</div>
</template>

<script>
import UsuallyComponent from './usually/index.vue';
import TemplateComponent from './template/list.vue';
import EmailManagementComponent from './email_management/list.vue';
import DispatchRulesComponent from './dispatcher_rules/index.vue'
import FolderComponent from './folder/list.vue';
import LabelComponent from './label/list.vue';
import QuickTextComponent from './quick_text/list.vue';
import BlacklistComponent from './blacklist/list.vue';
import ImportEmailComponent from './import_email/index.vue';
import OtherOptionComponent from './other_option/index.vue';

export default {
	data() {
		return {
			currentTab: '常规',
		};
	},
	props: ['selectedTab'],
	computed: {
		dynamicComponent() {
			switch (this.selectedTab) {
				case '常规':
					return UsuallyComponent;
				case '模板':
					return TemplateComponent;
				case '邮箱管理':
					return EmailManagementComponent;
				case '收发件规则':
					return DispatchRulesComponent;
				case '文件夹':
					return FolderComponent;
				case '标签':
					return LabelComponent;
				case '快速文本':
					return QuickTextComponent;
				case '黑名单':
					return BlacklistComponent;
				case '导入邮件':
					return ImportEmailComponent;
				case '其他配置':
					return OtherOptionComponent;
				default:
					return null;
			}
		},
	},
	methods: {
		backToHeader() {
			this.$emit('backToHeader')
		},
		onChangeTab(value) {
			this.$emit('changeTab', value);
		},
	},
};
</script>
