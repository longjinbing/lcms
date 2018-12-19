Vue.component('vueHtmlEditor',vueHtmlEditor);
var vm = new Vue(extend(common, {
    data: {
        baseUrl: '/health/attribute',
        queryParams: {
            pid: document.getElementById("healthId").value
        },
        addRules: {
            name: [
                {required: true, message: '属性名称不能为空'}
            ],
            content: [
                {required: true, message: '属性内容不能为空'}
            ],
            status: [
                {required: true, message: '不能为空'}
            ],
        },
        exportFields: [
            {key: 'name', label: '属性名称'},
            {key: 'content', label: '属性内容'},
            {key: 'orderNum', label: '排序'},
            {key: 'status', label: ''},
            {key: 'updateTime', label: '更新时间'},
            {key: 'updateName', label: '操作人'},
        ],
        content:''
    },
    methods: {
        saveBefore: function () {
            this.addFormData.healthId=this.queryParams.pid
        },
        updateBefore: function () {
            this.editFormData.healthId=this.queryParams.pid
        }
    }
}));
