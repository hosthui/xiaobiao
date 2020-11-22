let vm = new Vue({
    el: '#app',  //选中整个main
    data: {
        pageInfo: {},       //初始化对象     vue建议声明对象同时进行初始化，避免undefinded
    },
    methods: {
        selectAll: function (pageNum = 1, pageSize = 5) {
            axios({
                url: `myuser/selectAll/${pageSize}/${pageNum}`,  //相对路径同样受到base标签影响
            }).then(response => {  //省略function关键字  自动传递上下文中的this
                this.pageInfo=response.data.data
                }
            ).catch(error => {
                console.log(error.message);
            })
        },
        toDetail: function (data) {
           let JSONdata= JSON.stringify(data)
                sessionStorage.setItem("usermsg",JSONdata);
        },
        doUpdate: function (id) {
            axios({
                url: `myuser/doupdate`,
                method: "put",
                data:{"userFocusId":id}
            }).then(response => {
                layer.msg(response.data.message);
                this.selectAll(this.pageInfo.pageNum,this.pageInfo.pageSize)
            }).catch(error => {
                layer.msg(error.message)
            })
        }
    },
    created: function () {
        this.selectAll();
    }
});