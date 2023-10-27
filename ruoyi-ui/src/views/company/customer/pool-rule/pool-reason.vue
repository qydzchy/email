<template>
  <div class="mb-20">
    <div class="space-between">
      <div class="flex-middle">
        <span class="fs-13 bold">移入公海原因</span>
      </div>
      <div class="mr-10">
        <el-button class="add-rule-btn" round size="mini" :disabled="editStatus" @click="addReason">添加原因</el-button>
      </div>
    </div>
    <div class="mt-10">
      <ElTableDraggable handle=".el-icon-s-grid">
        <el-table style="height: 400px" row-key="customer-status" v-loading="tableLoading" :data="poolReasonList">
          <el-table-column width="40">
            <i class="el-icon-s-grid" style="cursor: grab"/>
          </el-table-column>
          <el-table-column prop="reason" label="原因" align="left">
            <template v-slot="scope">
              <span v-show="!scope.row.isEdit">{{ scope.row.reason }}</span>
              <el-input
                  v-show="scope.row.isEdit"
                  v-model="scope.row.reason"
                  :ref="'inputReason'+scope.row.$index"
                  placeholder="请输入原因">
              </el-input>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="200" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button v-show="!scope.row.isEdit" type="text" @click="onEdit(scope.row.id)">编辑</el-button>
              <DelPopover v-show="!scope.row.isEdit" :id="scope.row.id"/>
              <el-row v-show="scope.row.isEdit">
                <el-button type="text" @click="onCancelInput(scope.row.id)">取消</el-button>
                <el-button type="text" @click="onSaveInput(scope.row.reason)">保存</el-button>
              </el-row>
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
      editStatus: false,
      tableLoading: false,
    }
  },
  methods: {
    addReason() {
      this.poolReasonList.push({id: -1, reason: '', isEdit: true})
      this.editStatus = true
    },
    onEdit(id) {
      const tableIndex = this.poolReasonList.findIndex(val => val.id === id)
      this.$set(this.poolReasonList, tableIndex, {...this.poolReasonList[tableIndex], isEdit: true})
      this.editStatus = true
    },
    onCancelInput(id) {
      if (id === -1) {
        this.poolReasonList.pop()
      } else {
        this.poolReasonList.map(val => {
          if (val.id === id) {
            val.isEdit = false
          }
          return val
        })
      }

      this.editStatus = false
    },
    onSaveInput(reason) {
      this.tableLoading = true
      setTimeout(() => {
        this.tableLoading = false
        this.editStatus = false
        this.poolReasonList[this.poolReasonList.length - 1].isEdit = false
      }, 2000)
    }
  }

}
</script>

<style lang="scss">

</style>
