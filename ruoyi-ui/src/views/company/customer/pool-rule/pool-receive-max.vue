<template>
  <div class="pool-receive mb-20">
    <div class="space-between">
      <div class="flex-middle">
        <span class="fs-13 bold">领取上限</span>

        <span class="fs-13 ml-10 gray-text">没有指定领取上限的成员，默认为不限制领取数量</span>
      </div>
      <div class="mr-10">
        <el-button class="add-rule-btn" round size="mini" @click="receiveMaxDialog=true">添加规则</el-button>
      </div>
    </div>
    <div class="mt-10">
      <TableNext :list="receiveMaxList" :columns="receiveMaxColumn" :extra-option="{height:'260'}"/>
    </div>

    <el-dialog
        :visible.sync="receiveMaxDialog"
        width="400px"
        :title="receiveMaxDialogTitle"
        destroy-on-close
    >
      <el-form :model="receiveMaxForm">
        <el-form-item label="选择人员" required>
          <div class="form-item">
            <el-select v-model="receiveMaxForm.member" style="width:100%" :popper-append-to-body="false" multiple
                       class="select-tree">
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
        <el-form-item label="领取上限" required>
          <div class="form-item">
            <div class="flex-middle">
              <el-input-number style="width:80%" v-model="receiveMaxForm.limit" :min="1"
                               controls-position="right"></el-input-number>
              <span class="fs-12 lineH-24 ml-4">个公海客户</span>
            </div>
          </div>

        </el-form-item>
        <el-form-item label="上限周期" required>
          <div class="form-item">
            <el-select
                style="width: 100%"
                v-model="receiveMaxForm.period">
              <el-option
                  v-for="(item,index) in periodOption"
                  :key="index"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
          </div>

        </el-form-item>
      </el-form>


      <div slot="footer" class="dialog-footer">
        <el-button round @click="onCancel">取 消</el-button>
        <el-button type="primary" round>确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import DelPopover from '../DelPopover.vue'
import {EmptyStr} from "@/utils/tools";
import {treeList} from "@/mock";

const initReceiveMaxForm = {
  member: '',
  limit: 1,
  period: ''
}
export default {
  components: {TableNext, DelPopover},
  data() {
    return {
      receiveMaxList: [
        {id: 1, member: 'test', limit: 10, period: 'month'},
      ],
      receiveMaxColumn: [
        {
          label: '成员',
          field: 'member',
          align: 'left',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '领取上限',
          field: 'limit',
          width: '140',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '上限周期',
          'render-header': (h, {column}) => {
            return h('div', [
              h('span', column.label),
              h(
                  'el-tooltip',
                  {
                    props: {
                      effect: 'dark',
                      content: <div class="fs-12 lineH-24" style="width:210px">
                        领取上限按周期计算。譬如：公海客户领取上限为10，周期为每周。即每周最多可从公海领取10个公海客户到私海中。
                      </div>,
                    },
                  },
                  [
                    h('i', {
                      class: 'el-icon-question ml-5',
                    }),
                  ]
              ),
            ])
          },
          field: 'period',
          width: '140',
          render: (_row, field) => <div>
            {this.periodOptionMap[field]}
          </div>,
        },
        {
          label: '操作',
          field: 'operate',
          width: '140',
          render: (row) => {
            return (
                <el-row>
                  <el-button type='text' onClick={() => this.onEdit(row)}>
                    编辑
                  </el-button>
                  <DelPopover id={row?.id}/>
                </el-row>
            );
          },
        },
      ],
      receiveMaxDialog: false,
      receiveMaxDialogTitle: '添加领取上限规则',
      receiveMaxForm: initReceiveMaxForm,
      emptyOption: [],
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      data: treeList,
      periodOption: [
        {label: '每日', value: 'day'},
        {label: '每周', value: 'weak'},
        {label: '每月', value: 'month'},
        {label: '每年', value: 'year'},
      ],
      periodOptionMap: {
        day: '每日',
        weak: '每周',
        month: '每月',
        year: '每年'
      }
    }
  },
  methods: {
    // 编辑
    onEdit(row) {
      this.receiveMaxDialogTitle = '编辑客户分组'
      this.receiveMaxForm = {
        ...row
      }
      this.receiveMaxDialog = true
    },
    // 取消
    onCancel() {
      this.receiveMaxDialogTitle = '新建客户分组'
      this.receiveMaxForm = initReceiveMaxForm
      this.receiveMaxDialog = false
    },
  }

}
</script>

<style lang="scss">
.pool-receive {
  .select-tree {
    .el-select-dropdown__item {
      padding: 0 !important;
      overflow-y: auto;
    }
  }
}
</style>
