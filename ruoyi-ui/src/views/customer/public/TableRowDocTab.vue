<template>
  <div v-loading="tableLoading">
    <el-row class="mb-10">
      <CollapseWrap is-collapse :height="400">
        <template slot="header">
          <el-col class="flex-middle space-between">
            <div>文档</div>
            <el-dropdown trigger="click" @command="handleDocCommand">
              <el-tooltip placement="top" content="上传文件">
                <i class="el-icon-circle-plus-outline pointer" @click.stop></i>
              </el-tooltip>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="location">本地文件</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>

          </el-col>
        </template>
        <el-col class="mt-10 doc-form">
          <formCreate
              v-model="fApi"
              :rule="rule"
              :option="option"/>
        </el-col>
        <TableNext :list="tradeList" :columns="tradeColumns" :extra-option="extraOption"/>
      </CollapseWrap>

      <el-dialog
          title="上传文件"
          :visible.sync="uploadVisible"
          width="500px"
          :append-to-body="true"
          :close-on-click-modal="false"
          @close="uploadClose">
        <div class="flex-center my-10">
          <el-upload
              drag
              multiple
              action=""
              :auto-upload="false"
              :file-list="fileList"
              :on-change="handleChange"
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          </el-upload>
        </div>

        <template #footer>
          <el-button @click="uploadClose">取 消</el-button>
          <el-button type="primary" @click="confirmSubmit">提 交</el-button>
        </template>
      </el-dialog>
    </el-row>
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import CollapseWrap from "@/components/CollapseWrap/index.vue";
import {formOption} from "@/constant/form"
import {
  deleteImportDocument,
  downloadImportDocument,
  getImportDocumentList, uploadMultipleDocument,
} from "@/api/customer/config";
import {EmptyStr, formatFileSize} from "@/utils/tools";
import {getToken} from "@/utils/auth";

export default {
  props: {
    row: {
      type: Object,
      default: () => {
        return {
          customerId: ''
        }
      },
      required: false
    }
  },
  components: {TableNext, CollapseWrap},
  data() {
    return {
      tableLoading: false,
      fApi: {},
      rule: [{
        type: 'row',
        props: {
          type: 'flex',
          gutter: 4
        },
        children: [
          {
            type: 'col',
            props: {
              span: 8
            },
            children: [{
              type: "input",
              field: 'docName',
              props: {
                placeholder: '搜索文档名称',
              },
              children: [{
                type: 'button',
                props: {
                  icon: 'el-icon-search'
                },
                on: {
                  click: () => {
                    this.onSearch()
                  }
                },
                slot: 'append'
              }]
            }]
          },
          {
            type: 'col',
            props: {
              span: 8
            },
            children: [{
              field: "type",
              type: 'select',
              value: '0',
              options: [
                {value: "0", label: '全部类型'},
                {value: "1", label: '有奖上传'},
                {value: "2", label: '手动上传'},
                {value: "3", label: '销售订单已导单据'},
                {value: "4", label: '报价单已导单据'},
              ],
            }]
          },
          {
            type: 'col',
            props: {
              span: 8,
            },
            children: [
              {
                field: "date",
                type: 'DatePicker',
                props: {
                  type: "daterange",
                  format: "yyyy-MM-dd",
                  unlinkPanels: true,
                  rangeSeparator: "-",
                  startPlaceholder: "开始日期",
                  endPlaceholder: "结束日期",
                  pickerOptions: {
                    shortcuts: [
                      {
                        text: '今天',
                        onClick(picker) {
                          const today = new Date();
                          picker.$emit('pick', [today, today]);
                        }
                      },
                      {
                        text: '明天',
                        onClick(picker) {
                          const yesterday = new Date();
                          yesterday.setTime(yesterday.getTime() + 3600 * 1000 * 24);
                          picker.$emit('pick', [yesterday, yesterday]);
                        }
                      },
                      {
                        text: '未来7天',
                        onClick(picker) {
                          const end = new Date();
                          const start = new Date();
                          end.setTime(end.getTime() + 3600 * 1000 * 24 * 7);
                          picker.$emit('pick', [start, end]);
                        }
                      }, {
                        text: '未来30天',
                        onClick(picker) {
                          const end = new Date();
                          const start = new Date();
                          end.setTime(end.getTime() + 3600 * 1000 * 24 * 30);
                          picker.$emit('pick', [start, end]);
                        }
                      }, {
                        text: '未来90天',
                        onClick(picker) {
                          const end = new Date();
                          const start = new Date();
                          end.setTime(end.getTime() + 3600 * 1000 * 24 * 90);
                          picker.$emit('pick', [start, end]);
                        }
                      }]
                  }
                }
              },
            ]
          }]
      }],
      option: {...formOption},
      tradeList: [],
      tradeColumns: [
        {
          label: '文件名称',
          field: 'name',
          width: 200,
          render: (_row, field) => EmptyStr(field)
        },
        {
          label: '关联类型',
          field: 'type',
          width: 200,
          render: (_row, field) => {
            const mapType = {
              1: '邮件附件',
              2: '手动上传',
            }
            return <div>{mapType[field] || '---'}</div>
          }
        },
        {
          label: '文件大小',
          field: 'size',
          render: (_row, field) => {
            return formatFileSize(field)
          }
        },
        {
          label: '添加人',
          field: 'createBy',
          render: (_row, field) => EmptyStr(field)
        },
        {
          label: '操作',
          field: 'operate',
          fixed: 'right',
          width: 60,
          render: (row, _field) => {
            return <el-dropdown
                trigger="click"
                on={{
                  command: (value) => this.handleCommand(value, row)
                }}>
              <i class="operate-more pointer el-icon-more-outline" style="transform: rotate(90deg)"></i>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="send">发送邮件</el-dropdown-item>
                <el-dropdown-item command="download">下载附件</el-dropdown-item>
                <el-dropdown-item command="upload">上传云盘</el-dropdown-item>
                <el-dropdown-item command="delete">删除文档</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          }
        },
      ],
      extraOption: {},
      uploadVisible: false,
      uploadData: {
        customerId: ''
      },
      fileList: [],
    }
  },
  watch: {
    row: {
      handler(newVal) {
        if (newVal.id) {
          this.$nextTick(() => {
            this.uploadData.customerId = this.row.id
            this.getList()
          })
        }
      },
      deep: true,
    }
  },
  methods: {
    async getList() {
      try {
        const res = await getImportDocumentList({
          customerId: this.row.id
        })
        if (res.code === 200) {
          this.tradeList = res.data
        }
      } catch {
      }
    },
    handleCommand(value, row) {
      switch (value) {
        case 'send':
          break;
        case 'download':
          this.downloadDocFile(row.name, row.id)
          break;
        case 'upload':
          break;
        case 'delete':
          this.deleteImportDoc(row.id)
          break;
      }
    },
    async deleteImportDoc(id) {
      try {
        const res = await deleteImportDocument({id})
        if (res.code === 200) {
          this.$message.success('删除成功')
          await this.getList()
        }
      } catch {
      }
    },
    async downloadDocFile(name, id) {
      console.log(name, id)
      try {
        const res = await downloadImportDocument(id)
        const url = window.URL.createObjectURL(new Blob([res]));
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', name);
        document.body.appendChild(link);
        link.click();
      } catch {
      }
    },
    handleDocCommand(value) {
      switch (value) {
        case "location":
          this.uploadVisible = true
          break;
      }
    },
    handleChange(file, fileList) {
      let existFile = fileList.slice(0, fileList.length - 1).find(f => f.name === file.name);
      if (existFile) {
        this.$message.error('当前文件已经存在!');
        fileList.pop();
      }
      this.fileList = fileList;
    },

    // 删除图片
    handleDelete(file) {
      const fIndex = this.fileList.map(f => f.name).indexOf(file.name);
      if (fIndex > -1) {
        this.fileList.splice(fIndex, 1);
      }
    },
    async confirmSubmit() {
      if (!this.fileList.length) {
        this.$message.warning('请选择文件')
        return
      }
      let formData = new FormData()
      this.fileList.forEach(item => {
        formData.append('files', item.raw)
      })
      formData.append('customerId', this.row.id)
      try {
        const res = await uploadMultipleDocument(formData)
        if (res.code === 200) {
          this.$message.success('上传成功')
          this.uploadClose()
          await this.getList()
        }
      } catch {
      }
    },
    uploadClose() {
      this.fileList = []
      this.uploadVisible = false
    },
    onSearch() {
      console.log(this.fApi.formData())
    }
  }
}
</script>

<style lang="scss" scoped>
.doc-form {
  ::v-deep .el-form-item__content {
    width: 100%;

    .el-select, .el-date-editor {
      width: 100%;
    }
  }
}
</style>

