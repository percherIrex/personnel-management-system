<template>
    <div>
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


        <!--        默认每页是10条记录，所以总页数1000/10=100-->
        <el-pagination
                background
                layout="prev, pager, next"
                :page-size="pageSize"
                :total="total"
                @current-change="pageChange"
        >
        </el-pagination>
    </div>
</template>

<script>
    export default {
        methods: {
            //分页方法,点击后触发事件
            pageChange(currentPage) {
                const _this = this
                axios.get('http://localhost:8081/user/findAll/' + currentPage.toString() + "/9").then(function (resp) {
                    _this.tableData = resp.data.records
                    _this.pageSize = resp.data.size
                    _this.total = resp.data.total
                })
            }
        },

        data() {
            return {
                pageSize: 0,
                total: 0,
                tableData: [{
                    id: '888',
                    name: '神圣领域',
                    phone: '13888888888',
                    email: 'percherIrex@gmail.com ',
                    address: '上海市普陀区真北路',
                    depart: '销售部',
                    position: '部门主任',
                    gender: '男',
                    intime: '2018-03'
                }]
            }
        },

        created() {
            const _this = this
            _this.$forceUpdate()

            axios.get("http://localhost:8081/user/findAll/1/9").then(function (resp) {
                _this.tableData = resp.data.records
                _this.pageSize = resp.data.size
                _this.total = resp.data.total
            })
        }
    }
</script>