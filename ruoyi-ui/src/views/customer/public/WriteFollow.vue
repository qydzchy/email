<template>
  <el-row class="write-follow mt-10 px-16">
    <div class="flex-middle space-between">
      <div>
        <span class="fs-13">跟进类型</span>
        <el-select class="auto-select ml-10" v-model="formData.followUpType" size="small">
          <el-option v-for="(item,index) in followOptionList" :value="item.value" :label="item.label"
                     :key="index"></el-option>
        </el-select>
      </div>
      <div>
        <slot name="right"></slot>
      </div>
    </div>
    <div class="textarea-card my-10">
      <div class="header px-12">
        <div class="flex-middle space-between">
          <div>
            <span class="fs-13">快捷文本分组</span>
            <el-select class="auto-select ml-10" v-model="formData.fastType" size="small">
              <el-option v-for="(item,index) in fastOptionList" :value="item.id" :label="item.name"
                         :key="index"></el-option>
            </el-select>
          </div>
          <div>
            <el-popover
                v-if="fastOptionList.length"
                v-model="fastPopover"
                :append-to-body="false"
                class="write-follow-tag"
            >
              <template #default>
                <div>
                  <div class="fs-13">可点击回车连续添加快捷文本，最多20个</div>
                  <el-select
                      v-model="formData.fastTextTag"
                      style="width: 100%"
                      class="fast-select my-10"
                      size="small" multiple
                      allow-create
                      filterable default-first-option
                      :popper-append-to-body="false"
                  >
                  </el-select>
                  <div class="operate flex-end">
                    <el-button size="small" round @click="onCancelAddTag">取消</el-button>
                    <el-button size="small" round type="primary" @click="onConfirmAddTag">确认</el-button>
                  </div>
                </div>
              </template>
              <el-button type="text" icon="el-icon-setting" slot="reference">设置快捷文本</el-button>
            </el-popover>
            <el-button v-else type="text" icon="el-icon-setting" slot="reference"
                       @click="targetBlank('/company/customer-setting?tab=followText')">设置快捷文本
            </el-button>
            <el-tooltip placement="top" content="展开" v-if="showFullScreenIcon">
              <i class="el-icon-full-screen pointer ml-6" @click.stop="$emit('onFullScreen')"></i>
            </el-tooltip>
          </div>
        </div>
      </div>
      <div class="main">
        <div class="tag flex-middle px-16 gap-6">
          <el-tag class="pointer" effect="plain" v-for="(tag,idx) in fastTagList" :key="idx"
                  @click="joinFollowText(tag)">
            {{ tag }}
          </el-tag>
        </div>
        <el-input v-model="formData.followUpContent" type="textarea" resize="none" :rows="6"
                  placeholder="请输入内容，可以将附件拖拽至此，快速上传"></el-input>
      </div>
      <div class="foot px-16 pb-10">
        <div class="upload-file">

        </div>
        <div class="tool flex-middle space-between">
          <div class="flex-middle gap-8">
            <el-tooltip content="附件">
              <i class="el-icon-paperclip pointer"></i>
            </el-tooltip>
            <el-tooltip content="图片">
              <i class="el-icon-picture-outline pointer"></i>
            </el-tooltip>
          </div>
          <el-row class="flex-middle">
            <div class="auto-date-picker flex-middle">
              <span class="fs-13 pr-6">时间</span>
              <el-date-picker
                  key="submissionTime"
                  style="width:180px"
                  size="small"
                  v-model="formData.submissionTime"
                  placeholder="请选择日期"
                  clearable
                  type="datetime"
                  :picker-options="submissionPickerOptions"
                  align="left"
                  format="yyyy-MM-dd HH:mm:ss"
              ></el-date-picker>
            </div>
            <div class="flex-middle">
              <span class="fs-13 pl-6">跟进联系人</span>
              <el-select class="auto-select ml-10" v-model="formData.followUpContactId" size="small">
                <el-option v-for="(item,index) in followContactList" :value="item.userId" :label="item.nickName"
                           :key="index"></el-option>
              </el-select>
            </div>
          </el-row>

        </div>
      </div>
    </div>
    <!--    <el-row class="flex-middle py-10">-->
    <!--      <span class="fs-13">关联业务数据</span>-->
    <!--      <div class="ml-10">-->
    <!--        <el-select v-model="formData.businessData" size="small" style="width:200px">-->

    <!--        </el-select>-->
    <!--      </div>-->
    <!--      <div>-->
    <!--        <el-button size="small">选择</el-button>-->
    <!--      </div>-->
    <!--    </el-row>-->
    <el-row class="flex-middle py-10">
      <span class="fs-13">下次跟进日程</span>
      <div class="ml-10">
        <el-date-picker
            v-if="formData.allDayFlag"
            key="isFullDay"
            style="width:200px"
            size="small"
            v-model="formData.nextFollowUpSchedule"
            placeholder="请选择日期"
            clearable
            format="yyyy-MM-dd"
            :picker-options="pickerOptions"
            align="left"
        ></el-date-picker>
        <el-date-picker
            v-else
            key="isNotFullDay"
            style="width:200px"
            size="small"
            v-model="formData.nextFollowUpSchedule"
            placeholder="请选择日期"
            clearable
            type="datetime"
            :picker-options="pickerOptions"
            align="left"
            format="yyyy-MM-dd HH:mm:ss"
        ></el-date-picker>
        <el-checkbox class="ml-10" v-model="formData.allDayFlag">全天</el-checkbox>
        <el-button class="ml-20" type="text" v-if="!isFullEdit" @click="isFullEdit=true">完整编辑</el-button>
      </div>
    </el-row>
    <el-row v-show="isFullEdit">
      <el-form>
        <el-form-item>
          <div>日程内容</div>
          <el-input v-model="formData.scheduleContent" placeholder="跟进客户"></el-input>
        </el-form-item>
        <el-form-item>
          <SelectTagColor is-hover :checked-color.sync="formData.color" :color-map="colorMap"/>
        </el-form-item>
        <el-form-item>
          <div>备注</div>
          <div class="remark-wrap">
            <el-input v-model="formData.remarks" type="textarea" resize="none" :rows="6"
                      placeholder="请输入内容，可以将附件拖拽至此，快速上传"></el-input>
            <div class="px-16 pb-10 flex-middle gap-8">
              <el-tooltip content="附件">
                <i class="el-icon-paperclip pointer"></i>
              </el-tooltip>
              <el-tooltip content="图片">
                <i class="el-icon-picture-outline pointer"></i>
              </el-tooltip>
            </div>
          </div>
        </el-form-item>
      </el-form>
    </el-row>
    <!--  operate  -->
    <el-row class="write-operate flex-end">
      <el-button round size="small" @click="onCancel">取消</el-button>
      <el-button round type="primary" size="small" @click="onConfirm">确认</el-button>
    </el-row>
  </el-row>
</template>

<script>
import SelectTagColor from "@/views/components/SelectTagColor/index.vue";
import {deepClone, formatDate, formatDateSimple} from "@/utils";
import {followTextQuickEdit, followTextQuickList} from "@/api/company/followText";
import {targetBlank} from "@/utils/tools";
import {searchFollowerCustomer} from "@/api/customer/publicleads";
import {addFollowUpRecords, editFollowUpRecords} from "@/api/customer/records";

const initFormData = {
  customerId: null,//客户ID
  followUpType: 1,//跟进类型 1.快速记录 2.电话 3.会面 4.社交平台
  followUpContent: '', //跟进内容
  followUpContactId: '',//跟进联系人ID
  fastType: '',
  submissionTime: +new Date(),//提交时间
  fastTextTag: [],
  nextFollowUpSchedule: '', //下次跟进日程
  businessData: '',
  allDayFlag: true,//全天 0.否 1.是
  remarks: '',//备注
  scheduleContent: '',//日程内容
  color: '#ff3333'//颜色
}
export default {
  components: {SelectTagColor},
  props: {
    row: {
      type: Object,
      default: () => {
      },
      required: false
    },
    echoData: {
      type: Object,
      default: () => {
      },
      required: false
    },
    showFullScreenIcon: {
      type: Boolean,
      default: false,
      required: false
    },
  },
  data() {
    return {
      formData: {
        ...deepClone(initFormData)
      },
      fastPopover: false,
      fastOptionList: [],
      followOptionList: [
        {
          value: 1,
          label: '快速记录',
        },
        {
          value: 2,
          label: '电话',
        },
        {
          value: 3,
          label: '会面',
        },
        {
          value: 4,
          label: '社交平台',
        },
      ],
      followContactList: [
        {value: '测试', label: '测试'}
      ],
      submissionPickerOptions: {
        disabledDate: this.disabledDate
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
          }],
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
      isFullEdit: false,
    }
  },
  watch: {
    row: {
      handler(newVal) {
        if (!newVal?.id) {
          return
        }
        this.formData = {
          ...this.formData,
          ...newVal
        }
      },
      deep: true,
      immediate: true
    },
    echoData: {
      handler(newVal) {
        if (!newVal?.name) {
          return
        }
        this.joinFollowText(newVal.name)
      },
      deep: true,
      immediate: true
    }
  },
  computed: {
    fastTagList() {
      if (!this.formData.fastType) {
        return []
      }
      let option = []
      this.fastOptionList.map(val => {
        if (val.id === this.formData.fastType) {
          option = val.label
        }
      })
      this.formData.fastTextTag = option
      return option
    }
  },
  mounted() {
    this.getFastTag()
    this.getFollowPerson()
  },
  methods: {
    async getFastTag() {
      try {
        const res = await followTextQuickList()
        if (res.code === 200) {
          this.fastOptionList = res.data?.map((val, index) => {
            if (index === 0) {
              this.formData.fastType = val.id
            }
            val.label = val.label?.split(';') || ''
            return val
          })
        }
      } catch {
      }
    },
    // 关联联系人
    async getFollowPerson() {
      if (!this.row.customerId) return
      try {
        const res = await searchFollowerCustomer({
          id: this.row.customerId
        })
        if (res.code === 200) {
          this.followContactList = res.data
          this.followContactList?.forEach((val, index) => {
            if (index === 0) {
              this.formData.followUpContactId = val.userId
            }
          })
        }
      } catch {
      }
    },
    disabledDate(time) {
      return time.getTime() > Date.now()
    },
    onCancelAddTag() {
      let option = []
      this.fastOptionList.map(val => {
        if (val.id === this.formData.fastType) {
          option = val.label
        }
      })
      this.formData.fastTextTag = option
      this.fastPopover = false
    },
    async onConfirmAddTag() {
      if (!this.formData.fastTextTag.length) {
        return
      }
      let config = {
        id: this.formData.fastType,
        name: '',
        label: this.formData.fastTextTag?.join(';') || ''
      }
      this.fastOptionList.forEach(val => {
        if (val.id === config.id) {
          config.name = val.name
        }
      })
      try {
        const res = await followTextQuickEdit({...config})
        if (res.code === 200) {
          this.$message.success('修改成功')
          this.fastPopover = false
          await this.getFastTag()
        }
      } catch {

      }

    },
    joinFollowText(text) {
      if (!text) {
        return
      }
      this.formData.followUpContent = this.formData.followUpContent + text
    },
    onCancel() {
      this.formData = {
        ...deepClone(initFormData)
      }
      this.$emit('onCancel')
    },
    async onConfirm() {
      console.log(this.formData)
      const {
        id,
        followUpType,
        followUpContent,
        submissionTime,
        followUpContactId,
        nextFollowUpSchedule,
        allDayFlag,
        scheduleContent,
        color,
        remarks
      } = this.formData
      let config = {
        followUpType,
        followUpContent,
        submissionTime: formatDate(submissionTime),
        followUpContactId,
        nextFollowUpSchedule: allDayFlag ? formatDateSimple(nextFollowUpSchedule) : formatDate(nextFollowUpSchedule),
        allDayFlag: Number(allDayFlag),
        scheduleContent,
        color,
        remarks
      }
      if (id) {
        config = {...config, id: id}
        await this.editFollowUp(config)
      } else {
        config = {...config, customerId: this.row?.customerId}
        await this.addFollowUp(config)
      }
    },
    async addFollowUp(config) {
      if (!config.customerId) {
        return
      }
      try {
        const res = await addFollowUpRecords({...config})
        if (res.code === 200) {
          this.$message.success("添加成功")
          this.$emit('onConfirm')
        }
      } catch {
      }
    },
    async editFollowUp(config) {
      try {
        const res = await editFollowUpRecords({...config})
        if (res.code === 200) {
          this.$message.success("修改成功")
          this.$emit('onConfirm')
        }
      } catch {
      }
    },
    targetBlank
  }
}
</script>

<style lang="scss" scoped>
.write-follow {
  .write-follow-tag {
    ::v-deep .el-select-dropdown {
      display: none !important;
    }
  }

  .write-operate {
    padding: 10px 0;
  }
}

.auto-select {
  ::v-deep .el-input__inner {
    border: unset;
    width: 100px;

    &:active {
      border: unset;
    }

    &:hover {
      background-color: #f5f5f5;
    }
  }
}

.textarea-card {
  border-radius: 4px;
  width: 100%;
  border: 1px solid rgb(217, 217, 217);

  &:hover {
    border-color: rgb(64, 169, 255);
    box-shadow: rgba(24, 144, 255, 0.2) 0 0 0 2px;
  }

  .header {
    .fast-select {
      ::v-deep .el-input__suffix {
        display: none;
      }
    }
  }

  .foot {
    .auto-date-picker {
      ::v-deep .el-input__inner {
        border: unset;

        &:hover {
          border: unset;
        }
      }
    }
  }

  ::v-deep .el-textarea__inner {
    border: unset;

    &:hover {
      border: unset;
    }
  }
}

.remark-wrap {
  border: 1px solid rgb(217, 217, 217);

  &:hover {
    border-color: rgb(64, 169, 255);
    box-shadow: rgba(24, 144, 255, 0.2) 0 0 0 2px;
  }

  ::v-deep .el-textarea__inner {
    border: unset;

    &:hover {
      border: unset;
    }
  }
}
</style>
