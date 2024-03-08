<template>
  <div class="mail-detail--attach" data-v-155e8e5c="">
    <div class="mailDetail-attachs" data-v-4ceaee07="" data-v-155e8e5c="">
      <h3 class="mailDetail-attachs-head" data-v-4ceaee07="">
        <span class="attachment" data-v-4ceaee07="">附件（{{emailAttachmentList.length}} 个） </span>
        <span class="download-all" data-v-4ceaee07="">
				<a class="c-link" @click="downloadEmailAttachment(emailId, emailTitle)" data-v-4ceaee07="">全部下载</a>
			</span>
      </h3>
      <div data-v-955c185a="" data-v-4ceaee07="">
        <div class="mail-collapse-wrapper" data-v-955c185a="" :style="{'max-height': isExpanded ? 'unset' : '56px'}">
          <div class="mail-collapse-content" data-v-955c185a="">
            <ul class="mailDetail-attachs-body" data-v-4ceaee07="">
              <li v-for="attachment in emailAttachmentList" draggable="true" class="web-attach-content" data-v-4ceaee07="">
                <div class="attach-icon-content" data-v-4ceaee07="">
                  <svg class="mm-icon mm-icon-file-txt" viewBox="0 0 200 200" name="file_txt" fill="currentColor" data-v-4ceaee07="" style="height: 20px; width: 20px; flex-shrink: 0;">
                    <g>
                      <path d="M29,7.2c-2.3,0-4.8,0.9-6.6,2.8c-1.9,1.7-2.8,4.2-2.8,6.6v168.8c0,2.3,0.9,4.8,2.8,6.6   c1.9,1.9,4.2,2.8,6.6,2.8h137.5c2.3,0,4.8-0.9,6.6-2.8c1.9-1.9,2.8-4.2,2.8-6.6v-125L122.8,7.2H29z" fill="#659BFC"></path>
                      <path d="M175.9,60.4h-43.8c-2.3,0-4.8-0.9-6.6-2.8c-1.9-1.7-2.8-4.2-2.8-6.6V7.2L175.9,60.4z" fill="#ABD1FF"></path>
                    </g>
                    <g>
                      <path d="M52.1,99.6h-8.9c-2.3,0-4.3-1.9-4.3-4.3c0-2.3,1.9-4.3,4.3-4.3h27c2.3,0,4.3,1.9,4.3,4.3   c0,2.3-1.9,4.3-4.3,4.3h-8.9v29.2c0,2.6-2,4.6-4.6,4.6s-4.6-2-4.6-4.6V99.6z" fill="#FFFFFF"></path>
                      <path d="M80.3,125.7l11.3-13.9L81,98.7c-0.7-0.9-1.4-2.1-1.4-3.4c0-2.6,1.9-4.6,4.6-4.6c2,0,3.1,0.8,4.3,2.3l9.2,12.2   l9.1-12c1.3-1.6,2.5-2.5,4.5-2.5c2,0,4.2,1.6,4.2,4.3c0,1.3-0.5,2.4-1.4,3.5l-10.7,13.1l11.3,13.9c0.7,0.9,1.4,2.1,1.4,3.4   c0,2.6-1.9,4.6-4.6,4.6c-2,0-3.1-0.8-4.3-2.3l-9.8-12.9l-9.7,12.7c-1.3,1.6-2.5,2.5-4.5,2.5c-2,0-4.2-1.6-4.2-4.3   C79,127.9,79.4,126.8,80.3,125.7z" fill="#FFFFFF"></path>
                      <path d="M133.6,99.6h-8.9c-2.3,0-4.3-1.9-4.3-4.3c0-2.3,1.9-4.3,4.3-4.3h27c2.3,0,4.3,1.9,4.3,4.3   c0,2.3-1.9,4.3-4.3,4.3h-8.9v29.2c0,2.6-2,4.6-4.6,4.6s-4.6-2-4.6-4.6V99.6z" fill="#FFFFFF"></path>
                    </g>
                  </svg>
                  <div class="attach-content" data-v-4ceaee07="">
                    <h4 :title="attachment.name" class="attach-name-wrapper" data-v-4ceaee07="">
                      <span :title="attachment.name" class="attach-name ellipsis" @click="preview(attachment.id, attachment.name)" data-v-4ceaee07="">{{attachment.name}}</span>
                    </h4>
                    <div data-v-4ceaee07="" style="display: flex;">
										<span class="mm-tooltip" data-v-4ceaee07="">
											<span class="mm-tooltip-trigger">
												<a @click="downloadAttachment(attachment)" target="_self" class="c-link" data-v-4ceaee07="">
													<div class="attach-tool-item" data-v-4ceaee07="">
														<span class="okki-icon-wrap" data-v-4ceaee07="">​<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
																<path d="M4 13.5a1 1 0 011 1V18a1 1 0 001 1h12a1 1 0 001-1v-3.5a1 1 0 112 0V18a3 3 0 01-3 3H6a3 3 0 01-3-3v-3.5a1 1 0 011-1z"></path>
																<path d="M12.707 15.707a1 1 0 01-1.414 0l-5-5a1 1 0 011.414-1.414L11 12.586V4a1 1 0 112 0v8.586l3.293-3.293a1 1 0 111.414 1.414l-5 5z"></path>
															</svg>
														</span>
													</div>
												</a>
											</span>
										</span>
                      <!-- Add more actions if needed -->
                    </div>
                    <h4 class="attach-size" data-v-4ceaee07="">
                      <span class="size-text ellipsis"data-v-4ceaee07="">
                        {{attachment.size}}
                      </span>
                    </h4>
                  </div>
                </div>
              </li>
            </ul>
          </div>
        </div>

        <div class="loadmore" data-v-955c185a="">
          <div class="loadmore-btn" @click="toggleExpansion" data-v-955c185a="">
          <span :class="['okki-icon-wrap', 'icon', !isExpanded ? 'rotate-flag' : '']" data-v-955c185a="">​<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
              <path fill-rule="evenodd" clip-rule="evenodd" d="M12 8.5a1 1 0 00-.707.293l-6 6a1 1 0 101.414 1.414L12 10.914l5.293 5.293a1 1 0 001.414-1.414l-6-6A1 1 0 0012 8.5z"></path>
            </svg>
          </span>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>
<style scoped>
.size-text.ellipsis {
  position: relative;
}

.overlay-download-btn {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  cursor: pointer;
  background: rgba(255, 255, 255, 0.01);
}
</style>
<script>
import {downloadEmailAttachment} from "@/api/email/email";
import {downloadAttachment} from "@/api/email/attachment";
import Base64 from "@/api/base64.min";


export default {
  data() {
    return {
      isExpanded: false,
    };
  },

  props: ['emailAttachmentList', 'emailId', 'emailTitle'],

  methods: {
    preview(id, name) {
      let originUrl = window.location.origin;
      let previewUrl = originUrl + '/dev-api/email/attachment/download/' + id + '?fullfilename=' + name;
      window.open("http://localhost:8012/onlinePreview?url="+encodeURIComponent(global.Base64.encode(previewUrl)));
    },

    toggleExpansion() {
      this.isExpanded = !this.isExpanded;
    },

    // 下载附件
    downloadAttachment(attachment) {
      downloadAttachment(attachment.id).then((response) => {
        const url = window.URL.createObjectURL(new Blob([response]));
        const link = document.createElement('a');
        link.href = url;
        let filename = attachment.name;
        link.setAttribute('download', filename);
        document.body.appendChild(link);
        link.click();
      });
    },

    // 下载邮件附件
    downloadEmailAttachment(emailId, emailTitle) {
      downloadEmailAttachment(emailId).then((response) => {
        const url = window.URL.createObjectURL(new Blob([response], {type: 'application/zip'}));
        const link = document.createElement('a');
        link.href = url;
        let filename = emailTitle + ".zip";
        link.setAttribute('download', filename);
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);  // 移除创建的链接元素
        window.URL.revokeObjectURL(url); // 释放Blob URL资源
      }).catch((error) => {
        console.error("下载邮件附件失败:", error);
      });
    }
  }
}

</script>
