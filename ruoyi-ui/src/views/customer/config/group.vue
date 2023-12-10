<template>
  <div class="page-origin">
    <div class="my-14 flex-middle space-between">
      <div class="fs-14 gray-text">一级分组: {{ totalGroup }}个</div>
    </div>
    <div class="table-card">
      <TableNext
          v-loading="tableLoading"
          :list="originList"
          :columns="originColumns"
          :extra-option="{
          height:'80vh',
          rowKey:'id',
          defaultExpandAll:isExpandAll,
          treeProps:{
            children: 'children',
            hasChildren: 'hasChildren'
          }
         }"/>
    </div>

  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import DelPopover from "@/components/DevPopover/index.vue"
import {EmptyStr} from "@/utils/tools";
import {getSettingsPacket} from "@/api/customer/config";

export default {
  components: {
    TableNext,
    DelPopover
  },
  data() {
    return {
      originList: [],
      originColumns: [
        {
          label: '分组名称',
          field: 'name',
          align: 'left',
          render: (row, field) => EmptyStr(field),
        },
        {
          label: '操作',
          field: 'operate',
          width: '200',
          align: 'right',
          fixed: 'right',
          render: (_row) => {
            return (
                <el-tooltip content="点击隐藏">
                  <i class="pointer el-icon-view"></i>
                </el-tooltip>
            );
          },
        },
      ],
      // 是否展开，默认全部折叠
      isExpandAll: false,
      editStatus: false,
      tableLoading: false,
    }
  },
  computed: {
    totalGroup() {
      return this.generateCount(this.originList)
    },
  },
  mounted() {
    this.getList()
  },
  methods: {
    /** 获取数据 **/
    async getList() {
      try {
        this.tableLoading = true;
        const res = await getSettingsPacket().finally(() => {
          this.tableLoading = false
        })
        if (res.code === 200) {
          this.originList = res.data
        }
      } catch {
      }

    },
    generateCount(arr){
      let count = 0
      const deepSearch = (arr) => {
        arr?.forEach(val => {
          if (val.id) {
            count = count + 1
          }
          if (val.children && val.children.length) {
            deepSearch(val.children)
          }
        })
      }
      deepSearch(arr)
      return count
    },
  }
}
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
