<template>
  <div class="mb-20">
    <div class="space-between">
      <div class="flex-middle">
        <span class="fs-13 bold">移入公海白名单</span>
        <span class="fs-13 ml-10 gray-text">白名单人员的客户不受移入公海规则的限制</span>
      </div>
      <div class="mr-10">
        <el-button class="add-rule-btn" round size="mini" @click="whiteListDialog=true">添加名单</el-button>
      </div>
    </div>
    <div class="mt-10">
      <el-checkbox>排除禁用账号</el-checkbox>
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
      <TableNext :list="whitelist" :columns="whiteListColumn" :extra-option="{height:'260'}"
      />
    </div>
    <el-dialog
      :visible.sync="whiteListDialog"
      width="400px"
      style="margin-top: 25vh"
      title="添加白名单"
      destroy-on-close
    >
      <el-form>
        <el-form-item label="选择人员">
          <div class="form-item">
            <el-select style="width:100%" :popper-append-to-body="false" multiple class="select-tree">
              <el-option :value="emptyOption" style="height:auto">
                <el-tree
                  :data="data"
                  show-checkbox
                  node-key="id"
                  ref="tree"
                  highlight-current
                  :default-expand-all="false"
                  :props="defaultProps"></el-tree>
              </el-option>

            </el-select>
          </div>
        </el-form-item>
      </el-form>


      <div slot="footer" class="dialog-footer">
        <el-button round @click="whiteListDialog=false">取 消</el-button>
        <el-button type="primary" round @click="onConfirm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import DelPopover from "../DelPopover.vue";
import {EmptyStr} from "@/utils/tools";
import {treeList} from "@/mock";

export default {
  components: {TableNext},
  data() {
    return {
      // 移入公海白名单
      whitelist: [
        {id: 1, member: 'test'}
      ],
      whiteListColumn: [
        {
          label: '成员',
          field: 'member',
          align: 'left',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '操作',
          field: 'operate',
          width: '140',
          render: (row) => {
            return (
              <DelPopover id={row?.id}/>
            );
          },
        },
      ],
      memberOption: [],
      chooseMember: [],
      whiteListDialog: false,
      emptyOption: [],
      data: treeList,
      defaultProps: {
        children: 'children',
        label: 'label'
      }
    }
  },
  methods: {
    onConfirm() {

    }
  }

}
</script>

<style lang="scss">

</style>
