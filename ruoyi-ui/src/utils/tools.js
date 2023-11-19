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
