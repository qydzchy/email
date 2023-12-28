<template>
    <div class="mm-split-pane mm-split-pane__right" style="left: 12.2835%;">
        <div class="page-mail-list layout-extraSidebar-content" style="background-color: white;">
            <span>
                <Header @switch="onSwitch" />
            </span>
        </div>
    </div>
</template>

<script>
import Header from './header'
import Content from './content'
export default {
    components: {
        Header,
        Content
    },
    methods: {
        onSwitch(value) {
            this.$emit('switch', ...[value])
        }
    }
}
</script>

<style lang="scss" scoped>
@import url('./css/index.css');
</style>