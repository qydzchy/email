<template>
  <div>
    <el-tabs v-model="curTab" type="card" @tab-click="handleTabs" lazy>
      <el-tab-pane v-for="(tab,index) in tabsList" :key="index" :label="tab.label" :name="tab.name" lazy>
        <el-row v-if="curTab===tab.name">
          <div class="flex-middle space-between mb-10">
            <el-row class="flex1 gap-10" type="flex">
              <el-input style="width:240px" size="small" v-model="tagFilterForm.inputTag" placeholder="输入搜索标签">
                <template #append>
                  <el-button icon="el-icon-search"></el-button>
                </template>
              </el-input>
              <SelectNext
                  v-if="curTab==='childTag'"
                  style="width:240px"
                  :value="tagFilterForm.selectDptOrPerson"
                  :select-options="selectOption">
              </SelectNext>
            </el-row>
            <el-button v-if="curTab==='companyTag'" type="primary" round @click="onAdd('company')">添加公司标签
            </el-button>
            <el-button v-if="curTab==='myTag'" type="primary" round @click="onAdd('my')">添加个人标签</el-button>
          </div>

          <TableNext
              :list="list"
              :loading="tableLoading"
              :columns="columnsMap[tab.name]"
              :extra-option="{height:'60vh'}"
              :paginate-option="curTab==='childTag'?paginateOption:{}"/>
        </el-row>
      </el-tab-pane>
    </el-tabs>
    <el-dialog
        :title="dialogTitle" width="460px" style="margin-top: 25vh"
        :visible.sync="tagDialog"
        :close-on-click-modal="false">
      <el-form :model="tagDialogForm" ref="tagFormRef" :rules="tagRules" @submit.native.prevent>
        <el-form-item label="标签名称" prop="name">
          <el-input v-model="tagDialogForm.name" autocomplete="off" placeholder="请输入标签名称"
                    @keydown.enter.native="onConfirm"></el-input>
        </el-form-item>
        <el-form-item label="标签颜色">
          <SelectTagColor :checked-color.sync="tagDialogForm.color" :color-map="colorMap"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button round :loading="btnLoading" @click="onCancel">取 消</el-button>
        <el-button type="primary" round :loading="btnLoading" @click="onConfirm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import SelectNext from "@/components/SelectNext/index.vue";
import SelectTagColor from "@/views/components/SelectTagColor/index.vue";
import DevPopover from "@/components/DevPopover/index.vue";
import {customerConfigTagColorMap} from '@/views/components/SelectTagColor/colorMap'
import {
  addCompanyTag,
  addPersonalTag,
  deleteCompanyTag,
  deletePersonalTag,
  editCompanyTag,
  editPersonalTag,
  getCustomerTagList,
  setCompanyTag
} from "@/api/customer/config";
import {deepClone} from "@/utils";
import {EmptyStr} from "@/utils/tools";

const initDialogForm = {
  id: '',
  name: '',
  color: '#80c463'
}
export default {
  components: {
    SelectTagColor,
    TableNext,
    SelectNext
  },
  data() {
    return {
      curTab: 'companyTag',
      tabsList: [
        {label: '公司标签', name: 'companyTag'},
        {label: '我的标签', name: 'myTag'},
        {label: '下属标签', name: 'childTag'},
      ],
      tabsMap: {
        companyTag: '1',
        myTag: '2',
        childTag: '',
      },
      list: [],
      columnsMap: {
        companyTag: [
          {
            label: '标签名称',
            field: 'name',
            align: 'left',
            render: (row, field) => {
              const colorOpacity = row.color ? row.color + '4d' : ''
              return field ? <span class="px-6 py-6"
                                   style={{color: row.color, backgroundColor: colorOpacity}}>{field}</span> : '---'
            }
          },
          {
            label: '操作',
            field: 'operate',
            width: 200,
            render: (row, _field) => {
              return <el-row>
                <el-button type="text" onClick={() => this.onEdit(row)}>修改</el-button>
                <DevPopover id={row.id} on={{onDelete: (id) => this.onDelete(id)}}/>
              </el-row>
            },
          },
        ],
        myTag: [
          {
            label: '标签名称',
            field: 'name',
            align: 'left',
            render: (row, field) => {
              const colorOpacity = row.color ? row.color + '4d' : ''
              return field ? <span class="px-6 py-6"
                                   style={{color: row.color, backgroundColor: colorOpacity}}>{field}</span> : '---'
            }
          },
          {
            label: '属性',
            field: 'prop',
            align: 'left',
            render: (_row, field) => EmptyStr(field),
          },
          {
            label: '操作',
            field: 'operate',
            width: 200,
            render: (row, _field) => {
              return <el-row>
                <el-button type="text" onClick={() => this.setCompanyTagReq({id: row.id})}>设为公司标签</el-button>
                <el-button type="text" onClick={() => this.onEdit(row)}>修改</el-button>
                <DevPopover id={row.id} on={{onDelete: (id) => this.onDelete(id)}}/>
              </el-row>
            },
          },
        ],
        childTag: [
          {
            label: '标签名称',
            field: 'name',
            align: 'left',
            render: (row, field) => {
              const colorOpacity = row.color ? row.color + '4d' : ''
              return field ? <span class="px-6 py-6"
                                   style={{color: row.color, backgroundColor: colorOpacity}}>{field}</span> : '---'
            }
          },
          {
            label: '创建人',
            field: 'creator',
            width: 200,
            render: (_row, field) => EmptyStr(field),
          },
          {
            label: '操作',
            field: 'operate',
            width: 200,
            render: (row, _field) => {
              return <el-button type="text" onClick={() => this.setCompanyTagReq({id: row.id})}>设为公司标签</el-button>
            },
          },
        ]
      },
      paginateOption: {
        total: 1,
        layout: 'prev, pager, next',
        pageSize: 20,
      },
      tagFilterForm: {
        inputTag: '',
        selectDptOrPerson: ''
      },
      selectOption: {
        placeholder: '选择部门或业务员',
        filterable: true,
        clearable: true
      },
      dialogTitle: '',
      tagDialog: false,
      btnLoading: false,
      tableLoading: false,
      tagDialogForm: {...deepClone(initDialogForm)},
      tagRules: {
        name: [
          {required: true, message: '请输入名称', trigger: 'blur'}
        ]
      },
      colorMap: customerConfigTagColorMap
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    async getList() {
      try {
        this.tableLoading = true
        const res = await getCustomerTagList(
            {type: this.tabsMap[this.curTab]}
        ).finally(() => {
          this.tableLoading = false
        })
        if (res.code === 200) {
          this.list = res.data
        }
      } catch {
      }
    },
    handleTabs(tab) {
      this.curTab = tab.name
      this.getList()
    },
    onAdd(type) {
      const typeMap = {
        company: '新增公司标签',
        my: '新增个人标签'
      }
      this.dialogTitle = typeMap[type]
      this.tagDialog = true
    },
    onEdit(row) {
      const typeMap = {
        company: '修改公司标签',
        my: '修改个人标签'
      }
      this.dialogTitle = typeMap[this.curTab]
      this.tagDialogForm = {
        ...this.tagDialogForm,
        ...row
      }
      this.tagDialog = true
    },
    async addCompanyTagReq(data) {
      try {
        this.btnLoading = true
        const res = await addCompanyTag({...data}).finally(() => {
          this.btnLoading = false
        })
        if (res.code === 200) {
          this.$message.success('添加公司标签成功')
          await this.getList()
          this.tagDialog = false
        }
      } catch {
      }
    },
    async editCompanyTagReq(data) {
      try {
        this.btnLoading = true
        const res = await editCompanyTag({...data}).finally(() => {
          this.btnLoading = false
        })
        if (res.code === 200) {
          this.$message.success('修改公司标签成功')
          await this.getList()
          this.tagDialog = false
        }
      } catch {
      }
    },
    async addPersonalTagReq(data) {
      try {
        this.btnLoading = true
        const res = await addPersonalTag({...data}).finally(() => {
          this.btnLoading = false
        })
        if (res.code === 200) {
          this.$message.success('添加个人标签成功')
          await this.getList()
          this.tagDialog = false
        }
      } catch {
      }
    },
    async editPersonalTagReq(data) {
      try {
        this.btnLoading = true
        const res = await editPersonalTag({...data}).finally(() => {
          this.btnLoading = false
        })
        if (res.code === 200) {
          this.$message.success('添加个人标签成功')
          await this.getList()
          this.tagDialog = false
        }
      } catch {
      }
    },
    async setCompanyTagReq(data) {
      try {
        const res = await setCompanyTag({...data})
        if (res.code === 200) {
          this.$message.success('设置公司标签成功')
          await this.getList()
        }
      } catch {
      }
    },
    async onDelete(id) {
      try {
        if (this.curTab === 'companyTag') {
          const res = await deleteCompanyTag({id})
          if (res.code === 200) {
            this.$message.success('删除公司标签成功')
            await this.getList()
          }
        } else if (this.curTab === 'myTag') {
          const res = await deletePersonalTag({id})
          if (res.code === 200) {
            this.$message.success('删除个人标签成功')
            await this.getList()
          }
        }
      } catch {
      }
    },
    onConfirm() {
      this.$refs['tagFormRef'].validate((valid) => {
        if (!valid) {
          return
        }
        let data = {...this.tagDialogForm}
        if (this.curTab === 'companyTag') {
          if (!data.id) {
            delete data.id
            this.addCompanyTagReq(data)
          } else {
            this.editCompanyTagReq(data)
          }

        } else if (this.curTab === 'myTag') {
          if (!data.id) {
            delete data.id
            this.addPersonalTagReq(data)
          } else {
            this.editPersonalTagReq(data)
          }
        }
      });
    },
    onCancel() {
      if (this.btnLoading) {
        return
      }
      this.dialogTitle = ''
      this.tagDialogForm = {...deepClone(initDialogForm)}
      this.tagDialog = false
    },
  }
}
</script>

<style lang="scss" scoped>

</style>
