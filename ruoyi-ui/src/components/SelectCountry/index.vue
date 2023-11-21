<template>
  <el-row type="flex" :gutter="10">
    <el-col>
      <el-select-tree
          v-model="country"
          :data="countryList"
          clearable
          filterable
          :render-content="renderContent"
          :render-after-expand="false"
          @change="handleValue"
      >
      </el-select-tree>
    </el-col>
    <el-col v-if="country==='CN'">
      <el-cascader
          v-model="provinceAndCity"
          :options="province"
          :props="provinceProps"
          @change="handleValue"
          clearable
      ></el-cascader>
    </el-col>
  </el-row>
</template>

<script>
import {province, countryList} from '@/assets/data/countryData'

export default {
  props: {
    value: {
      type: Array,
      default: () => [],
      required: false
    }
  },
  data() {
    return {
      country: '',
      provinceAndCity: [],
      countryList: countryList,
      provinceProps: {
        value: 'label',
        label: 'label'
      },
      province
    }
  },
  watch: {
    value: {
      handler(newVal) {
        if (newVal && newVal.length) {
          this.country = newVal[0]
          this.provinceAndCity = [newVal[1], newVal[2]].filter(val => val)
        }
      },
      deep: true,
      immediate: true
    }
  },
  methods: {
    renderContent(h, {_node, data, _store}) {
      return <div>
        {data.type !== 'parent'
            ? <div>
              {data.svg && <svg-icon icon-class={data.svg}/>}
              <span class="pl-4">{data.label}</span>
            </div>
            : <div>{data.label}</div>}
      </div>

    },
    handleValue() {
      if (this.country !== 'CN') {
        this.provinceAndCity = []
      }
      this.$emit('input', [this.country, ...this.provinceAndCity]);
    },
  }
}
</script>

<style lang="scss" scoped>

</style>
