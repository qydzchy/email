<template>
  <div v-if="searchPage">
    <el-form :model="formData">
      <div class="okki-modal-root">
        <div class="okki-modal-mask"></div>
        <div tabindex="-1" class="okki-modal-wrap okki-modal-centered" role="dialog" aria-labelledby="vcDialogTitle1">
          <div class="okki-modal main-toolbar-search-advanced-search" role="document" style="width: 500px; transform-origin: 1076px 45px;">
            <div tabindex="0" aria-hidden="true" style="width: 0px; height: 0px; overflow: hidden; outline: none;"></div>
            <div class="okki-modal-content">
              <button type="button" aria-label="Close" class="okki-modal-close" @click="close">
                  <span class="okki-modal-close-x">
                    <span role="img" aria-label="close" class="anticon anticon-close okki-modal-close-icon okki-modal-close-icon" icon="[object Object]" spin="false">
                      <svg focusable="false" class="" data-icon="close" width="1em" height="1em" fill="currentColor" aria-hidden="true" viewBox="64 64 896 896">
                        <path d="M563.8 512l262.5-312.9c4.4-5.2.7-13.1-6.1-13.1h-79.8c-4.7 0-9.2 2.1-12.3 5.7L511.6 449.8 295.1 191.7c-3-3.6-7.5-5.7-12.3-5.7H203c-6.8 0-10.5 7.9-6.1 13.1L459.4 512 196.9 824.9A7.95 7.95 0 00203 838h79.8c4.7 0 9.2-2.1 12.3-5.7l216.5-258.1 216.5 258.1c3 3.6 7.5 5.7 12.3 5.7h79.8c6.8 0 10.5-7.9 6.1-13.1L563.8 512z"></path>
                      </svg>
                    </span>
                  </span>
              </button>
              <div class="okki-modal-header">
                <div class="okki-modal-title" id="vcDialogTitle1">邮件高级搜索</div>
              </div>
              <div class="okki-modal-body" style="height: calc(-100px + 100vh);">
                <form class="okki-form okki-form-vertical">
                  <!---->
                  <!---->
                  <div class="okki-row okki-form-item" style="row-gap: 0px;">
                    <div class="okki-col okki-form-item-label" style="width: 7em;">
                      <label class="" title="">
                        <span class="text-medium-8">关键字</span>
                        <!---->
                      </label>
                    </div>
                    <div class="okki-col okki-form-item-control">
                      <div class="okki-form-item-control-input">
                        <div class="okki-form-item-control-input-content">
                            <span class="okki-input-group okki-input-group-compact">
                              <div inlinetitle="" bordered="true" filterborderless="false" class="okki-select okki-select-single okki-select-show-arrow" style="width: 20%;">
                                <el-select v-model="formData.keywordType" clearable placeholder="全部">
                                  <el-option label="主题" value="1"></el-option>
                                  <el-option label="正文" value="2"></el-option>
                                  <el-option label="附件名" value="3"></el-option>
                                </el-select>
                              </div>
                              <el-input v-model="formData.keyword" placeholder="请输入邮件主题/正文/附件名" style="width: 80%;"></el-input>
                            </span>
                        </div>
                        <!---->
                      </div>
                      <!---->
                      <!---->
                    </div>
                  </div>
                  <!---->
                  <div class="okki-row okki-form-item" style="row-gap: 0px;">
                    <div class="okki-col okki-form-item-label" style="width: 7em;">
                      <label class="" title="">
                        <span class="text-medium-8">客户</span>
                        <!---->
                      </label>
                    </div>
                    <div class="okki-col okki-form-item-control">
                      <div class="okki-form-item-control-input">
                        <div class="okki-form-item-control-input-content">
                          <el-input v-model="formData.customerKeyword" placeholder="请输入客户名称/编号/邮箱/联系人昵称" style="width: 100%;"></el-input>
                        </div>
                        <!---->
                      </div>
                      <!---->
                      <!---->
                    </div>
                  </div>
                  <div class="okki-row okki-form-item" style="row-gap: 0px;">
                    <div class="okki-col okki-form-item-label" style="width: 7em;">
                      <label class="" title="">
                        <span class="text-medium-8">收发件人</span>
                        <!---->
                      </label>
                    </div>
                    <div class="okki-col okki-form-item-control">
                      <div class="okki-form-item-control-input">
                        <div class="okki-form-item-control-input-content">
                          <el-radio v-model="formData.senderRecipientType" label="1" value="1">发件人或收件人</el-radio>
                          <el-radio v-model="formData.senderRecipientType" label="2" value="2">发件人和收件人</el-radio>
                          <!---->
                          <!-- 显示发件人和收件人文本框 -->
                          <div v-if="formData.senderRecipientType === '2'">
                            <div class="think-input mb-4px">
                              <el-input v-model="formData.recipient" placeholder="请填写收件人"></el-input>
                            </div>
                            <div class="think-input">
                              <el-input v-model="formData.sender" placeholder="请填写发件人"></el-input>
                            </div>
                          </div>

                          <!-- 仅显示收件人文本框 -->
                          <div v-else-if="formData.senderRecipientType === '1'">
                            <div class="think-input mb-4px">
                              <el-input v-model="formData.recipient" placeholder="请填写收件人"></el-input>
                            </div>
                          </div>
                        </div>
                        <!---->
                      </div>
                      <!---->
                      <!---->
                    </div>
                  </div>
                  <div class="okki-row okki-form-item" style="row-gap: 0px;">
                    <div class="okki-col okki-form-item-label" style="width: 7em;">
                      <label class="" title="">
                        <span class="text-medium-8">邮件标签</span>
                        <!---->
                      </label>
                    </div>
                    <div class="okki-col okki-form-item-control">
                      <div class="okki-form-item-control-input">
                        <div class="okki-form-item-control-input-content">
                          <el-radio v-model="formData.labelType" label="1" value="1">包含任一标签</el-radio>
                          <el-radio v-model="formData.labelType" label="2" value="2">包含全部标签</el-radio>
                          <el-select
                            v-model="formData.labelIdList"
                            multiple
                            collapse-tags
                            style="width: 100%;"
                            placeholder="请选择">
                            <el-option
                              v-for="item in labelOptions"
                              :key="item.value"
                              :label="item.label"
                              :value="item.value">
                            </el-option>
                          </el-select>
                        </div>
                        <!---->
                      </div>
                      <!---->
                      <!---->
                    </div>
                  </div>
                  <div class="okki-row okki-form-item" style="row-gap: 0px;">
                    <div class="okki-col okki-form-item-label" style="width: 7em;">
                      <label class="" title="">
                        <span class="text-medium-8">时间</span>
                        <!---->
                      </label>
                    </div>
                    <div class="okki-col okki-form-item-control">
                      <div class="okki-form-item-control-input">
                        <el-date-picker
                          v-model="formData.sendDate"
                          type="daterange"
                          style="width: 100%;"
                          range-separator="至"
                          start-placeholder="开始日期"
                          end-placeholder="结束日期"
                          :start-date="formData.startSendDate"
                          :end-date="formData.endSendDate">
                        </el-date-picker>
                        <!---->
                      </div>
                      <!---->
                      <!---->
                    </div>
                  </div>
                  <div class="okki-row okki-form-item" style="row-gap: 0px;">
                    <div class="okki-col okki-form-item-label" style="width: 7em;">
                      <label class="" title="">
                        <span>文件夹</span>
                        <!---->
                        <svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" spin="false" rtl="false" viewBox="0 0 48 48" preserveAspectRatio="xMidYMid meet" fill="none" role="presentation" class="ml-4px" data-v-9c1d5ba6="">
                          <g data-v-9c1d5ba6="">
                            <path d="M0 0h48v48H0z" fill="#fff" fill-opacity=".01" data-v-9c1d5ba6=""></path>
                            <path data-follow-stroke="currentColor" data-follow-fill="currentColor" d="M24 44a19.937 19.937 0 0 0 14.142-5.858A19.937 19.937 0 0 0 44 24a19.938 19.938 0 0 0-5.858-14.142A19.937 19.937 0 0 0 24 4 19.938 19.938 0 0 0 9.858 9.858 19.938 19.938 0 0 0 4 24a19.937 19.937 0 0 0 5.858 14.142A19.938 19.938 0 0 0 24 44Z" stroke-width="4" stroke-linejoin="round" fill="#BFBFBF" stroke="#BFBFBF" data-v-9c1d5ba6=""></path>
                            <path d="M24 28.625v-4a6 6 0 1 0-6-6" stroke="#FFF" stroke-width="4" stroke-linecap="round" stroke-linejoin="round" data-v-9c1d5ba6=""></path>
                            <path fill-rule="evenodd" clip-rule="evenodd" d="M24 37.625a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5Z" fill="#FFF" data-v-9c1d5ba6=""></path>
                          </g>
                        </svg>
                        <!---->
                      </label>
                    </div>
                    <div class="okki-col okki-form-item-control">
                      <div class="okki-form-item-control-input">
                        <div class="okki-form-item-control-input-content">
                          <el-radio v-model="formData.folderType" label="1" value="1">全部</el-radio>
                          <el-radio v-model="formData.folderType" label="2" value="2">指定文件夹</el-radio>
                          <el-select v-if="formData.folderType === '2'" v-model="formData.folderIdList" multiple collapse-tags style="width: 100%;" placeholder="请选择">
                            <el-option-group
                              v-for="group in folderOptions"
                              :key="group.label"
                              :label="group.label">
                              <el-option
                                v-for="item in group.options"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                              </el-option>
                            </el-option-group>
                          </el-select>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="okki-row okki-form-item" style="row-gap: 0px;">
                    <div class="okki-col okki-form-item-label" style="width: 7em;">
                      <label class="" title="">
                        <span class="text-medium-8">附件</span>
                        <!---->
                      </label>
                    </div>
                    <div class="okki-col okki-form-item-control">
                      <div class="okki-form-item-control-input">
                        <div class="okki-form-item-control-input-content">
                          <el-radio v-model="formData.attachmentType" label="1" value="1">不限</el-radio>
                          <el-radio v-model="formData.attachmentType" label="2" value="2">含附件</el-radio>
                          <el-radio v-model="formData.attachmentType" label="3" value="3">不含附件</el-radio>
                        </div>
                        <!---->
                      </div>
                      <!---->
                      <!---->
                    </div>
                  </div>
                  <div class="okki-row okki-form-item" style="row-gap: 0px;">
                    <div class="okki-col okki-form-item-label" style="width: 7em;">
                      <label class="" title="">
                        <span class="text-medium-8">打钉</span>
                        <!---->
                      </label>
                    </div>
                    <div class="okki-col okki-form-item-control">
                      <div class="okki-form-item-control-input">
                        <div class="okki-form-item-control-input-content">
                          <el-radio v-model="formData.fixedType" label="1" value="1">不限</el-radio>
                          <el-radio v-model="formData.fixedType" label="2" value="2">有</el-radio>
                          <el-radio v-model="formData.fixedType" label="3" value="3">无</el-radio>
                        </div>
                        <!---->
                      </div>
                      <!---->
                      <!---->
                    </div>
                  </div>
                  <div class="okki-row okki-form-item" style="row-gap: 0px;">
                    <div class="okki-col okki-form-item-label" style="width: 7em;">
                      <label class="" title="">
                        <span class="text-medium-8">收发类型</span>
                        <!---->
                      </label>
                    </div>
                    <div class="okki-col okki-form-item-control">
                      <div class="okki-form-item-control-input">
                        <div class="okki-form-item-control-input-content">
                          <el-radio v-model="formData.transceiverType" label="1" value="1">不限</el-radio>
                          <el-radio v-model="formData.transceiverType" label="2" value="2">收取的</el-radio>
                          <el-radio v-model="formData.transceiverType" label="3" value="3">发送的</el-radio>
                        </div>
                        <!---->
                      </div>
                      <!---->
                      <!---->
                    </div>
                  </div>
                  <div class="okki-row okki-form-item" style="row-gap: 0px;">
                    <div class="okki-col okki-form-item-label" style="width: 7em;">
                      <label class="" title="">
                        <span class="text-medium-8">发送情况</span>
                        <!---->
                      </label>
                    </div>
                    <div class="okki-col okki-form-item-control">
                      <div class="okki-form-item-control-input">
                        <div class="okki-form-item-control-input-content">
                          <el-radio v-model="formData.sendType" label="1" value="1">不限</el-radio>
                          <el-radio v-model="formData.sendType" label="2" value="2">发送失败</el-radio>
                          <el-radio v-model="formData.sendType" label="3" value="3">发送成功</el-radio>
                        </div>
                        <!---->
                      </div>
                      <!---->
                      <!---->
                    </div>
                  </div>
                </form>
              </div>
              <div class="okki-modal-footer">
                <button class="okki-btn okki-btn-primary okki-btn-round okki-btn-background-ghost" type="button" @click="clear">
                  <!---->
                  <span>清 空</span>
                </button>
                <button class="okki-btn okki-btn-primary okki-btn-round do-search" type="button" @click="filter">
                  <!---->
                  <span>筛 选</span>
                </button>
              </div>
            </div>
            <div tabindex="0" aria-hidden="true" style="width: 0px; height: 0px; overflow: hidden; outline: none;"></div>
          </div>
        </div>
      </div>
    </el-form>
  </div>
</template>
<script>
import {EventBus} from "@/api/email/event-bus";
import {listLabel} from "@/api/email/label";
import {listTypeFolder} from "@/api/email/folder";
import PopoverSelectFolder from "@/views/email/customer_email/PopoverSelectFolder.vue";
export default {
  components: {PopoverSelectFolder},
  data() {
    return {
      searchPage: false,
      labelOptions: [],
      folderOptions: [],
      formData: {
        keywordType: null,
        keyword: '',
        customerKeyword: '',
        senderRecipientType: '1',
        recipient: '',
        sender: '',
        sendStatus: "1",
        labelType: '1',
        labelIdList: [],
        sendDate: '',
        folderType: '1',
        attachmentType: '1',
        fixedType: '1',
        transceiverType: '1',
        sendType: '1',
        startSendTime: null,
        endSendTime: null,
        folderIdList:[],
      }
    }
  },

  methods: {
    open() {
      this.searchPage = true;
      this.refreshLabelList();
      this.refreshListTypeFolder();
    },

    close() {
      this.searchPage = false;
    },

    filter() {
      // 如果发送日期不为空
      if (this.formData.sendDate) {
        // 获取选中的日期范围
        const [startDate, endDate] = this.formData.sendDate;
        // 设置开始日期为选中日期的 00:00:00
        this.formData.startSendTime = this.formatDate(startDate, 'yyyy-MM-dd 00:00:00');
        // 设置结束日期为选中日期的 23:59:59
        this.formData.endSendTime = this.formatDate(endDate, 'yyyy-MM-dd 23:59:59');
      } else {
        // 如果发送日期为空，则开始日期和结束日期都设置为 null
        this.formData.startSendTime = null;
        this.formData.endSendTime = null;
      }

      this.formData.sendDate = null;

      this.$emit('filter', this.formData);
      this.close();
    },

    formatDate(date, format) {
      const year = date.getFullYear();
      let month = date.getMonth() + 1;
      if (month < 10) month = '0' + month;
      let day = date.getDate();
      if (day < 10) day = '0' + day;
      let hours = date.getHours();
      if (hours < 10) hours = '0' + hours;
      let minutes = date.getMinutes();
      if (minutes < 10) minutes = '0' + minutes;
      let seconds = date.getSeconds();
      if (seconds < 10) seconds = '0' + seconds;

      return format
        .replace('yyyy', year)
        .replace('MM', month)
        .replace('dd', day)
        .replace('HH', hours)
        .replace('mm', minutes)
        .replace('ss', seconds);
    },

    clear() {
      this.formData = Object.assign({}, this.$options.data().formData);
    },

    refreshLabelList() {
      listLabel().then((response) => {
        this.labelOptions = response.data.map(item => ({ value: item.id, label: item.name }));
      });
    },

    refreshListTypeFolder() {
      listTypeFolder().then((response) => {
        // 处理系统文件夹和自定义文件夹的数据
        const systemFolders = response.data.find(folderGroup => folderGroup.label === '系统文件夹').folderList;
        const customFolders = response.data.find(folderGroup => folderGroup.label === '自定义文件夹').folderList;

        // 将数据转换成适合组件的格式
        const formattedSystemFolders = systemFolders.map(folder => ({
          label: folder.name,
          value: folder.id
        }));

        const formattedCustomFolders = customFolders.map(folder => ({
          label: folder.name,
          value: folder.id
        }));

        // 将系统文件夹和自定义文件夹合并为一个选项数组
        const allFolders = [
          { label: '系统文件夹', options: formattedSystemFolders },
          { label: '自定义文件夹', options: formattedCustomFolders }
        ];

        // 将合并后的选项数组赋值给 folderOptions
        this.folderOptions = allFolders;
      });
    }
  },
};
</script>
