var vm = new Vue(extend(common,{
    data: {
        baseUrl: '/logs/smslog',
        addRules: {
            content: [
            {required: true, message: '短信内容不能为空'}
        ],
                    phone: [
            {required: true, message: '接收方不能为空'}
        ],
                                                    },
        exportFields: [
       {key: 'content', label: '短信内容'},
       {key: 'phone', label: '接收方'},
       {key: 'sign', label: '用户签名'},
       {key: 'type', label: '类型'},
       {key: 'extno', label: '扩展码'},
       {key: 'status', label: '发送状态'},
       {key: 'sendId', label: '发送编号'},
       {key: 'invalidNum', label: '失败数量'},
       {key: 'successNum', label: '失败数量'},
       {key: 'blackNum', label: '黑名单数'},
       {key: 'returnMsg', label: '返回消息'},
       {key: 'updateTime', label: '更新时间'},
       {key: 'updateName', label: '操作人'},
        ]
    },
    methods: {

    }
}));
