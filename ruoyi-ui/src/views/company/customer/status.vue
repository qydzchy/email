<template>
  <div>
    <div class="flex-column">
      <div class="fs-14 my-10">按照业务进展阶段划分客户状态，帮助业务员针对性的跟进客户，提高成单率。</div>
      <div class="mb-10 pr-50 flex-end">
        <el-button type="primary" round size="small" @click="stageDialog=true">新增阶段</el-button>
      </div>

    </div>

    <ElTableDraggable handle=".el-icon-s-grid">
      <el-table row-key="customer-status" :data="list">
        <el-table-column width="40">
          <i class="el-icon-s-grid" style="cursor: grab"/>
        </el-table-column>
        <el-table-column prop="stageName" label="阶段名称" align="left">
          <template slot-scope="scope">
            <div class="flex-middle">
              <div class="table-color-wrap" :style="{backgroundColor:colorMap[scope.row.color]}">
                {{ scope.row.stageName.slice(0, 1) }}
              </div>
              <div class="ml-10">{{ scope.row.stageName }}</div>
            </div>

          </template>
        </el-table-column>
        <el-table-column label="操作" width="200" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button size="mini" type="text" @click="onEdit(scope.row)">
              编辑
            </el-button>
            <el-button size="mini" type="text">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </ElTableDraggable>

    <el-dialog title="新增阶段" width="460px" style="margin-top: 25vh" :visible.sync="stageDialog"
               destroy-on-close>
      <el-form :model="stageForm" ref="fastTextRef" :rules="stageRules">
        <el-form-item label="阶段名称" prop="groupName">
          <el-input v-model="stageForm.stageName" autocomplete="off" placeholder="请输入阶段名称"></el-input>
        </el-form-item>
        <el-form-item label="标签颜色" prop="groupName">
          <div class="container">
            <div class="color-box">
              <div class="color-wrap"
                   v-for="(color,key) in colorMap"
                   :key="key"
                   :style="{backgroundColor:color}"
                   @click="stageForm.color = key">
                <i class="el-icon-check check-icon" v-if="stageForm.color===key"
                ></i>
              </div>
            </div>
          </div>

        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button round @click="stageDialog=false">取 消</el-button>
        <el-button type="primary" round>确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import ElTableDraggable from "el-table-draggable";

const initStageForm = {
  stageName: '',
  color: '',
}
export default {
  components: {
    ElTableDraggable,
  },
  data() {
    return {
      list: [
        {
          id: 1,
          stageName: '1',
          color: '0'
        },
        {
          id: 2,
          stageName: '2',
          color: '2'
        },
        {
          id: 3,
          stageName: '3',
          color: '4'
        },
      ],
      stageDialog: false,
      stageForm: initStageForm,
      stageRules: {},
      colorMap: {
        0: '#000000',
        1: '#dadada',
        2: '#bc5959',
        3: '#000000',
        4: '#dadada',
        5: '#bc5959',
        6: '#000000',
        7: '#dadada',
        8: '#bc5959',
        9: '#000000',
        10: '#dadada',
        11: '#bc5959',
        12: '#000000',
        13: '#dadada',
        14: '#bc5959',
        15: '#000000',
        16: '#dadada',
        17: '#bc5959'
      },

    }
  },
  mounted() {
  },
  methods: {
    onEdit(item) {
      this.stageForm = {...item}
      this.stageDialog = true
    },
    onConfirm() {
      this.onCancel()
    },
    onCancel() {
      this.stageForm = initStageForm
      this.stageDialog = false
    },
  }
}
</script>

<style lang="scss" scoped>
.table-color-wrap {
  width: 26px;
  height: 26px;
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #ffffff;
  padding: 4px;
}

.container {
  width: 100%;
  display: inline-block;
  box-sizing: border-box;
}

.color-box {
  width: 256px;
  row-gap: 8px;
  display: flex;
  justify-content: flex-start;
  flex-flow: row wrap;
  box-sizing: border-box;

  .color-wrap {
    width: 20px;
    height: 20px;
    padding: 0 4px;
    max-width: 100%;
    min-height: 1px;
    position: relative;
    border-radius: 4px;
    margin: 0 4px;
    cursor: pointer;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .color-wrap > .check-icon {
    color: #ffffff;
    position: absolute;
  }
}


::v-deep .el-table__row {
  &:focus-visible {
    outline: #0a6aff auto 1px;
  }
}

::v-deep .el-dialog {
  border-radius: 14px;
}

::v-deep .el-dialog__header {
  border-bottom: 1px solid #dadada;
}
</style>
