<template>
  <div class="mb-20">
    <div class="space-between">
      <div class="flex-middle">
        <span class="fs-13 bold">移入公海原因</span>
      </div>
      <div class="mr-10">
        <el-button class="add-rule-btn" round size="mini">添加原因</el-button>
      </div>
    </div>
    <div class="mt-10">
      <ElTableDraggable handle=".el-icon-s-grid">
        <el-table row-key="customer-status" :data="poolReasonList">
          <el-table-column width="40">
            <i class="el-icon-s-grid" style="cursor: grab"/>
          </el-table-column>
          <el-table-column prop="reason" label="原因" align="left">

          </el-table-column>
          <el-table-column label="操作" width="200" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <DelPopover/>
            </template>
          </el-table-column>
        </el-table>
      </ElTableDraggable>

    </div>
  </div>
</template>

<script>
import ElTableDraggable from "el-table-draggable";
import {EmptyStr} from "@/utils/tools";
import DelPopover from "@/views/company/customer/DelPopover.vue";


export default {
  components: {DelPopover, ElTableDraggable},
  data() {
    return {
      customerEmpty: <div>暂无数据</div>,
      poolReasonList: [
        {id: 1, reason: '123'},
        {id: 2, reason: '12345'},
        {id: 3, reason: '123456'},
      ],
    }
  }

}
</script>

<style lang="scss">

</style>
