<template>
    <div class="mm-split-pane mm-split-pane__right" style="left: 12.2835%;">
        <div class="page-mail-list layout-extraSidebar-content" style="background-color: white;">
            <span>
                <Header :isHide="isHide" @switch="onSwitch">
                    <Content ref="customerContent" :selectedTaskId="selectedTaskId"
                        @handlerHeader="bool => isHide = bool" />
                </Header>
            </span>
        </div>
    </div>
</template>

<script>
import Header from './header'
import Content from './content'
export default {
    props: {
        selectedTaskId: {
            type: String | Number,
            default: '',
            required: false
        }
    },
    components: {
        Header,
        Content
    },
    data() {
        return {
            isHide: false
        }
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