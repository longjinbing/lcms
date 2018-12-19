var vm = new Vue(extend(common,{
    data: {
        baseUrl: '/cms/cateogry',
        addRules: {
            parentId: [
            {required: true, message: '父Id不能为空'}
        ],
                    name: [
            {required: true, message: '名称不能为空'}
        ],
                    remark: [
            {required: true, message: '不能为空'}
        ],
                    orderNum: [
            {required: true, message: '排序不能为空'}
        ],
                    status: [
            {required: true, message: '状态不能为空'}
        ],
                },
        addFormData:{
            orderNum:0,
            status:1,
            parentId:0
        },
        exportFields: [
       {key: 'name', label: '名称'},
       {key: 'remark', label: ''},
       {key: 'orderNum', label: '排序'},
       {key: 'status', label: '状态'},
       {key: 'updateTime', label: ''},
       {key: 'updateName', label: '操作人'},
        ]
    },
    methods: {
        updateDialogOpen:function (row) {
            var _this = this;
            this.doGet(this.baseUrl + "/" + row.id, function (r) {
                _this.editFormData = r.data;
            })
            this.visible.editFormVisible = true;
        }
    }
}));
