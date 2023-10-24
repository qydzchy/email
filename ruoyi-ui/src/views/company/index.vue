<template>
  <div class="company pt-4">
    <div class="left-wrap">
      <div class="menu" :class="{'is-collapsed':collapsed}">
        <div class="space-between pt-4 pl-10 pr-8 pb-8 borderBox">
          <div class="fs-18 bold" style="color: #0c163d">企业管理</div>
          <div class="arrow-left-icon pointer" @click="collapsed=true">
            <i class="el-icon-d-arrow-left"></i>
          </div>

        </div>
        <el-collapse v-model="activeCollapsed" @change="handleCollapsed">
          <el-collapse-item v-for="(item,index) in menuList" :key="index" :name="item.name">
            <template v-slot:title>
              <div class="el-collapse-item__header">
                <i class="header-icon el-icon-info"></i>
                <span class="pl-6">{{ item.title }}</span>
              </div>

            </template>
            <ul v-for="(subItem,idx) in item.subList" :key="idx">
              <li class="pointer" :class="{'active':subItem.linkTo===isComponent}" @click="jumpTo(subItem.linkTo)">
                {{ subItem.title }}
              </li>
            </ul>
          </el-collapse-item>
        </el-collapse>
      </div>
      <div class="left-wrap-icon pointer" :class="{'arrow-right':collapsed}" @click="collapsed=false">
        <i class="el-icon-d-arrow-right fs-14"></i>
      </div>
    </div>

    <div class="right-wrap pt-16 px-32">
      <keep-alive>
        <Component :is="isComponent"/>
      </keep-alive>
      <div v-if="!isComponent">
        暂无内容
      </div>
    </div>
  </div>
</template>

<script>
import CustomerSetting from './customer/index.vue'

export default {
  components: {
    CustomerSetting
  },
  data() {
    return {
      collapsed: false,
      activeCollapsed: ['1', '2'],
      menuList: [
        {
          title: '销售管理',
          name: '1',
          subList: [
            {title: '客户设置', linkTo: 'customer-setting'},
            {title: '来源设置', linkTo: 'origin-setting'},
          ],
        },
        {
          title: '沟通管理',
          name: '2',
          subList: [
            {title: '邮箱管理', linkTo: 'email-manage'},
          ],
        }
      ],
      isComponent: '', // 根据选择展示对应的视图组件
    }
  },
  mounted() {
    const currentComponent = this.$route.meta?.is || ''
    this.compareRoute(currentComponent)
  },
  methods: {
    handleCollapsed() {
    },
    jumpTo(path) {
      this.$router.push('/company/' + path)
    },
    compareRoute(componentName) {
      // 路由匹配动态选择组件
      const companyRouteList = ['customer-setting']
      if (companyRouteList.includes(componentName)) {
        this.isComponent = componentName
      } else {
        this.isComponent = ''
      }
    },
  }
}
</script>

<style lang="scss" scoped>
.company {
  width: 100%;
  display: flex;
  min-height: calc(100vh - 84px);
}

.left-wrap {
  position: relative;

  .menu {
    display: flex;
    flex-direction: column;
    position: relative;
    width: 161px;
    height: 100%;
    background-color: #fafafa;
    border-right: 1px solid #f0f0f0;
    box-sizing: border-box;
    transition: all 0.3s;

    .arrow-left-icon {
      width: 24px;
      height: 24px;
      border-radius: 50%;
      display: flex;
      align-items: center;
      justify-content: center;

      &:hover {
        background-color: #f0f0f0;
      }
    }
  }

  .is-collapsed {
    display: none;
  }

  .left-wrap-icon {
    position: absolute;
    justify-content: center;
    align-items: center;
    height: 28px;
    color: #666;
    z-index: 15;
    text-align: center;
    cursor: pointer;
    background: #fafafa;
    top: 16px;
    left: 0;
    width: 20px;
    border: 1px solid #f0f0f0;
    box-shadow: 4px 0 4px rgba(0, 0, 0, .08);
    border-radius: 0 100px 100px 0;
    display: none;
  }

  .arrow-right {
    display: flex;
  }

  ul {
    margin: 0;
    padding-inline-start: 0;
    display: flex;
    align-items: center;
  }

  ul > li {
    width: 100%;
    height: 32px;
    padding-left: 18px;
    margin-bottom: 4px;
    border-radius: 4px;
    display: flex;
    align-items: center;
    position: relative;
    list-style: none;

    &:hover {
      background: #f0f0f0;
    }
  }

  ul > li.active {
    background: #f0f0f0;
  }

}


::v-deep .el-collapse {
  border: unset;
}

::v-deep .el-collapse-item__header,
::v-deep .el-collapse-item__wrap {
  background-color: #fafafa;
  border: unset;
  padding: 0 4px;
}

::v-deep .el-collapse-item__header {
  height: 32px;
}

::v-deep .el-collapse-item__content {
  padding-bottom: 0;
}

::v-deep .el-collapse-item__wrap {
  padding: 0 10px;
}

.right-wrap {
  flex: 1;
}
</style>
