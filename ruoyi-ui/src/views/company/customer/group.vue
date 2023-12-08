<template>
  <div class="pool-group">
    <div class="info-tip flex-middle">
      <div><i class="el-icon-warning mr-6" style="color: #1c76fd"></i></div>
      <div class="fs-12 wrap" style="color: #666666">
        <p>对客户资源进行分层分级管理，可迅速区分客户类型，针对性地跟进维系。大部分企业采取以下方式分组，供您参考：</p>
        <p>1、按照客户重要程度：潜在客户\意向客户\初次成交\多次成交\VIP客户</p>
        <p>2、按照客户交易推进程度：潜在客户\营销中客户\意向客户\样品客户\合作客户\复购客户</p>
        <p>3、按照客户地理位置</p>
        <p>4、按照公司产品线</p>
      </div>
    </div>
    <div class="my-16 flex-middle space-between">
      <div class="fs-14">分组管理客户，最多可设置2级子分组。
        一级分组可设置自动移入公海规则，子分组遵循与一级父分组相同的规则。
      </div>
      <el-button type="primary" round @click="groupDialog = true">新建分组</el-button>
    </div>
    <el-table
      lazy
      v-if="refreshTable"
      v-loading="loading"
      :data="menuList"
      row-key="id"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <template #empty>
        <el-empty :imageSize="100"></el-empty>
      </template>
      <el-table-column prop="name" label="客户分组" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column label="操作" width="200" align="center" fixed="right">
        <template v-slot="scope">
          <el-row>
            <el-button size="mini" type="text" @click="addGroupTable(scope.row)" v-if="scope.row.level!==3">
              添加子级分组
            </el-button>
            <el-button v-else style="visibility: hidden" size="mini" type="text">添加子级分组</el-button>
            <el-button size="mini" type="text" @click="editGroupTable(scope.row)">
              编辑
            </el-button>
            <DelPopover :id="scope.row.id" width="200" :content="`确定要删除分组【${scope.row.name}】吗？`"
                        @onDelete="onDelete"/>
          </el-row>
        </template>
      </el-table-column>
    </el-table>
    <div class="flex-middle mt-10">
      <el-checkbox v-model="checkChildren" @change="editSettings">子分组必选</el-checkbox>
      <el-tooltip placement="top">
        <div slot="content" class="group-tooltip">
          <p>勾选“子分组必选”后，则不允许只选择填入父级分组。</p>
          <p></p>
          <p>如：分组A，其下有子级分组a1，则必须选择到a1才允许保存</p>
        </div>
        <i class="el-icon-warning-outline ml-6"></i>
      </el-tooltip>
    </div>
    <!-- dialog -->
    <el-dialog :title="groupDialogTitle" width="460px" :visible.sync="groupDialog"
               destroy-on-close @close="onCancel">
      <el-form :model="groupDialogForm" class="group-dialog-form">
        <el-form-item label="父级分组" prop="parentName">
          <div class="parent-group">
            {{ groupDialogForm.parentName }}
          </div>

        </el-form-item>
        <el-form-item label="分组名称" prop="name">
          <el-input v-model="groupDialogForm.name" autocomplete="off" placeholder="请输入分组名称"></el-input>
        </el-form-item>
        <el-form-item prop="designatedMember">
          <div class="flex-column">
            <div>
              <span class="bold black-color">可用成员</span>
              <el-tooltip placement="top">
                <div slot="content">
                  <div class="pool-rule-tooltip fs-14 lineH-24" style="width:300px">
                    分组仍然对所有成员可见，但在客户新建、编辑场景中，仅可用成员可保存当前分组作为客户分组的值。
                  </div>
                </div>
                <i class="el-icon-warning-outline ml-6"></i>
              </el-tooltip>
            </div>
            <el-radio-group class="flex-column gap-10 mb-10" v-model="groupDialogForm.availableMember" @change="handleMemberType">
              <el-radio :label="1">全部成员</el-radio>
              <el-radio :label="2">指定成员</el-radio>
            </el-radio-group>
            <el-select-tree
              style="width: 100%"
              v-model="groupDialogForm.designatedMember"
              multiple
              clearable
              filterable
              show-checkbox
              collapse-tags
              :data="memberOption"
              :props="defaultProps"
              :disabled="groupDialogForm.availableMember === 1"
              :default-expand-all="true"/>
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
import DelPopover from "./DelPopover.vue";
import {mapState} from "vuex";
import {packetAdd, packetDelete, packetEdit, packetList} from "@/api/company/group";
import {listDeptUsersTree} from "@/api/system/dept";

const initGroupForm = {
  id: '',
  name: '',
  parentId: -1,
  parentName: '客户分组',
  availableMember: 1,
  designatedMember: []
}

export default {
  dicts: ['sys_show_hide', 'sys_normal_disable'],
  components: {
    DelPopover
  },
  data() {
    return {
      loading: false, // 遮罩层
      refreshTable: true, // 重新渲染表格状态
      tempMenuList: [],
      menuList: [], // 菜单表格树数据
      isExpandAll: false, // 是否展开，默认全部折叠
      checkChildren: true, // 选择子分组
      visible: false, // 选择删除提示
      groupDialog: false, //
      groupDialogTitle: '新建客户分组',
      groupDialogForm: {...initGroupForm},
      defaultProps: {
        children: 'children',
        label: 'name',
        value: 'id'
      },
      memberOption: [],
      tempAllDept: [],
      tempAllUser: [],
    }
  },
  mounted() {
    this.getList()
    this.getCommonTree()
    this.$watch('settings', (newVal) => {
      const {mandatorySubgroupFlag} = newVal
      this.checkChildren = Boolean(mandatorySubgroupFlag)
    }, {immediate: true})
  },
  computed: {
    ...mapState({
      settings: state => state.company.settings
    }),
  },
  methods: {
    /** 获取数据 **/
    async getList() {
      this.loading = true;
      try {
        const res = await packetList().finally(() => {
          this.loading = false
        })
        if (res.code === 200) {
          this.menuList = this.generateLevelList(res.data)
        }
      } catch {
      }
    },
    async getCommonTree() {
      try {
        const res = await listDeptUsersTree()
        if (res.code === 200) {
          this.memberOption = this.generateMemberOption(res.data)
        }
      } catch {
      }
    },
    // 表格添加组
    addGroupTable(row) {
      const searchName = this.generateSearchParent(this.menuList, row?.parentId)
      const parentName = row?.parentId !== -1 ? searchName : '客户分组'
      this.groupDialogForm = {
        ...this.groupDialogForm,
        parentId: row?.id,
        parentName: parentName
      }
      this.groupDialog = true
    },
    editGroupTable(row) {
      this.groupDialogTitle = '编辑客户分组'
      const searchName = this.generateSearchParent(this.menuList, row?.parentId)
      const parentName = row?.parentId !== -1 ? searchName : '客户分组'
      const designatedMember = row?.designatedMember || null
      this.groupDialogForm = {
        ...this.groupDialogForm,
        ...row,
        parentName: parentName,
        designatedMember: this.generateMemberChoose(designatedMember)
      }
      this.groupDialog = true
    },
    async onDelete(id) {
      try {
        const res = await packetDelete({id})
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
    async packetAddReq(row) {
      try {
        const {name, parentId, parentName, availableMember, designatedMember} = row
        const res = await packetAdd({
          name,
          parentId,
          parentName,
          availableMember,
          designatedMember: availableMember === 2 ? designatedMember : "",
        })
        if (res.code === 200) {
          this.$message({
            type: 'success',
            message: '新增成功'
          })
          await this.getList()
          this.onCancel()
        }
      } catch {
      }
    },
    async packetEditReq(row) {
      try {
        const {id, name, parentId, parentName, availableMember, designatedMember} = row
        const res = await packetEdit({
          id,
          name,
          parentId,
          parentName,
          availableMember,
          designatedMember: availableMember === 2 ? designatedMember : "",
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
    // 确认
    onConfirm() {
      if (!this.groupDialogForm.name) {
        this.$message({
          type: 'error',
          message: '请填写分组名'
        })
        return
      }
      const formData = {
        ...this.groupDialogForm,

      }
      if (!formData.id) {
        this.packetAddReq(formData)
      } else {
        this.packetEditReq(formData)
      }
    },

    // 取消
    onCancel() {
      this.groupDialogTitle = '新建客户分组'
      this.groupDialogForm = {...initGroupForm}
      this.groupDialog = false
    },

    editSettings() {
      this.$store.dispatch('company/EditCompanyCustomerSettings', {
        ...this.settings,
        mandatorySubgroupFlag: +this.checkChildren
      }).then(res => {
        if (res) {
          this.$message({
            type: 'success',
            message: '修改成功'
          })
        }
      })
    },
    handleMemberType(value){
      if(value===1){
        this.groupDialogForm.designatedMember = []
      }
    },
    generateLevelList(list) {

      const deepLevel = (arr, count = 0) => {
        count++
        return arr.map(val => {
          val.level = count
          const child = val.children
          if (child && child.length) {
            deepLevel(child, count)
          }
          return val
        })

      }
      return deepLevel(list)
    },
    generateSearchParent(list, parentId) {
      let parentName = ''
      const searchParent = (arr, id) => {
        return arr.forEach(val => {
          if (val.id === id) {
            parentName = val.name
          }
          val?.children && searchParent(val.children, id)
        })
      }
      searchParent(list, parentId)
      return parentName
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

<style lang="scss" scoped>
.info-tip {
  margin: 0;
  padding: 8px 15px;
  border: 0;
  border-radius: 4px;
  background-color: #ebf3ff;
  box-sizing: border-box;
  font-feature-settings: "tnum";
  overflow-wrap: break-word;
  align-items: center;
  color: #333;
  display: flex;
  font-size: 14px;
  font-variant: tabular-nums;
  list-style: none;
  position: relative;

  .wrap {
    line-height: 20px;
  }

  .wrap > p {
    margin-block-start: 0;
    margin-block-end: 0;
  }
}

.group-dialog-form {
  .black-color {
    color: #606266
  }

  .parent-group {
    width: 100%;
    display: inline-block;
  }

  .group-tip {
    width: 300px;
  }
}


.group-tooltip {
  width: 200px;
}


::v-deep .el-dialog {
  border-radius: 14px;
}

::v-deep .el-dialog__header {
  border-bottom: 1px solid #dadada;
}

.pool-group {
  .select-tree {
    .el-select-dropdown__item {
      padding: 0 !important;
      overflow-y: auto;
    }
  }
}

</style>
