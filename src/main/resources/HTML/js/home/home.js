let vm = new Vue({
    el: '#app',
    data: {
        countuser:{
            num:"",
            percent:""
        },
        countarticle:{
            num:"",
            percent:""
        },
        countmeeting:{
            num:"",
            percent:""
        },
        option: {
            // 标题
            title: {
                text: '小标数据汇总'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    label: {
                        backgroundColor: '#aaaaff'
                    }
                }
            },
            // 图表说明
            legend: {
                data: ['新增用户', '新增文章', '新开会议']
            },
            // 工具栏(图片另存为按钮)
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            // 布局
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [{
                type: 'category',
                boundaryGap: false,
                data: []
            }],
            yAxis: [{
                type: 'value'
            }],
            series: [{
                name: '新增用户',
                type: 'line',
                stack: '总量',
                areaStyle: {},
                data: []
            },
                {
                    name: '新增文章',
                    type: 'line',
                    stack: '总量',
                    areaStyle: {},
                    data: []
                },
                {
                    name: '新开会议',
                    type: 'line',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'top'
                        }
                    },
                    areaStyle: {},
                    data: []
                }
            ]
        }
    },
    methods: {
        selectAll: function () {
            let date = new Date();
            this.option.xAxis[0].data[8]=this.dateFormat("mm.dd",date)
            for (let i = 7; i >=0; i--) {
                date.setDate(date.getDate()-1)
                this.option.xAxis[0].data[i]=this.dateFormat("mm.dd",date)
            }
            axios({
                method: 'post',
                url: `home/selectAll`,
                data:this.option.xAxis[0].data
            }).then(response => {
                let all = response.data.data;
                this.option.series[0].data=all.user
                this.countuser.num=eval(all.user.join("+"))
                this.countuser.percent=this.countuser.num%100

                this.option.series[1].data=all.meeting
                this.countarticle.num=eval(all.meeting.join("+"))
                this.countarticle.percent=this.countarticle.num%100

                this.option.series[2].data=all.article
                this.countmeeting.num=eval(all.article.join("+"))
                this.countmeeting.percent=this.countmeeting.num%100
                this.init()
            }).catch(error => {
                layer.msg(error.message);
            });
        },
        init: function () {
            echarts.init(document.getElementById('main')).setOption(this.option);
        },
        dateFormat: function (fmt, date) {
            let ret;
            const opt = {
                "Y+": date.getFullYear().toString(),        // 年
                "m+": (date.getMonth() + 1).toString(),     // 月
                "d+": date.getDate().toString(),            // 日
                "H+": date.getHours().toString(),           // 时
                "M+": date.getMinutes().toString(),         // 分
                "S+": date.getSeconds().toString()          // 秒
                // 有其他格式化字符需求可以继续添加，必须转化成字符串
            };
            for (let k in opt) {
                ret = new RegExp("(" + k + ")").exec(fmt);
                if (ret) {
                    fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
                }
                ;
            }
            ;
            return fmt;
        },
        Add: function () {
            this.flag = false;
            axios({
                url: "manager/app/insert",
                method: "post",
                data: this.appVersion
            }).then(response => {
                if (response.data.success) {//如果返回success是true
                    //成功插入  1.切换回列表页 2.重新查询数据 3.提示 4.清空表单信息
                    this.flag = true;
                    // console.log(response.data.obj);//null:后台没有传递对象
                    if (this.pageInfo.size >= this.pageInfo.pageSize) {
                        this.selectAll(this.pageInfo.pages + 1, this.pageInfo.pageSize);
                    } else {
                        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);//重新查询数据库表
                    }
                    layer.msg(response.data.msg);//成功提示
                    this.appVersion = {};//将当前页面的数据清空
                } else {
                    //失败提示
                    layer.msg(response.data.msg);
                }
            }).catch(error => {
                layer.msg(error.message);//异常提示
            })
        }
    },
    created:
        function () {

            this.selectAll();
        }
})