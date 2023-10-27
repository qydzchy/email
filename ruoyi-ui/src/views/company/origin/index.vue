<template>
  <div class="page-origin">
    <div class="bold fs-30 pb-4">
      来源设置
    </div>
    <div class="table-card">
      <TableNext
        :list="originList"
        :columns="originColumns"
        :extra-option="{
          height:'80vh',
          rowKey:'menuId',
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
import {EmptyStr} from "@/utils/tools";
import {listMenu} from "@/api/system/menu";

export default {
  components: {
    TableNext
  },
  data() {
    return {
      originList: [],
      originColumns: [
        {
          label: '成员',
          field: 'menuName',
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
            const visible = !(+row?.visible)
            return (
              visible ?
                <el-row>
                  <el-button type='text'>
                    编辑
                  </el-button>
                  <el-button type='text'>
                    删除
                  </el-button>
                </el-row> :
                <div>
                  <el-tooltip placement="top" content="不可编辑和删除">
                    <i class="el-icon-lock gray-text"></i>
                  </el-tooltip>
                </div>
            );
          },
        },
      ],
      // 是否展开，默认全部折叠
      isExpandAll: false,
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    /** 获取数据 **/
    getList() {
      this.loading = true;
      listMenu({}).then(response => {
        this.originList = this.handleTree(response.data, "menuId");
        this.loading = false;
      });
    },
  }
}
</script>

<style lang="scss" scoped>
.page-origin {
}

.table-card {
  padding-right: 20px;
}
</style>
