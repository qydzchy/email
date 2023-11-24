<template>
  <div class="search-group flex-middle gap-8">
    <el-select v-model="searchQuery.columnName" @change="searchQuery.value=''">
      <el-option v-for="item in filterOption" :key="item.columnName" :value="item.columnName"
                 :label="item.nickName"></el-option>
    </el-select>
    <template v-if="secondFilterProp.type === 'select'">
      <el-select v-model="searchQuery.value" @change="handleFilter">
        <el-option
            v-for="(item,index) in secondFilterProp.options"
            :key="index"
            :value="item[secondFilterProp.props.value]"
            :label="item[secondFilterProp.props.label]">
        </el-option>
      </el-select>
    </template>
    <template v-else-if="secondFilterProp.type === 'input'">
      <el-input
          v-model="searchQuery.value"
          placeholder="请输入内容"
          @keydown.enter.native="handleFilter"
          @blur.native="handleFilter"/>
    </template>
    <template v-else-if="secondFilterProp.type==='country'">
      <select-country :value.sync="searchQuery.value" @input="handleFilter"/>
    </template>
    <template v-else-if="secondFilterProp.type==='date'">
      <el-date-picker
          v-model="searchQuery.value"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"/>
    </template>

    <!--    <el-input style="width:200px" placeholder="请输入"></el-input>-->

    <!--    <FilterDrawer/>-->
    <!--                <el-popover-->
    <!--                  trigger="click"-->
    <!--                  width="200"-->
    <!--                >-->
    <!--                  <el-button icon="el-icon-setting" round slot="reference">设置</el-button>-->
    <!--                </el-popover>-->
  </div>
</template>

<script>
import FilterDrawer from "./FilterDrawer.vue";

export default {
  props: {
    indexOpt: {
      type: Object,
      default: () => {
        return {
          teamMemberOption: [],
        }
      },
      required: false
    },
    query: {
      type: Object,
      default: () => {
      },
      required: false
    },
  },
  components: {FilterDrawer},
  data() {
    return {
      filterOption: [
        {columnName: 'userId', nickName: '成员'},
        {columnName: 'company_name', nickName: '公司名称'},
        {columnName: 'email', nickName: '邮箱'},
        {columnName: 'customer_tag', nickName: '客户标签'},
        {columnName: 'customer_packet', nickName: '分组'},
        {columnName: 'country_region', nickName: '国家地区'},
        {columnName: 'customer_source', nickName: '客户来源'},
        {columnName: 'customer_stage', nickName: '客户阶段'},
        {columnName: 'customer_rating', nickName: '客户星级'},
        {columnName: 'last_contacted_at', nickName: '最近联系时间'},
        {columnName: 'next_move_pool', nickName: '下次移入公海日期'},
        {columnName: 'focusFlag', nickName: '是否关注'},
        {columnName: 'follow_up_personnel', nickName: '跟进人'},
      ],
      filterSecondOption: {
        userId: {
          type: 'select',
          props: {
            value: 'userId',
            label: 'nickName',
          },
          options: [],
        },
        company_name: {
          type: 'input',
        },
        email: {
          type: 'input'
        },
        customer_tag: {
          type: 'select',
          props: {
            value: 'value',
            label: 'label',
          },
          options: [],
        },
        customer_packet: {
          type: 'select',
          props: {
            value: 'value',
            label: 'label',
          },
          options: [],
        },
        country_region: {
          type: 'country',
        },
        customer_source: {
          type: 'select',
          props: {
            value: 'value',
            label: 'label',
          },
          options: [],
        },
        customer_stage: {
          type: 'select',
          props: {
            value: 'value',
            label: 'label',
          },
          options: [],
        },
        customer_rating: {
          type: 'select',
          props: {
            value: 'value',
            label: 'label',
          },
          options: [
            {value: 0, label: '0星'},
            {value: 1, label: '1星'},
            {value: 2, label: '2星'},
            {value: 3, label: '3星'},
            {value: 4, label: '4星'},
            {value: 5, label: '5星'},
          ],
        },
        last_contacted_at: {
          type: 'date',
        },
        next_move_pool: {
          type: 'date',
        },
        is_follow: {
          type: 'select',
          props: {
            value: 'value',
            label: 'label',
          },
          options: [
            {value: 0, label: '不限'},
            {value: 1, label: '关注'},
            {value: 2, label: '未关注'},
          ],
        },
        follow_up_personnel: {
          type: 'select',
          props: {
            value: 'value',
            label: 'label',
          },
          options: [],
        },
      },
      searchQuery: {
        columnName: 'userId',
        value: '',
      }
    }
  },
  watch: {
    indexOpt: {
      handler(newVal) {
        this.filterSecondOption.userId.options = newVal.teamMemberOption
      },
      deep: true,
      immediate: true,
    },
    searchQuery: {
      handler(newVal) {
        this.searchQuery = newVal
      },
      deep: true,
      immediate: true,
    }
  },
  computed: {
    secondFilterProp() {
      const {type, options, props} = this.filterSecondOption[this.searchQuery.columnName]
      return {
        type,
        options,
        props
      }
    },
  },
  methods: {
    handleFilter() {
      console.log(this.searchQuery)
      this.$emit('handleSearch', this.searchQuery)
    },
  }
}
</script>

<style lang="scss" scoped>

</style>
