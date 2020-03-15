<template>
    <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="请假理由">
            <el-input type="textarea" v-model="form.reason"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">请假</el-button>
        </el-form-item>
    </el-form>
</template>
<script>
    export default {
        data() {
            return {
                form: {
                    reason:"",
                    status:false,
                    name: "",
                    uid: ""
                }
            }
        },
        methods: {
            onSubmit() {
                let _this = this
                let my = JSON.parse(sessionStorage.getItem("userInfo"))
                this.form.name = my.name
                this.form.uid = my.uid

                if (my.uid !== "") {
                    axios.post("http://localhost:8081/record/saveRecordAbsent", _this.form).then(function (resp) {
                        if (resp.data) {
                            _this.$message('你已成功请假！')
                            _this.$router.push("sign/clock")
                        } else {
                            _this.$message.warning('你今日已有请假或你需要先签到后再操作')
                        }
                    })
                }
            }
        }
    }
</script>