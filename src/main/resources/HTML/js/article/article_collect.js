let vm = new Vue({
    el: '#app',  //选中整个main
    data: {
        pageInfo: {},       //初始化对象     vue建议声明对象同时进行初始化，避免undefinded
        articlename:"",
    },
    methods: {
        selectAll: function (pageNum = 1, pageSize = 3) {
            axios({
                url: `article/selectUserAll/${pageNum}/${pageSize}`,  //相对路径同样受到base标签影响
                params:{'articleName':this.articlename}
            }).then(response => {  //省略function关键字  自动传递上下文中的this
                    this.pageInfo=response.data.data
                }
            ).catch(error => {
                console.log(error.message);
            })
        },
        toDetail: function (data) {
            let JSONdata= JSON.stringify(data)
            sessionStorage.setItem("articlemsg",JSONdata);
        },
        toadd(){
            window.location.href="article/toAdd"
        }
    },
    created: function () {
        this.selectAll();
    },

});