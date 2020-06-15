<template>
    <div>
        <vxe-table
                ref="xTable"
                :keep-source="true"
                border
                resizable
                show-overflow
                :data="tableData"
                :edit-config="{trigger: 'manual', mode: 'row'}">
            <vxe-table-column field="id" title="编号" width="60"></vxe-table-column>
            <vxe-table-column field="name" title="姓名"
                              :edit-render="{name: 'input', attrs: {type: 'text'}}"></vxe-table-column>
            <vxe-table-column field="gender" title="性别"
                              :edit-render="{name: '$select', options: sexList}"></vxe-table-column>
            <vxe-table-column field="phone" title="电话"
                              :edit-render="{name: '$input', props: {type: 'number'}}"></vxe-table-column>
            <vxe-table-column field="email" title="邮箱"
                              :edit-render="{name: 'input', attrs: {type: 'text'}}"></vxe-table-column>
            <vxe-table-column field="depart" title="部门"
                              :edit-render="{name: 'input', attrs: {type: 'text'}}"></vxe-table-column>
            <vxe-table-column field="position" title="职位"
                              :edit-render="{name: 'input', attrs: {type: 'text'}}"></vxe-table-column>
            <vxe-table-column field="address" title="地址"
                              :edit-render="{name: 'input', attrs: {type: 'text'}}"></vxe-table-column>

            <vxe-table-column title="操作">
                <template v-slot="{ row }">
                    <template v-if="$refs.xTable.isActiveByRow(row)">
                        <vxe-button @click="saveRowEvent(row)" size="mini">保存</vxe-button>
                        <vxe-button @click="cancelRowEvent(row)" size="mini">取消</vxe-button>
                    </template>
                    <template v-else>

                        <el-button @click="editRowEvent(row)" type="primary" icon="el-icon-edit" circle
                                   size="small"></el-button>
                        <el-button @click="deleRowEvent(row)" type="danger" size="small" icon="el-icon-delete"
                                   circle></el-button>
                    </template>
                </template>
            </vxe-table-column>
        </vxe-table>

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
        inject: ['reload']
        ,
        data() {
            return {
                pageSize: 0,
                total: 0,
                tableData: [
                    {
                        id: '',
                        name: '',
                        phone: '',
                        email: '',
                        address: '',
                        depart: '',
                        position: '',
                        gender: '',
                    }
                ],
                sexList: [
                    {label: '女', value: '女'},
                    {label: '男', value: '男'}
                ]
            }
        },

        created() {
            const _this = this
            axios.get("http://localhost:8081/user/findAll/1/9").then(function (resp) {
                _this.tableData = resp.data.records
                _this.pageSize = resp.data.size
                _this.total = resp.data.total
            })
        },

        methods: {
            //分页方法,点击后触发事件
            pageChange(currentPage) {
                const _this = this
                axios.get('http://localhost:8081/user/findAll/' + currentPage.toString() + "/9").then(function (resp) {
                    _this.tableData = resp.data.records
                    _this.pageSize = resp.data.size
                    _this.total = resp.data.total
                })
            },

            //触发编辑事件
            editRowEvent(row) {
                this.$refs.xTable.setActiveRow(row)
            },

            //删除事件
            deleRowEvent(row) {
                let _this = this
                let my = JSON.parse(sessionStorage.getItem("userInfo"))
                this.$XModal.confirm('您确定要删除该数据?').then(type => {
                    if (type === 'confirm') {
                        this.$refs.xTable.remove(row)
                        //向服务器请求删除
                        axios.delete("http://localhost:8081/user/deleteUser/" +
                            row.id+"/"+row.name+"/"+my.uid+"/"+my.name)
                            .then(function (resp) {
                            if (resp.data) {
                                _this.$message('删除成功');
                                //window.location.reload()
                                _this.reload()
                            } else {
                                _this.$message('删除失败,数据不存在');
                            }
                        })
                    } else {
                        _this.$message({
                            type: 'info',
                            message: '已取消删除'
                        })
                    }
                })
            },

            //修改后保存事件
            saveRowEvent(row) {
                let _this = this
                let my = JSON.parse(sessionStorage.getItem("userInfo"))

                this.$refs.xTable.clearActived().then(() => {

                    //提交到数据库,自动封装成Json
                    axios.put("http://localhost:8081/user/updateUser/"+my.uid+"/"+my.name, row).then(function (resp) {
                        if (resp.data) {
                            _this.$message('修改成功');
                        } else {
                            _this.$message('修改失败');
                        }
                    })

                })
            },

            //取消修改后还原表数据
            cancelRowEvent(row) {
                let _this = this
                this.$refs.xTable.clearActived().then(() => {
                    // 还原行数据 要开启keep-source
                    console.log("取消了更改")
                    _this.$refs.xTable.revertData(row)
                })
            }
        }
    }
</script>