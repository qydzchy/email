<template>
  <div class="mb-20">
    <div class="space-between">
      <div class="flex-middle">
        <span class="fs-13 bold">公海分组</span>
        <span
            class="fs-13 ml-10 gray-text">可以通过公海分组来隔离公司的客户资源；公海分组中的客户被移入公海后，仅对公海分组的成员可见</span>
      </div>
      <div class="mr-10">
        <el-button class="add-rule-btn" round size="mini" @click="poolGroupDialog = true">新增分组</el-button>
      </div>
    </div>
    <div class="mt-10">
      <TableNext :list="poolGroupByList" :columns="poolGroupByColumns" :extra-option="{height:'260'}"
      />
    </div>
    <el-dialog
        :visible.sync="poolGroupDialog"
        width="400px"
        style="margin-top: 25vh"
        :title="poolGroupDialogTitle"
        destroy-on-close
    >
      <el-form :model="poolGroupFrom">
        <el-form-item label="分组名称" props="name">
          <el-input :disabled="poolGroupFrom.isView" v-model="poolGroupFrom.name"
                    placeholder="请输入分组名称"></el-input>
        </el-form-item>
        <el-form-item label="分组成员" props="userIds">
          <div class="form-item">
            <TreeSelectNext
                v-if="!poolGroupFrom.isView"
                :default-props="defaultProps"
                :tree-data="memberOption"
                :echo-data.sync="poolGroupFrom.userIds"
            />

            <div class="flex-middle" v-else>
              <el-tooltip :content="formatMemberList">
                <div style="width: auto">
                  {{ formatMemberList }}
                </div>
              </el-tooltip>
            </div>
          </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button round @click="onCancel">取 消</el-button>
        <el-button type="primary" round @click="onConfirm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import DelPopover from "@/views/company/customer/DelPopover.vue";
import {EmptyStr} from "@/utils/tools";
import {treeList} from "@/mock";
import {groupsAdd, groupsDelete, groupsEdit, groupsList} from "@/api/company/poolRule";
import TreeSelectNext from "@/components/TreeSelectNext/index.vue";
import {listDeptUsersTree} from "@/api/system/dept";

const initPoolGroupFrom = {
  name: '',
  userIds: [],
  isView: false
}
export default {
  components: {TreeSelectNext, TableNext, DelPopover},
  data() {
    return {
      poolGroupByList: [],
      poolGroupByColumns: [
        {
          label: '名称',
          field: 'name',
          align: 'left',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '成员',
          field: 'userInfoList',
          align: 'left',
          render: (_row, field) => {
            if (!field?.length) {
              return '---'
            }
            const imgList = field.map((val, index) => {
              if (index > 3) {
                return null
              }
              const name = val?.nickName || ''

              return <el-tooltip disabled={!name} content={name}>
                <el-avatar class="el-icon-user-solid icon-40 radius-20 mx-4"></el-avatar>
              </el-tooltip>
            })
            return <div class="flex-middle">
              {imgList}
              {field.length > 4 ? <span class="ml-6 fs-12 gray-text">共计{field.length}位</span> : null}
            </div>
          },
        },
        {
          label: '操作',
          field: 'operate',
          width: '120',
          render: (row) => {
            return (
                <div>
                  {
                    row?.default ?
                        <div class="flex-middle flex-center">
                          <el-button type="text" onClick={() => this.onView(row)}>查看</el-button>
                          <el-tooltip placement="top">
                            <div slot="content">
                              <div class="fs-14 lineH-24">
                                默认公海分组不支持编辑
                              </div>
                            </div>
                            <i class="el-icon-warning-outline ml-6"></i>
                          </el-tooltip>
                        </div>
                        : <el-row>
                          <el-button type='text' onClick={() => this.onEdit(row)}>
                            编辑
                          </el-button>
                          <DelPopover id={row?.id} on={{onDelete: (id) => this.onDelete(id)}}/>
                        </el-row>
                  }
                </div>

            );
          },
        },
      ],
      poolGroupFrom: {...initPoolGroupFrom},
      poolGroupDialog: false,
      poolGroupDialogTitle: '新建公海分组',
      emptyOption: [],
      data: treeList,
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      memberOption: [],
      tableLoading: false,
      btnLoading: false,
    }
  }
  ,
  computed: {
    formatMemberList() {
      if (!this.poolGroupFrom.memberList.length) {
        return ''
      }
      let arr = []
      this.poolGroupFrom.memberList.map(val => {
        arr.push(val.name)
      })
      return arr.join(',')
    }
  }
  ,
  mounted() {
    this.getList()
    this.getCommonTree()
  },
  methods: {
    async getList() {
      this.tableLoading = true
      try {
        const res = await groupsList().finally(() => {
          this.tableLoading = false
        })
        if (res.code === 200) {
          let disabledList = []
          this.poolGroupByList = res.data
          this.poolGroupByList.forEach(val => {
            disabledList.push(val.userId)
          })
          this.disabledList = disabledList
        }
      } catch {
        this.tableLoading = false
      }
    },
    async getCommonTree() {
      try {
        const res = await listDeptUsersTree()
        if (res.code === 200) {
          this.memberOption = res.data
        }
      } catch {
      }
    },
    onView(row) {
      this.poolGroupFrom = {
        name: row.name,
        memberList: row.member,
        isView: true
      }
      this.poolGroupDialogTitle = '查看公海分组'
      this.poolGroupDialog = true
    },
    onEdit(row) {
      console.log(row)
      this.poolGroupFrom = {
        id: row?.id,
        name: row?.name,
        userIds: row?.userInfoList?.map(val => val?.name) || [],
        isView: false
      }
      this.poolGroupDialogTitle = '编辑公海分组'
      this.poolGroupDialog = true
    },
    async onDelete(id) {
      try {
        const res = await groupsDelete({id})
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
    // 新增分组
    async groupsAddReq(row) {
      try {
        const res = await groupsAdd({
          name: row?.name,
          userIds: row?.userIds.join(',')
        }).finally(() => {
          this.btnLoading = false
        })
        if (res.code === 200) {
          this.$message({
            type: 'success',
            message: '添加成功'
          })
          await this.getList()
          this.onCancel()
        }
      } catch {
      }
    },
    // 编辑分组
    async groupsEditReq(row) {
      try {
        const res = await groupsEdit({
          id: row?.id,
          name: row?.name,
          userIds: row?.userIds.join(',')
        }).finally(() => {
          this.btnLoading = false
        })
        if (res.code === 200) {
          this.$message({
            type: 'success',
            message: '修改成功'
          })
          await this.getList()
          this.onCancel()
        }
      } catch {
      }
    },
    onConfirm() {
      this.btnLoading = true
      const formData = this.poolGroupFrom
      if (!formData.id) {
        this.groupsAddReq(formData)
      } else {
        this.groupsEditReq(formData)
      }
    },
    onCancel() {
      console.log(this.poolGroupFrom)
      this.poolGroupDialogTitle = '添加公海分组'
      this.poolGroupFrom = initPoolGroupFrom
      this.poolGroupDialog = false
    },
  }
}
</script>

<style lang="scss">
.select-tree {
  .el-select-dropdown__item {
    padding: 0 !important;
    overflow-y: auto;
  }
}
</style>
