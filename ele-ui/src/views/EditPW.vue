<template>
    <div class="PW">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
            <el-form-item label="旧密码" prop="oldPW">
                <el-input placeholder="输入旧密码" v-model="ruleForm.oldPW" show-password></el-input>
            </el-form-item>
            <el-form-item label="新密码" prop="newPW1">
                <el-input placeholder="输入新密码" v-model="ruleForm.newPW1" show-password></el-input>
            </el-form-item>
            <el-form-item label="新密码" prop="newPW2">
                <el-input placeholder="再次输入新密码" v-model="ruleForm.newPW2" show-password></el-input>
            </el-form-item>
            <el-form-item >
                <el-button type="primary" @click="changePW">修改</el-button>
                <el-button @click="returnHome">取消</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "EditPW",

        data() {
            return {
                ruleForm: {
                    oldPW: '',
                    newPW1: '',
                    newPW2: ''
                },
                rules: {
                    oldPW: [
                        {required: true, message: '请输入旧密码', trigger: 'blur'}
                    ],
                    newPW1: [
                        {required: true, message: '请输入新密码', trigger: 'blur'}
                    ],
                    newPW2: [
                        {required: true, message: '请输入新密码', trigger: 'blur'}
                    ],
                }
            }
        },

        methods: {
            changePW() {
                let pw1 = this.ruleForm.newPW1
                let pw2 = this.ruleForm.newPW2
                let old = this.ruleForm.oldPW
                let _this = this

                if (pw1 != pw2) {
                    this.$message.warning("你两次输入的新密码不相同")
                } else {
                    let my = JSON.parse(sessionStorage.getItem("userInfo"))

                    axios.put("http://localhost:8081/login/" + my.uid + "/" + pw1 + "/" + old).then(function (resp) {
                        if (resp.data) {
                            _this.$message.success("你已经成功修改密码")
                            _this.$router.push("/personal")
                        } else {
                            _this.$message.info("修改密码不成功，请重试")
                        }
                    })

                }
            },
            returnHome() {
                this.$router.push("/index")
            }
        }
    }
</script>

<style scoped>
    .PW{
        width: 400px;
        height: 400px;
        margin: 0 auto;
        /*box-shadow: 2px 2px 10px #909090;*/
    }
</style>