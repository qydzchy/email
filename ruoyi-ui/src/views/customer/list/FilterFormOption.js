const ordinaryRuleForm = [
  // 占位
  {
    field: 'manId',
    type: 'hidden'
  },
  {
    title: '公司名称',
    type: 'input',
    field: 'companyName',
    props: {
      placeholder: '请输入'
    },
    validate: [
      {
        required: false,
        message: '请输入',
        trigger: 'blur'
      }
    ],
    col: {
      span: 12
    }
  },
  {
    title: '邮箱',
    type: 'input',
    field: 'email',
    props: {
      placeholder: '请输入'
    },
    validate: [
      {
        required: false,
        message: '请输入',
        trigger: 'blur'
      }
    ],
    col: {
      span: 12
    }
  },
  {
    title: '客户标签',
    type: 'radio',
    field: 'tagRadio',
    value: "0",
    options: [
      {value: "0", label: "包含任一标签"},
      {value: "1", label: "包含全部标签"},
    ],
  },
  {
    title: '',
    type: 'select',
    field: 'tag',
    value: ["0"],
    props: {
      multiple:true,
    },
    options: [
      {
        value: "0", label: '新鲜水果', 'slot': function ($h) {
          return $h('el-tag', {
            props:{color:'#eaeaea'}
          }, ['新鲜水果'])
        }
      },
      {value: "1", label: "包含全部标签"},
    ],
  },
  {
    title: '分组',
    type: 'tree-select-next',
    field: 'group',
    props: {
      treeData: [],
      echoData: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    }
  },
  {
    title: '国家地区',
    type: 'select',
    field: 'area',
  },
  {
    title: '来源',
    type: 'select',
    field: 'origin',
    options: [
      {value: "0", label: '测试',},
      {value: "1", label: "包含全部标签"},
    ],
    props: {
      multiple: true,
    }
  },
  {
    title: '客户星级',
    type: 'select',
    field: 'origin',
    options: [
      {value: "0", label: '0星',},
      {value: "1", label: '1星',},
      {value: "2", label: '2星',},
      {value: "3", label: '3星',},
      {value: "4", label: '4星',},
      {value: "5", label: '5星',},
    ],
    props: {}
  },
  {
    type: 'row',
    children: [
      {
        type: 'col',
        props: {
          span: 24,
        },
        style: {
          display: 'inline-block',
          lineHeight: '36px',
          fontSize: '14px',
          color: '#606266',
          fontWeight: 700,
        },
        children: ['最近联系时间']
      },
      {
        type: 'row',
        children: [
          {
            type: 'col',
            props: {
              span: 10, lg: 10, xl: 10, md: 24, sm: 24, xs: 24
            },
            style: {
              display: 'flex',
              alignItems: 'center',
              paddingBottom: '6px'
            },
            children: [
              {
                type: 'col',
                props: {
                  span: 4,
                },
                children: ['最近'],
              },
              {
                type: 'col',
                children: [
                  {
                    type: 'inputNumber',
                    field: 'day',
                    props: {
                      controlsPosition: 'right',
                    }
                  }
                ]
              }
            ]
          },
          {
            type: 'col',
            props: {
              span: 14, lg: 14, xl: 14, md: 24, sm: 24, xs: 24
            },
            style: {
              display: 'flex',
              alignItems: 'center',
              paddingBottom: '6px'
            },
            children: [{
              type: 'col',
              props: {
                span: 4,
              },
              children: ['天内']
            }, {
              type: 'col',
              children: [
                {
                  type: 'select',
                  field: 'contact'
                }
              ]
            }]
          },

        ]
      },

    ]
  },
  {
    title: '下次移入公海日期',
    field: "movePoolDate",
    type: 'DatePicker',
    props: {
      type: "daterange",
      format: "yyyy-MM-dd",
      unlinkPanels: true,
      rangeSeparator: "至",
      startPlaceholder: "开始日期",
      endPlaceholder: "结束日期",
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          }
        }]
      }
    }
  },
  {
    title: '是否关注',
    type: 'radio',
    field: 'isFollow',
    value: "0",
    options: [
      {value: "0", label: "不限制"},
      {value: "1", label: "已关注"},
      {value: "2", label: "未关注"},
    ],

  },
  {
    title: '跟进人',
    type: 'cascader',
    field: 'follower',
  }
]

export {
  ordinaryRuleForm
}
