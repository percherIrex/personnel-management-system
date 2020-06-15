<template>
    <div class="personal-main">
        <h2>个人中心</h2>
        <!--        <div class="alert w49 alert-info" v-for="item in this.Info">{{item}}</div>-->
        <div class="alert w49 alert-info">个人ID：{{this.Info.id}}</div>
        <div class="alert w49 alert-info" v-if="status==='未签到'">今日状态：{{this.status}}
            <router-link to="/sign/clock" style="font-size: xx-small;">点击这里去签到...</router-link>
        </div>
        <div class="alert w49 alert-info" v-else>今日状态：{{this.status}}</div>
        <div class="alert w49 alert-info">邮箱：{{this.Info.email}}</div>
        <div class="alert w49 alert-info">电话：{{this.Info.phone}}</div>
        <div class="alert w49 alert-info">住址：{{this.Info.address}}</div>
        <div class="alert w49 alert-info">所属部门：{{this.Info.depart}}</div>
        <div class="alert w49 alert-info">当前职位：{{this.Info.position}}</div>
        <div class="alert w49 alert-info">加入系统时间：{{this.Info.intime}}</div>

    </div>
</template>

<script>
    export default {
        name: "Personal",

        data() {
            return {
                Info: {},
                status: ""
            }
        },

        created() {
            let my = JSON.parse(sessionStorage.getItem("userInfo"))
            let _this = this

            axios.get("http://localhost:8081/user/findById/" + my.uid).then(function (resp) {
                _this.Info = resp.data
            })

            axios.get("http://localhost:8081/record/" + my.uid).then(function (res) {
                _this.status = res.data
            })
        }
    }
</script>

<style scoped>
    .personal-main {
        width: 1000px;
        height: 400px;
        margin: auto;
        box-shadow: 0px 0px 10px #90878d;
        border-radius: 5px;
    }

    .w49 {
        width: 40%;
        float: left;
        margin-right: 30px;
        margin-top: 10px;
    }

    .alert {
        margin-bottom: 20px;
        padding: 10px 35px 10px 14px;
        border: 1px solid #000000;
        border-radius: 5px;
    }

    .alert-info {
        border-color: #bce8f1;
        background-color: #d9edf7;
        color: #3a87ad;
    }
</style>