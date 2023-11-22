<template>
  <div class="flex-middle">
    <el-tag
        class="customer-tag ml-4"
        v-for="(tag,index) in tagList"
        :key="index"
        :style="{color: tag.color,backgroundColor:tag.color?tag.color+'4d':''}"
        closable
        @close="removeTag(tag.id)"
    >
      {{ tag.name }}
    </el-tag>
    <!--    <el-button class="ml-10" size="mini" icon="el-icon-plus"-->
    <!--               @click="$router.push('/customer/config?tab=tags')"></el-button>-->
    <!--    <el-popover>-->
    <!--      <template #default>-->

    <!--      </template>-->
    <!--      <template #reference>-->
    <!--        <el-button class="ml-10" size="mini" icon="el-icon-plus"></el-button>-->
    <!--      </template>-->
    <!--    </el-popover>-->

  </div>
</template>

<script>
import {editCustomer} from "@/api/customer/publicleads";

export default {
  props: {
    detailId: {
      type: String | Number,
      default: '',
      required: true
    },
    tagList: {
      type: Array,
      default: () => [],
      required: false
    }
  },
  methods: {
    async editCustomer(data) {
      try {
        const res = await editCustomer({...data})
        if (res.code === 200) {
          this.$emit('onClose')
          this.$message.success('删除成功')
        }
      } catch {
      }
    },
    // 删除标签
    removeTag(id) {
      let tagIds = []
      this.tagList.forEach(val => {
        if (val.id !== id) {
          tagIds.push(val.id)
        }
      })
      const data = {
        id: this.detailId,
        tagIds: tagIds
      }
      this.editCustomer(data)
    },
  }
}
</script>

<style lang="scss" scoped>

</style>
