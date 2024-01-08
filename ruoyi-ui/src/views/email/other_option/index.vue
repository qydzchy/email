<template>
	<div class="mail-plus-config-other config-page-item">
		<div class="mail-config-header">
			<div>
				<h3 class="config-page-title">其他配置</h3>
			</div>
		</div>
		<div class="mail-plus-config-other-content">
			<div class="section-title">邮件延迟发送</div>
			<div class="section-wrapper">
				<span class="label">邮件延迟发送:</span>
				<div class="section-inner">
					<div class="mm-radio-group radio-list">
						<el-radio-group v-model="optionForm.delayedMailDelivery">
							<el-radio :label="1">即时发送</el-radio>
							<el-radio :label="2">延迟 30 秒发送</el-radio>
							<el-radio :label="3">延迟 1 分钟发送</el-radio>
							<el-radio :label="4">延迟 2 分钟发送</el-radio>
							<el-radio :label="5">延迟 5 分钟发送</el-radio>
						</el-radio-group>
					</div>
					<div class="tips" style="padding-left: 22px">
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
							<div class="mm-form-field-control" style="margin-left: 0px">
								<span class="mm-form-field-children"><span
										class="mm-input--suffix mm-input mm-input-number"><!----><span
											class="mm-input-affix-wrapper"><!----><input type="number"
												class="mm-input-inner" v-model="optionForm.min" @blur="
													optionForm.min < 30
														? (optionForm.min = 30)
														: optionForm.min >
														1800 &&
														(optionForm.min = 1800)
													" /><span class="mm-input-suffix"><!---->
												<div class="mm-input-number-control-wrapper">
													<span class="mm-input-number-control" :class="{
														'mm-input-number-control--disabled':
															optionForm.min <=
															30,
													}" @click="
	optionForm.min >
	30 &&
	optionForm.min--
	">
														<svg class="mm-icon mm-icon-down" viewBox="0 0 24 24" name="down"
															xmlns="XMLNS" fill="#606266" style="
																height: 12px;
																width: 12px;
															">
															<path
																d="M3.8 6.5h16.4c.4 0 .8.6.4 1l-8 9.8c-.3.3-.9.3-1.2 0l-8-9.8c-.4-.4-.1-1 .4-1z">
															</path>
														</svg></span><span class="mm-input-number-control" :class="{
															'mm-input-number-control--disabled':
																optionForm.min >=
																1800,
														}" @click="
	optionForm.min <
	1800 &&
	optionForm.min++
	"><svg class="mm-icon mm-icon-up" viewBox="0 0 24 24" name="up" xmlns="XMLNS" fill="#606266" style="
																height: 12px;
																width: 12px;
															">
															<path
																d="M20.2 17.5H3.8c-.4 0-.8-.6-.4-1l8-9.8c.3-.3.9-.3 1.2 0l8 9.8c.4.4.1 1-.4 1z">
															</path>
														</svg></span>
												</div>
											</span></span><!----></span></span><!----><!---->
							</div>
						</div>
						<span>~</span>
						<div class="mm-form-field--required mm-form-field">
							<!---->
							<div class="mm-form-field-control" style="margin-left: 0px">
								<span class="mm-form-field-children"><span
										class="mm-input--suffix mm-input mm-input-number"><!----><span
											class="mm-input-affix-wrapper"><!----><input type="number"
												class="mm-input-inner" v-model="optionForm.max" @blur="
													optionForm.max < 30
														? (optionForm.max = 30)
														: optionForm.max >
														1800 &&
														(optionForm.max = 1800)
													" /><span class="mm-input-suffix"><!---->
												<div class="mm-input-number-control-wrapper">
													<span class="mm-input-number-control" :class="{
														'mm-input-number-control--disabled':
															optionForm.max <=
															30,
													}" @click="
	optionForm.max >
	30 &&
	optionForm.max--
	"><svg class="mm-icon mm-icon-down" viewBox="0 0 24 24" name="down" xmlns="XMLNS" fill="#606266" style="
																height: 12px;
																width: 12px;
															">
															<path
																d="M3.8 6.5h16.4c.4 0 .8.6.4 1l-8 9.8c-.3.3-.9.3-1.2 0l-8-9.8c-.4-.4-.1-1 .4-1z">
															</path>
														</svg></span><span class="mm-input-number-control" :class="{
															'mm-input-number-control--disabled':
																optionForm.max >=
																1800,
														}" @click="
	optionForm.max <
	1800 &&
	optionForm.max++
	"><svg class="mm-icon mm-icon-up" viewBox="0 0 24 24" name="up" xmlns="XMLNS" fill="#606266" style="
																height: 12px;
																width: 12px;
															">
															<path
																d="M20.2 17.5H3.8c-.4 0-.8-.6-.4-1l8-9.8c.3-.3.9-.3 1.2 0l8 9.8c.4.4.1 1-.4 1z">
															</path>
														</svg></span>
												</div>
											</span></span><!----></span></span><!----><!---->
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
					<el-radio-group v-model="optionForm.localRealTimeTime">
						<el-radio :label="1">启用</el-radio>
						<el-radio :label="0">停用</el-radio>
					</el-radio-group>
				</div>
			</div>
			<div class="section-wrapper">
				<span class="label">邮件发送提醒:</span>
				<div class="mm-radio-group section-list">
					<el-radio-group v-model="optionForm.emailRemindersFlag">
						<el-radio :label="1">弹窗提醒</el-radio>
						<el-radio :label="2">不提醒</el-radio>
					</el-radio-group>
				</div>
			</div>
			<div class="section-title">邮件翻译</div>
			<div class="section-wrapper">
				<span class="label">邮件翻译功能:</span>
				<div class="mm-radio-group radio-list">
					<el-radio-group v-model="optionForm.emailTranslationFunctionFlag">
						<el-radio :label="1">启用</el-radio>
						<el-radio :label="0">停用</el-radio>
					</el-radio-group>
				</div>
			</div>
			<div class="section-title">待处理邮件</div>
			<div class="section-wrapper">
				<span class="label">待处理关闭:</span>
				<div class="mm-radio-group radio-list">
					<el-radio-group v-model="optionForm.pendingClose">
						<el-radio :label="1">回复邮件后自动关闭待处理状态</el-radio>
						<el-radio :label="2">手动关闭待处理状态</el-radio>
					</el-radio-group>
				</div>
			</div>
			<div class="section-wrapper">
				<span class="label">自动化待处理:</span>
				<div class="section-innner">
					<div class="mm-radio-group radio-list">
						<el-radio-group v-model="optionForm.automationPending">
							<el-radio :label="1">启用</el-radio>
							<el-radio :label="0">停用</el-radio>
						</el-radio-group>
					</div>
					<!----><!---->
				</div>
			</div>
			<div class="section-title">异常邮箱检测</div>
			<div class="section-wrapper">
				<span class="label">异常邮箱检测:</span>
				<div class="mm-radio-group radio-list">
					<el-radio-group v-model="optionForm.abnormalMailboxDetection">
						<el-radio :label="1">启用</el-radio>
						<el-radio :label="0">停用</el-radio>
					</el-radio-group>
				</div>
			</div>
		</div>
		<div class="btn-bottom">
			<div class="mm-space mm-space__horizontal">
				<div class="mm-space-item" style="margin-right: 16px">
					<button type="button" class="mm-button" @click="onCancel">
						<!----><!----><span>取消</span><!---->
					</button>
				</div>
				<div class="mm-space-item">
					<button type="button" class="mm-button mm-button__primary" @click="onSave">
						<!----><!----><span>保存</span><!---->
					</button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { otherConfigInfo, otherConfigEdit } from '@/api/email/otherConfig'
export default {
	data() {
		return {
			optionForm: {
				min: 30,
				max: 1800,
				delayedMailDelivery: 0,
				localRealTimeTime: 0,
				emailRemindersFlag: 0,
				emailTranslationFunctionFlag: 0,
				pendingClose: 0,
				automationPending: 0,
				abnormalMailboxDetection: 0,
			},
		};
	},
	mounted() {
		this.getInfo()
	},
	methods: {
		async getInfo() {
			try {
				const res = await otherConfigInfo()
				if (res.code === 200) {
					const data = res.data
					const sendingInterval = data?.sendingInterval?.split('~')
					this.optionForm = {
						min: sendingInterval?.[0] || 30,
						max: sendingInterval?.[1] || 1800,
						delayedMailDelivery: data.delayedMailDelivery,
						localRealTimeTime: data.localRealTimeTime,
						emailRemindersFlag: data.emailRemindersFlag,
						emailTranslationFunctionFlag: data.emailTranslationFunctionFlag,
						pendingClose: data.pendingClose,
						automationPending: data.automationPending,
						abnormalMailboxDetection: data.abnormalMailboxDetection,
					}
				}
			} catch { }
		},
		async editOtherConfig(data) {
			try {
				const res = await otherConfigEdit(data)
				if (res.code === 200) {
					this.$message.success('保存成功')
				}
			} catch {

			}
		},
		onSave() {
			const {
				min,
				max,
				delayedMailDelivery,
				localRealTimeTime,
				emailRemindersFlag,
				emailTranslationFunctionFlag,
				pendingClose,
				automationPending,
				abnormalMailboxDetection
			} = this.optionForm
			const data = {
				sendingInterval: `${min}~${max}`,
				delayedMailDelivery,
				localRealTimeTime,
				emailRemindersFlag,
				emailTranslationFunctionFlag,
				pendingClose,
				automationPending,
				abnormalMailboxDetection
			}
			this.editOtherConfig(data)
		},
		onCancel() {
			this.$emit('changeTab', '常规');
		},
	},
};
</script>

<style lang="scss" scoped>
@import url('./css/index.css');
</style>
