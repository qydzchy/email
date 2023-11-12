import {generatePhone} from "@/utils/tools";


const UsuallyInfoRule = [
    // 占位
    {
        field: 'id',
        type: 'hidden'
    },
    {
        title: '公司网址',
        type: 'input',
        field: 'companyWebsite',
        props: {
            placeholder: '请输入'
        },
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
                required: true,
                message: '请输入',
                trigger: 'blur'
            }
        ],
    },
    {
        title: '简称',
        type: 'input',
        field: 'shortName',
        props: {
            placeholder: '请输入'
        },
    },
    {
        title: '国家地区',
        type: 'select-country',
        field: 'countryRegion',
    },
    {
        title: '客户来源',
        type: 'select',
        field: 'origin',
    },
    {
        title: '客户标签',
        type: 'select',
        field: 'tagIds',
    },
    {
        title: '分组',
        type: 'select',
        field: 'packetId',
    },
    {
        title: '客户阶段',
        type: 'select',
        field: 'stageId',
    },
    {
        title: '客户星级',
        type: 'rate',
        field: 'rating',
    },
    {
        title: '客户编号',
        type: 'input',
        field: 'customerNo',
        props: {
            placeholder: '请输入'
        },
    },
    {
        title: '公海分组',
        type: 'select',
        field: 'poolGroup',
        options: [
            {value: 1, label: '公共公海分组'},
            {value: 2, label: '公海2组'},
        ],
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
                                                field: 'phonePrefix',
                                                props: {
                                                    filterable: true,
                                                    clearable: true
                                                },
                                                options: generatePhone()
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
                                                field: 'phone',
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
        field: 'timezone',
    },
    {
        title: '规模',
        type: 'select',
        field: 'scale',
        options: [
            {value: 1, label: '少于59人'},
            {value: 2, label: '60-149人'},
            {value: 3, label: '150-499人'},
            {value: 4, label: '500-999人'},
            {value: 5, label: '1000-4999人'},
            {value: 6, label: '5000人以上'},
        ]
    },
    {
        title: '产品分组',
        type: 'select',
        field: 'productGroup'
    },
    {
        title: '传真',
        type: 'input',
        field: 'fax',
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
        field: 'companyRemarks',
        props: {
            type: 'textarea',
            rows: 3,
            resize: 'none',
            placeholder: '请输入'
        },
    },
    {
        title: '公司logo',
        type: 'upload',
        field: 'companyLogo',
        props: {
            action: process.env.VUE_APP_BASE_API + "/common/upload",
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
