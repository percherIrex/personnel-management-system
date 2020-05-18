<template>
    <div>
        <vxe-toolbar>
            <template v-slot:buttons>
                <vxe-input v-model="filterName" type="search" placeholder="搜索" @input="search"></vxe-input>
            </template>
        </vxe-toolbar>

        <vxe-table
                border
                height="450"
                :data="tableData">
            <vxe-table-column type="seq" width="80"></vxe-table-column>
            <vxe-table-column field="who" title="主体" type="html"></vxe-table-column>
            <vxe-table-column field="operate" title="操作" type="html"></vxe-table-column>
            <vxe-table-column field="what" title="对象" type="html"></vxe-table-column>
            <vxe-table-column field="date" title="日期" type="html"></vxe-table-column>
            <vxe-table-column field="time" title="时间" type="html"></vxe-table-column>
            <template v-slot:empty>
            <span style="color: red;">
              <p>没有数据</p>
            </span>
            </template>
        </vxe-table>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                filterName: '',
                tableData :[]
            }
        },

        created() {

            let _this = this
            axios.get("http://localhost:8081/operation").then(function (resp) {
                //console.log(resp.data)
                _this.tableData = resp.data
            })
        },

        methods: {
            search(){
                let _this = this
                let key =  this.filterName.toString()
                axios.get("http://localhost:8081/operation/"+ key).then(function (resp) {
                    _this.tableData = resp.data
                })
            }
        }

    }
</script>

<style scoped>
    .keyword-lighten {
        color: #000;
        background-color: #FFFF00;
    }
</style>