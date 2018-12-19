var vm = new Vue(extend(common,{
    data: {
        baseUrl: '/goods/goodsspecification',
        addRules: {
            goodsId: [
            {required: true, message: '商品不能为空'}
        ],
                    specificationId: [
            {required: true, message: '规格不能为空'}
        ],
                    value: [
            {required: true, message: '值不能为空'}
        ],
                    imageUrl: [
            {required: true, message: '图片不能为空'}
        ],
                    status: [
            {required: true, message: '不能为空'}
        ],
                },
        exportFields: [
       {key: 'goodsId', label: '商品'},
       {key: 'specificationId', label: '规格'},
       {key: 'value', label: '值'},
       {key: 'imageUrl', label: '图片'},
       {key: 'status', label: ''},
       {key: 'updateTime', label: ''},
       {key: 'updateName', label: '操作人'},
        ]
    },
    methods: {

    }
}));
