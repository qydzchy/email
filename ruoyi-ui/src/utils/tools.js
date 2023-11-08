import Router from '@/router'

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
