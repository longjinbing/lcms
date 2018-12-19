var vm = new Vue(extend(common,{
    data: {
        baseUrl: '/health/testquestion',
        addRules: {
            question: [
            {required: true, message: '问题不能为空'}
        ],
                    healthId: [
            {required: true, message: '体质Id不能为空'}
        ],
                    status: [
            {required: true, message: '不能为空'}
        ],
                },
        exportFields: [
       {key: 'question', label: '问题'},
       {key: 'healthId', label: '体质Id'},
       {key: 'status', label: ''},
       {key: 'updateTime', label: '更新时间'},
       {key: 'updateName', label: '操作人'},
        ],
        categoryList:[]
    },
    methods: {
        init: function () {
            var _this=this;
            _this.doGet("/health/category/categorylist",function (r) {
                _this.categoryList=r.data;
            })
        }
    }
}));
