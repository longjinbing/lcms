'use strict'

var contextPath = "lcms";

var common = {
    el: '#main',
    data: {
        queryParams: {
            total: 0,
            offset: 0,
            limit: 10,
            current: 1,
            sort: '',
            order: '',
            field: '',
            text: '',
            stime: '',
            etime: ''
        },
        pickerOptions: {
            shortcuts: [{
                text: '今天',
                onClick(picker) {
                    picker.$emit('pick', new Date());
                }
            }, {
                text: '昨天',
                onClick(picker) {
                    var date = new Date();
                    date.setTime(date.getTime() - 3600 * 1000 * 24);
                    picker.$emit('pick', date);
                }
            }, {
                text: '一周前',
                onClick(picker) {
                    var date = new Date();
                    date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                    picker.$emit('pick', date);
                }
            }]
        },
        tableData: [],
        visible: {
            editFormVisible: false,
            addFormVisible: false,
            detailsVisible: false,
            exportVisible: false,
            userInfoVisible: false
        },
        tagTypeList: ['info', 'success', 'warning', 'danger', 'primary'],
        statusList: ['锁定', '正常'],
        exportFields: [],
        displayFields: [],
        selectedFields: [],
        detailsData: {},
        addFormData: {},
        editFormData: {},
        selectedRow: [],
        tableLoading: false,
        formLoading: false,
        userInfo: {},
        kaptchaKey: '',
        uploadFileList: []
    },
    methods: {
        init: function () {
        },
        openAddFormBefore: function () {
        },
        openEditFormBefore: function () {
        },
        saveBefore: function () {
        },
        updateBefore: function () {
        },
        loadData: function () {
            var _this = this;
            this.doGet(this.baseUrl + "/list", function (r) {
                _this.tableData = r.data.rows;
                _this.queryParams.total = parseInt(r.data.total);
            }, this.filterObject(this.queryParams))
        },
        addDialogOpen: function () {
            this.addFormData = {};
            this.openAddFormBefore();
        },
        updateDialogOpen: function (index, row) {
            var _this = this;
            this.doGet(this.baseUrl + "/" + row.id, function (r) {
                _this.editFormData = r.data;
                _this.openEditFormBefore(r);
            })
            this.visible.editFormVisible = true;
        },
        selectedChange: function (selection) {
            this.selectedRow = selection;
        },
        deleteData: function (index, row) {
            var ids = [], _this = this;
            this
            for (var i = 0; i < this.selectedRow.length; i++) {
                ids.push(this.selectedRow[i].id);
            }
            ;
            if (ids.length == 0) {
                this.error("未选择数据");
                return false;
            }
            _this.confirm("您正在删除重要数据，请确认？", function () {
                _this.doPost(_this.baseUrl + "/delete", function (r) {
                    _this.selectedRow = [];
                    _this.loadData();
                    _this.success("删除成功")
                }, ids)
            })
        },
        getSelectSingleRow: function () {
            if (this.selectedRow == undefined || this.selectedRow.length == 0) {
                this.error("请选择行");
                return;
            }
            if (this.selectedRow.length > 1) {
                this.error("只能选择一行");
                return;
            }
            return this.selectedRow[0];
        },
        deleteDataRow: function (id) {
            var ids = [id], _this = this;
            _this.confirm("您正在删除重要数据，请确认？", function () {
                _this.doPost(_this.baseUrl + "/delete", function (r) {
                    _this.selectedRow = [];
                    _this.loadData();
                    _this.success("删除成功")
                }, ids)
            })
        },
        sortUp: function (index, row) {
            var _this = this;
            if (this.queryParams.offset == 0 && index == 0) {
                _this.error("已经是第一条了，无法上移");
                return;
            }
            this.doPost(this.baseUrl + "/sortup", function (r) {
                _this.loadData();
            }, {index: this.queryParams.offset + index, id: row.id, queryParams: this.filterObject(this.queryParams)})
        },
        sortDown: function (index, row) {
            var _this = this;
            if (this.queryParams.total == index + this.queryParams.offset + 1) {
                _this.error("已经在最后了，无法下移");
                return;
            }
            this.doPost(this.baseUrl + "/sortdown", function (r) {
                _this.loadData();
            }, {index: this.queryParams.offset + index, id: row.id, queryParams: this.filterObject(this.queryParams)})
        },
        sortTop: function (index, row) {
            var _this = this;
            if (this.queryParams.offset == 0 && index == 0) {
                _this.error("已经是第一条了，无法上移");
                return;
            }
            this.doPost(this.baseUrl + "/sorttop", function (r) {
                _this.loadData();
            }, {index: this.queryParams.offset + index, id: row.id, queryParams: this.filterObject(this.queryParams)})
        },
        inputNumberFocus: function (event) {
            if (event.taget.value == undefined) {
                event.taget.value = 1;
            }
        },
        detailsDialogOpen: function (index, row) {
            var _this = this;
            this.doGet(this.baseUrl + "/details/" + row.id, function (r) {
                _this.detailsData = r.data;
                _this.visible.detailsVisible = true;
            });
        },
        saveFormData: function (formName) {
            this.saveBefore();
            var _this = this;
            this.$refs[formName].validate(function (valid) {
                if (valid) {
                    _this.doPost(_this.baseUrl + "/save", function (r) {
                        _this.visible.addFormVisible = false;
                        _this.$refs[formName].resetFields();
                        _this.loadData();
                        _this.success("添加成功");
                    }, _this.addFormData)
                } else {
                    return false;
                }
            });
        },
        openTab: function (src, title) {
            parent.vm.newTab(src, title);
        },
        returnParentTab: function () {
            parent.vm.closeTab();
        },
        refershParentTab: function () {
            parent.vm.refershIframe();
        },
        updateFormData: function (formName) {
            this.updateBefore();
            var _this = this;
            this.$refs[formName].validate(function (valid) {
                if (valid) {
                    _this.doPost(_this.baseUrl + "/update", function (r) {
                        _this.visible.editFormVisible = false;
                        _this.$refs[formName].resetFields();
                        _this.loadData();
                        _this.success("修改成功");
                    }, _this.editFormData)
                } else {
                    return false;
                }
            });
        },
        sortChange: function (obj) {
            this.queryParams.sort = obj.prop;
            this.queryParams.order = obj.order == "descending" ? "desc" : "asc";
            this.loadData();
        },
        sizeChange: function (size) {
            if (size != this.queryParams.limit) {
                this.queryParams.limit = size;
                this.loadData();
            }
        },
        pageChange: function (current) {
            if (current != this.queryParams.current) {
                this.queryParams.current = current;
                this.queryParams.offset = (current - 1) * this.queryParams.limit;
                this.loadData();
            }
        },
        targetUrl: function (url) {
            var rootarr = window.location.href.split('/');
            if (rootarr[3] == contextPath) {
                return rootarr[0] + '//' + rootarr[2] + '/' + rootarr[3] + url;
            } else {
                return rootarr[0] + '//' + rootarr[2] + url;
            }
        },
        doGet: function (url, callback, data) {
            var _this = this;
            _this.tableLoading = _this.tableLoading != undefined ? true : undefined;
            _this.formLoading = _this.formLoading != undefined ? true : undefined;
            data = data == undefined ? {} : data;
            axios.get(this.targetUrl(url), {params: data})
                .then(function (response) {
                    if (_this.check(response.data)) {
                        callback(response.data);
                    }
                })
                .catch(function (error) {
                    console.log(url + JSON.stringify(data))
                    _this.error("网络发生故障");
                });
        },
        queryUserInfo: function (userId) {
            this.visible.detailsVisible = false;
            var _this = this;
            this.doGet("/sysuser/" + userId, function (r) {
                _this.userInfo = r.data;
                _this.visible.userInfoVisible = true;
            })
        },
        doPost: function (url, callback, data) {
            var _this = this;
            _this.formLoading = _this.formLoading != undefined ? true : undefined;
            data = data == undefined ? {} : data;
            axios.post(this.targetUrl(url), data)
                .then(function (response) {
                    if (_this.check(response.data)) {
                        callback(response.data);
                    }
                })
                .catch(function (error) {
                    _this.error("网络发生故障");
                });
        },
        generatorIndex: function (index) {
            return this.queryParams.offset + 1 + index;
        },
        success: function (msg) {
            this.$notify({
                title: '操作结果',
                message: msg,
                type: 'success'
            });
        },
        error: function (msg) {
            this.$message.error(msg);
        },
        confirm: function (msg, callback) {
            this.$confirm(msg, '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                callback();
            }).catch(function () {
                this.$message({
                    type: 'info',
                    message: '已取消'
                });
            });
        },
        exportOpen: function () {
            this.displayFields = Object.assign([], this.exportFields);
            this.visible.exportVisible = true;
        },
        exportData: function () {
            if (this.selectedFields.length == 0) {
                this.error("请选择导出列");
                return false;
            }
            var _this = this;
            var props = this.exportFields.filter(function (item) {
                return _this.selectedFields.indexOf(item.key) > -1 ? true : false;
            });
            var sendData = {queryParams: this.filterObject(this.queryParams), selectedFields: props}
            this.visible.exportVisible = false;
            this.download(this.baseUrl + "/export", sendData);
            this.selectedFields = [];
        },
        filterObject: function (obj) {
            var newObj = Object.assign({}, obj);
            for (var key in newObj) {
                if (newObj[key] === "" || newObj[key] === undefined) {
                    delete newObj[key];
                }
            }
            return newObj;
        },
        check: function (r) {
            var result = false;
            this.tableLoading = this.tableLoading != undefined ? false : undefined;
            this.formLoading = this.formLoading != undefined ? false : undefined;
            switch (r.code) {
                case 200:
                    result = true;
                    break;
                case 405:
                    this.error(r.msg);
                    window.top.location.href = this.targetUrl("/account/unauthorized?message=") + r.msg;
                    break;
                case 403:
                    this.error(r.msg);
                    break;
                case 402:
                    this.error(r.msg);
                    break;
                case 401:
                    this.error(r.msg);
                    break;
            }
            return result;
        },
        renderKaptcha: function () {
            this.$refs.kapcher.$emit('render');
        },
        limitText: function (text, limit) {
            if (text.length < limit) {
                return text;
            } else {
                return text.substring(0, limit) + "...";
            }
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        download: function (url, data, type) {
            var _this = this;
            type = type == undefined ? 'application/json' : type;
            var xhr = new XMLHttpRequest();
            xhr.open('POST', this.targetUrl(url), true);
            xhr.setRequestHeader('content-type', type);
            xhr.responseType = "blob";
            xhr.onload = function () {
                var name = xhr.getResponseHeader("Content-disposition");
                if (name == null || name == undefined) {
                    _this.error("网络错误，下载文件失败，请稍后重试!");
                } else {
                    if (name == undefined) {
                        _this.error("文件名不存在，请检查！");
                        return;
                    }
                    var filename = name.substring(22, name.length - 1);
                    var fileType = xhr.getResponseHeader("Content-Type").split(";")[0];
                    var blob = new Blob([xhr.response], {type: fileType});
                    var link = document.createElement('a');
                    var body = document.querySelector('body');
                    link.href = window.URL.createObjectURL(blob);
                    link.download = decodeURI(filename);
                    link.style.display = 'none';
                    body.appendChild(link);
                    link.click();
                    body.removeChild(link);
                }
            };
            xhr.ontimeout = function (e) {
                _this.error("网络超时");
            };
            xhr.onerror = function (e) {
                _this.error("网络错误");
            };
            xhr.send(JSON.stringify(data));
        }
    },
    created: function () {
        this.init();
        if (this.baseUrl != undefined) this.loadData();
    }
};

var kaptcha = {
    data: {kaptchaKey: ''},
    template: '<img ref="kaptcha" @click="render()" class="vertify-img">',
    methods: {
        render: function () {
            var _this = this;
            var xmlhttp;
            xmlhttp = new XMLHttpRequest();
            xmlhttp.open("GET", targetUrl("/kaptcha/render?kaptcha-key=") + _this.kaptchaKey, true);
            xmlhttp.responseType = "blob";
            xmlhttp.onload = function () {
                if (this.status == 200) {
                    var blob = this.response;
                    _this.$refs.kaptcha.src = window.URL.createObjectURL(blob);
                    _this.$emit('update:key', xmlhttp.getResponseHeader("kaptchaKey"));
                    _this.kaptchaKey = xmlhttp.getResponseHeader("kaptchaKey");
                }
            }
            xmlhttp.send();
        },
    },
    created: function () {
        this.render();
        this.$on('render', function () {
            this.render();
        });
    }
}

var uploadImage = {
    props: ['image', 'limit'],
    data: function () {
        return {
            uploadFileList: [],
            uploadAction: targetUrl("/upload/image"),
            init: true
        }
    },
    template: ' <el-upload :limit="limit" :file-list="uploadFileList"' +
        ' name="file" :action="uploadAction" :on-exceed="limitOver" :on-success="uploadImageSuccess" :on-remove="removeImage" :on-error="uploadImageError" list-type="picture-card">' +
        '<i class="el-icon-plus"></i></el-upload>',
    methods: {
        uploadImageSuccess: function (response, file, fileList) {
            var src = response.data[0];
            fileList[fileList.length - 1].url = targetUrl(src);
            fileList[fileList.length - 1].status = "SUCCESS";
            this.notify(fileList);
        },
        removeImage: function (file, fileList) {
            this.notify(fileList);
        },
        notify: function (fileList) {
            this.init = false;
            var arr = [], url;
            for (var i = 0; i < fileList.length; i++) {
                url = fileList[i].url;
                arr.push(url.substring(url.indexOf("/static"), url.length));
            }
            this.image = arr.join(";");
            this.$emit("update:image", this.image);
        },
        limitOver: function () {
            this.$message.error("只允许上传" + this.limit + "图片");
        },
        uploadImageError: function (err, file, fileList) {

        },
    },
    watch: {
        image: function () {
            if (this.init && this.image != undefined) {
                var arr = this.image.split(';');
                for (var i = 0; i < arr.length; i++) {
                    this.uploadFileList.push({
                        name: new Date().getTime(),
                        url: targetUrl(arr[i])
                    });
                }
                this.init = false;
            }
        }
    }
}

var elSelect = {
    props: ['options', 'selected', 'changeOnSelect'],
    data: function () {
        return {
            deptProps: {
                value: 'id',
                label: 'name',
                children: 'children'
            },
            selectValue: []
        }
    },
    template: '<el-cascader :props="deptProps" v-model="selectValue" change-on-select="changeOnSelect" @change="select" :options="options"></el-cascader>',
    methods: {
        select: function (value) {
            this.selected = value[value.length - 1];
        },
        childNode: function (nodes, value) {
            for (var i = 0; i < nodes.length; i++) {
                if (nodes[i].id != this.selected) {
                    if (nodes[i].children != undefined && nodes[i].children.length > 0) {
                        var temp = [];
                        if (value.length > 0) {
                            for (var j = 0; j < value.length; j++) {
                                temp.push(value[j]);
                            }
                        }
                        temp.push(nodes[i].id);
                        this.childNode(nodes[i].children, temp);
                    }
                } else {
                    value.push(nodes[i].id)
                    this.selectValue = value;
                    break;
                }
            }
        }
    },
    watch: {
        selected: function () {
            if (this.selectValue[this.selectValue.length - 1] == this.selected) {
                this.$emit("update:selected", this.selected);
            } else {
                if (this.selected != undefined) {
                    this.childNode(this.options, []);
                }
            }

        }
    }
}

var targetUrl = function (url) {
    var rootarr = window.location.href.split('/');
    if (rootarr[3] == contextPath) {
        return rootarr[0] + '//' + rootarr[2] + '/' + rootarr[3] + url;
    } else {
        return rootarr[0] + '//' + rootarr[2] + url;
    }
}

function extend(target) {
    var src, copy;
    var args = arguments
    for (var i = 1; i < arguments.length; i++) {
        var source = arguments[i]
        for (var key in source) {
            if (!target[key]) {
                target[key] = source[key];
            } else {
                if (typeof source[key] == "object") {
                    if (Object && source[key] != {}) {
                        target[key] = extend(target[key], source[key]);
                    } else {
                        target[key] = {};
                    }
                } else {
                    target[key] = source[key];
                }
            }
        }
    }
    return target;
}

var tree = {
    data: {
        currentRow: {}
    },
    methods: {
        loadData: function () {
            var _this = this;
            this.doGet(this.baseUrl + "/list", function (r) {
                _this.tableData=[];
                for (var i = 0; i < r.data.rows.length; i++) {
                    r.data.rows[i].level = 0;
                    _this.tableData.push(r.data.rows[i])
                }
                _this.queryParams.total = parseInt(r.data.total);
            }, this.filterObject(this.queryParams))
        },
        getSingleSelectRow: function () {
            var msg=this.selectedRow.length == 1?true:this.selectedRow.length>1?'只允许选择一条数据':'请选择一条数据'
            if (msg!=1) {
                this.error(msg);
                return;
            }
            return this.selectedRow[0]
        },
        openAddFormDialogSame: function () {
            var row = this.getSingleSelectRow();
            if (row != undefined) {
                this.addFormData.parentId = row.parentId;
                this.visible.addFormVisible = true;
            }
        },
        openAddFormDialogChild: function () {
            var row = this.getSingleSelectRow();
            if (row != undefined) {
                this.addFormData.parentId = row.id;
                this.visible.addFormVisible = true;
            }
        },
        expandColumn: function (index, row) {
            var childNode = row.children;
            if (!row.expand) {
                this.tableData[index].expand = true;
                for (var i = 0; i < childNode.length; i++) {
                    childNode[i].level = row.level + 1;
                    this.tableData.splice(index + 1 + i, 0, childNode[i]);
                }
                this.currentRow = {index: index, children: false};
            } else {
                this.notyfyChildNodeClose(index);
                this.tableData.splice(index + 1, this.childNodeCount(index, row));
                this.tableData[index].expand = false;
            }
        },
        notyfyChildNodeClose:function(index){
            var level=this.tableData[index].level;
            for (var i = index + 1; i < this.tableData.length; i++) {
                if (this.tableData[i].level > level) {
                    this.tableData[i].expand=false;
                } else {
                    break;
                }
            }
        },
        childNodeCount: function (index, row) {
            var len = 0;
            for (var i = index + 1; i < this.tableData.length; i++) {
                if (this.tableData[i].level > row.level) {
                    len++;
                } else {
                    break;
                }
            }
            return len;
        }

    }
}

function dateFormat(date) {
    var y = date.getFullYear(),
        m = date.getMonth(),
        d = date.getDay(),
        h = date.getHours(),
        mm = date.getMinutes(),
        ss = date.getSeconds();
    mm = m + 1 < 10 ? "0" + m + 1 : m + 1;
    d = d < 10 ? "0" + d : d;
    return y + "-" + m + "-" + d + " " + h + ":" + mm + ":" + ss;
}

var ueditor={
    prop:['content','height','id'],
    template: '<script class="ueditor" :id="id" type="text/plain"></script>',
    data: function(){
        return {
            ue:{}
        }
    },
    created: function () {
        this.ue=UE.getEditor(this.id);
        var _this=this;
        this.ue.addListener('blur',function(editor){
            _this.$emit('update:content',_this.ue.getContent());
        })
    },
    watch:{
        content: function (newvalue) {
            if(this.ue.getContent()!=this.content) {
                this.ue.setContent(this.content);
            }
        }
    }
}

var vueHtmlEditor={
    props:['content'],
    template: '<vue-html5-editor :content="content" @change="change" :auto-height="true"></vue-html5-editor>',
    methods:{
        change:function (content) {
            this.$emit('update:content',content);
        }
    },
    created:function () {
        useVueHtmlEditor();
    }
}
function useVueHtmlEditor() {
    Vue.use(VueHtml5Editor, {
        // 全局组件名称，使用new VueHtml5Editor(options)时该选项无效
        // global component name
        name: "vue-html5-editor",
        // 是否显示模块名称，开启的话会在工具栏的图标后台直接显示名称
        // if set true,will append module name to toolbar after icon
        showModuleName: false,
        // 自定义各个图标的class，默认使用的是font-awesome提供的图标
        // custom icon class of built-in modules,default using font-awesome
        icons: {
            text: "fa fa-pencil",
            color: "fa fa-paint-brush",
            font: "fa fa-font",
            align: "fa fa-align-justify",
            list: "fa fa-list",
            link: "fa fa-chain",
            unlink: "fa fa-chain-broken",
            tabulation: "fa fa-table",
            image: "fa fa-file-image-o",
            hr: "fa fa-minus",
            eraser: "fa fa-eraser",
            undo: "fa-undo fa",
            "full-screen": "fa fa-arrows-alt",
            info: "fa fa-info",
        },
        // 配置图片模块
        // config image module
        image: {
            // 文件最大体积，单位字节  max file size
            sizeLimit: 512 * 1024,
            // 上传参数,默认把图片转为base64而不上传
            // upload config,default null and convert image to base64
            upload: {
                url: targetUrl("/upload/image"),
                headers: {},
                params: {},
                fieldName: 'file'
            },
            // 压缩参数,默认使用localResizeIMG进行压缩,设置为null禁止压缩
            // compression config,default resize image by localResizeIMG (https://github.com/think2011/localResizeIMG)
            // set null to disable compression
            compress: {
                width: 1600,
                height: 1600,
                quality: 80
            },
            // 响应数据处理,最终返回图片链接
            // handle response data，return image url
            uploadHandler(responseText){
                //default accept json data like  {ok:false,msg:"unexpected"} or {ok:true,data:"image url"}
                var json = JSON.parse(responseText)
                if (json.code==200) {
                    return json.data[0];
                } else {
                    vm.alert("上传失败")
                }
            }
        },
        // 语言，内建的有英文（en-us）和中文（zh-cn）
        //default en-us, en-us and zh-cn are built-in
        language: "zh-cn",
        // 自定义语言
        i18n: {
            //specify your language here
            "zh-cn": {
                "align": "对齐方式",
                "image": "图片",
                "list": "列表",
                "link": "链接",
                "unlink": "去除链接",
                "table": "表格",
                "font": "文字",
                "full screen": "全屏",
                "text": "排版",
                "eraser": "格式清除",
                "info": "关于",
                "color": "颜色",
                "please enter a url": "请输入地址",
                "create link": "创建链接",
                "bold": "加粗",
                "italic": "倾斜",
                "underline": "下划线",
                "strike through": "删除线",
                "subscript": "上标",
                "superscript": "下标",
                "heading": "标题",
                "font name": "字体",
                "font size": "文字大小",
                "left justify": "左对齐",
                "center justify": "居中",
                "right justify": "右对齐",
                "ordered list": "有序列表",
                "unordered list": "无序列表",
                "fore color": "前景色",
                "background color": "背景色",
                "row count": "行数",
                "column count": "列数",
                "save": "确定",
                "upload": "上传",
                "progress": "进度",
                "unknown": "未知",
                "please wait": "请稍等",
                "error": "错误",
                "abort": "中断",
                "reset": "重置"
            }
        },
        // 隐藏不想要显示出来的模块
        // the modules you don't want
        hiddenModules: [],
        // 自定义要显示的模块，并控制顺序
        // keep only the modules you want and customize the order.
        // can be used with hiddenModules together
        visibleModules: [
            "text",
            "color",
            "font",
            "align",
            "list",
            "link",
            "unlink",
            "tabulation",
            "image",
            "hr",
            "eraser",
            "undo",
            "full-screen",
            "info",
        ],
        // 扩展模块，具体可以参考examples或查看源码
        // extended modules
        modules: {
            //omit,reference to source code of build-in modules
        }
    })
}


