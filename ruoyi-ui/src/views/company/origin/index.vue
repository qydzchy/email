<template>
  <div class="page-origin">
    <div class="bold fs-26 pb-4">
      来源设置
    </div>
    <div class="my-14 flex-middle space-between">
      <div class="fs-14 gray-text">* 应用于：线索来源、客户来源、商机来源</div>
      <el-button class="mr-20" type="primary" round size="medium" :disabled="editStatus" @click="addOrigin">添加来源
      </el-button>
    </div>
    <div class="table-card">
      <TableNext
          v-loading="tableLoading"
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
import DelPopover from "@/components/DevPopover/index.vue"
import {EmptyStr} from "@/utils/tools";
import {listMenu} from "@/api/system/menu";
import {debounce} from "@/utils";
import {reasonAdd, reasonEdit} from "@/api/company/poolRule";
import {getOriginList, originAdd, originDelete, originEdit} from "@/api/company/origin";

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
          label: '来源名称',
          field: 'name',
          align: 'left',
          render: (row, field) => {

            return <div style="width:100%">
              <span style={{display: row?.isEdit ? 'none' : 'block'}}>{field}</span>
              <el-input
                  value={field}
                  style={{display: row?.isEdit ? 'block' : 'none'}}
                  onInput={(value) => this.handleOriginNameInput(row, value)}
                  nativeOnKeydown={(e) => this.inputKeydown(e, row)}
                  placeholder="请输入原因">
              </el-input>
            </div>
          },
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
                    <div>
                      <el-row style={{display: row?.isEdit ? 'none' : 'block'}}>
                        <el-button type='text' onClick={() => this.onEdit(row?.id)}>
                          编辑
                        </el-button>
                        <DelPopover id={row?.id} on={{onDelete: (id) => this.originDeleteReq(id)}}/>
                      </el-row>
                      <el-row style={{display: row?.isEdit ? 'block' : 'none'}}>
                        <el-button type="text" onClick={() => this.onCancelInput(row?.id)}>取消</el-button>
                        <el-button type="text" onClick={() => this.onSaveInput(row)}>保存</el-button>
                      </el-row>
                    </div>
                    :
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
      editStatus: false,
      tableLoading: false,
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    /** 获取数据 **/
    async getList() {
      this.tableLoading = true;
      try {
        const res = await getOriginList().finally(() => {
          this.tableLoading = false;
        })
        if (res.code === 200) {
          this.originList = res.data
        }
      } catch {
      }
      //
      // listMenu({}).then(response => {
      //   this.originList = this.handleTree(response.data, "menuId");
      //   console.log(this.originList)
      //   this.tableLoading = false;
      // });
    },
    addOrigin() {
      this.originList.unshift({id: -1, name: '', isEdit: true})
      this.editStatus = true
    },
    onEdit(id) {
      const tableIndex = this.originList.findIndex(val => val.id === id)
      this.$set(this.originList, tableIndex, {...this.originList[tableIndex], isEdit: true})
      this.editStatus = true
    },
    onCancelInput(id) {
      if (id === -1) {
        this.originList.shift()
      } else {
        this.originList.map(val => {
          if (val.id === id) {
            val.isEdit = false
          }
          return val
        })
      }

      this.editStatus = false
    },
    async originDeleteReq(id) {
      try {
        const res = await originDelete({id})
        if (res.code === 200) {
          this.$message({
            type: 'success',
            message: '删除成功'
          })
          await this.getList()
        }
      } catch {
      }
    },
    originAddReq(row) {
      return new Promise(async (resolve) => {
        try {
          const res = await originAdd({
            name: row?.name
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
    originEditReq(row) {
      return new Promise(async (resolve) => {
        try {
          const res = await originEdit({
            name: row?.name,
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
      console.log(!item.name)
      if (!item.name) {
        this.$message({
          type: 'error',
          message: '添加失败,原因不能为空'
        })
        this.tableLoading = false
        return
      }

      if (item?.id === -1) {
        isSet = await this.originAddReq(item)
      } else {
        isSet = await this.originEditReq(item)
      }

      if (isSet) {
        const tableIndex = this.originList.findIndex(val => val.id === item?.id)
        this.$set(this.originList, tableIndex, {...item, isEdit: false})
        this.editStatus = false
      }
    },
    handleOriginNameInput(item, value) {
      const tableIndex = this.originList.findIndex(val => val.id === item?.id)
      this.$set(this.originList, tableIndex, {...item, name: value})
    },
    inputKeydown(e, row) {
      // 回车输入
      if (e.keyCode === 13) {
        this.onSaveInput(row)
      }
    }
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
