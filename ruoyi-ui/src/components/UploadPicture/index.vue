<template>
	<div>
		<el-upload
			:action="baseUrl + 'common/uploads'"
			name="files"
			:on-preview="handleModify"
			:on-remove="handleRemove"
			:on-change="handleChange"
			:file-list="fileList"
			accept="image/*"
			:auto-upload="false"
			list-type="picture"
			multiple>
			<el-button
				slot="trigger"
				size="small"
				type="primary"
				>选择图片</el-button
			>
			<div>修改文件名，需选取图片后点击文件名</div>
		</el-upload>
	</div>
</template>
<script>
	export default {
		props: {
			value: [Array, String],
		},
		data() {
			return {
				fileList: [],
				baseUrl: process.env.VUE_APP_BASE_API,
			};
		},
		watch: {
			value(n) {
				if (n?.length) {
					this.fileList = n;
				} else {
					this.fileList = [];
				}
			},
		},
		methods: {
			handleChange(_file, fileList) {
				this.fileList = fileList;
				this.$emit('input', this.fileList);
			},
			// 修改文件名
			handleModify(file) {
				this.$prompt('请输入修改的文件名', '提示', {
					confirmButtonText: '确定',
					confirmButtonText: '取消',
					inputPattern: /^.+$/,
					inputErrorMessage: '填写不能为空',
				})
					.then(({ value }) => {
						this.fileList = this.fileList.map((val) => {
							if (val.uid === file.uid) {
								let newFileName = this.splitFileName(
									file.raw.name,
									value,
								);
								val.name = newFileName;
								val.raw = new File([file.raw], newFileName, {
									type: file.raw.type,
								});
							}
							return val;
						});
						this.$emit('input', this.fileList);
					})
					.catch(() => {});
			},
			handleRemove(file) {
				this.fileList = this.fileList.filter(
					(val) => val.uid !== file.uid,
				);
			},
			splitFileName(str, convertName) {
				const strAfter = str.split('.').pop();
				if (!strAfter) {
					return strAfter;
				}
				return `${convertName}.${strAfter}`;
			},
		},
	};
</script>

<style lang="scss" scoped></style>
