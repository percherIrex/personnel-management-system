<template>
    <div>

        <el-table style="width: 100%"
                  height="510"
                  :data="tableData">
            <el-table-column label="ID" prop="uid"></el-table-column>
            <el-table-column label="姓名" prop="name"></el-table-column>
            <el-table-column label="身份" prop="duty" :formatter="userType"></el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-switch :disabled="this_uid===scope.row.uid" v-model="scope.row.author"
                               @change="changeEvent(scope.row)"></el-switch>
                </template>
            </el-table-column>

        </el-table>

    </div>
</template>

<script>
    export default {
        data() {
            return {

                this_uid: 0,

                tableData: [
                    {
                        uid: 23,
                        duty: '员工',
                        author: true,
                        name: '王小虎',
                    },
                ]
            }
        },

        methods: {
            userType(row) {
                return row.duty = row.author ? '管理员' : '员工'
            }
            ,
            changeEvent(row) {
                let _this = this
                let my = JSON.parse(sessionStorage.getItem("userInfo"))
                if (row.author) {
                    row.duty = "管理员"
                    //http://localhost:8081/login/editRole/{uid}/1
                    //向服务器请求row.id变为管理员
                    axios.put("http://localhost:8081/login/editRole/" + row.uid + "/1/"
                        + row.name + "/" + my.uid + "/" + my.name).then(function (resp) {
                        if (resp.data) {
                            _this.$message.success(row.name + "已成为管理员")
                        } else {
                            console.log("error")
                        }
                    })
                } else {
                    row.duty = "员工"
                    //http://localhost:8081/login/editRole/{uid}/2
                    //向服务器撤销row.id的管理员资格
                    axios.put("http://localhost:8081/login/editRole/" + row.uid + "/2/"
                        + row.name + "/" + my.uid + "/" + my.name).then(function (resp) {
                        if (resp.data) {
                            _this.$message.info(row.name + "已降级为员工")
                        } else {
                            console.log("error")
                        }
                    })
                }
            }
        },
        created() {
            let _this = this
            this.this_uid = JSON.parse(sessionStorage.getItem("userInfo")).uid
            axios.get("http://localhost:8081/login/getRole").then(function (resp) {
                _this.tableData = resp.data
            })
        }
    }
</script>