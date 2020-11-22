let vm = new Vue({
    el: '#app',  //选中整个main
    data: {
        user:{},
        password:""
    },
    methods: {
        addUser: function () {
            axios({
                url: "login/register",
                method: "post",
                data: this.user
            }).then(response => {
                layer.msg(response.data.message)
                setTimeout(function(){ window.location.href="login"; }, 1000);
            }).catch(error => {
                layer.msg(error.message)
            })
        }
    }
});