<template>
  <div class="flex-start">
    <div class="container">
      <div class="menu">
        <el-collapse>
          <template v-for="item in menuList">
            <div
                class="menu-item px-10 flex-middle space-between fs-14"
                :class="{'active':item.userId === curMenuActive}"
                :key="item.userId"
                @click="changeMenu(item.userId)"
            >
              <span>{{ item.nickName }}</span>
              <span>{{ item.segmentCount }}</span>
            </div>
          </template>

        </el-collapse>
      </div>
    </div>
    <div class="mt-20 mx-10 flex1">
      <div class="flex-middle space-between" v-if="curMenuActive===isMySegment">
        <el-tabs v-model="curTab" type="card" @tab-click="handleTabOfTable">
          <el-tab-pane :label="`全部(${echoTableTabCount.all})`" name="all"></el-tab-pane>
          <el-tab-pane :label="`公司共享(${echoTableTabCount.company})`" name="company"></el-tab-pane>
          <el-tab-pane :label="`个人使用(${echoTableTabCount.mySelf})`" name="personal"></el-tab-pane>
        </el-tabs>
        <el-button type="primary" round @click="drawerVisible=true">新建自定义客群
        </el-button>
      </div>
      <div class="fs-14" v-else>
        此处仅显示同事的个人设置客群，复制并保存后即可设置为自己的客群。
      </div>

      <TableNext
          class="mt-10 segment-list"
          v-loading="tableLoading"
          :list="list"
          :columns="columns"
          :extra-option="extraOption"
          :paginate-option="paginateOption"
      />
    </div>
    <DrawerCreateCustomerGroup
        :visible.sync="drawerVisible"
        :row="createCustomerRow"
        @onConfirm="onConfirm"
        @onCancel="onCancel"/>
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import DrawerCreateCustomerGroup from "./DrawerCreateCustomerGroup.vue";
import DelPopover from "@/components/DevPopover";
import {deleteSegment, getSegmentList, getSegmentUserList} from "@/api/customer/segment";
import {deepClone} from "@/utils";

export default {
  components: {
    TableNext,
    DrawerCreateCustomerGroup
  },
  data() {
    return {
      curTab: 'all',
      curMenuActive: '',
      menuList: [],
      tableLoading: false,
      extraOption: {
        height: '80vh',
        rowKey: 'id',
        defaultExpandAll: false,
        treeProps: {
          children: 'children',
          hasChildren: 'hasChildren'
        }
      },
      paginateOption: {
        total: 0,
        layout: 'total, prev, pager, next , sizes',
        currentPage: 1,
        pageSize: 10,
        pageSizes: [10, 20, 50, 100],
      },
      list: [],
      companyShareList: [],
      myUseList: [],
      columns: [
        {
          label: '客群名称',
          field: 'name',
          align: 'left',
          className: 'customer-name',
          render: (row, field) => {
            return <span>
              <el-tooltip placement="top" content={field}>
                <div className="flex-middle">
                  <span>{field}</span>
                  {row?.usageScope === 2 ? <el-tag size="mini" effect='plain' class="ml-6">个人</el-tag> : null}
                </div>
              </el-tooltip>
            </span>
          }
        },
        {
          label: '客群规则',
          field: 'menuType',
          align: 'left',
          render: (row, field) => {
            return <div>
              <span class="mr-10">{field}</span>
              <el-popover trigger="hover" placement="top">
                <div class="flex-center flex-column">
                  <span class="fs-14 bold">可见范围</span>
                  <span>{row.usageScopeName}</span>
                </div>
                <el-button type="text" slot="reference">详情</el-button>
              </el-popover>
            </div>
          }
        },
        {
          label: '客户数',
          field: 'customerCount',
          width: 120,
          align: 'center',
          render: (row, field) => {
            return <el-tooltip placement="top" content="去客户列表查看">
              <el-button type="text" disabled={row?.id === -1}>{field}</el-button>
            </el-tooltip>
          }
        },
        {
          label: '操作',
          field: 'operate',
          width: 160,
          render: (row, _field) => {
            return row?.level === 1 && ![-1, -2].includes(row?.id) ? <el-row>
              <el-button type="text" onClick={() => this.onCommOperate(row)}>编辑</el-button>
              <el-button type="text" onClick={() => this.onCommOperate(row, 'copy')}>复制</el-button>
              <DelPopover
                  width={400}
                  content="是否解散此客群？客群解散后无法恢复，但不会删除该客群下的客户"
                  delText="解散"
                  id={row.id}
                  on={{onDelete: (id) => this.onDelete(id)}}/>
            </el-row> : null
          }
        },
      ],
      drawerVisible: false,
      createCustomerRow: {},
    }
  },
  computed: {
    isMySegment() {
      return this.menuList?.[0]?.userId || ''
    },
    echoTableTabCount() {
      return {
        company: this.companyShareList.length,
        mySelf: this.myUseList.length,
        all: this.companyShareList.length + this.myUseList.length
      }
    },
  },
  mounted() {
    this.getMenuList()
  },
  methods: {
    async getList(params) {
      try {
        this.tableLoading = true;
        const res = await getSegmentList({...params}).finally(() => {
          this.tableLoading = false
        })
        if (res.code === 200) {
          this.list = res.data
          let companyShare = []
          let myUse = []
          this.list.map(val => {
            if (val?.usageScope === 1) {
              companyShare.push(val)
            } else if (val?.usageScope === 2) {
              myUse.push(val)
            }
            val.level = 1
            return val
          })
          this.companyShareList = companyShare
          this.myUseList = myUse
        }
      } catch {
      }
    },
    async getMenuList() {
      try {
        const res = await getSegmentUserList()
        if (res.code === 200) {
          this.menuList = res.data
          this.curMenuActive = this.menuList?.[0]?.userId || ''
          await this.getList({
            createId: this.curMenuActive,
          })
        }
      } catch {

      }
    },
    changeMenu(id) {
      this.curMenuActive = id
      this.getList({
        createId: this.curMenuActive,
      })
    },
    handleTabOfTable(tab) {
      switch (tab.name) {
        case "all":
          this.list = [...this.companyShareList, ...this.myUseList].sort(val => val.id)
          break;
        case "company":
          this.list = this.companyShareList
          break;
        case "personal":
          this.list = this.myUseList
          break;
      }
    },
    onCommOperate(row, type) {
      if (type === 'copy') {
        delete row.id
      }
      this.createCustomerRow = deepClone(row)
      this.drawerVisible = true

    },
    async onDelete(id) {
      try {
        const res = await deleteSegment({id})
        if (res.code === 200) {
          this.$message.success('解散成功')
          await this.getList({
            createId: this.curMenuActive,
          })
        }
      } catch (e) {
        console.error(e)
      }
    },
    async onConfirm() {
      this.onCancel()
      await this.getList({
        createId: this.curMenuActive,
      })
    },
    onCancel() {
      this.createCustomerRow = {}
      this.drawerVisible = false

    }
  }
}
</script>

<style lang="scss" scoped>
.container {
  width: 160px;
  min-width: 160px;
  height: 80vh;
  position: relative;
  border-left: 1px solid #f0f0f0;
  background: #f7f8fb;

  .menu {
    overflow-y: auto;
    flex: 1;
    height: 100%;

    &-item {
      height: 40px;
      cursor: pointer;

      &:hover {
        background-color: #f3f3f3;
      }
    }

    .menu-item.active {
      color: #0064ff;
      background-color: #f0f8ff;
    }


    ::v-deep .el-collapse-item__header,
    ::v-deep .el-collapse-item__wrap {
      background-color: transparent;
    }

    ::v-deep .el-collapse-item__wrap {
      padding: 0;
    }
  }
}

.segment-list {
  ::v-deep .customer-name {
    .cell {
      display: flex;
    }
  }
}

</style>
