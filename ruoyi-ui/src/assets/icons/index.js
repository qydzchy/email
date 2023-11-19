import Vue from 'vue'
import SvgIcon from '@/components/SvgIcon'// svg component

// register globally
Vue.component('svg-icon', SvgIcon)

const req = require.context('./svg', false, /\.svg$/)
const country = require.context('./country-svg', false, /\.svg$/)
const platform = require.context('./platform-svg', false, /\.svg$/)
const other = require.context('./other-svg', false, /\.svg$/)
const requireAll = requireContext => requireContext.keys().map(requireContext)
requireAll(req)
requireAll(country)
requireAll(platform)
requireAll(other)
