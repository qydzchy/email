<template>
  <div>
    <div class="info-tip flex-middle">
      <div><i class="el-icon-warning mr-6" style="color: #1c76fd"></i></div>
      <div class="fs-12 wrap" style="color: #666666">
        <p>对客户资源进行分层分级管理，可迅速区分客户类型，针对性地跟进维系。大部分企业采取以下方式分组，供您参考：</p>
        <p>1、按照客户重要程度：潜在客户\意向客户\初次成交\多次成交\VIP客户</p>
        <p>2、按照客户交易推进程度：潜在客户\营销中客户\意向客户\样品客户\合作客户\复购客户</p>
        <p>3、按照客户地理位置</p>
        <p>4、按照公司产品线</p>
      </div>
    </div>
    <div class="my-16 flex-middle space-between">
      <div class="fs-14">分组管理客户，最多可设置2级子分组。
        一级分组可设置自动移入公海规则，子分组遵循与一级父分组相同的规则。
      </div>
      <el-button type="primary" round>新建分组</el-button>
    </div>
    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="menuList"
      row-key="menuId"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column prop="menuName" label="客户分组" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column label="操作" width="200" align="center" fixed="right" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button size="mini" type="text">
            添加子级分组
          </el-button>
          <el-button size="mini" type="text">
            编辑
          </el-button>
          <el-popover
            class="ml-10"
            width="260"
            trigger="click"
            placement="left-start"
            :ref="'popover-'+scope.$index"
          >
            <div class="flex-column">
              <div class="flex-middle">
                <i class="el-icon-info" style="color: #E6A23C"></i>
                <span class="pl-4">{{ `确定要删除分组【${scope.row.menuName}】吗？` }}</span>
              </div>
              <div class="flex-middle flex-end mt-16">
                <el-button round size="small" @click="hidePopover(scope.$index)">取消</el-button>
                <el-button type="danger" round size="small">删除</el-button>
              </div>
            </div>
            <el-button size="mini" type="text" slot="reference">
              删除
            </el-button>
          </el-popover>

        </template>
      </el-table-column>
    </el-table>
    <div class="flex-middle mt-10">
      <el-checkbox v-model="checkChildren">子分组必选</el-checkbox>
      <el-tooltip placement="top">
        <div slot="content" class="group-tooltip">
          <p>勾选“子分组必选”后，则不允许只选择填入父级分组。</p>
          <p></p>
          <p>如：分组A，其下有子级分组a1，则必须选择到a1才允许保存</p>
        </div>
        <i class="el-icon-warning-outline ml-6"></i>
      </el-tooltip>
    </div>
  </div>
</template>

<script>
import {delMenu, getMenu, listMenu} from "@/api/system/menu";
import TableNext from '@/components/TableNext'

export default {
  dicts: ['sys_show_hide', 'sys_normal_disable'],
  data() {
    return {
      loading: false, // 遮罩层
      refreshTable: true, // 重新渲染表格状态
      menuList: [], // 菜单表格树数据
      isExpandAll: false, // 是否展开，默认全部折叠
      checkChildren: true, // 选择子分组
      visible: false, // 选择删除提示
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    /** 获取数据 **/
    getList() {
      this.loading = true;
      listMenu({}).then(response => {
        this.menuList = this.handleTree(response.data, "menuId");
        this.loading = false;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      getMenu(row.menuId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改菜单";
      });
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.menuId) {
        this.form.parentId = row.menuId;
      } else {
        this.form.parentId = 0;
      }
      this.open = true;
      this.title = "添加菜单";
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      this.$modal.confirm('是否确认删除名称为"' + row.menuName + '"的数据项？').then(function () {
        return delMenu(row.menuId);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    hidePopover(index){
      this.$refs[`popover-${index}`].doClose();
    }
  }
}
</script>

<style lang="scss" scoped>
.info-tip {
  margin: 0;
  padding: 8px 15px;
  border: 0;
  border-radius: 4px;
  background-color: #ebf3ff;
  box-sizing: border-box;
  font-feature-settings: "tnum";
  overflow-wrap: break-word;
  align-items: center;
  color: #333;
  display: flex;
  font-size: 14px;
  font-variant: tabular-nums;
  list-style: none;
  position: relative;

  .wrap {
    line-height: 20px;
  }

  .wrap > p {
    margin-block-start: 0;
    margin-block-end: 0;
  }
}

.group-tooltip {
  width: 200px;
}
</style>
