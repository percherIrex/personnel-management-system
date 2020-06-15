<template>
    <div class="gra-main">
        <div class="w" id="gra-main-1"></div>
        <div class="w" id="gra-main-2"></div>
        <div class="w" id="gra-main-3"></div>
        <div class="w" id="gra-main-4"></div>
    </div>
</template>
<script>
    import echarts from 'echarts'
    export default {
        mounted() {
            let myChart1 = echarts.init(document.getElementById('gra-main-1'))
            let option1 = {
                title: {
                    text: '今日企业考勤情况',
                    left: 'center',
                    textStyle: {
                        color: '#000'
                    }
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b}: {c} ({d}%)'
                },
                legend: {
                    orient: 'vertical',
                    left: 10,
                    data: ['已签到人员数', '未签到人员数', '请假人员数']
                },
                series: [
                    {
                        name: '企业考勤',
                        type: 'pie',
                        radius: ['50%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            label: {
                                show: true,
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        },
                        labelLine: {
                            show: false
                        },
                        data: []
                    }
                ]
            };
            myChart1.setOption(option1)
            axios.get("http://localhost:8081/record/count").then(function (resp) {
                myChart1.setOption({
                    series: [{
                        data: [
                            {value: resp.data.cof_sign, name: '已签到人员数'},
                            {value: resp.data.cof_absent, name: '请假人员数'},
                            {value: (resp.data.cof_total - resp.data.cof_sign - resp.data.cof_absent), name: '未签到人员数'},
                        ]
                    }]
                })
            })
            //div2的图
            let d = new Date()
            let day = new Date(d.getFullYear(), d.getMonth() + 1, 0).getDate() //当月总天数
            let my = JSON.parse(sessionStorage.getItem("userInfo"))
            let myChart2 = echarts.init(document.getElementById('gra-main-2'))
            let option2 = {
                title: {
                    text: '个人当月考勤情况',
                    left: 'center',
                    textStyle: {
                        color: '#000'
                    }
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b}: {c} ({d}%)'
                },
                legend: {
                    orient: 'vertical',
                    left: 10,
                    data: ['已签到天数', '未签到天数', '请假天数']
                },
                series: [
                    {
                        name: '个人考勤',
                        type: 'pie',
                        radius: ['50%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            label: {
                                show: true,
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        },
                        labelLine: {
                            show: false
                        },
                        data: []
                    }
                ]
            };
            myChart2.setOption(option2)
            axios.get("http://localhost:8081/record/vision/" + my.uid).then(function (resp) {
                myChart2.setOption({
                    series: [{
                        data: [
                            {value: resp.data.cof_sign, name: '已签到天数'},
                            {value: resp.data.cof_absent, name: '请假天数'},
                            {value: (day - resp.data.cof_sign - resp.data.cof_absent), name: '未签到天数'},
                        ]
                    }]
                })
            })
            //div3的图
            let myChart3 = echarts.init(document.getElementById('gra-main-3'))
            let option3 = {
                title: {
                    text: '企业中各部门人数',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b} : {c} ({d}%)'
                },
                legend: {
                    left: 'center',
                    top: 'bottom',
                    data: []
                },
                toolbox: {
                    show: true,
                    feature: {
                        mark: {show: true},
                        magicType: {
                            show: true,
                            type: ['pie', 'funnel']
                        },

                    }
                },
                series: [
                    {
                        name: '',
                        type: 'pie',
                        radius: [30, 110],
                        center: ['50%', '50%'],
                        roseType: 'area',
                        data: []
                    }
                ]
            };
            myChart3.setOption(option3)
            axios.get("http://localhost:8081/user/countDepart").then(function (resp) {
                myChart3.setOption({
                    series: [{
                        data: resp.data
                    }]
                })
            })
            //div4的图
            let myChart4 = echarts.init(document.getElementById('gra-main-4'))
            let option4 = {
                title: {
                    text: '企业员工性别分布情况',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b}: {c} ({d}%)'
                },
                legend: {
                    orient: 'vertical',
                    left: 10,
                    data: []
                },
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        selectedMode: 'single',
                        radius: [0, '70%'],

                        label: {
                            position: 'inner'
                        },
                        labelLine: {
                            show: false
                        },
                        data: []
                    },
                ]
            };
            myChart4.setOption(option4)
            axios.get("http://localhost:8081/user/countGender").then(function (resp) {

                myChart4.setOption({
                    legend: {
                        data: resp.data.name
                    },
                    series: [
                        {
                            data: [
                                {name: resp.data[1].name, value: resp.data[1].value},
                                {name: resp.data[0].name, value: resp.data[0].value, selected: true}
                            ]
                        }
                    ]
                })
            })
        }
    }
</script>

<style>
    * {
        margin: 0;
        padding: 0;
    }
    .gra-main {
        width: 83%;
        height: 75%;
        position: absolute;
    }
    .w {
        width: 50%;
        height: 50%;
        float: left;
        box-shadow: 0px 0px 2px #90878d;
    }
    #gra-main-2 {
        background-color: aliceblue;
    }
    #gra-main-3 {
        background-color: aliceblue;
    }
</style>