<template>
  <div class="schedule-dialog">
    <el-dialog
        width="600px"
        :title="formData.scheduleId ? '编辑日程':'新建日程'"
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
            <el-row type="flex">
              <el-col :span="12">
                <SelectNext
                    :value.sync="scheduleForm.recurringSchedule"
                    :select-options="{options:recurringScheduleOption}"/>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item label="自定义周期" v-if="scheduleForm.recurringSchedule === 5">
            <el-row type="flex">
              <el-col :span="6">
                <el-input-number
                    size="small"
                    controls-position="right"
                    v-model="scheduleForm.customCycleValue"
                    :min="1">
                </el-input-number>
              </el-col>
              <el-col :span="6">
                <SelectNext
                    :value.sync="scheduleForm.customCycleType"
                    :select-options="{options:customCycleTypeOption}"/>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item label="周期结束时间" v-if="scheduleForm.recurringSchedule !==1">
            <el-date-picker
                v-model="scheduleForm.cycleEndTime"
                type="datetime"
                clearable
                format="yyyy-MM-dd HH:mm:ss"
                placeholder="选择日期时间">
            </el-date-picker>

          </el-form-item>
          <el-form-item label="提醒时间">
            <el-row type="flex" align="middle" v-for="(item,index) in reminderTimeList" :key="index">
              <el-col :span="10">
                <SelectNext
                    :value.sync="item.reminderTimeType"
                    :select-options="{options:reminderTimeOption}"
                />
              </el-col>
              <el-col :span="10" :offset="1">
                <el-date-picker
                    v-if="item.reminderTimeType === 6"
                    v-model="item.reminderTimeValue"
                    type="datetime"
                    clearable
                    format="yyyy-MM-dd HH:mm:ss"
                    placeholder="选择日期时间"/>
              </el-col>
              <el-col :span="1" :offset="1">
                <i class="pointer el-icon-remove-outline" @click="onDeleteRow(item.id)"></i>
              </el-col>
            </el-row>
            <el-button type="text" plain class="el-icon-plus mt-6" round @click="addReminderRow">
              添加提醒时间
            </el-button>
          </el-form-item>
          <el-form-item label="关联对象">
            <el-row type="flex" :gutter="6">
              <el-col :span="6">
                <el-select value="客户" disabled>
                  <el-option value="客户" label="客户"></el-option>
                </el-select>
              </el-col>
              <el-col :span="10">
                <el-select :value="[formData.companyName]" multiple disabled>
                  <el-option :value="formData.companyName" :label="formData.companyName"></el-option>
                </el-select>
              </el-col>
            </el-row>

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
            <el-input v-model="scheduleForm.remark" placeholder="请输入内容" type="textarea" resize="none"
                      :rows="3"></el-input>
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
  customCycleValue: 1, //自定义周期-值
  customCycleType: 1, //自定义周期-类型 1.天 2.周 3.月
  cycleEndTime: "",
  reminderTime: "",
  remark: "",//备注
  userIds: [], //参与人ID
  dates: [+new Date(), +new Date()]
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
              picker.$emit('pick', [today, today]);
            }
          },
          {
            text: '明天',
            onClick(picker) {
              const yesterday = new Date();
              yesterday.setTime(yesterday.getTime() + 3600 * 1000 * 24);
              picker.$emit('pick', [yesterday, yesterday]);
            }
          },
          {
            text: '未来7天',
            onClick(picker) {
              const start = new Date()
              const end = new Date();
              end.setTime(end.getTime() + 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '未来30天',
            onClick(picker) {
              const start = new Date()
              const end = new Date();
              end.setTime(end.getTime() + 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '未来90天',
            onClick(picker) {
              const start = new Date()
              const end = new Date();
              end.setTime(end.getTime() + 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
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
      customCycleTypeOption: [
        {value: 1, label: '天'},
        {value: 2, label: '周'},
        {value: 3, label: '月'},
      ],
      reminderTimeOption: [
        {value: 1, label: '不提醒'},
        {value: 2, label: '当天开始(上午9:00)'},
        {value: 3, label: '1天前(上午9:00)'},
        {value: 4, label: '2天前(上午9:00)'},
        {value: 5, label: '1周前(上午9:00)'},
        {value: 6, label: '自定义'},
      ],
      followPersonList: [],
      reminderTimeList: [
        {
          id: +new Date(),
          reminderTimeType: 1,
          reminderTimeValue: ''
        }
      ],
      btnLoading: false,
    }
  },
  watch: {
    formData: {
      handler(newVal) {
        if (!this.scheduleForm.scheduleContent) {
          this.scheduleForm.scheduleContent = newVal.companyName ? `跟进客户：${newVal.companyName}` : ''
        }
      },
      deep: true,
      immediate: true
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
          if (this.followPersonList && this.followPersonList.length) {
            this.scheduleForm.userIds = [].concat(this.followPersonList[0].userId)
          }
        }
      } catch (e) {
        console.error(e)
      }
    },
    async addFormSchedule(data) {
      try {
        this.btnLoading = true
        const res = await addSchedule({...data}).finally(() => {
          this.btnLoading = false
        })
        if (res.code === 200) {
          this.$message.success('添加日程成功')
          this.$emit('onConfirm')
        }
      } catch {
      }
    },
    async editFormSchedule(data) {
      try {
        this.btnLoading = true
        const res = await editSchedule({...data}).finally(() => {
          this.btnLoading = false
        })
        if (res.code === 200) {
          this.$message.success('编辑日程成功')
          this.$emit('onConfirm')
        }
      } catch {
      }
    },
    onConfirm() {
      const {
        dates,
        color,
        remark,
        userIds,
        allDayFlag,
        scheduleContent,
        recurringSchedule,
        customCycleValue,
        customCycleType,
        cycleEndTime,
      } = this.scheduleForm
      let config = {
        color,
        remark,
        userIds,
        scheduleContent,
        recurringSchedule,
        customCycleValue,
        customCycleType,
        cycleEndTime: formatDate(cycleEndTime),
        customerId: this.formData.customerId,
        allDayFlag: Number(allDayFlag),
        scheduleStartTime: allDayFlag ? formatDateSimple(dates[0]) : formatDate(dates[0]),
        scheduleEndTime: allDayFlag ? formatDateSimple(dates[1]) : formatDate(dates[1]),
        reminderTime: this.generateReminderTime(this.reminderTimeList)
      }
      if (!this.formData.scheduleId) {
        config = {...config, customerId: this.formData.customerId}
        this.addFormSchedule(config)
      } else {
        config = {...config, id: this.formData.scheduleId}
        this.editFormSchedule(config)
      }
    },
    onCancel() {
      this.$emit('update:visible', false)
      // 根据情况使用
      this.$emit('onHideDialog')
    },
    addReminderRow() {
      this.reminderTimeList.push({
        id: +new Date(),
        reminderTimeType: 1,
        reminderTimeValue: ''
      })
    },
    onDeleteRow(id) {
      this.reminderTimeList = this.reminderTimeList.filter(val => val.id !== id)
    },
    generateReminderTime(arr) {
      const newList = arr.map(val => {
        delete val.id
        val.reminderTimeValue = formatDate(val.reminderTimeValue)
        return val
      })
      return JSON.stringify(newList)
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
