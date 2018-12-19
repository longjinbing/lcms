var vm = new Vue(extend(common,{
    data: {
        baseUrl: '/health/testresult',
        addRules: {
            userId: [
            {required: true, message: '用户id不能为空'}
        ],
                    username: [
            {required: true, message: '姓名不能为空'}
        ],
                    phone: [
            {required: true, message: '联系方式不能为空'}
        ],
                    sex: [
            {required: true, message: '性别不能为空'}
        ],
                    healthId: [
            {required: true, message: '体质id不能为空'}
        ],
                    status: [
            {required: true, message: '不能为空'}
        ],
                },
        exportFields: [
       {key: 'userId', label: '用户id'},
       {key: 'username', label: '姓名'},
       {key: 'phone', label: '联系方式'},
       {key: 'sex', label: '性别'},
       {key: 'healthId', label: '体质id'},
       {key: 'status', label: ''},
       {key: 'updateTime', label: '更新时间'},
       {key: 'updateName', label: '操作人'},
        ]
    },
    methods: {

    }
}));
