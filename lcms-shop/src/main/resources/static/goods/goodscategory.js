Vue.component('upload-image',uploadImage);
var vm = new Vue(extend(common,tree,{
    data: {
        baseUrl: '/goods/goodscategory',
        addRules: {
            name: [
            {required: true, message: '名称不能为空'}
        ],
                    keywords: [
            {required: true, message: '关键字不能为空'}
        ],
                    frontDesc: [
            {required: true, message: '介绍不能为空'}
        ],
                    parentId: [
            {required: true, message: '上级ID不能为空'}
        ],
                    showIndex: [
            {required: true, message: '不能为空'}
        ],
                    bannerUrl: [
            {required: true, message: '轮播图片不能为空'}
        ],
                    iconUrl: [
            {required: true, message: '图标不能为空'}
        ],
                    imgUrl: [
            {required: true, message: '图片不能为空'}
        ],
                    wapBannerUrl: [
            {required: true, message: 'app轮播图不能为空'}
        ],
                    level: [
            {required: true, message: '不能为空'}
        ],
                    type: [
            {required: true, message: '类型不能为空'}
        ],
                    frontName: [
            {required: true, message: '名称不能为空'}
        ],
                    status: [
            {required: true, message: '不能为空'}
        ],
                },
        exportFields: [
       {key: 'name', label: '名称'},
       {key: 'keywords', label: '关键字'},
       {key: 'frontDesc', label: '介绍'},
       {key: 'orderNum', label: '排序'},
       {key: 'showIndex', label: ''},
       {key: 'bannerUrl', label: '轮播图片'},
       {key: 'iconUrl', label: '图标'},
       {key: 'imgUrl', label: '图片'},
       {key: 'wapBannerUrl', label: 'app轮播图'},
       {key: 'level', label: ''},
       {key: 'type', label: '类型'},
       {key: 'frontName', label: '名称'},
       {key: 'status', label: ''},
       {key: 'updateTime', label: ''},
       {key: 'updateName', label: '操作人'},
        ]
    },
    methods: {
        openAddFormDialogSame:function () {
            var row=this.getSelectSingleRow();
            if(row!=undefined) {
                this.openTab("/shop/addgoodscategory/" + row.parentId,'添加同级分类');
            }
        },
        openAddFormDialogChild:function () {
            var row=this.getSelectSingleRow();

            if(row!=undefined) {
                this.openTab("/shop/addgoodscategory/" + row.id,'添加下级分类');
            }
        }
    }
}));
