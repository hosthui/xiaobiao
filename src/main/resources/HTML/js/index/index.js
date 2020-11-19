let vm = new Vue({
    el: '#app',
    data: {
        username:"",
        password: "",
        code:"",
        render:"kaptcha/render"
    },
    methods: {
        refresh:function () {
            this.render="kaptcha/render?"+new Date()
        },
        loginIn: function () {
            axios({
                url:"login/loginIn",
                method:"post",
                data:{username:this.username,password:this.password,code:this.code}
            }).then(response=>{
                let result=response.data.obj
                if (response.data.flag){
                    let userjson = JSON.stringify(result);
                    sessionStorage.setItem("loginsession",userjson);
                    window.location.href="main/toHome"
                }else {
                    layer.msg(response.data.message)
                    this.refresh()
                }
            }).catch(error=>{
                layer.msg(error)
                this.refresh()
            })
        },
        toUpdate: function (data) {
            layer.obj = data;
            layer.open({
                type: 2,
                title: false,
                shadeClose: true,
                shade: 0.1,
                area: ['80%', '80%'],
                content: 'manager/app/toupdate',
                end: () => {
                    this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize)
                }
            })
        },
        toDel: function (app) {
            app.delFlag = 1
            layer.msg('确认删除', {
                time: 20000, //20s后自动关闭
                btn: ['确定', '取消'],
                yes: () => {
                    axios({
                        url: "manager/app/update",
                        method: "put",
                        data: app
                    }).then(response => {
                        layer.msg(response.data.message)
                        this.selectAll();
                    }).catch(error => {
                        layer.msg(error.message)
                    })

                }
            });
        }
    }
});