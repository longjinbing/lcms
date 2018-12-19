var vm = new Vue(extend(common,{
    data: {
        baseUrl: '/cms/articlecomment',
        addRules: {
            parentId: [
            {required: true, message: '父评论ID不能为空'}
        ],
                    content: [
            {required: true, message: '内容不能为空'}
        ],
                        },
        exportFields: [
       {key: 'content', label: '内容'},
       {key: 'ip', label: 'Ip'},
       {key: 'status', label: '状态'},
       {key: 'updateTime', label: ''},
       {key: 'updateName', label: '操作人'},
        ]
    },
    methods: {

    }
}));
