<template>
  <div class="mm-tabs-content">
    <div id="pane-0" aria-hidden="true" aria-labelledby="tab-0" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
    <div id="pane-1" aria-hidden="true" aria-labelledby="tab-1" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
    <div id="pane-2" aria-hidden="true" aria-labelledby="tab-2" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
    <div id="pane-3" aria-hidden="true" aria-labelledby="tab-3" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
    <div id="pane-4" aria-hidden="true" aria-labelledby="tab-4" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
    <div id="pane-5" aria-hidden="true" aria-labelledby="tab-5" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
    <div id="pane-6" aria-hidden="true" aria-labelledby="tab-6" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
    <div id="pane-7" aria-hidden="false" aria-labelledby="tab-7" class="mm-tab-pane" role="tabpanel" style=""></div>
    <div id="pane-8" aria-hidden="true" aria-labelledby="tab-8" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
    <div id="pane-9" aria-hidden="true" aria-labelledby="tab-9" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
    <div id="pane-10" aria-hidden="true" aria-labelledby="tab-10" class="mm-tab-pane" role="tabpanel" style="display: none;"></div>
    <div class="mail-plus-black config-page-item">
      <div class="mail-plus-black-content">
        <div class="mail-black-email">
          <div class="mail-config-header">
            <div>
              <h3 class="config-page-title">邮箱地址黑名单</h3>
              <span class="tips">黑名单中邮箱对应的邮件将自动收取到垃圾邮件（不会收到新邮件提醒）</span>
            </div>
            <button type="button" class="mm-button mm-button__text flex items-center text-blue-300" @click="addEmailBtn()">
              <!---->
              <!---->
              <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
                <path fill-rule="evenodd" clip-rule="evenodd" d="M12 2a10 10 0 100 20 10 10 0 000-20zm0 18a8 8 0 110-16.001A8 8 0 0112 20z"></path>
                <path d="M13 8a1 1 0 10-2 0v3H8a1 1 0 100 2h3v3a1 1 0 102 0v-3h3a1 1 0 100-2h-3V8z"></path>
              </svg>
              <span class="ml-1">添加邮箱地址</span>
              <!---->
            </button>
          </div>
          <div class="mail-black-search flex">
																		<span class="mm-input">
																			<!---->
																			<span class="mm-input-affix-wrapper">
																				<!---->
																				<input v-model="emailContent" placeholder="请输入关键字搜索" maxlength="60" type="text" class="mm-input-inner" value="">
                                        <!---->
																			</span>
                                      <!---->
																		</span>
            <div class="mail-black-search-icon cursor-pointer" @click="emailSearch">
              <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
                <path fill-rule="evenodd" clip-rule="evenodd" d="M17 10.5a6.5 6.5 0 11-13 0 6.5 6.5 0 0113 0zm-1.473 6.855a8.5 8.5 0 111.463-1.365l3.303 3.303a1 1 0 01-1.414 1.414l-3.352-3.352z"></path>
              </svg>
            </div>
          </div>
          <!---->
          <div class="mail-black-list" v-if="emailDatas && emailDatas.length > 0">
            <ul>
              <li class="mail-black-list-item mail-black-header">
                <p>邮箱地址</p>
                <div class="btn-list">
                  <span class="mr-2">操作</span>
                </div>
              </li>
              <li v-for="emailData in emailDatas" :key="emailData.id" class="mail-black-list-item">
                <p>{{emailData.content}}</p>
                <div class="btn-list">
                  <div class="btn-list-item text-blue-300" @click="deleteBlacklist(emailData.id, 1)">删除</div>
                </div>
              </li>
            </ul>
            <ul class="mm-pagination mm-pagination__right">
              <li :class="['mm-pagination-prev', emailPageNum === 1 ? 'disabled': '']" @click="emailPrevPage">
                <svg class="mm-icon mm-icon-chevronleft" viewBox="0 0 24 24" name="chevronleft" fill="currentColor" style="height: 0.875rem; width: 0.875rem;">
                  <path d="M15.8 22l-9.6-9.4c-.3-.3-.3-.8 0-1.1l9.6-9.4c.3-.3.7-.3 1 0l1 1c.3.3.3.7 0 1l-7.6 7.4c-.3.3-.3.8 0 1.1l7.5 7.4c.3.3.3.7 0 1l-1 1c-.2.2-.6.2-.9 0z"></path>
                </svg>
              </li>
              <li>
                <ul class="mm-pagination-pager">
                  <li v-for="pageNumber in emailDisplayedPages" :key="pageNumber" :class="[pageNumber === emailPageNum ? 'active' : '', 'mm-pagination-number']">
                    <span @click="emailChangePage(pageNumber)">{{ pageNumber }}</span>
                  </li>
                </ul>
              </li>
              <li :class="['mm-pagination-next', emailPageNum === emailTotalPage ? 'disabled' : '']" @click="emailNextPage">
                <svg class="mm-icon mm-icon-chevronright" viewBox="0 0 24 24" name="chevronright" fill="currentColor" style="height: 0.875rem; width: 0.875rem;">
                  <path d="M8.3 2l9.5 9.5c.3.3.3.7 0 1L8.3 22c-.3.2-.8.2-1.1 0l-1-1c-.2-.3-.2-.8 0-1.1l7.6-7.4c.2-.3.2-.7 0-1L6.3 4.1C6 3.8 6 3.3 6.3 3l1-1c.3-.2.7-.2 1 0z"></path>
                </svg>
              </li>
            </ul>
          </div>

          <div class="mail-black-list" v-else>
            <div class="mm-empty no-data-block">
              <div class="empty-custom-render">
                <div class="empty-custom-image">
                  <img data-savepage-src="https://cdn.xiaoman.cn/crm_web/ks/prod/mail_subapp/static/img/no-data.33209b5c.png" src="data:image/webp;base64,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">
                </div>
                <div class="empty-custom-desc">
                  <span>暂无数据</span>
                </div>
              </div>
            </div>
            <ul class="mm-pagination mm-pagination__right">
              <li class="disabled mm-pagination-prev">
                <svg class="mm-icon mm-icon-chevronleft" viewBox="0 0 24 24" name="chevronleft" fill="currentColor" style="height: 0.875rem; width: 0.875rem;">
                  <path d="M15.8 22l-9.6-9.4c-.3-.3-.3-.8 0-1.1l9.6-9.4c.3-.3.7-.3 1 0l1 1c.3.3.3.7 0 1l-7.6 7.4c-.3.3-.3.8 0 1.1l7.5 7.4c.3.3.3.7 0 1l-1 1c-.2.2-.6.2-.9 0z"></path>
                </svg>
              </li>
              <li>
                <ul class="mm-pagination-pager">
                  <li class="active mm-pagination-number">
                    <span>1</span>
                  </li>
                  <!---->
                  <!---->
                  <!---->
                </ul>
              </li>
              <li class="disabled mm-pagination-next">
                <svg class="mm-icon mm-icon-chevronright" viewBox="0 0 24 24" name="chevronright" fill="currentColor" style="height: 0.875rem; width: 0.875rem;">
                  <path d="M8.3 2l9.5 9.5c.3.3.3.7 0 1L8.3 22c-.3.2-.8.2-1.1 0l-1-1c-.2-.3-.2-.8 0-1.1l7.6-7.4c.2-.3.2-.7 0-1L6.3 4.1C6 3.8 6 3.3 6.3 3l1-1c.3-.2.7-.2 1 0z"></path>
                </svg>
              </li>
            </ul>
          </div>

        </div>
        <div class="mail-black-domain">
          <div class="mail-config-header">
            <div>
              <h3 class="config-page-title">域名黑名单</h3>
              <span class="tips">黑名单中域名对应的邮件将自动收取到垃圾邮件（不会收到新邮件提醒）</span>
            </div>
            <button type="button" class="mm-button mm-button__text flex items-center text-blue-300" @click="addDomainBtn">
              <!---->
              <!---->
              <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
                <path fill-rule="evenodd" clip-rule="evenodd" d="M12 2a10 10 0 100 20 10 10 0 000-20zm0 18a8 8 0 110-16.001A8 8 0 0112 20z"></path>
                <path d="M13 8a1 1 0 10-2 0v3H8a1 1 0 100 2h3v3a1 1 0 102 0v-3h3a1 1 0 100-2h-3V8z"></path>
              </svg>
              <span class="ml-1">添加域名</span>
              <!---->
            </button>
          </div>
          <div class="mail-black-search flex">
																		<span class="mm-input">
																			<!---->
																			<span class="mm-input-affix-wrapper">
																				<!---->
																				<input v-model="domainContent" placeholder="请输入关键字搜索" maxlength="60" type="text" class="mm-input-inner" value="">
                                        <!---->
																			</span>
                                      <!---->
																		</span>
            <div class="mail-black-search-icon cursor-pointer" @click="domainSearch">
              <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon" fill="currentColor">
                <path fill-rule="evenodd" clip-rule="evenodd" d="M17 10.5a6.5 6.5 0 11-13 0 6.5 6.5 0 0113 0zm-1.473 6.855a8.5 8.5 0 111.463-1.365l3.303 3.303a1 1 0 01-1.414 1.414l-3.352-3.352z"></path>
              </svg>
            </div>
          </div>
          <!---->

          <div class="mail-black-list" v-if="domainDatas && domainDatas.length > 0">
            <ul>
              <li class="mail-black-list-item mail-black-header">
                <p>邮箱地址</p>
                <div class="btn-list">
                  <span class="mr-2">操作</span>
                </div>
              </li>
              <li v-for="domainData in domainDatas" :key="domainData.id" class="mail-black-list-item">
                <p>{{domainData.content}}</p>
                <div class="btn-list">
                  <div class="btn-list-item text-blue-300" @click="deleteBlacklist(domainData.id, 2)">删除</div>
                </div>
              </li>
            </ul>
            <ul class="mm-pagination mm-pagination__right">
              <li :class="['mm-pagination-prev', domainPageNum === 1 ? 'disabled': '']" @click="domainPrevPage">
                <svg class="mm-icon mm-icon-chevronleft" viewBox="0 0 24 24" name="chevronleft" fill="currentColor" style="height: 0.875rem; width: 0.875rem;">
                  <path d="M15.8 22l-9.6-9.4c-.3-.3-.3-.8 0-1.1l9.6-9.4c.3-.3.7-.3 1 0l1 1c.3.3.3.7 0 1l-7.6 7.4c-.3.3-.3.8 0 1.1l7.5 7.4c.3.3.3.7 0 1l-1 1c-.2.2-.6.2-.9 0z"></path>
                </svg>
              </li>
              <li>
                <ul class="mm-pagination-pager">
                  <li v-for="pageNumber in domainDisplayedPages" :key="pageNumber" :class="[pageNumber === domainPageNum ? 'active' : '', 'mm-pagination-number']">
                    <span @click="domainChangePage(pageNumber)">{{ pageNumber }}</span>
                  </li>
                </ul>
              </li>
              <li :class="['mm-pagination-next', domainPageNum === domainTotalPage ? 'disabled' : '']" @click="domainNextPage">
                <svg class="mm-icon mm-icon-chevronright" viewBox="0 0 24 24" name="chevronright" fill="currentColor" style="height: 0.875rem; width: 0.875rem;">
                  <path d="M8.3 2l9.5 9.5c.3.3.3.7 0 1L8.3 22c-.3.2-.8.2-1.1 0l-1-1c-.2-.3-.2-.8 0-1.1l7.6-7.4c.2-.3.2-.7 0-1L6.3 4.1C6 3.8 6 3.3 6.3 3l1-1c.3-.2.7-.2 1 0z"></path>
                </svg>
              </li>
            </ul>
          </div>

          <div class="mail-black-list" v-else>
            <div class="mm-empty no-data-block">
              <div class="empty-custom-render">
                <div class="empty-custom-image">
                  <img src="data:image/webp;base64,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">
                </div>
                <div class="empty-custom-desc">
                  <span>暂无数据</span>
                </div>
              </div>
            </div>
            <ul class="mm-pagination mm-pagination__right">
              <li class="disabled mm-pagination-prev">
                <svg class="mm-icon mm-icon-chevronleft" viewBox="0 0 24 24" name="chevronleft" fill="currentColor" style="height: 0.875rem; width: 0.875rem;">
                  <path d="M15.8 22l-9.6-9.4c-.3-.3-.3-.8 0-1.1l9.6-9.4c.3-.3.7-.3 1 0l1 1c.3.3.3.7 0 1l-7.6 7.4c-.3.3-.3.8 0 1.1l7.5 7.4c.3.3.3.7 0 1l-1 1c-.2.2-.6.2-.9 0z"></path>
                </svg>
              </li>
              <li>
                <ul class="mm-pagination-pager">
                  <li class="active mm-pagination-number">
                    <span>1</span>
                  </li>
                  <!---->
                  <!---->
                  <!---->
                </ul>
              </li>
              <li class="disabled mm-pagination-next">
                <svg class="mm-icon mm-icon-chevronright" viewBox="0 0 24 24" name="chevronright" fill="currentColor" style="height: 0.875rem; width: 0.875rem;">
                  <path d="M8.3 2l9.5 9.5c.3.3.3.7 0 1L8.3 22c-.3.2-.8.2-1.1 0l-1-1c-.2-.3-.2-.8 0-1.1l7.6-7.4c.2-.3.2-.7 0-1L6.3 4.1C6 3.8 6 3.3 6.3 3l1-1c.3-.2.7-.2 1 0z"></path>
                </svg>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <!---->
    </div>

    <addDomainTemplate ref="addDomain"></addDomainTemplate>
    <addEmailTemplate ref="addEmail"></addEmailTemplate>
  </div>
</template>
<style lang="scss">
@import '../../../static/scss/email/blacklist/68899.267e0df9.css';
</style>
<script>
import addEmailTemplate from './addEmail.vue';
import addDomainTemplate from './addDomain.vue';
import {listBlacklist, deleteBlacklist} from "@/api/email/blacklist";
import {EventBus} from "@/api/email/event-bus";

export default {
  components: {addEmailTemplate, addDomainTemplate},

  data() {
    return {
      emailContent: '',
      domainContent: '',
      emailPageNum: 1,
      domainPageNum: 1,
      pageSize: 5,
      emailTotal: 0,
      emailDatas: null,
      domainTotal: 0,
      domainDatas: null,
      maxDisplayedPages: 5
    }
  },

  computed: {
    emailTotalPage() {
      return Math.ceil(this.emailTotal / this.pageSize);
    },

    domainTotalPage() {
      return Math.ceil(this.domainTotal / this.pageSize);
    },

    emailDisplayedPages() {
      const middle = Math.floor(this.maxDisplayedPages / 2);
      const start = Math.max(1, this.emailPageNum - middle);
      const end = Math.min(this.emailTotalPage, start + this.maxDisplayedPages - 1);
      return Array.from({ length: end - start + 1 }, (_, index) => start + index);
    },

    domainDisplayedPages() {
      const middle = Math.floor(this.maxDisplayedPages / 2);
      const start = Math.max(1, this.domainPageNum - middle);
      const end = Math.min(this.domainTotalPage, start + this.maxDisplayedPages - 1);
      return Array.from({ length: end - start + 1 }, (_, index) => start + index);
    }
  },

  methods: {
    addEmailBtn() {
      this.$refs.addEmail.open();
    },

    addDomainBtn() {
      this.$refs.addDomain.open();
    },

    blacklistEmailList() {
      const query = {
        type: 1,
        content: this.emailContent,
        pageNum: this.emailPageNum,
        pageSize: this.pageSize
      }

      listBlacklist(query).then(response => {
        this.emailDatas = response.rows;
        this.emailTotal = response.total;
      }).catch(error => {
        console.error("Failed to fetch blacklist emails:", error);
      });
    },

    blacklistDomainList() {
      const query = {
        type: 2,
        content: this.domainContent,
        pageNum: this.domainPageNum,
        pageSize: this.pageSize
      }

      listBlacklist(query).then(response => {
        this.domainDatas = response.rows;
        this.domainTotal = response.total;
      }).catch(error => {
        console.error("Failed to fetch blacklist domains:", error);
      });
    },

    emailChangePage(pageNumber) {
      if (pageNumber >= 1 && pageNumber <= this.emailTotalPage) {
        this.emailPageNum = pageNumber;
        // 获取数据
        this.blacklistEmailList();
      }
    },

    domainChangePage(pageNumber) {
      if (pageNumber >= 1 && pageNumber <= this.domainTotalPage) {
        this.domainPageNum = pageNumber;
        // 获取数据
        this.blacklistDomainList();
      }
    },

    emailPrevPage() {
      if (this.emailPageNum > 1) {
        this.emailChangePage(this.emailPageNum - 1);
        this.blacklistEmailList();
      }
    },
    emailNextPage() {
      if (this.emailPageNum < this.emailTotalPage) {
        this.emailChangePage(this.emailPageNum + 1);
        this.blacklistEmailList();
      }
    },

    domainPrevPage() {
      if (this.domainPageNum > 1) {
        this.domainChangePage(this.domainPageNum - 1);
        this.blacklistDomainList();
      }
    },

    domainNextPage() {
      if (this.domainPageNum < this.domainTotalPage) {
        this.domainChangePage(this.domainPageNum + 1);
        this.blacklistDomainList();
      }
    },

    // 邮箱搜索
    emailSearch() {
      this.emailPageNum = 1;
      this.blacklistEmailList();
    },

    // 域名搜索
    domainSearch() {
      this.domainPageNum = 1;
      this.blacklistDomainList();
    },

    // 删除
    deleteBlacklist(id, type) {
      const data = {
        id: id
      };
      deleteBlacklist(data).then(response => {
        if (type === 1) {
          this.blacklistEmailList();
        } else if (type === 2){
          this.blacklistDomainList();
        }
      }).catch(error => {
        console.error("Failed to fetch blacklist domains:", error);
      });
    }
  },

  mounted() {
    EventBus.$on('refresh-blacklist-email-list', this.blacklistEmailList);
    EventBus.$on('refresh-blacklist-domain-list', this.blacklistDomainList);
  },

  beforeDestroy() {
    EventBus.$off('refresh-blacklist-email-list', this.blacklistEmailList);
    EventBus.$off('refresh-blacklist-domain-list', this.blacklistDomainList);
  },

  created() {
    this.blacklistEmailList();
    this.blacklistDomainList();
  },
};

</script>
