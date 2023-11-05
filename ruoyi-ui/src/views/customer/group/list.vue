<template>
  <div class="flex-start">
    <div class="container">
      <div class="menu">
        <el-collapse v-model="activeNames" @change="handleChange">
          <template v-for="item in menuList">
            <div
              class="menu-item px-10 flex-middle space-between fs-14"
              :class="{'active':item.key === curMenuActive}"
              :key="item.key"
              @click="curMenuActive = item.key"
            >
              <span>{{ item.name }}</span>
              <span>{{ item.count }}</span>
            </div>
          </template>

        </el-collapse>
      </div>
    </div>
    <div class="mt-20 mx-10 flex1">
      <el-tabs type="card">
        <el-tab-pane label="全部" name="all"></el-tab-pane>
        <el-tab-pane label="公司共享" name="share"></el-tab-pane>
        <el-tab-pane label="个人使用" name="personal"></el-tab-pane>
      </el-tabs>
      <TableNext
        class="mt-10"
        v-loading="tableLoading"
        :list="list"
        :columns="columns"
        :extra-option="extraOption"
      />
    </div>

  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import {listMenu} from "@/api/system/menu";

export default {
  components: {
    TableNext
  },
  data() {
    return {
      curMenuActive: 'all',
      activeNames: ['group'],
      menuList: [
        {name: '全部', key: 'all', count: 6},
        {name: '我的关注', key: 'follow', count: 0},
        {
          name: '分组',
          key: 'group',
          children: [
            {name: '全部', key: 'group-all', count: 1},
            {name: '未分组', key: 'group-un', count: 0},
          ]
        },
      ],
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
      list: [],
      columns: [
        {
          label: '客群名称',
          field: 'menuName',
          align: 'left',
          render: (_row, field) => {
            return <el-tooltip placement="top" content={field}>
              <span> {field}</span>
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
              <el-button type="text">编辑</el-button>
              <el-button type="text">复制</el-button>
              <el-tooltip placement="top" content="是否解散此客群？客群解散后无法恢复，但不会删除该客群下的客户">
                <el-button type="text">解散</el-button>
              </el-tooltip>
            </el-row> : null
          }
        },
      ],
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    getList() {
      this.tableLoading = true;
      listMenu({}).then(response => {
        this.list = this.generateLevelList(this.handleTree(response.data, "menuId"));
        this.tableLoading = false;
      });
    },
    handleChange(e) {

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
