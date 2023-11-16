<template>
  <div class="schedule-dialog">
    <el-dialog
        width="600px"
        title="新建日程"
        :visible.sync="scheduleDialog"
        :close-on-click-modal="false"
        :append-to-body="true"
        @close="onCancel">
      <div class="container">
        <el-form :model="scheduleForm" label-position="top" class="schedule-form">
          <el-form-item label="日程内容" required>
            <el-input v-model="scheduleForm.scheduleContent" placeholder="请输入"/>
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
                    type="daterange"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
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
                    type="datetimerange"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                    clearable
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
            <el-select v-model="scheduleForm.userIds" multiple filterable>
              <el-option
                  v-for="(item,index) in followPersonList"
                  :key="index"
                  :label="item.nickName"
                  :value="item.userId">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="备注">
            <el-input placeholder="请输入内容" type="textarea" resize="none" :rows="3"></el-input>
          </el-form-item>
        </el-form>
      </div>

      <div slot="footer" class="dialog-footer">
        <el-button round :loading="btnLoading" @click="onCancel">取 消</el-button>
        <el-button type="primary" round :loading="btnLoading" @click="onConfirm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import SelectTagColor from "@/views/components/SelectTagColor/index.vue";
import SelectNext from "@/components/SelectNext/index.vue";
import {deepClone, formatDate, formatDateSimple} from "@/utils";
import {addSchedule, editSchedule} from "@/api/customer/schedule";
import {searchFollowerCustomer} from "@/api/customer/publicleads";

const initForm = {
  customerId: "", //客户ID
  scheduleContent: "", //日程内容
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
    formData: {
      type: Object,
      default: () => {
      },
      required: false
    },
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
      ],
      followPersonList: [],
      btnLoading: false,
    }
  },
  watch: {
    formData: {
      handler(newVal) {
        if (newVal && newVal?.id) {
          this.scheduleForm = {
            ...this.scheduleForm,
            ...newVal
          }
        }
      }
    },
    visible: {
      handler(newVal) {
        this.scheduleDialog = newVal
      },
      immediate: true
    }
  },
  mounted() {
    this.getFollowPerson()
  },
  methods: {
    async getFollowPerson() {
      if (!this.formData.customerId) return
      try {
        const res = await searchFollowerCustomer({
          id: this.formData.customerId,
        })
        if (res.code === 200) {
          this.followPersonList = res.data
        }
      } catch {
      }
    },
    async addFormSchedule() {
      if (!this.formData?.customerId) {
        return
      }
      const {
        scheduleContent,
        color,
        allDayFlag,
        recurringSchedule,
        dates,
        userIds
      } = this.scheduleForm
      const config = {
        scheduleContent,
        color,
        recurringSchedule,
        userIds,
        customerId: this.formData.customerId,
        allDayFlag: Number(allDayFlag),
        scheduleStartTime: allDayFlag ? formatDateSimple(dates[0]) : formatDate(dates[0]),
        scheduleEndTime: allDayFlag ? formatDateSimple(dates[1]) : formatDate(dates[1]),
      }
      try {
        this.btnLoading = true
        const res = await addSchedule({...config}).finally(() => {
          this.btnLoading = false
        })
        if (res.code === 200) {
          this.$message.success('添加日程成功')
          this.onCancel()
        }
      } catch {
      }
    },
    async editFormSchedule() {
      try {
        this.btnLoading = true
        const res = await editSchedule({
          ...this.scheduleForm
        }).finally(() => {
          this.btnLoading = false
        })
        if (res.code === 200) {
          this.$message.success('编辑日程成功')
          this.onCancel()
        }
      } catch {
      }
    },
    onConfirm() {
      if (!this.formData.id) {
        this.addFormSchedule()
      } else {
        this.editFormSchedule()
      }
    },
    onCancel() {
      this.$emit('update:visible', false)
      // 根据情况使用
      this.$emit('onHideDialog')
    }
  }
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
