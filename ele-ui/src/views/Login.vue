<template>
    <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="输入账号">
            <el-input placeholder="账号" v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="输入密码">
            <el-input placeholder="密码" v-model="form.password" show-password></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">登录</el-button>
        </el-form-item>
    </el-form>
</template>
<script>
    export default {
        data() {
            return {
                form: {
                    username: '',
                    password: '',
                }
            }
        },
        methods: {
            onSubmit() {
                let _this = this
                let usn = this.form.username.toString()
                let pwd = this.form.password.toString()

                axios.get("http://localhost:8081/login/"+usn+"/"+pwd).then(function (resp) {
                    //console.log(resp)
                    if(resp.data===""){
                        _this.$message("用户不存在");
                    }else {
                        // console.log(resp)
                        sessionStorage.setItem("userInfo",JSON.stringify(resp.data))
                        _this.$router.push("/index")
                    }
                })
            }
        }
    }
</script>