<template>
  <div class="active-tab">
    <!--    <div class="create-company flex-middle flex-center py-2">-->
    <!--      <p class="fs-14">-->
    <!--        如有新的交易，可在此-->
    <!--        <el-button type="text" style="text-underline: #0d0c0c">新建商机</el-button>-->
    <!--        进行管理。-->
    <!--      </p>-->
    <!--    </div>-->
    <div class="mt-20" v-if="!showWriteFollow">
      <div class="flex-middle space-between">
        <div class="fs-14 bold">写跟进</div>
        <div class="flex-middle gap-8">
          <el-popover v-model="showTemplatePopover" width="280" trigger="click" placement="bottom-end"
                      :append-to-body="false">
            <el-row>
              <div class="flex-middle space-between">
                <span>点击选择模板，可自动填充到输入框</span>
                <i class="el-icon-setting pl-10 pointer"
                   @click="targetBlank('/company/customer-setting?tab=followText')"></i>
              </div>
              <div style="max-height: 300px;overflow-y: auto">
                <div class="template-card py-8 px-16 mt-8" v-for="(text,index) in followTextList" :key="index"
                     @click="chooseTemplateText(text)">
                  <p class="template-title bold">{{ text.name }}</p>
                  <p class="template-content fs-12">{{ text.content }}</p>
                </div>
              </div>

            </el-row>
            <el-button round size="small" slot="reference">选择模板</el-button>
          </el-popover>
          <el-button v-if="!scheduleList.length" round size="small" @click="dialogSchedule=true">添加日程</el-button>
        </div>

      </div>
      <el-input class="mt-10" placeholder="点击这里记录跟进细节，同步最新进展。" @focus="showWriteFollow=true">
        <template #suffix>
          <el-tooltip placement="top" content="展开">
            <i class="el-icon-full-screen pointer mr-6" @click.stop="templateVisible=true"></i>
          </el-tooltip>
        </template>
      </el-input>
    </div>
    <div class="mt-20" v-else>
      <WriteFollow :row="row" :echoData="templateData" show-full-screen-icon @onFullScreen="templateVisible=true"
                   @onCancel="onCancelWriteFollow" @onConfirm="onConfirmWriteFollow">
        <template #right>
          <el-popover v-model="showTemplatePopover" width="280" trigger="click" placement="bottom-end"
                      :append-to-body="false">
            <el-row>
              <div class="flex-middle space-between">
                <span>点击选择模板，可自动填充到输入框</span>
                <i class="el-icon-setting pl-10 pointer"
                   @click="targetBlank('/company/customer-setting?tab=followText')"></i>
              </div>
              <div style="max-height: 300px;overflow-y: auto">
                <div class="template-card py-8 px-16 mt-8" v-for="(text,index) in followTextList" :key="index"
                     @click="chooseTemplateText(text)">
                  <p class="template-title bold">{{ text.name }}</p>
                  <p class="template-content fs-12">{{ text.content }}</p>
                </div>
              </div>

            </el-row>
            <el-button round size="medium" slot="reference">选择模板</el-button>
          </el-popover>
        </template>
      </WriteFollow>
    </div>
    <!--  日程  -->
    <template v-if="options.isShowSchedule">
      <div class="mt-20" v-if="scheduleList.length">
        <div class="flex-middle space-between">
          <div class="fs-14 bold">计划日程</div>
          <el-button round size="small" @click="dialogSchedule=true">添加日程</el-button>
        </div>
        <ul class="plan-ul">
          <li class="plan-li pointer" v-for="(schedule,index) in scheduleList" :key="index">
            <el-popover :append-to-body="false">
              <template #default>
                <div style="width: 400px">内容</div>
              </template>
              <template #reference>
                <div class="flex-middle" style="width: 100%">
                  <div class="circle" :style="{color:schedule.color}"></div>
                  <span class="date">{{ formatMonthAndDay(schedule.scheduleStartTime) }}</span>
                  <span class="content flex1">{{ schedule.scheduleContent }}</span>
                </div>
              </template>
            </el-popover>
          </li>
        </ul>
      </div>
    </template>

    <div class="mt-20">
      <div class="flex-middle space-between">
        <div class="fs-14 bold">历史动态 <i class="el-icon-refresh"></i></div>
        <el-row type="flex" class="gap-10">
          <i class="el-icon-search fs-14 pointer">&nbsp;搜索</i>
          <i class="el-icon-s-operation fs-14 pointer">&nbsp;筛选</i>
        </el-row>
      </div>
      <div class="mt-10 flex-middle space-between">
        <el-row>
          <el-button type="primary" size="small">全部({{ timeLineList.length }})</el-button>
          <template v-if="timeLineList.length">
            <el-button size="small">跟进记录</el-button>
            <el-button size="small" :disabled="true">往来邮件</el-button>
            <el-button size="small" :disabled="true">聊天记录</el-button>
            <el-button size="small" :disabled="true">其他</el-button>
          </template>

        </el-row>
        <div
            class="flex-middle fs-14 pointer"
            @click="onSortTime"
        >
              <span class="caret-wrapper">
                <i class="sort-caret ascending" :class="{'active':sortActive==='1'}"></i>
                <i class="sort-caret descending" :class="{'active':sortActive==='2'}"></i>
              </span>
          <span class="lineH-10">时间</span>
        </div>
      </div>
      <div class="customer-timeline mt-20">
        <el-timeline v-if="timeLineList.length">
          <el-timeline-item
              placement="top"
              v-for="(item, index) in timeLineList"
              :key="index"
              icon="el-icon-document"
              :type="item.type"
              color="#0bbd87"
              :size="item.size">
            <el-card shadow="hover">
              <div class="card-header">
                <div class="flex-middle space-between mx-20 py-10">
                  <div class="flex-middle">
                    <span class="fs-12 bold">{{ mapFollowUpType[item.followUpType] }}</span>
                    <div class="line"></div>
                    <span class="fs-12">操作人：{{ item.operator }}</span>
                  </div>
                  <div class="fs-12">
                    {{ item.timestamp }}
                  </div>
                </div>
              </div>
              <div class="card-content">
                <div class="message px-20">
                  <div class="pt-10">{{ item.followUpContent }}</div>
                  <div class="pt-10">关联联系人: {{ generatePerson(item.followUpContactId) }}</div>
                  <div class="flex-end flex-middle">
                    <el-row type="flex" :gutter="10" v-if="item.editable">
                      <el-col>
                        <el-tooltip placement="top" content="可删除24小时内您发布的跟进">
                          <DelPopover self-slot :id="item.id" @onDelete="onDeleteRecord">
                            <i class="el-icon-delete pointer fs-12"></i>
                          </DelPopover>
                        </el-tooltip>
                      </el-col>
                      <el-col>
                        <el-tooltip placement="top" content="编辑评论">
                          <i class="el-icon-edit pointer fs-12" @click="onEditTemplate(item)"></i>
                        </el-tooltip>
                      </el-col>
                    </el-row>
                    <el-button class="ml-10" style="color:#303133" type="text" icon="el-icon-s-comment" size="small"
                               @click="onAdd(item.id)">
                      评论
                    </el-button>
                  </div>
                </div>
                <div class="comment mt-20 px-20">
                  <template v-if="item.add">
                    <el-input v-model="item.newComment" placeholder="请输入评论内容" type="textarea" resize="none"
                              :rows="3" size="small"
                              @keydown.enter.native="onConfirmAddComment(item)"/>
                    <div class="flex-end py-10">
                      <el-button round size="small" @click="onCancelAdd(item.id)">取消</el-button>
                      <el-button type="primary" round size="small" @click="onConfirmAddComment(item)">确认</el-button>
                    </div>
                  </template>
                  <template v-if="item.commentList">
                    <div class="comment-item flex-column mb-10" v-for="comment in item.commentList" :key="comment.id">
                      <template v-if="!comment.edit">
                        <div class="flex-middle space-between">
                          <el-row type="flex" align="middle" class="fs-14">
                            <i class="el-icon-s-comment"></i>
                            <span class="pl-14 gray-text">{{ comment.operator }}</span>
                            <span class="pl-10 gray-text">{{ formatDate(comment.operatorTime) }}</span>
                          </el-row>
                          <el-row class="icon-operate" :gutter="8">
                            <el-col>
                              <el-tooltip placement="top" content="删除评论">
                                <DelPopover self-slot :id="comment.id" @onDelete="onDelete">
                                  <i class="el-icon-delete pointer"></i>
                                </DelPopover>
                              </el-tooltip>
                            </el-col>
                            <el-col>
                              <el-tooltip placement="top" content="编辑评论">
                                <i class="el-icon-edit pointer" @click="onEdit(item.id,comment,true)"></i>
                              </el-tooltip>
                            </el-col>
                          </el-row>
                        </div>
                        <div class="comment-content mt-10">
                          <span>{{ comment.comment }}</span>
                        </div>
                      </template>
                      <template v-else>
                        <el-input v-model="comment.comment" placeholder="请输入评论内容" type="textarea" resize="none"
                                  :rows="3" size="small"
                                  @keydown.enter.native="onConfirmEditComment(item.id,comment)"/>
                        <div class="flex-end pt-10">
                          <el-button round size="small" @click="onEdit(item.id,comment,false)">取消</el-button>
                          <el-button type="primary" round size="small" @click="onConfirmEditComment(item.id,comment)">
                            确认
                          </el-button>
                        </div>
                      </template>

                    </div>
                  </template>

                </div>
              </div>
            </el-card>

          </el-timeline-item>

        </el-timeline>
        <el-empty v-else :image-size="100"/>
      </div>
    </div>
    <template>
      <DialogTemplateFollow
          v-if="templateVisible" :visible.sync="templateVisible" :row="templateDrawerRow"
          @close="templateVisible = false" @onConfirm="onConfirmTemplateFollow"/>
    </template>
    <template>
      <DialogSchedule v-if="dialogSchedule" :visible.sync="dialogSchedule" :formData="row"/>
    </template>
  </div>
</template>

<script>
import DialogTemplateFollow from "./DialogTemplateFollow.vue";
import WriteFollow from "./WriteFollow.vue";
import DelPopover from "@/views/company/customer/DelPopover.vue";
import DialogSchedule from "@/views/customer/list/DialogSchedule.vue";
import {targetBlank} from '@/utils/tools'
import {addRecordsComment, deleteRecordsComment, editRecordsComment} from "@/api/customer/comment";
import {followTextTemplateList} from "@/api/company/followText";
import {getScheduleList} from "@/api/customer/schedule";
import {formatDate, formatMonthAndDay} from "@/utils";
import {deleteFollowUpRecords, getFollowUpRecordsList} from "@/api/customer/records";
import {searchFollowerCustomer} from "@/api/customer/publicleads";

export default {
  props: {
    row: {
      type: Object,
      default: () => {
      },
      required: false
    },
    options: {
      type: Object,
      default: () => {
        return {
          isShowSchedule: true
        }
      },
      required: false
    }
  },
  components: {
    DialogSchedule,
    DialogTemplateFollow,
    WriteFollow,
    DelPopover
  },
  data() {
    return {
      showWriteFollow: false,
      templateVisible: false,
      sortActive: "2",
      timeLineList: [],
      tempEditValue: '',
      templateDrawerRow: {},
      followTextList: [],
      templateData: {},
      showTemplatePopover: false,
      scheduleList: [],
      dialogSchedule: false,
      followContactList: [],
      mapFollowUpType: {
        1: '快速记录',
        2: '电话',
        3: '会面',
        4: '社交平台',
      }
    }
  },
  mounted() {
    this.getTemplateList()
  },
  watch: {
    row: {
      handler(newVal) {
        if (newVal?.customerId) {
          this.getScheduleList()
          this.getRecordList()
          this.getFollowPerson()
        }
      },
      deep: true,
      immediate: true
    }
  },
  methods: {
    formatDate,
    // 快捷模板
    async getTemplateList() {
      try {
        const res = await followTextTemplateList()
        if (res.code === 200) {
          this.followTextList = res.data
        }
      } catch (e) {
        console.error(e)
      }
    },
    // 发布跟进列表
    async getRecordList() {
      try {
        const res = await getFollowUpRecordsList({
          customerId: this.row.customerId,
        })
        if (res.code === 200) {
          this.timeLineList = res.data
          this.timeLineList?.map(val => {
            if (val.comments && val.comments.length) {
              val.comments.map(comment => {
                comment.edit = false
                return comment
              })
            }
            val.add = false
            val.newComment = ''
            return val
          })
        }
      } catch (e) {
        console.error(e)
      }
    },
    // 关联联系人
    async getFollowPerson() {
      try {
        const res = await searchFollowerCustomer({
          id: this.row.customerId
        })
        if (res.code === 200) {
          this.followContactList = res.data
        }
      } catch {
      }
    },
    generatePerson(id) {
      let personName = ''
      this.followContactList.forEach(val => {
        if (val.userId === id) {
          personName = val.nickName
        }
      })
      return personName || '---'
    },
    // 日程列表
    async getScheduleList() {
      if (!this.options.isShowSchedule) {
        return
      }
      try {
        const res = await getScheduleList({
          customerId: this.row.customerId,
          pageNum: 1,
          pageSize: 3,
        })
        if (res.code === 200) {
          this.scheduleList = res.rows
        }
      } catch {
      }
    },
    chooseTemplateText(text) {
      if (!this.showWriteFollow) {
        this.showWriteFollow = true
      }
      this.templateData = {
        id: +new Date(),
        name: text.name,
      }
      this.showTemplatePopover = false
    },
    onCancelWriteFollow() {
      this.showWriteFollow = false
      this.templateData = {}
    },
    async onConfirmWriteFollow() {
      await this.getRecordList()
      this.onCancelWriteFollow()
    },
    onSortTime() {
      const mapSort = {
        "1": "2",
        "2": "1"
      }
      this.sortActive = mapSort[this.sortActive]
    },
    async addComment(item) {
      try {
        const res = await addRecordsComment({
          followUpRecordsId: item.id,
          comment: item.newComment
        })
        if (res.code === 200) {
          this.$message.success('添加成功')
          this.onCancelAdd(item.id)
          await this.getRecordList()
        }
      } catch {
      }
    },
    onConfirmAddComment(item) {
      if (!item.newComment) {
        this.$message.warning('添加评论内容不能为空')
        return
      }
      this.addComment(item)
    },
    onAdd(id) {
      const target = this.timeLineList.findIndex(val => val.id === id)
      if (target === -1) {
        return
      }
      this.$set(this.timeLineList, target, {...this.timeLineList[target], add: true})
    },
    onCancelAdd(id) {
      const target = this.timeLineList.findIndex(val => val.id === id)
      if (target === -1) {
        return
      }
      this.$set(this.timeLineList, target, {...this.timeLineList[target], add: false, newComment: ''})
    },
    // 修改写跟进
    onEditTemplate(item) {
      this.templateDrawerRow = {
        ...item,
        customerId: this.row?.customerId,
        allDayFlag: Boolean(item.allDayFlag)
      }
      this.templateVisible = true
    },
    onEdit(itemId, comment, bool) {
      !this.tempEditValue && (this.tempEditValue = comment?.comment)
      this.timeLineList = this.timeLineList.map(val => {
        if (val.id === itemId) {
          val.commentList?.map(c => {
            if (c.id === comment?.id) {
              if (!bool) {
                c.comment = this.tempEditValue
                this.tempEditValue = ''
              }
              c.edit = bool
            }
            return c
          })
        }
        return val
      })

    },
    async onConfirmEditComment(itemId, comment) {
      if (!comment.comment) {
        this.$message.warning('编辑评论内容不能为空')
        return
      }
      try {
        const res = await editRecordsComment({
          id: comment.id,
          comment: comment.comment
        })
        if (res.code === 200) {
          this.$message.success('编辑成功')
          this.tempEditValue = comment.comment
          this.onEdit(itemId, comment, false)
        }
      } catch {
      }
    },
    async onDelete(id) {
      try {
        const res = await deleteRecordsComment({id})
        if (res.code === 200) {
          this.$message.success('删除成功')
          await this.getRecordList()
        }
      } catch {
      }
    },
    async onDeleteRecord(id) {
      try {
        const res = await deleteFollowUpRecords({id})
        if (res.code === 200) {
          this.$message.success('删除成功')
          await this.getRecordList()
        }
      } catch {
      }
    },
    // 确认跟进操作
    onConfirmTemplateFollow() {
      this.getRecordList()
      this.templateVisible = false
    },
    targetBlank,
    formatMonthAndDay,
  }
}
</script>

<style lang="scss" scoped>
.active-tab {
  .template-card {
    background-color: rgba(247, 248, 251);
    border-radius: 4px;
    border: 1px solid rgb(235, 237, 240);
    word-break: break-all;
    cursor: pointer;

    &:hover {
      background-color: rgba(236, 249, 255)
    }

    p {
      margin: 0;
    }

    > .template-title {
      overflow: hidden;
      display: -webkit-box;
      -webkit-box-orient: vertical;
      -webkit-line-clamp: 2;
      color: rgb(37, 40, 46)
    }

    > .template-content {
      white-space: pre-wrap;
      color: rgb(37, 40, 46);
      margin-top: 8px !important;
    }
  }

  .create-company {
    border: 1px solid #f0f0f0;

    p {
      margin-block-start: 0;
      margin-block-end: 0;
    }
  }

  ::v-deep .el-input__suffix {
    display: flex;
    align-items: center;
  }

  .caret-wrapper {
    display: inline-flex;
    flex-direction: column;
    align-items: center;
    height: 34px;
    width: 24px;
    vertical-align: middle;
    cursor: pointer;
    overflow: initial;
    position: relative;
  }

  .plan-ul {
    padding-inline-start: 0;
  }

  .plan-ul > .plan-li {
    list-style: none;
    display: flex;
    align-items: center;
    font-size: 14px;

    .circle {
      width: 8px;
      height: 8px;
      background: rgb(255, 51, 51);
      border-radius: 8px;
      padding: 0 4px;
    }

    .date {
      width: 102px;
      color: #0a6aff;
      word-wrap: break-word;
      padding-left: 4px;
    }

    .content {
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
      color: rgba(37, 40, 46)
    }
  }

  .sort-caret {
    width: 0;
    height: 0;
    border: solid 5px transparent;
    position: absolute;
    left: 7px;
  }

  .sort-caret.ascending {
    border-bottom-color: #C0C4CC;
    top: 5px;
  }

  .sort-caret.descending {
    border-top-color: #C0C4CC;
    bottom: 7px;
  }

  .sort-caret.ascending.active {
    border-bottom-color: #1890ff;
  }

  .sort-caret.descending.active {
    border-top-color: #1890ff;
  }

  .customer-timeline {
    ::v-deep .el-timeline-item__node {
      width: 20px;
      height: 20px;
      left: -5px;
    }

    ::v-deep .el-timeline {
      margin-block-start: 0;
      margin-block-end: 0;
      padding-inline-start: 10px;
    }

    ::v-deep .el-timeline-item__timestamp.is-top {
      padding: 0;
      margin: 0;
    }

    ::v-deep .el-card__body {
      padding: 0 0 20px 0;
    }

    .card-header {
      border-bottom: 1px solid rgba(0, 0, 0, .06);
      background-color: #ecf5ff;

      .line {
        width: 1px;
        height: 12px;
        background-color: rgba(0, 0, 0, .06);
        margin: 0 6px;
      }
    }

    .card-content {
      .message {
        border-bottom: 1px solid rgba(0, 0, 0, .06);
      }
    }

    .card-content {
      .comment-item {

        .icon-operate {
          display: none;
        }

        &:hover .icon-operate {
          display: flex !important;
        }
      }
    }


  }


}

</style>
