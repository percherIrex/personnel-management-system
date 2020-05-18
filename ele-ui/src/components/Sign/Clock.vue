<template>
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item>
            <el-button type="primary" @click="onSubmit">每日打卡</el-button>
            <el-button type="primary" @click="onSubmit2">临时请假</el-button>
        </el-form-item>
        <el-form-item label="查询考勤情况">
            <el-select v-model="formInline.region" placeholder="请选择">
                <el-option label="今日打卡" value="0"></el-option>
                <el-option label="我的打卡" value="1"></el-option>
                <el-option label="我的请假" value="2"></el-option>
                <el-option label="今日请假" value="3"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSearch">查询</el-button>
        </el-form-item>

        <el-form-item label="总人数">
            <span>{{numbers.total}}</span>
        </el-form-item>
        <el-form-item label="今日已打卡人数">
            <span>{{numbers.sign}}</span>
        </el-form-item>
        <el-form-item label="今日请假人数">
            <span>{{numbers.absent}}</span>
        </el-form-item>

        <el-table
                v-if="flag"
                :data="tableData"
                stripe
                style="width: 100%">
            <el-table-column
                    prop="name"
                    label="打卡人">
            </el-table-column>
            <el-table-column
                    prop="date"
                    label="打卡日期">
            </el-table-column>
            <el-table-column
                    prop="reason"
                    label="打卡时间">
            </el-table-column>
        </el-table>

        <el-table
                v-else
                :data="tableData"
                stripe
                style="width: 100%">
            <el-table-column
                    prop="name"
                    label="请假人">
            </el-table-column>
            <el-table-column
                    prop="date"
                    label="请假日期">
            </el-table-column>
            <el-table-column
                    prop="reason"
                    label="请假原因">
            </el-table-column>
        </el-table>

    </el-form>
</template>
<script>
    export default {
        inject:[
            'reload'
        ],

        data() {
            return {

                flag: true,

                submitData: {
                    name: "",
                    status: true,
                    uid: ""
                },

                formInline: {
                    region: ''
                },

                tableData: [{
                    date: '',
                    name: '',
                    reason: ''
                }]
                ,
                numbers: {
                    total: 0,
                    sign: 0,
                    absent: 0
                }
                ,
                form: {
                    reason:"临时请假",
                    status:false,
                    name: "",
                    uid: ""
                }
            }
        },
        methods: {
            onSubmit() {
                //console.log('打卡');
                let _this = this
                let my = JSON.parse(sessionStorage.getItem("userInfo"))
                this.submitData.name = my.name
                this.submitData.uid = my.uid

                if (my.uid !== "") {
                    axios.post("http://localhost:8081/record/saveRecord", _this.submitData).then(function (resp) {
                        if (resp.data) {
                            //_this.$router.push("/empty")
                            _this.reload();
                            _this.$message.success('打卡成功')
                        } else {
                            _this.$message('你今天已经打过卡了！')
                        }
                    })
                }else{
                    _this.$message('请先登录')
                }
            },
            onSubmit2() {
                let _this = this
                let my = JSON.parse(sessionStorage.getItem("userInfo"))
                this.form.name = my.name
                this.form.uid = my.uid

                if (my.uid !== "") {
                    axios.post("http://localhost:8081/record/saveRecordAbsent", _this.form).then(function (resp) {
                        if (resp.data) {
                            _this.reload();
                            _this.$message('你已成功请假！')
                            //_this.$router.push("clock")
                        } else {
                            _this.$message.warning('你今日已有请假或你需要先签到后再操作')
                        }
                    })
                }
            }
            ,
            onSearch() {
                ///http://localhost:8081/record/signList/{method}/{uid}
                let _this = this
                let uid = JSON.parse(sessionStorage.getItem("userInfo")).uid
                let m = this.formInline.region

                if (m >= 2) {
                    this.flag = false
                } else {
                    this.flag = true
                }

                axios.get("http://localhost:8081/record/signList/" + m + "/" + uid).then(function (resp) {
                    _this.tableData = resp.data
                })

                //console.log(this.formInline.region+"|"+uid)
            }
        },

        created() {
            //http://localhost:8081/record/count
            let _this = this
            axios.get("http://localhost:8081/record/count").then(function (resp) {
                _this.numbers.sign = resp.data.cof_sign
                _this.numbers.absent = resp.data.cof_absent
                _this.numbers.total = resp.data.cof_total
            })
        }
    }
</script>