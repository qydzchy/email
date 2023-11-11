<template>
  <div class="cell">
    <template v-if="showForm">
      <!--   input   -->
      <template v-if="type==='input'">
        <el-input
          size="small"
          :value.sync="value"
          v-bind="formOption"
          @input="(val)=>$emit('onInput',val)"
          @blur="onBlur"
          @keydown.enter.native="onEnter"
          clearable
          placeholder="请输入"
        />
      </template>
      <!--   select   -->
      <template v-if="type==='select'">
        <SelectNext
          :value.sync="value"
          :selectOptions="formOption"
          @change="handleChange"
          @visibleChange="visibleChange"/>
      </template>
      <!--   tree   -->
      <template v-if="type==='tree'">
        <el-select-tree
          style="width:100%"
          ref="select-tree"
          :value.sync="value"
          v-bind="formOption"
          @change="changeSelectTree"
          @visible-change="visibleChange"
        >
        </el-select-tree>
      </template>
      <!--   rate   -->
      <template v-if="type==='rate'">
        <el-rate :value.sync="value" v-bind="formOption" @change="handleChange"></el-rate>
      </template>
      <!--   tel   -->
      <template v-if="type==='tel'">

      </template>
      <!--   picture   -->
      <template v-if="type==='picture'">
        <el-image v-bind="formOption"/>
      </template>
    </template>
    <!--   展示字段数据   -->
    <div class="wrap flex-middle space-between" v-else @click="onClick">
      <span class="span-style" :title="content">
          <slot name="content">
            <span v-if="type==='select'">
              {{ generateSelectValue }}
            </span>
            <span v-if="type==='tree'">
              {{ generateTreeValue }}
            </span>
            <span v-else>
               {{ content || '---' }}
            </span>
          </slot>
      </span>
      <el-row
        type="flex"
        class="pl-10 gap-8 right-icon"
      >
        <i v-if="disabledEdit && showEditIcon" class="el-icon-edit pointer" @click.stop="onEdit"></i>
        <i v-if="showCopyIcon" class="el-icon-copy-document pointer" @click.stop="onCopy"></i>
      </el-row>
    </div>
  </div>

</template>

<script>
import SelectNext from "@/components/SelectNext/index.vue";

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
      type: "input" | "select" | "rate" | "tel",
      default: "",
      required: false
    },
    showForm: {
      type: Boolean,
      default: false,
      required: false
    },
    value: {
      type: String | Number | Array,
      default: '',
      required: false
    },
    text: {
      type: String,
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
    }
  },
  watch: {
    text: {
      handler(newVal) {
        this.content = newVal
      },
      immediate: true
    },
  },
  computed: {
    generateSelectValue() {
      return this.formOption.options?.find(val => val.value === this.content)?.label
    },
    generateTreeValue() {
      let res = ''
      const deepSearch = (arr, value) => {
        return arr.filter(val => {
          if (val.value === value) {
            res = val.label
          }
          if (val.children && val.children.length) {
            deepSearch(val.children, value)
          }
        })
      }
      deepSearch(this.formOption.data, this.value)
      return res || '---'
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
    changeSelectTree(value) {
      this.$emit('onChange', value)
    },
    onClick() {
      this.$emit('click')
    },
    handleChange(val) {
      this.$emit('onChange', val)
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
