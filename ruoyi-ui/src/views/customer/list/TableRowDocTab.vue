<template>
  <div v-loading="tableLoading">
    <el-row class="mb-10">
      <el-col class="flex-middle space-between">
        <div>文档</div>
        <el-dropdown trigger="click">
          <el-tooltip placement="top" content="上传文件">
            <i class="el-icon-circle-plus-outline pointer"></i>
          </el-tooltip>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>本地文件</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>

      </el-col>
      <el-col class="mt-10 doc-form">
        <formCreate
          v-model="fApi"
          :rule="rule"
          :option="option"/>
      </el-col>
      <TableNext :list="tradeList" :columns="tradeColumns" :extra-option="extraOption"/>
    </el-row>
  </div>
</template>

<script>
import TableNext from "@/components/TableNext/index.vue";
import {formOption} from "@/constant/form"

export default {
  components: {TableNext},
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
        },
        {
          label: '关联类型',
          field: 'type',
          width: 200,
        },
        {
          label: '文件大小',
          field: 'size',
        },
        {
          label: '添加人',
          field: 'person',
        },
        {
          label: '操作',
          field: 'operate',
          fixed: 'right',
          width: 60,
        },
      ],
      extraOption: {}
    }
  },
  methods: {
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
    .el-select,.el-date-editor{
      width: 100%;
    }
  }
}
</style>

