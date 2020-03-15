<template>
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="关键词">
            <el-input v-model="formInline.key" placeholder="oneKeyword"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSearch" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-table
                :data="tableData"
                stripe
                style="width: 100%">
            <el-table-column
                    prop="id"
                    label="员工ID"
                    width="80">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="姓名"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="gender"
                    label="性别"
                    width="80">
            </el-table-column>
            <el-table-column
                    prop="phone"
                    label="电话"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="email"
                    label="邮箱"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="address"
                    label="员工住址"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="depart"
                    label="所属部门">
            </el-table-column>
            <el-table-column
                    prop="position"
                    label="职位"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="intime"
                    label="入职日期">
            </el-table-column>
        </el-table>
    </el-form>
</template>

<script>
    export default {
        data() {
            return {
                formInline: {
                    key: ''
                },
                tableData: [{
                    id: '',
                    name: '',
                    phone: '',
                    email: '',
                    address: '',
                    depart: '',
                    position: '',
                    gender: '',
                    intime: ''
                }]
            }
        },
        methods: {
            onSearch() {
                //console.log(this.formInline.key);
                //http://localhost:8081/user/findByKey/
                const _this = this
                axios.get('http://localhost:8081/user/findByKey/' + _this.formInline.key.toString()).then(function (resp) {
                    _this.tableData = resp.data
                })
            }
        }
    }
</script>

<style scoped>

</style>