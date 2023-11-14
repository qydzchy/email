<template>
  <div class="schedule-dialog">
    <el-dialog
        width="600px"
        title="新建日程"
        :visible.sync="scheduleDialog"
        :close-on-click-modal="false"
        :append-to-body="true"
        @close="$emit('update:visible',false)">
      <div class="container">
        <el-form label-position="top" class="schedule-form">
          <el-form-item label="日程内容" required>
            <el-input/>
          </el-form-item>
          <el-form-item>
            <SelectTagColor is-hover :checked-color.sync="scheduleForm.color" :color-map="colorMap"/>
          </el-form-item>
          <el-form-item label="日程时间" required>
            <el-row type="flex">
              <el-col :span="18">
                <el-date-picker
                    v-if="!!scheduleForm.allDayFlag"
                    key="isFullDay"
                    style="width:100%"
                    size="small"
                    v-model="scheduleForm.dates"
                    placeholder="请选择日期"
                    clearable
                    format="yyyy-MM-dd"
                    :picker-options="pickerOptions"
                    align="left"
                ></el-date-picker>
                <el-date-picker
                    v-else
                    key="isNotFullDay"
                    style="width:100%"
                    size="small"
                    v-model="scheduleForm.dates"
                    placeholder="请选择日期"
                    clearable
                    type="datetime"
                    :picker-options="pickerOptions"
                    align="left"
                ></el-date-picker>
              </el-col>
              <el-col :span="4" class="pl-10">
                <el-checkbox v-model="scheduleForm.allDayFlag">全天</el-checkbox>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item label="周期性日程">
            <SelectNext
                :value.sync="scheduleForm.recurringSchedule"
                :select-options="{options:recurringScheduleOption}"
                @change="(value)=>scheduleForm.recurringSchedule = value"/>
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
          <el-form-item label="备注">
            <el-input placeholder="请输入内容" type="textarea" resize="none" :rows="3"></el-input>
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
import SelectTagColor from "@/views/components/SelectTagColor/index.vue";
import SelectNext from "@/components/SelectNext/index.vue";
import {deepClone} from "@/utils";

const initForm = {
  customerId: "", //客户ID
  scheduleContent: "日程内容测试01", //日程内容
  color: '#ff3333', //颜色
  allDayFlag: true, //全天 0.否 1.是
  scheduleStartTime: "", //日程开始时间
  scheduleEndTime: "", //日程结束时间
  recurringSchedule: 1, //周期性日程 1.不设置 2.每天 3.每周 4.每月 5.自定义
  customCycleValue: null, //自定义周期-值
  customCycleType: null, //自定义周期-类型 1.天 2.周 3.月
  cycleEndTime: "",
  reminderTime: "",
  userIds: [], //参与人ID
  dates: []
}
export default {
  components: {SelectTagColor, SelectNext},
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
      scheduleForm: {
        ...deepClone(initForm)
      },
      pickerOptions: {
        shortcuts: [
          {
            text: '今天',
            onClick(picker) {
              const today = new Date();
              picker.$emit('pick', today);
            }
          },
          {
            text: '明天',
            onClick(picker) {
              const yesterday = new Date();
              yesterday.setTime(yesterday.getTime() + 3600 * 1000 * 24);
              picker.$emit('pick', yesterday);
            }
          },
          {
            text: '未来7天',
            onClick(picker) {
              const end = new Date();
              end.setTime(end.getTime() + 3600 * 1000 * 24 * 7);
              picker.$emit('pick', end);
            }
          }, {
            text: '未来30天',
            onClick(picker) {
              const end = new Date();
              end.setTime(end.getTime() + 3600 * 1000 * 24 * 30);
              picker.$emit('pick', end);
            }
          }, {
            text: '未来90天',
            onClick(picker) {
              const end = new Date();
              end.setTime(end.getTime() + 3600 * 1000 * 24 * 90);
              picker.$emit('pick', end);
            }
          }]
      },
      colorMap: [
        '#ff3333',
        '#ff9933',
        '#e7c837',
        '#28b779',
        '#00ccff',
        '#cc9933',
        '#9999ff'
      ],
      recurringScheduleOption: [
        {value: 1, label: '不设置'},
        {value: 2, label: '每天'},
        {value: 3, label: '每周'},
        {value: 4, label: '每月'},
        {value: 5, label: '自定义'},
      ]
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
.schedule-dialog {
  .container {
    height: 600px;
    overflow-y: auto;

    .schedule-form {
      ::v-deep .el-form-item {
        margin-bottom: 0;
        margin-top: 10px;
      }
    }
  }

  ::v-deep .el-dialog__body {
    padding-top: 0;
  }
}


</style>
