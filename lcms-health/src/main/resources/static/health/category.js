var vm = new Vue(extend(common,{
    data: {
        baseUrl: '/health/category',
        addRules: {
            name: [
            {required: true, message: '体质名称不能为空'}
        ],
                    remark: [
            {required: true, message: '简单描述不能为空'}
        ],
                    status: [
            {required: true, message: '不能为空'}
        ],
                },
        exportFields: [
       {key: 'name', label: '体质名称'},
       {key: 'remark', label: '简单描述'},
       {key: 'status', label: ''},
       {key: 'updateTime', label: '更新时间'},
       {key: 'updateName', label: '操作人'},
        ]
    },
    methods: {

    }
}));
