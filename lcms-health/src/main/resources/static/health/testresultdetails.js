var vm = new Vue(extend(common,{
    data: {
        baseUrl: '/health/testresultdetails',
        addRules: {
            resultId: [
            {required: true, message: '结果id不能为空'}
        ],
                    constitutionId: [
            {required: true, message: '体质id不能为空'}
        ],
                    result: [
            {required: true, message: '分值不能为空'}
        ],
                    status: [
            {required: true, message: '不能为空'}
        ],
                },
        exportFields: [
       {key: 'resultId', label: '结果id'},
       {key: 'constitutionId', label: '体质id'},
       {key: 'result', label: '分值'},
       {key: 'status', label: ''},
       {key: 'updateTime', label: '更新时间'},
       {key: 'updateName', label: '操作人'},
        ]
    },
    methods: {

    }
}));
