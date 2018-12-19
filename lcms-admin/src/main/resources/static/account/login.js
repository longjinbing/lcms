Vue.component('kaptcha-code',kaptcha);
var vm = new Vue(extend(common,{
    data: {
        user: {
            username:'longjinbin',
            password:'123456'
        },
        target: 1,
        rules: {
            username: [{
                required: true, message: '请输入用户名'
            }],
            password: [{
                required: true, message: '请输入密码'
            }],
            kaptcha: [{
                required: true, message: '请输入验证码'
            }]
        }
    },
    beforeCreate: function () {
        if (window.top != window) {
            window.top.location.href = window.location.href;
        }

    },
    methods: {
        login: function (formName) {
            this.saveBefore();
            var _this = this;
            this.$refs[formName].validate(function(valid){
                if (valid) {
                    _this.formLoading=true;
                    document.getElementById("check-result").innerText='';
                    _this.user.kaptchaKey=_this.kaptchaKey;
                    axios({
                        url:_this.targetUrl("/account/login"),
                        method:'post',
                        data:_this.user
                    }).then(function (response) {
                        var r=response.data;
                        if (r.code == 200) {//登录成功
                            parent.location.href = r.target;
                        } else {
                            if(r.msg=="验证码错误"){
                                _this.user.kaptcha=''
                            }else if(r.msg=="用户名或密码错误"){
                                _this.user={};
                            }
                            _this.renderKaptcha();
                            document.getElementById("check-result").innerText=r.msg;
                            _this.formLoading=false;
                        }
                        this.formLoading=false;
                    })
                        .catch(function (error) {
                            _this.error(error);
                            _this.formLoading=false;
                        });
                } else {
                    return false;
                }
            });
        },
    },
    created: function () {
        /*axios({
            url:this.targetUrl("/account/login"),
            method:'post',
            data:{username:'longjinbin',password:'123456'}
        }).then(function (response) {
            parent.location.href = '/';
        })*/
    }
}));
