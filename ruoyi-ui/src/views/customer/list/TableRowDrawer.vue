<template>
  <div>
    <div class="table-row-drawer">
      <el-drawer :visible.sync="visible" :modal="false" :wrapperClosable="false" destroy-on-close :show-close="false"
                 @close="onHideDrawer">
        <template #title>
          <div class="header flex-middle space-between py-20 px-20">
            <div>
              <i class="pointer el-icon-right" @click="onHideDrawer"></i>
            </div>
            <el-row class="flex-middle">
              <el-row class="flex-middle gap-16">
                <CollageIcon :show="focusFlag" :default-hide="false" @click="onCollageIcon"/>
                <el-tooltip placement="top" content="写邮件">
                  <i class="el-icon-message pointer"></i>
                </el-tooltip>
                <el-tooltip placement="top" content="编辑">
                  <i class="el-icon-edit pointer" @click="editVisible = true"></i>
                </el-tooltip>
              </el-row>
              <el-row class="ml-10">
                <OperateMenu :row="rowData" :indexOpt="indexOpt" :commandList="commandList">
                  <el-tooltip placement="left" content="更多操作">
                    <i class="operate-more pointer el-icon-more-outline" style="transform: rotate(90deg)"></i>
                  </el-tooltip>
                </OperateMenu>
              </el-row>

            </el-row>
          </div>
        </template>
        <div class="container">
          <div class="base-info flex-start px-30">
            <el-avatar shape="square" :src="rowData.companyLogo"></el-avatar>

            <div class="pl-10 fs-14">
              <label>{{ rowData.companyName || '---' }}</label>
              <div class="my-10 flex-middle">
                <span>{{ rowData.customerNo || '---' }}</span>
                <span class="ml-10">
                  <CellOperate type="country" :text="rowData.countryRegion" :show-copy-icon="false"
                               :show-edit-icon="false"></CellOperate>
                </span>
              </div>
              <div class="mb-10">跟进入: {{ rowData.followPerson || '---' }}</div>
              <TableRowTags :detail-id="row.id" :tag-list="rowData.tagList" :index-opt="indexOpt" @onClose="reload"/>
            </div>
          </div>
          <div class="tabs mt-10">
            <TableRowTabs :row="rowData" :options="options" @reload="reload"/>
          </div>
        </div>
        <el-backtop target=".el-tabs__content" :visibility-height="100"/>
      </el-drawer>

    </div>
    <CreateCustomerDrawer :visible.sync="editVisible" :row.sync="rowData" @load="onHideCreateDrawer"/>
  </div>
</template>

<script>
import TableRowTabs from './TableRowTabs.vue'
import TableRowTags from "./TableRowTags.vue";
import CreateCustomerDrawer from "./CreateCustomerDrawer.vue";
import CollageIcon from "@/views/components/Customer/CollageIcon.vue";
import CellOperate from "@/views/customer/list/CellOperate.vue";
import {editFocusFlagCustomer, getCustomerDetail} from "@/api/customer/publicleads";
import {deepClone} from "@/utils";
import OperateMenu from "@/views/customer/list/OperateMenu.vue";

export default {
  props: {
    row: {
      type: Object,
      default: () => {
        return {
          id: '',
          focusFlag: false
        }
      },
      required: false
    },
    visible: {
      type: Boolean,
      default: false,
      required: false,
    },
    indexOpt: {
      type: Object,
      default: () => {
        return {
          groupOption: [],
        }
      }
    },
  },
  components: {
    OperateMenu,
    TableRowTabs,
    TableRowTags,
    CollageIcon,
    CellOperate,
    CreateCustomerDrawer,
  },
  data() {
    return {
      editVisible: false,
      options: {
        isShowSchedule: true,
        isTabSetHeight: true,
        isShowInfo: true,
        indexOpt: this.indexOpt
      },
      commandList: ['share', 'cancel', 'movePool', 'reassign'],
      focusFlag: false,
      rowData: {
        followPerson: '',
        companyName: '',
        customerNoType: '',
        companyLogo: '',
        followUpPersonnelList: [],
        sourceIds: [],
        sourceList: [],
        tagIds: [],
        tagList: [],
        stageId: '',
        stage: [],
        packetId: '',
        packet: [],
      },
    }
  },
  watch: {
    row: {
      handler(newVal) {
        this.focusFlag = Boolean(newVal.focusFlag)
        if (newVal?.id) {
          this.getDetailData()
        }
      },
      immediate: true,
    },
  },
  methods: {
    async getDetailData() {
      try {
        const res = await getCustomerDetail({
          id: this.row.id
        })
        if (res.code === 200) {
          this.rowData = res.data
          this.rowData.countryRegion = this.rowData.countryRegion?.split('/') || []
          this.rowData.customerId = this.rowData.id
          let sourceList = deepClone(this.rowData.sourceList)
          this.rowData.sourceIds = sourceList?.map(val => val.id)
          let tagList = deepClone(this.rowData.tagList)
          this.rowData.tagIds = tagList?.map(val => val.id)
          this.rowData.stageId = this.rowData.stage?.id
          this.rowData.packetId = this.rowData.packet?.id
          this.rowData.timezone = +this.rowData.timezone
          this.rowData.followPerson = this.rowData.followUpPersonnelList?.[0]?.nickName
          this.rowData.contactList = this.generateContactList(this.rowData?.contactList)
        }
      } catch (e) {
        console.error(e)
      }
    },
    async onCollageIcon(status) {
      if (!this.row.id) {
        return
      }
      try {
        const res = await editFocusFlagCustomer({id: this.row.id})
        if (res.code === 200) {
          this.focusFlag = !status
          this.$emit('load')
        }
      } catch {
      }
    },
    reload() {
      this.getDetailData()
      this.$emit('load')
    },
    onHideDrawer() {
      this.rowData = {}
      this.$emit('update:visible', false)
    },
    onHideCreateDrawer() {
      this.editVisible = false
      setTimeout(() => {
        this.getDetailData()
        this.$emit('load')
      }, 400)
    },
    generateContactList(arr) {
      if (arr && !arr.length) {
        return []
      }
      return arr.map(val => {
        val.phone = val.phone ? JSON?.parse(val.phone) : []
        val.socialPlatform = val.socialPlatform ? JSON?.parse(val.socialPlatform) : []
        return val
      })
    },
  }

}
</script>

<style lang="scss" scoped>

.table-row-drawer {
  ::v-deep .el-drawer__wrapper {
    z-index: 2 !important;
  }

  ::v-deep .el-drawer.rtl {
    width: 600px !important;
  }


  ::v-deep .el-drawer__header {
    padding: 0;
  }

  .header {
    border-bottom: 1px solid #f0f0f0;
  }

  .container {
  }
}
</style>
