<template>
  <div>
    <div class="flex-column">
      <div class="fs-14 my-10">按照业务进展阶段划分客户状态，帮助业务员针对性的跟进客户，提高成单率。</div>
      <div class="mb-10 pr-50 flex-end">
        <el-button type="primary" round size="small">新增阶段</el-button>
      </div>

    </div>

    <ElTableDraggable handle=".el-icon-s-grid">
      <el-table row-key="customer-status" :data="list">
        <el-table-column width="40">
          <i class="el-icon-s-grid" style="cursor: grab"/>
        </el-table-column>
        <el-table-column prop="stageName" label="阶段名称" align="left">
          <template slot-scope="scope">
            {{ scope.row.stageName }}
          </template>
        </el-table-column>
        <el-table-column label="操作" width="200" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button size="mini" type="text">
              编辑
            </el-button>
            <el-button size="mini" type="text">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </ElTableDraggable>
  </div>
</template>

<script>
import ElTableDraggable from "el-table-draggable";

export default {
  components: {
    ElTableDraggable,
  },
  data() {
    return {
      list: [
        {
          id: 1,
          stageName: '1'
        },
        {
          id: 2,
          stageName: '2'
        },
        {
          id: 3,
          stageName: '3'
        },
      ]
    }
  },
  mounted() {
  }
}
</script>

<style lang="scss" scoped>
::v-deep .el-table__row {
  &:focus-visible {
    outline: #0a6aff auto 1px;
  }

}
</style>
