<template>
    <div>
        <span>请假审批</span>
        <span v-if="!userInfo.author">你不是管理员，你没有足够的权限使用该功能</span>
        <el-table
                v-else
                :data="tableData"
                style="width: 100%">

            <el-table-column
                    prop="name"
                    label="申请人"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="reason"
                    label="申请原因"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="date"
                    label="申请日期"
                    width="180">
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            @click="handleEdit(scope.$index, scope.row)">允许
                    </el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">拒绝
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    export default {
        name: "HandleApp",
        inject:[
            'reload'
        ],
        data() {
            return {
                userInfo: {
                    name: "",
                    author: 0,
                    uid: "",
                },
                tableData: [{
                    applicationId: "",
                    name: "",
                    reason: "",
                    date: "",
                }]
            }
        },

        created() {
            let _this = this
            let my = JSON.parse(sessionStorage.getItem("userInfo"));
            this.userInfo.name = my.name
            this.userInfo.uid = my.uid
            this.userInfo.author = my.author

            axios.get("http://localhost:8081/application/handle").then(function (resp) {
                _this.tableData = resp.data
            })
        },

        methods: {
            handleEdit(index, row) {
                let _this = this
                //console.log(row.applicationId);
                //http://localhost:8081/application/{way}/{main_id}/{main_name}
                axios.put("http://localhost:8081/application/" + 1 + "/" + this.userInfo.uid + "/" + this.userInfo.name, row)
                    .then(function (resp) {
                    if (resp.data) {
                        _this.reload();
                        _this.$message('已处理');
                    } else {
                        _this.$message('未知错误');
                    }
                })
            },
            handleDelete(index, row) {
                let _this = this
                axios.put("http://localhost:8081/application/" + 0 + "/" + this.userInfo.uid + "/" + this.userInfo.name, row)
                    .then(function (resp) {
                    if (resp.data) {
                        _this.reload();
                        _this.$message('已处理');
                    } else {
                        _this.$message('未知错误');
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>