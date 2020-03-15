<template>
    <div>
        <vxe-table
                border
                resizable
                :height="500"
                :data="tableData"
                :edit-config="{trigger: 'click', mode: 'cell'}"
                @edit-actived="editActivedEvent"
                @edit-closed="editClosedEvent">
            <vxe-table-column field="uid" title="UID"></vxe-table-column>
            <vxe-table-column field="name" title="名字"></vxe-table-column>
            <vxe-table-column field="position" title="职称"></vxe-table-column>


            <vxe-table-column field="wages" title="基本工资"
                              :edit-render="{name: '$input', props: {type: 'number'}}"></vxe-table-column>
            <vxe-table-column field="fund" title="公积金基数"
                              :edit-render="{name: '$input', props: {type: 'number'}}"></vxe-table-column>
            <vxe-table-column field="insure1" title="养老保险基数"
                              :edit-render="{name: '$input', props: {type: 'number'}}"></vxe-table-column>
            <vxe-table-column field="insure2" title="医疗保险基数"
                              :edit-render="{name: '$input', props: {type: 'number'}}"></vxe-table-column>
            <vxe-table-column field="insure3" title="失业保险基数"
                              :edit-render="{name: '$input', props: {type: 'number'}}"></vxe-table-column>
            <vxe-table-column field="insure4" title="工伤保险基数"
                              :edit-render="{name: '$input', props: {type: 'number'}}"></vxe-table-column>
            <vxe-table-column field="insure5" title="生育保险基数"
                              :edit-render="{name: '$input', props: {type: 'number'}}"></vxe-table-column>
            <!--            <vxe-table-column field="total" title="保险总基数" :formatter="countAmount"></vxe-table-column>-->
            <vxe-table-column title="保险总基数">
                <template v-slot="{ row }">
                    <span>{{ countAmount(row) }}</span>
                </template>
            </vxe-table-column>

        </vxe-table>
    </div>

</template>

<script>
    export default {
        data() {
            return {
                tableData: [
                    {
                        id: "",
                        uid: 99,
                        name: "王安之",
                        position: "教授",
                        wages:5000,
                        fund: 0.12,
                        insure1: 0.2,
                        insure2: 0.1,
                        insure3: 0.01,
                        insure4: 0.01,
                        insure5: 0.008,


                    }
                ],
                fundList: []
            }
        },
        created() {
            let _this = this
            axios.get("http://localhost:8081/capital/list").then(function (resp) {
                _this.tableData = resp.data
            })
        },

        methods: {
            countAmount (row) {
                let sum =  row.insure1+row.insure2+row.insure3+row.insure4+row.insure5
                sum = parseFloat(sum).toFixed(3)
                return sum
            },
            editActivedEvent({row, column}, event) {
                //console.log(`打开 ${column.title} 列编辑`)
                // let v = 0.1 + 0.2 + 0.01 + 0.01 + 0.008
                // v = v.toFixed(3)
                // console.log(v)
            },
            editClosedEvent({row, column}, event) {
                axios.put("http://localhost:8081/capital/update", row).then(function (resp) {
                    if (resp.data) {
                        console.log("修改成功")
                    } else {
                        console.log("nothing happen")
                    }
                })
            }
        }
    }
</script>