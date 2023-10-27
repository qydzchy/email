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
          <el-button class="kind-button" size="medium" round @click="followTextDialog=true">添加模板</el-button>
        </div>

      </div>
      <div class="mt-16 flex-wrap gap-16">
        <div class="follow-text-template radius-5" style="width:33.333%" v-for="item in followTextList"
             :key="item.id">
          <div class="wrap px-16 py-14 radius-4 flex-column">
            <div class="card-head flex-middle space-between">
              <div class="fs-14 bold">模板名称：{{ item.templateName }}</div>
              <el-row :gutter="2">
                <el-button type="text" @click="onEdit('followText',item)">编辑</el-button>
                <DelPopover @onDelete="confirmDelTemplate"/>
              </el-row>
            </div>
            <div class="card-main fs-14 gray-text">
              {{ item.templateContent }}
            </div>
          </div>
        </div>
      </div>
    </el-card>
    <!--  跟进模板表单  -->
    <el-dialog title="添加快捷模板" width="500px" style="margin-top: 25vh" :visible.sync="followTextDialog"
               destroy-on-close>
      <el-form :model="followTextForm" ref="followTextRef" :rules="followTextRules">
        <el-form-item label="模板名称" prop="templateName">
          <el-input v-model="followTextForm.templateName" autocomplete="off" placeholder="请输入模板名称"></el-input>
        </el-form-item>
        <el-form-item label="模板内容" prop="templateContent">
          <el-input type="textarea" v-model="followTextForm.templateContent" :rows="4" resize="none"
                    autocomplete="off" placeholder="请输入模板内容"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button round @click="onCancel('followText')">取 消</el-button>
        <el-button type="primary" round @click="onConfirm('followText','followTextRef')">确 定</el-button>
      </div>
    </el-dialog>

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
          <el-button class="kind-button" size="medium" round @click="fastTextDialog=true">添加文本分组</el-button>
        </div>

      </div>
      <div class="mt-16 flex-wrap gap-16">
        <div class="follow-text-template radius-5 mb-20" v-for="item in fastTextList" :key="item.id">
          <div class="px-16 py-14 radius-4 flex-column">
            <div class="card-head flex-middle space-between">
              <div class="fs-14 bold">模板名称：{{ item.groupName }}</div>
              <el-row :gutter="2">
                <el-button type="text" @click="onEdit('fastText',item)">编辑</el-button>
                <DelPopover :id="item" @onDelete="confirmDelText"/>
              </el-row>
            </div>
            <div class="flex-middle gap-8">
              <el-tag effect="plain" v-for="(tag,idx) in item.textTag" :key="idx">{{ tag }}</el-tag>
            </div>
          </div>
        </div>
      </div>
    </el-card>


    <!--  快捷文本表单  -->
    <el-dialog title="添加快捷文本" width="500px" style="margin-top: 25vh" :visible.sync="fastTextDialog"
               destroy-on-close>
      <el-form :model="fastTextForm" ref="fastTextRef" :rules="fastTextRules">
        <el-form-item label="文本分组名称" prop="groupName">
          <el-input v-model="fastTextForm.groupName" autocomplete="off" placeholder="请输入文本分组名称"></el-input>
        </el-form-item>
        <el-form-item label="文本标签" prop="groupName">
          <el-select
            style="width: 100%"
            autocomplete="off"
            multiple
            clearable
            filterable
            allow-create
            default-first-option
            v-model="fastTextForm.textTag"
            placeholder="请输入文本标签">
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button round @click="onCancel('fastText')">取 消</el-button>
        <el-button type="primary" round @click="onConfirm('fastText','fastTextRef')">确 定</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
import DelPopover from "./DelPopover.vue";

const iniFollowTextForm = {
  templateName: '',
  templateContent: ''
}

const iniFastTextForm = {
  groupName: '',
  textTag: ''
}

export default {
  components: {
    DelPopover
  },
  data() {
    return {
      // 写跟进时间
      followTextRadio: 1,
      followTextList: [
        {id: 1, templateName: '模板名称测试', templateContent: '模板内容测试'}
      ],
      followTextDialog: false,
      followTextForm: iniFollowTextForm,
      followTextRules: {
        templateName: [
          {required: true, message: '请输入文本分组名称', trigger: 'blur'},
        ],
        templateContent: [
          {required: true, message: '请输入文本标签', trigger: 'blur'},
        ],
      },
      // 快捷文本
      fastTextList: [
        {id: 1, groupName: '模板名称测试', textTag: ['hello', '你好', 'test']}
      ],
      fastTextDialog: false,
      fastTextForm: iniFastTextForm,
      fastTextRules: {
        groupName: [
          {required: true, message: '请输入文本分组名称', trigger: 'blur'},
        ],
        textTag: [
          {required: true, message: '请添加文本标签', trigger: 'change'},
        ],
      }
    }
  },
  methods: {
    confirmDelTemplate(id) {

    },
    confirmDelText(id) {
      console.log(id)
    },
    // 编辑
    onEdit(type, item) {
      if (type === 'followText') {
        this.followTextForm = {...item}
        this.followTextDialog = true
      } else if (type === 'fastText') {
        this.fastTextForm = {...item}
        this.fastTextDialog = true
      }
    },
    // 确认
    onConfirm(type, formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (type === 'followText') {
            console.log(this.followTextForm)
            // this.followTextDialog = false
          } else if (type === 'fastText') {
            console.log(this.fastTextForm)
            // this.fastTextDialog = false
          }
        } else {
          return false;
        }
      });
    },
    // 取消
    onCancel(type) {
      if (type === 'followText') {
        this.followTextForm = iniFollowTextForm
        this.followTextDialog = false
      } else if (type === 'fastText') {
        this.fastTextForm = iniFastTextForm
        this.fastTextDialog = false
      }
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
