<template>
  <div class="mb-20">
    <div class="space-between">
      <div class="flex-middle">
        <span class="fs-13 bold">移入公海白名单</span>
        <span class="fs-13 ml-10 gray-text">白名单人员的客户不受移入公海规则的限制</span>
      </div>
      <div class="mr-10">
        <el-button class="add-rule-btn" round size="mini" @click="whiteListDialog = true">添加名单</el-button>
      </div>
    </div>
    <div class="mt-10">
      <el-checkbox v-model="disabledAccount" @change="editSettings">排除禁用账号</el-checkbox>
      <el-tooltip placement="top">
        <div slot="content">
          <div class="pool-rule-tooltip fs-14 lineH-24">
            禁用账号是在企业设置 - 组织架构 - 个人信息卡片中进行「禁用CRM」操作的账号
          </div>
        </div>
        <i class="el-icon-warning-outline ml-6"></i>
      </el-tooltip>
    </div>
    <div class="mt-10">
      <TableNext :list="whitelist" :columns="whiteListColumn" :extra-option="{height:'260'}" :loading="tableLoading"
      />
    </div>
    <el-dialog
        :visible.sync="whiteListDialog"
        width="400px"
        style="margin-top: 25vh"
        title="添加白名单"
        destroy-on-close
        @close="onCancel"
    >
      <el-form :model="whiteListForm" ref="whiteListRef">
        <el-form-item label="选择人员" props="userIds">
          <div class="form-item">
            <TreeSelectNext
                :default-props="defaultProps"
                :tree-data="memberOption"
                :echo-data.sync="whiteListForm.userIds"
                :disabled-list="disabledList"
                disabled-key="id"
                echo-name="nickName"
            />
          </div>
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
import DelPopover from "@/components/DevPopover/index.vue";
import TreeSelectNext from "@/components/TreeSelectNext/index.vue"
import {EmptyStr} from "@/utils/tools";
import {whiteList, whiteListAdd, whiteListDelete} from "@/api/company/poolRule";
import {listDeptUsersTree} from "@/api/system/dept";
import {mapState} from "vuex";

const initWhiteListForm = {
  userIds: []
}
export default {
  components: {TableNext, TreeSelectNext},
  data() {
    return {
      // 移入公海白名单
      whitelist: [],
      whiteListColumn: [
        {
          label: '成员',
          field: 'nickName',
          align: 'left',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '操作',
          field: 'operate',
          width: '140',
          render: (row) => {
            return (
                <DelPopover id={row?.id} on={{onDelete: (id) => this.onDelete(id)}}/>
            );
          },
        },
      ],
      memberOption: [],
      chooseMember: [],
      whiteListDialog: false,
      whiteListForm: {...initWhiteListForm},
      emptyOption: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      disabledList: [],
      tableLoading: false,
      btnLoading: false,
      disabledAccount: false
    }
  },
  computed: {
    ...mapState({
      settings: state => state.company.settings
    })
  },
  mounted() {
    this.getList()
    this.getCommonTree()
    this.$watch('settings', (newVal) => {
      const {accountDisabledFlag} = newVal
      this.disabledAccount = Boolean(accountDisabledFlag)
    }, {immediate:true})
  },
  methods: {
    async getList() {
      this.tableLoading = true
      try {
        const res = await whiteList().finally(() => {
          this.tableLoading = false
        })
        if (res.code === 200) {
          let disabledList = []
          this.whitelist = res.data
          this.whitelist.forEach(val => {
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
    async addWhiteListReq() {
      this.btnLoading = true
      try {
        const res = await whiteListAdd({userIdList: this.whiteListForm.userIds}).finally(() => {
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

    async onDelete(id) {
      try {
        const res = await whiteListDelete({id})
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

    onConfirm() {
      this.addWhiteListReq()
    },

    onCancel() {
      this.whiteListForm = initWhiteListForm
      this.whiteListDialog = false
    },

    editSettings() {
      this.$store.dispatch('company/EditCompanyCustomerSettings', {
        ...this.settings,
        accountDisabledFlag: +this.disabledAccount
      }).then(res => {
        if (res) {
          this.$message({
            type: 'success',
            message: '修改成功'
          })
        }
      })
    },

  }

}
</script>

<style lang="scss">

</style>
