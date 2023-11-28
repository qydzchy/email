<template>
	<div class="flex-middle flex-wrap gap-4">
		<el-tag
			class="customer-tag"
			v-for="(tag, index) in tagList"
			:class="{ 'ml-4': index }"
			:key="index"
			:style="{
				color: tag.color,
				backgroundColor: tag.color ? tag.color + '4d' : '',
			}"
			closable
			@close="removeTag(tag.id)">
			{{ tag.name }}
		</el-tag>
		<PopoverCustomerTag
			:visible.sync="tagVisible"
			:tagList="tagList"
			:tagOption="indexOpt.tagOption"
			showBtn
			@hide="onHide"
			@confirm="tagPopoverConfirm"
			@reloadTag="reloadTag">
			<template #contentTag>
				<el-button
					class="ml-10"
					size="mini"
					icon="el-icon-plus"
					@click="tagVisible = true"></el-button>
			</template>
		</PopoverCustomerTag>
	</div>
</template>

<script>
	import { editCustomer } from '@/api/customer/publicleads';
	import PopoverCustomerTag from './PopoverCustomerTag.vue';
	export default {
		props: {
			detailId: {
				type: String | Number,
				default: '',
				required: true,
			},
			tagList: {
				type: Array,
				default: () => [],
				required: false,
			},
			indexOpt: {
				type: Object,
				default: () => {
					return {
						tagOption: [],
					};
				},
				required: false,
			},
		},
		components: {
			PopoverCustomerTag,
		},
		watch: {
			detailId: {
				handler(newVal) {
					if (newVal) {
						this.tagVisible = false;
					}
				},
			},
		},
		data() {
			return {
				tagVisible: false,
			};
		},
		methods: {
			async editCustomer(data) {
				try {
					const res = await editCustomer({ ...data });
					if (res.code === 200) {
						this.reloadTag()
						this.$message.success('标签更新成功');
						this.tagVisible = false;
					}
				} catch {}
			},
			// 删除标签
			removeTag(id) {
				let tagIds = [];
				this.tagList.forEach((val) => {
					if (val.id !== id) {
						tagIds.push(val.id);
					}
				});
				const data = {
					id: this.detailId,
					tagIds: tagIds,
				};
				this.editCustomer(data);
			},
			onHide() {
				this.tagVisible = false;
			},
			tagPopoverConfirm(value) {
				let data = {
					id: this.detailId,
					tagIds: value.map((val) => val.id),
				};
				this.editCustomer(data);
			},
			reloadTag() {
				this.$emit('reloadTag');
			},
		},
	};
</script>

<style lang="scss" scoped></style>
