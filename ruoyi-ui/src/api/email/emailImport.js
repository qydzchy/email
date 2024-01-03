import request from '@/utils/request'

// 邮件任务列表
export function getEmailTaskList() {
    return request({
        url: '/email/import/task/list',
        method: 'get'
    })
}
// 文件夹列表
export function getImportFolderList() {
    return request({
        url: '/email/import/folder/list',
        method: 'get'
    })
}
// 导入邮箱
export function emailImport(data) {
    return request({
        headers: {
            'Content-Type': 'multipart/form-data'
        },
        url: '/email/import/upload',
        method: 'post',
        data
    })
}
// 已导入邮件列表
export function getEmailImportList(params) {
    return request({
        url: '/email/import/page',
        method: 'get',
        params
    })
}

