<template>
  <div>
    <!-- 写跟进时间 -->
    <el-card class="mb-20" shadow="hover">
      <div class="mb-20 flex-middle">
        <i class="vertical-line mr-5"></i>
        <span class="bold">写跟进时间</span>
      </div>
      <el-radio-group v-model="followTextRadio">
        <el-radio :label="1">提交跟进记录时默认提交时间，不允许修改</el-radio>
        <el-radio :label="0">提交跟进记录时默认提交时间，可手动修改为历史时间</el-radio>
      </el-radio-group>
    </el-card>
    <!-- 写跟进模板 -->
    <el-card class="mb-20" shadow="hover">
      <div class="space-between">
        <div>
          <div class="mb-6 flex-middle">
            <i class="vertical-line mr-5"></i>
            <span class="bold">写跟进模板</span>
          </div>
          <div class="fs-14">
            可设置公司统一的模板，业务员在写跟进时可根据模板填写对应内容。
          </div>
        </div>
        <div class="flex-middle">
          <el-button class="kind-button" size="medium" round>添加模板</el-button>
        </div>

      </div>
      <div class="mt-16 flex-wrap gap-16">
        <!--    TODO遍历数据    -->
        <div class="follow-text-template radius-5" style="width:33.333%">
          <div class="wrap px-16 py-14 radius-4 flex-column">
            <div class="card-head flex-middle space-between">
              <div class="fs-14 bold">模板名称：模板名称测试</div>
              <el-row :gutter="2">
                <el-button type="text">编辑</el-button>
                <el-button type="text">删除</el-button>
              </el-row>
            </div>
            <div class="card-main fs-14 gray-text">
              模板内容测试
            </div>
          </div>
        </div>
      </div>
    </el-card>
    <!-- 写跟进快捷文本 -->
    <el-card class="mb-20" shadow="hover">
      <div class="space-between">
        <div>
          <div class="mb-6 flex-middle">
            <i class="vertical-line mr-5"></i>
            <span class="bold">写跟进快捷文本</span>
          </div>
          <div class="fs-14">
            可设置公司统一的常用文本，业务员在写跟进时可快速选择。
          </div>
        </div>
        <div class="flex-middle">
          <el-button class="kind-button" size="medium" round>添加文本分组</el-button>
        </div>

      </div>
      <div class="mt-16 flex-wrap gap-16">
        <!--    TODO遍历数据    -->
        <div class="follow-text-template radius-5 mb-20" v-for="item in 6" :key="item">
          <div class="px-16 py-14 radius-4 flex-column">
            <div class="card-head flex-middle space-between">
              <div class="fs-14 bold">模板名称：模板名称测试</div>
              <el-row :gutter="2">
                <el-button type="text">编辑</el-button>
                <el-button type="text">删除</el-button>
              </el-row>
            </div>
            <div class="flex-middle gap-8">
              <el-tag effect="plain">hello</el-tag>
              <el-tag effect="plain">你好</el-tag>
            </div>
          </div>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 写跟进时间
      followTextRadio: 1
    }
  }
}
</script>

<style lang="scss" scoped>
.vertical-line {
  display: inline-block;
  width: 4px;
  height: 14px;
  border-radius: 4px;
  background-color: #017aff;
}

.kind-button {
  color: #0064ff;
  border-color: #0064ff;
}

.follow-text-template {
  border: 1px solid #dfe1e5;
  background-color: #f7f8fb;

  .wrap {
    height: 148px;
  }
}
</style>
