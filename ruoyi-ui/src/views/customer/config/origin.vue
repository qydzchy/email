<template>
	<div class="page-origin">
		<div class="my-14 flex-middle space-between">
			<div class="fs-14">* 应用于：线索来源、客户来源、商机来源</div>
		</div>
		<div class="table-card">
			<TableNext
				v-loading="tableLoading"
				:list="originList"
				:columns="originColumns"
				:extra-option="{
					height: '80vh',
					rowKey: 'menuId',
					defaultExpandAll: isExpandAll,
					treeProps: {
						children: 'children',
						hasChildren: 'hasChildren',
					},
				}" />
		</div>
	</div>
</template>

<script>
	import TableNext from '@/components/TableNext/index.vue';
	import DelPopover from '@/components/DevPopover/index.vue';
	import { EmptyStr } from '@/utils/tools';
	import { getCustomerSourceList } from '@/api/customer/config';

	export default {
		components: {
			TableNext,
			DelPopover,
		},
		data() {
			return {
				originList: [],
				originColumns: [
					{
						label: '分组名称',
						field: 'name',
						align: 'left',
						render: (_row, field) => EmptyStr(field),
					},
					{
						label: '操作',
						field: 'operate',
						width: '200',
						align: 'right',
						fixed: 'right',
						render: (row) => {
							const visible = !+row?.visible;
							return (
								<div>
									<el-tooltip content="点击隐藏">
										<i class='el-icon-view pointer'></i>
									</el-tooltip>
								</div>
							);
						},
					},
				],
				// 是否展开，默认全部折叠
				isExpandAll: false,
				editStatus: false,
				tableLoading: false,
			};
		},
		mounted() {
			this.getList();
		},
		methods: {
			/** 获取数据 **/
			async getList() {
				this.tableLoading = true;
				try {
					const res = await getCustomerSourceList().finally(() => {
						this.tableLoading = false;
					});
					if (res.code === 200) {
						this.originList = res.data;
					}
				} catch {}
			},
		},
	};
</script>

<style lang="scss" scoped>
	.page-origin {
		::v-deep .el-table__cell > .cell {
			display: flex;
			align-items: center;
		}
	}

	.table-card {
		padding-right: 20px;
	}
</style>
