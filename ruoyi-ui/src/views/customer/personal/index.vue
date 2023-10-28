<template>
  <div>
    id:{{ curId }}
  </div>
</template>

<script>
export default {
  props: {
    id: String | Number
  },
  data() {
    return {
      curId: ''
    }
  },
  mounted() {
    this.curId = this.$route.params?.id
  }
}
</script>

<style lang="scss" scoped>

</style>
