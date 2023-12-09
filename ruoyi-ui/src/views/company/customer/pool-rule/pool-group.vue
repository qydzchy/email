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
        :close-on-click-modal="false"
        :close-on-press-escape="false"
        destroy-on-close
    >
      <el-form ref="poolGroupRef" :model="poolGroupFrom" :rules="poolGroupFromRule">
        <el-form-item label="分组名称" prop="name">
          <el-input :disabled="poolGroupFrom.isView" v-model="poolGroupFrom.name"
                    placeholder="请输入分组名称"></el-input>
        </el-form-item>
        <el-form-item label="分组成员" prop="groupMember">
          <div class="form-item">
            <el-select-tree
              v-if="!poolGroupFrom.isView"
              style="width: 100%"
              v-model="poolGroupFrom.groupMember"
              multiple
              clearable
              filterable
              show-checkbox
              collapse-tags
              :data="memberOption"
              :props="defaultProps"
              :default-expand-all="true"/>
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
import {groupsAdd, groupsDelete, groupsEdit, groupsList} from "@/api/company/poolRule";
import {listDeptUsersTree} from "@/api/system/dept";

const initPoolGroupFrom = {
  name: '',
  groupMember: [],
  isView: false
}
export default {
  components: {TableNext, DelPopover},
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
          field: 'groupMember',
          align: 'left',
          render: (_row, field) => {
            if (!field?.length) {
              return '---'
            }
            const imgList = field.map((val, index) => {
              if (index > 3) {
                return null
              }
              
              const name = this.memberNameSearch(val)
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
                    row?.defaultGroupFlag ?
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
      poolGroupFromRule: {
        name: [
          {required: true, message: '请输入分组名称', trigger: 'blur'}
        ],
        groupMember: [
          {required: true, message: '请选择分组成员', trigger: 'change'}
        ],
      },
      poolGroupDialog: false,
      poolGroupDialogTitle: '新建公海分组',
      emptyOption: [],
      defaultProps: {
        children: 'children',
        label: 'name',
        value: 'id'
      },
      memberOption: [],
      tableLoading: false,
      btnLoading: false,
      tempAllDept: [],
      tempAllUser: [],
    }
  },
  computed: {
    formatMemberList() {
      if (!this.poolGroupFrom.groupMember.length) {
        return ''
      }
      let arr = []
      this.poolGroupFrom.groupMember.forEach(val => {
        arr.push(this.memberNameSearch(val))
      })
      return arr.join(',')
    }
  },
  mounted() {
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
          // let disabledList = []
          const data = res.data?.map(val=>{
            val.groupMember =  this.generateMemberChoose(val.groupMember)
            return val
          }) || []
          this.poolGroupByList = data
          // this.poolGroupByList.forEach(val => {
          //   disabledList.push(val.userId)
          // })
          // this.disabledList = disabledList
        }
      } catch {
        this.tableLoading = false
      }
    },
    async getCommonTree() {
      try {
        const res = await listDeptUsersTree().finally(()=>{
          // 请求完成后再请求列表
          this.getList()
        })
        if (res.code === 200) {
          this.memberOption = this.generateMemberOption(res.data)
        }
        
      } catch {
      }
    },
    onView(row) {
      this.poolGroupFrom = {
        name: row.name,
        groupMember: row.groupMember,
        isView: true
      }
      this.poolGroupDialogTitle = '查看公海分组'
      this.poolGroupDialog = true
    },
    onEdit(row) {
      this.poolGroupFrom = {
        id: row?.id,
        name: row?.name,
        groupMember: row?.groupMember,
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
          groupMember: row?.groupMember || ''
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
      } catch(e) {
        console.error(e);
      }
    },
    // 编辑分组
    async groupsEditReq(row) {
      try {
        const res = await groupsEdit({
          id: row?.id,
          name: row?.name,
          groupMember: row?.groupMember || ''
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
      } catch(e) {
        console.error(e);
      }
    },
    onConfirm() {
      this.$refs.poolGroupRef.validate(valid => {
        if (valid) {
          this.btnLoading = true
          const formData = {
            ...this.poolGroupFrom,
            groupMember:this.generateMemberFormat(this.poolGroupFrom.groupMember)
          }
          if (!formData.id) {
            this.groupsAddReq(formData)
          } else {
            this.groupsEditReq(formData)
          }
        }
      })

    },
    onCancel() {
      this.poolGroupDialogTitle = '添加公海分组'
      this.poolGroupFrom = initPoolGroupFrom
      this.poolGroupDialog = false
    },
    memberNameSearch(id){
      let name = ''
      const memberListUnion = [...this.tempAllDept,...this.tempAllUser]
      memberListUnion.forEach(val=>{
        if(val.id===id){
          name = val.name
        }
      })
      return name
    },
    generateMemberChoose(scopeData) {
      let scope = JSON?.parse(scopeData)
      if (!scope) {
        return []
      }
      // 部门
      let dept = []
      if (scope?.dept?.allFlag) {
        this.tempAllDept.forEach(val => {
          dept.push(val.id)
        })
      } else {
        dept = scope?.dept?.deptIds || []
      }
      // 用户
      let user = []
      if (scope?.user?.allFlag) {
        this.tempAllUser.forEach(val => {
          user.push(val.id)
        })
      } else {
        user = scope?.user?.userIds || []
      }
      
      return [...dept, ...user]

    },
    // 格式化成员结构
    generateMemberOption(list) {
      let ids = []
      let allDept = {
        id: 'allDept',
        name: '全部部门',
        children: []
      }
      let allUser = {
        id: 'allUser',
        name: '全部人员',
        children: []
      }
      const deepSearch = arr => arr.forEach(val => {
        ids.push(val.id)
        if (val.type === 1) {
          allDept.children.push({
            id: val.id,
            name: val.name
          })
        } else if (val.type === 2) {
          allUser.children.push({
            id: val.id,
            name: val.name
          })
        }
        if (val.children && val.children.length) {
          deepSearch(val.children)
        }
      })
      deepSearch(list)
      this.tempAllDept = allDept.children
      this.tempAllUser = allUser.children
      return [{
        id: 'all',
        name: '全公司可见',
        children: [allDept, allUser]
      }]
    },
    generateMemberFormat(ids) {
      let templateScope = {
        "dept": { //部门
          "allFlag": true, // true所有部门 false指定部门
          "deptIds": [] //指定部门ID
        },
        "user": { //用户
          "allFlag": true, //true所有用户 false指定用户
          "userIds": [] //指定用户ID
        }
      }
      // 校验勾选部门
      const validDept = this.tempAllDept.every(val => ids.includes(val.id))
      if (validDept) {
        templateScope.dept.allFlag = true
      } else {
        templateScope.dept.allFlag = false
        let deptIds = this.tempAllDept.filter(val => ids.includes(val.id))
        templateScope.dept.deptIds = deptIds.map(val => val.id)
      }
      // 校验勾选用户
      const validUser = this.tempAllUser.every(val => ids.includes(val.id))
      if (validUser) {
        templateScope.user.allFlag = true
      } else {
        templateScope.user.allFlag = false
        let userIds = this.tempAllUser.filter(val => ids.includes(val.id))
        templateScope.user.userIds = userIds.map(val => val.id)
      }
      return JSON.stringify(templateScope)
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
