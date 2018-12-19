var vm = new Vue(extend(common,{
    data: {
        baseUrl: '/goods/goodsattributecategory',
        addRules: {
            attributeCategoryId: [
            {required: true, message: '商品类型不能为空'}
        ],
                    name: [
            {required: true, message: '属性名称不能为空'}
        ],
                    inputType: [
            {required: true, message: '当添加商品时,该属性的添加类别; 0为手功输入;1为选择输入;2为多行文本输入不能为空'}
        ],
                    value: [
            {required: true, message: '即选择输入,则attr_name对应的值的取值就是该这字段值 不能为空'}
        ],
                    status: [
            {required: true, message: '不能为空'}
        ],
                },
        exportFields: [
       {key: 'attributeCategoryId', label: '商品类型'},
       {key: 'name', label: '属性名称'},
       {key: 'inputType', label: '当添加商品时,该属性的添加类别; 0为手功输入;1为选择输入;2为多行文本输入'},
       {key: 'value', label: '即选择输入,则attr_name对应的值的取值就是该这字段值 '},
       {key: 'orderNum', label: ''},
       {key: 'status', label: ''},
       {key: 'updateTime', label: ''},
       {key: 'updateName', label: '操作人'},
        ]
    },
    methods: {

    }
}));
