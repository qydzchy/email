<template>
	<div class="mail-plus-import config-page-item">
		<div class="steps">
			<h3 class="config-page-title">导入邮件</h3>
			<ul class="c-form-inputItem">
				<li class="flex items-center">
					<label class="m-label">第一步：</label>
					<el-select placeholder="选择目标邮箱" style="width:378px;"></el-select>
					<el-select placeholder="选择目标文件夹" class="ml-6" style="width:378px;"></el-select>
				</li>
				<li class="pt-2">
					<label class="m-label">第二步：</label>
					<div class="mx-2 py-2 inline-block">
						<a class="disabled m-btn m-uploadBtn">
							<span @click="onChooseFile">选择文件</span>
							<input ref="selectFileRef" disabled="" type="file" name="file" />
						</a>
						<span class="e-file-value block">
							<span style="color: red">暂时只支持pst、eml文件或者是eml文件组成的zip压缩包，不超过2G</span>
						</span>
					</div>
				</li>
				<li class="pt-2">
					<label class="m-label">第三步：</label>
					<div class="mx-2 py-2 inline-block">
						<div>
							<button class="disabled m-btn" type="button">
								<span>确认上传</span></button><span class="e-upload-tips"></span><!---->
						</div>
						<label class="mm-checkbox">
							<input true-value="true" type="checkbox" />
							<span class="mm-checkbox-input"></span><span class="mm-checkbox-label">
								过滤导入邮件
							</span>
						</label>
					</div>
				</li>
			</ul>
		</div>
		<div class="mail-history">
			<h3 class="config-page-title history-title">
				历史任务列表
				<span class="okki-icon-wrap" title="刷新列表" style="cursor: pointer">&ZeroWidthSpace;<svg title="刷新列表"
						xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" aria-hidden="true"
						class="okki-svg-icon" fill="currentColor">
						<line x1="21" y1="4" x2="21" y2="12"></line>
						<line x1="3" y1="12" x2="3" y2="20"></line>
						<path fill-rule="evenodd" clip-rule="evenodd"
							d="M12 4a7.993 7.993 0 00-6.762 3.723A1 1 0 113.55 6.652 9.993 9.993 0 0112 2a9.985 9.985 0 018 3.999V4a1 1 0 112 0v8c0 .149-.003.296-.01.443a1 1 0 11-1.998-.087A8 8 0 0012 4zM3.174 9.967a1 1 0 01.879 1.108 8 8 0 0014.997 4.71 1 1 0 011.76.948A9.998 9.998 0 0112 22a9.985 9.985 0 01-8-3.999V20a1 1 0 11-2 0v-8c0-.39.022-.775.066-1.154a1 1 0 011.108-.879z">
						</path>
					</svg></span>
			</h3>
			<div class="mail-history-list c-form-inputItem">
				<table>
					<thead>
						<tr>
							<th class="list-name">文件名</th>
							<th class="list-mail">邮箱</th>
							<th class="list-folder">文件夹</th>
							<th class="list-status">导入状态</th>
							<th class="list-count"><span>邮件总数</span></th>
							<th class="list-deal">
								<span>已处理数</span><span class="mm-tooltip inline-block h-4"><span
										class="mm-tooltip-trigger"><svg xmlns="http://www.w3.org/2000/svg" width="14"
											height="14" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
											fill="currentColor" style="vertical-align: -0.1em">
											<path fill-rule="evenodd" clip-rule="evenodd"
												d="M4 12a8 8 0 1116 0 8 8 0 01-16 0zm8-10C6.477 2 2 6.477 2 12s4.477 10 10 10 10-4.477 10-10S17.523 2 12 2zm1.2 4.75a1.25 1.25 0 11-2.5 0 1.25 1.25 0 012.5 0zM11.2 9a1 1 0 100 2v5h-.9a1 1 0 100 2h3.5a1 1 0 100-2h-.6v-6a1 1 0 00-1-1h-1z">
											</path>
										</svg></span><!----></span>
							</th>
							<th class="list-fail">处理失败数</th>
							<th class="list-desc">失败原因</th>
							<th class="list-time">导入时间</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<p title="A16Z合伙人：5G来了，我们能干啥？-2019-10-11.eml" class="ellipsis">
									A16Z合伙人：5G来了，我们能干啥？-2019-10-11.eml
								</p>
							</td>
							<td>
								<p title="sales17@allxchips.com" class="ellipsis">
									sales17@allxchips.com
								</p>
							</td>
							<td>
								<p>收件箱</p>
							</td>
							<td><span class="success">导入成功</span></td>
							<td>
								<p>1</p>
							</td>
							<td>
								<span>1</span><span class="mm-tooltip inline-block h-4"><span
										class="mm-tooltip-trigger"><svg xmlns="http://www.w3.org/2000/svg" width="14"
											height="14" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
											fill="currentColor" style="
												vertical-align: -0.1em;
												margin-left: 4px;
											">
											<path fill-rule="evenodd" clip-rule="evenodd"
												d="M4 12a8 8 0 1116 0 8 8 0 01-16 0zm8-10C6.477 2 2 6.477 2 12s4.477 10 10 10 10-4.477 10-10S17.523 2 12 2zm1.2 4.75a1.25 1.25 0 11-2.5 0 1.25 1.25 0 012.5 0zM11.2 9a1 1 0 100 2v5h-.9a1 1 0 100 2h3.5a1 1 0 100-2h-.6v-6a1 1 0 00-1-1h-1z">
											</path>
										</svg></span><!----></span>
							</td>
							<td>
								<p>0</p>
							</td>
							<td>
								<p>——</p>
							</td>
							<td>
								<p>2023-12-22 15:16:59</p>
							</td>
						</tr>
						<tr>
							<td>
								<p title="“火箭蛋”又来袭蛋价后期仍有上涨可能-2019-07-17.eml" class="ellipsis">
									“火箭蛋”又来袭蛋价后期仍有上涨可能-2019-07-17.eml
								</p>
							</td>
							<td>
								<p title="w0r1d_space@sohu.com" class="ellipsis">
									w0r1d_space@sohu.com
								</p>
							</td>
							<td>
								<p>tjm01</p>
							</td>
							<td><span class="success">导入成功</span></td>
							<td>
								<p>1</p>
							</td>
							<td>
								<span>1</span><span class="mm-tooltip inline-block h-4"><span
										class="mm-tooltip-trigger"><svg xmlns="http://www.w3.org/2000/svg" width="14"
											height="14" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
											fill="currentColor" style="
												vertical-align: -0.1em;
												margin-left: 4px;
											">
											<path fill-rule="evenodd" clip-rule="evenodd"
												d="M4 12a8 8 0 1116 0 8 8 0 01-16 0zm8-10C6.477 2 2 6.477 2 12s4.477 10 10 10 10-4.477 10-10S17.523 2 12 2zm1.2 4.75a1.25 1.25 0 11-2.5 0 1.25 1.25 0 012.5 0zM11.2 9a1 1 0 100 2v5h-.9a1 1 0 100 2h3.5a1 1 0 100-2h-.6v-6a1 1 0 00-1-1h-1z">
											</path>
										</svg></span><!----></span>
							</td>
							<td>
								<p>0</p>
							</td>
							<td>
								<p>——</p>
							</td>
							<td>
								<p>2023-07-26 17:49:45</p>
							</td>
						</tr>
						<tr>
							<td>
								<p title="“手铐打不开，民警求助消防”上热搜：119吗，我是110-2019-07-17.eml" class="ellipsis">
									“手铐打不开，民警求助消防”上热搜：119吗，我是110-2019-07-17.eml
								</p>
							</td>
							<td>
								<p title="w0r1d_space@sohu.com" class="ellipsis">
									w0r1d_space@sohu.com
								</p>
							</td>
							<td>
								<p>tjm01</p>
							</td>
							<td><span class="success">导入成功</span></td>
							<td>
								<p>1</p>
							</td>
							<td>
								<span>1</span><span class="mm-tooltip inline-block h-4"><span
										class="mm-tooltip-trigger"><svg xmlns="http://www.w3.org/2000/svg" width="14"
											height="14" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
											fill="currentColor" style="
												vertical-align: -0.1em;
												margin-left: 4px;
											">
											<path fill-rule="evenodd" clip-rule="evenodd"
												d="M4 12a8 8 0 1116 0 8 8 0 01-16 0zm8-10C6.477 2 2 6.477 2 12s4.477 10 10 10 10-4.477 10-10S17.523 2 12 2zm1.2 4.75a1.25 1.25 0 11-2.5 0 1.25 1.25 0 012.5 0zM11.2 9a1 1 0 100 2v5h-.9a1 1 0 100 2h3.5a1 1 0 100-2h-.6v-6a1 1 0 00-1-1h-1z">
											</path>
										</svg></span><!----></span>
							</td>
							<td>
								<p>0</p>
							</td>
							<td>
								<p>——</p>
							</td>
							<td>
								<p>2023-07-26 17:48:50</p>
							</td>
						</tr>
						<tr>
							<td>
								<p title="“火箭蛋”又来袭蛋价后期仍有上涨可能-2019-07-17.eml" class="ellipsis">
									“火箭蛋”又来袭蛋价后期仍有上涨可能-2019-07-17.eml
								</p>
							</td>
							<td>
								<p title="w0r1d_space@sohu.com" class="ellipsis">
									w0r1d_space@sohu.com
								</p>
							</td>
							<td>
								<p>tjm01</p>
							</td>
							<td><span class="success">导入成功</span></td>
							<td>
								<p>1</p>
							</td>
							<td>
								<span>1</span><span class="mm-tooltip inline-block h-4"><span
										class="mm-tooltip-trigger"><svg xmlns="http://www.w3.org/2000/svg" width="14"
											height="14" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
											fill="currentColor" style="
												vertical-align: -0.1em;
												margin-left: 4px;
											">
											<path fill-rule="evenodd" clip-rule="evenodd"
												d="M4 12a8 8 0 1116 0 8 8 0 01-16 0zm8-10C6.477 2 2 6.477 2 12s4.477 10 10 10 10-4.477 10-10S17.523 2 12 2zm1.2 4.75a1.25 1.25 0 11-2.5 0 1.25 1.25 0 012.5 0zM11.2 9a1 1 0 100 2v5h-.9a1 1 0 100 2h3.5a1 1 0 100-2h-.6v-6a1 1 0 00-1-1h-1z">
											</path>
										</svg></span><!----></span>
							</td>
							<td>
								<p>0</p>
							</td>
							<td>
								<p>——</p>
							</td>
							<td>
								<p>2023-07-26 17:47:22</p>
							</td>
						</tr>
						<tr>
							<td>
								<p title="“火箭蛋”又来袭蛋价后期仍有上涨可能-2019-07-17.eml" class="ellipsis">
									“火箭蛋”又来袭蛋价后期仍有上涨可能-2019-07-17.eml
								</p>
							</td>
							<td>
								<p title="w0r1d_space@sohu.com" class="ellipsis">
									w0r1d_space@sohu.com
								</p>
							</td>
							<td>
								<p>tjm01</p>
							</td>
							<td><span class="success">导入成功</span></td>
							<td>
								<p>1</p>
							</td>
							<td>
								<span>1</span><span class="mm-tooltip inline-block h-4"><span
										class="mm-tooltip-trigger"><svg xmlns="http://www.w3.org/2000/svg" width="14"
											height="14" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
											fill="currentColor" style="
												vertical-align: -0.1em;
												margin-left: 4px;
											">
											<path fill-rule="evenodd" clip-rule="evenodd"
												d="M4 12a8 8 0 1116 0 8 8 0 01-16 0zm8-10C6.477 2 2 6.477 2 12s4.477 10 10 10 10-4.477 10-10S17.523 2 12 2zm1.2 4.75a1.25 1.25 0 11-2.5 0 1.25 1.25 0 012.5 0zM11.2 9a1 1 0 100 2v5h-.9a1 1 0 100 2h3.5a1 1 0 100-2h-.6v-6a1 1 0 00-1-1h-1z">
											</path>
										</svg></span><!----></span>
							</td>
							<td>
								<p>0</p>
							</td>
							<td>
								<p>——</p>
							</td>
							<td>
								<p>2023-07-26 17:46:34</p>
							</td>
						</tr>
						<tr>
							<td>
								<p title="阿斯顿·马丁全新跑车将于7月19日国内首发-2019-07-17.eml" class="ellipsis">
									阿斯顿·马丁全新跑车将于7月19日国内首发-2019-07-17.eml
								</p>
							</td>
							<td>
								<p title="w0r1d_space@sohu.com" class="ellipsis">
									w0r1d_space@sohu.com
								</p>
							</td>
							<td>
								<p>tjm01</p>
							</td>
							<td><span class="success">导入成功</span></td>
							<td>
								<p>1</p>
							</td>
							<td>
								<span>1</span><span class="mm-tooltip inline-block h-4"><span
										class="mm-tooltip-trigger"><svg xmlns="http://www.w3.org/2000/svg" width="14"
											height="14" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
											fill="currentColor" style="
												vertical-align: -0.1em;
												margin-left: 4px;
											">
											<path fill-rule="evenodd" clip-rule="evenodd"
												d="M4 12a8 8 0 1116 0 8 8 0 01-16 0zm8-10C6.477 2 2 6.477 2 12s4.477 10 10 10 10-4.477 10-10S17.523 2 12 2zm1.2 4.75a1.25 1.25 0 11-2.5 0 1.25 1.25 0 012.5 0zM11.2 9a1 1 0 100 2v5h-.9a1 1 0 100 2h3.5a1 1 0 100-2h-.6v-6a1 1 0 00-1-1h-1z">
											</path>
										</svg></span><!----></span>
							</td>
							<td>
								<p>0</p>
							</td>
							<td>
								<p>——</p>
							</td>
							<td>
								<p>2023-07-26 17:45:50</p>
							</td>
						</tr>
						<tr>
							<td>
								<p title="70城房价涨幅回落下半年资金压力或迫使房企降价-2019-07-17.eml" class="ellipsis">
									70城房价涨幅回落下半年资金压力或迫使房企降价-2019-07-17.eml
								</p>
							</td>
							<td>
								<p title="w0r1d_space@sohu.com" class="ellipsis">
									w0r1d_space@sohu.com
								</p>
							</td>
							<td>
								<p>tjm01</p>
							</td>
							<td><span class="success">导入成功</span></td>
							<td>
								<p>1</p>
							</td>
							<td>
								<span>1</span><span class="mm-tooltip inline-block h-4"><span
										class="mm-tooltip-trigger"><svg xmlns="http://www.w3.org/2000/svg" width="14"
											height="14" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
											fill="currentColor" style="
												vertical-align: -0.1em;
												margin-left: 4px;
											">
											<path fill-rule="evenodd" clip-rule="evenodd"
												d="M4 12a8 8 0 1116 0 8 8 0 01-16 0zm8-10C6.477 2 2 6.477 2 12s4.477 10 10 10 10-4.477 10-10S17.523 2 12 2zm1.2 4.75a1.25 1.25 0 11-2.5 0 1.25 1.25 0 012.5 0zM11.2 9a1 1 0 100 2v5h-.9a1 1 0 100 2h3.5a1 1 0 100-2h-.6v-6a1 1 0 00-1-1h-1z">
											</path>
										</svg></span><!----></span>
							</td>
							<td>
								<p>0</p>
							</td>
							<td>
								<p>——</p>
							</td>
							<td>
								<p>2023-07-26 17:44:46</p>
							</td>
						</tr>
						<tr>
							<td>
								<p title="“火箭蛋”又来袭蛋价后期仍有上涨可能-2019-07-17.eml" class="ellipsis">
									“火箭蛋”又来袭蛋价后期仍有上涨可能-2019-07-17.eml
								</p>
							</td>
							<td>
								<p title="w0r1d_space@sohu.com" class="ellipsis">
									w0r1d_space@sohu.com
								</p>
							</td>
							<td>
								<p>tjm01</p>
							</td>
							<td><span class="success">导入成功</span></td>
							<td>
								<p>1</p>
							</td>
							<td>
								<span>1</span><span class="mm-tooltip inline-block h-4"><span
										class="mm-tooltip-trigger"><svg xmlns="http://www.w3.org/2000/svg" width="14"
											height="14" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
											fill="currentColor" style="
												vertical-align: -0.1em;
												margin-left: 4px;
											">
											<path fill-rule="evenodd" clip-rule="evenodd"
												d="M4 12a8 8 0 1116 0 8 8 0 01-16 0zm8-10C6.477 2 2 6.477 2 12s4.477 10 10 10 10-4.477 10-10S17.523 2 12 2zm1.2 4.75a1.25 1.25 0 11-2.5 0 1.25 1.25 0 012.5 0zM11.2 9a1 1 0 100 2v5h-.9a1 1 0 100 2h3.5a1 1 0 100-2h-.6v-6a1 1 0 00-1-1h-1z">
											</path>
										</svg></span><!----></span>
							</td>
							<td>
								<p>0</p>
							</td>
							<td>
								<p>——</p>
							</td>
							<td>
								<p>2023-07-26 17:44:25</p>
							</td>
						</tr>
					</tbody>
				</table>
				<!---->
			</div>
			<div class="mail-page">
				<ul class="mm-pagination mm-pagination__right">
					<li class="disabled mm-pagination-prev">
						<svg class="mm-icon mm-icon-chevronleft" viewBox="0 0 24 24" name="chevronleft" xmlns="XMLNS"
							fill="currentColor" style="height: 0.875rem; width: 0.875rem">
							<path
								d="M15.8 22l-9.6-9.4c-.3-.3-.3-.8 0-1.1l9.6-9.4c.3-.3.7-.3 1 0l1 1c.3.3.3.7 0 1l-7.6 7.4c-.3.3-.3.8 0 1.1l7.5 7.4c.3.3.3.7 0 1l-1 1c-.2.2-.6.2-.9 0z">
							</path>
						</svg>
					</li>
					<li>
						<ul class="mm-pagination-pager">
							<li class="active mm-pagination-number">
								<span>1</span>
							</li>
							<!----><!----><!---->
						</ul>
					</li>
					<li class="disabled mm-pagination-next">
						<svg class="mm-icon mm-icon-chevronright" viewBox="0 0 24 24" name="chevronright" xmlns="XMLNS"
							fill="currentColor" style="height: 0.875rem; width: 0.875rem">
							<path
								d="M8.3 2l9.5 9.5c.3.3.3.7 0 1L8.3 22c-.3.2-.8.2-1.1 0l-1-1c-.2-.3-.2-.8 0-1.1l7.6-7.4c.2-.3.2-.7 0-1L6.3 4.1C6 3.8 6 3.3 6.3 3l1-1c.3-.2.7-.2 1 0z">
							</path>
						</svg>
					</li>
				</ul>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	data(){
		return{

		}
	},
	methods:{
		onChooseFile(){
			console.log(this.$refs.selectFileRef);
			this.$refs.selectFileRef.click()
		}
	}
};
</script>

<style lang="scss" scoped>
@import url('./css/index.css');
</style>
