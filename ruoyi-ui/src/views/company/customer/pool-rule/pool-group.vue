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
        <el-form-item label="分组名称">
          <el-input :disabled="poolGroupFrom.isView" v-model="poolGroupFrom.name"
                    placeholder="请输入分组名称"></el-input>
        </el-form-item>
        <el-form-item label="分组成员">
          <div class="form-item">
            <el-select v-if="!poolGroupFrom.isView" style="width:100%" :popper-append-to-body="false" multiple
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
        <el-button type="primary" round>确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import DelPopover from "@/views/company/customer/DelPopover.vue";
import {EmptyStr} from "@/utils/tools";
import {treeList} from "@/mock";

const initPoolGroupFrom = {
  name: '',
  memberList: [],
  isView: false
}
export default {
  components: {TableNext, DelPopover},
  data() {
    return {
      poolGroupByList: [
        {
          name: '公共公海分组',
          default: true,
          member: [
            {name: 'Hetty', avatar: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'},
          ],
        },
        {
          name: '公共公海分组22',
          default: false,
          member: [
            {name: 'yizhi', avatar: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'}
          ],
        }
      ],
      poolGroupByColumns: [
        {
          label: '名称',
          field: 'name',
          align: 'left',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '成员',
          field: 'member',
          align: 'left',
          render: (_row, field) => {
            if (!field?.length) {
              return '---'
            }
            const imgList = field.map((val, index) => {
              if (index > 3) {
                return null
              }
              return <el-tooltip content={val.name}>
                <el-image class="icon-40 radius-20 mx-4" src={val.avatar}></el-image>
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
                    : <el-row v-else>
                      <el-button type='text' onClick={() => this.onEdit(row)}>
                        编辑
                      </el-button>
                      <DelPopover id={row?.id}/>
                    </el-row>
                }
              </div>

            );
          },
        },
      ],
      poolGroupFrom: initPoolGroupFrom,
      poolGroupOptions: [
        {
          value: 'zhinan',
          label: '指南',
          children: [{
            value: 'shejiyuanze',
            label: '设计原则',
          }, {
            value: 'daohang',
            label: '导航',
          }]
        }
      ],
      poolGroupDialog: false,
      poolGroupDialogTitle: '添加白名单',
      emptyOption: [],
      data: treeList,
      defaultProps: {
        children: 'children',
        label: 'label'
      }
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
  methods: {
    onView(row) {
      this.poolGroupFrom = {
        name: row.name,
        memberList: row.member,
        isView: true
      }
      this.poolGroupDialogTitle = '查看白名单'
      this.poolGroupDialog = true
    },
    onEdit(row) {
      this.poolGroupFrom = {
        name: row.name,
        memberList: row.member?.map(val => val.name) || [],
        isView: false
      }
      console.log(this.poolGroupFrom)
      this.poolGroupDialogTitle = '查看白名单'
      this.poolGroupDialog = true
    },
    onCancel() {
      console.log(this.poolGroupFrom)
      this.poolGroupDialogTitle = '添加白名单'
      this.poolGroupFrom = initPoolGroupFrom
      this.poolGroupDialog = false
    },
    nodeExpand() {
    },
    nodeCollapse() {
    }
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
