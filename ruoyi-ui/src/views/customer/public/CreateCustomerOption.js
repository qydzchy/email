import {generatePhone} from "@/utils/tools";
import {timeZoneList} from "@/assets/data/countryData";
import {getToken} from "@/utils/auth";


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
        emit: ['on-change', 'blur'],
        emitPrefix: 'country',
    },
    {
        title: '客户来源',
        type: 'el-select-tree',
        field: 'sourceIds',
        value: [],
        props: {
            data: [],
            props: {
                value: 'id',
                label: 'name',
            },
            showCheckbox: true,
            checkStrictly: true,
            multiple: true,
            collapseTags: true,
        },
    },
    {
        title: '客户标签',
        type: 'select',
        field: 'tagIds',
        props: {
            multiple: true
        },
        options: [],
    },
    {
        title: '分组',
        type: 'el-select-tree',
        field: 'packetId',
        value: '',
        props: {
            data: [],
            props: {
                value: 'id',
                label: 'name',
            },
            checkStrictly: true,
        },
    },
    {
        title: '客户阶段',
        type: 'select',
        field: 'stageId',
        options: [],
    },
    {
        title: '客户星级',
        type: 'rate',
        field: 'rating',
    },
    {
        title: '客户编号',
        type: 'radio',
        field: 'customerNoType',
        value: 1,
        options: [
            {value: 1, label: "自动生成"},
            {value: 2, label: "自定义"},
        ],
        update(val, rule) {
            if (val === 1) {
                rule.control[0].rule[0].value = ''
            }
        },
        control: [
            {
                handle: val => val === 2,
                rule: [
                    {
                        type: 'input',
                        field: 'customerNo',
                        value: '',
                        props: {
                            placeholder: '请输入',
                            clearable: true
                        }
                    }
                ]
            }
        ]
    },
    {
        title: '公海分组',
        type: 'select',
        field: 'poolGroup',
        value: 1,
        options: [],
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
    // {
    //     title: '主营产品',
    //     type: 'cascader',
    //     field: 'product',
    // },
    // {
    //     title: '客户类型',
    //     type: 'select',
    //     field: 'type',
    // },
    // {
    //     title: '年采购额',
    //     type: 'select',
    //     field: 'year',
    // },
    // {
    //     title: '采购意向',
    //     type: 'select',
    //     field: 'yx',
    // },
    {
        title: '时区',
        type: 'select',
        field: 'timezone',
        options: timeZoneList,
        tempOptions: timeZoneList,
        props: {
            filterable: true,
        }
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
    // {
    //     title: '产品分组',
    //     type: 'select',
    //     field: 'productGroup'
    // },
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
        value: '',
        props: {
            action: process.env.VUE_APP_BASE_API + "/common/upload",
            fileList: [],
            multiple: false,
            limit: 1,
            uploadType: "image",
            accept: "image/*",
            name: 'file',
            withCredentials: true,
            modalTitle:'公司logo',
            headers: {
                Authorization: 'Bearer ' + getToken()
            },
            onSuccess: (res, file) => {
                file.url = process.env.VUE_APP_BASE_API + res.fileName
            }
        },
    }
]


export {
    UsuallyInfoRule,
    OtherInfoRule
}
