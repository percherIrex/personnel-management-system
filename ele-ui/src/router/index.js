import Vue from 'vue'
import VueRouter from 'vue-router'
import App from "../App";
import Index from "../views/Index";
import Login from "../views/Login";

Vue.use(VueRouter)

const routes = [
    {
        path: "/",
        name: "Login",
        component: Login
    },
    {
        path: "/index",
        name: "员工资料",
        component: Index,
        //redirect:"路径",
        children: [
            {
                path: "/user/list",
                name: "员工信息",
                component: () => import('../components/User/UserList.vue')
            },
        ]
    },
    {
        path: "/navigation",
        name: "人事管理",
        component: Index,
        children: [
            {
                path: "/user/new",
                name: "新晋员工",
                component: () => import('../components/User/UserSave.vue')
            },

            {
                path: "/user/edit",
                name: "人员调动",
                component: () => import('../components/User/UserEdit.vue')
            },

            {
                path: "/user/search",
                name: "查询员工",
                component: () => import('../components/User/UserSearch.vue')
            },
        ]
    },
    {
        path: "/navigation2",
        name: "薪资管理",
        component: Index,
        children:[
            {
                path:"/salary/edit",
                name:"薪资编辑",
                component:()=>import('../components/Salary/SalaryEdit')
            },
            {
                path:"/salary/out",
                name:"月度报表",
                component:()=>import('../components/Salary/SalaryOut')
            }

        ]
    },
    {
        path: "/navigation3",
        name: "考勤管理",
        component: Index,
        children: [
            {
                path: "/sign/clock",
                name: "上班打卡",
                component: () => import('../components/Sign/Clock')
            },
            {
                path: "/sign/absent",
                name: "请假相关",
                component: () => import('../components/Sign/Absent')
            }
        ]
    },
    {
        path: "/navigation4",
        name: "权限管理",
        component: Index,
        children: [{
            path: "/control",
            name: "权限操作",
            component: () => import("../components/Author/Control")
        }]

    },

]

const router = new VueRouter({

    routes

})

export default router
