package com.ljb.controller;

import com.ljb.annotion.MenuDescription;
import com.ljb.service.SystemService;
import com.ljb.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/11<br>
 * 描述: <br>
 */
@RestController
@RequestMapping("manage/recyle")
@MenuDescription(group = "资源中心", name = "回收管理", action = "manage/recyle.html")
public class RecyleController {
    @Autowired
    private SystemService systemService;

    @RequestMapping("/clearrecyledata")
    @MenuDescription(name = "清空回收站")
    public R clearData() {
        systemService.clearRecyleData();
        return R.ok();
    }

    @RequestMapping("/initsystem")
    @MenuDescription(name = "初始化系统")
    public R initSystem() {
        systemService.initSystem();
        return R.ok();
    }

}
