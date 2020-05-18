<template>
    <div>
        <el-container style="height: 660px; border: 1px solid #eee">

            <el-header>
                <div style="text-align: left; ">
                    <h1> 欢迎使用广东海洋大学人事管理系统</h1>
                </div>
            </el-header>
            <el-header>
                <div style="text-align: right; font-size: 12px">
                    <router-link v-if="!userInfo.run" to="/">请登录</router-link>
                    <div v-else>
                        <span>{{userInfo.name}}</span> |
                        <span v-if="userInfo.author">管理员</span>
                        <span v-else>员工</span> |
                        <el-button type="text" size="small" @click="logOut">退出登录</el-button>
                    </div>
                </div>
            </el-header>

            <el-container >
                <el-aside width="200px" style="background-color: rgb(238, 241, 246)" >
                    <!--添加router 绑定在菜单-->
                    <el-menu router>

                        <!--                    这里取router中的数组 index取的是下标-->
                        <!--                        <el-submenu v-for="(item,index) in $router.options.routes" :index="index.toString()"-->
                        <!--                                    v-if="!item.show" >-->
                        <!--                            <template slot="title">-->
                        <!--                                <i class="el-icon-menu"></i>-->
                        <!--                                {{item.name}}-->
                        <!--                            </template>-->
                        <!--                            &lt;!&ndash;                    路径跳转 index绑定路径 记得先加router&ndash;&gt;-->
                        <!--                            <el-menu-item v-for="_item in item.children" :index="_item.path" v-if="!_item.show">-->
                        <!--                                &lt;!&ndash;                                      :class="$route.path===_item.path?'is-active':''">&ndash;&gt;-->
                        <!--                                {{_item.name}}-->
                        <!--                            </el-menu-item>-->
                        <!--                        </el-submenu>-->

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

                        <el-submenu :index="'2'" :disabled="!userInfo.author">
                            <template slot="title">
                                <i class="el-icon-user-solid"></i>
                                {{$router.options.routes[2].name}}
                            </template>
                            <el-menu-item v-for="item in $router.options.routes[2].children" :index="item.path"
                                          v-if="!item.show">
                                {{item.name}}
                            </el-menu-item>
                        </el-submenu>

                        <el-submenu :index="'3'" :disabled="!userInfo.author">
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

                        <el-submenu :index="'5'" :disabled="!userInfo.author">
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
        background: -webkit-linear-gradient(left,mediumturquoise,skyblue, deepskyblue);
        color: linen;
        line-height: 60px;
    }

    .el-aside {
        color: #303133;
    }
</style>