<template>
  <div>
    <ul class="color-picker-dropdown-list-wrapper">
      <li
        v-for="color in colors"
        :key="color"
        :class="{ 'picked': selectedColor === color }"
        :style="{ background: `rgb(${color})` }"
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
        '0, 0, 0',
        '188, 89, 89',
        '216, 117, 56',
        '32, 152, 144',
        '75, 103, 157',
        '89, 93, 190',
        '51, 51, 51',
        '228, 62, 62',
        '235, 153, 85',
        '97, 188, 129',
        '93, 137, 233',
        '141, 84, 189',
        '123, 130, 145',
        '238, 123, 123',
        '226, 173, 40',
        '128, 196, 99',
        '74, 168, 235',
        '172, 172, 172'
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
