let vm = new Vue({
    el: '#app',  //选中整个main
    data: {
        article: {},       //初始化对象     vue建议声明对象同时进行初始化，避免undefinded
        faCount:{},
        isfa:""
    },
    methods: {
        favoriteCount:function (id) {
            axios({
                url: `article/favoritecount`,  //相对路径同样受到base标签影响
                params:{'articleId':id}
            }).then(response => {  //省略function关键字  自动传递上下文中的this
                    this.faCount=response.data.data.list
                this.isfa=response.data.data.isfa
                }
            ).catch(error => {
                console.log(error.message);
            })

        },
        toDetail: function (data) {
            let JSONdata= JSON.stringify(data)
            sessionStorage.setItem("usermsg",JSONdata);
        },
        toUpdate:function () {
            axios({
                url: `article/favoriteup`,  //相对路径同样受到base标签影响
                method:"put",
                data:{'articleId':this.article.id,"isfa":this.isfa}
            }).then(response => {  //省略function关键字  自动传递上下文中的this
                layer.msg(response.data.message)
                this.isfa=!this.isfa
                }
            ).catch(error => {
                console.log(error.message);
            })
        }
    },
    created: function () {
        this.article=JSON.parse(sessionStorage.getItem("articlemsg"));
        this.favoriteCount(this.article.id)
    }
});