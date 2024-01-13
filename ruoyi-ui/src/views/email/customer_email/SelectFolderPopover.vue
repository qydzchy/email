<template>
    <span class="mm-tooltip mail-toolbar-btn-item">
        <el-popover v-model="visible" placement="bottom" width="100%" trigger="manual">
            <div class="content" v-clickOutside="closePopover">
                <div class="fs-16 bold py-8">移动至</div>
                <el-cascader-panel v-model="selectFolder" :options="folderOption" :props="cascaderProps"
                    @change="handleSelectFolder"></el-cascader-panel>
            </div>

            <template slot="reference">
                <span class="mm-tooltip-trigger" @click.stop="visible = !visible">
                    <span>
                        <span class="okki-icon-wrap tool-bar-icon-item">​<svg xmlns="http://www.w3.org/2000/svg" width="20"
                                height="20" viewBox="0 0 24 24" aria-hidden="true" class="okki-svg-icon"
                                fill="currentColor">
                                <path fill-rule="evenodd" clip-rule="evenodd"
                                    d="M2 6a3 3 0 013-3h4.379a3 3 0 012.108.866l1.824 1.8H19a3 3 0 013 3V18a3 3 0 01-3 3H5a3 3 0 01-3-3V6zm2 0a1 1 0 011-1h4.379a1 1 0 01.703.289l1.823 1.8a2 2 0 001.406.578H19a1 1 0 011 1V10H4V6zm16 6v6a1 1 0 01-1 1H5a1 1 0 01-1-1v-6h16z">
                                </path>
                            </svg>
                        </span>
                    </span>
                </span>
            </template>

        </el-popover>
    </span>
</template>

<script>
import { listFolder } from "@/api/email/folder";
export default {
    props: {
        ids: {
            type: Array,
            default: () => {
                return []
            },
            required: false
        }
    },
    data() {
        return {
            visible: false,
            cascaderProps: {
                expandTrigger: 'hover',
                checkStrictly: true,
                value: 'id',
                label: 'name'
            },
            selectFolder: [],
            folderOption: []
        }
    },
    mounted(){
        this.getFolderList()
    },
    methods: {
        async getFolderList() {
            try {
                const res = await listFolder()
                if (res.code == 200) {
                    this.folderOption = res.data
                }
            } catch { }
        },
        handleSelectFolder(value) {
            console.log(this.ids);
            console.log(value);
            this.closePopover()
        },
        closePopover() {
            this.visible = false
            this.selectFolder = []
        }
    }
}
</script>

<style lang="scss" scoped></style>