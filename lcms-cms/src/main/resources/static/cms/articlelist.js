var vm = new Vue(extend(common,{
    data: {
        baseUrl: '/cms/article',
        ableList:['禁止','允许'],
        statusList:['暂存','发布'],
        queryParams:{
            pid:document.getElementById("categoryId").value
        },
        addRules: {
            title: [
            {required: true, message: '文章标题不能为空'}
        ],
                        content: [
            {required: true, message: '文件内容不能为空'}
        ],
                    viewCount: [
            {required: true, message: '浏览数不能为空'}
        ],
                    orderNum: [
            {required: true, message: '排序不能为空'}
        ],
                    status: [
            {required: true, message: '状态不能为空'}
        ],
                    categoryId: [
            {required: true, message: '类别Id不能为空'}
        ],
                    author: [
            {required: true, message: '作者不能为空'}
        ],
                    publishTime: [
            {required: true, message: '发布时间不能为空'}
        ],
                    commentable: [
            {required: true, message: '能否评价不能为空'}
        ],
                    },
        exportFields: [
       {key: 'title', label: '文章标题'},
       {key: 'image', label: '图片url'},
       {key: 'content', label: '文件内容'},
       {key: 'viewCount', label: '浏览数'},
       {key: 'orderNum', label: '排序'},
       {key: 'status', label: '状态'},
       {key: 'updateTime', label: '更新时间'},
       {key: 'catogryId', label: '类别Id'},
       {key: 'author', label: '作者'},
       {key: 'publishTime', label: '发布时间'},
       {key: 'commentable', label: '能否评价'},
       {key: 'attachment', label: '附件地址'},
       {key: 'updateName', label: '操作人'},
        ]
    },
    methods: {
        saveBefore:function () {
            this.addFormData.categoryId=this.queryParams.pid;
        },
        publishArticle:function () {
            var _this=this;
            if(this.selectedRow==undefined||this.selectedRow.length==0){
                _this.error("请选择文章");
            }
            var ids=[];
            for(var i=0;i<this.selectedRow.length;i++){
                ids.push(this.selectedRow[i].id);
            }
            this.doPost(this.baseUrl+"/publish",function (r) {
                _this.loadData();
            },ids);
        },
        stopArticlePublish:function () {
            var _this=this;
            if(this.selectedRow==undefined||this.selectedRow.length==0){
                _this.error("请选择文章");
            }
            var ids=[];
            for(var i=0;i<this.selectedRow.length;i++){
                ids.push(this.selectedRow[i].id);
            }
            this.doPost(this.baseUrl+"/stopPublish",function (r) {
                _this.loadData();
            },ids);
        }
    }
}));
