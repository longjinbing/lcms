var vm = new Vue(extend(common,{
    data: {
        baseUrl: '/logs/log',
        addRules: {
                                            },
        exportFields: [
       {key: 'operation', label: '用户操作'},
       {key: 'method', label: '请求方法'},
       {key: 'params', label: '请求参数'},
       {key: 'ip', label: 'IP地址'},
       {key: 'updateTime', label: '操作时间'},
       {key: 'url', label: 'URL'},
       {key: 'remark', label: '备注'},
       {key: 'level', label: '级别'},
       {key: 'errorStack', label: '错误堆栈信息'},
       {key: 'status', label: '状态'},
       {key: 'updateName', label: '操作人'},
        ]
    },
    methods: {

    }
}));
