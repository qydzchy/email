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
                @click="curMenuActive = item.userId"
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
          <el-tab-pane label="全部" name="-1"></el-tab-pane>
          <el-tab-pane label="公司共享" name="1"></el-tab-pane>
          <el-tab-pane label="个人使用" name="2"></el-tab-pane>
        </el-tabs>
        <el-button type="primary" round @click="drawerVisible=true">新建自定义客群
        </el-button>
      </div>
      <div class="fs-14" v-else>
        此处仅显示同事的个人设置客群，复制并保存后即可设置为自己的客群。
      </div>

      <TableNext
          class="mt-10"
          v-loading="tableLoading"
          :list="list"
          :columns="columns"
          :extra-option="extraOption"
          :paginate-option="paginateOption"
      />
    </div>
    <DrawerCreateCustomerGroup :visible.sync="drawerVisible" @onCancel="onCancel"/>
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import DrawerCreateCustomerGroup from "./DrawerCreateCustomerGroup.vue";
import {getSegmentList, getSegmentUserList} from "@/api/customer/segment";

export default {
  components: {
    TableNext,
    DrawerCreateCustomerGroup
  },
  data() {
    return {
      curTab: '1',
      curMenuActive: '',
      menuList: [],
      tableLoading: false,
      extraOption: {
        height: '80vh',
        rowKey: 'menuId',
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
      columns: [
        {
          label: '客群名称',
          field: 'menuName',
          align: 'left',
          render: (_row, field) => {
            return <el-tooltip placement="top" content={field}>
              <span>{field}</span>
            </el-tooltip>
          }
        },
        {
          label: '客群规则',
          field: 'menuType',
          align: 'left',
          render: (_row, field) => {
            return <div>
              <span class="mr-10">{field}</span>
              <el-popover trigger="hover" placement="top">
                <div>内容</div>
                <el-button type="text" slot="reference">详情</el-button>
              </el-popover>
            </div>
          }
        },
        {
          label: '客户数',
          field: 'orderNum',
          width: 120,
          align: 'center',
          render: (_row, field) => {
            return <el-tooltip placement="top" content="去客户列表查看">
              <el-button type="text">{field}</el-button>
            </el-tooltip>
          }
        },
        {
          label: '操作',
          field: 'operate',
          width: 160,
          render: (row, _field) => {
            return row?.level === 1 ? <el-row>
              <el-button type="text" onClick={() => this.onEdit(row)}>编辑</el-button>
              <el-button type="text">复制</el-button>
              <el-tooltip placement="top" content="是否解散此客群？客群解散后无法恢复，但不会删除该客群下的客户">
                <el-button type="text" onCick={() => this.onDelete(row)}>解散</el-button>
              </el-tooltip>
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
  },
  mounted() {
    this.getList({
      createId: 1,
      usageScope: 1,
    })
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
        }
      } catch {

      }
    },
    handleTabOfTable(tab) {
      this.getList({
        createId: 1,
        usageScope: Math.abs(+tab.name),
      })
    },
    onEdit(row) {
      this.drawerVisible = true
      this.createCustomerRow = row
    },
    onDelete(row) {
    },
    onCancel() {
      this.createCustomerRow = {}
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


</style>
