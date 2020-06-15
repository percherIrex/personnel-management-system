<template>
    <!--    model用来绑数据， rules用来绑定校验规则-->
    <div class="user-save-main">
        <h3>添加新员工</h3>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <!--        prop绑定的是校验规则-->
            <el-form-item style="width: 25%" label="姓名" prop="name">
                <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="gender">
                <el-radio-group v-model="ruleForm.gender">
                    <el-radio label="男" value="男"></el-radio>
                    <el-radio label="女" value="女"></el-radio>
                </el-radio-group>
            </el-form-item>

            <el-form-item style="width: 25%" label="电话" prop="phone">
                <el-input v-model="ruleForm.phone"></el-input>
            </el-form-item>
            <el-form-item style="width: 25%" label="邮箱" prop="email">
                <el-input v-model="ruleForm.email"></el-input>
            </el-form-item>
            <el-form-item style="width: 25%" label="住址" prop="address">
                <el-input v-model="ruleForm.address"></el-input>
            </el-form-item>
            <el-form-item style="width: 25%" label="部门" prop="depart">
                <el-select v-model="ruleForm.depart" placeholder="请选择部门">
                    <el-option label="销售部" value="销售部"></el-option>
                    <el-option label="研发部" value="研发部"></el-option>
                    <el-option label="人事部" value="人事部"></el-option>
                    <el-option label="生产部" value="生产部"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item style="width: 25%" label="职位" prop="position">
                <el-input v-model="ruleForm.position"></el-input>
            </el-form-item>


            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">添加</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    export default {

        inject: ['reload'],

        data() {
            return {
                ruleForm: {
                    name: '',
                    gender: '',
                    phone: '',
                    email: '',
                    address: '',
                    depart: '',
                    position: '',
                },
                rules: {
                    // required是否必选  blur失去焦点触发
                    name: [
                        {required: true, message: '请输入名字', trigger: 'blur'},
                        {min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur'}
                    ],
                    gender: [
                        {required: true, message: '请选择性别', trigger: 'change'}
                    ],
                    phone: [
                        {required: true, message: '请输入电话', trigger: 'blur'},
                        {min: 11, max: 11, message: '格式不符合', trigger: 'blur'},
                    ],
                    email: [
                        {required: true, message: '不能为空', trigger: 'blur'},
                    ],
                    address: [
                        {required: true, message: '不能为空', trigger: 'blur'},

                    ],
                    depart: [
                        {required: true, message: '不能为空', trigger: 'blur'},
                    ],
                    position: [
                        {required: true, message: '不能为空', trigger: 'blur'},
                    ],

                }
            };
        },
        methods: {
            submitForm(formName) {
                let _this = this
                let my = JSON.parse(sessionStorage.getItem("userInfo"))

                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        //提交到数据库,自动封装成Json
                        axios.post("http://localhost:8081/user/saveUser/" + my.uid + "/" + my.name, _this.ruleForm)
                            .then(function (resp) {
                                if (resp.data) {
                                    //页面跳转
                                    _this.$router.push("/user/list")
                                    _this.$notify({
                                        title: '提示',
                                        message: '人员添加成功，账号为电话号码，默认密码为000000',
                                        duration: 0,
                                        type: "success"
                                    })
                                }
                            })

                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>

<style>
    .user-save-main {
        margin: 0 auto;
    }
    .demo-ruleForm{
        margin: 0 auto;
    }
</style>