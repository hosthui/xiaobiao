let vm = new Vue({
    el: '#app',
    data: {
        email:"",
        code:"",
        user:{}
    },
    methods: {
        reset: function () {
            axios({
                url: `login/reset/${this.code}`,
                method: "post",
                data: this.user
            }).then(response => {
                if (response.data.success) {//如果返回success是true

                }
                //提示
                layer.msg(response.data.msg);
            }).catch(error => {
                layer.msg(error.message);
            });
        }
        ,
        sendCode: function () {
            debugger
            axios({
                url: "login/send",
                method: "get",
                params: {'email':this.email}
            }).then(response => {
                if (response.data.flag) {
                    layer.msg(response.data.message)
                    this.user.email=this.email
                } else {
                    //失败提示
                    layer.msg(response.data.message);
                }
            }).catch(error => {
                layer.msg(error.message);//异常提示
            })
        }
    }
})