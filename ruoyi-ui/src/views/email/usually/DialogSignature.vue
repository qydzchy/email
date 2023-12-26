<template>
	<div>
		<el-dialog
			:title="title"
			:visible.sync="dialogVisible"
			width="30%"
			:before-close="handleClose">
			<span>这是一段信息</span>
			<span
				slot="footer"
				class="dialog-footer">
				<el-button @click="dialogVisible = false">取 消</el-button>
				<el-button
					type="primary"
					@click="dialogVisible = false"
					>确 定</el-button
				>
			</span>
		</el-dialog>
	</div>
</template>

<script>
	export default {
		props: {
			dialogVisible: {
				type: Boolean,
				default: false,
                required:true
			},
			signatureData: {
				type: Object,
				default: {
					id: '',
				},
			},
		},
		data() {
			return {
			};
		},
		computed: {
			title() {
				return this.signatureData.id ? '新建个性签名' : '编辑个性签名';
			},
		},
		methods: {
			handleClose(done) {
				
			},
		},
	};
</script>

<style lang="scss" scoped></style>
