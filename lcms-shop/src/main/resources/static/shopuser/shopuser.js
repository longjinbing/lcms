var vm = new Vue(extend(common,{
    data: {
        baseUrl: '/shopuser/shopuser',
        sexList:['女','男'],
        addRules: {
            username: [
            {required: true, message: '不能为空'}
        ],
                    password: [
            {required: true, message: '不能为空'}
        ],
                    gender: [
            {required: true, message: '不能为空'}
        ],
                    birthday: [
            {required: true, message: '不能为空'}
        ],
                    registerTime: [
            {required: true, message: '不能为空'}
        ],
                    lastLoginTime: [
            {required: true, message: '不能为空'}
        ],
                    lastLoginIp: [
            {required: true, message: '不能为空'}
        ],
                    userLevelId: [
            {required: true, message: '不能为空'}
        ],
                    nickname: [
            {required: true, message: '不能为空'}
        ],
                    mobile: [
            {required: true, message: '不能为空'}
        ],
                    registerIp: [
            {required: true, message: '不能为空'}
        ],
                    avatar: [
            {required: true, message: '不能为空'}
        ],
                    weixinOpenid: [
            {required: true, message: '不能为空'}
        ],
                    status: [
            {required: true, message: '不能为空'}
        ],
                },
        exportFields: [
       {key: 'username', label: ''},
       {key: 'password', label: ''},
       {key: 'gender', label: ''},
       {key: 'birthday', label: ''},
       {key: 'registerTime', label: ''},
       {key: 'lastLoginTime', label: ''},
       {key: 'lastLoginIp', label: ''},
       {key: 'userLevelId', label: ''},
       {key: 'nickname', label: ''},
       {key: 'mobile', label: ''},
       {key: 'registerIp', label: ''},
       {key: 'avatar', label: ''},
       {key: 'weixinOpenid', label: ''},
       {key: 'status', label: ''},
       {key: 'updateTime', label: ''},
       {key: 'updateName', label: '操作人'},
        ]
    },
    methods: {

    }
}));
