var vm = new Vue(extend(common,{
    data: {
        baseUrl: '/goods/goodsattribute',
        addRules: {
            goodsId: [
            {required: true, message: '商品Id不能为空'}
        ],
                    attributeId: [
            {required: true, message: '属性Id不能为空'}
        ],
                    value: [
            {required: true, message: '属性值不能为空'}
        ],
                    status: [
            {required: true, message: '不能为空'}
        ],
                },
        exportFields: [
       {key: 'goodsId', label: '商品Id'},
       {key: 'attributeId', label: '属性Id'},
       {key: 'value', label: '属性值'},
       {key: 'status', label: ''},
       {key: 'updateTime', label: ''},
       {key: 'updateName', label: '操作人'},
        ]
    },
    methods: {

    }
}));
