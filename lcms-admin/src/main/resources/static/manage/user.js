var vm = new Vue(extend(common,{
    data: {
        baseUrl: '/manage/user',
        addRules: {
            username: [
                {required: true, message: '用户名不能为空'}
            ],
            nickname: [
                {required: true, message: '昵称不能为空'}
            ],
            name: [
                {required: true, message: '姓名不能为空'}
            ],
            password: [
                {required: true, message: '密码不能为空'}
            ],
            email: [
                {required: true, message: '邮箱不能为空'}
            ],
            phone: [
                {required: true, message: '手机号不能为空'}
            ],
            status: [
                {required: true, message: '状态不能为空'}
            ],
            number: [
                {required: true, message: '编号不能为空'}
            ],
            remark: [
                {required: true, message: '备注不能为空'}
            ],
        },
        exportFields: [
       {key: 'username', label: '用户名'},
       {key: 'nickname', label: '昵称'},
       {key: 'name', label: '姓名'},
       {key: 'password', label: '密码'},
       {key: 'email', label: '邮箱'},
       {key: 'phone', label: '手机号'},
       {key: 'status', label: '状态'},
       {key: 'updateTime', label: '更新时间'},
       {key: 'code', label: '编号'},
       {key: 'remark', label: '备注'},
       {key: 'updateName', label: '操作人'},
        ],
        visible:{
            editUserRoleDialogVisible:false
        },
        selectRoleList:[],
        roleData:[]
    },
    methods: {
        saveUserRoleData:function () {
            var _this=this;
            this.doPost("/manage/userrole/saveuserrole",function (r) {
                _this.success("操作成功");
                _this.visible.editUserRoleDialogVisible=false;
            },{userId:this.addFormData.userId,roleIds:this.selectRoleList})
        },
        openUserRoleDialog:function (index,row) {
            var _this=this;
            this.doGet("/manage/role/rolelist/"+row.id,function (r) {
                _this.roleData=r.data;
                for(var i=0;i<_this.roleData.length;i++){
                    if(_this.roleData[i].checked=='checked'){
                        _this.selectRoleList.push(_this.roleData[i].id);
                    }
                }
                _this.addFormData.userId=row.id;
                _this.visible.editUserRoleDialogVisible=true;
            })
        }
    }
}));
