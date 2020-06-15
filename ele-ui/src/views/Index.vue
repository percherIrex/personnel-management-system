<template>
    <div>
        <el-container style="height: 660px; border: 1px solid #eee">

            <el-header>
                <div style="text-align: left; ">
                    <h1> 欢迎使用小型企业人事管理系统</h1>
                </div>
            </el-header>
            <el-header>
                <div style="text-align: right; font-size: 12px">
                    <router-link v-if="!userInfo.run" to="/">请登录</router-link>
                    <div v-else>
                        <el-dropdown trigger="click">
                            <span class="el-dropdown-link">
                            {{userInfo.name}}<i class="el-icon-arrow-down el-icon--right"></i>
                            </span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item>
                                    <el-button type="text" size="small" @click="toPersonal">个人中心</el-button>
                                </el-dropdown-item>
                                <el-dropdown-item>
                                    <el-button type="text" size="small" @click="changePW">修改密码</el-button>
                                </el-dropdown-item>
                                <el-dropdown-item divided>
                                    <el-button type="text" size="small" @click="logOut">退出登录</el-button>
                                </el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>|
                        <span v-if="userInfo.author">管理员</span>
                        <span v-else>员工</span> |
                    </div>
                </div>
            </el-header>

            <el-container >
                <el-aside width="200px" style="background-color: rgb(238, 241, 246)" >
                    <!--添加router 绑定在菜单-->
                    <el-menu router>

                        <el-submenu :index="'1'">
                            <template slot="title">
                                <i class="el-icon-view"></i>
                                {{$router.options.routes[1].name}}
                            </template>
                            <el-menu-item v-for="item in $router.options.routes[1].children" :index="item.path"
                                          v-if="!item.show">
                                {{item.name}}
                            </el-menu-item>
                        </el-submenu>

                        <el-submenu :index="'2'" :hidden="!userInfo.author">
                            <template slot="title">
                                <i class="el-icon-user-solid"></i>
                                {{$router.options.routes[2].name}}
                            </template>
                            <el-menu-item v-for="item in $router.options.routes[2].children" :index="item.path"
                                          v-if="!item.show">
                                {{item.name}}
                            </el-menu-item>
                        </el-submenu>

                        <el-submenu :index="'3'" :hidden="!userInfo.author">
                            <template slot="title">
                                <i class="el-icon-s-data"></i>
                                {{$router.options.routes[3].name}}
                            </template>
                            <el-menu-item v-for="item in $router.options.routes[3].children" :index="item.path"
                                          v-if="!item.show">
                                {{item.name}}
                            </el-menu-item>
                        </el-submenu>

                        <el-submenu :index="'4'">
                            <template slot="title">
                                <i class="el-icon-edit-outline"></i>
                                {{$router.options.routes[4].name}}
                            </template>
                            <el-menu-item v-for="item in $router.options.routes[4].children" :index="item.path"
                                          v-if="!item.show">
                                {{item.name}}
                            </el-menu-item>
                        </el-submenu>

                        <el-submenu :index="'5'" :hidden="!userInfo.author">
                            <template slot="title">
                                <i class="el-icon-magic-stick"></i>
                                {{$router.options.routes[5].name}}
                            </template>
                            <el-menu-item v-for="item in $router.options.routes[5].children" :index="item.path"
                                          v-if="!item.show">
                                {{item.name}}
                            </el-menu-item>
                        </el-submenu>


                    </el-menu>

                </el-aside>
                <el-main>
                    <router-view/>
                </el-main>
            </el-container>

        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Index",

        data() {
            return {
                userInfo: {
                    name: "",
                    author: 0,
                    uid: "",
                    run: 0,
                }
            }
        },

        methods: {
            logOut() {
                sessionStorage.clear()
                this.$message("你已退出登录")
                this.$router.push("/")
            },
            toPersonal(){
                this.$router.push("/personal")
                //console.log(this.$route.path)
            },
            changePW(){
                this.$router.push("/changePW")
            }
        },


        created() {
            let my = JSON.parse(sessionStorage.getItem("userInfo"))
            this.userInfo.name = my.name
            this.userInfo.uid = my.uid
            this.userInfo.author = my.author

            //用户登录
            if (my.uid !== "") {
                this.userInfo.run = 1
            }

        }
    }


</script>

<style scoped>
    .el-header {
        /*background-color: #909399;*/
        background: -webkit-linear-gradient(left,#545c64,#545c64);
        color: linen;
        line-height: 60px;
    }

    .el-aside {
        background-color: #303133;
    }

    .el-dropdown-link {
        cursor: pointer;
        color: #ffffff;
    }
    .el-icon-arrow-down {
        font-size: 12px;
    }
</style>