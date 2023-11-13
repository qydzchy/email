<template>
  <el-row class="write-follow mt-10 px-16">
    <div>
      <span class="fs-13">跟进类型</span>
      <el-select class="auto-select ml-10" v-model="formData.followType" size="small">
        <el-option v-for="(item,index) in followOptionList" :value="item.value" :label="item.label"
                   :key="index"></el-option>
      </el-select>
    </div>
    <div class="textarea-card my-10">
      <div class="header px-12">
        <div class="flex-middle space-between">
          <div>
            <span class="fs-13">快捷文本分组</span>
            <el-select class="auto-select ml-10" v-model="formData.fastType" size="small">
              <el-option v-for="(item,index) in fastOptionList" :value="item.value" :label="item.label"
                         :key="index"></el-option>
            </el-select>
          </div>
          <div>
            <el-popover v-model="fastPopover" :append-to-body="false" :popper-options="{positionFixed:true}">
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
                  ></el-select>
                  <div class="operate flex-end">
                    <el-button size="small" @click="fastPopover=false">取消</el-button>
                    <el-button size="small" type="primary">确认</el-button>
                  </div>
                </div>
              </template>
              <el-button type="text" icon="el-icon-setting" slot="reference">设置个人快捷文本</el-button>
            </el-popover>
            <el-tooltip placement="top" content="展开" v-if="showFullScreenIcon">
              <i class="el-icon-full-screen pointer ml-6" @click.stop="$emit('onFullScreen')"></i>
            </el-tooltip>
          </div>
        </div>
      </div>
      <div class="main">
        <el-input v-model="formData.followText" type="textarea" resize="none" :rows="6"
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
                key="fastTextTime"
                style="width:180px"
                size="small"
                v-model="formData.fastTextTime"
                placeholder="请选择日期"
                clearable
                type="datetime"
                :picker-options="pickerOptions"
                align="left"
              ></el-date-picker>
            </div>
            <div class="flex-middle">
              <span class="fs-13 pl-6">跟进联系人</span>
              <el-select class="auto-select ml-10" v-model="formData.followContact" size="small">
                <el-option v-for="(item,index) in followContactList" :value="item.value" :label="item.label"
                           :key="index"></el-option>
              </el-select>
            </div>
          </el-row>

        </div>
      </div>
    </div>
    <el-row class="flex-middle py-10">
      <span class="fs-13">关联业务数据</span>
      <div class="ml-10">
        <el-select v-model="formData.businessData" size="small" style="width:200px">

        </el-select>
      </div>
      <div>
        <el-button size="small">选择</el-button>
      </div>
    </el-row>
    <el-row class="flex-middle py-10">
      <span class="fs-13">下次跟进日程</span>
      <div class="ml-10">
        <el-date-picker
          v-if="formData.nextFollowFullDay"
          key="isFullDay"
          style="width:200px"
          size="small"
          v-model="formData.nextFollowDate"
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
          v-model="formData.nextFollowDate"
          placeholder="请选择日期"
          clearable
          type="datetime"
          :picker-options="pickerOptions"
          align="left"
        ></el-date-picker>
        <el-checkbox class="ml-10" v-model="formData.nextFollowFullDay">全天</el-checkbox>
        <el-button class="ml-20" type="text" v-if="!isFullEdit" @click="isFullEdit=true">完整编辑</el-button>
      </div>
    </el-row>
    <el-row v-show="isFullEdit">
      <el-form>
        <el-form-item>
          <div>日程内容</div>
          <el-input placeholder="跟进客户："></el-input>
        </el-form-item>
        <el-form-item>
          <div>备注</div>
          <div class="remark-wrap">
            <el-input v-model="formData.remark" type="textarea" resize="none" :rows="6"
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
      <el-button round size="small">取消</el-button>
      <el-button round type="primary" size="small">确认</el-button>
    </el-row>
  </el-row>
</template>

<script>
export default {
  props: {
    showFullScreenIcon: {
      type: Boolean,
      default: false,
      required: false
    }
  },
  data() {
    return {
      formData: {
        followType: 'fastWrite',
        followText: '',
        followContact: '测试',
        fastType: 'selfGroup',
        fastTextTime: '',
        fastTextTag: [],
        nextFollowDate: '',
        businessData: '',
        nextFollowFullDay: true,
        remark: ''
      },
      fastPopover: false,
      followOptionList: [
        {
          value: 'fastWrite',
          label: '快速记录',
        },
        {
          value: 'phone',
          label: '电话',
        },
        {
          value: 'faceToFace',
          label: '会面',
        },
        {
          value: 'communityPlatform',
          label: '社交平台',
        },
      ],
      followContactList: [
        {value: '测试', label: '测试'}
      ],
      fastOptionList: [
        {
          value: 'selfGroup',
          label: '个人分组',
        },
        {
          value: 'talk',
          label: '洽谈',
        },
        {
          value: 'unmatched',
          label: '未成交',
        },
        {
          value: 'following',
          label: '跟单中',
        },
        {
          value: 'fallback',
          label: '售后',
        },
        {
          value: 'retain',
          label: '客情维系',
        },
      ],
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
      isFullEdit: false,
    }
  },
  computed: {
    datePickerType() {
      return !this.formData.nextFollowFullDay ? 'datetime' : 'date'
    }
  },
  methods: {}
}
</script>

<style lang="scss" scoped>
.write-follow {

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
