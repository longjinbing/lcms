var vm = new Vue(extend(common,{
    data: {
        baseUrl: '/goods/goodsissue',
        addRules: {
            question: [
            {required: true, message: '问题不能为空'}
        ],
                    answer: [
            {required: true, message: '回答不能为空'}
        ],
                    status: [
            {required: true, message: '不能为空'}
        ],
                },
        exportFields: [
       {key: 'question', label: '问题'},
       {key: 'answer', label: '回答'},
       {key: 'status', label: ''},
       {key: 'updateTime', label: ''},
       {key: 'updateName', label: '操作人'},
        ]
    },
    methods: {

    }
}));
