import {country} from '@/assets/data/countryData'

function generatePhone(arr) {
  return arr.map(val => {
    return {
      label: `${val?.area || ''} ${val?.areaCode || ''}`,
      value: val?.areaCode || ''
    }
  })
}

const UsuallyInfoRule = [
  // 占位
  {
    field: 'id',
    type: 'hidden'
  },
  {
    title: '公司网址',
    type: 'input',
    field: 'website',
    props: {
      placeholder: '请输入'
    },
  },
  {
    title: '公司名称',
    type: 'input',
    field: 'name',
    props: {
      placeholder: '请输入'
    },
    validate: [
      {
        required: true,
        message: '请输入',
        trigger: 'blur'
      }
    ],
  },
  {
    title: '简称',
    type: 'input',
    field: 'simpleName',
    props: {
      placeholder: '请输入'
    },
  },
  {
    title: '国家地区',
    type: 'select',
    field: 'area',
  },
  {
    title: '客户来源',
    type: 'select',
    field: 'origin',
  },
  {
    title: '客户标签',
    type: 'select',
    field: 'tag',
  },
  {
    title: '分组',
    type: 'select',
    field: 'group',
  },
  {
    title: '客户阶段',
    type: 'select',
    field: 'stage',
  },
  {
    title: '客户星级',
    type: 'rate',
    field: 'rate',
  },
  {
    title: '客户编号',
    type: 'input',
    field: 'customerId',
  },
  {
    title: '公海分组',
    type: 'select',
    field: 'poolGroup',
    validate: [
      {
        required: true,
        message: '请选择',
        trigger: 'change'
      }
    ],
  },
  {
    type: 'col',
    children: [
      {
        type: 'row',
        children: [
          {
            type: 'col',
            props: {
              span: 24
            },
            style: {
              fontSize: '14px',
              color: '#606266',
              fontWeight: '700',
              lineHeight: '36px',
            },
            children: ['座机']
          },
          {
            type: 'col',
            props: {
              span: 24
            },
            children: [
              {
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
                    children: [
                      {
                        type: 'select',
                        field: 'telPrefix',
                        props: {
                          filterable: true,
                          clearable: true
                        },
                        options: generatePhone(country)
                      }
                    ]
                  },
                  {
                    type: 'col',
                    props: {
                      span: 16
                    },
                    children: [
                      {
                        type: 'input',
                        field: 'tel',
                        props: {
                          placeholder: '电话号码'
                        }
                      }
                    ]
                  }
                ]
              }
            ]
          }
        ]
      }
    ]
  }
]

const OtherInfoRule = [
  {
    title: '主营产品',
    type: 'cascader',
    field: 'product',
  },
  {
    title: '客户类型',
    type: 'select',
    field: 'type',
  },
  {
    title: '年采购额',
    type: 'select',
    field: 'year',
  },
  {
    title: '采购意向',
    type: 'select',
    field: 'yx',
  },
  {
    title: '时区',
    type: 'select',
    field: 'sq',
  },
  {
    title: '规模',
    type: 'select',
    field: 'gm',
  },
  {
    title: '产品分组',
    type: 'select',
    field: 'productGroup'
  },
  {
    title: '传真',
    type: 'input',
    field: 'cz',
    props: {
      placeholder: '请输入'
    },
  },
  {
    title: '详细地址',
    type: 'input',
    field: 'address',
    props: {
      placeholder: '请输入'
    },
  },
  {
    title: '公司备注',
    type: 'input',
    props: {
      type: 'textarea',
      rows: 3,
      resize: 'none',
      placeholder: '请输入'
    },
    field: 'remark'
  },
  {
    title: '公司logo',
    type: 'upload',
    field: 'logo',
    props: {
      action: 'https://jsonplaceholder.typicode.com/posts/',
      multiple: false,
      limit: 1,
      uploadType: 'image*/',
      name: 'file',
    },
  }
]


export {
  UsuallyInfoRule,
  OtherInfoRule
}
