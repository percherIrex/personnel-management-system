<template>
    <div>
        <vxe-toolbar>
            <template v-slot:buttons>
                <vxe-button @click="exportDataEvent">生成报表</vxe-button>
<!--                <vxe-button @click="exportSelectEvent">导出选中</vxe-button>-->
            </template>
        </vxe-toolbar>

        <vxe-table
                highlight-hover-row
                export-config
                border="none"
                ref="xTable"
                height="460"
                :data="tableData">
<!--            <vxe-table-column field="uid" title="UID" fixed="left"></vxe-table-column>-->
            <vxe-table-column field="name" title="名字"></vxe-table-column>
            <vxe-table-column field="position" title="职称"></vxe-table-column>
            <vxe-table-column field="wages" title="基本工资"></vxe-table-column>
            <vxe-table-column field="fund" title="公积金基数"></vxe-table-column>
            <vxe-table-column field="insure1" title="养老保险基数"></vxe-table-column>
            <vxe-table-column field="insure2" title="医疗保险基数"></vxe-table-column>
            <vxe-table-column field="insure3" title="失业保险基数"></vxe-table-column>
            <vxe-table-column field="insure4" title="工伤保险基数"></vxe-table-column>
            <vxe-table-column field="insure5" title="生育保险基数"></vxe-table-column>
            <vxe-table-column field="a" title="保险总基数" :formatter="countAmount"></vxe-table-column>
            <!--            <vxe-table-column title="本月打卡数" :formatter="countSign"></vxe-table-column>-->
            <vxe-table-column field="sign" title="打卡"></vxe-table-column>
            <vxe-table-column field="absent" title="请假"></vxe-table-column>
            <vxe-table-column field="b" title="缺勤" :formatter="countAbsent"></vxe-table-column>
            <vxe-table-column field="c" title="请假应扣" :formatter="countDeduct1"></vxe-table-column>
            <vxe-table-column field="d" title="缺勤应扣" :formatter="countDeduct2"></vxe-table-column>
            <vxe-table-column field="e" title="五险一金合" :formatter="countDeduct3"></vxe-table-column>
<!--            <vxe-table-column field="f" title="应发合" :formatter="countDeduct4"></vxe-table-column>-->
            <vxe-table-column field="g" title="应扣合" :formatter="countDeduct5"></vxe-table-column>
            <vxe-table-column field="h" title="实发" :formatter="countDeduct6"></vxe-table-column>
        </vxe-table>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                tableData: [
                    {
                        // id: "",
                        // uid: "",
                        // name: "",
                        // position: "教授",
                        // wages: 5000,
                        // fund: 0.12,
                        // insure1: 0.2,
                        // insure2: 0.1,
                        // insure3: 0.01,
                        // insure4: 0.01,
                        // insure5: 0.008,
                        // absence:0
                    }
                ],

            }
        },

        created() {
            let _this = this
            axios.get("http://localhost:8081/capital/list/final").then(function (resp) {
                _this.tableData = resp.data
            })

        },
        methods: {
            countDeduct6(row) {
                let r = row.row
                let d = new Date()
                let day = new Date(d.getFullYear(), d.getDate()+1, 0).getDate()
                //基本工资减应合扣  保底是3000
                //没有保底了
                let result = r.wages - ((day - r.sign - r.absent) * 500) + (r.absent * 200)
                if (result < 0) {
                    result = 0
                }
                return result
            }
            ,
            countDeduct5(row) {
                let r = row.row
                let d = new Date()
                let day = new Date(d.getFullYear(), d.getDate()+1, 0).getDate()

                return ((day - r.sign - r.absent) * 500) + (r.absent * 200)
            }
            ,
            countDeduct4(row) {
                let r = row.row
                let sum = r.insure1 + r.insure2 + r.insure3 + r.insure4 + r.insure5
                sum = parseFloat(sum).toFixed(3)
                return (sum * r.wages) + r.wages
            }
            ,
            countDeduct3(row) {
                let r = row.row
                let sum = r.insure1 + r.insure2 + r.insure3 + r.insure4 + r.insure5
                sum = parseFloat(sum).toFixed(3)
                return sum * r.wages
            }
            ,
            countDeduct2(row) {
                let r = row.row
                let d = new Date()
                let day = new Date(d.getFullYear(), d.getDate()+1, 0).getDate()

                return (day - r.sign - r.absent) * 500
            }
            ,
            countDeduct1(row) {
                let r = row.row
                return r.absent * 200
            }
            ,
            countAbsent(row) {
                let r = row.row
                let d = new Date()
                let day = new Date(d.getFullYear(), d.getDate()+1, 0).getDate()

                return (day - r.sign - r.absent)
            },

            countAmount(row) {
                let r = row.row
                let sum = r.insure1 + r.insure2 + r.insure3 + r.insure4 + r.insure5
                sum = parseFloat(sum).toFixed(3)
                return sum
            },

            exportDataEvent() {
                let date = new Date()
                let outFormat = {
                    type: 'csv',
                    filename: date.getFullYear().toString() + "_" + (date.getMonth() + 1).toString() + "_" + date.getDate().toString()
                }
                this.$refs.xTable.exportData(outFormat)
            },


            // exportSelectEvent(row) {
            //     //console.log(row.row)
            // }
        }
    }
</script>