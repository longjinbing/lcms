Vue.component('upload-image',uploadImage)
Vue.component('u-editor',ueditor)
var vm = new Vue(extend(common,{
    data: {
        baseUrl: '/cms/article',
        ableList:['禁止','允许'],
        addRules: {
            title: [
                {required: true, message: '文章标题不能为空'}
            ],
            content: [
                {required: true, message: '文件内容不能为空'}
            ],
            viewCount: [
                {required: true, message: '浏览数不能为空'}
            ],
            orderNum: [
                {required: true, message: '排序不能为空'}
            ],
            status: [
                {required: true, message: '状态不能为空'}
            ],
            catogryId: [
                {required: true, message: '类别Id不能为空'}
            ],
            author: [
                {required: true, message: '作者不能为空'}
            ],
            publishTime: [
                {required: true, message: '发布时间不能为空'}
            ],
            commentable: [
                {required: true, message: '能否评价不能为空'}
            ]
        },
        addFormData:{
            categoryId:document.getElementById("categoryId").value,
            id:document.getElementById("articleId").value
        }
    },
    methods: {
        init : function() {
            var _this = this;
            this.addFormData.publishTime=dateFormat(new Date());
            if (this.addFormData.id != undefined && this.addFormData.id.length>0) {
                this.doGet(this.baseUrl + "/" + this.addFormData.id, function (r) {
                    _this.addFormData = r.data;
                })
            }
        },
        saveFormData: function (formName) {
            var _this = this;
            this.$refs[formName].validate(function (valid) {
                if (valid) {
                    var action=_this.addFormData.id==undefined||_this.addFormData.id.length==0?'/save':'/update';
                    _this.doPost(_this.baseUrl + action, function (r) {
                        _this.refershParentTab();
                    }, _this.addFormData)
                } else {
                    return false;
                }
            });
        },
    },
    created:function () {
   this.init();
    }
}));
