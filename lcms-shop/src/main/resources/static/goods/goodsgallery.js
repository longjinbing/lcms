var vm = new Vue(extend(common,{
    data: {
        baseUrl: '/goods/goodsgallery',
        addRules: {
            goodsId: [
            {required: true, message: '商品id不能为空'}
        ],
                    imgUrl: [
            {required: true, message: '图片不能为空'}
        ],
                    imgDesc: [
            {required: true, message: '描述不能为空'}
        ],
                    status: [
            {required: true, message: '不能为空'}
        ],
                },
        exportFields: [
       {key: 'goodsId', label: '商品id'},
       {key: 'imgUrl', label: '图片'},
       {key: 'imgDesc', label: '描述'},
       {key: 'orderNum', label: '排序'},
       {key: 'status', label: ''},
       {key: 'updateTime', label: ''},
       {key: 'updateName', label: '操作人'},
        ]
    },
    methods: {

    }
}));
