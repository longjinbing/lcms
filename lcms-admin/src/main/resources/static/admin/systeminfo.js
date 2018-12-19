var vm = new Vue(extend(common,{
    data: {
        soft: {
            osName: 'window',
            version: '10.0',
            jdkVersion: '1.8.0_101',
            server: 'Tomcat 8',
            database: 'Mysql 5.7',
            cache: 'Redis',
            runtime: 2000
        },
        dram: {
            totalMemory: 2048,
            maxMemory: 1600,
            freeMemory: 448
        },
        disk: {
            freeMemory: 0,
            totalMemory: 0,
            usageMemory: 0,
            percent: 0
        },
        tasklist: [],
        infolist: []
    },
    methods: {
        init: function () {
            var _this = this;
            axios.get(this.targetUrl("/actuator/disk")).then(function (r) {
                r=r.data;
                _this.disk = r.data;
            })
        },
        initData: function () {
            var _this = this;
            axios.get(this.targetUrl("/actuator/env")).then( function (r) {
                r=r.data;
                var systemProperties = r.propertySources[2].properties;
                _this.soft.osName = systemProperties["os.name"].value;
                _this.soft.version = systemProperties["os.version"].value.substring(0,10);
                _this.soft.jdkVersion = systemProperties["java.runtime.version"].value.substring(0,10);
            });
            axios.get(this.targetUrl("/actuator/metrics/process.uptime")).then( function (r) {
                r=r.data;
                var time = parseInt(r.measurements[0].value)
                _this.soft.runtime = _this.formatSeconds(time);
                setInterval(
                    function () {
                        time++;
                        _this.soft.runtime = _this.formatSeconds(time);
                    }, 1000
                )
            });
        },
        jvmInfo: function () {
            var _this = this;
            axios.get(this.targetUrl("/actuator/jvm")).then(function (r) {
                r=r.data;
                _this.dram = r.data;
            });
        },
        cpuUsage: function () {
            var _this = this;
            axios.get(this.targetUrl("/actuator/metrics/system.cpu.usage")).then(function (r) {
                r=r.data;
                option.series[0].data[0].value = (parseFloat(r.measurements[0].value) * 100).toFixed(2);
                cpuGaugeChart.setOption(option, true);
            });
        },
        clear: function () {
            this.success("清理成功");
            this.jvmInfo();
            this.cpuUsage();
        },
        formatSeconds: function (value) {
            var theTime = parseInt(value); // 秒
            var theTime1 = 0; // 分
            var theTime2 = 0; // 小时 //
            if (theTime > 60) {
                theTime1 = parseInt(theTime / 60);
                theTime = parseInt(theTime % 60);
                if (theTime1 > 60) {
                    theTime2 = parseInt(theTime1 / 60);
                    theTime1 = parseInt(theTime1 % 60);
                }
            }
            var result = '' + parseInt(theTime) + '秒';
            if (theTime1 > 0) {
                result = '' + parseInt(theTime1) + '分' + result;
            }
            if (theTime2 > 0) {
                result = '' + parseInt(theTime2) + '小时' + result;
            }
            return result;
        }
    },
    created: function () {
        this.init();
        this.initData();
        this.jvmInfo();
        this.cpuUsage();
        var _this = this;
        setInterval(
            function () {
                if(window.getComputedStyle(document.getElementsByTagName("body")[0], null).zIndex.trim()=="auto") {
                    _this.jvmInfo();
                    _this.cpuUsage();
                }
            }, 60000);
    }
}));
var option = {
    tooltip: {
        formatter: "{a} <br/>{b} : {c}%"
    },
    toolbox: {
        feature: {
            restore: {},
            saveAsImage: {}
        }
    },
    series: [
        {
            name: 'CPU占用',
            type: 'gauge',
            axisLine: {            // 坐标轴线
                lineStyle: {       // 属性lineStyle控制线条样式
                    width: 5
                }
            },
            splitLine: {           // 分隔线
                length: 15,         // 属性length控制线长
                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                    color: 'auto'
                }
            },
            detail: {
                formatter: '{value}%',
                fontSize: 14
            },
            data: [{value: 0, name: 'CPU占用'}]
        }
    ]
};
var cpuGaugeChart = echarts.init(document.getElementById('cpu-info'));
