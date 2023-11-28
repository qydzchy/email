<template>
	<el-select
		style="width: 100%"
		:value.sync="selectValue"
		size="small"
		v-bind="selectOptions"
		@change="handleChange"
		@visible-change="visibleChange">
		<el-option
			v-for="(opt, index) in selectOptions.options"
			:key="index"
			:label="opt[generateProps.label]"
			:value="opt[generateProps.value]">
			<span v-if="!opt.bgColor">{{
				opt[generateProps.label]
			}}</span>
			<span
				v-else
				class="white-text px-4 py-4 radius-4"
				:style="`background-color:${opt.bgColor}`">
				{{ opt[generateProps.label] }}
			</span>
		</el-option>
	</el-select>
</template>

<script>
	export default {
		props: {
			value: {
				type: String | Number,
				default: '',
				required: true,
			},
			selectOptions: {
				type: Object,
				default: () => {
					return {
						options: [],
						props: {
							label: 'label',
							value: 'value',
						},
					};
				},
				required: false,
			},
		},
		data() {
			return {
				selectValue: '',
			};
		},
		watch: {
			value: {
				handler(newVal) {
					this.selectValue = newVal;
				},
				immediate: true,
			},
		},
		computed: {
			generateProps() {
				return {
					label: this.selectOptions?.props?.label || 'label',
					value: this.selectOptions?.props?.value || 'value',
				};
			},
		},
		methods: {
			handleChange(value) {
				this.$emit('update:value', value);
			},
			visibleChange(bool) {
				this.$emit('visibleChange', bool);
			},
		},
	};
</script>

<style lang="scss" scoped></style>
