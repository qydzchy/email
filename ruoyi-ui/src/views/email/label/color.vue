<template>
  <div>
    <ul class="color-picker-dropdown-list-wrapper">
      <li
        v-for="color in colors"
        :key="color"
        :class="{ 'picked': selectedColor === color }"
        :style="{ background: color }"
        @click="selectColor(color)"
      >
        <i class="m-icon icon-check-thin" v-if="selectedColor === color"></i>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      labelColorPage: false,
      selectedColor: null, // 初始化为空
      colors: [  // 保存所有颜色值
        'rgb(0, 0, 0)',
        'rgb(188, 89, 89)',
        'rgb(216, 117, 56)',
        'rgb(32, 152, 144)',
        'rgb(75, 103, 157)',
        'rgb(89, 93, 190)',
        'rgb(51, 51, 51)',
        'rgb(228, 62, 62)',
        'rgb(235, 153, 85)',
        'rgb(97, 188, 129)',
        'rgb(93, 137, 233)',
        'rgb(141, 84, 189)',
        'rgb(123, 130, 145)',
        'rgb(238, 123, 123)',
        'rgb(226, 173, 40)',
        'rgb(128, 196, 99)',
        'rgb(74, 168, 235)',
        'rgb(172, 172, 172)'
      ]
    }
  },

  methods: {
    selectColor(color) {
      this.selectedColor = color;
      this.$emit('color-selected', this.selectedColor);
    }
  },

  props: {
    initialColor: String
  },

  created() {
    this.selectedColor = this.initialColor;
  }
};
</script>
