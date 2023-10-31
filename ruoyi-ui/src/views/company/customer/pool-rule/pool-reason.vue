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
          <template slot="empty">
            <el-empty :imageSize="100"></el-empty>
          </template>
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
                  @keydown.enter.native="onSaveInput(scope.row)"
                  placeholder="请输入原因">
              </el-input>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="200" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button v-show="!scope.row.isEdit" type="text" @click="onEdit(scope.row.id)">编辑</el-button>
              <DelPopover v-show="!scope.row.isEdit" :id="scope.row.id" @onDelete="onDelete"/>
              <el-row v-show="scope.row.isEdit">
                <el-button type="text" @click="onCancelInput(scope.row.id)">取消</el-button>
                <el-button type="text" @click="onSaveInput(scope.row)">保存</el-button>
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
import {reasonAdd, reasonDelete, reasonEdit, reasonList} from "@/api/company/poolRule";


export default {
  components: {DelPopover, ElTableDraggable},
  data() {
    return {
      poolReasonList: [],
      editStatus: false,
      tableLoading: false,
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    async getList() {
      this.tableLoading = true
      try {
        const res = await reasonList().finally(() => {
          this.tableLoading = false
        })
        if (res.code === 200) {
          this.poolReasonList = res.data
        }
      } catch {

      }
    },
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
    reasonAddReq(row) {
      return new Promise(async (resolve) => {
        try {
          if(!row.reason){
            this.$message({
              type: 'error',
              message: '添加失败,原因不能为空'
            })
            resolve(false)
            return
          }
          const res = await reasonAdd({
            reason: row?.reason
          }).finally(() => {
            this.tableLoading = false
          })
          if (res.code === 200) {
            this.$message({
              type: 'success',
              message: '添加成功'
            })
            resolve(true)
            return
          }
          resolve(false)
        } catch {
          resolve(false)
        }
      })

    },
    reasonEditReq(row) {
      return new Promise(async (resolve) => {
        try {
          const res = await reasonEdit({
            reason: row?.reason,
            id: row?.id
          }).finally(() => {
            this.tableLoading = false
          })
          if (res.code === 200) {
            this.$message({
              type: 'success',
              message: '编辑成功'
            })
            resolve(true)
            return
          }
          resolve(false)
        } catch {
          resolve(false)
        }
      })

    },
    async onSaveInput(item) {
      let isSet = false
      this.tableLoading = true

      if (item?.id === -1) {
        isSet = await this.reasonAddReq(item)
      } else {
        isSet = await this.reasonEditReq(item)
      }

      if (isSet) {
        const tableIndex = this.poolReasonList.findIndex(val => val.id === item?.id)
        this.$set(this.poolReasonList, tableIndex, {...item, isEdit: false})
        this.editStatus = false
      }
    },
    async onDelete(id) {
      try {
        const res = await reasonDelete({id})
        if (res.code === 200) {
          this.$message({
            type: 'success',
            message: '删除成功'
          })
          await this.getList()
        }
      } catch {
      }
    }
  }

}
</script>

<style lang="scss">

</style>
