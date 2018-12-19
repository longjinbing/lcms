var vm = new Vue(extend(common, {
    data: {
        menuList: {},
        userInfo: {},
        currentTab: '1',
        tabList: [{
            label: '首页',
            id: '1',
            url: 'admin/systeminfo.html'
        }],
    },
    methods: {
        menuItemClick: function (menu) {
            var exist = false;
            for (var i = 0; i < this.tabList.length; i++) {
                if (this.tabList[i].id == menu.id) {
                    exist = true;
                    this.currentTab = this.tabList[i].id;
                }
            }
            if (!exist) {
                this.tabList.push({
                    label: menu.label,
                    id: menu.id + '',
                    url: menu.data
                });
            }
            this.currentTab = menu.id;
        },
        refershIframe:function(){
            var id=this.currentTab.substring(0,this.currentTab.lastIndexOf("-"));
            var iframe=document.getElementById("pane-"+id).childNodes[0];
            iframe.src=iframe.src+"?"+new Date().getTime();
            this.removeTab(this.currentTab);
            for (var i = 0; i < this.tabList.length; i++) {
                if (this.tabList[i].id == id) {
                    this.currentTab=id;
                    return;
                }
            }
        },
        newTab:function(src,title){
            var id=this.currentTab+"-"+new Date().getTime();
            src=src.substring(0,1)=='/'?src.substring(1,src.length):src;
            this.tabList.push({
                label:title,
                id: id,
                url: src
            });
            this.currentTab = id;
        },
        closeTab:function(){
            var id=this.currentTab.substring(0,this.currentTab.lastIndexOf("-"));
            this.removeTab(this.currentTab);
            for (var i = 0; i < this.tabList.length; i++) {
                if (this.tabList[i].id == id) {
                    this.currentTab=id;
                    return;
                }
            }
        },
        removeTab: function (id) {
            if (this.tabList.length > 1) {
                var position = 0;
                for (var i = 0; i < this.tabList.length; i++) {
                    if (this.tabList[i].id == id) {
                        this.tabList.splice(i, 1);
                        position = i;
                    }
                }
                this.currentTab = this.tabList[position] ? this.tabList[position].id : this.tabList[position - 1].id;
            } else {
                this.error("无法关闭");
            }
        },
        getMenuList: function (event) {
            var _this = this;
            this.doGet("/manage/menu/menulist", function (r) {
                _this.menuList = r.data
            });
        },
        logout: function () {
            var _this = this;
            this.confirm('您确定要安全退出本次登录吗？', function () {
                window.top.location.href = _this.targetUrl("/account/logout?");
            });
        },
        getUser: function () {
            var _this = this;
            this.doGet("/userinfo/info", function (r) {
                _this.userInfo = r.data.user
            });
        }
    },
    created: function () {
        this.getMenuList();
        this.getUser();
    },
    mounted: function () {
        var _this = this;
        var tabContainer=document.getElementById("el-tab");
        var tabItem=tabContainer.getElementsByClassName("el-tabs__item");
        document.addEventListener("contextmenu", function (event) {
            if(event.target.className.indexOf("el-tabs__item")>-1){
                var id = event.target.id.substring(4, event.target.id.length);
                var index;
                for(var i=0;i< tabItem.length;i++){
                    if(tabItem[i]==event.target){
                        index=i;
                    }
                }
                var contextMenu=document.createElement("div");
                contextMenu.id="context-menu";
                var ul=document.createElement("ul");
                var liData=[
                    {
                        className:'refersh',
                        iconClass:'el-icon-refresh',
                        label:'刷新'
                    },
                    {
                        className:'close-cur',
                        iconClass:'el-icon-circle-close',
                        label:'关闭标签'
                    },
                    {
                        className:'close-others',
                        iconClass:'el-icon-delete',
                        label:'关闭其他标签'
                    },
                    {
                        className:'close-left',
                        iconClass:'el-icon-caret-left',
                        label:'关闭左侧标签'
                    },
                    {
                        className:'close-right',
                        iconClass:'el-icon-caret-right',
                        label:'关闭右侧标签'
                    }
                ];
                for(var i=0;i<liData.length;i++){
                    var li=document.createElement("li");
                    li.className=liData[i].className;
                    var ie=document.createElement("i");
                    ie.className=liData[i].iconClass;
                    li.appendChild(ie);
                    li.innerText=liData[i].label;
                    ul.appendChild(li);
                }
                contextMenu.appendChild(ul);
                document.body.appendChild(contextMenu);
                contextMenu.style.left=event.pageX+"px";
                contextMenu.style.top=event.pageY+"px";;
                contextMenu.addEventListener("click", function (event) {
                    var cloneTabList =extend([], _this.tabList);
                    if (event.target.className.indexOf("refersh")>-1) {
                        var tabContentItem =tabContainer.getElementsByTagName("iframe");
                        tabContentItem[index].src=tabContentItem[index].src + "?"+new Date().getTime();
                    } else if (event.target.className.indexOf("close-cur")>-1) {
                        _this.removeTab(id);
                    } else if (event.target.className.indexOf("close-others")>-1) {
                        for (var i = 0; i < cloneTabList.length; i++) {
                            if (i != index) {
                                _this.removeTab(cloneTabList[i].id);
                            }
                        }
                    } else if (event.target.className.indexOf("close-left")>-1) {
                        for (var i = 0; i < index; i++) {
                            _this.removeTab(cloneTabList[i].id);
                        }
                    } else if (event.target.className.indexOf("close-right")>-1) {
                        for (var i = index + 1; i < cloneTabList.length; i++) {
                            if (cloneTabList[i] != undefined) {
                                _this.removeTab(cloneTabList[i].id);
                            }
                        }
                    }
                    contextMenu.parentNode.removeChild(contextMenu);
                });
                contextMenu.addEventListener("mouseleave",function (event) {
                    event.target.parentNode.removeChild(event.target);
                })
                event.preventDefault();
            }});
    }
}));
