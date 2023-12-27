<template>
	<div>
		<el-dialog :title="title" :visible.sync="dialogVisible" width="700px" :before-close="handleClose">
			<el-form class="mt-10" style="overflow:hidden;" :model="signatureForm" label-width="40px">
				<el-form-item label="名称">
					<el-input v-model="signatureForm.name" placeholder="请输入"></el-input>
				</el-form-item>
				<el-form-item label="内容">
					<div style="border:1px solid #ccc;">
						<Toolbar ref="editorInstance" style="border-bottom: 1px solid #ccc" :editor="editor"
							:defaultConfig="toolbarConfig" />
						<!-- 编辑器 -->
						<Editor ref="editorInstance" style="height: 500px; overflow-y: hidden"
							v-model="signatureForm.content" @onCreated="onCreated" mode="default" />
					</div>
				</el-form-item>
			</el-form>
			<span slot="footer" class="dialog-footer">
				<el-button @click="dialogVisible = false">取 消</el-button>
				<el-button type="primary" @click="dialogVisible = false">确 定</el-button>
			</span>
		</el-dialog>
	</div>
</template>

<script>
import { Editor, Toolbar } from "@wangeditor/editor-for-vue";
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
				name: '',
				content: '',
			},
		};
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
			this.editor.getConfig().MENU_CONF['uploadImage'] = {
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
		},
		handleClose(done) {

		},
	},
};
</script>

<style lang="scss" scoped></style>
