var vm = new Vue(extend(common,{
    data: {
        baseUrl: '/manage/token',
        addRules: {
                    remark: [
            {required: true, message: '备注不能为空'}
        ],
                    ip: [
            {required: true, message: 'IP地址不能为空'}
        ],
                    },
        exportFields: [
       {key: 'expireTime', label: '过期时间'},
       {key: 'ticket', label: '票据'},
       {key: 'remark', label: '备注'},
       {key: 'ip', label: 'IP地址'},
       {key: 'principle', label: '授权'},
       {key: 'updateName', label: '操作人'},
        ]
    },
    methods: {

    }
}));
