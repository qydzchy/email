<template>
  <div class="company">
    <div class="container pt-4">
      <div class="left-wrap">
        <div class="menu" :class="{'is-collapsed':collapsed}">
          <div class="space-between pt-4 pl-10 pr-8 pb-8 borderBox">
            <div class="fs-18 bold" style="color: #0c163d">客户</div>
            <div class="arrow-left-icon pointer" @click="collapsed=true">
              <i class="el-icon-d-arrow-left"></i>
            </div>

          </div>
          <el-collapse v-model="activeCollapsed" @change="handleCollapsed">
            <el-collapse-item v-for="(item,index) in menuList" :key="index" :name="item.name">
              <template v-slot:title>
                <div class="el-collapse-item__header">
                  <i class="icon-13" :class="item.icon"></i>
                  <span class="pl-6 bold">{{ item.title }}</span>
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
          <RouterView/>
        </keep-alive>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  components: {},
  data() {
    return {
      collapsed: false,
      activeCollapsed: ['1', '2'],
      menuList: [
        {
          title: '菜单',
          name: '1',
          icon: 'el-icon-s-operation',
          subList: [
            {title: '客户列表', linkTo: 'list'},
            {title: '公海客户', linkTo: 'public'},
            {title: '客户查重', linkTo: 'query'},
            {title: '客户设置', linkTo: 'config'},
          ],
        }
      ],
      isComponent: '',
    }
  },
  mounted() {
    this.isComponent = this.$route.meta?.is || ''
  },
  methods: {
    handleCollapsed() {
    },
    jumpTo(path) {
      this.$router.push('/customer/' + path)
    },
  }
}
</script>

<style lang="scss" scoped>
.company {
  display: flex;
}

.container {
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
    width: 0;
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
  width: 100%;
  height: 100%;
  overflow: auto;
  box-sizing: border-box;
  min-width: 1000px;
}
</style>
