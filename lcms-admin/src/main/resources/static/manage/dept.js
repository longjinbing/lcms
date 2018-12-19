var vm = new Vue(extend(common, {
    data: {
        baseUrl: '/manage/dept',
        addRules: {
            name:[{required:true,message:"必须填写"}],
            contactName:[{required:true,message:"必须填写"}],
            contactPhone:[{required:true,message:"必须填写"}],
            remark:[{required:true,message:"必须填写"}],
        },
        exportFields: [
            {key: 'remark', label: '职责'},
            {key: 'name', label: '部门名称'},
            {key: 'contactName', label: '负责人'},
            {key: 'contactPhone', label: '电话'}
        ],
    },
    methods: {

    }
},tree));
