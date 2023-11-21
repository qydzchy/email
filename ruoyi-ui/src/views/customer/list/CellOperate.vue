<template>
  <div class="cell">
    <template v-if="showForm">
      <!--   input   -->
      <template v-if="type==='input'">
        <el-input
            size="small"
            :value.sync="curValue"
            v-bind="formOption"
            @input="(val)=>$emit('onInput',val)"
            @blur="onBlur"
            clearable
            placeholder="请输入"
            @keydown.enter.native="onEnter"
        />
      </template>
      <!--   select   -->
      <template v-else-if="type==='select'">
        <SelectNext
            :value.sync="curValue"
            :selectOptions="formOption"
            @update:value="handleChange"
            @visibleChange="visibleChange"/>
      </template>
      <!--   tree   -->
      <template v-else-if="type==='tree'">
        <el-select-tree
            style="width:100%"
            ref="select-tree"
            :value.sync="curValue"
            v-bind="formOption"
            @change="changeSelectTree"
            @visible-change="visibleChange"
        >
        </el-select-tree>
      </template>
      <!--   rate   -->
      <template v-else-if="type==='rate'">
        <el-rate :value.sync="curValue" v-bind="formOption" @change="handleChange"></el-rate>
      </template>
      <!--   tel   -->
      <template v-else-if="type==='tel'">
        <el-row type="flex" :gutter="4">
          <el-col :span="8">
            <SelectNext
                :value.sync="curValue.phone_prefix"
                :selectOptions="{options}"
                @change="handleChangeTel"/>
          </el-col>
          <el-col :span="16">
            <el-input
                size="small"
                :value.sync="curValue.phone"
                @input="handleInputTel"
                clearable
                placeholder="请输入"
                @keydown.enter.native="onEnter"></el-input>
          </el-col>
        </el-row>
      </template>
      <!--   country   -->
      <template v-else-if="type==='country'">
        <select-country :value.sync="curValue"></select-country>
      </template>
      <!--   picture   -->
      <template v-if="type==='picture'">
        <el-image v-bind="formOption"/>
      </template>
    </template>
    <!--   展示字段数据   -->
    <div class="wrap flex-middle space-between" v-else @click="onClick">
      <span class="span-style" :title="generateTitle">
          <slot name="content">
            <span v-if="type==='select'">
              {{ generateSelectValue }}
            </span>
            <span v-else-if="type==='tree'">
              {{ generateTreeValue }}
            </span>
            <span v-else-if="type==='tel'">
              {{ generateTelValue }}
            </span>
            <span v-else-if="type==='country'" class="flex-middle">
              <svg-icon v-if="generateCountryValue.svg" class="pr-4 fs-20" :icon-class="generateCountryValue.svg"/>
              {{ generateCountryValue.value }}
            </span>
            <span v-else>
               {{ content || '---' }}
            </span>
          </slot>
      </span>
      <el-row
          type="flex"
          class="pl-10 gap-8 right-icon"
          v-if="!['picture'].includes(type)"
      >
        <i v-if="disabledEdit && showEditIcon" class="el-icon-edit pointer" @click.stop="onEdit"></i>
        <i v-if="showCopyIcon" class="el-icon-copy-document pointer" @click.stop="onCopy"></i>
      </el-row>
    </div>
  </div>

</template>

<script>
import SelectNext from "@/components/SelectNext/index.vue";
import {generatePhone} from "@/utils/tools";
import {countryList} from '@/assets/data/countryData'


export default {
  props: {
    formOption: {
      type: Object,
      default: () => {
        return {
          options: [],
          data: [],
          isStrictly: true,
          propsSelect: {},
          propsTree: {}
        }
      },
      required: false
    },
    type: {
      type: "input" | "select" | "rate" | "tel" | "country" | "picture",
      default: "",
      required: false
    },
    showForm: {
      type: Boolean,
      default: false,
      required: false
    },
    curValue: {
      type: String | Number | Array | Object,
      default: '',
      required: false
    },
    text: {
      type: String | Number | Array | Object,
      default: '',
      required: false
    },
    disabledEdit: {
      type: Boolean,
      default: true,
      required: false
    },
    showEditIcon: {
      type: Boolean,
      default: true,
      required: false
    },
    showCopyIcon: {
      type: Boolean,
      default: true,
      required: false
    }
  },
  components: {
    SelectNext
  },
  data() {
    return {
      content: '',
      options: generatePhone()

    }
  },
  watch: {
    text: {
      handler(newVal) {
        this.content = newVal
      },
      immediate: true,
      deep: true,
    },
  },
  computed: {
    generateTitle() {
      let echoTitle = ''
      if (this.type === 'country') {
        echoTitle = this.generateCountryValue.value
      } else {
        echoTitle = this.content
      }
      return echoTitle
    },
    generateSelectValue() {
      return this.formOption.options?.find(val => val.value === this.content)?.label || '---'
    },
    generateTreeValue() {
      let res = ''
      const deepSearch = (arr, value) => {
        return arr?.filter(val => {
          if (val.value === value) {
            res = val.label
          }
          if (val.children && val.children.length) {
            deepSearch(val.children, value)
          }
        })
      }
      deepSearch(this.formOption.data, this.curValue)
      return res || '---'
    },
    generateTelValue() {
      const phonePrefix = this.content?.phone_prefix
      const phone = this.content?.phone
      return (phonePrefix || phone) ? `${phonePrefix} - ${phone}` : '---'
    },
    generateCountryValue() {
      if (!this.content?.length) {
        return {
          value: '---'
        }
      }
      let country = this.content[0] || ''
      let countrySvg = ''
      const province = this.content[1] || ''
      const city = this.content[2] || ''
      const deepSearch = arr => {
        arr.forEach(val => {
          if (val.value === country) {
            country = val.label
            countrySvg = val.svg
          }
          if (val.children && val.children?.length) {
            deepSearch(val.children)
          }
        })
      }
      deepSearch(countryList)
      return {
        value: `${country} ${province} ${city}`,
        svg: countrySvg
      }
    },
  },
  mounted() {
  },
  methods: {
    onCopy() {
      if (!this.content) {
        this.$message.info('复制内容不能为空')
        return
      }
      this.$copyText(this.content).then(() => {
        this.$message.success('复制成功')
      })
    },
    onClick() {
      this.$emit('click')
    },
    handleChange(val) {
      this.$emit('onChange', val)
    },
    changeSelectTree(value) {
      this.$emit('onChange', value)
    },
    handleChangeTel(value) {
      this.$emit('onChange', {...this.curValue, phone_prefix: value})
    },
    handleInputTel(value) {
      this.$emit('onInput', {...this.curValue, phone: value})
    },
    onEnter() {
      this.$emit('onEnter')
    },
    onEdit() {
      this.$emit('onEdit')
    },
    onBlur() {
      this.$emit('onBlur')
    },
    visibleChange(bool) {
      !bool && this.onBlur()
    },
  },
}
</script>

<style lang="scss" scoped>
.cell {
  .wrap {
    height: 32px;
  }

  &:hover .right-icon > i {
    display: block;
  }

  .right-icon > i {
    display: none;
  }
}

.span-style {
  cursor: default;

  > span {
    width: auto;
  }
}


</style>
