<template>
	<div class="mail-plus-config-other config-page-item">
		<div class="mail-config-header">
			<div><h3 class="config-page-title">其他配置</h3></div>
		</div>
		<div class="mail-plus-config-other-content">
			<div class="section-title">邮件延迟发送</div>
			<div class="section-wrapper">
				<span class="label">邮件延迟发送:</span>
				<div class="section-inner">
					<div class="mm-radio-group radio-list">
						<label class="mm-radio"
							><input
								name="mm-radio-group-39"
								type="radio"
								value="0" /><span class="mm-radio-faux"
								><span class="mm-radio-input"></span
								><span class="mm-radio-label"
									>即时发送</span
								></span
							></label
						><label class="mm-radio"
							><input
								name="mm-radio-group-39"
								type="radio"
								value="0.5" /><span class="mm-radio-faux"
								><span class="mm-radio-input"></span
								><span class="mm-radio-label"
									>延迟 30 秒发送</span
								></span
							></label
						><label class="mm-radio"
							><input
								name="mm-radio-group-39"
								type="radio"
								value="1" /><span class="mm-radio-faux"
								><span class="mm-radio-input"></span
								><span class="mm-radio-label"
									>延迟 1 分钟发送</span
								></span
							></label
						><label class="mm-radio"
							><input
								name="mm-radio-group-39"
								type="radio"
								value="2" /><span class="mm-radio-faux"
								><span class="mm-radio-input"></span
								><span class="mm-radio-label"
									>延迟 2 分钟发送</span
								></span
							></label
						><label class="mm-radio"
							><input
								name="mm-radio-group-39"
								type="radio"
								value="5" /><span class="mm-radio-faux"
								><span class="mm-radio-input"></span
								><span class="mm-radio-label"
									>延迟 5 分钟发送</span
								></span
							></label
						>
					</div>
					<div
						class="tips"
						style="padding-left: 22px">
						该设置仅对非定时邮件生效；实现机制同定时邮件，系统自动加上延迟时间作为定时邮件，延迟时间内邮件保存在草稿箱
					</div>
				</div>
			</div>
			<div class="section-title">群发单显</div>
			<div class="section-wrapper">
				<span class="label">发送间隔:</span>
				<div>
					<form class="mm-form-horizontal mm-form time-interval">
						<span>随机发送的时间间隔为</span>
						<div class="mm-form-field--required mm-form-field">
							<!---->
							<div
								class="mm-form-field-control"
								style="margin-left: 0px">
								<span class="mm-form-field-children"
									><span
										class="mm-input--suffix mm-input mm-input-number"
										><!----><span
											class="mm-input-affix-wrapper"
											><!----><input
												type="text"
												class="mm-input-inner" /><span
												class="mm-input-suffix"
												><!---->
												<div
													class="mm-input-number-control-wrapper">
													<span
														class="mm-input-number-control mm-input-number-control--disabled"
														><svg
															class="mm-icon mm-icon-down"
															viewBox="0 0 24 24"
															name="down"
															xmlns="XMLNS"
															fill="#606266"
															style="
																height: 12px;
																width: 12px;
															">
															<path
																d="M3.8 6.5h16.4c.4 0 .8.6.4 1l-8 9.8c-.3.3-.9.3-1.2 0l-8-9.8c-.4-.4-.1-1 .4-1z"></path></svg></span
													><span
														class="mm-input-number-control"
														><svg
															class="mm-icon mm-icon-up"
															viewBox="0 0 24 24"
															name="up"
															xmlns="XMLNS"
															fill="#606266"
															style="
																height: 12px;
																width: 12px;
															">
															<path
																d="M20.2 17.5H3.8c-.4 0-.8-.6-.4-1l8-9.8c.3-.3.9-.3 1.2 0l8 9.8c.4.4.1 1-.4 1z"></path></svg
													></span></div></span></span
										><!----></span
									></span
								><!----><!---->
							</div>
						</div>
						<span>~</span>
						<div class="mm-form-field--required mm-form-field">
							<!---->
							<div
								class="mm-form-field-control"
								style="margin-left: 0px">
								<span class="mm-form-field-children"
									><span
										class="mm-input--suffix mm-input mm-input-number"
										><!----><span
											class="mm-input-affix-wrapper"
											><!----><input
												type="text"
												class="mm-input-inner" /><span
												class="mm-input-suffix"
												><!---->
												<div
													class="mm-input-number-control-wrapper">
													<span
														class="mm-input-number-control"
														><svg
															class="mm-icon mm-icon-down"
															viewBox="0 0 24 24"
															name="down"
															xmlns="XMLNS"
															fill="#606266"
															style="
																height: 12px;
																width: 12px;
															">
															<path
																d="M3.8 6.5h16.4c.4 0 .8.6.4 1l-8 9.8c-.3.3-.9.3-1.2 0l-8-9.8c-.4-.4-.1-1 .4-1z"></path></svg></span
													><span
														class="mm-input-number-control mm-input-number-control--disabled"
														><svg
															class="mm-icon mm-icon-up"
															viewBox="0 0 24 24"
															name="up"
															xmlns="XMLNS"
															fill="#606266"
															style="
																height: 12px;
																width: 12px;
															">
															<path
																d="M20.2 17.5H3.8c-.4 0-.8-.6-.4-1l8-9.8c.3-.3.9-.3 1.2 0l8 9.8c.4.4.1 1-.4 1z"></path></svg
													></span></div></span></span
										><!----></span
									></span
								><!----><!---->
							</div>
						</div>
						秒
					</form>
					<div class="tips">
						延长发件间隔，可有效降低被判为垃圾邮件的概率，防止封号。时间间隔范围：30~1800秒。
					</div>
				</div>
			</div>
			<div class="section-title">当地实时时间</div>
			<div class="section-wrapper">
				<span class="label">当地实时时间:</span>
				<div class="mm-radio-group radio-list">
					<label class="mm-radio"
						><input
							name="mm-radio-group-40"
							type="radio"
							value="1" /><span class="mm-radio-faux"
							><span class="mm-radio-input"></span
							><span class="mm-radio-label">启用</span></span
						></label
					><label class="mm-radio"
						><input
							name="mm-radio-group-40"
							type="radio"
							value="0" /><span class="mm-radio-faux"
							><span class="mm-radio-input"></span
							><span class="mm-radio-label">停用</span></span
						></label
					>
				</div>
			</div>
			<div class="section-wrapper">
				<span class="label">邮件发送提醒:</span>
				<div class="mm-radio-group section-list">
					<label class="mm-radio"
						><input
							name="mm-radio-group-41"
							type="radio"
							value="1" /><span class="mm-radio-faux"
							><span class="mm-radio-input"></span
							><span class="mm-radio-label">弹窗提醒</span></span
						></label
					><label class="mm-radio"
						><input
							name="mm-radio-group-41"
							type="radio"
							value="0" /><span class="mm-radio-faux"
							><span class="mm-radio-input"></span
							><span class="mm-radio-label">不提醒</span></span
						></label
					>
				</div>
			</div>
			<div class="section-title">邮件翻译</div>
			<div class="section-wrapper">
				<span class="label">邮件翻译功能:</span>
				<div class="mm-radio-group radio-list">
					<label class="mm-radio"
						><input
							name="mm-radio-group-42"
							type="radio"
							value="1" /><span class="mm-radio-faux"
							><span class="mm-radio-input"></span
							><span class="mm-radio-label">启用</span></span
						></label
					><label class="mm-radio"
						><input
							name="mm-radio-group-42"
							type="radio"
							value="0" /><span class="mm-radio-faux"
							><span class="mm-radio-input"></span
							><span class="mm-radio-label">停用</span></span
						></label
					>
				</div>
			</div>
			<div class="section-title">待处理邮件</div>
			<div class="section-wrapper">
				<span class="label">待处理关闭:</span>
				<div class="mm-radio-group radio-list">
					<label class="mm-radio"
						><input
							name="mm-radio-group-43"
							type="radio"
							value="1" /><span class="mm-radio-faux"
							><span class="mm-radio-input"></span
							><span class="mm-radio-label"
								>回复邮件后自动关闭待处理状态</span
							></span
						></label
					><label class="mm-radio"
						><input
							name="mm-radio-group-43"
							type="radio"
							value="0" /><span class="mm-radio-faux"
							><span class="mm-radio-input"></span
							><span class="mm-radio-label"
								>手动关闭待处理状态</span
							></span
						></label
					>
				</div>
			</div>
			<div class="section-wrapper">
				<span class="label">自动化待处理:</span>
				<div class="section-innner">
					<div class="mm-radio-group radio-list">
						<label class="mm-radio"
							><input
								name="mm-radio-group-44"
								type="radio"
								value="1" /><span class="mm-radio-faux"
								><span class="mm-radio-input"></span
								><span class="mm-radio-label">启用</span></span
							></label
						><label class="mm-radio"
							><input
								name="mm-radio-group-44"
								type="radio"
								value="0" /><span class="mm-radio-faux"
								><span class="mm-radio-input"></span
								><span class="mm-radio-label">停用</span></span
							></label
						>
					</div>
					<!----><!---->
				</div>
			</div>
			<div class="section-title">异常邮箱检测</div>
			<div class="section-wrapper">
				<span class="label">异常邮箱检测:</span>
				<div class="mm-radio-group radio-list">
					<label class="mm-radio"
						><input
							name="mm-radio-group-45"
							type="radio"
							value="1" /><span class="mm-radio-faux"
							><span class="mm-radio-input"></span
							><span class="mm-radio-label">启用</span></span
						></label
					><label class="mm-radio"
						><input
							name="mm-radio-group-45"
							type="radio"
							value="0" /><span class="mm-radio-faux"
							><span class="mm-radio-input"></span
							><span class="mm-radio-label">停用</span></span
						></label
					>
				</div>
			</div>
		</div>
		<div class="btn-bottom">
			<div class="mm-space mm-space__horizontal">
				<div
					class="mm-space-item"
					style="margin-right: 16px">
					<button
						type="button"
						class="mm-button">
						<!----><!----><span>取消</span
						><!---->
					</button>
				</div>
				<div class="mm-space-item">
					<button
						type="button"
						class="mm-button mm-button__primary">
						<!----><!----><span>保存</span
						><!---->
					</button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	export default {};
</script>

<style lang="scss" scoped>
@import url('./css/index.css');
</style>
