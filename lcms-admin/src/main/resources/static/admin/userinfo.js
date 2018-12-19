var vm = new Vue(extend(common,{
    data: {user:{},roles:[]},
    statusList:['禁用','正常','违规封号'],
    methods: {
        init:function () {
            var _this=this;
            this.doPost("/userinfo/info",function (r) {
                _this.user=r.data.user;
                _this.roles=r.data.roles;
            })
        }
    }
}));
