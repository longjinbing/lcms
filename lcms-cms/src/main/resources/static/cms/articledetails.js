Vue.component('upload-image',uploadImage);
var vm = new Vue(extend(common,{
    data: {
        baseUrl: '/cms/article',
        datailsData:{
            id:document.getElementById("articleId").value
        },
        pc:true
    },
    methods: {
        init : function() {
            var _this = this;
            if (this.datailsData.id != undefined && this.datailsData.id.length>0) {
                this.doGet(this.baseUrl + "/" + this.datailsData.id, function (r) {
                    _this.datailsData = r.data;
                })
            }
        }
    },
    created:function () {
       this.init();
    }
}));
