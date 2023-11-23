import Router from '@/router'
import {country} from '@/assets/data/countryData'

// 空占位
export function EmptyStr(text) {
    if (!text) {
        return '---'
    }
    return text
}

export function targetBlank(url) {
    const path = Router.resolve({
        path: url,
    })
    window.open(path.href, "_blank")
}

export function generatePhone() {
    return country.map(val => {
        return {
            label: `${val?.area || ''} ${val?.areaCode || ''}`,
            value: val?.areaCode || ''
        }
    })
}

// 将数组映射成对象值
export function generateMapKey(arr, key = 'value', value = 'label') {
    let mapValue = {}
    if (arr && !arr?.length) {
        return mapValue
    }
    arr.forEach(val => {
        Object.assign(mapValue, {[val[key]]: val[value]})
    })
    return mapValue
}

// 格式化字节
export function formatFileSize(bytes) {
    let units = ['B', 'KB', 'MB', 'GB', 'TB']
    while (bytes >= 1024 && units.length > 1) {
        bytes /= 1024
        units.shift()
    }
    return Number(bytes.toFixed(2)) + units[0]
}
