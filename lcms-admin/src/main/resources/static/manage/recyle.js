var vm = new Vue(extend(common,{
    data: {},
    methods: {
        initSystem:function () {
            this.doPost("/manage/recyle/initsystem",function () {
                window.location.href=targetUrl("/account/logout");
            })
        },
        clearRecyle:function () {
            var _this=this;
            this.doPost("/manage/recyle/clearrecyledata",function () {
                _this.success("清除成功");
            })
        }
    },
    created:function () {

    }
}));
