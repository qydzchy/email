<template>
  <div>
    <el-dialog width="600px" :visible.sync="scheduleDialog" :close-on-click-modal="false" :append-to-body="true"
               title="新建日程"
               @close="$emit('update:visible',false)">
      <div class="container">
        <el-form label-position="top" class="schedule-form">
          <el-form-item label="日程内容" required>
            <el-input/>
          </el-form-item>
          <el-form-item label="日程时间" required>
            <el-row type="flex">
              <el-col :span="18">
                <el-date-picker
                  type="datetimerange"
                  :picker-options="pickerOptions"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  align="left">
                </el-date-picker>
              </el-col>
              <el-col :span="4">
                <el-checkbox>全天</el-checkbox>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item label="周期性日程">
            <el-select></el-select>
          </el-form-item>
          <el-form-item label="提醒时间">
            <el-select></el-select>
          </el-form-item>
          <el-form-item label="关联对象">
            <el-select></el-select>
          </el-form-item>
          <el-form-item label="参与人" required>
            <el-select></el-select>
          </el-form-item>
          <el-form-item label="备注" required>
            <el-input placeholder="请输入内容" type="textarea"></el-input>
          </el-form-item>
        </el-form>
      </div>

      <div slot="footer" class="dialog-footer">
        <el-button>取 消</el-button>
        <el-button type="primary">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  props: {
    visible: {
      type: Boolean,
      default: false,
      required: false
    }
  },
  data() {
    return {
      scheduleDialog: false,
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          }
        }]
      }
    }
  },
  watch: {
    visible: {
      handler(newVal) {
        this.scheduleDialog = newVal
      },
      immediate: true
    }
  },
}
</script>

<style lang="scss" scoped>
::v-deep .el-dialog__body {
  padding-top: 0;
}

.container {
  height: 680px;
  overflow-y: auto;

  .schedule-form {
    ::v-deep .el-form-item {
      margin-bottom: 0;
      margin-top: 10px;
    }
  }
}


</style>
