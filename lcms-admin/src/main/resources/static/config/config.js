
var vm = new Vue(extend(common,tree,{
    data: {
        baseUrl: '/config/config',
        addRules: {
                            },
        exportFields: [
       {key: 'item', label: '键名'},
       {key: 'value', label: '键值'},
       {key: 'status', label: '状态'},
       {key: 'remark', label: '备注'},
       {key: 'updateTime', label: '更新时间'},
       {key: 'updateName', label: '操作人'},
        ]
    },
    methods: {

    }
}));
