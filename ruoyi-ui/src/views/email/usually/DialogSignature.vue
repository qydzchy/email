<template>
	<div>
		<el-dialog :title="title" :visible.sync="dialogVisible" width="700px" @close="onCancel">
			<el-form class="mt-10" style="overflow:hidden;" :model="signatureForm" ref="signatureFormRef" label-width="50px"
				:rules="rules">
				<el-form-item label="名称" prop="title">
					<el-input v-model="signatureForm.title" placeholder="请输入"></el-input>
				</el-form-item>
				<el-form-item label="内容" prop="content">
					<div style="border:1px solid #ccc;">
						<Toolbar ref="editorInstance" style="border-bottom: 1px solid #ccc" :editor="editor"
							:defaultConfig="toolbarConfig" />
						<!-- 编辑器 -->
						<Editor ref="editorInstance" style="height: 260px" v-model="signatureForm.content"
							@onCreated="onCreated" mode="default" />
					</div>
				</el-form-item>
			</el-form>
			<span slot="footer" class="dialog-footer">
				<el-button @click="onCancel">取 消</el-button>
				<el-button type="primary" @click="onConfirm">确 定</el-button>
			</span>
		</el-dialog>
	</div>
</template>

<script>
import { Editor, Toolbar } from "@wangeditor/editor-for-vue";
import { addSignature, editSignature } from '@/api/email/usually'
import { fontSizeList, fontFamilyList, colors } from '@/constant/editorOption'
export default {
	props: {
		dialogVisible: {
			type: Boolean,
			default: false,
			required: true
		},
		signatureData: {
			type: Object,
			default: {
				id: '',
			},
		},
	},
	components: {
		Editor,
		Toolbar,
	},
	data() {
		return {
			editor: null,
			toolbarConfig: {
				excludeKeys: [
					'group-video'
				]
			},
			signatureForm: {
				id: '',
				title: '',
				content: '',
			},
			rules: {
				title: [
					{ required: true, message: '请填写名称', trigger: 'blur' }
				]
			},
		};
	},
	watch: {
		signatureData: {
			handler(newVal) {
				this.signatureForm = {
					...newVal
				}
			}
		},
		deep: true,
		immediate: true
	},
	computed: {
		title() {
			return this.signatureData?.id ? '编辑个性签名' : '新建个性签名';
		},
	},
	methods: {
		onCreated(editor) {
			let that = this
			this.editor = Object.seal(editor);
			this.editor.getConfig().MENU_CONF = {
				...this.editor.getConfig().MENU_CONF,
				fontSize: {
					fontSizeList
				},
				fontFamily: {
					fontFamilyList
				},
				color: {
					colors
				},
				uploadImage: {
					fieldName: 'file',
					server: '/common/upload',
					maxFileSize: 500 * 1024, //500kb
					base64LimitSize: 500 * 1024,
					allowedFileTypes: ['image/*'],
					onError(file, err, _res) {
						let errType = ''
						err?.message?.includes('500 KB') && (errType = 'picture')
						switch (errType) {
							case 'picture':
								that.$message.error('邮件图片不能大于 500K')
								break;
							default:
								break;
						}
					}
				}
			}
		},
		async addReq(data) {
			try {
				const res = await addSignature(data)
				if (res.code === 200) {
					this.$message.success('新增成功')
					this.$emit('onConfirm')
				}
			} catch { }
		},
		async editReq(data) {
			try {
				const res = await editSignature(data)
				if (res.code === 200) {
					this.$message.success('编辑成功')
					this.$emit('onConfirm')
				}
			} catch { }
		},
		onConfirm() {
			this.$refs.signatureFormRef.validate(res => {
				if (res) {
					let data = {
						...this.signatureForm
					}
					if (!data.id) {
						delete data.id
						this.addReq(data)
					} else {
						this.editReq(data)
					}
				}
			})

		},
		onCancel() {
			this.$emit('onCancel')
		},
	},
};
</script>

<style lang="scss" scoped>
::v-deep .el-form-item__content {
	overflow-y: auto;
	overflow-x: hidden;
}
</style>
