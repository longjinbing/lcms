var vm = new Vue(extend(common,{
    data: {
        baseUrl: '/manage/menu',
        addRules: {
            parentId: [
                {required: true, message: '上级菜单不能为空'}
            ],
            name: [
                {required: true, message: '菜单名称不能为空'}
            ],
            url: [
                {required: true, message: '菜单URL不能为空'}
            ],
            type: [
                {required: true, message: '类型不能为空'}
            ],
            icon: [
                {required: true, message: '菜单图标不能为空'}
            ],
            orderNum: [
                {required: true, message: '排序不能为空'}
            ],
            status: [
                {required: true, message: '状态不能为空'}
            ],
        },
        statusList:['隐藏','显示'],
        menuTypeList:['目录','菜单',"功能"],
        exportFields: [
       {key: 'name', label: '菜单名称'},
       {key: 'url', label: '菜单URL'},
       {key: 'type', label: '类型'},
       {key: 'icon', label: '菜单图标'},
       {key: 'orderNum', label: '排序'},
       {key: 'status', label: '状态'},
       {key: 'updateTime', label: '更新时间'},
       {key: 'updateName', label: '操作人'},
        ]
    },
    methods: {

    }
},tree));
