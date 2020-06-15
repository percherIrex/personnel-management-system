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
        name: "企业信息",
        component: Index,
        //redirect:"路径",
        children: [
            {
                path: "/user/list",
                name: "员工信息",
                component: () => import('../components/User/UserList.vue')
            },
            {
                path: "/vision",
                name: "可视化",
                component: ()=> import('../views/Vision')
            }
        ]
    },
    {
        path: "/navigation",
        name: "人员管理",
        component: Index,
        children: [
            {
                path: "/user/new",
                name: "新增员工",
                component: () => import('../components/User/UserSave.vue')
            },

            {
                path: "/user/edit",
                name: "信息更改",
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
        children: [
            {
                path: "/salary/edit",
                name: "薪资编辑",
                component: () => import('../components/Salary/SalaryEdit')
            },
            {
                path: "/salary/out",
                name: "月度报表",
                component: () => import('../components/Salary/SalaryOut')
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
            },
            {
                path: "/app/handle",
                name: "请假审批",
                component: () => import('../components/Sign/HandleApp')
            }
        ]
    },
    {
        path: "/navigation4",
        name: "系统安全",
        component: Index,
        children: [
            {
                path: "/control",
                name: "权限操作",
                component: () => import("../components/Author/Control")
            },
            {
                path: "/operation",
                name: "操作历史",
                component: () => import("../components/Operation/OperationList")
            }
        ]

    },
    {
        path: "/navi5",
        name: "更改密码",
        component: Index,
        children: [
            {
                path: "/changePW",
                component: () => import("../views/EditPW")
            },
            {
                path: "/personal",
                component: () => import("../views/Personal")
            }
        ]
    }


]

const router = new VueRouter({

    routes

})

export default router
