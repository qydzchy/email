<template>
  <div>
<!--    <el-button type="primary" @click="drawerVisible = true">打开抽屉</el-button>-->
    <el-drawer :visible.sync="drawerVisible" direction="rtl" :wrapperClosable="false" destroy-on-close :show-close="false">
      <!-- 抽屉标题 -->
      <template #title>
        <div class="drawer-title">筛选条件</div>
        <i class="el-icon-close close-icon" @click="closeDrawer"></i>
      </template>
      <div class="drawer-content">
        <el-form :model="formData" ref="form">
          <!-- 公司名称 -->
          <el-form-item>
            <div>
              <span>公司名称</span>
            </div>
            <div>
              <el-input v-model="formData.companyName"></el-input>
            </div>
          </el-form-item>
          <!-- 邮箱 -->
          <el-form-item>
            <div>
              <span>邮箱</span>
            </div>
            <div>
              <el-input v-model="formData.email"></el-input>
            </div>
          </el-form-item>
          <!-- 客户标签 -->
          <el-form-item>
            <div>
              <span>客户标签</span>
            </div>
            <div>
              <el-radio-group v-model="formData.tagType">
                <el-radio label="1" value="1">包含任一标签</el-radio>
                <el-radio label="2" value="2">包含全部标签</el-radio>
              </el-radio-group>
            </div>
            <div>
              <el-select v-model="formData.tagIdList" multiple collapse-tags :style="{ width: 'calc(100%)' }">
                <el-option
                  v-for="item in tagList"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </div>
          </el-form-item>
          <!-- 客户分组 -->
          <el-form-item>
            <div>
              <span>客户分组</span>
            </div>
            <el-select-tree v-model="formData.packetIdList" placeholder="全部分组" :data="packetOptions"
                            :props="{ value: 'id', label: 'name' }" :default-expand-all="true" multiple collapse-tags clearable
                            :check-strictly="true" style="width: calc(100%);">
            </el-select-tree>
          </el-form-item>
          <!-- 国家地区 -->
          <el-form-item>
            <div>
              <span>国家地区</span>
            </div>
            <select-country v-model="formData.countryList"></select-country>
          </el-form-item>
          <!-- 客户来源 -->
          <el-form-item>
            <div>
              <span>客户来源</span>
            </div>
            <el-select v-model="formData.sourceIdList" multiple collapse-tags :style="{ width: 'calc(100%)' }">
              <el-option
                v-for="item in sourceList"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <!-- 跟进人 -->
          <el-form-item>
            <div>
              <span>跟进人</span>
            </div>
            <el-select v-model="formData.followerIdList" multiple collapse-tags :style="{ width: 'calc(100%)' }">
              <!-- 选项数据 -->
              <el-option label="人员1" value="1"></el-option>
              <el-option label="人员2" value="2"></el-option>
            </el-select>
          </el-form-item>
          <!-- 跟进时间 -->
          <el-form-item>
            <div>
              <span>最近跟进时间</span>
            </div>
            最近
            <el-input-number v-model="formData.followupDays" controls-position="right" :min="0" :step="1" style="width: 100px;"></el-input-number>
            天内
            <el-select v-model="formData.followupType">
              <el-option label="未跟进" value="1"></el-option>
              <el-option label="已跟进" value="2"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </div>
      <!-- 抽屉底部按钮 -->
      <div class="drawer-footer">
        <el-button @click="clearForm">清空</el-button>
        <el-button type="primary" @click="submitForm">筛选</el-button>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import {getCustomerTagList} from "@/api/customer/config";
import {packetList} from "@/api/company/group";
import {getOriginList} from "@/api/company/origin";
import CellOperate from "@/views/customer/list/CellOperate.vue";
export default {
  components: {CellOperate},
  data() {
    return {
      drawerVisible: false,
      tagList: [],
      packetOptions: [],
      sourceList: [],
      formData: {
        companyName: '',
        email: '',
        tagType: '1',
        tagIdList:[],
        packetIdList: [],
        countryList: [],
        sourceIdList: [],
        followerIdList: [],
        followupDays: '',
        followupType: ''
      }
    };
  },
  methods: {
    clearForm() {
      // 清空表单数据
      Object.keys(this.formData).forEach(key => {
        this.formData.companyName = '';
        this.formData.email = '';
        this.formData.tagType = '1';
        this.formData.tagIdList = [];
        this.formData.packetIdList = [];
        this.formData.countryList = [];
        this.formData.sourceIdList = [];
        this.formData.followerIdList = [];
        this.formData.followupDays = '';
        this.formData.followupType = '';
      });
    },
    submitForm() {
      // 表单验证
      this.$refs.form.validate(valid => {
        if (valid) {
          // 提交表单数据
          this.$emit('customerFilter', this.formData);
          // 关闭抽屉
          this.drawerVisible = false;
        }
      });
    },
    fetchTagList() {
      getCustomerTagList().then((response) => {
        this.tagList = response.data.map(item => ({ value: item.id, label: item.name }));
      });
    },
    fetchPacketList() {
      packetList()
        .then(response => {
          const packetData = response.data;
          const formattedOptions = packetData.map(group => ({
            id: group.id,
            name: group.name,
            children: group.children.map(child => ({
              id: child.id,
              name: child.name
            }))
          }));
          this.packetOptions = formattedOptions;
        })
        .catch(error => {
          console.error('Failed to fetch packet list:', error);
        });
    },
    fetchSourceList() {
      getOriginList().then((response) => {
        this.sourceList = response.data.map(item => ({ value: item.id, label: item.name }));
      });
    },
    openDrawer() {
      this.drawerVisible = true;
    },
    closeDrawer() {
      this.drawerVisible = false;
    }
  },
  created() {
    this.fetchTagList();
    this.fetchPacketList();
    this.fetchSourceList();
  }
};
</script>

<style scoped>
  .drawer-title {
    margin-bottom: 2px;
  }

  .close-icon {
    cursor: pointer;
  }

  .drawer-content {
    padding: 20px;
  }

  .drawer-footer {
    padding: 10px 20px;
    text-align: right;
  }
</style>
