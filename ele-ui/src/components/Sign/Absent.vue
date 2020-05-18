<template>
    <div>
        <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="请假理由">
                <el-input type="textarea" v-model="form.reason"></el-input>
            </el-form-item>
            <el-form-item label="请假日期">
                <el-date-picker
                        v-model="form.date"
                        type="date"
                        placeholder="选择日期"
                        value-format="yyyy-MM-dd">
                </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">申请请假</el-button>
            </el-form-item>
        </el-form>

        <span>我的请假申请</span>
        <el-table
                :data="tableData"
                stripe
                style="width: 100%">
            <el-table-column
                    prop="reason"
                    label="申请理由">
            </el-table-column>
            <el-table-column
                    prop="date"
                    label="申请日期">
            </el-table-column>
            <el-table-column
                    prop="approver"
                    label="处理人">
            </el-table-column>
            <el-table-column
                    prop="allow"
                    :formatter="result"
                    label="处理结果">
            </el-table-column>
        </el-table>
    </div>
</template>
<script>
    export default {
        inject: [
            'reload'
        ],

        data() {
            return {
                form: {
                    reason: "",
                    name: "",
                    uid: "",
                    date: "",
                },
                tableData: [{
                    reason: "",
                    date: "",
                    approver: "",
                    allow : ""
                }]
            }
        },
        created() {
            let _this = this
            let my = JSON.parse(sessionStorage.getItem("userInfo"))
            axios.get("http://localhost:8081/application/" + my.uid).then(function (resp) {
                _this.tableData = resp.data
            })
        }
        ,
        methods: {
            onSubmit() {
                let _this = this
                let my = JSON.parse(sessionStorage.getItem("userInfo"))
                this.form.name = my.name
                this.form.uid = my.uid

                if (my.uid !== "") {
                    axios.post("http://localhost:8081/application/" + my.author, _this.form).then(function (resp) {
                        if (resp.data) {
                            _this.reload();
                            _this.$message('你已成功申请请假')
                        } else {
                            _this.$message.warning('你该日已申请')
                        }
                    })

                }
            },

            result(row,column){
                if(row.allow == null){
                    return "未处理"
                }else if(row.allow){
                    return "已批准"
                }else {
                    return "不批准"
                }
            }
        }
    }
</script>