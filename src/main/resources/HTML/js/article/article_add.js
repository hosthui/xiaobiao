let vm = new Vue({
    el: '#app',  //选中整个main
    data: {
        article:{}
    },
    methods: {
        doAdd: function () {
            axios({
                url: `article/doAdd`,  //相对路径同样受到base标签影响
                method:"post",
                data: this.article
            }).then(response => {  //省略function关键字  自动传递上下文中的this
                    layer.msg(response.data.message)
                window.location.href="article"
                }
            ).catch(error => {
                console.log(error.message);
            })
        }
    }

});