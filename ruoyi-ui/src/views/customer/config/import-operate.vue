<template>
  <div class="mx-20">
    <div class="bold fs-22 mt-20 pb-4">
      客户导入
    </div>
    <div>
      <main class="okki-layout-content bg-white overflow-auto">
        <div class="mx-auto mt-6 px-6 min-w-screen-lg w-screen-lg">
          <!--          <div class="okki-steps okki-steps-horizontal okki-steps-label-vertical">-->
          <!--            <div class="okki-steps-item okki-steps-item-process" isarrow="false">-->
          <!--              <div role="button" tabindex="0" class="okki-steps-item-container">-->
          <!--                <div class="okki-steps-item-tail">&lt;!&ndash;&ndash;&gt;</div>-->
          <!--                <div class="okki-steps-item-icon"><span class="okki-steps-icon">1</span></div>-->
          <!--                <div class="okki-steps-item-content">-->
          <!--                  <div class="okki-steps-item-title">上传文件&lt;!&ndash;&ndash;&gt;</div>&lt;!&ndash;&ndash;&gt;</div>-->
          <!--              </div>-->
          <!--            </div>-->
          <!--            <div class="okki-steps-item okki-steps-item-wait" isarrow="false">-->
          <!--              <div role="button" tabindex="0" class="okki-steps-item-container">-->
          <!--                <div class="okki-steps-item-tail">&lt;!&ndash;&ndash;&gt;</div>-->
          <!--                <div class="okki-steps-item-icon"><span class="okki-steps-icon">2</span></div>-->
          <!--                <div class="okki-steps-item-content">-->
          <!--                  <div class="okki-steps-item-title">匹配字段&lt;!&ndash;&ndash;&gt;</div>&lt;!&ndash;&ndash;&gt;</div>-->
          <!--              </div>-->
          <!--            </div>-->
          <!--            <div class="okki-steps-item okki-steps-item-wait" isarrow="false">-->
          <!--              <div role="button" tabindex="0" class="okki-steps-item-container">-->
          <!--                <div class="okki-steps-item-tail">&lt;!&ndash;&ndash;&gt;</div>-->
          <!--                <div class="okki-steps-item-icon"><span class="okki-steps-icon">3</span></div>-->
          <!--                <div class="okki-steps-item-content">-->
          <!--                  <div class="okki-steps-item-title">查看导入结果&lt;!&ndash;&ndash;&gt;</div>&lt;!&ndash;&ndash;&gt;</div>-->
          <!--              </div>-->
          <!--            </div>-->
          <!--          </div>-->
          <form class="okki-form okki-form-vertical items-start w-2/3 py-6 mx-auto flex flex-col" style="margin:0 auto"
                is-ames="false">
            <div class="p-4 my-8 bg-[#F9F9F9] rounded-lg">
              <ul class="text-text-2">注意事项：
                <li class="mt-2 text-text-3">1. 多选、标签、国家地区、社交平台、邮箱、座机等字段格式需按规范填写，单次导入数据不可超过10,000行
                  <a class="okki-btn okki-btn-link okki-btn-round visited:text-link-1 !leading-6"
                     target="_blank" :href="formatImport"><!----><span>查看格式要求</span></a>
                </li>
                <li class="mt-2 text-text-3">2.若文件表头有合并单元格，请取消合并。您也可以参考或使用导入模板 <a
                    class="okki-btn okki-btn-link okki-btn-round visited:text-link-1 !leading-6"
                    target="_self"><!----><span @click="downloadTemplateFile">下载模板</span></a></li>
              </ul>
            </div>
            <div class="okki-row okki-form-item" style="row-gap: 0px;">
              <div class="okki-col okki-form-item-label okki-form-item-label-left"><label class="" title=""><span
                  class="text-text-3">客户导入至</span><!----></label></div>
              <div class="okki-col okki-form-item-control">
                <div class="okki-form-item-control-input">
                  <div class="okki-form-item-control-input-content">
                    <div class="okki-radio-group okki-radio-group-outline okki-radio-group-default">
                      <label class="okki-radio-wrapper text-text-2"
                             :class="{'okki-radio-wrapper-checked':importType===1}" @click="importType=1">
                        <span class="okki-radio" :class="{'okki-radio-checked':importType===1}">
                          <input type="radio" class="okki-radio-input" autofocus="" value="1"/>
                          <span class="okki-radio-inner"></span>
                        </span>
                        <span>客户列表</span>
                      </label>
                      <label class="okki-radio-wrapper mx-6 text-text-2"
                             :class="{'okki-radio-wrapper-checked':importType===2}" @click="importType=2">
                      <span class="okki-radio" :class="{'okki-radio-checked':importType===2}">
                      <input type="radio" class="okki-radio-input" value="2"/>
                        <span class="okki-radio-inner"></span>
                      </span>
                        <span>公海客户</span>
                      </label>
                    </div>
                  </div><!----></div><!----><!----></div>
            </div>
            <div class="okki-row okki-form-item" style="row-gap: 0px;">
              <div class="okki-col okki-form-item-label okki-form-item-label-left"><label class="" title=""><span
                  class="text-gray-500">当导入的客户或联系人与已有数据重复时，是否更新数据</span><!----></label></div>
              <div class="okki-col okki-form-item-control">
                <div class="okki-form-item-control-input">
                  <div class="okki-form-item-control-input-content">
                    <div class="okki-radio-group okki-radio-group-outline okki-radio-group-default">
                      <label class="okki-radio-wrapper text-text-2" :class="{'okki-radio-wrapper-checked':updateFlag}"
                             @click="updateFlag=true">
                         <span class="okki-radio" :class="{'okki-radio-checked':updateFlag}">
                          <input type="radio" class="okki-radio-input" value="1"/>
                          <span class="okki-radio-inner"></span>
                         </span>
                        <span>更新数据</span>
                      </label>
                      <label class="okki-radio-wrapper text-text-2 mx-6"
                             :class="{'okki-radio-wrapper-checked':!updateFlag}" @click="updateFlag=false">
                        <span class="okki-radio" :class="{'okki-radio-checked':!updateFlag}">
                           <input type="radio" class="okki-radio-input" value="0"/>
                          <span class="okki-radio-inner"></span>
                        </span>
                        <span>不更新</span>
                      </label>
                    </div>
                  </div><!----></div><!----><!----></div>
            </div>
            <div class="okki-row okki-form-item mt-4 w-full" style="row-gap: 0px;"><!---->
              <div class="okki-col okki-form-item-control">
                <div class="okki-form-item-control-input">
                  <div class="okki-form-item-control-input-content">
                    <span>
                      <div class="okki-upload okki-upload-select okki-upload-select-text">
                        <el-upload
                            :file-list="fileList"
                            :limit="1"
                            accept=".xls,.xlsx,.csv"
                            ref="uploadFile"
                            :action="uploadAction"
                            :headers="headers"
                            :on-error="handleUploadError"
                            :on-success="handleUploadSuccess"
                            :before-upload="handleBeforeUpload"
                            :on-remove="handleDelete"
                        >
                          <el-button icon="el-icon-upload" round plain>上传文件</el-button>
                        </el-upload>
                    </div>
                    </span></div><!----></div><!----><!---->
              </div>
            </div>
            <div class="my-14 w-full flex justify-center">
              <button class="okki-btn okki-btn-primary okki-btn-round guide-step-upload-next" type="button"
                      @click="submitImport">
                <!----><span>提交</span></button>
            </div>
          </form>
        </div>
      </main>
    </div>
  </div>
</template>

<script>
import "@/static/scss/xiaoman/windi.f61bb6b4.css"
import "@/static/scss/xiaoman/5261.43952d38.css"
import {downloadTemplate, importCustomer} from "@/api/customer/config";
import {getToken} from "@/utils/auth";
import {saveAs} from 'file-saver';
import formatImport from '@/assets/pdf/formatImportant.pdf'

export default {
  data() {
    return {
      curStep: 0,
      activeClass: '',
      awaitClass: '',
      importType: 1,
      updateFlag: false,
      uploadList: [],
      fileList: [],
      uploadAction: process.env.VUE_APP_BASE_API + "/common/upload", // 上传的图片服务器地址
      headers: {
        Authorization: "Bearer " + getToken(),
      },
      formatImport
    }
  },
  methods: {
    async downloadTemplateFile() {
      try {
        const res = await downloadTemplate()
        const blob = new Blob([res])
        saveAs(blob, 'template.xlsx')
      } catch {
      }
    },
    // 上传失败
    handleUploadError() {
      this.$modal.msgError("上传失败，请重试");
      this.$modal.closeLoading();
    },
    handleBeforeUpload() {
      this.$modal.loading("正在上传图片，请稍候...");
    },
    // 上传成功回调
    handleUploadSuccess(res, file) {
      if (res.code === 200) {
        this.uploadList.push({name: res.newFileName, url: res.fileName});
        this.uploadedSuccessfully();
      } else {
        this.$modal.closeLoading();
        this.$modal.msgError(res.msg);
        this.$refs.uploadFile.handleRemove(file);
        this.uploadedSuccessfully();
      }
    },
    // 上传结束处理
    uploadedSuccessfully() {
      if (this.uploadList.length) {
        this.fileList = this.fileList.concat(this.uploadList);
        this.uploadList = [];
        this.$modal.closeLoading();
      }
    },
    // 删除图片
    handleDelete(file) {
      const findex = this.fileList.map(f => f.name).indexOf(file.name);
      if (findex > -1) {
        this.fileList.splice(findex, 1);
      }
    },
    async submitImport() {
      try {
        const file = this.fileList?.[0].url
        const res = importCustomer({
          updateFlag: this.updateFlag,
          importType: this.importType,
          file: file
        })
        if (res.code === 200) {
          this.$router.push('/customer/config?tab=import')
        }
      } catch {
      }
    },
  }
}
</script>

<style lang="css" scoped>

</style>
