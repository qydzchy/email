<template>
  <div class="active-tab">
    <div class="create-company flex-middle flex-center py-2">
      <p class="fs-14">
        如有新的交易，可在此
        <el-button type="text" style="text-underline: #0d0c0c">新建商机</el-button>
        进行管理。
      </p>
    </div>
    <div class="mt-20">
      <div class="flex-middle space-between">
        <div class="fs-14 bold">写跟进</div>
        <el-button round size="medium">选择模板</el-button>
      </div>
      <el-input class="mt-10" placeholder="点击这里记录跟进细节，同步最新进展。">
        <template #suffix>
          <i class="el-icon-full-screen mr-6"></i>
        </template>

      </el-input>
    </div>
    <div class="mt-20">
      <div class="flex-middle space-between">
        <div class="fs-14 bold">计划日程</div>
        <el-button round size="medium">添加日程</el-button>
      </div>
    </div>
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
        <el-timeline>
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
                    <span class="fs-12 bold">快速记录</span>
                    <div class="line"></div>
                    <span class="fs-12">操作人：allxchips</span>
                  </div>
                  <div class="fs-12">
                    {{ item.timestamp }}
                  </div>
                </div>
              </div>
              <div class="card-content">
                <div class="message px-20">
                  <div class="pt-10">hello你好</div>
                  <div class="pt-10">关联联系人：王五</div>
                  <div class="flex-end">
                    <el-button type="text" icon="el-icon-s-comment" size="small">评论</el-button>
                  </div>
                </div>
                <div class="comment mt-20 px-20">
                  <div class="comment-item flex-column mb-10" v-for="item in 2" :key="item">
                    <div class="flex-middle space-between">
                      <el-row type="flex" align="middle" class="fs-14">
                        <i class="el-icon-s-comment"></i>
                        <span class="pl-14 gray-text">allxchips</span>
                        <span class="pl-10 gray-text">11-03 08:11</span>
                      </el-row>
                      <el-row class="icon-operate" :gutter="8">
                        <el-col>
                          <el-tooltip placement="top" content="删除评论">
                            <i class="el-icon-delete pointer"></i>
                          </el-tooltip>
                        </el-col>
                        <el-col>
                          <el-tooltip placement="top" content="编辑评论">
                            <i class="el-icon-edit pointer"></i>
                          </el-tooltip>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="comment-content mt-10">
                      测试
                    </div>
                  </div>
                </div>
              </div>
            </el-card>

          </el-timeline-item>

        </el-timeline>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  data() {
    return {
      sortActive: "2",
      timeLineList: [
        {
          content: '支持使用图标',
          timestamp: '2018-04-12 20:46',
          size: 'large',
          icon: 'el-icon-document',
          color: '#0bbd87'
        }, {
          content: '支持自定义颜色',
          timestamp: '2018-04-03 20:46',

        }, {
          content: '支持自定义尺寸',
          timestamp: '2018-04-03 20:46',
          size: 'large'
        }, {
          content: '默认样式的节点',
          timestamp: '2018-04-03 20:46'
        }
      ]
    }
  },
  methods: {
    onSortTime() {
      const mapSort = {
        "1": "2",
        "2": "1"
      }
      this.sortActive = mapSort[this.sortActive]
    },
  }
}
</script>

<style lang="scss" scoped>
.active-tab {
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
