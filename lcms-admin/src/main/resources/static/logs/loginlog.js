var vm = new Vue(extend(common,{
    data: {
        baseUrl: '/logs/loginlog',
        addRules: {
                            },
        exportFields: [
       {key: 'status', label: '用户状态'},
       {key: 'ip', label: 'IP地址'},
       {key: 'updateTime', label: '更新时间'},
       {key: 'device', label: '登陆来源'},
       {key: 'remark', label: '备注'},
       {key: 'result', label: '结果'},
       {key: 'updateName', label: '操作人'},
        ]
    },
    methods: {

    }
}));
