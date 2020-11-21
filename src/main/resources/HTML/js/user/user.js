let vm = new Vue({
    el: '#app',  //选中整个main
    data: {
        page: {},       //初始化对象     vue建议声明对象同时进行初始化，避免undefinded
        username:"",
        focusId:[],
        focusIds:[]
    },
    methods: {
        selectAll: function (pageNum = 1, pageSize = 5) {
            axios({
                url: `user/selectAll/${pageSize}/${pageNum}`,  //相对路径同样受到base标签影响
                params:{'username':this.username}
            }).then(response => {  //省略function关键字  自动传递上下文中的this
                    let pages= response.data.data.page;
                    this.focusId=response.data.data.focous;
                    this.focusIds=[];
                    for (let i = 0; i <pages.content.length; i++) {
                        for (let j = 0; j < this.focusId.length; j++) {
                            if (pages.content[i].id==this.focusId[j].userFocusId){
                                pages.content[i].checke=true
                                this.focusIds.push(this.focusId[j].userFocusId);
                                break;
                            }else {
                                pages.content[i].checke=false
                            }
                        }
                    }
                    this.page=pages
                }
            ).catch(error => {
                // console.log(error.message);
            })
        },
        addApp: function () {
            this.actives = !this.actives
            axios({
                url: "manager/app/insert",
                method: "post",
                data: this.app
            }).then(response => {
                this.actives = !this.actives
                layer.msg("添加成功")
                this.selectAll()
                this.app = {
                    platform: "0",
                    forceUpdate: "0"
                }
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        doUpdate: function (pageNum,pageSize) {
            debugger;

            let newfocusId=[];
            for (let i = 0; i <this.page.content.length; i++) {
                if (this.page.content[i].checke==true){
                    newfocusId.push(this.page.content[i].id)
                }
                axios({
                    url: "user/doupdate",
                    method: "put",
                    data: {"newfocus":newfocusId,"focusId":this.focusIds}
                }).then(response => {
                    this.selectAll(pageNum,pageSize)
                }).catch(error => {
                    layer.msg(error.message)
                })
            }

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
    },
    created: function () {
        console.group('created 创建状态===============》');
        this.selectAll();
    },
    mounted:function () {
        window.addEventListener('beforeunload', e => this.doUpdate(e))
        // window.addEventListener('unload', e => this.unloadHandler(e))
    },
    destroyed:function () {
        window.removeEventListener('beforeunload', e => this.doUpdate(e))
    }
});