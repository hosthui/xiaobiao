let vm = new Vue({
    el: '#app',  //选中整个main
    data: {
        user: {},       //初始化对象     vue建议声明对象同时进行初始化，避免undefinded
    },
    methods: {
    },
    created: function () {
        this.user=JSON.parse(sessionStorage.getItem("usermsg"));
    }
});