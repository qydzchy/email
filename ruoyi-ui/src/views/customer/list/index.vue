<template>
  <div class="page-customer-public">
    <div class="bold fs-24 space-between px-30 pb-10 header">
      <div>
        <span class="mr-6">客户列表</span>
        <el-radio-group v-model="listType">
          <el-radio-button :label="0">我的客户</el-radio-button>
          <el-radio-button :label="1">团队客户</el-radio-button>
        </el-radio-group>
<!--        <el-select class="ml-6"></el-select>-->
      </div>
      <div>
        <el-button type="primary" round>新建客户</el-button>
      </div>
    </div>

    <div class="container">
      <split-pane @resize="resize" :min-percent='16' :default-percent='16' split="vertical" ref="splitPane"
                  :class="{'cur-collapsed':collapsed}"
      >
        <template #paneL>
          <div class="left-wrap">
            <div class="flex-column" :class="{'is-collapsed':collapsed}" style="height: inherit;">
              <div class="menu">
                <el-collapse v-model="activeNames" @change="handleChange">
                  <template v-for="item in menuList">
                    <div
                      class="menu-item px-10 flex-middle space-between fs-14"
                      :class="{'active':item.key === curMenuActive}"
                      :key="item.key"
                      v-if="!item.children"
                      @click="curMenuActive = item.key"
                    >
                      <span>{{ item.name }}</span>
                      <span>{{ item.count }}</span>

                    </div>
                    <div class="pt-4" v-else>
                      <el-collapse-item :key="item.key" :name="item.key">
                        <template #title>
                          <div class="pl-6 fs-14">
                            {{ item.name }}
                            <!--                            <i class="header-icon el-icon-info"></i>-->
                          </div>

                        </template>
                        <div
                          class="menu-item flex-middle space-between my-4 pl-20 pr-10"
                          :class="{'active':subItem.key === curMenuActive}"
                          v-for="subItem in item.children"
                          :key="subItem.key"
                          @click="curMenuActive = subItem.key">
                          <span>{{ subItem.name }}</span>
                          <span>{{ subItem.count }}</span>
                        </div>
                      </el-collapse-item>
                    </div>

                  </template>

                </el-collapse>
              </div>
              <div class="fixed-operate flex-middle flex-center">
                <div class="hover-color fs-14 pointer">客群管理</div>
                <el-tooltip>
                  <template #content>
                    查看&nbsp;<a style="text-decoration: underline">客群功能详细说明</a>
                  </template>
                  <i class="hover-color el-icon-question pl-6 pointer"></i>
                </el-tooltip>
              </div>
              <span class="right-wrap-icon pointer" @click="onCollapsed">
              <i class="el-icon-d-arrow-left"></i>
            </span>
            </div>
            <div class="left-wrap-icon pointer" :class="{'arrow-right':collapsed}" @click="onLeftCollapsed">
              <i class="el-icon-d-arrow-right fs-14"></i>
            </div>

          </div>
        </template>
        <template #paneR>
          <div class="right-wrap">
            <div class="wrap pt-10 flex-middle space-between ml-30">
              <div>
                <span class="bold">全部客户</span>
                <span class="gray-text ml-2">69 个客户</span>
              </div>
              <div class="search-group flex-middle gap-8">
                <el-select style="width:200px" placeholder="请选择" v-model="searchQuery.group"></el-select>
                <el-input style="width:200px" placeholder="请输入"></el-input>
                <FilterDrawer/>
                <!--                <el-popover-->
                <!--                  trigger="click"-->

                <!--                  width="200"-->
                <!--                  content="这是一段内容,这是一段内容,这是一段内容,这是一段内容。">-->
                <!--                  <el-button icon="el-icon-setting" round slot="reference">设置</el-button>-->
                <!--                </el-popover>-->
              </div>
            </div>
            <div class="mt-20">
              <TableNext :list="list" :columns="columns" :extra-option="{height:'66vh'}"
                         :paginate-option="paginateOption"/>
            </div>

          </div>

        </template>
      </split-pane>

    </div>
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import TreeSelect from "@riophae/vue-treeselect";
import FilterDrawer from './FilterDrawer.vue'
import {EmptyStr} from "@/utils/tools";
import {listMenu} from "@/api/system/menu";

export default {
  components: {TreeSelect, TableNext, FilterDrawer},
  data() {
    return {
      listType: 0,
      collapsed: false,
      percent: 16,
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
      list: [{
        companyName: '111',
        nearly: '111'
      }],
      columns: [
        {type: 'selection', width: '50'},
        {
          label: '公司名称',
          field: 'companyName',
          fixed: 'left',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '最近跟进',
          field: 'nearly',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '最近动态',
          field: 'companyName',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '原跟进人',
          field: 'contactName',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '国家地区',
          field: 'email',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '客户类型',
          field: 'phone',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '客户评分',
          field: 'telOrigin',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '最近联系时间',
          field: 'area',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '时区',
          field: 'followMan',
          width: '200',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '社交平台',
          field: 'department',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '操作',
          field: 'operate',
          fixed: 'right',
          render: (_row, field) => {
            const operateList = [
              {name: ''}
            ]
            return <div>
              <el-popover appendToBody={false}>
                <div class="operate-list">
                  <ul>

                  </ul>
                </div>
                <i class="operate-more pointer el-icon-more-outline" slot="reference"></i>
              </el-popover>
            </div>
          }
        }
      ],
      paginateOption: {
        total: 0,
        layout: 'total, sizes, prev, pager, next',
        pageSize: 20,
        pageSizes: [10, 20, 50, 100]
      },
      menuOptions: [],
      searchQuery: {
        group: '',
        parentId: null,

      }
    }
  },
  mounted() {
    this.getTreeselect();
  },
  methods: {
    /** 转换菜单数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.menuId,
        label: node.menuName,
        children: node.children
      };
    },
    /** 查询菜单下拉树结构 */
    getTreeselect() {
      listMenu().then(response => {
        this.menuOptions = [];
        const menu = {menuId: 0, menuName: '主类目', children: []};
        menu.children = this.handleTree(response.data, "menuId");
        this.menuOptions.push(menu);
      });
    },
    onCollapsed() {
      this.collapsed = true
      this.$refs.splitPane.percent = 0
    },
    onLeftCollapsed() {
      this.collapsed = false
      console.log(this.percent)
      this.$refs.splitPane.percent = this.percent
    },
    resize(value) {
      console.log(value)
      if (value > 50) {
        this.$refs.splitPane.percent = 50
        this.percent = 50
        return
      }
      this.percent = value
    },
    handleChange(val) {
      console.log(val);
    }
  }
}
</script>

<style lang="scss" scoped>
.page-customer-public {
  .header {
    border-bottom: 1px solid #f0f0f0;
  }
}

.container {
  height: calc(100vh - 154px);

  .cur-collapsed {
    ::v-deep .splitter-pane-resizer {
      display: none;
    }
  }

  .is-collapsed {
    width: 0;
    display: none;
  }

  .left-wrap {
    position: relative;
    width: 100%;
    height: 100%;
    border-left: 1px solid #f0f0f0;
    background: #f7f8fb;

    .menu {
      overflow-y: auto;
      flex: 1;

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

    .fixed-operate {
      height: 40px;
      border-top: 1px solid #f0f0f0;

      .hover-color {
        &:hover {
          color: #0a6aff;
        }
      }
    }

    &:hover {
      .right-wrap-icon {
        visibility: visible;
      }
    }

    .right-wrap-icon {
      width: 22px;
      height: 22px;
      position: absolute;
      top: 10px;
      right: -22px;
      display: flex;
      align-items: center;
      justify-content: center;
      border: 1px solid rgba(223, 225, 229);
      border-top-right-radius: 9999px;
      border-bottom-right-radius: 9999px;
      visibility: hidden;
      cursor: pointer;
      z-index: 10;
    }

    .left-wrap-icon {
      position: absolute;
      justify-content: center;
      align-items: center;
      height: 28px;
      color: #666;
      z-index: 15;
      text-align: center;
      background: #fafafa;
      top: 16px;
      left: 0;
      width: 20px;
      border: 1px solid #f0f0f0;
      box-shadow: 4px 0 4px rgba(0, 0, 0, .08);
      border-radius: 0 100px 100px 0;
      display: none;
    }

    .arrow-right {
      display: flex;
    }
  }

  .right-wrap {
    box-sizing: border-box;
    margin-right: 20px;

    .operate-list {
      width: 400px;
    }

    .operate-more {
      transform: rotate(90deg);
    }
  }

  ::v-deep .splitter-pane-resizer.vertical {
    width: 4px;
    border-left: 0 solid hsla(0, 0%, 100%, 0);
    border-right: 0 solid hsla(0, 0%, 100%, 0);
    border-radius: 2px;
    margin-left: 1px;
    z-index: 4;
  }

  ::v-deep .splitter-pane-resizer {
    background-color: transparent;
    opacity: unset;

    &:hover, &:active {
      background-color: #0a6aff;
    }
  }

  ::v-deep .splitter-paneL {
    display: inline;
    padding-right: 0 !important;
  }

  ::v-deep .splitter-paneR {
    padding: 0;
  }
}

</style>
