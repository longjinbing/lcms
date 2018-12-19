Vue.component('m-select', elSelect);
var vm = new Vue(extend(common, {
    data: {
        baseUrl: '/manage/role',
        addRules: {
            name: [{required: true, message: "必须填写"}],
            description: [{required: true, message: "必须填写"}],
            deptId: [{required: true, message: "必须填写"}],
            remark: [{required: true, message: "必须填写"}],
        },
        exportFields: [
            {key: 'name', label: '角色名称'},
            {key: 'remark', label: '备注'},
            {key: 'updateTime', label: '更新时间'},
            {key: 'description', label: '描述'},
            {key: 'updateName', label: '操作人'},
        ],
        visible: {
            editRoleMenuDialogVisible: false
        },
        treeData: [],
        deptData: [],
        roleProps: {
            label: "name"
        },
        selectedIds:[]
    },
    methods: {
        init: function () {
            var _this = this;
            _this.doGet("/manage/dept/deptlist", function (r) {
                _this.deptData = r.data;
            })
        },
        editRoleMenuDialogOpen: function () {
            this.visible.editRoleMenuDialogVisible = true;
        },
        editRoleMenuDialog: function (index, row) {
            var _this = this;
            this.selectedIds=[];
            this.treeData=[];
            this.doGet("/manage/rolemenu/menulist/" + row.id, function (r) {
                _this.treeData = r.data;
                _this.addFormData.roleId=row.id;
                _this.childNode(r.data);
                _this.visible.editRoleMenuDialogVisible = true;
            })
        },
        childNode:function(nodes){
            for(var i=0;i<nodes.length;i++){
                if(nodes[i].checked=="checked") {
                    this.selectedIds.push(nodes[i].id);
                }
                if(nodes[i].children!=undefined&&nodes[i].children.length>0){
                    this.childNode(nodes[i].children);
                }
            }
        },
        saveRoleMenuData: function () {
            var _this=this;
            this.doPost("/manage/rolemenu/rolemenusave",function (r) {
                _this.success("操作成功");
                _this.visible.editRoleMenuDialogVisible = false;
            },{roleId:this.addFormData.roleId,menuIds:this.$refs.menuTree.getCheckedKeys()})
        }
    }
}));
