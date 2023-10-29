<template>
  <div>
    <div class="flex-column">
      <div class="fs-14 my-10">按照业务进展阶段划分客户状态，帮助业务员针对性的跟进客户，提高成单率。</div>
      <div class="mb-10 pr-50 flex-end">
        <el-button type="primary" round size="small" @click="stageDialog=true">新增阶段</el-button>
      </div>

    </div>

    <ElTableDraggable handle=".el-icon-s-grid">
      <el-table row-key="customer-status" :data="list" v-loading="tableLoading">
        <template slot="empty">
          <el-empty imageSize={100}></el-empty>
        </template>
        <el-table-column width="40">
          <i class="el-icon-s-grid" style="cursor: grab"/>
        </el-table-column>
        <el-table-column prop="name" label="阶段名称" align="left">
          <template slot-scope="scope">
            <div class="flex-middle">
              <div class="table-color-wrap" :style="{backgroundColor:colorMap[scope.row.color]}">
                {{ scope.row.name.slice(0, 1) }}
              </div>
              <div class="ml-10">{{ scope.row.name }}</div>
            </div>

          </template>
        </el-table-column>
        <el-table-column label="操作" width="200" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button size="mini" type="text" @click="onEdit(scope.row)">
              编辑
            </el-button>
            <DelPopover :id="scope.row.id" @onDelete="stageDeleteReq"/>
          </template>
        </el-table-column>
      </el-table>
    </ElTableDraggable>

    <el-dialog title="新增阶段" width="460px" style="margin-top: 25vh" :visible.sync="stageDialog"
               destroy-on-close>
      <el-form :model="stageForm" ref="stageFormRef" :rules="stageRules">
        <el-form-item label="阶段名称" prop="name">
          <el-input v-model="stageForm.name" autocomplete="off" placeholder="请输入阶段名称"></el-input>
        </el-form-item>
        <el-form-item label="标签颜色">
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
        <el-button round :loading="btnLoading" @click="onCancel">取 消</el-button>
        <el-button type="primary" round :loading="btnLoading" @click="onConfirm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import ElTableDraggable from "el-table-draggable";
import DelPopover from "./DelPopover.vue";
import {stageAdd, stageDelete, stageEdit, stageList} from "@/api/company/status";

const initStageForm = {
  id: '',
  name: '',
  color: '0',
}
export default {
  components: {
    ElTableDraggable,
    DelPopover
  },
  data() {
    return {
      list: [],
      stageDialog: false,
      stageForm: initStageForm,
      stageRules: {
        name: [
          {required: true, message: '请输入名称', trigger: 'blur'},

        ]
      },
      colorMap: {
        0: '#000000',
        1: '#bc5959',
        2: '#d87538',
        3: '#209890',
        4: '#4b679d',
        5: '#595dbe',
        6: '#333333',
        7: '#e43e3e',
        8: '#eb9955',
        9: '#61bc81',
        10: '#5d89e9',
        11: '#8d54bd',
        12: '#7b8291',
        13: '#ee7b7b',
        14: '#e2ad28',
        15: '#80c463',
        16: '#4aa8eb',
        17: '#acacac'
      },
      tableLoading: false,
      btnLoading: false,
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    async getList() {
      this.tableLoading = true
      try {
        const res = await stageList().finally(() => {
          this.tableLoading = false
        })
        if (res.code === 200) {
          this.list = res.data
        }
      } catch (e) {
        this.tableLoading = false
      }
    },
    async stageAddReq() {
      try {
        const res = await stageAdd({...this.stageForm}).finally(() => {
          this.btnLoading = false
        })
        if (res?.code === 200) {

          this.$message({
            type: 'success',
            message: '添加成功'
          });
          await this.getList()
          this.onCancel()
        }
      } catch {
        this.btnLoading = false
      }
    },
    async stageEditReq() {
      try {
        const res = await stageEdit({...this.stageForm}).finally(() => {
          this.btnLoading = false
        })
        if (res?.code === 200) {
          this.$message({
            type: 'success',
            message: '修改成功'
          });
          await this.getList()
          this.onCancel()
        }

      } catch {
        this.btnLoading = false
      }
    },
    async stageDeleteReq(id) {
      try {
        const res = await stageDelete({id})
        if (res?.code === 200) {
          this.$message({
            type: 'success',
            message: '删除成功'
          });
          await this.getList()
        }
      } catch {
        this.btnLoading = false
      }
    },
    onEdit(item) {
      this.stageForm = {...item}
      this.stageDialog = true
    },
    onConfirm() {
      this.$refs['stageFormRef'].validate((valid) => {
        if (valid) {
          this.btnLoading = true
          if (!this.stageForm.id) {
            this.stageAddReq()
          } else {
            this.stageEditReq()
          }
        }
      });
    },
    onCancel() {
      if (this.btnLoading) {
        return
      }
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
