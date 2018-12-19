Vue.component('upload-image', uploadImage);
Vue.component('m-select', elSelect);
Vue.component('u-editor',ueditor)
var vm = new Vue(extend(common, {
    data: {
        baseUrl: '/goods/goods',
        statusList: ['已下架', '已上架', '售完'],
        goodsUnitList: [],
        addRules: {
            categoryId: [
                {required: true, message: '分类不能为空'}
            ],
            name: [
                {required: true, message: '名称不能为空'}
            ],
            goodsNumber: [
                {required: true, message: '库存量不能为空'}
            ],
            keywords: [
                {required: true, message: '关键字不能为空'}
            ],
            goodsDesc: [
                {required: true, message: '商品描述不能为空'}
            ],
            counterPrice: [
                {required: true, message: '专柜价格不能为空'}
            ],
            goodsUnit: [
                {required: true, message: '商品单位不能为空'}
            ],
            primaryPicUrl: [
                {required: true, message: '商品主图不能为空'}
            ],
            listPicUrl: [
                {required: true, message: '商品列表图不能为空'}
            ],
            retailPrice: [
                {required: true, message: '零售价格不能为空'}
            ],
            unitPrice: [
                {required: true, message: '单位价格，单价不能为空'}
            ],
            appExclusivePrice: [
                {required: true, message: 'APP专享价不能为空'}
            ],
            isAppExclusive: [
                {required: true, message: '是否是APP专属不能为空'}
            ],
            marketPrice: [
                {required: true, message: '不能为空'}
            ],
            status: [
                {required: true, message: '不能为空'}
            ]
        },
        activeSetp: 1,
        categoryList: [],
        addFormData:{
            id:document.getElementById("goodsId").value
        },
        brandList:[]
    },
    methods: {
        init: function () {
            this.getGoodsUnit();
            this.getGoodsCategory();
            this.getBrandList();
            var _this = this;
            if (this.addFormData.id != undefined && this.addFormData.id.length > 0) {
                this.doGet(this.baseUrl + "/" + this.addFormData.id, function (r) {
                    _this.addFormData = r.data;
                })
            }
        },
        getGoodsUnit: function () {
            var _this = this;
            this.doGet("/config/config/list/"+'商品单位', function (r) {
                _this.goodsUnitList = r.data;
            })
        },
        getGoodsCategory: function () {
            var _this = this;
            this.doGet("/goods/goodscategory/categorylist", function (r) {
                _this.categoryList = r.data;
            })
        },
        getBrandList: function () {
            var _this = this;
            this.doGet("/config/brand/brandlist", function (r) {
                _this.brandList = r.data;
            })
        },
        saveFormData: function (formName) {
            var _this = this;
            this.$refs[formName].validate(function (valid) {
                if (valid) {
                    var action = _this.addFormData.id == undefined || _this.addFormData.id.length == 0 ? '/save' : '/update';
                    _this.doPost(_this.baseUrl + action, function (r) {
                        _this.refershParentTab();
                    }, _this.addFormData)
                } else {
                    console.log("验证shiba")
                    return false;
                }
            });
        },
    },
    created: function () {
        this.init();
    }
}));
