<template>
  <div class="container">
    <div class="color-box">
      <div
        class="color-wrap"
        v-for="(color,index) in colorMap"
        :key="index"
        :class="{'color-hover':isHover,'color-checked':color===checkedColor}"
        :style="{backgroundColor:color}"
        @click="$emit('update:checkedColor',color)">
        <i class="el-icon-check check-icon"></i>
      </div>
    </div>
  </div>
</template>

<script>
import {companyStatusColorMap} from './colorMap'

export default {
  props: {
    checkedColor: {
      type: Number | String,
      default: 0,
      required: true
    },
    colorMap: {
      type: Array,
      default: () => {
        return companyStatusColorMap
      },
      required: false
    },
    isHover: {
      type: Boolean,
      default: false,
      required: false
    }
  },
}
</script>

<style lang="scss" scoped>
.container {
  width: 100%;
  display: inline-block;
  box-sizing: border-box;
}

.color-box {
  width: 256px;
  row-gap: 8px;
  display: flex;
  justify-content: flex-start;
  flex-flow: row wrap;
  box-sizing: border-box;

  .color-wrap {
    width: 20px;
    height: 20px;
    padding: 0 4px;
    max-width: 100%;
    min-height: 1px;
    position: relative;
    border-radius: 4px;
    margin: 0 4px;
    cursor: pointer;
    display: flex;
    align-items: center;
    justify-content: center;
    transition: all .3s;
  }

  .color-wrap > .check-icon {
    color: #ffffff;
    position: absolute;
    display: none;
  }

  .color-checked > .check-icon {
    display: block !important;
  }

  .color-hover {
    &:hover {
      transform: scale(1.2);
    }
  }

  .color-hover.color-checked {
    transform: scale(1.2);
  }
}
</style>
