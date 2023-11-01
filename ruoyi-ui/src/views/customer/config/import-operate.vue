<template>
  <div>
    <div class="bold fs-22 pb-4">
      客户导入
    </div>
    <div>
      <main class="okki-layout-content bg-white overflow-auto">
        <div class="mx-auto mt-6 px-6 min-w-screen-lg w-screen-lg">
          <div class="okki-steps okki-steps-horizontal okki-steps-label-vertical">
            <div class="okki-steps-item okki-steps-item-process" isarrow="false">
              <div role="button" tabindex="0" class="okki-steps-item-container">
                <div class="okki-steps-item-tail"><!----></div>
                <div class="okki-steps-item-icon"><span class="okki-steps-icon">1</span></div>
                <div class="okki-steps-item-content">
                  <div class="okki-steps-item-title">上传文件<!----></div><!----></div>
              </div>
            </div>
            <div class="okki-steps-item okki-steps-item-wait" isarrow="false">
              <div role="button" tabindex="0" class="okki-steps-item-container">
                <div class="okki-steps-item-tail"><!----></div>
                <div class="okki-steps-item-icon"><span class="okki-steps-icon">2</span></div>
                <div class="okki-steps-item-content">
                  <div class="okki-steps-item-title">匹配字段<!----></div><!----></div>
              </div>
            </div>
            <div class="okki-steps-item okki-steps-item-wait" isarrow="false">
              <div role="button" tabindex="0" class="okki-steps-item-container">
                <div class="okki-steps-item-tail"><!----></div>
                <div class="okki-steps-item-icon"><span class="okki-steps-icon">3</span></div>
                <div class="okki-steps-item-content">
                  <div class="okki-steps-item-title">查看导入结果<!----></div><!----></div>
              </div>
            </div>
          </div>
          <form class="okki-form okki-form-vertical items-start w-2/3 py-6 mx-auto flex flex-col" style="margin:0 auto"
                is-ames="false">
            <div class="p-4 my-8 bg-[#F9F9F9] rounded-lg">
              <ul class="text-text-2">注意事项：
                <li class="mt-2 text-text-3">1. 多选、标签、国家地区、社交平台、邮箱、座机等字段格式需按规范填写，单次导入数据不可超过10,000行
                  <a class="okki-btn okki-btn-link okki-btn-round visited:text-link-1 !leading-6"
                     href="https://jinyuncrmdevelop.oss-cn-hangzhou.aliyuncs.com/template/20221222/%E5%AF%BC%E5%85%A5%E6%A0%BC%E5%BC%8F%E8%A6%81%E6%B1%82.pdf"
                     target="_self"><!----><span>查看格式要求</span></a></li>
                <li class="mt-2 text-text-3">2.若文件表头有合并单元格，请取消合并。您也可以参考或使用导入模板 <a
                    class="okki-btn okki-btn-link okki-btn-round visited:text-link-1 !leading-6"
                    href="https://jinyuncrmdevelop.oss-cn-hangzhou.aliyuncs.com/template/20221222/%E5%AE%A2%E6%88%B7%E5%AF%BC%E5%85%A5%E6%A8%A1%E6%9D%BF.xlsx"
                    target="_self"><!----><span>下载模板</span></a></li>
              </ul>
            </div>
            <div class="okki-row okki-form-item" style="row-gap: 0px;">
              <div class="okki-col okki-form-item-label okki-form-item-label-left"><label class="" title=""><span
                  class="text-text-3">客户导入至</span><!----></label></div>
              <div class="okki-col okki-form-item-control">
                <div class="okki-form-item-control-input">
                  <div class="okki-form-item-control-input-content">
                    <div class="okki-radio-group okki-radio-group-outline okki-radio-group-default"><label
                        class="okki-radio-wrapper okki-radio-wrapper-checked text-text-2"><span
                        class="okki-radio okki-radio-checked"><input type="radio" class="okki-radio-input" autofocus=""
                                                                     value="0"><span
                        class="okki-radio-inner"></span></span><span>客户列表</span></label><label
                        class="okki-radio-wrapper mx-6 text-text-2"><span class="okki-radio"><input type="radio"
                                                                                                    class="okki-radio-input"
                                                                                                    value="1"><span
                        class="okki-radio-inner"></span></span><span>公海客户</span></label></div>
                  </div><!----></div><!----><!----></div>
            </div>
            <div class="okki-row okki-form-item" style="row-gap: 0px;">
              <div class="okki-col okki-form-item-label okki-form-item-label-left"><label class="" title=""><span
                  class="text-gray-500">当导入的客户或联系人与已有数据重复时，是否更新数据</span><!----></label></div>
              <div class="okki-col okki-form-item-control">
                <div class="okki-form-item-control-input">
                  <div class="okki-form-item-control-input-content">
                    <div class="okki-radio-group okki-radio-group-outline okki-radio-group-default"><label
                        class="okki-radio-wrapper text-text-2"><span class="okki-radio"><input type="radio"
                                                                                               class="okki-radio-input"
                                                                                               value="1"><span
                        class="okki-radio-inner"></span></span><span>更新数据</span></label><label
                        class="okki-radio-wrapper okki-radio-wrapper-checked text-text-2 mx-6"><span
                        class="okki-radio okki-radio-checked"><input type="radio" class="okki-radio-input"
                                                                     value="0"><span
                        class="okki-radio-inner"></span></span><span>不更新</span></label></div>
                  </div><!----></div><!----><!----></div>
            </div>
            <div class="okki-row okki-form-item mt-4 w-full" style="row-gap: 0px;"><!---->
              <div class="okki-col okki-form-item-control">
                <div class="okki-form-item-control-input">
                  <div class="okki-form-item-control-input-content"><span><div
                      class="okki-upload okki-upload-select okki-upload-select-text">
                    <span tabindex="0"
                          class="okki-upload"
                          role="button"><input
                        type="file" accept=".xls,.xlsx,.csv" capture="false" style="display: none;"><button
                        class="okki-btn okki-btn-default okki-btn-round okki-btn-background-ghost guide-step-upload-customer-excel"
                        type="button"><!----><span class="i-icon i-icon-upload valar-iconpark-icon mr-2"
                                                   data-v-2a8742d4="" style="vertical-align: -0.1em;"><svg width="12"
                                                                                                           height="12"
                                                                                                           viewBox="0 0 48 48"
                                                                                                           fill="none"><mask
                        id="icon-2af77b1b04cec2fa" maskUnits="userSpaceOnUse" x="0" y="0" width="48" height="48"
                        style="mask-type: alpha;"><path d="M48 0H0V48H48V0Z" fill="black"></path></mask><g
                        mask="url(#icon-2af77b1b04cec2fa)"><path d="M6 24.0083V42H42V24" stroke="black" stroke-width="4"
                                                                 stroke-linecap="round" stroke-linejoin="round"></path><path
                        d="M33 15L24 6L15 15" stroke="black" stroke-width="4" stroke-linecap="round"
                        stroke-linejoin="round"></path><path d="M23.9917 32V6" stroke="black" stroke-width="4"
                                                             stroke-linecap="round"
                                                             stroke-linejoin="round"></path></g></svg></span><span>上传文件</span></button></span></div><div
                      class="okki-upload-list okki-upload-list-text"></div></span></div><!----></div><!----><!---->
              </div>
            </div>
            <div class="my-14 w-full flex justify-center">
              <button class="okki-btn okki-btn-primary okki-btn-round guide-step-upload-next" disabled="" type="button">
                <!----><span>下一步</span></button>
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

export default {
  data() {
    return {
      curStep: 0,
      activeClass:'',
      awaitClass:'',
    }
  }
}
</script>

<style lang="css" scoped>

</style>
