<template>
	<el-popover
		:value.sync="visible"
		trigger="manual"
		:append-to-body="false">
		<template slot="default">
			<div
				class="tag-popover"
				v-clickOutside="onHidePopover">
				<div>
					<div class="echo-tag-list mb-4">
						<el-tag
							class="mt-4"
							:class="{ 'ml-4': index }"
							v-for="(tag, index) in innerTagList"
							:key="index"
							:style="{
								color: tag.color,
								backgroundColor: tag.color
									? tag.color + '4d'
									: '',
							}"
							size="small"
							closable
							@close="removeTag(tag.id)">
							{{ tag.name }}
						</el-tag>
					</div>
					<div class="flex-middle input-area mb-4">
						<el-input
							v-model="searchTag"
							size="small"
							placeholder="搜索或直接输入新建标签"
							@keydown.enter.native="
								addPersonalTagReq
							"></el-input>
						<el-tooltip
							placement="top"
							content="标签管理">
							<i
								class="el-icon-setting pointer ml-6"
								@click="
									targetBlank('/customer/config?tab=tags')
								"></i>
						</el-tooltip>
					</div>
				</div>
				<el-divider class="my-4"></el-divider>
				<div class="tag-list my-5">
					<template v-if="innerTagOption.length">
						<div
							class="tag-li py-5 flex-middle space-between"
							:class="{
								'tag-active': validTagIncludes(item.id),
							}"
							v-for="item in innerTagOption"
							:key="item.id"
							@click="selectTagItem(item)">
							<div
								:title="item.name"
								class="px-6 py-6 radius-4"
								:style="{
									width: 'max-content',
									color: item.color,
									backgroundColor: item.color
										? item.color + '4d'
										: '',
								}">
								{{ item.name }}
							</div>
							<i
								v-show="validTagIncludes(item.id)"
								class="el-icon-check mr-10"
								style="color: #5cb6ff"></i>
						</div>
					</template>
					<template v-else>
						<div v-show="searchTag" class="tag-li my-5 flex-middle space-between">
							<div
								class="px-6 py-6 radius-4"
								style="
									width: max-content;
									color: #209890;
									background-color: #2098904d;
								">
								{{ searchTag }}
							</div>
							<el-button
								class="mr-10"
								size="small"
								type="text"
								@click="addPersonalTagReq"
								>新建标签</el-button
							>
						</div>
					</template>
				</div>
				<div
					v-if="showBtn"
					class="flex-end">
					<el-button
						size="small"
						round
						@click="onCancelPopover"
						>取消</el-button
					>
					<el-button
						size="small"
						round
						type="primary"
						@click="onConfirmPopover">
						确认
					</el-button>
				</div>
			</div>
		</template>
		<div
			slot="reference"
			class="line-clamp1">
			<slot name="contentTag"></slot>
		</div>
	</el-popover>
</template>

<script>
	import { targetBlank } from '@/utils/tools';
	import { deepClone, debounce } from '@/utils';
	import { addPersonalTag } from '@/api/customer/config';

	export default {
		props: {
			visible: {
				type: Boolean,
				default: false,
				required: true,
			},
			tagList: {
				type: Array,
				default: () => [],
				required: false,
			},
			tagOption: {
				type: Array,
				default: () => [],
				required: true,
			},
			showBtn: {
				type: Boolean,
				default: false,
				required: false,
			},
		},
		data() {
			return {
				popoverVisible: false,
				innerTagList: [],
				innerTagOption: [],
				tempOption: [],
				searchTag: '',
			};
		},
		watch: {
			tagList: {
				handler(newVal) {
					this.innerTagList = JSON.parse(JSON.stringify(newVal));
				},
				deep: true,
				immediate: true,
			},
			tagOption: {
				handler(newVal) {
					this.innerTagOption = deepClone(newVal);
					this.tempOption = this.innerTagOption;
					this.innerTagOption.forEach((val) => {
						if (val.name === this.searchTag) {
							this.innerTagList.push(val);
							this.searchTag = '';
						}
					});
				},
				deep: true,
				immediate: true,
			},
			searchTag: {
				handler(newVal) {
					const tempOption = deepClone(this.tempOption);
					this.innerTagOption = !newVal
						? tempOption
						: tempOption.filter((val) => {
								if (val.name.includes(newVal)) {
									return true;
								} else {
									return false;
								}
						  });
				},
			},
		},
		methods: {
			async addPersonalTagReq() {
				if(!this.searchTag){
					this.$message.warning('标签内容不能为空')
					return
				}
				try {
					this.btnLoading = true;
					const res = await addPersonalTag({
						name: this.searchTag,
						color: '#209890',
					});
					if (res.code === 200) {
						this.$emit('reloadTag');
					}
				} catch {}
			},
			removeTag(id) {
				this.innerTagList = this.innerTagList.filter(
					(val) => val.id !== id,
				);
			},
			selectTagItem(item) {
				let innerTagList = this.innerTagList;
				innerTagList = innerTagList.map((val) => val.id);
				if (!innerTagList.includes(item.id)) {
					this.innerTagList.push(item);
				}else{
					this.innerTagList = this.innerTagList.filter(val=>val.id!==item.id)
				}
			},
			onConfirmPopover() {
				this.$emit('confirm', this.innerTagList);
			},
			onCancelPopover() {
				this.innerTagList = JSON.parse(JSON.stringify(this.tagList));
				this.$emit('hide');
			},
			onHidePopover() {
				// console.log(this.innerTagList);
				// this.$emit('onHide', this.innerTagList);
			},
			validTagIncludes(id) {
				let innerTagList = this.innerTagList;
				innerTagList = innerTagList?.map((val) => val.id);
				return innerTagList?.includes(id);
			},
			targetBlank,
		},
	};
</script>

<style lang="scss" scoped>
	.tag-popover {
		width: 300px;
		.echo-tag-list {
			max-height: 100px;
			overflow-y: auto;
		}
		.input-area {
			::v-deep .el-input__inner {
				border: none;
			}
		}
		.tag-list {
			max-height: 260px;
			overflow-y: auto;
			.tag-li {
				&:hover {
					background-color: rgb(247, 248, 251);
				}
			}
			.tag-active {
				background-color: rgb(236, 249, 255);
				&:hover {
					background-color: rgb(236, 249, 255);
				}
			}
		}
	}
</style>
