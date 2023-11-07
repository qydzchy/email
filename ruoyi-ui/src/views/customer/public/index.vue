<template>
  <div class="page-customer-public mx-20 pt-16">
    <div class="bold fs-24 pb-4">
      公海客户
    </div>
    <div class="wrap pt-10 flex-middle space-between">
      <div>共 {{ paginateOption.total }} 个客户</div>
      <div class="search-group flex-middle gap-8">
        <el-select style="width:200px" placeholder="请选择" v-model="searchQuery.group"></el-select>
        <TreeSelect
            style="width: 200px"
            v-model="searchQuery.parentId"
            :options="menuOptions"
            :normalizer="normalizer"
            :show-count="true"
            :multiple="true"
            placeholder="全部分组"
        />
      </div>
    </div>
    <div class="mt-20">
      <TableNext :list="list" :columns="columns" :extra-option="{height:'70vh'}" :paginate-option="paginateOption"/>
    </div>
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import TreeSelect from "@riophae/vue-treeselect";
import {EmptyStr} from "@/utils/tools";
import {listMenu} from "@/api/system/menu";

export default {
  components: {TreeSelect, TableNext},
  data() {
    return {
      list: [],
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
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '最近动态',
          field: 'companyName',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '原跟进人',
          field: 'contactName',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '国家地区',
          field: 'email',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '客户类型',
          field: 'phone',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '客户评分',
          field: 'telOrigin',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '最近联系时间',
          field: 'area',
          render: (_row, field) => EmptyStr(field),
        }, {
          label: '时区',
          field: 'followMan',
          render: (_row, field) => EmptyStr(field),
        },
        {
          label: '社交平台',
          field: 'department',
          render: (_row, field) => EmptyStr(field),
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
  }
}
</script>

<style lang="scss" scoped>
</style>
